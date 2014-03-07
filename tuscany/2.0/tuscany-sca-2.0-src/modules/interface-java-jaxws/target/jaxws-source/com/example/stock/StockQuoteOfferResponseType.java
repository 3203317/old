
package com.example.stock;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for StockQuoteOfferResponseType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="StockQuoteOfferResponseType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="stockQuoteOfferReturn" type="{http://www.example.com/stock}StockOffer" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "StockQuoteOfferResponseType", propOrder = {
    "stockQuoteOfferReturn"
})
public class StockQuoteOfferResponseType {

    protected StockOffer stockQuoteOfferReturn;

    /**
     * Gets the value of the stockQuoteOfferReturn property.
     * 
     * @return
     *     possible object is
     *     {@link StockOffer }
     *     
     */
    public StockOffer getStockQuoteOfferReturn() {
        return stockQuoteOfferReturn;
    }

    /**
     * Sets the value of the stockQuoteOfferReturn property.
     * 
     * @param value
     *     allowed object is
     *     {@link StockOffer }
     *     
     */
    public void setStockQuoteOfferReturn(StockOffer value) {
        this.stockQuoteOfferReturn = value;
    }

}
