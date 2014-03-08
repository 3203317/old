{-------------------------------------------------------------------------------
                      LightweightCTI for Delphi/C++Builder
                    专门为开发人员而写的轻型语音应用基础框架
            (C)Copyright 1998 - 2006 Sjteksoft - LightweightCTI项目组
            
　　本框架为开放源代码的自由软件，您可以遵照GNU GPL(GNU General Public License 
通用公共许可协议)及LightweightCTI补充协议的发布协议来修改和重新发布此软件包。

　　发布这一软件包的目的是为了能使Delphi/C++ Builder社区的广大开发人员轻松编写
基于语音卡（包括Dialogic、东进、三汇等）的相关应用软件或工具，希望它能够切实为
您的开发工作带来便利，我们并不提供任何担保。甚至没有适合特定目的而隐含的担保。
您也可以通过下面的联系方式，向项目组了解并获取商业授权的版本，更为详细的信息请
参考GPL协议及LightweightCTI补充协议。

　　在下载并使用此软件包时，您已经和软件包一起收到一份GPL协议及LightweightCTI补
充协议的副本。如果还没有您可以访问我们的网站或发送电子邮件索取。

　　网站主页：http://21chainwater.vicp.net
              http://www.21chinawater.com
　　电子邮件：Sjsteksoft@gmail.com
-------------------------------------------------------------------------------}

unit TextLoger;

interface

uses
  Classes, SysUtils, LightweightCTI;

type
  // 日志文件组织形式
  TRollingStyle = (rsDay, rsWeek, rsMonth, rsQuarter, rsYear);

  TTextLoger = class(TComponent, ILoger)
  private
    FlogFile: string;
    FShowLogLevel: Boolean;
    FRollingStyle: TRollingStyle;
    FVCLComObject: Pointer;
    
    function FormatLogInfo(const Alevel: TLogLevel; const Amsg: string;
      E: Exception = nil): string;
    procedure WriteLog(const Amsg: string);
  protected
    // IInterface
    function QueryInterface(const IID: TGUID; out Obj): HResult; virtual; stdcall;
    function _AddRef: Integer; stdcall;
    function _Release: Integer; stdcall;
  public
    constructor Create(AOwner: TComponent); overload; override;
    constructor Create(AOwner: TComponent; const Alogfile: string); reintroduce; overload;

    procedure Debug(Amsg: string); overload;                                    // 调试信息
    procedure Debug(Amsg: string; Aexception: Exception); overload;
    procedure Info(Amsg: string); overload;                                     // 一般信息
    procedure Info(Amsg: string; Aexception: Exception); overload;
    procedure Warn(Amsg: string); overload;                                     // 警告信息
    procedure Warn(Amsg: string; Aexception: Exception); overload;
    procedure Error(Amsg: string); overload;                                    // 错误信息
    procedure Error(Amsg: string; Aexception: Exception); overload;
    procedure Fatal(Amsg: string); overload;                                    // 致命错误信息
    procedure Fatal(Amsg: string; Aexception: Exception); overload;
    procedure Talk(Amsg: string); overload;
    procedure Talk(Amsg: string; Aexception: Exception); overload;

    property ShowLogLevel: Boolean read FShowLogLevel write FShowLogLevel default False;
  end;

  TDefaultLoger = class(TTextLoger);
  
implementation

const

  LALogLevelLabels: array [TLogLevel] of string = ('调试信息', '日志信息', '警告信息', '程序错误', '严重错误', '来电显示');

  LCDefaultLogfile = 'LightweightCTI.log';
    
{ TTextLoger }

procedure TTextLoger.Debug(Amsg: string; Aexception: Exception);
begin
  WriteLog(FormatLogInfo(llDebug, Amsg, Aexception));
end;

procedure TTextLoger.Debug(Amsg: string);
begin
  WriteLog(FormatLogInfo(llDebug, Amsg));
end;

procedure TTextLoger.Error(Amsg: string);
begin
  WriteLog(FormatLogInfo(llError, Amsg));
end;

procedure TTextLoger.Error(Amsg: string; Aexception: Exception);
begin
  WriteLog(FormatLogInfo(llError, Amsg, Aexception));
end;

procedure TTextLoger.Fatal(Amsg: string; Aexception: Exception);
begin
  WriteLog(FormatLogInfo(llFatal, Amsg, Aexception));
end;

procedure TTextLoger.Fatal(Amsg: string);
begin
  WriteLog(FormatLogInfo(llFatal, Amsg));
end;

procedure TTextLoger.Info(Amsg: string);
begin
  WriteLog(FormatLogInfo(llInfo, Amsg));
end;

{-------------------------------------------------------------------------------
  Procedure: TTextLoger.FormatLogInfo
  Author:    Xsp
  DateTime:  2006-08-17
  Arguments: const Alevel: TLogLevel; const Amsg: string; Aexception: Exception
  Result:    string
  Purpose:   格式化日志信息
-------------------------------------------------------------------------------}
function TTextLoger.FormatLogInfo(const Alevel: TLogLevel; const Amsg: string;
  E: Exception): string;
var
  Astr: string;
begin
  Astr := FormatDateTime('yyyy-mm-dd hh:nn:ss', Now) + '  ' + Amsg;

  if Assigned(E) then
  begin
    Astr := Astr + '，异常名称：' + E.ClassName;
    Astr := Astr + '  ' +  Format('异常内容：%s', [E.Message]);
  end;
  Result := iif(FShowLogLevel, '$' + LALogLevelLabels[Alevel] + '$  ' + Astr, Astr);
end;

procedure TTextLoger.Info(Amsg: string; Aexception: Exception);
begin
  WriteLog(FormatLogInfo(llInfo, Amsg, Aexception));
end;

procedure TTextLoger.Warn(Amsg: string; Aexception: Exception);
begin
  WriteLog(FormatLogInfo(llWarn, Amsg, Aexception));
end;

procedure TTextLoger.Warn(Amsg: string);
begin
  WriteLog(FormatLogInfo(llWarn, Amsg));
end;

function TTextLoger._AddRef: Integer;
begin
  if FVCLComObject = nil then
    Result := -1
  else
    Result := IVCLComObject(FVCLComObject)._AddRef;
end;

function TTextLoger._Release: Integer;
begin
  if FVCLComObject = nil then
    Result := -1
  else
    Result := IVCLComObject(FVCLComObject)._Release;
end;

function TTextLoger.QueryInterface(const IID: TGUID; out Obj): HResult;
begin
  if FVCLComObject = nil then
  begin
    if GetInterface(IID, Obj) then Result := S_OK
    else Result := E_NOINTERFACE
  end else
    Result := IVCLComObject(FVCLComObject).QueryInterface(IID, Obj);
end;

{-------------------------------------------------------------------------------
  Procedure: TTextLoger.WriteLog
  Author:    Xsp
  DateTime:  2006-08-17
  Arguments: const Amsg: string
  Result:    None
  Purpose:   将日志写入文本文件中
-------------------------------------------------------------------------------}
procedure TTextLoger.WriteLog(const Amsg: string);
var
  lf: Text;
  Adir, AdateStr: string;
begin
  // 检查日志记录文件夹是否存在
  Adir := ExtractFilePath(FlogFile);
  if not DirectoryExists(Adir) then ForceDirectories(Adir);

  // 根据日志组织形式检查是否需要写新的日志文件，当前只对按天组织的形式进行了处理
  if FRollingStyle = rsDay then
  begin
    AdateStr := FormatDateTime('yyyy-mm-dd', Date);
    if Pos(AdateStr, FlogFile) = 0 then
      FlogFile := Copy(FlogFile, 1, Length(FlogFile) - 4) + ' ' + AdateStr + Copy(FlogFile, Length(FlogFile) - 3, 4);
  end;

  AssignFile(lf, FlogFile);
  {$i-}
  Append(lf);
  {$i+}
  if IOresult <> 0 then
    Rewrite(lf);
  writeln(lf, Amsg);
  CloseFile(lf);
end;

constructor TTextLoger.Create(AOwner: TComponent);
begin
  inherited;
  FlogFile := ExePath + 'log\' + LCDefaultLogfile;
  FShowLogLevel := False;
  FRollingStyle := rsDay;
end;

constructor TTextLoger.Create(AOwner: TComponent; const Alogfile: string);
begin
  inherited Create(AOwner);
  FlogFile := Alogfile;
end;

procedure TTextLoger.Talk(Amsg: string);
begin

end;

procedure TTextLoger.Talk(Amsg: string; Aexception: Exception);
begin

end;

initialization
  Classes.RegisterClass(TTextLoger);

finalization
  Classes.UnregisterClass(TTextLoger);

end.
