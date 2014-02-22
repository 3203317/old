package net.foreworld.flex.widget
{
	import mx.events.FlexEvent;
	import mx.modules.Module;
	
	[Event(name="widgetConfigLoaded", type="flash.events.Event")]
	public class FWBaseWidget extends Module implements IFWBaseWidget
	{
        public static const STATE_MINIMIZED:String = "minimized";
        public static const STATE_MAXIMIZED:String = "maximized";
        public static const STATE_CLOSED:String = "closed";
		
		
		public function FWBaseWidget()
		{
			super();
			this.layout = "absolute";
			this.addEventListener(FlexEvent.CREATION_COMPLETE, this.this_CREATION_COMPLETE_Handler);
		}
		
		private function this_CREATION_COMPLETE_Handler($evt:FlexEvent):void{
			this.removeEventListener(FlexEvent.CREATION_COMPLETE, this.this_CREATION_COMPLETE_Handler);
			
		}

		public function setId($val:Number):void
		{
		}
		
		public function setName($val:String):void
		{
		}
		
		public function setTitle($val:String):void
		{
		}
		
		public function setIcon($val:String):void
		{
		}
		
		public function setConfig($val:String):void
		{
		}
		
		public function setState($val:String):void
		{
		}
		
	}
}