package net.foreworld.flex.components.FWMP3Player
{
	public final class FWMP3PlayerStatus
	{
		public static const PLAY:FWMP3PlayerStatus = new FWMP3PlayerStatus(0);
		public static const PAUSE:FWMP3PlayerStatus = new FWMP3PlayerStatus(1);
		public static const CLOSE:FWMP3PlayerStatus = new FWMP3PlayerStatus(2);
		
		
		private static var _limit:Boolean = limit(); 
		
		private var _val:Object;
		
		public function FWMP3PlayerStatus($val:Object)
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