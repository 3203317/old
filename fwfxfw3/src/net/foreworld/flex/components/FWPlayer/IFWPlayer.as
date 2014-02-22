package net.foreworld.flex.components.FWPlayer
{
	
	public interface IFWPlayer
	{
		function play():void;
		
		function prev():void;
		
		function next():void;
		
		function close():void;
		
		function pause():void;
		
		/**
		 * 播放器状态
		 * */
		function set playerStatus($val:FWPlayerStatus):void;
		function get playerStatus():FWPlayerStatus;
		
		/**
		 * 播放器模式
		 * */
		function set playerMode($val:FWPlayerMode):void;
		function get playerMode():FWPlayerMode;
		
		/**
		 * 播放列表
		 * */
		function set playList($val:FWScheduling):void;
		function get playList():FWScheduling;
		
		/**
		 * 播放位置 单位：毫秒
		 * */
		function set position($val:int):void;
		function get position():int;
		
		/**
		 * 播放位置 单位：毫秒
		 * */
		function set bufferTime($val:int):void;
		function get bufferTime():int;
	}
}