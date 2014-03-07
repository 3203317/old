
package com.example.stock.async;

import java.util.List;
import java.util.concurrent.Future;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.AsyncHandler;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.Response;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.6b10 
 * Generated source version: 2.1
 * 
 */
@WebService(name = "StockExceptionTest", targetNamespace = "http://www.example.com/stock")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface StockExceptionTest {


    /**
     * 
     * @param input
     * @return
     *     returns javax.xml.ws.Response<com.example.stock.async.StockQuoteOfferResponseType>
     */
    @WebMethod(operationName = "stockQuoteOffer")
    @RequestWrapper(localName = "stockQuoteOffer", targetNamespace = "http://www.example.com/stock", className = "com.example.stock.async.StockQuoteOffer")
    @ResponseWrapper(localName = "stockQuoteOfferResponse", targetNamespace = "http://www.example.com/stock", className = "com.example.stock.async.StockQuoteOfferResponseType")
    public Response<StockQuoteOfferResponseType> stockQuoteOfferAsync(
        @WebParam(name = "input", targetNamespace = "")
        List<StockOffer> input);

    /**
     * 
     * @param input
     * @param asyncHandler
     * @return
     *     returns java.util.concurrent.Future<? extends java.lang.Object>
     */
    @WebMethod(operationName = "stockQuoteOffer")
    @RequestWrapper(localName = "stockQuoteOffer", targetNamespace = "http://www.example.com/stock", className = "com.example.stock.async.StockQuoteOffer")
    @ResponseWrapper(localName = "stockQuoteOfferResponse", targetNamespace = "http://www.example.com/stock", className = "com.example.stock.async.StockQuoteOfferResponseType")
    public Future<?> stockQuoteOfferAsync(
        @WebParam(name = "input", targetNamespace = "")
        List<StockOffer> input,
        @WebParam(name = "asyncHandler", targetNamespace = "")
        AsyncHandler<StockQuoteOfferResponseType> asyncHandler);

    /**
     * 
     * @param input
     * @return
     *     returns com.example.stock.async.StockOffer
     * @throws InvalidSymbolFault_Exception
     * @throws TestNotDeclaredAtSourceFault
     * @throws MarketClosedFault
     */
    @WebMethod
    @WebResult(name = "stockQuoteOfferReturn", targetNamespace = "")
    @RequestWrapper(localName = "stockQuoteOffer", targetNamespace = "http://www.example.com/stock", className = "com.example.stock.async.StockQuoteOffer")
    @ResponseWrapper(localName = "stockQuoteOfferResponse", targetNamespace = "http://www.example.com/stock", className = "com.example.stock.async.StockQuoteOfferResponseType")
    public StockOffer stockQuoteOffer(
        @WebParam(name = "input", targetNamespace = "")
        List<StockOffer> input)
        throws InvalidSymbolFault_Exception, MarketClosedFault, TestNotDeclaredAtSourceFault
    ;

}
