/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.example.ipo.sdo;

import commonj.sdo.helper.HelperContext;
import org.apache.tuscany.sdo.helper.HelperContextImpl;


/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @generated
 */
public interface SdoFactory
{

	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	SdoFactory INSTANCE = com.example.ipo.sdo.impl.SdoFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Address</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Address</em>'.
	 * @generated
	 */
	Address createAddress();

	/**
	 * Returns a new object of class '<em>item</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>item</em>'.
	 * @generated
	 */
	item createitem();

	/**
	 * Returns a new object of class '<em>Items</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Items</em>'.
	 * @generated
	 */
	Items createItems();

	/**
	 * Returns a new object of class '<em>Purchase Order Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Purchase Order Type</em>'.
	 * @generated
	 */
	PurchaseOrderType createPurchaseOrderType();

	/**
	 * Returns a new object of class '<em>UK Address</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>UK Address</em>'.
	 * @generated
	 */
	UKAddress createUKAddress();

	/**
	 * Returns a new object of class '<em>US Address</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>US Address</em>'.
	 * @generated
	 */
	USAddress createUSAddress();

  /**
   * Registers the types supported by this Factory within the supplied scope.argument
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param scope an instance of HelperContext used to manage the scoping of types.
	 * @generated
   */
  public void register(HelperContext scope);
   
} //SdoFactory
