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

package org.apache.tuscany.sca.binding.rest.wireformat.json;

/**
 * JSON Wireformat factory for REST Binding
 * 
 * @version $Rev: 937850 $ $Date: 2010-04-25 18:52:22 +0100 (Sun, 25 Apr 2010) $
 */
public interface JSONWireFormatFactory {

    /**
     * Create a new JSON wireformat for REST Binding
     * @return the new JSON wire format
     */
    JSONWireFormat createRESTWireFormatJSON();
}
