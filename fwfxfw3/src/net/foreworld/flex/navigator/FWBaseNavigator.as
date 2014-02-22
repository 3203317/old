package net.foreworld.flex.navigator
{
	import flash.utils.setTimeout;
	
	import net.foreworld.flex.event.FWEvent;
	import net.foreworld.flex.plugin.FWBasePlugin;

	public class FWBaseNavigator extends FWBasePlugin implements IFWBaseNavigator
	{
		
		public function FWBaseNavigator()
		{
			super();
		}
		
//		protected override function preCallManager():void{
//			if(this.parentApplication.hasEventListener(FWEvent.NAVIGATOR_LOADED)){
//				this.parentApplication.dispatchEvent(new FWEvent(FWEvent.NAVIGATOR_LOADED, false, false, this));
//			}
//			else{
//				setTimeout(preCallManager, 500);
//			}
//		}
	}
}