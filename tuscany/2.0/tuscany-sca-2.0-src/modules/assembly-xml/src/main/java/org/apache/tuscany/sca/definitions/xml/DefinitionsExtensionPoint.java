/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.apache.tuscany.sca.definitions.xml;

import java.net.URL;
import java.util.List;

import org.apache.tuscany.sca.definitions.Definitions;

/**
 * An extension point for built-in SCA intent/policySet definition files
 *
 * @version $Rev: 796166 $ $Date: 2009-07-21 08:03:47 +0100 (Tue, 21 Jul 2009) $
 */
public interface DefinitionsExtensionPoint {
    String DEFINITIONS_FILE = "org.apache.tuscany.sca.definitions.xml.Definitions";

    /**
     * Add a definitions document
     *
     * @param url the URL of the definitions
     */
    void addDefinitionsDocument(URL url);

    /**
     * Remove a definitions document
     *
     * @param url the URL of the definitions
     */
    void removeDefinitionsDocument(URL url);
    
    /**
     * @param definitions
     */
    void addDefinitions(Definitions definitions);
    /**
     * @param definitions
     */
    void removeDefinitions(Definitions definitions);

    /**
     * Get the list of definitions
     * @return A list of definitions
     */
    List<Definitions> getDefinitions();

}
