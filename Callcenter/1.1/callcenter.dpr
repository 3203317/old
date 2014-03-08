program callcenter;

uses
  Forms,
  LightweightCTI in 'LightweightCTI.pas',
  GlobalConstants in 'GlobalConstants.pas',
  TextLoger in 'TextLoger.pas',
  D641XCTICardDriver in 'D641XCTICardDriver.pas',
  D641XChannel in 'D641XChannel.pas',
  XMLParse in 'XMLParse.pas',
  tce132 in 'tce132.pas',
  Isdndll in 'Isdndll.pas',
  HTJS_ISDNDLL in 'HTJS_ISDNDLL.pas',
  HTJS_Tce132 in 'HTJS_Tce132.pas',
  MainFrm in 'MainFrm.pas' {Mainform},
  UdpBroadCast in 'UdpBroadCast.pas',
  D641XChannel_callback in 'D641XChannel_callback.pas';

{$R *.res}

begin
  Application.Initialize;
  Application.CreateForm(TMainform, Mainform);
  Application.Run;
end.
