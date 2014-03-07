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

import java.util.List;

import org.apache.tuscany.sca.policy.PolicySet;
import org.apache.tuscany.sca.interfacedef.Operation;
import org.apache.tuscany.sca.invocation.Phase;
import org.apache.tuscany.sca.invocation.PhasedInterceptor;
import org.apache.tuscany.sca.provider.BasePolicyProvider;
import org.apache.tuscany.sca.runtime.RuntimeComponent;

import archetype.it.basic.util.PolicyHelper;
import archetype.it.basic.Basic.BasicPolicy;

/**
 * @version $Rev$ $Date$
 */
public class BasicImplementationPolicyProvider extends BasePolicyProvider<BasicPolicy> {
    private RuntimeComponent component;
    
    public BasicImplementationPolicyProvider(RuntimeComponent component) {
        super(BasicPolicy.class, component.getImplementation());
        this.component = component;
    }

    /**
     * @see archetype.it.provider.PolicyProvider#createInterceptor(archetype.it.interfacedef.Operation)
     */
    @Override
    public PhasedInterceptor createInterceptor(Operation operation) {
        List<PolicySet> policySets = PolicyHelper.findPolicySets(component, BasicPolicy.POLICY_QNAME);
        
        
        return policySets.isEmpty() ? null : new BasicPolicyInterceptor(getContext(), operation, getPhase());
    }
    
    public String getPhase() {
        return Phase.IMPLEMENTATION_POLICY;
    }

}
