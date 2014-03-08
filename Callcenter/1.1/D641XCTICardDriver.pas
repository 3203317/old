{-------------------------------------------------------------------------------
                      LightweightCTI for Delphi/C++Builder
                    ר��Ϊ������Ա��д����������Ӧ�û������
            (C)Copyright 1998 - 2006 Sjteksoft - LightweightCTI��Ŀ��
            
���������Ϊ����Դ������������������������GNU GPL(GNU General Public License 
ͨ�ù������Э��)��LightweightCTI����Э��ķ���Э�����޸ĺ����·������������

����������һ�������Ŀ����Ϊ����ʹDelphi/C++ Builder�����Ĺ�󿪷���Ա���ɱ�д
����ģ�⿨������Dialogic������������ȣ������Ӧ������򹤾ߣ�ϣ�����ܹ���ʵΪ
���Ŀ��������������������ǲ����ṩ�κε���������û���ʺ��ض�Ŀ�Ķ������ĵ�����
��Ҳ����ͨ���������ϵ��ʽ������Ŀ���˽Ⲣ��ȡ��ҵ��Ȩ�İ汾����Ϊ��ϸ����Ϣ��
�ο�GPLЭ�鼰LightweightCTI����Э�顣

���������ز�ʹ�ô������ʱ�����Ѿ��������һ���յ�һ��GPLЭ�鼰LightweightCTI��
��Э��ĸ����������û�������Է������ǵ���վ���͵����ʼ���ȡ��

������վ��ҳ��http://21chainwater.vicp.net
              http://www.21chinawater.com
���������ʼ���Sjsteksoft@gmail.com
-------------------------------------------------------------------------------}

unit D641XCTICardDriver;

interface

uses
  Classes, Windows, SysUtils, Forms, LightweightCTI;

type
  THackChannelManager = class(TChannelManager);
  
  TD641XCTICardDriver = class(TAbstractCTICardDriver)
  private
    FtrunkCount,FuserCount:integer;
  protected
    function InitializeChannel: Integer; override;
  public
    procedure Initialize; override;
    procedure Release; override;
    function CanClose: Boolean; override;
    procedure OnAfterEnter(Sender: TObject);
    function ChannelManager: TChannelManager;
  end;

  function LoadCTIDriver(Amanager: TChannelManager; HostApplication: TApplication):
    TAbstractCTICardDriver; stdcall; export;
  
implementation

uses D641XChannel, TextLoger,htjs_tce132,htjs_Isdndll,GlobalConstants,inifiles;

{-------------------------------------------------------------------------------
  Procedure: Unknown Name
  Author:    Xsp
  DateTime:  2006-08-18
  Arguments: None
  Result:    None
  Purpose:   D641X�忨�������������������ϵͳ��DLL���������ʵ�ִ˺���ǩ��
-------------------------------------------------------------------------------}
function LoadCTIDriver(Amanager: TChannelManager; HostApplication: TApplication): TAbstractCTICardDriver;
begin
  Application.Handle := HostApplication.Handle;
  Result := TD641XCTICardDriver.Create(Amanager, THackChannelManager(Amanager).FLoger);
end;

{ TD641XCTICardDriver }

function TD641XCTICardDriver.CanClose: Boolean;
begin
  Result := True;
end;

function TD641XCTICardDriver.ChannelManager: TChannelManager;
begin
  if Assigned(Owner) then
    Result := TChannelManager(Owner)
  else
    Result := nil;
end;

{-------------------------------------------------------------------------------
  Procedure: TD641XCTICardDriver.Initialize
  Author:    Xsp
  DateTime:  2006-08-18
  Arguments: None
  Result:    None
  Purpose:   ��ʼ��D641Xģ�⿨
-------------------------------------------------------------------------------}
procedure TD641XCTICardDriver.Initialize;
const
  LA: array[0..4] of string = ('�ɹ�', '���豸��������ʱ��������', '�ڶ�ȡTC08A-V.INI�ļ�ʱ��������',
    'INI�ļ���������ʵ�ʵ�Ӳ����һ��ʱ��������', 'δ֪�����������Ա��ϵ');
var
  AloadDRVflag: Integer;
  Astr,ApromptVoiceIdxFile: string;
begin
  Loger.Info('��ʼ�� D641X ģ�⿨����');
  with owner as  TChannelManager do begin
      ApromptVoiceIdxFile := Configuration.GetNodeAttribute(RootPath + 'Adpaters\Driver', 'promptVoiceIdxFile');
      CardDriver_virtual := Configuration.GetNodeAttribute(RootPath + 'Adpaters\Driver', 'mode') = 'virtual';
      AloadDRVflag := HTJS_Sys_EnableCard('', pchar(ApromptVoiceIdxFile));
  end;
  try
    FcanWork     := AloadDRVflag = 0; // ������������
    if FcanWork then   FcanWork := HTJS_ISDN_InitSystem() = 1;
    if FcanWork then
    begin

      FtrunkCount := HTJS_Trk_GetTotalTrunkNum();
      FuserCount := HTJS_User_GetTotalUserNum();
      FChannelCount:=FtrunkCount+FuserCount;

      Loger.Info('��ʼ�� D641X ϵ��ģ�⿨�ɹ�');

      SIG_STOP := -1;
      //�绰æ��
      SIG_BUSY := HTJS_Voc_SFVC_ForPlay_New(0,false);
      HTJS_Voc_LoopPlayPromptFile(SIG_BUSY,'BUSY');
      //�绰��ͨ�Ժ����
      SIG_RINGBACK := HTJS_Voc_SFVC_ForPlay_New(0,false);
      HTJS_Voc_LoopPlayPromptFile(SIG_RINGBACK,'RING');
      //ժ����ĳ���
      SIG_DIALTONE := HTJS_Voc_SFVC_ForPlay_New(0,false);
      HTJS_Voc_LoopPlayPromptFile(SIG_DIALTONE,'TONE');
      //�ز��绰����
      SIG_BACK := HTJS_Voc_SFVC_ForPlay_New(0,false);
      HTJS_Voc_LoopPlayPromptFile(SIG_BACK,'SIGN');
      
      // ��ʼ��ͨ��
      Loger.Info('׼����ʼ������ͨ������');
      InitializeChannel;
      Loger.Info('��ʼ��������' + Self.Name + '�¶�Ӧ������ͨ���ɹ�');
    end else
      Release;
  finally
    Astr := '��ʼ�� D641X ģ�⿨��������' + iif(FcanWork, '�ɹ�', 'ʧ��');
    AloadDRVflag := iif(0 - AloadDRVflag > High(LA), High(LA), AloadDRVflag); // ����Խ����
    Loger.Info(Astr + iif(FcanWork, '', LA[0 - AloadDRVflag]));
  end;

  inherited;
end;

{-------------------------------------------------------------------------------
  Procedure: TD641XCTICardDriver.InitializeChannel
  Author:    Xsp
  DateTime:  2006.05.19
  Arguments: None
  Result:    Integer
  Purpose:   ��ʼ���忨���õ�ͨ�����ڴ˴���ͨ������־���ʹ����TextLoger��ϵͳ��
  ����������ΪĬ�ϵ���־�������Ҳ����ͨ��Amanager.GetLoger��ȡ���õ���־�����
-------------------------------------------------------------------------------}
function TD641XCTICardDriver.InitializeChannel: Integer;
var
  I: Integer;  F_ini:TInifile;
  Achn_user: TUserChannel;Achn_trunk: TTrunkChannel;
  Amanager: TChannelManager;
  ALoger: TDefaultLoger;
begin     Result := 0;
  Amanager := Owner as TChannelManager;
  Amanager.OnAfterEnter := OnAfterEnter;
  if not Assigned(Amanager) then Exit;
  F_ini := TInifile.Create('.\channels.ini');
  // ��ͨ��������ӵ��б���
  for I := 0 to FuserCount - 1 do
  try
    ALoger := TDefaultLoger.Create(Self, ExePath + 'log\userChannel-' +  Format('%d', [I]) + '.log');
    Achn_user  := TUserChannel.Create(Self, I,ctUser , Amanager,ALoger as ILoger);
    THackChannelManager(Amanager).RegisterUserChannel(Achn_user); // ע��ͨ��
    Achn_user.FserverType:=F_ini.ReadString('serverType',inttostr(i),'');
    Loger.Info(Format('��ʼ���� %-2d ���û�ͨ���ɹ���ͨ�����ͣ�%s',[I, GAChannelTypeLabels[Achn_user.ChannelType]]));
    //Achn_user.ResetChannel;
    Achn_user.FserverNum :=0;
  except on E: Exception do
    begin
      Loger.Error(Format('��ʼ���� %d ���û�ͨ��ʧ��', [I]), E);
      Continue;
    end;
  end;
  F_ini.Free;
  for I := 0 to FtrunkCount - 1 do
  try
    ALoger := TDefaultLoger.Create(Self, ExePath + 'log\trunkChannel-' +  Format('%d', [I]) + '.log');
    Achn_trunk  := TTrunkChannel.Create(Self, I,ctTrunk , Amanager,ALoger as ILoger);
    Achn_trunk.Fpcm := I div GCE1Count;   Achn_trunk.Fchn := I mod GCE1Count;
    THackChannelManager(Amanager).RegisterTruncChannel(Achn_trunk); // ע��ͨ��
    Loger.Info(Format('��ʼ���� %-2d ���м�ͨ���ɹ���ͨ�����ͣ�%s',[I, GAChannelTypeLabels[cttrunk]]));
    Achn_trunk.ResetChannel;
  except on E: Exception do
    begin
      Loger.Error(Format('��ʼ���� %d ���м�ͨ��ʧ��', [I]), E);
      Continue;
    end;
  end;
  Result := FchannelCount;
end;

{-------------------------------------------------------------------------------
  Procedure: TD641XCTICardDriver.Release
  Author:    Xsp
  DateTime:  2006.05.10
  Arguments: None
  Result:    None
  Purpose:   ж�����������ͷ�ģ�⿨��ռ�õ�ϵͳ��Դ
-------------------------------------------------------------------------------}
procedure TD641XCTICardDriver.Release;
var
  ACanRelease: Boolean;
begin
  if Assigned(FOnReleasing) then
  begin
    FOnReleasing(Self, ACanRelease);
    if not ACanRelease then
    begin
      Loger.Info('�����û�������ֹж������������ǰ���ܽ���ж�ء�');
      Exit;
    end;
  end;

  // ж��������
  HTJS_ISDN_ExitSystem();
  HTJS_Sys_disableCard();

  if Assigned(FOnReleased) then
    FOnReleased(Self);
end;

procedure TD641XCTICardDriver.OnAfterEnter(Sender: TObject);
var  Amanager : TChannelManager; I : integer;
begin
  HTJS_ISDN_getevent;  HTJS_Sys_PushPlay;
  if Time = 4.5/24 then begin
    Amanager := Owner as TChannelManager;
    for I := 0 to FuserCount - 1 do
    try
      TUserChannel(Amanager.userChannels[I]).FserverNum := 0;
    except on E: Exception do
      begin
        Loger.Error('��ʼ���û�ͨ������ʧ��', E);
        Continue;
      end;
    end;
  end;
end;

initialization
  Classes.RegisterClass(TD641XCTICardDriver);

finalization
  Classes.UnRegisterClass(TD641XCTICardDriver);

end.
