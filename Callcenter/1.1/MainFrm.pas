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

unit MainFrm;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, StdCtrls, ExtCtrls, ComCtrls, ToolWin, LightweightCTI, ActnList,
  ImgList, Buttons, Grids, Menus;

type
  THackChannel = class(TAbstractChannel);

  TMainform = class(TForm)
    clbrTools: TCoolBar;
    tlbChannels: TToolBar;
    btnLoad: TToolButton;
    ToolButton1: TToolButton;
    btnRun: TToolButton;
    btnPause: TToolButton;
    btnStop: TToolButton;
    ToolButton2: TToolButton;
    btnExit: TToolButton;
    actlstDemo: TActionList;
    ilImages: TImageList;
    actLoadChannelManager: TAction;
    actRun: TAction;
    actPause: TAction;
    actStop: TAction;
    actExit: TAction;
    actRefresh: TAction;
    actSave: TAction;
    actOpen: TAction;
    ilState: TImageList;
    ilChannel: TImageList;
    statBar: TStatusBar;
    pgcTest: TPageControl;
    TabSheet1: TTabSheet;
    lvTrunkChannels: TListView;
    TabSheet2: TTabSheet;
    lvUserChannels: TListView;
    TabSheet3: TTabSheet;
    DrawGrid1: TDrawGrid;
    ImageList1: TImageList;
    PopupMenu1: TPopupMenu;
    PopupMenu2: TPopupMenu;
    N1: TMenuItem;
    procedure actLoadChannelManagerExecute(Sender: TObject);
    procedure actExitExecute(Sender: TObject);
    procedure FormClose(Sender: TObject; var Action: TCloseAction);
    procedure actRunExecute(Sender: TObject);
    procedure DrawGrid1DblClick(Sender: TObject);
    procedure DrawGrid1DrawCell(Sender: TObject; ACol, ARow: Integer;
      Rect: TRect; State: TGridDrawState);
    procedure N1Click(Sender: TObject);
    procedure DrawGrid1TopLeftChanged(Sender: TObject);
    procedure actStopExecute(Sender: TObject);
  private

  public
    procedure ListHeadInfo(AChanelCount: integer; AList: TStringList);
    procedure ListChanelServices; //在表格中显示已存在的分配信息
    procedure ClickCell(ACol,ARow : integer);
  end;

var
  Mainform: TMainform;

implementation

uses
  TextLoger, GlobalConstants,D641XChannel_callback;

{$R *.dfm}

procedure TMainform.actRunExecute(Sender: TObject);
begin
  Screen.Cursor := crHourGlass;
  try
    with  ChannelCallback(self) do begin
      ResumeChannels(ctTrunk);ResumeChannels(ctUser);
    end;
  finally
    TAction(Sender).Enabled := False;  btnStop.Enabled := True;
    Screen.Cursor := crDefault;
  end;
end;

procedure TMainform.actLoadChannelManagerExecute(Sender: TObject);
begin
  Screen.Cursor := crHourGlass;
  try
    ChannelCallback(self).initChannelManager;
    DrawGrid1.RowCount := ChannelManager.userChannels.Count + 1;
    DrawGrid1.ColCount := ServerTypeList.Count + 2;
    ListHeadInfo(DrawGrid1.RowCount,ServerTypeList);
  finally
    TAction(Sender).Enabled := False; btnRun.Enabled := True;
    Screen.Cursor := crDefault;
  end;
end;

procedure TMainform.actExitExecute(Sender: TObject);
begin
  Application.Terminate;
end;

procedure TMainform.FormClose(Sender: TObject; var Action: TCloseAction);
begin
  ChannelCallback(self).Free;
  ServerTypeList.Free;
end;


procedure TMainform.ListChanelServices;
var
   row, col, k : integer;
   ServerStr : string;
   myRect : TRect;
   channelServerList: TStringList;
begin
  channelServerList := TStringList.Create;
  try
    if ChannelManager.userChannels.Count > 0 then
    begin
      for row:=0 to DrawGrid1.RowCount-2 do
      begin
        ServerStr := TAbstractChannel(ChannelManager.userChannels.Items[Row]).FserverType;
        if ServerStr <> '' then
        begin
          if pos(',',ServerStr) <= 0 then
          begin
            for col:=0 to ServerTypeList.Count-1 do
            begin
              if ServerTypeList.Names[col] = ServerStr then
              begin
                myRect := DrawGrid1.CellRect(col+1,row+1);
                ImageList1.Draw(DrawGrid1.Canvas,myRect.Left,myRect.Top,0);
              end;
            end;
          end else
          if pos(',',ServerStr) > 1 then
          begin
            channelServerList.CommaText := ServerStr;
            for k:=0 to channelServerList.Count - 1 do
            begin
              for col:=0 to ServerTypeList.Count-1 do
              begin
                if ServerTypeList.Names[col] = channelServerList[k] then
                begin
                  myRect := DrawGrid1.CellRect(col+1,row+1);
                  ImageList1.Draw(DrawGrid1.Canvas,myRect.Left,myRect.Top,0);
                end;
              end;
            end;
          end;
        end;
        if TAbstractChannel(ChannelManager.userChannels.Items[Row]).Status = csDisable then
        begin
          myRect := DrawGrid1.CellRect(ServerTypeList.count + 1,row+1);
          DrawGrid1.Canvas.FillRect(myRect);
        end else
        begin
          myRect := DrawGrid1.CellRect(ServerTypeList.count + 1,row+1);
          ImageList1.Draw(DrawGrid1.Canvas,myRect.Left,myRect.Top,0);
        end;
      end;//endfor
    end;
  finally
    channelServerList.Free;
  end;
end;

procedure TMainform.ListHeadInfo(AChanelCount: integer;
  AList: TStringList);
var
  row,col: integer;
  myRect: TRect;
  tmpName : string;
begin
  //写坐席
  for row:=0 to AChanelCount do
  begin
    myRect := DrawGrid1.CellRect(0,row);
    if row = 0 then
    begin
      DrawGrid1.Canvas.Brush.Color := clBtnFace	;
      DrawGrid1.Canvas.TextRect(myRect,myRect.Left+5,myRect.Top+5,'坐席号')
    end else
    begin
      DrawGrid1.Canvas.Brush.Color := clBtnFace;
      myRect := DrawGrid1.CellRect(0,row);
      DrawGrid1.Canvas.TextRect(myRect,myRect.Left+10,myRect.Top+5,IntToStr(Row-1));
    end;
  end;
  if not Assigned(AList) then  exit;
  for col := 1 to AList.Count+1 do
  begin
    myRect := DrawGrid1.CellRect(col,0);
    DrawGrid1.Canvas.Font.Name := 'MS Sans Serif';
    DrawGrid1.Canvas.Font.Size := 8;
    DrawGrid1.Canvas.Brush.Color := clBtnFace	;
   // DrawGrid1.Canvas.Font.Style := [fsBold];
    if col <> Alist.Count+1 then
    begin
      tmpName := AList.Values[IntToStr(Col)];
      DrawGrid1.Canvas.TextRect(myRect,myRect.Left+10,myRect.Top+5,tmpName);
    end else
      DrawGrid1.Canvas.TextRect(myRect,myRect.Left+10,myRect.Top+5,'起用');
  end;
end;

procedure TMainform.DrawGrid1DblClick(Sender: TObject);
begin
  if TDrawGrid(Sender).Row > 0 then
      ClickCell(TDrawGrid(Sender).Col,TDrawGrid(Sender).Row);
end;

procedure TMainform.ClickCell(ACol, ARow: integer);
var
  myRect : TRect;
  ServerStr, Code: String;
begin
    if ACol = ServerTypeList.Count + 1 then
    begin
      if TAbstractChannel(ChannelManager.userChannels.items[ARow-1]).Status <> csDisable then
      begin
        TAbstractChannel(ChannelManager.userChannels.items[ARow-1]).Status := csDisable;
        myRect := DrawGrid1.CellRect(ACol,ARow);
        DrawGrid1.Canvas.FillRect(myRect);
      end else
      begin
        TAbstractChannel(ChannelManager.userChannels.items[ARow-1]).Status := csFree;
        myRect := DrawGrid1.CellRect(ACol,ARow);
        ImageList1.Draw(DrawGrid1.Canvas,myRect.Left,myRect.Top,0);
      end;

    end;
    ServerStr := TAbstractChannel(ChannelManager.userChannels.items[ARow-1]).FserverType;
    Code := IntToStr(ACol);
    if ServerStr <> '' then
    begin
      if pos(Code,ServerStr) > 0 then
      begin
        myRect := DrawGrid1.CellRect(ACol,ARow);
        //DrawGrid1.Canvas.FillRect(myRect);
        //下面是更改服务字符串
        //判断是不是最后一个，如果是要删除前面的逗号,否则要删除后面的逗号
        if Length(ServerStr) = Length(Code) then
        begin
          DrawGrid1.Canvas.FillRect(myRect);
          ServerStr := '';
        end else
          if Length(ServerStr) = (pos(Code,ServerStr) + Length(Code)-1) then
          begin
            DrawGrid1.Canvas.FillRect(myRect);
            Delete(ServerStr,pos(Code,ServerStr)-1,length(Code) + 1);
          end else
          begin
            DrawGrid1.Canvas.FillRect(myRect);
            Delete(ServerStr,pos(Code,ServerStr),length(Code) + 1);
          end;
        TAbstractChannel(ChannelManager.userChannels.items[ARow-1]).FserverType := ServerStr;
      end else   //该列中没有服务
      begin
        myRect := DrawGrid1.CellRect(ACol,ARow);
        ImageList1.Draw(DrawGrid1.Canvas,myRect.Left,myRect.Top,0);
        TAbstractChannel(ChannelManager.userChannels.items[ARow-1]).FserverType := ServerStr + ',' + Code;
      end;
    end else // 整个通道都没有选中的信息
    begin
       myRect := DrawGrid1.CellRect(ACol,ARow);
       ImageList1.Draw(DrawGrid1.Canvas,myRect.Left,myRect.Top,0);
       TAbstractChannel(ChannelManager.userChannels.items[ARow-1]).FserverType := Code;
    end;

end;

procedure TMainform.DrawGrid1DrawCell(Sender: TObject; ACol, ARow: Integer;
  Rect: TRect; State: TGridDrawState);
begin
  {if (gdFixed in State) and (ARow = 0) then
  begin
    DrawGrid1.Canvas.Brush.Color := clBtnFace	;
  end;
   }
  if self.Tag =1 then
  begin
    //用这个状态解决了鼠标滚动时在第一列出现图标的情况
    ListHeadInfo(ChannelManager.userChannels.Count,ServerTypeList);
    if (gdFocused in State) or (gdSelected in State) then    //gdSelected  ,gdFixed
    begin
      ListChanelServices;
    end;
  end
  else  ListHeadInfo(0,ServerTypeList);
end;

procedure TMainform.N1Click(Sender: TObject);
begin
  ListHeadInfo(ChannelManager.userChannels.Count,ServerTypeList);
  ListChanelServices;
end;

procedure TMainform.DrawGrid1TopLeftChanged(Sender: TObject);
begin
  ListHeadInfo(ChannelManager.userChannels.Count,ServerTypeList);
  ListChanelServices;
  DrawGrid1.Canvas.Refresh;
end;

procedure TMainform.actStopExecute(Sender: TObject);
begin
  Screen.Cursor := crHourGlass;
  try
    with  ChannelCallback(self) do begin
      TerminateChannels(ctTrunk); TerminateChannels(ctUser);
    end;
  finally
    TAction(Sender).Enabled := False;  btnRun.Enabled := True;
    Screen.Cursor := crDefault;
  end;
end;

end.
