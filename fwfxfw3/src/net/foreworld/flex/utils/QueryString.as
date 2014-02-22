package net.foreworld.flex.utils
{
	import flash.external.ExternalInterface;
	import flash.utils.Dictionary;
	import flash.utils.getQualifiedClassName;	
	
	/**
	 * Singleton used to grab data out of the query string.
	 * 
	 * @author Matt Przybylski [http://www.reintroducing.com]
	 * @version 1.1
	 * 
	 * 主页 http://evolve.reintroducing.com/2008/07/03/as3/as3-querystring/
	 * 
	 * 用法：var __qs:QueryString = QueryString.getInstance();
	 *		Alert.show(__qs.getValue('param'));
	 */
	public final class QueryString
	{
//- PRIVATE & PROTECTED VARIABLES -------------------------------------------------------------------------

		// singleton instance
		private static var _instance:QueryString;
		private static var _allowInstance:Boolean;
		
		private var _pairDict:Dictionary;
		private var _url:String;
		private var _pairs:Array;
		
//- PUBLIC & INTERNAL VARIABLES ---------------------------------------------------------------------------
		
		
		
//- CONSTRUCTOR	-------------------------------------------------------------------------------------------
	
		// singleton instance of QueryString
		public static function getInstance():QueryString 
		{
			if (QueryString._instance == null)
			{
				QueryString._allowInstance = true;
				QueryString._instance = new QueryString();
				QueryString._allowInstance = false;
			}
			
			return QueryString._instance;
		}
		
		public function QueryString() 
		{
			this.parseValues();
			
			if (!QueryString._allowInstance)
			{
				throw new Error("Error: Use QueryString.getInstance() instead of the new keyword.");
			}
		}
		
//- PRIVATE & PROTECTED METHODS ---------------------------------------------------------------------------
		
		private function parseValues():void
		{
			this._url = ExternalInterface.call("document.location.search.toString");
			this._pairDict = new Dictionary(true);
			//2010-6-20 优化 不加这行  赋值空则抛异常
			if(this._url == "") return;
			//
			this._pairs = this._url.split("?")[1].split("&");
			
			var pairName:String;
			var pairValue:String;
			
			for (var i:int = 0; i < this._pairs.length; i++)
			{
				pairName = this._pairs[i].split("=")[0];
				pairValue = this._pairs[i].split("=")[1];
				
				this._pairDict[pairName] = pairValue;
			}
		}
		
//- PUBLIC & INTERNAL METHODS -----------------------------------------------------------------------------
	
		/**
		 * Returns the value of the specified query string parameter.
		 * 
		 * @param $val A string identifying the parameter for whose value you want to retrieve
		 * 
		 * @return String The value for the given parameter
		 */
		public function getValue($val:String):String
		{
			if (this._pairDict[$val] == null)
			{
				return "";
			}
			else
			{
				return this._pairDict[$val];
			}
		}
	
//- EVENT HANDLERS ----------------------------------------------------------------------------------------
	
		
	
//- GETTERS & SETTERS -------------------------------------------------------------------------------------
	
		
	
//- HELPERS -----------------------------------------------------------------------------------------------
	
		public function toString():String
		{
			return getQualifiedClassName(this);
		}
	
//- END CLASS ---------------------------------------------------------------------------------------------
	}
}