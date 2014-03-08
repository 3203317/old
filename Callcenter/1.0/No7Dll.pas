unit No7Dll;

interface
//////////////////////////////////////////////////////////
//ͨ��״̬����
//ʹ�� DJNo7_GetChnState( ����, ͨ���� ) ���
//////////////////////////////////////////////////////////

//����״̬
const CH_FREE		   =	1;
//DLL�ȴ�Ӧ�ò�ͬ���ͷ���Դ
const CH_WAIT_RELEASE_NO7	=2;
//��������״̬
const CH_LOCAL_BLOCK	=3;
//Զ������״̬
const CH_REMOTE_BLOCK	=4 ;
//������̬(���粻ͨ��ԭ����)
const CH_UNAVIABLE_NO7	=5;

//ȥ����ͨ״̬
const CALLER_CONNECT  =6;
//���з��Ͳ����ź�,�ȴ����л����ͷż໤�ź�
const CALLER_WAIT_RLG =7;

//���е���,����ȫ��ַ
const CALLEE_RECV_IAM =8;
//������ͨ״̬
const CALLEE_CONNECT  =9;
//�ѷ��͹һ��źţ��ȴ����з��Ͳ����ź�
const CALLEE_WAIT_CLF =10;

//�ڲ�״̬��Ӧ�ó��򲻶Դ�״̬���д���
const CH_CANOMIT      =11;

//////////////////////////////////////////////////////////
//ͨ����������
//ʹ�� DJNo7_SetChnState( ����, ͨ����, Action ) ����
//////////////////////////////////////////////////////////

//APP ����ͨ��״̬Ϊ OxO2 ʱ��ͬ���ͷ�ͨ����Դ
const	CH_SET_FREE		=1 ;
//����ͨ��
const	CH_SET_BLOCK	=2  ;
//�������
const	CH_SET_UNBLOCK	=3   ;

//����������������������ͱ������źţ���·��ͨ��������Ӧ���ź�
const	CALLEE_SET_ACM	=4    ;
			//����������������������ͺ���ʧ����Ϣ
			//const	CALLEE_SET_UBM	5
			//����������������������͵�ַ��ȫ�ź�
			//const	CALLEE_SET_ADI	5
//����ժ���ź�
const	CALLEE_SET_ANC	=6     ;
//����������������������ͱ���æ�ź�
const	CALLEE_SET_SSB	=7      ;
//����������������������ͱ�����æ�ź�
const	CALLEE_SET_SLB	=8       ;
//����������������������ͱ��г�æ�ź�
const	CALLEE_SET_STB	=9;
//����������������������Ϳպ��ź�
const	CALLEE_SET_UNN	=10;
//�����������������������Ӧ���ź�
const	CALLEE_SET_ANU	=11 ;
//�������������ͱ��йһ��ź�
const	CALLEE_SET_CBK	=12  ;
//�������������Ͳ����ź�
const	CALLEE_SET_RLG	=13   ;

//ȥ�����������Ͳ����ź�
const	CALLER_SET_CLF	=14    ;

//////////////////////////////////////////////////////////
//����ʧ��״̬
//////////////////////////////////////////////////////////
//δ���ؽ��
const C_NO_RESULT		=0 ;
//���п���
const C_USER_IDLE		=1 ;
//����ժ������ͨ״̬
const C_USER_OFFHOOK	=2 ;
//��ַ��ȫ
const C_ADDR_LACK		=3 ;
//ռ��
const C_USER_BUSY		=4 ;
//�պ�
const	C_UNALLOC_CODE	=5 ;
//����ͬ��
const	C_CALL_COLLIDE	=6 ;
//�Ծ��޻�Ӧ
const	C_TIME_OUT		=7 ;
//����ԭ����в��ɹ�
const	C_CALL_FAIL		=8 ;

//////////////////////////////////////////////////////////
//����ʧ����״̬
//////////////////////////////////////////////////////////
//�û�æ
const C_SUB_SSB	   =1     ;
//�û���æ
const C_SUB_SLB		=2    ;
//�û���æ
const C_SUB_STB		 =3   ;

//////////////////////////////////////////////////////////
//�������ɹ���Ϣ���Ͷ���
//ΪCCB_SetChnStateA( nPCM, nCHN, CALLEE_SET_ACM, param )��param����
//param must be: (ACM_ADDR_XXXX | ACM_CALLEE_XXXX)
//////////////////////////////////////////////////////////
//��ַȫ
const ACM_ADDR_COMPLETE =	0 ;
//��ַȫ,�Ʒ�
const ACM_ADDR_CHARGE	   =	1;
//��ַȫ,���Ʒ�
const ACM_ADDR_UNCHARGE	=2 ;
//��ַȫ,Ͷ��ʽ�绰
const ACM_ADDR_COINTEL	=3 ;

//�û�æ��״̬δָʾ
const ACM_CALLEE_UNKNOW	=0 ;
//�û���
const ACM_CALLEE_IDLE		=4 ;

//////////////////////////////////////////////////////////
//������м�����Ϣ���Ͷ���
//ΪCCB_SetChnStateA( nPCM, nCHN, CALLEE_SET_CSM, param )��param����
//////////////////////////////////////////////////////////
//Ӧ���ź�,�Ʒ�δ˵��(ANU)
const CSM_CHARGE_NOINDICATE=0  ;
//Ӧ���ź�,�Ʒ�(ANC)
const CSM_CHARGE			  =1;
//Ӧ���ź�,���Ʒ�(ANN)
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
