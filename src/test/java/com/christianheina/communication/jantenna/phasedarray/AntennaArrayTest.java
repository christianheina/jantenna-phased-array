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

import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.christianheina.communication.jantenna.commons.ThetaPhi;
import com.christianheina.communication.jantenna.phasedarray.AntennaArray;
import com.christianheina.communication.jantenna.phasedarray.weighting.WeightAlgorithm;

/**
 * Unit test for {@link AntennaArray}.
 * 
 * @author Christian Heina (developer@christianheina.com)
 */
@SuppressWarnings("javadoc")
public class AntennaArrayTest {

    @Test
    public void builderTest() {
        AntennaArray aa = AntennaArray.newBuilder().addAntennaLocation(new Vector3D(0, 0, 0))
                .setWeightAlgorithm(WeightAlgorithm.newConjugateWeightAlgorithm(0, ThetaPhi.fromDegrees(90, 0)))
                .setDesignFrequency(2E6).build();
        Assert.assertEquals(aa.getAntennaArray().length, 1);
    }

    @Test
    public void fromEquallySpacedArrayTest() {
        AntennaArray aa = AntennaArray.fromEquallySpacedArray(1, 24, 16, 0, 0,
                WeightAlgorithm.newConjugateWeightAlgorithm(0, ThetaPhi.fromDegrees(90, 0)));
        Assert.assertEquals(aa.getAntennaArray().length, 384);
    }

}
