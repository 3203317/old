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

unit LightweightCTI;

interface

uses
  Classes, Windows, Messages, SysUtils, Contnrs, ExtCtrls, Forms, XMLParse,
  GlobalConstants;

type
  // 会话类型
  TSesstionType = (stCallIn, stCallOut, stFree);
  // 通道类型
  TChannelType = (ctUser, ctTrunk, ctEmpty, ctRecord, ctMessage, ctFax, ctVirtual);
  // 语音资源类型
  TVoiceResourceType = (vrtUnkonwn, vrtStandard, vrtLocalism, vrtEnglish);

  // 通道状态
  TChannelStatus = (
    csRing,          // 振铃
    csFree,          // 空闲
    csOffHook,       // 摘机
    csDial,          // 拨号
    csWaitingSignal, // 等待拨号音结果
    csTimeOut,       // 超时
    csConnect,       // 连接，被叫摘机
    csPlaying,       // 放音
    csRecording,     // 录音
    csGetDtmf,       // 收码
    csHangup,        // 挂机
    csLink,          // 连接，会议模式
    csDisable,      // 禁用
    csWait);

  // 放音结果
  TPlayMessageResult = (
    pmrPlaying,       //放音进行中
    pmrComplete,     // 放音正常完毕
    pmrKeyPress,     // 对方按键打断
    pmrHangup);      // 放音未完挂机

  // 录音结果
  TRecordFileResult = (
    rfrRecording,
    rfrComplete,     // 录音正常结束
    rfrKeyPress,     // 对方按键打断
    rfrHangup);      // 录音未完挂机

  // 信号音类型
  TSignalType = (stNoResult, stNoDialTone, stBusy, stConnect, stNoBody, stNoSignal, stTimeOut);

  // TTS语音转换类型
  TPlayNumberType = (pntMessage, pntTelphone, pntMoney, pntNumber);

  // 日志记录级别
  TLogLevel = (llDebug, llInfo, llWarn, llError, llFatal, llTalk);

  // 日志组件接口
  ILoger = interface ['{069AC7C9-0A96-468F-A006-EFC6670E674D}']
    procedure Debug(Amsg: string); overload;                                    // 调试信息
    procedure Debug(Amsg: string; Aexception: Exception); overload;
    procedure Info(Amsg: string); overload;                                     // 一般信息
    procedure Info(Amsg: string; Aexception: Exception); overload;
    procedure Warn(Amsg: string); overload;                                     // 警告信息
    procedure Warn(Amsg: string; Aexception: Exception); overload;
    procedure Error(Amsg: string); overload;                                    // 错误信息
    procedure Error(Amsg: string; Aexception: Exception); overload;
    procedure Fatal(Amsg: string); overload;                                    // 致命错误信息
    procedure Fatal(Amsg: string; Aexception: Exception); overload;
  end;

  // 脚本引擎
  IScriptEngine = interface ['{BF724106-7A2C-4BAF-BA43-A7B54CBB31B8}']
    procedure LoadScripts(const Afilename: string);
    function Debug(OutPutList: TStringList): Integer;
    function RunScripts(OutPutList: TStringList): Integer;
  end;

  // TTS引擎接口
  ITTSEngine = interface ['{BC6787DE-2ACD-4FD8-86CB-B291E359CDB3}']
    function PlayMessage(Channel: TObject; const Amsg: string; const AllowBreak: Boolean = True;
      const APlayType: TPlayNumberType = pntMessage;
      const AVoiceResource: TVoiceResourceType = vrtStandard): TPlayMessageResult; // 放音
    function PlayToFile(Channel: TObject; const Atxt: string; const Afilename: string;
      const AVoiceResource: TVoiceResourceType = vrtStandard): Boolean;
  end;
  
  TAbstractSession = class;

  // TTS抽象接口　
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
      virtual; abstract; // 放音
    function PlayToFile(Channel: TObject; const Atxt: string; const Afilename: string;
      const AVoiceResource: TVoiceResourceType = vrtStandard): Boolean;
      virtual; abstract; // 放音到文件
    property CanWork: Boolean read FcanWork;
  end;

  // 板卡驱动程序适配器接口
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

  // 板卡清除事件
  TReleasingEvent = procedure(Sender: TObject; var Allowable: Boolean) of object;

  // 板卡驱动程序适配器抽象类，所有适配器实现都必须继承于此类
  TAbstractCTICardDriver = class(TComponent, ICTICardDriver)
  private
    FLoger: ILoger;
    FConfigIndex: Integer;
  protected
    FcanWork: Boolean;
    FchannelCount: Integer;
    FchannelIndex: Integer;

    // 适配器事件程序
    FOnInitialized: TNotifyEvent;
    FOnReleasing: TReleasingEvent;
    FOnReleased: TNotifyEvent;

    function GetCanWork: Boolean; virtual;
    function GetChannelCount: Integer; virtual;
    function InitializeChannel: Integer; virtual; abstract;
    property Loger: ILoger read FLoger; // 日志组件
  public
    constructor Create(Aowner: TComponent); overload; override;
    constructor Create(AOwner: TComponent; Aloger: ILoger); reintroduce; overload;
    destructor Destroy; override;

    // 适配器成员
    procedure Initialize; virtual;
    procedure Release; virtual; abstract;
    function CanClose: Boolean; virtual; abstract;
    property CanWork: Boolean read GetCanWork default False;
    property ChannelCount: Integer read GetChannelCount default 0;
    property ChannelIndex: Integer read FChannelIndex default 0; // 当前适配器第一个通道的索引号
    property ConfigIndex: Integer read FConfigIndex write FConfigIndex default -1; // 当前板卡在配置文件中的索引
  published
    property OnInitialized: TNotifyEvent read FOnInitialized write FOnInitialized;
    property OnReleasing: TReleasingEvent read FOnReleasing write FOnReleasing;
    property OnReleased: TNotifyEvent read FOnReleased write FOnReleased;
  end;

  // 语音卡通道接口
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

    // 振铃或挂机函数
    function RingDetect(const Atimes: Integer = 1): Boolean; // 振铃检测
    procedure StopRing();
    procedure OffHook; // 摘机
    function OffHookDetect: Boolean; // 内线摘机检测
    procedure HangUp; // 被叫挂机
    function HangUpDetect: Boolean; // 被叫挂机检测
    procedure ResetChannel; // 重置通道     
    function Schedule(AserverType: string; Afilelist: string): Integer;//获取最少接电话的坐席号


    // 主叫或被叫函数
    function GetCallerNumber: string; // 获取主叫号码
    function GetCalleeNumber: string; // 获取被叫号码

    // 拨号及放音函数
    function Dial(const Adialnum: string; const APrefixnum: string = ''):
      TSignalType; // 向外拨号
    function DtmfHit: Boolean; // 按键检测
    function GetDTMF(const Alength: Integer; Asuffix: string = '#';
      const Atimeout: Integer = 0): string; // 取DTMF号码
    procedure ClearDTMF; // 清空DTMF缓存
    function PlayMessage(const Atxt: string; const AllowBreak: Boolean = True):
      TPlayMessageResult; // 播放一段文字
    function PlayNumber(const Atxt: string; const AllowBreak: Boolean = True;
      const APlayType: TPlayNumberType = pntMessage): TPlayMessageResult; // 播放数字
    procedure PlayVoice(AvoiceType: integer);
    function PlayFile(const Afilelist, AfileExt: string;
      const AllowBreak: Boolean = True): TPlayMessageResult; // 播放语音文件
    function PlayFileToTrunkAndUser(const Afilelist, AfileExt: string;
      const AllowBreak: Boolean = True; AchannelID: Integer = -1): TPlayMessageResult; // 给双方播放音乐
    function PlayToFile(const Atxt: string; const Afilename: string;
      const AVoiceResource: TVoiceResourceType = vrtStandard): Boolean; // 播放到文件
    function SendMessage(const Atxt: string): Boolean; // 发送信息



    // 录音函数
    function RecordFile(const Afilename: string; ALengthTimes: Integer = 60;
      AllowBreak: Boolean = True): TRecordFileResult; // 录音


    // 通道连通函数
    function LinkTo(const AChannelID: Integer = -1;
      const AChannelType: TChannelType = ctUser): Integer; // 连接到某一通道
    function UnLink(const AChannelID: Integer = -1;
      const AChannelType: TChannelType = ctUser; ChannelToChannel:boolean = false): Integer; // 断开与某通道的连接
    function UnLinkAll: Boolean; // 断开与所有通道的连接
    function ListenTo(const AChannelID: Integer): Integer; // 监听某一通道

    property BackspaceKey: Char read GetBackspaceKey write SetBackspaceKey; // 删除键键值
    property ChannelID: Integer read GetChannelID; // 通道标识
    property ChannelType: TChannelType read GetChannelType; // 通道类型
    property Dtmf: string read InnerGetDtmf;
    property Status: TChannelStatus read GetChannelStatus; // 通道当前状态
    property LastStatus: TChannelStatus read GetLastStatus; // 通道前一次状态
    property ScriptEngine: IScriptEngine read GetScriptEngine; // 脚本引擎
    property VoiceResource: TVoiceResourceType read GetVoiceResource write
      SetVoiceResource; // 语音资源类型
    property TimeOut: Integer read GetTimeOut write SetTimeOut;
  end;

  // 收码事件
  TGetDTMFEvent = procedure (Sender: TObject; const Adtmf: string) of object;
  // 呼入/呼出事件
  TCallEvent = procedure (Sender: TObject; const ACallNumber: string) of object;
  // 通道忙事件
  TChannelBusyEvent = procedure(Sender: TObject; LinkChannel: TObject;
    const ACallNumber: string; var Data: Variant) of object;
  // 日志事件
  TChannelLogEvent = procedure (Sender: TObject; const Amsg: string) of object;
  // 脚本执行完成事件
  TScriptitsCompletedEvent = procedure (Sender: TObject; ScriptEngine: IScriptEngine;
    OutPutList: TStringList) of object;

  TChannelManager = class;

  // 通道抽象类
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
    FCallNumber: string; // 记录主叫或被叫号码
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

    // 辅助函数
    function CheckFile(const Afilename: string): string;

    property Owner: TComponent read FOwner;
    property Loger: ILoger read FLoger; // 日志组件
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

    // 振铃或挂机函数
    function RingDetect(const Atimes: Integer = 1): Boolean; virtual; abstract; // 振铃检测
    procedure StopRing();virtual;abstract;
    procedure OffHook; virtual; abstract; // 摘机
    function OffHookDetect: Boolean; virtual; abstract; // 内线摘机检测
    procedure HangUp; virtual; abstract; // 被叫挂机
    function HangUpDetect: Boolean; virtual; abstract; // 被叫挂机检测
    procedure ResetChannel; virtual; abstract; // 重置通道
    function Schedule(AserverType: string ; Afilelist: string): Integer;virtual; abstract;//获取最少接电话的坐席号


    // 主叫或被叫函数
    function GetCallerNumber: string; virtual; abstract; // 获取主叫号码
    function GetCalleeNumber: string; virtual; abstract; // 获取被叫号码

    // 拨号及放音函数
    function Dial(const Adialnum: string; const APrefixnum: string = ''):
      TSignalType; virtual; abstract; // 向外拨号
    function DtmfHit: Boolean; virtual; abstract; // 按键检测
    function GetDTMF(const Alength: Integer; Asuffix: string = '#';
      const Atimeout: Integer = 0): string; virtual; abstract; // 取DTMF号码
    procedure ClearDTMF; virtual; abstract; // 清空DTMF缓存
    function PlayMessage(const Atxt: string; const AllowBreak: Boolean = True):
      TPlayMessageResult; virtual; abstract; // 播放一段文字
    function PlayNumber(const Atxt: string; const AllowBreak: Boolean = True;
      const APlayType: TPlayNumberType = pntMessage): TPlayMessageResult;
      virtual; abstract; // 播放数字
    procedure PlayVoice(AvoiceType: integer);overload;virtual;abstract;
    function PlayFile(const Afilelist, AfileExt: string;
      const AllowBreak: Boolean = True): TPlayMessageResult; overload; virtual;
      abstract; // 播放语音文件

    function PlayFileToTrunkAndUser(const Afilelist, AfileExt: string;
      const AllowBreak: Boolean = True; AchannelID: Integer = -1): TPlayMessageResult; overload; virtual;abstract; // 给双方播放音乐

    function PlayToFile(const Atxt: string; const Afilename: string;
      const AVoiceResource: TVoiceResourceType = vrtStandard): Boolean;
      virtual; abstract; // 播放到文件
    function SendMessage(const Atxt: string): Boolean; virtual; abstract; // 发送信息


    // 录音函数
    function RecordFile(const Afilename: string; ALengthTimes: Integer = 60;
      AllowBreak: Boolean = True): TRecordFileResult; virtual; abstract; // 录音

    // 通道连通函数
    function LinkTo(const AChannelID: Integer = -1;
      const AChannelType: TChannelType = ctUser): Integer; virtual; abstract; // 连接到某一通道
    function UnLink(const AChannelID: Integer = -1;
      const AChannelType: TChannelType = ctUser; ChannelToChannel:boolean = false): Integer; virtual; abstract; // 断开与某通道的连接
    function UnLinkAll: Boolean; virtual; abstract; // 断开与所有通道的连接
    function ListenTo(const AChannelID: Integer): Integer; virtual; abstract; // 监听某一通道

    procedure ChannelLog(const Amsg: string); virtual; // 记录通道日志
    
    property BackspaceKey: Char read GetBackspaceKey write SetBackspaceKey; // 删除键键值
    property ChannelID: Integer read GetChannelID; // 通道标识
    property ChannelType: TChannelType read GetChannelType; // 通道类型
    property Dtmf: string read InnerGetDtmf;
    property Status: TChannelStatus read GetChannelStatus write SetChannelStatus; // 通道当前状态
    property LastStatus: TChannelStatus read GetLastStatus; // 通道前一次状态
    property ScriptEngine: IScriptEngine read GetScriptEngine; // 脚本引擎
    property VoiceResource: TVoiceResourceType read GetVoiceResource write
      SetVoiceResource; // 语音资源类型
    property TimeOut: Integer read GetTimeOut write SetTimeOut;

    property ChannelManager: TChannelManager read FChannelManager; // 通道管理器
    property Running: Boolean read FRunning write SetRunning default False; // 处理标志
    property Scripts: AnsiString read FScripts write SetScripts; // 脚本
    property Terminated;
  published
    property OnChannelCreated: TNotifyEvent read FOnChannelCreated
      write FOnChannelCreated; // 通道创建事件
    property OnScriptsChanged: TNotifyEvent read FOnScriptsChanged
      write FOnScriptsChanged; // 脚本文件变化事件
    property OnScriptsCompleted: TScriptitsCompletedEvent read FOnScriptsCompleted
      write FOnScriptsCompleted; // 脚本执行完成事件
    property OnStatusChanged: TNotifyEvent read FOnStatusChanged
      write FOnStatusChanged; // 状态变化事件
    property OnGetDTMF: TGetDTMFEvent read FOnGetDTMF write FOnGetDTMF; // 收码事件
    property OnCallIn: TCallEvent read FOnCallIn write FOnCallIn; // 呼入事件
    property OnCallOut: TCallEvent read FOnCallOut write FOnCallOut; // 呼出事件
    property OnChannelBusy: TChannelBusyEvent read FOnChannelBusy write FOnChannelBusy; // 通道忙事件
    property OnChannelLog: TChannelLogEvent read FOnChannelLog write FOnChannelLog; // 日志记录事件
    property OnRun: TNotifyEvent read FOnRun write FOnRun; // 业务逻辑执行事件
    property OnResetChannel: TNotifyEvent read FOnResetChannel write FOnResetChannel; // 重置通道事件
    property OnTimeOut: TNotifyEvent read FOnTimeOut write FOnTimeOut; // 超时事件
  end;

  // 会话接口
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

    procedure StoreSession; // 持久化会话

    property SessionID: string read GetSessionID; // 会话标识
    property StartTime: TDateTime read GetStartTime write SetStartTime; // 开始时间
    property EndTime: TDateTime read GetEndTime write SetEndTime; // 结束时间
    property SesstionType: TSesstionType read GetSessionType write SetSessionType; // 会话类型
    property PhoneNumber: string read GetPhoneNumber write SetPhoneNumber; // 电话号码
  end;

  // 会话抽象类
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

    procedure StoreSession; virtual; abstract; // 持久化会话

    property SessionID: string read GetSessionID write SetSessionID;// 会话标识
    property StartTime: TDateTime read GetStartTime write SetStartTime; // 开始时间
    property EndTime: TDateTime read GetEndTime write SetEndTime; // 结束时间
    property SesstionType: TSesstionType read GetSessionType write SetSessionType; // 会话类型
    property PhoneNumber: string read GetPhoneNumber write SetPhoneNumber; // 电话号码
  end;

  // 任务接口
  ITask = interface ['{D4B24567-0548-4A54-86B4-6D9035149A44}']
    function GetTaskID: string;
    procedure StoreTask;
    property TaskID: string read GetTaskID; // 任务标识
  end;

  // 呼入、呼出抽象类
  TAbstractCallTask = class(TComponent, ITask)
  private
    FCalling: Boolean;

    function GetSession: ISession;
    function GetTaskID: string;
  protected
    FTaskID: string;
    FSession: ISession;
    procedure StoreTask; virtual; abstract; // 保存任务
  public
    constructor Create(AOwner: TComponent); overload; override;
    constructor Create(AOwner: TComponent; AtaskID: string; ASession: ISession); reintroduce; overload; virtual;

    property TaskID: string read GetTaskID;
    property Calling: Boolean read FCalling write FCalling default False; // 呼叫标志
    property Session: ISession read GetSession; // 当前任务对应的会话
  end;

  // 队列填充器接口
  ITaskQueueFillter = interface ['{5813CA3C-FE00-47F3-9ECF-D3C026219699}']
    procedure FillQueue; // 填充操作
  end;

  // 任务管理器接口
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

    property Active: Boolean read GetActive write SetActive; // 任务管理器状态
    property Interval: Integer read GetInterval write SetInterval; // 队列填充间隔时间，秒
    property Locked: Boolean read GetLocked; // 队列锁定标志
    property MaxlimitCount: Integer read GetMaxlimitCount write SetMaxlimitCount; // 队列阀值
    property TaskQueue: TObjectQueue read GetTaskQueue; // 呼叫任务队列
    property TaskCount: Integer read GetTaskCount; // 队列中的任务数
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
    property Active: Boolean read GetActive write SetActive; // 任务管理器状态
    property Interval: Integer read GetInterval write SetInterval; // 队列填充间隔时间，秒
    property Locked: Boolean read GetLocked; // 队列锁定标志
    property MaxlimitCount: Integer read GetMaxlimitCount write SetMaxlimitCount; // 队列阀值
    property TaskQueue: TObjectQueue read GetTaskQueue; // 呼叫任务队列
    property TaskCount: Integer read GetTaskCount; // 队列中的任务数
    property OnTimer: TNotifyEvent read FOnTimer write FOnTimer; // 队列填充调度事件
  end;

  // 系统资源临界区对象，通过临界区来解决资源争用的问题
  TCriticalSection = class
  private
    FName: string; // 临界区名称
    FCS: TRTLCriticalSection;
  public
    constructor Create(const Aname: string);
    destructor Destroy; override;

    procedure EnterCS;
    procedure LeaveCS;
    
    property Name: string read FName;
  end;

  // 通道初始化事件
  TChannelInitializedEvent = procedure (Sender: TObject; var Channel: TAbstractChannel) of object;
  
  // 通道管理器
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
    function  GetCS(const Aname: string): TCriticalSection; // 获取一个临界区对象
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

    procedure EnterCS(const ACSName: string); // 进入临界区
    procedure LeaveCS(const ACSName: string); // 离开临界区

    procedure Initialize; // 初始化LightweightCTI

    property truncChannels: TList read FtruncChannels; // 所有加载的中继通道
    property userChannels: TList read FuserChannels; // 所有加载的用户通道
    property ProjectDir: TFileName read FProjectDir write SetProjectDir; // 项目文件夹
    property ResourceDir: TFileName read FResourceDir write SetResourceDir; // 资源文件夹
    property TimeOut: Integer read FTimeOut write SetTimeOut default 5; // 超时设置,默认值为5秒
    property TaskManager: TAbstractTaskManager read FTaskManater; // 任务管理器
    property TTSEngine: ITTSEngine read FTTSEngine; // TTS引擎
    property Loger: ILoger read FLoger; // 日志记录组件
  published
    property ConfigFile: TFileName read FconfigFile write FconfigFile; // 系统配置文件
    property Configuration: TSampleXML read FConfiguration; // 系统配置信息读取类
    property RootPath: string read FRootPath write FRootPath; // 系统配置根节点
    property OnChannelInitialized: TChannelInitializedEvent read FOnChannelInitialized write FOnChannelInitialized; // 通道初始化事件
    property OnAfterEnter: TNotifyEvent read FOnAfterEnter write FOnAfterEnter; // 进入临界区后执行事件
  end;

  // 板卡驱动程序适配器导出函数，如果板卡适配器采用动态链接库的形式进行发布与配置
  // 则必须采用此签名进行导出。
  TLoadCTIDriver = function(Amanager: TChannelManager; HostApplication: TApplication): TAbstractCTICardDriver;
  // 日志组件导出函数
  TLoadLoger = function(HostApplication: TApplication): ILoger;
  // 任务管理器导出函数
  TLoadTaskManager = function(Amanager: TChannelManager; HostApplication: TApplication): TAbstractTaskManager;
  // TTS引擎导出函数
  TLoadTTSEngine = function(Amanager: TChannelManager; ALoger: ILoger; HostApplication: TApplication): ITTSEngine;
  
  function ExePath: TFileName;
  function iif(const Test: Boolean; const ATrue, AFalse: Variant): Variant;
  function LoadTextFile(const FileName: TFileName): string;
  function PathAddSeparator(const Path: string): string;
  function StrToStringList(const Astr: string; const Asplit: Char = ';'): TStringList;
  function ChannelManager: TChannelManager;
  
const
  // 预定义的通道状态
  GAChannelStatusLabels: array [TChannelStatus] of string = (RsRing,RsFree, RsOffHook,
    RsDial, RsWaitingSignal, RsTimeOut, RsConnect, RsPlaying, RsRecording,
    RsGetDtmf, RsHangUp, RsLink, RsDisable,RsWait);
  // 语音资源文件位置
  GAVoiceResource: array [TVoiceResourceType] of string = (RsUnKonwn,
    RsStandard, RsLocalism, RsEnglish);
  // 通道类型说明
  GAChannelTypeLabels: array[TChannelType] of string = (RsUser, RsTrunk, RsEmpty,
    RsRecord, RsMessage, RsFax, RsVirtual);
  // 信息号类型
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
// 通道管理器全局入口程序
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
// 获取当前执行文件的根路径
//------------------------------------------------------------------------------
function ExePath: TFileName;
begin
  Result := ExtractFilePath(ParamStr(0));
end;

//------------------------------------------------------------------------------
// 三目判断操作函数
//------------------------------------------------------------------------------
function iif(const Test: Boolean; const ATrue, AFalse: Variant): Variant;
begin
  if Test then
    Result := ATrue
  else
    Result := AFalse;
end;

//------------------------------------------------------------------------------
// 加载文件并返回文件内容
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
// 为文件夹路径添加分隔符
//------------------------------------------------------------------------------
function PathAddSeparator(const Path: string): string;
begin
  Result := Path;
  if (Path = '') or (AnsiLastChar(Path) <> PathSeparator) then
    Result := Path + PathSeparator;
end;

//------------------------------------------------------------------------------
// 使用指定的分隔符将（默认为“;”）将字符串转换为列表
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
  // ... 具体实现类的代码
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
  Popose:    检查文件是否存在，系统首先会在全局资源文件夹中查找该文件，如果不存在
         就查询项目文件夹或系统安装目录。当前版本只在资源文件夹中进行搜索，没有
         采用智能搜索的功能。
-------------------------------------------------------------------------------}
function TAbstractChannel.CheckFile(const Afilename: string): string;
  // 在指定文件夹中查询相应的文件
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

  // 查找指定文件
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
      vrtUnkonwn : // 未指定资源类型
        begin
          Adir := iif(FChannelManager.ResourceDir = '', ExePath,
            PathAddSeparator(FChannelManager.ResourceDir));
          if FileExists(Adir + Afile) then
            Result := Adir + Afile;
        end;

      vrtEnglish, vrtStandard, vrtLocalism : // 指定了具体的类型
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
  // 通道创建事件
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
  Purpose:   通道业务逻辑执行事件，也是线程调度事件，客户端应用通过调用Resume来
  　　触发此事件，系统将首先检查有没有为系统指定外部脚本，如果指定了脚本则直接
      处理脚本文件，而不再处理Run事件，如果不存在脚本则执行Run事件。
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
        // 脚本加载工作由具体的实现类负责
        ScriptEngine.RunScripts(AOutPutlst);
      except on E: Exception do
        Loger.Error('执行脚本程序失败', E);
      end;
      // 脚本执行完成事件
      if Assigned(FOnScriptsCompleted) then
        FOnScriptsCompleted(Self, ScriptEngine, AOutPutlst);
    end else
      if Assigned(FOnRun) then
        FOnRun(Self)
      else // 如果执行时既没有提供脚本程序也没有提供事件处理程序则抛出异常
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
  FScriptEngine   := nil; // 动态脚本引擎
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
  Popose:    设置当前通道的状态，在实现类中通过属性访问此成员
-------------------------------------------------------------------------------}
procedure TAbstractChannel.SetChannelStatus(const Value: TChannelStatus);
begin
  if FStatus <> Value then
  begin
    FLastStatus := FStatus;
    FStatus := Value;
    // 触发通道状态改变事件
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
  Purpose:   设置超时时间
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
  Purpose:   清除呼叫队列中的所有任务，在清除任务时需要确保对没有进行处理的任务进
    行保存，以便在下次加载时可以重新进行呼叫处理。
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
  Purpose:   创建任务管理器
-------------------------------------------------------------------------------}
constructor TAbstractTaskManager.Create(AOwner: TComponent);
begin
  inherited Create(AOwner);
  FTaskQueue := TObjectQueue.Create;
  FActive := False;
  FLocked := False;
  FInterval := GCTaskManagerInterval; // 默认的任务队列填充时间为30秒
  FTimer := TTimer.Create(AOwner);
  FTimer.Interval := FInterval * 1000;
end;

{-------------------------------------------------------------------------------
  Procedure: TAbstractTaskManager.Create
  Author:    Xsp
  DateTime:  2006-08-09
  Arguments: AOwner: TComponent; AInterval: Integer
  Result:    None
  Purpose:   创建任务管理，并设置任务队列填充的时间间隔
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
  Purpose:   释放任务管理器
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
  Purpose:   从呼叫队列中取出一个待处理的任务，业务开发人员需要对返回的对象进行
  　　类型检查。
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
  Purpose:   设置任务管理器当前的状态，如果状态为真则需要激活内部的Timer控件，让
    其可以根据预设的时间间隔来填充任务队列。
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
  Purpose:   设置任务队列填充的时间间隔，单位为秒
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
  Purpose:   设置任务队列阀值，以保证呼叫虚拟机能够均衡任务而不出现饥饿的现象
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
  Purpose:   创建任务
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
  Purpose:   创建任务并指定任务标识及会话
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
    FHandleArray[I] := 0; // 将其初始化为0
end;

{-------------------------------------------------------------------------------
  Procedure: TChannelManager.AddChannel
  Author:    Xsp
  DateTime:  2006-08-23
  Arguments: AChannel: TAbstractChannel
  Result:    None
  Purpose:   向通道管理器注册新的通道，注册新通道时系统将检查用户程序是否指定的
  　　通道初始化事件，在此事件用户可以进一步对通道进行处理，如添加相应的事件程序
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
  Purpose:   释放系统所占用的资源，在释放资源时要注意顺序不然会造成系统相关资源
    处理无效。
-------------------------------------------------------------------------------}
destructor TChannelManager.Destroy;
var
  I: Integer;
  ActiDriver: TAbstractCTICardDriver;
  Acs: TCriticalSection;
  Achnl: TAbstractChannel;
  K: Cardinal;
begin
  // 停止所有通道的操作
  for I := FtruncChannels.Count - 1 downto 0 do
  begin
    Achnl := TAbstractChannel(FtruncChannels.Items[I]);
    if Assigned(Achnl) then
    begin
      FLoger.Info(Format('停止适配器 %s 下第 %d 条通道的脚本执行',
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
      FLoger.Info(Format('停止适配器 %s 下第 %d 条通道的脚本执行',
        [TAbstractCTICardDriver(Achnl.Owner).Name, Achnl.ChannelID]));
      Achnl.Terminate;
      Achnl.FScriptEngine := nil;
    end;
  end;
  K := GetTickCount;
  while GetTickCount - K < 5000 do ; // 延迟5秒钟以便所有通道都能正常结束

  FConfiguration.Free;
  if Assigned(FTaskManater) then
  begin
    FLoger.Info('清除任务管理器内所有未完成的任务……');
    FTaskManater.Free;
    FLoger.Info('卸载任务管理器');
  end;

  // 释放TTS引擎
  if Assigned(FTTSEngine) then
  begin
    FLoger.Info('卸载TTS语音引擎');
    FTTSEngine := nil;
  end;

  // 释放板卡适配器
  for I := FCTICardDrivers.Count - 1 downto 0 do
  begin
    ActiDriver := TAbstractCTICardDriver(FCTICardDrivers.Items[I]);
    FLoger.Info(Format('卸载适配器 %s ', [ActiDriver.Name]));
    ActiDriver.Free;
  end;
  FCTICardDrivers.Free;

  // 释放已经加载的程序集
  FLoger.Info('卸载系统加载的外部资源……');
  for I := Low(FHandleArray) to High(FHandleArray) do
    if FHandleArray[I] <> 0 then
      FreeLibrary(FHandleArray[I]);

  // 释放临界区列表
  FLoger.Info('释放系统申请的临界区……');
  for I := FCSList.Count - 1 downto 0 do
  begin
    Acs := TCriticalSection(FCSList.Items[I]);
    Acs.Free;
  end;
  FCSList.Free;

  // 释放日志组件
  FLoger.Info('卸载日志服务');
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
  Purpose:   进入临界区，如果临界区不存在则首先创建一个再进入
-------------------------------------------------------------------------------}
procedure TChannelManager.EnterCS(const ACSName: string);
var
  Acs: TCriticalSection;
begin
  Acs := GetCS(ACSName);
  if not Assigned(Acs) then
  begin
    Acs := TCriticalSection.Create(ACSName);
    FCSList.Add(Pointer(Acs)); // 将临界区对象加入列表
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
  Purpose:   从临界区列表中获取一个指定名称的临界区对象
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
  // 如果没有设置日志组件则使用通道管理器的日志组件
  if Trim(Atype) = '' then
  begin
    Result := FLoger;
    Exit;
  end;

  // 初始化日志组件
  J := Pos(',', Atype);
  if J <> 0 then // 从DLL加载
  begin
    AdllFile := Copy(Atype, J, Length(Atype) - J);
    Atype := Copy(Atype, 1, J - 1);
    if FileExists(AdllFile) then
      raise Exception.Create(Format('日志记录组件 %s 所对应的程序集 "%s" 不存在',
        [Atype, AdllFile]));
    AHandle := LoadLibrary(PChar(AdllFile));
    if AHandle <> 0 then
    begin
      FHandleArray[FHandleIndex] := AHandle; // 保存句柄
      Inc(FHandleIndex);
      LoadLoger := GetProcAddress(AHandle, PChar('LoadLoger'));
      if Assigned(LoadLoger) then
        ALoger := LoadLoger(Application);
    end;
  end else
    ALoger := TComponentClass(GetClass(Atype)).Create(Self) as ILoger; // 直接创建日志组件

  Result := ALoger;
end;

{-------------------------------------------------------------------------------
  Procedure: TChannelManager.Initialize
  Author:    Xsp
  DateTime:  2006-08-15
  Arguments: None
  Result:    None
  Purpose:   初始化LightweightCTI，使其处于准备就绪状态。在初始化时将首先检查有
    没有为通道管理器指定配置文件，如果没有则抛出异常。
-------------------------------------------------------------------------------}
procedure TChannelManager.Initialize;

  //初始化日志记录组件，系统必须提供一个日志组件否则将抛出一个异常
  procedure InitLoger(Atype: string);
  var
    AHandle: Cardinal;
    AlogerType, AdllFile: string;
    J: Integer;
    LoadLoger: TLoadLoger;
  begin
    J := Pos(',', Atype);
    if J <> 0 then // 从DLL加载
    begin
      AlogerType := Copy(Atype, 1, J - 1);
      AdllFile := Copy(Atype, J + 1, Length(Atype) - J);
      if FileExists(AdllFile) then
        raise Exception.Create(Format('日志记录组件 %s 所对应的程序集 "%s" 不存在',
          [AlogerType, AdllFile]));
      AHandle := LoadLibrary(PChar(AdllFile));
      if AHandle <> 0 then
      begin
        FHandleArray[FHandleIndex] := AHandle; // 保存句柄
        Inc(FHandleIndex);
        LoadLoger := GetProcAddress(AHandle, PChar('LoadLoger'));
        if Assigned(LoadLoger) then
          FLoger := LoadLoger(Application);
      end;
    end else
      FLoger := TComponentClass(GetClass(Atype)).Create(Self) as ILoger; // 直接创建日志组件

    // 成功创建日志组件
    if Assigned(FLoger) then
    begin
      FLoger.Info('-------------------!!!LIGHTWEIGHTCTI STARTED!!!--------------------');
      FLoger.Info('-------------------------------------------------------------------');
      FLoger.Info('初始化日志记录组件成功')
    end else
      raise Exception.Create(Format('初始化日志记录组件 %s 失败', [Atype]));
  end;

  // 初始化任务管理器
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
      FLoger.Info('准备初始化任务管理器……');
      J := Pos(',', Atype);
      if J <> 0 then
      begin
        ATaskManagerType := Copy(Atype, 1, J - 1);
        AdllFile := Copy(Atype, J + 1, Length(Atype) - J);
        if not FileExists(AdllFile) then
        begin
          FLoger.Error(Format('任务管理器 %s 所对应的程序集 "%s" 不存在',
            [ATaskManagerType, AdllFile]));
          Exit;
        end;
        AHandle := LoadLibrary(PChar(AdllFile));
        if AHandle <> 0 then
        begin
          FHandleArray[FHandleIndex] := AHandle; // 保存句柄
          Inc(FHandleIndex);
          LoadTaskManager := GetProcAddress(AHandle, PChar('LoadTaskManager'));
          if Assigned(LoadTaskManager) then
            FTaskManater := LoadTaskManager(Self, Application)
          else
            FLoger.Info(Format('初始化任务管理器 %s 失败', [Atype]));
        end;
      end else
      begin
        FTaskManater := TAbstractTaskManager(TComponentClass(GetClass(Atype)).NewInstance);
        FTaskManater.Create(Self, AInterval);
      end;

      // 设置任务管理器相关属性
      if Assigned(FTaskManater) then
      begin
        FTaskManater.Name := Aname;
        FTaskManater.Interval := AInterval;
        FTaskManater.MaxlimitCount := AMaxlimitCount;
        FTaskManater.Active := AActive;
        FLoger.Info('初始化任务管理器成功');
      end;
    except on E: Exception do
      FLoger.Error(Format('初始化任务管理器 %s 失败', [Atype]), E);
    end;
  end;

  // 初始化TTS引擎
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
          FLoger.Error(Format('TTS引擎程序集文件 %s 不存在，无法初始化TTS引擎', [AdllFile]));
          Exit;
        end;
        // 从外部程序集加载TTS引擎
        AHandle := LoadLibrary(PChar(AdllFile));
        if AHandle <> 0 then
        begin
          FHandleArray[FHandleIndex] := AHandle;
          FHandleIndex := FHandleIndex + 1;
          LoadTTSEngine := GetProcAddress(AHandle, 'LoadTTSEngine');
          if Assigned(LoadTTSEngine) then
          FTTSEngine := LoadTTSEngine(Self, FLoger, Application);
        end else
          FLoger.Error(Format('加载TTS引擎程序集文件 %s 失败', [AdllFile]));
      end else
      begin
        // 直接创建TTS引擎
        ATTS := TAbstractTTSEngine(GetClass(Atype).NewInstance);
        ATTS.Create(Self, FLoger);
        FTTSEngine := ATTS as ITTSEngine;
      end;
    except on E: Exception do
      FLoger.Error(Format('创建TTS引擎 %s 时出现错误', [Atype]), E);
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
    raise Exception.Create('没有为系统提供配置文件不能进行初始化。');

  // 初始化系统配置类
  if not Assigned(FConfiguration) then
  begin
    FConfiguration := TSampleXML.Create(FconfigFile);
  end;
  // 设置项目文件夹、资源文件夹及通话超时值
  FProjectDir := FConfiguration.GetNodeText(FRootPath + 'ProjectsDir');
  FResourceDir := FConfiguration.GetNodeText(FRootPath + 'ResourceDir');
  Astr := FConfiguration.GetNodeText(FRootPath + 'TimeOut');
  FTimeOut := StrToIntDef(Astr, GCDefaultTimeOut);
  InitLoger(FConfiguration.GetNodeAttribute(FRootPath + 'Loger', 'type')); // 初始化日志组件
  
  // 加载适配器，系统目前可以支持多个板卡多少类型的板卡
  N := FConfiguration.GetChildCount(FRootPath + 'Adpaters', 'Driver');
  for I := 0 to N - 1 do // 顺序读取每个适配器的配置文件，并初始化
  try
    Aname := FConfiguration.GetNodeAttribute(FRootPath + 'Adpaters\Driver', 'name', I);
    Atype := FConfiguration.GetNodeAttribute(FRootPath + 'Adpaters\Driver', 'type', I);
    FLoger.Info('准备初始化板卡适配器' + Aname + ', 类型为 ' + Atype);
    Adriver := nil;
    J := Pos(',', Atype);
    if J <> 0 then // 分解适配器类型及DLL文件
    begin
      Adllfile := Copy(Atype, J + 1, Length(Atype) - J);
      Atype    := Copy(Atype, 1, J - 1);
      // 从外部加载适配器
      if not FileExists(Adllfile) then
      begin
        FLoger.Error(Format('板卡驱动程序适配器程序集文件 "%s" 不存在', [Adllfile]));
        Continue;
      end;
      AHandle := LoadLibrary(PChar(Adllfile)); // 加载程序集
      if AHandle <> 0 then
      begin
        FHandleArray[FHandleIndex] := AHandle;
        Inc(FHandleIndex);
        Loaddriver := GetProcAddress(AHandle, PChar('LoadCTIDriver'));
        if Assigned(Loaddriver) then
          Adriver := Loaddriver(Self, Application); // 创建板卡适配器
        if Adriver = nil then
          FLoger.Info('初始化板卡适配器' + Aname + ' 失败');
      end else
        FLoger.Error(Format('加载板卡驱动适配器程序集 "%s" 失败', [Adllfile]))
    end else
    begin // 直接加载
      AdriverClass := GetClass(Atype);
      Adriver := TAbstractCTICardDriver(AdriverClass.NewInstance);
      Adriver.Create(Self, FLoger);
    end;

    // 当前适配器加载成功
    if Assigned(Adriver) then
    begin
      FLoger.Info('加载适配器' + Aname + '成功');
      Adriver.Name := Aname;
      Adriver.ConfigIndex := I; // 通过配置索引方便板卡适配器加载自身的配置信息
      Adriver.FchannelIndex := FtruncChannels.Count+FuserChannels.Count;
      Adriver.Initialize;
      FCTICardDrivers.Add(Pointer(Adriver)); // 将适配器添加到列表中
    end;
  except on E: Exception do
    begin
      FLoger.Error('加载适配器' + Aname + '失败', E);
      Continue;
    end;
  end;

  { 初始化TTS引擎，由于有些板卡本身的TTS引擎依赖于板卡是否加载成功，所以在初始化
    　　TTS引擎时必须检查其依赖的板卡是否已经加载。}
  Atype := FConfiguration.GetNodeAttribute(FRootPath + 'TTSEngine', 'type');
  Astr := FConfiguration.GetNodeAttribute(FRootPath + 'TTSEngine', 'dependency'); // 依赖于Driver的name属性
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

  // 初始化任务管理器
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
  Purpose:   离开临界区，首先根据指定的临界区名称获取一个临界区对象，如果对象不
    存在则表示其从未创建过。
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
  Purpose:   更新系统全局超时设置
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
      if Assigned(Achnl) then // 对通道有效性进行检查
        Achnl.TimeOut := Value;
    end;
    for I := 0 to FuserChannels.Count - 1 do
    begin
      Achnl := TAbstractChannel(FuserChannels.Items[I]);
      if Assigned(Achnl) then // 对通道有效性进行检查
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
