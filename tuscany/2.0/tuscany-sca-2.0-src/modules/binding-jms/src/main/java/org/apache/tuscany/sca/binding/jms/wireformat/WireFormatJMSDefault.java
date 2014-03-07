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
package org.apache.tuscany.sca.binding.jms.wireformat;

import javax.xml.namespace.QName;

import org.apache.tuscany.sca.assembly.WireFormat;
import org.apache.tuscany.sca.assembly.xml.Constants;

/**
 * 
 * @version $Rev: 986685 $ $Date: 2010-08-18 15:00:03 +0100 (Wed, 18 Aug 2010) $
 */
public class WireFormatJMSDefault implements WireFormat {
    public static final QName WIRE_FORMAT_JMS_DEFAULT_QNAME = new QName(Constants.SCA11_NS, "wireFormat.jmsDefault");
    
    public static final String WIRE_FORMAT_JMS_DEFAULT_FORMAT_ATTR = "sendFormat";
    
    public static final String WIRE_FORMAT_JMS_DEFAULT_TEXT_FORMAT_VAL = "text";
    public static final String WIRE_FORMAT_JMS_DEFAULT_BYTES_FORMAT_VAL = "bytes";

    //default is to use a javax.jms.BytesMessage
    private boolean useBytesMessage = true;
    
    public QName getSchemaName() {
        return WIRE_FORMAT_JMS_DEFAULT_QNAME;
    }

    public boolean isUnresolved() {
        return false;
    }

    public void setUnresolved(boolean unresolved) {
    }

    @Override
    public boolean equals(Object obj) {
        return this.getClass() == obj.getClass();
    }
    
    public void setUseBytesMessage(boolean useBytesMessage) {
        this.useBytesMessage = useBytesMessage;
    }

    public boolean isUseBytesMessage() {
        return useBytesMessage;
    }
}
