/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.example.stock.sdo.impl;

import commonj.sdo.helper.HelperContext;
import org.apache.tuscany.sdo.helper.HelperContextImpl;
import org.apache.tuscany.sdo.helper.TypeHelperImpl;

import com.example.stock.sdo.*;

import commonj.sdo.DataObject;
import commonj.sdo.Property;
import commonj.sdo.Type;

import org.apache.tuscany.sdo.impl.FactoryBase;

import org.apache.tuscany.sdo.model.ModelFactory;

import org.apache.tuscany.sdo.model.impl.ModelFactoryImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * Generator information:
 * patternVersion=1.2; -noNotification -noUnsettable
 * <!-- end-user-doc -->
 * @generated
 */
public class StockFactoryImpl extends FactoryBase implements StockFactory
{

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String NAMESPACE_URI = "http://www.example.com/stock";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String NAMESPACE_PREFIX = "stock";

	/**
	 * The version of the generator pattern used to generate this class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String PATTERN_VERSION = "1.2";
	
	public static final int INVALID_SYMBOL_FAULT = 1;
	
	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StockFactoryImpl()
	{
		super(NAMESPACE_URI, NAMESPACE_PREFIX, "com.example.stock.sdo");
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
			case INVALID_SYMBOL_FAULT: return (DataObject)createInvalidSymbolFault();
			default:
				return super.create(typeNumber);
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InvalidSymbolFault createInvalidSymbolFault()
	{
		InvalidSymbolFaultImpl invalidSymbolFault = new InvalidSymbolFaultImpl();
		return invalidSymbolFault;
	}
	
	// Following creates and initializes SDO metadata for the supported types.			
	protected Type invalidSymbolFaultType = null;

	public Type getInvalidSymbolFault()
	{
		return invalidSymbolFaultType;
	}
	

	private static StockFactoryImpl instance = null; 
	public static StockFactoryImpl init()
	{
		if (instance != null ) return instance;
		instance = new StockFactoryImpl();

		// Create package meta-data objects
		instance.createMetaData();

		// Initialize created meta-data
		instance.initializeMetaData();
		
		// Mark meta-data to indicate it can't be changed
		//theStockFactoryImpl.freeze(); //FB do we need to freeze / should we freeze ????

		return instance;
	}
  
	private boolean isCreated = false;

	public void createMetaData()
	{
		if (isCreated) return;
		isCreated = true;	

		// Create types and their properties
		invalidSymbolFaultType = createType(false, INVALID_SYMBOL_FAULT);
		createProperty(true, invalidSymbolFaultType,InvalidSymbolFaultImpl._INTERNAL_MESSAGE); 
		createProperty(true, invalidSymbolFaultType,InvalidSymbolFaultImpl._INTERNAL_SYMBOL); 
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

		// Initialize types and properties
		initializeType(invalidSymbolFaultType, InvalidSymbolFault.class, "InvalidSymbolFault", false);
		property = getLocalProperty(invalidSymbolFaultType, 0);
		initializeProperty(property, theModelPackageImpl.getString(), "message", null, 1, 1, InvalidSymbolFault.class, false, false, false);

		property = getLocalProperty(invalidSymbolFaultType, 1);
		initializeProperty(property, theModelPackageImpl.getString(), "symbol", null, 1, 1, InvalidSymbolFault.class, false, false, false);

		createXSDMetaData(theModelPackageImpl);
	}
	  
	protected void createXSDMetaData(ModelFactoryImpl theModelPackageImpl)
	{
		super.initXSD();
		
		Property property = null;
		

		property = createGlobalProperty
		  ("InvalidSymbolFault",
		  this.getInvalidSymbolFault(),
			 new String[]
			 {
			 "kind", "element",
			 "name", "InvalidSymbolFault",
			 "namespace", "##targetNamespace"
			 });
                
		property = createGlobalProperty
		  ("MarketClosedFault",
		  theModelPackageImpl.getString(),
			 new String[]
			 {
			 "kind", "element",
			 "name", "MarketClosedFault",
			 "namespace", "##targetNamespace"
			 },
			 IS_ATTRIBUTE);
                
		addXSDMapping
		  (invalidSymbolFaultType,
			 new String[] 
			 {
			 "name", "InvalidSymbolFault_._type",
			 "kind", "elementOnly"
			 });

		addXSDMapping
			(getLocalProperty(invalidSymbolFaultType, 0),
			 new String[]
			 {
			 "kind", "element",
			 "name", "message"
			 });

		addXSDMapping
			(getLocalProperty(invalidSymbolFaultType, 1),
			 new String[]
			 {
			 "kind", "element",
			 "name", "symbol"
			 });

  }
    
} //StockFactoryImpl
