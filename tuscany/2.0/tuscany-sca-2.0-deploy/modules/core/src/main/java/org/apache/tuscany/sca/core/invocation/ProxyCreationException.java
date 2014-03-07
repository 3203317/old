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
package org.apache.tuscany.sca.core.invocation;

import org.apache.tuscany.sca.core.factory.ObjectCreationException;


/**
 * Denotes an error creating a proxy
 *
 * @version $Rev: 644844 $ $Date: 2008-04-04 20:32:38 +0100 (Fri, 04 Apr 2008) $
 */
public class ProxyCreationException extends ObjectCreationException {
    private static final long serialVersionUID = 8002454344828513781L;

    public ProxyCreationException() {
        super();
    }

    public ProxyCreationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProxyCreationException(String message) {
        super(message);
    }

    public ProxyCreationException(Throwable cause) {
        super(cause);
    }

}
