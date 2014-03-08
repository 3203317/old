{-------------------------------------------------------------------------------
                      LightweightCTI for Delphi/C++Builder
                    专门为开发人员而写的轻型语音应用基础框架
            (C)Copyright 1998 - 2006 Sjteksoft - LightweightCTI项目组
            
　　本框架为开放源代码的自由软件，您可以遵照GNU GPL(GNU General Public License 
通用公共许可协议)及LightweightCTI补充协议的发布协议来修改和重新发布此软件包。

　　发布这一软件包的目的是为了能使Delphi/C++ Builder社区的广大开发人员轻松编写
基于语音卡（包括Dialogic、东进、三汇等）的相关应用软件或工具，希望它能够切实为
您的开发工作带来便利，我们并不提供任何担保。甚至没有适合特定目的而隐含的担保。
您也可以通过下面的联系方式，向项目组了解并获取商业授权的版本，更为详细的信息请
参考GPL协议及LightweightCTI补充协议。

　　在下载并使用此软件包时，您已经和软件包一起收到一份GPL协议及LightweightCTI补
充协议的副本。如果还没有您可以访问我们的网站或发送电子邮件索取。

　　网站主页：http://21chainwater.vicp.net
              http://www.21chinawater.com
　　电子邮件：Sjsteksoft@gmail.com
-------------------------------------------------------------------------------}

unit GlobalConstants;

interface

uses
  Controls, Classes, Messages, Graphics;

var
  GFlickeringFrameColor: TColor = clRed;                                        // 颜色

const
  GCMaxCTICard = 255;                                                           // 单个虚拟机最多可以加载多个语音卡
  GCConfigFilename = 'LightweightCTI.config';
  GCDefaultTimeOut  = 5;                                                        // 默认的超时值，秒
  GCTimeOutRate = 1000;                                                         // 将超时值转换为毫秒的倍数
  GCTaskManagerInterval = 60;                                                   // 任务管理器默认刷新时间间隔
  GCMaxLimitCount = 30;                                                         // 任务队列上限阀值
  GCConfigurationRootPath = 'Configuration';
  GCProjectsDirectory = 'Projects\';                                            // 默认的项目文件夹
var   GCResourcesDirectory:string = 'Resources\';
const                                          // 默认的资源文件夹
  GCDataPath = 'Data\';                                                         // 本地数据库文件夹
  GUserDataPath = '';                                                           // 用户私有数据保存路径
  GAllUserDataPath = 'Users Local Setting\All Users\';                          // 所有用户数据保存路径

  GCStatusbar = 'Statusbar';                                                    // 系统状态条  
  GCBarmanager = 'dxbarMain';                                                   // 系统工具栏管理器
  GCStyleControlller = 'EditStyleController';                                   // 样式管理器

  // 数据库连接字符串
  GCdbconStr1 = 'Provider=SQLOLEDB.1;Integrated Security=SSPI;Persist Security Info=False;User ID=sa;Initial Catalog=%s;Data Source=%s';
  GCdbconStr2 = 'Provider=SQLOLEDB.1;Persist Security Info=False;User ID=%s;Initial Catalog=%s;Data Source=%s';
  GCdbconStr3 = 'Provider=SQLOLEDB.1;Password=%s;Persist Security Info=True;User ID=%s;Initial Catalog=%s;Data Source=%s';

resourcestring
  RsUser = '内线';
  RsTrunk = '外线';
  RsEmpty = '悬空';
  RsRecord = '录音';
  RsMessage = '短信';
  RsFax = '传真';
  RsVirtual = '虚拟';  

  RsRing = '振铃';
  RsFree = '空闲';                // 空闲
  RsOffHook = '摘机';             // 摘机
  RsDial = '拨号';                // 拨号
  RsWaitingSignal = '等待拨号音'; // 等待拨号音
  RsTimeOut = '超时';             // 超时
  RsConnect = '连接';             // 连接，被叫摘机
  RsPlaying = '放音';             // 放音
  RsRecording = '录音';           // 录音 
  RsGetDtmf = '收码';             // 收码
  RsHangup = '挂机';              // 挂机
  RsLink = '连接';                // 连接
  RsDisable = '禁用';             // 禁用
  RsWait = '等待';                //等待

  RsSIGNoResult = '没有返回';
  RsSIGNoDialTone = '无拨号音';
  RsSIGBusy = '对方忙音';
  RsSIGConnect = '对方摘机';
  RsSIGNoBody = '无人接听';
  RsSIGNoSignal = '无信号音';
  RsSIGTimeOut = '联接超时';

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

  RsCTID641X = '东进D641系列板卡';

  RsUnKonwn = 'UnKonwn';
  RsSQLServer = 'SQLServer';
  RsAccess = 'Access';
  RsOracle = 'Oracle';
  RsMySql = 'MySql';
  RsSysbase = 'Sysbase';

  RsReadOnly = '只读';
  RsNormal = '常规';
  RsDesign = '设计';
  RsPreview = '预览';
  RsReport = '报表';

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
  ServerTypeList : TStringList; //服务类型
type DISCONNECT_REASON =
(
	RSN_UNKNOW_REASON = $00,  		//原因未知
	RSN_UNALLOC_NUMBER = $01,  		//空号
	RSN_NORMAL_DISCONNECT = $10,	//正常的呼叫清除
	RSN_USER_BUSY = $11,		 	//用户忙
	RSN_NO_RESPOND = $12,		  	//无响应
	RSN_NO_ANSWER = $13,		   	//无用户应答
	RSN_REFUSR_CALL = $15,			//呼叫拒绝
	RSN_NUMBER_ERROR = $1C,		//号码不全
	RSN_TIMEOUT	= $66,				//超时
	RSN_DCHANNEL_DOWN = $fd,		//链路中断
	RSN_BCHANNEL_UNAVIABLE = $22,  //通道不可用
	RSN_UNAVIABLE_CIRCULT = $2c,	//无可用通路
	RSN_UNSVIABLE_MSG = $5F		//无效的消息
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
//通道状态定义
//使用 DJISDN_GetChnState( 卡号, 通道号 ) 获得
//////////////////////////////////////////////////////////
//空闲状态
const CH_FREE=					1;
//DLL等待应用层同步释放资源
const CH_WAIT_APP_FREE =		2;
//不可用态(网络不通等原因导致)
const CH_UNAVIABLE	=		3     ;
//等待连接证实
const CH_WAIT_CONNECT_ACK	=	4  ;
//连通状态
const CH_CONNECT			 =	5   ;
//已发送拆线信号，等待对方发送释放信号
const CH_WAIT_RELEASE		  =	6    ;
//已发送释放信号，等待对方发送释放证实信号
const CH_WAIT_RELEASE_COMPLETE =7   ;

//来话方 呼叫到达
const CALLEE_WAIT_ANSWER	 =	11  ;

//去话方 已呼出，等待被叫回应
const CALLER_WAIT_ANSWER	  =	12  ;
//去话方 已接收到提醒消息
const CALLER_RECV_ALERT	  =	21      ;
//去话方 已接收到建立确认消息
const CALLER_RECV_SETUP_ACK	=22     ;
//去话方 已接收到呼叫过程消息
const CALLER_RECV_CALLPROCESS =23   ;

//////////////////////////////////////////////////////////
//通道动作定义
//使用 DJISDN_SetChnState( 卡号, 通道号, Action ) 设置
//////////////////////////////////////////////////////////
//APP 层检查通道状态为 OxO2 时，同步释放通道资源
const	CH_SET_FREE		=		1     ;
//设置通道连接
const CH_SET_CONNECT	 =	2       ;
//解除通道连接
const CH_SET_DISCONNECT	=3         ;
//被叫方发送提醒消息
const CALLEE_SET_ALERTING  =	4 ;

//////////////////////////////////////////////////////////
//呼出结果
//////////////////////////////////////////////////////////
//设置通道重新启动
const	CH_SET_RESTART	  =	5      ;

//////////////////////////////////////////////////////////
//呼出结果
//////////////////////////////////////////////////////////
//未返回结果
const C_NO_RESULT	 =	0;
//被叫空闲
const C_USER_IDLE	  =	1 ;
//被叫摘机，连通状态
const C_USER_OFFHOOK   =	2;

implementation

end.
