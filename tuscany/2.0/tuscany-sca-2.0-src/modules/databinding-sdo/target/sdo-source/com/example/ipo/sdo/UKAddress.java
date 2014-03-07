/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.example.ipo.sdo;

import java.math.BigInteger;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>UK Address</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.example.ipo.sdo.UKAddress#getPostcode <em>Postcode</em>}</li>
 *   <li>{@link com.example.ipo.sdo.UKAddress#getExportCode <em>Export Code</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public interface UKAddress extends Address
{
	/**
	 * Returns the value of the '<em><b>Postcode</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Postcode</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Postcode</em>' attribute.
	 * @see #setPostcode(String)
	 * @generated
	 */
	String getPostcode();

	/**
	 * Sets the value of the '{@link com.example.ipo.sdo.UKAddress#getPostcode <em>Postcode</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Postcode</em>' attribute.
	 * @see #getPostcode()
	 * @generated
	 */
	void setPostcode(String value);

	/**
	 * Returns the value of the '<em><b>Export Code</b></em>' attribute.
	 * The default value is <code>"1"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Export Code</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Export Code</em>' attribute.
	 * @see #setExportCode(BigInteger)
	 * @generated
	 */
	BigInteger getExportCode();

	/**
	 * Sets the value of the '{@link com.example.ipo.sdo.UKAddress#getExportCode <em>Export Code</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Export Code</em>' attribute.
	 * @see #getExportCode()
	 * @generated
	 */
	void setExportCode(BigInteger value);

} // UKAddress
