package net.foreworld.flex.components
{
	public interface IFWComponent
	{
		function get cid():String;
		function set cid($val:String):void;
		
		function get cname():String;
		function set cname($val:String):void;
		
		function get desc():String;
		function set desc($val:String):void;
		
		function get version():String;	
		function set version($val:String):void;
		
		function get title():String;	
		function set title($val:String):void;
		
		function get cfgUrl():String;
		function set cfgUrl($val:String):void;
	}
}