package net.foreworld.flex.utils
{
	import mx.collections.ArrayCollection;
	
	public class FWHashMap
	{
		private var _objects:Object;
		
		private var _length:int;
		
		public function FWHashMap()
		{
			this._objects = {};
			
			this._length = 0;
		}
		
		///////////////////////////////////////////////////////////////////////
		
		
		/**
		 * 从此映射中移除所有映射关系
		 * */
		public function clear():void{
			
		}
		
		/**
		 * 如果此映射包含对于指定的键的映射关系，则返回 true
		 * */
		public function containsKey($key:String):Boolean{
			return this.get1($key) != null;
		}
		
		/**
		 * 如果此映射将一个或多个键映射到指定值，则返回 true
		 * */
		public function containsValue($value:Object):Boolean{
			return false;	
		}
		
		/**
		 * 返回指定键在此标识哈希映射中所映射的值，如果对于此键来说，映射不包含任何映射关系，则返回 null
		 * */
		public function get1($key:String):Object{
			return this._objects[$key];
		}
		
		/**
		 * 如果此映射不包含键-值映射关系，则返回 true
		 * */
		public function isEmpty():Boolean{
			return this._length == 0;
		}
		
		/**
		 * 在此映射中关联指定值与指定键
		 * */
		public function put($key:String, $value:Object):void{
			this._objects[$key] = $value;
			this._length++;
		}
		
		/**
		 * 如果此映射中存在该键的映射关系，则将其删除。
		 * */
		public function remove($key:String):void{
			delete this._objects[$key];
			this._length--;
		}
		
		/**
		 * 返回此映射中的键-值映射关系数
		 * */
		public function size():int{
			return this._length;
		}
		
		/**
		 * 返回此映射所包含的值的 ArrayCollection 视图
		 * */
		public function values():ArrayCollection{
			return null;
		}

	}
}