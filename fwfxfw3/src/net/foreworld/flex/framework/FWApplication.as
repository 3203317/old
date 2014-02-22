/**
 * Flex 编码优化
 * http://insideria.com/2009/04/51-actionscript-30-and-flex-op.html
 * 
 * app加载顺序
 * 1457ms>>app0.preinitialize					应用程序application已实例化，但此时还未创建任何相关的孩子组件（child component）
 * 1471ms>>app0.outTextArea.preinitialize
 * 1489ms>>app0.outTextArea.initialize
 * 1495ms>>app0.HelloButton.preinitialize
 * 1496ms>>app0.HelloButton.initialize
 * 1497ms>>app0.GoodByeButton.preinitialize
 * 1498ms>>app0.GoodByeButton.initialize
 * 1498ms>>app0.initialize						此时，创建了相应的孩子组件，但还未对这些子组件进行布局
 * 1611ms>>app0.outTextArea.creationComplete
 * 1611ms>>app0.HelloButton.creationComplete
 * 1611ms>>app0.GoodByeButton.creationComplete
 * 1611ms>>app0.creationComplete				应用程序application完成全部实例化，并完成所有子组件的布局
 * 1619ms>>app0.applicationComplete				上面三处事件的完成，表明application内部启动的整个进程完成，
 * 												接下来便会通知SystemManager派发applicationComplete事件。
 * 												此时，启动程序启动完成并准备运行.
 *  
 * */
package net.foreworld.flex.framework
{
	import flash.events.Event;
	
	import mx.core.Application;
	import mx.events.FlexEvent;
	import mx.logging.Log;
	import mx.logging.LogEventLevel;
	import mx.logging.targets.TraceTarget;
	
	import net.foreworld.flex.event.FWEvent;
	import net.foreworld.flex.event.FWEventBus;

	[Event(name="appError", type="net.foreworld.flex.event.FWEvent")]
	[Event(name="appMsg", type="net.foreworld.flex.event.FWEvent")]
	public class FWApplication extends Application implements IFWApplication
	{
		include "configManager/configManager.as";
		include "configManager/styleManager.as";
		include "configManager/contextMenuManager.as";
		include "configManager/actionManager.as";
		include "configManager/initManager.as";
		
		public static const CLASS_ID:String = "net.foreworld.flex.framework.FWApplication";
		
		private var _eventBus:FWEventBus;
		
		private var _cfg:XML = null;
		
		private var _enableLog:Boolean;
		
		public function FWApplication()
		{
			super();
			
			Log.getLogger(CLASS_ID).info("初始化事件Bus");
			this._eventBus = FWEventBus.getInstance();
			
			this.addEventListener(FlexEvent.INITIALIZE, this.this_INITIALIZE_Handler);
		}
		
		private function this_INITIALIZE_Handler($evt:FlexEvent):void{
			this.removeEventListener(FlexEvent.INITIALIZE, this.this_INITIALIZE_Handler);
		
			Log.getLogger(CLASS_ID).info("初始化配置管理器");
			this.configManager();
		}
		
		public override function hasEventListener($type:String):Boolean{
			return FWEventBus.getInstance().hasEventListener($type);
		}
		
		public override function addEventListener($type:String, $listener:Function, $useCapture:Boolean=false, $priority:int=0, $useWeakReference:Boolean=false):void{
			FWEventBus.getInstance().addEventListener($type, $listener, $useCapture, $priority, $useWeakReference);
		}
		
		public override function removeEventListener($type:String, $listener:Function, $useCapture:Boolean=false):void{
			FWEventBus.getInstance().removeEventListener($type, $listener, $useCapture);
		}
		
		public override function dispatchEvent($evt:Event):Boolean{
			return FWEventBus.getInstance().dispatchEvent($evt);
		}
		
		public function dispatch($type:String):Boolean{
			return FWEventBus.getInstance().dispatch($type);
		}
		
		private function get app():IFWApplication{
			return this;
		}
		
		public function get cfg():XML{
			return this._cfg;
		}
		
		public function set cfg($val:XML):void{
			this._cfg = $val;
		
			Log.getLogger(CLASS_ID).info("主程序版本:"+ cfg.extension.application.@version);
			
			Log.getLogger(CLASS_ID).info("初始化Action管理器");
			this.actionManager();
			
			Log.getLogger(CLASS_ID).info("初始化右键菜单管理器");
			this.contextMenuManager();
			
			Log.getLogger(CLASS_ID).info("初始化样式管理器");
			this.styleManager();
			
			Log.getLogger(CLASS_ID).info("初始化Init管理器");	
			this.initManager();
		}
		
		public function set enableLog($val:Boolean):void{
			this._enableLog = $val;
			
			if(this._enableLog){
				var __traceTarget:TraceTarget = new TraceTarget();
				
				__traceTarget.filters=["net.foreworld.flex.*"];
				
				__traceTarget.level = LogEventLevel.INFO;
				
				__traceTarget.includeCategory = true;
				__traceTarget.includeLevel = true;
				__traceTarget.includeTime = true;
				
				Log.addTarget(__traceTarget);	
				
				Log.getLogger(CLASS_ID).info("初始化日志记录");
			}
		}
	}
}