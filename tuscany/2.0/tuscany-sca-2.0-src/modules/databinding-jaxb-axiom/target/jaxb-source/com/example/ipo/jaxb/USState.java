//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.09.21 at 06:40:31 ���� CST 
//


package com.example.ipo.jaxb;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for USState.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="USState">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="AK"/>
 *     &lt;enumeration value="AL"/>
 *     &lt;enumeration value="AR"/>
 *     &lt;enumeration value="CA"/>
 *     &lt;enumeration value="PA"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "USState")
@XmlEnum
public enum USState {

    AK,
    AL,
    AR,
    CA,
    PA;

    public String value() {
        return name();
    }

    public static USState fromValue(String v) {
        return valueOf(v);
    }

}
