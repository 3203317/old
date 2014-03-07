
package com.example.stock.async;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.6b10 
 * Generated source version: 2.1
 * 
 */
@WebServiceClient(name = "StockExceptionTestService", targetNamespace = "http://www.example.com/stock", wsdlLocation = "file:/D:/soft/tuscany/RC%202.0/tuscany-distribution-all-2.0-src/tuscany-sca-2.0-src/modules/interface-java-jaxws/src/test/resources/wsdl/StockExceptionTest.wsdl")
public class StockExceptionTestService
    extends Service
{

    private final static URL STOCKEXCEPTIONTESTSERVICE_WSDL_LOCATION;
    private final static WebServiceException STOCKEXCEPTIONTESTSERVICE_EXCEPTION;
    private final static QName STOCKEXCEPTIONTESTSERVICE_QNAME = new QName("http://www.example.com/stock", "StockExceptionTestService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("file:/D:/soft/tuscany/RC%202.0/tuscany-distribution-all-2.0-src/tuscany-sca-2.0-src/modules/interface-java-jaxws/src/test/resources/wsdl/StockExceptionTest.wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        STOCKEXCEPTIONTESTSERVICE_WSDL_LOCATION = url;
        STOCKEXCEPTIONTESTSERVICE_EXCEPTION = e;
    }

    public StockExceptionTestService() {
        super(__getWsdlLocation(), STOCKEXCEPTIONTESTSERVICE_QNAME);
    }

    public StockExceptionTestService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    /**
     * 
     * @return
     *     returns StockExceptionTest
     */
    @WebEndpoint(name = "StockExceptionTestServiceSoapPort")
    public StockExceptionTest getStockExceptionTestServiceSoapPort() {
        return super.getPort(new QName("http://www.example.com/stock", "StockExceptionTestServiceSoapPort"), StockExceptionTest.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns StockExceptionTest
     */
    @WebEndpoint(name = "StockExceptionTestServiceSoapPort")
    public StockExceptionTest getStockExceptionTestServiceSoapPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://www.example.com/stock", "StockExceptionTestServiceSoapPort"), StockExceptionTest.class, features);
    }

    private static URL __getWsdlLocation() {
        if (STOCKEXCEPTIONTESTSERVICE_EXCEPTION!= null) {
            throw STOCKEXCEPTIONTESTSERVICE_EXCEPTION;
        }
        return STOCKEXCEPTIONTESTSERVICE_WSDL_LOCATION;
    }

}
