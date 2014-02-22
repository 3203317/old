package net.foreworld.flex.components
{
	import mx.logging.Log;
	
	import net.foreworld.flex.components.FWPlayer.FWPlayerMode;
	import net.foreworld.flex.components.FWPlayer.FWPlayerStatus;
	import net.foreworld.flex.components.FWPlayer.FWScheduling;
	import net.foreworld.flex.components.FWPlayer.IFWPlayer;
	import net.foreworld.flex.utils.FWUtil;

	public class FWPlayer implements IFWComponent, IFWPlayer
	{
		public static const CLASS_ID:String = FWUtil.getInstance().getQualifiedClassName(FWPlayer);
		
		private var _cid:String;
		private var _cname:String;
        private var _desc:String;
        private var _version:String;        
        private var _title:String;     
        
        private var _cfgUrl:String;
		
		public function set cid($val:String):void{
			this._cid = $val;
		}
		public function get cid():String
		{
			return this._cid;
		}
		
		public function set cname($val:String):void{
			this._cname = $val;
		}
		
		public function get cname():String
		{
			return this._cname;
		}
		
		public function set desc($val:String):void{
			this._desc = $val;
		}
		
		public function get desc():String
		{
			return this._desc;
		}
		
		public function set version($val:String):void{
			this._version = $val;
		}
		
		public function get version():String
		{
			return this._version;
		}
		
		public function set title($val:String):void{
			this._title = $val;
		}
		
		public function get title():String
		{
			return this._title;
		}
		
		public function set cfgUrl($val:String):void{
			this._cfgUrl = $val;
		}
		
		public function get cfgUrl():String
		{
			return this._cfgUrl;
		}
		
		/***********************************************************************/
		
		private var _playerStatus:FWPlayerStatus = null;
		
		public function set playerStatus($val:FWPlayerStatus):void
		{
			this._playerStatus = $val;
		}
		
		public function get playerStatus():FWPlayerStatus
		{
			return this._playerStatus;
		}
		
		/***********************************************************************/
		
		private var _playerMode:FWPlayerMode = null;
		
		public function set playerMode($val:FWPlayerMode):void
		{
			this._playerMode = $val;
		}
		
		public function get playerMode():FWPlayerMode
		{
			return this._playerMode;
		}
		
		/***********************************************************************/
		
		private var _playList:FWScheduling = null;
		
		public function set playList($val:FWScheduling):void
		{
			this._playList = $val;
		}
		
		public function get playList():FWScheduling
		{
			return this._playList;
		}
		
		/***********************************************************************/
		
		private var _position:int;
		
		public function set position($val:int):void
		{
			this._position = $val;
		}
		
		public function get position():int
		{
			return this._position;
		}
		
		/***********************************************************************/
		
		private var _bufferTime:int = 5000;
		
		public function set bufferTime($val:int):void
		{
			this._bufferTime = $val;
		}
		
		public function get bufferTime():int
		{
			return this._bufferTime;
		}
		
		/***********************************************************************/
		
		public function FWPlayer()
		{
			this._playerStatus = FWPlayerStatus.CLOSE;
			
			this._playerMode = FWPlayerMode.LIST;
			
			this._position = 0;
		}
		
		public function play():void
		{			
			if(this._playList == null || this._playList.length == 0) return;
			
			switch(this._playerStatus){
				case FWPlayerStatus.CLOSE:{
					this.playStart();
					break;
				}
				case FWPlayerStatus.PAUSE:{
					this.playStart();
					break;
				}
				case FWPlayerStatus.PLAY:{
					break;
				}
			}
		}
		
		public function prev():void
		{
			if(this._playList == null || this._playList.length == 0) return;
			
			switch(this._playerStatus){
				case FWPlayerStatus.CLOSE:{
					break;
				}
				case FWPlayerStatus.PAUSE:{
					this.playStop();
					break;
				}
				case FWPlayerStatus.PLAY:{
					this.playStop();
					break;
				}
			}
			this._playList.prev;
			this.playStart();
		}
		
		public function next():void
		{
			if(this._playList == null || this._playList.length == 0) return;
			
			switch(this._playerStatus){
				case FWPlayerStatus.CLOSE:{
					break;
				}
				case FWPlayerStatus.PAUSE:{
					this.playStop();
					break;
				}
				case FWPlayerStatus.PLAY:{
					this.playStop();
					break;
				}
			}
			this._playList.next;
			this.playStart();
		}
		
		public function close():void
		{
			if(this._playList == null || this._playList.length == 0) return;
			
			switch(this._playerStatus){
				case FWPlayerStatus.CLOSE:{
					break;
				}
				case FWPlayerStatus.PAUSE:{
					this.playStop();
					break;
				}
				case FWPlayerStatus.PLAY:{
					this.playStop();
					break;
				}
			}
		}
		
		public function pause():void
		{
			if(this._playList == null || this._playList.length == 0) return;
			
			switch(this._playerStatus){
				case FWPlayerStatus.CLOSE:{
					break;
				}
				case FWPlayerStatus.PAUSE:{
					break;
				}
				case FWPlayerStatus.PLAY:{
					this.playPause();
					break;
				}
			}
		}
		
		/***********************************************************************/
		
		/**
		 * 播放开始
		 * */
		public function playStart():void{
			Log.getLogger(CLASS_ID).info('playStart');
		}
		
		/**
		 * 播放停止
		 * */
		public function playStop():void{
			Log.getLogger(CLASS_ID).info('playStop');
		}
		
		/**
		 * 播放暂停
		 * */
		public function playPause():void{
			Log.getLogger(CLASS_ID).info('playPause');
		}
		
	}
}