program CallCenterDC;

uses
  Forms,
  Tlhelp32,
  SysUtils,
  windows,
  Messages,
  Unit1 in 'Unit1.pas' {frm_main},
  E1Fax in 'E1Fax.pas',
  Isdndll in 'Isdndll.pas',
  MyFunction in 'MyFunction.pas',
  No7Dll in 'No7Dll.pas',
  tce132 in 'tce132.pas',
  dmdm in 'dmdm.pas' {dm: TDataModule},
  frm_login in 'frm_login.pas' {frmlogin},
  frm_set in 'frm_set.pas' {frmset},
  frm_logout in 'frm_logout.pas' {frmlogout},
  frminfo in 'frminfo.pas' {Frm_Info};

{$R *.res}
function FindTask(ExeFileName:string):integer;
const 
  PROCESS_TERMINATE = $0001; 
var
  ContinueLoop: BOOLean;
  FSnapshotHandle: THandle; 
  FProcessEntry32: TProcessEntry32;
  i:integer;
begin
  i:=0;
  FSnapshotHandle := CreateToolhelp32Snapshot(TH32CS_SNAPPROCESS, 0); 
  FProcessEntry32.dwSize := SizeOf(FProcessEntry32); 
  ContinueLoop := Process32First(FSnapshotHandle, FProcessEntry32); 

  while Integer(ContinueLoop) <> 0 do
  begin
    if ((UpperCase(ExtractFileName(FProcessEntry32.szExeFile))=UpperCase(ExeFileName)) or
        (UpperCase(FProcessEntry32.szExeFile)=UpperCase(ExeFileName))) then
    i:=i+1;
    ContinueLoop := Process32Next(FSnapshotHandle, FProcessEntry32);
  end;            
  CloseHandle(FSnapshotHandle);
  result:=i;
end;

begin
  Application.Initialize;
  if (FindTask('CallCenterDC.e')>1) or (FindTask('CallCenterDC.exe')>1) then
  begin
    application.MessageBox('河南航天金穗呼叫中心底层服务已经运行！','系统提示',MB_ICONASTERISK); 
    application.Terminate;
    exit;
  end;
  Application.Title := '河南航天金穗呼叫中心底层服务';
  Application.CreateForm(Tdm, dm);
  Application.CreateForm(Tfrm_main, frm_main);
  Application.CreateForm(TFrm_Info, Frm_Info);
  Application.Run;
end.
