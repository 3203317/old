package net.foreworld.flex.container
{
	import net.foreworld.flex.plugin.IFWBasePlugin;
	import net.foreworld.flex.utils.FWHashMap;
	
	public interface IFWBaseContainer extends IFWBasePlugin
	{
		function get plugins():FWHashMap;
		
		function addPlugin($val:Object):void;
		
		function showPlugin($val:Object):void;
		
		function removePlugin($val:Object):void;
		
	}
}