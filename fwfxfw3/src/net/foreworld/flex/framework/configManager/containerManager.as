
import net.foreworld.flex.container.IFWBaseContainer;
import net.foreworld.flex.event.FWEvent;
import net.foreworld.flex.utils.FWHashMap;
import net.foreworld.flex.utils.FWStaticConst;

private var _containers:FWHashMap;

private function containerManager():void
{
	this._containers = new FWHashMap();
	
	this.addEventListener(FWEvent.CONTAINER_LOADED, this_CONTAINER_LOADED_Handler);
	
	this.addEventListener(FWEvent.PLUGIN_LOAD, this_PLUGIN_LOAD_Handler);
	
	function this_CONTAINER_LOADED_Handler($evt:FWEvent):void{
		var ___baseContainer:IFWBaseContainer = IFWBaseContainer($evt.data);
		
		_containers.put(___baseContainer.pid, ___baseContainer);
		
		___baseContainer.init(cfg);
	}
	
	function this_PLUGIN_LOAD_Handler($evt:FWEvent):void{
		var ___data:Object = $evt.data;
		
		//根据容器ID net.foreworld.flex.container 获取容器对象
		var ___object:Object = _containers.get1(___data.target);
		
		//如果该容器不存在 则返回
		if(___object == null) return;
		
		//得到该容器的指针
		var ___baseContainer:IFWBaseContainer = IFWBaseContainer(___object);
		
		//得到该容器的插件哈希表
		var ___plugins:FWHashMap = ___baseContainer.plugins;
		
		//得到该容器中具体的插件对象
		___object = ___plugins.get1(___data.id);
		
		//如果插件在该容器中不存在，则添加到该容器中
		if(___object == null){
			//设置当前载入插件的状态为载入中
			___plugins.put(___data.id, {status:FWStaticConst.LOADING});
			//执行容器的添加插件方法
			___baseContainer.addPlugin(___data);
		}
		else{//如果插件在该容器中存在，则...
			//得到该容器的加载状态，如果为loading 则是正在加载中，加载中则返回，等待加载完成的事件发送
			if(___object.status == FWStaticConst.COMPLETE){
				___baseContainer.showPlugin(___data);
			}
		}
	}
}

