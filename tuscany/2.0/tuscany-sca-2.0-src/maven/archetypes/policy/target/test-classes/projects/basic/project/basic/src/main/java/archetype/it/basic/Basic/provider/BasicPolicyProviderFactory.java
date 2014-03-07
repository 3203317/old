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

package archetype.it.basic.Basic.provider;

import org.apache.tuscany.sca.assembly.Endpoint;
import org.apache.tuscany.sca.assembly.EndpointReference;
import org.apache.tuscany.sca.core.ExtensionPointRegistry;
import org.apache.tuscany.sca.provider.PolicyProvider;
import org.apache.tuscany.sca.provider.PolicyProviderFactory;
import org.apache.tuscany.sca.runtime.RuntimeComponent;

import archetype.it.basic.Basic.BasicPolicy;

/**
 * @version $Rev$ $Date$
 */
public class BasicPolicyProviderFactory implements PolicyProviderFactory<BasicPolicy> {
    private ExtensionPointRegistry registry;
    
    public BasicPolicyProviderFactory(ExtensionPointRegistry registry) {
        super();
        this.registry = registry;
    }

    /**
     * @see archetype.it.provider.PolicyProviderFactory#createImplementationPolicyProvider(archetype.it.runtime.RuntimeComponent, archetype.it.assembly.Implementation)
     */
    @Override
    public PolicyProvider createImplementationPolicyProvider(RuntimeComponent component) {
        return new BasicImplementationPolicyProvider(component);
    }

    /**
     * @see archetype.it.provider.PolicyProviderFactory#createReferencePolicyProvider(archetype.it.runtime.RuntimeComponent, archetype.it.runtime.RuntimeComponentReference, archetype.it.assembly.Binding)
     */
    @Override
    public PolicyProvider createReferencePolicyProvider(EndpointReference endpointReference) {
        return new BasicReferencePolicyProvider(endpointReference);
    }

    /**
     * @see archetype.it.provider.PolicyProviderFactory#createServicePolicyProvider(archetype.it.runtime.RuntimeComponent, archetype.it.runtime.RuntimeComponentService, archetype.it.assembly.Binding)
     */
    @Override
    public PolicyProvider createServicePolicyProvider(Endpoint endpoint) {
        return new BasicServicePolicyProvider(endpoint);
    }

    /**
     * @see archetype.it.provider.ProviderFactory#getModelType()
     */
    @Override
    public Class<BasicPolicy> getModelType() {
        return BasicPolicy.class;
    }

}
