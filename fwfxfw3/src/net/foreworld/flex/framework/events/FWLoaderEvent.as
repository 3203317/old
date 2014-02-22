package net.foreworld.flex.framework.events
{
	import flash.events.Event;

	public final class FWLoaderEvent extends Event
	{
		public static const COMPLETE:String = "complete";
		
		public function FWLoaderEvent($name:String, $fwClass:Class)
		{
			super(COMPLETE, true, false);
			
			this.name = $name;
			this.fwClass = $fwClass;
		}
		
		public var name:String;
		
		public var fwClass:Class;
	}
}