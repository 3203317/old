unit Isdndll;

interface
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


//int WINAPI DJISDN_InitSystem();
function DJISDN_InitSystem():integer;stdcall; far external 'Tc_isdn.dll';
//void WINAPI DJISDN_ExitSystem();stdcall; far external 'Tc_isdn.dll';
procedure DJISDN_ExitSystem();stdcall; far external 'Tc_isdn.dll';
//void WINAPI DJISDN_GetEvent();
procedure DJISDN_GetEvent();stdcall; far external 'Tc_isdn.dll';
//int WINAPI DJISDN_GetChnState(int nPCM, int nCHN);
function DJISDN_GetChnState(nPcm:integer;nCHN:integer):integer;stdcall; far external 'Tc_isdn.dll';

//int WINAPI DJISDN_SetChnState(int nPCM, int nCHN, int nState, int nParam);
function DJISDN_SetChnState(nPCm:integer;nCHN:integer;nState:integer;nParam:integer):boolean;stdcall; far external 'Tc_isdn.dll';
//void WINAPI DJISDN_GetCallerNumber(int nPCM, int nCHN, char * szNumber);
procedure DJISDN_GetCallerNumber(nPCM:integer;nCHN:integer; szNumber:pchar);stdcall; far external 'Tc_isdn.dll';
//void WINAPI DJISDN_GetCallerSubAddr(int nPCM, int nCHN, char * szSubAddr);
procedure DJISDN_GetCallerSubAddr(nPcm:integer;nCHN:integer;szSubAddr:pchar);stdcall; far external 'Tc_isdn.dll';
//void WINAPI DJISDN_GetCalleeNumber(int nPCM, int nCHN, char * szNumber);
procedure DJISDN_GetCalleeNumber(nPCM:integer;nCHN:integer;szNumber:pchar);stdcall; far external 'Tc_isdn.dll';
//void WINAPI DJISDN_GetCalleeSubAddr(int nPCM, int nCHN, char * szSubAddr);
procedure DJISDN_GetCalleeSubAddr(nPCM:integer; nCHN:integer;szSubAddr:pchar);stdcall; far external 'Tc_isdn.dll';

//bool WINAPI DJISDN_GetCalloutChn( int * nPCM, int * nCHN );
function DJISDN_GetCalloutChn(nPcm:pointer;nCHN:pointer):boolean;stdcall; far external 'Tc_isdn.dll';
//int WINAPI DJISDN_Callout(int nPCM, int nCHN, char * szCalleeNumber, char * szCalleeSubAddr="", char * szCallerNumber="", char * szCallerSubAddr="");
function DJISDN_Callout(nPCM:integer;nCHN:integer;szCalleeNumber:pchar;szCalleeSubAddr:pchar;szCallerNumber:pchar;szcallerSubAddr:pchar):integer;stdcall; far external 'Tc_isdn.dll';
//int WINAPI DJISDN_GetCalloutResult(int nPCM, int nCHN);
function DJISDN_GetCalloutResult(nPCM:integer;nCHN:integer):integer;stdcall; far external 'Tc_isdn.dll';
//int WINAPI DJISDN_GetDisconnectReason(int nPCM, int nCHN);
function DJISDN_GetDisconnectReason(nPCM:integer;nCHN:integer):integer;stdcall; far external 'Tc_isdn.dll';
//bool WINAPI DJISDN_SendRawData(int nPCM, BYTE * pRawData, int nLen);
function DJISDN_SendRawDAta(nPCM:integer;pRawData:pointer;nLen:integer):boolean;stdcall; far external 'Tc_isdn.dll';
//void WINAPI DJISDN_SetSystemMode( int nMode , int nParam );
procedure DJISDN_SetSystemMode(nMode:integer;nParam:integer);stdcall; far external 'Tc_isdn.dll';





implementation

end.
