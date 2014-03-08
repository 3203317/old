unit frm_set;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, StdCtrls, ComCtrls, ExtCtrls, IniFiles;

type
  Tfrmset = class(TForm)
    Panel1: TPanel;
    Panel2: TPanel;
    Button1: TButton;
    Button2: TButton;
    PageControl1: TPageControl;
    TabSheet1: TTabSheet;
    Label1: TLabel;
    Label2: TLabel;
    Label3: TLabel;
    Label4: TLabel;
    Label5: TLabel;
    fwzxEdit: TEdit;
    ywslEdit: TEdit;
    tsjyEdit: TEdit;
    gxzxEdit: TEdit;
    TabSheet2: TTabSheet;
    TabSheet4: TTabSheet;
    ywslCHK: TCheckBox;
    TelywslWZ: TEdit;
    tsjyCHK: TCheckBox;
    TeltsjyWZ: TEdit;
    fwzxCHK: TCheckBox;
    TelfwzxWZ: TEdit;
    TabSheet5: TTabSheet;
    chkRecordFile: TCheckBox;
    TabSheet7: TTabSheet;
    RecordSavePath: TEdit;
    TabSheet3: TTabSheet;
    Label6: TLabel;
    SysLogPath: TEdit;
    Label7: TLabel;
    HRLogPath: TEdit;
    Label8: TLabel;
    HCLogPath: TEdit;
    Button3: TButton;
    initZJ: TEdit;
    Label10: TLabel;
    Label9: TLabel;
    initZX: TEdit;
    Button4: TButton;
    Label11: TLabel;
    Label12: TLabel;
    zjzxEdit: TEdit;
    swzxEdit: TEdit;
    procedure FormClose(Sender: TObject; var Action: TCloseAction);
    procedure Button2Click(Sender: TObject);
    procedure FormCreate(Sender: TObject);
    procedure fwzxCHKClick(Sender: TObject);
    procedure ywslCHKClick(Sender: TObject);
    procedure tsjyCHKClick(Sender: TObject);
    procedure Button1Click(Sender: TObject);
    procedure FormKeyDown(Sender: TObject; var Key: Word;
      Shift: TShiftState);
    procedure chkRecordFileClick(Sender: TObject);
    procedure Button3Click(Sender: TObject);
    procedure Button4Click(Sender: TObject);
    procedure initZJKeyPress(Sender: TObject; var Key: Char);
    procedure initZXKeyPress(Sender: TObject; var Key: Char);
  private
    { Private declarations }
  public
    { Public declarations }
  end;

var
  frmset: Tfrmset;

implementation

uses Unit1;

{$R *.dfm}

procedure Tfrmset.FormClose(Sender: TObject; var Action: TCloseAction);
begin
  Release;
end;

procedure Tfrmset.Button2Click(Sender: TObject);
begin
  Close;
end;

procedure Tfrmset.FormCreate(Sender: TObject);
begin
  fwzxEdit.Text := FWZXZX;
  ywslEdit.Text := YWSLZX;
  tsjyEdit.Text := TSJYZX;
  gxzxEdit.Text := GXZX;

  TelfwzxWZ.Text := TelFWZXZJNumber;
  TelywslWZ.Text := TelYWSLZJNumber;
  TeltsjyWZ.Text := TelTSJYZJNumber;

  if TelFWZXZJ = 0 then
    begin
      fwzxCHK.Checked := False;
      TelfwzxWZ.Enabled := False;
      TelfwzxWZ.ReadOnly := True;
    end
  else
    begin                      
      fwzxCHK.Checked := True;
      TelfwzxWZ.Enabled := True;
      TelfwzxWZ.ReadOnly := False;
    end;
    
  if TelYWSLZJ = 0 then
    begin
      ywslCHK.Checked := False;
      TelywslWZ.Enabled := False;
      TelywslWZ.ReadOnly := True;
    end
  else
    begin                      
      ywslCHK.Checked := True;
      TelywslWZ.Enabled := True;
      TelywslWZ.ReadOnly := False;
    end;

  if TelTSJYZJ = 0 then
    begin
      tsjyCHK.Checked := False;
      TeltsjyWZ.Enabled := False;
      TeltsjyWZ.ReadOnly := True;
    end
  else
    begin                      
      tsjyCHK.Checked := True;
      TeltsjyWZ.Enabled := True;
      TeltsjyWZ.ReadOnly := False;
    end;

  if Sys.TelRecord = 0 then
    begin
      chkRecordFile.Checked := False;
      RecordSavePath.Enabled := False;
      RecordSavePath.ReadOnly := True;
    end
  else
    begin
      chkRecordFile.Checked := True;
      RecordSavePath.Enabled := True;
      RecordSavePath.ReadOnly := False;
    end;

  RecordSavePath.Text := Sys.RecordSavePath;

  SysLogPath.Text := Sys.SaveLogPath;
  HRLogPath.Text := Sys.SaveHRPath;
  HCLogPath.Text := Sys.SaveHCPath;
end;

procedure Tfrmset.fwzxCHKClick(Sender: TObject);
begin
  if fwzxCHK.Checked then
    begin
      TelfwzxWZ.Enabled := True;
      TelfwzxWZ.ReadOnly := False;
    end
  else
    begin                         
      TelfwzxWZ.Enabled := False;
      TelfwzxWZ.ReadOnly := True;
    end;
end;

procedure Tfrmset.ywslCHKClick(Sender: TObject);
begin
  if ywslCHK.Checked then
    begin
      TelywslWZ.Enabled := True;
      TelywslWZ.ReadOnly := False;
    end
  else
    begin                         
      TelywslWZ.Enabled := False;
      TelywslWZ.ReadOnly := True;
    end;
end;

procedure Tfrmset.tsjyCHKClick(Sender: TObject);
begin
  if tsjyCHK.Checked then
    begin
      TeltsjyWZ.Enabled := True;
      TeltsjyWZ.ReadOnly := False;
    end
  else
    begin                         
      TeltsjyWZ.Enabled := False;
      TeltsjyWZ.ReadOnly := True;
    end;
end;

procedure Tfrmset.Button1Click(Sender: TObject);
var
  tmpif: TIniFile;
begin
  tmpif := TIniFile.Create(ExtractFilePath(Application.ExeName) + IniFile);
  tmpif.WriteString('set','FWZXZX',Trim(fwzxEdit.Text));
  tmpif.WriteString('set','YWSLZX',Trim(ywslEdit.Text));
  tmpif.WriteString('set','TSJYZX',Trim(tsjyEdit.Text));
  tmpif.WriteString('set','GXZX',Trim(gxzxEdit.Text));

  
  Unit1.FWZXZX := Trim(fwzxEdit.Text);//������ѯ
  Unit1.YWSLZX := Trim(ywslEdit.Text);//ҵ������
  Unit1.TSJYZX := Trim(tsjyEdit.Text);//Ͷ�߽���
  Unit1.GXZX := Trim(gxzxEdit.Text);//������ϯ

  if fwzxCHK.Checked then
    begin
      tmpif.WriteInteger('set','TelFWZXZJ',1);//������ѯת���ǻ��
      Unit1.TelFWZXZJ := 1;
    end
  else
    begin
      tmpif.WriteInteger('set','TelFWZXZJ',0);
      Unit1.TelFWZXZJ := 0;
    end;

  tmpif.WriteString('set','TelFWZXZJNumber',Trim(TelfwzxWZ.Text));//������ѯת�ӵĵ绰
  Unit1.TelFWZXZJNumber := Trim(TelfwzxWZ.Text);
  
  if ywslCHK.Checked then
    begin
      tmpif.WriteInteger('set','TelYWSLZJ',1);//ҵ������ת��
      Unit1.TelYWSLZJ := 1;
    end
  else
    begin
      tmpif.WriteInteger('set','TelYWSLZJ',0);
      Unit1.TelYWSLZJ := 0;
    end;

  tmpif.WriteString('set','TelYWSLZJNumber',Trim(TelywslWZ.Text));//ҵ������ת�Ӻ���
  Unit1.TelYWSLZJNumber := Trim(TelywslWZ.Text);

  if tsjyCHK.Checked then
    begin
      tmpif.WriteInteger('set','TelTSJYZJ',1);
      Unit1.TelTSJYZJ := 1;
    end
  else
    begin
      tmpif.WriteInteger('set','TelTSJYZJ',0);
      Unit1.TelTSJYZJ := 0;
    end;

  tmpif.WriteString('set','TelTSJYZJNumber',Trim(TeltsjyWZ.Text));
  Unit1.TelTSJYZJNumber := Trim(TeltsjyWZ.Text);

  if chkRecordFile.Checked then
      tmpif.WriteInteger('set','TelRecord',1)
  else
      tmpif.WriteInteger('set','TelRecord',0);


  tmpif.WriteString('set','RecordSavePath',Trim(RecordSavePath.Text));//�绰¼����·��
  tmpif.WriteString('set','SaveLogPath',Trim(SysLogPath.Text));//ϵͳ��־·��
  tmpif.WriteString('set','SaveHRPath',Trim(HRLogPath.Text));//������־·��
  tmpif.WriteString('set','SaveHCPath',Trim(HCLogPath.Text));//������־·��

  tmpif.Free;

  frm_main.BtnSecurity.Enabled := true;
  frm_main.BtnUtilities.Enabled := false;
  frmset.Close;
end;

procedure Tfrmset.FormKeyDown(Sender: TObject; var Key: Word;
  Shift: TShiftState);
begin
  if key = 27 then
    ShowMessage('1');
end;

procedure Tfrmset.chkRecordFileClick(Sender: TObject);
begin
  if chkRecordFile.Checked then
    begin
      RecordSavePath.Enabled := True;
      RecordSavePath.ReadOnly := False;
    end
  else
    begin
      RecordSavePath.Enabled := False;
      RecordSavePath.ReadOnly := True;
    end;
end;

procedure Tfrmset.Button3Click(Sender: TObject);
begin
try
  if Trim(initZJ.Text) <> '' then
    begin
      frm_main.TrunkReset(StrToInt(Trim(initZJ.Text)));
    end;
except
  on E:Exception do
      frm_main.SaveSysLog('0 Button3Click '+ e.Message);
end;
end;

procedure Tfrmset.Button4Click(Sender: TObject);
begin
try
  if Trim(initZX.Text) <> '' then
    begin
      frm_main.UserReset(StrToInt(Trim(initZX.Text))-1);
    end;
except
  on E:Exception do
      frm_main.SaveSysLog('0 Button4Click '+ e.Message);
end;
end;

procedure Tfrmset.initZJKeyPress(Sender: TObject; var Key: Char);
begin
  if not (key in ['0'..'9','.',#13,#8]) then
    key:=#0;
end;

procedure Tfrmset.initZXKeyPress(Sender: TObject; var Key: Char);
begin
  if not (key in ['0'..'9','.',#13,#8]) then
    key:=#0;
end;

end.
