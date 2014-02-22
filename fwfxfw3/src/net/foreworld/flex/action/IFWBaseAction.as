package net.foreworld.flex.action
{
	import net.foreworld.flex.framework.IFWApplication;
	
	public interface IFWBaseAction
	{
		function get id():String;
		function set id($val:String):void;
		
		function get aname():String;
		function set aname($val:String):void;
		
		function get title():String;
		function set title($val:String):void;
		
		function get desc():String;
		function set desc($val:String):void;
		
		function get version():String;
		function set version($val:String):void;
		
		function get parentApp():IFWApplication;
		function set parentApp($val:IFWApplication):void;
		
		function init($val:XML):void;
		function destory():void;
		
		function start():void;
		function stop():void;	
	}
}