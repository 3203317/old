package net.foreworld.flex.framework
{
	import flash.display.Loader;
	import flash.display.LoaderInfo;
	import flash.events.Event;
	import flash.events.EventDispatcher;
	import flash.events.IEventDispatcher;
	import flash.events.IOErrorEvent;
	import flash.net.URLRequest;
	import flash.system.ApplicationDomain;
	import flash.system.LoaderContext;
	
	import net.foreworld.flex.framework.events.FWLoaderEvent;
	import net.foreworld.flex.utils.FWUtil;
	
	/**
	 * 加载Action
	 * 1.加载完成后创建实现了IFWPlugin接口的类，添加到FWPluginManager中，第二次调用不需要在次loader。是否存在载入new对象？
	 * 
	 * */
	public final class FWLoader extends EventDispatcher
	{
		//加载到子域
		public static const TARGET_CHILD:String = "child";
		//加载到同域
		public static const TARGET_SAME:String = "same";
		//加载到新域
		public static const TARGET_NEW:String = "new";
		
		private var _loader:Loader = null;
		
		private var _loaderContext:LoaderContext = null;
		
		private var _loaderInfo:LoaderInfo = null;
		
		public function FWLoader($name:String, $url:String, $target:String="same", target:IEventDispatcher=null)
		{
			super(target);
			
			this._loader = new Loader();
			
			this._loaderContext = new LoaderContext();
			
			switch($target){
				case TARGET_CHILD:{
					this._loaderContext.applicationDomain = new ApplicationDomain(ApplicationDomain.currentDomain);
					break;
				}
				case TARGET_SAME:{
					this._loaderContext.applicationDomain = ApplicationDomain.currentDomain;
					break;
				}
				case TARGET_NEW:{
					this._loaderContext.applicationDomain = new ApplicationDomain();
					break;
				}
			}
			
			this._loaderInfo = this._loader.contentLoaderInfo;
			
			this._loaderInfo.addEventListener(Event.COMPLETE, this._loaderInfo_COMPLETE_Handler);
			
			this._loaderInfo.addEventListener(IOErrorEvent.IO_ERROR, this._loaderInfo_IO_ERROR_Handler);
			
			this._loader.name = $name;
			
			this._loader.load(new URLRequest($url), this._loaderContext);
			
			
		}
		
		private function _loaderInfo_COMPLETE_Handler($evt:Event):void{
			this._loaderInfo_RemoveAllEventListener();
			
			var __loaderInfo:LoaderInfo = LoaderInfo($evt.currentTarget);
			
			this.dispatchEvent(new FWLoaderEvent(__loaderInfo.loader.name, FWUtil.getInstance().getClass(__loaderInfo.loader.name)));
			
			__loaderInfo = null;
			
			this.unload();
		}
		
		private function _loaderInfo_IO_ERROR_Handler($evt:IOErrorEvent):void{
			this._loaderInfo_RemoveAllEventListener();
			
			this.unload();
		}
		
		private function _loaderInfo_RemoveAllEventListener():void{
			this._loaderInfo.removeEventListener(Event.COMPLETE, this._loaderInfo_COMPLETE_Handler);			
			this._loaderInfo.removeEventListener(IOErrorEvent.IO_ERROR, this._loaderInfo_IO_ERROR_Handler);
		}
		
		public function unload():void{
			this._loaderContext = null;
			
			this._loaderInfo = null;
			
			this._loader.unload();
			this._loader = null;
		}
	}
}