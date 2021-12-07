/*
 * Copyright 2021 Christian Heina
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.christianheina.communication.jantenna.phasedarray;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.math3.complex.Complex;
import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;

import com.christianheina.communication.jantenna.commons.Constants;
import com.christianheina.communication.jantenna.commons.ElectricField;
import com.christianheina.communication.jantenna.commons.Field;
import com.christianheina.communication.jantenna.commons.FieldType;
import com.christianheina.communication.jantenna.commons.ThetaPhi;
import com.christianheina.communication.jantenna.commons.WeightableElement;
import com.christianheina.communication.jantenna.phasedarray.config.PhasedArrayAntennaCalculationConfig;
import com.christianheina.communication.jantenna.phasedarray.exceptions.PhasedArrayAntennaException;

/**
 * Phased array functionality.
 * 
 * @author Christian Heina (developer@christianheina.com)
 */
public class PhasedArrayAntenna {

    private PhasedArrayAntenna() {
        /* Hidden Constructor */ }

    /**
     * Create array factor
     * 
     * @param frequency
     *            wavelength
     * @param antennaArray
     *            antenna array to use when creating array factor
     * @param angles
     *            angles used in the created array factor
     * 
     * @return a field containing the array factor
     */
    public static Field newArrayFactorAsync(double frequency, AntennaArray antennaArray, List<ThetaPhi> angles) {
        ExecutorService executorService = Executors
                .newFixedThreadPool(PhasedArrayAntennaCalculationConfig.getConfig().getNumberOfThreads());
        return newArrayFactorAsync(executorService, frequency, antennaArray, angles);
    }

    /**
     * Create array factor
     * 
     * @param executorService
     *            the ExecutorService used for asynchronous array factor calculations
     * @param frequency
     *            wavelength
     * @param antennaArray
     *            antenna array to use when creating array factor
     * @param angles
     *            angles used in the created array factor
     * 
     * @return a field containing the array factor
     */
    public static Field newArrayFactorAsync(ExecutorService executorService, double frequency,
            AntennaArray antennaArray, List<ThetaPhi> angles) {
        double lambda = Constants.VACCUM_SPEED_OF_LIGHT / frequency;
        @SuppressWarnings("unchecked")
        CompletableFuture<Complex>[] futureArray = new CompletableFuture[angles.size()];
        for (int i = 0; i < angles.size(); i++) {
            futureArray[i] = calculateArrayFactorAsync(executorService, lambda, antennaArray, angles.get(i));
        }
        executorService.shutdown();
        try {
            CompletableFuture.allOf(futureArray).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new PhasedArrayAntennaException(
                    "Encountered unexpected exception while asynchronously calculating array factor", e);
        }
        List<Complex> fieldDataList = new ArrayList<>(angles.size());
        for (int i = 0; i < futureArray.length; i++) {
            try {
                fieldDataList.add(futureArray[i].get());
            } catch (InterruptedException | ExecutionException e) {
                throw new PhasedArrayAntennaException(
                        "Encountered unexpected exception while asynchronously calculating array factor", e);
            }
        }

        return Field.newBuilder().setThetaPhiList(angles).addElectricField(ElectricField.RELATIVE_GAIN, fieldDataList)
                .setFreqency(frequency).setFieldType(FieldType.FARFIELD).build();
    }

    private static CompletableFuture<Complex> calculateArrayFactorAsync(ExecutorService executorService, double lambda,
            AntennaArray antennaArray, ThetaPhi angle) {
        CompletableFuture<Complex> future = new CompletableFuture<>();

        executorService.execute(() -> {
            Complex sum = Complex.ZERO;
            Vector3D k = PhasedArrayUtil.calculateWaveVector(lambda, angle);
            for (WeightableElement antenna : antennaArray.getAntennaArray()) {
                Complex vk = PhasedArrayUtil.calculateSteeringVector(k, antenna.getElementLocation());
                Complex c = antenna.getElementWeight().multiply(vk);
                sum = sum.add(c);
            }
            future.complete(sum);
        });
        future.exceptionally(e -> {
            throw new PhasedArrayAntennaException(
                    "Encountered unexpected exception while asynchronously calculating array factor", e);
        });
        return future;
    }

}
