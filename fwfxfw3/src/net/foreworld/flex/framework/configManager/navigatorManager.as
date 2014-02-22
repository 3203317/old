
import net.foreworld.flex.navigator.IFWBaseNavigator;

private function navigatorManager():void
{
	this.addEventListener(FWEvent.NAVIGATOR_LOADED, this_NAVIGATOR_LOADED_Handler);
			
	function this_NAVIGATOR_LOADED_Handler($evt:FWEvent):void{
		var ___baseNavigator:IFWBaseNavigator = IFWBaseNavigator($evt.data);
		
		___baseNavigator.init(cfg);
	}
}

