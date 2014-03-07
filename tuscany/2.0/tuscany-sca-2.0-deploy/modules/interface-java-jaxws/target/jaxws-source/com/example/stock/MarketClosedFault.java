
package com.example.stock;

import javax.xml.ws.WebFault;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.6b10 
 * Generated source version: 2.1
 * 
 */
@WebFault(name = "MarketClosedFault", targetNamespace = "http://www.example.com/stock")
public class MarketClosedFault
    extends Exception
{

    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private int faultInfo;

    /**
     * 
     * @param message
     * @param faultInfo
     */
    public MarketClosedFault(String message, int faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param message
     * @param faultInfo
     * @param cause
     */
    public MarketClosedFault(String message, int faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: int
     */
    public int getFaultInfo() {
        return faultInfo;
    }

}
