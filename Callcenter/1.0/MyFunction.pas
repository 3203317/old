unit MyFunction;

interface

uses windows,Dialogs,SysUtils,Sockets,Controls,Classes;




type TDateDiff = record
  days,hours,minutes,seconds,mseconds:integer;
end;

const TRK_FREE = 0;//ͨ������
const TRK_PLAYWELCOME = 1;//���Ż�ӭ����
const TRK_PLAYSELECT = 2;//����ѡ������
const TRK_FWZX = 3;//������ѯ
const TRK_YWSL = 4;//ҵ������
const TRK_TSJY = 5;//Ͷ�߽���
const TRK_BUSY = 6;//��ϯ��æ
const TRK_SENDPHONECODE = 7;//���͵绰����
const TRK_PLAYZXCODE = 8;//������ϯ��
const TRK_READYTALK = 9;//׼��ͨ��
const TRK_TALKING = 10;//ͨ����
const TRK_TALKING1 = 11;//ͨ����
const TRK_WAIT = 12;//���еȴ�
const TRK_CONWAIT = 13;//���ӵȴ�
const TRK_PLAYHOOKON = 14;//���Źһ�����
const TRK_TELWZ1 = 15;//�绰����ת
const TRK_TELWZ2 = 16;//��ת����

const TRK_TELWZ3 = 24;//��תͨ����

const TRK_SENDHOOKONSIGN = 17;//���͹һ��ź�
const TRK_MANYI1 = 18;//����ȵ���1
const TRK_MANYI2 = 19;//����ȵ���2
const TRK_MANYI3 = 20;//����ȵ���2

const TRK_ONHOOK = 21;//���͹һ��ź�

const TRK_RING = 22;//�ͻ���������
const TRK_UCONNECT = 23;//ͨ����


const USER_FREE = 0;//����
const USER_RING = 1;//�û�����
const USER_HOOKOFF = 2;//�û�ժ��
const USER_HOOKON = 3;//�û��һ�
const USER_BUSY = 4;//�û���æ
const USER_TALKING = 5;//����ͨ����
const USER_WAITHOOKON = 6;//�ȴ��һ�
const USER_DIALING = 7;//�û�����
const USER_HOOKONING = 8;//���ڹһ�

const USER_UHOOKOFF = 9;//�û�ժ��
const USER_DIAL = 10;//���ں���
const USER_ENDSSESION = 11;//������
const USER_UCONNECT = 12;//ͨ����
const USER_WAITDIAL = 13;//�ȴ�����

const USER_ENDDIAL = 14;//�ȴ��һ�
const USER_NOCONN = 15;//��ϯû������



function GetLocalIP: String;//��ȡ����ip
function GetWelcomeVoice:PAnsiChar;//��ȡ��ӭ�����ļ����ݵ�ǰ��ʱ��
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

function GetWelcomeVoice:PAnsiChar;//��ȡ��ӭ�����ļ����ݵ�ǰ��ʱ��
var
  currTime: TTime;
begin
  currTime := Time;

  if (currTime >= StrToTime('00:00:00')) and (currTime < StrToTime('12:00:00')) then//����
    begin
      Result := 'voc\htjs.011';//���Ϻ�,��ӭ�µ纽����Ϣ�ͻ���������
      Exit;
    end
  else if (currTime >= StrToTime('12:00:00')) and (currTime < StrToTime('14:00:00')) then//����
    begin
      Result := 'voc\htjs.013';//
      Exit;
    end
  else if (currTime >= StrToTime('14:00:00')) and (currTime < StrToTime('18:00:00')) then//����
    begin
      Result := 'voc\htjs.014';
      Exit;
    end
  else//����
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
