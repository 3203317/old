/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.example.ipo.sdo.impl;

import com.example.ipo.sdo.SdoFactory;
import com.example.ipo.sdo.UKAddress;

import commonj.sdo.Type;

import java.math.BigInteger;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>UK Address</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.example.ipo.sdo.impl.UKAddressImpl#getPostcode <em>Postcode</em>}</li>
 *   <li>{@link com.example.ipo.sdo.impl.UKAddressImpl#getExportCode <em>Export Code</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class UKAddressImpl extends AddressImpl implements UKAddress
{

	public final static int POSTCODE = AddressImpl.SDO_PROPERTY_COUNT + 0;

	public final static int EXPORT_CODE = AddressImpl.SDO_PROPERTY_COUNT + 1;

	public final static int SDO_PROPERTY_COUNT = AddressImpl.SDO_PROPERTY_COUNT + 2;

	public final static int EXTENDED_PROPERTY_COUNT = AddressImpl.EXTENDED_PROPERTY_COUNT - 0;


	/**
	 * The internal feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */ 
	public final static int _INTERNAL_NAME = AddressImpl._INTERNAL_NAME;

	/**
	 * The internal feature id for the '<em><b>Street</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */ 
	public final static int _INTERNAL_STREET = AddressImpl._INTERNAL_STREET;

	/**
	 * The internal feature id for the '<em><b>City</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */ 
	public final static int _INTERNAL_CITY = AddressImpl._INTERNAL_CITY;

	/**
	 * The internal feature id for the '<em><b>Postcode</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */ 
	public final static int _INTERNAL_POSTCODE = AddressImpl.INTERNAL_PROPERTY_COUNT + 0;

	/**
	 * The internal feature id for the '<em><b>Export Code</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */ 
	public final static int _INTERNAL_EXPORT_CODE = AddressImpl.INTERNAL_PROPERTY_COUNT + 1;

	/**
	 * The number of properties for this type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public final static int INTERNAL_PROPERTY_COUNT = AddressImpl.INTERNAL_PROPERTY_COUNT + 2;

	protected int internalConvertIndex(int internalIndex)
	{
		switch (internalIndex)
		{
			case _INTERNAL_NAME: return NAME;
			case _INTERNAL_STREET: return STREET;
			case _INTERNAL_CITY: return CITY;
			case _INTERNAL_POSTCODE: return POSTCODE;
			case _INTERNAL_EXPORT_CODE: return EXPORT_CODE;
		}
		return super.internalConvertIndex(internalIndex);
	}


	/**
	 * The default value of the '{@link #getPostcode() <em>Postcode</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPostcode()
	 * @generated
	 * @ordered
	 */
	protected static final String POSTCODE_DEFAULT_ = null;

	/**
	 * The cached value of the '{@link #getPostcode() <em>Postcode</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPostcode()
	 * @generated
	 * @ordered
	 */
	protected String postcode = POSTCODE_DEFAULT_;

	/**
	 * The default value of the '{@link #getExportCode() <em>Export Code</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExportCode()
	 * @generated
	 * @ordered
	 */
	protected static final BigInteger EXPORT_CODE_DEFAULT_ = new BigInteger("1");

	/**
	 * The cached value of the '{@link #getExportCode() <em>Export Code</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExportCode()
	 * @generated
	 * @ordered
	 */
	protected BigInteger exportCode = EXPORT_CODE_DEFAULT_;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UKAddressImpl()
	{
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Type getStaticType()
	{
		return ((SdoFactoryImpl)SdoFactory.INSTANCE).getUKAddress();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getStaticPropertyCount()
	{
		return INTERNAL_PROPERTY_COUNT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getPostcode()
	{
		return postcode;
	}
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPostcode(String newPostcode)
	{
		postcode = newPostcode;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigInteger getExportCode()
	{
		return exportCode;
	}
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setExportCode(BigInteger newExportCode)
	{
		exportCode = newExportCode;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object get(int propertyIndex, boolean resolve)
	{
		switch (propertyIndex)
		{
			case POSTCODE:
				return getPostcode();
			case EXPORT_CODE:
				return getExportCode();
		}
		return super.get(propertyIndex, resolve);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void set(int propertyIndex, Object newValue)
	{
		switch (propertyIndex)
		{
			case POSTCODE:
				setPostcode((String)newValue);
				return;
			case EXPORT_CODE:
				setExportCode((BigInteger)newValue);
				return;
		}
		super.set(propertyIndex, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unset(int propertyIndex)
	{
		switch (propertyIndex)
		{
			case POSTCODE:
				setPostcode(POSTCODE_DEFAULT_);
				return;
			case EXPORT_CODE:
				setExportCode(EXPORT_CODE_DEFAULT_);
				return;
		}
		super.unset(propertyIndex);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSet(int propertyIndex)
	{
		switch (propertyIndex)
		{
			case POSTCODE:
				return POSTCODE_DEFAULT_ == null ? postcode != null : !POSTCODE_DEFAULT_.equals(postcode);
			case EXPORT_CODE:
				return EXPORT_CODE_DEFAULT_ == null ? exportCode != null : !EXPORT_CODE_DEFAULT_.equals(exportCode);
		}
		return super.isSet(propertyIndex);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String toString()
	{
		if (isProxy(this)) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (postcode: ");
		result.append(postcode);
		result.append(", exportCode: ");
		result.append(exportCode);
		result.append(')');
		return result.toString();
	}

} //UKAddressImpl
