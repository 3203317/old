unit E1Fax;

interface
const MAX_FAX_CARD_NUM	=16;
const MAX_FAX_CHANNEL_NUM	=(4*MAX_FAX_CARD_NUM) ;
const MAX_VOICE_CHANNEL_NUM =128;
const HIGH_RESOLUTION	=1;
const LOW_RESOLUTION	=0;
const DOT_0_IS_WHITE    =  0;
const DOT_1_IS_WHITE    =  1;
const FAX_FINISH_ALLPAGE  =1;
const FAX_FINISH_ONEPAGE=  2;
const FAX_SENDING=  0;
const FAX_OPENFILEFAIL= -3;
const FAX_ERRORCLOSE= -2;

function DJFax_DriverReady(wBuffSize:WORD):integer;stdcall; far external 'tce1_32.dll';
procedure DJFax_DisableCard();stdcall; far external 'tce1_32.dll';
procedure DJFax_GetSysInfo(TmpInfo:pointer); stdcall; far external 'tce1_32.dll';
function DJFax_GetTotalFaxChnl():integer;stdcall; far external 'tce1_32.dll';

function DJFax_SetLocalID (wChnl:WORD;s:PChar ):integer;stdcall; far external 'tce1_32.dll';
function DJFax_GetLocalID (wChnl:WORD;s:PChar ):integer;stdcall; far external 'tce1_32.dll';
function DJFax_SetDialNo(wChnl:WORD;DialNo:PChar):integer;stdcall; far external 'tce1_32.dll';
function DJFax_SetValue(wChnl:WORD;s:pointer;Position:WORD;Count:WORD):integer;stdcall; far external 'tce1_32.dll';
function DJFax_GetValue(wChnl:WORD; s:pointer;Position:WORD;Count:WORD):integer;stdcall; far external 'tce1_32.dll';

function DJFax_SetLink(wFaxChnl:WORD;trunkID:WORD):integer;stdcall; far external 'tce1_32.dll';
function DJFax_ClearLink (wFaxChnl:WORD;trunkID:WORD):integer;stdcall; far external 'tce1_32.dll';
function DJFax_SelfCheckSetLink(wFaxChnl:WORD):integer;stdcall; far external 'tce1_32.dll';
function DJFax_SelfCheckBreakLink(wFaxChnl:WORD):integer;stdcall; far external 'tce1_32.dll';

function DJFax_GetRcvBytes(wChnl:WORD):longInt;stdcall; far external 'tce1_32.dll';
function DJFax_GetSendBytes(wChnl:WORD):longInt;stdcall; far external 'tce1_32.dll';

function DJFax_GetOneFreeFaxChnl():integer;stdcall; far external 'tce1_32.dll';
function DJFax_GetFaxChnlOfVoiceChnl(trunkID:WORD):integer; stdcall; far external 'tce1_32.dll';
function DJFax_GetVoiceChnlOfFaxChnl(trunkID:WORD):integer;stdcall; far external 'tce1_32.dll';
function DJFax_GetMiddleStatus(wChnl:WORD):integer;stdcall; far external 'tce1_32.dll';
function DJFax_GetErrCode(wChnl:WORD):integer;stdcall; far external 'tce1_32.dll';
function DJFax_GetErrPhase(wChnl:WORD):integer;stdcall; far external 'tce1_32.dll';
function DJFax_GetErrSubst(wChnl:WORD):integer;stdcall; far external 'tce1_32.dll';

procedure DJFax_StopFax(wChnl:WORD);stdcall; far external 'tce1_32.dll';


function DJFax_SendFaxFile(wChnl:WORD;FileName:PChar):integer;stdcall; far external 'tce1_32.dll';
function DJFax_CheckTransmit(wChnl:WORD):integer;stdcall; far external 'tce1_32.dll';
function DJFax_RcvFaxFile(wChnl:WORD;FileName:PChar):integer;stdcall; far external 'tce1_32.dll';

function DJFax_SetResolution(wChnl:WORD;ResolutionFlag:integer):integer;stdcall; far external 'tce1_32.dll';

//--------------------------------------------------------------------------
//Add by WGL 1998.11.26


//function DJFax_SetModemCtrol (  wChnl:WORD):integer; stdcall; far external 'tce1_32.dll';
//function DJFax_GetModemCtrol ( wChnl:WORD):integer;stdcall; far external 'tce1_32.dll';
//function DJFax_GetLineStatus(  wChnl:WORD):integer;stdcall; far external 'tce1_32.dll';
//function DJFax_GetModemStatus(  wChnl:WORD):integer;stdcall; far external 'tce1_32.dll';
//function DJFax_GetSendBufSize(  wChnl:WORD):integer; stdcall; far external 'tce1_32.dll';


//function DJFax_ReadModemBuf( wChnl:WORD;DataBuf:PChar):integer;stdcall; far external 'tce1_32.dll';
//function DJFax_WriteModemBuf( wChnl:WORD;DataBuf:PChar;DataLen:integer):integer;stdcall; far external 'tce1_32.dll';


function DJCvt_InitConvert():integer;stdcall; far external 'tce1_32.dll';
procedure DJCvt_DisableConvert();stdcall; far external 'tce1_32.dll';
function DJCvt_Open(wChnl:WORD;cbFaxFileName:PChar;cbResolution:BYTE;
            wPageLineNo:WORD):integer; stdcall; far external 'tce1_32.dll';
function DJCvt_Close(wChnl:WORD):integer;stdcall; far external 'tce1_32.dll';
function DJCvt_DotLine(wChnl:WORD;cbDotStr:PChar;wDotSize:WORD;wDotFlag:WORD):integer;stdcall; far external 'tce1_32.dll';
function DJCvt_TextLine(wChnl:WORD;cbTextStr:PChar):integer; stdcall; far external 'tce1_32.dll';
function DJCvt_BmpImage(wChnl:WORD;cbImageStr:PChar):integer;stdcall; far external 'tce1_32.dll';
function DJCvt_BmpFile(wChnl:WORD;cbBmpFileName:PChar):integer; stdcall; far external 'tce1_32.dll';






implementation

end.
