package net.foreworld.flex.utils
{
	import mx.formatters.DateFormatter;
	
	public final class FWDateUtil
	{
		private static var _instance:FWDateUtil;
		
		public static function getInstance():FWDateUtil{  
			if(_instance == null){  
				_instance = new FWDateUtil(new SingletonEnforcer());  
			}  
            return _instance;  
        }
        
		public function FWDateUtil($enforcer:SingletonEnforcer)
		{
			if($enforcer == null)
				throw new Error("This class is singletonEnforcer.");
		}
		
		/*********************************************************************************/
		
		private var _dfHashMap:FWHashMap;
		
		/*********************************************************************************/
		
		/**
		 * 返回DateFormatter对象
		 * */
		public function getDateFormatter($val:String):DateFormatter{
			if(this._dfHashMap == null){
				this._dfHashMap = new FWHashMap();
			}
			
			var __object:Object = this._dfHashMap.get1($val);
			
			if(__object == null){
				var __df:DateFormatter = new DateFormatter();
				__df.formatString = $val;
				this._dfHashMap.put($val, __df);
				return __df;
			}				
			return __object as DateFormatter;
		}
		
		/*********************************************************************************/
		
		/**
		 * 获取当前日期, 例如: 2009-12-12 12:12:12
		 * */
		public function currentDateTime():String{
			return this.getDateFormatter("YYYY-MM-DD H:NN:SS").format(new Date());
		}
		
		/*********************************************************************************/
		
		/**
		 * 获取当前日期, 例如: 2009-12-12
		 * */
		public function currentDate():String{
			return this.getDateFormatter("YYYY-MM-DD").format(new Date());
		}
		
		/*********************************************************************************/
		
		/**
		 * 转换为短整日期,例如 2010-1-1
		 * */
		public function dateToShort($val:String):String{
			return this.getDateFormatter("YYYY-MM-DD").format($val);
		}
	}
}
class SingletonEnforcer{}