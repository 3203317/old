
import mx.styles.StyleManager;

import net.foreworld.flex.event.FWEvent;

private function styleManager():void
{
//	var __eventDispatcher:IEventDispatcher;
	
	var __style:String = "";
	
	loadStyle(cfg.extension.application.@style);
	
	this.addEventListener(FWEvent.STYLE_LOAD, this_STYLE_LOAD_Handler);

	/***********************************************************************************/
	
	function this_STYLE_LOAD_Handler($evt:FWEvent):void{
		loadStyle(String($evt.data));
	}
	
	function loadStyle($style:String):void{
		if($style == __style) return;
		
		if(__style != ""){
			StyleManager.unloadStyleDeclarations(__style, false);
		}
		
		StyleManager.loadStyleDeclarations($style, true);
		
		__style = $style;
	}
	
//	/**
//	 * 加载样式表
//	 * */
//	function loadStyle($style:String):void{
//		//如果加载的样式表和当前的样式表一样 则返回 不重复加载样式表
//		if($style == __style) return;
//		
//		//如果当前的样式表不为空 则卸载老的样式
//		if(__style != ""){
//			//参数2为false 说明先不立即更新样式
//			StyleManager.unloadStyleDeclarations(__style, false);
//		}
//		
//		__eventDispatcher = StyleManager.loadStyleDeclarations($style, true);	
//
//		__eventDispatcher.addEventListener(StyleEvent.COMPLETE, __eventDispatcher_COMPLETE_Handler($style), false, 0, true); 
//		
//		__eventDispatcher.addEventListener(StyleEvent.ERROR, __eventDispatcher_ERROR_Handler, false, 0, true);
//	}
//	
//	function __eventDispatcher_COMPLETE_Handler($style:String):Function{
//		return function($evt:StyleEvent):void{
//			$evt.currentTarget.removeEventListener($evt.type, arguments.callee);
//			$evt.currentTarget.removeEventListener(StyleEvent.ERROR, __eventDispatcher_ERROR_Handler);
//			
//			__style = $style;
//		}
//	}
//	
//	function __eventDispatcher_ERROR_Handler($evt:StyleEvent):void{
//		__eventDispatcher.removeEventListener(StyleEvent.ERROR, __eventDispatcher_ERROR_Handler);
//		__eventDispatcher.removeEventListener(StyleEvent.COMPLETE, __eventDispatcher_COMPLETE_Handler);
//		
//		dispatchEvent(new FWEvent(FWEvent.APP_ERROR, false, false, {
//			msg:"加载样式失败",
//			evt:$evt
//		}));
//	}
}

