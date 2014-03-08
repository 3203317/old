unit frm_login;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, StdCtrls;

type
  Tfrmlogin = class(TForm)
    GroupBox1: TGroupBox;
    Edit1: TEdit;
    Button1: TButton;
    procedure Button1Click(Sender: TObject);
  private
    { Private declarations }
  public
    { Public declarations }
  end;

var
  frmlogin: Tfrmlogin;

implementation

uses Unit1, frm_set;

{$R *.dfm}

procedure Tfrmlogin.Button1Click(Sender: TObject);
begin
  frmlogin.Close;
  frmlogin.Hide;
  frm_main.BtnUtilities.Enabled := False;
  frm_main.BtnSecurity.Enabled := true;
  frm_main.ZxStatusOpen;
  frmset := Tfrmset.Create(nil);
  frmset.ShowModal;
end;

end.
