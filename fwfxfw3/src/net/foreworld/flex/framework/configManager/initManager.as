
import net.foreworld.flex.event.FWEvent;
import net.foreworld.flex.utils.FWUtil;

private function initManager():void{
	for each(var ___xml:XML in this.cfg.extension.application.init){
		this.dispatchEvent(new FWEvent(___xml.@target.toString() == "" ? FWEvent.ACTION_LOAD : FWEvent.PLUGIN_LOAD, false, false, {
			id:FWUtil.getInstance().getClassName(___xml.@url),
			url:___xml.@url
		}));
	}
}