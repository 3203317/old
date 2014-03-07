/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.example.ipo.sdo.impl;

import com.example.ipo.sdo.Address;
import com.example.ipo.sdo.Items;
import com.example.ipo.sdo.PurchaseOrderType;
import com.example.ipo.sdo.SdoFactory;

import commonj.sdo.Type;

import org.apache.tuscany.sdo.impl.DataObjectBase;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Purchase Order Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.example.ipo.sdo.impl.PurchaseOrderTypeImpl#getShipTo <em>Ship To</em>}</li>
 *   <li>{@link com.example.ipo.sdo.impl.PurchaseOrderTypeImpl#getBillTo <em>Bill To</em>}</li>
 *   <li>{@link com.example.ipo.sdo.impl.PurchaseOrderTypeImpl#getComment <em>Comment</em>}</li>
 *   <li>{@link com.example.ipo.sdo.impl.PurchaseOrderTypeImpl#getItems <em>Items</em>}</li>
 *   <li>{@link com.example.ipo.sdo.impl.PurchaseOrderTypeImpl#getOrderDate <em>Order Date</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PurchaseOrderTypeImpl extends DataObjectBase implements PurchaseOrderType
{

	public final static int SHIP_TO = 0;

	public final static int BILL_TO = 1;

	public final static int COMMENT = 2;

	public final static int ITEMS = 3;

	public final static int ORDER_DATE = 4;

	public final static int SDO_PROPERTY_COUNT = 5;

	public final static int EXTENDED_PROPERTY_COUNT = 0;


	/**
	 * The internal feature id for the '<em><b>Ship To</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */ 
	public final static int _INTERNAL_SHIP_TO = 0;

	/**
	 * The internal feature id for the '<em><b>Bill To</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */ 
	public final static int _INTERNAL_BILL_TO = 1;

	/**
	 * The internal feature id for the '<em><b>Comment</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */ 
	public final static int _INTERNAL_COMMENT = 2;

	/**
	 * The internal feature id for the '<em><b>Items</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */ 
	public final static int _INTERNAL_ITEMS = 3;

	/**
	 * The internal feature id for the '<em><b>Order Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */ 
	public final static int _INTERNAL_ORDER_DATE = 4;

	/**
	 * The number of properties for this type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public final static int INTERNAL_PROPERTY_COUNT = 5;

	protected int internalConvertIndex(int internalIndex)
	{
		switch (internalIndex)
		{
			case _INTERNAL_SHIP_TO: return SHIP_TO;
			case _INTERNAL_BILL_TO: return BILL_TO;
			case _INTERNAL_COMMENT: return COMMENT;
			case _INTERNAL_ITEMS: return ITEMS;
			case _INTERNAL_ORDER_DATE: return ORDER_DATE;
		}
		return super.internalConvertIndex(internalIndex);
	}


	/**
	 * The cached value of the '{@link #getShipTo() <em>Ship To</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getShipTo()
	 * @generated
	 * @ordered
	 */
	
	protected Address shipTo = null;
	
	/**
	 * The cached value of the '{@link #getBillTo() <em>Bill To</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBillTo()
	 * @generated
	 * @ordered
	 */
	
	protected Address billTo = null;
	
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
	 * The cached value of the '{@link #getItems() <em>Items</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getItems()
	 * @generated
	 * @ordered
	 */
	
	protected Items items = null;
	
	/**
	 * The default value of the '{@link #getOrderDate() <em>Order Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOrderDate()
	 * @generated
	 * @ordered
	 */
	protected static final String ORDER_DATE_DEFAULT_ = null;

	/**
	 * The cached value of the '{@link #getOrderDate() <em>Order Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOrderDate()
	 * @generated
	 * @ordered
	 */
	protected String orderDate = ORDER_DATE_DEFAULT_;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PurchaseOrderTypeImpl()
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
		return ((SdoFactoryImpl)SdoFactory.INSTANCE).getPurchaseOrderType();
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
	public Address getShipTo()
	{
		return shipTo;
	}
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ChangeContext basicSetShipTo(Address newShipTo, ChangeContext changeContext)
	{
		Address oldShipTo = shipTo;
		shipTo = newShipTo;
		return changeContext;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setShipTo(Address newShipTo)
	{
		if (newShipTo != shipTo)
		{
			ChangeContext changeContext = null;
			if (shipTo != null)
				changeContext = inverseRemove(shipTo, this, OPPOSITE_FEATURE_BASE - _INTERNAL_SHIP_TO, null, changeContext);
			if (newShipTo != null)
				changeContext = inverseAdd(newShipTo, this, OPPOSITE_FEATURE_BASE - _INTERNAL_SHIP_TO, null, changeContext);
			changeContext = basicSetShipTo(newShipTo, changeContext);
			if (changeContext != null) dispatch(changeContext);
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Address getBillTo()
	{
		return billTo;
	}
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ChangeContext basicSetBillTo(Address newBillTo, ChangeContext changeContext)
	{
		Address oldBillTo = billTo;
		billTo = newBillTo;
		return changeContext;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBillTo(Address newBillTo)
	{
		if (newBillTo != billTo)
		{
			ChangeContext changeContext = null;
			if (billTo != null)
				changeContext = inverseRemove(billTo, this, OPPOSITE_FEATURE_BASE - _INTERNAL_BILL_TO, null, changeContext);
			if (newBillTo != null)
				changeContext = inverseAdd(newBillTo, this, OPPOSITE_FEATURE_BASE - _INTERNAL_BILL_TO, null, changeContext);
			changeContext = basicSetBillTo(newBillTo, changeContext);
			if (changeContext != null) dispatch(changeContext);
		}
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
	public Items getItems()
	{
		return items;
	}
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ChangeContext basicSetItems(Items newItems, ChangeContext changeContext)
	{
		Items oldItems = items;
		items = newItems;
		return changeContext;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setItems(Items newItems)
	{
		if (newItems != items)
		{
			ChangeContext changeContext = null;
			if (items != null)
				changeContext = inverseRemove(items, this, OPPOSITE_FEATURE_BASE - _INTERNAL_ITEMS, null, changeContext);
			if (newItems != null)
				changeContext = inverseAdd(newItems, this, OPPOSITE_FEATURE_BASE - _INTERNAL_ITEMS, null, changeContext);
			changeContext = basicSetItems(newItems, changeContext);
			if (changeContext != null) dispatch(changeContext);
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getOrderDate()
	{
		return orderDate;
	}
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOrderDate(String newOrderDate)
	{
		orderDate = newOrderDate;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ChangeContext inverseRemove(Object otherEnd, int propertyIndex, ChangeContext changeContext)
	{
		switch (propertyIndex)
		{
			case SHIP_TO:
				return basicSetShipTo(null, changeContext);
			case BILL_TO:
				return basicSetBillTo(null, changeContext);
			case ITEMS:
				return basicSetItems(null, changeContext);
		}
		return super.inverseRemove(otherEnd, propertyIndex, changeContext);
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
			case SHIP_TO:
				return getShipTo();
			case BILL_TO:
				return getBillTo();
			case COMMENT:
				return getComment();
			case ITEMS:
				return getItems();
			case ORDER_DATE:
				return getOrderDate();
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
			case SHIP_TO:
				setShipTo((Address)newValue);
				return;
			case BILL_TO:
				setBillTo((Address)newValue);
				return;
			case COMMENT:
				setComment((String)newValue);
				return;
			case ITEMS:
				setItems((Items)newValue);
				return;
			case ORDER_DATE:
				setOrderDate((String)newValue);
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
			case SHIP_TO:
				setShipTo((Address)null);
				return;
			case BILL_TO:
				setBillTo((Address)null);
				return;
			case COMMENT:
				setComment(COMMENT_DEFAULT_);
				return;
			case ITEMS:
				setItems((Items)null);
				return;
			case ORDER_DATE:
				setOrderDate(ORDER_DATE_DEFAULT_);
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
			case SHIP_TO:
				return shipTo != null;
			case BILL_TO:
				return billTo != null;
			case COMMENT:
				return COMMENT_DEFAULT_ == null ? comment != null : !COMMENT_DEFAULT_.equals(comment);
			case ITEMS:
				return items != null;
			case ORDER_DATE:
				return ORDER_DATE_DEFAULT_ == null ? orderDate != null : !ORDER_DATE_DEFAULT_.equals(orderDate);
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
		result.append(" (comment: ");
		result.append(comment);
		result.append(", orderDate: ");
		result.append(orderDate);
		result.append(')');
		return result.toString();
	}

} //PurchaseOrderTypeImpl
