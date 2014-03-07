/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.example.ipo.sdo.impl;

import com.example.ipo.sdo.SdoFactory;
import com.example.ipo.sdo.item;

import commonj.sdo.Type;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.apache.tuscany.sdo.impl.DataObjectBase;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>item</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.example.ipo.sdo.impl.itemImpl#getProductName <em>Product Name</em>}</li>
 *   <li>{@link com.example.ipo.sdo.impl.itemImpl#getQuantity <em>Quantity</em>}</li>
 *   <li>{@link com.example.ipo.sdo.impl.itemImpl#getUSPrice <em>US Price</em>}</li>
 *   <li>{@link com.example.ipo.sdo.impl.itemImpl#getComment <em>Comment</em>}</li>
 *   <li>{@link com.example.ipo.sdo.impl.itemImpl#getShipDate <em>Ship Date</em>}</li>
 *   <li>{@link com.example.ipo.sdo.impl.itemImpl#getPartNum <em>Part Num</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class itemImpl extends DataObjectBase implements item
{

	public final static int PRODUCT_NAME = 0;

	public final static int QUANTITY = 1;

	public final static int US_PRICE = 2;

	public final static int COMMENT = 3;

	public final static int SHIP_DATE = 4;

	public final static int PART_NUM = 5;

	public final static int SDO_PROPERTY_COUNT = 6;

	public final static int EXTENDED_PROPERTY_COUNT = 0;


	/**
	 * The internal feature id for the '<em><b>Product Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */ 
	public final static int _INTERNAL_PRODUCT_NAME = 0;

	/**
	 * The internal feature id for the '<em><b>Quantity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */ 
	public final static int _INTERNAL_QUANTITY = 1;

	/**
	 * The internal feature id for the '<em><b>US Price</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */ 
	public final static int _INTERNAL_US_PRICE = 2;

	/**
	 * The internal feature id for the '<em><b>Comment</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */ 
	public final static int _INTERNAL_COMMENT = 3;

	/**
	 * The internal feature id for the '<em><b>Ship Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */ 
	public final static int _INTERNAL_SHIP_DATE = 4;

	/**
	 * The internal feature id for the '<em><b>Part Num</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */ 
	public final static int _INTERNAL_PART_NUM = 5;

	/**
	 * The number of properties for this type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public final static int INTERNAL_PROPERTY_COUNT = 6;

	protected int internalConvertIndex(int internalIndex)
	{
		switch (internalIndex)
		{
			case _INTERNAL_PRODUCT_NAME: return PRODUCT_NAME;
			case _INTERNAL_QUANTITY: return QUANTITY;
			case _INTERNAL_US_PRICE: return US_PRICE;
			case _INTERNAL_COMMENT: return COMMENT;
			case _INTERNAL_SHIP_DATE: return SHIP_DATE;
			case _INTERNAL_PART_NUM: return PART_NUM;
		}
		return super.internalConvertIndex(internalIndex);
	}


	/**
	 * The default value of the '{@link #getProductName() <em>Product Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProductName()
	 * @generated
	 * @ordered
	 */
	protected static final String PRODUCT_NAME_DEFAULT_ = null;

	/**
	 * The cached value of the '{@link #getProductName() <em>Product Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProductName()
	 * @generated
	 * @ordered
	 */
	protected String productName = PRODUCT_NAME_DEFAULT_;

	/**
	 * The default value of the '{@link #getQuantity() <em>Quantity</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getQuantity()
	 * @generated
	 * @ordered
	 */
	protected static final BigInteger QUANTITY_DEFAULT_ = null;

	/**
	 * The cached value of the '{@link #getQuantity() <em>Quantity</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getQuantity()
	 * @generated
	 * @ordered
	 */
	protected BigInteger quantity = QUANTITY_DEFAULT_;

	/**
	 * The default value of the '{@link #getUSPrice() <em>US Price</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUSPrice()
	 * @generated
	 * @ordered
	 */
	protected static final BigDecimal US_PRICE_DEFAULT_ = null;

	/**
	 * The cached value of the '{@link #getUSPrice() <em>US Price</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUSPrice()
	 * @generated
	 * @ordered
	 */
	protected BigDecimal usPrice = US_PRICE_DEFAULT_;

	/**
	 * The default value of the '{@link #getComment() <em>Comment</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getComment()
	 * @generated
	 * @ordered
	 */
	protected static final String COMMENT_DEFAULT_ = null;

	/**
	 * The cached value of the '{@link #getComment() <em>Comment</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getComment()
	 * @generated
	 * @ordered
	 */
	protected String comment = COMMENT_DEFAULT_;

	/**
	 * The default value of the '{@link #getShipDate() <em>Ship Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getShipDate()
	 * @generated
	 * @ordered
	 */
	protected static final String SHIP_DATE_DEFAULT_ = null;

	/**
	 * The cached value of the '{@link #getShipDate() <em>Ship Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getShipDate()
	 * @generated
	 * @ordered
	 */
	protected String shipDate = SHIP_DATE_DEFAULT_;

	/**
	 * The default value of the '{@link #getPartNum() <em>Part Num</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPartNum()
	 * @generated
	 * @ordered
	 */
	protected static final String PART_NUM_DEFAULT_ = null;

	/**
	 * The cached value of the '{@link #getPartNum() <em>Part Num</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPartNum()
	 * @generated
	 * @ordered
	 */
	protected String partNum = PART_NUM_DEFAULT_;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public itemImpl()
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
		return ((SdoFactoryImpl)SdoFactory.INSTANCE).getitem();
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
	public String getProductName()
	{
		return productName;
	}
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setProductName(String newProductName)
	{
		productName = newProductName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigInteger getQuantity()
	{
		return quantity;
	}
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setQuantity(BigInteger newQuantity)
	{
		quantity = newQuantity;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigDecimal getUSPrice()
	{
		return usPrice;
	}
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUSPrice(BigDecimal newUSPrice)
	{
		usPrice = newUSPrice;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getComment()
	{
		return comment;
	}
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setComment(String newComment)
	{
		comment = newComment;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getShipDate()
	{
		return shipDate;
	}
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setShipDate(String newShipDate)
	{
		shipDate = newShipDate;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getPartNum()
	{
		return partNum;
	}
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPartNum(String newPartNum)
	{
		partNum = newPartNum;
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
			case PRODUCT_NAME:
				return getProductName();
			case QUANTITY:
				return getQuantity();
			case US_PRICE:
				return getUSPrice();
			case COMMENT:
				return getComment();
			case SHIP_DATE:
				return getShipDate();
			case PART_NUM:
				return getPartNum();
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
			case PRODUCT_NAME:
				setProductName((String)newValue);
				return;
			case QUANTITY:
				setQuantity((BigInteger)newValue);
				return;
			case US_PRICE:
				setUSPrice((BigDecimal)newValue);
				return;
			case COMMENT:
				setComment((String)newValue);
				return;
			case SHIP_DATE:
				setShipDate((String)newValue);
				return;
			case PART_NUM:
				setPartNum((String)newValue);
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
			case PRODUCT_NAME:
				setProductName(PRODUCT_NAME_DEFAULT_);
				return;
			case QUANTITY:
				setQuantity(QUANTITY_DEFAULT_);
				return;
			case US_PRICE:
				setUSPrice(US_PRICE_DEFAULT_);
				return;
			case COMMENT:
				setComment(COMMENT_DEFAULT_);
				return;
			case SHIP_DATE:
				setShipDate(SHIP_DATE_DEFAULT_);
				return;
			case PART_NUM:
				setPartNum(PART_NUM_DEFAULT_);
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
			case PRODUCT_NAME:
				return PRODUCT_NAME_DEFAULT_ == null ? productName != null : !PRODUCT_NAME_DEFAULT_.equals(productName);
			case QUANTITY:
				return QUANTITY_DEFAULT_ == null ? quantity != null : !QUANTITY_DEFAULT_.equals(quantity);
			case US_PRICE:
				return US_PRICE_DEFAULT_ == null ? usPrice != null : !US_PRICE_DEFAULT_.equals(usPrice);
			case COMMENT:
				return COMMENT_DEFAULT_ == null ? comment != null : !COMMENT_DEFAULT_.equals(comment);
			case SHIP_DATE:
				return SHIP_DATE_DEFAULT_ == null ? shipDate != null : !SHIP_DATE_DEFAULT_.equals(shipDate);
			case PART_NUM:
				return PART_NUM_DEFAULT_ == null ? partNum != null : !PART_NUM_DEFAULT_.equals(partNum);
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
		result.append(" (productName: ");
		result.append(productName);
		result.append(", quantity: ");
		result.append(quantity);
		result.append(", USPrice: ");
		result.append(usPrice);
		result.append(", comment: ");
		result.append(comment);
		result.append(", shipDate: ");
		result.append(shipDate);
		result.append(", partNum: ");
		result.append(partNum);
		result.append(')');
		return result.toString();
	}

} //itemImpl
