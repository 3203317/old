/*
ah * Licensed to the Apache Software Foundation (ASF) under one
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

package org.apache.tuscany.sca.binding.sca.provider;

import org.apache.tuscany.sca.assembly.SCABinding;
import org.apache.tuscany.sca.core.ExtensionPointRegistry;
import org.apache.tuscany.sca.core.UtilityExtensionPoint;
import org.apache.tuscany.sca.provider.BindingProviderFactory;
import org.apache.tuscany.sca.provider.ReferenceBindingProvider;
import org.apache.tuscany.sca.provider.SCABindingMapper;
import org.apache.tuscany.sca.provider.ServiceBindingProvider;
import org.apache.tuscany.sca.runtime.RuntimeEndpoint;
import org.apache.tuscany.sca.runtime.RuntimeEndpointReference;

/**
 * The factory for creating SCA Binding providers
 * 
 * @version $Rev: 1078431 $ $Date: 2011-03-06 09:12:37 +0000 (Sun, 06 Mar 2011) $
 */
public class RuntimeSCABindingProviderFactory implements BindingProviderFactory<SCABinding> {
    
    private ExtensionPointRegistry extensionPoints;
    private SCABindingMapper scaBindingMapper;
    
    public RuntimeSCABindingProviderFactory(ExtensionPointRegistry extensionPoints) {
        this.extensionPoints = extensionPoints; 
        UtilityExtensionPoint utilities = extensionPoints.getExtensionPoint(UtilityExtensionPoint.class);
        this.scaBindingMapper = utilities.getUtility(SCABindingMapper.class);
    } 
    
    public ReferenceBindingProvider createReferenceBindingProvider(RuntimeEndpointReference endpointReference) {
        return  new RuntimeSCAReferenceBindingProvider(extensionPoints, endpointReference);
    }

    public ServiceBindingProvider createServiceBindingProvider(RuntimeEndpoint endpoint) {
        return new RuntimeSCAServiceBindingProvider(scaBindingMapper, endpoint);
    }

    public Class<SCABinding> getModelType() {
        return SCABinding.class;
    }
}
