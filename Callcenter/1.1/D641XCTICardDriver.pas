{-------------------------------------------------------------------------------
                      LightweightCTI for Delphi/C++Builder
                    专门为开发人员而写的轻型语音应用基础框架
            (C)Copyright 1998 - 2006 Sjteksoft - LightweightCTI项目组
            
　　本框架为开放源代码的自由软件，您可以遵照GNU GPL(GNU General Public License 
通用公共许可协议)及LightweightCTI补充协议的发布协议来修改和重新发布此软件包。

　　发布这一软件包的目的是为了能使Delphi/C++ Builder社区的广大开发人员轻松编写
基于模拟卡（包括Dialogic、东进、三汇等）的相关应用软件或工具，希望它能够切实为
您的开发工作带来便利，我们并不提供任何担保。甚至没有适合特定目的而隐含的担保。
您也可以通过下面的联系方式，向项目组了解并获取商业授权的版本，更为详细的信息请
参考GPL协议及LightweightCTI补充协议。

　　在下载并使用此软件包时，您已经和软件包一起收到一份GPL协议及LightweightCTI补
充协议的副本。如果还没有您可以访问我们的网站或发送电子邮件索取。

　　网站主页：http://21chainwater.vicp.net
              http://www.21chinawater.com
　　电子邮件：Sjsteksoft@gmail.com
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
  Purpose:   D641X板卡适配器导出函数，如果系统从DLL加载则必须实现此函数签名
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
  Purpose:   初始化D641X模拟卡
-------------------------------------------------------------------------------}
procedure TD641XCTICardDriver.Initialize;
const
  LA: array[0..4] of string = ('成功', '打开设备驱动程序时发生错误', '在读取TC08A-V.INI文件时发生错误',
    'INI文件的设置与实际的硬件不一致时发生错误', '未知错误请与管理员联系');
var
  AloadDRVflag: Integer;
  Astr,ApromptVoiceIdxFile: string;
begin
  Loger.Info('初始化 D641X 模拟卡……');
  with owner as  TChannelManager do begin
      ApromptVoiceIdxFile := Configuration.GetNodeAttribute(RootPath + 'Adpaters\Driver', 'promptVoiceIdxFile');
      CardDriver_virtual := Configuration.GetNodeAttribute(RootPath + 'Adpaters\Driver', 'mode') = 'virtual';
      AloadDRVflag := HTJS_Sys_EnableCard('', pchar(ApromptVoiceIdxFile));
  end;
  try
    FcanWork     := AloadDRVflag = 0; // 加载驱动程序
    if FcanWork then   FcanWork := HTJS_ISDN_InitSystem() = 1;
    if FcanWork then
    begin

      FtrunkCount := HTJS_Trk_GetTotalTrunkNum();
      FuserCount := HTJS_User_GetTotalUserNum();
      FChannelCount:=FtrunkCount+FuserCount;

      Loger.Info('初始化 D641X 系列模拟卡成功');

      SIG_STOP := -1;
      //电话忙音
      SIG_BUSY := HTJS_Voc_SFVC_ForPlay_New(0,false);
      HTJS_Voc_LoopPlayPromptFile(SIG_BUSY,'BUSY');
      //电话打通以后的音
      SIG_RINGBACK := HTJS_Voc_SFVC_ForPlay_New(0,false);
      HTJS_Voc_LoopPlayPromptFile(SIG_RINGBACK,'RING');
      //摘机后的长音
      SIG_DIALTONE := HTJS_Voc_SFVC_ForPlay_New(0,false);
      HTJS_Voc_LoopPlayPromptFile(SIG_DIALTONE,'TONE');
      //重播电话的音
      SIG_BACK := HTJS_Voc_SFVC_ForPlay_New(0,false);
      HTJS_Voc_LoopPlayPromptFile(SIG_BACK,'SIGN');
      
      // 初始化通道
      Loger.Info('准备初始化语音通道……');
      InitializeChannel;
      Loger.Info('初始化适配器' + Self.Name + '下对应的语音通道成功');
    end else
      Release;
  finally
    Astr := '初始化 D641X 模拟卡驱动程序' + iif(FcanWork, '成功', '失败');
    AloadDRVflag := iif(0 - AloadDRVflag > High(LA), High(LA), AloadDRVflag); // 数组越界检查
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
  Purpose:   初始化板卡可用的通道，在此处对通道的日志组件使用了TextLoger，系统中
  　　将其作为默认的日志组件，你也可以通过Amanager.GetLoger获取配置的日志组件。
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
  // 将通道对象添加到列表中
  for I := 0 to FuserCount - 1 do
  try
    ALoger := TDefaultLoger.Create(Self, ExePath + 'log\userChannel-' +  Format('%d', [I]) + '.log');
    Achn_user  := TUserChannel.Create(Self, I,ctUser , Amanager,ALoger as ILoger);
    THackChannelManager(Amanager).RegisterUserChannel(Achn_user); // 注册通道
    Achn_user.FserverType:=F_ini.ReadString('serverType',inttostr(i),'');
    Loger.Info(Format('初始化第 %-2d 条用户通道成功，通道类型：%s',[I, GAChannelTypeLabels[Achn_user.ChannelType]]));
    //Achn_user.ResetChannel;
    Achn_user.FserverNum :=0;
  except on E: Exception do
    begin
      Loger.Error(Format('初始化第 %d 条用户通道失败', [I]), E);
      Continue;
    end;
  end;
  F_ini.Free;
  for I := 0 to FtrunkCount - 1 do
  try
    ALoger := TDefaultLoger.Create(Self, ExePath + 'log\trunkChannel-' +  Format('%d', [I]) + '.log');
    Achn_trunk  := TTrunkChannel.Create(Self, I,ctTrunk , Amanager,ALoger as ILoger);
    Achn_trunk.Fpcm := I div GCE1Count;   Achn_trunk.Fchn := I mod GCE1Count;
    THackChannelManager(Amanager).RegisterTruncChannel(Achn_trunk); // 注册通道
    Loger.Info(Format('初始化第 %-2d 条中继通道成功，通道类型：%s',[I, GAChannelTypeLabels[cttrunk]]));
    Achn_trunk.ResetChannel;
  except on E: Exception do
    begin
      Loger.Error(Format('初始化第 %d 条中继通道失败', [I]), E);
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
  Purpose:   卸载适配器，释放模拟卡所占用的系统资源
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
      Loger.Info('由于用户程序阻止卸载适配器，当前不能进行卸载。');
      Exit;
    end;
  end;

  // 卸载适配器
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
        Loger.Error('初始化用户通道次数失败', E);
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
