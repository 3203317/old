{-------------------------------------------------------------------------------
                      LightweightCTI for Delphi/C++Builder
                    ר��Ϊ������Ա��д����������Ӧ�û������
            (C)Copyright 1998 - 2006 Sjteksoft - LightweightCTI��Ŀ��
            
���������Ϊ����Դ������������������������GNU GPL(GNU General Public License 
ͨ�ù������Э��)��LightweightCTI����Э��ķ���Э�����޸ĺ����·������������

����������һ�������Ŀ����Ϊ����ʹDelphi/C++ Builder�����Ĺ�󿪷���Ա���ɱ�д
����������������Dialogic������������ȣ������Ӧ������򹤾ߣ�ϣ�����ܹ���ʵΪ
���Ŀ��������������������ǲ����ṩ�κε���������û���ʺ��ض�Ŀ�Ķ������ĵ�����
��Ҳ����ͨ���������ϵ��ʽ������Ŀ���˽Ⲣ��ȡ��ҵ��Ȩ�İ汾����Ϊ��ϸ����Ϣ��
�ο�GPLЭ�鼰LightweightCTI����Э�顣

���������ز�ʹ�ô������ʱ�����Ѿ��������һ���յ�һ��GPLЭ�鼰LightweightCTI��
��Э��ĸ����������û�������Է������ǵ���վ���͵����ʼ���ȡ��

������վ��ҳ��http://21chainwater.vicp.net
              http://www.21chinawater.com
���������ʼ���Sjsteksoft@gmail.com
-------------------------------------------------------------------------------}

unit TextLoger;

interface

uses
  Classes, SysUtils, LightweightCTI;

type
  // ��־�ļ���֯��ʽ
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

    procedure Debug(Amsg: string); overload;                                    // ������Ϣ
    procedure Debug(Amsg: string; Aexception: Exception); overload;
    procedure Info(Amsg: string); overload;                                     // һ����Ϣ
    procedure Info(Amsg: string; Aexception: Exception); overload;
    procedure Warn(Amsg: string); overload;                                     // ������Ϣ
    procedure Warn(Amsg: string; Aexception: Exception); overload;
    procedure Error(Amsg: string); overload;                                    // ������Ϣ
    procedure Error(Amsg: string; Aexception: Exception); overload;
    procedure Fatal(Amsg: string); overload;                                    // ����������Ϣ
    procedure Fatal(Amsg: string; Aexception: Exception); overload;
    procedure Talk(Amsg: string); overload;
    procedure Talk(Amsg: string; Aexception: Exception); overload;

    property ShowLogLevel: Boolean read FShowLogLevel write FShowLogLevel default False;
  end;

  TDefaultLoger = class(TTextLoger);
  
implementation

const

  LALogLevelLabels: array [TLogLevel] of string = ('������Ϣ', '��־��Ϣ', '������Ϣ', '�������', '���ش���', '������ʾ');

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
  Purpose:   ��ʽ����־��Ϣ
-------------------------------------------------------------------------------}
function TTextLoger.FormatLogInfo(const Alevel: TLogLevel; const Amsg: string;
  E: Exception): string;
var
  Astr: string;
begin
  Astr := FormatDateTime('yyyy-mm-dd hh:nn:ss', Now) + '  ' + Amsg;

  if Assigned(E) then
  begin
    Astr := Astr + '���쳣���ƣ�' + E.ClassName;
    Astr := Astr + '  ' +  Format('�쳣���ݣ�%s', [E.Message]);
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
  Purpose:   ����־д���ı��ļ���
-------------------------------------------------------------------------------}
procedure TTextLoger.WriteLog(const Amsg: string);
var
  lf: Text;
  Adir, AdateStr: string;
begin
  // �����־��¼�ļ����Ƿ����
  Adir := ExtractFilePath(FlogFile);
  if not DirectoryExists(Adir) then ForceDirectories(Adir);

  // ������־��֯��ʽ����Ƿ���Ҫд�µ���־�ļ�����ǰֻ�԰�����֯����ʽ�����˴���
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
