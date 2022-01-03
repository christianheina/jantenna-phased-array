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

import org.apache.commons.math3.complex.Complex;
import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.christianheina.communication.jantenna.commons.Constants;
import com.christianheina.communication.jantenna.commons.ThetaPhi;

/**
 * Unit test for {@link PhasedArrayUtil}.
 * 
 * @author Christian Heina (developer@christianheina.com)
 */
@SuppressWarnings("javadoc")
public class PhasedArrayUtilTest {

    private static final double THRESHOLD = 0.0000000000001;

    @Test
    public void calculateWaveVectorTest() {
        double freq = 28 * Math.pow(10, 9);
        double lambda = Constants.VACCUM_SPEED_OF_LIGHT / freq;
        Vector3D k = PhasedArrayUtil.calculateWaveVector(lambda, ThetaPhi.fromDegrees(90, 0));
        Assert.assertTrue(Math.abs(k.getX() - 2 * Math.PI / lambda) < THRESHOLD);
        Assert.assertTrue(Math.abs(k.getY() - 0) < THRESHOLD);
        Assert.assertTrue(Math.abs(k.getZ() - 0) < THRESHOLD);
    }

    @Test
    public void calculateSteeringVectorTest() {
        double freq = 28 * Math.pow(10, 9);
        double lambda = Constants.VACCUM_SPEED_OF_LIGHT / freq;
        Vector3D k = PhasedArrayUtil.calculateWaveVector(lambda, ThetaPhi.fromDegrees(0, 0));
        Complex vk = PhasedArrayUtil.calculateSteeringVector(k, new Vector3D(0, 0, 0));
        Complex expected = Complex.ONE;
        Assert.assertTrue(Math.abs(vk.getReal() - expected.getReal()) < THRESHOLD);
        Assert.assertTrue(Math.abs(vk.getImaginary() - expected.getImaginary()) < THRESHOLD);
    }
}
