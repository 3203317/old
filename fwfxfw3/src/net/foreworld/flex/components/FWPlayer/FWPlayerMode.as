package net.foreworld.flex.components.FWPlayer
{
	/**
	 * 播放模式：单曲循环、顺序播放、列表循环、随机播放
	 * */
	public final class FWPlayerMode
	{
		public static const SINGLE:FWPlayerMode = new FWPlayerMode(0);
		public static const SEQUENCE:FWPlayerMode = new FWPlayerMode(1);
		public static const LIST:FWPlayerMode = new FWPlayerMode(2);
		public static const RANDOM:FWPlayerMode = new FWPlayerMode(3);
		
		private static var _limit:Boolean = limit(); 
		
		private var _val:Object;
		
		public function FWPlayerMode($val:Object)
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
					return "SINGLE";
				case 1:
					return "SEQUENCE";
				case 2:
					return "LIST";
				case 3:
					return "RANDOM";
			}
			
			return "Undefined playerPlayStatus";
		}

	}
}