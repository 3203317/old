package net.foreworld.flex.components
{
	import mx.controls.CheckBox;

	[IconFile('FWCheckBox/icon.png')]
	public class FWCheckBox extends CheckBox implements IFWComponent
	{
		private var _cid:String;
		private var _cname:String;
        private var _desc:String;
        private var _version:String;        
        private var _title:String;     
        
        private var _cfgUrl:String;  
		
		public function set cid($val:String):void{
			this._cid = $val;
		}
		public function get cid():String
		{
			return this._cid;
		}
		
		public function set cname($val:String):void{
			this._cname = $val;
		}
		
		public function get cname():String
		{
			return this._cname;
		}
		
		public function set desc($val:String):void{
			this._desc = $val;
		}
		
		public function get desc():String
		{
			return this._desc;
		}
		
		public function set version($val:String):void{
			this._version = $val;
		}
		
		public function get version():String
		{
			return this._version;
		}
		
		public function set title($val:String):void{
			this._title = $val;
		}
		
		public function get title():String
		{
			return this._title;
		}
		
		public function set cfgUrl($val:String):void{
			this._cfgUrl = $val;
		}
		
		public function get cfgUrl():String
		{
			return this._cfgUrl;
		}
		
		public function FWCheckBox()
		{
			super();
		}
		
		private var _value:Object;
		
		public function get value():Object{
			return this._value;
		}
		
		[Inspectable(category="Foreworld", type="Object", defaultValue="")]
		public function set value($val:*):void{
			this._value = $val;
		}
	}
}