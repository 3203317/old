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

package helloworld;

import org.apache.tuscany.implementation.bpel.example.helloworld.HelloPortType;
import org.oasisopen.sca.annotation.Reference;

/**
 * @version $Rev: 783234 $ $Date: 2009-06-10 07:53:12 +0100 (Wed, 10 Jun 2009) $
 */
public class HelloWorld {
    private HelloPortType helloService;
    
    public HelloWorld() {
        super();
    }
    
    @Reference
    public void setHelloService(HelloPortType helloService){
        this.helloService = helloService;
    }
    
    public String hello(String hello) throws java.rmi.RemoteException {
        System.out.println(">>> Invoking helloService.hello with : " + hello);
        return this.helloService.hello(hello);
    }

}