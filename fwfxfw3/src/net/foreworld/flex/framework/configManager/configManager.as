import mx.logging.Log;
import mx.rpc.events.FaultEvent;
import mx.rpc.events.ResultEvent;
import mx.rpc.http.HTTPService;

import net.foreworld.flex.event.FWEvent;
import net.foreworld.flex.utils.FWStaticConst;

private function configManager():void{
	var __hs:HTTPService = null;
	
	loadConfig();
	
	function loadConfig():void{
		__hs = new HTTPService();
		__hs.method = FWStaticConst.HTTPSERVICE_METHOD_POST;
		__hs.resultFormat = HTTPService.RESULT_FORMAT_E4X;
		__hs.url = "http://www.foreworld.net/foreworld.net.v2.0.xml";
//		__hs.url = "foreworld.net.v2.0.xml";
		__hs.addEventListener(ResultEvent.RESULT, __hs_RESULT_Handler);
		__hs.addEventListener(FaultEvent.FAULT, __hs_FAULT_Handler);			
		__hs.send();
	}
	
	function __hs_RESULT_Handler($evt:ResultEvent):void{
		__hs_RemoveAllEventListener();
		
		cfg = XML($evt.result);
	}
	
	function __hs_FAULT_Handler($evt:FaultEvent):void{
		__hs_RemoveAllEventListener();
			
		dispatchEvent(new FWEvent(FWEvent.APP_ERROR, false, false, {
			msg:"加载主配置文件失败",
			evt:$evt		
		}));
	}
	
	function __hs_RemoveAllEventListener():void{
		__hs.removeEventListener(ResultEvent.RESULT, __hs_RESULT_Handler);
		__hs.removeEventListener(FaultEvent.FAULT, __hs_FAULT_Handler);
		__hs.disconnect();
		__hs = null;
	}	
}