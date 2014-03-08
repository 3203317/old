unit tce132;

interface
uses
    Windows ;
//implementation

const SS1=0;
const DSS1=1;
const SS7=7;
const PCM_TRUNK_NUM = 30;
const _MFC_BUF_LEN = 31;
const _CALL_BUF_LEN = 31;
const _DTMF_BUF_LEN  = 31;
const _PMT_VOICE_BUF_LEN = 31;

//const MFC_BUF_LEN = 31
//const CALL_BUF_LEN = 31
//const DTMF_BUF_LEN  = 31
//const PMT_VOICE_BUF_LEN = 31


const _CONVERSATION_RECORD_BEGIN_VOICE_ID = 20;





//    TrunkStep
const	Step_Free=0;                            	//0 for import and export
const	Step_Used=1;				//1 for import
const	Step_RecvCall=2;				//2
const	Step_RecvMfc=3;				//3
const	Step_StopMfc=4;				//4
const	Step_RecvFail=5;				//5
const	Step_Delay=6; 				//6
const	Step_Wait=7;				//7
const	Step_Connect=8;				//8
const	Step_ForeHangOff=9;			//9
const	Step_BackHangOff=10;			//10
const	Step_Block=11; 				//11
const	Step_OverTime=12;				//12
const	Step_Disable=13;				//13	//disable send and receive phone
const	Step_ReadyDial=14; 			//14	//for export
const	Step_DialCall=15;				//15
const	Step_DialMfc=16;				//16
const	Step_DialStop=17;				//17
const	Step_DialFail=18;				//18
const	Step_FreeRes=19;				//19
//   end of trunkstep


type
     TrunkType = (
	Type_Import,
	Type_Export
     );

type
    DialStatus = (
	DS_NoDial,      //0
	DS_Dialing, 	//1
	DS_Busy,	//2
	DS_Wait,	//3
	DS_Connect, 	//4
	DS_LineError,	//5
	DS_NoUser,	//6
	DS_OverTime 	//7
    );

type
    VoiceResourcesType = (
	Res_NoRes,    	        //0
	Res_File,		//1
	Res_Xms 		//2
    );

type
    VoiceOperatorType = (
	OP_NoOperator,          //0
	OP_Play, 		//1
	OP_LoopPlay,            //2
	OP_Record		//3
    );

type
    PstnErr = -20 .. 0;
//enum error
{type
    PstnErr = (
	_ERR_OK 				= 0,
	_ERR_XmsAllocError			= -1,
	_ERR_MemAllocError			= -2,
	_ERR_CanNotOpenFile                     = -3,
	_ERR_NoTrunkRes 			= -4,
	_ERR_NoUserRes				= -5,
	_ERR_NoVoiceRes 			= -6,
	_ERR_InvalidID				= -7,
	_ERR_ReConnDifferChannel	        = -8,
	_ERR_OperateTypeErr 		        = -9,
	_ERR_InvalidTrunkStep		        = -10,
	_ERR_NoConnChannel			= -11,
	_ERR_INIsetErr				= -12,
	_ERR_NullPtr				= -13,
	_ERR_NoMvipRes				= -14,
	_ERR_InvalidDTMF			= -15,
        _ERR_LastDtmfNotSendEnd     = -16,
	_ERR_OpenTCE1Device			= -17,		//	add by H.J.N
	_ERR_CheckHardware			= -18,		//	add by H.J.N
	_ERR_PromptFile				= -19,		//	add by H.J.N
	_ERR_DirectPlayXms			= -20		//	add by H.J.N
    );
}
const _DL_FORE_FREE = $0b;	//A
const _DL_FORE_USED = $03;	//B
const _DL_FORE_CONFIRM = $03;	//C
const _DL_FORE_CONNECT = $03;	//D
const _DL_FORE_FOREHANGOFF = $0b;	//E
const _DL_FORE_BUSY = $0b;	//F
const _DL_FORE_BACKHANGOFF = $03;	//C

const _DL_BACK_FREE = $0b;	//A 0x08|0x03
const _DL_BACK_USED = $0b;	//B
const _DL_BACK_CONFIRM = $0f;	//C 0x0c|0x03
const _DL_BACK_CONNECT = $07;	//D 0x04|0x03
const _DL_BACK_BACKHANGOFF = $0f;	//C
const _DL_BACK_FOREHANGOFF = $0f;	//F
const _DL_BACK_BLOCK = $0f;	//F

const MFC_BACK_A1 = 16;	//request next
const MFC_BACK_A2 = 17;	//request from home
const MFC_BACK_A3 = 18;	//change to B signal
const MFC_BACK_A4 = 19;	//busy
const MFC_BACK_A5 = 20;	//space No.
const MFC_BACK_A6 = 21;	//request KA and caller

const MFC_BACK_KB1 = 16; //user is free
const MFC_BACK_KB2 = 17; //no used
const MFC_BACK_KB3 = 18; //no used
const MFC_BACK_KB4 = 19; //user is busy
const MFC_BACK_KB5 = 20; //space No.
const MFC_BACK_KB6 = 21; //user is free and controled by Caller

const MFC_BACK_KD1 = 1;	//long half auto call
const MFC_BACK_KD2 = 2;	//long auto call
const MFC_BACK_KD3 = 3;	//city phone
const MFC_BACK_KD4 = 4;	//city FAX or DATA
const MFC_BACK_KD5 = 5;	//half auto confirm caller number
const MFC_BACK_KD6 = 6;	//test call

const MFC_FORE_KA1 = 1;
const MFC_FORE_KA2 = 2;
const MFC_FORE_KA3 = 3;
const MFC_FORE_KA4 = 4;
const MFC_FORE_KA5 = 5;
const MFC_FORE_KA6 = 6;
const MFC_FORE_KA7 = 7;
const MFC_FORE_KA8 = 8;
const MFC_FORE_KA9 = 9;
const MFC_FORE_KA10 = 10;

//define the failure reason
const _ERR_USED_FOREHANGOFF  =  1;
const _ERR_USED_OVERTIME     =  2;
const _ERR_RCALL_OVERTIME    =  3;
const _ERR_RCALL_FOREHANGOFF =  4;
const _ERR_RCALL_TOO_LEN     =  5;
const _ERR_RMFC_FOREHANGOFF  =  6;
const _ERR_RMFC_OVERTIME     =  7;
const _ERR_RMFC_TOO_LEN      =  8;
const _ERR_RSTOP_FOREHANGOFF =  9;
const _ERR_RSTOP_OVERTIME    =  10;
const _ERR_DREDY_OVERTIME    =  11;
const _ERR_DMFC_LINEERR      =  12;
const _ERR_DMFC_BACK_A_ERR1  =  13;
const _ERR_DMFC_BACK_A_ERR2  =  14;
const _ERR_DMFC_BACK_A_ERR3  =  15;
const _ERR_DMFC_OVERTIME     =  16;
const _ERR_DCALL_LINEERR     =  17;
const _ERR_DCALL_BACK_A_ERR1 =  18;
const _ERR_DCALL_BACK_A_ERR2 =  19;
const _ERR_DCALL_OVERTIME    =  20;
const _ERR_DSTOP_LINEERR     =  21;
const _ERR_DSTOP_NOUSER      =  22;
const _ERR_DSTOP_BUSY        =  23;
const _ERR_DSTOP_OVERTIME    =  24;
const _ERR_RCALL_LONGSEND    =  25;


{//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//initiate the DT60 PCM Voice board}
function DJSys_EnableCard (const configFile:PCHAR; const promptVoiceIdxFile:PCHAR) : integer; stdcall; far external 'tce1_32.dll';
procedure DJSys_DisableCard; stdcall; far external 'tce1_32.dll';
procedure DJSys_PushPlay;  stdcall; far external 'tce1_32.dll';
function DJSys_EnableDtmfSend():boolean;stdcall; far external 'tce1_32.dll';

///******************************************************************************\
// *
// *	Functions for Trunk channel
// *
//\******************************************************************************/
function  DJTrk_GetTotalTrunkNum : integer;  stdcall; far external 'tce1_32.dll';
function  DJTrk_GetPcmTrunkNum :integer; stdcall; far external 'tce1_32.dll';

function  DJTrk_GetWaitTime(trunkID:integer ): WORD; stdcall; far external 'tce1_32.dll';
function  DJTrk_GetConnectVoiceTime(trunkID : integer ): WORD;  stdcall; far external 'tce1_32.dll';
function  DJTrk_GetConnectTime	(trunkID : integer) : WORD;  stdcall; far external 'tce1_32.dll';
function  DJTrk_GetDtmfTime (trunkID : integer) : WORD;  stdcall; far external 'tce1_32.dll';
function  DJTrk_GetTrunkConnectTime(trunkID : integer) : LongInt;  stdcall; far external 'tce1_32.dll';

function  DJTrk_GetPcmID(trunkID : integer ): integer;  stdcall; far external 'tce1_32.dll';

function  DJTrk_GetConnectTrunkID(trunkID : integer): integer;  stdcall; far external 'tce1_32.dll';
function  DJTrk_GetConnectUserID(trunkID : integer) : integer;  stdcall; far external 'tce1_32.dll';
function  DJTrk_GetListenUserID(trunkID : integer) : integer;  stdcall; far external 'tce1_32.dll';  
function  DJTrk_GetListenTrunkID(trunkID : integer): integer;  stdcall; far external 'tce1_32.dll';
function  DJTrk_GetTrunkPlayID(trunkID : integer): integer;  stdcall; far external 'tce1_32.dll';  
function  DJTrk_GetTrunkRecordID(trunkID : integer): integer;  stdcall; far external 'tce1_32.dll';  

function  DJTrk_CheckReady(trunkID : integer) : boolean;   stdcall;  far external 'tce1_32.dll';  
function  DJTrk_CheckConnect(trunkID : integer) : boolean;  stdcall; far external 'tce1_32.dll';  
function  DJTrk_CheckWait(trunkID : integer) : boolean;  stdcall; far external 'tce1_32.dll';  
function  DJTrk_CheckForwardHangUp(trunkID : integer) : boolean;  stdcall; far external 'tce1_32.dll';  
function  DJTrk_CheckTrunkIn(trunkID : integer) : boolean;  stdcall; far external 'tce1_32.dll';  
function  DJTrk_CheckTrunkFree(trunkID : integer) : boolean;  stdcall; far external 'tce1_32.dll';  
function  DJTrk_CheckTrunkEnable	(trunkID : integer) : boolean;  stdcall; far external 'tce1_32.dll';
function  DJTrk_CheckPlayPromptStrEnd	(trunkID : integer) : boolean;  stdcall; far external 'tce1_32.dll';
function  DJUser_CheckPlayPromptStrEnd	(userId : integer) : boolean;  stdcall; far external 'tce1_32.dll';

function  DJTrk_BackwardHangUp(trunkID : integer) : boolean;  stdcall; far external 'tce1_32.dll';
function  DJTrk_DisableTrunk(trunkID : integer) : boolean;  stdcall; far external 'tce1_32.dll';  
function  DJTrk_EnableTrunk(trunkID : integer) : boolean;  stdcall; far external 'tce1_32.dll';  

function  DJTrk_GetFreeTrunkIDForDial (pcmID : integer) : integer;  stdcall; far external 'tce1_32.dll';  
function  DJTrk_StartDial(trunkID: integer;
			  const szPhoneNum : pchar;
			  const szCallerStr) : boolean;
                stdcall; far external 'tce1_32.dll';  
procedure DJTrk_StartDial_SetParam (foreKD : BYTE; foreKA : BYTE ); stdcall; far external 'tce1_32.dll';

function  DJTrk_AppendTelNum(trunkID : integer; phoneCode : char ) : boolean;  stdcall; far external 'tce1_32.dll';  //ADD
function  DJTrk_GetDialStatus(trunkID : integer) : DialStatus;  stdcall; far external 'tce1_32.dll';  
function  trunkCheckDialIsFail(trunkID : integer) : boolean;  stdcall; far external 'tce1_32.dll';  

function  DJTrk_CheckApplyDtmf(trunkID : integer) : boolean;  stdcall; far external 'tce1_32.dll';   //these is a dtmf resource link to this trunk
function  DJTrk_ApplyDtmf(trunkID : integer) : boolean;  stdcall; far external 'tce1_32.dll';  
function  DJTrk_FreeDtmf(trunkID : integer) : boolean;  stdcall; far external 'tce1_32.dll';  

function  DJTrk_GetMfcCode(trunkID : integer) : pchar;  stdcall; far external 'tce1_32.dll';  
function  DJTrk_GetHostCode(trunkID : integer) : pchar;  stdcall; far external 'tce1_32.dll';
function  DJTrk_GetDtmfCode(trunkID : integer) : pchar;  stdcall; far external 'tce1_32.dll';
function  DJTrk_GetDtmfCodeNew(trunkID : integer) : pchar;  stdcall; far external 'tce1_32.dll';
function  DJTrk_GetReciveMfcNum(trunkID : integer) : integer;  stdcall; far external 'tce1_32.dll';
function  DJTrk_GetReciveCallerNum(trunkID : integer) : integer;  stdcall; far external 'tce1_32.dll';  
function  DJTrk_GetReciveDtmfNum(trunkID : integer) : integer;  stdcall; far external 'tce1_32.dll';
function  DJTrk_GetFirstDtmfCode(trunkID : integer) : char;  stdcall; far external 'tce1_32.dll';  
function  DJTrk_GetLastDtmfCode	(trunkID : integer) : char;  stdcall; far external 'tce1_32.dll';
function  DJTrk_InitDtmfBuf(trunkID : integer) : boolean;  stdcall; far external 'tce1_32.dll';

function  DJTrk_SendDtmfStr(trunkid:integer;sendstr:pchar):integer;stdcall; far external 'tce1_32.dll';
function  DJTrk_CheckDtmfSendEnd(trunkid:integer):boolean;stdcall; far external 'tce1_32.dll';

function  DJTrk_SetTrunkType(trunkID : integer; Tktype : TrunkType) : boolean;  stdcall; far external 'tce1_32.dll';
function  DJTrk_GetTrunkStatus(trunkID : integer) : integer;  stdcall; far external 'tce1_32.dll';


function  DJTrk_PlayPromptStr(trunkID : integer; pcPrompStr: String) : integer;  stdcall; far external 'tce1_32.dll';
function  DJUser_PlayPromptStr(trunkID : integer; pcPrompStr: String) : integer;  stdcall; far external 'tce1_32.dll';

function  DJTrk_GetTrunkKD(trunkID : integer) : BYTE;  stdcall; far external 'tce1_32.dll';  
function  DJTrk_GetTrunkKB(trunkID : integer) : BYTE;  stdcall; far external 'tce1_32.dll';  
function  DJTrk_GetTrunkForwardKA(trunkID : integer) : BYTE;  stdcall; far external 'tce1_32.dll';  
function  DJTrk_GetTrunkBackwardA(trunkID : integer) : BYTE;  stdcall; far external 'tce1_32.dll';
function  DJTrk_GetTrunkForwardMFN(trunkID : integer) : BYTE;  stdcall; far external 'tce1_32.dll';  
function  DJTrk_GetTrunkForwardDL (trunkID : integer) : byte;  stdcall; far external 'tce1_32.dll';
function  DJTrk_GetTrunkBackwardDL(trunkID : integer) : byte;  stdcall; far external 'tce1_32.dll';

function  trunkPutBackDL(trunkID : integer; backDL : BYTE) : boolean;  stdcall; far external 'tce1_32.dll';  
function  DJTrk_WaitBackwardA3(trunkID : integer) : boolean;  stdcall; far external 'tce1_32.dll';
function  DJTrk_SetTrunkKB(trunkID : integer; backKB : BYTE) : boolean;  stdcall; far external 'tce1_32.dll';
procedure  DJTrk_SetTrunkKB_SetParam (backA : BYTE);  stdcall; far external 'tce1_32.dll';  

///******************************************************************************\
// *
// *	Functions for User channel
// *
//\******************************************************************************/
function  DJUser_GetTotalUserNum : integer;  stdcall; far external 'tce1_32.dll';  
procedure DJUser_SetPowerON (userID : integer);  stdcall; far external 'tce1_32.dll';   	//default
procedure DJUser_SetPowerOFF(userID : integer);  stdcall; far external 'tce1_32.dll';  
function  DJUser_CheckPowerStatus(userID : integer) : boolean;  stdcall; far external 'tce1_32.dll';   	//if Power is ON then return TRUE

procedure DJUser_SetUserHalfPower(userID : integer);  stdcall; far external 'tce1_32.dll';  
procedure DJUser_SetUserFullPower(userID : integer);  stdcall; far external 'tce1_32.dll';  

function  DJUser_RingDetect(userID : integer) : boolean;  stdcall; far external 'tce1_32.dll';  
function  DJUser_StartRing(userID : integer) : boolean;  stdcall; far external 'tce1_32.dll';
function  DJUser_StopRing(userID : integer) : boolean;  stdcall; far external 'tce1_32.dll';  

function  DJUser_EnableDialSound(userID : integer) : boolean;  stdcall; far external 'tce1_32.dll';   //default
function  DJUser_DisableDialSound(userID : integer) : boolean;  stdcall; far external 'tce1_32.dll';  

function  DJUser_CheckHookOFF(userID : integer) : boolean;  stdcall; far external 'tce1_32.dll';  

function  DJUser_GetDialNum(userID : integer) : integer;  stdcall; far external 'tce1_32.dll';
function  DJUser_GetDialNumNew(userID : integer) : integer;  stdcall; far external 'tce1_32.dll';
function  DJUser_GetDialCode(userID : integer) : pchar;  stdcall; far external 'tce1_32.dll';  
function  DJUser_GetDialCodeNew(userID : integer) : pchar;  stdcall; far external 'tce1_32.dll';  
function  DJUser_GetFirstDialCode(userID : integer) : char;  stdcall; far external 'tce1_32.dll';
function  DJUser_GetLastDialCode(userID : integer) : char;  stdcall; far external 'tce1_32.dll';  
function  DJUser_InitDialBuf(userID : integer) : boolean;  stdcall; far external 'tce1_32.dll';

function  DJUser_GetConnectTrunkID(userID : integer) : integer;  stdcall; far external 'tce1_32.dll';  
function  DJUser_GetConnectUserID(userID : integer) : integer;  stdcall; far external 'tce1_32.dll';  
function  DJUser_GetListenUserID(userID : integer) : integer;  stdcall; far external 'tce1_32.dll';  
function  DJUser_GetListenTrunkID(userID : integer) : integer;  stdcall; far external 'tce1_32.dll';  
function  DJUser_GetPlayChannel(userID : integer) : integer;  stdcall; far external 'tce1_32.dll';
function  DJUser_GetRecordChannel(userID : integer) : integer;  stdcall; far external 'tce1_32.dll';  

function  DJUser_SetSendSoundValue(userID : integer; value : integer) : boolean;  stdcall; far external 'tce1_32.dll';  
function  DJUser_SetReciveSoundValue(userID : integer;value : integer) : boolean;  stdcall; far external 'tce1_32.dll';  
function  DJUser_GetSendSoundValue(userID : integer) : integer;  stdcall; far external 'tce1_32.dll';  
function  DJUser_GetReciveSoundValue(userID : integer) : integer;  stdcall; far external 'tce1_32.dll';  

function  DJUser_GetPreStopDialToNowTime(userID : integer) : WORD;  stdcall; far external 'tce1_32.dll';  

function  DJUser_SearchFreeTrunkAndDial(userID : integer;
				       pcmID : integer;
				       szPhoneNum : pchar;
                                       szCallerStr : pchar ) : PstnErr;
                         stdcall; far external 'tce1_32.dll';  
function  DJUser_DialByTrunk (userID : integer;
			  trunkID : integer;
			  szPhoneNum : pchar;
                          szCallerStr : pchar ) : boolean;
                         stdcall; far external 'tce1_32.dll';  
function  DJUser_GetDialTrunkID(userID : integer) : integer;  stdcall; far external 'tce1_32.dll';  
function  DJUser_GetDialStatus(userID : integer) : DialStatus;  stdcall; far external 'tce1_32.dll';

//added by N.C.J 1999.6.14
function  DJUser_PlayFileNew(userID:integer;FileName:PChar;Position:DWORD;Length:DWORD):integer; stdcall; far external 'tce1_32.dll';
procedure DJUser_StopPlayFile(userID:integer);  stdcall; far external 'tce1_32.dll';
function DJUser_RecordFileNew(userID:integer;FileName:PChar;Position:DWORD;Length:DWORD):integer;stdcall; far external 'tce1_32.dll';
procedure DJUser_StopRecordFile(userID:integer); stdcall; far external 'tce1_32.dll';
//end of N.C.J

{/******************************************************************************\
 *
 *	Functions for Voice channel
 *
\******************************************************************************/}
function  DJVoc_GetTotalVoiceChannel : integer;  stdcall; far external 'tce1_32.dll';  
function  DJVoc_VoiceStart(voiceChannelID : integer;
			  voiceResHandle : integer;
                          voiceResSize : LongInt;
                          voiceResOffset : LongInt;
                          voiceResType : VoiceResourcesType;
                          voiceOpType : VoiceOperatorType) : integer;
           stdcall; far external 'tce1_32.dll';
function  DJVoc_PlayNextVoice(voiceChannelID : integer;
			     voiceResHandle : integer;
			     voiceResSize : LongInt;
                             voiceResOffset : LongInt) : integer;
            stdcall; far external 'tce1_32.dll';  
function  DJVoc_FromHeadRePlay(voiceID : integer) : integer;  stdcall; far external 'tce1_32.dll';  

function  DJVoc_PlayPromptFile(voiceID : integer;voiceName : pchar) : boolean;  stdcall; far external 'tce1_32.dll';  
function  DJVoc_LoopPlayPromptFile( voiceID : integer;voiceName : pchar) : boolean;  stdcall; far external 'tce1_32.dll';  
function  DJVoc_PlayPromptID(voiceID : integer;voiceResID : integer) : boolean;  stdcall; far external 'tce1_32.dll';  
function  DJVoc_LoopPlayPromptID(voiceID : integer;voiceResID : integer) : boolean;  stdcall; far external 'tce1_32.dll';  


function  DJVoc_PauseChannelVoiceOp(voiceID : integer) : boolean;  stdcall; far external 'tce1_32.dll';  
function  DJVoc_ContinueChannelVoiceOp(voiceID : integer) : boolean;  stdcall; far external 'tce1_32.dll';  
function  DJVoc_CheckChannelVoicePause(voiceID : integer) : boolean;  stdcall; far external 'tce1_32.dll';  

function  DJVoc_VoiceStop(voiceChannelID : integer) : boolean;  stdcall; far external 'tce1_32.dll';

function  DJVoc_CheckVoiceEnd(voiceChannelID : integer) : boolean;  stdcall; far external 'tce1_32.dll';  
function  DJVoc_CheckVoiceChannelOp(voiceChannelID : integer) : boolean;  stdcall; far external 'tce1_32.dll';  

function  DJVoc_GetVoiceDataLength(voiceChannelID : integer) : longint;  stdcall; far external 'tce1_32.dll';  
function  DJVoc_GetVoiceHandle(voiceChannelID : integer) : integer;  stdcall; far external 'tce1_32.dll';  
function  DJVoc_GetVoiceErr(voiceID : integer) : integer;  stdcall; far external 'tce1_32.dll';  
function  DJVoc_GetVoiceResourceType(voiceID : integer) : VoiceResourcesType;  stdcall; far external 'tce1_32.dll';  
function  DJVoc_GetVoiceOperateType (voiceID : integer) : VoiceOperatorType;  stdcall; far external 'tce1_32.dll';  

function  DJVoc_GetPromptInfoByName(voiceName : pchar;
       			var voiceHandle : integer;
       			var voiceSize : LongInt;
       			var voiceOffset : LongInt) : boolean;
                 stdcall; far external 'tce1_32.dll';

function  DJVoc_GetPromptInfoByHandle(voiceResID : integer;
				var voiceHandle  : Integer;
				var voiceSize : LongInt;
				var voiceOffset : LongInt) : boolean;
            stdcall; far external 'tce1_32.dll';

function  DJVoc_SearchFreeVoiceChannelForPlay  : integer;  stdcall; far external 'tce1_32.dll';
function  DJVoc_SearchFreeVoiceChannelForRecord : integer;  stdcall; far external 'tce1_32.dll';

function  DJVoc_GetRecordTrunkID (voiceChannelID : integer) : integer;  stdcall; far external 'tce1_32.dll';
function  DJVoc_GetRecordUserID	(voiceChannelID : integer) : integer;  stdcall; far external 'tce1_32.dll';  

///******************************************************************************\
// *
// *	Functions for Connect Operate
// *
//\******************************************************************************/
function  DJExg_SetListenUserToUser(trgUserID: integer;srcUserID : integer): boolean;  stdcall; far external 'tce1_32.dll';  
function  DJExg_ClearListenUserFromUser(trgUserID : integer): boolean;  stdcall; far external 'tce1_32.dll';  
function  DJExg_SetListenUserToTrunk( userID : integer;trunkID : integer): boolean;  stdcall; far external 'tce1_32.dll';  
function  DJExg_ClearListenUserFromTrunk( userID : integer): boolean;  stdcall; far external 'tce1_32.dll';  

function  DJExg_SetListenTrunkToUser(trunkID : integer; userID : integer): boolean;  stdcall; far external 'tce1_32.dll';  
function  DJExg_ClearListenTrunkFromUser( trunkID : integer): boolean;  stdcall; far external 'tce1_32.dll';  
function  DJExg_SetListenTrunkToTrunk( trgTrunkID : integer;srcTrunkID : integer): boolean;  stdcall; far external 'tce1_32.dll';
function  DJExg_ClearListenTrunkFromTrunk( trgTrunkID : integer): boolean;  stdcall; far external 'tce1_32.dll';  

function  DJExg_SetLinkTrunkAndUser( trunkID : integer;userID : integer): boolean;  stdcall; far external 'tce1_32.dll';  
function  DJExg_ClearLinkTrunkAndUserByTrunk(trunkID : integer): boolean;  stdcall; far external 'tce1_32.dll';  
function  DJExg_ClearLinkTrunkAndUserByUser( userID : integer): boolean;  stdcall; far external 'tce1_32.dll';  

function  DJExg_SetLinkTrunkAndTrunk(trgTrunkID : integer;srcTrunkID : integer): boolean;  stdcall; far external 'tce1_32.dll';  
function  DJExg_ClearLinkTrunkAndTrunk (trunkID : integer): boolean;  stdcall; far external 'tce1_32.dll';  
function  DJExg_LinkUserAndUser(trgUserID : integer; srcUserID : integer): boolean;  stdcall; far external 'tce1_32.dll';  
function  DJExg_ClearLinkUserAndUser(userID : integer): boolean;  stdcall; far external 'tce1_32.dll';  
function  DJExg_SetLinkPlayVoiceToTrunk	(trunkID : integer;voiceID : integer): boolean;  stdcall; far external 'tce1_32.dll';  
procedure  DJExg_VoiceToTrunk_SetParam (conn : boolean);  stdcall; far external 'tce1_32.dll';  

function  DJExg_ClearLinkPlayVoiceFromTrunk(trunkID : integer): boolean;  stdcall; far external 'tce1_32.dll';  
function  DJExg_SetLinkPlayVoiceToUser(userID : integer;voiceID : integer): boolean;  stdcall; far external 'tce1_32.dll';  
function  DJExg_ClearLinkPlayVoiceFromUser(userID : integer): boolean;  stdcall; far external 'tce1_32.dll';  

function  DJExg_SetLinkRecordVoiceToUser(userID : integer;voiceID : integer): boolean;  stdcall; far external 'tce1_32.dll';  
function  DJExg_ClearLinkRecordVoiceFromUser(userID : integer): boolean;  stdcall; far external 'tce1_32.dll';  
function  DJExg_SetLinkRecordVoiceToTrunk (trunkID : integer;voiceID : integer): boolean;  stdcall; far external 'tce1_32.dll';  
function  DJExg_ClearLinkRecordVoiceFromTrunk(trunkID : integer): boolean;  stdcall; far external 'tce1_32.dll';

/////////////////////////////////////////////////////////////////////////////
function  DJSys_GetPcmNum : integer;  stdcall; far external 'tce1_32.dll';  

const BASE        =	500;
const CURRENTIN   =     BASE;
const CURRENTOUT  =	BASE + 1;
const LASTIN	  =	BASE + 2;
const LASTOUT	  =	BASE + 3;
const TOTALIN	  =	BASE + 4;
const TOTALOUT    =	BASE + 5;

function  DJSys_GetImpExpNum(ImpExpType : integer) : WORD;  stdcall; far external 'tce1_32.dll';  
function  DJSys_GetTotalImpExpNum( ImpExpType : integer) : LongInt;  stdcall; far external 'tce1_32.dll';  
function  DJSys_GetPcmImpExpNum( pcmID : integer;ImpExpType : integer) : WORD;  stdcall; far external 'tce1_32.dll';
function  DJSys_GetPcmTotalImpExpNum( pcmID : integer;ImpExpType : integer) : LongInt;  stdcall; far external 'tce1_32.dll';  

function  DJSys_GetPcmStatus(pcmID : integer) : BYTE;  stdcall; far external 'tce1_32.dll';  
procedure DJSys_StopWarning;  stdcall; far external 'tce1_32.dll';  
procedure DJSys_EnableWarn;  stdcall;  far external 'tce1_32.dll';  
procedure DJSys_DisableWarn;  stdcall; far external 'tce1_32.dll';

procedure DJSys_GetSysRunTime(var hours : LongInt; var minutes : BYTE;
          var seconds : BYTE);  stdcall; far external 'tce1_32.dll';
procedure DJSys_GetSysTime(var hours : BYTE; var minutes : BYTE;
          var seconds : BYTE);  stdcall; far external 'tce1_32.dll';  
procedure DJSys_SetSysTime(hours :  BYTE; minutes :  BYTE;
          seconds :  BYTE);  stdcall; far external 'tce1_32.dll';  

function  DJSys_GetErrCode : PstnErr;  stdcall; far external 'tce1_32.dll';  

function  DJSys_FreeMfcNum : integer;  stdcall; far external 'tce1_32.dll';  
function  DJSys_FreePlayVoiceNum : integer;  stdcall; far external 'tce1_32.dll';  
function  DJSys_FreeDtmfNum : integer;  stdcall; far external 'tce1_32.dll';  

function  DJSys_GetInstallDir : pchar;  stdcall; far external 'tce1_32.dll';  
const DISABLEDTMF = 0;
const ENABLEDTMF = 1;
procedure  DJSys_AutoApplyDtmf( DtmfMode : integer);  stdcall; far external 'tce1_32.dll';  

function  DJSys_IsAutoApplyDtmf : boolean;  stdcall; far external 'tce1_32.dll';  

procedure  DJSys_EnableAutoKB;  stdcall; far external 'tce1_32.dll';  
procedure  DJSys_DisableAutoKB;  stdcall; far external 'tce1_32.dll';

//--------------------------------------------------------
//Add by Wang Guoli 1998.3.17
function  DJVoc_PlayFile(trunkID : integer; FileName : pchar) : boolean;  stdcall; far external 'tce1_32.dll';  
procedure DJVoc_StopPlayFile(trunkID : integer);  stdcall; far external 'tce1_32.dll';  
procedure DJVoc_InitIndexPlayFile(trunkID : integer);  stdcall; far external 'tce1_32.dll';  
function  DJVoc_AddIndexPlayFile(trunkID : integer; FileName : pchar) : boolean;  stdcall; far external 'tce1_32.dll';  
function  DJVoc_StartIndexPlayFile(trunkID : integer) : boolean;  stdcall; far external 'tce1_32.dll';  
procedure DJVoc_StopIndexPlayFile(trunkID : integer);  stdcall; far external 'tce1_32.dll';  
function  DJVoc_CheckIndexPlayEnd(trunkID : integer) : boolean;  stdcall; far external 'tce1_32.dll';  
function  DJVoc_RecordFile(trunkID : integer; FileName : pchar;
             RecordLen : LongInt) : boolean;  stdcall; far external 'tce1_32.dll';
procedure DJVoc_StopRecordFile(trunkID : integer);  stdcall; far external 'tce1_32.dll';
//added by N.C.J 1999.6.14
function DJVoc_RecordFileNew(trunkID:integer;FileName:PChar;Position:DWORD;Length:DWORD):integer; stdcall; far external 'tce1_32.dll';
function DJVoc_PlayFileNew(trunkID:integer;FileName:PChar;Position:DWORD;Length:DWORD):integer; stdcall; far external 'tce1_32.dll';
//end of add
procedure DJTrk_GetMfcCodeA( trunkID : integer; MfcCode : pchar);  stdcall; far external 'tce1_32.dll';
procedure DJTrk_GetHostCodeA( trunkID : integer; HostCode : pchar );  stdcall; far external 'tce1_32.dll';
procedure DJTrk_GetDtmfCodeA( trunkID : integer; DtmfCode : pchar);  stdcall; far external 'tce1_32.dll';

//addd by lrg
function DJVoc_SFVC_ForPlay(trunkid:integer):integer;stdcall; far external 'tce1_32.dll';
function DJVoc_SFVC_ForRecord(trunkid:integer):integer;stdcall; far external 'tce1_32.dll';
function DJVoc_SFVC_ForPlay_New(trunkid:integer;isfortrunk:boolean):integer;stdcall; far external 'tce1_32.dll';

function DJConf_InitConfCard():integer;stdcall; far external 'tce1_32.dll';
procedure DJConf_ReleaseConfCard();stdcall; far external 'tce1_32.dll';


implementation

end.
