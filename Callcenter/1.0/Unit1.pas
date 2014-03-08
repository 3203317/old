unit Unit1;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, ExtCtrls, ImgList, OleCtrls, MSWinsockLib_TLB, StdCtrls, Grids,
  RzButton, RzPanel, RzStatus, IniFiles, DB, ADODB;

const IniFile = 'sys.ini';//系统配置文件
const Max = 240;

const VOCBUSY = 0;//电话忙音
const RINGBACK = 1;//电话打通以后的音
const VOCTONE = 2;//摘机后长音
const SIG = 3;//重播电话的音


type Sys_Info = record
  Pcm: integer;
  FreeDtmf: integer;
  FreeVoice: integer;
  TrunkNum: integer;
  UserNum: integer;
  FaxNum: integer;
  WaitTelCount: Integer;//当前的等待电话数量
  PhoneNumber: string; 
  TelRecord: integer;//0为不能电话录音 反之为1
  RecordSavePath: string;//语音文件存放路径
  SaveLogPath: string;//系统日志存放路径
  SaveHRPath: string;//呼入日志保存
  SaveHCPath: string;//呼出日志保存
  LocalhostIP: string;//本机ip
  SqlSa: string;//数据库用户名
  SqlPass: string;//数据库密码
  TalkingCount: Integer;//当前的通话数量
  NoConnCount: Integer;//当前的未连接数量
  UserBasyCount: Integer;//当前坐席正忙的数量
  UserFreeCount: Integer;//坐席空闲的数量
  FreeCount: Integer;//中继卡的数量
end;

type Trunk_Info = record
  Working: Boolean;//此通道是否在工作
  Status: Integer;//通道状态
  FlowStatus: Integer;//流程状态
  Content: string;//信息内容
  DTMF: string;//dtmf
  Service: Integer;//服务类型
  LinkUser: Integer;//通道与坐席连接的连通号
  CalleeSubAddr: string;//被叫子地址号码
  CalleeNumber: string;//被叫号码
  CallerSubAddr: string;//主叫子地址号码
  CallerNumber: string;//主叫号码   
  HRTime: TDateTime;//呼入时间
  THTime: TDateTime;//通话时间
  JSTime: TDateTime;//结束时间
  GJTime: TDateTime;//挂机时间
  PlayZXCode: boolean;//播放座西号
  Delay: integer;//
  ManYiDu: string;//满意度
end;   

type User_Info = record
  Working: Boolean;
  TelCount: Integer;
  DTMF: STRING;
  IP: STRING;
  FlowStatus: Integer;
  CalleeSubAddr: string;//被叫子地址号码
  CalleeNumber: string;//被叫号码
  CallerSubAddr: string;//主叫子地址号码
  CallerNumber: string;//主叫号码   
  LinkTrunk: Integer;
  Delay: Integer;//wait time
  PlayZXCode: boolean;//
  DialNum: string;//呼出拨号  
  HCTime: TDateTime;//呼出时间
  THTime: TDateTime;//通话时间
  JSTime: TDateTime;//结束时间
  GJTime: TDateTime;//挂机时间
end;  

type
  Tfrm_main = class(TForm)
    RzStatusBar1: TRzStatusBar;
    RzStatusPane1: TRzStatusPane;
    RzStatusPane3: TRzStatusPane;
    RzClockStatus1: TRzClockStatus;
    RzStatusPane2: TRzStatusPane;
    RzToolbar1: TRzToolbar;
    BtnExit: TRzToolButton;
    BtnUtilities: TRzToolButton;
    BtnSecurity: TRzToolButton;
    GroupBox1: TGroupBox;
    StringGrid1: TStringGrid;
    GroupBox2: TGroupBox;
    StringGrid2: TStringGrid;
    GroupBox3: TGroupBox;
    CheckBox1: TCheckBox;
    CheckBox2: TCheckBox;
    CheckBox3: TCheckBox;
    CheckBox4: TCheckBox;
    CheckBox5: TCheckBox;
    CheckBox6: TCheckBox;
    CheckBox7: TCheckBox;
    CheckBox8: TCheckBox;
    CheckBox9: TCheckBox;
    CheckBox10: TCheckBox;
    CheckBox11: TCheckBox;
    CheckBox12: TCheckBox;
    CheckBox13: TCheckBox;
    CheckBox14: TCheckBox;
    CheckBox15: TCheckBox;
    CheckBox16: TCheckBox;
    CheckBox17: TCheckBox;
    CheckBox18: TCheckBox;
    CheckBox19: TCheckBox;
    CheckBox20: TCheckBox;
    CheckBox21: TCheckBox;
    CheckBox22: TCheckBox;
    CheckBox23: TCheckBox;
    CheckBox24: TCheckBox;
    CheckBox25: TCheckBox;
    CheckBox26: TCheckBox;
    CheckBox27: TCheckBox;
    CheckBox28: TCheckBox;
    CheckBox29: TCheckBox;
    CheckBox30: TCheckBox;
    CheckBox31: TCheckBox;
    CheckBox32: TCheckBox;
    Winsock1: TWinsock;
    Winsock2: TWinsock;
    Winsock3: TWinsock;
    Winsock4: TWinsock;
    Winsock5: TWinsock;
    Winsock6: TWinsock;
    Winsock7: TWinsock;
    Winsock8: TWinsock;
    Winsock9: TWinsock;
    Winsock10: TWinsock;
    Winsock11: TWinsock;
    Winsock12: TWinsock;
    Winsock13: TWinsock;
    Winsock14: TWinsock;
    Winsock15: TWinsock;
    Winsock16: TWinsock;
    Winsock17: TWinsock;
    Winsock18: TWinsock;
    Winsock19: TWinsock;
    Winsock20: TWinsock;
    Winsock21: TWinsock;
    Winsock22: TWinsock;
    Winsock23: TWinsock;
    Winsock24: TWinsock;
    Winsock25: TWinsock;
    Winsock26: TWinsock;
    Winsock27: TWinsock;
    Winsock28: TWinsock;
    Winsock29: TWinsock;
    Winsock30: TWinsock;
    WinsockWait: TWinsock;
    WinsockControl: TWinsock;
    Winsock31: TWinsock;
    Winsock32: TWinsock;
    ImageList1: TImageList;
    Timer1: TTimer;
    BtnFinish: TRzToolButton;
    ADOQuery1: TADOQuery;
    BtnView: TRzToolButton;
    Timer2: TTimer;
    Winsock33: TWinsock;
    Winsock34: TWinsock;
    Winsock35: TWinsock;
    Winsock36: TWinsock;
    Winsock37: TWinsock;
    Winsock38: TWinsock;
    Winsock39: TWinsock;
    Winsock40: TWinsock;
    Winsock41: TWinsock;
    Winsock42: TWinsock;
    Winsock43: TWinsock;
    Winsock44: TWinsock;
    Winsock45: TWinsock;
    Winsock46: TWinsock;
    Winsock47: TWinsock;
    Winsock48: TWinsock;
    Winsock49: TWinsock;
    Winsock50: TWinsock;
    Winsock51: TWinsock;
    Winsock52: TWinsock;
    Winsock53: TWinsock;
    Winsock54: TWinsock;
    Winsock55: TWinsock;
    Winsock56: TWinsock;
    Winsock57: TWinsock;
    Winsock58: TWinsock;
    Winsock59: TWinsock;
    Winsock60: TWinsock;
    Winsock61: TWinsock;
    Winsock62: TWinsock;
    Winsock63: TWinsock;
    Winsock64: TWinsock;
    CheckBox33: TCheckBox;
    CheckBox34: TCheckBox;
    CheckBox35: TCheckBox;
    CheckBox36: TCheckBox;
    CheckBox37: TCheckBox;
    CheckBox38: TCheckBox;
    CheckBox39: TCheckBox;
    CheckBox40: TCheckBox;
    CheckBox41: TCheckBox;
    CheckBox42: TCheckBox;
    CheckBox43: TCheckBox;
    CheckBox44: TCheckBox;
    CheckBox45: TCheckBox;
    CheckBox46: TCheckBox;
    CheckBox47: TCheckBox;
    CheckBox48: TCheckBox;
    CheckBox49: TCheckBox;
    CheckBox50: TCheckBox;
    CheckBox51: TCheckBox;
    CheckBox52: TCheckBox;
    CheckBox53: TCheckBox;
    CheckBox54: TCheckBox;
    CheckBox55: TCheckBox;
    CheckBox56: TCheckBox;
    CheckBox57: TCheckBox;
    CheckBox58: TCheckBox;
    CheckBox59: TCheckBox;
    CheckBox60: TCheckBox;
    CheckBox61: TCheckBox;
    CheckBox62: TCheckBox;
    CheckBox63: TCheckBox;
    CheckBox64: TCheckBox;
    procedure Winsock1Close(Sender: TObject);
    procedure Winsock2Close(Sender: TObject);
    procedure Winsock3Close(Sender: TObject);
    procedure Winsock4Close(Sender: TObject);
    procedure Winsock5Close(Sender: TObject);
    procedure Winsock6Close(Sender: TObject);
    procedure Winsock7Close(Sender: TObject);
    procedure Winsock8Close(Sender: TObject);
    procedure Winsock9Close(Sender: TObject);
    procedure Winsock10Close(Sender: TObject);
    procedure Winsock11Close(Sender: TObject);
    procedure Winsock12Close(Sender: TObject);
    procedure Winsock13Close(Sender: TObject);
    procedure Winsock14Close(Sender: TObject);
    procedure Winsock15Close(Sender: TObject);
    procedure Winsock16Close(Sender: TObject);
    procedure Winsock17Close(Sender: TObject);
    procedure Winsock18Close(Sender: TObject);
    procedure Winsock19Close(Sender: TObject);
    procedure Winsock20Close(Sender: TObject);
    procedure Winsock21Close(Sender: TObject);
    procedure Winsock22Close(Sender: TObject);
    procedure Winsock23Close(Sender: TObject);
    procedure Winsock24Close(Sender: TObject);
    procedure Winsock25Close(Sender: TObject);
    procedure Winsock26Close(Sender: TObject);
    procedure Winsock27Close(Sender: TObject);
    procedure Winsock28Close(Sender: TObject);
    procedure Winsock29Close(Sender: TObject);
    procedure Winsock30Close(Sender: TObject);
    procedure Winsock31Close(Sender: TObject);
    procedure Winsock32Close(Sender: TObject);
    procedure WinSockWork(WsId: Integer);
    procedure Winsock1ConnectionRequest(ASender: TObject;
      requestID: Integer);
    procedure Winsock2ConnectionRequest(ASender: TObject;
      requestID: Integer);
    procedure Winsock3ConnectionRequest(ASender: TObject;
      requestID: Integer);
    procedure Winsock4ConnectionRequest(ASender: TObject;
      requestID: Integer);
    procedure Winsock5ConnectionRequest(ASender: TObject;
      requestID: Integer);
    procedure Winsock6ConnectionRequest(ASender: TObject;
      requestID: Integer);
    procedure Winsock7ConnectionRequest(ASender: TObject;
      requestID: Integer);
    procedure Winsock8ConnectionRequest(ASender: TObject;
      requestID: Integer);
    procedure Winsock9ConnectionRequest(ASender: TObject;
      requestID: Integer);
    procedure Winsock10ConnectionRequest(ASender: TObject;
      requestID: Integer);
    procedure Winsock11ConnectionRequest(ASender: TObject;
      requestID: Integer);
    procedure Winsock12ConnectionRequest(ASender: TObject;
      requestID: Integer);
    procedure Winsock13ConnectionRequest(ASender: TObject;
      requestID: Integer);
    procedure Winsock14ConnectionRequest(ASender: TObject;
      requestID: Integer);
    procedure Winsock15ConnectionRequest(ASender: TObject;
      requestID: Integer);
    procedure Winsock16ConnectionRequest(ASender: TObject;
      requestID: Integer);
    procedure Winsock17ConnectionRequest(ASender: TObject;
      requestID: Integer);
    procedure Winsock18ConnectionRequest(ASender: TObject;
      requestID: Integer);
    procedure Winsock19ConnectionRequest(ASender: TObject;
      requestID: Integer);
    procedure Winsock20ConnectionRequest(ASender: TObject;
      requestID: Integer);
    procedure Winsock21ConnectionRequest(ASender: TObject;
      requestID: Integer);
    procedure Winsock22ConnectionRequest(ASender: TObject;
      requestID: Integer);
    procedure Winsock23ConnectionRequest(ASender: TObject;
      requestID: Integer);
    procedure Winsock24ConnectionRequest(ASender: TObject;
      requestID: Integer);
    procedure Winsock25ConnectionRequest(ASender: TObject;
      requestID: Integer);
    procedure Winsock26ConnectionRequest(ASender: TObject;
      requestID: Integer);
    procedure Winsock27ConnectionRequest(ASender: TObject;
      requestID: Integer);
    procedure Winsock28ConnectionRequest(ASender: TObject;
      requestID: Integer);
    procedure Winsock29ConnectionRequest(ASender: TObject;
      requestID: Integer);
    procedure Winsock30ConnectionRequest(ASender: TObject;
      requestID: Integer);
    procedure Winsock31ConnectionRequest(ASender: TObject;
      requestID: Integer);
    procedure Winsock32ConnectionRequest(ASender: TObject;
      requestID: Integer);
    procedure WinSockCR(WsId: Integer;RequestId: Integer);
    procedure CheckBox1Click(Sender: TObject);
    procedure CheckBox2Click(Sender: TObject);
    procedure CheckBox3Click(Sender: TObject);
    procedure CheckBox4Click(Sender: TObject);
    procedure CheckBox5Click(Sender: TObject);
    procedure CheckBox6Click(Sender: TObject);
    procedure CheckBox7Click(Sender: TObject);
    procedure CheckBox8Click(Sender: TObject);
    procedure CheckBox9Click(Sender: TObject);
    procedure CheckBox10Click(Sender: TObject);
    procedure CheckBox11Click(Sender: TObject);
    procedure CheckBox12Click(Sender: TObject);
    procedure CheckBox13Click(Sender: TObject);
    procedure CheckBox14Click(Sender: TObject);
    procedure CheckBox15Click(Sender: TObject);
    procedure CheckBox16Click(Sender: TObject);
    procedure CheckBox17Click(Sender: TObject);
    procedure CheckBox18Click(Sender: TObject);
    procedure CheckBox19Click(Sender: TObject);
    procedure CheckBox20Click(Sender: TObject);
    procedure CheckBox21Click(Sender: TObject);
    procedure CheckBox22Click(Sender: TObject);
    procedure CheckBox23Click(Sender: TObject);
    procedure CheckBox24Click(Sender: TObject);
    procedure CheckBox25Click(Sender: TObject);
    procedure CheckBox26Click(Sender: TObject);
    procedure CheckBox27Click(Sender: TObject);
    procedure CheckBox28Click(Sender: TObject);
    procedure CheckBox29Click(Sender: TObject);
    procedure CheckBox30Click(Sender: TObject);
    procedure CheckBox31Click(Sender: TObject);
    procedure CheckBox32Click(Sender: TObject);
    procedure CheckBoxWork(CHKId: integer);
    procedure DBConn;
    procedure FormCreate(Sender: TObject);
    procedure ZxStatusClose;
    function Initini: Boolean;
    procedure Timer1Timer(Sender: TObject);
    procedure TrunkWork(TrunkId: Integer);
    procedure TrunkShow(TrunkId: Integer);
    procedure UserWork(UserId: Integer);
    procedure UserShow(UserId: Integer);
    procedure TrunkReset(TrunkId: Integer);
    function GetFreeTrunk: Integer;//
    function GetFreeUser(Service: Integer): Integer;//获取空闲的坐席id
    function GetWinSock(TrunkId: Integer): TWinsock;
    procedure UserReset(UserId: integer);
    procedure ZxStatusOpen;
    procedure BtnUtilitiesClick(Sender: TObject);
    procedure BtnSecurityClick(Sender: TObject);
    procedure BtnExitClick(Sender: TObject);
    function GetZXCodeVoc(UserId: Integer): String;
    procedure WinsockWaitClose(Sender: TObject);
    procedure WinsockWaitConnectionRequest(ASender: TObject;
      requestID: Integer);
    procedure WinsockControlConnectionRequest(ASender: TObject;
      requestID: Integer);
    procedure FormCloseQuery(Sender: TObject; var CanClose: Boolean);
    procedure BtnFinishClick(Sender: TObject);//获取用户呼出时拨号的字符串
    procedure StartRecord(TrunkId: integer;UserId: integer);//开始录音
    procedure EndRecord(UserId: integer);//结束录音
    procedure SaveSysLog(Content: string);//写系统日志
    procedure SaveHRLog(TrunkId: Integer);//保存呼入日志
    procedure SaveHCLog(UserId: Integer);
    procedure SaveTelStat();//保存坐席每天的电话记录
    procedure Winsock11DataArrival(ASender: TObject; bytesTotal: Integer);//保存呼入日志
    procedure WinsockDA(index: integer);
    procedure Winsock1DataArrival(ASender: TObject; bytesTotal: Integer);
    procedure Winsock2DataArrival(ASender: TObject; bytesTotal: Integer);
    procedure Winsock3DataArrival(ASender: TObject; bytesTotal: Integer);
    procedure Winsock4DataArrival(ASender: TObject; bytesTotal: Integer);
    procedure Winsock5DataArrival(ASender: TObject; bytesTotal: Integer);
    procedure Winsock6DataArrival(ASender: TObject; bytesTotal: Integer);
    procedure Winsock7DataArrival(ASender: TObject; bytesTotal: Integer);
    procedure Winsock8DataArrival(ASender: TObject; bytesTotal: Integer);
    procedure Winsock9DataArrival(ASender: TObject; bytesTotal: Integer);
    procedure Winsock10DataArrival(ASender: TObject; bytesTotal: Integer);
    procedure Winsock12DataArrival(ASender: TObject; bytesTotal: Integer);
    procedure Winsock13DataArrival(ASender: TObject; bytesTotal: Integer);
    procedure Winsock14DataArrival(ASender: TObject; bytesTotal: Integer);
    procedure Winsock15DataArrival(ASender: TObject; bytesTotal: Integer);
    procedure Winsock16DataArrival(ASender: TObject; bytesTotal: Integer);
    procedure Winsock17DataArrival(ASender: TObject; bytesTotal: Integer);
    procedure Winsock18DataArrival(ASender: TObject; bytesTotal: Integer);
    procedure Winsock19DataArrival(ASender: TObject; bytesTotal: Integer);
    procedure Winsock20DataArrival(ASender: TObject; bytesTotal: Integer);
    procedure Winsock21DataArrival(ASender: TObject; bytesTotal: Integer);
    procedure Winsock22DataArrival(ASender: TObject; bytesTotal: Integer);
    procedure Winsock23DataArrival(ASender: TObject; bytesTotal: Integer);
    procedure Winsock24DataArrival(ASender: TObject; bytesTotal: Integer);
    procedure Winsock25DataArrival(ASender: TObject; bytesTotal: Integer);
    procedure Winsock26DataArrival(ASender: TObject; bytesTotal: Integer);
    procedure Winsock27DataArrival(ASender: TObject; bytesTotal: Integer);
    procedure Winsock28DataArrival(ASender: TObject; bytesTotal: Integer);
    procedure Winsock29DataArrival(ASender: TObject; bytesTotal: Integer);
    procedure Winsock30DataArrival(ASender: TObject; bytesTotal: Integer);
    procedure Winsock31DataArrival(ASender: TObject; bytesTotal: Integer);
    procedure Winsock32DataArrival(ASender: TObject; bytesTotal: Integer);
    procedure BtnViewClick(Sender: TObject);
    procedure Timer2Timer(Sender: TObject);
    procedure Winsock33Close(Sender: TObject);
    procedure Winsock34Close(Sender: TObject);
    procedure Winsock35Close(Sender: TObject);
    procedure Winsock36Close(Sender: TObject);
    procedure Winsock37Close(Sender: TObject);
    procedure Winsock38Close(Sender: TObject);
    procedure Winsock39Close(Sender: TObject);
    procedure Winsock40Close(Sender: TObject);
    procedure Winsock41Close(Sender: TObject);
    procedure Winsock42Close(Sender: TObject);
    procedure Winsock43Close(Sender: TObject);
    procedure Winsock44Close(Sender: TObject);
    procedure Winsock45Close(Sender: TObject);
    procedure Winsock46Close(Sender: TObject);
    procedure Winsock47Close(Sender: TObject);
    procedure Winsock48Close(Sender: TObject);
    procedure Winsock49Close(Sender: TObject);
    procedure Winsock50Close(Sender: TObject);
    procedure Winsock51Close(Sender: TObject);
    procedure Winsock52Close(Sender: TObject);
    procedure Winsock53Close(Sender: TObject);
    procedure Winsock54Close(Sender: TObject);
    procedure Winsock55Close(Sender: TObject);
    procedure Winsock56Close(Sender: TObject);
    procedure Winsock57Close(Sender: TObject);
    procedure Winsock58Close(Sender: TObject);
    procedure Winsock59Close(Sender: TObject);
    procedure Winsock60Close(Sender: TObject);
    procedure Winsock61Close(Sender: TObject);
    procedure Winsock62Close(Sender: TObject);
    procedure Winsock63Close(Sender: TObject);
    procedure Winsock64Close(Sender: TObject);
    procedure Winsock33ConnectionRequest(ASender: TObject;
      requestID: Integer);
    procedure Winsock34ConnectionRequest(ASender: TObject;
      requestID: Integer);
    procedure Winsock35ConnectionRequest(ASender: TObject;
      requestID: Integer);
    procedure Winsock36ConnectionRequest(ASender: TObject;
      requestID: Integer);
    procedure Winsock37ConnectionRequest(ASender: TObject;
      requestID: Integer);
    procedure Winsock38ConnectionRequest(ASender: TObject;
      requestID: Integer);
    procedure Winsock39ConnectionRequest(ASender: TObject;
      requestID: Integer);
    procedure Winsock40ConnectionRequest(ASender: TObject;
      requestID: Integer);
    procedure Winsock41ConnectionRequest(ASender: TObject;
      requestID: Integer);
    procedure Winsock42ConnectionRequest(ASender: TObject;
      requestID: Integer);
    procedure Winsock43ConnectionRequest(ASender: TObject;
      requestID: Integer);
    procedure Winsock44ConnectionRequest(ASender: TObject;
      requestID: Integer);
    procedure Winsock45ConnectionRequest(ASender: TObject;
      requestID: Integer);
    procedure Winsock46ConnectionRequest(ASender: TObject;
      requestID: Integer);
    procedure Winsock47ConnectionRequest(ASender: TObject;
      requestID: Integer);
    procedure Winsock48ConnectionRequest(ASender: TObject;
      requestID: Integer);
    procedure Winsock49ConnectionRequest(ASender: TObject;
      requestID: Integer);
    procedure Winsock50ConnectionRequest(ASender: TObject;
      requestID: Integer);
    procedure Winsock51ConnectionRequest(ASender: TObject;
      requestID: Integer);
    procedure Winsock52ConnectionRequest(ASender: TObject;
      requestID: Integer);
    procedure Winsock53ConnectionRequest(ASender: TObject;
      requestID: Integer);
    procedure Winsock54ConnectionRequest(ASender: TObject;
      requestID: Integer);
    procedure Winsock55ConnectionRequest(ASender: TObject;
      requestID: Integer);
    procedure Winsock56ConnectionRequest(ASender: TObject;
      requestID: Integer);
    procedure Winsock57ConnectionRequest(ASender: TObject;
      requestID: Integer);
    procedure Winsock58ConnectionRequest(ASender: TObject;
      requestID: Integer);
    procedure Winsock59ConnectionRequest(ASender: TObject;
      requestID: Integer);
    procedure Winsock60ConnectionRequest(ASender: TObject;
      requestID: Integer);
    procedure Winsock61ConnectionRequest(ASender: TObject;
      requestID: Integer);
    procedure Winsock62ConnectionRequest(ASender: TObject;
      requestID: Integer);
    procedure Winsock33DataArrival(ASender: TObject; bytesTotal: Integer);
    procedure Winsock34DataArrival(ASender: TObject; bytesTotal: Integer);
    procedure Winsock35DataArrival(ASender: TObject; bytesTotal: Integer);
    procedure Winsock36DataArrival(ASender: TObject; bytesTotal: Integer);
    procedure Winsock37DataArrival(ASender: TObject; bytesTotal: Integer);
    procedure Winsock38DataArrival(ASender: TObject; bytesTotal: Integer);
    procedure Winsock39DataArrival(ASender: TObject; bytesTotal: Integer);
    procedure Winsock40DataArrival(ASender: TObject; bytesTotal: Integer);
    procedure Winsock41DataArrival(ASender: TObject; bytesTotal: Integer);
    procedure Winsock42DataArrival(ASender: TObject; bytesTotal: Integer);
    procedure Winsock43DataArrival(ASender: TObject; bytesTotal: Integer);
    procedure Winsock44DataArrival(ASender: TObject; bytesTotal: Integer);
    procedure Winsock45DataArrival(ASender: TObject; bytesTotal: Integer);
    procedure Winsock46DataArrival(ASender: TObject; bytesTotal: Integer);
    procedure Winsock47DataArrival(ASender: TObject; bytesTotal: Integer);
    procedure Winsock48DataArrival(ASender: TObject; bytesTotal: Integer);
    procedure Winsock49DataArrival(ASender: TObject; bytesTotal: Integer);
    procedure Winsock50DataArrival(ASender: TObject; bytesTotal: Integer);
    procedure Winsock51DataArrival(ASender: TObject; bytesTotal: Integer);
    procedure Winsock52DataArrival(ASender: TObject; bytesTotal: Integer);
    procedure Winsock53DataArrival(ASender: TObject; bytesTotal: Integer);
    procedure Winsock54DataArrival(ASender: TObject; bytesTotal: Integer);
    procedure Winsock55DataArrival(ASender: TObject; bytesTotal: Integer);
    procedure Winsock56DataArrival(ASender: TObject; bytesTotal: Integer);
    procedure Winsock57DataArrival(ASender: TObject; bytesTotal: Integer);
    procedure Winsock58DataArrival(ASender: TObject; bytesTotal: Integer);
    procedure Winsock59DataArrival(ASender: TObject; bytesTotal: Integer);
    procedure Winsock60DataArrival(ASender: TObject; bytesTotal: Integer);
    procedure Winsock61DataArrival(ASender: TObject; bytesTotal: Integer);
    procedure Winsock62DataArrival(ASender: TObject; bytesTotal: Integer);
    procedure Winsock63ConnectionRequest(ASender: TObject;
      requestID: Integer);
    procedure Winsock64ConnectionRequest(ASender: TObject;
      requestID: Integer);
    procedure Winsock63DataArrival(ASender: TObject; bytesTotal: Integer);
    procedure Winsock64DataArrival(ASender: TObject; bytesTotal: Integer);
    procedure CheckBox33Click(Sender: TObject);
    procedure CheckBox34Click(Sender: TObject);
    procedure CheckBox35Click(Sender: TObject);
    procedure CheckBox36Click(Sender: TObject);
    procedure CheckBox37Click(Sender: TObject);
    procedure CheckBox38Click(Sender: TObject);
    procedure CheckBox39Click(Sender: TObject);
    procedure CheckBox40Click(Sender: TObject);
    procedure CheckBox41Click(Sender: TObject);
    procedure CheckBox42Click(Sender: TObject);
    procedure CheckBox43Click(Sender: TObject);
    procedure CheckBox44Click(Sender: TObject);
    procedure CheckBox45Click(Sender: TObject);
    procedure CheckBox46Click(Sender: TObject);
    procedure CheckBox47Click(Sender: TObject);
    procedure CheckBox48Click(Sender: TObject);
    procedure CheckBox49Click(Sender: TObject);
    procedure CheckBox50Click(Sender: TObject);
    procedure CheckBox51Click(Sender: TObject);
    procedure CheckBox52Click(Sender: TObject);
    procedure CheckBox53Click(Sender: TObject);
    procedure CheckBox54Click(Sender: TObject);
    procedure CheckBox55Click(Sender: TObject);
    procedure CheckBox56Click(Sender: TObject);
    procedure CheckBox57Click(Sender: TObject);
    procedure CheckBox58Click(Sender: TObject);
    procedure CheckBox59Click(Sender: TObject);
    procedure CheckBox60Click(Sender: TObject);
    procedure CheckBox61Click(Sender: TObject);
    procedure CheckBox62Click(Sender: TObject);
    procedure CheckBox63Click(Sender: TObject);
    procedure CheckBox64Click(Sender: TObject);
  private
    { Private declarations }
  public
    { Public declarations }
  end;

var
  frm_main: Tfrm_main;
  wswait: integer;
  wscontrol: integer;
  Sys: Sys_Info;
  VocChannel:array[0..10] of Integer;
  Trunk:array[0..Max-1] of Trunk_Info;
  User:array[0..Max-1] of User_Info;
  FWZXZX: string;
  YWSLZX: string;
  TSJYZX: string;
  GXZX: STRING;
  WSPort:array[0..max-1] of Integer;
  TelFWZXZJ: integer;
  TelFWZXZJNumber: string;
  TelYWSLZJ: integer;
  TelYWSLZJNumber: string;
  TelTSJYZJ: integer;
  TelTSJYZJNumber: string;
  tempchar: PChar;

implementation

uses dmdm, MyFunction, Isdndll, No7Dll, tce132, frm_login, frm_logout, frminfo;

{$R *.dfm}

procedure Tfrm_main.ZxStatusOpen;
var
  tmpint: integer;
begin
try
  for tmpint := 1 to Sys.UserNum do
    begin
      TCheckBox(FindComponent('CheckBox'+ IntToStr(tmpint))).Enabled := True;
    end;
except       
    on E:Exception do
      SaveSysLog('0 ZxStatusOpen '+ e.Message);
end;
end;

function Tfrm_main.Initini: Boolean;//初始化ini文件
var
  //tmpstr: String;
  tmpif: TIniFile;
  //tmpint: integer;
begin
  try
    //ShowMessage(GetDateDiff2(StrToDateTime('2006-11-11 11:11:30'),StrToDateTime('2006-11-11 11:12:20')));
    //showmessage('1-1-2006-11-11-10:10:10-63329098');

	  if FileExists(IniFile) = False then//判断系统配置文件是否存在
	    begin
	      Application.MessageBox('系统配置文件不存在！','提示',MB_ICONEXCLAMATION);
	      Initini := False;
	      Exit;
	    end;

	  tmpif := TIniFile.Create(ExtractFilePath(Application.ExeName) + IniFile);

	  WSPort[0] := tmpif.ReadInteger('winsock:port','Winsock1',0);
	  WSPort[1] := tmpif.ReadInteger('winsock:port','Winsock2',0);
	  WSPort[2] := tmpif.ReadInteger('winsock:port','Winsock3',0);
	  WSPort[3] := tmpif.ReadInteger('winsock:port','Winsock4',0);
	  WSPort[4] := tmpif.ReadInteger('winsock:port','Winsock5',0);
	  WSPort[5] := tmpif.ReadInteger('winsock:port','Winsock6',0);
	  WSPort[6] := tmpif.ReadInteger('winsock:port','Winsock7',0);
	  WSPort[7] := tmpif.ReadInteger('winsock:port','Winsock8',0);
	  WSPort[8] := tmpif.ReadInteger('winsock:port','Winsock9',0);
	  WSPort[9] := tmpif.ReadInteger('winsock:port','Winsock10',0);
	  WSPort[10] := tmpif.ReadInteger('winsock:port','Winsock11',0);
	  WSPort[11] := tmpif.ReadInteger('winsock:port','Winsock12',0);
	  WSPort[12] := tmpif.ReadInteger('winsock:port','Winsock13',0);
	  WSPort[13] := tmpif.ReadInteger('winsock:port','Winsock14',0);
	  WSPort[14] := tmpif.ReadInteger('winsock:port','Winsock15',0);
	  WSPort[15] := tmpif.ReadInteger('winsock:port','Winsock16',0);
	  WSPort[16] := tmpif.ReadInteger('winsock:port','Winsock17',0);
	  WSPort[17] := tmpif.ReadInteger('winsock:port','Winsock18',0);
	  WSPort[18] := tmpif.ReadInteger('winsock:port','Winsock19',0);
	  WSPort[19] := tmpif.ReadInteger('winsock:port','Winsock20',0);
	  WSPort[20] := tmpif.ReadInteger('winsock:port','Winsock21',0);
	  WSPort[21] := tmpif.ReadInteger('winsock:port','Winsock22',0);
	  WSPort[22] := tmpif.ReadInteger('winsock:port','Winsock23',0);
	  WSPort[23] := tmpif.ReadInteger('winsock:port','Winsock24',0);
	  WSPort[24] := tmpif.ReadInteger('winsock:port','Winsock25',0);
	  WSPort[25] := tmpif.ReadInteger('winsock:port','Winsock26',0);
	  WSPort[26] := tmpif.ReadInteger('winsock:port','Winsock27',0);
	  WSPort[27] := tmpif.ReadInteger('winsock:port','Winsock28',0);
	  WSPort[28] := tmpif.ReadInteger('winsock:port','Winsock29',0);
	  WSPort[29] := tmpif.ReadInteger('winsock:port','Winsock30',0);
	  WSPort[30] := tmpif.ReadInteger('winsock:port','Winsock31',0);
	  WSPort[31] := tmpif.ReadInteger('winsock:port','Winsock32',0);
	  WSPort[32] := tmpif.ReadInteger('winsock:port','Winsock33',0);
	  WSPort[33] := tmpif.ReadInteger('winsock:port','Winsock34',0);
	  WSPort[34] := tmpif.ReadInteger('winsock:port','Winsock35',0);
	  WSPort[35] := tmpif.ReadInteger('winsock:port','Winsock36',0);
	  WSPort[36] := tmpif.ReadInteger('winsock:port','Winsock37',0);
	  WSPort[37] := tmpif.ReadInteger('winsock:port','Winsock38',0);
	  WSPort[38] := tmpif.ReadInteger('winsock:port','Winsock39',0);
	  WSPort[39] := tmpif.ReadInteger('winsock:port','Winsock40',0);
	  WSPort[40] := tmpif.ReadInteger('winsock:port','Winsock41',0);
	  WSPort[41] := tmpif.ReadInteger('winsock:port','Winsock42',0);
	  WSPort[42] := tmpif.ReadInteger('winsock:port','Winsock43',0);
	  WSPort[43] := tmpif.ReadInteger('winsock:port','Winsock44',0);
	  WSPort[44] := tmpif.ReadInteger('winsock:port','Winsock45',0);
	  WSPort[45] := tmpif.ReadInteger('winsock:port','Winsock46',0);
	  WSPort[46] := tmpif.ReadInteger('winsock:port','Winsock47',0);
	  WSPort[47] := tmpif.ReadInteger('winsock:port','Winsock48',0);
	  WSPort[48] := tmpif.ReadInteger('winsock:port','Winsock49',0);
	  WSPort[49] := tmpif.ReadInteger('winsock:port','Winsock50',0);
	  WSPort[50] := tmpif.ReadInteger('winsock:port','Winsock51',0);
	  WSPort[51] := tmpif.ReadInteger('winsock:port','Winsock52',0);
	  WSPort[52] := tmpif.ReadInteger('winsock:port','Winsock53',0);
	  WSPort[53] := tmpif.ReadInteger('winsock:port','Winsock54',0);
	  WSPort[54] := tmpif.ReadInteger('winsock:port','Winsock55',0);
	  WSPort[55] := tmpif.ReadInteger('winsock:port','Winsock56',0);
	  WSPort[56] := tmpif.ReadInteger('winsock:port','Winsock57',0);
	  WSPort[57] := tmpif.ReadInteger('winsock:port','Winsock58',0);
	  WSPort[58] := tmpif.ReadInteger('winsock:port','Winsock59',0);
	  WSPort[59] := tmpif.ReadInteger('winsock:port','Winsock60',0);
	  WSPort[60] := tmpif.ReadInteger('winsock:port','Winsock61',0);
	  WSPort[61] := tmpif.ReadInteger('winsock:port','Winsock62',0);
	  WSPort[62] := tmpif.ReadInteger('winsock:port','Winsock63',0);
	  WSPort[63] := tmpif.ReadInteger('winsock:port','Winsock64',0);
	  wswait := tmpif.ReadInteger('winsock:port','WinsockWait',0);
	  wscontrol := tmpif.ReadInteger('winsock:port','WinsockControl',0);

    FWZXZX := Trim(tmpif.ReadString('set','FWZXZX',''));//服务咨询坐席
    YWSLZX := Trim(tmpif.ReadString('set','YWSLZX',''));//
    TSJYZX := Trim(tmpif.ReadString('set','TSJYZX',''));//
    GXZX := Trim(tmpif.ReadString('set','GXZX',''));//

    //电话转接
    TelFWZXZJ := tmpif.ReadInteger('set','TelFWZXZJ',0);
    TelFWZXZJNumber := Trim(tmpif.ReadString('set','TelFWZXZJNumber',''));
    TelYWSLZJ := tmpif.ReadInteger('set','TelYWSLZJ',0);
    TelYWSLZJNumber := Trim(tmpif.ReadString('set','TelYWSLZJNumber',''));
    TelTSJYZJ := tmpif.ReadInteger('set','TelTSJYZJ',0);
    TelTSJYZJNumber := Trim(tmpif.ReadString('set','TelTSJYZJNumber',''));

    //电话录音
    Sys.TelRecord := tmpif.ReadInteger('set','TelRecord',0);
    
    //录音文件存放路径
    Sys.RecordSavePath := Trim(tmpif.ReadString('set','RecordSavePath',''));
    
    //系统日志存放路径
    Sys.SaveLogPath := Trim(tmpif.ReadString('set','SaveLogPath',''));

    //呼出日志保存路径
    Sys.SaveHRPath := Trim(tmpif.ReadString('set','SaveHRPath',''));

    //呼出日志保存路径
    Sys.SaveHCPath := Trim(tmpif.ReadString('set','SaveHCPath',''));

    //SaveSysLog('1 msdmfamsdfajlsdfjlasdjf');
    
    Sys.PhoneNumber := Trim(tmpif.ReadString('set','PHONENUMBER','037163329079'));//本地的电话号码

    Sys.LocalhostIP := Trim(tmpif.ReadString('db','ds','')); 
    Sys.SqlSa := Trim(tmpif.ReadString('db','user',''));
    Sys.SqlPass := Trim(tmpif.ReadString('db','pass',''));

    SaveSysLog('1 ini初始化成功');
	  Initini := True;
  except
    SaveSysLog('0 ini初始化失败');
    Initini := False;
  end;

end;

procedure Tfrm_main.ZxStatusClose;
var
  tmpint: integer;
begin
try
  for tmpint := 1 to Sys.UserNum do
    begin
      TCheckBox(FindComponent('CheckBox'+ IntToStr(tmpint))).Enabled := False;
    end;
except   
    on E:Exception do
      SaveSysLog('0 ZxStatusClose '+ e.Message);
end;
end;

procedure Tfrm_main.DBConn;
begin
  try
    dm.ADOConnection1.Connected := false;
    //dm.ADOConnection2.ConnectionString := 'Provider=MSDAORA.1;Password='+Sys.SqlPass+';User ID='+Sys.SqlSa+';Data Source='+Sys.LocalhostIP+';Persist Security Info=True';
    dm.ADOConnection1.ConnectionString := 'Provider=SQLOLEDB.1;Password='+Sys.SqlPass+';Persist Security Info=True;User ID='+Sys.SqlSa+';Initial Catalog=callcenter;Data Source='+Sys.LocalhostIP;
    dm.ADOConnection1.LoginPrompt := false;
    dm.ADOConnection1.Connected := true;
    SaveSysLog('1 连接数据库成功');
  except
    SaveSysLog('0 连接数据库失败');
  end;
end;

procedure Tfrm_main.Winsock1Close(Sender: TObject);
begin
  WinSockWork(1);
end;

procedure Tfrm_main.Winsock2Close(Sender: TObject);
begin
  WinSockWork(2);
end;

procedure Tfrm_main.Winsock3Close(Sender: TObject);
begin
  WinSockWork(3);
end;

procedure Tfrm_main.Winsock4Close(Sender: TObject);
begin
  WinSockWork(4);
end;

procedure Tfrm_main.Winsock5Close(Sender: TObject);
begin
  WinSockWork(5);
end;

procedure Tfrm_main.Winsock6Close(Sender: TObject);
begin
  WinSockWork(6);
end;

procedure Tfrm_main.Winsock7Close(Sender: TObject);
begin
  WinSockWork(7);
end;

procedure Tfrm_main.Winsock8Close(Sender: TObject);
begin
  WinSockWork(8);
end;

procedure Tfrm_main.Winsock9Close(Sender: TObject);
begin
  WinSockWork(9);
end;

procedure Tfrm_main.Winsock10Close(Sender: TObject);
begin
  WinSockWork(10);
end;

procedure Tfrm_main.Winsock11Close(Sender: TObject);
begin
  WinSockWork(11);
end;

procedure Tfrm_main.Winsock12Close(Sender: TObject);
begin
  WinSockWork(12);
end;

procedure Tfrm_main.Winsock13Close(Sender: TObject);
begin
  WinSockWork(13);
end;

procedure Tfrm_main.Winsock14Close(Sender: TObject);
begin
  WinSockWork(14);
end;

procedure Tfrm_main.Winsock15Close(Sender: TObject);
begin
  WinSockWork(15);
end;

procedure Tfrm_main.Winsock16Close(Sender: TObject);
begin
  WinSockWork(16);
end;

procedure Tfrm_main.Winsock17Close(Sender: TObject);
begin
  WinSockWork(17);
end;

procedure Tfrm_main.Winsock18Close(Sender: TObject);
begin
  WinSockWork(18);
end;

procedure Tfrm_main.Winsock19Close(Sender: TObject);
begin
  WinSockWork(19);
end;

procedure Tfrm_main.Winsock20Close(Sender: TObject);
begin
  WinSockWork(20);
end;

procedure Tfrm_main.Winsock21Close(Sender: TObject);
begin
  WinSockWork(21);
end;

procedure Tfrm_main.Winsock22Close(Sender: TObject);
begin
  WinSockWork(22);
end;

procedure Tfrm_main.Winsock23Close(Sender: TObject);
begin
  WinSockWork(23);
end;

procedure Tfrm_main.Winsock24Close(Sender: TObject);
begin
  WinSockWork(24);
end;

procedure Tfrm_main.Winsock25Close(Sender: TObject);
begin
  WinSockWork(25);
end;

procedure Tfrm_main.Winsock26Close(Sender: TObject);
begin
  WinSockWork(26);
end;

procedure Tfrm_main.Winsock27Close(Sender: TObject);
begin
  WinSockWork(27);
end;

procedure Tfrm_main.Winsock28Close(Sender: TObject);
begin
  WinSockWork(28);
end;

procedure Tfrm_main.Winsock29Close(Sender: TObject);
begin
  WinSockWork(29);
end;

procedure Tfrm_main.Winsock30Close(Sender: TObject);
begin
  WinSockWork(30);
end;

procedure Tfrm_main.Winsock31Close(Sender: TObject);
begin
  WinSockWork(31);
end;

procedure Tfrm_main.Winsock32Close(Sender: TObject);
begin
  WinSockWork(32);
end;

procedure Tfrm_main.WinSockWork(WsId: Integer);
var
  tmpws: TWinsock;
begin
  try
    tmpws := TWinsock(FindComponent('Winsock'+ IntToStr(WsId)));
    tmpws.Close;
    tmpws.Bind(WSPort[WsId-1]);
    tmpws.Listen;
    TCheckBox(FindComponent('CheckBox'+ IntToStr(WsId))).Checked := False;
  except    
    on E:Exception do
      SaveSysLog('0 WinSockWork '+ inttostr(wsid) +' '+ e.Message);
  end;
end;

procedure Tfrm_main.Winsock1ConnectionRequest(ASender: TObject;
  requestID: Integer);
begin
  WinSockCR(1,requestID);
end;

procedure Tfrm_main.Winsock2ConnectionRequest(ASender: TObject;
  requestID: Integer);
begin
  WinSockCR(2,requestID);
end;

procedure Tfrm_main.Winsock3ConnectionRequest(ASender: TObject;
  requestID: Integer);
begin
  WinSockCR(3,requestID);
end;

procedure Tfrm_main.Winsock4ConnectionRequest(ASender: TObject;
  requestID: Integer);
begin
  WinSockCR(4,requestID);
end;

procedure Tfrm_main.Winsock5ConnectionRequest(ASender: TObject;
  requestID: Integer);
begin
  WinSockCR(5,requestID);
end;

procedure Tfrm_main.Winsock6ConnectionRequest(ASender: TObject;
  requestID: Integer);
begin
  WinSockCR(6,requestID);
end;

procedure Tfrm_main.Winsock7ConnectionRequest(ASender: TObject;
  requestID: Integer);
begin
  WinSockCR(7,requestID);
end;

procedure Tfrm_main.Winsock8ConnectionRequest(ASender: TObject;
  requestID: Integer);
begin
  WinSockCR(8,requestID);
end;

procedure Tfrm_main.Winsock9ConnectionRequest(ASender: TObject;
  requestID: Integer);
begin
  WinSockCR(9,requestID);
end;

procedure Tfrm_main.Winsock10ConnectionRequest(ASender: TObject;
  requestID: Integer);
begin
  WinSockCR(10,requestID);
end;

procedure Tfrm_main.Winsock11ConnectionRequest(ASender: TObject;
  requestID: Integer);
begin
  WinSockCR(11,requestID);
end;

procedure Tfrm_main.Winsock12ConnectionRequest(ASender: TObject;
  requestID: Integer);
begin
  WinSockCR(12,requestID);
end;

procedure Tfrm_main.Winsock13ConnectionRequest(ASender: TObject;
  requestID: Integer);
begin
  WinSockCR(13,requestID);
end;

procedure Tfrm_main.Winsock14ConnectionRequest(ASender: TObject;
  requestID: Integer);
begin
  WinSockCR(14,requestID);
end;

procedure Tfrm_main.Winsock15ConnectionRequest(ASender: TObject;
  requestID: Integer);
begin
  WinSockCR(15,requestID);
end;

procedure Tfrm_main.Winsock16ConnectionRequest(ASender: TObject;
  requestID: Integer);
begin
  WinSockCR(16,requestID);
end;

procedure Tfrm_main.Winsock17ConnectionRequest(ASender: TObject;
  requestID: Integer);
begin
  WinSockCR(17,requestID);
end;

procedure Tfrm_main.Winsock18ConnectionRequest(ASender: TObject;
  requestID: Integer);
begin
  WinSockCR(18,requestID);
end;

procedure Tfrm_main.Winsock19ConnectionRequest(ASender: TObject;
  requestID: Integer);
begin
  WinSockCR(19,requestID);
end;

procedure Tfrm_main.Winsock20ConnectionRequest(ASender: TObject;
  requestID: Integer);
begin
  WinSockCR(20,requestID);
end;

procedure Tfrm_main.Winsock21ConnectionRequest(ASender: TObject;
  requestID: Integer);
begin
  WinSockCR(21,requestID);
end;

procedure Tfrm_main.Winsock22ConnectionRequest(ASender: TObject;
  requestID: Integer);
begin
  WinSockCR(22,requestID);
end;

procedure Tfrm_main.Winsock23ConnectionRequest(ASender: TObject;
  requestID: Integer);
begin
  WinSockCR(23,requestID);
end;

procedure Tfrm_main.Winsock24ConnectionRequest(ASender: TObject;
  requestID: Integer);
begin
  WinSockCR(24,requestID);
end;

procedure Tfrm_main.Winsock25ConnectionRequest(ASender: TObject;
  requestID: Integer);
begin
  WinSockCR(25,requestID);
end;

procedure Tfrm_main.Winsock26ConnectionRequest(ASender: TObject;
  requestID: Integer);
begin
  WinSockCR(26,requestID);
end;

procedure Tfrm_main.Winsock27ConnectionRequest(ASender: TObject;
  requestID: Integer);
begin
  WinSockCR(27,requestID);
end;

procedure Tfrm_main.Winsock28ConnectionRequest(ASender: TObject;
  requestID: Integer);
begin
  WinSockCR(28,requestID);
end;

procedure Tfrm_main.Winsock29ConnectionRequest(ASender: TObject;
  requestID: Integer);
begin
  WinSockCR(29,requestID);
end;

procedure Tfrm_main.Winsock30ConnectionRequest(ASender: TObject;
  requestID: Integer);
begin
  WinSockCR(30,requestID);
end;

procedure Tfrm_main.Winsock31ConnectionRequest(ASender: TObject;
  requestID: Integer);
begin
  WinSockCR(31,requestID);
end;

procedure Tfrm_main.Winsock32ConnectionRequest(ASender: TObject;
  requestID: Integer);
begin
  WinSockCR(32,requestID);
end;

procedure Tfrm_main.WinSockCR(WsId: Integer;RequestId: Integer);
var
  tmpws: TWinsock;
begin
  try
    tmpws := TWinsock(FindComponent('Winsock'+ IntToStr(WsId)));
    if tmpws.State <> 0 then
      begin
        tmpws.Close;
      end;
    tmpws.Accept(RequestId);
    TCheckBox(FindComponent('CheckBox'+ IntToStr(WsId))).Checked := True;
  except        
    on E:Exception do
      SaveSysLog('0 WinSockCR '+ e.Message);
  end;
end;

procedure Tfrm_main.CheckBox1Click(Sender: TObject);
begin
  CheckBoxWork(1);
end;

procedure Tfrm_main.CheckBox2Click(Sender: TObject);
begin
  CheckBoxWork(2);
end;

procedure Tfrm_main.CheckBox3Click(Sender: TObject);
begin
  CheckBoxWork(3);
end;

procedure Tfrm_main.CheckBox4Click(Sender: TObject);
begin
  CheckBoxWork(4);
end;

procedure Tfrm_main.CheckBox5Click(Sender: TObject);
begin
  CheckBoxWork(5);
end;

procedure Tfrm_main.CheckBox6Click(Sender: TObject);
begin
  CheckBoxWork(6);
end;

procedure Tfrm_main.CheckBox7Click(Sender: TObject);
begin
  CheckBoxWork(7);
end;

procedure Tfrm_main.CheckBox8Click(Sender: TObject);
begin
  CheckBoxWork(8);
end;

procedure Tfrm_main.CheckBox9Click(Sender: TObject);
begin
  CheckBoxWork(9);
end;

procedure Tfrm_main.CheckBox10Click(Sender: TObject);
begin
  CheckBoxWork(10);
end;

procedure Tfrm_main.CheckBox11Click(Sender: TObject);
begin
  CheckBoxWork(11);
end;

procedure Tfrm_main.CheckBox12Click(Sender: TObject);
begin
  CheckBoxWork(12);
end;

procedure Tfrm_main.CheckBox13Click(Sender: TObject);
begin
  CheckBoxWork(13);
end;

procedure Tfrm_main.CheckBox14Click(Sender: TObject);
begin
  CheckBoxWork(14);
end;

procedure Tfrm_main.CheckBox15Click(Sender: TObject);
begin
  CheckBoxWork(15);
end;

procedure Tfrm_main.CheckBox16Click(Sender: TObject);
begin
  CheckBoxWork(16);
end;

procedure Tfrm_main.CheckBox17Click(Sender: TObject);
begin
  CheckBoxWork(17);
end;

procedure Tfrm_main.CheckBox18Click(Sender: TObject);
begin
  CheckBoxWork(18);
end;

procedure Tfrm_main.CheckBox19Click(Sender: TObject);
begin
  CheckBoxWork(19);
end;

procedure Tfrm_main.CheckBox20Click(Sender: TObject);
begin
  CheckBoxWork(20);
end;

procedure Tfrm_main.CheckBox21Click(Sender: TObject);
begin
  CheckBoxWork(21);
end;

procedure Tfrm_main.CheckBox22Click(Sender: TObject);
begin
  CheckBoxWork(22);
end;

procedure Tfrm_main.CheckBox23Click(Sender: TObject);
begin
  CheckBoxWork(23);
end;

procedure Tfrm_main.CheckBox24Click(Sender: TObject);
begin
  CheckBoxWork(24);
end;

procedure Tfrm_main.CheckBox25Click(Sender: TObject);
begin
  CheckBoxWork(25);
end;

procedure Tfrm_main.CheckBox26Click(Sender: TObject);
begin
  CheckBoxWork(26);
end;

procedure Tfrm_main.CheckBox27Click(Sender: TObject);
begin
  CheckBoxWork(27);
end;

procedure Tfrm_main.CheckBox28Click(Sender: TObject);
begin
  CheckBoxWork(28);
end;

procedure Tfrm_main.CheckBox29Click(Sender: TObject);
begin
  CheckBoxWork(29);
end;

procedure Tfrm_main.CheckBox30Click(Sender: TObject);
begin
  CheckBoxWork(30);
end;

procedure Tfrm_main.CheckBox31Click(Sender: TObject);
begin
  CheckBoxWork(31);
end;

procedure Tfrm_main.CheckBox32Click(Sender: TObject);
begin
  CheckBoxWork(32);
end;

procedure Tfrm_main.CheckBoxWork(CHKId: integer);
var
  tmpws: TWinsock;
begin
  try
    if TCheckBox(FindComponent('CheckBox'+ IntToStr(CHKId))).Checked = False then
      begin
        tmpws := TWinsock(FindComponent('Winsock'+ IntToStr(CHKId)));
        tmpws.Close;
        tmpws.Bind(WSPort[CHKId-1]);
        tmpws.Listen;
      end;
  except
    on E:Exception do
      SaveSysLog('0 CheckBoxWork '+ inttostr(CHKId) +' '+ e.Message);
  end;
end;

procedure Tfrm_main.FormCreate(Sender: TObject);
var
  tmpint: integer;
begin
  
  Timer1.Enabled := False;
  RzStatusPane3.Caption := GetLocalIP;    
  Sys.WaitTelCount := 0;
  Sys.UserNum := 64;
  Sys.NoConnCount := 0;
  Sys.TalkingCount := 0;
  Sys.UserBasyCount := 0;
  ZxStatusClose;
  tempchar := StrAlloc(20);//分配内存空间

  if Initini then
    begin
      DBConn;
  {ADOQuery1.Close;
  ADOQuery1.SQL.Add('insert into test (a,b)');
  ADOQuery1.SQL.Add('values (:a,:b)');
  ADOQuery1.Parameters.ParamByName('a').Value := 'aann';
  ADOQuery1.Parameters.ParamByName('b').Value := 'haha';
  ADOQuery1.ExecSQL;
  ADOQuery1.Close;}

      if DJSys_EnableCard('','prompt.ini') <> 0 then
        begin
          Application.MessageBox('板卡驱动加载失败！','提示',MB_ICONEXCLAMATION);
          SaveSysLog('0 板卡驱动加载失败');
          Exit;
        end;
      if DJISDN_InitSystem <> 1 then
        begin
          Application.MessageBox('ISDN驱动加载失败！','提示',MB_ICONEXCLAMATION);
          SaveSysLog('0 ISDN驱动加载失败');
          Exit;
        end;
    end; 

  Sys.Pcm := DJSys_GetPcmNum;//本系统内的pcm中继数目
  Sys.FreeDtmf := DJSys_FreeDtmfNum;//得到空闲的dtmf资源数
  Sys.FreeVoice := DJSys_FreePlayVoiceNum;//得到空闲的放音语音资源数
  Sys.TrunkNum := DJTrk_GetTotalTrunkNum;//系统内的中继通道总数
  Sys.UserNum := DJUser_GetTotalUserNum;//取用户通道总数
  //Sys.FaxNum := DJFax_GetTotalFaxChnl;//传真卡通道 

  if Sys.TrunkNum = 0 then
    begin
      Application.MessageBox('中继通道数为0！','提示',MB_ICONEXCLAMATION);
      SaveSysLog('0 中继通道数为0');
      Exit;
    end;      
  
  StringGrid1.ColWidths[0] := 27;
  StringGrid1.Cells[0,0] := '通道';
  StringGrid1.Cells[1,0] := '通道状态';
  StringGrid1.Cells[2,0] := '流程状态';
  StringGrid1.Cells[3,0] := '主叫号码';
  StringGrid1.Cells[4,0] := '被叫号码';
  StringGrid1.Cells[5,0] := '信息内容';
  StringGrid1.Cells[6,0] := 'DTMF';
  StringGrid1.ColWidths[5] := 0;
  StringGrid1.ColWidths[2] := 76;
  StringGrid1.ColWidths[3] := 100;
  StringGrid1.ColWidths[4] := 100;

  StringGrid1.RowCount := Sys.TrunkNum + 1;//中继卡表格的行数

  for tmpint := 0 to Sys.TrunkNum - 1 do
  begin
    StringGrid1.Cells[0,tmpint+1] := IntToStr(tmpint);
    Trunk[tmpint].Working := false;//初始化该通道工作状态为未工作
    //Trunk[tempTrunk].FlowStatus := TRK_FREE;//

    
    Trunk[tmpint].HRTime := StrToDateTime('00:00:00');
    Trunk[tmpint].THTime := StrToDateTime('00:00:00');
    Trunk[tmpint].JSTime := StrToDateTime('00:00:00');
    Trunk[tmpint].GJTime := StrToDateTime('00:00:00');

    Trunk[tmpint].ManYiDu := '0';
  end;

  StringGrid2.ColWidths[0] := 27;
  StringGrid2.Cells[0,0] := '通道';
  StringGrid2.ColWidths[1] := 80;
  StringGrid2.Cells[1,0] := '通道状态';
  StringGrid2.Cells[2,0] := '主叫号码';
  StringGrid2.Cells[3,0] := 'DTMF';
  StringGrid2.Cells[4,0] := '通话';
  StringGrid2.ColWidths[4] := 30;
  StringGrid2.Cells[5,0] := 'IP';
  StringGrid2.ColWidths[5] := 25;   
  StringGrid2.ColWidths[2] := 100;

  StringGrid2.RowCount := Sys.UserNum + 1;//坐席卡表格的行数

  //Sys.UserNum := 32;
  //sys.TrunkNum := 30;

  for tmpint := 0 to Sys.UserNum - 1 do
    begin
      StringGrid2.Cells[0,tmpint+1] := IntToStr(tmpint+1);//通道号

      DJUser_SetPowerON(tmpint);

      if DJUser_EnableDialSound(tmpint) = false then
        begin
          DJSys_DisableCard;//这个地方有问题
          SaveSysLog('0 坐席卡语音通道初始化失败 '+ IntToStr(tmpint));
        end;

      User[tmpint].LinkTrunk := -1;

      TWinsock(FindComponent('Winsock'+ IntToStr(tmpint+1))).Bind(WSPort[tmpint]);//端口绑定
      TWinsock(FindComponent('Winsock'+ IntToStr(tmpint+1))).Listen;           

      User[tmpint].HCTime := StrToDateTime('00:00:00');
      User[tmpint].THTime := StrToDateTime('00:00:00');
      User[tmpint].JSTime := StrToDateTime('00:00:00');
      User[tmpint].GJTime := StrToDateTime('00:00:00');
    end;

    
  {WinsockWait.Bind(wswait);
  WinsockWait.Listen;
  WinsockControl.Bind(wscontrol);
  WinsockControl.Listen;}

  //VocChannel[VOCBUSY] := DJVoc_SearchFreeVoiceChannelForPlay;//电话忙音
  VocChannel[VOCBUSY] := DJVoc_SFVC_ForPlay_New(0,false);
  DJVoc_LoopPlayPromptFile(VocChannel[VOCBUSY],'BUSY');
  //VocChannel[RINGBACK] := DJVoc_SearchFreeVoiceChannelForPlay;//电话打通以后的音  
  VocChannel[RINGBACK] := DJVoc_SFVC_ForPlay_New(0,false);
  DJVoc_LoopPlayPromptFile(VocChannel[RINGBACK],'RING');
  //VocChannel[VOCTONE] := DJVoc_SearchFreeVoiceChannelForPlay;//摘机后的长音
  VocChannel[VOCTONE] := DJVoc_SFVC_ForPlay_New(0,false);
  DJVoc_LoopPlayPromptFile(VocChannel[VOCTONE],'TONE');
  //VocChannel[SIG] := DJVoc_SearchFreeVoiceChannelForPlay;//重播电话的音
  VocChannel[SIG] := DJVoc_SFVC_ForPlay_New(0,false);
  DJVoc_LoopPlayPromptFile(VocChannel[SIG],'SIGN');

  SaveSysLog('1 底层系统初始化成功');

  Timer1.Enabled := True; 
end;     

procedure Tfrm_main.Timer1Timer(Sender: TObject);
var
  tmpint,tmpint1: integer;
  tmpif: TIniFile;
begin
try
  {处理中继通道的事件，改变中继通道的状态。此函数要在主流程的大循环中调用，
  一般同函数DJSys_PushPlay放在一起}
  DJISDN_GetEvent;
  {维持文件录音和文件放音连续的函数。要求应用程序必须在小于4秒钟内调用本函数一次(允许多次)
  在数字中继卡的底层驱动程序中，对每个语音通道开辟了64K的缓冲区。当进行文件
  方式的录音或放音时，不断的调用函数DJSys_PushPlay可以保证缓冲区的更新。}
  DJSys_PushPlay;

  //待接电话
  Sys.WaitTelCount := 0;
  Sys.FreeCount := 0;

  

  for tmpint := 0 to Sys.TrunkNum - 1 do
    begin
      TrunkWork(tmpint);
      TrunkShow(tmpint);

      
      //if (Trunk[tmpint].FlowStatus = TRK_WAIT) or (stringgrid1.Cells[2,tmpint+1] = '发送电话号码')  then
      if stringgrid1.Cells[2,tmpint+1] = '播放等待音乐' then
        Sys.WaitTelCount := Sys.WaitTelCount + 1;
      
      if stringgrid1.Cells[2,tmpint+1] = '空闲' then
        Sys.FreeCount := Sys.FreeCount + 1;


      for tmpint1 := 0 to Sys.UserNum - 1 do
        begin
          UserWork(tmpint1);
          UserShow(tmpint1);
        end;
    end;

               
  Sys.NoConnCount := 0;//未连接数量
  Sys.TalkingCount := 0;//当前的通话数量
  Sys.UserBasyCount := 0;//当前坐席正忙的数量
  Sys.UserFreeCount := 0;//坐席空闲的数量
  for tmpint := 0 to Sys.UserNum - 1 do
    begin
      if (User[tmpint].FlowStatus = USER_UCONNECT)  OR (User[tmpint].FlowStatus = USER_TALKING) then
        Sys.TalkingCount := Sys.TalkingCount + 1;
      //if User[tmpint].FlowStatus = USER_NOCONN then
        //Sys.NoConnCount := Sys.NoConnCount + 1;
      //if User[tmpint].FlowStatus = User_Busy then
      //  Sys.UserBasyCount := Sys.UserBasyCount + 1;

      if (stringgrid2.Cells[1,tmpint+1] = '坐席正忙') or (stringgrid2.Cells[1,tmpint+1] = '用户摘机1') or (stringgrid2.Cells[1,tmpint+1] = '用户摘机2') then
        Sys.UserBasyCount := Sys.UserBasyCount + 1;

      if stringgrid2.Cells[1,tmpint+1] = '坐席没有连接' then
        Sys.NoConnCount := Sys.NoConnCount + 1;
        
      if stringgrid2.Cells[1,tmpint+1] = '空闲' then
        Sys.UserFreeCount := Sys.UserFreeCount + 1;
    end;

  //RzStatusPane2.Caption := '总语音通道:'+ IntToStr(DJVoc_GetTotalVoiceChannel) +' 空闲语音通道:'+ IntToStr(DJSys_FreePlayVoiceNum) +' 通话数量：'+ IntToStr(Sys.TalkingCount) +' 等待数量:'+ IntToStr(Sys.WaitTelCount) +' 正忙数量：'+ IntToStr(Sys.UserBasyCount) +' 未连接数量：'+ IntToStr(Sys.NoConnCount);}
  RzStatusPane2.Caption := '总语音通道:'+ IntToStr(DJVoc_GetTotalVoiceChannel) +' 空闲语音通道:'+ IntToStr(DJSys_FreePlayVoiceNum) +' 等待数量:'+ IntToStr(Sys.WaitTelCount) +' 中继卡通道:'+ IntToStr(Sys.TrunkNum) +' 坐席卡通道:'+ IntToStr(Sys.UserNum);

  
  if Frm_Info <> nil then
  begin
    Frm_Info.ut.Caption := IntToStr(Sys.TalkingCount);//用户通话
    Frm_Info.uk.Caption := IntToStr(Sys.FreeCount);//用户空闲
    Frm_Info.ud.Caption := IntToStr(Sys.WaitTelCount);//用户等待

    Frm_Info.zt.Caption := IntToStr(Sys.TalkingCount);//坐席通话
    Frm_Info.zk.Caption := IntToStr(Sys.UserFreeCount);;//坐席空闲
    Frm_Info.zz.Caption := IntToStr(Sys.UserBasyCount);//坐席正忙
    Frm_Info.zw.Caption := IntToStr(Sys.NoConnCount);//坐席未连接
  end;

    //写入ini文件 服务当前运行状态显示
    tmpif := TIniFile.Create(ExtractFilePath(Application.ExeName) + IniFile);
    tmpif.WriteString('cs','ut',IntToStr(Sys.TalkingCount));
    tmpif.WriteString('cs','uk',IntToStr(Sys.FreeCount));
    tmpif.WriteString('cs','ud',IntToStr(Sys.WaitTelCount));
    tmpif.WriteString('cs','zt',IntToStr(Sys.TalkingCount));
    tmpif.WriteString('cs','zk',IntToStr(Sys.UserFreeCount));
    tmpif.WriteString('cs','zz',IntToStr(Sys.UserBasyCount));
    tmpif.WriteString('cs','zw',IntToStr(Sys.NoConnCount));
    tmpif.Free;

  //清空ip地址和坐席通道
  if FormatDateTime('hh:mm:ss',now) = '23:59:59' then
    begin
      SaveTelStat;
      {for tmpint1 := 0 to Sys.UserNum - 1 do
        begin

          User[tmpint1].TelCount := 0;
          StringGrid2.Cells[5,tmpint1+1] := '';
        end;}
    end;  

except      
    on E:Exception do
      SaveSysLog('0 Timer1Timer '+ e.Message);
end;
end;

procedure Tfrm_main.TrunkShow(TrunkId: Integer);
var
  npcm,nchn,chnstate: integer;
  tmpstr: string;
begin
  try
  if (TrunkId < 0) or (TrunkId >= Sys.TrunkNum) then
    exit;
  npcm := TrunkId div 30;
  nchn := TrunkId mod 30;
  chnstate := DJISDN_GetChnState(npcm,nchn);

  //通道状态
  case chnstate of
    CH_FREE:
      tmpstr := 'FREE';
    CH_WAIT_APP_FREE:
      tmpstr := 'WAIT_APP_FREE';
    CH_UNAVIABLE:
      tmpstr := 'UNAVIABLE';
    CH_WAIT_CONNECT_ACK:
      tmpstr := 'WAIT_CONNECT_ACK';
    CH_CONNECT:
      tmpstr := 'CONNECT';
    CH_WAIT_RELEASE:
      tmpstr := 'WAIT_RELEASE';
    CH_WAIT_RELEASE_COMPLETE:
      tmpstr := 'WAIT_RELEASE_COMPLETE';
    CALLEE_WAIT_ANSWER:
      tmpstr := 'WAIT_ANSWER';
    CALLER_WAIT_ANSWER:
      tmpstr := 'WAIT_ANSWER';
    CALLER_RECV_ALERT:
      tmpstr := 'RECV_ALERT';
    CALLER_RECV_SETUP_ACK:
      tmpstr := 'RECY_SETUP_ACK';
    CALLER_RECV_CALLPROCESS:
      tmpstr := 'RECV_CALLPROCESS';
  else
    tmpstr := '';
  end;

  if tmpstr <> StringGrid1.Cells[1,TrunkId+1] then
    StringGrid1.Cells[1,TrunkId+1] := tmpstr;

  //流程状态
  case Trunk[TrunkId].FlowStatus of
    TRK_FREE:
      tmpstr := '空闲';
    TRK_PLAYWELCOME:
      tmpstr := '播放欢迎音乐';
    TRK_PLAYSELECT:
      tmpstr := '播放选择服务';
    TRK_FWZX:
      tmpstr := '服务咨询';
    TRK_YWSL:
      tmpstr := '业务受理';
    TRK_TSJY:
      tmpstr := '投诉建议';
    TRK_WAIT:
      tmpstr := '播放等待音乐';
    TRK_BUSY:
      tmpstr := '话务员正忙';
    TRK_PLAYZXCODE:
      tmpstr := '播放话务员号';
    TRK_SENDHOOKONSIGN:
      tmpstr := '发送挂机信号';
    TRK_PLAYHOOKON:
      tmpstr := '播放挂机音乐';
    TRK_TALKING:
      tmpstr := '通话中';
    TRK_TALKING1:
      tmpstr := '通话中1';
    TRK_SENDPHONECODE:
      tmpstr := '发送电话号码';
    TRK_READYTALK:
      tmpstr := '准备通话';
    TRK_CONWAIT:
      tmpstr := '连接等待';
    TRK_MANYI1:
      tmpstr := '满意度调查1';
    TRK_MANYI2:
      tmpstr := '满意度调查2';
    TRK_MANYI3:
      tmpstr := '满意度调查3';
    TRK_ONHOOK:
      tmpstr := '发送挂机信号';
    TRK_RING:
      tmpstr := '客户正在振铃';
    TRK_UCONNECT:
      tmpstr := '通话中';
    TRK_TELWZ1:
      tmpstr := '电话已外转';
    TRK_TELWZ2:
      tmpstr := '外转振铃';
    TRK_TELWZ3:
      tmpstr := '外转通话中';
    else
      tmpstr := '';
    end;

  if tmpstr <> StringGrid1.Cells[2,TrunkId+1] then
    StringGrid1.Cells[2,TrunkId+1] := tmpstr;

  //显示dtmf
  if Trunk[TrunkId].FlowStatus = Trk_Free then
    StringGrid1.Cells[6,TrunkId+1] := ''
  else
    begin
      tmpstr := DJTrk_GetDtmfCode(TrunkId);
      if StringGrid1.Cells[6,TrunkId+1] <> tmpstr then
        StringGrid1.Cells[6,TrunkId+1] := tmpstr;
    end;

  //显示主叫号码和被叫号码
  tmpstr := Trunk[TrunkId].CalleeSubAddr + Trunk[TrunkId].CalleeNumber;
  if StringGrid1.Cells[4,TrunkId+1] <> tmpstr then
    StringGrid1.Cells[4,TrunkId+1] := tmpstr;
  tmpstr := Trunk[TrunkId].CallerSubAddr + Trunk[TrunkId].CallerNumber;
  if StringGrid1.Cells[3,TrunkId+1] <> tmpstr then
    StringGrid1.Cells[3,TrunkId+1] := tmpstr;
  except    
    on E:Exception do
      SaveSysLog('0 TrunkShow '+ e.Message);
  end;
end;

procedure Tfrm_main.TrunkWork(TrunkId: Integer);
var
  npcm,nchn,chnstate,tmpint,tmpint2: Integer;
  //tmpchar: PChar;
  dtmf: Char;
  tmpws: TWinsock;
  tmpstr: String;
begin
try
  npcm := TrunkId div 30;
  nchn := TrunkId mod 30;
  chnstate := DJISDN_GetChnState(npcm,nchn);

  if (chnstate = CH_WAIT_APP_FREE) or (chnstate = CH_WAIT_RELEASE) then
    begin
      TrunkReset(TrunkId);
      DJISDN_SetChnState(npcm,nchn,CH_SET_FREE,0);//设置通道状态为空闲
      Exit;
    end;

  //tmpchar := StrAlloc(20);

  case Trunk[TrunkId].FlowStatus of
    TRK_FREE:
      begin
        if chnstate = CALLEE_WAIT_ANSWER then
          begin
            {DJISDN_GetCalleeSubAddr(npcm,nchn,tmpchar);//获取被叫子地址号码
            Trunk[TrunkId].CalleeSubAddr := String(tmpchar);
            DJISDN_GetCalleeNumber(npcm,nchn,tmpchar);//获取被叫号码
            Trunk[TrunkId].CalleeNumber := String(tmpchar);
            DJISDN_GetCallerSubAddr(npcm,nchn,tmpchar);//获取主叫子地址号码
            Trunk[TrunkId].CallerSubAddr := String(tmpchar);
            DJISDN_GetCallerNumber(npcm,nchn,tmpchar);//获取主叫号码
            Trunk[TrunkId].CallerNumber := String(tmpchar);}

            
            DJISDN_GetCalleeSubAddr(npcm,nchn,tempchar);//获取被叫子地址号码
            Trunk[TrunkId].CalleeSubAddr := String(tempchar);
            DJISDN_GetCalleeNumber(npcm,nchn,tempchar);//获取被叫号码
            Trunk[TrunkId].CalleeNumber := String(tempchar);
            DJISDN_GetCallerSubAddr(npcm,nchn,tempchar);//获取主叫子地址号码
            Trunk[TrunkId].CallerSubAddr := String(tempchar);
            DJISDN_GetCallerNumber(npcm,nchn,tempchar);//获取主叫号码
            Trunk[TrunkId].CallerNumber := String(tempchar);

            Trunk[TrunkId].HRTime := Now;

            DJISDN_SetChnState(npcm,nchn,CH_SET_CONNECT,0);//设置通道连接
            DJVoc_PlayFile(TrunkId,GetWelcomeVoice);
            Trunk[TrunkId].FlowStatus := TRK_PLAYWELCOME;//播放欢迎语音
          end;
        
      end;
    TRK_PLAYWELCOME:
      begin
        tmpint := DJTrk_GetTrunkPlayID(TrunkId);//取得给本通道号放音的语音通道号
        if (tmpint >= 0) and DJVoc_CheckVoiceEnd(tmpint) then
          begin
            DJVoc_StopPlayFile(TrunkId);//停止该通道的放音
            DJVoc_PlayFile(TrunkId,'voc\htjs.04');//服务咨询请按1,业务受理请按2,投诉建议请按3
            DJTrk_InitDtmfBuf(TrunkId);//清空dtmf缓冲区
            Trunk[TrunkId].FlowStatus := TRK_PLAYSELECT;//播放选择音乐
          end;
      end;
    TRK_PLAYSELECT:
      begin
        tmpint := DJTrk_GetTrunkPlayID(TrunkId);
        if tmpint >= 0 then
          begin
            if DJVoc_CheckVoiceEnd(tmpint) then//如果语音播放完毕
              begin
                DJVoc_StopPlayFile(TrunkId);//停止播放语音文件
                DJVoc_PlayFile(TrunkId,'voc\htjs.05');//
                Trunk[TrunkId].FlowStatus := TRK_PLAYHOOKON;
              end
            else
              begin
                if DJTrk_GetReciveDtmfNum(TrunkId) > 0 then
                  begin
                    dtmf := DJTrk_GetLastDtmfCode(TrunkId);
                    DJTrk_InitDtmfBuf(TrunkId);
                    case dtmf of
                      '1'://服务咨询                                                   5
                        begin
                          if (TelFWZXZJ = 1) and (TelFWZXZJNumber <> '') then
                            begin
                              Trunk[TrunkId].LinkUser := GetFreeTrunk;

                              if Trunk[TrunkId].LinkUser = -1 then//没有空闲的通道
                                begin
                                  SaveHRLog(TrunkId);
                                end
                              else
                                begin
                                  DJISDN_Callout(Trunk[TrunkId].LinkUser div 30,Trunk[TrunkId].LinkUser mod 30,pchar(TelFWZXZJNumber),'',pchar(sys.PhoneNumber),'');
                                  DJExg_SetLinkTrunkAndTrunk(TrunkId,Trunk[TrunkId].LinkUser);
                                  Trunk[Trunk[TrunkId].LinkUser].LinkUser := TrunkId;
                                  Trunk[TrunkId].CalleeSubAddr := '';
                                  Trunk[TrunkId].CalleeNumber := Trunk[TrunkId].CallerSubAddr + Trunk[TrunkId].CallerNumber;
                                  Trunk[TrunkId].CallerSubAddr := '';
                                  Trunk[TrunkId].CallerNumber := TelFWZXZJNumber;
                                  Trunk[TrunkId].FlowStatus := TRK_TELWZ1;
                                  Trunk[Trunk[TrunkId].LinkUser].FlowStatus := TRK_TELWZ2;
                                end;
                            end
                          else
                            begin
                              DJVoc_StopPlayFile(TrunkId);
                              Trunk[TrunkId].Service := 1;
                              Trunk[TrunkId].FlowStatus := TRK_FWZX;
                            end;
                        end;
                      '2'://业务受理
                        begin
                          if (TelYWSLZJ = 1) and (TelYWSLZJNumber <> '') then
                            begin
                              Trunk[TrunkId].LinkUser := GetFreeTrunk;

                              if Trunk[TrunkId].LinkUser = -1 then
                                begin
                                  SaveHRLog(TrunkId);
                                end
                              else
                                begin
                                  DJISDN_Callout(Trunk[TrunkId].LinkUser div 30,Trunk[TrunkId].LinkUser mod 30,pchar(TelYWSLZJNumber),pchar(''),pchar(sys.PhoneNumber),pchar(''));
                                  DJExg_SetLinkTrunkAndTrunk(TrunkId,Trunk[TrunkId].LinkUser);
                                  Trunk[Trunk[TrunkId].LinkUser].LinkUser := TrunkId;
                                  Trunk[TrunkId].CalleeSubAddr := '';
                                  Trunk[TrunkId].CalleeNumber := Trunk[TrunkId].CallerSubAddr + Trunk[TrunkId].CallerNumber;
                                  Trunk[TrunkId].CallerSubAddr := '';
                                  Trunk[TrunkId].CallerNumber := TelYWSLZJNumber;
                                  Trunk[TrunkId].FlowStatus := TRK_TELWZ1;
                                  Trunk[Trunk[TrunkId].LinkUser].FlowStatus := TRK_TELWZ2;
                                end;
                            end
                          else
                            begin
                              DJVoc_StopPlayFile(TrunkId);
                              Trunk[TrunkId].Service := 2;
                              Trunk[TrunkId].FlowStatus := TRK_FWZX;
                            end;
                        end;
                      '3'://投诉建议
                        begin
                          if (TelTSJYZJ = 1) and (TelTSJYZJNumber <> '') then
                            begin
                              Trunk[TrunkId].LinkUser := GetFreeTrunk;

                              if Trunk[TrunkId].LinkUser = -1 then
                                begin
                                  SaveHRLog(TrunkId);
                                end
                              else
                                begin
                                  DJISDN_Callout(Trunk[TrunkId].LinkUser div 30,Trunk[TrunkId].LinkUser mod 30,pchar(TelTSJYZJNumber),pchar(''),pchar(sys.PhoneNumber),pchar(''));
                                  DJExg_SetLinkTrunkAndTrunk(TrunkId,Trunk[TrunkId].LinkUser);
                                  Trunk[Trunk[TrunkId].LinkUser].LinkUser := TrunkId;
                                  Trunk[TrunkId].CalleeSubAddr := '';
                                  Trunk[TrunkId].CalleeNumber := Trunk[TrunkId].CallerSubAddr + Trunk[TrunkId].CallerNumber;
                                  Trunk[TrunkId].CallerSubAddr := '';
                                  Trunk[TrunkId].CallerNumber := TelTSJYZJNumber;
                                  Trunk[TrunkId].FlowStatus := TRK_TELWZ1;
                                  Trunk[Trunk[TrunkId].LinkUser].FlowStatus := TRK_TELWZ2;
                                end;
                            end
                          else
                            begin
                              DJVoc_StopPlayFile(TrunkId);
                              Trunk[TrunkId].Service := 3;
                              Trunk[TrunkId].FlowStatus := TRK_FWZX;
                            end;
                        end;
                      end;
                  end;
              end;
          end;           
        if DJISDN_GetChnState(npcm,nchn) <> CH_CONNECT then
          begin
            TrunkReset(TrunkId);
          end;
      end;
    TRK_TELWZ1://电话已外转
      begin      
        if DJISDN_GetChnState(npcm,nchn) <> CH_CONNECT then
          begin
            DJExg_ClearLinkTrunkAndTrunk(trunkid);
            trunk[trunkid].FlowStatus := TRK_SENDHOOKONSIGN;
            trunk[trunk[trunkid].LinkUser].FlowStatus := TRK_ONHOOK;
          end;

        if DJTrk_GetConnectTrunkID(trunkid) = -1 then
          begin
            trunk[trunkid].FlowStatus := TRK_SENDHOOKONSIGN;
            trunk[trunk[trunkid].LinkUser].FlowStatus := TRK_ONHOOK;
          end;

        if (DJISDN_GetChnState(trunk[trunkid].LinkUser div 30,trunk[trunkid].LinkUser mod 30) <> CH_CONNECT) and (trunk[trunk[trunkid].LinkUser].FlowStatus = TRK_TELWZ3) then
          begin
            DJExg_ClearLinkTrunkAndTrunk(trunkid);
            trunk[trunkid].FlowStatus := TRK_SENDHOOKONSIGN;
            trunk[trunk[trunkid].LinkUser].FlowStatus := TRK_ONHOOK;
          end;

        if DJISDN_GetChnState(trunk[trunkid].LinkUser div 30,trunk[trunkid].LinkUser mod 30) = CH_FREE then
          begin
            if DJTrk_GetConnectTrunkID(trunkid) >= 0 then
              DJExg_ClearLinkTrunkAndTrunk(trunkid);
              
            trunk[trunkid].FlowStatus := TRK_SENDHOOKONSIGN;
            trunk[trunk[trunkid].LinkUser].FlowStatus := TRK_ONHOOK;
          end;
      end;   
    TRK_TELWZ2://外转振铃
      begin
        if DJISDN_GetChnState(npcm,nchn) = CH_CONNECT then
          trunk[trunkid].FlowStatus := TRK_TELWZ3;
      end;
    TRK_TELWZ3://外转通话中
      begin
      end;
    TRK_FWZX://服务咨询
      begin
        Trunk[TrunkId].LinkUser := GetFreeUser(Trunk[TrunkId].Service);
        if Trunk[TrunkId].LinkUser = -1 then//没有可用的坐席连接
          begin
            DJVoc_PlayFile(TrunkId,'voc\htjs.02');
            Trunk[TrunkId].FlowStatus := TRK_BUSY;//通道忙
          end
        else
          begin
            User[Trunk[TrunkId].LinkUser].CalleeSubAddr := Trunk[TrunkId].CalleeSubAddr;
            User[Trunk[TrunkId].LinkUser].CalleeNumber := Trunk[TrunkId].CalleeNumber;
            User[Trunk[TrunkId].LinkUser].CallerSubAddr := Trunk[TrunkId].CallerSubAddr;
            User[Trunk[TrunkId].LinkUser].CallerNumber := Trunk[TrunkId].CallerNumber;

            User[Trunk[TrunkId].LinkUser].LinkTrunk := TrunkId;
            DJUser_StartRing(Trunk[TrunkId].LinkUser);
            User[Trunk[TrunkId].LinkUser].FlowStatus := USER_RING;
            DJExg_SetLinkPlayVoiceToTrunk(TrunkId,VocChannel[RINGBACK]);

            tmpws := GetWinSock(Trunk[TrunkId].LinkUser);

            //0429 改
            if tmpws.State = 7 then //如果连接正常
              begin
                tmpws.SendData('PHONE'+ inttostr(trunk[trunkid].Service) + Trunk[TrunkId].CallerSubAddr + Trunk[TrunkId].CallerNumber);
                //Trunk[TrunkId].FlowStatus := TRK_SENDPHONECODE;//发送电话号码给winsock
              end
            else
              begin
                {UserReset(Trunk[TrunkId].LinkUser);
                //多选框取消
                TCheckBox(FindComponent('CheckBox'+ IntToStr(Trunk[TrunkId].LinkUser + 1))).checked := false;
              }end; 
            //0429 改
            //0509晚改  改了又加的原因是如果座席没有电，服务器有电，那么也可以接听
            Trunk[TrunkId].FlowStatus := TRK_SENDPHONECODE;//发送电话号码给winsock
            //0509晚改
          end;
      end;
    TRK_SENDPHONECODE://发送电话号码给winsock
      begin
        if DJUser_CheckHookOFF(Trunk[TrunkId].LinkUser) then//检查用户是否挂机
          begin
            //0508  检测如果坐席摘机,那么停止振铃
            if DJUser_RingDetect(Trunk[TrunkId].LinkUser) then
              DJUser_StopRing(Trunk[TrunkId].LinkUser);
            //0508
            User[Trunk[TrunkId].LinkUser].Working := True;

            //tmpstr := 'd1,d2,d6';
            tmpstr := GetZXCodeVoc(Trunk[TrunkId].LinkUser);//获取坐席编码的语音文件

            //0429 移动
            DJExg_ClearLinkPlayVoiceFromTrunk(TrunkId);
            //0429
            
            DJUser_PlayPromptStr(Trunk[TrunkId].LinkUser,tmpstr);
            DJTrk_PlayPromptStr(TrunkId,tmpstr);

            Trunk[TrunkId].HRTime := Now;

            User[Trunk[TrunkId].LinkUser].TelCount := User[Trunk[TrunkId].LinkUser].TelCount + 1;

            Trunk[TrunkId].PlayZXCode := false;
            User[Trunk[TrunkId].LinkUser].PlayZXCode := false;
            Trunk[TrunkId].FlowStatus := TRK_PLAYZXCODE;//给两方播放通道号码
          end;
        if DJISDN_GetChnState(npcm,nchn) <> CH_CONNECT then
          begin
            SaveHRLog(TrunkId);
            UserReset(Trunk[TrunkId].LinkUser);
          end;
      end;
    TRK_PLAYZXCODE://给双方播放通道号码
      begin
        if Trunk[TrunkId].PlayZXCode = false then
          begin
            if DJTrk_CheckPlayPromptStrEnd(TrunkId) then
              begin
                DJVoc_StopPlayFile(TrunkId);
                DJVoc_PlayFile(TrunkId,'voc\htjs.11');
                Trunk[TrunkId].PlayZXCode := true;
              end;
          end;
        if User[Trunk[TrunkId].LinkUser].PlayZXCode = false then
          begin
            if DJUser_CheckPlayPromptStrEnd(Trunk[TrunkId].LinkUser) then
              begin
                DJUser_StopPlayFile(Trunk[TrunkId].LinkUser);
                DJUser_PlayFileNew(Trunk[TrunkId].LinkUser,'voc\htjs.11',0,4294967295);
                User[Trunk[TrunkId].LinkUser].PlayZXCode := true;
              end;
          end;
        if (Trunk[TrunkId].PlayZXCode = true) and (User[Trunk[TrunkId].LinkUser].PlayZXCode = true) then
          begin
            Trunk[TrunkId].PlayZXCode := false;
            User[Trunk[TrunkId].LinkUser].PlayZXCode := false;
            Trunk[TrunkId].FlowStatus := TRK_READYTALK;//准备通话
          end;
        if DJISDN_GetChnState(npcm,nchn) <> CH_CONNECT then
          begin
            //SaveHRLog(TrunkId);
            //User[Trunk[TrunkId].LinkUser].FlowStatus := USER_WAITHOOKON;


            User[Trunk[TrunkId].LinkUser].FlowStatus := USER_HOOKON;
            if DJTrk_GetTrunkPlayID(TrunkId) >= 0 then
              begin
                DJVoc_StopPlayFile(TrunkId);
              end;
            if DJUser_GetPlayChannel(Trunk[TrunkId].LinkUser) >= 0 then
              begin
                DJUser_StopPlayFile(Trunk[TrunkId].LinkUser);
              end;

          end;
      end;
    TRK_READYTALK://准备通话
      begin
        tmpint := DJTrk_GetTrunkPlayID(TrunkId);
        tmpint2 := DJUser_GetPlayChannel(Trunk[TrunkId].LinkUser);
        if (tmpint >= 0) and (tmpint2 >= 0) and DJVoc_CheckVoiceEnd(tmpint) and DJVoc_CheckVoiceEnd(tmpint2) then
          begin
            DJVoc_StopPlayFile(TrunkId);
            DJUser_StopPlayFile(Trunk[TrunkId].LinkUser);
            Trunk[TrunkId].FlowStatus := TRK_TALKING;//通话中
            User[Trunk[TrunkId].LinkUser].Delay := 0; 
          end;
        if DJISDN_GetChnState(npcm,nchn) <> CH_CONNECT then
          begin

            if tmpint >= 0 then
              begin
                DJVoc_StopPlayFile(TrunkId);
              end;
            if tmpint2 >= 0 then
              begin
                DJVoc_StopPlayFile(Trunk[TrunkId].LinkUser);
              end;
            Trunk[TrunkId].FlowStatus := TRK_SENDHOOKONSIGN;
            //User[Trunk[TrunkId].LinkUser].FlowStatus := USER_WAITHOOKON;
            User[Trunk[TrunkId].LinkUser].FlowStatus := USER_HOOKON;
            
          end;
      end;
    TRK_TALKING:
      begin
        if User[Trunk[TrunkId].LinkUser].Delay = 1 then
          begin
            Trunk[TrunkId].THTime := Now;//通话开始时间
            User[Trunk[TrunkId].LinkUser].Delay := 0;
            DJExg_SetLinkTrunkAndUser(TrunkId,Trunk[TrunkId].LinkUser);//连接两个通道
            Trunk[TrunkId].FlowStatus := TRK_TALKING1;
            //在此可以进行录音
            if Sys.TelRecord = 1 then
              begin
                StartRecord(TrunkId,trunk[trunkid].LinkUser);
              end;
          end
        else
          begin
            User[Trunk[TrunkId].LinkUser].Delay := User[Trunk[TrunkId].LinkUser].Delay + 1; 
          end;
        if DJISDN_GetChnState(npcm,nchn) <> CH_CONNECT then
          begin

            Trunk[TrunkId].FlowStatus := TRK_SENDHOOKONSIGN;
            //User[Trunk[TrunkId].LinkUser].FlowStatus := USER_WAITHOOKON;
            User[Trunk[TrunkId].LinkUser].FlowStatus := USER_HOOKON;
          end;
      end;
    TRK_TALKING1://通话中
      begin
        if DJISDN_GetChnState(npcm,nchn) <> CH_CONNECT then
          begin
            Trunk[TrunkId].FlowStatus := TRK_SENDHOOKONSIGN;//通道状态
            User[Trunk[TrunkId].LinkUser].FlowStatus := USER_HOOKON;//坐席状态

            if Sys.TelRecord = 1 then
              begin
                EndRecord(trunk[trunkid].LinkUser);
              end;

            Trunk[TrunkId].JSTime := Now;
            //SaveHRLog(TrunkId);
            //此处进行数据库保存
          end;
        if Sys.TelRecord = 1 then
          begin
            tmpint := DJUser_GetRecordChannel(trunk[trunkid].LinkUser);
            if (tmpint >= 0) and DJVoc_CheckVoiceEnd(tmpint) then
              DJUser_StopRecordFile(trunk[trunkid].LinkUser);
          end;
      end;
    TRK_BUSY:
      begin
        tmpint := DJTrk_GetTrunkPlayID(TrunkId);
        if (tmpint >= 0) and DJVoc_CheckVoiceEnd(tmpint) then
          begin
            DJVoc_StopPlayFile(TrunkId);
            DJVoc_PlayFile(TrunkId,'voc\htjs.03');
            Trunk[TrunkId].FlowStatus := TRK_WAIT;
            //Sys.WaitTelCount := Sys.WaitTelCount + 1;
          end;
        if DJISDN_GetChnState(npcm,nchn) <> CH_CONNECT then
          begin
            //SaveHRLog(TrunkId);
          end;
      end;
    TRK_WAIT://播放等待音乐
      begin
        tmpint := DJTrk_GetTrunkPlayID(TrunkId);
        if (tmpint >= 0) and DJVoc_CheckVoiceEnd(tmpint) then
          begin
            DJVoc_StopPlayFile(TrunkId);
            DJVoc_PlayFile(TrunkId,'voc\htjs.15');
            Trunk[TrunkId].FlowStatus := TRK_CONWAIT;
            DJTrk_InitDtmfBuf(TrunkId);
          end;
        Trunk[TrunkId].LinkUser := GetFreeUser(Trunk[TrunkId].Service);

        if Trunk[TrunkId].LinkUser >= 0 then
          begin
            DJVoc_StopPlayFile(TrunkId);
            if DJISDN_GetChnState(npcm,nchn) = CH_CONNECT then
              begin
                User[Trunk[TrunkId].LinkUser].CalleeSubAddr := Trunk[TrunkId].CalleeSubAddr;
                User[Trunk[TrunkId].LinkUser].CalleeNumber := Trunk[TrunkId].CalleeNumber;
                User[Trunk[TrunkId].LinkUser].CallerSubAddr := Trunk[TrunkId].CallerSubAddr;
                User[Trunk[TrunkId].LinkUser].CallerNumber := Trunk[TrunkId].CallerNumber;

                User[Trunk[TrunkId].LinkUser].LinkTrunk := TrunkId;
                DJUser_StartRing(Trunk[TrunkId].LinkUser);
                User[Trunk[TrunkId].LinkUser].FlowStatus := USER_RING;
                DJExg_SetLinkPlayVoiceToTrunk(TrunkId,VocChannel[RINGBACK]);

                tmpws := GetWinSock(Trunk[TrunkId].LinkUser);

                //0429 注释
                {if tmpws.State = 7 then
                  begin
                    tmpws.SendData('PHONE'+ inttostr(trunk[trunkid].Service) + Trunk[TrunkId].CallerSubAddr + Trunk[TrunkId].CallerNumber);
                  end;

                //Sys.WaitTelCount := Sys.WaitTelCount - 1;
                Trunk[TrunkId].FlowStatus := TRK_SENDPHONECODE;//发送电话号码给winsock} 
                //0429 注释

               //0429 ++
            if tmpws.State = 7 then //如果连接正常
              begin
                tmpws.SendData('PHONE'+ inttostr(trunk[trunkid].Service) + Trunk[TrunkId].CallerSubAddr + Trunk[TrunkId].CallerNumber);
                //Trunk[TrunkId].FlowStatus := TRK_SENDPHONECODE;//发送电话号码给winsock
              end
            else
              begin
                {UserReset(Trunk[TrunkId].LinkUser);
                //多选框取消
                TCheckBox(FindComponent('CheckBox'+ IntToStr(Trunk[TrunkId].LinkUser + 1))).checked := false;
              }end; 
               //0429 ++

               //0509 ++
               Trunk[TrunkId].FlowStatus := TRK_SENDPHONECODE;//发送电话号码给winsock
               //0509 ++
              
              end;
          end;

        if DJISDN_GetChnState(npcm,nchn) <> CH_CONNECT then
          begin
            SaveHRLog(TrunkId);
            //Sys.WaitTelCount := Sys.WaitTelCount - 1;
          end;
        
        {tmpint := DJTrk_GetTrunkPlayID(TrunkId);
        if (tmpint >= 0) and DJVoc_CheckVoiceEnd(tmpint) then
          begin
            DJVoc_StopPlayFile(TrunkId);
            DJVoc_PlayFile(TrunkId,'voc\htjs.15');
            Trunk[TrunkId].FlowStatus := TRK_CONWAIT;
            DJTrk_InitDtmfBuf(TrunkId);
          end;
        Trunk[TrunkId].LinkUser := GetFreeUser(Trunk[TrunkId].Service);
        if Trunk[TrunkId].LinkUser >= 0 then
          begin
            DJVoc_StopPlayFile(TrunkId);
            if DJISDN_GetChnState(npcm,nchn) = CH_CONNECT then
              begin                
                User[Trunk[TrunkId].LinkUser].CalleeSubAddr := Trunk[TrunkId].CalleeSubAddr;
                User[Trunk[TrunkId].LinkUser].CalleeNumber := Trunk[TrunkId].CalleeNumber;
                User[Trunk[TrunkId].LinkUser].CallerSubAddr := Trunk[TrunkId].CallerSubAddr;
                User[Trunk[TrunkId].LinkUser].CallerNumber := Trunk[TrunkId].CallerNumber;

                User[Trunk[TrunkId].LinkUser].LinkTrunk := TrunkId;
                DJUser_StartRing(Trunk[TrunkId].LinkUser);
                User[Trunk[TrunkId].LinkUser].FlowStatus := USER_RING;
                DJExg_SetLinkPlayVoiceToTrunk(TrunkId,VocChannel[RINGBACK]);

                tmpws := GetWinSock(Trunk[TrunkId].LinkUser);

                if tmpws.State = 7 then
                  begin
                    tmpws.SendData('PHONE'+ inttostr(trunk[trunkid].Service) + User[Trunk[TrunkId].LinkUser].CallerSubAddr + User[Trunk[TrunkId].LinkUser].CallerNumber);
                  end;

                Trunk[TrunkId].FlowStatus := TRK_SENDPHONECODE;
                Sys.WaitTelCount := Sys.WaitTelCount - 1;
              end;
          end;
        if DJISDN_GetChnState(npcm,nchn) <> CH_CONNECT then
          begin
            SaveHRLog(TrunkId);
            Sys.WaitTelCount := Sys.WaitTelCount - 1;
          end; }
      end;
    TRK_CONWAIT://连接等待
      begin
        tmpint := DJTrk_GetTrunkPlayID(TrunkId);
        if (tmpint >= 0) and DJVoc_CheckVoiceEnd(tmpint) then
          begin
            DJVoc_StopPlayFile(TrunkId);
            DJVoc_PlayFile(TrunkId,'voc\htjs.05');
            Trunk[TrunkId].FlowStatus := TRK_PLAYHOOKON;
            //Sys.WaitTelCount := Sys.WaitTelCount - 1;
          end;
        dtmf := DJTrk_GetLastDtmfCode(TrunkId);
        if dtmf = '1' then
          begin
            DJVoc_StopPlayFile(TrunkId);
            DJVoc_PlayFile(TrunkId,'voc\htjs.03');
            Trunk[TrunkId].FlowStatus := TRK_WAIT;
          end;
        if DJISDN_GetChnState(npcm,nchn) <> CH_CONNECT then
          begin
            SaveHRLog(TrunkId);
            //Sys.WaitTelCount := Sys.WaitTelCount - 1;
          end;
      end;
    TRK_PLAYHOOKON://播放挂机音乐
      begin
        tmpint := DJTrk_GetTrunkPlayID(TrunkId);
        if (tmpint >= 0) and DJVoc_CheckVoiceEnd(tmpint) then
          begin
            DJVoc_StopPlayFile(TrunkId);
            Trunk[TrunkId].FlowStatus := TRK_SENDHOOKONSIGN;
          end;
        if DJISDN_GetChnState(npcm,nchn) <> CH_CONNECT then
          begin
            DJVoc_StopPlayFile(TrunkId);
          end;
      end;
    TRK_SENDHOOKONSIGN://发送挂机信号
      begin
        SaveHRLog(TrunkId);
        DJISDN_SetChnState(npcm,nchn,CH_SET_DISCONNECT,0);
        TrunkReset(TrunkId);      
      end;
    TRK_MANYI1://满意度调查1
      begin
        tmpint := DJTrk_GetConnectUserID(trunkId);
        if tmpint >= 0 then
          DJExg_ClearLinkTrunkAndUserByTrunk(trunkId);
        if DJISDN_GetChnState(npcm,nchn) <> CH_CONNECT then
          begin
            SaveHRLog(TrunkId);
            trunk[trunkid].FlowStatus := TRK_SENDHOOKONSIGN;
          end;
        DJVoc_PlayFile(trunkid,'voc\htjs.12');
        trunk[trunkid].FlowStatus := TRK_MANYI2;
        DJTrk_InitDtmfBuf(trunkid);
      end;
    TRK_MANYI2://满意度调查2
      begin
        tmpint := DJTrk_GetTrunkPlayID(trunkId);
        if (tmpint >= 0) and DJVoc_CheckVoiceEnd(tmpint) then
          begin
            DJVoc_StopPlayFile(trunkid);
            DJTrk_InitDtmfBuf(trunkid);
            trunk[trunkid].FlowStatus := TRK_MANYI3;
            trunk[trunkid].Delay := 0;
          end;
        dtmf := DJTrk_GetLastDtmfCode(trunkid);
        if (dtmf = '1') or (dtmf = '2') then
          begin
            Trunk[TrunkId].ManYiDu := dtmf;
            if tmpint >= 0 then
              DJVoc_StopPlayFile(trunkid);

            DJVoc_PlayFile(trunkid,'voc\htjs.14');
            trunk[trunkid].FlowStatus := TRK_PLAYHOOKON;
          end;
        if DJISDN_GetChnState(npcm,nchn) <> CH_CONNECT then
          begin
            if tmpint >= 0 then
              DJVoc_StopPlayFile(trunkid);
            trunk[trunkid].FlowStatus := TRK_SENDHOOKONSIGN;
            SaveHRLog(TrunkId);
          end;
      end;
    TRK_MANYI3://满意度调查3
      begin
        if DJISDN_GetChnState(npcm,nchn) <> CH_CONNECT then
          begin
            trunk[trunkid].FlowStatus := TRK_SENDHOOKONSIGN;
          end;
        dtmf := DJTrk_GetLastDtmfCode(trunkid);
        if (dtmf = '1') or (dtmf = '2') then
          begin
            trunk[trunkid].Delay := 0;
            DJVoc_PlayFile(trunkid,'voc\htjs.14');
            trunk[trunkid].FlowStatus := TRK_PLAYHOOKON;
          end
        else
          begin
            trunk[trunkid].Delay := trunk[trunkid].Delay + 1;
            if trunk[trunkid].Delay = 70 then
              begin
                trunk[trunkid].Delay := 0;
                DJVoc_PlayFile(trunkid,'voc\htjs.05');
                trunk[trunkid].FlowStatus := TRK_PLAYHOOKON;
              end;
          end;
      end;
    TRK_ONHOOK://发送挂机信号
      begin
        DJISDN_SetChnState(npcm,nchn,CH_SET_DISCONNECT,0);
        trunkreset(trunkid);
      end;
    TRK_RING://客户正在振铃
      begin
        if DJISDN_GetChnState(npcm,nchn) = CH_FREE then
          trunk[trunkid].FlowStatus := TRK_FREE;
        if DJISDN_GetCalloutResult(npcm,nchn) = C_USER_OFFHOOK then
          trunk[trunkid].FlowStatus := TRK_UCONNECT;
      end;
    TRK_UCONNECT://
      begin
        if DJISDN_GetChnState(npcm,nchn) <> CH_CONNECT then
          begin
            trunk[trunkid].FlowStatus := TRK_ONHOOK;
            user[trunk[trunkid].LinkUser].FlowStatus := USER_ENDSSESION;
          end;
      end;
    end;
except   
    on E:Exception do
      SaveSysLog('0 TrunkWork '+ IntToStr(Trunk[TrunkId].FlowStatus) +' '+ e.Message);
end;
end;

procedure Tfrm_main.UserWork(UserId: Integer);
var
  npcm,nchn,tmpint: integer;
  tmpstr: string;
begin
try
  npcm := User[UserId].LinkTrunk div 30;
  nchn := User[UserId].LinkTrunk mod 30;


  //0512wan++
  {if (DJUser_CheckHookOFF(UserId) = false) then
    begin
      //UserReset(UserId);
      if (User[UserId].LinkTrunk >= 0) and (Trunk[User[UserId].LinkTrunk].FlowStatus <> TRK_FREE) then
        begin
          DJISDN_SetChnState(npcm,nchn,CH_SET_DISCONNECT,0);
        end;
      Exit;
    end;}
  //0512wan++

  case User[UserId].FlowStatus of
    USER_FREE:
      begin
        if DJUser_CheckHookOFF(UserId) and (DJUser_GetConnectTrunkID(UserId) = -1) then
          begin
            DJExg_SetLinkPlayVoiceToUser(UserId,VocChannel[VOCTONE]);
            User[UserId].FlowStatus := USER_UHOOKOFF;
          end;
      end;
    USER_RING:
      begin
        if DJISDN_GetChnState(npcm,nchn) <> CH_CONNECT then
          begin
            if DJUser_RingDetect(UserId) then
              DJUser_StopRing(UserId);
            User[UserId].FlowStatus := USER_HOOKON;
          end;
        if DJUser_CheckHookOFF(UserId) then
          begin
            User[UserId].HCTime := Now;
            if DJUser_RingDetect(UserId) then
              DJUser_StopRing(UserId);
            User[UserId].FlowStatus := USER_HOOKOFF;
          end;
      end;
    USER_HOOKON:
      begin
        tmpint := DJUser_GetConnectTrunkID(UserId);
        //0509 注释这段
        {if tmpint >= 0 then
          begin
            DJExg_ClearLinkTrunkAndUserByTrunk(User[UserId].LinkTrunk);
            Trunk[tmpint].FlowStatus := TRK_SENDHOOKONSIGN;
          end
        else
          begin
            DJExg_ClearLinkTrunkAndUserByUser(UserId);
          end;}
        //0509 注释这段
          //0509 ++
          if tmpint >= 0 then
            begin
              Trunk[tmpint].FlowStatus := TRK_SENDHOOKONSIGN;
            end;
          DJExg_ClearLinkTrunkAndUserByTrunk(User[UserId].LinkTrunk);
          //0509 ++
          DJExg_SetLinkPlayVoiceToUser(UserId,VocChannel[VOCBUSY]);
          User[UserId].FlowStatus := USER_BUSY;
      end;
    USER_BUSY:
      begin
        if DJUser_CheckHookOFF(UserId) = False then
          begin
            SaveHCLog(UserId);
            UserReset(UserId);
          end;
      end;
    USER_HOOKOFF://摘机
      begin
        //DJUser_DisableDialSound(userid);//禁止在用户通道在摘机时发送拨号音
        //DJUser_EnableDialSound(UserId);//启用
        tmpint := DJUser_GetConnectTrunkID(userid);
        {if (tmpint = -1) and DJUser_RingDetect(userid) = false then
          DJUser_StopRing(userid);}
        if tmpint = -1 then
          begin
            //0508 改
            if DJUser_RingDetect(UserId) then
              DJUser_StopRing(UserId);
            //0508 改
          end;

        if user[userid].LinkTrunk >= 0 then
          begin
            User[UserId].THTime := Now;
            DJUser_InitDialBuf(userid);
            user[userid].FlowStatus := USER_TALKING;
          end
        else
          user[userid].FlowStatus := USER_HOOKON; 
      end;
    USER_DIALING:
      begin
        //
      end;
    USER_UHOOKOFF://用户摘机2
      begin    
        //0429
        if GetWinSock(userid).State <> 7 then
          begin
            //0430 如果端口对应的选择框打勾
            {if TCheckBox(FindComponent('CheckBox'+ IntToStr(userid + 1))).checked then
              begin
                UserReset(UserId);
                TCheckBox(FindComponent('CheckBox'+ IntToStr(userid + 1))).checked := false;
              end;}
          end;
        //0429
        //DJUser_DisableDialSound(UserId);
        DJUser_EnableDialSound(UserId);
        if DJUser_GetDialNum(userid) > 0 then
          begin
            DJExg_ClearLinkPlayVoiceFromUser(userid);
            user[userid].FlowStatus := USER_WAITDIAL;
          end
        else if DJUser_CheckHookOFF(userid) = false then
          begin
            user[userid].FlowStatus := USER_HOOKONING;
          end;
      end;
    USER_WAITDIAL://等待拨号
      begin
        //0429 如果winsock服务器与客户端的sock不能通讯
        if GetWinSock(userid).State <> 7 then
          begin
            //0430 如果端口对应的选择框打勾
            {if TCheckBox(FindComponent('CheckBox'+ IntToStr(userid + 1))).checked then
            //0430
              begin
                UserReset(UserId);  //坐席初始化                                       
                TCheckBox(FindComponent('CheckBox'+ IntToStr(userid + 1))).checked := false;
              end;}                                                          
          end;
        //0429
        tmpstr := DJUser_GetLastDialCode(userid);
        if tmpstr = '#' then
          begin
            //user[userid].DialNum := copy(DJUser_GetDialCode(userid),1,8);//获取外拨的电话号码
            //tmpint := length(user[userid].DialNum);

            tmpstr := DJUser_GetDialCode(userid);//获取外拨的电话号码
            tmpint := length(tmpstr);

            if (tmpint = 9) or (tmpint = 13) or (tmpint = 12) then
              begin
                user[userid].DialNum := copy(tmpstr,1,length(tmpstr)-1);

                user[userid].LinkTrunk := GetFreeTrunk;

                if user[userid].LinkTrunk = -1 then
                  begin
                    user[userid].FlowStatus := USER_ENDDIAL;
                  end
                else
                  begin 
                  if (tmpint = 9) and (copy(user[userid].DialNum,1,1) = '0') then//本地电话 第一位不能是0
                    begin
                      user[userid].FlowStatus := USER_ENDDIAL;
                      exit;
                    end;

                    DJISDN_Callout(user[userid].LinkTrunk div 30,user[userid].LinkTrunk mod 30,pchar(user[userid].DialNum),'',pchar(Sys.PhoneNumber),'');
                    trunk[user[userid].LinkTrunk].LinkUser := userid;
                    trunk[user[userid].LinkTrunk].FlowStatus := TRK_RING;
                    user[userid].FlowStatus := USER_DIAL;
                    DJExg_ClearLinkPlayVoiceFromUser(userid);
                    DJExg_SetLinkTrunkAndUser(user[userid].LinkTrunk,userid);
                  end;
              end;
          end
        else
          begin              
            //showmessage('2');
          end;
          
        if DJUser_CheckHookOFF(userid) = false then
          user[userid].FlowStatus := USER_ENDDIAL;
      end;
    USER_ENDDIAL://等待挂机
      begin
        DJExg_ClearLinkTrunkAndUserByUser(userid);
        DJExg_SetLinkPlayVoiceToUser(userid,VocChannel[VOCBUSY]);
        user[userid].FlowStatus := USER_BUSY;
      end;
    USER_HOOKONING:
      begin
        if DJUser_CheckHookOFF(UserId) = false then
          begin
            SaveHCLog(UserId);
            UserReset(UserId);
          end;
      end;
    USER_DIAL://正在呼出
      begin
        if (DJISDN_GetChnState(npcm,nchn) = CH_FREE) and DJUser_CheckHookOFF(userid) then
          user[userid].FlowStatus := USER_ENDSSESION;

        if DJISDN_GetChnState(npcm,nchn) = CH_CONNECT then
          begin
            user[userid].FlowStatus := USER_UCONNECT;
            DJUser_InitDialBuf(userid);
          end;

        if DJUser_CheckHookOFF(userid) = false then
          begin
            user[userid].FlowStatus := USER_ENDSSESION;
            trunk[user[userid].LinkTrunk].FlowStatus := TRK_ONHOOK;
          end;
      end;
    USER_ENDSSESION://结束会话
      begin
        DJExg_ClearLinkTrunkAndUserByUser(userid);
        DJExg_SetLinkPlayVoiceToUser(userid,VocChannel[VOCBUSY]);
        user[userid].FlowStatus := USER_BUSY;
      end;
    USER_UCONNECT://呼出时 的通话ing
      begin
        if DJUser_CheckHookOFF(userid) = false then
          begin
            user[userid].FlowStatus := USER_ENDSSESION;
            trunk[user[userid].LinkTrunk].FlowStatus := TRK_ONHOOK;
          end;
      end;
    USER_TALKING:
      begin
        if DJUser_CheckHookOFF(UserId) = false then
          begin
            User[UserId].Working := false ;
            if DJTrk_GetTrunkPlayID(User[UserId].LinkTrunk) >= 0 then
              DJVoc_StopPlayFile(User[UserId].LinkTrunk);
            if DJUser_GetPlayChannel(UserId) >= 0 then
              DJUser_StopPlayFile(UserId);

            //结束时间
            Trunk[User[UserId].LinkTrunk].JSTime := Now;

            User[UserId].FlowStatus := USER_BUSY;

            if trunk[user[userid].LinkTrunk].FlowStatus = TRK_TALKING1 then
              begin
                trunk[user[userid].LinkTrunk].FlowStatus := TRK_MANYI1;
              end
            else
              trunk[user[userid].LinkTrunk].FlowStatus := TRK_SENDHOOKONSIGN;
        end;
      end;
    end;
except       
    on E:Exception do
      //SaveSysLog('0 UserWork '+ inttostr(User[UserId].FlowStatus) +' '+ e.Message);
end;
end;

procedure Tfrm_main.UserShow(UserId: Integer);
var
  tmpstr: string;
begin
try
  if (UserId < 0) or (UserId >= Sys.UserNum) then
    Exit;

  if StringGrid2.Cells[4,UserId+1] <> IntToStr(User[UserId].TelCount) then
    StringGrid2.Cells[4,UserId+1] := IntToStr(User[UserId].TelCount);//显通话次数

  //用户流程状态
  case User[UserId].FlowStatus of
    USER_FREE:
      tmpstr := '空闲';
    USER_RING:
      tmpstr := '用户振铃';
    USER_HOOKOFF:
      tmpstr := '用户摘机1';
    USER_HOOKON:
      tmpstr := '用户挂机';
    USER_BUSY:
      tmpstr := '坐席正忙';
    USER_TALKING:
      tmpstr := '通话中';
    USER_WAITHOOKON :
      tmpstr := '等待挂机';
    USER_DIALING:
      tmpstr := '用户拨号';
    USER_HOOKONING:
      tmpstr := '正在挂机';  
    USER_UHOOKOFF:
      tmpstr := '用户摘机2';
    USER_DIAL:
      tmpstr := '正在呼出';
    USER_ENDSSESION:
      tmpstr := '结束会话';
    USER_UCONNECT:
      tmpstr := '通话中';
    USER_WAITDIAL:
      tmpstr := '等待拨号';
    USER_ENDDIAL:
      tmpstr := '等待挂机1';
    else
      tmpstr := '';
    end;

  if tmpstr <> StringGrid2.Cells[1,UserId+1] then
    StringGrid2.Cells[1,UserId+1] := tmpstr;

  if TCheckBox(FindComponent('CheckBox'+ IntToStr(UserId+1))).checked = false then
    begin
      stringgrid2.Cells[1,UserId+1] := '坐席没有连接';
      //User[UserId].FlowStatus := USER_NOCONN;
    end;     
    
  stringgrid2.Cells[2,userid+1] := user[userid].CallerSubAddr + user[userid].CallerNumber;

  //获取坐席端的ip地址
  if Trim(StringGrid2.Cells[5,userid+1]) = '' then
  begin
    if TWinsock(FindComponent('Winsock'+ IntToStr(userid+1))).state = 7 then
      StringGrid2.Cells[5,userid+1] := GetIPD(TWinsock(FindComponent('Winsock'+ IntToStr(userid+1))).RemoteHostIP);
  end;
except   
    on E:Exception do
      SaveSysLog('0 UserShow '+ e.Message);
end;
end;

procedure Tfrm_main.TrunkReset(TrunkId: Integer);
var
  tmpint: Integer;
  vrt: VoiceResourcesType;
begin
  try
  if (TrunkId < 0) or (Trunk[TrunkId].FlowStatus = TRK_FREE) then
    Exit; 

  if DJTrk_GetConnectUserID(TrunkId) >= 0 then//取得用LINK方式联接在本通道上的用户通道号
    begin
      DJExg_ClearLinkTrunkAndUserByTrunk(TrunkId);//根据中继通道号，来断开本中继通道与用户通道之间的双向连通
    end;
  if DJTrk_GetConnectTrunkID(TrunkId) >= 0 then//取得用LINK方式联接在本通道上的中继通道号
    begin
      DJExg_ClearLinkTrunkAndTrunk(TrunkId);//断开两个中继通道之间的双向连通
    end;
  if DJTrk_GetTrunkRecordID(TrunkId) >= 0 then//取得给本通道录音的语音通道号
    begin
      DJVoc_StopRecordFile(TrunkId);//停止文件录音
    end;

  tmpint := DJTrk_GetTrunkPlayID(TrunkId);//取得给本通道放音的语音通道号

  if tmpint >= 0 then//
    begin
      vrt := DJVoc_GetVoiceResourceType(tmpint);
      DJExg_ClearLinkPlayVoiceFromTrunk(TrunkId);//断开中继通道和放音语音通道的连通

      if (vrt = Res_File) or (DJVoc_GetVoiceOperateType(tmpint) = OP_Play) then
        DJVoc_StopPlayFile(TrunkId);//停止文件放音。多次调用本函数没有影响

      DJVoc_VoiceStop(tmpint);
    end;

  DJTrk_InitDtmfBuf(TrunkId);//清空系统的DTMF缓冲区，如果在缓冲区中有DTMF按键的值，将会丢失
  Trunk[TrunkId].FlowStatus := TRK_FREE;//通道状态初始化
  Trunk[TrunkId].Service := 0;
  Trunk[TrunkId].DTMF := '';
  Trunk[TrunkId].LinkUser := -1;

  Trunk[TrunkId].CalleeSubAddr := '';
  Trunk[TrunkId].CalleeNumber := '';
  Trunk[TrunkId].CallerSubAddr := '';
  Trunk[TrunkId].CallerNumber := '';

  Trunk[TrunkId].HRTime := StrToDateTime('00:00:00');
  Trunk[TrunkId].THTime := StrToDateTime('00:00:00');
  Trunk[TrunkId].JSTime := StrToDateTime('00:00:00');
  Trunk[TrunkId].GJTime := StrToDateTime('00:00:00');

  //0428++
  UserReset(Trunk[TrunkId].LinkUser);
  //0428
  except        
    on E:Exception do
      SaveSysLog('0 TrunkReset '+ e.Message);
  end;
end;

function Tfrm_main.GetFreeTrunk: Integer;//
var
  tmpint: integer;
begin
  try
  for tmpint := 0 to Sys.TrunkNum - 1 do
    begin
      if DJISDN_GetChnState(tmpint div 30,tmpint mod 30) = CH_FREE then
        begin
          Result := tmpint;
          Exit;
        end;
    end;
    result := -1;
  except
    result := -1;
  end;
end;

function Tfrm_main.GetFreeUser(Service: Integer): Integer;//获取空闲的坐席id
var
  tmpstr: string;
  tmpint,tmpmax,tmpuser: integer;
  tmpchk: TCheckBox;
begin
  try
  tmpuser := -1;
  tmpmax := 10000;
  case Service of
    1:
      begin
        tmpstr := FWZXZX;//服务咨询
      end;
    2:
      begin
        tmpstr := YWSLZX;//
      end;
    3:
      begin
        //投诉建议单独处理
        tmpstr := TSJYZX;
        if Trim(tmpstr) = '' then
          begin
            Result := -1;
            Exit;
          end;
      end;
    end;

  if Service = 3 then//如果服务类型为3 那么直接进行下面的循环操作
    begin
      tmpstr := ','+ tmpstr +',';
    end
  else
    begin
      if (tmpstr = '') and (GXZX = '') then //如果两个字符串都未空值
        begin
          Result := -1;
          Exit;
        end
        else if (tmpstr = '') and (GXZX <> '') then//如果坐席字符串为空,共享坐席不为空
          begin
            tmpstr := ','+ GXZX +',';
          end
        else if (tmpstr <> '') and (GXZX = '') then//如果坐席字符串不为空,共享坐席为空
          begin
            tmpstr := ','+ tmpstr +',';
          end
        else//如果两个字符串都不为空
          tmpstr := ','+ tmpstr +',' + GXZX +',';
      end;

  for tmpint := 0 to Sys.UserNum - 1 do
    begin
      tmpchk := TCheckBox(FindComponent('CheckBox'+ IntToStr(tmpint + 1)));

      if tmpchk.Checked and (User[tmpint].Working = False) and (User[tmpint].FlowStatus = USER_FREE) and (Pos(','+ IntToStr(tmpint+1) +',',tmpstr) > 0) then
        begin
          if User[tmpint].TelCount < tmpmax then
            begin
              tmpuser := tmpint;
              tmpmax := User[tmpint].TelCount;
            end;
        end;
    end;

    if tmpuser <> -1 then
      begin
        User[tmpuser].Working := True;
      end;

    //User[tmpuser].Working := True;
    Result := tmpuser;
  except
    Result := -1;
  end;
end;

function Tfrm_main.GetWinSock(TrunkId: Integer): TWinsock;
begin
  try
    Result := TWinsock(FindComponent('Winsock'+ IntToStr(TrunkId+1)));
  except
    Result := nil;  
  end
end;

procedure Tfrm_main.UserReset(UserId: integer);
var
  tmpint: integer;
begin
  try
  if UserId >= 0 then
    begin
    
      DJUser_SetPowerOFF(Userid);
      DJUser_SetPowerON(userid);
      
      if DJUser_RingDetect(UserId) then
        DJUser_StopRing(UserId);
      tmpint := DJUser_GetPlayChannel(UserId);
      if tmpint >= 0 then
        begin
          DJExg_ClearLinkPlayVoiceFromUser(UserId);
          DJUser_StopPlayFile(UserId);
        end;
      tmpint := DJUser_GetRecordChannel(UserId);
      if tmpint >= 0 then
          DJUser_StopRecordFile(UserId);
      tmpint := DJUser_GetConnectTrunkID(UserId);
      if tmpint <> -1 then
          DJExg_ClearLinkTrunkAndUserByUser(UserId);
      tmpint := DJUser_GetConnectUserID(UserId);
      if tmpint <> -1 then
        DJExg_ClearLinkUserAndUser(UserId);
      tmpint := DJUser_GetListenUserID(UserId);
      if tmpint <> -1 then
        DJExg_ClearListenUserFromUser(UserId);

      DJUser_InitDialBuf(UserId);
      User[UserId].LinkTrunk := -1;
      User[UserId].FlowStatus := USER_FREE;
      User[UserId].Working := false;
      User[UserId].CalleeSubAddr := '';
      User[UserId].CalleeNumber := '';
      User[UserId].CallerSubAddr := '';
      User[UserId].CallerNumber := '';

      User[UserId].HCTime := StrToDateTime('00:00:00');
      User[UserId].THTime := StrToDateTime('00:00:00');
      User[UserId].JSTime := StrToDateTime('00:00:00');
      User[UserId].GJTime := StrToDateTime('00:00:00');

    end;
  except  
    on E:Exception do
      SaveSysLog('0 UserReset '+ e.Message);
  end;
end;

procedure Tfrm_main.BtnUtilitiesClick(Sender: TObject);
begin
  frmlogin := Tfrmlogin.Create(nil);
  frmlogin.ShowModal;
end;

procedure Tfrm_main.BtnSecurityClick(Sender: TObject);
begin
  BtnUtilities.Enabled := true;
  BtnSecurity.Enabled := false;
  ZxStatusClose;
end;

procedure Tfrm_main.BtnExitClick(Sender: TObject);
begin
  frmlogout := Tfrmlogout.Create(nil);
  frmlogout.ShowModal;
end;

function Tfrm_main.GetZXCodeVoc(UserId: Integer): String;//获取坐席号的语音文件
begin
  case UserId of
    0:
      Result := 'd0,d1,d6';
    1:
      Result := 'd0,d2,d6';
    2:
      Result := 'd0,d3,d6';
    3:
      Result := 'd0,d4,d6';
    4:
      Result := 'd0,d5,d6';
    5:
      Result := 'd0,d6,d6';
    6:
      Result := 'd0,d7,d6';
    7:
      Result := 'd0,d8,d6';
    8:
      Result := 'd0,d9,d6';
    9:
      Result := 'd1,d0,d6';
    10:
      Result := 'd1,d1,d6';
    11:
      Result := 'd1,d2,d6';
    12:
      Result := 'd1,d3,d6';
    13:
      Result := 'd1,d4,d6';
    14:
      Result := 'd1,d5,d6';
    15:
      Result := 'd1,d6,d6';
    16:
      Result := 'd1,d7,d6';
    17:
      Result := 'd1,d8,d6';
    18:
      Result := 'd1,d9,d6';
    19:
      Result := 'd2,d0,d6';
    20:
      Result := 'd2,d1,d6';
    21:
      Result := 'd2,d2,d6';
    22:
      Result := 'd2,d3,d6';
    23:
      Result := 'd2,d4,d6';
    24:
      Result := 'd2,d5,d6';
    25:
      Result := 'd2,d6,d6';
    26:
      Result := 'd2,d7,d6';
    27:
      Result := 'd2,d8,d6';
    28:
      Result := 'd2,d9,d6';
    29:
      Result := 'd3,d0,d6';
    30:
      Result := 'd3,d1,d6';
    31:
      Result := 'd3,d2,d6';
    32:
      Result := 'd3,d3,d6';
    33:
      Result := 'd3,d4,d6';
    34:
      Result := 'd3,d5,d6';
    35:
      Result := 'd3,d6,d6';
    36:
      Result := 'd3,d7,d6';
    37:
      Result := 'd3,d8,d6';
    38:
      Result := 'd3,d9,d6';
    39:
      Result := 'd4,d0,d6';
    40:
      Result := 'd4,d1,d6';
    41:
      Result := 'd4,d2,d6';
    42:
      Result := 'd4,d3,d6';
    43:
      Result := 'd4,d4,d6';
    44:
      Result := 'd4,d5,d6';
    45:
      Result := 'd4,d6,d6';
    46:
      Result := 'd4,d7,d6';
    47:
      Result := 'd4,d8,d6';
    48:
      Result := 'd4,d9,d6';
    49:
      Result := 'd5,d0,d6';
    50:
      Result := 'd5,d1,d6';
    51:
      Result := 'd5,d2,d6';
    52:
      Result := 'd5,d3,d6';
    53:
      Result := 'd5,d4,d6';
    54:
      Result := 'd5,d5,d6';
    55:
      Result := 'd5,d6,d6';
    56:
      Result := 'd5,d7,d6';
    57:
      Result := 'd5,d8,d6';
    58:
      Result := 'd5,d9,d6';
    59:
      Result := 'd6,d0,d6';
    60:
      Result := 'd6,d1,d6';
    61:
      Result := 'd6,d2,d6';
    62:
      Result := 'd6,d3,d6';
    63:
      Result := 'd6,d4,d6';
    end;
end;

procedure Tfrm_main.WinsockWaitClose(Sender: TObject);
begin
  try
    WinsockWait.Close;
    WinsockWait.Bind(wswait);
    WinsockWait.Listen;
  except             
    on E:Exception do
      SaveSysLog('0 WinsockWaitClose '+ e.Message);
  end;
end;

procedure Tfrm_main.WinsockWaitConnectionRequest(ASender: TObject;
  requestID: Integer);
begin
  if WinsockWait.State <> 0 then
    WinsockWait.Close;
  WinsockWait.Accept(requestID);
end;

procedure Tfrm_main.WinsockControlConnectionRequest(ASender: TObject;
  requestID: Integer);
begin
  if WinsockControl.State <> 0 then
    WinsockControl.Close;
  WinsockControl.Accept(requestID);
end;

procedure Tfrm_main.FormCloseQuery(Sender: TObject; var CanClose: Boolean);
begin 
  frmlogout := Tfrmlogout.Create(nil);
  frmlogout.ShowModal;
  if frmlogout.ModalResult = mrCancel then
    CanClose := False;
end;

procedure Tfrm_main.BtnFinishClick(Sender: TObject);
begin
  //Winsock5.SendData('PHONE1234567');
  //showmessage(winsock5.RemoteHostIP)
  //if winsock5.State = 7 then
  //showmessage(TWinsock(FindComponent('Winsock'+ IntToStr(5))).RemoteHostIP);
  //ShowMessage(FormatDateTime('yyyy-mm-dd',Now));
  SaveTelStat;
end;

procedure Tfrm_main.StartRecord(TrunkId: integer;UserId: integer);//开始录音
var tmpstr: string;
begin
  try
    tmpstr := Sys.RecordSavePath +'\'+ FormatDateTime('yyyymmdd',Date);
    //每天都创建一个新的文件
    CreateDir(tmpstr);
    //DJUser_RecordFileNew(userid,pchar(tmpstr+'\'+ inttostr(Trunk[TrunkId].Service) +'_'+ IntToStr(UserId) +'_'+ DateTimeToStr(now) +'_'+ Trunk[TrunkId].CallerSubAddr + Trunk[TrunkId].CallerNumber +'.pcm'),0,20000000);
    //DJUser_RecordFileNew(userid,pchar(tmpstr+'\'+ inttostr(Trunk[TrunkId].Service) +'_'+ IntToStr(UserId+1) +'_'+ FormatDateTime('yyyymmddhhmmss',now) +'_'+ Trunk[TrunkId].CallerSubAddr + Trunk[TrunkId].CallerNumber +'.pcm'),0,20000000);
    DJVoc_RecordFileNew(TrunkId,pchar(tmpstr+'\'+ inttostr(Trunk[TrunkId].Service) +'_'+ IntToStr(UserId+1) +'_'+ FormatDateTime('yyyymmddhhmmss',now) +'_'+ Trunk[TrunkId].CallerSubAddr + Trunk[TrunkId].CallerNumber +'.pcm'),0,20000000);
  except
    on E:Exception do
      SaveSysLog('0 StartRecord '+ e.Message);
  end;
end;

procedure Tfrm_main.EndRecord(UserId: integer);//结束录音
begin
  try
  if DJUser_GetRecordChannel(UserId) >= 0 then
    DJUser_StopRecordFile(Userid);
  except
    on E:Exception do
      SaveSysLog('0 EndRecord '+ e.Message);
  end;
end;

procedure Tfrm_main.SaveSysLog(Content: string);//写系统日志
var
  f: TextFile;
  sl: TStringList;
  filepath: string;
begin
  //
  try
  filepath := Sys.SaveLogPath +'\'+ FormatDateTime('yyyymmdd',Date) +'.log';
  if FileExists(filepath) = false then
  begin
    AssignFile(f,filepath);
    Rewrite(f);
    Writeln(f,TimeToStr(Now)+' '+Content);
    CloseFile(f);
  end
  else
  begin
    sl := TStringList.Create;
    sl.LoadFromFile(filepath);
    //sl.Append(TimeToStr(Now)+' '+Content);
    sl.Add(TimeToStr(Now)+' '+Content);
    sl.SaveToFile(filepath);
    sl.Free;
  end;
  except
  end;
end;

procedure Tfrm_main.SaveHRLog(TrunkId: Integer);//保存呼入日志
var
  f: TextFile;
  sl: TStringList;
  filepath,tmpstr,tmpstr2,tmpstr3,tmpstr4: string;
begin
  try
  //
  Trunk[TrunkId].GJTime := Now;
  //通话时长
  tmpstr3 := GetDateDiff2(trunk[trunkid].THTime,trunk[trunkid].JSTime);
  //总时长
  tmpstr4 := GetDateDiff2(trunk[trunkid].HRTime,trunk[trunkid].GJTime);
  
  tmpstr2 := IntToStr(Trunk[TrunkId].Service) +'   '+ inttostr(trunkid) +'          '+ Trunk[TrunkId].CallerSubAddr + Trunk[TrunkId].CallerNumber +'        '+ Trunk[TrunkId].CalleeSubAddr + Trunk[TrunkId].CallerSubAddr +'        '+ trunk[Trunkid].ManYiDu +'        '+ DateTimeToStr(trunk[trunkid].HRTime) +'        '+ DateTimeToStr(trunk[trunkid].THTime) +'         '+ DateTimeToStr(trunk[trunkid].JSTime) +'         '+ DateTimeToStr(trunk[trunkid].GJTime) +'        '+ tmpstr3 +'       '+tmpstr4+'    '+ inttostr(trunk[trunkid].LinkUser);
  filepath := Sys.SaveHRPath +'\'+ FormatDateTime('yyyymmdd',Date) +'.log';
  if FileExists(filepath) = false then
  begin
    tmpstr := '服务类型 中继通道号   主叫电话         被叫电话     满意度        呼入时间            通话时间            结束时间            挂机时间     通话时长  总时长  坐席通道号';
    AssignFile(f,filepath);
    Rewrite(f);
    Writeln(f,tmpstr);
    Writeln(f,tmpstr2);
    CloseFile(f);
  end
  else
  begin
    sl := TStringList.Create;
    sl.LoadFromFile(filepath);
    //sl.Append(TimeToStr(Now)+' '+Content);
    sl.Add(tmpstr2);
    sl.SaveToFile(filepath);
    sl.Free;
  end;

  ADOQuery1.Close;
  ADOQuery1.SQL.Clear;
  ADOQuery1.SQL.Add('insert into HR_Phone (TEL_NO,TEL_START,TEL_OFF,TEL_TIME,TEL_ZX,TEL_FWLX,TEL_TRK,TEL_NOEE,TEL_START0,TEL_TIME1,TEL_OFF1,TEL_MYD,TEL_BMY)');
  ADOQuery1.SQL.Add('values (:TEL_NO,:TEL_START,:TEL_OFF,:TEL_TIME,:TEL_ZX,:TEL_FWLX,:TEL_TRK,:TEL_NOEE,:TEL_START0,:TEL_TIME1,:TEL_OFF1,:TEL_MYD,:TEL_BMY)');
  //主叫号码
  ADOQuery1.Parameters.ParamByName('TEL_NO').Value := Trunk[TrunkId].CallerSubAddr + Trunk[TrunkId].CallerNumber;
  //开始时间
  ADOQuery1.Parameters.ParamByName('TEL_START').Value := DateTimeToStr(trunk[trunkid].HRTime);
  //结束时间
  ADOQuery1.Parameters.ParamByName('TEL_OFF').Value := DateTimeToStr(trunk[trunkid].GJTime);
  ADOQuery1.Parameters.ParamByName('TEL_TIME').Value := '';
  ADOQuery1.Parameters.ParamByName('TEL_ZX').Value := inttostr(trunk[trunkid].LinkUser);
  ADOQuery1.Parameters.ParamByName('TEL_FWLX').Value := IntToStr(Trunk[TrunkId].Service);
  ADOQuery1.Parameters.ParamByName('TEL_TRK').Value := inttostr(trunkid);
  ADOQuery1.Parameters.ParamByName('TEL_NOEE').Value := Trunk[TrunkId].CalleeSubAddr + Trunk[TrunkId].CallerSubAddr;
  ADOQuery1.Parameters.ParamByName('TEL_START0').Value := DateTimeToStr(trunk[trunkid].THTime);
  ADOQuery1.Parameters.ParamByName('TEL_TIME1').Value := '';
  ADOQuery1.Parameters.ParamByName('TEL_OFF1').Value := DateTimeToStr(trunk[trunkid].JSTime);
  ADOQuery1.Parameters.ParamByName('TEL_MYD').Value := '0';
  ADOQuery1.Parameters.ParamByName('TEL_BMY').Value := '0';
  ADOQuery1.ExecSQL;
  ADOQuery1.Close;
  except
    on E:Exception do
      SaveSysLog('0 SaveHRLog '+ e.Message);
  end;
end;


procedure Tfrm_main.SaveHCLog(UserId: Integer);//保存呼入日志
var
  f: TextFile;
  sl: TStringList;
  filepath,tmpstr,tmpstr2,tmpstr3,tmpstr4: string;
begin
  //
  if userid < 0 then exit;
  try
  User[UserId].GJTime := Now;
  //通话时长
  tmpstr3 := GetDateDiff2(User[UserId].THTime,User[UserId].JSTime);
  //总时长
  tmpstr4 := GetDateDiff2(User[UserId].HCTime,User[UserId].GJTime);
  tmpstr2 := inttostr(user[userid].LinkTrunk) +'  '+ user[userid].DialNum +'   '+ DateTimeToStr(user[userid].HCTime) +'  '+ DateTimeToStr(user[userid].THTime) +'  '+ DateTimeToStr(user[userid].JSTime) +'  '+ DateTimeToStr(user[userid].JSTime) +'  '+ tmpstr3 +'  '+ tmpstr4 +'  '+ inttostr(userid);
  filepath := Sys.SaveHCPath +'\'+ FormatDateTime('yyyymmdd',Date) +'.log';
  if FileExists(filepath) = false then
  begin
    tmpstr := '中继通道号   主叫电话         呼出时间            通话时间            结束时间            挂机时间     通话时长  总时长  坐席通道号';
    AssignFile(f,filepath);
    Rewrite(f);
    Writeln(f,tmpstr);
    Writeln(f,tmpstr2);
    CloseFile(f);
  end
  else
  begin
    sl := TStringList.Create;
    sl.LoadFromFile(filepath);
    //sl.Append(TimeToStr(Now)+' '+Content);
    sl.Add(tmpstr2);
    sl.SaveToFile(filepath);
    sl.Free;
  end;
  
  ADOQuery1.Close;
  ADOQuery1.SQL.Clear;
  ADOQuery1.SQL.Add('insert into hc_Phone (TEL_NO,TEL_START,TEL_OFF,TEL_TIME,TEL_ZX,TEL_FWLX,TEL_TRK,TEL_NOEE,TEL_START0,TEL_TIME1)');
  ADOQuery1.SQL.Add('values (:TEL_NO,:TEL_START,:TEL_OFF,:TEL_TIME,:TEL_ZX,:TEL_FWLX,:TEL_TRK,:TEL_NOEE,:TEL_START0,:TEL_TIME1)');
  //呼出的号码
  ADOQuery1.Parameters.ParamByName('TEL_NO').Value := user[userid].DialNum;
  ADOQuery1.Parameters.ParamByName('TEL_START').Value := DateTimeToStr(User[UserId].HCTime);
  ADOQuery1.Parameters.ParamByName('TEL_OFF').Value := DateTimeToStr(User[UserId].GJTime);
  ADOQuery1.Parameters.ParamByName('TEL_TIME').Value := '';
  ADOQuery1.Parameters.ParamByName('TEL_ZX').Value := inttostr(userid);
  ADOQuery1.Parameters.ParamByName('TEL_FWLX').Value := '0';
  ADOQuery1.Parameters.ParamByName('TEL_TRK').Value := inttostr(user[userid].LinkTrunk);
  ADOQuery1.Parameters.ParamByName('TEL_NOEE').Value := DateTimeToStr(User[UserId].THTime);
  ADOQuery1.Parameters.ParamByName('TEL_START0').Value := DateTimeToStr(User[UserId].JSTime);
  ADOQuery1.Parameters.ParamByName('TEL_TIME1').Value := '';
  ADOQuery1.ExecSQL;
  ADOQuery1.Close;
  except
    on E:Exception do
      SaveSysLog('0 SaveHCLog '+ e.Message);
  end;
end;



procedure Tfrm_main.Winsock11DataArrival(ASender: TObject;
  bytesTotal: Integer);
begin
  WinsockDA(11);
end;


procedure Tfrm_main.WinsockDA(index: integer);
var
  tmpws: TWinsock;
  tmpstr: OleVariant;
begin
  try
    tmpws := TWinsock(FindComponent('Winsock'+ IntToStr(index)));
    tmpws.GetData(tmpstr);
    if copy(Trim(tmpstr),1,5) = 'PHONE' then
      begin
        if User[index-1].FlowStatus = USER_UHOOKOFF then
          begin
            User[index-1].CallerNumber := Trim(tmpstr);
            User[index-1].FlowStatus := USER_WAITDIAL;
          end;
      end;
  except    
    on E:Exception do
      SaveSysLog('0 WinsockDA '+ inttostr(index) +' '+ e.Message);
  end;
end;

procedure Tfrm_main.Winsock1DataArrival(ASender: TObject;
  bytesTotal: Integer);
begin
  WinsockDA(1);
end;

procedure Tfrm_main.Winsock2DataArrival(ASender: TObject;
  bytesTotal: Integer);
begin
  WinsockDA(2);
end;

procedure Tfrm_main.Winsock3DataArrival(ASender: TObject;
  bytesTotal: Integer);
begin
  WinsockDA(3);
end;

procedure Tfrm_main.Winsock4DataArrival(ASender: TObject;
  bytesTotal: Integer);
begin
  WinsockDA(4);
end;

procedure Tfrm_main.Winsock5DataArrival(ASender: TObject;
  bytesTotal: Integer);
begin
  WinsockDA(5);
end;

procedure Tfrm_main.Winsock6DataArrival(ASender: TObject;
  bytesTotal: Integer);
begin
  WinsockDA(6);
end;

procedure Tfrm_main.Winsock7DataArrival(ASender: TObject;
  bytesTotal: Integer);
begin
  WinsockDA(7);
end;

procedure Tfrm_main.Winsock8DataArrival(ASender: TObject;
  bytesTotal: Integer);
begin
  WinsockDA(8);
end;

procedure Tfrm_main.Winsock9DataArrival(ASender: TObject;
  bytesTotal: Integer);
begin
  WinsockDA(9);
end;

procedure Tfrm_main.Winsock10DataArrival(ASender: TObject;
  bytesTotal: Integer);
begin
  WinsockDA(10);
end;

procedure Tfrm_main.Winsock12DataArrival(ASender: TObject;
  bytesTotal: Integer);
begin
  WinsockDA(12);
end;

procedure Tfrm_main.Winsock13DataArrival(ASender: TObject;
  bytesTotal: Integer);
begin
  WinsockDA(13);
end;

procedure Tfrm_main.Winsock14DataArrival(ASender: TObject;
  bytesTotal: Integer);
begin
  WinsockDA(14);
end;

procedure Tfrm_main.Winsock15DataArrival(ASender: TObject;
  bytesTotal: Integer);
begin
  WinsockDA(15);
end;

procedure Tfrm_main.Winsock16DataArrival(ASender: TObject;
  bytesTotal: Integer);
begin
  WinsockDA(16);
end;

procedure Tfrm_main.Winsock17DataArrival(ASender: TObject;
  bytesTotal: Integer);
begin
  WinsockDA(17);
end;

procedure Tfrm_main.Winsock18DataArrival(ASender: TObject;
  bytesTotal: Integer);
begin
  WinsockDA(18);
end;

procedure Tfrm_main.Winsock19DataArrival(ASender: TObject;
  bytesTotal: Integer);
begin
  WinsockDA(19);
end;

procedure Tfrm_main.Winsock20DataArrival(ASender: TObject;
  bytesTotal: Integer);
begin
  WinsockDA(20);
end;

procedure Tfrm_main.Winsock21DataArrival(ASender: TObject;
  bytesTotal: Integer);
begin
  WinsockDA(21);
end;

procedure Tfrm_main.Winsock22DataArrival(ASender: TObject;
  bytesTotal: Integer);
begin
  WinsockDA(22);
end;

procedure Tfrm_main.Winsock23DataArrival(ASender: TObject;
  bytesTotal: Integer);
begin
  WinsockDA(23);
end;

procedure Tfrm_main.Winsock24DataArrival(ASender: TObject;
  bytesTotal: Integer);
begin
  WinsockDA(24);
end;

procedure Tfrm_main.Winsock25DataArrival(ASender: TObject;
  bytesTotal: Integer);
begin
  WinsockDA(25);
end;

procedure Tfrm_main.Winsock26DataArrival(ASender: TObject;
  bytesTotal: Integer);
begin
  WinsockDA(26);
end;

procedure Tfrm_main.Winsock27DataArrival(ASender: TObject;
  bytesTotal: Integer);
begin
  WinsockDA(27);
end;

procedure Tfrm_main.Winsock28DataArrival(ASender: TObject;
  bytesTotal: Integer);
begin
  WinsockDA(28);
end;

procedure Tfrm_main.Winsock29DataArrival(ASender: TObject;
  bytesTotal: Integer);
begin
  WinsockDA(29);
end;

procedure Tfrm_main.Winsock30DataArrival(ASender: TObject;
  bytesTotal: Integer);
begin
  WinsockDA(30);
end;

procedure Tfrm_main.Winsock31DataArrival(ASender: TObject;
  bytesTotal: Integer);
begin
  WinsockDA(31);
end;

procedure Tfrm_main.Winsock32DataArrival(ASender: TObject;
  bytesTotal: Integer);
begin
  WinsockDA(32);
end;

procedure Tfrm_main.SaveTelStat();//保存坐席每天的电话记录
var
  tmpint1: integer;
begin
  try   

  //ADOQuery1.Close;
  //ADOQuery1.SQL.Clear;
  //ADOQuery1.SQL.Add('insert into telstat (cdate,a1,a2,a3,a4,a5,a6,a7,a8,a9,a10,a11,a12,a13,a14,a15,a16,a17,a18,a19,a20,a21,a22,a23,a24,a25,a26,a27,a28,a29,a30,a31,a32)');
  //ADOQuery1.SQL.Add('values (:cdate,:a1,:a2,:a3,:a4,:a5,:a6,:a7,:a8,:a9,:a10,:a11,:a12,:a13,:a14,:a15,:a16,:a17,:a18,:a19,:a20,:a21,:a22,:a23,:a24,:a25,:a26,:a27,:a28,:a29,:a30,:a31,:a32)');
  //呼出的号码

  //ADOQuery1.Parameters.ParamByName('cdate').Value := FormatDateTime('yyyy-mm-dd',Now);

    //for tmpint1 := 0 to Sys.UserNum - 1 do
    for tmpint1 := 0 to Sys.UserNum - 1 do
        begin

          //ADOQuery1.Parameters.ParamByName('a'+IntToStr(tmpint1+1)).Value := User[tmpint1].TelCount;

          User[tmpint1].TelCount := 0;
          StringGrid2.Cells[5,tmpint1+1] := '';
        end;


  //ADOQuery1.ExecSQL;
  //ADOQuery1.Close;
  except
    on E:Exception do
      SaveSysLog('0 SaveTelStat '+ e.Message);
  end;
end;

procedure Tfrm_main.BtnViewClick(Sender: TObject);
begin
  Frm_Info := TFrm_Info.Create(nil);
  Frm_Info.ShowModal;
end;

procedure Tfrm_main.Timer2Timer(Sender: TObject);
var
  tmpif: TIniFile;
begin
  try
    tmpif := TIniFile.Create(ExtractFilePath(Application.ExeName) + IniFile);
    tmpif.WriteString('cs','ut','123');
    tmpif.Free;
    //SaveSysLog('1 ini初始化成功');

  except
    //SaveSysLog('0 ini初始化失败');

  end;
end;

procedure Tfrm_main.Winsock33Close(Sender: TObject);
begin
  WinSockWork(33);
end;

procedure Tfrm_main.Winsock34Close(Sender: TObject);
begin
  WinSockWork(34);
end;

procedure Tfrm_main.Winsock35Close(Sender: TObject);
begin
  WinSockWork(35);
end;

procedure Tfrm_main.Winsock36Close(Sender: TObject);
begin
  WinSockWork(36);
end;

procedure Tfrm_main.Winsock37Close(Sender: TObject);
begin
  WinSockWork(37);
end;

procedure Tfrm_main.Winsock38Close(Sender: TObject);
begin
  WinSockWork(38);
end;

procedure Tfrm_main.Winsock39Close(Sender: TObject);
begin
  WinSockWork(39);
end;

procedure Tfrm_main.Winsock40Close(Sender: TObject);
begin
  WinSockWork(40);
end;

procedure Tfrm_main.Winsock41Close(Sender: TObject);
begin
  WinSockWork(41);
end;

procedure Tfrm_main.Winsock42Close(Sender: TObject);
begin
  WinSockWork(42);
end;

procedure Tfrm_main.Winsock43Close(Sender: TObject);
begin
  WinSockWork(43);
end;

procedure Tfrm_main.Winsock44Close(Sender: TObject);
begin
  WinSockWork(44);
end;

procedure Tfrm_main.Winsock45Close(Sender: TObject);
begin
  WinSockWork(45);
end;

procedure Tfrm_main.Winsock46Close(Sender: TObject);
begin
  WinSockWork(46);
end;

procedure Tfrm_main.Winsock47Close(Sender: TObject);
begin
  WinSockWork(47);
end;

procedure Tfrm_main.Winsock48Close(Sender: TObject);
begin
  WinSockWork(48);
end;

procedure Tfrm_main.Winsock49Close(Sender: TObject);
begin
  WinSockWork(49);
end;

procedure Tfrm_main.Winsock50Close(Sender: TObject);
begin
  WinSockWork(50);
end;

procedure Tfrm_main.Winsock51Close(Sender: TObject);
begin
  WinSockWork(51);
end;

procedure Tfrm_main.Winsock52Close(Sender: TObject);
begin
  WinSockWork(52);
end;

procedure Tfrm_main.Winsock53Close(Sender: TObject);
begin
  WinSockWork(53);
end;

procedure Tfrm_main.Winsock54Close(Sender: TObject);
begin
  WinSockWork(54);
end;

procedure Tfrm_main.Winsock55Close(Sender: TObject);
begin
  WinSockWork(55);
end;

procedure Tfrm_main.Winsock56Close(Sender: TObject);
begin
  WinSockWork(56);
end;

procedure Tfrm_main.Winsock57Close(Sender: TObject);
begin
  WinSockWork(57);
end;

procedure Tfrm_main.Winsock58Close(Sender: TObject);
begin
  WinSockWork(58);
end;

procedure Tfrm_main.Winsock59Close(Sender: TObject);
begin
  WinSockWork(59);
end;

procedure Tfrm_main.Winsock60Close(Sender: TObject);
begin
  WinSockWork(60);
end;

procedure Tfrm_main.Winsock61Close(Sender: TObject);
begin
  WinSockWork(61);
end;

procedure Tfrm_main.Winsock62Close(Sender: TObject);
begin
  WinSockWork(62);
end;

procedure Tfrm_main.Winsock63Close(Sender: TObject);
begin
  WinSockWork(63);
end;

procedure Tfrm_main.Winsock64Close(Sender: TObject);
begin
  WinSockWork(64);
end;

procedure Tfrm_main.Winsock33ConnectionRequest(ASender: TObject;
  requestID: Integer);
begin
  WinSockCR(33,requestID);
end;

procedure Tfrm_main.Winsock34ConnectionRequest(ASender: TObject;
  requestID: Integer);
begin
  WinSockCR(34,requestID);
end;

procedure Tfrm_main.Winsock35ConnectionRequest(ASender: TObject;
  requestID: Integer);
begin
  WinSockCR(35,requestID);
end;

procedure Tfrm_main.Winsock36ConnectionRequest(ASender: TObject;
  requestID: Integer);
begin
  WinSockCR(36,requestID);
end;

procedure Tfrm_main.Winsock37ConnectionRequest(ASender: TObject;
  requestID: Integer);
begin
  WinSockCR(37,requestID);
end;

procedure Tfrm_main.Winsock38ConnectionRequest(ASender: TObject;
  requestID: Integer);
begin
  WinSockCR(38,requestID);
end;

procedure Tfrm_main.Winsock39ConnectionRequest(ASender: TObject;
  requestID: Integer);
begin
  WinSockCR(39,requestID);
end;

procedure Tfrm_main.Winsock40ConnectionRequest(ASender: TObject;
  requestID: Integer);
begin
  WinSockCR(40,requestID);
end;

procedure Tfrm_main.Winsock41ConnectionRequest(ASender: TObject;
  requestID: Integer);
begin
  WinSockCR(41,requestID);
end;

procedure Tfrm_main.Winsock42ConnectionRequest(ASender: TObject;
  requestID: Integer);
begin
  WinSockCR(42,requestID);
end;

procedure Tfrm_main.Winsock43ConnectionRequest(ASender: TObject;
  requestID: Integer);
begin
  WinSockCR(43,requestID);
end;

procedure Tfrm_main.Winsock44ConnectionRequest(ASender: TObject;
  requestID: Integer);
begin
  WinSockCR(44,requestID);
end;

procedure Tfrm_main.Winsock45ConnectionRequest(ASender: TObject;
  requestID: Integer);
begin
  WinSockCR(45,requestID);
end;

procedure Tfrm_main.Winsock46ConnectionRequest(ASender: TObject;
  requestID: Integer);
begin
  WinSockCR(46,requestID);
end;

procedure Tfrm_main.Winsock47ConnectionRequest(ASender: TObject;
  requestID: Integer);
begin
  WinSockCR(47,requestID);
end;

procedure Tfrm_main.Winsock48ConnectionRequest(ASender: TObject;
  requestID: Integer);
begin
  WinSockCR(48,requestID);
end;

procedure Tfrm_main.Winsock49ConnectionRequest(ASender: TObject;
  requestID: Integer);
begin
  WinSockCR(49,requestID);
end;

procedure Tfrm_main.Winsock50ConnectionRequest(ASender: TObject;
  requestID: Integer);
begin
  WinSockCR(50,requestID);
end;

procedure Tfrm_main.Winsock51ConnectionRequest(ASender: TObject;
  requestID: Integer);
begin
  WinSockCR(51,requestID);
end;

procedure Tfrm_main.Winsock52ConnectionRequest(ASender: TObject;
  requestID: Integer);
begin
  WinSockCR(52,requestID);
end;

procedure Tfrm_main.Winsock53ConnectionRequest(ASender: TObject;
  requestID: Integer);
begin
  WinSockCR(53,requestID);
end;

procedure Tfrm_main.Winsock54ConnectionRequest(ASender: TObject;
  requestID: Integer);
begin
  WinSockCR(54,requestID);
end;

procedure Tfrm_main.Winsock55ConnectionRequest(ASender: TObject;
  requestID: Integer);
begin
  WinSockCR(55,requestID);
end;

procedure Tfrm_main.Winsock56ConnectionRequest(ASender: TObject;
  requestID: Integer);
begin
  WinSockCR(56,requestID);
end;

procedure Tfrm_main.Winsock57ConnectionRequest(ASender: TObject;
  requestID: Integer);
begin
  WinSockCR(57,requestID);
end;

procedure Tfrm_main.Winsock58ConnectionRequest(ASender: TObject;
  requestID: Integer);
begin
  WinSockCR(58,requestID);
end;

procedure Tfrm_main.Winsock59ConnectionRequest(ASender: TObject;
  requestID: Integer);
begin
  WinSockCR(59,requestID);
end;

procedure Tfrm_main.Winsock60ConnectionRequest(ASender: TObject;
  requestID: Integer);
begin
  WinSockCR(60,requestID);
end;

procedure Tfrm_main.Winsock61ConnectionRequest(ASender: TObject;
  requestID: Integer);
begin
  WinSockCR(61,requestID);
end;

procedure Tfrm_main.Winsock62ConnectionRequest(ASender: TObject;
  requestID: Integer);
begin
  WinSockCR(62,requestID);
end;  

procedure Tfrm_main.Winsock63ConnectionRequest(ASender: TObject;
  requestID: Integer);
begin
  WinSockCR(63,requestID);
end;

procedure Tfrm_main.Winsock64ConnectionRequest(ASender: TObject;
  requestID: Integer);
begin
  WinSockCR(64,requestID);
end;

procedure Tfrm_main.Winsock33DataArrival(ASender: TObject;
  bytesTotal: Integer);
begin
  WinsockDA(33);
end;

procedure Tfrm_main.Winsock34DataArrival(ASender: TObject;
  bytesTotal: Integer);
begin
  WinsockDA(34);
end;

procedure Tfrm_main.Winsock35DataArrival(ASender: TObject;
  bytesTotal: Integer);
begin
  WinsockDA(35);
end;

procedure Tfrm_main.Winsock36DataArrival(ASender: TObject;
  bytesTotal: Integer);
begin
  WinsockDA(36);
end;

procedure Tfrm_main.Winsock37DataArrival(ASender: TObject;
  bytesTotal: Integer);
begin
  WinsockDA(37);
end;

procedure Tfrm_main.Winsock38DataArrival(ASender: TObject;
  bytesTotal: Integer);
begin
  WinsockDA(38);
end;

procedure Tfrm_main.Winsock39DataArrival(ASender: TObject;
  bytesTotal: Integer);
begin
  WinsockDA(39);
end;

procedure Tfrm_main.Winsock40DataArrival(ASender: TObject;
  bytesTotal: Integer);
begin
  WinsockDA(40);
end;

procedure Tfrm_main.Winsock41DataArrival(ASender: TObject;
  bytesTotal: Integer);
begin
  WinsockDA(41);
end;

procedure Tfrm_main.Winsock42DataArrival(ASender: TObject;
  bytesTotal: Integer);
begin
  WinsockDA(42);
end;

procedure Tfrm_main.Winsock43DataArrival(ASender: TObject;
  bytesTotal: Integer);
begin
  WinsockDA(43);
end;

procedure Tfrm_main.Winsock44DataArrival(ASender: TObject;
  bytesTotal: Integer);
begin
  WinsockDA(44);
end;

procedure Tfrm_main.Winsock45DataArrival(ASender: TObject;
  bytesTotal: Integer);
begin
  WinsockDA(45);
end;

procedure Tfrm_main.Winsock46DataArrival(ASender: TObject;
  bytesTotal: Integer);
begin
  WinsockDA(46);
end;

procedure Tfrm_main.Winsock47DataArrival(ASender: TObject;
  bytesTotal: Integer);
begin
  WinsockDA(47);
end;

procedure Tfrm_main.Winsock48DataArrival(ASender: TObject;
  bytesTotal: Integer);
begin
  WinsockDA(48);
end;

procedure Tfrm_main.Winsock49DataArrival(ASender: TObject;
  bytesTotal: Integer);
begin
  WinsockDA(49);
end;

procedure Tfrm_main.Winsock50DataArrival(ASender: TObject;
  bytesTotal: Integer);
begin
  WinsockDA(50);
end;

procedure Tfrm_main.Winsock51DataArrival(ASender: TObject;
  bytesTotal: Integer);
begin
  WinsockDA(51);
end;

procedure Tfrm_main.Winsock52DataArrival(ASender: TObject;
  bytesTotal: Integer);
begin
  WinsockDA(52);
end;

procedure Tfrm_main.Winsock53DataArrival(ASender: TObject;
  bytesTotal: Integer);
begin
  WinsockDA(53);
end;

procedure Tfrm_main.Winsock54DataArrival(ASender: TObject;
  bytesTotal: Integer);
begin
  WinsockDA(54);
end;

procedure Tfrm_main.Winsock55DataArrival(ASender: TObject;
  bytesTotal: Integer);
begin
  WinsockDA(55);
end;

procedure Tfrm_main.Winsock56DataArrival(ASender: TObject;
  bytesTotal: Integer);
begin
  WinsockDA(56);
end;

procedure Tfrm_main.Winsock57DataArrival(ASender: TObject;
  bytesTotal: Integer);
begin
  WinsockDA(57);
end;

procedure Tfrm_main.Winsock58DataArrival(ASender: TObject;
  bytesTotal: Integer);
begin
  WinsockDA(58);
end;

procedure Tfrm_main.Winsock59DataArrival(ASender: TObject;
  bytesTotal: Integer);
begin
  WinsockDA(59);
end;

procedure Tfrm_main.Winsock60DataArrival(ASender: TObject;
  bytesTotal: Integer);
begin
  WinsockDA(60);
end;

procedure Tfrm_main.Winsock61DataArrival(ASender: TObject;
  bytesTotal: Integer);
begin
  WinsockDA(61);
end;

procedure Tfrm_main.Winsock62DataArrival(ASender: TObject;
  bytesTotal: Integer);
begin
  WinsockDA(62);
end;

procedure Tfrm_main.Winsock63DataArrival(ASender: TObject;
  bytesTotal: Integer);
begin
  WinsockDA(63);
end;

procedure Tfrm_main.Winsock64DataArrival(ASender: TObject;
  bytesTotal: Integer);
begin
  WinsockDA(64);
end;

procedure Tfrm_main.CheckBox33Click(Sender: TObject);
begin
  CheckBoxWork(33);
end;

procedure Tfrm_main.CheckBox34Click(Sender: TObject);
begin
  CheckBoxWork(34);
end;

procedure Tfrm_main.CheckBox35Click(Sender: TObject);
begin
  CheckBoxWork(35);
end;

procedure Tfrm_main.CheckBox36Click(Sender: TObject);
begin
  CheckBoxWork(36);
end;

procedure Tfrm_main.CheckBox37Click(Sender: TObject);
begin
  CheckBoxWork(37);
end;

procedure Tfrm_main.CheckBox38Click(Sender: TObject);
begin
  CheckBoxWork(38);
end;

procedure Tfrm_main.CheckBox39Click(Sender: TObject);
begin
  CheckBoxWork(39);
end;

procedure Tfrm_main.CheckBox40Click(Sender: TObject);
begin
  CheckBoxWork(40);
end;

procedure Tfrm_main.CheckBox41Click(Sender: TObject);
begin
  CheckBoxWork(41);
end;

procedure Tfrm_main.CheckBox42Click(Sender: TObject);
begin
  CheckBoxWork(42);
end;

procedure Tfrm_main.CheckBox43Click(Sender: TObject);
begin
  CheckBoxWork(43);
end;

procedure Tfrm_main.CheckBox44Click(Sender: TObject);
begin
  CheckBoxWork(44);
end;

procedure Tfrm_main.CheckBox45Click(Sender: TObject);
begin
  CheckBoxWork(45);
end;

procedure Tfrm_main.CheckBox46Click(Sender: TObject);
begin
  CheckBoxWork(46);
end;

procedure Tfrm_main.CheckBox47Click(Sender: TObject);
begin
  CheckBoxWork(47);
end;

procedure Tfrm_main.CheckBox48Click(Sender: TObject);
begin
  CheckBoxWork(48);
end;

procedure Tfrm_main.CheckBox49Click(Sender: TObject);
begin
  CheckBoxWork(49);
end;

procedure Tfrm_main.CheckBox50Click(Sender: TObject);
begin
  CheckBoxWork(50);
end;

procedure Tfrm_main.CheckBox51Click(Sender: TObject);
begin
  CheckBoxWork(51);
end;

procedure Tfrm_main.CheckBox52Click(Sender: TObject);
begin
  CheckBoxWork(52);
end;

procedure Tfrm_main.CheckBox53Click(Sender: TObject);
begin
  CheckBoxWork(53);
end;

procedure Tfrm_main.CheckBox54Click(Sender: TObject);
begin
  CheckBoxWork(54);
end;

procedure Tfrm_main.CheckBox55Click(Sender: TObject);
begin
  CheckBoxWork(55);
end;

procedure Tfrm_main.CheckBox56Click(Sender: TObject);
begin
  CheckBoxWork(56);
end;

procedure Tfrm_main.CheckBox57Click(Sender: TObject);
begin
  CheckBoxWork(57);
end;

procedure Tfrm_main.CheckBox58Click(Sender: TObject);
begin
  CheckBoxWork(58);
end;

procedure Tfrm_main.CheckBox59Click(Sender: TObject);
begin
  CheckBoxWork(59);
end;

procedure Tfrm_main.CheckBox60Click(Sender: TObject);
begin
  CheckBoxWork(60);
end;

procedure Tfrm_main.CheckBox61Click(Sender: TObject);
begin
  CheckBoxWork(61);
end;

procedure Tfrm_main.CheckBox62Click(Sender: TObject);
begin
  CheckBoxWork(62);
end;

procedure Tfrm_main.CheckBox63Click(Sender: TObject);
begin
  CheckBoxWork(63);
end;

procedure Tfrm_main.CheckBox64Click(Sender: TObject);
begin
  CheckBoxWork(64);
end;

end.
