package net.foreworld.flex.utils
{
	import com.hurlant.crypto.Crypto;
	import com.hurlant.crypto.hash.IHash;
	import com.hurlant.util.Hex;
	
	import flash.utils.ByteArray;
	
	public final class FWEncryptUtil
	{
		private static var _instance:FWEncryptUtil;
		
		public static function getInstance():FWEncryptUtil{  
			if(_instance == null){  
				_instance = new FWEncryptUtil(new SingletonEnforcer());  
			}  
            return _instance;  
        }
		
		public function FWEncryptUtil($enforcer:SingletonEnforcer)
		{
			if($enforcer == null)
				throw new Error("This class is singletonEnforcer.");
		}
		
		/**  
         * md5
         */   
        public function md5($val:String):String{  
			var __ih:IHash = Crypto.getHash('md5');
				
			var __ba:ByteArray = Hex.toArray(Hex.fromString($val))
				
			__ba = __ih.hash(__ba);
				
			return Hex.fromArray(__ba);
        }

	}
}

class SingletonEnforcer{}