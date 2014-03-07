
package com.example.stock;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.example.stock package. 
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

    private final static QName _TestNotDeclaredAtSourceFault_QNAME = new QName("http://www.example.com/stock", "TestNotDeclaredAtSourceFault");
    private final static QName _StockQuoteOfferResponse_QNAME = new QName("http://www.example.com/stock", "stockQuoteOfferResponse");
    private final static QName _MarketClosedFault_QNAME = new QName("http://www.example.com/stock", "MarketClosedFault");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.example.stock
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link AnyElement }
     * 
     */
    public AnyElement createAnyElement() {
        return new AnyElement();
    }

    /**
     * Create an instance of {@link StockQuoteOfferResponseType }
     * 
     */
    public StockQuoteOfferResponseType createStockQuoteOfferResponseType() {
        return new StockQuoteOfferResponseType();
    }

    /**
     * Create an instance of {@link InvalidSymbolFault }
     * 
     */
    public InvalidSymbolFault createInvalidSymbolFault() {
        return new InvalidSymbolFault();
    }

    /**
     * Create an instance of {@link StockOffer }
     * 
     */
    public StockOffer createStockOffer() {
        return new StockOffer();
    }

    /**
     * Create an instance of {@link StockQuoteOffer }
     * 
     */
    public StockQuoteOffer createStockQuoteOffer() {
        return new StockQuoteOffer();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.example.com/stock", name = "TestNotDeclaredAtSourceFault")
    public JAXBElement<String> createTestNotDeclaredAtSourceFault(String value) {
        return new JAXBElement<String>(_TestNotDeclaredAtSourceFault_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StockQuoteOfferResponseType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.example.com/stock", name = "stockQuoteOfferResponse")
    public JAXBElement<StockQuoteOfferResponseType> createStockQuoteOfferResponse(StockQuoteOfferResponseType value) {
        return new JAXBElement<StockQuoteOfferResponseType>(_StockQuoteOfferResponse_QNAME, StockQuoteOfferResponseType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.example.com/stock", name = "MarketClosedFault")
    public JAXBElement<Integer> createMarketClosedFault(Integer value) {
        return new JAXBElement<Integer>(_MarketClosedFault_QNAME, Integer.class, null, value);
    }

}
