/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.example.ipo.sdo.impl;

import com.example.ipo.sdo.SdoFactory;
import com.example.ipo.sdo.USAddress;

import commonj.sdo.Type;

import java.math.BigInteger;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>US Address</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.example.ipo.sdo.impl.USAddressImpl#getState <em>State</em>}</li>
 *   <li>{@link com.example.ipo.sdo.impl.USAddressImpl#getZip <em>Zip</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class USAddressImpl extends AddressImpl implements USAddress
{

	public final static int STATE = AddressImpl.SDO_PROPERTY_COUNT + 0;

	public final static int ZIP = AddressImpl.SDO_PROPERTY_COUNT + 1;

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
	 * The internal feature id for the '<em><b>State</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */ 
	public final static int _INTERNAL_STATE = AddressImpl.INTERNAL_PROPERTY_COUNT + 0;

	/**
	 * The internal feature id for the '<em><b>Zip</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */ 
	public final static int _INTERNAL_ZIP = AddressImpl.INTERNAL_PROPERTY_COUNT + 1;

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
			case _INTERNAL_STATE: return STATE;
			case _INTERNAL_ZIP: return ZIP;
		}
		return super.internalConvertIndex(internalIndex);
	}


	/**
	 * The default value of the '{@link #getState() <em>State</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getState()
	 * @generated
	 * @ordered
	 */
	protected static final String STATE_DEFAULT_ = null;

	/**
	 * The cached value of the '{@link #getState() <em>State</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getState()
	 * @generated
	 * @ordered
	 */
	protected String state = STATE_DEFAULT_;

	/**
	 * The default value of the '{@link #getZip() <em>Zip</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getZip()
	 * @generated
	 * @ordered
	 */
	protected static final BigInteger ZIP_DEFAULT_ = null;

	/**
	 * The cached value of the '{@link #getZip() <em>Zip</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getZip()
	 * @generated
	 * @ordered
	 */
	protected BigInteger zip = ZIP_DEFAULT_;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public USAddressImpl()
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
		return ((SdoFactoryImpl)SdoFactory.INSTANCE).getUSAddress();
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
	public String getState()
	{
		return state;
	}
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setState(String newState)
	{
		state = newState;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigInteger getZip()
	{
		return zip;
	}
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setZip(BigInteger newZip)
	{
		zip = newZip;
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
			case STATE:
				return getState();
			case ZIP:
				return getZip();
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
			case STATE:
				setState((String)newValue);
				return;
			case ZIP:
				setZip((BigInteger)newValue);
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
			case STATE:
				setState(STATE_DEFAULT_);
				return;
			case ZIP:
				setZip(ZIP_DEFAULT_);
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
			case STATE:
				return STATE_DEFAULT_ == null ? state != null : !STATE_DEFAULT_.equals(state);
			case ZIP:
				return ZIP_DEFAULT_ == null ? zip != null : !ZIP_DEFAULT_.equals(zip);
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
		result.append(" (state: ");
		result.append(state);
		result.append(", zip: ");
		result.append(zip);
		result.append(')');
		return result.toString();
	}

} //USAddressImpl
