/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.example.ipo.sdo.impl;

import commonj.sdo.helper.HelperContext;
import org.apache.tuscany.sdo.helper.HelperContextImpl;
import org.apache.tuscany.sdo.helper.TypeHelperImpl;

import com.example.ipo.sdo.*;

import commonj.sdo.DataObject;
import commonj.sdo.Property;
import commonj.sdo.Type;

import java.math.BigInteger;

import org.apache.tuscany.sdo.impl.FactoryBase;

import org.apache.tuscany.sdo.model.ModelFactory;

import org.apache.tuscany.sdo.model.impl.ModelFactoryImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * Generator information:
 * patternVersion=1.2; -prefix Sdo -noNotification -noUnsettable
 * <!-- end-user-doc -->
 * @generated
 */
public class SdoFactoryImpl extends FactoryBase implements SdoFactory
{

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String NAMESPACE_URI = "http://www.example.com/IPO";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String NAMESPACE_PREFIX = "ipo";

	/**
	 * The version of the generator pattern used to generate this class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String PATTERN_VERSION = "1.2";
	
	public static final int ADDRESS = 1;	
	public static final int ITEM = 2;	
	public static final int ITEMS = 3;	
	public static final int PURCHASE_ORDER_TYPE = 4;	
	public static final int UK_ADDRESS = 5;	
	public static final int US_ADDRESS = 6;	
	public static final int POSTCODE = 7;	
	public static final int QUANTITY = 8;	
	public static final int SKU = 9;	
	public static final int UK_POSTCODE = 10;	
	public static final int US_STATE = 11;
	
	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SdoFactoryImpl()
	{
		super(NAMESPACE_URI, NAMESPACE_PREFIX, "com.example.ipo.sdo");
	}

	/**
	 * Registers the Factory instance so that it is available within the supplied scope.
   * @argument scope a HelperContext instance that will make the types supported by this Factory available.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void register(HelperContext scope) 
	{
		if(scope == null) {
			throw new IllegalArgumentException("Scope can not be null");
		}

		if (((HelperContextImpl)scope).getExtendedMetaData().getPackage(NAMESPACE_URI) != null)
			return;
    
		// Register this package with provided scope   
		((HelperContextImpl)scope).getExtendedMetaData().putPackage(NAMESPACE_URI, this);
		
		//Register dependent packages with provided scope
		ModelFactory.INSTANCE.register(scope);
  }
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataObject create(int typeNumber)
	{
		switch (typeNumber)
		{
			case ADDRESS: return (DataObject)createAddress();
			case ITEM: return (DataObject)createitem();
			case ITEMS: return (DataObject)createItems();
			case PURCHASE_ORDER_TYPE: return (DataObject)createPurchaseOrderType();
			case UK_ADDRESS: return (DataObject)createUKAddress();
			case US_ADDRESS: return (DataObject)createUSAddress();
			default:
				return super.create(typeNumber);
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object createFromString(int typeNumber, String initialValue)
	{
		switch (typeNumber)
		{
			case POSTCODE:
				return createPostcodeFromString(initialValue);
			case QUANTITY:
				return createquantityFromString(initialValue);
			case SKU:
				return createSKUFromString(initialValue);
			case UK_POSTCODE:
				return createUKPostcodeFromString(initialValue);
			case US_STATE:
				return createUSStateFromString(initialValue);
			default:
				throw new IllegalArgumentException("The type number '" + typeNumber + "' is not a valid datatype");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertToString(int typeNumber, Object instanceValue)
	{
		switch (typeNumber)
		{
			case POSTCODE:
				return convertPostcodeToString(instanceValue);
			case QUANTITY:
				return convertquantityToString(instanceValue);
			case SKU:
				return convertSKUToString(instanceValue);
			case UK_POSTCODE:
				return convertUKPostcodeToString(instanceValue);
			case US_STATE:
				return convertUSStateToString(instanceValue);
			default:
				throw new IllegalArgumentException("The type number '" + typeNumber + "' is not a valid datatype");
		}
	}
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Address createAddress()
	{
		AddressImpl address = new AddressImpl();
		return address;
	}
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public item createitem()
	{
		itemImpl item = new itemImpl();
		return item;
	}
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Items createItems()
	{
		ItemsImpl items = new ItemsImpl();
		return items;
	}
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PurchaseOrderType createPurchaseOrderType()
	{
		PurchaseOrderTypeImpl purchaseOrderType = new PurchaseOrderTypeImpl();
		return purchaseOrderType;
	}
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UKAddress createUKAddress()
	{
		UKAddressImpl ukAddress = new UKAddressImpl();
		return ukAddress;
	}
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public USAddress createUSAddress()
	{
		USAddressImpl usAddress = new USAddressImpl();
		return usAddress;
	}
	
	// Following creates and initializes SDO metadata for the supported types.		
	protected Type addressType = null;

	public Type getAddress()
	{
		return addressType;
	}
			
	protected Type itemType = null;

	public Type getitem()
	{
		return itemType;
	}
		
	protected Type itemsType = null;

	public Type getItems()
	{
		return itemsType;
	}
		
	protected Type purchaseOrderTypeType = null;

	public Type getPurchaseOrderType()
	{
		return purchaseOrderTypeType;
	}
		
	protected Type ukAddressType = null;

	public Type getUKAddress()
	{
		return ukAddressType;
	}
		
	protected Type usAddressType = null;

	public Type getUSAddress()
	{
		return usAddressType;
	}
		
	protected Type postcodeType = null;

	public Type getPostcode()
	{
		return postcodeType;
	}
		
	protected Type quantityType = null;

	public Type getquantity()
	{
		return quantityType;
	}
		
	protected Type skuType = null;

	public Type getSKU()
	{
		return skuType;
	}
		
	protected Type ukPostcodeType = null;

	public Type getUKPostcode()
	{
		return ukPostcodeType;
	}
		
	protected Type usStateType = null;

	public Type getUSState()
	{
		return usStateType;
	}
	

	private static SdoFactoryImpl instance = null; 
	public static SdoFactoryImpl init()
	{
		if (instance != null ) return instance;
		instance = new SdoFactoryImpl();

		// Create package meta-data objects
		instance.createMetaData();

		// Initialize created meta-data
		instance.initializeMetaData();
		
		// Mark meta-data to indicate it can't be changed
		//theSdoFactoryImpl.freeze(); //FB do we need to freeze / should we freeze ????

		return instance;
	}
  
	private boolean isCreated = false;

	public void createMetaData()
	{
		if (isCreated) return;
		isCreated = true;	

		// Create types and their properties
		addressType = createType(false, ADDRESS);
		createProperty(true, addressType,AddressImpl._INTERNAL_NAME); 
		createProperty(true, addressType,AddressImpl._INTERNAL_STREET); 
		createProperty(true, addressType,AddressImpl._INTERNAL_CITY); 
		itemType = createType(false, ITEM);
		createProperty(true, itemType,itemImpl._INTERNAL_PRODUCT_NAME); 
		createProperty(true, itemType,itemImpl._INTERNAL_QUANTITY); 
		createProperty(true, itemType,itemImpl._INTERNAL_US_PRICE); 
		createProperty(true, itemType,itemImpl._INTERNAL_COMMENT); 
		createProperty(true, itemType,itemImpl._INTERNAL_SHIP_DATE); 
		createProperty(true, itemType,itemImpl._INTERNAL_PART_NUM); 
		itemsType = createType(false, ITEMS);
		createProperty(false, itemsType,ItemsImpl._INTERNAL_ITEM); 
		purchaseOrderTypeType = createType(false, PURCHASE_ORDER_TYPE);
		createProperty(false, purchaseOrderTypeType,PurchaseOrderTypeImpl._INTERNAL_SHIP_TO); 
		createProperty(false, purchaseOrderTypeType,PurchaseOrderTypeImpl._INTERNAL_BILL_TO); 
		createProperty(true, purchaseOrderTypeType,PurchaseOrderTypeImpl._INTERNAL_COMMENT); 
		createProperty(false, purchaseOrderTypeType,PurchaseOrderTypeImpl._INTERNAL_ITEMS); 
		createProperty(true, purchaseOrderTypeType,PurchaseOrderTypeImpl._INTERNAL_ORDER_DATE); 
		ukAddressType = createType(false, UK_ADDRESS);
		createProperty(true, ukAddressType,UKAddressImpl._INTERNAL_POSTCODE); 
		createProperty(true, ukAddressType,UKAddressImpl._INTERNAL_EXPORT_CODE); 
		usAddressType = createType(false, US_ADDRESS);
		createProperty(true, usAddressType,USAddressImpl._INTERNAL_STATE); 
		createProperty(true, usAddressType,USAddressImpl._INTERNAL_ZIP); 

		// Create data types
		postcodeType = createType(true, POSTCODE );
		quantityType = createType(true, QUANTITY );
		skuType = createType(true, SKU );
		ukPostcodeType = createType(true, UK_POSTCODE );
		usStateType = createType(true, US_STATE );
	}
	
	private boolean isInitialized = false;

	public void initializeMetaData()
	{
		if (isInitialized) return;
		isInitialized = true;

		// Obtain other dependent packages
		ModelFactoryImpl theModelPackageImpl = (ModelFactoryImpl)ModelFactoryImpl.init();
		Property property = null;

		// Add supertypes to types
		addSuperType(ukAddressType, this.getAddress());
		addSuperType(usAddressType, this.getAddress());

		// Initialize types and properties
		initializeType(addressType, Address.class, "Address", false);
		property = getLocalProperty(addressType, 0);
		initializeProperty(property, theModelPackageImpl.getString(), "name", null, 1, 1, Address.class, false, false, false);

		property = getLocalProperty(addressType, 1);
		initializeProperty(property, theModelPackageImpl.getString(), "street", null, 1, 1, Address.class, false, false, false);

		property = getLocalProperty(addressType, 2);
		initializeProperty(property, theModelPackageImpl.getString(), "city", null, 1, 1, Address.class, false, false, false);

		initializeType(itemType, item.class, "item", false);
		property = getLocalProperty(itemType, 0);
		initializeProperty(property, theModelPackageImpl.getString(), "productName", null, 1, 1, item.class, false, false, false);

		property = getLocalProperty(itemType, 1);
		initializeProperty(property, this.getquantity(), "quantity", null, 1, 1, item.class, false, false, false);

		property = getLocalProperty(itemType, 2);
		initializeProperty(property, theModelPackageImpl.getDecimal(), "USPrice", null, 1, 1, item.class, false, false, false);

		property = getLocalProperty(itemType, 3);
		initializeProperty(property, theModelPackageImpl.getString(), "comment", null, 0, 1, item.class, false, false, false);

		property = getLocalProperty(itemType, 4);
		initializeProperty(property, theModelPackageImpl.getYearMonthDay(), "shipDate", null, 0, 1, item.class, false, false, false);

		property = getLocalProperty(itemType, 5);
		initializeProperty(property, this.getSKU(), "partNum", null, 1, 1, item.class, false, false, false);

		initializeType(itemsType, Items.class, "Items", false);
		property = getLocalProperty(itemsType, 0);
		initializeProperty(property, this.getitem(), "item", null, 0, -1, Items.class, false, false, false, true , null);

		initializeType(purchaseOrderTypeType, PurchaseOrderType.class, "PurchaseOrderType", false);
		property = getLocalProperty(purchaseOrderTypeType, 0);
		initializeProperty(property, this.getAddress(), "shipTo", null, 1, 1, PurchaseOrderType.class, false, false, false, true , null);

		property = getLocalProperty(purchaseOrderTypeType, 1);
		initializeProperty(property, this.getAddress(), "billTo", null, 1, 1, PurchaseOrderType.class, false, false, false, true , null);

		property = getLocalProperty(purchaseOrderTypeType, 2);
		initializeProperty(property, theModelPackageImpl.getString(), "comment", null, 0, 1, PurchaseOrderType.class, false, false, false);

		property = getLocalProperty(purchaseOrderTypeType, 3);
		initializeProperty(property, this.getItems(), "items", null, 1, 1, PurchaseOrderType.class, false, false, false, true , null);

		property = getLocalProperty(purchaseOrderTypeType, 4);
		initializeProperty(property, theModelPackageImpl.getYearMonthDay(), "orderDate", null, 0, 1, PurchaseOrderType.class, false, false, false);

		initializeType(ukAddressType, UKAddress.class, "UKAddress", false);
		property = getLocalProperty(ukAddressType, 0);
		initializeProperty(property, this.getUKPostcode(), "postcode", null, 1, 1, UKAddress.class, false, false, false);

		property = getLocalProperty(ukAddressType, 1);
		initializeProperty(property, theModelPackageImpl.getInteger(), "exportCode", "1", 0, 1, UKAddress.class, false, false, false);

		initializeType(usAddressType, USAddress.class, "USAddress", false);
		property = getLocalProperty(usAddressType, 0);
		initializeProperty(property, this.getUSState(), "state", null, 1, 1, USAddress.class, false, false, false);

		property = getLocalProperty(usAddressType, 1);
		initializeProperty(property, theModelPackageImpl.getInteger(), "zip", null, 1, 1, USAddress.class, false, false, false);

		// Initialize data types
		initializeType(postcodeType, String.class, "Postcode", true, false);

		initializeType(quantityType, BigInteger.class, "quantity", true, false);

		initializeType(skuType, String.class, "SKU", true, false);

		initializeType(ukPostcodeType, String.class, "UKPostcode", true, false);

		initializeType(usStateType, String.class, "USState", true, false);

		createXSDMetaData(theModelPackageImpl);
	}
	  
	protected void createXSDMetaData(ModelFactoryImpl theModelPackageImpl)
	{
		super.initXSD();
		
		Property property = null;
		

		addXSDMapping
		  (addressType,
			 new String[] 
			 {
			 "name", "Address",
			 "kind", "elementOnly"
			 });

		addXSDMapping
			(getLocalProperty(addressType, 0),
			 new String[]
			 {
			 "kind", "element",
			 "name", "name"
			 });

		addXSDMapping
			(getLocalProperty(addressType, 1),
			 new String[]
			 {
			 "kind", "element",
			 "name", "street"
			 });

		addXSDMapping
			(getLocalProperty(addressType, 2),
			 new String[]
			 {
			 "kind", "element",
			 "name", "city"
			 });

		property = createGlobalProperty
		  ("comment",
		  theModelPackageImpl.getString(),
			 new String[]
			 {
			 "kind", "element",
			 "name", "comment",
			 "namespace", "##targetNamespace"
			 },
			 IS_ATTRIBUTE);
                
		property = createGlobalProperty
		  ("purchaseOrder",
		  this.getPurchaseOrderType(),
			 new String[]
			 {
			 "kind", "element",
			 "name", "purchaseOrder",
			 "namespace", "##targetNamespace"
			 });
                
		addXSDMapping
		  (itemType,
			 new String[] 
			 {
			 "name", "item_._type",
			 "kind", "elementOnly"
			 });

		addXSDMapping
			(getLocalProperty(itemType, 0),
			 new String[]
			 {
			 "kind", "element",
			 "name", "productName"
			 });

		addXSDMapping
			(getLocalProperty(itemType, 1),
			 new String[]
			 {
			 "kind", "element",
			 "name", "quantity"
			 });

		addXSDMapping
			(getLocalProperty(itemType, 2),
			 new String[]
			 {
			 "kind", "element",
			 "name", "USPrice"
			 });

		addXSDMapping
			(getLocalProperty(itemType, 3),
			 new String[]
			 {
			 "kind", "element",
			 "name", "comment",
			 "namespace", "##targetNamespace"
			 });

		addXSDMapping
			(getLocalProperty(itemType, 4),
			 new String[]
			 {
			 "kind", "element",
			 "name", "shipDate"
			 });

		addXSDMapping
			(getLocalProperty(itemType, 5),
			 new String[]
			 {
			 "kind", "attribute",
			 "name", "partNum"
			 });

		addXSDMapping
		  (itemsType,
			 new String[] 
			 {
			 "name", "Items",
			 "kind", "elementOnly"
			 });

		addXSDMapping
			(getLocalProperty(itemsType, 0),
			 new String[]
			 {
			 "kind", "element",
			 "name", "item"
			 });

		addXSDMapping
		  (purchaseOrderTypeType,
			 new String[] 
			 {
			 "name", "PurchaseOrderType",
			 "kind", "elementOnly"
			 });

		addXSDMapping
			(getLocalProperty(purchaseOrderTypeType, 0),
			 new String[]
			 {
			 "kind", "element",
			 "name", "shipTo"
			 });

		addXSDMapping
			(getLocalProperty(purchaseOrderTypeType, 1),
			 new String[]
			 {
			 "kind", "element",
			 "name", "billTo"
			 });

		addXSDMapping
			(getLocalProperty(purchaseOrderTypeType, 2),
			 new String[]
			 {
			 "kind", "element",
			 "name", "comment",
			 "namespace", "##targetNamespace"
			 });

		addXSDMapping
			(getLocalProperty(purchaseOrderTypeType, 3),
			 new String[]
			 {
			 "kind", "element",
			 "name", "items"
			 });

		addXSDMapping
			(getLocalProperty(purchaseOrderTypeType, 4),
			 new String[]
			 {
			 "kind", "attribute",
			 "name", "orderDate"
			 });

		addXSDMapping
		  (ukAddressType,
			 new String[] 
			 {
			 "name", "UKAddress",
			 "kind", "elementOnly"
			 });

		addXSDMapping
			(getLocalProperty(ukAddressType, 0),
			 new String[]
			 {
			 "kind", "element",
			 "name", "postcode"
			 });

		addXSDMapping
			(getLocalProperty(ukAddressType, 1),
			 new String[]
			 {
			 "kind", "attribute",
			 "name", "exportCode"
			 });

		addXSDMapping
		  (usAddressType,
			 new String[] 
			 {
			 "name", "USAddress",
			 "kind", "elementOnly"
			 });

		addXSDMapping
			(getLocalProperty(usAddressType, 0),
			 new String[]
			 {
			 "kind", "element",
			 "name", "state"
			 });

		addXSDMapping
			(getLocalProperty(usAddressType, 1),
			 new String[]
			 {
			 "kind", "element",
			 "name", "zip"
			 });

		addXSDMapping
		  (postcodeType,
			 new String[] 
			 {
			 "name", "Postcode",
			 "baseType", "commonj.sdo#String",
			 "length", "7"
			 });

		addXSDMapping
		  (quantityType,
			 new String[] 
			 {
			 "name", "quantity_._type",
			 "baseType", "commonj.sdo#Integer",
			 "maxExclusive", "100"
			 });

		addXSDMapping
		  (skuType,
			 new String[] 
			 {
			 "name", "SKU",
			 "baseType", "commonj.sdo#String",
			 "pattern", "\\d{3}-[A-Z]{2}"
			 });

		addXSDMapping
		  (ukPostcodeType,
			 new String[] 
			 {
			 "name", "UKPostcode",
			 "baseType", "Postcode",
			 "pattern", "[A-Z]{2}\\d\\s\\d[A-Z]{2}"
			 });

		addXSDMapping
		  (usStateType,
			 new String[] 
			 {
			 "name", "USState",
			 "baseType", "commonj.sdo#String",
			 "enumeration", "AK AL AR CA PA"
			 });

  }
    
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String createPostcodeFromString(String initialValue)
	{
		return (String)((ModelFactoryImpl)ModelFactory.INSTANCE).createStringFromString(initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertPostcodeToString(Object instanceValue)
	{
		return ((ModelFactoryImpl)ModelFactory.INSTANCE).convertStringToString(instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigInteger createquantityFromString(String initialValue)
	{
		return (BigInteger)((ModelFactoryImpl)ModelFactory.INSTANCE).createIntegerFromString(initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertquantityToString(Object instanceValue)
	{
		return ((ModelFactoryImpl)ModelFactory.INSTANCE).convertIntegerToString(instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String createSKUFromString(String initialValue)
	{
		return (String)((ModelFactoryImpl)ModelFactory.INSTANCE).createStringFromString(initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertSKUToString(Object instanceValue)
	{
		return ((ModelFactoryImpl)ModelFactory.INSTANCE).convertStringToString(instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String createUKPostcodeFromString(String initialValue)
	{
		return (String)createPostcodeFromString(initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertUKPostcodeToString(Object instanceValue)
	{
		return convertPostcodeToString(instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String createUSStateFromString(String initialValue)
	{
		return (String)((ModelFactoryImpl)ModelFactory.INSTANCE).createStringFromString(initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertUSStateToString(Object instanceValue)
	{
		return ((ModelFactoryImpl)ModelFactory.INSTANCE).convertStringToString(instanceValue);
	}

} //SdoFactoryImpl
