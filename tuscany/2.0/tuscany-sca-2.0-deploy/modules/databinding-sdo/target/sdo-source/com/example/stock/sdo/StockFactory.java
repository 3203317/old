/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.example.stock.sdo;

import commonj.sdo.helper.HelperContext;
import org.apache.tuscany.sdo.helper.HelperContextImpl;


/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @generated
 */
public interface StockFactory
{

	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	StockFactory INSTANCE = com.example.stock.sdo.impl.StockFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Invalid Symbol Fault</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Invalid Symbol Fault</em>'.
	 * @generated
	 */
	InvalidSymbolFault createInvalidSymbolFault();

  /**
   * Registers the types supported by this Factory within the supplied scope.argument
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param scope an instance of HelperContext used to manage the scoping of types.
	 * @generated
   */
  public void register(HelperContext scope);
   
} //StockFactory
