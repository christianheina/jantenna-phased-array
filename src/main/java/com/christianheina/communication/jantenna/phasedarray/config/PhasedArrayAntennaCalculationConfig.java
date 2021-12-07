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

package com.christianheina.communication.jantenna.phasedarray.config;

/**
 * Configuration of calculation resources.<br>
 * Configuration will affect calculation times.
 * 
 * @author Christian Heina (developer@christianheina.com)
 */
public class PhasedArrayAntennaCalculationConfig {

    /**
     * Default Configuration, {@link DefaultConfig}.
     */
    public static final Config DEFAULT_CONFIG = new DefaultConfig();

    private static Config config = DEFAULT_CONFIG;

    private PhasedArrayAntennaCalculationConfig() {
        /* Hidden Constructor */
    }

    /**
     * Get active config
     * 
     * @return config
     */
    public static Config getConfig() {
        return config;
    }

    /**
     * Set active config
     * 
     * @param newConfig
     *            config to set as active
     */
    public static void setConfig(Config newConfig) {
        config = newConfig;
    }

}
