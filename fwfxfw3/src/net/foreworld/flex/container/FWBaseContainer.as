package net.foreworld.flex.container
{
	import net.foreworld.flex.event.FWEvent;
	import net.foreworld.flex.plugin.FWBasePlugin;
	import net.foreworld.flex.utils.FWHashMap;
	import net.foreworld.flex.utils.FWStaticConst;
	import net.foreworld.flex.utils.FWUtil;

	public class FWBaseContainer extends FWBasePlugin implements IFWBaseContainer
	{
		private var _plugins:FWHashMap;
		
		public function get plugins():FWHashMap{
			return this._plugins;
		}
		
		/********************************************************************************************/
		
		public function FWBaseContainer()
		{
			super();
			
			this._plugins = new FWHashMap(); 
			
		}
		
		/********************************************************************************************/
		
		private function this_PLUGIN_LOAD_Handler($evt:FWEvent):void{
			this.loadPlugin($evt.data);
		}
		
		private function loadPlugin($val:Object):void{
			//获取hashmap中的已经存在插件
			var __object:Object = this._plugins.get1($val.id);
			
			//如果对象为null，说明不存在，则需要添加
			if(__object == null){
				//设置当前载入插件的状态为载入中
				this._plugins.put($val.id, {status:FWStaticConst.LOADING});
				//执行容器的添加插件方法
				this.addPlugin($val);
			}
			else{//如果插件在该容器中存在，则...
				//得到该容器的加载状态，如果为loading 则是正在加载中，加载中则返回，等待加载完成的事件发送
				if(__object.status == FWStaticConst.COMPLETE){
					this.showPlugin($val);
				}
			}
		}
		
		/********************************************************************************************/
		
		public override function init($val:XML=null):void{
			super.init($val);
			
			this.loadPlugin({
				id:FWUtil.getInstance().getClassName($val.extension.container.item.@url),
				label:$val.extension.container.item.@label,
				url:$val.extension.container.item.@url
			});
			
			this.parentApplication.addEventListener(FWEvent.PLUGIN_LOAD + this.pid, this.this_PLUGIN_LOAD_Handler);
		}
		
		public function addPlugin($val:Object):void{
			
		}
		
		public function showPlugin($val:Object):void{
			
		}
		
		public function removePlugin($val:Object):void{
			
		}
	}
}