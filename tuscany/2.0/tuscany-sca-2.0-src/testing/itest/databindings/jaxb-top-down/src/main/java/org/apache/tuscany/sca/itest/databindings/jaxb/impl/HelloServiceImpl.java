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

package org.apache.tuscany.sca.itest.databindings.jaxb.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.tuscany.sca.itest.databindings.jaxb.HelloService;
import org.oasisopen.sca.annotation.Service;

/**
 * An implementation of HelloService.
 * 
 * @version $Rev: 903668 $ $Date: 2010-01-27 15:04:56 +0000 (Wed, 27 Jan 2010) $
 */
@Service(HelloService.class)
public class HelloServiceImpl implements HelloService {
    public String getGreetings(String name) {
        return "Hello " + name;
    }

    public String[] getGreetingsArray(String[] names) {
        String[] resps = new String[names.length];
        for (int i = 0; i < names.length; ++i) {
            resps[i] = "Hello " + names[i];
        }
        return resps;
    }

    public List<String> getGreetingsList(List<String> names) {
        List<String> resps = new ArrayList<String>();
        for (int i = 0; i < names.size(); ++i) {
            resps.add("Hello " + names.get(i));
        }
        return resps;
    }

    public ArrayList<String> getGreetingsArrayList(ArrayList<String> names) {
        ArrayList<String> resps = new ArrayList<String>();
        for (int i = 0; i < names.size(); ++i) {
            resps.add("Hello " + names.get(i));
        }
        return resps;
    }

    public Map<String, String> getGreetingsMap(Map<String, String> namesMap) {
        for (Map.Entry<String, String> entry : namesMap.entrySet()) {
            entry.setValue("Hello " + entry.getKey());
        }
        return namesMap;
    }

    public HashMap<String, String> getGreetingsHashMap(HashMap<String, String> namesMap) {
        for (Map.Entry<String, String> entry : namesMap.entrySet()) {
            entry.setValue("Hello " + entry.getKey());
        }
        return namesMap;
    }

    public String getGreetingsVarArgs(String... names) {
        String resp = "Hello";
        for(int i = 0; i < names.length; ++i) {
            resp += (" "+names[i]);
        }
        return resp;
    }
}
