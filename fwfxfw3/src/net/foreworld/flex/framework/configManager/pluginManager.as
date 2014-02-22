
import net.foreworld.flex.event.FWEvent;
import net.foreworld.flex.plugin.IFWBasePlugin;

private function pluginManager():void{
	this.addEventListener(FWEvent.PLUGIN_LOADED, this_PLUGIN_LOADED_Handler);
	
	function this_PLUGIN_LOADED_Handler($evt:FWEvent):void{
		var ___basePlugin:IFWBasePlugin = IFWBasePlugin($evt.data);
		
		___basePlugin.init(cfg);
	}
}

