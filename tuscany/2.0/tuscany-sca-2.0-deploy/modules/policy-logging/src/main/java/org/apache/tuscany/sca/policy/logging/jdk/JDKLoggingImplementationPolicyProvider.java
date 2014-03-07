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

package org.apache.tuscany.sca.policy.logging.jdk;

import java.util.List;

import org.apache.tuscany.sca.interfacedef.Operation;
import org.apache.tuscany.sca.invocation.Phase;
import org.apache.tuscany.sca.invocation.PhasedInterceptor;
import org.apache.tuscany.sca.provider.BasePolicyProvider;
import org.apache.tuscany.sca.runtime.RuntimeComponent;

/**
 * @version $Rev: 1186226 $ $Date: 2011-10-19 15:03:07 +0100 (Wed, 19 Oct 2011) $
 */
public class JDKLoggingImplementationPolicyProvider extends BasePolicyProvider<JDKLoggingPolicy> {

    public JDKLoggingImplementationPolicyProvider(RuntimeComponent component) {
        super(JDKLoggingPolicy.class, component.getImplementation());
    }

    /**
     * @see org.apache.tuscany.sca.provider.PolicyProvider#createInterceptor(org.apache.tuscany.sca.interfacedef.Operation)
     */
    public PhasedInterceptor createInterceptor(Operation operation) {
        List<JDKLoggingPolicy> policies = findPolicies(operation);
        return policies.isEmpty() ? null : new JDKLoggingPolicyInterceptor(subject, getContext(), operation,
                                                                           policies, Phase.IMPLEMENTATION_POLICY);
    }

}
