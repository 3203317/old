package net.foreworld.flex.utils
{
	public final class FWRandomNumberUtil
	{
		private static var _instance:FWRandomNumberUtil;
		
		public static function getInstance():FWRandomNumberUtil{  
			if(_instance == null){  
				_instance = new FWRandomNumberUtil(new SingletonEnforcer());  
			}  
            return _instance;  
        }
		
		public function FWRandomNumberUtil($enforcer:SingletonEnforcer)
		{
			if($enforcer == null)
				throw new Error("This class is singletonEnforcer.");
		}
		
		
		/**
		 * 返回指定范围的随机整数
		 * */
		public function randomRangeInt($min:int, $max:int):int{
			return Math.floor(Math.random() * ($max - $min + 1)) + $min;
		}
		
		/**
		 * 随机数
		 * */
		public function random():Number{
			return Math.random();
		}

	}
}

class SingletonEnforcer{}