
import flash.events.ContextMenuEvent;
import flash.system.Capabilities;
import flash.ui.ContextMenuItem;

import net.foreworld.flex.event.FWEvent;
import net.foreworld.flex.utils.FWUtil;


private function contextMenuManager():void
{
	createContextMenu(cfg.extension.(@point=="net.foreworld.flex.popupMenus"));

	function createContextMenu($val:XMLList):void{
		var ___xmlList:XMLList = $val.category.(@id=="net.foreworld.flex.popupMenus").separator;
	
		for each(var ___xml:XML in ___xmlList){
			
			var ____xmlList:XMLList = $val.popupMenu.(@popupMenuPath=="net.foreworld.flex.popupMenus/"+ ___xml.@name)
			
			for(var ____i:int=0, ____j:int=____xmlList.length(); ____i<____j; ____i++){
				
				var _____contextMenuItem:ContextMenuItem = new ContextMenuItem(____xmlList[____i].@label, ____i == 0);
				
				_____contextMenuItem.addEventListener(ContextMenuEvent.MENU_ITEM_SELECT, _____contextMenuItem_MENU_ITEM_SELECT_Handler({
					id:FWUtil.getInstance().getClassName(____xmlList[____i].@url),
					label:____xmlList[____i].@label,
					url:____xmlList[____i].@url,
					target:____xmlList[____i].@target
				}));
				
				contextMenu.customItems.push(_____contextMenuItem);
			}
		}
		
		var ___flashVersion:ContextMenuItem = new ContextMenuItem(Capabilities.version, true);
		___flashVersion.enabled = false;
		contextMenu.customItems.push(___flashVersion);
		
		var ___poweredBy:ContextMenuItem = new ContextMenuItem("Powered By : Foreworld.Net", true);
		___poweredBy.enabled = false;				
		contextMenu.customItems.push(___poweredBy);
		
		contextMenu.hideBuiltInItems();
	}
	
	function _____contextMenuItem_MENU_ITEM_SELECT_Handler($object:Object):Function{
		return function($evt:ContextMenuEvent):void{
			dispatchEvent(new FWEvent($object.target.toString() == "" ? FWEvent.ACTION_LOAD : FWEvent.PLUGIN_LOAD + $object.target.toString(), false, false, $object));
		}
	}
}