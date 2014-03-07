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
package org.apache.tuscany.sca.contribution.zip;

import junit.framework.TestCase;

import org.apache.tuscany.sca.node.Contribution;
import org.apache.tuscany.sca.node.Node;
import org.apache.tuscany.sca.node.NodeFactory;

import calculator.CalculatorService;

/**
 * Test SCADomain.newInstance and invocation of a service.
 *
 * @version $Rev: 775283 $ $Date: 2009-05-15 19:36:42 +0100 (Fri, 15 May 2009) $
 */
public class ContributionZIPTestCase extends TestCase {

    private Node node;

    @Override
    protected void setUp() throws Exception {
        node =
            NodeFactory.newInstance().createNode(new Contribution("foo",
                                                                  "src/test/resources/repository/contribution.zip"));
        node.start();
    }

    public void testInvoke() throws Exception {
        CalculatorService service = node.getService(CalculatorService.class, "CalculatorServiceComponent");
        assertEquals(3.0, service.add(1, 2));
    }

    @Override
    protected void tearDown() throws Exception {
        node.stop();
    }

}