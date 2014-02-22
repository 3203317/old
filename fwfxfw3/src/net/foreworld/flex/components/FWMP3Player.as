package net.foreworld.flex.components
{
	import flash.errors.IOError;
	import flash.events.Event;
	import flash.events.IOErrorEvent;
	import flash.media.Sound;
	import flash.media.SoundChannel;
	import flash.media.SoundLoaderContext;
	import flash.net.URLRequest;
	
	import mx.logging.Log;
	
	import net.foreworld.flex.components.FWPlayer.FWPlayerStatus;
	import net.foreworld.flex.components.FWPlayer.FWScheduling;
	import net.foreworld.flex.utils.FWUtil;
	
	[IconFile('FWMP3Player/icon.png')]
	public final class FWMP3Player extends FWPlayer
	{
		public static const CLASS_ID:String = FWUtil.getInstance().getQualifiedClassName(FWMP3Player);
		
		/***********************************************************************/
		
		/**
		 * 初始化播放列表
		 * */
		public function FWMP3Player($playList:FWScheduling = null)
		{
			this.playList = $playList;
			
			super();
			
			this.init();
		}
		
		/***********************************************************************/
		
		private var _urlr:URLRequest;
		
		private var _sound:Sound;
		
		private var _buffer:SoundLoaderContext;
		
		private var _soundChannel:SoundChannel;
		
		public function init():void{
			if(this.playList == null) return;
			
			this._urlr = new URLRequest;
			
			this._buffer = new SoundLoaderContext(this.bufferTime);
		}
		
		/***********************************************************************/
		
		override public function playStart():void{
			this.playerStatus = FWPlayerStatus.PLAY;

			Log.getLogger(CLASS_ID).info("Player Status : PLAY");
			
			Log.getLogger(CLASS_ID).info("MP3 : "+ this.playList.current.name);
			
			this._urlr.url = this.playList.current.url;
			
			this._sound = new Sound;
			
			this._sound.addEventListener(IOErrorEvent.IO_ERROR, this._sound_IO_ERROR_Handler);
			
			this._sound.addEventListener(Event.COMPLETE, this._sound_COMPLETE_Handler);
			
			this._sound.load(this._urlr, this._buffer);
			
			this._soundChannel = this._sound.play(this.position);
		}
		
		/***********************************************************************/
		
		private function _sound_COMPLETE_Handler($evt:Event):void{
			this._sound_RemoveAllEventListener();
			//播放完毕
			this._soundChannel.addEventListener(Event.SOUND_COMPLETE, this._soundChannel_SOUND_COMPLETE_Handler);
		}
		
		private function _sound_IO_ERROR_Handler($evt:IOErrorEvent):void{
			this._sound_RemoveAllEventListener();
			
			this.next();
		}
		
		private function _sound_RemoveAllEventListener():void{
			this._sound.removeEventListener(IOErrorEvent.IO_ERROR, this._sound_IO_ERROR_Handler);
			this._sound.removeEventListener(Event.COMPLETE, this._sound_COMPLETE_Handler);
		}
		
		/***********************************************************************/
		
		private function _soundChannel_SOUND_COMPLETE_Handler($evt:Event):void{
			this._soundChannel_RemoveAllEventListener();
			
			this.next();
		}
		
		private function _soundChannel_RemoveAllEventListener():void{
			this._soundChannel.removeEventListener(Event.SOUND_COMPLETE, this._soundChannel_SOUND_COMPLETE_Handler);
		}
		
		/***********************************************************************/
		
		override public function playStop():void{
			this.playerStatus = FWPlayerStatus.CLOSE;

			Log.getLogger(CLASS_ID).info("Player Status : CLOSE");
			
			this.position = 0;
			
			if(this._soundChannel != null){
				this._soundChannel.stop();					
				
				this._soundChannel_RemoveAllEventListener();
				
				this._soundChannel = null;
			}
			
			if(this._sound != null){
				try{
					this._sound.close();
				}
				catch($err:IOError){
					Log.getLogger(CLASS_ID).info("Couldn't close stream "+ $err.message);
				}
				
				this._sound_RemoveAllEventListener();
				
				this._sound = null;
			}
		}	
		
		/***********************************************************************/
		
		override public function playPause():void{
			this.playerStatus = FWPlayerStatus.PAUSE;

			Log.getLogger(CLASS_ID).info("Player Status : PAUSE");
			
			if(this._soundChannel == null) return;
			
			this.position = this._soundChannel.position;
			
			this._soundChannel.stop();
		}		
	}
}