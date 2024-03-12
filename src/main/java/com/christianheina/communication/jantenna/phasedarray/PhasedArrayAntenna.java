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

import java.util.List;
import java.util.concurrent.ExecutorService;

import com.christianheina.communication.jantenna.commons.Field;
import com.christianheina.communication.jantenna.commons.ThetaPhi;

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
     * 
     * @deprecated As of 2024-03-13 this is replaced by
     *             {@link com.christianheina.communication.jantenna.phasedarray.ArrayFactor#newArrayFactorAsync(double, AntennaArray, List)
     *             newArrayFactorAsync(double frequency, AntennaArray antennaArray, List angles)}. This method will be
     *             supported until 2024-06-13 and removed 2024-09-13.
     */
    @Deprecated
    public static Field newArrayFactorAsync(double frequency, AntennaArray antennaArray, List<ThetaPhi> angles) {
        return ArrayFactor.newArrayFactorAsync(frequency, antennaArray, angles);
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
     * 
     * @deprecated As of 2024-03-13 this is replaced by
     *             {@link com.christianheina.communication.jantenna.phasedarray.ArrayFactor#newArrayFactorAsync(ExecutorService, double, AntennaArray, List)
     *             newArrayFactorAsync(ExecutorService executorService, double frequency, AntennaArray antennaArray,
     *             List angles)}. This method will be supported until 2024-06-13 and removed 2024-09-13.
     */
    @Deprecated
    public static Field newArrayFactorAsync(ExecutorService executorService, double frequency,
            AntennaArray antennaArray, List<ThetaPhi> angles) {
        return ArrayFactor.newArrayFactorAsync(executorService, frequency, antennaArray, angles);
    }

    /**
     * Create phased array. <br>
     * Both {@code arrayFactor} and {@code averageEmbeddedAntennaField} must contain same angles.
     * 
     * @param averageEmbeddedAntennaField
     *            the average embedded field.
     * @param arrayFactor
     *            the array factor field.
     * 
     * @return a field containing the phased array
     */
    public static Field newPhasedArray(Field averageEmbeddedAntennaField, Field arrayFactor) {
        return averageEmbeddedAntennaField.multiply(arrayFactor);
    }

    /**
     * Create phased array
     * 
     * @param averageEmbeddedAntennaField
     *            the average embedded field. Must contain same angles as the {@code angles}
     * @param frequency
     *            wavelength
     * @param antennaArray
     *            antenna array to use when creating phased array
     * @param angles
     *            angles used in the created phased array
     * 
     * @return a field containing the phased array
     */
    public static Field newPhasedArrayAsync(Field averageEmbeddedAntennaField, double frequency,
            AntennaArray antennaArray, List<ThetaPhi> angles) {
        Field arrayFactor = ArrayFactor.newArrayFactorAsync(frequency, antennaArray, angles);
        return newPhasedArray(averageEmbeddedAntennaField, arrayFactor);
    }

    /**
     * Create phased array
     * 
     * @param averageEmbeddedAntennaField
     *            the average embedded field. Must contain same angles as the {@code angles}
     * @param executorService
     *            the ExecutorService used for asynchronous array factor calculations
     * @param frequency
     *            wavelength
     * @param antennaArray
     *            antenna array to use when creating phased array
     * @param angles
     *            angles used in the created phased array
     * 
     * @return a field containing the phased array
     */
    public static Field newPhasedArrayAsync(Field averageEmbeddedAntennaField, ExecutorService executorService,
            double frequency, AntennaArray antennaArray, List<ThetaPhi> angles) {
        Field arrayFactor = ArrayFactor.newArrayFactorAsync(executorService, frequency, antennaArray, angles);
        return newPhasedArray(averageEmbeddedAntennaField, arrayFactor);
    }

}
