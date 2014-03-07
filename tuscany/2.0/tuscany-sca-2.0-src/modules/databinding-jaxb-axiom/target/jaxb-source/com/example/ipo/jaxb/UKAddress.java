//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.09.21 at 06:40:31 ���� CST 
//


package com.example.ipo.jaxb;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for UKAddress complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="UKAddress">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.example.com/IPO}Address">
 *       &lt;sequence>
 *         &lt;element name="postcode" type="{http://www.example.com/IPO}UKPostcode"/>
 *       &lt;/sequence>
 *       &lt;attribute name="exportCode" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" fixed="1" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UKAddress", propOrder = {
    "postcode"
})
public class UKAddress
    extends Address
{

    @XmlElement(required = true)
    protected String postcode;
    @XmlAttribute(name = "exportCode")
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger exportCode;

    /**
     * Gets the value of the postcode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPostcode() {
        return postcode;
    }

    /**
     * Sets the value of the postcode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPostcode(String value) {
        this.postcode = value;
    }

    /**
     * Gets the value of the exportCode property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getExportCode() {
        if (exportCode == null) {
            return new BigInteger("1");
        } else {
            return exportCode;
        }
    }

    /**
     * Sets the value of the exportCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setExportCode(BigInteger value) {
        this.exportCode = value;
    }

}
