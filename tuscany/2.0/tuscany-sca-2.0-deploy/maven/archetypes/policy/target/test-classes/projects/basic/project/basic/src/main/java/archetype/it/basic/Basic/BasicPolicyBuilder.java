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

package archetype.it.basic.Basic;

import java.util.ArrayList;
import java.util.List;

import javax.xml.namespace.QName;

import org.apache.tuscany.sca.assembly.Component;
import org.apache.tuscany.sca.assembly.Endpoint;
import org.apache.tuscany.sca.assembly.EndpointReference;
import org.apache.tuscany.sca.assembly.Implementation;
import org.apache.tuscany.sca.assembly.builder.BuilderContext;
import org.apache.tuscany.sca.assembly.builder.PolicyBuilder;
import org.apache.tuscany.sca.policy.PolicyExpression;
import org.apache.tuscany.sca.policy.PolicySet;
import org.apache.tuscany.sca.policy.PolicySubject;

/**
 * 
 */
public class BasicPolicyBuilder implements PolicyBuilder<BasicPolicy> {

	@Override
    public boolean build(Endpoint endpoint, BuilderContext context) {
        List<BasicPolicy> polices = getPolicies(endpoint);
        System.out.println(endpoint + ": " + polices);
        return true;
    }

	@Override
    public boolean build(EndpointReference endpointReference, BuilderContext context) {
        List<BasicPolicy> polices = getPolicies(endpointReference);
        System.out.println(endpointReference + ": " + polices);
        return true;
    }

	@Override
    public boolean build(Component component, Implementation implementation, BuilderContext context) {
        List<BasicPolicy> polices = getPolicies(implementation);
        System.out.println(implementation + ": " + polices);
        return true;
    }

	@Override
    public QName getPolicyType() {
        return BasicPolicy.POLICY_QNAME;
    }
	
	@Override
    public List<QName> getSupportedBindings() {
        return null;
    }

    private List<BasicPolicy> getPolicies(PolicySubject subject) {
        List<BasicPolicy> polices = new ArrayList<BasicPolicy>();
        for (PolicySet ps : subject.getPolicySets()) {
            for (PolicyExpression exp : ps.getPolicies()) {
                if (getPolicyType().equals(exp.getName())) {
                    polices.add((BasicPolicy)exp.getPolicy());
                }
            }
        }
        return polices;
    }

    @Override
    public boolean build(EndpointReference endpointReference, Endpoint endpoint, BuilderContext context) {
        return true;
    }

}
