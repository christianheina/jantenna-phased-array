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

package examples;

import java.util.List;

import com.christianheina.communication.jantenna.commons.Field;
import com.christianheina.communication.jantenna.commons.ThetaPhi;
import com.christianheina.communication.jantenna.commons.Util;
import com.christianheina.communication.jantenna.phasedarray.AntennaArray;
import com.christianheina.communication.jantenna.phasedarray.ArrayFactor;
import com.christianheina.communication.jantenna.phasedarray.weighting.WeightAlgorithm;

/**
 * Example of array factor calculation including creation of antenna array and elements.
 * 
 * @author Christian Heina (developer@christianheina.com)
 */
@SuppressWarnings("javadoc")
public class ArrayFactorExample {

    @SuppressWarnings("unused")
    public static void main(String[] args) {
        double freq = 2 * Math.pow(10, 9);
        double lambda = Util.calculateLambda(freq);
        List<ThetaPhi> angleList = ThetaPhi.equallySpacedSphere(1);
        AntennaArray antennaArray = AntennaArray.fromEquallySpacedArray(1, 8, 4, 0.5, freq,
                WeightAlgorithm.newConjugateWeightAlgorithm(lambda, ThetaPhi.fromDegrees(103, -1.5)));
        Field field = ArrayFactor.newArrayFactorAsync(freq, antennaArray, angleList);
    }
}
