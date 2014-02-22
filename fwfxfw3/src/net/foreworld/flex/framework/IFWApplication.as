package net.foreworld.flex.framework
{
	import flash.events.Event;
	
	public interface IFWApplication
	{
		function hasEventListener($type:String):Boolean;
		
		function addEventListener($type:String, $listener:Function, $useCapture:Boolean=false, $priority:int=0, $useWeakReference:Boolean=false):void;
		
		function removeEventListener($type:String, $listener:Function, $useCapture:Boolean=false):void;
		
		function dispatchEvent($evt:Event):Boolean;
		
		function dispatch($type:String):Boolean;
		
		function get cfg():XML;
	}
}