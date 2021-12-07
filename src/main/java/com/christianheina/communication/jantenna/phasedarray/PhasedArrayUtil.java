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

import com.christianheina.communication.jantenna.commons.ThetaPhi;

/**
 * Util functionality related to phased array calculations.
 * 
 * @author Christian Heina (developer@christianheina.com)
 */
public class PhasedArrayUtil {

    private PhasedArrayUtil() {
        /* Hidden Constructor */
    }

    /**
     * Calculates wave vector.<br>
     * Wave Vector is a vector describing the phase variation of a plane wave.
     * 
     * @param lambda
     *            wavelength
     * @param thetaPhi
     *            spherical coordinates according to IEEE format
     * 
     * @return wave vector
     */
    public static Vector3D calculateWaveVector(double lambda, ThetaPhi thetaPhi) {
        return new Vector3D(Math.sin(thetaPhi.getTheta()) * Math.cos(thetaPhi.getPhi()),
                Math.sin(thetaPhi.getTheta()) * Math.sin(thetaPhi.getPhi()), Math.cos(thetaPhi.getTheta()))
                        .scalarMultiply(2 * Math.PI / lambda);
    }

    /**
     * Calculate steering vector.
     * 
     * @param k
     *            wave vector
     * @param r
     *            antenna element location
     * 
     * @return steering vector
     */
    public static Complex calculateSteeringVector(Vector3D k, Vector3D r) {
        return new Complex(0, -1 * k.dotProduct(r)).exp();
    }

}
