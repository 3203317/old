/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.example.ipo.sdo.impl;

import com.example.ipo.sdo.Items;
import com.example.ipo.sdo.SdoFactory;
import com.example.ipo.sdo.item;

import commonj.sdo.Type;

import java.util.Collection;
import java.util.List;

import org.apache.tuscany.sdo.impl.DataObjectBase;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Items</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.example.ipo.sdo.impl.ItemsImpl#getItem <em>Item</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ItemsImpl extends DataObjectBase implements Items
{

	public final static int ITEM = 0;

	public final static int SDO_PROPERTY_COUNT = 1;

	public final static int EXTENDED_PROPERTY_COUNT = 0;


	/**
	 * The internal feature id for the '<em><b>Item</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */ 
	public final static int _INTERNAL_ITEM = 0;

	/**
	 * The number of properties for this type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public final static int INTERNAL_PROPERTY_COUNT = 1;

	protected int internalConvertIndex(int internalIndex)
	{
		switch (internalIndex)
		{
			case _INTERNAL_ITEM: return ITEM;
		}
		return super.internalConvertIndex(internalIndex);
	}


	/**
	 * The cached value of the '{@link #getItem() <em>Item</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getItem()
	 * @generated
	 * @ordered
	 */
	
	protected List item = null;
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ItemsImpl()
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
		return ((SdoFactoryImpl)SdoFactory.INSTANCE).getItems();
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
	public List getItem()
	{
		if (item == null)
		{
		  item = createPropertyList(ListKind.CONTAINMENT, item.class, ITEM, 0);
		}
		return item;
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
			case ITEM:
				return removeFromList(getItem(), otherEnd, changeContext);
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
			case ITEM:
				return getItem();
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
			case ITEM:
				getItem().clear();
				getItem().addAll((Collection)newValue);
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
			case ITEM:
				getItem().clear();
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
			case ITEM:
				return item != null && !item.isEmpty();
		}
		return super.isSet(propertyIndex);
	}

} //ItemsImpl
