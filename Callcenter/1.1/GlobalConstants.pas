{-------------------------------------------------------------------------------
                      LightweightCTI for Delphi/C++Builder
                    ר��Ϊ������Ա��д����������Ӧ�û������
            (C)Copyright 1998 - 2006 Sjteksoft - LightweightCTI��Ŀ��
            
���������Ϊ����Դ������������������������GNU GPL(GNU General Public License 
ͨ�ù������Э��)��LightweightCTI����Э��ķ���Э�����޸ĺ����·������������

����������һ�������Ŀ����Ϊ����ʹDelphi/C++ Builder�����Ĺ�󿪷���Ա���ɱ�д
����������������Dialogic������������ȣ������Ӧ������򹤾ߣ�ϣ�����ܹ���ʵΪ
���Ŀ��������������������ǲ����ṩ�κε���������û���ʺ��ض�Ŀ�Ķ������ĵ�����
��Ҳ����ͨ���������ϵ��ʽ������Ŀ���˽Ⲣ��ȡ��ҵ��Ȩ�İ汾����Ϊ��ϸ����Ϣ��
�ο�GPLЭ�鼰LightweightCTI����Э�顣

���������ز�ʹ�ô������ʱ�����Ѿ��������һ���յ�һ��GPLЭ�鼰LightweightCTI��
��Э��ĸ����������û�������Է������ǵ���վ���͵����ʼ���ȡ��

������վ��ҳ��http://21chainwater.vicp.net
              http://www.21chinawater.com
���������ʼ���Sjsteksoft@gmail.com
-------------------------------------------------------------------------------}

unit GlobalConstants;

interface

uses
  Controls, Classes, Messages, Graphics;

var
  GFlickeringFrameColor: TColor = clRed;                                        // ��ɫ

const
  GCMaxCTICard = 255;                                                           // ��������������Լ��ض��������
  GCConfigFilename = 'LightweightCTI.config';
  GCDefaultTimeOut  = 5;                                                        // Ĭ�ϵĳ�ʱֵ����
  GCTimeOutRate = 1000;                                                         // ����ʱֵת��Ϊ����ı���
  GCTaskManagerInterval = 60;                                                   // ���������Ĭ��ˢ��ʱ����
  GCMaxLimitCount = 30;                                                         // ����������޷�ֵ
  GCConfigurationRootPath = 'Configuration';
  GCProjectsDirectory = 'Projects\';                                            // Ĭ�ϵ���Ŀ�ļ���
var   GCResourcesDirectory:string = 'Resources\';
const                                          // Ĭ�ϵ���Դ�ļ���
  GCDataPath = 'Data\';                                                         // �������ݿ��ļ���
  GUserDataPath = '';                                                           // �û�˽�����ݱ���·��
  GAllUserDataPath = 'Users Local Setting\All Users\';                          // �����û����ݱ���·��

  GCStatusbar = 'Statusbar';                                                    // ϵͳ״̬��  
  GCBarmanager = 'dxbarMain';                                                   // ϵͳ������������
  GCStyleControlller = 'EditStyleController';                                   // ��ʽ������

  // ���ݿ������ַ���
  GCdbconStr1 = 'Provider=SQLOLEDB.1;Integrated Security=SSPI;Persist Security Info=False;User ID=sa;Initial Catalog=%s;Data Source=%s';
  GCdbconStr2 = 'Provider=SQLOLEDB.1;Persist Security Info=False;User ID=%s;Initial Catalog=%s;Data Source=%s';
  GCdbconStr3 = 'Provider=SQLOLEDB.1;Password=%s;Persist Security Info=True;User ID=%s;Initial Catalog=%s;Data Source=%s';

resourcestring
  RsUser = '����';
  RsTrunk = '����';
  RsEmpty = '����';
  RsRecord = '¼��';
  RsMessage = '����';
  RsFax = '����';
  RsVirtual = '����';  

  RsRing = '����';
  RsFree = '����';                // ����
  RsOffHook = 'ժ��';             // ժ��
  RsDial = '����';                // ����
  RsWaitingSignal = '�ȴ�������'; // �ȴ�������
  RsTimeOut = '��ʱ';             // ��ʱ
  RsConnect = '����';             // ���ӣ�����ժ��
  RsPlaying = '����';             // ����
  RsRecording = '¼��';           // ¼�� 
  RsGetDtmf = '����';             // ����
  RsHangup = '�һ�';              // �һ�
  RsLink = '����';                // ����
  RsDisable = '����';             // ����
  RsWait = '�ȴ�';                //�ȴ�

  RsSIGNoResult = 'û�з���';
  RsSIGNoDialTone = '�޲�����';
  RsSIGBusy = '�Է�æ��';
  RsSIGConnect = '�Է�ժ��';
  RsSIGNoBody = '���˽���';
  RsSIGNoSignal = '���ź���';
  RsSIGTimeOut = '���ӳ�ʱ';

  RsEqualTo = 'equal to';
  RsStartingWith = 'starting with';
  RsEndsWith = 'ends with';
  RsContains = 'contains';
  RsIsContainedWithin = 'is contained within';
  RsNotEmpty = 'not empty';
  RsStep = 'Step ';
  RsComments = 'Comments';

  RsVoiceFile = 'voice file';
  RsTTS = 'text to speech';
  RsDate = 'date';
  RsTime = 'time';
  RsNumber = 'number';
  RsAmount = 'amount';
  RsDayOfWeek = 'day of week';
  RsSleep = 'sleep';

  RsCTID641X = '����D641ϵ�а忨';

  RsUnKonwn = 'UnKonwn';
  RsSQLServer = 'SQLServer';
  RsAccess = 'Access';
  RsOracle = 'Oracle';
  RsMySql = 'MySql';
  RsSysbase = 'Sysbase';

  RsReadOnly = 'ֻ��';
  RsNormal = '����';
  RsDesign = '���';
  RsPreview = 'Ԥ��';
  RsReport = '����';

  RsEnglish  = 'English\';
  RsStandard = 'Standard\';
  RsLocalism = 'Localism\';

const
  GCE1Count = 30;
var
  SIG_STOP,SIG_DIALTONE,SIG_BUSY,SIG_BUSY1,SIG_BUSY2,SIG_RINGBACK,SIG_BACK:integer;
  CardDriver_virtual : boolean; BroadIp : string; BroadOutPort,BroadListenPort : integer;
  GClocalphone : string; channel_sleep_time, callback_sleep_time: integer;
  ptl_IncomingTelegram, ptl_UserChannelOn, ptl_UserChannelOff, ptl_SessionLog, ptl_ChannelsTotal : string;
  ServerTypeList : TStringList; //��������
type DISCONNECT_REASON =
(
	RSN_UNKNOW_REASON = $00,  		//ԭ��δ֪
	RSN_UNALLOC_NUMBER = $01,  		//�պ�
	RSN_NORMAL_DISCONNECT = $10,	//�����ĺ������
	RSN_USER_BUSY = $11,		 	//�û�æ
	RSN_NO_RESPOND = $12,		  	//����Ӧ
	RSN_NO_ANSWER = $13,		   	//���û�Ӧ��
	RSN_REFUSR_CALL = $15,			//���оܾ�
	RSN_NUMBER_ERROR = $1C,		//���벻ȫ
	RSN_TIMEOUT	= $66,				//��ʱ
	RSN_DCHANNEL_DOWN = $fd,		//��·�ж�
	RSN_BCHANNEL_UNAVIABLE = $22,  //ͨ��������
	RSN_UNAVIABLE_CIRCULT = $2c,	//�޿���ͨ·
	RSN_UNSVIABLE_MSG = $5F		//��Ч����Ϣ
);


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

const BASE        =	500;
const CURRENTIN   =     BASE;
const CURRENTOUT  =	BASE + 1;
const LASTIN	  =	BASE + 2;
const LASTOUT	  =	BASE + 3;
const TOTALIN	  =	BASE + 4;
const TOTALOUT    =	BASE + 5;


const DISABLEDTMF = 0;
const ENABLEDTMF = 1;


//////////////////////////////////////////////////////////
//ͨ��״̬����
//ʹ�� DJISDN_GetChnState( ����, ͨ���� ) ���
//////////////////////////////////////////////////////////
//����״̬
const CH_FREE=					1;
//DLL�ȴ�Ӧ�ò�ͬ���ͷ���Դ
const CH_WAIT_APP_FREE =		2;
//������̬(���粻ͨ��ԭ����)
const CH_UNAVIABLE	=		3     ;
//�ȴ�����֤ʵ
const CH_WAIT_CONNECT_ACK	=	4  ;
//��ͨ״̬
const CH_CONNECT			 =	5   ;
//�ѷ��Ͳ����źţ��ȴ��Է������ͷ��ź�
const CH_WAIT_RELEASE		  =	6    ;
//�ѷ����ͷ��źţ��ȴ��Է������ͷ�֤ʵ�ź�
const CH_WAIT_RELEASE_COMPLETE =7   ;

//������ ���е���
const CALLEE_WAIT_ANSWER	 =	11  ;

//ȥ���� �Ѻ������ȴ����л�Ӧ
const CALLER_WAIT_ANSWER	  =	12  ;
//ȥ���� �ѽ��յ�������Ϣ
const CALLER_RECV_ALERT	  =	21      ;
//ȥ���� �ѽ��յ�����ȷ����Ϣ
const CALLER_RECV_SETUP_ACK	=22     ;
//ȥ���� �ѽ��յ����й�����Ϣ
const CALLER_RECV_CALLPROCESS =23   ;

//////////////////////////////////////////////////////////
//ͨ����������
//ʹ�� DJISDN_SetChnState( ����, ͨ����, Action ) ����
//////////////////////////////////////////////////////////
//APP ����ͨ��״̬Ϊ OxO2 ʱ��ͬ���ͷ�ͨ����Դ
const	CH_SET_FREE		=		1     ;
//����ͨ������
const CH_SET_CONNECT	 =	2       ;
//���ͨ������
const CH_SET_DISCONNECT	=3         ;
//���з�����������Ϣ
const CALLEE_SET_ALERTING  =	4 ;

//////////////////////////////////////////////////////////
//�������
//////////////////////////////////////////////////////////
//����ͨ����������
const	CH_SET_RESTART	  =	5      ;

//////////////////////////////////////////////////////////
//�������
//////////////////////////////////////////////////////////
//δ���ؽ��
const C_NO_RESULT	 =	0;
//���п���
const C_USER_IDLE	  =	1 ;
//����ժ������ͨ״̬
const C_USER_OFFHOOK   =	2;

implementation

end.
