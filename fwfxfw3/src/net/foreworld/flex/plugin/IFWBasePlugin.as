package net.foreworld.flex.plugin
{
	public interface IFWBasePlugin
	{
		function get pid():String;
		function set pid($val:String):void;
		
		function get pname():String;
		function set pname($val:String):void;
		
		function get desc():String;
		function set desc($val:String):void;
		
		function get version():String;
		function set version($val:String):void;
		
		function get title():String;
		function set title($val:String):void;
		
		function get cfgUrl():String;
		function set cfgUrl($val:String):void;
		
		function init($val:XML=null):void;
		function destory():void;
		
		function start():void;
		function stop():void;	
	}
}