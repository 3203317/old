unit D641XChannel;

interface

uses
  Classes, Windows, SysUtils, LightweightCTI;

type
  THackChannel = class(TAbstractChannel);

  TTrunkChannel = class(TAbstractChannel)
  private
    function PlayPromptStr(const Afilelist: string):TPlayMessageResult;
    function PlayFileDetect(const AllowBreak: Boolean):TPlayMessageResult;
    procedure PlayFileStop;
    procedure PlayFileStop2(AchannelID: Integer);
    function GetDTMFCode:string;
    function InnerSchedule(AserverType: string): Integer;
    function PlayPromptStr2(AchannelID: Integer; Afilelist: string):TPlayMessageResult;
    function PlayFileDetect2(const AllowBreak: Boolean; AchannelID: Integer):TPlayMessageResult;
    function innerDial(const Adialnum,  APrefixnum: string): integer;
  protected
  public
    Fpcm,Fchn : integer;  
    function RingDetect(const Atimes: Integer = 1): Boolean; override; // 振铃检测
    procedure OffHook; override; // 摘机
    function OffHookDetect: Boolean; override; // 内线摘机检测
    procedure HangUp; override; // 被叫挂机
    function HangUpDetect: Boolean; override; // 被叫挂机检测
    procedure StopRing;override;
    procedure ResetChannel; override; // 重置通道
    function Schedule(AserverType: string; Afilelist: string): Integer; override;//获取最少接电话的坐席号

    // 主叫或被叫函数
    function GetCallerNumber: string; override; // 获取主叫号码
    function GetCalleeNumber: string; override; // 获取被叫号码

    // 拨号及放音函数
    function Dial(const Adialnum: string; const APrefixnum: string = ''):
      TSignalType; override; // 向外拨号
    function DtmfHit: Boolean; override; // 按键检测
    function GetDTMF(const Alength: Integer; Asuffix: string = '#';
      const Atimeout: Integer = 0): string; override; // 取DTMF号码
    procedure ClearDTMF; override; // 清空DTMF缓存
    function PlayMessage(const Atxt: string; const AllowBreak: Boolean = True):
      TPlayMessageResult; override; // 播放一段文字
    function PlayNumber(const Atxt: string; const AllowBreak: Boolean = True;
      const APlayType: TPlayNumberType = pntMessage): TPlayMessageResult;
      override; // 播放数字
    procedure PlayVoice(AvoiceType: integer);override;
    function PlayFile(const Afilelist, AfileExt: string;
      const AllowBreak: Boolean = True): TPlayMessageResult; override; // 播放语音文件   
    function PlayFileToTrunkAndUser(const Afilelist, AfileExt: string;
      const AllowBreak: Boolean = True; AchannelID: Integer = -1): TPlayMessageResult; override; // 给双方播放音乐
    function PlayToFile(const Atxt: string; const Afilename: string;
      const AVoiceResource: TVoiceResourceType = vrtStandard): Boolean;
      override; // 播放到文件
    function SendMessage(const Atxt: string): Boolean; override; // 发送信息

    // 录音函数
    function RecordFile(const Afilename: string; ALengthTimes: Integer = 60;
      AllowBreak: Boolean = True): TRecordFileResult; override; // 录音

    // 通道连通函数
    function LinkTo(const AChannelID: Integer = -1;
      const AChannelType: TChannelType = ctUser): Integer; override; // 连接到某一通道
    function UnLink(const AChannelID: Integer = -1;
      const AChannelType: TChannelType = ctUser; ChannelToChannel:boolean = false): Integer; override; // 断开与某通道的连接
    function UnLinkAll: Boolean; override; // 断开与所有通道的连接
    function ListenTo(const AChannelID: Integer): Integer; override; // 监听某一通道
    procedure RecordFile_stop;
    function DailResultDetect : integer;
  end;
  
  TUserChannel = class(TAbstractChannel)
  private
    function PlayPromptStr(const Afilelist: string):TPlayMessageResult;       
    function PlayFileDetect(const AllowBreak: Boolean):TPlayMessageResult;
    procedure PlayFileStop;
    function GetDTMFCode:string;
  protected
  public
    localphone : string;//= '037163329079';
    FserverNum : integer;
    function RingDetect(const Atimes: Integer = 1): Boolean; override; // 振铃检测
    procedure StopRing;override;
    procedure OffHook; override; // 摘机
    function OffHookDetect: Boolean; override; // 内线摘机检测
    procedure HangUp; override; // 被叫挂机
    function HangUpDetect: Boolean; override; // 被叫挂机检测
    procedure ResetChannel; override; // 重置通道

    // 主叫或被叫函数
    function GetCallerNumber: string; override; // 获取主叫号码
    function GetCalleeNumber: string; override; // 获取被叫号码

    // 拨号及放音函数
    function Dial(const Adialnum: string; const APrefixnum: string = ''):
      TSignalType; override; // 向外拨号
    function DtmfHit: Boolean; override; // 按键检测
    function GetDTMF(const Alength: Integer; Asuffix: string = '#';
      const Atimeout: Integer = 0): string; override; // 取DTMF号码
    procedure ClearDTMF; override; // 清空DTMF缓存
    function PlayMessage(const Atxt: string; const AllowBreak: Boolean = True):
      TPlayMessageResult; override; // 播放一段文字
    function PlayNumber(const Atxt: string; const AllowBreak: Boolean = True;
      const APlayType: TPlayNumberType = pntMessage): TPlayMessageResult;
      override; // 播放数字
    procedure PlayVoice(AvoiceType: integer);override;
    function PlayFile(const Afilelist, AfileExt: string;
      const AllowBreak: Boolean = True): TPlayMessageResult; override; // 播放语音文件
    function PlayToFile(const Atxt: string; const Afilename: string;
      const AVoiceResource: TVoiceResourceType = vrtStandard): Boolean;  override;
    function PlayFileToTrunkAndUser(const Afilelist, AfileExt: string;
      const AllowBreak: Boolean = True; AchannelID: Integer = -1): TPlayMessageResult; // 给双方播放音乐      
      override; // 播放到文件
    function SendMessage(const Atxt: string): Boolean; override; // 发送信息

    // 录音函数
    function RecordFile(const Afilename: string; ALengthTimes: Integer = 60;
      AllowBreak: Boolean = True): TRecordFileResult; override; // 录音

    // 通道连通函数
    function LinkTo(const AChannelID: Integer = -1;
      const AChannelType: TChannelType = ctUser): Integer; override; // 连接到某一通道
    function UnLink(const AChannelID: Integer = -1;
      const AChannelType: TChannelType = ctUser; ChannelToChannel:boolean = false): Integer; override; // 断开与某通道的连接
    function UnLinkAll: Boolean; override; // 断开与所有通道的连接
    function ListenTo(const AChannelID: Integer): Integer; override; // 监听某一通道  
    function Schedule(AserverType: string;Afilelist: string): Integer;override;//获取最少接电话的坐席号
  end;
implementation

uses
  GlobalConstants, HTJS_Tce132, HTJS_ISDNDLL, Math;

const
  D641XCS = 'D641XCS'; // 临界区字符串

procedure TTrunkChannel.ClearDTMF;
begin
  inherited;  FDtmf := '';
  FChannelManager.EnterCS(D641XCS);
  try
    {$IFDEF CTI_DEBUG} ChannelLog('DEBUG# 清除按键缓冲区中的键按信息'); {$ENDIF}
    HTJS_Trk_InitDtmfBuf (ChannelID);
  finally
    FChannelManager.LeaveCS(D641XCS);
  end;
end;

procedure TTrunkchannel.StopRing;
begin       
  FChannelManager.EnterCS(D641XCS);
  try
    //
  finally
    FChannelManager.LeaveCS(D641XCS);
  end;
end;


function TTrunkChannel.DailResultDetect : integer;
begin
  FChannelManager.EnterCS(D641XCS);
  try
    Result :=  HTJS_ISDN_GetCalloutResult(fpcm,fchn);
  finally
    FChannelManager.LeaveCS(D641XCS);
  end;
end;
function TTrunkChannel.innerDial(const Adialnum,  APrefixnum: string): integer;
begin
  FChannelManager.EnterCS(D641XCS);
  try
    Result :=  HTJS_ISDN_Callout(Fpcm,Fchn,PChar(Adialnum), PChar(APrefixnum),PChar(GClocalphone), '');
  finally
    FChannelManager.LeaveCS(D641XCS);
  end;
end;
{-------------------------------------------------------------------------------
  Procedure: TTrunkChannel.Dial
  Author:    hxn
  DateTime:  2007.08.30
  Arguments: Adialnum,  APrefixnum: string; AchannelID:integer
  Result:    TSignalType
  Popose:    拨号
-------------------------------------------------------------------------------}
function TTrunkChannel.Dial(const Adialnum,  APrefixnum: string): TSignalType;
begin  Result := stNoResult;
  if Assigned(FOnCallOut) then FOnCallOut(Self, iif(APrefixnum <> '', APrefixnum + ',' + Adialnum, Adialnum));
  innerDial(Adialnum,  APrefixnum);
  Status := csDial; ChannelLog('系统开发拨号，呼出号码为：' + Adialnum);
end;

function TTrunkChannel.DtmfHit: Boolean;
begin
  FChannelManager.EnterCS(D641XCS);
  try
    Result := HTJS_Trk_GetReciveDtmfNum(ChannelID)>0;
  finally
    FChannelManager.LeaveCS(D641XCS);
  end;
end;

function TTrunkChannel.GetCalleeNumber: string;
begin
  Result := '400 811 0006';
end;

function TTrunkChannel.GetCallerNumber: string;
var
  AcallerID: PAnsiChar;   Atmp: string;
begin
  {$IFDEF CTI_DEBUG}   ChannelLog('开始获取主叫号码……');  {$ENDIF}
  Result := ''; AcallerID := StrAlloc(32 * SizeOf(Char));
  FChannelManager.EnterCS(D641XCS);
  try
    HTJS_ISDN_GetCallerNumber(Fpcm,Fchn,AcallerID);//获取主叫号码
    Atmp := AcallerID;
    HTJS_ISDN_GetCallerSubAddr(Fpcm,Fchn,AcallerID);//获取主叫子地址号码
    Atmp := Atmp + AcallerID;
    FCallNumber := Atmp;  Result := Atmp; ChannelLog('获取主叫号码为：' + Result);
    if Assigned(FOnCallIn) then FOnCallIn(Self, Result);
    FCallSession.PhoneNumber := FCallNumber;
  finally
    StrDispose(AcallerID);
    FChannelManager.LeaveCS(D641XCS);
  end;
end;

function TTrunkChannel.GetDTMFCode:string;
begin
  FChannelManager.EnterCS(D641XCS);
  try Result := 'z';
    if (HTJS_ISDN_GetChnState(Fpcm,Fchn) <> CH_WAIT_APP_FREE) and (HTJS_ISDN_GetChnState(Fpcm,Fchn) <> CH_WAIT_RELEASE) then
      Result := HTJS_Trk_GetDtmfCode(ChannelID);
  finally
    FChannelManager.LeaveCS(D641XCS);
  end;
end;

function TTrunkChannel.GetDTMF(const Alength: Integer; Asuffix: string;
  const Atimeout: Integer): string;
var
  K: Integer; T: Cardinal;
begin
  Result := ''; K := iif(Atimeout = 0, TimeOut, Atimeout); // 设置按键的超时值
  Status  := csGetDtmf; T := GetTickCount;
  {$IFDEF CTI_DEBUG} Log('准备获取对方按键……'); {$ENDIF}
  while (length(FDtmf)<Alength) and (GetTickCount - T < Cardinal(K * GCTimeOutRate)) do begin
    FDtmf := GetDTMFCode;
    if FDtmf = 'z' then break;
  end;
  if  FDtmf <> 'z' then
    if length(FDtmf)<Alength then   FDtmf := ''
    else FDtmf := copy(FDtmf,1,Alength);
  Result := FDtmf; ChannelLog('获取对方按键完成，对方按键为：' + FDtmf);
  ClearDTMF;
end;

procedure TTrunkChannel.HangUp;
begin
  FChannelManager.EnterCS(D641XCS);
  try
    HTJS_ISDN_SetChnState(Fpcm,Fchn,CH_SET_DISCONNECT,0);
    HTJS_ISDN_SetChnState(Fpcm,Fchn,CH_SET_FREE,0);//设置通道状态为空闲
    FCallSession.EndTime:=now;
    if FCallSession.SesstionType <> stFree then FCallSession.StoreSession;
    FCallSession.SesstionType := stFree;
  finally
    FChannelManager.LeaveCS(D641XCS);
  end;
end;

function TTrunkChannel.HangUpDetect: Boolean;
begin
  FChannelManager.EnterCS(D641XCS);
  try
    //Result := HTJS_ISDN_GetChnState(Fpcm,Fchn) = CH_SET_DISCONNECT
    Result := (HTJS_ISDN_GetChnState(Fpcm,Fchn) = CH_WAIT_APP_FREE) or (HTJS_ISDN_GetChnState(Fpcm,Fchn) = CH_WAIT_RELEASE);
    if Result then begin
      FCallSession.EndTime:=now;
      if FCallSession.SesstionType <> stFree then FCallSession.StoreSession;
      FCallSession.SesstionType := stFree;
    end;
  finally
    FChannelManager.LeaveCS(D641XCS);
  end;
end;

function TTrunkChannel.Schedule(AserverType: string;Afilelist: string): Integer;
var PlayResult : TPlayMessageResult;
begin
  Result := InnerSchedule(AserverType);
  if Result <> -1 then exit;
  PlayResult := PlayPromptStr(Afilelist);
  if PlayResult <> pmrHangup then
    while  (PlayFileDetect(false) = pmrPlaying) do begin
     Result := InnerSchedule(AserverType);
     if Result = -1 then sleep(channel_sleep_time) else break;
    end;
  PlayFileStop;
end;
function TTrunkChannel.InnerSchedule(AserverType: string): Integer;//获取最少接电话的坐席号
var
  i,AchannelID,max:integer;  Achnl: TAbstractChannel;
begin
  FChannelManager.EnterCS(D641XCS);
  try
    AchannelID := -1;
    max := 1000;
    for i:= 0 to ChannelManager.userChannels.Count-1 do
      begin
        Achnl := TAbstractChannel(ChannelManager.userChannels[i]);
        if (Achnl.Status = csFree) and (pos(AserverType+',',Achnl.FserverType+',') > 0) and (Achnl.FserverNum < max) and (Achnl.HangUpDetect) and (Achnl.RingDetect() = false) then
          begin
            AchannelID := i;
            max := Achnl.FserverNum;
          end;
      end;
      
    Status := csWait;
    if AchannelID <> -1 then
      begin
        TAbstractChannel(ChannelManager.userChannels[AchannelID]).Status := csLink;
        //TAbstractChannel(ChannelManager.userChannels[AchannelID]).Terminate;
      end;

    Result := AchannelID;
  finally
    FChannelManager.LeaveCS(D641XCS);
  end;
end;

function TTrunkChannel.LinkTo(const AChannelID: Integer; const AChannelType: TChannelType): Integer;
var Achnl : TAbstractChannel;
begin
  FChannelManager.EnterCS(D641XCS);
  try
    Result := -1; HTJS_Exg_ClearLinkPlayVoiceFromTrunk(ChannelID);
    if (AChannelID <> -1) and (AChannelType = ctUser) and HTJS_Exg_SetLinkTrunkAndUser(ChannelID,AChannelID) then begin
      Result := AChannelID; FLinkToChannel := AChannelID; FLinkToType := ctUser; 
      Achnl := ChannelManager.userChannels[AChannelID];
      TUserChannel(Achnl).FLinkToChannel := ChannelID; TUserChannel(Achnl).FLinkToType := ctTrunk;
      status := csconnect; ChannelLog('坐席和中继通话中');
      if FCallSession.SesstionType = stCallIn then begin
        FCallSession.SessionID := TUserChannel(Achnl).FCallSession.SessionID;
        FCallSession.PhoneNumber := TUserChannel(Achnl).FCallSession.PhoneNumber;
      end;
      if FCallSession.SesstionType = stCallOut then begin
        TUserChannel(Achnl).FCallSession.SessionID := FCallSession.SessionID;
        TUserChannel(Achnl).FCallSession.PhoneNumber := FCallSession.PhoneNumber;
      end;
    end;
    if (AChannelID <> -1) and (AChannelType = ctTrunk) and HTJS_Exg_SetLinkTrunkAndUser(AChannelID,ChannelID) then begin
      Result := AChannelID; FLinkToChannel := AChannelID; FLinkToType := ctTrunk;
      Achnl := ChannelManager.TruncChannels[AChannelID];
      TTrunkChannel(Achnl).FLinkToChannel := ChannelID; TTrunkChannel(Achnl).FLinkToType := ctTrunk;
      status := csconnect; ChannelLog('中继之间通话中');
      if FCallSession.SesstionType = stCallIn then begin
        FCallSession.SessionID := TTrunkChannel(Achnl).FCallSession.SessionID;
        FCallSession.PhoneNumber := TTrunkChannel(Achnl).FCallSession.PhoneNumber;
      end;
      if FCallSession.SesstionType = stCallOut then begin
        TTrunkChannel(Achnl).FCallSession.SessionID := FCallSession.SessionID;
        TTrunkChannel(Achnl).FCallSession.PhoneNumber := FCallSession.PhoneNumber;
      end;
    end;
  finally
    FChannelManager.LeaveCS(D641XCS);
  end;
end;

function TTrunkChannel.ListenTo(const AChannelID: Integer): Integer;
begin
    result :=0;
end;

procedure TTrunkChannel.OffHook;
begin
  FChannelManager.EnterCS(D641XCS);
  try
    htjs_isdn_setchnstate(Fpcm,Fchn,ch_set_connect,0);
    FCallSession.SesstionType := stCallOut; FCallSession.StartTime:=now;
    Status := csConnect;ChannelLog('中继摘机');
  finally
    FChannelManager.LeaveCS(D641XCS);
  end;
end;

function TTrunkChannel.OffHookDetect: Boolean;
begin
  FChannelManager.EnterCS(D641XCS);
  try  {$IFDEF CTI_DEBUG} ChannelLog('进行挂机检测'); {$ENDIF}
      Result := HTJS_ISDN_GetChnState(Fpcm,fchn) = ch_connect;
      if Result then begin
        FCallSession.SesstionType := stCallOut;FCallSession.StartTime:=now;
        Status := csConnect;ChannelLog('对方摘机');
      end;
      //else ChannelLog(inttostr(HTJS_ISDN_GetChnState(Fpcm,fchn)));
  finally
    FChannelManager.LeaveCS(D641XCS);
  end;
end;


function TTrunkChannel.PlayPromptStr(const Afilelist: string):TPlayMessageResult;
begin
  FChannelManager.EnterCS(D641XCS);
  try
    if Status = csHangup then Result := pmrHangup
    else begin
      Status := csPlaying; Result := pmrPlaying;
      HTJS_Trk_PlayPromptStr(ChannelID,Afilelist);
      HTJS_Trk_InitDtmfBuf (ChannelID);
    end;
  finally
    FChannelManager.LeaveCS(D641XCS);
  end;
end;


function TTrunkChannel.PlayPromptStr2(AchannelID: Integer; Afilelist: string):TPlayMessageResult;
var tmpstr: string;
begin
  FChannelManager.EnterCS(D641XCS);
  try
    if Status = csHangup then Result := pmrHangup
    else begin
      Status := csPlaying; Result := pmrPlaying;
      HTJS_Exg_ClearLinkPlayVoiceFromTrunk(ChannelID);
      AchannelID := AchannelID +1;
      tmpstr := 'd'+ IntToStr(AchannelID div 10) +',d'+ IntToStr(AchannelID mod 10) +',d6,'+ Afilelist;
      HTJS_Trk_PlayPromptStr(ChannelID,tmpstr);
      HTJS_User_PlayPromptStr(AchannelID-1,tmpstr);
      HTJS_Trk_InitDtmfBuf (ChannelID);
      HTJS_User_InitDialBuf(AchannelID-1);
    end;
  finally
    FChannelManager.LeaveCS(D641XCS);
  end;
end;

function TTrunkChannel.PlayFileDetect(const AllowBreak: Boolean):TPlayMessageResult;
var Aplaystop : boolean;
begin
  FChannelManager.EnterCS(D641XCS);
  try   Result := pmrPlaying;
    {$IFDEF CTI_DEBUG} ChannelLog('检测对方是否挂机'); {$ENDIF}
    if (HTJS_ISDN_GetChnState(Fpcm,Fchn) = CH_WAIT_APP_FREE) or (HTJS_ISDN_GetChnState(Fpcm,Fchn) = CH_WAIT_RELEASE) then begin// 检测对方是否挂机
      ChannelLog('对方挂机中断当前语音文件的播放'); Result := pmrHangup;
    end else begin
      Aplaystop := HTJS_Trk_CheckPlayPromptStrEnd(ChannelID);
      if Aplaystop then Result := pmrComplete
      else if AllowBreak and (HTJS_Trk_GetReciveDtmfNum(ChannelID)>0) then begin
        ChannelLog('对方按键停止当前语音文件的播放'); Result := pmrKeyPress;
      end;
    end;
  finally
    FChannelManager.LeaveCS(D641XCS);
  end;
end;


function TTrunkChannel.PlayFileDetect2(const AllowBreak: Boolean;AchannelID: Integer):TPlayMessageResult;
var Aplaystop,Atrunkstop,Auserstop : boolean;
begin
  FChannelManager.EnterCS(D641XCS);
  try   Result := pmrPlaying;
    {$IFDEF CTI_DEBUG} ChannelLog('检测对方是否挂机'); {$ENDIF}
    if (HTJS_ISDN_GetChnState(Fpcm,Fchn) = CH_WAIT_APP_FREE) or (HTJS_ISDN_GetChnState(Fpcm,Fchn) = CH_WAIT_RELEASE) or (HTJS_User_CheckHookOFF(AchannelID) = false) then begin// 检测对方是否挂机
      ChannelLog('双方挂机中断当前语音文件的播放'); Result := pmrHangup;
    end else begin
      //Aplaystop := HTJS_Trk_CheckPlayPromptStrEnd(ChannelID) and HTJS_User_CheckPlayPromptStrEnd(AchannelID);

      Atrunkstop := HTJS_Trk_CheckPlayPromptStrEnd(channelId);
      Auserstop :=  HTJS_User_CheckPlayPromptStrEnd(AchannelID);

      Aplaystop := Atrunkstop and Auserstop;

      if Aplaystop then Result := pmrComplete
      else if AllowBreak and (HTJS_Trk_GetReciveDtmfNum(ChannelID)>0) then begin
        ChannelLog('双方按键停止当前语音文件的播放'); Result := pmrKeyPress;
      end;
    end;
  finally
    FChannelManager.LeaveCS(D641XCS);
  end;
end;

procedure TTrunkChannel.PlayFileStop;
begin
  FChannelManager.EnterCS(D641XCS);
  try
      HTJS_Voc_StopPlayFile(ChannelID); ChannelLog('播放语音文件结束');
  finally
    FChannelManager.LeaveCS(D641XCS);
  end;
end;

procedure TTrunkChannel.PlayFileStop2(AchannelID: Integer);
begin
  FChannelManager.EnterCS(D641XCS);
  try
      HTJS_Voc_StopPlayFile(ChannelID);  
      HTJS_User_StopPlayFile(AchannelID);
      ChannelLog('播放语音文件结束');
  finally
    FChannelManager.LeaveCS(D641XCS);
  end;
end;

function TTrunkChannel.PlayFile(const Afilelist, AfileExt: string; const AllowBreak: Boolean): TPlayMessageResult;
begin
  Result := PlayPromptStr(Afilelist);
  if Result <> pmrHangup then
    while  PlayFileDetect(AllowBreak) = pmrPlaying do sleep(channel_sleep_time);
  PlayFileStop;
end;

procedure TTrunkChannel.PlayVoice(AvoiceType: Integer);
begin
  FChannelManager.EnterCS(D641XCS);
  try
    HTJS_Exg_ClearLinkPlayVoiceFromTrunk(ChannelID);
    if AvoiceType<>-1 then  HTJS_Exg_SetLinkPlayVoiceToTrunk(ChannelID,AvoiceType);
  finally   
    FChannelManager.LeaveCS(D641XCS);
  end;
end;

function TTrunkChannel.PlayFileToTrunkAndUser(const Afilelist, AfileExt: string; const AllowBreak: Boolean = True; AchannelID: Integer = -1): TPlayMessageResult; // 给双方播放音乐
var
  Achnl :  TAbstractChannel;
begin
  Achnl := TAbstractChannel(ChannelManager.userChannels[AchannelID]);
  Achnl.FserverNum := Achnl.FserverNum + 1;
  Result := PlayPromptStr2(AchannelID,Afilelist);
  if Result <> pmrHangup then
    while  PlayFileDetect2(AllowBreak,AchannelID) = pmrPlaying do sleep(channel_sleep_time);
  PlayFileStop2(AchannelID);
end;

function TTrunkChannel.PlayMessage(const Atxt:string; const AllowBreak: Boolean): TPlayMessageResult;
begin
  if not Assigned(FChannelManager.TTSEngine) then
    raise Exception.Create('TTS not initialization, can not PlayMessage.');
  Result := FChannelManager.TTSEngine.PlayMessage(Self, Atxt, AllowBreak, pntMessage, VoiceResource);
end;

function TTrunkChannel.PlayNumber(const Atxt: string;
  const AllowBreak: Boolean;
  const APlayType: TPlayNumberType): TPlayMessageResult;
begin
  if not Assigned(FChannelManager.TTSEngine) then raise Exception.Create('TTS not initialization, can not PlayMessage.');
  Result := FChannelManager.TTSEngine.PlayMessage(Self, Atxt, AllowBreak, APlayType, VoiceResource);
end;

function TTrunkChannel.PlayToFile(const Atxt, Afilename: string;
  const AVoiceResource: TVoiceResourceType = vrtStandard): Boolean;
begin
  if not Assigned(FChannelManager.TTSEngine) then  raise Exception.Create('TTS not initialization, can not PlayMessage.');
  Result := FChannelManager.TTSEngine.PlayToFile(Self, Atxt, Afilename, AVoiceResource);
end;

procedure TTrunkChannel.RecordFile_stop;
begin
  FChannelManager.EnterCS(D641XCS);
  try
    if Status = csRecording then HTJS_Voc_StopRecordFile(ChannelID);
  finally
    FChannelManager.LeaveCS(D641XCS);
  end;
end;
function TTrunkChannel.RecordFile(const Afilename: string;
  ALengthTimes: Integer; AllowBreak: Boolean): TRecordFileResult;
var Adir: string;
begin
  Adir := ExtractFilePath(Afilename); if not DirectoryExists(Adir) then ForceDirectories(Adir);
  FChannelManager.EnterCS(D641XCS);
  try {$IFDEF CTI_DEBUG} ChannelLog('准备进行通道录音……');{$ENDIF}
    HTJS_Voc_RecordFileNew(ChannelID,PChar(Afilename), 8000 * ALengthTimes, 0);
    Status := csRecording;
  finally
    FChannelManager.LeaveCS(D641XCS);
    Result := rfrRecording;
  end;
end;

procedure TTrunkChannel.ResetChannel;
var Ach1 : TAbstractChannel;
begin
  inherited;
  FChannelManager.EnterCS(D641XCS);
  try  ChannelLog('重置通道');   Fsatisfaction := 0;
    if Status = csRecording then
     HTJS_Voc_StopRecordFile(ChannelID);
    if (FLinkToChannel <> -1) and (FLinkToChannel <> -100) then begin
      Ach1 := TAbstractChannel(FChannelManager.truncChannels[FLinkToChannel]);
      THackChannel(Ach1).FLinkToChannel := -1; Ach1.Status := csFree; 
    end;
    
    if HTJS_Trk_GetConnectUserID(ChannelID) <> -1 then HTJS_Exg_ClearLinkTrunkAndUserByTrunk(channelID);
    if HTJS_Trk_GetConnectTrunkID(ChannelID) <> -1 then HTJS_Exg_ClearLinkTrunkAndTrunk(channelID);
    HTJS_Trk_InitDtmfBuf(ChannelID);
    HTJS_ISDN_SetChnState(Fpcm,Fchn,CH_SET_DISCONNECT,0);
    HTJS_ISDN_SetChnState(Fpcm,Fchn,CH_SET_FREE,0);
    FLinkToChannel := -1;Status := csFree;
    if Assigned(FOnResetChannel) then FOnResetChannel(Self);     // 事件处理程序
  finally
    FChannelManager.LeaveCS(D641XCS);
  end;
end;

function TTrunkChannel.RingDetect(const Atimes: Integer): Boolean;
begin
  FChannelManager.EnterCS(D641XCS);
  try  Result:=false;
    Result := HTJS_ISDN_GetChnState(Fpcm,Fchn) = CALLEE_WAIT_ANSWER;
    if result then begin
      HTJS_Trk_InitDtmfBuf(ChannelID);
      FCallSession.SesstionType := stCallIn; FCallSession.StartTime:=now;
    end;
  finally
    FChannelManager.LeaveCS(D641XCS);
  end;
end;

function TTrunkChannel.SendMessage(const Atxt: string): Boolean;
begin
  Result := true;
end;

function TTrunkChannel.UnLink(const AChannelID: Integer; const AChannelType: TChannelType; ChannelToChannel:boolean): Integer;
var Achnl : TAbstractChannel;
begin
  try
    FChannelManager.EnterCS(D641XCS);
    if HTJS_Trk_GetConnectUserID(ChannelID) <> -1 then begin
      HTJS_Exg_ClearLinkTrunkAndUserByTrunk(ChannelID);
      Achnl := ChannelManager.userChannels[FLinkToChannel];
      if not ChannelToChannel then begin
        TUserChannel(Achnl).FLinkToChannel := -1; TUserChannel(Achnl).FLinkToType := ctEmpty;
      end else TUserChannel(Achnl).FLinkToChannel := -100;
    end;
    if HTJS_Trk_GetConnectTrunkID(ChannelID) <> -1 then begin
      HTJS_Exg_ClearLinkTrunkAndTrunk(ChannelID);
      Achnl := ChannelManager.TruncChannels[FLinkToChannel];
      if not ChannelToChannel then begin
        TTrunkChannel(Achnl).FLinkToChannel := -1; TTrunkChannel(Achnl).FLinkToType := ctEmpty;
      end else TTrunkChannel(Achnl).FLinkToChannel := -100;
    end;
    Result := 0;
  finally
    FChannelManager.LeaveCS(D641XCS);
  end;
end;

function TTrunkChannel.UnLinkAll: Boolean;
begin
  Result := true;
end;


procedure TUserChannel.ClearDTMF;
begin
  inherited;  FDtmf := '';
  try
    {$IFDEF CTI_DEBUG} ChannelLog('DEBUG# 清除按键缓冲区中的键按信息'); {$ENDIF}
    FChannelManager.EnterCS(D641XCS);
    HTJS_User_InitDialBuf(ChannelID);
  finally
    FChannelManager.LeaveCS(D641XCS);
  end;
end;

{-------------------------------------------------------------------------------
  Procedure: TUserChannel.Dial
  Author:    hxn
  DateTime:  2007.08.30
  Arguments: Adialnum, APrefixnum: string; AchannelID:integer
  Result:    string
  Popose:    拨号
-------------------------------------------------------------------------------}
function TUserChannel.Dial(const Adialnum, APrefixnum: string): TSignalType;
var ARingResult : boolean;
begin
  try
    FChannelManager.EnterCS(D641XCS);
    if HTJS_User_RingDetect(ChannelID) then HTJS_User_StopRing(ChannelID);
    ARingResult := Htjs_user_startring(ChannelID);
    if ARingResult then begin
      FCallNumber := Adialnum;
      FCallSession.StartTime := now;
      if Assigned(FOnCallIn) then FOnCallIn(Self, APrefixnum + Adialnum);
    end;
  finally
    FChannelManager.LeaveCS(D641XCS);
  end;
  result := stConnect;
end;

function TUserChannel.DtmfHit: Boolean;
begin
  FChannelManager.EnterCS(D641XCS);
  try
    Result := HTJS_User_GetDialNum(ChannelID)>0;
  finally
    FChannelManager.LeaveCS(D641XCS);
  end;
end;

function TUserChannel.GetCalleeNumber: string;
begin
    Result := '400 811 0006';
end;

function TUserChannel.GetCallerNumber: string;
begin
  {$IFDEF CTI_DEBUG}   ChannelLog('开始获取主叫号码……');  {$ENDIF}
  Result := FCallNumber;
end;

function TUserChannel.GetDTMFCode:string;
begin
  FChannelManager.EnterCS(D641XCS);
  try Result := 'z';
    if HTJS_User_CheckHookOff(channelid) then
      begin
        Result := HTJS_User_GetDialCode(ChannelID);
        //Htjs_user_initdialbuf(channelid);
      end;
  finally
    FChannelManager.LeaveCS(D641XCS);
  end;
end;

function TUserChannel.GetDTMF(const Alength: Integer; Asuffix: string;
  const Atimeout: Integer): string;
var
  K: Integer; T: Cardinal;
begin
  //HTJS_Exg_ClearLinkPlayVoiceFromUser(channelid);
  //Self.PlayVoice(SIG_STOP);
  Result := ''; K := iif(Atimeout = 0, TimeOut, Atimeout); // 设置按键的超时值
  Status  := csGetDtmf; T := GetTickCount;  FDtmf := '';
  {$IFDEF CTI_DEBUG} Log('准备获取对方按键……'); {$ENDIF}
  if Alength = -1 then begin
    while ((FDtmf = '') or (FDtmf[length(FDtmf)] <> '#')) and (GetTickCount - T < Cardinal(K * GCTimeOutRate)) do begin
      FDtmf := GetDTMFCode;
      if FDtmf = 'z' then break else sleep(channel_sleep_time);
    end;
    if (FDtmf <> '') and (FDtmf[length(FDtmf)] <> '#') then FDtmf := ''
    else FDtmf := copy(FDtmf,1,length(FDtmf)-1);
    end
  else begin
    while (length(FDtmf)<Alength) and (GetTickCount - T < Cardinal(K * GCTimeOutRate)) do begin
      FDtmf := GetDTMFCode;
      if FDtmf = 'z' then break;
    end;
    if  FDtmf <> 'z' then
      if length(FDtmf)<Alength then   FDtmf := ''
      else FDtmf := copy(FDtmf,1,Alength);
  end;
  Result := FDtmf; ChannelLog('获取对方按键完成，对方按键为：' + FDtmf);
  ClearDTMF;
end;

procedure TUserChannel.HangUp;
begin
  FChannelManager.EnterCS(D641XCS);
  try
    //tc08a32.HangUp(ChannelID);
    HTJS_User_SetPowerOFF(ChannelID);//对于用户模块停止馈电
    FCallSession.EndTime:=now;
    if FCallSession.SesstionType <> stFree then FCallSession.StoreSession;
    FCallSession.SesstionType := stFree;    
  finally
    FChannelManager.LeaveCS(D641XCS);
  end;
end;

function TUserChannel.HangUpDetect: Boolean;
begin
  FChannelManager.EnterCS(D641XCS);
  try
    Result := HTJS_User_CheckHookOFF(ChannelID) = false;
    FCallSession.EndTime:=now;
    if FCallSession.SesstionType <> stFree then FCallSession.StoreSession;
    FCallSession.SesstionType := stFree;    
  finally
    FChannelManager.LeaveCS(D641XCS);
  end;
end;

function TUserChannel.LinkTo(const AChannelID: Integer; const AChannelType: TChannelType): Integer;
var
  Achnl: TAbstractChannel;
begin
  FChannelManager.EnterCS(D641XCS);
  try
    Result := -1; HTJS_Exg_ClearLinkPlayVoiceFromUser(ChannelID); 
    if (AChannelID <> -1)and(AChannelType = ctUser) and HTJS_Exg_LinkUserAndUser(ChannelID,AChannelID) then
    begin
      HTJS_User_StopRing(AChannelID);
      Result := AChannelID; FLinkToChannel := AChannelID; FLinkToType := AChannelType;
      Achnl := ChannelManager.userChannels[AChannelID];
      TUserChannel(Achnl).FLinkToChannel := ChannelID; TUserChannel(Achnl).FLinkToType := ctUser;
      status := csconnect; ChannelLog('坐席之间通话中');
      if FCallSession.SesstionType = stCallIn then begin
        FCallSession.SessionID := TUserChannel(Achnl).FCallSession.SessionID;
        FCallSession.PhoneNumber := TUserChannel(Achnl).FCallSession.PhoneNumber;
      end;
      if FCallSession.SesstionType = stCallOut  then begin
        TUserChannel(Achnl).FCallSession.SessionID := FCallSession.SessionID;
        TUserChannel(Achnl).FCallSession.PhoneNumber := FCallSession.PhoneNumber;
      end;
    end;
    if (AChannelID <> -1) and (AChannelType = ctTrunk) and HTJS_Exg_SetLinkTrunkAndUser(AChannelID,ChannelID) then begin
      HTJS_User_StopRing(AChannelID);
      Result := AChannelID; FLinkToChannel := AChannelID; FLinkToType := AChannelType;
      Achnl := ChannelManager.TruncChannels[AChannelID];
      TTrunkChannel(Achnl).FLinkToChannel := ChannelID; TTrunkChannel(Achnl).FLinkToType := ctUser;
      status := csconnect; ChannelLog('坐席和中继通话中');
      if FCallSession.SesstionType = stCallIn then begin
        FCallSession.SessionID := TTrunkChannel(Achnl).FCallSession.SessionID;
        FCallSession.PhoneNumber := TTrunkChannel(Achnl).FCallSession.PhoneNumber;
      end;
      if FCallSession.SesstionType = stCallOut then begin
        TTrunkChannel(Achnl).FCallSession.SessionID := FCallSession.SessionID;
        TTrunkChannel(Achnl).FCallSession.PhoneNumber := FCallSession.PhoneNumber;
      end;
    end;

  finally
    FChannelManager.LeaveCS(D641XCS);
  end;
end;

function TUserChannel.ListenTo(const AChannelID: Integer): Integer;
begin
    result :=0;
    //HTJS_Voc_RecordFileNew(userid,pchar(tmpstr+'\' + IntToStr(UserId+1) +'_'+ FormatDateTime('yyyymmddhhmmss',now) +'_'+ Trunk[TrunkId].CallerSubAddr + Trunk[TrunkId].CallerNumber +'.pcm'),0,20000000);
end;

procedure TUserChannel.OffHook;
begin
  try
    FChannelManager.EnterCS(D641XCS);
    status := csOffHook;
    if HTJS_User_RingDetect(ChannelID) then HTJS_User_StopRing(ChannelID);
    ChannelLog(GAChannelTypeLabels[ChannelType] + '通道摘机');
  finally
    FChannelManager.LeaveCS(D641XCS);
  end;
end;

function TUserChannel.OffHookDetect: Boolean;
begin
  FChannelManager.EnterCS(D641XCS);
  try
    {$IFDEF CTI_DEBUG} ChannelLog('进行挂机检测'); {$ENDIF}
    Result := HTJS_User_CheckHookOFF(ChannelID); //
    if Result then begin
      Status := csOffHook;
      if HTJS_User_RingDetect(ChannelID) then HTJS_User_StopRing(ChannelID);
      FCallSession.SesstionType := stCallOut;FCallSession.StartTime:=now;
      ChannelLog('呼出');
    end;
  finally
    FChannelManager.LeaveCS(D641XCS);
  end;
end;

function TUserChannel.PlayPromptStr(const Afilelist: string):TPlayMessageResult;
begin
  FChannelManager.EnterCS(D641XCS);
  try
    if Status = csHangup then Result := pmrHangup
    else begin
      Status := csPlaying; Result := pmrPlaying;
      HTJS_User_PlayPromptStr(ChannelID,Afilelist);
      HTJS_User_InitDialBuf (ChannelID);
    end;
  finally
    FChannelManager.LeaveCS(D641XCS);
  end;
end;

function TUserChannel.PlayFile(const Afilelist, AfileExt: string;
  const AllowBreak: Boolean): TPlayMessageResult;
begin
  Result := PlayPromptStr(Afilelist);
  if Result <> pmrHangup then
    while  PlayFileDetect(AllowBreak) = pmrPlaying do sleep(channel_sleep_time);
  PlayFileStop;
end;

procedure TUserChannel.PlayVoice(AvoiceType: integer);
begin
  FChannelManager.EnterCS(D641XCS);
  try
    HTJS_Exg_ClearLinkPlayVoiceFromUser(ChannelID);
    if AvoiceType <> -1 then  HTJS_Exg_SetLinkPlayVoiceToUser(ChannelID,AvoiceType);
  finally
    FChannelManager.LeaveCS(D641XCS);
  end;    
end;

procedure TUserChannel.PlayFileStop;
begin
  FChannelManager.EnterCS(D641XCS);
  try
      HTJS_user_StopPlayFile(ChannelID); ChannelLog('播放语音文件结束');
  finally
    FChannelManager.LeaveCS(D641XCS);
  end;
end;

function TUserChannel.PlayFileDetect(const AllowBreak: Boolean):TPlayMessageResult;
var Aplaystop : boolean;
begin
  FChannelManager.EnterCS(D641XCS);
  try   Result := pmrPlaying;
    {$IFDEF CTI_DEBUG} ChannelLog('检测对方是否挂机'); {$ENDIF}
    if htjs_user_checkhookoff(ChannelID) = false then begin// 检测对方是否挂机
      ChannelLog('对方挂机中断当前语音文件的播放'); Result := pmrHangup;
    end else begin
      Aplaystop := HTJS_user_CheckPlayPromptStrEnd(ChannelID);
      if Aplaystop then Result := pmrComplete
      else if AllowBreak and (HTJS_User_GetDialNum(ChannelID)>0) then begin
        ChannelLog('对方按键停止当前语音文件的播放'); Result := pmrKeyPress;
      end;
    end;
  finally
    FChannelManager.LeaveCS(D641XCS);
  end;
end;

function TUserChannel.PlayMessage(const Atxt:string; const AllowBreak: Boolean): TPlayMessageResult;
begin
  if not Assigned(FChannelManager.TTSEngine) then
    raise Exception.Create('TTS not initialization, can not PlayMessage.');
  Result := FChannelManager.TTSEngine.PlayMessage(Self, Atxt, AllowBreak, pntMessage, VoiceResource);
end;

function TUserChannel.PlayNumber(const Atxt: string;
  const AllowBreak: Boolean;
  const APlayType: TPlayNumberType): TPlayMessageResult;
begin
  if not Assigned(FChannelManager.TTSEngine) then raise Exception.Create('TTS not initialization, can not PlayMessage.');
  Result := FChannelManager.TTSEngine.PlayMessage(Self, Atxt, AllowBreak, APlayType, VoiceResource);
end;

function TUserChannel.PlayToFile(const Atxt, Afilename: string;
  const AVoiceResource: TVoiceResourceType = vrtStandard): Boolean;
begin
  if not Assigned(FChannelManager.TTSEngine) then  raise Exception.Create('TTS not initialization, can not PlayMessage.');
  Result := FChannelManager.TTSEngine.PlayToFile(Self, Atxt, Afilename, AVoiceResource);
end;

function TUserChannel.PlayFileToTrunkAndUser(const Afilelist, AfileExt: string; const AllowBreak: Boolean = True; AchannelID: Integer = -1): TPlayMessageResult; // 给双方播放音乐
begin
  result :=  pmrComplete;
end;
function TUserChannel.RecordFile(const Afilename: string;
  ALengthTimes: Integer; AllowBreak: Boolean): TRecordFileResult;
var Adir: string;
begin
  FChannelManager.EnterCS(D641XCS);
  try    // 检查文件路径是否有效
    Adir := ExtractFilePath(Afilename); if not DirectoryExists(Adir) then ForceDirectories(Adir);

    if Status = csPlaying then
      if channeltype = ctTrunk then HTJS_Voc_StopIndexPlayFile(ChannelID)
      else  HTJS_Exg_ClearLinkPlayVoiceFromUser(ChannelID);

    {$IFDEF CTI_DEBUG}     ChannelLog('准备进行通道录音……');     {$ENDIF}
    HTJS_User_InitDialBuf(ChannelID);    Status := csRecording;
    HTJS_Voc_RecordFileNew(ChannelID,PChar(Afilename), 8000 * ALengthTimes, 0);
    result := rfrRecording;
  finally
    FChannelManager.LeaveCS(D641XCS);
  end;
end;

procedure TUserChannel.ResetChannel;
var Ach1 : TAbstractChannel;
begin
  inherited;
  FChannelManager.EnterCS(D641XCS);
  try  ChannelLog('重置通道');  Fsatisfaction := 0;
    if (FLinkToChannel <> -1) and (FLinkToChannel <> -100) then begin
      if FLinkToType = ctUser then
        Ach1 := TAbstractChannel(FChannelManager.userChannels[FLinkToChannel])
      else  
        Ach1 := TAbstractChannel(FChannelManager.truncChannels[FLinkToChannel]);

      THackChannel(Ach1).FLinkToChannel := -1; //Ach1.Status := csFree;
    end;

    if HTJS_User_GetConnectTrunkID(ChannelID) <> -1 then HTJS_Exg_ClearLinkTrunkAndUserByUser(ChannelID);
    if HTJS_User_GetConnectUserID(ChannelID) <> -1 then HTJS_Exg_ClearLinkUserAndUser(ChannelID);

    FLinkToChannel := -1;

    if HTJS_User_RingDetect(ChannelID) then HTJS_User_StopRing(ChannelID);
    if HTJS_User_GetPlayChannel(ChannelID)<> -1 then HTJS_Exg_ClearLinkPlayVoiceFromUser(ChannelID);
    if HTJS_User_GetListenUserID(ChannelID)<> -1 then HTJS_Exg_ClearListenUserFromUser(ChannelID);
    if HTJS_User_GetListenTrunkID(ChannelID)<> -1 then HTJS_Exg_ClearListenUserFromTrunk(ChannelID);
    
    HTJS_User_InitDialBuf(ChannelID);
    HTJS_User_SetPowerON(ChannelID);
    Status := csFree;
    if Assigned(FOnResetChannel) then FOnResetChannel(Self);     // 事件处理程序
  finally
    FChannelManager.LeaveCS(D641XCS);
  end;
end;

function TUserChannel.RingDetect(const Atimes: Integer): Boolean;
begin
  FChannelManager.EnterCS(D641XCS);
  try
    Result :=  HTJS_User_RingDetect(ChannelID);
    if  Result then begin
      FCallSession.SesstionType := stCallIn;FCallSession.StartTime:=now;
      Status := csRing;ChannelLog('对方来电');
    end;
  finally
    FChannelManager.LeaveCS(D641XCS);
  end;
end;

procedure TUserChannel.StopRing;
begin
  FChannelManager.EnterCS(D641XCS);
  try
    HTJS_User_StopRing(ChannelID);
  finally    
    FChannelManager.LeaveCS(D641XCS);
  end;
end;


function TUserChannel.SendMessage(const Atxt: string): Boolean;
begin
   Result :=  false;
end;

function TUserChannel.UnLink(const AChannelID: Integer; const AChannelType: TChannelType; ChannelToChannel:boolean): Integer;
var Achnl : TAbstractChannel;
begin
  try
    FChannelManager.EnterCS(D641XCS);
    if HTJS_User_GetConnectTrunkID(ChannelID) <> -1 then begin
      HTJS_Exg_ClearLinkTrunkAndUserByUser(ChannelID);
      Achnl := ChannelManager.TruncChannels[FLinkToChannel];
      if not ChannelToChannel then begin
        TTrunkChannel(Achnl).FLinkToChannel := -1; TTrunkChannel(Achnl).FLinkToType := ctEmpty;
      end else TTrunkChannel(Achnl).FLinkToChannel := -100;
    end;
    if HTJS_User_GetConnectUserID(ChannelID) <> -1 then begin
      HTJS_Exg_ClearLinkUserAndUser(ChannelID);
      Achnl := ChannelManager.userChannels[FLinkToChannel];
      if not ChannelToChannel then begin
        TUserChannel(Achnl).FLinkToChannel := -1; TUserChannel(Achnl).FLinkToType := ctEmpty;
      end else TUserChannel(Achnl).FLinkToChannel := -100;
    end;
    Result := 0;
  finally
    FChannelManager.LeaveCS(D641XCS);
  end;
end;

function TUserChannel.UnLinkAll: Boolean;
begin
   Result :=  false;
end;

function TUserChannel.Schedule(AserverType: string ;Afilelist: string): Integer;//获取最少接电话的坐席号
var
  AchannelID,i :integer;   Achnl: TTrunkChannel;
begin
  //FChannelManager.EnterCS(D641XCS);
  try
    AchannelID := -1;
    for i:= 0 to ChannelManager.truncChannels.Count-1 do
      begin
        Achnl := ChannelManager.truncChannels[i];
        if (Achnl.Status = csFree) then
          begin
            AchannelID := i;
            Achnl.Status := csLink;
            //Achnl.Terminate;
            break;
          end;
      end;
    Result := AchannelID;    {
    if Result <> -1 then begin
      Achnl := ChannelManager.truncChannels[AchannelID];
      HTJS_ISDN_SetChnState(Achnl.Fpcm,Achnl.Fchn,CALLEE_SET_ALERTING,0);
    end;                      }
  finally
    //FChannelManager.LeaveCS(D641XCS);
  end;
end;

end.
