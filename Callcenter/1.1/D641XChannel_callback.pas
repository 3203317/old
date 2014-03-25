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
  ������:    TCallSession.StoreSession
  ����:      hxn
  ����:      2007.09.05
  ����:      None
  ����ֵ:    None
  ����:      �־û��Ự
-------------------------------------------------------------------------------}
procedure TCallSession.StoreSession;
const
  LASessionType: array [TSesstionType] of string = ('����', '����', '����');
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
// ͨ���ص�������ȫ����ڳ���
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
  Purpose:   ��ʼ��ͨ��������
-------------------------------------------------------------------------------}
procedure TChannelCallback.initChannelManager;
var
  Aname, Afile, ATotalInterval: string; I, N: Integer;
begin
  with ChannelManager do  begin
    OnChannelInitialized := Self.OnChannelInitialized; Initialize; // ��ʼ��
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
    csRing		  : ;       // ����
    csFree		  : UserFree := UserFree + 1;       // ����
    csOffHook		: UserBusy := UserBusy + 1;       // ժ��
    csTimeOut		: ;       // ��ʱ
    csConnect		: ;       // ���ӣ�����ժ��
    csPlaying		: ;       // ����
    csGetDtmf		: ;       // ����
    csHangup		: ;       // �һ�
    csLink		  : UserTalking := UserTalking+1;       // ����
    csDisable		: UserDisonnect := UserDisonnect+1;       // ����    
    end;
  end;
  for I := 0 to ChannelManager.truncChannels.Count - 1 do begin
    Achnl := ChannelManager.truncChannels[I];
    case Achnl.Status of
    csRing		  : ;       // ����
    csFree		  : TrunkFree := TrunkFree+1;       // ����
    csOffHook		: ;       // ժ��
    csTimeOut		: ;       // ��ʱ
    csConnect		: ;       // ���ӣ�����ժ��
    csPlaying		: ;       // ����
    csGetDtmf		: ;       // ����
    csHangup		: ;       // �һ�
    csLink		  : TrunkTalking := TrunkTalking+1;       // ����
    csDisable		: ;       // ����
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
  Purpose:   ͨ����ʼ���¼�
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
    // ����ͨ�����¼���������
    Channel.OnGetDTMF := OnGetDTMF;
    Channel.OnCallOut := OnCall;
    Channel.OnStatusChanged := OnChannelStatusChanged;
    Channel.OnResetChannel := OnResetChannel;
    Channel.OnChannelLog := OnChannelLog;
    Channel.OnCallIn :=  OnCallIn;
    Channel.OnRun:= RunEvent;

    // ��ͨ���������ӵ���ͼ
    lvChannels.Items.BeginUpdate;
    Aitem := lvChannels.Items.Add;
    Aitem.Caption := IntToStr(Channel.ChannelID);
    Aitem.SubItems.Add(GAChannelTypeLabels[Channel.ChannelType]);
    Aitem.SubItems.Add(GAChannelStatusLabels[Channel.Status]);
    Aitem.SubItems.Add(''); // DTMF��(2)
    Aitem.SubItems.Add(''); // �绰����(3)
    Aitem.SubItems.Add(''); // ��ϸ����(4)
    Aitem.SubItems.Add(''); // ��ע(5)
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
  ������:    TChannelCallback.OnTruncCall
  ����:      hxn
  ����:      2007-09-04
  ����:      Sender: TObject; const APhoneNumber: string
  ����ֵ:    None
  ����:      �����¼���������
-------------------------------------------------------------------------------}
procedure TChannelCallback.OnCall(Sender: TObject; const APhoneNumber: string);
var
  Achnl: TAbstractChannel;
begin
  Achnl := TAbstractChannel(Sender);
  Tlistview(Achnl.FVCLComObject).Items.Item[Achnl.ChannelID].SubItems.Strings[3] := APhoneNumber;
end;

{-------------------------------------------------------------------------------
  ������:    TChannelCallback.OnTruncCall
  ����:      hxn
  ����:      2007-09-04
  ����:      Sender: TObject; const APhoneNumber: string
  ����ֵ:    None
  ����:      �����¼���������
-------------------------------------------------------------------------------}
procedure TChannelCallback.OnCallIn(Sender: TObject; const ACallNumber: string);
begin
  OnCall(Sender,ACallNumber);
  if THackChannel(Sender).ChannelType = ctUser then
  udpmanager.SendInfo(BroadIp,BroadOutPort,ptl_IncomingTelegram
  +copy(inttostr(101+THackChannel(Sender).ChannelID),2,2)+ACallNumber);
end;

{-------------------------------------------------------------------------------
  ������:    TChannelCallback.OnTruncChannelStatusChanged
  ����:      hxn
  ����:      2007-09-04
  ����:      Sender: TObject
  ����ֵ:    None
  ����:      ��ͨ��״̬�ı�ʱ�����ô��¼����޸�ϵͳ������ʾ����
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
  ������:    TChannelCallback.OnTruncGetDTMF
  ����:      hxn
  ����:      2007-09-04
  ����:      Sender: TObject; const Adtmf: string
  ����ֵ:    None
  ����:      ͨ����DTMF���¼���������
-------------------------------------------------------------------------------}
procedure TChannelCallback.OnGetDTMF(Sender: TObject; const Adtmf: string);
var
  Achnl: TAbstractChannel;
begin
  Achnl := TAbstractChannel(Sender);
  Tlistview(Achnl.FVCLComObject).Items.Item[Achnl.ChannelID].SubItems.Strings[2] := Adtmf;
end;

{-------------------------------------------------------------------------------
  ������:    TChannelCallback.OnTruncChannelLog
  ����:      hxn
  ����:      2007-09-04
  ����:      Sender: TObject; const msg: string
  ����ֵ:    None
  ����:      ͨ����־��������ͨ�����¼����Խ�ϵͳ�ڲ����������������¼������
  ������ʾ���û�������
-------------------------------------------------------------------------------}
procedure TChannelCallback.OnChannelLog(Sender: TObject; const msg: string);
var
  Achnl: TAbstractChannel;
begin
  Achnl := TAbstractChannel(Sender);
  Tlistview(Achnl.FVCLComObject).Items.Item[Achnl.ChannelID].SubItems.Strings[4] := msg;
end;

{-------------------------------------------------------------------------------
  ������:    TChannelCallback.OnTruncChannelLog
  ����:      hxn
  ����:      2007-09-04
  ����:      Sender: TObject
  ����ֵ:    None
  ����:      ͨ������
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
  ������:    TChannelCallback.OnUserChannelOn
  ����:      hxn
  ����:      2007-09-04
  ����:      Sender: TObject; const msg: string
  ����ֵ:    None
  ����:      �û�ͨ����
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
  ������:    TChannelCallback.OnUserChannelOff
  ����:      hxn
  ����:      2007-09-04
  ����:      Sender: TObject; const msg: string
  ����ֵ:    None
  ����:      �û�ͨ���ر�
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
  while not Channel.DtmfHit do //���ͨ��û�н��յ�������
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
      Channel.PlayVoice(SIG_STOP);//ֹͣ�Ը�ͨ������
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

      while not Channel.HangUpDetect do //������ϯû�йһ�,ִ��ѭ��
        begin
          if AUserChannel.OffHookDetect then //����Է�ժ��
            begin
              Channel.PlayVoice(SIG_STOP);
              Channel.LinkTo(AUserChannelID,ctUser);
              break;
            end; 
          Sleep(callback_sleep_time);
        end;
          while not Channel.HangUpDetect do     //������֮��
            begin
              if AUserChannel.OffHookDetect = false then //����Է��һ�
                begin
                  break;
                end;
              Sleep(callback_sleep_time);
            end;
      
      if Channel.OffHookDetect then Channel.PlayVoice(SIG_BUSY);

      while Channel.OffHookDetect do sleep(callback_sleep_time); //�������ϯû�йһ�,����busy��

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

  if Channel.OffHookDetect then begin //�����ϯժ��,������ִ��,ժ��ʱֹͣ����
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