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
package org.apache.tuscany.sca.itest.oneway;

/**
 * The client for the oneway itest.
 *
 * @version $Rev: 905553 $ $Date: 2010-02-02 09:40:38 +0000 (Tue, 02 Feb 2010) $
 */
public interface OneWayClient { 

    /**
     * This method will invoke the doSomething() @OneWay method on the OneWayService
     * the specified number of times.
     * 
     * @param count The number of times to invoke doSomething() on the OneWayService
     */
    void doSomething(int count);

    /**
     * This method will invoke a @OneWay method that throws an exception.
     */
    void doSomethingWhichThrowsException();
}
