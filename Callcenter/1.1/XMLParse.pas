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

{　　
单元说明：
　　该单元源自网上一位朋友的XML解析组件，原始作者为罗庚。本人在其基础上对接口进
行了部分调整使其更易于使用。
修改记录：
　　2006-08-25 对原XMLParse进行重构并修正了其中部分错误。
}

unit XMLParse;

interface

uses
  XMLIntf, XMLDoc, Classes, SysUtils;

type
  TSampleXML = class(TObject)
  private
    XMLDocument: IXMLDocument;
    Fxmlfile: string;
    FDelimiter: Char;
  public
    constructor Create(const Axmlfile: string = '');
    destructor Destroy; override;

    function LoadFromFile(const Afilename: string): Boolean;
    function GetNode(const Apath: string; ARowIndex: Integer = 0): IXMLNode;
    function GetChildCount(const AParentPath, Achildname: string): Integer;
    function GetNodeText(const Apath: string; ARowIndex: Integer = 0): string;
    function GetNodeAttribute(const Apath, Aattribute: string; ARowIndex: Integer = 0): string;
  end;

implementation

{ TXMLDOMEx }

{-------------------------------------------------------------------------------
  过程名:    TSampleXML.Create
  作者:      Xu Shengping
  日期:      2006-08-25
  参数:      const Axmlfile: string
  返回值:    无
  目的：     创建XML分析器
-------------------------------------------------------------------------------}
constructor TSampleXML.Create(const Axmlfile: string);
begin
  FDelimiter := '\';
  Fxmlfile := Axmlfile;
  XMLDocument := TXMLDocument.Create(Fxmlfile);
  XMLDocument.Active := True;
end;

{-------------------------------------------------------------------------------
  过程名:    TSampleXML.Destroy
  作者:      Xu Shengping
  日期:      2006-08-25
  参数:      无
  返回值:    无
  目的：     释放其占用的资源
-------------------------------------------------------------------------------}
destructor TSampleXML.Destroy;
begin
  if Assigned(XMLDocument) then XMLDocument := nil;
end;

{-------------------------------------------------------------------------------
  过程名:    TSampleXML.GetChildCount
  作者:      Xu Shengping
  日期:      2006-08-25
  参数:      const AParentPath, Achildname: string
  返回值:    Integer
  目的：     获取指定路径下指定子节点的个数
-------------------------------------------------------------------------------}
function TSampleXML.GetChildCount(const AParentPath, Achildname: string): Integer;
var
  XMLParentNode: IXMLNode;
  N, I: Integer;
begin
  Result := -1;
  XMLParentNode := GetNode(AParentPath);
  if not Assigned(XMLParentNode) then Exit;

  if Achildname = '' then
    Result := XMLParentNode.ChildNodes.Count
  else
  begin
    N := 0;
    for I := 0 to XMLParentNode.ChildNodes.Count - 1 do
      if LowerCase(XMLParentNode.ChildNodes.Nodes[I].NodeName) = LowerCase(Achildname) then
        Inc(N);
    Result := N;
  end;
end;

{-------------------------------------------------------------------------------
  过程名:    TSampleXML.GetNode
  作者:      Xu Shengping
  日期:      2006-08-25
  参数:      const Apath: string; ARowIndex: Integer
  返回值:    IXMLNode
  目的：     获取XML中编号第ARowIndex个路径为Apath的节点
-------------------------------------------------------------------------------}
function TSampleXML.GetNode(const Apath: string;
  ARowIndex: Integer): IXMLNode;
var
  XMLNode: IXMLNode;
  NodePath: TStrings;
  APathCount, APathIndex, I: Integer;
  IsFound: Boolean;
  AnodeName: string;
begin
  Result   := nil;
  NodePath := TStringList.Create;
  NodePath.Delimiter := FDelimiter;
  NodePath.DelimitedText := Apath;

  // 获取XML文档的根节点
  APathCount := NodePath.Count;
  APathIndex := 0;
  XMLNode := XMLDocument.DocumentElement;

  IsFound := XMLNode.NodeName = NodePath.Strings[APathIndex];
  Inc(APathIndex);

  while IsFound and (APathIndex < APathCount) do
  begin
    AnodeName := NodePath.Strings[APathIndex];
    // 遍历所有的子节点, 从中查找下名为 AnodeName 的节点.
    IsFound := False;
    for I := 0 to XMLNode.ChildNodes.Count - 1 do
    begin
       if LowerCase(XMLNode.ChildNodes.Nodes[i].NodeName) = LowerCase(AnodeName) then
       begin
         // 当还没有到达ANodePath中指定的最后的节点时,继续往下层遍历
          if APathIndex <> (APathCount - 1) then
          begin
            IsFound := True;
            XMLNode := XMLNode.ChildNodes.Nodes[I];
            break;
          end else
          begin
            Dec(ARowIndex);
            if ARowIndex < 0 then
            begin
              IsFound := True;
              XMLNode := XMLNode.ChildNodes.Nodes[I];
              Break;
            end;
          end;
       end;
    end;
    Inc(APathIndex);
  end; // end of while

  if IsFound then Result := XMLNode
end;

{-------------------------------------------------------------------------------
  过程名:    TSampleXML.GetNodeAttribute
  作者:      Xu Shengping
  日期:      2006-08-25
  参数:      const Apath, Aattribute: string; ARowIndex: Integer
  返回值:    string
  目的：     获取指定路径下的属性值
-------------------------------------------------------------------------------}
function TSampleXML.GetNodeAttribute(const Apath, Aattribute: string;
  ARowIndex: Integer): string;
var
  XMLNode: IXMLNode;
begin
  XMLNode := GetNode(Apath, ARowIndex);
  if not Assigned(XMLNode) or not XMLNode.HasAttribute(Aattribute) then
    Result := ''
  else
    Result := XMLNode.Attributes[Aattribute];
end;

{-------------------------------------------------------------------------------
  过程名:    TSampleXML.GetNodeText
  作者:      Xu Shengping
  日期:      2006-08-25
  参数:      const Apath: string; ARowIndex: Integer
  返回值:    string
  目的：     获取指定路径下节点文本
-------------------------------------------------------------------------------}
function TSampleXML.GetNodeText(const Apath: string;
  ARowIndex: Integer): string;
var
  XMLNode: IXMLNode;
begin
  XMLNode := GetNode(Apath, ARowIndex);
  if not Assigned(XMLNode) or not XMLNode.IsTextElement then
    Result := ''
  else
    Result := XMLNode.Text;
end;

{-------------------------------------------------------------------------------
  过程名:    TSampleXML.LoadFromFile
  作者:      Xu Shengping
  日期:      2006-08-25
  参数:      const Afilename: string
  返回值:    Boolean
  目的：     从文件加载XML文档
-------------------------------------------------------------------------------}
function TSampleXML.LoadFromFile(const Afilename: string): Boolean;
begin
  XMLDocument.Active := False;
  XMLDocument.LoadFromFile(Afilename);
  XMLDocument.Active := True;
  Result := XMLDocument.Active;
end;

end.
