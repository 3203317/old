unit No7Dll;

interface
//////////////////////////////////////////////////////////
//通道状态定义
//使用 DJNo7_GetChnState( 卡号, 通道号 ) 获得
//////////////////////////////////////////////////////////

//空闲状态
const CH_FREE		   =	1;
//DLL等待应用层同步释放资源
const CH_WAIT_RELEASE_NO7	=2;
//本地锁闭状态
const CH_LOCAL_BLOCK	=3;
//远端锁闭状态
const CH_REMOTE_BLOCK	=4 ;
//不可用态(网络不通等原因导致)
const CH_UNAVIABLE_NO7	=5;

//去话连通状态
const CALLER_CONNECT  =6;
//主叫方送拆线信号,等待被叫回送释放监护信号
const CALLER_WAIT_RLG =7;

//呼叫到达,接收全地址
const CALLEE_RECV_IAM =8;
//来话连通状态
const CALLEE_CONNECT  =9;
//已发送挂机信号，等待主叫发送拆线信号
const CALLEE_WAIT_CLF =10;

//内部状态，应用程序不对此状态进行处理
const CH_CANOMIT      =11;

//////////////////////////////////////////////////////////
//通道动作定义
//使用 DJNo7_SetChnState( 卡号, 通道号, Action ) 设置
//////////////////////////////////////////////////////////

//APP 层检查通道状态为 OxO2 时，同步释放通道资源
const	CH_SET_FREE		=1 ;
//锁闭通道
const	CH_SET_BLOCK	=2  ;
//解除锁闭
const	CH_SET_UNBLOCK	=3   ;

//来话方动作，来话到达后，送被叫闲信号，话路接通，但不送应答信号
const	CALLEE_SET_ACM	=4    ;
			//来话方动作，来话到达后，送呼叫失败消息
			//const	CALLEE_SET_UBM	5
			//来话方动作，来话到达后，送地址不全信号
			//const	CALLEE_SET_ADI	5
//被叫摘机信号
const	CALLEE_SET_ANC	=6     ;
//来话方动作，来话到达后，送被叫忙信号
const	CALLEE_SET_SSB	=7      ;
//来话方动作，来话到达后，送被叫市忙信号
const	CALLEE_SET_SLB	=8       ;
//来话方动作，来话到达后，送被叫长忙信号
const	CALLEE_SET_STB	=9;
//来话方动作，来话到达后，送空号信号
const	CALLEE_SET_UNN	=10;
//来话方动作，来话到达后，送应答信号
const	CALLEE_SET_ANU	=11 ;
//来话方动作，送被叫挂机信号
const	CALLEE_SET_CBK	=12  ;
//来话方动作，送拆线信号
const	CALLEE_SET_RLG	=13   ;

//去话方动作，送拆线信号
const	CALLER_SET_CLF	=14    ;

//////////////////////////////////////////////////////////
//呼出失败状态
//////////////////////////////////////////////////////////
//未返回结果
const C_NO_RESULT		=0 ;
//被叫空闲
const C_USER_IDLE		=1 ;
//被叫摘机，连通状态
const C_USER_OFFHOOK	=2 ;
//地址不全
const C_ADDR_LACK		=3 ;
//占线
const C_USER_BUSY		=4 ;
//空号
const	C_UNALLOC_CODE	=5 ;
//发生同抢
const	C_CALL_COLLIDE	=6 ;
//对局无回应
const	C_TIME_OUT		=7 ;
//其它原因呼叫不成功
const	C_CALL_FAIL		=8 ;

//////////////////////////////////////////////////////////
//呼出失败子状态
//////////////////////////////////////////////////////////
//用户忙
const C_SUB_SSB	   =1     ;
//用户市忙
const C_SUB_SLB		=2    ;
//用户长忙
const C_SUB_STB		 =3   ;

//////////////////////////////////////////////////////////
//后向建立成功消息类型定义
//为CCB_SetChnStateA( nPCM, nCHN, CALLEE_SET_ACM, param )中param参数
//param must be: (ACM_ADDR_XXXX | ACM_CALLEE_XXXX)
//////////////////////////////////////////////////////////
//地址全
const ACM_ADDR_COMPLETE =	0 ;
//地址全,计费
const ACM_ADDR_CHARGE	   =	1;
//地址全,不计费
const ACM_ADDR_UNCHARGE	=2 ;
//地址全,投币式电话
const ACM_ADDR_COINTEL	=3 ;

//用户忙闲状态未指示
const ACM_CALLEE_UNKNOW	=0 ;
//用户闲
const ACM_CALLEE_IDLE		=4 ;

//////////////////////////////////////////////////////////
//后向呼叫监视消息类型定义
//为CCB_SetChnStateA( nPCM, nCHN, CALLEE_SET_CSM, param )中param参数
//////////////////////////////////////////////////////////
//应答信号,计费未说明(ANU)
const CSM_CHARGE_NOINDICATE=0  ;
//应答信号,计费(ANC)
const CSM_CHARGE			  =1;
//应答信号,不计费(ANN)
const CSM_UNCHARGE		   =2 ;

//////////////////////////////////////////////////////////

function DJNo7_InitSystem():integer; stdcall; far external 'TcNo7.dll';
procedure DJNo7_ExitSystem(); stdcall; far external 'TcNo7.dll';
procedure DJNo7_GetEvent(); stdcall; far external 'TcNo7.dll';
function DJNo7_GetChnState(  mo_num:byte;dt_num:byte ):byte; stdcall; far external 'TcNo7.dll';
procedure DJNo7_SetChnState(mo_num:byte; dt_num:byte; state:byte); stdcall; far external 'TcNo7.dll';
procedure DJNo7_SetChnStateA(nPCM:byte;nCHN:byte;state:byte;param:byte ) ;  stdcall; far external 'TcNo7.dll';
procedure DJNo7_GetCalleeNum(mo_num:byte; dt_num:byte;call_id:pchar );  stdcall; far external 'TcNo7.dll';
procedure DJNo7_GetCallerNum(mo_num:byte;dt_num:byte;call_id:pchar );  stdcall; far external 'TcNo7.dll';
function DJNo7_GetCalloutChn(mo_num:pointer;dt_num:pointer ):boolean; stdcall; far external 'TcNo7.dll';
function DJNo7_Callout(mo_num:byte;dt_num:byte;callee:pchar;caller:pchar):boolean; stdcall; far external 'TcNo7.dll';
function DJNo7_CheckCalloutResult(mo_num:byte;dt_num:byte ):byte; stdcall; far external 'TcNo7.dll';

implementation

end.
