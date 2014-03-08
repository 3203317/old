unit MyFunction;

interface

uses windows,Dialogs,SysUtils,Sockets,Controls,Classes;




type TDateDiff = record
  days,hours,minutes,seconds,mseconds:integer;
end;

const TRK_FREE = 0;//通道空闲
const TRK_PLAYWELCOME = 1;//播放欢迎语音
const TRK_PLAYSELECT = 2;//播放选择音乐
const TRK_FWZX = 3;//服务咨询
const TRK_YWSL = 4;//业务受理
const TRK_TSJY = 5;//投诉建议
const TRK_BUSY = 6;//坐席正忙
const TRK_SENDPHONECODE = 7;//发送电话号码
const TRK_PLAYZXCODE = 8;//播放坐席号
const TRK_READYTALK = 9;//准备通话
const TRK_TALKING = 10;//通话中
const TRK_TALKING1 = 11;//通话中
const TRK_WAIT = 12;//呼叫等待
const TRK_CONWAIT = 13;//连接等待
const TRK_PLAYHOOKON = 14;//播放挂机音乐
const TRK_TELWZ1 = 15;//电话已外转
const TRK_TELWZ2 = 16;//外转振铃

const TRK_TELWZ3 = 24;//外转通话中

const TRK_SENDHOOKONSIGN = 17;//发送挂机信号
const TRK_MANYI1 = 18;//满意度调查1
const TRK_MANYI2 = 19;//满意度调查2
const TRK_MANYI3 = 20;//满意度调查2

const TRK_ONHOOK = 21;//发送挂机信号

const TRK_RING = 22;//客户正在振铃
const TRK_UCONNECT = 23;//通话中


const USER_FREE = 0;//空闲
const USER_RING = 1;//用户振铃
const USER_HOOKOFF = 2;//用户摘机
const USER_HOOKON = 3;//用户挂机
const USER_BUSY = 4;//用户正忙
const USER_TALKING = 5;//正在通话中
const USER_WAITHOOKON = 6;//等待挂机
const USER_DIALING = 7;//用户拨号
const USER_HOOKONING = 8;//正在挂机

const USER_UHOOKOFF = 9;//用户摘机
const USER_DIAL = 10;//正在呼出
const USER_ENDSSESION = 11;//结束话
const USER_UCONNECT = 12;//通话中
const USER_WAITDIAL = 13;//等待拨号

const USER_ENDDIAL = 14;//等待挂机
const USER_NOCONN = 15;//坐席没有连的



function GetLocalIP: String;//获取本地ip
function GetWelcomeVoice:PAnsiChar;//获取欢迎语音文件根据当前的时间
function GetIPD(IP: string): string;
function GetDateDiff2(const Date1,Date2:TDateTime): String;


implementation

function GetIPD(IP: string): string;
var
  tmpStr: TStrings;
begin
  tmpStr := TStringList.Create;
  tmpStr.Delimiter := '.';
  tmpStr.DelimitedText := IP;
  Result := tmpStr[3];
end;

function GetLocalIP: String;
var
  tp: TTcpClient;
begin
  tp := TTcpClient.Create(nil);
  tp.Close;
  tp.Open;
  Result := tp.LocalHostAddr;
  tp.Close;
end;

function GetWelcomeVoice:PAnsiChar;//获取欢迎语音文件根据当前的时间
var
  currTime: TTime;
begin
  currTime := Time;

  if (currTime >= StrToTime('00:00:00')) and (currTime < StrToTime('12:00:00')) then//早上
    begin
      Result := 'voc\htjs.011';//早上好,欢迎致电航天信息客户服务热线
      Exit;
    end
  else if (currTime >= StrToTime('12:00:00')) and (currTime < StrToTime('14:00:00')) then//中午
    begin
      Result := 'voc\htjs.013';//
      Exit;
    end
  else if (currTime >= StrToTime('14:00:00')) and (currTime < StrToTime('18:00:00')) then//下午
    begin
      Result := 'voc\htjs.014';
      Exit;
    end
  else//晚上
    begin
      Result := 'voc\htjs.015';
      Exit;
    end;
end;

function  GetDateDiff(const Date1,Date2:TDateTime):TDateDiff;
var Diff:TDateTime; 
    temp:TDateDiff;
begin
   Diff:=abs(Date1-Date2);
   temp.days:=trunc(Diff);
   Diff:=(Diff-temp.days)*24;
   temp.hours:=trunc(Diff);
   Diff:=(Diff-temp.hours)*60;
   temp.minutes:=trunc(Diff);
   Diff:=(Diff-temp.minutes)*60;
   temp.seconds:=trunc(Diff);
   Diff:=(Diff-temp.seconds)*1000;
   temp.mseconds:=trunc(Diff);
   Result:=temp;
end;

function GetDateDiff2(const Date1,Date2:TDateTime): String;
begin
  try
    Result := inttostr(GetDateDiff(Date1,Date2).minutes) +':'+ inttostr(GetDateDiff(Date1,Date2).seconds);
  except
    Result := '00:00';
  end;
end;


end.
