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
 * Configuration API used by phase array antenna calculations.
 * 
 * @author Christian Heina (developer@christianheina.com)
 */
public interface Config {

    /**
     * Retrieve number of threads available for use.
     * 
     * @return number of threads
     */
    int getNumberOfThreads();

}
