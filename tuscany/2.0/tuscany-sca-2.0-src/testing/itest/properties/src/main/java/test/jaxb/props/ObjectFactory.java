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
//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.3 in JDK 1.6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2008.06.07 at 03:30:47 PM EDT 
//
package test.jaxb.props;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the test.jaxb.props package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _MyRCProps_QNAME = new QName("http://test.sca.jaxb/rcprops", "MyRCProps");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: test.jaxb.props
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ReturnCodeProperties }
     * 
     */
    public ReturnCodeProperties createReturnCodeProperties() {
        return new ReturnCodeProperties();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReturnCodeProperties }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://test.sca.jaxb/rcprops", name = "MyRCProps")
    public JAXBElement<ReturnCodeProperties> createMyRCProps(ReturnCodeProperties value) {
        return new JAXBElement<ReturnCodeProperties>(_MyRCProps_QNAME, ReturnCodeProperties.class, null, value);
    }

}
