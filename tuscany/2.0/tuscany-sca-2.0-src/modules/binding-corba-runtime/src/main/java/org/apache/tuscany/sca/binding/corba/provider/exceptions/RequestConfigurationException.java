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

package org.apache.tuscany.sca.binding.corba.provider.exceptions;

/**
 * @version $Rev: 697647 $ $Date: 2008-09-22 01:52:08 +0100 (Mon, 22 Sep 2008) $ 
 */
public class RequestConfigurationException extends Exception {

    private static final long serialVersionUID = 1L;
    private String objectId;

    public RequestConfigurationException(String message, String objectId) {
        super(message + ", object id was: " + objectId);
        this.objectId = objectId;
    }

    public RequestConfigurationException(String message) {
        super(message);
    }

    public String getType() {
        return objectId;
    }

}
