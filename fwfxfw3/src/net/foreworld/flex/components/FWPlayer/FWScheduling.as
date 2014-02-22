package net.foreworld.flex.components.FWPlayer
{
	import mx.collections.ArrayCollection;
	
	import net.foreworld.flex.utils.FWRandomNumberUtil;
	
	/**
	 * 调度类 用于调度音、视频的播放算法
	 * 
	 * 单曲循环
	 * 顺序播放
	 * 列表循环
	 * 随机播放
	 * 
	 * */
	public final class FWScheduling extends ArrayCollection
	{
		
		public function FWScheduling($source:Array = null)
		{
			super($source);
			
			this.init();
			
		}
		
		/**
		 * 初始化数据
		 * */
		private function init():void{
			
			this._index = this.length > 0 ? 0 : -1;
			
			this._mode = FWPlayerMode.SEQUENCE;
		}
		
		/***************************************************************/
		
		//当前的播放的索引
		private var _index:int;
		
		public function get index():int{
			return this._index;
		}
		
		public function set index($val:int):void{
			this._index = $val;
		}
		
		/***************************************************************/
		
		/**
		 * 播放模式
		 * */
		private var _mode:FWPlayerMode;
		
		public function get mode():FWPlayerMode{
			return this._mode;
		}
		
		public function set mode($val:FWPlayerMode):void{
			this._mode = $val;
		}
		
		/***************************************************************/
		
		/**
		 * 获取下一个对象
		 * */
		public function get next():Object{
			if(this._index == -1 || this.length == 0) return null;
			
			switch(this.mode){
				case FWPlayerMode.SEQUENCE:{
					if(++this._index == this.length){
						this._index = 0;
					}			
					break;
				}
				case FWPlayerMode.LIST:{
					if(++this._index == this.length){
						this._index = 0;
					}	
					break;
				}
				case FWPlayerMode.RANDOM:{
					this._index = FWRandomNumberUtil.getInstance().randomRangeInt(0, this.length - 1);
					break;
				}
			}		
			
			return this.getItemAt(this._index);
		}
		
		/**
		 * 获取当前对象
		 * */
		public function get current():Object{
			return (this._index == -1 || this.length == 0) ? null : this.getItemAt(this._index);
		}
		
		/**
		 * 获取上一个对象
		 * */
		public function get prev():Object{
			if(this._index == -1 || this.length == 0) return null;
			
			switch(this.mode){
				case FWPlayerMode.SEQUENCE:{
					if(--this._index == -1){
						this._index = this.length - 1;
					}
					break;
				}
				case FWPlayerMode.LIST:{
					if(--this._index == -1){
						this._index = this.length - 1;
					}
					break;
				}
				case FWPlayerMode.RANDOM:{
					this._index = FWRandomNumberUtil.getInstance().randomRangeInt(0, this.length - 1);
					break;
				}
			}		
			
			return this.getItemAt(this._index);
		}
		
		/***************************************************************/
		
		/**
		 * 移除全部对象
		 * */
		override public function removeAll():void{
			this.removeAll();
			
			this._index = -1;
		}
		
		/***************************************************************/
		
		/**
		 * 删除一项对象
		 * 
		 * 1、删除完对象后，如果项总数为0，说明播放列表为空，则设置index为-1，即不能播放任何文件。
		 * 2、如果删除的项为当前播放的项，并且当前播放的项是最后一项，那么设置当前项为第一项，即设置到播放列表头部
		 * 
		 * */
		override public function removeItemAt($index:int):Object{
			var __o:Object = this.removeItemAt($index);
			
			if(this.length == 0){
				this._index = -1;
			}
			else if(this._index == $index && this._index == this.length){
				this._index = 0;
			}
			
			return __o;
		}
		
		/***************************************************************/
		
		/**
		 * 添加一项对象
		 * */
		override public function addItem($item:Object):void{
			super.addItem($item);
		}
		
		/***************************************************************/
		
		/**
		 * 最后一个
		 * */
		public function isLast():Boolean{
			if(this._index == -1) return false;
			
			return this._index + 1 == this.length;
		}
		
		/**
		 * 第一个
		 * */
		public function isFirst():Boolean{
			if(this._index == -1) return false;
			
			return this._index - 1 == -1;
		}
		
	}
}