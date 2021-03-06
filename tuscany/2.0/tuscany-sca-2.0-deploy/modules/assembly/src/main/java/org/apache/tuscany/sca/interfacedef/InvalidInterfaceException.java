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
package org.apache.tuscany.sca.interfacedef;

/**
 * @version $Rev: 938056 $ $Date: 2010-04-26 15:12:30 +0100 (Mon, 26 Apr 2010) $
 * @tuscany.spi.extension.inheritfrom
 */
public abstract class InvalidInterfaceException extends Exception {
    private static final long serialVersionUID = -3850574026273544538L;

    public InvalidInterfaceException() {
        super();
    }

    public InvalidInterfaceException(String message) {
        super(message);
    }

    public InvalidInterfaceException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidInterfaceException(Throwable cause) {
        super(cause);
    }
}
