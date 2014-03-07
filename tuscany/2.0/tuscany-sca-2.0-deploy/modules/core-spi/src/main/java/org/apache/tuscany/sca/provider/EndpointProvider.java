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

package org.apache.tuscany.sca.provider;


/**
 * Defines the extra operation that service binding providers implement 
 * to configure the binding chain.
 * 
 * @version $Rev: 938098 $ $Date: 2010-04-26 16:42:28 +0100 (Mon, 26 Apr 2010) $
 * @tuscany.spi.extension.asclient
 */
public interface EndpointProvider extends ServiceBindingProvider {
    /**
     * Configure the binding invocation chain
     */
    void configure();
}
