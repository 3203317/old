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

{����
��Ԫ˵����
�����õ�ԪԴ������һλ���ѵ�XML���������ԭʼ����Ϊ�޸���������������϶Խӿڽ�
���˲��ֵ���ʹ�������ʹ�á�
�޸ļ�¼��
����2006-08-25 ��ԭXMLParse�����ع������������в��ִ���
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
  ������:    TSampleXML.Create
  ����:      Xu Shengping
  ����:      2006-08-25
  ����:      const Axmlfile: string
  ����ֵ:    ��
  Ŀ�ģ�     ����XML������
-------------------------------------------------------------------------------}
constructor TSampleXML.Create(const Axmlfile: string);
begin
  FDelimiter := '\';
  Fxmlfile := Axmlfile;
  XMLDocument := TXMLDocument.Create(Fxmlfile);
  XMLDocument.Active := True;
end;

{-------------------------------------------------------------------------------
  ������:    TSampleXML.Destroy
  ����:      Xu Shengping
  ����:      2006-08-25
  ����:      ��
  ����ֵ:    ��
  Ŀ�ģ�     �ͷ���ռ�õ���Դ
-------------------------------------------------------------------------------}
destructor TSampleXML.Destroy;
begin
  if Assigned(XMLDocument) then XMLDocument := nil;
end;

{-------------------------------------------------------------------------------
  ������:    TSampleXML.GetChildCount
  ����:      Xu Shengping
  ����:      2006-08-25
  ����:      const AParentPath, Achildname: string
  ����ֵ:    Integer
  Ŀ�ģ�     ��ȡָ��·����ָ���ӽڵ�ĸ���
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
  ������:    TSampleXML.GetNode
  ����:      Xu Shengping
  ����:      2006-08-25
  ����:      const Apath: string; ARowIndex: Integer
  ����ֵ:    IXMLNode
  Ŀ�ģ�     ��ȡXML�б�ŵ�ARowIndex��·��ΪApath�Ľڵ�
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

  // ��ȡXML�ĵ��ĸ��ڵ�
  APathCount := NodePath.Count;
  APathIndex := 0;
  XMLNode := XMLDocument.DocumentElement;

  IsFound := XMLNode.NodeName = NodePath.Strings[APathIndex];
  Inc(APathIndex);

  while IsFound and (APathIndex < APathCount) do
  begin
    AnodeName := NodePath.Strings[APathIndex];
    // �������е��ӽڵ�, ���в�������Ϊ AnodeName �Ľڵ�.
    IsFound := False;
    for I := 0 to XMLNode.ChildNodes.Count - 1 do
    begin
       if LowerCase(XMLNode.ChildNodes.Nodes[i].NodeName) = LowerCase(AnodeName) then
       begin
         // ����û�е���ANodePath��ָ�������Ľڵ�ʱ,�������²����
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
  ������:    TSampleXML.GetNodeAttribute
  ����:      Xu Shengping
  ����:      2006-08-25
  ����:      const Apath, Aattribute: string; ARowIndex: Integer
  ����ֵ:    string
  Ŀ�ģ�     ��ȡָ��·���µ�����ֵ
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
  ������:    TSampleXML.GetNodeText
  ����:      Xu Shengping
  ����:      2006-08-25
  ����:      const Apath: string; ARowIndex: Integer
  ����ֵ:    string
  Ŀ�ģ�     ��ȡָ��·���½ڵ��ı�
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
  ������:    TSampleXML.LoadFromFile
  ����:      Xu Shengping
  ����:      2006-08-25
  ����:      const Afilename: string
  ����ֵ:    Boolean
  Ŀ�ģ�     ���ļ�����XML�ĵ�
-------------------------------------------------------------------------------}
function TSampleXML.LoadFromFile(const Afilename: string): Boolean;
begin
  XMLDocument.Active := False;
  XMLDocument.LoadFromFile(Afilename);
  XMLDocument.Active := True;
  Result := XMLDocument.Active;
end;

end.
