unit Isdndll;  
interface
uses GlobalConstants;
function DJISDN_InitSystem():integer; stdcall; far external 'Tc_isdn.dll';
procedure DJISDN_ExitSystem();stdcall; far external 'Tc_isdn.dll';
procedure DJISDN_GetEvent();stdcall; far external 'Tc_isdn.dll';
function DJISDN_GetChnState(nPcm:integer;nCHN:integer):integer;stdcall; far external 'Tc_isdn.dll';
function DJISDN_SetChnState(nPCm:integer;nCHN:integer;nState:integer;nParam:integer):boolean;stdcall; far external 'Tc_isdn.dll';
procedure DJISDN_GetCallerNumber(nPCM:integer;nCHN:integer; szNumber:pchar);stdcall; far external 'Tc_isdn.dll';
procedure DJISDN_GetCallerSubAddr(nPcm:integer;nCHN:integer;szSubAddr:pchar);stdcall; far external 'Tc_isdn.dll';
procedure DJISDN_GetCalleeNumber(nPCM:integer;nCHN:integer;szNumber:pchar);stdcall; far external 'Tc_isdn.dll';
procedure DJISDN_GetCalleeSubAddr(nPCM:integer; nCHN:integer;szSubAddr:pchar);stdcall; far external 'Tc_isdn.dll';
function DJISDN_GetCalloutChn(nPcm:pointer;nCHN:pointer):boolean;stdcall; far external 'Tc_isdn.dll';
function DJISDN_Callout(nPCM:integer;nCHN:integer;szCalleeNumber:pchar;szCalleeSubAddr:pchar;szCallerNumber:pchar;szcallerSubAddr:pchar):integer;stdcall; far external 'Tc_isdn.dll';
function DJISDN_GetCalloutResult(nPCM:integer;nCHN:integer):integer;stdcall; far external 'Tc_isdn.dll';
function DJISDN_GetDisconnectReason(nPCM:integer;nCHN:integer):integer;stdcall; far external 'Tc_isdn.dll';
function DJISDN_SendRawDAta(nPCM:integer;pRawData:pointer;nLen:integer):boolean;stdcall; far external 'Tc_isdn.dll';
procedure DJISDN_SetSystemMode(nMode:integer;nParam:integer);stdcall; far external 'Tc_isdn.dll';
implementation
end.
