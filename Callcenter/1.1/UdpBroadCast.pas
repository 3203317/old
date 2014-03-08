unit UdpBroadCast;

interface

uses
  Classes, Dialogs, IdUDPClient, IdAntiFreezeBase, IdAntiFreeze, IdUDPServer,
  IdSocketHandle;

type
   TUdpConnectEvent = procedure(Sender: TObject; AMsg: string) of object;

type
  TUdpServerClient = class(TComponent)
  private
    FActive : boolean;
    FOnClientConnect: TUdpConnectEvent;
    FOnClientDisconnect : TUdpConnectEvent;
    IdUdpServer1: TIdUDPServer;
    IdUdpClient1: TIdUDPClient;
    //IdAntiFreeze1: TIdAntiFreeze;
  public
    procedure StartListen(APort: integer);
    procedure StopListen;
    function SendInfo(AIp: string; Aport: integer; AMsg: string): boolean;
    function BroadCastInfo(AMsg: string; Aport: integer): boolean;
    procedure IdUDPServer1UDPRead(Sender: TObject; AData: TStream;
      ABinding: TIdSocketHandle);
    constructor create; reintroduce;
    destructor Destroy; override;
    property active : boolean read FActive;
  published
    property OnClientConnect : TUdpConnectEvent Read FOnClientConnect write FOnClientConnect;
    Property OnClientDisconnect: TUdpConnectEvent Read FOnClientDisconnect write FOnClientDisconnect;
  end;

var
   FUdpManager : TUdpServerClient;
function UdpManager : TUdpServerClient;

implementation
uses  GlobalConstants;
function UdpManager : TUdpServerClient;
begin
  if not Assigned(FUdpManager) then
  begin
     FUdpManager := TUdpServerClient.create;
  end;
  result := FUdpManager;
end;


{ TUdpServerClient }

function TUdpServerClient.SendInfo(AIp: string; Aport: integer;
  AMsg: string): boolean;
begin
  Result := true;
  try
    IdUdpClient1.Send(AIp,Aport,AMsg);
  except
    Result := false;
  end;
end;

function TUdpServerClient.BroadCastInfo(AMsg: string;
  Aport: integer): boolean;
begin
  Result := true;
  try
    IdUdpClient1.Broadcast(AMsg,APort);
  except
    Result := false;
  end;
end;

constructor TUdpServerClient.create;
begin
   IdUdpServer1 := TIdUdpServer.Create(nil);
   IdUdpServer1.OnUDPRead := IdUDPServer1UDPRead;
   IdUdpClient1 := TIdUdpClient.Create(nil);
   IdUdpClient1.BroadcastEnabled := true;
end;

destructor TUdpServerClient.Destroy;
begin
  if IdUdpServer1.Active then
    IdUdpServer1.Active := False;
  IdUdpServer1.Free;
  IdUdpClient1.Free;  
end;

procedure TUdpServerClient.IdUDPServer1UDPRead(Sender: TObject;
  AData: TStream; ABinding: TIdSocketHandle);
var
   DataStringStream : TStringStream;
   Head,Body, RecvStr : string;
begin
  DataStringStream := TStringStream.Create('');
  try
    DataStringStream.CopyFrom(AData,Adata.Size);
    RecvStr := DataStringStream.DataString;
    Head := Copy(RecvStr,1,2);
    Body := Copy(RecvStr,3,Length(RecvStr) - 2);
    if Head = ptl_UserChannelOn then
    begin
      if Assigned(OnClientConnect) then
         OnClientConnect(self, Body);
    end;
    if Head = ptl_UserChannelOff then
    begin
      if Assigned(OnClientDisconnect) then
        OnClientDisconnect(self, Body);
    end;
    //showMessage(DataStringStream.DataString);
   // Memo1.Lines.Add(DataStringStream.DataString);
  finally
    DataStringStream.Free;
  end;
end;

procedure TUdpServerClient.StartListen(APort: integer);
begin
  IdUdpServer1.DefaultPort := APort;
  IdUdpServer1.Active := true;
  FActive := true;
end;

procedure TUdpServerClient.StopListen;
begin
  IdUdpServer1.Active := false;
  FActive := false;
end;

initialization
  Classes.RegisterClass(TUdpServerClient);

finalization
  Classes.UnRegisterClass(TUdpServerClient);


end.
