unit tce132;
{$DEFINE CTI_DEBUG}
interface
uses  Windows ,GlobalConstants;
{//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//initiate the DT60 PCM Voice board}
function DJSys_EnableCard (const configFile:PCHAR; const promptVoiceIdxFile:PCHAR) : integer; stdcall; far external 'tce1_32.dll';
procedure DJSys_DisableCard; stdcall; far external 'tce1_32.dll';
procedure DJSys_PushPlay;  stdcall; far external 'tce1_32.dll';
function DJSys_EnableDtmfSend():boolean;stdcall; far external 'tce1_32.dll';
///******************************************************************************\
// *
// *	Functions for Trunk channel
// *
//\******************************************************************************/
function  DJTrk_GetTotalTrunkNum : integer; stdcall; far external 'tce1_32.dll';
function  DJTrk_GetPcmTrunkNum :integer; stdcall; far external 'tce1_32.dll';
function  DJTrk_GetWaitTime(trunkID:integer ): WORD; stdcall; far external 'tce1_32.dll';
function  DJTrk_GetConnectVoiceTime(trunkID : integer ): WORD;  stdcall; far external 'tce1_32.dll';
function  DJTrk_GetConnectTime	(trunkID : integer) : WORD;  stdcall; far external 'tce1_32.dll';
function  DJTrk_GetDtmfTime (trunkID : integer) : WORD;  stdcall; far external 'tce1_32.dll';
function  DJTrk_GetTrunkConnectTime(trunkID : integer) : LongInt;  stdcall; far external 'tce1_32.dll';
function  DJTrk_GetPcmID(trunkID : integer ): integer;  stdcall; far external 'tce1_32.dll';
function  DJTrk_GetConnectTrunkID(trunkID : integer): integer;  stdcall; far external 'tce1_32.dll';
function  DJTrk_GetConnectUserID(trunkID : integer) : integer;  stdcall; far external 'tce1_32.dll';
function  DJTrk_GetListenUserID(trunkID : integer) : integer;  stdcall; far external 'tce1_32.dll';  
function  DJTrk_GetListenTrunkID(trunkID : integer): integer;  stdcall; far external 'tce1_32.dll';
function  DJTrk_GetTrunkPlayID(trunkID : integer): integer;  stdcall; far external 'tce1_32.dll';  
function  DJTrk_GetTrunkRecordID(trunkID : integer): integer;  stdcall; far external 'tce1_32.dll';  
function  DJTrk_CheckReady(trunkID : integer) : boolean;   stdcall;  far external 'tce1_32.dll';  
function  DJTrk_CheckConnect(trunkID : integer) : boolean;  stdcall; far external 'tce1_32.dll';  
function  DJTrk_CheckWait(trunkID : integer) : boolean;  stdcall; far external 'tce1_32.dll';  
function  DJTrk_CheckForwardHangUp(trunkID : integer) : boolean;  stdcall; far external 'tce1_32.dll';  
function  DJTrk_CheckTrunkIn(trunkID : integer) : boolean;  stdcall; far external 'tce1_32.dll';  
function  DJTrk_CheckTrunkFree(trunkID : integer) : boolean;  stdcall; far external 'tce1_32.dll';  
function  DJTrk_CheckTrunkEnable	(trunkID : integer) : boolean;  stdcall; far external 'tce1_32.dll';
function  DJTrk_CheckPlayPromptStrEnd	(trunkID : integer) : boolean;  stdcall; far external 'tce1_32.dll';
function  DJUser_CheckPlayPromptStrEnd	(userId : integer) : boolean;  stdcall; far external 'tce1_32.dll';
function  DJTrk_BackwardHangUp(trunkID : integer) : boolean;  stdcall; far external 'tce1_32.dll';
function  DJTrk_DisableTrunk(trunkID : integer) : boolean;  stdcall; far external 'tce1_32.dll';  
function  DJTrk_EnableTrunk(trunkID : integer) : boolean;  stdcall; far external 'tce1_32.dll';  
function  DJTrk_GetFreeTrunkIDForDial (pcmID : integer) : integer;  stdcall; far external 'tce1_32.dll';  
function  DJTrk_StartDial(trunkID: integer;
			  const szPhoneNum : pchar;
			  const szCallerStr) : boolean;
                stdcall; far external 'tce1_32.dll';  
procedure DJTrk_StartDial_SetParam (foreKD : BYTE; foreKA : BYTE ); stdcall; far external 'tce1_32.dll';
function  DJTrk_AppendTelNum(trunkID : integer; phoneCode : char ) : boolean;  stdcall; far external 'tce1_32.dll';  //ADD
function  DJTrk_GetDialStatus(trunkID : integer) : DialStatus;  stdcall; far external 'tce1_32.dll';  
function  trunkCheckDialIsFail(trunkID : integer) : boolean;  stdcall; far external 'tce1_32.dll';  
function  DJTrk_CheckApplyDtmf(trunkID : integer) : boolean;  stdcall; far external 'tce1_32.dll';   //these is a dtmf resource link to this trunk
function  DJTrk_ApplyDtmf(trunkID : integer) : boolean;  stdcall; far external 'tce1_32.dll';  
function  DJTrk_FreeDtmf(trunkID : integer) : boolean;  stdcall; far external 'tce1_32.dll';  
function  DJTrk_GetMfcCode(trunkID : integer) : pchar;  stdcall; far external 'tce1_32.dll';  
function  DJTrk_GetHostCode(trunkID : integer) : pchar;  stdcall; far external 'tce1_32.dll';
function  DJTrk_GetDtmfCode(trunkID : integer) : pchar;  stdcall; far external 'tce1_32.dll';
function  DJTrk_GetDtmfCodeNew(trunkID : integer) : pchar;  stdcall; far external 'tce1_32.dll';
function  DJTrk_GetReciveMfcNum(trunkID : integer) : integer;  stdcall; far external 'tce1_32.dll';
function  DJTrk_GetReciveCallerNum(trunkID : integer) : integer;  stdcall; far external 'tce1_32.dll';  
function  DJTrk_GetReciveDtmfNum(trunkID : integer) : integer;  stdcall; far external 'tce1_32.dll';
function  DJTrk_GetFirstDtmfCode(trunkID : integer) : char;  stdcall; far external 'tce1_32.dll';  
function  DJTrk_GetLastDtmfCode	(trunkID : integer) : char;  stdcall; far external 'tce1_32.dll';
function  DJTrk_InitDtmfBuf(trunkID : integer) : boolean;  stdcall; far external 'tce1_32.dll';
function  DJTrk_SendDtmfStr(trunkid:integer;sendstr:pchar):integer;stdcall; far external 'tce1_32.dll';
function  DJTrk_CheckDtmfSendEnd(trunkid:integer):boolean;stdcall; far external 'tce1_32.dll';
function  DJTrk_SetTrunkType(trunkID : integer; Tktype : TrunkType) : boolean;  stdcall; far external 'tce1_32.dll';
function  DJTrk_GetTrunkStatus(trunkID : integer) : integer;  stdcall; far external 'tce1_32.dll';
function  DJTrk_PlayPromptStr(trunkID : integer; pcPrompStr: String) : integer;  stdcall; far external 'tce1_32.dll';
function  DJUser_PlayPromptStr(trunkID : integer; pcPrompStr: String) : integer;  stdcall; far external 'tce1_32.dll';
function  DJTrk_GetTrunkKD(trunkID : integer) : BYTE;  stdcall; far external 'tce1_32.dll';  
function  DJTrk_GetTrunkKB(trunkID : integer) : BYTE;  stdcall; far external 'tce1_32.dll';  
function  DJTrk_GetTrunkForwardKA(trunkID : integer) : BYTE;  stdcall; far external 'tce1_32.dll';  
function  DJTrk_GetTrunkBackwardA(trunkID : integer) : BYTE;  stdcall; far external 'tce1_32.dll';
function  DJTrk_GetTrunkForwardMFN(trunkID : integer) : BYTE;  stdcall; far external 'tce1_32.dll';  
function  DJTrk_GetTrunkForwardDL (trunkID : integer) : byte;  stdcall; far external 'tce1_32.dll';
function  DJTrk_GetTrunkBackwardDL(trunkID : integer) : byte;  stdcall; far external 'tce1_32.dll';
function  trunkPutBackDL(trunkID : integer; backDL : BYTE) : boolean;  stdcall; far external 'tce1_32.dll';  
function  DJTrk_WaitBackwardA3(trunkID : integer) : boolean;  stdcall; far external 'tce1_32.dll';
function  DJTrk_SetTrunkKB(trunkID : integer; backKB : BYTE) : boolean;  stdcall; far external 'tce1_32.dll';
procedure  DJTrk_SetTrunkKB_SetParam (backA : BYTE);  stdcall; far external 'tce1_32.dll';  
///******************************************************************************\
// *
// *	Functions for User channel
// *
//\******************************************************************************/
function  DJUser_GetTotalUserNum : integer;  stdcall; far external 'tce1_32.dll';
procedure DJUser_SetPowerON (userID : integer);  stdcall; far external 'tce1_32.dll';   	//default
procedure DJUser_SetPowerOFF(userID : integer);  stdcall; far external 'tce1_32.dll';  
function  DJUser_CheckPowerStatus(userID : integer) : boolean;  stdcall; far external 'tce1_32.dll';   	//if Power is ON then return TRUE
procedure DJUser_SetUserHalfPower(userID : integer);  stdcall; far external 'tce1_32.dll';  
procedure DJUser_SetUserFullPower(userID : integer);  stdcall; far external 'tce1_32.dll';  
function  DJUser_RingDetect(userID : integer) : boolean;  stdcall; far external 'tce1_32.dll';  
function  DJUser_StartRing(userID : integer) : boolean;  stdcall; far external 'tce1_32.dll';
function  DJUser_StopRing(userID : integer) : boolean;  stdcall; far external 'tce1_32.dll';  
function  DJUser_EnableDialSound(userID : integer) : boolean;  stdcall; far external 'tce1_32.dll';   //default
function  DJUser_DisableDialSound(userID : integer) : boolean;  stdcall; far external 'tce1_32.dll';  
function  DJUser_CheckHookOFF(userID : integer) : boolean;  stdcall; far external 'tce1_32.dll';  
function  DJUser_GetDialNum(userID : integer) : integer;  stdcall; far external 'tce1_32.dll';
function  DJUser_GetDialNumNew(userID : integer) : integer;  stdcall; far external 'tce1_32.dll';
function  DJUser_GetDialCode(userID : integer) : pchar;  stdcall; far external 'tce1_32.dll';  
function  DJUser_GetDialCodeNew(userID : integer) : pchar;  stdcall; far external 'tce1_32.dll';  
function  DJUser_GetFirstDialCode(userID : integer) : char;  stdcall; far external 'tce1_32.dll';
function  DJUser_GetLastDialCode(userID : integer) : char;  stdcall; far external 'tce1_32.dll';  
function  DJUser_InitDialBuf(userID : integer) : boolean;  stdcall; far external 'tce1_32.dll';
function  DJUser_GetConnectTrunkID(userID : integer) : integer;  stdcall; far external 'tce1_32.dll';  
function  DJUser_GetConnectUserID(userID : integer) : integer;  stdcall; far external 'tce1_32.dll';  
function  DJUser_GetListenUserID(userID : integer) : integer;  stdcall; far external 'tce1_32.dll';  
function  DJUser_GetListenTrunkID(userID : integer) : integer;  stdcall; far external 'tce1_32.dll';  
function  DJUser_GetPlayChannel(userID : integer) : integer;  stdcall; far external 'tce1_32.dll';
function  DJUser_GetRecordChannel(userID : integer) : integer;  stdcall; far external 'tce1_32.dll';  
function  DJUser_SetSendSoundValue(userID : integer; value : integer) : boolean;  stdcall; far external 'tce1_32.dll';  
function  DJUser_SetReciveSoundValue(userID : integer;value : integer) : boolean;  stdcall; far external 'tce1_32.dll';  
function  DJUser_GetSendSoundValue(userID : integer) : integer;  stdcall; far external 'tce1_32.dll';  
function  DJUser_GetReciveSoundValue(userID : integer) : integer;  stdcall; far external 'tce1_32.dll';  
function  DJUser_GetPreStopDialToNowTime(userID : integer) : WORD;  stdcall; far external 'tce1_32.dll';  
function  DJUser_SearchFreeTrunkAndDial(userID : integer;
				       pcmID : integer;
				       szPhoneNum : pchar;
                                       szCallerStr : pchar ) : PstnErr;
                         stdcall; far external 'tce1_32.dll';  
function  DJUser_DialByTrunk (userID : integer;
			  trunkID : integer;
			  szPhoneNum : pchar;
                          szCallerStr : pchar ) : boolean;
                         stdcall; far external 'tce1_32.dll';  
function  DJUser_GetDialTrunkID(userID : integer) : integer;  stdcall; far external 'tce1_32.dll';  
function  DJUser_GetDialStatus(userID : integer) : DialStatus;  stdcall; far external 'tce1_32.dll';
//added by N.C.J 1999.6.14
function  DJUser_PlayFileNew(userID:integer;FileName:PChar;Position:DWORD;Length:DWORD):integer; stdcall; far external 'tce1_32.dll';
procedure DJUser_StopPlayFile(userID:integer);  stdcall; far external 'tce1_32.dll';
function DJUser_RecordFileNew(userID:integer;FileName:PChar;Position:DWORD;Length:DWORD):integer;stdcall; far external 'tce1_32.dll';
procedure DJUser_StopRecordFile(userID:integer); stdcall; far external 'tce1_32.dll';
//end of N.C.J
{/******************************************************************************\
 *
 *	Functions for Voice channel
 *
\******************************************************************************/}
function  DJVoc_GetTotalVoiceChannel : integer;  stdcall; far external 'tce1_32.dll';  
function  DJVoc_VoiceStart(voiceChannelID : integer;
			  voiceResHandle : integer;
                          voiceResSize : LongInt;
                          voiceResOffset : LongInt;
                          voiceResType : VoiceResourcesType;
                          voiceOpType : VoiceOperatorType) : integer;
           stdcall; far external 'tce1_32.dll';
function  DJVoc_PlayNextVoice(voiceChannelID : integer;
			     voiceResHandle : integer;
			     voiceResSize : LongInt;
                             voiceResOffset : LongInt) : integer;
            stdcall; far external 'tce1_32.dll';  
function  DJVoc_FromHeadRePlay(voiceID : integer) : integer;  stdcall; far external 'tce1_32.dll';  
function  DJVoc_PlayPromptFile(voiceID : integer;voiceName : pchar) : boolean;  stdcall; far external 'tce1_32.dll';  
function  DJVoc_LoopPlayPromptFile( voiceID : integer;voiceName : pchar) : boolean;  stdcall; far external 'tce1_32.dll';  
function  DJVoc_PlayPromptID(voiceID : integer;voiceResID : integer) : boolean;  stdcall; far external 'tce1_32.dll';  
function  DJVoc_LoopPlayPromptID(voiceID : integer;voiceResID : integer) : boolean;  stdcall; far external 'tce1_32.dll';  
function  DJVoc_PauseChannelVoiceOp(voiceID : integer) : boolean;  stdcall; far external 'tce1_32.dll';  
function  DJVoc_ContinueChannelVoiceOp(voiceID : integer) : boolean;  stdcall; far external 'tce1_32.dll';  
function  DJVoc_CheckChannelVoicePause(voiceID : integer) : boolean;  stdcall; far external 'tce1_32.dll';  
function  DJVoc_VoiceStop(voiceChannelID : integer) : boolean;  stdcall; far external 'tce1_32.dll';
function  DJVoc_CheckVoiceEnd(voiceChannelID : integer) : boolean;  stdcall; far external 'tce1_32.dll';  
function  DJVoc_CheckVoiceChannelOp(voiceChannelID : integer) : boolean;  stdcall; far external 'tce1_32.dll';  
function  DJVoc_GetVoiceDataLength(voiceChannelID : integer) : longint;  stdcall; far external 'tce1_32.dll';  
function  DJVoc_GetVoiceHandle(voiceChannelID : integer) : integer;  stdcall; far external 'tce1_32.dll';  
function  DJVoc_GetVoiceErr(voiceID : integer) : integer;  stdcall; far external 'tce1_32.dll';  
function  DJVoc_GetVoiceResourceType(voiceID : integer) : VoiceResourcesType;  stdcall; far external 'tce1_32.dll';  
function  DJVoc_GetVoiceOperateType (voiceID : integer) : VoiceOperatorType;  stdcall; far external 'tce1_32.dll';  
function  DJVoc_GetPromptInfoByName(voiceName : pchar;
       			var voiceHandle : integer;
       			var voiceSize : LongInt;
       			var voiceOffset : LongInt) : boolean;
                 stdcall; far external 'tce1_32.dll';
function  DJVoc_GetPromptInfoByHandle(voiceResID : integer;
				var voiceHandle  : Integer;
				var voiceSize : LongInt;
				var voiceOffset : LongInt) : boolean;
            stdcall; far external 'tce1_32.dll';
function  DJVoc_SearchFreeVoiceChannelForPlay  : integer;  stdcall; far external 'tce1_32.dll';
function  DJVoc_SearchFreeVoiceChannelForRecord : integer;  stdcall; far external 'tce1_32.dll';
function  DJVoc_GetRecordTrunkID (voiceChannelID : integer) : integer;  stdcall; far external 'tce1_32.dll';
function  DJVoc_GetRecordUserID	(voiceChannelID : integer) : integer;  stdcall; far external 'tce1_32.dll';  
///******************************************************************************\
// *
// *	Functions for Connect Operate
// *
//\******************************************************************************/
function  DJExg_SetListenUserToUser(trgUserID: integer;srcUserID : integer): boolean;  stdcall; far external 'tce1_32.dll';  
function  DJExg_ClearListenUserFromUser(trgUserID : integer): boolean;  stdcall; far external 'tce1_32.dll';  
function  DJExg_SetListenUserToTrunk( userID : integer;trunkID : integer): boolean;  stdcall; far external 'tce1_32.dll';  
function  DJExg_ClearListenUserFromTrunk( userID : integer): boolean;  stdcall; far external 'tce1_32.dll';  
function  DJExg_SetListenTrunkToUser(trunkID : integer; userID : integer): boolean;  stdcall; far external 'tce1_32.dll';  
function  DJExg_ClearListenTrunkFromUser( trunkID : integer): boolean;  stdcall; far external 'tce1_32.dll';  
function  DJExg_SetListenTrunkToTrunk( trgTrunkID : integer;srcTrunkID : integer): boolean;  stdcall; far external 'tce1_32.dll';
function  DJExg_ClearListenTrunkFromTrunk( trgTrunkID : integer): boolean;  stdcall; far external 'tce1_32.dll';  
function  DJExg_SetLinkTrunkAndUser( trunkID : integer;userID : integer): boolean;  stdcall; far external 'tce1_32.dll';  
function  DJExg_ClearLinkTrunkAndUserByTrunk(trunkID : integer): boolean;  stdcall; far external 'tce1_32.dll';  
function  DJExg_ClearLinkTrunkAndUserByUser( userID : integer): boolean;  stdcall; far external 'tce1_32.dll';  
function  DJExg_SetLinkTrunkAndTrunk(trgTrunkID : integer;srcTrunkID : integer): boolean;  stdcall; far external 'tce1_32.dll';  
function  DJExg_ClearLinkTrunkAndTrunk (trunkID : integer): boolean;  stdcall; far external 'tce1_32.dll';  
function  DJExg_LinkUserAndUser(trgUserID : integer; srcUserID : integer): boolean;  stdcall; far external 'tce1_32.dll';  
function  DJExg_ClearLinkUserAndUser(userID : integer): boolean;  stdcall; far external 'tce1_32.dll';  
function  DJExg_SetLinkPlayVoiceToTrunk	(trunkID : integer;voiceID : integer): boolean;  stdcall; far external 'tce1_32.dll';  
procedure  DJExg_VoiceToTrunk_SetParam (conn : boolean);  stdcall; far external 'tce1_32.dll';  
function  DJExg_ClearLinkPlayVoiceFromTrunk(trunkID : integer): boolean;  stdcall; far external 'tce1_32.dll';  
function  DJExg_SetLinkPlayVoiceToUser(userID : integer;voiceID : integer): boolean;  stdcall; far external 'tce1_32.dll';  
function  DJExg_ClearLinkPlayVoiceFromUser(userID : integer): boolean;  stdcall; far external 'tce1_32.dll';  
function  DJExg_SetLinkRecordVoiceToUser(userID : integer;voiceID : integer): boolean;  stdcall; far external 'tce1_32.dll';  
function  DJExg_ClearLinkRecordVoiceFromUser(userID : integer): boolean;  stdcall; far external 'tce1_32.dll';  
function  DJExg_SetLinkRecordVoiceToTrunk (trunkID : integer;voiceID : integer): boolean;  stdcall; far external 'tce1_32.dll';  
function  DJExg_ClearLinkRecordVoiceFromTrunk(trunkID : integer): boolean;  stdcall; far external 'tce1_32.dll';
/////////////////////////////////////////////////////////////////////////////
function  DJSys_GetPcmNum : integer;  stdcall; far external 'tce1_32.dll';
function  DJSys_GetImpExpNum(ImpExpType : integer) : WORD;  stdcall; far external 'tce1_32.dll';  
function  DJSys_GetTotalImpExpNum( ImpExpType : integer) : LongInt;  stdcall; far external 'tce1_32.dll';  
function  DJSys_GetPcmImpExpNum( pcmID : integer;ImpExpType : integer) : WORD;  stdcall; far external 'tce1_32.dll';
function  DJSys_GetPcmTotalImpExpNum( pcmID : integer;ImpExpType : integer) : LongInt;  stdcall; far external 'tce1_32.dll';  
function  DJSys_GetPcmStatus(pcmID : integer) : BYTE;  stdcall; far external 'tce1_32.dll';  
procedure DJSys_StopWarning;  stdcall; far external 'tce1_32.dll';  
procedure DJSys_EnableWarn;  stdcall;  far external 'tce1_32.dll';  
procedure DJSys_DisableWarn;  stdcall; far external 'tce1_32.dll';
procedure DJSys_GetSysRunTime(var hours : LongInt; var minutes : BYTE;
          var seconds : BYTE);  stdcall; far external 'tce1_32.dll';
procedure DJSys_GetSysTime(var hours : BYTE; var minutes : BYTE;
          var seconds : BYTE);  stdcall; far external 'tce1_32.dll';  
procedure DJSys_SetSysTime(hours :  BYTE; minutes :  BYTE;
          seconds :  BYTE);  stdcall; far external 'tce1_32.dll';  
function  DJSys_GetErrCode : PstnErr;  stdcall; far external 'tce1_32.dll';  
function  DJSys_FreeMfcNum : integer;  stdcall; far external 'tce1_32.dll';  
function  DJSys_FreePlayVoiceNum : integer;  stdcall; far external 'tce1_32.dll';  
function  DJSys_FreeDtmfNum : integer;  stdcall; far external 'tce1_32.dll';  
function  DJSys_GetInstallDir : pchar;  stdcall; far external 'tce1_32.dll';  
procedure  DJSys_AutoApplyDtmf( DtmfMode : integer);  stdcall; far external 'tce1_32.dll';  
function  DJSys_IsAutoApplyDtmf : boolean;  stdcall; far external 'tce1_32.dll';  
procedure  DJSys_EnableAutoKB;  stdcall; far external 'tce1_32.dll';  
procedure  DJSys_DisableAutoKB;  stdcall; far external 'tce1_32.dll';
//--------------------------------------------------------
//Add by Wang Guoli 1998.3.17
function  DJVoc_PlayFile(trunkID : integer; FileName : pchar) : boolean;  stdcall; far external 'tce1_32.dll';  
procedure DJVoc_StopPlayFile(trunkID : integer);  stdcall; far external 'tce1_32.dll';  
procedure DJVoc_InitIndexPlayFile(trunkID : integer);  stdcall; far external 'tce1_32.dll';  
function  DJVoc_AddIndexPlayFile(trunkID : integer; FileName : pchar) : boolean;  stdcall; far external 'tce1_32.dll';  
function  DJVoc_StartIndexPlayFile(trunkID : integer) : boolean;  stdcall; far external 'tce1_32.dll';  
procedure DJVoc_StopIndexPlayFile(trunkID : integer);  stdcall; far external 'tce1_32.dll';  
function  DJVoc_CheckIndexPlayEnd(trunkID : integer) : boolean;  stdcall; far external 'tce1_32.dll';  
function  DJVoc_RecordFile(trunkID : integer; FileName : pchar;
             RecordLen : LongInt) : boolean;  stdcall; far external 'tce1_32.dll';
procedure DJVoc_StopRecordFile(trunkID : integer);  stdcall; far external 'tce1_32.dll';
//added by N.C.J 1999.6.14
function DJVoc_RecordFileNew(trunkID:integer;FileName:PChar;Position:DWORD;Length:DWORD):integer; stdcall; far external 'tce1_32.dll';
function DJVoc_PlayFileNew(trunkID:integer;FileName:PChar;Position:DWORD;Length:DWORD):integer; stdcall; far external 'tce1_32.dll';
//end of add
procedure DJTrk_GetMfcCodeA( trunkID : integer; MfcCode : pchar);  stdcall; far external 'tce1_32.dll';
procedure DJTrk_GetHostCodeA( trunkID : integer; HostCode : pchar );  stdcall; far external 'tce1_32.dll';
procedure DJTrk_GetDtmfCodeA( trunkID : integer; DtmfCode : pchar);  stdcall; far external 'tce1_32.dll';
//addd by lrg
function DJVoc_SFVC_ForPlay(trunkid:integer):integer;stdcall; far external 'tce1_32.dll';
function DJVoc_SFVC_ForRecord(trunkid:integer):integer;stdcall; far external 'tce1_32.dll';
function DJVoc_SFVC_ForPlay_New(trunkid:integer;isfortrunk:boolean):integer;stdcall; far external 'tce1_32.dll';
function DJConf_InitConfCard():integer;stdcall; far external 'tce1_32.dll';
procedure DJConf_ReleaseConfCard();stdcall; far external 'tce1_32.dll';
implementation
end.
