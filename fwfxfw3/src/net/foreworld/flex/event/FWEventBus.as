package net.foreworld.flex.event
{
	import flash.events.Event;
	import flash.events.EventDispatcher;
	import flash.events.IEventDispatcher;

	public final class FWEventBus extends EventDispatcher
	{
		private static var _instance:FWEventBus;
		
		public static function getInstance():FWEventBus{  
			if(_instance == null){  
				_instance = new FWEventBus(new SingletonEnforcer());  
			}  
            return _instance;  
        }
		
		public function FWEventBus($enforcer:SingletonEnforcer, $target:IEventDispatcher=null)
		{
			if($enforcer == null){
				throw new Error("This class is singletonEnforcer.");
			}
//			super($target);
		}
		
		public function dispatch($type:String):Boolean{
			return this.dispatchEvent(new Event($type));
		}
		
	}
}

class SingletonEnforcer{}