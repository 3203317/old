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

import static javax.xml.stream.XMLStreamConstants.END_ELEMENT;
import static javax.xml.stream.XMLStreamConstants.START_ELEMENT;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

import org.apache.tuscany.sca.contribution.processor.ContributionReadException;
import org.apache.tuscany.sca.contribution.processor.ContributionResolveException;
import org.apache.tuscany.sca.contribution.processor.ContributionWriteException;
import org.apache.tuscany.sca.contribution.processor.ProcessorContext;
import org.apache.tuscany.sca.contribution.processor.StAXArtifactProcessor;
import org.apache.tuscany.sca.contribution.resolver.ModelResolver;
import org.apache.tuscany.sca.core.FactoryExtensionPoint;

/**
 *
 * @version $Rev$ $Date$
 */
public class BasicPolicyProcessor implements StAXArtifactProcessor<BasicPolicy> {
	
    public BasicPolicyProcessor(FactoryExtensionPoint modelFactories) {
    }

    @Override
    public QName getArtifactType() {
        return BasicPolicy.POLICY_QNAME;
    }
    
    @Override
    public BasicPolicy read(XMLStreamReader reader, ProcessorContext context) throws ContributionReadException, XMLStreamException {
        BasicPolicy policy = new BasicPolicy();
        int event = reader.getEventType();
        QName name = null;
        
        
        while (reader.hasNext()) {
            event = reader.getEventType();
            switch (event) {
                case START_ELEMENT : {
                    name = reader.getName();
                    if ( name.equals(BasicPolicy.POLICY_QNAME) ) {
                        String config = reader.getAttributeValue(null, "name");
                        policy.setConfigProperty(config);
                    }
                    break;
                }
            }
            
            if ( event == END_ELEMENT ) {
                if ( BasicPolicy.POLICY_QNAME.equals(reader.getName()) ) {
                    break;
                } 
            }
            
            //Read the next element
            if (reader.hasNext()) {
                reader.next();
            }
        }
         
        return policy;
    }

    @Override
    public void write(BasicPolicy policy, XMLStreamWriter writer, ProcessorContext context) throws ContributionWriteException,
                                                        XMLStreamException {
        String prefix = "tuscany";
        writer.writeStartElement(prefix, 
        						 BasicPolicy.POLICY_QNAME.getLocalPart(),
        						 BasicPolicy.POLICY_QNAME.getNamespaceURI());
        writer.writeNamespace("tuscany", BasicPolicy.SCA11_TUSCANY_NS);
        
        if (policy.getConfigProperty() != null) {
            writer.writeAttribute("name", policy.getConfigProperty());
        }
        
        writer.writeEndElement();
    }

    @Override
    public Class<BasicPolicy> getModelType() {
        return BasicPolicy.class;
    }

    @Override
    public void resolve(BasicPolicy arg0, ModelResolver arg1, ProcessorContext context) throws ContributionResolveException {

    }
    
}
