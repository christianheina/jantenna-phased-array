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

package com.christianheina.communication.jantenna.phasedarray.weighting;

import org.apache.commons.math3.complex.Complex;
import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.christianheina.communication.jantenna.commons.Constants;
import com.christianheina.communication.jantenna.commons.ThetaPhi;

/**
 * Unit test for {@link ConjugateWeightAlgorithm}.
 * 
 * @author Christian Heina (developer@christianheina.com)
 */
@SuppressWarnings("javadoc")
public class ConjugateWeightAlgorithmTest {

    private ConjugateWeightAlgorithm wa;

    @BeforeMethod
    public void setup() {
        double freq = 28 * Math.pow(10, 9);
        double lambda = Constants.VACCUM_SPEED_OF_LIGHT / freq;
        ThetaPhi pointingDirection = ThetaPhi.fromDegrees(0, 0);
        wa = new ConjugateWeightAlgorithm(lambda, pointingDirection);
    }

    @Test
    public void calculateWeightTest() {
        Complex weight = wa.calculateWeight(new Vector3D(0, 0, 0));
        Assert.assertEquals(weight, Complex.ONE);
    }

}
