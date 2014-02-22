
import flash.display.Loader;
import flash.display.LoaderInfo;
import flash.events.Event;
import flash.events.IOErrorEvent;
import flash.net.URLRequest;
import flash.system.ApplicationDomain;
import flash.system.LoaderContext;

import net.foreworld.flex.action.IFWBaseAction;
import net.foreworld.flex.event.FWEvent;
import net.foreworld.flex.utils.FWHashMap;
import net.foreworld.flex.utils.FWStaticConst;
import net.foreworld.flex.utils.FWUtil;

private var _actions:FWHashMap;

private function actionManager():void
{
	this._actions = new FWHashMap();
	
	this.addEventListener(FWEvent.ACTION_LOAD, this_ACTION_LOAD_Handler);
	
	this.addEventListener(FWEvent.ACTION_UNLOAD, this_ACTION_UNLOAD_Handler);
	
	function this_ACTION_LOAD_Handler($evt:FWEvent):void{
		var ___url:String = $evt.data.url;
		var ___id:String = $evt.data.id;
		
		var ___object:Object = _actions.get1(___id);
		
		//判断哈希表中是否存在该对象，如果存在，则取现成的对象
		if(___object != null){
			//如果当前的状态为载入中，则返回
			if(___object.status == FWStaticConst.LOADING){
				return;
			}
			
			var ___action:IFWBaseAction = IFWBaseAction(___object.object);
			
			___action.start();
			
			return;
		}
		
		//设置当前载入action的状态为载入中
		_actions.put(___id, {status:FWStaticConst.LOADING});		
		
		//开始加载swf
		var ___loader:Loader = new Loader();
		
		var ___loaderContext:LoaderContext = new LoaderContext();
		//加载到同域
		___loaderContext.applicationDomain = new ApplicationDomain(ApplicationDomain.currentDomain);
		
		var ___loaderInfo:LoaderInfo = ___loader.contentLoaderInfo;
		
		___loaderInfo.addEventListener(Event.COMPLETE, ___loaderInfo_COMPLETE_Handler(___id, ___loader, ___loaderContext, ___loaderInfo));
		
		___loaderInfo.addEventListener(IOErrorEvent.IO_ERROR, ___loaderInfo_IO_ERROR_Handler(___id, ___loader, ___loaderContext, ___loaderInfo));
		
		___loader.load(new URLRequest(___url), ___loaderContext);
	}
	
	function ___loaderInfo_COMPLETE_Handler($id:String, $loader:Loader, $loaderContext:LoaderContext, $loaderInfo:LoaderInfo):Function{
		return function($evt:Event):void{
			$evt.currentTarget.removeEventListener($evt.type, arguments.callee);
			$evt.currentTarget.removeEventListener(IOErrorEvent.IO_ERROR, ___loaderInfo_IO_ERROR_Handler);
			
			var ___class:Class = FWUtil.getInstance().getClass($id, $loaderInfo);
			
			var ___baseAction:IFWBaseAction = new ___class;
			
			___baseAction.parentApp = app;
			
			___baseAction.init(cfg);
			
			_actions.remove($id);
			
			_actions.put($id, {status:FWStaticConst.COMPLETE, object:___baseAction});
			
			unLoader($loader, $loaderContext, $loaderInfo);			
		}
	}
	
	function ___loaderInfo_IO_ERROR_Handler($id:String, $loader:Loader, $loaderContext:LoaderContext, $loaderInfo:LoaderInfo):Function{
		return function($evt:Event):void{
			$evt.currentTarget.removeEventListener($evt.type, arguments.callee);
			$evt.currentTarget.removeEventListener(Event.COMPLETE, ___loaderInfo_COMPLETE_Handler);
			
			_actions.remove($id);
			
			unLoader($loader, $loaderContext, $loaderInfo);
			
			dispatchEvent(new FWEvent(FWEvent.APP_ERROR, false, false, {
				msg:"Action加载异常",
				evt:$evt
			}));		
		}		
	}
	
	function unLoader($loader:Loader, $loaderContext:LoaderContext, $loaderInfo:LoaderInfo):void{
		$loaderContext = null;
		$loaderInfo = null;
		$loader.unload();
		$loader = null;
	}
	
	function this_ACTION_UNLOAD_Handler($evt:FWEvent):void{
		var ___baseAction:IFWBaseAction = IFWBaseAction($evt.data);
		
		var ___id:String = ___baseAction.id;
		
		___baseAction = null;
		
		_actions.remove(___id);
	}
}

