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

import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;

import com.christianheina.communication.jantenna.commons.Util;
import com.christianheina.communication.jantenna.commons.WeightableElement;
import com.christianheina.communication.jantenna.phasedarray.weighting.WeightAlgorithm;

/**
 * Antenna Array model
 * 
 * @author Christian Heina (developer@christianheina.com)
 */
public class AntennaArray {

    private WeightableElement[] antennaArray;

    private AntennaArray(WeightableElement[] antennaArray) {
        this.antennaArray = antennaArray;
    }

    /**
     * Create antenna array with same lambda spacing between all elements in the array.
     * 
     * @param sizeX
     *            size in X dimension. Must be 1
     * @param sizeY
     *            size in Y dimension.
     * @param sizeZ
     *            size in Z dimension.
     * @param spacingX
     *            the element spacing in X-dimension in lambda (wavelengths).<br>
     *            For example 0.5
     * @param spacingY
     *            the element spacing in Y-dimension in lambda (wavelengths).<br>
     *            For example 0.5
     * @param spacingZ
     *            the element spacing in Z-dimension in lambda (wavelengths).<br>
     *            For example 0.5
     * @param designFrequency
     *            the design frequency of the array.<br>
     *            This along with spacing will determine the distance between each element in the array.
     * @param weightAlgorithm
     *            the algorithm used to calculate the weight
     * 
     * @return new {@link AntennaArray} instance
     * 
     * @exception IllegalArgumentException
     *                sizeX, sizeY or sizeZ is less than 1.
     */
    public static AntennaArray fromEquallySpacedArray(int sizeX, int sizeY, int sizeZ, double spacingX, double spacingY,
            double spacingZ, double designFrequency, WeightAlgorithm weightAlgorithm) {
        if (sizeX < 1 || sizeY < 1 || sizeZ < 1) {
            throw new IllegalArgumentException(
                    "Array size in X, Y or Z dimension is less than 1. Actual size, (X, Y, X), is (" + sizeX + ", "
                            + sizeY + ", " + sizeZ + ").");
        }
        double lambdaDistance = Util.calculateLambda(designFrequency);
        double distanceX = calculateDistance(spacingX, lambdaDistance);
        double distanceY = calculateDistance(spacingY, lambdaDistance);
        double distanceZ = calculateDistance(spacingZ, lambdaDistance);
        WeightableElement[] antennaArray = new WeightableElement[sizeX * sizeY * sizeZ];
        int arrayIndex = 0;
        for (int xInd = 0; xInd < sizeX; xInd++) {
            for (int yInd = 0; yInd < sizeY; yInd++) {
                for (int zInd = 0; zInd < sizeZ; zInd++) {
                    Vector3D r = new Vector3D(xInd * distanceX, yInd * distanceY, zInd * distanceZ);
                    antennaArray[arrayIndex] = new WeightableElement(r, designFrequency,
                            weightAlgorithm.calculateWeight(r));
                    arrayIndex++;
                }
            }
        }
        return new AntennaArray(antennaArray);
    }

    private static double calculateDistance(double spacingLambda, double lambdaDistance) {
        return spacingLambda * lambdaDistance;
    }

    /**
     * Create antenna array with same lambda spacing between all elements in the array.
     * 
     * @param sizeX
     *            size in X dimension.
     * @param sizeY
     *            size in Y dimension.
     * @param sizeZ
     *            size in Z dimension.
     * @param spacing
     *            the element spacing in lambda (wavelengths).<br>
     *            For example 0.5
     * @param designFrequency
     *            the design frequency of the array.<br>
     *            This along with spacing will determine the distance between each element in the array.
     * @param weightAlgorithm
     *            the algorithm used to calculate the weight
     * 
     * @return new {@link AntennaArray} instance
     */
    public static AntennaArray fromEquallySpacedArray(int sizeX, int sizeY, int sizeZ, double spacing,
            double designFrequency, WeightAlgorithm weightAlgorithm) {
        return fromEquallySpacedArray(sizeX, sizeY, sizeZ, spacing, spacing, spacing, designFrequency, weightAlgorithm);
    }

    /**
     * Create antenna array with same lambda spacing between all elements in the array.
     * 
     * @param sizeY
     *            size in Y dimension.
     * @param sizeZ
     *            size in Z dimension.
     * @param spacingY
     *            the element spacing in Y-dimension in lambda (wavelengths).<br>
     *            For example 0.5
     * @param spacingZ
     *            the element spacing in Z-dimension in lambda (wavelengths).<br>
     *            For example 0.5
     * @param designFrequency
     *            the design frequency of the array.<br>
     *            This along with spacing will determine the distance between each element in the array.
     * @param weightAlgorithm
     *            the algorithm used to calculate the weight
     * 
     * @return new {@link AntennaArray} instance
     */
    public static AntennaArray fromEquallySpacedArray(int sizeY, int sizeZ, double spacingY, double spacingZ,
            double designFrequency, WeightAlgorithm weightAlgorithm) {
        return fromEquallySpacedArray(1, sizeY, sizeZ, 0, spacingY, spacingZ, designFrequency, weightAlgorithm);
    }

    /**
     * Create antenna array with same lambda spacing between all elements in the array.
     * 
     * @param sizeY
     *            size in Y dimension.
     * @param sizeZ
     *            size in Z dimension.
     * @param spacing
     *            the element spacing in lambda (wavelengths).<br>
     *            For example 0.5
     * @param designFrequency
     *            the design frequency of the array.<br>
     *            This along with spacing will determine the distance between each element in the array.
     * @param weightAlgorithm
     *            the algorithm used to calculate the weight
     * 
     * @return new {@link AntennaArray} instance
     */
    public static AntennaArray fromEquallySpacedArray(int sizeY, int sizeZ, double spacing, double designFrequency,
            WeightAlgorithm weightAlgorithm) {
        return fromEquallySpacedArray(sizeY, sizeZ, spacing, spacing, designFrequency, weightAlgorithm);
    }

    /**
     * Get elements in antenna array.
     * 
     * @return weightable elements in array.
     */
    public WeightableElement[] getAntennaArray() {
        return antennaArray;
    }

    /**
     * Create new instance of {@link Builder}
     * 
     * @return new {@link Builder}
     */
    public static Builder newBuilder() {
        return new Builder();
    }

    /**
     * Builder for {@link AntennaArray}
     * 
     * @author Christian Heina (developer@christianheina.com)
     */
    public static class Builder {

        private List<Vector3D> antennaLocationList = new ArrayList<>();
        private double designFrequency = 0;
        private WeightAlgorithm weightAlgorithm;

        private Builder() {
            /* Hidden Constructor */
        }

        /**
         * Add antenna location to array
         * 
         * @param antennaLocation
         *            location to add
         * 
         * @return this instance.
         */
        public Builder addAntennaLocation(Vector3D antennaLocation) {
            antennaLocationList.add(antennaLocation);
            return this;
        }

        /**
         * Set antenna array design frequency
         * 
         * @param designFrequency
         *            frequency to set
         * 
         * @return this instance.
         */
        public Builder setDesignFrequency(double designFrequency) {
            this.designFrequency = designFrequency;
            return this;
        }

        /**
         * Set weight algorithm.
         * 
         * @param weightAlgorithm
         *            weight algorithm to set
         * 
         * @return this instance.
         */
        public Builder setWeightAlgorithm(WeightAlgorithm weightAlgorithm) {
            this.weightAlgorithm = weightAlgorithm;
            return this;
        }

        /**
         * Build {@link AntennaArray} using this builder.
         * 
         * @return new instance of {@link AntennaArray}
         */
        public AntennaArray build() {
            WeightableElement[] antennaArray = new WeightableElement[antennaLocationList.size()];
            for (int i = 0; i < antennaLocationList.size(); i++) {
                antennaArray[i] = new WeightableElement(antennaLocationList.get(i), designFrequency,
                        weightAlgorithm.calculateWeight(antennaLocationList.get(i)));
            }
            return new AntennaArray(antennaArray);
        }
    }

}
