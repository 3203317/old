package net.foreworld.flex.components.FWPlayer
{
	/**
	 * 播放状态，播放、暂停、停止
	 * */
	public final class FWPlayerStatus
	{
		public static const PLAY:FWPlayerStatus = new FWPlayerStatus(0);
		public static const PAUSE:FWPlayerStatus = new FWPlayerStatus(1);
		public static const CLOSE:FWPlayerStatus = new FWPlayerStatus(2);
		
		private static var _limit:Boolean = limit(); 
		
		private var _val:Object;
		
		public function FWPlayerStatus($val:Object)
		{
			if(_limit){
				throw new Error("Cannot init Enum outside!");
				return;
			}
			this._val = $val;
		}
		
		private static function limit():Boolean{
			return true;
		}
		
		public function toString():String{
			switch(int(this._val)){
				case 0:
					return "PLAY";
				case 1:
					return "PAUSE";
				case 2:
					return "CLOSE";
			}
			
			return "Undefined playerPlayStatus";
		}

	}
}