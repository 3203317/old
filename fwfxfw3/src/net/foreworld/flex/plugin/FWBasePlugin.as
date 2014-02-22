package net.foreworld.flex.plugin
{
	import mx.events.FlexEvent;
	import mx.logging.Log;
	import mx.modules.Module;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	import mx.rpc.http.HTTPService;
	
	import net.foreworld.flex.event.FWEvent;
	import net.foreworld.flex.utils.FWStaticConst;

	public class FWBasePlugin extends Module implements IFWBasePlugin
	{
		private var _cfgUrl:String = null;
        
        private var _pid:String;
        private var _pname:String;
        private var _desc:String;
        private var _version:String;        
        private var _title:String;        
		
		public function get pid():String
		{
			return this._pid;
		}
		
		public function set pid($val:String):void
		{
			this._pid = $val;
		}
		
		public function get pname():String
		{
			return this._pname;
		}
		
		public function set pname($val:String):void
		{
			this._pname = $val;
		}
		
		public function get desc():String
		{
			return this._desc;
		}
		
		public function set desc($val:String):void
		{
			this._desc = $val;
		}
		
		public function get version():String
		{
			return this._version;
		}
		
		public function set version($val:String):void
		{
			this._version = $val;
		}
		
		public function get title():String{
			return this._title;
		}
		
		public function set title($val:String):void{
			this._title = $val;
		}
		
		public function get cfgUrl():String{
			return this._cfgUrl;
		}
		
		public function set cfgUrl($val:String):void{
			this._cfgUrl = $val;
		}
		
		/************************************************************************************/
        
		public function FWBasePlugin()
		{
			super();
			
			this.addEventListener(FlexEvent.CREATION_COMPLETE, this.this_CREATION_COMPLETE_Handler);
		}	
		
		/************************************************************************************/
		
		private function this_CREATION_COMPLETE_Handler($evt:FlexEvent):void{
			this.removeEventListener(FlexEvent.CREATION_COMPLETE, this.this_CREATION_COMPLETE_Handler);
			
			this.loadCfg();
		}
		
		private function loadCfg():void{
			if(this.cfgUrl != null){
				Log.getLogger(this.pid).info("加载配置文件");
				
				var __hs:HTTPService = new HTTPService();
				__hs.method = FWStaticConst.HTTPSERVICE_METHOD_POST;
				__hs.resultFormat = HTTPService.RESULT_FORMAT_E4X;
				__hs.url = this.cfgUrl;
				__hs.addEventListener(ResultEvent.RESULT, this.__hs_RESULT_Handler);
				__hs.addEventListener(FaultEvent.FAULT, this.__hs_FAULT_Handler);
				__hs.send();
			}
			else{
				this.init();
			}
		}
		
		private function __hs_RESULT_Handler($evt:ResultEvent):void{
			this.__hs_RemoveAllEventListener(HTTPService($evt.currentTarget));
			
			this.init(XML($evt.result));
		}
		
		private function __hs_FAULT_Handler($evt:FaultEvent):void{
			this.__hs_RemoveAllEventListener(HTTPService($evt.currentTarget));
			
			this.parentApplication.dispatchEvent(new FWEvent(FWEvent.APP_ERROR, false, false, {
				msg:"加载配置文件失败",
				evt:$evt
			}));
		}
		
		private function __hs_RemoveAllEventListener($hs:HTTPService):void{
			$hs.removeEventListener(ResultEvent.RESULT, this.__hs_RESULT_Handler);
			$hs.removeEventListener(FaultEvent.FAULT, this.__hs_FAULT_Handler);
			$hs.disconnect();
			$hs = null;
		}
		
		/************************************************************************************/
		
		public function init($val:XML=null):void
		{
			Log.getLogger(this.pid).info("init");
		}
		
		public function destory():void
		{
			Log.getLogger(this.pid).info("destory");
		}
		
		public function start():void
		{
			Log.getLogger(this.pid).info("start");
		}
		
		public function stop():void
		{
			Log.getLogger(this.pid).info("stop");
		}
		
	}
}