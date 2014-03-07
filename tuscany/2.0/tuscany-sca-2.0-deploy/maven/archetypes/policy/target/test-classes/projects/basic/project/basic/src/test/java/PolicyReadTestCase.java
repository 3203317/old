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

import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.URL;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

import junit.framework.TestCase;

import org.apache.tuscany.sca.contribution.processor.ProcessorContext;

import archetype.it.basic.Basic.BasicPolicy;
import archetype.it.basic.Basic.BasicPolicyProcessor;

/**
 * Test the reading of ws config params policy.
 *
 * @version $Rev$ $Date$
 */
public class PolicyReadTestCase extends TestCase {
    private ProcessorContext context = new ProcessorContext();
    
    @Override
    public void setUp() throws Exception {
    }

    @Override
    public void tearDown() throws Exception {
    }

    public void testPolicyReading() throws Exception { 
    	BasicPolicyProcessor processor = new BasicPolicyProcessor(null);
//      URL url = getClass().getResource("mock_policies.xml");
//      XMLInputFactory inputFactory = XMLInputFactory.newInstance();
//      
//      InputStream urlStream = url.openStream();
//      XMLStreamReader reader = inputFactory.createXMLStreamReader(urlStream);
//      
//      
//      BasicPolicy policy = processor.read(reader, context);
    }
    
    public void testPolicyWriting() throws Exception {
    	BasicPolicyProcessor processor = new BasicPolicyProcessor(null);
        
    	BasicPolicy policy = new BasicPolicy();
        
        XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();
        StringWriter sw = new StringWriter();
        XMLStreamWriter writer = outputFactory.createXMLStreamWriter(sw);
        processor.write(policy, writer, context);
        writer.close();
        
        XMLInputFactory inputFactory = XMLInputFactory.newInstance();
        StringReader sr = new StringReader(sw.toString());
        XMLStreamReader reader = inputFactory.createXMLStreamReader(sr);
        
        policy = processor.read(reader, context);
        
    }

}
