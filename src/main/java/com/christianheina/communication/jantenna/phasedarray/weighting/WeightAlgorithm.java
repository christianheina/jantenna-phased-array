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

import com.christianheina.communication.jantenna.commons.ThetaPhi;
import com.christianheina.communication.jantenna.commons.Util;

/**
 * Interface for weight algorithm
 * 
 * @author Christian Heina (developer@christianheina.com)
 */
public interface WeightAlgorithm {

    /**
     * Calculate weight based on antenna element position
     * 
     * @param r
     *            antenna element position
     * 
     * @return complex weight representation for element
     */
    Complex calculateWeight(Vector3D r);

    /**
     * Create new instance of {@link ConjugateWeightAlgorithm}
     * 
     * @param lambda
     *            wavelength used to calculate weights
     * @param pointingDirection
     *            pointing direction to calculate weights
     * 
     * @return new instance
     */
    public static WeightAlgorithm newConjugateWeightAlgorithmFromLambda(double lambda, ThetaPhi pointingDirection) {
        return new ConjugateWeightAlgorithm(lambda, pointingDirection);
    }

    /**
     * Create new instance of {@link ConjugateWeightAlgorithm}
     * 
     * @param lambda
     *            wavelength used to calculate weights
     * @param pointingDirection
     *            pointing direction to calculate weights
     * 
     * @return new instance
     * 
     * @deprecated As of 2024-02-11 this is replaced by
     *             {@link com.christianheina.communication.jantenna.phasedarray.weighting.WeightAlgorithm#newConjugateWeightAlgorithmFromLambda(double, ThetaPhi)
     *             newConjugateWeightAlgorithmFromLambda(double lambda, ThetaPhi pointingDirection)}. This method will
     *             be supported until 2024-05-11 and removed after 2024-08-11.
     */
    @Deprecated
    public static WeightAlgorithm newConjugateWeightAlgorithm(double lambda, ThetaPhi pointingDirection) {
        return newConjugateWeightAlgorithmFromLambda(lambda, pointingDirection);
    }

    /**
     * Create new instance of {@link ConjugateWeightAlgorithm}
     * 
     * @param frequency
     *            frequency used to calculate lambda value used when calculating weights
     * @param pointingDirection
     *            pointing direction to calculate weights
     * 
     * @return new instance
     */
    public static WeightAlgorithm newConjugateWeightAlgorithmFromFrequency(double frequency,
            ThetaPhi pointingDirection) {
        double lambda = Util.calculateLambda(frequency);
        return newConjugateWeightAlgorithmFromLambda(lambda, pointingDirection);
    }

}
