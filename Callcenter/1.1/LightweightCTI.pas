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

unit LightweightCTI;

interface

uses
  Classes, Windows, Messages, SysUtils, Contnrs, ExtCtrls, Forms, XMLParse,
  GlobalConstants;

type
  // �Ự����
  TSesstionType = (stCallIn, stCallOut, stFree);
  // ͨ������
  TChannelType = (ctUser, ctTrunk, ctEmpty, ctRecord, ctMessage, ctFax, ctVirtual);
  // ������Դ����
  TVoiceResourceType = (vrtUnkonwn, vrtStandard, vrtLocalism, vrtEnglish);

  // ͨ��״̬
  TChannelStatus = (
    csRing,          // ����
    csFree,          // ����
    csOffHook,       // ժ��
    csDial,          // ����
    csWaitingSignal, // �ȴ����������
    csTimeOut,       // ��ʱ
    csConnect,       // ���ӣ�����ժ��
    csPlaying,       // ����
    csRecording,     // ¼��
    csGetDtmf,       // ����
    csHangup,        // �һ�
    csLink,          // ���ӣ�����ģʽ
    csDisable,      // ����
    csWait);

  // �������
  TPlayMessageResult = (
    pmrPlaying,       //����������
    pmrComplete,     // �����������
    pmrKeyPress,     // �Է��������
    pmrHangup);      // ����δ��һ�

  // ¼�����
  TRecordFileResult = (
    rfrRecording,
    rfrComplete,     // ¼����������
    rfrKeyPress,     // �Է��������
    rfrHangup);      // ¼��δ��һ�

  // �ź�������
  TSignalType = (stNoResult, stNoDialTone, stBusy, stConnect, stNoBody, stNoSignal, stTimeOut);

  // TTS����ת������
  TPlayNumberType = (pntMessage, pntTelphone, pntMoney, pntNumber);

  // ��־��¼����
  TLogLevel = (llDebug, llInfo, llWarn, llError, llFatal, llTalk);

  // ��־����ӿ�
  ILoger = interface ['{069AC7C9-0A96-468F-A006-EFC6670E674D}']
    procedure Debug(Amsg: string); overload;                                    // ������Ϣ
    procedure Debug(Amsg: string; Aexception: Exception); overload;
    procedure Info(Amsg: string); overload;                                     // һ����Ϣ
    procedure Info(Amsg: string; Aexception: Exception); overload;
    procedure Warn(Amsg: string); overload;                                     // ������Ϣ
    procedure Warn(Amsg: string; Aexception: Exception); overload;
    procedure Error(Amsg: string); overload;                                    // ������Ϣ
    procedure Error(Amsg: string; Aexception: Exception); overload;
    procedure Fatal(Amsg: string); overload;                                    // ����������Ϣ
    procedure Fatal(Amsg: string; Aexception: Exception); overload;
  end;

  // �ű�����
  IScriptEngine = interface ['{BF724106-7A2C-4BAF-BA43-A7B54CBB31B8}']
    procedure LoadScripts(const Afilename: string);
    function Debug(OutPutList: TStringList): Integer;
    function RunScripts(OutPutList: TStringList): Integer;
  end;

  // TTS����ӿ�
  ITTSEngine = interface ['{BC6787DE-2ACD-4FD8-86CB-B291E359CDB3}']
    function PlayMessage(Channel: TObject; const Amsg: string; const AllowBreak: Boolean = True;
      const APlayType: TPlayNumberType = pntMessage;
      const AVoiceResource: TVoiceResourceType = vrtStandard): TPlayMessageResult; // ����
    function PlayToFile(Channel: TObject; const Atxt: string; const Afilename: string;
      const AVoiceResource: TVoiceResourceType = vrtStandard): Boolean;
  end;
  
  TAbstractSession = class;

  // TTS����ӿڡ�
  TAbstractTTSEngine = class(TComponent, ITTSEngine)
  private
    FLoger: ILoger;
  protected
    FcanWork: Boolean;
    procedure Initialize; virtual; abstract;
    procedure Release; virtual; abstract;

    property Loger: ILoger read FLoger;
  public
    constructor Create(AOwner: TComponent); overload; override;
    constructor Create(AOwner: TComponent; Aloger: ILoger); reintroduce; overload;
    destructor Destroy; override;

    function PlayMessage(Channel: TObject; const Amsg: string; const AllowBreak: Boolean;
      const APlayType: TPlayNumberType = pntMessage;
      const AVoiceResource: TVoiceResourceType = vrtStandard): TPlayMessageResult;
      virtual; abstract; // ����
    function PlayToFile(Channel: TObject; const Atxt: string; const Afilename: string;
      const AVoiceResource: TVoiceResourceType = vrtStandard): Boolean;
      virtual; abstract; // �������ļ�
    property CanWork: Boolean read FcanWork;
  end;

  // �忨���������������ӿ�
  ICTICardDriver = interface ['{AE899D6D-1E4E-4F4C-8968-C63A212D7336}']
    function GetCanwork: Boolean;
    function GetChannelCount: Integer;

    procedure Initialize;
    function InitializeChannel: Integer;
    procedure Release;
    function CanClose: Boolean;
    property CanWork: Boolean read GetCanWork;
    property ChannelCount: Integer read GetChannelCount;
  end;

  // �忨����¼�
  TReleasingEvent = procedure(Sender: TObject; var Allowable: Boolean) of object;

  // �忨�������������������࣬����������ʵ�ֶ�����̳��ڴ���
  TAbstractCTICardDriver = class(TComponent, ICTICardDriver)
  private
    FLoger: ILoger;
    FConfigIndex: Integer;
  protected
    FcanWork: Boolean;
    FchannelCount: Integer;
    FchannelIndex: Integer;

    // �������¼�����
    FOnInitialized: TNotifyEvent;
    FOnReleasing: TReleasingEvent;
    FOnReleased: TNotifyEvent;

    function GetCanWork: Boolean; virtual;
    function GetChannelCount: Integer; virtual;
    function InitializeChannel: Integer; virtual; abstract;
    property Loger: ILoger read FLoger; // ��־���
  public
    constructor Create(Aowner: TComponent); overload; override;
    constructor Create(AOwner: TComponent; Aloger: ILoger); reintroduce; overload;
    destructor Destroy; override;

    // ��������Ա
    procedure Initialize; virtual;
    procedure Release; virtual; abstract;
    function CanClose: Boolean; virtual; abstract;
    property CanWork: Boolean read GetCanWork default False;
    property ChannelCount: Integer read GetChannelCount default 0;
    property ChannelIndex: Integer read FChannelIndex default 0; // ��ǰ��������һ��ͨ����������
    property ConfigIndex: Integer read FConfigIndex write FConfigIndex default -1; // ��ǰ�忨�������ļ��е�����
  published
    property OnInitialized: TNotifyEvent read FOnInitialized write FOnInitialized;
    property OnReleasing: TReleasingEvent read FOnReleasing write FOnReleasing;
    property OnReleased: TNotifyEvent read FOnReleased write FOnReleased;
  end;

  // ������ͨ���ӿ�
  IChannel = interface ['{E43590C0-56B5-497F-A2CD-159D6B43E9C4}']
    function GetBackspaceKey: Char;
    procedure SetBackspaceKey(const Value: Char);
    function GetChannelID: Integer;
    function GetChannelType: TChannelType;
    function GetChannelStatus: TChannelStatus;
    function InnerGetDtmf: string;
    function GetScriptEngine: IScriptEngine;
    function GetLastStatus: TChannelStatus;
    function GetTimeOut: Integer;
    procedure SetTimeOut(const Value: Integer);
    function GetVoiceResource: TVoiceResourceType;
    procedure SetVoiceResource(const Value: TVoiceResourceType);

    // �����һ�����
    function RingDetect(const Atimes: Integer = 1): Boolean; // ������
    procedure StopRing();
    procedure OffHook; // ժ��
    function OffHookDetect: Boolean; // ����ժ�����
    procedure HangUp; // ���йһ�
    function HangUpDetect: Boolean; // ���йһ����
    procedure ResetChannel; // ����ͨ��     
    function Schedule(AserverType: string; Afilelist: string): Integer;//��ȡ���ٽӵ绰����ϯ��


    // ���л򱻽к���
    function GetCallerNumber: string; // ��ȡ���к���
    function GetCalleeNumber: string; // ��ȡ���к���

    // ���ż���������
    function Dial(const Adialnum: string; const APrefixnum: string = ''):
      TSignalType; // ���Ⲧ��
    function DtmfHit: Boolean; // �������
    function GetDTMF(const Alength: Integer; Asuffix: string = '#';
      const Atimeout: Integer = 0): string; // ȡDTMF����
    procedure ClearDTMF; // ���DTMF����
    function PlayMessage(const Atxt: string; const AllowBreak: Boolean = True):
      TPlayMessageResult; // ����һ������
    function PlayNumber(const Atxt: string; const AllowBreak: Boolean = True;
      const APlayType: TPlayNumberType = pntMessage): TPlayMessageResult; // ��������
    procedure PlayVoice(AvoiceType: integer);
    function PlayFile(const Afilelist, AfileExt: string;
      const AllowBreak: Boolean = True): TPlayMessageResult; // ���������ļ�
    function PlayFileToTrunkAndUser(const Afilelist, AfileExt: string;
      const AllowBreak: Boolean = True; AchannelID: Integer = -1): TPlayMessageResult; // ��˫����������
    function PlayToFile(const Atxt: string; const Afilename: string;
      const AVoiceResource: TVoiceResourceType = vrtStandard): Boolean; // ���ŵ��ļ�
    function SendMessage(const Atxt: string): Boolean; // ������Ϣ



    // ¼������
    function RecordFile(const Afilename: string; ALengthTimes: Integer = 60;
      AllowBreak: Boolean = True): TRecordFileResult; // ¼��


    // ͨ����ͨ����
    function LinkTo(const AChannelID: Integer = -1;
      const AChannelType: TChannelType = ctUser): Integer; // ���ӵ�ĳһͨ��
    function UnLink(const AChannelID: Integer = -1;
      const AChannelType: TChannelType = ctUser; ChannelToChannel:boolean = false): Integer; // �Ͽ���ĳͨ��������
    function UnLinkAll: Boolean; // �Ͽ�������ͨ��������
    function ListenTo(const AChannelID: Integer): Integer; // ����ĳһͨ��

    property BackspaceKey: Char read GetBackspaceKey write SetBackspaceKey; // ɾ������ֵ
    property ChannelID: Integer read GetChannelID; // ͨ����ʶ
    property ChannelType: TChannelType read GetChannelType; // ͨ������
    property Dtmf: string read InnerGetDtmf;
    property Status: TChannelStatus read GetChannelStatus; // ͨ����ǰ״̬
    property LastStatus: TChannelStatus read GetLastStatus; // ͨ��ǰһ��״̬
    property ScriptEngine: IScriptEngine read GetScriptEngine; // �ű�����
    property VoiceResource: TVoiceResourceType read GetVoiceResource write
      SetVoiceResource; // ������Դ����
    property TimeOut: Integer read GetTimeOut write SetTimeOut;
  end;

  // �����¼�
  TGetDTMFEvent = procedure (Sender: TObject; const Adtmf: string) of object;
  // ����/�����¼�
  TCallEvent = procedure (Sender: TObject; const ACallNumber: string) of object;
  // ͨ��æ�¼�
  TChannelBusyEvent = procedure(Sender: TObject; LinkChannel: TObject;
    const ACallNumber: string; var Data: Variant) of object;
  // ��־�¼�
  TChannelLogEvent = procedure (Sender: TObject; const Amsg: string) of object;
  // �ű�ִ������¼�
  TScriptitsCompletedEvent = procedure (Sender: TObject; ScriptEngine: IScriptEngine;
    OutPutList: TStringList) of object;

  TChannelManager = class;

  // ͨ��������
  TAbstractChannel = class(TThread, IChannel)
  private
    FOwner: TComponent;
    FLoger: ILoger;
    FBackspaceKey: Char;
    FChannelID: Integer;
    FChannelType: TChannelType;
    FStatus, FLastStatus: TChannelStatus;
    FScripts: AnsiString;
    FTimeOut: Integer;
    FVoiceResource: TVoiceResourceType;
    FRunning: Boolean;

    FOnScriptsChanged: TNotifyEvent;
    FOnScriptsCompleted: TScriptitsCompletedEvent;
    FOnChannelCreated: TNotifyEvent;

    procedure InnerInitialize; virtual;
    procedure SetRunning(const Value: Boolean);
    procedure SetScripts(const Value: AnsiString);
    procedure SetChannelStatus(const Value: TChannelStatus);
  protected
    FDtmf: string;
    FPolarity: Integer;
    FLinkToChannel: Integer;
    FLinkToType: TChannelType;
    FCallNumber: string; // ��¼���л򱻽к���
    FChannelManager: TChannelManager;
    FScriptEngine: IScriptEngine;
    FOnStatusChanged: TNotifyEvent;
    FOnGetDTMF: TGetDTMFEvent;
    FOnCallIn: TCallEvent;
    FOnCallOut: TCallEvent;
    FOnChannelBusy: TChannelBusyEvent;
    FOnChannelLog: TChannelLogEvent;
    FOnResetChannel: TNotifyEvent;
    FOnRun: TNotifyEvent;
    FOnTimeOut: TNotifyEvent;

    // TThread
    procedure Execute; override;

    // IInterface 
    function QueryInterface(const IID: TGUID; out Obj): HResult; virtual; stdcall;
    function _AddRef: Integer; stdcall;
    function _Release: Integer; stdcall;

    function GetBackspaceKey: Char;
    procedure SetBackspaceKey(const Value: Char);
    function GetChannelID: Integer;
    function GetChannelType: TChannelType;
    function GetChannelStatus: TChannelStatus;
    function InnerGetDtmf: string;
    function GetScriptEngine: IScriptEngine; virtual;
    function GetLastStatus: TChannelStatus;
    function GetTimeOut: Integer;
    procedure SetTimeOut(const Value: Integer);
    function GetVoiceResource: TVoiceResourceType;
    procedure SetVoiceResource(const Value: TVoiceResourceType);

    // ��������
    function CheckFile(const Afilename: string): string;

    property Owner: TComponent read FOwner;
    property Loger: ILoger read FLoger; // ��־���
  public
    FVCLComObject: Pointer;  
    FserverType : string;
    FserverNum : integer;
    Fsatisfaction : integer;
    FCallSession : TAbstractSession;
    constructor Create(CreateSuspended: Boolean); overload;
    constructor Create(AOwner: TComponent; AchannelID: Integer; Atype: TChannelType;
      Amanager: TChannelManager; Aloger: ILoger); reintroduce; overload; virtual;
    destructor Destroy; override;

    // �����һ�����
    function RingDetect(const Atimes: Integer = 1): Boolean; virtual; abstract; // ������
    procedure StopRing();virtual;abstract;
    procedure OffHook; virtual; abstract; // ժ��
    function OffHookDetect: Boolean; virtual; abstract; // ����ժ�����
    procedure HangUp; virtual; abstract; // ���йһ�
    function HangUpDetect: Boolean; virtual; abstract; // ���йһ����
    procedure ResetChannel; virtual; abstract; // ����ͨ��
    function Schedule(AserverType: string ; Afilelist: string): Integer;virtual; abstract;//��ȡ���ٽӵ绰����ϯ��


    // ���л򱻽к���
    function GetCallerNumber: string; virtual; abstract; // ��ȡ���к���
    function GetCalleeNumber: string; virtual; abstract; // ��ȡ���к���

    // ���ż���������
    function Dial(const Adialnum: string; const APrefixnum: string = ''):
      TSignalType; virtual; abstract; // ���Ⲧ��
    function DtmfHit: Boolean; virtual; abstract; // �������
    function GetDTMF(const Alength: Integer; Asuffix: string = '#';
      const Atimeout: Integer = 0): string; virtual; abstract; // ȡDTMF����
    procedure ClearDTMF; virtual; abstract; // ���DTMF����
    function PlayMessage(const Atxt: string; const AllowBreak: Boolean = True):
      TPlayMessageResult; virtual; abstract; // ����һ������
    function PlayNumber(const Atxt: string; const AllowBreak: Boolean = True;
      const APlayType: TPlayNumberType = pntMessage): TPlayMessageResult;
      virtual; abstract; // ��������
    procedure PlayVoice(AvoiceType: integer);overload;virtual;abstract;
    function PlayFile(const Afilelist, AfileExt: string;
      const AllowBreak: Boolean = True): TPlayMessageResult; overload; virtual;
      abstract; // ���������ļ�

    function PlayFileToTrunkAndUser(const Afilelist, AfileExt: string;
      const AllowBreak: Boolean = True; AchannelID: Integer = -1): TPlayMessageResult; overload; virtual;abstract; // ��˫����������

    function PlayToFile(const Atxt: string; const Afilename: string;
      const AVoiceResource: TVoiceResourceType = vrtStandard): Boolean;
      virtual; abstract; // ���ŵ��ļ�
    function SendMessage(const Atxt: string): Boolean; virtual; abstract; // ������Ϣ


    // ¼������
    function RecordFile(const Afilename: string; ALengthTimes: Integer = 60;
      AllowBreak: Boolean = True): TRecordFileResult; virtual; abstract; // ¼��

    // ͨ����ͨ����
    function LinkTo(const AChannelID: Integer = -1;
      const AChannelType: TChannelType = ctUser): Integer; virtual; abstract; // ���ӵ�ĳһͨ��
    function UnLink(const AChannelID: Integer = -1;
      const AChannelType: TChannelType = ctUser; ChannelToChannel:boolean = false): Integer; virtual; abstract; // �Ͽ���ĳͨ��������
    function UnLinkAll: Boolean; virtual; abstract; // �Ͽ�������ͨ��������
    function ListenTo(const AChannelID: Integer): Integer; virtual; abstract; // ����ĳһͨ��

    procedure ChannelLog(const Amsg: string); virtual; // ��¼ͨ����־
    
    property BackspaceKey: Char read GetBackspaceKey write SetBackspaceKey; // ɾ������ֵ
    property ChannelID: Integer read GetChannelID; // ͨ����ʶ
    property ChannelType: TChannelType read GetChannelType; // ͨ������
    property Dtmf: string read InnerGetDtmf;
    property Status: TChannelStatus read GetChannelStatus write SetChannelStatus; // ͨ����ǰ״̬
    property LastStatus: TChannelStatus read GetLastStatus; // ͨ��ǰһ��״̬
    property ScriptEngine: IScriptEngine read GetScriptEngine; // �ű�����
    property VoiceResource: TVoiceResourceType read GetVoiceResource write
      SetVoiceResource; // ������Դ����
    property TimeOut: Integer read GetTimeOut write SetTimeOut;

    property ChannelManager: TChannelManager read FChannelManager; // ͨ��������
    property Running: Boolean read FRunning write SetRunning default False; // �����־
    property Scripts: AnsiString read FScripts write SetScripts; // �ű�
    property Terminated;
  published
    property OnChannelCreated: TNotifyEvent read FOnChannelCreated
      write FOnChannelCreated; // ͨ�������¼�
    property OnScriptsChanged: TNotifyEvent read FOnScriptsChanged
      write FOnScriptsChanged; // �ű��ļ��仯�¼�
    property OnScriptsCompleted: TScriptitsCompletedEvent read FOnScriptsCompleted
      write FOnScriptsCompleted; // �ű�ִ������¼�
    property OnStatusChanged: TNotifyEvent read FOnStatusChanged
      write FOnStatusChanged; // ״̬�仯�¼�
    property OnGetDTMF: TGetDTMFEvent read FOnGetDTMF write FOnGetDTMF; // �����¼�
    property OnCallIn: TCallEvent read FOnCallIn write FOnCallIn; // �����¼�
    property OnCallOut: TCallEvent read FOnCallOut write FOnCallOut; // �����¼�
    property OnChannelBusy: TChannelBusyEvent read FOnChannelBusy write FOnChannelBusy; // ͨ��æ�¼�
    property OnChannelLog: TChannelLogEvent read FOnChannelLog write FOnChannelLog; // ��־��¼�¼�
    property OnRun: TNotifyEvent read FOnRun write FOnRun; // ҵ���߼�ִ���¼�
    property OnResetChannel: TNotifyEvent read FOnResetChannel write FOnResetChannel; // ����ͨ���¼�
    property OnTimeOut: TNotifyEvent read FOnTimeOut write FOnTimeOut; // ��ʱ�¼�
  end;

  // �Ự�ӿ�
  ISession = interface ['{B189E937-A55F-4957-B054-6DC78E286781}']
    function GetSessionID: string;
    function GetStartTime: TDateTime;
    procedure SetStartTime(const Value: TDateTime);
    function GetEndTime: TDateTime;
    procedure SetEndTime(const Value: TDateTime);
    function GetSessionType: TSesstionType;
    procedure SetSessionType(const Value: TSesstionType);
    function GetPhoneNumber: string;
    procedure SetPhoneNumber(const Value: string);

    procedure StoreSession; // �־û��Ự

    property SessionID: string read GetSessionID; // �Ự��ʶ
    property StartTime: TDateTime read GetStartTime write SetStartTime; // ��ʼʱ��
    property EndTime: TDateTime read GetEndTime write SetEndTime; // ����ʱ��
    property SesstionType: TSesstionType read GetSessionType write SetSessionType; // �Ự����
    property PhoneNumber: string read GetPhoneNumber write SetPhoneNumber; // �绰����
  end;

  // �Ự������
  TAbstractSession = class(TComponent, ISession)
  private
    FSessionID: string;
    FStartTime: TDateTime;
    FEndTime: TDateTime;
    FPhoneNumber: string;
    FSessionType: TSesstionType;

    function GetSessionID: string;
    procedure SetSessionID(const ASessionID: string);
    function GetStartTime: TDateTime;
    procedure SetStartTime(const Value: TDateTime);
    function GetEndTime: TDateTime;
    procedure SetEndTime(const Value: TDateTime);
    function GetSessionType: TSesstionType;
    procedure SetSessionType(const Value: TSesstionType);
    function GetPhoneNumber: string;
    procedure SetPhoneNumber(const Value: string);
  protected
  public
    constructor Create(AOwner: TComponent); overload; override;
    constructor Create(AOwner: TComponent; APhoneNumber: string;
      ASessionType: TSesstionType); reintroduce; overload;
    destructor Destroy; override;

    procedure StoreSession; virtual; abstract; // �־û��Ự

    property SessionID: string read GetSessionID write SetSessionID;// �Ự��ʶ
    property StartTime: TDateTime read GetStartTime write SetStartTime; // ��ʼʱ��
    property EndTime: TDateTime read GetEndTime write SetEndTime; // ����ʱ��
    property SesstionType: TSesstionType read GetSessionType write SetSessionType; // �Ự����
    property PhoneNumber: string read GetPhoneNumber write SetPhoneNumber; // �绰����
  end;

  // ����ӿ�
  ITask = interface ['{D4B24567-0548-4A54-86B4-6D9035149A44}']
    function GetTaskID: string;
    procedure StoreTask;
    property TaskID: string read GetTaskID; // �����ʶ
  end;

  // ���롢����������
  TAbstractCallTask = class(TComponent, ITask)
  private
    FCalling: Boolean;

    function GetSession: ISession;
    function GetTaskID: string;
  protected
    FTaskID: string;
    FSession: ISession;
    procedure StoreTask; virtual; abstract; // ��������
  public
    constructor Create(AOwner: TComponent); overload; override;
    constructor Create(AOwner: TComponent; AtaskID: string; ASession: ISession); reintroduce; overload; virtual;

    property TaskID: string read GetTaskID;
    property Calling: Boolean read FCalling write FCalling default False; // ���б�־
    property Session: ISession read GetSession; // ��ǰ�����Ӧ�ĻỰ
  end;

  // ����������ӿ�
  ITaskQueueFillter = interface ['{5813CA3C-FE00-47F3-9ECF-D3C026219699}']
    procedure FillQueue; // ������
  end;

  // ����������ӿ�
  ITaskManager = interface ['{B1C647AD-AE44-42AD-9FB0-4B141D0A9A09}']
    function GetTaskQueue: TObjectQueue;
    function GetNextTask: TObject;
    function GetTaskCount: Integer;
    function GetActive: Boolean;
    procedure SetActive(const Value: Boolean);
    procedure ClearTasks;
    function GetLocked: Boolean;
    function GetInterval: Integer;
    procedure SetInterval(const Value: Integer);
    function GetMaxlimitCount: Integer;
    procedure SetMaxlimitCount(const Value: Integer);

    property Active: Boolean read GetActive write SetActive; // ���������״̬
    property Interval: Integer read GetInterval write SetInterval; // ���������ʱ�䣬��
    property Locked: Boolean read GetLocked; // ����������־
    property MaxlimitCount: Integer read GetMaxlimitCount write SetMaxlimitCount; // ���з�ֵ
    property TaskQueue: TObjectQueue read GetTaskQueue; // �����������
    property TaskCount: Integer read GetTaskCount; // �����е�������
  end;

  TAbstractTaskManager = class(TComponent, ITaskManager)
  private
    FTaskQueue: TObjectQueue;
    FActive: Boolean;
    FInterval: Integer;
    FMaxlimitCount: Integer;

    function GetTaskQueue: TObjectQueue;
    function GetTaskCount: Integer;
    function GetActive: Boolean; virtual;
    procedure SetActive(const Value: Boolean); virtual;
    function GetLocked: Boolean;
    function GetInterval: Integer;
    procedure SetInterval(const Value: Integer);
    function GetMaxlimitCount: Integer; virtual;
    procedure SetMaxlimitCount(const Value: Integer); virtual;
  protected
    FLocked: Boolean;
    FTimer: TTimer;
    FOnTimer: TNotifyEvent;
  public
    constructor Create(AOwner: TComponent); overload; override;
    constructor Create(AOwner: TComponent; AInterval: Integer); reintroduce; overload; virtual;
    destructor Destroy; override;

    procedure ClearTasks; virtual;
    function GetNextTask: TObject; virtual;
    property Active: Boolean read GetActive write SetActive; // ���������״̬
    property Interval: Integer read GetInterval write SetInterval; // ���������ʱ�䣬��
    property Locked: Boolean read GetLocked; // ����������־
    property MaxlimitCount: Integer read GetMaxlimitCount write SetMaxlimitCount; // ���з�ֵ
    property TaskQueue: TObjectQueue read GetTaskQueue; // �����������
    property TaskCount: Integer read GetTaskCount; // �����е�������
    property OnTimer: TNotifyEvent read FOnTimer write FOnTimer; // �����������¼�
  end;

  // ϵͳ��Դ�ٽ�������ͨ���ٽ����������Դ���õ�����
  TCriticalSection = class
  private
    FName: string; // �ٽ�������
    FCS: TRTLCriticalSection;
  public
    constructor Create(const Aname: string);
    destructor Destroy; override;

    procedure EnterCS;
    procedure LeaveCS;
    
    property Name: string read FName;
  end;

  // ͨ����ʼ���¼�
  TChannelInitializedEvent = procedure (Sender: TObject; var Channel: TAbstractChannel) of object;
  
  // ͨ��������
  TChannelManager = class(TComponent)
  private
    FResourceDir, FProjectDir: TFileName;
    FconfigFile: TFileName;
    FRootPath: string;
    FConfiguration: TSampleXML;
    FTimeOut, FHandleIndex: Integer;
    FuserChannels,FtruncChannels: TList;
    FCTICardDrivers: TList;
    FCSList: TList;
    FTTSEngine: ITTSEngine;
    FHandleArray: array [0..GCMaxCTICard] of THandle;
    FTaskManater: TAbstractTaskManager;
    FOnChannelInitialized: TChannelInitializedEvent;
    FOnAfterEnter : TNotifyEvent;
    procedure SetProjectDir(const Value: TFileName);
    procedure SetResourceDir(const Value: TFileName);
    procedure SetTimeOut(const Value: Integer);
    function  GetCS(const Aname: string): TCriticalSection; // ��ȡһ���ٽ�������
  protected
    FLoger: ILoger;

    function GetLoger: ILoger;
    procedure RegisterTruncChannel(AChannel: TAbstractChannel);
    procedure RegisterUserChannel(AChannel: TAbstractChannel);
    property CTICardDrivers: TList read FCTICardDrivers;
  public
    constructor Create(AOwner: TComponent); overload; override;
    constructor Create(AOwner: TComponent; const AconfigFile: string); reintroduce; overload;
    destructor Destroy; override;

    procedure EnterCS(const ACSName: string); // �����ٽ���
    procedure LeaveCS(const ACSName: string); // �뿪�ٽ���

    procedure Initialize; // ��ʼ��LightweightCTI

    property truncChannels: TList read FtruncChannels; // ���м��ص��м�ͨ��
    property userChannels: TList read FuserChannels; // ���м��ص��û�ͨ��
    property ProjectDir: TFileName read FProjectDir write SetProjectDir; // ��Ŀ�ļ���
    property ResourceDir: TFileName read FResourceDir write SetResourceDir; // ��Դ�ļ���
    property TimeOut: Integer read FTimeOut write SetTimeOut default 5; // ��ʱ����,Ĭ��ֵΪ5��
    property TaskManager: TAbstractTaskManager read FTaskManater; // ���������
    property TTSEngine: ITTSEngine read FTTSEngine; // TTS����
    property Loger: ILoger read FLoger; // ��־��¼���
  published
    property ConfigFile: TFileName read FconfigFile write FconfigFile; // ϵͳ�����ļ�
    property Configuration: TSampleXML read FConfiguration; // ϵͳ������Ϣ��ȡ��
    property RootPath: string read FRootPath write FRootPath; // ϵͳ���ø��ڵ�
    property OnChannelInitialized: TChannelInitializedEvent read FOnChannelInitialized write FOnChannelInitialized; // ͨ����ʼ���¼�
    property OnAfterEnter: TNotifyEvent read FOnAfterEnter write FOnAfterEnter; // �����ٽ�����ִ���¼�
  end;

  // �忨����������������������������忨���������ö�̬���ӿ����ʽ���з���������
  // �������ô�ǩ�����е�����
  TLoadCTIDriver = function(Amanager: TChannelManager; HostApplication: TApplication): TAbstractCTICardDriver;
  // ��־�����������
  TLoadLoger = function(HostApplication: TApplication): ILoger;
  // �����������������
  TLoadTaskManager = function(Amanager: TChannelManager; HostApplication: TApplication): TAbstractTaskManager;
  // TTS���浼������
  TLoadTTSEngine = function(Amanager: TChannelManager; ALoger: ILoger; HostApplication: TApplication): ITTSEngine;
  
  function ExePath: TFileName;
  function iif(const Test: Boolean; const ATrue, AFalse: Variant): Variant;
  function LoadTextFile(const FileName: TFileName): string;
  function PathAddSeparator(const Path: string): string;
  function StrToStringList(const Astr: string; const Asplit: Char = ';'): TStringList;
  function ChannelManager: TChannelManager;
  
const
  // Ԥ�����ͨ��״̬
  GAChannelStatusLabels: array [TChannelStatus] of string = (RsRing,RsFree, RsOffHook,
    RsDial, RsWaitingSignal, RsTimeOut, RsConnect, RsPlaying, RsRecording,
    RsGetDtmf, RsHangUp, RsLink, RsDisable,RsWait);
  // ������Դ�ļ�λ��
  GAVoiceResource: array [TVoiceResourceType] of string = (RsUnKonwn,
    RsStandard, RsLocalism, RsEnglish);
  // ͨ������˵��
  GAChannelTypeLabels: array[TChannelType] of string = (RsUser, RsTrunk, RsEmpty,
    RsRecord, RsMessage, RsFax, RsVirtual);
  // ��Ϣ������
  GASignalTypeLabels: array[TSignalType] of string = (RsSIGNoResult,
    RsSIGNoDialTone, RsSIGBusy, RsSIGConnect, RsSIGNoBody, RsSIGNoSignal,
    RsSIGTimeOut);
      
implementation

uses
  XMLIntf;
  
const
  PathSeparator = '\';

var
  FChannelManager: TChannelManager;

//------------------------------------------------------------------------------
// ͨ��������ȫ����ڳ���
//------------------------------------------------------------------------------
function ChannelManager: TChannelManager;
begin
  if not Assigned(FChannelManager) then
  begin
    FChannelManager := TChannelManager.Create(Application, ExePath + GCConfigFilename);
  end;

  Result := FChannelManager;
end;

//------------------------------------------------------------------------------
// ��ȡ��ǰִ���ļ��ĸ�·��
//------------------------------------------------------------------------------
function ExePath: TFileName;
begin
  Result := ExtractFilePath(ParamStr(0));
end;

//------------------------------------------------------------------------------
// ��Ŀ�жϲ�������
//------------------------------------------------------------------------------
function iif(const Test: Boolean; const ATrue, AFalse: Variant): Variant;
begin
  if Test then
    Result := ATrue
  else
    Result := AFalse;
end;

//------------------------------------------------------------------------------
// �����ļ��������ļ�����
//------------------------------------------------------------------------------
function LoadTextFile(const FileName: TFileName): string;
begin
  with TStringList.Create do
  try
    LoadFromFile(FileName);
    Result := Text;
  finally
    Free;
  end;
end;

//------------------------------------------------------------------------------
// Ϊ�ļ���·����ӷָ���
//------------------------------------------------------------------------------
function PathAddSeparator(const Path: string): string;
begin
  Result := Path;
  if (Path = '') or (AnsiLastChar(Path) <> PathSeparator) then
    Result := Path + PathSeparator;
end;

//------------------------------------------------------------------------------
// ʹ��ָ���ķָ�������Ĭ��Ϊ��;�������ַ���ת��Ϊ�б�
//------------------------------------------------------------------------------
function StrToStringList(const Astr: string; const Asplit: Char): TStringList;
var
  Alst: TStringList;
  I: Integer;
  Atmp: string;
begin
  Alst := TStringList.Create;
  Atmp := Astr;
  I := Pos(Asplit, Atmp);
  while I <> 0 do
  begin
    Alst.Add(Copy(Atmp, 1, I -1));
    Delete(Atmp, 1, I);
    I := Pos(Asplit, Atmp);
  end;
  if Atmp <> '' then Alst.Add(Atmp);
  Result := Alst;
end;

{ TAbstractCTICardDriver }

constructor TAbstractCTICardDriver.Create(AOwner: TComponent; Aloger: ILoger);
begin
  Create(AOwner);
  FLoger := Aloger;
end;

constructor TAbstractCTICardDriver.Create(AOwner: TComponent);
begin
  inherited Create(AOwner);
  FcanWork := False;
  FchannelCount := 0;
  FchannelIndex := 0;
end;

destructor TAbstractCTICardDriver.Destroy;
begin
  Release;
  FLoger := nil;
  inherited;
end;

function TAbstractCTICardDriver.GetCanWork: Boolean;
begin
  Result := FcanWork;
end;

function TAbstractCTICardDriver.GetChannelCount: Integer;
begin
  Result := FchannelCount;
end;

procedure TAbstractCTICardDriver.Initialize;
begin
  // ... ����ʵ����Ĵ���
  if Assigned(FOnInitialized) then
    FOnInitialized(Self);
end;

{ TAbstractChannel }

procedure TAbstractChannel.ChannelLog(const Amsg: string);
var
  Astr: string;
begin
  Astr := Format('Channelid: %2d %s %s', [ChannelID, GAChannelTypeLabels[ChannelType], Amsg]);
  //FLoger.Info(Astr);
  if Assigned(FOnChannelLog) then
    FOnChannelLog(Self, Astr);
end;

{-------------------------------------------------------------------------------
  Procedure: TAbstractChannel.CheckFile
  Author:    Xsp
  DateTime:  2006.05.25
  Arguments: const Afilename: string
  Result:    string
  Popose:    ����ļ��Ƿ���ڣ�ϵͳ���Ȼ���ȫ����Դ�ļ����в��Ҹ��ļ������������
         �Ͳ�ѯ��Ŀ�ļ��л�ϵͳ��װĿ¼����ǰ�汾ֻ����Դ�ļ����н���������û��
         �������������Ĺ��ܡ�
-------------------------------------------------------------------------------}
function TAbstractChannel.CheckFile(const Afilename: string): string;
  // ��ָ���ļ����в�ѯ��Ӧ���ļ�
  function ReadFolder(const Folder, Mask: TFileName; FileList: TStrings): Integer;
  var
    SearchRec: TSearchRec;
    DosError: integer;
  begin
    FileList.Clear;
    Result := FindFirst(PathAddSeparator(Folder) + Mask, faAnyFile, SearchRec);
    DosError := Result;
    while DosError = 0 do begin
      if not ((SearchRec.Attr and faDirectory) = faDirectory) then
        FileList.Add(SearchRec.Name);
      DosError := FindNext(SearchRec);
    end;
    FindClose(SearchRec);
  end;

  // ����ָ���ļ�
  function SearchFile(const Adir, Afile: string; Alist: TStrings): string;
  begin
    ReadFolder(Adir, Afile, Alist);
    if Alist.Count > 0 then
      Result := Alist.Strings[0]
    else
      Result := Adir + Afile;
  end;

var
  Afile, Adir: string;
  Afilelist: TStringList;
begin
  Result := '';
  Afile  := Afilename;
  if FileExists(Afile) then
    Result := Afile
  else
  try
    Afilelist := TStringList.Create;
    Afile     := ExtractFileName(Afile);
    case VoiceResource of
      vrtUnkonwn : // δָ����Դ����
        begin
          Adir := iif(FChannelManager.ResourceDir = '', ExePath,
            PathAddSeparator(FChannelManager.ResourceDir));
          if FileExists(Adir + Afile) then
            Result := Adir + Afile;
        end;

      vrtEnglish, vrtStandard, vrtLocalism : // ָ���˾��������
        begin
          Adir := iif(FChannelManager.ResourceDir = '', ExePath,
            PathAddSeparator(FChannelManager.ResourceDir)) + GAVoiceResource[VoiceResource];
          if FileExists(Adir + Afile) then
            Result := Adir + Afile;
        end

      else
        begin
          Adir := iif(FChannelManager.ProjectDir = '', ExePath,
            PathAddSeparator(FChannelManager.ProjectDir));
          if FileExists(Adir + Afile) then
            Result := Adir + Afile;
        end;
    end;
  finally
    FreeAndNil(Afilelist);
  end;
end;

constructor TAbstractChannel.Create(CreateSuspended: Boolean);
begin
  inherited;
  InnerInitialize;
end;

constructor TAbstractChannel.Create(AOwner: TComponent; AchannelID: Integer;
  Atype: TChannelType; Amanager: TChannelManager; Aloger: ILoger);
begin
  Create(True);
  FChannelID   := AchannelID;
  FChannelType := Atype;
  FChannelManager := Amanager;
  FTimeOut := FChannelManager.TimeOut;
  FLoger := Aloger;
  FOwner := AOwner;
  // ͨ�������¼�
  if Assigned(FOnChannelCreated) then FOnChannelCreated(Self);
end;

destructor TAbstractChannel.Destroy;
begin
  FLoger := nil;
  FOwner := nil;
  FChannelManager := nil;
  inherited;
end;

{-------------------------------------------------------------------------------
  Procedure: TAbstractChannel.Execute
  Author:    Xsp
  DateTime:  2006-08-10
  Arguments: None
  Result:    None
  Purpose:   ͨ��ҵ���߼�ִ���¼���Ҳ���̵߳����¼����ͻ���Ӧ��ͨ������Resume��
  �����������¼���ϵͳ�����ȼ����û��Ϊϵͳָ���ⲿ�ű������ָ���˽ű���ֱ��
      ����ű��ļ��������ٴ���Run�¼�����������ڽű���ִ��Run�¼���
-------------------------------------------------------------------------------}
procedure TAbstractChannel.Execute;
var
  AOutPutlst: TStringList;
begin
  inherited;
  AOutPutlst := TStringList.Create;
  try
    if Assigned(ScriptEngine){FScripts <> ''} then
    begin
      try
        // �ű����ع����ɾ����ʵ���ฺ��
        ScriptEngine.RunScripts(AOutPutlst);
      except on E: Exception do
        Loger.Error('ִ�нű�����ʧ��', E);
      end;
      // �ű�ִ������¼�
      if Assigned(FOnScriptsCompleted) then
        FOnScriptsCompleted(Self, ScriptEngine, AOutPutlst);
    end else
      if Assigned(FOnRun) then
        FOnRun(Self)
      else // ���ִ��ʱ��û���ṩ�ű�����Ҳû���ṩ�¼�����������׳��쳣
        raise Exception.Create('no scripts program and Run event method,' + #13#10 +  
	  'you must supply one of them.');
  finally
    AOutPutlst.Free;
  end;
end;

function TAbstractChannel.GetBackspaceKey: Char;
begin
  Result := FBackspaceKey;
end;

function TAbstractChannel.GetChannelID: Integer;
begin
  Result := FChannelID;
end;

function TAbstractChannel.GetChannelStatus: TChannelStatus;
begin
  Result := FStatus;
end;

function TAbstractChannel.GetChannelType: TChannelType;
begin
  Result := FChannelType;
end;

function TAbstractChannel.GetLastStatus: TChannelStatus;
begin
  Result := FLastStatus;
end;

function TAbstractChannel.GetScriptEngine: IScriptEngine;
begin
  //raise Exception.Create('the free edition of LightweightCTI is not support Scripts.');
  Result := FScriptEngine;
end;

function TAbstractChannel.GetTimeOut: Integer;
begin
  Result := FTimeOut;
end;

function TAbstractChannel.GetVoiceResource: TVoiceResourceType;
begin
  Result := FVoiceResource;
end;

function TAbstractChannel.InnerGetDtmf: string;
begin
  Result := FDtmf;
end;

procedure TAbstractChannel.InnerInitialize;
begin
  FChannelManager := nil;
  FDtmf           := '';
  FLinkToChannel  := -1;
  FRunning        := False;
  FTimeOut        := GCDefaultTimeOut;
  FreeOnTerminate := False;
  FBackspaceKey   := '*';
  FScriptEngine   := nil; // ��̬�ű�����
  FVoiceResource  := vrtStandard;

  if FChannelType = ctEmpty then
    FStatus := csDisable
  else
    FStatus := csFree;
end;

function TAbstractChannel.QueryInterface(const IID: TGUID;
  out Obj): HResult;
begin
  if FVCLComObject = nil then
  begin
    if GetInterface(IID, Obj) then Result := S_OK
    else Result := E_NOINTERFACE
  end else
    Result := IVCLComObject(FVCLComObject).QueryInterface(IID, Obj);
end;

procedure TAbstractChannel.SetBackspaceKey(const Value: Char);
begin
  FBackspaceKey := Value;
end;

{-------------------------------------------------------------------------------
  Procedure: TAbstractChannel.SetStatus
  Author:    Xsp
  DateTime:  2006.05.23
  Arguments: const Value: TChannelStatus
  Result:    None
  Popose:    ���õ�ǰͨ����״̬����ʵ������ͨ�����Է��ʴ˳�Ա
-------------------------------------------------------------------------------}
procedure TAbstractChannel.SetChannelStatus(const Value: TChannelStatus);
begin
  if FStatus <> Value then
  begin
    FLastStatus := FStatus;
    FStatus := Value;
    // ����ͨ��״̬�ı��¼�
    if Assigned(FOnStatusChanged) then
      FOnStatusChanged(Self);
  end;
end;

procedure TAbstractChannel.SetRunning(const Value: Boolean);
begin
  if FRunning <> Value then
    FRunning := Value;
end;

procedure TAbstractChannel.SetScripts(const Value: AnsiString);
begin
  if FScripts <> Value then
  begin
    FScripts := Value;
    if Assigned(FOnScriptsChanged) then
      FOnScriptsChanged(Self);
  end;
end;

{-------------------------------------------------------------------------------
  Procedure: TAbstractChannel.SetTimeOut
  Author:    Xsp
  DateTime:  2006-08-09
  Arguments: const Value: Integer
  Result:    None
  Purpose:   ���ó�ʱʱ��
-------------------------------------------------------------------------------}
procedure TAbstractChannel.SetTimeOut(const Value: Integer);
begin
  if FTimeOut <> Value then
    FTimeOut := Value * 1000;
end;

procedure TAbstractChannel.SetVoiceResource(
  const Value: TVoiceResourceType);
begin
  if FVoiceResource <> Value then
    FVoiceResource := Value;
end;

function TAbstractChannel._AddRef: Integer;
begin
  if FVCLComObject = nil then
    Result := -1
  else
    Result := IVCLComObject(FVCLComObject)._AddRef;
end;

function TAbstractChannel._Release: Integer;
begin
  if FVCLComObject = nil then
    Result := -1
  else
    Result := IVCLComObject(FVCLComObject)._Release;
end;

{ TAbstractTaskManager }

{-------------------------------------------------------------------------------
  Procedure: TAbstractTaskManager.ClearTasks
  Author:    Xsp
  DateTime:  2006-08-10
  Arguments: None
  Result:    None
  Purpose:   ������ж����е������������������ʱ��Ҫȷ����û�н��д���������
    �б��棬�Ա����´μ���ʱ�������½��к��д���
-------------------------------------------------------------------------------}
procedure TAbstractTaskManager.ClearTasks;
var
  Atask: TObject;
begin
  FLocked := True;
  try
    while TaskCount > 0 do
    begin
      Atask := FTaskQueue.Pop();
      if Assigned(Atask) and (Atask is TAbstractCallTask) then
      begin
        TAbstractCallTask(Atask).StoreTask;
        FreeAndNil(Atask);
      end;
    end;
  finally
    FLocked := False;
  end;
end;

{-------------------------------------------------------------------------------
  Procedure: TAbstractTaskManager.Create
  Author:    Xsp
  DateTime:  2006-08-09
  Arguments: AOwner: TComponent
  Result:    None
  Purpose:   �������������
-------------------------------------------------------------------------------}
constructor TAbstractTaskManager.Create(AOwner: TComponent);
begin
  inherited Create(AOwner);
  FTaskQueue := TObjectQueue.Create;
  FActive := False;
  FLocked := False;
  FInterval := GCTaskManagerInterval; // Ĭ�ϵ�����������ʱ��Ϊ30��
  FTimer := TTimer.Create(AOwner);
  FTimer.Interval := FInterval * 1000;
end;

{-------------------------------------------------------------------------------
  Procedure: TAbstractTaskManager.Create
  Author:    Xsp
  DateTime:  2006-08-09
  Arguments: AOwner: TComponent; AInterval: Integer
  Result:    None
  Purpose:   ����������������������������ʱ����
-------------------------------------------------------------------------------}
constructor TAbstractTaskManager.Create(AOwner: TComponent;
  AInterval: Integer);
begin
  Create(AOwner);
  FInterval := AInterval;
  FTimer.Interval := FInterval * 1000;
end;

{-------------------------------------------------------------------------------
  Procedure: TAbstractTaskManager.Destroy
  Author:    Xsp
  DateTime:  2006-08-09
  Arguments: None
  Result:    None
  Purpose:   �ͷ����������
-------------------------------------------------------------------------------}
destructor TAbstractTaskManager.Destroy;
begin
  Active := False;
  FLocked := True;
  try
    ClearTasks;
    FTaskQueue.Free;
    FTimer.Enabled := False;
    FTimer.Free;
  finally
    FLocked := False;
  end;
  inherited;
end;

function TAbstractTaskManager.GetActive: Boolean;
begin
  Result := FActive;
end;

function TAbstractTaskManager.GetInterval: Integer;
begin
  Result := FInterval;
end;

function TAbstractTaskManager.GetLocked: Boolean;
begin
  Result := FLocked;
end;

function TAbstractTaskManager.GetMaxlimitCount: Integer;
begin
  Result := FMaxlimitCount;
end;

{-------------------------------------------------------------------------------
  Procedure: TAbstractTaskManager.GetNextTask
  Author:    Xsp
  DateTime:  2006-08-10
  Arguments: None
  Result:    ITask
  Purpose:   �Ӻ��ж�����ȡ��һ�������������ҵ�񿪷���Ա��Ҫ�Է��صĶ������
  �������ͼ�顣
-------------------------------------------------------------------------------}
function TAbstractTaskManager.GetNextTask: TObject;
begin
  if not FLocked and (GetTaskCount > 0) then
    Result := FTaskQueue.Pop
  else
    Result := nil;
end;

function TAbstractTaskManager.GetTaskCount: Integer;
begin
  Result := FTaskQueue.Count;
end;

function TAbstractTaskManager.GetTaskQueue: TObjectQueue;
begin
  Result := FTaskQueue;
end;

{-------------------------------------------------------------------------------
  Procedure: TAbstractTaskManager.SetActive
  Author:    Xsp
  DateTime:  2006-08-09
  Arguments: const Value: Boolean
  Result:    None
  Purpose:   ���������������ǰ��״̬�����״̬Ϊ������Ҫ�����ڲ���Timer�ؼ�����
    ����Ը���Ԥ���ʱ���������������С�
-------------------------------------------------------------------------------}
procedure TAbstractTaskManager.SetActive(const Value: Boolean);
begin
  if FActive <> Value then
  begin
    FActive := Value;
    FTimer.Enabled := FActive;
  end;
end;

{-------------------------------------------------------------------------------
  Procedure: TAbstractTaskManager.SetInterval
  Author:    Xsp
  DateTime:  2006-08-09
  Arguments: const Value: Integer
  Result:    None
  Purpose:   ���������������ʱ��������λΪ��
-------------------------------------------------------------------------------}
procedure TAbstractTaskManager.SetInterval(const Value: Integer);
begin
  if FInterval <> Value then
  begin
    FInterval := Value;
    FTimer.Interval := FInterval * 1000;
  end;
end;

{-------------------------------------------------------------------------------
  Procedure: TAbstractTaskManager.SetMaxlimitCount
  Author:    Xsp
  DateTime:  2006-08-09
  Arguments: const Value: Integer
  Result:    None
  Purpose:   ����������з�ֵ���Ա�֤����������ܹ���������������ּ���������
-------------------------------------------------------------------------------}
procedure TAbstractTaskManager.SetMaxlimitCount(const Value: Integer);
begin
  if FMaxlimitCount <> Value then
    FMaxlimitCount := Value;
end;

{ TAbstractCallTask }

{-------------------------------------------------------------------------------
  Procedure: TAbstractCallTask.Create
  Author:    Xsp
  DateTime:  2006-08-10
  Arguments: AOwner: TComponent
  Result:    None
  Purpose:   ��������
-------------------------------------------------------------------------------}
constructor TAbstractCallTask.Create(AOwner: TComponent);
begin
  inherited Create(AOwner);
  FTaskID  := '';
  FCalling := False;
  FSession := nil;
end;

{-------------------------------------------------------------------------------
  Procedure: TAbstractCallTask.Create
  Author:    Xsp
  DateTime:  2006-08-10
  Arguments: AOwner: TComponent; AtaksID: string; ASession: ISession
  Result:    None
  Purpose:   ��������ָ�������ʶ���Ự
-------------------------------------------------------------------------------}
constructor TAbstractCallTask.Create(AOwner: TComponent; AtaskID: string;
  ASession: ISession);
begin
  Create(AOwner);
  FTaskID  := AtaskID;
  FCalling := False;
  FSession := ASession;
end;

function TAbstractCallTask.GetSession: ISession;
begin
  Result := FSession;
end;

function TAbstractCallTask.GetTaskID: string;
begin
  Result := FTaskID;
end;

{ TAbstractTTSEngine }

constructor TAbstractTTSEngine.Create(AOwner: TComponent);
begin
  inherited;
  FcanWork := False;
end;

constructor TAbstractTTSEngine.Create(AOwner: TComponent; Aloger: ILoger);
begin
  Create(AOwner);
  FLoger := Aloger;
  Initialize;
end;

destructor TAbstractTTSEngine.Destroy;
begin
  Release;
  inherited;
end;

{ TChannelManager }

constructor TChannelManager.Create(AOwner: TComponent);
var
  I: Integer;
begin
  inherited;
  FtruncChannels := TList.Create;  FuserChannels := TList.Create;
  FCTICardDrivers := TList.Create;
  FCSList := TList.Create;
  FTTSEngine := nil;
  
  FHandleIndex := 0;
  FconfigFile  := '';
  FRootPath    := GCConfigurationRootPath + '\';
  for I := 0 to GCMaxCTICard - 1 do
    FHandleArray[I] := 0; // �����ʼ��Ϊ0
end;

{-------------------------------------------------------------------------------
  Procedure: TChannelManager.AddChannel
  Author:    Xsp
  DateTime:  2006-08-23
  Arguments: AChannel: TAbstractChannel
  Result:    None
  Purpose:   ��ͨ��������ע���µ�ͨ����ע����ͨ��ʱϵͳ������û������Ƿ�ָ����
  ����ͨ����ʼ���¼����ڴ��¼��û����Խ�һ����ͨ�����д����������Ӧ���¼�����
-------------------------------------------------------------------------------}
procedure TChannelManager.RegisterTruncChannel(AChannel: TAbstractChannel);
begin
  if Assigned(FOnChannelInitialized) then
    FOnChannelInitialized(Self, AChannel);

  FtruncChannels.Add(Pointer(AChannel));
end;

procedure TChannelManager.RegisterUserChannel(AChannel: TAbstractChannel);
begin
  if Assigned(FOnChannelInitialized) then
    FOnChannelInitialized(Self, AChannel);

  FuserChannels.Add(Pointer(AChannel));
end;

constructor TChannelManager.Create(AOwner: TComponent; const AconfigFile: string);
begin
  Create(AOwner);
  FconfigFile := AconfigFile;
end;

{-------------------------------------------------------------------------------
  Procedure: TChannelManager.Destroy
  Author:    Xsp
  DateTime:  2006-08-17
  Arguments: None
  Result:    None
  Purpose:   �ͷ�ϵͳ��ռ�õ���Դ�����ͷ���ԴʱҪע��˳��Ȼ�����ϵͳ�����Դ
    ������Ч��
-------------------------------------------------------------------------------}
destructor TChannelManager.Destroy;
var
  I: Integer;
  ActiDriver: TAbstractCTICardDriver;
  Acs: TCriticalSection;
  Achnl: TAbstractChannel;
  K: Cardinal;
begin
  // ֹͣ����ͨ���Ĳ���
  for I := FtruncChannels.Count - 1 downto 0 do
  begin
    Achnl := TAbstractChannel(FtruncChannels.Items[I]);
    if Assigned(Achnl) then
    begin
      FLoger.Info(Format('ֹͣ������ %s �µ� %d ��ͨ���Ľű�ִ��',
        [TAbstractCTICardDriver(Achnl.Owner).Name, Achnl.ChannelID]));
      Achnl.Terminate;
      Achnl.FScriptEngine := nil;
    end;
  end;
  for I := FuserChannels.Count - 1 downto 0 do
  begin
    Achnl := TAbstractChannel(FuserChannels.Items[I]);
    if Assigned(Achnl) then
    begin
      FLoger.Info(Format('ֹͣ������ %s �µ� %d ��ͨ���Ľű�ִ��',
        [TAbstractCTICardDriver(Achnl.Owner).Name, Achnl.ChannelID]));
      Achnl.Terminate;
      Achnl.FScriptEngine := nil;
    end;
  end;
  K := GetTickCount;
  while GetTickCount - K < 5000 do ; // �ӳ�5�����Ա�����ͨ��������������

  FConfiguration.Free;
  if Assigned(FTaskManater) then
  begin
    FLoger.Info('������������������δ��ɵ����񡭡�');
    FTaskManater.Free;
    FLoger.Info('ж�����������');
  end;

  // �ͷ�TTS����
  if Assigned(FTTSEngine) then
  begin
    FLoger.Info('ж��TTS��������');
    FTTSEngine := nil;
  end;

  // �ͷŰ忨������
  for I := FCTICardDrivers.Count - 1 downto 0 do
  begin
    ActiDriver := TAbstractCTICardDriver(FCTICardDrivers.Items[I]);
    FLoger.Info(Format('ж�������� %s ', [ActiDriver.Name]));
    ActiDriver.Free;
  end;
  FCTICardDrivers.Free;

  // �ͷ��Ѿ����صĳ���
  FLoger.Info('ж��ϵͳ���ص��ⲿ��Դ����');
  for I := Low(FHandleArray) to High(FHandleArray) do
    if FHandleArray[I] <> 0 then
      FreeLibrary(FHandleArray[I]);

  // �ͷ��ٽ����б�
  FLoger.Info('�ͷ�ϵͳ������ٽ�������');
  for I := FCSList.Count - 1 downto 0 do
  begin
    Acs := TCriticalSection(FCSList.Items[I]);
    Acs.Free;
  end;
  FCSList.Free;

  // �ͷ���־���
  FLoger.Info('ж����־����');
  FLoger.Info('-------------------!!!LIGHTWEIGHTCTI SHUTDOWN!!!-------------------');
  FLoger.Info('-------------------------------------------------------------------');
  FLoger := nil;

  inherited;
end;

{-------------------------------------------------------------------------------
  Procedure: TChannelManager.EnterCS
  Author:    Xsp
  DateTime:  2006-08-17
  Arguments: const ACSName: string
  Result:    None
  Purpose:   �����ٽ���������ٽ��������������ȴ���һ���ٽ���
-------------------------------------------------------------------------------}
procedure TChannelManager.EnterCS(const ACSName: string);
var
  Acs: TCriticalSection;
begin
  Acs := GetCS(ACSName);
  if not Assigned(Acs) then
  begin
    Acs := TCriticalSection.Create(ACSName);
    FCSList.Add(Pointer(Acs)); // ���ٽ�����������б�
  end;
  Acs.EnterCS;
  if Assigned(FOnAfterEnter) then  FOnAfterEnter(Self);
end;

{-------------------------------------------------------------------------------
  Procedure: TChannelManager.GetCS
  Author:    Xsp
  DateTime:  2006-08-17
  Arguments: const Aname: string
  Result:    TCriticalSection
  Purpose:   ���ٽ����б��л�ȡһ��ָ�����Ƶ��ٽ�������
-------------------------------------------------------------------------------}
function TChannelManager.GetCS(const Aname: string): TCriticalSection;
var
  I: Integer;
  Acs: TCriticalSection;
begin
  Result := nil;
  for I := 0 to FCSList.Count - 1 do
  begin
    Acs := TCriticalSection(FCSList.Items[I]);
    if Acs.Name = Aname then
    begin
      Result := Acs;
      Break;
    end;
  end;
end;

function TChannelManager.GetLoger: ILoger;
var
  Atype, AdllFile: string;
  J: Integer;
  LoadLoger: TLoadLoger;
  AHandle: THandle;
  ALoger: ILoger;
begin
  Atype := FConfiguration.GetNodeAttribute(FRootPath + 'Loger', 'type');
  // ���û��������־�����ʹ��ͨ������������־���
  if Trim(Atype) = '' then
  begin
    Result := FLoger;
    Exit;
  end;

  // ��ʼ����־���
  J := Pos(',', Atype);
  if J <> 0 then // ��DLL����
  begin
    AdllFile := Copy(Atype, J, Length(Atype) - J);
    Atype := Copy(Atype, 1, J - 1);
    if FileExists(AdllFile) then
      raise Exception.Create(Format('��־��¼��� %s ����Ӧ�ĳ��� "%s" ������',
        [Atype, AdllFile]));
    AHandle := LoadLibrary(PChar(AdllFile));
    if AHandle <> 0 then
    begin
      FHandleArray[FHandleIndex] := AHandle; // ������
      Inc(FHandleIndex);
      LoadLoger := GetProcAddress(AHandle, PChar('LoadLoger'));
      if Assigned(LoadLoger) then
        ALoger := LoadLoger(Application);
    end;
  end else
    ALoger := TComponentClass(GetClass(Atype)).Create(Self) as ILoger; // ֱ�Ӵ�����־���

  Result := ALoger;
end;

{-------------------------------------------------------------------------------
  Procedure: TChannelManager.Initialize
  Author:    Xsp
  DateTime:  2006-08-15
  Arguments: None
  Result:    None
  Purpose:   ��ʼ��LightweightCTI��ʹ�䴦��׼������״̬���ڳ�ʼ��ʱ�����ȼ����
    û��Ϊͨ��������ָ�������ļ������û�����׳��쳣��
-------------------------------------------------------------------------------}
procedure TChannelManager.Initialize;

  //��ʼ����־��¼�����ϵͳ�����ṩһ����־��������׳�һ���쳣
  procedure InitLoger(Atype: string);
  var
    AHandle: Cardinal;
    AlogerType, AdllFile: string;
    J: Integer;
    LoadLoger: TLoadLoger;
  begin
    J := Pos(',', Atype);
    if J <> 0 then // ��DLL����
    begin
      AlogerType := Copy(Atype, 1, J - 1);
      AdllFile := Copy(Atype, J + 1, Length(Atype) - J);
      if FileExists(AdllFile) then
        raise Exception.Create(Format('��־��¼��� %s ����Ӧ�ĳ��� "%s" ������',
          [AlogerType, AdllFile]));
      AHandle := LoadLibrary(PChar(AdllFile));
      if AHandle <> 0 then
      begin
        FHandleArray[FHandleIndex] := AHandle; // ������
        Inc(FHandleIndex);
        LoadLoger := GetProcAddress(AHandle, PChar('LoadLoger'));
        if Assigned(LoadLoger) then
          FLoger := LoadLoger(Application);
      end;
    end else
      FLoger := TComponentClass(GetClass(Atype)).Create(Self) as ILoger; // ֱ�Ӵ�����־���

    // �ɹ�������־���
    if Assigned(FLoger) then
    begin
      FLoger.Info('-------------------!!!LIGHTWEIGHTCTI STARTED!!!--------------------');
      FLoger.Info('-------------------------------------------------------------------');
      FLoger.Info('��ʼ����־��¼����ɹ�')
    end else
      raise Exception.Create(Format('��ʼ����־��¼��� %s ʧ��', [Atype]));
  end;

  // ��ʼ�����������
  procedure InitTaskManager(const Aname, Atype: string; const AInterval,
    AMaxlimitCount: Integer; const AActive: Boolean);
  var
    AHandle: Cardinal;
    ATaskManagerType, AdllFile: string;
    J: Integer;
    LoadTaskManager: TLoadTaskManager;
  begin
    if Trim(Atype) = '' then Exit;
    try
      FLoger.Info('׼����ʼ���������������');
      J := Pos(',', Atype);
      if J <> 0 then
      begin
        ATaskManagerType := Copy(Atype, 1, J - 1);
        AdllFile := Copy(Atype, J + 1, Length(Atype) - J);
        if not FileExists(AdllFile) then
        begin
          FLoger.Error(Format('��������� %s ����Ӧ�ĳ��� "%s" ������',
            [ATaskManagerType, AdllFile]));
          Exit;
        end;
        AHandle := LoadLibrary(PChar(AdllFile));
        if AHandle <> 0 then
        begin
          FHandleArray[FHandleIndex] := AHandle; // ������
          Inc(FHandleIndex);
          LoadTaskManager := GetProcAddress(AHandle, PChar('LoadTaskManager'));
          if Assigned(LoadTaskManager) then
            FTaskManater := LoadTaskManager(Self, Application)
          else
            FLoger.Info(Format('��ʼ����������� %s ʧ��', [Atype]));
        end;
      end else
      begin
        FTaskManater := TAbstractTaskManager(TComponentClass(GetClass(Atype)).NewInstance);
        FTaskManater.Create(Self, AInterval);
      end;

      // ��������������������
      if Assigned(FTaskManater) then
      begin
        FTaskManater.Name := Aname;
        FTaskManater.Interval := AInterval;
        FTaskManater.MaxlimitCount := AMaxlimitCount;
        FTaskManater.Active := AActive;
        FLoger.Info('��ʼ������������ɹ�');
      end;
    except on E: Exception do
      FLoger.Error(Format('��ʼ����������� %s ʧ��', [Atype]), E);
    end;
  end;

  // ��ʼ��TTS����
  procedure InitTTSEngine(const Atype: string);
  var
    AdllFile: string;
    AHandle: Cardinal;
    LoadTTSEngine: TLoadTTSEngine;
    J: Integer;
    ATTS: TAbstractTTSEngine;
  begin
    if Trim(Atype) = '' then Exit;

    J := Pos(',', Atype);
    try
      if J <> 0 then
      begin
        AdllFile := Copy(Atype, J + 1, Length(Atype) - J);
        if not FileExists(AdllFile) then
        begin
          FLoger.Error(Format('TTS��������ļ� %s �����ڣ��޷���ʼ��TTS����', [AdllFile]));
          Exit;
        end;
        // ���ⲿ���򼯼���TTS����
        AHandle := LoadLibrary(PChar(AdllFile));
        if AHandle <> 0 then
        begin
          FHandleArray[FHandleIndex] := AHandle;
          FHandleIndex := FHandleIndex + 1;
          LoadTTSEngine := GetProcAddress(AHandle, 'LoadTTSEngine');
          if Assigned(LoadTTSEngine) then
          FTTSEngine := LoadTTSEngine(Self, FLoger, Application);
        end else
          FLoger.Error(Format('����TTS��������ļ� %s ʧ��', [AdllFile]));
      end else
      begin
        // ֱ�Ӵ���TTS����
        ATTS := TAbstractTTSEngine(GetClass(Atype).NewInstance);
        ATTS.Create(Self, FLoger);
        FTTSEngine := ATTS as ITTSEngine;
      end;
    except on E: Exception do
      FLoger.Error(Format('����TTS���� %s ʱ���ִ���', [Atype]), E);
    end;
  end;

var
  Adriver, AtmpDRV: TAbstractCTICardDriver;
  AHandle: Cardinal;
  Loaddriver: TLoadCTIDriver;
  Astr, Aname, Atype, Adllfile, AInterval, AMaxlimitCount: string;
  N, I, J: Integer;
  AActive: Boolean;
  AdriverClass: TPersistentClass;
begin
  if FconfigFile = '' then
    raise Exception.Create('û��Ϊϵͳ�ṩ�����ļ����ܽ��г�ʼ����');

  // ��ʼ��ϵͳ������
  if not Assigned(FConfiguration) then
  begin
    FConfiguration := TSampleXML.Create(FconfigFile);
  end;
  // ������Ŀ�ļ��С���Դ�ļ��м�ͨ����ʱֵ
  FProjectDir := FConfiguration.GetNodeText(FRootPath + 'ProjectsDir');
  FResourceDir := FConfiguration.GetNodeText(FRootPath + 'ResourceDir');
  Astr := FConfiguration.GetNodeText(FRootPath + 'TimeOut');
  FTimeOut := StrToIntDef(Astr, GCDefaultTimeOut);
  InitLoger(FConfiguration.GetNodeAttribute(FRootPath + 'Loger', 'type')); // ��ʼ����־���
  
  // ������������ϵͳĿǰ����֧�ֶ���忨�������͵İ忨
  N := FConfiguration.GetChildCount(FRootPath + 'Adpaters', 'Driver');
  for I := 0 to N - 1 do // ˳���ȡÿ���������������ļ�������ʼ��
  try
    Aname := FConfiguration.GetNodeAttribute(FRootPath + 'Adpaters\Driver', 'name', I);
    Atype := FConfiguration.GetNodeAttribute(FRootPath + 'Adpaters\Driver', 'type', I);
    FLoger.Info('׼����ʼ���忨������' + Aname + ', ����Ϊ ' + Atype);
    Adriver := nil;
    J := Pos(',', Atype);
    if J <> 0 then // �ֽ����������ͼ�DLL�ļ�
    begin
      Adllfile := Copy(Atype, J + 1, Length(Atype) - J);
      Atype    := Copy(Atype, 1, J - 1);
      // ���ⲿ����������
      if not FileExists(Adllfile) then
      begin
        FLoger.Error(Format('�忨�������������������ļ� "%s" ������', [Adllfile]));
        Continue;
      end;
      AHandle := LoadLibrary(PChar(Adllfile)); // ���س���
      if AHandle <> 0 then
      begin
        FHandleArray[FHandleIndex] := AHandle;
        Inc(FHandleIndex);
        Loaddriver := GetProcAddress(AHandle, PChar('LoadCTIDriver'));
        if Assigned(Loaddriver) then
          Adriver := Loaddriver(Self, Application); // �����忨������
        if Adriver = nil then
          FLoger.Info('��ʼ���忨������' + Aname + ' ʧ��');
      end else
        FLoger.Error(Format('���ذ忨�������������� "%s" ʧ��', [Adllfile]))
    end else
    begin // ֱ�Ӽ���
      AdriverClass := GetClass(Atype);
      Adriver := TAbstractCTICardDriver(AdriverClass.NewInstance);
      Adriver.Create(Self, FLoger);
    end;

    // ��ǰ���������سɹ�
    if Assigned(Adriver) then
    begin
      FLoger.Info('����������' + Aname + '�ɹ�');
      Adriver.Name := Aname;
      Adriver.ConfigIndex := I; // ͨ��������������忨���������������������Ϣ
      Adriver.FchannelIndex := FtruncChannels.Count+FuserChannels.Count;
      Adriver.Initialize;
      FCTICardDrivers.Add(Pointer(Adriver)); // ����������ӵ��б���
    end;
  except on E: Exception do
    begin
      FLoger.Error('����������' + Aname + 'ʧ��', E);
      Continue;
    end;
  end;

  { ��ʼ��TTS���棬������Щ�忨�����TTS���������ڰ忨�Ƿ���سɹ��������ڳ�ʼ��
    ����TTS����ʱ�������������İ忨�Ƿ��Ѿ����ء�}
  Atype := FConfiguration.GetNodeAttribute(FRootPath + 'TTSEngine', 'type');
  Astr := FConfiguration.GetNodeAttribute(FRootPath + 'TTSEngine', 'dependency'); // ������Driver��name����
  if Trim(Astr) <> '' then
  begin
    for I := 0 to FCTICardDrivers.Count - 1 do
    begin
      AtmpDRV := TAbstractCTICardDriver(FCTICardDrivers.Items[I]);
      if Assigned(AtmpDRV) and (AtmpDRV.Name = Astr) then
      begin
        InitTTSEngine(Atype);
        Break;
      end;
    end;
  end else
    InitTTSEngine(Atype);

  // ��ʼ�����������
  Aname := FConfiguration.GetNodeAttribute(FRootPath + 'TaskManager', 'name');
  Atype := FConfiguration.GetNodeAttribute(FRootPath + 'TaskManager', 'type');
  AInterval := FConfiguration.GetNodeAttribute(FRootPath + 'TaskManager', 'interval');
  AMaxlimitCount := FConfiguration.GetNodeAttribute(FRootPath + 'TaskManager', 'maxlimitcount');
  Astr := FConfiguration.GetNodeAttribute(FRootPath + 'TaskManager', 'active');
  AActive := iif((LowerCase(Astr) = 'yes') or (LowerCase(Astr) = 'true'), True, False);

  InitTaskManager(Aname, Atype, StrToIntDef(AInterval, GCTaskManagerInterval),
    StrToIntDef(AMaxlimitCount, GCMaxLimitCount), AActive);
end;

{-------------------------------------------------------------------------------
  Procedure: TChannelManager.LeaveCS
  Author:    Xsp
  DateTime:  2006-08-17
  Arguments: const ACSName: string
  Result:    None
  Purpose:   �뿪�ٽ��������ȸ���ָ�����ٽ������ƻ�ȡһ���ٽ��������������
    �������ʾ���δ��������
-------------------------------------------------------------------------------}
procedure TChannelManager.LeaveCS(const ACSName: string);
var
  Acs: TCriticalSection;
begin
  Acs := GetCS(ACSName);
  if Assigned(Acs) then
    Acs.LeaveCS;
end;

procedure TChannelManager.SetProjectDir(const Value: TFileName);
begin
  FProjectDir := Value;
end;

procedure TChannelManager.SetResourceDir(const Value: TFileName);
begin
  FResourceDir := Value;
end;

{-------------------------------------------------------------------------------
  Procedure: TChannelManager.SetTimeOut
  Author:    Xsp
  DateTime:  2006-08-11
  Arguments: const Value: Integer
  Result:    None
  Purpose:   ����ϵͳȫ�ֳ�ʱ����
-------------------------------------------------------------------------------}
procedure TChannelManager.SetTimeOut(const Value: Integer);
var
  I: Integer;
  Achnl: TAbstractChannel;
begin
  if FTimeOut <> Value then
  begin
    FTimeOut := Value;
    for I := 0 to FtruncChannels.Count - 1 do
    begin
      Achnl := TAbstractChannel(FtruncChannels.Items[I]);
      if Assigned(Achnl) then // ��ͨ����Ч�Խ��м��
        Achnl.TimeOut := Value;
    end;
    for I := 0 to FuserChannels.Count - 1 do
    begin
      Achnl := TAbstractChannel(FuserChannels.Items[I]);
      if Assigned(Achnl) then // ��ͨ����Ч�Խ��м��
        Achnl.TimeOut := Value;
    end;
  end;
end;

{ TCriticalSection }

constructor TCriticalSection.Create(const Aname: string);
begin
  FName := Aname;
  InitializeCriticalSection(FCS); 
end;

destructor TCriticalSection.Destroy;
begin
  DeleteCriticalSection(FCS);
  inherited;
end;

procedure TCriticalSection.EnterCS;
begin
  EnterCriticalSection(FCS);
end;

procedure TCriticalSection.LeaveCS;
begin
  LeaveCriticalSection(FCS);
  application.ProcessMessages;
end;

{ TAbstractSession }

constructor TAbstractSession.Create(AOwner: TComponent);
begin
  inherited;
  SetSessionID('');
  FSessionType := stCallIn;
  FStartTime := Now;
end;

constructor TAbstractSession.Create(AOwner: TComponent;
  APhoneNumber: string; ASessionType: TSesstionType);
begin
  Create(AOwner);
  FPhoneNumber := APhoneNumber;
  FSessionType := ASessionType;
end;

destructor TAbstractSession.Destroy;
begin
  inherited;
end;

function TAbstractSession.GetEndTime: TDateTime;
begin
  Result := FEndTime;
end;

function TAbstractSession.GetPhoneNumber: string;
begin
  Result := FPhoneNumber;
end;

function TAbstractSession.GetSessionID: string;
begin
  Result := FSessionID;
end;

procedure TAbstractSession.SetSessionID(const ASessionID: string);
var
  Aguid: TGUID; SID : string;
begin
  if ASessionID = '' then begin
    CreateGUID(Aguid);
    SID := UpperCase(GUIDToString(Aguid));
  end else SID := ASessionID;
  FSessionID := SID;
end;

function TAbstractSession.GetSessionType: TSesstionType;
begin
  Result := FSessionType;
end;

function TAbstractSession.GetStartTime: TDateTime;
begin
  Result := FStartTime;
end;

procedure TAbstractSession.SetEndTime(const Value: TDateTime);
begin
  if FEndTime <> Value then FEndTime := Value;
end;

procedure TAbstractSession.SetPhoneNumber(const Value: string);
begin
  FPhoneNumber := Value;
end;

procedure TAbstractSession.SetSessionType(const Value: TSesstionType);
begin
  FSessionType := Value;
end;

procedure TAbstractSession.SetStartTime(const Value: TDateTime);
begin
  FStartTime := Value;
end;

end.
