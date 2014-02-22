package net.foreworld.flex.event
{
	import flash.events.Event;

	public final class FWEvent extends Event
	{
		/**
		 * 调用样式
		 * */
		public static const STYLE_LOAD:String = "styleLoad";
		
		/*******************************************************************************************/
		
		/**
		 * 动作载入(相当于调用动作)
		 * */
		public static const ACTION_LOAD:String = "actionLoad";
		
		/**
		 * 动作载入后
		 * */
		public static const ACTION_LOADED:String = "actionLoaded";
		
		/**
		 * 动作卸载
		 * */
		public static const ACTION_UNLOAD:String = "actionUnLoad";
		
		/**
		 * 动作卸载后
		 * */
		public static const ACTION_UNLOADED:String = "actionUnLoaded";
		
		/*******************************************************************************************/
		
		/**
		 * 插件载入
		 * */
		public static const PLUGIN_LOAD:String = "pluginLoad";	
		
		/**
		 * 插件载入后
		 * */
		public static const PLUGIN_LOADED:String = "pluginLoaded";	
			
		/**
		 * 插件卸载
		 * */
		public static const PLUGIN_UNLOAD:String = "pluginUnLoad";	
			
		/**
		 * 插件卸载后
		 * */
		public static const PLUGIN_UNLOADED:String = "pluginUnLoaded";	
		
		/*******************************************************************************************/
		
		/**
		 * 应用程序异常
		 * */
		public static const APP_ERROR:String = "appError";
		
		/**
		 * 应用程序消息
		 * */
		public static const APP_MSG:String = "appMsg";
		
		/*******************************************************************************************/
		
		/**
		 * 配置文件加载完成
		 * */
		public static const CFG_LOADED:String = "cfgLoaded";
		
		/*******************************************************************************************/
		
		/**
		 * 导航器加载完成
		 * */
		public static const NAVIGATOR_LOADED:String = "navigatorLoaded";
		
		/*******************************************************************************************/
		
		/**
		 * 容器加载完成
		 * */
		public static const CONTAINER_LOADED:String = "containerLoaded";
		
		/*******************************************************************************************/
		
		/**
		 * 进度事件
		 * */
		public static const PROGRESSEVENT:String = "progressEvent";
		
		/*******************************************************************************************/
		
		
		//---------------------------------------------------------------------
		//
		//	Constructor
		//
		//---------------------------------------------------------------------
		
		public function FWEvent($type:String, $bubbles:Boolean=false, $cancelable:Boolean=false, $data:Object=null)
		{
			this._data = $data;
			super($type, $bubbles, $cancelable);
		}
		
		//---------------------------------------------------------------------
		//
		//	Properties
		//
		//---------------------------------------------------------------------
		
		private var _data:Object;
		
		public function get data():Object{
			return this._data;
		}
		
		public function set data($data:Object):void{
			this._data = $data;
		}
		
	}
}