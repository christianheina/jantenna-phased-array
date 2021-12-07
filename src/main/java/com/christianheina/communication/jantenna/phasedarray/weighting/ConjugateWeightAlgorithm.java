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
import com.christianheina.communication.jantenna.phasedarray.PhasedArrayUtil;

/**
 * Weights calculated using the conjugate of the steering vector based on provided {@link ThetaPhi} pointing direction.
 * 
 * @author Christian Heina (developer@christianheina.com)
 */
public class ConjugateWeightAlgorithm implements WeightAlgorithm {

    private double lambda;
    private ThetaPhi pointingDirection;

    ConjugateWeightAlgorithm(double lambda, ThetaPhi pointingDirection) {
        this.lambda = lambda;
        this.pointingDirection = pointingDirection;
    }

    @Override
    public Complex calculateWeight(Vector3D r) {
        Vector3D k = PhasedArrayUtil.calculateWaveVector(lambda, pointingDirection);
        return PhasedArrayUtil.calculateSteeringVector(k, r).conjugate();
    }

}
