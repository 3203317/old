object frmset: Tfrmset
  Left = 336
  Top = 213
  BorderIcons = [biSystemMenu, biMinimize]
  BorderStyle = bsSingle
  Caption = #21628#21483#20013#24515#37197#32622
  ClientHeight = 314
  ClientWidth = 441
  Color = clBtnFace
  Font.Charset = ANSI_CHARSET
  Font.Color = clWindowText
  Font.Height = -12
  Font.Name = #23435#20307
  Font.Style = []
  OldCreateOrder = False
  Position = poDesktopCenter
  OnClose = FormClose
  OnCreate = FormCreate
  OnKeyDown = FormKeyDown
  PixelsPerInch = 96
  TextHeight = 12
  object Panel1: TPanel
    Left = 0
    Top = 0
    Width = 441
    Height = 314
    Align = alClient
    BevelOuter = bvNone
    TabOrder = 0
    object Panel2: TPanel
      Left = 0
      Top = 273
      Width = 441
      Height = 41
      Align = alBottom
      BevelOuter = bvNone
      TabOrder = 0
      object Button1: TButton
        Left = 136
        Top = 8
        Width = 75
        Height = 25
        Caption = #20445' '#23384'(&S)'
        TabOrder = 0
        OnClick = Button1Click
      end
      object Button2: TButton
        Left = 232
        Top = 8
        Width = 75
        Height = 25
        Caption = #21462' '#28040'(&C)'
        TabOrder = 1
        OnClick = Button2Click
      end
    end
    object PageControl1: TPageControl
      Left = 0
      Top = 0
      Width = 441
      Height = 273
      ActivePage = TabSheet7
      Align = alClient
      MultiLine = True
      TabOrder = 1
      object TabSheet1: TTabSheet
        Caption = #26381#21153#22352#24109#20998#37197
        object Label1: TLabel
          Left = 112
          Top = 32
          Width = 186
          Height = 12
          Caption = #26381#21153#22352#24109#36755#20837#26684#24335#65306'1,2,3,4,5,6,7'
          Font.Charset = ANSI_CHARSET
          Font.Color = clBlue
          Font.Height = -12
          Font.Name = #23435#20307
          Font.Style = []
          ParentFont = False
        end
        object Label2: TLabel
          Left = 80
          Top = 70
          Width = 54
          Height = 12
          Caption = #26381#21153#21672#35810':'
        end
        object Label3: TLabel
          Left = 80
          Top = 94
          Width = 54
          Height = 12
          Caption = #19994#21153#21463#29702':'
        end
        object Label4: TLabel
          Left = 80
          Top = 118
          Width = 54
          Height = 12
          Caption = #25237#35785#24314#35758':'
        end
        object Label5: TLabel
          Left = 80
          Top = 142
          Width = 54
          Height = 12
          Caption = #20849#20139#22352#24109':'
        end
        object Label11: TLabel
          Left = 80
          Top = 166
          Width = 54
          Height = 12
          Caption = #19987#23478#22352#24109':'
        end
        object Label12: TLabel
          Left = 80
          Top = 190
          Width = 54
          Height = 12
          Caption = #21830#21153#22352#24109':'
        end
        object fwzxEdit: TEdit
          Left = 136
          Top = 64
          Width = 225
          Height = 20
          TabOrder = 0
        end
        object ywslEdit: TEdit
          Left = 136
          Top = 88
          Width = 225
          Height = 20
          TabOrder = 1
        end
        object tsjyEdit: TEdit
          Left = 136
          Top = 112
          Width = 225
          Height = 20
          TabOrder = 2
        end
        object gxzxEdit: TEdit
          Left = 136
          Top = 136
          Width = 225
          Height = 20
          TabOrder = 3
        end
        object zjzxEdit: TEdit
          Left = 136
          Top = 160
          Width = 225
          Height = 20
          TabOrder = 4
        end
        object swzxEdit: TEdit
          Left = 136
          Top = 184
          Width = 225
          Height = 20
          TabOrder = 5
        end
      end
      object TabSheet2: TTabSheet
        Caption = #22352#24109#31471#21475
        ImageIndex = 1
      end
      object TabSheet4: TTabSheet
        Caption = #22806#37096#36716#25509
        ImageIndex = 3
        object ywslCHK: TCheckBox
          Left = 80
          Top = 115
          Width = 65
          Height = 17
          Caption = #19994#21153#21463#29702
          TabOrder = 0
          OnClick = ywslCHKClick
        end
        object TelywslWZ: TEdit
          Left = 152
          Top = 112
          Width = 153
          Height = 20
          TabOrder = 1
        end
        object tsjyCHK: TCheckBox
          Left = 80
          Top = 147
          Width = 65
          Height = 17
          Caption = #25237#35785#24314#35758
          TabOrder = 2
          OnClick = tsjyCHKClick
        end
        object TeltsjyWZ: TEdit
          Left = 152
          Top = 144
          Width = 153
          Height = 20
          TabOrder = 3
        end
        object fwzxCHK: TCheckBox
          Left = 80
          Top = 83
          Width = 65
          Height = 17
          Caption = #26381#21153#21672#35810
          TabOrder = 4
          OnClick = fwzxCHKClick
        end
        object TelfwzxWZ: TEdit
          Left = 152
          Top = 80
          Width = 153
          Height = 20
          TabOrder = 5
        end
      end
      object TabSheet5: TTabSheet
        Caption = #30005#35805#24405#38899
        ImageIndex = 4
        object chkRecordFile: TCheckBox
          Left = 48
          Top = 107
          Width = 89
          Height = 17
          Caption = #21551#29992#30005#35805#24405#38899
          TabOrder = 0
          OnClick = chkRecordFileClick
        end
        object RecordSavePath: TEdit
          Left = 144
          Top = 104
          Width = 225
          Height = 20
          TabOrder = 1
        end
      end
      object TabSheet7: TTabSheet
        Caption = #21021#22987#21270#36890#36947
        ImageIndex = 6
        object Label10: TLabel
          Left = 105
          Top = 63
          Width = 84
          Height = 12
          Caption = #21021#22987#21270#36890#36947#21495#65306
        end
        object Label9: TLabel
          Left = 104
          Top = 135
          Width = 84
          Height = 12
          Caption = #21021#22987#21270#22352#24109#21495#65306
        end
        object Button3: TButton
          Left = 241
          Top = 56
          Width = 57
          Height = 25
          Caption = #25191#34892
          TabOrder = 0
          OnClick = Button3Click
        end
        object initZJ: TEdit
          Left = 193
          Top = 59
          Width = 41
          Height = 20
          TabOrder = 1
          OnKeyPress = initZJKeyPress
        end
        object initZX: TEdit
          Left = 193
          Top = 131
          Width = 41
          Height = 20
          TabOrder = 2
          OnKeyPress = initZXKeyPress
        end
        object Button4: TButton
          Left = 241
          Top = 128
          Width = 57
          Height = 25
          Caption = #25191#34892
          TabOrder = 3
          OnClick = Button4Click
        end
      end
      object TabSheet3: TTabSheet
        Caption = #31995#32479#37197#32622
        ImageIndex = 5
        object Label6: TLabel
          Left = 73
          Top = 62
          Width = 60
          Height = 12
          Caption = #31995#32479#26085#24535#65306
        end
        object Label7: TLabel
          Left = 75
          Top = 109
          Width = 60
          Height = 12
          Caption = #21628#20837#26085#24535#65306
        end
        object Label8: TLabel
          Left = 75
          Top = 157
          Width = 60
          Height = 12
          Caption = #21628#20986#26085#24535#65306
        end
        object SysLogPath: TEdit
          Left = 131
          Top = 56
          Width = 209
          Height = 20
          TabOrder = 0
        end
        object HRLogPath: TEdit
          Left = 131
          Top = 104
          Width = 209
          Height = 20
          TabOrder = 1
        end
        object HCLogPath: TEdit
          Left = 131
          Top = 152
          Width = 209
          Height = 20
          TabOrder = 2
        end
      end
    end
  end
end
