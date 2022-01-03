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
import java.util.concurrent.Executors;

import org.apache.commons.math3.complex.Complex;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.christianheina.communication.jantenna.commons.Constants;
import com.christianheina.communication.jantenna.commons.Field;
import com.christianheina.communication.jantenna.commons.ThetaPhi;
import com.christianheina.communication.jantenna.commons.Util;
import com.christianheina.communication.jantenna.phasedarray.weighting.WeightAlgorithm;

/**
 * Unit test for {@link PhasedArrayAntenna}.
 * 
 * @author Christian Heina (developer@christianheina.com)
 */
@SuppressWarnings("javadoc")
public class PhasedArrayAntennaTest {

    @Test
    public void newArrayFactorAsyncSuppliedExecutorServiceTest() {
        double freq = 28 * Math.pow(10, 9);
        // double lambda = Constants.VACCUM_SPEED_OF_LIGHT / freq;
        List<ThetaPhi> angleList = ThetaPhi.equallySpacedSphere(1);
        ThetaPhi steeringAngle = ThetaPhi.fromDegrees(90, 0);
        AntennaArray antennaArray = AntennaArray.fromEquallySpacedArray(1, 24, 16, 0.5, freq,
                WeightAlgorithm.newConjugateWeightAlgorithm(Util.calculateLambda(freq), steeringAngle));
        Field field = PhasedArrayAntenna.newArrayFactorAsync(Executors.newSingleThreadExecutor(), freq, antennaArray,
                angleList);
        Assert.assertEquals(field.getThetaPhiList().size(), angleList.size());
        field.getAvailableElectricFields().forEach(electricFieldName -> {
            List<Complex> electricFieldList = field.getElectricField(electricFieldName);
            double max = Double.MIN_VALUE;
            List<ThetaPhi> maxAngleList = new ArrayList<>();
            for (int i = 0; i < electricFieldList.size(); i++) {
                if (electricFieldList.get(i).abs() >= max) {
                    max = electricFieldList.get(i).abs();
                    maxAngleList.add(angleList.get(i));
                }
            }
            boolean hasAngleInList = false;
            for (ThetaPhi thetaPhi : maxAngleList) {
                if (thetaPhi.getTheta() == steeringAngle.getTheta() && thetaPhi.getPhi() == steeringAngle.getPhi()) {
                    hasAngleInList = true;
                }
            }
            Assert.assertTrue(hasAngleInList);
        });

    }

    @Test
    public void newArrayFactorAsyncTest() {
        double freq = 28 * Math.pow(10, 9);
        double lambda = Constants.VACCUM_SPEED_OF_LIGHT / freq;
        List<ThetaPhi> angleList = ThetaPhi.equallySpacedSphere(1);
        ThetaPhi steeringAngle = ThetaPhi.fromDegrees(90, 0);
        AntennaArray antennaArray = AntennaArray.fromEquallySpacedArray(1, 24, 16, lambda / 2, freq,
                WeightAlgorithm.newConjugateWeightAlgorithm(lambda, steeringAngle));
        Field field = PhasedArrayAntenna.newArrayFactorAsync(freq, antennaArray, angleList);
        Assert.assertEquals(field.getThetaPhiList().size(), angleList.size());
        field.getAvailableElectricFields().forEach(electricFieldName -> {
            List<Complex> electricFieldList = field.getElectricField(electricFieldName);
            double max = Double.MIN_VALUE;
            List<ThetaPhi> maxAngleList = new ArrayList<>();
            for (int i = 0; i < electricFieldList.size(); i++) {
                if (electricFieldList.get(i).abs() >= max) {
                    max = electricFieldList.get(i).abs();
                    maxAngleList.add(angleList.get(i));
                }
            }
            boolean hasAngleInList = false;
            for (ThetaPhi thetaPhi : maxAngleList) {
                if (thetaPhi.getTheta() == steeringAngle.getTheta() && thetaPhi.getPhi() == steeringAngle.getPhi()) {
                    hasAngleInList = true;
                }
            }
            Assert.assertTrue(hasAngleInList);
        });

    }

}
