unit D641XChannel_callback;

interface
uses
  Classes, SysUtils, ComCtrls, ExtCtrls, LightweightCTI, UdpBroadCast;
type
  THackChannel = class(TAbstractChannel);

  TCallSession = class(TAbstractSession)
  private

  public
    FChannel: TAbstractChannel;
    procedure StoreSession; override;
  end;

  TChannelCallback = class(TObject)
  private
    Finiflag : boolean; Fowner : pointer;
    promptfiles : Tstringlist;
    FTimer : TTimer;
    procedure OnChannelInitialized(Sender: TObject; var Channel: TAbstractChannel);
    procedure OnChannelStatusChanged(Sender: TObject);
    procedure OnGetDTMF(Sender: TObject; const Adtmf: string);
    procedure OnCall(Sender: TObject; const APhoneNumber: string);
    procedure OnResetChannel(Sender: TObject);
    procedure OnChannelLog(Sender: TObject; const msg: string);
    procedure OnCallIn(Sender: TObject; const ACallNumber: string);
    procedure OnUserChannelOn(Sender: TObject; AMsg: string);
    procedure OnUserChannelOff(Sender: TObject; AMsg: string);
    procedure ChannelFree(var Channel: TAbstractChannel);
    procedure SaveChannelServerType;
    procedure UserChannelClick(Sender: TObject);
    procedure IniUserChannelMenuItem;
    procedure TotalChannelsStatus(sender : TObject);
  protected
    procedure UserListen(Channel : TAbstractChannel);
    procedure UserCall(Channel : TAbstractChannel);
    function UserCallUser(Channel : TAbstractChannel; AUserChannelID : integer) : boolean;
    procedure UserCallTrunk(Channel : TAbstractChannel; AphoneMo : string);
    function UserListenUser(Channel : TAbstractChannel; AUserChannelID : integer) : boolean;
  public
    constructor Create(Owner : TObject); overload;
    procedure ResumeChannels(const ChannelType:TChannelType);
    procedure TerminateChannels(const ChannelType:TChannelType);
    procedure initChannelManager;
    destructor Destroy; override;
    procedure OnTrunkRun(sender:TObject);
    procedure OnUserRun(sender:TObject);
  end;

  function ChannelCallback(AOwner : TObject) : TChannelCallback;
implementation

uses
  GlobalConstants, Forms, Inifiles, MainFrm, Menus, Windows;
var
  FChannelCallback: TChannelCallback;


{ TCallSession }


{-------------------------------------------------------------------------------
  过程名:    TCallSession.StoreSession
  作者:      hxn
  日期:      2007.09.05
  参数:      None
  返回值:    None
  作用:      持久化会话
-------------------------------------------------------------------------------}
procedure TCallSession.StoreSession;
const
  LASessionType: array [TSesstionType] of string = ('呼入', '呼出', '空闲');
var
  Astr: string;
begin  
  Astr := Format('%s,%s,%s,%s,%s,%s,%s,%s,%s,%s',
    [SessionID,LASessionType[SesstionType],GAChannelTypeLabels[FChannel.ChannelType],inttostr(FChannel.ChannelID),
    FormatDateTime('YYYY-MM-DD',StartTime),FormatDateTime('hh:nn:ss',StartTime),FormatDateTime('YYYY-MM-DD',EndTime),
    FormatDateTime('hh:nn:ss',EndTime), PhoneNumber,  inttostr(FChannel.Fsatisfaction)]);
  if assigned(THackChannel(FChannel).Loger) then THackChannel(FChannel).Loger.Info(Astr);
  udpmanager.SendInfo(BroadIp,BroadOutPort,ptl_SessionLog+Astr);
end;

//------------------------------------------------------------------------------
// 通道回调管理器全局入口程序
//------------------------------------------------------------------------------
function ChannelCallback(AOwner : TObject): TChannelCallback;
begin
  if not Assigned(FChannelCallback) then
  begin
    FChannelCallback := TChannelCallback.Create(AOwner);
  end;
  Result := FChannelCallback;
end;

constructor TChannelCallback.Create( Owner : TObject);
begin
  FOwner := Owner;  Finiflag:=false; TMainform(FOwner).Tag:=1;
  FTimer := TTimer.Create(nil);  FTimer.Enabled :=false;
end;

destructor TChannelCallback.Destroy;
var  Achnl: TAbstractChannel;  i : integer;
begin
  FTimer.Enabled := false; FTimer.Free;
  if Finiflag then
    with ChannelManager do begin
      SaveChannelServerType;
      for i:=0 to UserChannels.Count-1 do begin Achnl := THackChannel(UserChannels[i]);
          if Assigned(Achnl) then  begin ChannelFree(Achnl); Achnl.Terminate; end;
      end;
      for i:=0 to TruncChannels.Count-1 do begin Achnl := THackChannel(TruncChannels[i]);
          if Assigned(Achnl) then  begin ChannelFree(Achnl); Achnl.Terminate; end;
      end;
    end;
  UdpManager.Free; inherited;
end;

{-------------------------------------------------------------------------------
  Procedure: TMainform.actLoadChannelManagerExecute
  Author:    hxn
  DateTime:  2006-08-23
  Arguments: Sender: TObject
  Result:    None
  Purpose:   初始化通道管理器
-------------------------------------------------------------------------------}
procedure TChannelCallback.initChannelManager;
var
  Aname, Afile, ATotalInterval: string; I, N: Integer;
begin
  with ChannelManager do  begin
    OnChannelInitialized := Self.OnChannelInitialized; Initialize; // 初始化
    IniUserChannelMenuItem;
    Finiflag := true; promptfiles.Free;
    promptfiles := Tstringlist.Create;
    ServerTypeList := Tstringlist.Create;

    N := Configuration.GetChildCount(RootPath + 'ServerTypeList', 'type');
    for I := 0 to N - 1 do begin
      Aname := Configuration.GetNodeAttribute(RootPath + 'ServerTypeList\type', 'code', I);
      Afile := Configuration.GetNodeAttribute(RootPath + 'ServerTypeList\type', 'value', I);
      ServerTypeList.Values[Aname]:= Afile;
    end;

    GCResourcesDirectory := Configuration.GetNodeText(RootPath + 'ResourceDir');
    N := Configuration.GetChildCount(RootPath + 'timepromptfiles', 'time');
    for I := 0 to N - 1 do begin
      Aname := Configuration.GetNodeAttribute(RootPath + 'timepromptfiles\time', 'hour', I);
      Afile := Configuration.GetNodeAttribute(RootPath + 'timepromptfiles\time', 'file', I);
      promptfiles.Values[Aname]:= Afile;
    end;

    GClocalphone := Configuration.GetNodeAttribute(RootPath + 'setting', 'localphone');
    Aname := Configuration.GetNodeAttribute(RootPath + 'setting', 'channel_sleep_time');
    Afile := Configuration.GetNodeAttribute(RootPath + 'setting', 'callback_sleep_time');
    channel_sleep_time := StrToIntDef(Aname,100); callback_sleep_time :=  StrToIntDef(Afile,200);
    ATotalInterval := Configuration.GetNodeAttribute(RootPath + 'setting', 'TotalInterval');
    FTimer.Interval := StrToIntDef(ATotalInterval,10)*1000; FTimer.OnTimer := TotalChannelsStatus;

    ptl_IncomingTelegram := Configuration.GetNodeAttribute(RootPath + 'protocols\IncomingTelegram', 'code');
    ptl_UserChannelOn := Configuration.GetNodeAttribute(RootPath + 'protocols\UserChannelOn', 'code');
    ptl_UserChannelOff := Configuration.GetNodeAttribute(RootPath + 'protocols\UserChannelOff', 'code');
    ptl_SessionLog := Configuration.GetNodeAttribute(RootPath + 'protocols\SessionLog', 'code');
    ptl_ChannelsTotal := Configuration.GetNodeAttribute(RootPath + 'protocols\ChannelsTotal', 'code');

    with UdpManager do
    try
      BroadlistenPort := StrToIntDef(Configuration.GetNodeAttribute(RootPath + 'Broad', 'ListenPort'),8028);
      BroadIp := Configuration.GetNodeAttribute(RootPath + 'Broad', 'Ip');
      BroadOutPort := StrToIntDef(Configuration.GetNodeAttribute(RootPath + 'Broad', 'OutPort'),8029);
      StartListen(BroadlistenPort); OnClientConnect := OnUserChannelOn;
      OnClientDisConnect := OnUserChannelOff;
    except 
    end;
  end;
end;

procedure TChannelCallback.TotalChannelsStatus(sender : TObject);
var
  SendMsg : string; I : integer; Achnl : TAbstractChannel;
  UserFree,UserTalking,UserBusy,UserDisonnect,
  TrunkTalking,TrunkFree,TrunkWait : integer;
begin
  UserFree := 0; UserTalking := 0; UserBusy := 0; UserDisonnect := 0;
  TrunkTalking := 0; TrunkFree := 0; TrunkWait := 0;
  //UserDisable := 10; UserConnect := 10; UserBusy := 10; UserDisonnect := 34;
  //TrunkConnect := 10; TrunkFree := 10; TrunkLink := 40;
  for I := 0 to ChannelManager.userChannels.Count - 1 do begin
    Achnl := ChannelManager.userChannels[I];
    case Achnl.Status of
    csRing		  : ;       // 振铃
    csFree		  : UserFree := UserFree + 1;       // 空闲
    csOffHook		: UserBusy := UserBusy + 1;       // 摘机
    csTimeOut		: ;       // 超时
    csConnect		: ;       // 连接，被叫摘机
    csPlaying		: ;       // 放音
    csGetDtmf		: ;       // 收码
    csHangup		: ;       // 挂机
    csLink		  : UserTalking := UserTalking+1;       // 连接
    csDisable		: UserDisonnect := UserDisonnect+1;       // 禁用    
    end;
  end;
  for I := 0 to ChannelManager.truncChannels.Count - 1 do begin
    Achnl := ChannelManager.truncChannels[I];
    case Achnl.Status of
    csRing		  : ;       // 振铃
    csFree		  : TrunkFree := TrunkFree+1;       // 空闲
    csOffHook		: ;       // 摘机
    csTimeOut		: ;       // 超时
    csConnect		: ;       // 连接，被叫摘机
    csPlaying		: ;       // 放音
    csGetDtmf		: ;       // 收码
    csHangup		: ;       // 挂机
    csLink		  : TrunkTalking := TrunkTalking+1;       // 连接
    csDisable		: ;       // 禁用
    csWait      : TrunkWait := TrunkWait +1;
    end;
  end;
  SendMsg := ptl_ChannelsTotal + IntToStr(UserFree) +  ',' + IntToStr(UserTalking) +  ',' + IntToStr(UserBusy) +  ','
    + IntToStr(UserDisonnect) +  ',' + IntToStr(TrunkTalking) +  ',' + IntToStr(TrunkFree) +  ',' + IntToStr(TrunkWait);
  udpmanager.SendInfo(BroadIp,BroadOutPort,SendMsg);
end;

procedure TChannelCallback.ResumeChannels(const ChannelType:TChannelType);
var
  Achnl: THackChannel;  i : integer; Channels : TList;
begin
  with ChannelManager do begin
    if ChannelType = ctUser then Channels := UserChannels else Channels := TruncChannels;
    for i:=0 to Channels.Count-1 do begin Achnl := THackChannel(Channels[i]);
        if Assigned(Achnl) then  Achnl.Resume;
    end;
  end;
end;

procedure TChannelCallback.TerminateChannels(const ChannelType:TChannelType);
var
  Achnl: THackChannel;  i : integer; Channels : TList;
begin
  with ChannelManager do begin
    if ChannelType = ctUser then Channels := UserChannels else Channels := TruncChannels;
    for i:=0 to Channels.Count-1 do begin Achnl := THackChannel(Channels[i]);
        if Assigned(Achnl) then  Achnl.Terminate;
    end;
  end;
end;

{-------------------------------------------------------------------------------
  Procedure: TChannelCallback.OnChannelInitialized
  Author:    hxn
  DateTime:  2007-09-04
  Arguments: Sender: TObject; var Channel: TAbstractChannel
  Result:    None
  Purpose:   通道初始化事件
-------------------------------------------------------------------------------}
procedure TChannelCallback.OnChannelInitialized(Sender: TObject;var Channel: TAbstractChannel);
var
  Aitem: TListItem;  lvChannels : TListView; RunEvent:TNotifyEvent;
begin
  try   FTimer.Enabled := true;
    if Channel.ChannelType = ctUser then begin
      lvChannels := TMainform(Fowner).lvUserChannels;
      RunEvent := OnUserRun;
      end
    else begin
      lvChannels := TMainform(Fowner).lvTrunkChannels;
      RunEvent := OnTrunkRun;
    end;
    Channel.FCallSession := TCallSession.Create(nil);
    TCallSession(Channel.FCallSession).FChannel := Channel;
    Channel.FVCLComObject := lvChannels;
    // 设置通道的事件处理程序
    Channel.OnGetDTMF := OnGetDTMF;
    Channel.OnCallOut := OnCall;
    Channel.OnStatusChanged := OnChannelStatusChanged;
    Channel.OnResetChannel := OnResetChannel;
    Channel.OnChannelLog := OnChannelLog;
    Channel.OnCallIn :=  OnCallIn;
    Channel.OnRun:= RunEvent;

    // 将通道资料连接到视图
    lvChannels.Items.BeginUpdate;
    Aitem := lvChannels.Items.Add;
    Aitem.Caption := IntToStr(Channel.ChannelID);
    Aitem.SubItems.Add(GAChannelTypeLabels[Channel.ChannelType]);
    Aitem.SubItems.Add(GAChannelStatusLabels[Channel.Status]);
    Aitem.SubItems.Add(''); // DTMF码(2)
    Aitem.SubItems.Add(''); // 电话号码(3)
    Aitem.SubItems.Add(''); // 详细动作(4)
    Aitem.SubItems.Add(''); // 备注(5)
    Aitem.ImageIndex := Integer(Channel.ChannelType);
    Aitem.StateIndex := -1;
    lvChannels.Items.EndUpdate;
  except
  end;
end;

procedure TChannelCallback.ChannelFree(var Channel: TAbstractChannel);
begin
  try
    Channel.OnRun :=  nil;
    Channel.OnGetDTMF := nil;
    Channel.OnCallOut := nil;
    Channel.OnStatusChanged := nil;
    Channel.OnResetChannel := nil;
    Channel.OnChannelLog := nil;
    Channel.OnCallIn :=  nil;
  except
  end;
end;

{-------------------------------------------------------------------------------
  过程名:    TChannelCallback.OnTruncCall
  作者:      hxn
  日期:      2007-09-04
  参数:      Sender: TObject; const APhoneNumber: string
  返回值:    None
  作用:      呼出事件处理程序
-------------------------------------------------------------------------------}
procedure TChannelCallback.OnCall(Sender: TObject; const APhoneNumber: string);
var
  Achnl: TAbstractChannel;
begin
  Achnl := TAbstractChannel(Sender);
  Tlistview(Achnl.FVCLComObject).Items.Item[Achnl.ChannelID].SubItems.Strings[3] := APhoneNumber;
end;

{-------------------------------------------------------------------------------
  过程名:    TChannelCallback.OnTruncCall
  作者:      hxn
  日期:      2007-09-04
  参数:      Sender: TObject; const APhoneNumber: string
  返回值:    None
  作用:      呼入事件处理程序
-------------------------------------------------------------------------------}
procedure TChannelCallback.OnCallIn(Sender: TObject; const ACallNumber: string);
begin
  OnCall(Sender,ACallNumber);
  if THackChannel(Sender).ChannelType = ctUser then
  udpmanager.SendInfo(BroadIp,BroadOutPort,ptl_IncomingTelegram
  +copy(inttostr(101+THackChannel(Sender).ChannelID),2,2)+ACallNumber);
end;

{-------------------------------------------------------------------------------
  过程名:    TChannelCallback.OnTruncChannelStatusChanged
  作者:      hxn
  日期:      2007-09-04
  参数:      Sender: TObject
  返回值:    None
  作用:      当通道状态改变时，运用此事件来修改系统界面显示内容
-------------------------------------------------------------------------------}
procedure TChannelCallback.OnChannelStatusChanged(Sender: TObject);
var
  Aitem: TListItem;
  Achnl: TAbstractChannel;
begin
  Achnl := TAbstractChannel(Sender);
  try
    Aitem := Tlistview(Achnl.FVCLComObject).Items.Item[Achnl.ChannelID];
    if Assigned(Aitem) then
    begin
      Aitem.SubItems.Strings[1] := GAChannelStatusLabels[Achnl.Status];
      Aitem.ImageIndex := iif(Achnl.Status = csPlaying, 5, Integer(Achnl.ChannelType));
      Aitem.StateIndex := iif((Achnl.Status = csFree) or (Achnl.Status = csDisable), -1, 0);
    end;
  finally
  end;
end;

{-------------------------------------------------------------------------------
  过程名:    TChannelCallback.OnTruncGetDTMF
  作者:      hxn
  日期:      2007-09-04
  参数:      Sender: TObject; const Adtmf: string
  返回值:    None
  作用:      通道收DTMF码事件处理程序
-------------------------------------------------------------------------------}
procedure TChannelCallback.OnGetDTMF(Sender: TObject; const Adtmf: string);
var
  Achnl: TAbstractChannel;
begin
  Achnl := TAbstractChannel(Sender);
  Tlistview(Achnl.FVCLComObject).Items.Item[Achnl.ChannelID].SubItems.Strings[2] := Adtmf;
end;

{-------------------------------------------------------------------------------
  过程名:    TChannelCallback.OnTruncChannelLog
  作者:      hxn
  日期:      2007-09-04
  参数:      Sender: TObject; const msg: string
  返回值:    None
  作用:      通道日志处理程序，通过此事件可以将系统内部发生的所有情况记录下来或
  　　显示在用户界面上
-------------------------------------------------------------------------------}
procedure TChannelCallback.OnChannelLog(Sender: TObject; const msg: string);
var
  Achnl: TAbstractChannel;
begin
  Achnl := TAbstractChannel(Sender);
  Tlistview(Achnl.FVCLComObject).Items.Item[Achnl.ChannelID].SubItems.Strings[4] := msg;
end;

{-------------------------------------------------------------------------------
  过程名:    TChannelCallback.OnTruncChannelLog
  作者:      hxn
  日期:      2007-09-04
  参数:      Sender: TObject
  返回值:    None
  作用:      通道重置
-------------------------------------------------------------------------------}

procedure TChannelCallback.OnResetChannel(Sender: TObject);
var
  Achnl: TAbstractChannel;
begin
  Achnl := TAbstractChannel(Sender);
  with Tlistview(Achnl.FVCLComObject).Items.Item[Achnl.ChannelID].SubItems do
  begin
    Strings[2] := '';
    Strings[3] := '';
    Strings[4] := '';
    Strings[5] := '';
  end;
end;

{-------------------------------------------------------------------------------
  过程名:    TChannelCallback.OnUserChannelOn
  作者:      hxn
  日期:      2007-09-04
  参数:      Sender: TObject; const msg: string
  返回值:    None
  作用:      用户通道打开
-------------------------------------------------------------------------------}

procedure TChannelCallback.OnUserChannelOn(Sender: TObject; AMsg: string);
var AchannelID : integer;  Achnl: THackChannel;
begin
    AchannelID := StrToIntDef(AMsg,100)-1;
    if (AchannelID >= 0) and  (AchannelID < ChannelManager.UserChannels.Count) then begin
      Achnl := THackChannel(ChannelManager.UserChannels[AchannelID]);
      Achnl.ResetChannel;
    end;
end;

{-------------------------------------------------------------------------------
  过程名:    TChannelCallback.OnUserChannelOff
  作者:      hxn
  日期:      2007-09-04
  参数:      Sender: TObject; const msg: string
  返回值:    None
  作用:      用户通道关闭
-------------------------------------------------------------------------------}

procedure TChannelCallback.OnUserChannelOff(Sender: TObject; AMsg: string);
var AchannelID : integer;  Achnl: THackChannel;
begin
    AchannelID := StrToIntDef(AMsg,100)-1;
    if (AchannelID >= 0) and  (AchannelID < ChannelManager.UserChannels.Count) then begin
      Achnl := THackChannel(ChannelManager.UserChannels[AchannelID]);
      Achnl.Status := csDisable;
    end;
end;

procedure TChannelCallback.SaveChannelServerType;
var F_ini : TInifile; i : integer; Achnl : THackChannel;
begin
  F_ini := TInifile.Create('.\channels.ini');
  for i:=0 to ChannelManager.userChannels.Count-1 do begin
    Achnl := THackChannel(ChannelManager.userChannels[i]);
    if Assigned(Achnl) then F_ini.WriteString('serverType',inttostr(i),Achnl.FserverType);
  end;
  F_ini.Free;
end;

procedure TChannelCallback.UserChannelClick(Sender: TObject);
var i : integer;  Achnl: THackChannel;
begin
  with sender as TMenuItem do  begin
    i := TMainform(Fowner).lvUserChannels.ItemIndex;
    if i <>-1 then begin
      Achnl := THackChannel(ChannelManager.UserChannels[i]);
      case tag of
      0: begin Achnl.Terminate; Achnl.Status := csDisable; end;
      1: begin Achnl.Status := csFree; Achnl.Resume; end;
      2: Achnl.ResetChannel;
      end;
    end;
  end;
end;

procedure TChannelCallback.IniUserChannelMenuItem;
var item : TMenuItem; I, N : integer;  PopupMenu : TPopupMenu;
  ATag, ACaption, AImageIndex : string;
begin
  PopupMenu := TMainform(Fowner).PopupMenu1;
  with ChannelManager do begin
    N := Configuration.GetChildCount(RootPath + 'UserChannelPopupMenu', 'MenuItem');
    for I := 0 to N - 1 do begin
      ATag := Configuration.GetNodeAttribute(RootPath + 'UserChannelPopupMenu\MenuItem', 'Tag', I);
      ACaption := Configuration.GetNodeAttribute(RootPath + 'UserChannelPopupMenu\MenuItem', 'Caption', I);
      AImageIndex := Configuration.GetNodeAttribute(RootPath + 'UserChannelPopupMenu\MenuItem', 'ImageIndex', I);
      item := TMenuItem.Create(PopupMenu); PopupMenu.Items.Add(item);
      item.Tag := StrToIntDef(ATag,I);  item.ImageIndex := StrToIntDef(AImageIndex,I);
      item.Caption := ACaption; if ACaption <> '-' then item.OnClick := UserChannelClick;
    end;
  end;
  if N > 0 then PopupMenu.AutoPopup := true;
end;


procedure TChannelCallback.OnTrunkRun(sender:TObject);
var
  N: Integer; Adtmf,Afilename: string;  Channel : TAbstractChannel; ch : char;
  AserverType : string;  AUserChannelID : integer; T: Cardinal;
  AUserChannel :  TAbstractChannel;  ATimeOut : boolean;
begin  ATimeOut := false;
  Channel := sender as TAbstractChannel;
  while not Channel.Terminated do begin
    if Channel.RingDetect(1) then begin
      Channel.OffHook;  Channel.GetCallerNumber;
      Channel.PlayFile(promptfiles.Values[inttostr(trunc(Time * 24))], '.voc',false);
      N := 0;  ch := 'z';
      while  ( N<3 ) and  ( ServerTypeList.IndexOfName(ch)=-1 ) do begin
        Channel.PlayFile('Voc_Service', '.voc',true);
        Adtmf := Channel.GetDTMF(1);
        if Adtmf<>'' then ch := Adtmf[1] else begin ch := 'z'; N :=2;end;
        if (ch > '0') and (ch < chr(ServerTypeList.Count+49)) then AserverType := ch else N := N+1;
      end;
      if N = 3 then Channel.PlayFile('Voc_CallBack', '.voc',true)
      else if N < 3 then begin
        AUserChannelID := Channel.Schedule(AserverType,'Voc_UserBusy,Voc_PlayWaitMusic');
        if AUserChannelID = -1 then begin
           Channel.PlayFile('Voc_JiXuDengDai', '.voc',true);
           while Channel.GetDTMF(1)='1' do begin
              AUserChannelID := Channel.Schedule(AserverType,'Voc_UserBusy,Voc_PlayWaitMusic');
              if Channel.Terminated then break;
              if AUserChannelID <> -1 then break
              else Channel.PlayFile('Voc_JiXuDengDai', '.voc',true);
           end;
        end;
        if AUserChannelID <>-1 then begin
          Channel.PlayVoice(SIG_RINGBACK);
          AUserChannel := ChannelManager.userChannels[AUserChannelID];
          AUserChannel.Dial(Channel.GetCallerNumber); T := GetTickCount;
          while not AUserChannel.OffHookDetect do begin
            if Channel.HangUpDetect then  ATimeOut := true
            else ATimeOut := GetTickCount - T > Cardinal(30 * GCTimeOutRate);
            if ATimeOut then break else sleep(callback_sleep_time);
          end;
          if not ATimeOut then begin
              Channel.PlayVoice(SIG_STOP);
              Channel.PlayFileToTrunkAndUser('Voc_UserCode','.voc',false,AUserChannelID);
              Channel.LinkTo(AUserChannelID);
              Afilename := GCResourcesDirectory+IntToStr(THackChannel(Channel).FLinkToChannel+1);
              Afilename := Afilename +'_'+ FormatDateTime('yyyymmddhhmmss',now)+'_'+ Channel.GetCallerNumber +'.pcm';
              Channel.RecordFile(Afilename);
              while not Channel.HangUpDetect do begin
                //if (THackChannel(Channel).FLinkToChannel=-100)or((THackChannel(Channel).FLinkToChannel<>-1) and not AUserChannel.OffHookDetect)
                if THackChannel(Channel).FLinkToChannel<>-1 then sleep(callback_sleep_time) else break;
                if AUserChannel.HangUpDetect then break;
              end;

              Channel.PlayFile('Voc_ManYiDuDiaoCha', '.voc',true);
              Adtmf := Channel.GetDTMF(1);
              if Adtmf<>'' then ch := Adtmf[1] else begin ch := 'z';end;
              case ch of
                '1' : begin
                    //Channel.PlayFile('d1', '.voc',false);
                    Channel.Fsatisfaction := 1;
                  end;
                '2' : begin
                    //Channel.PlayFile('d2', '.voc',false);
                    Channel.Fsatisfaction := 2;
                  end;
              end;
              Channel.PlayFile('Voc_GoodBye', '.voc',false);
          end
          else AUserChannel.ResetChannel;
        end;
      end;
      Channel.ResetChannel;
    end;
    if Channel.OffHookDetect then begin
        while not Channel.HangUpDetect do
          case THackChannel(Channel).FLinkToChannel of
          -1: break;
          -100: Channel.PlayFile('VOC_SHIFT', '.voc',false);
          else sleep(callback_sleep_time);
          end;
        Channel.ResetChannel;
    end;
  end;
end;

procedure TChannelCallback.OnUserRun(sender:TObject);
var
  Channel : TAbstractChannel;
begin
  Channel := sender as TAbstractChannel;
  while not Channel.Terminated do begin
    if Channel.RingDetect then
      begin
        UserListen(Channel)
      end
    else
    if Channel.OffHookDetect then
      begin
        Channel.OffHook;
        UserCall(Channel);
      end;

    sleep(callback_sleep_time);
  end;
end;

procedure TChannelCallback.UserCall(Channel : TAbstractChannel);
var AphoneMo : string;  AUserChannelID : integer;
begin
  while not Channel.DtmfHit do //如果通道没有接收到按键码
    begin
      if Channel.HangUpDetect then
        begin
          break;
        end;
      if Channel.Terminated then exit;
      sleep(callback_sleep_time);
    end;

  if not Channel.HangUpDetect then
    begin
      Channel.PlayVoice(SIG_STOP);//停止对该通道放音
      AphoneMo := Channel.GetDTMF(-1,'',30);
      if AphoneMo<>'' then begin
        channel.PlayVoice(SIG_STOP);
        if (AphoneMo[1] <> '*') and (length(AphoneMo)>7) then  UserCallTrunk(Channel,AphoneMo);
        if (AphoneMo[1] = '*') and (length(AphoneMo) = 3) then begin
          AphoneMo := copy(AphoneMo,2,2);
          AUserChannelID := StrToIntDef(AphoneMo,0)-1;
          if AUserChannelID >= ChannelManager.userChannels.Count then AUserChannelID := -1;
          if AUserChannelID <> -1 then UserCallUser(Channel,AUserChannelID);
        end;
        Channel.ResetChannel;
    end;
  end else if Channel.Status <> csdisable then Channel.Status := csfree; 
end;


function TChannelCallback.UserCallUser(Channel : TAbstractChannel; AUserChannelID : integer) :boolean;
var   AUserChannel : TAbstractChannel;
begin
  Result := False;
  AUserChannel := ChannelManager.userChannels[AUserChannelID];
  if AUserChannel.Status = csFree then begin
      Channel.PlayVoice(SIG_RINGBACK);
      AUserChannel.Dial(Channel.GetCallerNumber);

      while not Channel.HangUpDetect do //当该坐席没有挂机,执行循环
        begin
          if AUserChannel.OffHookDetect then //如果对方摘机
            begin
              Channel.PlayVoice(SIG_STOP);
              Channel.LinkTo(AUserChannelID,ctUser);
              break;
            end; 
          Sleep(callback_sleep_time);
        end;
          while not Channel.HangUpDetect do     //连接上之后
            begin
              if AUserChannel.OffHookDetect = false then //如果对方挂机
                begin
                  break;
                end;
              Sleep(callback_sleep_time);
            end;
      
      if Channel.OffHookDetect then Channel.PlayVoice(SIG_BUSY);

      while Channel.OffHookDetect do sleep(callback_sleep_time); //如果该坐席没有挂机,播放busy音

      Channel.PlayVoice(SIG_STOP);
      
      Result := True;
    end;
end;

procedure TChannelCallback.UserCallTrunk(Channel : TAbstractChannel; AphoneMo : string);
var
  ALinkToChannel : integer;  APlayVoice : integer;
  ATrackChannel : TAbstractChannel;  T : Cardinal;  ATimeOut : boolean;
begin  ATimeOut := false;
  ALinkToChannel := Channel.Schedule('','');
  ATrackChannel := THackChannel(ChannelManager.TruncChannels[ALinkToChannel]);
  ATrackChannel.Dial(AphoneMo); T := GetTickCount;
  Channel.PlayVoice(SIG_RINGBACK);
  while not ATrackChannel.OffHookDetect do begin
    if Channel.HangUpDetect then ATimeOut := True
    else ATimeOut := GetTickCount - T > Cardinal(30 * GCTimeOutRate);
    if ATimeOut then break else sleep(callback_sleep_time);
  end;
  if not ATimeOut then begin    APlayVoice :=0;
      Channel.Linkto(ALinkToChannel,ctTrunk);
      while not Channel.HangUpDetect do begin
        if ATrackChannel.HangUpDetect then
          if APlayVoice < 10 then APlayVoice := APlayVoice + 1;
        if APlayVoice = 1 then Channel.PlayVoice(SIG_Busy);
        sleep(callback_sleep_time);
      end;
  end else begin
    if not Channel.HangUpDetect then Channel.PlayVoice(SIG_Busy);

    ATrackChannel.ResetChannel;
  end;

  while not Channel.HangUpDetect do sleep(callback_sleep_time);

  Channel.PlayVoice(SIG_STOP);
end;

procedure TChannelCallback.UserListen(Channel : TAbstractChannel);
var AphoneMo : string;  AUserChannelID : integer; ATrunkChannel,AUserChannel: TAbstractChannel;
  ALinkToChannel : integer; ALinkToType : TChannelType; AHangUp : boolean;
begin
  while Channel.RingDetect do
    begin   if Channel.Terminated then exit;
      if Channel.OffHookDetect then break;
      sleep(callback_sleep_time);
    end;

  if Channel.OffHookDetect then begin //如果坐席摘机,则往里执行,摘机时停止振铃
    Channel.PlayVoice(SIG_STOP);
    while not Channel.HangUpDetect do begin
      if Channel.Terminated then exit; AHangUp := true;
      ALinkToChannel := THackChannel(Channel).FLinkToChannel;
      if ALinkToChannel = -1 then begin sleep(10); continue; end;
      ALinkToChannel := THackChannel(Channel).FLinkToChannel;
      ALinkToType := THackChannel(Channel).FLinkToType;
      if ALinkToType =ctTrunk then begin
        ATrunkChannel := ChannelManager.TruncChannels[ALinkToChannel];
        if ATrunkChannel.HangUpDetect then Channel.PlayVoice(SIG_BUSY)
        else begin Channel.PlayVoice(SIG_STOP); AHangUp := false; end;
      end
      else begin
        AUserChannel := ChannelManager.UserChannels[ALinkToChannel];
        if AUserChannel.HangUpDetect then Channel.PlayVoice(SIG_BUSY)
        else begin Channel.PlayVoice(SIG_STOP); AHangUp := false; end;
      end;
      if AHangUp then begin sleep(callback_sleep_time); continue; end;
      if Channel.HangUpDetect then break;
      if not Channel.DtmfHit then
        begin
          sleep(callback_sleep_time);
          continue;
        end;
      AphoneMo := Channel.GetDTMF(-1,'',30);
      if (AphoneMo <> '') and (AphoneMo[1] = '*') and (length(AphoneMo) = 3) then begin
        AphoneMo := copy(AphoneMo,2,2);
        AUserChannelID := StrToIntDef(AphoneMo,0)-1;
        if AUserChannelID >= ChannelManager.userChannels.Count then AUserChannelID := -1;
        if AUserChannelID <> -1 then   begin
          ATrunkChannel := ChannelManager.TruncChannels[ALinkToChannel];
          channel.unlink(ALinkToChannel,ALinkToType,true);
          if not UserlistenUser(Channel,AUserChannelID) then
            Channel.Linkto(ALinkToChannel,ctTrunk)
          else  ATrunkChannel.LinkTo(AUserChannelID);
        end;
      end; 
      sleep(callback_sleep_time);
    end;
  end;
  Channel.ResetChannel;

end;

function TChannelCallback.UserListenUser(Channel: TAbstractChannel; AUserChannelID : integer) : boolean;
var   AUserChannel : TAbstractChannel;  T : Cardinal;   ATimeOut : boolean;
begin  ATimeOut := false;
  AUserChannel := ChannelManager.userChannels[AUserChannelID];
  Channel.PlayVoice(SIG_RINGBACK);
  AUserChannel.Dial(Channel.GetCallerNumber); T := GetTickCount;
  while not AUserChannel.OffHookDetect do begin
    ATimeOut := GetTickCount - T > Cardinal(30 * GCTimeOutRate);
    if ATimeOut then break else sleep(callback_sleep_time);
  end;
  if not ATimeOut then begin
      Channel.Linkto(AUserChannelID,ctUser);
      while not Channel.HangUpDetect and not ATimeOut do begin
        if AUserChannel.HangUpDetect then ATimeOut := true else  sleep(callback_sleep_time);
      end;
  end;
  result := not ATimeOut;
end;

end.
