unit HTJS_Tce132;

interface

uses Windows, GlobalConstants, tce132;

function HTJS_Sys_EnableCard (const configFile:PCHAR; const promptVoiceIdxFile:PCHAR) : integer;
procedure HTJS_Sys_DisableCard;
procedure HTJS_Sys_PushPlay;
function HTJS_Sys_EnableDtmfSend():boolean;
function  HTJS_Trk_GetTotalTrunkNum : integer;
function  HTJS_Trk_GetPcmTrunkNum :integer;
function  HTJS_Trk_GetWaitTime(trunkID:integer ): WORD;
function  HTJS_Trk_GetConnectVoiceTime(trunkID : integer ): WORD; 
function  HTJS_Trk_GetConnectTime	(trunkID : integer) : WORD;
function  HTJS_Trk_GetDtmfTime (trunkID : integer) : WORD;
function  HTJS_Trk_GetTrunkConnectTime(trunkID : integer) : LongInt;
function  HTJS_Trk_GetPcmID(trunkID : integer ): integer;
function  HTJS_Trk_GetConnectTrunkID(trunkID : integer): integer;
function  HTJS_Trk_GetConnectUserID(trunkID : integer) : integer;
function  HTJS_Trk_GetListenUserID(trunkID : integer) : integer;
function  HTJS_Trk_GetListenTrunkID(trunkID : integer): integer;
function  HTJS_Trk_GetTrunkPlayID(trunkID : integer): integer;
function  HTJS_Trk_GetTrunkRecordID(trunkID : integer): integer;
function  HTJS_Trk_CheckReady(trunkID : integer) : boolean;
function  HTJS_Trk_CheckConnect(trunkID : integer) : boolean;
function  HTJS_Trk_CheckWait(trunkID : integer) : boolean;
function  HTJS_Trk_CheckForwardHangUp(trunkID : integer) : boolean;
function  HTJS_Trk_CheckTrunkIn(trunkID : integer) : boolean;
function  HTJS_Trk_CheckTrunkFree(trunkID : integer) : boolean;
function  HTJS_Trk_CheckTrunkEnable	(trunkID : integer) : boolean;
function  HTJS_Trk_CheckPlayPromptStrEnd	(trunkID : integer) : boolean;
function  HTJS_User_CheckPlayPromptStrEnd	(userId : integer) : boolean;
function  HTJS_Trk_BackwardHangUp(trunkID : integer) : boolean;
function  HTJS_Trk_DisableTrunk(trunkID : integer) : boolean;
function  HTJS_Trk_EnableTrunk(trunkID : integer) : boolean;
function  HTJS_Trk_GetFreeTrunkIDForDial (pcmID : integer) : integer;
function  HTJS_Trk_StartDial(trunkID: integer; const szPhoneNum : pchar; const szCallerStr) : boolean;
procedure HTJS_Trk_StartDial_SetParam (foreKD : BYTE; foreKA : BYTE );
function  HTJS_Trk_AppendTelNum(trunkID : integer; phoneCode : char ) : boolean;
function  HTJS_Trk_GetDialStatus(trunkID : integer) : DialStatus;
function  HTJS_trunkCheckDialIsFail(trunkID : integer) : boolean;
function  HTJS_Trk_CheckApplyDtmf(trunkID : integer) : boolean;
function  HTJS_Trk_ApplyDtmf(trunkID : integer) : boolean;
function  HTJS_Trk_FreeDtmf(trunkID : integer) : boolean; 
function  HTJS_Trk_GetMfcCode(trunkID : integer) : pchar;
function  HTJS_Trk_GetHostCode(trunkID : integer) : pchar;
function  HTJS_Trk_GetDtmfCode(trunkID : integer) : pchar;
function  HTJS_Trk_GetDtmfCodeNew(trunkID : integer) : pchar;
function  HTJS_Trk_GetReciveMfcNum(trunkID : integer) : integer;
function  HTJS_Trk_GetReciveCallerNum(trunkID : integer) : integer;
function  HTJS_Trk_GetReciveDtmfNum(trunkID : integer) : integer;
function  HTJS_Trk_GetFirstDtmfCode(trunkID : integer) : char;
function  HTJS_Trk_GetLastDtmfCode	(trunkID : integer) : char;
function  HTJS_Trk_InitDtmfBuf(trunkID : integer) : boolean;
function  HTJS_Trk_SendDtmfStr(trunkid:integer;sendstr:pchar):integer;
function  HTJS_Trk_CheckDtmfSendEnd(trunkid:integer):boolean;
function  HTJS_Trk_SetTrunkType(trunkID : integer; Tktype : TrunkType) : boolean;
function  HTJS_Trk_GetTrunkStatus(trunkID : integer) : integer;
function  HTJS_Trk_PlayPromptStr(trunkID : integer; pcPrompStr: String) : integer;
function  HTJS_User_PlayPromptStr(trunkID : integer; pcPrompStr: String) : integer;
function  HTJS_Trk_GetTrunkKD(trunkID : integer) : BYTE;
function  HTJS_Trk_GetTrunkKB(trunkID : integer) : BYTE;
function  HTJS_Trk_GetTrunkForwardKA(trunkID : integer) : BYTE;
function  HTJS_Trk_GetTrunkBackwardA(trunkID : integer) : BYTE;
function  HTJS_Trk_GetTrunkForwardMFN(trunkID : integer) : BYTE;
function  HTJS_Trk_GetTrunkForwardDL (trunkID : integer) : byte;
function  HTJS_Trk_GetTrunkBackwardDL(trunkID : integer) : byte;
function  HTJS_trunkPutBackDL(trunkID : integer; backDL : BYTE) : boolean;
function  HTJS_Trk_WaitBackwardA3(trunkID : integer) : boolean;
function  HTJS_Trk_SetTrunkKB(trunkID : integer; backKB : BYTE) : boolean;
procedure  HTJS_Trk_SetTrunkKB_SetParam (backA : BYTE);
function  HTJS_User_GetTotalUserNum : integer;
procedure HTJS_User_SetPowerON (userID : integer);     	
procedure HTJS_User_SetPowerOFF(userID : integer);    
function  HTJS_User_CheckPowerStatus(userID : integer) : boolean;     	
procedure HTJS_User_SetUserHalfPower(userID : integer);    
procedure HTJS_User_SetUserFullPower(userID : integer);    
function  HTJS_User_RingDetect(userID : integer) : boolean;    
function  HTJS_User_StartRing(userID : integer) : boolean;  
function  HTJS_User_StopRing(userID : integer) : boolean;    
function  HTJS_User_EnableDialSound(userID : integer) : boolean;     
function  HTJS_User_DisableDialSound(userID : integer) : boolean;  
function  HTJS_User_CheckHookOFF(userID : integer) : boolean;    
function  HTJS_User_GetDialNum(userID : integer) : integer;  
function  HTJS_User_GetDialNumNew(userID : integer) : integer;  
function  HTJS_User_GetDialCode(userID : integer) : pchar;  
function  HTJS_User_GetDialCodeNew(userID : integer) : pchar;    
function  HTJS_User_GetFirstDialCode(userID : integer) : char;  
function  HTJS_User_GetLastDialCode(userID : integer) : char;    
function  HTJS_User_InitDialBuf(userID : integer) : boolean;  
function  HTJS_User_GetConnectTrunkID(userID : integer) : integer;    
function  HTJS_User_GetConnectUserID(userID : integer) : integer;    
function  HTJS_User_GetListenUserID(userID : integer) : integer;    
function  HTJS_User_GetListenTrunkID(userID : integer) : integer;  
function  HTJS_User_GetPlayChannel(userID : integer) : integer;  
function  HTJS_User_GetRecordChannel(userID : integer) : integer;  
function  HTJS_User_SetSendSoundValue(userID : integer; value : integer) : boolean;  
function  HTJS_User_SetReciveSoundValue(userID : integer;value : integer) : boolean;  
function  HTJS_User_GetSendSoundValue(userID : integer) : integer;  
function  HTJS_User_GetReciveSoundValue(userID : integer) : integer;
function  HTJS_User_GetPreStopDialToNowTime(userID : integer) : WORD;  
function  HTJS_User_SearchFreeTrunkAndDial(userID : integer; pcmID : integer;  szPhoneNum : pchar; szCallerStr : pchar ) : PstnErr;  
function  HTJS_User_DialByTrunk (userID : integer;  trunkID : integer;  szPhoneNum : pchar; szCallerStr : pchar ) : boolean; 
function  HTJS_User_GetDialTrunkID(userID : integer) : integer;  
function  HTJS_User_GetDialStatus(userID : integer) : DialStatus;   
function  HTJS_User_PlayFileNew(userID:integer;FileName:PChar;Position:DWORD;Length:DWORD):integer; 
procedure HTJS_User_StopPlayFile(userID:integer);  
function HTJS_User_RecordFileNew(userID:integer;FileName:PChar;Position:DWORD;Length:DWORD):integer;
procedure HTJS_User_StopRecordFile(userID:integer);
function  HTJS_Voc_GetTotalVoiceChannel : integer;  
function  HTJS_Voc_VoiceStart(voiceChannelID : integer;   voiceResHandle : integer;     voiceResSize : LongInt;        voiceResOffset : LongInt;     voiceResType : VoiceResourcesType;          voiceOpType : VoiceOperatorType) : integer;      
function  HTJS_Voc_PlayNextVoice(voiceChannelID : integer;  	     voiceResHandle : integer;   			     voiceResSize : LongInt;                                voiceResOffset : LongInt) : integer;              
function  HTJS_Voc_FromHeadRePlay(voiceID : integer) : integer;  
function  HTJS_Voc_PlayPromptFile(voiceID : integer;voiceName : pchar) : boolean;  
function  HTJS_Voc_LoopPlayPromptFile( voiceID : integer;voiceName : pchar) : boolean;  
function  HTJS_Voc_PlayPromptID(voiceID : integer;voiceResID : integer) : boolean;  
function  HTJS_Voc_LoopPlayPromptID(voiceID : integer;voiceResID : integer) : boolean;  
function  HTJS_Voc_PauseChannelVoiceOp(voiceID : integer) : boolean;  
function  HTJS_Voc_ContinueChannelVoiceOp(voiceID : integer) : boolean;    
function  HTJS_Voc_CheckChannelVoicePause(voiceID : integer) : boolean;    
function  HTJS_Voc_VoiceStop(voiceChannelID : integer) : boolean;  
function  HTJS_Voc_CheckVoiceEnd(voiceChannelID : integer) : boolean;  
function  HTJS_Voc_CheckVoiceChannelOp(voiceChannelID : integer) : boolean;    
function  HTJS_Voc_GetVoiceDataLength(voiceChannelID : integer) : longint;    
function  HTJS_Voc_GetVoiceHandle(voiceChannelID : integer) : integer;    
function  HTJS_Voc_GetVoiceErr(voiceID : integer) : integer;  
function  HTJS_Voc_GetVoiceResourceType(voiceID : integer) : VoiceResourcesType;  
function  HTJS_Voc_GetVoiceOperateType (voiceID : integer) : VoiceOperatorType;    
function  HTJS_Voc_GetPromptInfoByName(voiceName : pchar;         			var voiceHandle : integer;       			var voiceSize : LongInt;       			var voiceOffset : LongInt) : boolean;                 
function  HTJS_Voc_GetPromptInfoByHandle(voiceResID : integer;  				var voiceHandle  : Integer;				var voiceSize : LongInt;				var voiceOffset : LongInt) : boolean;            
function  HTJS_Voc_SearchFreeVoiceChannelForPlay  : integer;  
function  HTJS_Voc_SearchFreeVoiceChannelForRecord : integer;  
function  HTJS_Voc_GetRecordTrunkID (voiceChannelID : integer) : integer;  
function  HTJS_Voc_GetRecordUserID	(voiceChannelID : integer) : integer;    
function  HTJS_Exg_SetListenUserToUser(trgUserID: integer;srcUserID : integer): boolean;    
function  HTJS_Exg_ClearListenUserFromUser(trgUserID : integer): boolean;  
function  HTJS_Exg_SetListenUserToTrunk( userID : integer;trunkID : integer): boolean;  
function  HTJS_Exg_ClearListenUserFromTrunk( userID : integer): boolean;    
function  HTJS_Exg_SetListenTrunkToUser(trunkID : integer; userID : integer): boolean;  
function  HTJS_Exg_ClearListenTrunkFromUser( trunkID : integer): boolean;    
function  HTJS_Exg_SetListenTrunkToTrunk( trgTrunkID : integer;srcTrunkID : integer): boolean;  
function  HTJS_Exg_ClearListenTrunkFromTrunk( trgTrunkID : integer): boolean;  
function  HTJS_Exg_SetLinkTrunkAndUser( trunkID : integer;userID : integer): boolean;    
function  HTJS_Exg_ClearLinkTrunkAndUserByTrunk(trunkID : integer): boolean;  
function  HTJS_Exg_ClearLinkTrunkAndUserByUser( userID : integer): boolean;    
function  HTJS_Exg_SetLinkTrunkAndTrunk(trgTrunkID : integer;srcTrunkID : integer): boolean;    
function  HTJS_Exg_ClearLinkTrunkAndTrunk (trunkID : integer): boolean;    
function  HTJS_Exg_LinkUserAndUser(trgUserID : integer; srcUserID : integer): boolean;    
function  HTJS_Exg_ClearLinkUserAndUser(userID : integer): boolean;  
function  HTJS_Exg_SetLinkPlayVoiceToTrunk	(trunkID : integer;voiceID : integer): boolean;  
procedure  HTJS_Exg_VoiceToTrunk_SetParam (conn : boolean);    
function  HTJS_Exg_ClearLinkPlayVoiceFromTrunk(trunkID : integer): boolean;    
function  HTJS_Exg_SetLinkPlayVoiceToUser(userID : integer;voiceID : integer): boolean;    
function  HTJS_Exg_ClearLinkPlayVoiceFromUser(userID : integer): boolean;    
function  HTJS_Exg_SetLinkRecordVoiceToUser(userID : integer;voiceID : integer): boolean;    
function  HTJS_Exg_ClearLinkRecordVoiceFromUser(userID : integer): boolean;  
function  HTJS_Exg_SetLinkRecordVoiceToTrunk (trunkID : integer;voiceID : integer): boolean;    
function  HTJS_Exg_ClearLinkRecordVoiceFromTrunk(trunkID : integer): boolean;  
function  HTJS_Sys_GetPcmNum : integer;
function  HTJS_Sys_GetImpExpNum(ImpExpType : integer) : WORD;  
function  HTJS_Sys_GetTotalImpExpNum( ImpExpType : integer) : LongInt;  
function  HTJS_Sys_GetPcmImpExpNum( pcmID : integer;ImpExpType : integer) : WORD;  
function  HTJS_Sys_GetPcmTotalImpExpNum( pcmID : integer;ImpExpType : integer) : LongInt;  
function  HTJS_Sys_GetPcmStatus(pcmID : integer) : BYTE;  
procedure HTJS_Sys_StopWarning;  
procedure HTJS_Sys_EnableWarn;
procedure HTJS_Sys_DisableWarn;  
procedure HTJS_Sys_GetSysRunTime(var hours : LongInt; var minutes : BYTE;            var seconds : BYTE);  
procedure HTJS_Sys_GetSysTime(var hours : BYTE; var minutes : BYTE;          var seconds : BYTE);  
procedure HTJS_Sys_SetSysTime(hours :  BYTE; minutes :  BYTE;          seconds :  BYTE);  
function  HTJS_Sys_GetErrCode : PstnErr;  
function  HTJS_Sys_FreeMfcNum : integer;    
function  HTJS_Sys_FreePlayVoiceNum : integer;  
function  HTJS_Sys_FreeDtmfNum : integer;    
function  HTJS_Sys_GetInstallDir : pchar;
procedure  HTJS_Sys_AutoApplyDtmf( DtmfMode : integer);    
function  HTJS_Sys_IsAutoApplyDtmf : boolean;  
procedure  HTJS_Sys_EnableAutoKB;  
procedure  HTJS_Sys_DisableAutoKB;
function  HTJS_Voc_PlayFile(trunkID : integer; FileName : pchar) : boolean;    
procedure HTJS_Voc_StopPlayFile(trunkID : integer);  
procedure HTJS_Voc_InitIndexPlayFile(trunkID : integer);  
function  HTJS_Voc_AddIndexPlayFile(trunkID : integer; FileName : pchar) : boolean;  
function  HTJS_Voc_StartIndexPlayFile(trunkID : integer) : boolean;  
procedure HTJS_Voc_StopIndexPlayFile(trunkID : integer);  
function  HTJS_Voc_CheckIndexPlayEnd(trunkID : integer) : boolean;  
function  HTJS_Voc_RecordFile(trunkID : integer; FileName : pchar;              RecordLen : LongInt) : boolean;  
procedure HTJS_Voc_StopRecordFile(trunkID : integer);
function HTJS_Voc_RecordFileNew(trunkID:integer;FileName:PChar;Position:DWORD;Length:DWORD):integer; 
function HTJS_Voc_PlayFileNew(trunkID:integer;FileName:PChar;Position:DWORD;Length:DWORD):integer;
procedure HTJS_Trk_GetMfcCodeA( trunkID : integer; MfcCode : pchar);  
procedure HTJS_Trk_GetHostCodeA( trunkID : integer; HostCode : pchar );  
procedure HTJS_Trk_GetDtmfCodeA( trunkID : integer; DtmfCode : pchar);
function HTJS_Voc_SFVC_ForPlay(trunkid:integer):integer;
function HTJS_Voc_SFVC_ForRecord(trunkid:integer):integer;
function HTJS_Voc_SFVC_ForPlay_New(trunkid:integer;isfortrunk:boolean):integer;
function HTJS_Conf_InitConfCard():integer;
procedure HTJS_Conf_ReleaseConfCard();
implementation
function HTJS_Sys_EnableCard (const configFile:PCHAR; const promptVoiceIdxFile:PCHAR) : integer;
begin
  if CardDriver_virtual then begin
    result := 0;
  end else begin
    Result := DJSys_EnableCard (configFile,promptVoiceIdxFile);
  end;
end;
procedure HTJS_Sys_DisableCard;
begin
  if CardDriver_virtual then begin
  end else begin
    DJSys_DisableCard;
  end;
end;
procedure HTJS_Sys_PushPlay;
begin
  if CardDriver_virtual then begin
  end else begin
    DJSys_PushPlay;
  end;
end;
function HTJS_Sys_EnableDtmfSend():boolean;
begin
  if CardDriver_virtual then begin
    Result := True;
  end else begin
    Result := DJSys_EnableDtmfSend;
  end;
end;
function  HTJS_Trk_GetTotalTrunkNum : integer;
begin
  if CardDriver_virtual then begin
    Result := 2;
  end else begin
    Result := DJTrk_GetTotalTrunkNum;
  end;
end;
function  HTJS_Trk_GetPcmTrunkNum :integer;
begin
  if CardDriver_virtual then begin
    Result := 60;
  end else begin
    Result := DJTrk_GetPcmTrunkNum;
  end;
end;
function  HTJS_Trk_GetWaitTime(trunkID:integer ): WORD;
begin
  if CardDriver_virtual then begin
    Result := 60;
  end else begin
    Result := DJTrk_GetWaitTime(trunkId);
  end;
end;
function  HTJS_Trk_GetConnectVoiceTime(trunkID : integer ): WORD;
begin
  if CardDriver_virtual then begin
    Result := 60;
  end else begin
    Result := DJTrk_GetConnectVoiceTime(trunkID);
  end;
end;
function  HTJS_Trk_GetConnectTime	(trunkID : integer) : WORD;
begin
  if CardDriver_virtual then begin
    Result := 60;
  end else begin
    Result := DJTrk_GetConnectTime(trunkID);
  end;
end;
function  HTJS_Trk_GetDtmfTime (trunkID : integer) : WORD;
begin
  if CardDriver_virtual then begin
    Result := 60;
  end else begin
    Result := DJTrk_GetDtmfTime(trunkID);
  end;
end;
function  HTJS_Trk_GetTrunkConnectTime(trunkID : integer) : LongInt;
begin
  if CardDriver_virtual then begin
    Result := 60;
  end else begin
    Result := DJTrk_GetTrunkConnectTime(trunkID);
  end;
end;
function  HTJS_Trk_GetPcmID(trunkID : integer ): integer;
begin
  if CardDriver_virtual then begin
    Result := 60;
  end else begin
    Result := DJTrk_GetPcmID(trunkID);
  end;
end;
function  HTJS_Trk_GetConnectTrunkID(trunkID : integer): integer;
begin
  if CardDriver_virtual then begin
    Result := 60;
  end else begin
    Result := DJTrk_GetConnectTrunkID(trunkID);
  end;
end;
function  HTJS_Trk_GetConnectUserID(trunkID : integer) : integer;
begin
  if CardDriver_virtual then begin
    Result := 60;
  end else begin
    Result := DJTrk_GetConnectUserID(trunkID);
  end;
end;
function  HTJS_Trk_GetListenUserID(trunkID : integer) : integer;
begin
  if CardDriver_virtual then begin
    Result := 60;
  end else begin
    Result := DJTrk_GetListenUserID(trunkID);
  end;
end;
function  HTJS_Trk_GetListenTrunkID(trunkID : integer): integer;
begin
  if CardDriver_virtual then begin
    Result := 60;
  end else begin
    Result := DJTrk_GetListenTrunkID(trunkID);
  end;
end;
function  HTJS_Trk_GetTrunkPlayID(trunkID : integer): integer;
begin
  if CardDriver_virtual then begin
    Result := 60;
  end else begin
    Result := DJTrk_GetTrunkPlayID(trunkID);
  end;
end;
function  HTJS_Trk_GetTrunkRecordID(trunkID : integer): integer;
begin
  if CardDriver_virtual then begin
    Result := 60;
  end else begin
    Result := DJTrk_GetTrunkRecordID(trunkID);
  end;
end;
function  HTJS_Trk_CheckReady(trunkID : integer) : boolean;
begin
  if CardDriver_virtual then begin
    Result := True;
  end else begin
    Result := DJTrk_CheckReady(trunkID);
  end;
end;
function  HTJS_Trk_CheckConnect(trunkID : integer) : boolean;
begin
  if CardDriver_virtual then begin
    Result := True;
  end else begin
    Result := DJTrk_CheckConnect(trunkID);
  end;
end;
function  HTJS_Trk_CheckWait(trunkID : integer) : boolean;
begin
  if CardDriver_virtual then begin
    Result := True;
  end else begin
    Result := DJTrk_CheckWait(trunkID);
  end;
end;
function  HTJS_Trk_CheckForwardHangUp(trunkID : integer) : boolean;
begin
  if CardDriver_virtual then begin
    Result := True;
  end else begin
    Result := DJTrk_CheckForwardHangUp(trunkID);
  end;
end;
function  HTJS_Trk_CheckTrunkIn(trunkID : integer) : boolean;
begin
  if CardDriver_virtual then begin
    Result := True;
  end else begin
    Result := DJTrk_CheckTrunkIn(trunkID);
  end;
end;
function  HTJS_Trk_CheckTrunkFree(trunkID : integer) : boolean;
begin
  if CardDriver_virtual then begin
    Result := True;
  end else begin
    Result := DJTrk_CheckTrunkFree(trunkID);
  end;
end;
function  HTJS_Trk_CheckTrunkEnable	(trunkID : integer) : boolean;
begin
  if CardDriver_virtual then begin
    Result := True;
  end else begin
    Result := DJTrk_CheckTrunkEnable	(trunkID);
  end;
end;
function  HTJS_Trk_CheckPlayPromptStrEnd	(trunkID : integer) : boolean;
begin
  if CardDriver_virtual then begin
    Result := True;
  end else begin
    Result := DJTrk_CheckPlayPromptStrEnd(trunkID);
  end;
end;
function  HTJS_User_CheckPlayPromptStrEnd	(userId : integer) : boolean;
begin
  if CardDriver_virtual then begin
    Result := True;
  end else begin
    Result := DJUser_CheckPlayPromptStrEnd(userId);
  end;
end;
function  HTJS_Trk_BackwardHangUp(trunkID : integer) : boolean;
begin
  if CardDriver_virtual then begin
    Result := True;
  end else begin
    Result := DJTrk_BackwardHangUp(trunkID);
  end;
end;
function  HTJS_Trk_DisableTrunk(trunkID : integer) : boolean;
begin
  if CardDriver_virtual then begin
    Result := True;
  end else begin
    Result := DJTrk_DisableTrunk(trunkID);
  end;
end;
function  HTJS_Trk_EnableTrunk(trunkID : integer) : boolean;
begin
  if CardDriver_virtual then begin
    Result := True;
  end else begin
    Result := DJTrk_EnableTrunk(trunkID);
  end;
end;
function  HTJS_Trk_GetFreeTrunkIDForDial (pcmID : integer) : integer;
begin
  if CardDriver_virtual then begin
    Result := 60;
  end else begin
    Result := DJTrk_GetFreeTrunkIDForDial(pcmID);
  end;
end;
function  HTJS_Trk_StartDial(trunkID: integer; const szPhoneNum : pchar; const szCallerStr) : boolean;
begin
  if CardDriver_virtual then begin
    Result := True;
  end else begin
    Result := DJTrk_StartDial(trunkID,szPhoneNum,szCallerStr);
  end;
end;
procedure HTJS_Trk_StartDial_SetParam (foreKD : BYTE; foreKA : BYTE );
begin
  if CardDriver_virtual then begin
  end else begin
    DJTrk_StartDial_SetParam (foreKD,foreKA);
  end;
end;
function  HTJS_Trk_AppendTelNum(trunkID : integer; phoneCode : char ) : boolean;
begin
  if CardDriver_virtual then begin
    Result := True;
  end else begin
    Result := DJTrk_AppendTelNum(trunkID,phoneCode);
  end;
end;
function  HTJS_Trk_GetDialStatus(trunkID : integer) : DialStatus;
begin
  if CardDriver_virtual then begin
    Result := DS_NoDial;
  end else begin
    Result := DJTrk_GetDialStatus(trunkID);
  end;
end;
function  HTJS_trunkCheckDialIsFail(trunkID : integer) : boolean;
begin
  if CardDriver_virtual then begin
    Result := True;
  end else begin
    Result := trunkCheckDialIsFail(trunkID);
  end;
end;
function  HTJS_Trk_CheckApplyDtmf(trunkID : integer) : boolean;
begin
  if CardDriver_virtual then begin
    Result := True;
  end else begin
    Result := DJTrk_CheckApplyDtmf(trunkID);
  end;
end;
function  HTJS_Trk_ApplyDtmf(trunkID : integer) : boolean;
begin
  if CardDriver_virtual then begin
    Result := True;
  end else begin
    Result := DJTrk_ApplyDtmf(trunkID);
  end;
end;
function  HTJS_Trk_FreeDtmf(trunkID : integer) : boolean;
begin
  if CardDriver_virtual then begin
    Result := True;
  end else begin
    Result := DJTrk_FreeDtmf(trunkID);
  end;
end;
function  HTJS_Trk_GetMfcCode(trunkID : integer) : pchar;
begin
  if CardDriver_virtual then begin
    Result := '1';
  end else begin
    Result := DJTrk_GetMfcCode(trunkID);
  end;
end;
function  HTJS_Trk_GetHostCode(trunkID : integer) : pchar;
begin
  if CardDriver_virtual then begin
    Result := '1';
  end else begin
    Result := DJTrk_GetHostCode(trunkID);
  end;
end;
function  HTJS_Trk_GetDtmfCode(trunkID : integer) : pchar;
begin
  if CardDriver_virtual then begin
    Result := '12345678';
  end else begin
    Result := DJTrk_GetDtmfCode(trunkID);
  end;
end;
function  HTJS_Trk_GetDtmfCodeNew(trunkID : integer) : pchar;
begin
  if CardDriver_virtual then begin
    Result := '1';
  end else begin
    Result := DJTrk_GetDtmfCodeNew(trunkID);
  end;
end;
function  HTJS_Trk_GetReciveMfcNum(trunkID : integer) : integer;
begin
  if CardDriver_virtual then begin
    Result := 1;
  end else begin
    Result := DJTrk_GetReciveMfcNum(trunkID);
  end;
end;
function  HTJS_Trk_GetReciveCallerNum(trunkID : integer) : integer;  
begin
    if CardDriver_virtual then begin
	Result := 1;
    end else begin
	Result := DJTrk_GetReciveCallerNum(trunkID);
    end;
end;
function  HTJS_Trk_GetReciveDtmfNum(trunkID : integer) : integer;  
begin
    if CardDriver_virtual then begin
	Result := 1;
    end else begin
	Result := DJTrk_GetReciveDtmfNum(trunkID);
    end;
end;
function  HTJS_Trk_GetFirstDtmfCode(trunkID : integer) : char;  
begin
    if CardDriver_virtual then begin
	Result := '1';
    end else begin
	Result := DJTrk_GetFirstDtmfCode(trunkID);
    end;
end;
function  HTJS_Trk_GetLastDtmfCode(trunkID : integer) : char;  
begin
    if CardDriver_virtual then begin
	Result := '1';
    end else begin
	Result := DJTrk_GetLastDtmfCode(trunkID);
    end;
end;
function  HTJS_Trk_InitDtmfBuf(trunkID : integer) : boolean;  
begin
    if CardDriver_virtual then begin
	Result := True;
    end else begin
	Result := DJTrk_InitDtmfBuf(trunkID);
    end;
end;
function  HTJS_Trk_SendDtmfStr(trunkid:integer;sendstr:pchar):integer;
begin
    if CardDriver_virtual then begin
        Result := 1;
    end else begin
        Result := DJTrk_SendDtmfStr(trunkid,sendstr);
    end;
end;
function  HTJS_Trk_CheckDtmfSendEnd(trunkid:integer):boolean;
begin
    if CardDriver_virtual then begin
        Result := True;
    end else begin
        Result := DJTrk_CheckDtmfSendEnd(trunkid);
    end;
end;
function  HTJS_Trk_SetTrunkType(trunkID : integer; Tktype : TrunkType) : boolean;
begin
    if CardDriver_virtual then begin
        Result := True;
    end else begin
        Result := DjTrk_SetTrunkType(trunkID,Tktype);
    end;
end;
function  HTJS_Trk_GetTrunkStatus(trunkID : integer) : integer;
begin
    if CardDriver_virtual then begin
        Result := 1;
    end else begin
        Result := DJTrk_GetTrunkStatus(trunkID);
    end;
end;
function  HTJS_Trk_PlayPromptStr(trunkID : integer; pcPrompStr: String) : integer;
begin
    if CardDriver_virtual then begin
        Result := 1;
    end else begin
        Result := DJTrk_PlayPromptStr(trunkID, pcPrompStr);
    end;
end;
function  HTJS_User_PlayPromptStr(trunkID : integer; pcPrompStr: String) : integer;  
begin
    if CardDriver_virtual then begin
        Result := 1;
    end else begin
        Result := DJUser_PlayPromptStr(trunkID, pcPrompStr);
    end;
end;
function  HTJS_Trk_GetTrunkKD(trunkID : integer) : BYTE;  
begin
    if CardDriver_virtual then begin
        Result := 1;
    end else begin
        Result := DJTrk_GetTrunkKD(trunkID);
    end;
end;
function  HTJS_Trk_GetTrunkKB(trunkID : integer) : BYTE;
begin
    if CardDriver_virtual then begin
        Result := 1;
    end else begin
        Result := DJTrk_GetTrunkKB(trunkID);
    end;
end;
function  HTJS_Trk_GetTrunkForwardKA(trunkID : integer) : BYTE;
begin
    if CardDriver_virtual then begin
        Result := 1;
    end else begin
        Result := DJTrk_GetTrunkForwardKA(trunkID);
    end;
end;
function  HTJS_Trk_GetTrunkBackwardA(trunkID : integer) : BYTE;
begin
    if CardDriver_virtual then begin
        Result := 1;
    end else begin
        Result := DJTrk_GetTrunkBackwardA(trunkID);
    end;
end;
function  HTJS_Trk_GetTrunkForwardMFN(trunkID : integer) : BYTE;
begin
    if CardDriver_virtual then begin
        Result := 1;
    end else begin
        Result := DJTrk_GetTrunkForwardMFN(trunkID);
    end;
end;
function  HTJS_Trk_GetTrunkForwardDL (trunkID : integer) : byte;
begin
    if CardDriver_virtual then begin
        Result := 1;
    end else begin
        Result := DJTrk_GetTrunkForwardDL(trunkID);
    end;
end;
function  HTJS_Trk_GetTrunkBackwardDL(trunkID : integer) : byte;
begin
    if CardDriver_virtual then begin
        Result := 1;
    end else begin
        Result := DJTrk_GetTrunkBackwardDL(trunkID);
    end;
end;
function  HTJS_trunkPutBackDL(trunkID : integer; backDL : BYTE) : boolean;
begin
    if CardDriver_virtual then begin
        Result := True;
    end else begin
        Result := trunkPutBackDL(trunkID, backDL);
    end;
end;
function  HTJS_Trk_WaitBackwardA3(trunkID : integer) : boolean;
begin
    if CardDriver_virtual then begin
        Result := True;
    end else begin
        Result := DJTrk_WaitBackwardA3(trunkID);
    end;
end;
function  HTJS_Trk_SetTrunkKB(trunkID : integer; backKB : BYTE) : boolean;
begin
    if CardDriver_virtual then begin
        Result := True;
    end else begin
        Result := DJTrk_SetTrunkKB(trunkID, backKB);
    end;
end;
procedure  HTJS_Trk_SetTrunkKB_SetParam (backA : BYTE);
begin
    if CardDriver_virtual then begin
    end else begin
        DJTrk_SetTrunkKB_SetParam (backA);
    end;
end;
function  HTJS_User_GetTotalUserNum : integer;
begin
  if CardDriver_virtual then begin
    Result := 2;
  end else begin //Result := 10;
    Result := DJUser_GetTotalUserNum;
  end;
end;
procedure HTJS_User_SetPowerON (userID : integer);
begin
    if CardDriver_virtual then begin
    end else begin
        DJUser_SetPowerON (userID);
    end;
end;
   	
procedure HTJS_User_SetPowerOFF(userID : integer);
begin
    if CardDriver_virtual then begin
    end else begin
        DJUser_SetPowerOFF(userID);
    end;
end;
function  HTJS_User_CheckPowerStatus(userID : integer) : boolean;
begin
    if CardDriver_virtual then begin
        Result := True;
    end else begin
        Result := DJUser_CheckPowerStatus(userID );
    end;
end;
   	
procedure HTJS_User_SetUserHalfPower(userID : integer);
begin
    if CardDriver_virtual then begin
    end else begin
        DJUser_SetUserHalfPower(userID);
    end;
end;
procedure HTJS_User_SetUserFullPower(userID : integer);
begin
    if CardDriver_virtual then begin
    end else begin
        DJUser_SetUserFullPower(userID);
    end;
end;
function  HTJS_User_RingDetect(userID : integer) : boolean;
begin
    if CardDriver_virtual then begin
        Result := True;
    end else begin
        Result := DJUser_RingDetect(userID);
    end;
end;
function  HTJS_User_StartRing(userID : integer) : boolean;
begin
    if CardDriver_virtual then begin
        Result := True;
    end else begin
        Result := DJUser_StartRing(userID);
    end;
end;
function  HTJS_User_StopRing(userID : integer) : boolean;
begin
    if CardDriver_virtual then begin
        Result := True;
    end else begin
        Result := DJUser_StopRing(userID);
    end;
end;
function  HTJS_User_EnableDialSound(userID : integer) : boolean;
begin
    if CardDriver_virtual then begin
        Result := True;
    end else begin
        Result := DJUser_EnableDialSound(userID);
    end;
end;
function  HTJS_User_DisableDialSound(userID : integer) : boolean;
begin
    if CardDriver_virtual then begin
        Result := True;
    end else begin
        Result := DJUser_DisableDialSound(userID);
    end;
end;
function  HTJS_User_CheckHookOFF(userID : integer) : boolean;
begin
    if CardDriver_virtual then begin
        Result := True;
    end else begin
        Result := DJUser_CheckHookOFF(userID);
    end;
end;
function  HTJS_User_GetDialNum(userID : integer) : integer;
begin
    if CardDriver_virtual then begin
        Result := 1;
    end else begin
        Result := DJUser_GetDialNum(userID);
    end;
end;
function  HTJS_User_GetDialNumNew(userID : integer) : integer;
begin
    if CardDriver_virtual then begin
        Result := 1;
    end else begin
        Result := DJUser_GetDialNumNew(userID);
    end;
end;
function  HTJS_User_GetDialCode(userID : integer) : pchar;
begin
    if CardDriver_virtual then begin
        Result := '*01#';
    end else begin
        Result := DJUser_GetDialCode(userID);
    end;
end;
function  HTJS_User_GetDialCodeNew(userID : integer) : pchar;
begin
    if CardDriver_virtual then begin
        Result := '1';
    end else begin
        Result := DJUser_GetDialCodeNew(userID);
    end;
end;
function  HTJS_User_GetFirstDialCode(userID : integer) : char;
begin
    if CardDriver_virtual then begin
        Result := '1';
    end else begin
        Result := DJUser_GetFirstDialCode(userID);
    end;
end;
function  HTJS_User_GetLastDialCode(userID : integer) : char;
begin
    if CardDriver_virtual then begin
        Result := '1';
    end else begin
        Result := DJUser_GetLastDialCode(userID);
    end;
end;
function  HTJS_User_InitDialBuf(userID : integer) : boolean;
begin
    if CardDriver_virtual then begin
        Result := True;
    end else begin
        Result := DJUser_InitDialBuf(userID);
    end;
end;
function  HTJS_User_GetConnectTrunkID(userID : integer) : integer;
begin
    if CardDriver_virtual then begin
        Result := 1;
    end else begin
        Result := DJUser_GetConnectTrunkID(userID);
    end;
end;
function  HTJS_User_GetConnectUserID(userID : integer) : integer;
begin
    if CardDriver_virtual then begin
        Result := 1;
    end else begin
        Result := DJUser_GetConnectUserID(userID);
    end;
end;
function  HTJS_User_GetListenUserID(userID : integer) : integer;
begin
    if CardDriver_virtual then begin
        Result := 1;
    end else begin
        Result := DJUser_GetListenUserID(userID);
    end;
end;
function  HTJS_User_GetListenTrunkID(userID : integer) : integer;
begin
    if CardDriver_virtual then begin
        Result := 1;
    end else begin
        Result := DJUser_GetListenTrunkID(userID);
    end;
end;
function  HTJS_User_GetPlayChannel(userID : integer) : integer;
begin
    if CardDriver_virtual then begin
        Result := 1;
    end else begin
        Result := DJUser_GetPlayChannel(userID);
    end;
end;
function  HTJS_User_GetRecordChannel(userID : integer) : integer;
begin
    if CardDriver_virtual then begin
        Result := 1;
    end else begin
        Result := DJUser_GetRecordChannel(userID);
    end;
end;
function  HTJS_User_SetSendSoundValue(userID : integer; value : integer) : boolean;
begin
    if CardDriver_virtual then begin
        Result := True;
    end else begin
        Result := DJUser_SetSendSoundValue(userID, value);
    end;
end;
function  HTJS_User_SetReciveSoundValue(userID : integer;value : integer) : boolean;
begin
    if CardDriver_virtual then begin
        Result := True;
    end else begin
        Result := DJUser_SetReciveSoundValue(userID,value);
    end;
end;
function  HTJS_User_GetSendSoundValue(userID : integer) : integer;
begin
    if CardDriver_virtual then begin
        Result := 1;
    end else begin
        Result := DJUser_GetSendSoundValue(userID);
    end;
end;
function  HTJS_User_GetReciveSoundValue(userID : integer) : integer;
begin
    if CardDriver_virtual then begin
        Result := 1;
    end else begin
        Result := DJUser_GetReciveSoundValue(userID);
    end;
end;
function  HTJS_User_GetPreStopDialToNowTime(userID : integer) : WORD;  
begin
    if CardDriver_virtual then begin
        Result := 1;
    end else begin
        Result := DJUser_GetPreStopDialToNowTime(userID);
    end;
end;
function  HTJS_User_SearchFreeTrunkAndDial(userID : integer; pcmID : integer;  szPhoneNum : pchar; szCallerStr : pchar ) : PstnErr;  
begin
    if CardDriver_virtual then begin
        Result := 0;
    end else begin
        Result := DJUser_SearchFreeTrunkAndDial(userID, pcmID,  szPhoneNum, szCallerStr);
    end;
end;
function  HTJS_User_DialByTrunk (userID : integer;  trunkID : integer;  szPhoneNum : pchar; szCallerStr : pchar ) : boolean; 
begin
    if CardDriver_virtual then begin
        Result := True;
    end else begin
        Result := DJUser_DialByTrunk (userID,  trunkID,  szPhoneNum, szCallerStr);
    end;
end;
function  HTJS_User_GetDialTrunkID(userID : integer) : integer;  
begin
    if CardDriver_virtual then begin
        Result := 1;
    end else begin
        Result := DJUser_GetDialTrunkID(userID);
    end;
end;
function  HTJS_User_GetDialStatus(userID : integer) : DialStatus;  
begin
    if CardDriver_virtual then begin
        Result := DS_NoDial;
    end else begin
        Result := DJUser_GetDialStatus(userID);
    end;
end;
function  HTJS_User_PlayFileNew(userID:integer;FileName:PChar;Position:DWORD;Length:DWORD):integer; 
begin
    if CardDriver_virtual then begin
        Result := 1;
    end else begin
        Result := DJUser_PlayFileNew(userID,FileName,Position,Length);
    end;
end;
procedure HTJS_User_StopPlayFile(userID:integer);  
begin
    if CardDriver_virtual then begin
    end else begin
        DJUser_StopPlayFile(userID);  
    end;
end;
function HTJS_User_RecordFileNew(userID:integer;FileName:PChar;Position:DWORD;Length:DWORD):integer;
begin
    if CardDriver_virtual then begin
        Result := 1;
    end else begin
        Result := DJUser_RecordFileNew(userID,FileName,Position,Length);
    end;
end;
procedure HTJS_User_StopRecordFile(userID:integer); 
begin
    if CardDriver_virtual then begin
    end else begin
        DJUser_StopRecordFile(userID);
    end;
end;
function  HTJS_Voc_GetTotalVoiceChannel : integer;  
begin
    if CardDriver_virtual then begin
        Result := 1;
    end else begin
        Result := DJVoc_GetTotalVoiceChannel;
    end;
end;
function  HTJS_Voc_VoiceStart(voiceChannelID : integer;   voiceResHandle : integer;     voiceResSize : LongInt;        voiceResOffset : LongInt;     voiceResType : VoiceResourcesType;          voiceOpType : VoiceOperatorType) : integer;      
begin
    if CardDriver_virtual then begin
        Result := 1;
    end else begin
        Result := DJVoc_VoiceStart(voiceChannelID,voiceResHandle,voiceResSize,voiceResOffset,voiceResType,voiceOpType);
    end;
end;
function  HTJS_Voc_PlayNextVoice(voiceChannelID : integer;  	     voiceResHandle : integer;   			     voiceResSize : LongInt;                                voiceResOffset : LongInt) : integer;              
begin
    if CardDriver_virtual then begin
        Result := 1;
    end else begin
        Result := DJVoc_PlayNextVoice(voiceChannelID,voiceResHandle,voiceResSize,voiceResOffset)
    end;
end;
function  HTJS_Voc_FromHeadRePlay(voiceID : integer) : integer;  
begin
    if CardDriver_virtual then begin
        Result := 1;
    end else begin
        Result := DJVoc_FromHeadRePlay(voiceID);  
    end;
end;
function  HTJS_Voc_PlayPromptFile(voiceID : integer;voiceName : pchar) : boolean;  
begin
    if CardDriver_virtual then begin
        Result := True;
    end else begin
        Result := DJVoc_PlayPromptFile(voiceID,voiceName);
    end;
end;
function  HTJS_Voc_LoopPlayPromptFile( voiceID : integer;voiceName : pchar) : boolean;  
begin
    if CardDriver_virtual then begin
        Result := True;
    end else begin
        Result := DJVoc_LoopPlayPromptFile(voiceID,voiceName);  
    end;
end;
function  HTJS_Voc_PlayPromptID(voiceID : integer;voiceResID : integer) : boolean;  
begin
    if CardDriver_virtual then begin
        Result := True;
    end else begin
        Result := DJVoc_PlayPromptID(voiceID,voiceResID);  
    end;
end;
function  HTJS_Voc_LoopPlayPromptID(voiceID : integer;voiceResID : integer) : boolean;  
begin
    if CardDriver_virtual then begin
        Result := True;
    end else begin
        Result := DJVoc_LoopPlayPromptID(voiceID,voiceResID);  
    end;
end;
function  HTJS_Voc_PauseChannelVoiceOp(voiceID : integer) : boolean;  
begin
    if CardDriver_virtual then begin
        Result := True;
    end else begin
        Result := DJVoc_PauseChannelVoiceOp(voiceID);  
    end;
end;
function  HTJS_Voc_ContinueChannelVoiceOp(voiceID : integer) : boolean;  
begin
    if CardDriver_virtual then begin
        Result := True;
    end else begin
        Result := DJVoc_ContinueChannelVoiceOp(voiceID); 
    end;
end;
function  HTJS_Voc_CheckChannelVoicePause(voiceID : integer) : boolean;  
begin
    if CardDriver_virtual then begin
        Result := True;
    end else begin
        Result := DJVoc_CheckChannelVoicePause(voiceID);  
    end;
end;
function  HTJS_Voc_VoiceStop(voiceChannelID : integer) : boolean;  
begin
    if CardDriver_virtual then begin
        Result := True;
    end else begin
        Result := DJVoc_VoiceStop(voiceChannelID);  
    end;
end;
function  HTJS_Voc_CheckVoiceEnd(voiceChannelID : integer) : boolean;  
begin
    if CardDriver_virtual then begin
        Result := True;
    end else begin
        Result := DJVoc_CheckVoiceEnd(voiceChannelID);  
    end;
end;
function  HTJS_Voc_CheckVoiceChannelOp(voiceChannelID : integer) : boolean;  
begin
    if CardDriver_virtual then begin
        Result := True;
    end else begin
        Result := DJVoc_CheckVoiceChannelOp(voiceChannelID);  
    end;
end;
function  HTJS_Voc_GetVoiceDataLength(voiceChannelID : integer) : longint;  
begin
    if CardDriver_virtual then begin
        Result := 1;
    end else begin
        Result := DJVoc_GetVoiceDataLength(voiceChannelID);
    end;
end;
function  HTJS_Voc_GetVoiceHandle(voiceChannelID : integer) : integer;  
begin
    if CardDriver_virtual then begin
        Result := 1;
    end else begin
        Result := DJVoc_GetVoiceHandle(voiceChannelID); 
    end;
end;
function  HTJS_Voc_GetVoiceErr(voiceID : integer) : integer;  
begin
    if CardDriver_virtual then begin
        Result := 1;
    end else begin
        Result := DJVoc_GetVoiceErr(voiceID);  
    end;
end;
function  HTJS_Voc_GetVoiceResourceType(voiceID : integer) : VoiceResourcesType;  
begin
    if CardDriver_virtual then begin
        Result := Res_NoRes;
    end else begin
        Result := DJVoc_GetVoiceResourceType(voiceID); 
    end;
end;
function  HTJS_Voc_GetVoiceOperateType (voiceID : integer) : VoiceOperatorType;  
begin
    if CardDriver_virtual then begin
        Result := OP_NoOperator;
    end else begin
        Result := DJVoc_GetVoiceOperateType(voiceID);  
    end;
end;
function  HTJS_Voc_GetPromptInfoByName(voiceName : pchar;         			var voiceHandle : integer;       			var voiceSize : LongInt;       			var voiceOffset : LongInt) : boolean;                 
begin
    if CardDriver_virtual then begin
        Result := True;
    end else begin
        Result := DJVoc_GetPromptInfoByName(voiceName,voiceHandle,voiceSize,voiceOffset);                 
    end;
end;
function  HTJS_Voc_GetPromptInfoByHandle(voiceResID : integer;  				var voiceHandle  : Integer;				var voiceSize : LongInt;				var voiceOffset : LongInt) : boolean;            
begin
    if CardDriver_virtual then begin
        Result := True;
    end else begin
        Result := DJVoc_GetPromptInfoByHandle(voiceResID,voiceHandle,voiceSize,voiceOffset);
    end;
end;
function  HTJS_Voc_SearchFreeVoiceChannelForPlay  : integer;  
begin
    if CardDriver_virtual then begin
        Result := 1;
    end else begin
        Result := DJVoc_SearchFreeVoiceChannelForPlay;
    end;
end;
function  HTJS_Voc_SearchFreeVoiceChannelForRecord : integer;  
begin
    if CardDriver_virtual then begin
        Result := 1;
    end else begin
        Result := DJVoc_SearchFreeVoiceChannelForRecord;
    end;
end;
function  HTJS_Voc_GetRecordTrunkID (voiceChannelID : integer) : integer;  
begin
    if CardDriver_virtual then begin
        Result := 1;
    end else begin
        Result := DJVoc_GetRecordTrunkID (voiceChannelID);
    end;
end;
function  HTJS_Voc_GetRecordUserID	(voiceChannelID : integer) : integer;  
begin
    if CardDriver_virtual then begin
        Result := 1;
    end else begin
        Result := DJVoc_GetRecordUserID	(voiceChannelID);
    end;
end;
function  HTJS_Exg_SetListenUserToUser(trgUserID: integer;srcUserID : integer): boolean;  
begin
    if CardDriver_virtual then begin
        Result := True;
    end else begin
        Result := DJExg_SetListenUserToUser(trgUserID,srcUserID);
    end;
end;
function  HTJS_Exg_ClearListenUserFromUser(trgUserID : integer): boolean;  
begin
    if CardDriver_virtual then begin
        Result := True;
    end else begin
        Result := DJExg_ClearListenUserFromUser(trgUserID);
    end;
end;
function  HTJS_Exg_SetListenUserToTrunk( userID : integer;trunkID : integer): boolean;  
begin
    if CardDriver_virtual then begin
        Result := True;
    end else begin
        Result := DJExg_SetListenUserToTrunk(userID,trunkID,);
    end;
end;
function  HTJS_Exg_ClearListenUserFromTrunk( userID : integer): boolean;  
begin
    if CardDriver_virtual then begin
        Result := True;
    end else begin
        Result := DJExg_ClearListenUserFromTrunk(userID);
    end;
end;
function  HTJS_Exg_SetListenTrunkToUser(trunkID : integer; userID : integer): boolean;  
begin
    if CardDriver_virtual then begin
        Result := True;
    end else begin
        Result := DJExg_SetListenTrunkToUser(trunkID,userID);
    end;
end;
function  HTJS_Exg_ClearListenTrunkFromUser( trunkID : integer): boolean;  
begin
    if CardDriver_virtual then begin
        Result := True;
    end else begin
        Result := DJExg_ClearListenTrunkFromUser(trunkID);
    end;
end;
function  HTJS_Exg_SetListenTrunkToTrunk( trgTrunkID : integer;srcTrunkID : integer): boolean;  
begin
    if CardDriver_virtual then begin
        Result := True;
    end else begin
        Result := DJExg_SetListenTrunkToTrunk(trgTrunkID,srcTrunkID);
    end;
end;
function  HTJS_Exg_ClearListenTrunkFromTrunk( trgTrunkID : integer): boolean;  
begin
    if CardDriver_virtual then begin
        Result := True;
    end else begin
        Result := DJExg_ClearListenTrunkFromTrunk(trgTrunkID);
    end;
end;
function  HTJS_Exg_SetLinkTrunkAndUser( trunkID : integer;userID : integer): boolean;  
begin
    if CardDriver_virtual then begin
        Result := True;
    end else begin
        Result := DJExg_SetLinkTrunkAndUser(trunkID,userID);
    end;
end;
function  HTJS_Exg_ClearLinkTrunkAndUserByTrunk(trunkID : integer): boolean;  
begin
    if CardDriver_virtual then begin
        Result := True;
    end else begin
        Result := DJExg_ClearLinkTrunkAndUserByTrunk(trunkID);
    end;
end;
function  HTJS_Exg_ClearLinkTrunkAndUserByUser( userID : integer): boolean;  
begin
    if CardDriver_virtual then begin
        Result := True;
    end else begin
        Result := DJExg_ClearLinkTrunkAndUserByUser(userID);
    end;
end;
function  HTJS_Exg_SetLinkTrunkAndTrunk(trgTrunkID : integer;srcTrunkID : integer): boolean;  
begin
    if CardDriver_virtual then begin
        Result := True;
    end else begin
        Result := DJExg_SetLinkTrunkAndTrunk(trgTrunkID,srcTrunkID);
    end;
end;
function  HTJS_Exg_ClearLinkTrunkAndTrunk (trunkID : integer): boolean;  
begin
    if CardDriver_virtual then begin
        Result := True;
    end else begin
        Result := DJExg_ClearLinkTrunkAndTrunk(trunkID);
    end;
end;
function  HTJS_Exg_LinkUserAndUser(trgUserID : integer; srcUserID : integer): boolean;  
begin
    if CardDriver_virtual then begin
        Result := True;
    end else begin
        Result := DJExg_LinkUserAndUser(trgUserID,srcUserID);
    end;
end;
function  HTJS_Exg_ClearLinkUserAndUser(userID : integer): boolean;  
begin
    if CardDriver_virtual then begin
        Result := True;
    end else begin
        Result := DJExg_ClearLinkUserAndUser(userID);
    end;
end;
function  HTJS_Exg_SetLinkPlayVoiceToTrunk	(trunkID : integer;voiceID : integer): boolean;  
begin
    if CardDriver_virtual then begin
        Result := True;
    end else begin
        Result := DJExg_SetLinkPlayVoiceToTrunk	(trunkID,voiceID);
    end;
end;
procedure  HTJS_Exg_VoiceToTrunk_SetParam (conn : boolean);  
begin
    if CardDriver_virtual then begin
    end else begin
        DJExg_VoiceToTrunk_SetParam(conn);
    end;
end;
function  HTJS_Exg_ClearLinkPlayVoiceFromTrunk(trunkID : integer): boolean;  
begin
    if CardDriver_virtual then begin
        Result := True;
    end else begin
        Result := DJExg_ClearLinkPlayVoiceFromTrunk(trunkID);
    end;
end;
function  HTJS_Exg_SetLinkPlayVoiceToUser(userID : integer;voiceID : integer): boolean;  
begin
    if CardDriver_virtual then begin
        Result := True;
    end else begin
        Result := DJExg_SetLinkPlayVoiceToUser(userID,voiceID);
    end;
end;
function  HTJS_Exg_ClearLinkPlayVoiceFromUser(userID : integer): boolean;  
begin
    if CardDriver_virtual then begin
        Result := True;
    end else begin
        Result := DJExg_ClearLinkPlayVoiceFromUser(userID);
    end;
end;
function  HTJS_Exg_SetLinkRecordVoiceToUser(userID : integer;voiceID : integer): boolean;  
begin
    if CardDriver_virtual then begin
        Result := True;
    end else begin
        Result := DJExg_SetLinkRecordVoiceToUser(userID,voiceID);
    end;
end;
function  HTJS_Exg_ClearLinkRecordVoiceFromUser(userID : integer): boolean;  
begin
    if CardDriver_virtual then begin
        Result := True;
    end else begin
        Result := DJExg_ClearLinkRecordVoiceFromUser(userID);
    end;
end;
function  HTJS_Exg_SetLinkRecordVoiceToTrunk (trunkID : integer;voiceID : integer): boolean;  
begin
    if CardDriver_virtual then begin
        Result := True;
    end else begin
        Result := DJExg_SetLinkRecordVoiceToTrunk (trunkID,voiceID);
    end;
end;
function  HTJS_Exg_ClearLinkRecordVoiceFromTrunk(trunkID : integer): boolean;  
begin
    if CardDriver_virtual then begin
        Result := True;
    end else begin
        Result := DJExg_ClearLinkRecordVoiceFromTrunk(trunkID);
    end;
end;
function  HTJS_Sys_GetPcmNum : integer;  
begin
    if CardDriver_virtual then begin
        Result := 60;
    end else begin
        Result := DJSys_GetPcmNum;
    end;
end;
function  HTJS_Sys_GetImpExpNum(ImpExpType : integer) : WORD;  
begin
    if CardDriver_virtual then begin
        Result := 1;
    end else begin
        Result := DJSys_GetImpExpNum(ImpExpType);
    end;
end;
function  HTJS_Sys_GetTotalImpExpNum( ImpExpType : integer) : LongInt;  
begin
    if CardDriver_virtual then begin
        Result := 1;
    end else begin
        Result := DJSys_GetTotalImpExpNum(ImpExpType);
    end;
end;
function  HTJS_Sys_GetPcmImpExpNum( pcmID : integer;ImpExpType : integer) : WORD;  
begin
    if CardDriver_virtual then begin
        Result := 1;
    end else begin
        Result := DJSys_GetPcmImpExpNum(pcmID,ImpExpType);
    end;
end;
function  HTJS_Sys_GetPcmTotalImpExpNum( pcmID : integer;ImpExpType : integer) : LongInt;  
begin
    if CardDriver_virtual then begin
        Result := 1;
    end else begin
        Result := DJSys_GetPcmTotalImpExpNum(pcmID,ImpExpType);
    end;
end;
function  HTJS_Sys_GetPcmStatus(pcmID : integer) : BYTE;  
begin
    if CardDriver_virtual then begin
        Result := 1;
    end else begin
        Result := DJSys_GetPcmStatus(pcmID);
    end;
end;
procedure HTJS_Sys_StopWarning;  
begin
    if CardDriver_virtual then begin
    end else begin
	DJSys_StopWarning
    end;
end;
procedure HTJS_Sys_EnableWarn;
begin
    if CardDriver_virtual then begin
    end else begin
        DJSys_EnableWarn;
    end;
end;
procedure HTJS_Sys_DisableWarn;  
begin
    if CardDriver_virtual then begin
    end else begin
	DJSys_DisableWarn;
    end;
end;
procedure HTJS_Sys_GetSysRunTime(var hours : LongInt; var minutes : BYTE;            var seconds : BYTE);  
begin
    if CardDriver_virtual then begin
    end else begin
	DJSys_GetSysRunTime(hours,minutes,seconds);  
    end;
end;
procedure HTJS_Sys_GetSysTime(var hours : BYTE; var minutes : BYTE;          var seconds : BYTE);  
begin
    if CardDriver_virtual then begin
    end else begin
	DJSys_GetSysTime(hours,minutes,seconds);  
    end;
end;
procedure HTJS_Sys_SetSysTime(hours :  BYTE; minutes :  BYTE;          seconds :  BYTE);  
begin
    if CardDriver_virtual then begin
    end else begin
	DJSys_SetSysTime(hours,minutes,seconds);  
    end;
end;
function  HTJS_Sys_GetErrCode : PstnErr;
begin
    if CardDriver_virtual then begin
        Result := 0;
    end else begin
        Result := DJSys_GetErrCode;
    end;
end;
function  HTJS_Sys_FreeMfcNum : integer;  
begin
    if CardDriver_virtual then begin
        Result := 1;
    end else begin
        Result := DJSys_FreeMfcNum;
    end;
end;
function  HTJS_Sys_FreePlayVoiceNum : integer;  
begin
    if CardDriver_virtual then begin
        Result := 1;
    end else begin
        Result := DJSys_FreePlayVoiceNum;
    end;
end;
function  HTJS_Sys_FreeDtmfNum : integer;  
begin
    if CardDriver_virtual then begin
        Result := 1;
    end else begin
        Result := DJSys_FreeDtmfNum;
    end;
end;
function  HTJS_Sys_GetInstallDir : pchar;  
begin
    if CardDriver_virtual then begin
        Result := '1';
    end else begin
        Result := DJSys_GetInstallDir;
    end;
end;
procedure  HTJS_Sys_AutoApplyDtmf( DtmfMode : integer);  
begin
    if CardDriver_virtual then begin
    end else begin
        DJSys_AutoApplyDtmf(DtmfMode);
    end;
end;
function  HTJS_Sys_IsAutoApplyDtmf : boolean;  
begin
    if CardDriver_virtual then begin
        Result := True;
    end else begin
        Result := DJSys_IsAutoApplyDtmf;
    end;
end;
procedure  HTJS_Sys_EnableAutoKB;  
begin
    if CardDriver_virtual then begin
    end else begin
	DJSys_EnableAutoKB;
    end;
end;
procedure  HTJS_Sys_DisableAutoKB;  
begin
    if CardDriver_virtual then begin
    end else begin
	DJSys_DisableAutoKB;
    end;
end;
function  HTJS_Voc_PlayFile(trunkID : integer; FileName : pchar) : boolean;  
begin
    if CardDriver_virtual then begin
        Result := True;
    end else begin
        Result := DJVoc_PlayFile(trunkID,FileName);
    end;
end;
procedure HTJS_Voc_StopPlayFile(trunkID : integer);  
begin
    if CardDriver_virtual then begin
    end else begin
	DJVoc_StopPlayFile(trunkID);
    end;
end;
procedure HTJS_Voc_InitIndexPlayFile(trunkID : integer);  
begin
    if CardDriver_virtual then begin
    end else begin
	DJVoc_InitIndexPlayFile(trunkID);
    end;
end;
function  HTJS_Voc_AddIndexPlayFile(trunkID : integer; FileName : pchar) : boolean;  
begin
    if CardDriver_virtual then begin
        Result := True;
    end else begin
        Result := DJVoc_AddIndexPlayFile(trunkID,FileName);
    end;
end;
function  HTJS_Voc_StartIndexPlayFile(trunkID : integer) : boolean;  
begin
    if CardDriver_virtual then begin
        Result := True;
    end else begin
        Result := DJVoc_StartIndexPlayFile(trunkID);
    end;
end;
procedure HTJS_Voc_StopIndexPlayFile(trunkID : integer);  
begin
    if CardDriver_virtual then begin
    end else begin
	DJVoc_StopIndexPlayFile(trunkID);
    end;
end;
function  HTJS_Voc_CheckIndexPlayEnd(trunkID : integer) : boolean;  
begin
    if CardDriver_virtual then begin
        Result := True;
    end else begin
        Result := DJVoc_CheckIndexPlayEnd(trunkID);
    end;
end;
function  HTJS_Voc_RecordFile(trunkID : integer; FileName : pchar;              RecordLen : LongInt) : boolean;  
begin
    if CardDriver_virtual then begin
        Result := True;
    end else begin
        Result := DJVoc_RecordFile(trunkID,FileName,RecordLen);
    end;
end;
procedure HTJS_Voc_StopRecordFile(trunkID : integer);  
begin
    if CardDriver_virtual then begin
    end else begin
	DJVoc_StopRecordFile(trunkID);
    end;
end;
function HTJS_Voc_RecordFileNew(trunkID:integer;FileName:PChar;Position:DWORD;Length:DWORD):integer; 
begin
    if CardDriver_virtual then begin
        Result := 1;
    end else begin
        Result := DJVoc_RecordFileNew(trunkID,FileName,Position,Length);
    end;
end;
function HTJS_Voc_PlayFileNew(trunkID:integer;FileName:PChar;Position:DWORD;Length:DWORD):integer; 
begin
    if CardDriver_virtual then begin
        Result := 1;
    end else begin
        Result := DJVoc_PlayFileNew(trunkID,FileName,Position,Length);
    end;
end;
procedure HTJS_Trk_GetMfcCodeA( trunkID : integer; MfcCode : pchar);  
begin
    if CardDriver_virtual then begin
    end else begin
	DJTrk_GetMfcCodeA(trunkID,MfcCode);
    end;
end;
procedure HTJS_Trk_GetHostCodeA( trunkID : integer; HostCode : pchar );  
begin
    if CardDriver_virtual then begin
    end else begin
	DJTrk_GetHostCodeA(trunkID,HostCode);
    end;
end;
procedure HTJS_Trk_GetDtmfCodeA( trunkID : integer; DtmfCode : pchar);  
begin
    if CardDriver_virtual then begin
    end else begin
        DJTrk_GetDtmfCodeA(trunkID,DtmfCode);
    end;
end;
function HTJS_Voc_SFVC_ForPlay(trunkid:integer):integer;
begin
    if CardDriver_virtual then begin
        Result := 1;
    end else begin
        Result := DJVoc_SFVC_ForPlay(trunkid);
    end;
end;
function HTJS_Voc_SFVC_ForRecord(trunkid:integer):integer;
begin
    if CardDriver_virtual then begin
        Result := 1;
    end else begin
        Result := DJVoc_SFVC_ForRecord(trunkid);
    end;
end;
function HTJS_Voc_SFVC_ForPlay_New(trunkid:integer;isfortrunk:boolean):integer;
begin
    if CardDriver_virtual then begin
        Result := 1;
    end else begin
        Result := DJVoc_SFVC_ForPlay_New(trunkid,isfortrunk);
    end;
end;
function HTJS_Conf_InitConfCard():integer;
begin
    if CardDriver_virtual then begin
        Result := 1;
    end else begin
        Result := DJConf_InitConfCard;
    end;
end;
procedure HTJS_Conf_ReleaseConfCard();
begin
    if CardDriver_virtual then begin
    end else begin
	    DJConf_ReleaseConfCard;
    end;
end;
end.
