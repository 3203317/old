unit Isdndll;

interface
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
