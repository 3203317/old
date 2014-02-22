package net.foreworld.flex.components
{
	import flash.ui.ContextMenu;
	import flash.ui.ContextMenuItem;
	
	import mx.controls.VideoDisplay;
	import mx.events.FlexEvent;
	import mx.events.VideoEvent;

	[IconFile('FWVideoDisplay/icon.png')]
	public class FWVideoDisplay extends VideoDisplay implements IFWComponent
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
		
		public function FWVideoDisplay()
		{
			super();
			
			this.addEventListener(FlexEvent.CREATION_COMPLETE, this.this_CREATION_COMPLETE_Handler);
		}
		
		private function this_CREATION_COMPLETE_Handler($evt:FlexEvent):void{
			//生成右键菜单
			var __contextMenu:ContextMenu = new ContextMenu;
			var __contextMenuItem:ContextMenuItem = new ContextMenuItem(this.version);
			__contextMenuItem.enabled = false;
			__contextMenu.customItems.push(__contextMenuItem);
			this.contextMenu = __contextMenu;
			this.contextMenu.hideBuiltInItems();
			
			//添加监听
			this.addEventListener(VideoEvent.CLOSE, this.this_CLOSE_Handler);
			this.addEventListener(VideoEvent.COMPLETE, this.this_COMPLETE_Handler);
			this.addEventListener(VideoEvent.PLAYHEAD_UPDATE, this.this_PLAYHEAD_UPDATE_Handler);
			this.addEventListener(VideoEvent.READY, this.this_READY_Handler);
			this.addEventListener(VideoEvent.REWIND, this.this_REWIND_Handler);
			this.addEventListener(VideoEvent.STATE_CHANGE, this.this_STATE_CHANGE_Handler);
		}
		
		private function this_CLOSE_Handler($evt:VideoEvent):void{
			this.removeEventListener(VideoEvent.CLOSE, this.this_CLOSE_Handler);	
		}
		
		private function this_COMPLETE_Handler($evt:VideoEvent):void{
			this.removeEventListener(VideoEvent.COMPLETE, this.this_COMPLETE_Handler);	
		}
		
		private function this_PLAYHEAD_UPDATE_Handler($evt:VideoEvent):void{
			this.removeEventListener(VideoEvent.PLAYHEAD_UPDATE, this.this_PLAYHEAD_UPDATE_Handler);	
		}
		
		private function this_READY_Handler($evt:VideoEvent):void{
			this.removeEventListener(VideoEvent.READY, this.this_READY_Handler);	
		}
		
		private function this_REWIND_Handler($evt:VideoEvent):void{
			this.removeEventListener(VideoEvent.REWIND, this.this_REWIND_Handler);	
		}
		
		private function this_STATE_CHANGE_Handler($evt:VideoEvent):void{
			this.removeEventListener(VideoEvent.STATE_CHANGE, this.this_STATE_CHANGE_Handler);	
		}
		
		
	}
}