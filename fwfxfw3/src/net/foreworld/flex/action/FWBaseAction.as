package net.foreworld.flex.action
{
	import flash.display.Sprite;
	
	import mx.logging.Log;
	
	import net.foreworld.flex.event.FWEvent;
	import net.foreworld.flex.framework.IFWApplication;
	
	public class FWBaseAction extends Sprite implements IFWBaseAction
	{
		private var _parentApp:IFWApplication;
		
		private var _id:String;
		private var _aname:String;
		private var _title:String;
		private var _desc:String;
		private var _version:String;
		
		public function FWBaseAction()
		{
			super();
		}
		
		public function get parentApp():IFWApplication{
			return this._parentApp;
		}
		
		public function set parentApp($val:IFWApplication):void{
			this._parentApp = $val;
		}
		
		public function get id():String
		{
			return this._id;
		}
		
		public function set id($val:String):void{
			this._id = $val;
		}
		
		public function get aname():String
		{
			return this._aname;
		}
		
		public function set aname($val:String):void{
			this._aname = $val;
		}
		
		public function get title():String
		{
			return this._title;
		}
		
		public function set title($val:String):void{
			this._title = $val;
		}
		
		public function get desc():String
		{
			return this._desc;
		}
		
		public function set desc($val:String):void{
			this._desc = $val;
		}
		
		public function get version():String
		{
			return this._version;
		}
		
		public function set version($val:String):void{
			this._version = $val;
		}
		
		public function init($val:XML):void
		{
			Log.getLogger(this.id).info("init");
		}
		
		public function destory():void
		{
			Log.getLogger(this.id).info("destory");
			
			this.parentApp.dispatchEvent(new FWEvent(FWEvent.ACTION_UNLOAD, false, false, this));
		}
		
		public function start():void
		{
			Log.getLogger(this.id).info("start");
		}
		
		public function stop():void
		{
			Log.getLogger(this.id).info("stop");
		}
		
	}
}