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

import org.testng.Assert;
import org.testng.annotations.Test;

import com.christianheina.communication.jantenna.commons.ThetaPhi;

/**
 * Unit test for {@link WeightAlgorithm}.
 * 
 * @author Christian Heina (developer@christianheina.com)
 */
@SuppressWarnings("javadoc")
public class WeightAlgorithmTest {

    @Test
    public void newConjugateWeightAlgorithmTest() {
        double freq = 28 * Math.pow(10, 9);
        ThetaPhi pointingDirection = ThetaPhi.fromDegrees(0, 0);
        WeightAlgorithm weightAlgorithm = WeightAlgorithm.newConjugateWeightAlgorithmFromFrequency(freq,
                pointingDirection);
        Assert.assertEquals(weightAlgorithm.getClass(), ConjugateWeightAlgorithm.class);
    }

}
