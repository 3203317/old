package net.foreworld.flex.utils
{
	import com.adobe.net.URI;
	import com.adobe.serialization.json.JSON;
	
	import flash.display.LoaderInfo;
	import flash.display.StageDisplayState;
	import flash.net.LocalConnection;
	import flash.net.URLStream;
	import flash.system.ApplicationDomain;
	import flash.system.System;
	import flash.utils.describeType;
	import flash.utils.getQualifiedClassName;
	
	import mx.core.Application;
	import mx.managers.BrowserManager;
	
	public final class FWUtil
	{
		private static var _instance:FWUtil;
		
		public static function getInstance():FWUtil{  
			if(_instance == null){  
				_instance = new FWUtil(new SingletonEnforcer());  
			}  
            return _instance;  
        }
        
		public function FWUtil($enforcer:SingletonEnforcer)
		{
			if($enforcer == null)
				throw new Error("This class is singletonEnforcer.");
		}
		
		public function GC():void{
			try{
				new LocalConnection().connect('foo');
				new LocalConnection().connect('foo');
				System.gc();
			}
			catch($err:Error){
				//
			}
		}
		
		public function describeTypeToJson($class:Class):String{
			var __x:XML = describeType($class);
			
			var __object:Object = {} /* of Object */;
			
			__object.className = __x.@name.toString();
			
			for each(var __xml:XML in __x.accessor){
				if(__xml.@declaredBy == __x.@name){
					__object[__xml.@name] =  $class[__xml.@name];
				}
			}
			
			var __str:String = JSON.encode(__object);
			
			__object = null;
			__x = null;
			
			return __str;
		}
		
		/**
		 * gb2312转换utf-8
		 * */
		public function gb2312ToUtf8($stream:URLStream):String{
			return $stream.readMultiByte($stream.bytesAvailable, 'gb2312');
		}
		
		//////////////////////////////////////////////////////////////////////////////////
		
		/**
		 * 设置浏览器标题
		 * */
		public function setTitle($val:String):void{
			BrowserManager.getInstance().setTitle($val);
		}
		
		//////////////////////////////////////////////////////////////////////////////////
		
		/**
		 * 获取Class
		 * */
		public function getClass($name:String, $loaderInfo:LoaderInfo=null):Class{
			try{
				if($loaderInfo == null){
					return ApplicationDomain.currentDomain.getDefinition($name) as Class;
				}
				return $loaderInfo.applicationDomain.getDefinition($name) as Class;	
			}
			catch($err:ReferenceError){
				return null;
			}
			return null;		
		}
		
		/**
		 * 全屏
		 * */
		public function fullScreen($app:Application):void{
			$app.stage.displayState = ($app.stage.displayState == StageDisplayState.NORMAL?StageDisplayState.FULL_SCREEN:StageDisplayState.NORMAL);
			
			if($app.stage.displayState == StageDisplayState.FULL_SCREEN){
				
			}
		}
		
		/**
		 * 将 net.foreworld.flex.framework::FWApplication 转换为
		 * net.foreworld.flex.framework.FWApplication
		 * */
		public function getQualifiedClassName($class:Class):String{
			return flash.utils.getQualifiedClassName($class).replace("::", ".");
		}
		
		/**
		 * 获取IP Port Path
		 * */
		public function getURI():String{
			var __uri:URI = new URI(Application.application.url);
			return __uri.authority +" "+ __uri.port +" "+ __uri.path;
		}
		
		/**
		 * 根据swf的url获取类名
		 * */
		public function getClassName($url:String):String{
			return FWStringUtil.getInstance().replaceAll(FWStringUtil.getInstance().replaceAll($url, ".swf", ""), "/", ".");
		}
		
	}
}

class SingletonEnforcer{}