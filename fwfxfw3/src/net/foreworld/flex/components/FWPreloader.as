/**
 * Created by 黄鑫 | 3203317@QQ.COM | HTTP://WWW.FOREWORLD.NET | 2009-12-14
 * */
package net.foreworld.flex.components
{
	import flash.display.Loader;
	import flash.display.Sprite;
	import flash.events.Event;
	import flash.events.ProgressEvent;
	import flash.net.URLRequest;
	import flash.text.TextField;
	import flash.text.TextFormat;
	
	import mx.events.FlexEvent;
	import mx.preloaders.DownloadProgressBar;

	public final class FWPreloader extends DownloadProgressBar implements IFWComponent
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
		
		private var _l:Loader;
		
		private var _tx:TextField;
		
		private var _s:Sprite;
		
		public function FWPreloader()
		{
			this._l = new Loader;
			this._l.load(new URLRequest('net/foreworld/flex/components/fwPreloader.swf'));
			this.addChild(this._l);
			
			var __tf:TextFormat = new TextFormat(null, null, 0xFFFFFF, null, null, null, null, null, 'left');
			
			this._tx = new TextField;
			this._tx.defaultTextFormat = __tf;
			this._tx.selectable = false;
			this.addChild(_tx);
			
			__tf = null;	
			
			super();
		}
		
		override public function set preloader($s:Sprite):void{  
			this._s = $s; 
  			//四个侦听~分别是 加载进度 / 加载完毕 / 初始化进度 / 初始化完毕
			this._s.addEventListener(ProgressEvent.PROGRESS, this._s_PROGRESS_Handler);
			this._s.addEventListener(Event.COMPLETE, this._s_COMPLETE_Handler);
			this._s.addEventListener(FlexEvent.INIT_PROGRESS, this._s_INIT_PROGRESS_Handler);
			this._s.addEventListener(FlexEvent.INIT_COMPLETE, this._s_INIT_COMPLETE_Handler);
			
			this.stage.addEventListener(Event.RESIZE, this.stage_RESIZE_Handler);
			
			this.stage_RESIZE_Handler();
			
		}
		
		/**
		 * 正在加载
		 * */
		private function _s_PROGRESS_Handler($evt:ProgressEvent):void{
			this._tx.text = "正在加载..."+ int($evt.bytesLoaded / $evt.bytesTotal * 100) +"%";
		}
		
		/**
		 * 加载完毕
		 * */
		private function _s_COMPLETE_Handler($evt:Event):void{
			
		}
		
		/**
		 * 正在初始化...
		 * */
		private function _s_INIT_PROGRESS_Handler($evt:FlexEvent):void{
			
		}
		
		/**
		 * 初始化完毕
		 * */
		private function _s_INIT_COMPLETE_Handler($evt:FlexEvent):void{
			this.remove();
			this.dispatchEvent(new Event(Event.COMPLETE));
		}
		
		private function remove():void{
			this._s.removeEventListener(ProgressEvent.PROGRESS, this._s_PROGRESS_Handler);
			this._s.removeEventListener(Event.COMPLETE, this._s_COMPLETE_Handler);
			this._s.removeEventListener(FlexEvent.INIT_PROGRESS, this._s_INIT_PROGRESS_Handler);
			this._s.removeEventListener(FlexEvent.INIT_COMPLETE, this._s_INIT_COMPLETE_Handler);
			
			this.stage.removeEventListener(Event.RESIZE, this.stage_RESIZE_Handler);
		}
		
		private function stage_RESIZE_Handler($evt:Event = null):void{
			this._l.x = this.stage.stageWidth * .5 - 285;		
			this._l.y = this.stage.stageHeight * .5 - 190;
			
			this._tx.x = 0;
			this._tx.y = this.stage.stageHeight - 20;
			
			this.graphics.clear();
			this.graphics.beginFill(0x333333);
			this.graphics.drawRect(0, 0, this.stage.stageWidth, this.stage.stageHeight);
			this.graphics.endFill();
		}
		
	}
}