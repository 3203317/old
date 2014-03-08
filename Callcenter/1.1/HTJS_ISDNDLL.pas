unit HTJS_ISDNDLL;
interface
uses GlobalConstants,isdndll;

function HTJS_ISDN_InitSystem():integer; 
procedure HTJS_ISDN_ExitSystem();
procedure HTJS_ISDN_GetEvent();
function HTJS_ISDN_GetChnState(nPcm:integer;nCHN:integer):integer;
function HTJS_ISDN_SetChnState(nPCm:integer;nCHN:integer;nState:integer;nParam:integer):boolean;
procedure HTJS_ISDN_GetCallerNumber(nPCM:integer;nCHN:integer; szNumber:pchar);
procedure HTJS_ISDN_GetCallerSubAddr(nPcm:integer;nCHN:integer;szSubAddr:pchar);
procedure HTJS_ISDN_GetCalleeNumber(nPCM:integer;nCHN:integer;szNumber:pchar);
procedure HTJS_ISDN_GetCalleeSubAddr(nPCM:integer; nCHN:integer;szSubAddr:pchar);
function HTJS_ISDN_GetCalloutChn(nPcm:pointer;nCHN:pointer):boolean;
function HTJS_ISDN_Callout(nPCM:integer;nCHN:integer;szCalleeNumber:pchar;szCalleeSubAddr:pchar;szCallerNumber:pchar;szcallerSubAddr:pchar):integer;
function HTJS_ISDN_GetCalloutResult(nPCM:integer;nCHN:integer):integer;
function HTJS_ISDN_GetDisconnectReason(nPCM:integer;nCHN:integer):integer;
function HTJS_ISDN_SendRawDAta(nPCM:integer;pRawData:pointer;nLen:integer):boolean;
procedure HTJS_ISDN_SetSystemMode(nMode:integer;nParam:integer);
implementation
uses SysUtils;
function HTJS_ISDN_InitSystem():integer; 
begin
    if CardDriver_virtual then begin
        Result := 1;
    end else begin
        Result := DJISDN_InitSystem;
    end;
end;
procedure HTJS_ISDN_ExitSystem();
begin
    if CardDriver_virtual then begin
    end else begin
	DJISDN_ExitSystem;
    end;
end;
procedure HTJS_ISDN_GetEvent();
begin
    if CardDriver_virtual then begin
    end else begin
	DJISDN_GetEvent;       
    end;
end;
function HTJS_ISDN_GetChnState(nPcm:integer;nCHN:integer):integer;
begin
    if CardDriver_virtual then begin
        Result := CALLEE_WAIT_ANSWER;
    end else begin
        Result := DJISDN_GetChnState(nPcm,nCHN);
    end;
end;
function HTJS_ISDN_SetChnState(nPCm:integer;nCHN:integer;nState:integer;nParam:integer):boolean;
begin
    if CardDriver_virtual then begin
        Result := True;
    end else begin
        Result := DJISDN_SetChnState(nPCm,nCHN,nState,nParam);
    end;
end;
procedure HTJS_ISDN_GetCallerNumber(nPCM:integer;nCHN:integer; szNumber:pchar);
begin
    if CardDriver_virtual then begin
      strpcopy(szNumber,'12345678');
    end else begin
	DJISDN_GetCallerNumber(nPCM,nCHN,szNumber);
    end;
end;
procedure HTJS_ISDN_GetCallerSubAddr(nPcm:integer;nCHN:integer;szSubAddr:pchar);
begin
    if CardDriver_virtual then begin
      strpcopy(szSubAddr,'');
    end else begin
        DJISDN_GetCallerSubAddr(nPcm,nCHN,szSubAddr);
    end;
end;
procedure HTJS_ISDN_GetCalleeNumber(nPCM:integer;nCHN:integer;szNumber:pchar);
begin
    if CardDriver_virtual then begin
    end else begin
        DJISDN_GetCalleeNumber(nPCM,nCHN,szNumber);
    end;
end;
procedure HTJS_ISDN_GetCalleeSubAddr(nPCM:integer; nCHN:integer;szSubAddr:pchar);
begin
    if CardDriver_virtual then begin
    end else begin
        DJISDN_GetCalleeSubAddr(nPCM,nCHN,szSubAddr);
    end;
end;
function HTJS_ISDN_GetCalloutChn(nPcm:pointer;nCHN:pointer):boolean;
begin
    if CardDriver_virtual then begin
        Result := True;
    end else begin
        Result := DJISDN_GetCalloutChn(nPcm,nCHN);
    end;
end;
function HTJS_ISDN_Callout(nPCM:integer;nCHN:integer;szCalleeNumber:pchar;szCalleeSubAddr:pchar;szCallerNumber:pchar;szcallerSubAddr:pchar):integer;
begin
    if CardDriver_virtual then begin
        Result := 1;
    end else begin
        Result := DJISDN_Callout(nPCM,nCHN,szCalleeNumber,szCalleeSubAddr,szCallerNumber,szcallerSubAddr);
    end;
end;
function HTJS_ISDN_GetCalloutResult(nPCM:integer;nCHN:integer):integer;
begin
    if CardDriver_virtual then begin
        Result := 1;
    end else begin
        Result := DJISDN_GetCalloutResult(nPCM,nCHN);
    end;
end;
function HTJS_ISDN_GetDisconnectReason(nPCM:integer;nCHN:integer):integer;
begin
    if CardDriver_virtual then begin
        Result := 1;
    end else begin
        Result := DJISDN_GetDisconnectReason(nPCM,nCHN);
    end;
end;
function HTJS_ISDN_SendRawDAta(nPCM:integer;pRawData:pointer;nLen:integer):boolean;
begin
    if CardDriver_virtual then begin
        Result := True;
    end else begin
        Result := DJISDN_SendRawDAta(nPCM,pRawData,nLen);
    end;
end;
procedure HTJS_ISDN_SetSystemMode(nMode:integer;nParam:integer);
begin
    if CardDriver_virtual then begin
    end else begin
	DJISDN_SetSystemMode(nMode,nParam);	
    end;
end;
end.
