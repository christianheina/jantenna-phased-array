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

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.christianheina.communication.jantenna.phasedarray.config.Config;
import com.christianheina.communication.jantenna.phasedarray.config.DefaultConfig;
import com.christianheina.communication.jantenna.phasedarray.config.PhasedArrayAntennaCalculationConfig;

/**
 * Unit test for {@link PhasedArrayAntennaCalculationConfig}.
 * 
 * @author Christian Heina (developer@christianheina.com)
 */
@SuppressWarnings("javadoc")
public class PhasedArrayAntennaCalculationConfigTest {

    @Test(priority = 1)
    public void getConfigTest() {
        Config config = PhasedArrayAntennaCalculationConfig.getConfig();
        Assert.assertEquals(config.getClass(), DefaultConfig.class);
    }

    @Test(priority = 2)
    public void setConfigTest() {
        PhasedArrayAntennaCalculationConfig.setConfig(new CustomConfig());
        Config config = PhasedArrayAntennaCalculationConfig.getConfig();
        Assert.assertEquals(config.getClass(), CustomConfig.class);
    }

    @AfterMethod
    public void restore() {
        PhasedArrayAntennaCalculationConfig.setConfig(PhasedArrayAntennaCalculationConfig.DEFAULT_CONFIG);
    }

    private static class CustomConfig implements Config {

        @Override
        public int getNumberOfThreads() {
            return 1;
        }

    }

}
