unit frm_logout;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, StdCtrls;

type
  Tfrmlogout = class(TForm)
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
  frmlogout: Tfrmlogout;

implementation

uses Unit1, tce132, Isdndll;

{$R *.dfm}

procedure Tfrmlogout.Button1Click(Sender: TObject);
begin
  if frm_main.Timer1.Enabled then
    begin
      frm_main.Timer1.Enabled := False;
      DJISDN_ExitSystem;
      DJSys_DisableCard;

      frm_main.SaveSysLog('1 ÏµÍ³ÍË³ö');
      Application.Terminate;
    end
  else
    Application.Terminate;
end;

end.
