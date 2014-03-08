object frm_main: Tfrm_main
  Left = 12
  Top = 12
  BorderIcons = [biSystemMenu, biMinimize]
  BorderStyle = bsSingle
  Caption = #21628#21483#20013#24515#24213#23618#26381#21153#31471
  ClientHeight = 707
  ClientWidth = 992
  Color = clBtnFace
  DefaultMonitor = dmDesktop
  Font.Charset = ANSI_CHARSET
  Font.Color = clWindowText
  Font.Height = -12
  Font.Name = #23435#20307
  Font.Style = []
  OldCreateOrder = False
  Position = poDesktopCenter
  OnCloseQuery = FormCloseQuery
  OnCreate = FormCreate
  PixelsPerInch = 96
  TextHeight = 12
  object RzStatusBar1: TRzStatusBar
    Left = 0
    Top = 688
    Width = 992
    Height = 19
    BorderInner = fsNone
    BorderOuter = fsNone
    BorderSides = [sdLeft, sdTop, sdRight, sdBottom]
    BorderWidth = 0
    TabOrder = 0
    object RzStatusPane1: TRzStatusPane
      Left = 0
      Top = 0
      Width = 297
      Height = 19
      Align = alClient
      Caption = #27827#21335#33322#22825#37329#31319#30005#23376#26377#38480#20844#21496
    end
    object RzStatusPane3: TRzStatusPane
      Left = 759
      Top = 0
      Width = 98
      Height = 19
      Align = alRight
      Caption = '255.255.255.255'
    end
    object RzClockStatus1: TRzClockStatus
      Left = 857
      Top = 0
      Width = 135
      Height = 19
      Align = alRight
    end
    object RzStatusPane2: TRzStatusPane
      Left = 297
      Top = 0
      Width = 462
      Height = 19
      Align = alRight
    end
  end
  object RzToolbar1: TRzToolbar
    Left = 0
    Top = 0
    Width = 992
    Height = 29
    Images = ImageList1
    ButtonWidth = 60
    ShowButtonCaptions = True
    TextOptions = ttoSelectiveTextOnRight
    BorderInner = fsNone
    BorderOuter = fsGroove
    BorderSides = [sdTop]
    BorderWidth = 0
    TabOrder = 1
    ToolbarControls = (
      BtnView
      BtnUtilities
      BtnSecurity
      BtnExit
      BtnFinish)
    object BtnExit: TRzToolButton
      Left = 184
      Top = 2
      DisabledIndex = 7
      ImageIndex = 6
      Caption = #36864#20986
      OnClick = BtnExitClick
    end
    object BtnUtilities: TRzToolButton
      Left = 64
      Top = 2
      DisabledIndex = 9
      ImageIndex = 8
      Caption = #37197#32622
      OnClick = BtnUtilitiesClick
    end
    object BtnSecurity: TRzToolButton
      Left = 124
      Top = 2
      DisabledIndex = 11
      ImageIndex = 10
      Caption = #38145#23450
      Enabled = False
      OnClick = BtnSecurityClick
    end
    object BtnFinish: TRzToolButton
      Left = 244
      Top = 2
      DisabledIndex = 13
      ImageIndex = 12
      Caption = #27979#35797
      Visible = False
      OnClick = BtnFinishClick
    end
    object BtnView: TRzToolButton
      Left = 4
      Top = 2
      DisabledIndex = 15
      ImageIndex = 14
      Caption = #29366#24577
      OnClick = BtnViewClick
    end
  end
  object GroupBox1: TGroupBox
    Left = 0
    Top = 29
    Width = 505
    Height = 659
    Align = alLeft
    Caption = #20013#32487#21345#36890#36947
    TabOrder = 2
    object StringGrid1: TStringGrid
      Left = 2
      Top = 14
      Width = 501
      Height = 643
      Align = alClient
      ColCount = 7
      DefaultColWidth = 48
      DefaultRowHeight = 15
      RowCount = 31
      TabOrder = 0
    end
  end
  object GroupBox2: TGroupBox
    Left = 505
    Top = 29
    Width = 383
    Height = 659
    Align = alClient
    Caption = #22352#24109#21345#36890#36947
    TabOrder = 3
    object StringGrid2: TStringGrid
      Left = 2
      Top = 14
      Width = 379
      Height = 643
      Align = alClient
      ColCount = 6
      DefaultColWidth = 48
      DefaultRowHeight = 15
      RowCount = 33
      TabOrder = 0
    end
    object Winsock33: TWinsock
      Left = 107
      Top = 304
      Width = 28
      Height = 28
      OnDataArrival = Winsock33DataArrival
      OnConnectionRequest = Winsock33ConnectionRequest
      OnClose = Winsock33Close
      ControlData = {
        2143341208000000E5020000E502000092D88D24000006000000000000000000
        0000000000000000}
    end
    object Winsock34: TWinsock
      Left = 131
      Top = 304
      Width = 28
      Height = 28
      OnDataArrival = Winsock34DataArrival
      OnConnectionRequest = Winsock34ConnectionRequest
      OnClose = Winsock34Close
      ControlData = {
        2143341208000000E5020000E502000092D88D24000006000000000000000000
        0000000000000000}
    end
    object Winsock35: TWinsock
      Left = 155
      Top = 304
      Width = 28
      Height = 28
      OnDataArrival = Winsock35DataArrival
      OnConnectionRequest = Winsock35ConnectionRequest
      OnClose = Winsock35Close
      ControlData = {
        2143341208000000E5020000E502000092D88D24000006000000000000000000
        0000000000000000}
    end
    object Winsock36: TWinsock
      Left = 179
      Top = 304
      Width = 28
      Height = 28
      OnDataArrival = Winsock36DataArrival
      OnConnectionRequest = Winsock36ConnectionRequest
      OnClose = Winsock36Close
      ControlData = {
        2143341208000000E5020000E502000092D88D24000006000000000000000000
        0000000000000000}
    end
    object Winsock37: TWinsock
      Left = 203
      Top = 304
      Width = 28
      Height = 28
      OnDataArrival = Winsock37DataArrival
      OnConnectionRequest = Winsock37ConnectionRequest
      OnClose = Winsock37Close
      ControlData = {
        2143341208000000E5020000E502000092D88D24000006000000000000000000
        0000000000000000}
    end
    object Winsock38: TWinsock
      Left = 227
      Top = 304
      Width = 28
      Height = 28
      OnDataArrival = Winsock38DataArrival
      OnConnectionRequest = Winsock38ConnectionRequest
      OnClose = Winsock38Close
      ControlData = {
        2143341208000000E5020000E502000092D88D24000006000000000000000000
        0000000000000000}
    end
    object Winsock39: TWinsock
      Left = 251
      Top = 304
      Width = 28
      Height = 28
      OnDataArrival = Winsock39DataArrival
      OnConnectionRequest = Winsock39ConnectionRequest
      OnClose = Winsock39Close
      ControlData = {
        2143341208000000E5020000E502000092D88D24000006000000000000000000
        0000000000000000}
    end
    object Winsock40: TWinsock
      Left = 275
      Top = 304
      Width = 28
      Height = 28
      OnDataArrival = Winsock40DataArrival
      OnConnectionRequest = Winsock40ConnectionRequest
      OnClose = Winsock40Close
      ControlData = {
        2143341208000000E5020000E502000092D88D24000006000000000000000000
        0000000000000000}
    end
    object Winsock41: TWinsock
      Left = 299
      Top = 304
      Width = 28
      Height = 28
      OnDataArrival = Winsock41DataArrival
      OnConnectionRequest = Winsock41ConnectionRequest
      OnClose = Winsock41Close
      ControlData = {
        2143341208000000E5020000E502000092D88D24000006000000000000000000
        0000000000000000}
    end
    object Winsock42: TWinsock
      Left = 323
      Top = 304
      Width = 28
      Height = 28
      OnDataArrival = Winsock42DataArrival
      OnConnectionRequest = Winsock42ConnectionRequest
      OnClose = Winsock42Close
      ControlData = {
        2143341208000000E5020000E502000092D88D24000006000000000000000000
        0000000000000000}
    end
    object Winsock43: TWinsock
      Left = 107
      Top = 336
      Width = 28
      Height = 28
      OnDataArrival = Winsock43DataArrival
      OnConnectionRequest = Winsock43ConnectionRequest
      OnClose = Winsock43Close
      ControlData = {
        2143341208000000E5020000E502000092D88D24000006000000000000000000
        0000000000000000}
    end
    object Winsock44: TWinsock
      Left = 131
      Top = 336
      Width = 28
      Height = 28
      OnDataArrival = Winsock44DataArrival
      OnConnectionRequest = Winsock44ConnectionRequest
      OnClose = Winsock44Close
      ControlData = {
        2143341208000000E5020000E502000092D88D24000006000000000000000000
        0000000000000000}
    end
    object Winsock45: TWinsock
      Left = 155
      Top = 336
      Width = 28
      Height = 28
      OnDataArrival = Winsock45DataArrival
      OnConnectionRequest = Winsock45ConnectionRequest
      OnClose = Winsock45Close
      ControlData = {
        2143341208000000E5020000E502000092D88D24000006000000000000000000
        0000000000000000}
    end
    object Winsock46: TWinsock
      Left = 179
      Top = 336
      Width = 28
      Height = 28
      OnDataArrival = Winsock46DataArrival
      OnConnectionRequest = Winsock46ConnectionRequest
      OnClose = Winsock46Close
      ControlData = {
        2143341208000000E5020000E502000092D88D24000006000000000000000000
        0000000000000000}
    end
    object Winsock47: TWinsock
      Left = 203
      Top = 336
      Width = 28
      Height = 28
      OnDataArrival = Winsock47DataArrival
      OnConnectionRequest = Winsock47ConnectionRequest
      OnClose = Winsock47Close
      ControlData = {
        2143341208000000E5020000E502000092D88D24000006000000000000000000
        0000000000000000}
    end
    object Winsock48: TWinsock
      Left = 227
      Top = 336
      Width = 28
      Height = 28
      OnDataArrival = Winsock48DataArrival
      OnConnectionRequest = Winsock48ConnectionRequest
      OnClose = Winsock48Close
      ControlData = {
        2143341208000000E5020000E502000092D88D24000006000000000000000000
        0000000000000000}
    end
    object Winsock49: TWinsock
      Left = 251
      Top = 336
      Width = 28
      Height = 28
      OnDataArrival = Winsock49DataArrival
      OnConnectionRequest = Winsock49ConnectionRequest
      OnClose = Winsock49Close
      ControlData = {
        2143341208000000E5020000E502000092D88D24000006000000000000000000
        0000000000000000}
    end
    object Winsock50: TWinsock
      Left = 275
      Top = 336
      Width = 28
      Height = 28
      OnDataArrival = Winsock50DataArrival
      OnConnectionRequest = Winsock50ConnectionRequest
      OnClose = Winsock50Close
      ControlData = {
        2143341208000000E5020000E502000092D88D24000006000000000000000000
        0000000000000000}
    end
    object Winsock51: TWinsock
      Left = 299
      Top = 336
      Width = 28
      Height = 28
      OnDataArrival = Winsock51DataArrival
      OnConnectionRequest = Winsock51ConnectionRequest
      OnClose = Winsock51Close
      ControlData = {
        2143341208000000E5020000E502000092D88D24000006000000000000000000
        0000000000000000}
    end
    object Winsock52: TWinsock
      Left = 323
      Top = 336
      Width = 28
      Height = 28
      OnDataArrival = Winsock52DataArrival
      OnConnectionRequest = Winsock52ConnectionRequest
      OnClose = Winsock52Close
      ControlData = {
        2143341208000000E5020000E502000092D88D24000006000000000000000000
        0000000000000000}
    end
    object Winsock53: TWinsock
      Left = 107
      Top = 360
      Width = 28
      Height = 28
      OnDataArrival = Winsock53DataArrival
      OnConnectionRequest = Winsock53ConnectionRequest
      OnClose = Winsock53Close
      ControlData = {
        2143341208000000E5020000E502000092D88D24000006000000000000000000
        0000000000000000}
    end
    object Winsock54: TWinsock
      Left = 131
      Top = 360
      Width = 28
      Height = 28
      OnDataArrival = Winsock54DataArrival
      OnConnectionRequest = Winsock54ConnectionRequest
      OnClose = Winsock54Close
      ControlData = {
        2143341208000000E5020000E502000092D88D24000006000000000000000000
        0000000000000000}
    end
    object Winsock55: TWinsock
      Left = 155
      Top = 360
      Width = 28
      Height = 28
      OnDataArrival = Winsock55DataArrival
      OnConnectionRequest = Winsock55ConnectionRequest
      OnClose = Winsock55Close
      ControlData = {
        2143341208000000E5020000E502000092D88D24000006000000000000000000
        0000000000000000}
    end
    object Winsock56: TWinsock
      Left = 179
      Top = 360
      Width = 28
      Height = 28
      OnDataArrival = Winsock56DataArrival
      OnConnectionRequest = Winsock56ConnectionRequest
      OnClose = Winsock56Close
      ControlData = {
        2143341208000000E5020000E502000092D88D24000006000000000000000000
        0000000000000000}
    end
    object Winsock57: TWinsock
      Left = 203
      Top = 360
      Width = 28
      Height = 28
      OnDataArrival = Winsock57DataArrival
      OnConnectionRequest = Winsock57ConnectionRequest
      OnClose = Winsock57Close
      ControlData = {
        2143341208000000E5020000E502000092D88D24000006000000000000000000
        0000000000000000}
    end
    object Winsock58: TWinsock
      Left = 227
      Top = 360
      Width = 28
      Height = 28
      OnDataArrival = Winsock58DataArrival
      OnConnectionRequest = Winsock58ConnectionRequest
      OnClose = Winsock58Close
      ControlData = {
        2143341208000000E5020000E502000092D88D24000006000000000000000000
        0000000000000000}
    end
    object Winsock59: TWinsock
      Left = 251
      Top = 360
      Width = 28
      Height = 28
      OnDataArrival = Winsock59DataArrival
      OnConnectionRequest = Winsock59ConnectionRequest
      OnClose = Winsock59Close
      ControlData = {
        2143341208000000E5020000E502000092D88D24000006000000000000000000
        0000000000000000}
    end
    object Winsock60: TWinsock
      Left = 275
      Top = 360
      Width = 28
      Height = 28
      OnDataArrival = Winsock60DataArrival
      OnConnectionRequest = Winsock60ConnectionRequest
      OnClose = Winsock60Close
      ControlData = {
        2143341208000000E5020000E502000092D88D24000006000000000000000000
        0000000000000000}
    end
    object Winsock61: TWinsock
      Left = 299
      Top = 360
      Width = 28
      Height = 28
      OnDataArrival = Winsock61DataArrival
      OnConnectionRequest = Winsock61ConnectionRequest
      OnClose = Winsock61Close
      ControlData = {
        2143341208000000E5020000E502000092D88D24000006000000000000000000
        0000000000000000}
    end
    object Winsock62: TWinsock
      Left = 323
      Top = 360
      Width = 28
      Height = 28
      OnDataArrival = Winsock62DataArrival
      OnConnectionRequest = Winsock62ConnectionRequest
      OnClose = Winsock62Close
      ControlData = {
        2143341208000000E5020000E502000092D88D24000006000000000000000000
        0000000000000000}
    end
    object Winsock63: TWinsock
      Left = 107
      Top = 384
      Width = 28
      Height = 28
      OnDataArrival = Winsock63DataArrival
      OnConnectionRequest = Winsock63ConnectionRequest
      OnClose = Winsock63Close
      ControlData = {
        2143341208000000E5020000E502000092D88D24000006000000000000000000
        0000000000000000}
    end
    object Winsock64: TWinsock
      Left = 131
      Top = 384
      Width = 28
      Height = 28
      OnDataArrival = Winsock64DataArrival
      OnConnectionRequest = Winsock64ConnectionRequest
      OnClose = Winsock64Close
      ControlData = {
        2143341208000000E5020000E502000092D88D24000006000000000000000000
        0000000000000000}
    end
  end
  object GroupBox3: TGroupBox
    Left = 888
    Top = 29
    Width = 104
    Height = 659
    Align = alRight
    Caption = #22352#24109#35774#32622
    TabOrder = 4
    object CheckBox1: TCheckBox
      Left = 8
      Top = 16
      Width = 41
      Height = 17
      Caption = '1'#21495
      TabOrder = 0
      OnClick = CheckBox1Click
    end
    object CheckBox2: TCheckBox
      Left = 8
      Top = 32
      Width = 41
      Height = 17
      Caption = '2'#21495
      TabOrder = 1
      OnClick = CheckBox2Click
    end
    object CheckBox3: TCheckBox
      Left = 8
      Top = 48
      Width = 41
      Height = 17
      Caption = '3'#21495
      TabOrder = 2
      OnClick = CheckBox3Click
    end
    object CheckBox4: TCheckBox
      Left = 8
      Top = 64
      Width = 41
      Height = 17
      Caption = '4'#21495
      TabOrder = 3
      OnClick = CheckBox4Click
    end
    object CheckBox5: TCheckBox
      Left = 8
      Top = 80
      Width = 41
      Height = 17
      Caption = '5'#21495
      TabOrder = 4
      OnClick = CheckBox5Click
    end
    object CheckBox6: TCheckBox
      Left = 8
      Top = 96
      Width = 41
      Height = 17
      Caption = '6'#21495
      TabOrder = 5
      OnClick = CheckBox6Click
    end
    object CheckBox7: TCheckBox
      Left = 8
      Top = 112
      Width = 41
      Height = 17
      Caption = '7'#21495
      TabOrder = 6
      OnClick = CheckBox7Click
    end
    object CheckBox8: TCheckBox
      Left = 8
      Top = 128
      Width = 41
      Height = 17
      Caption = '8'#21495
      TabOrder = 7
      OnClick = CheckBox8Click
    end
    object CheckBox9: TCheckBox
      Left = 8
      Top = 144
      Width = 41
      Height = 17
      Caption = '9'#21495
      TabOrder = 8
      OnClick = CheckBox9Click
    end
    object CheckBox10: TCheckBox
      Left = 8
      Top = 160
      Width = 41
      Height = 17
      Caption = '10'#21495
      TabOrder = 9
      OnClick = CheckBox10Click
    end
    object CheckBox11: TCheckBox
      Left = 8
      Top = 176
      Width = 41
      Height = 17
      Caption = '11'#21495
      TabOrder = 10
      OnClick = CheckBox11Click
    end
    object CheckBox12: TCheckBox
      Left = 8
      Top = 192
      Width = 41
      Height = 17
      Caption = '12'#21495
      TabOrder = 11
      OnClick = CheckBox12Click
    end
    object CheckBox13: TCheckBox
      Left = 8
      Top = 208
      Width = 41
      Height = 17
      Caption = '13'#21495
      TabOrder = 12
      OnClick = CheckBox13Click
    end
    object CheckBox14: TCheckBox
      Left = 8
      Top = 224
      Width = 41
      Height = 17
      Caption = '14'#21495
      TabOrder = 13
      OnClick = CheckBox14Click
    end
    object CheckBox15: TCheckBox
      Left = 8
      Top = 240
      Width = 41
      Height = 17
      Caption = '15'#21495
      TabOrder = 14
      OnClick = CheckBox15Click
    end
    object CheckBox16: TCheckBox
      Left = 8
      Top = 256
      Width = 41
      Height = 17
      Caption = '16'#21495
      TabOrder = 15
      OnClick = CheckBox16Click
    end
    object CheckBox17: TCheckBox
      Left = 8
      Top = 272
      Width = 41
      Height = 17
      Caption = '17'#21495
      TabOrder = 16
      OnClick = CheckBox17Click
    end
    object CheckBox18: TCheckBox
      Left = 8
      Top = 288
      Width = 41
      Height = 17
      Caption = '18'#21495
      TabOrder = 17
      OnClick = CheckBox18Click
    end
    object CheckBox19: TCheckBox
      Left = 8
      Top = 304
      Width = 41
      Height = 17
      Caption = '19'#21495
      TabOrder = 18
      OnClick = CheckBox19Click
    end
    object CheckBox20: TCheckBox
      Left = 8
      Top = 320
      Width = 41
      Height = 17
      Caption = '20'#21495
      TabOrder = 19
      OnClick = CheckBox20Click
    end
    object CheckBox21: TCheckBox
      Left = 8
      Top = 336
      Width = 41
      Height = 17
      Caption = '21'#21495
      TabOrder = 20
      OnClick = CheckBox21Click
    end
    object CheckBox22: TCheckBox
      Left = 8
      Top = 352
      Width = 41
      Height = 17
      Caption = '22'#21495
      TabOrder = 21
      OnClick = CheckBox22Click
    end
    object CheckBox23: TCheckBox
      Left = 8
      Top = 368
      Width = 41
      Height = 17
      Caption = '23'#21495
      TabOrder = 22
      OnClick = CheckBox23Click
    end
    object CheckBox24: TCheckBox
      Left = 8
      Top = 384
      Width = 41
      Height = 17
      Caption = '24'#21495
      TabOrder = 23
      OnClick = CheckBox24Click
    end
    object CheckBox25: TCheckBox
      Left = 8
      Top = 400
      Width = 41
      Height = 17
      Caption = '25'#21495
      TabOrder = 24
      OnClick = CheckBox25Click
    end
    object CheckBox26: TCheckBox
      Left = 8
      Top = 416
      Width = 41
      Height = 17
      Caption = '26'#21495
      TabOrder = 25
      OnClick = CheckBox26Click
    end
    object CheckBox27: TCheckBox
      Left = 8
      Top = 432
      Width = 41
      Height = 17
      Caption = '27'#21495
      TabOrder = 26
      OnClick = CheckBox27Click
    end
    object CheckBox28: TCheckBox
      Left = 8
      Top = 448
      Width = 41
      Height = 17
      Caption = '28'#21495
      TabOrder = 27
      OnClick = CheckBox28Click
    end
    object CheckBox29: TCheckBox
      Left = 8
      Top = 464
      Width = 41
      Height = 17
      Caption = '29'#21495
      TabOrder = 28
      OnClick = CheckBox29Click
    end
    object CheckBox30: TCheckBox
      Left = 8
      Top = 480
      Width = 41
      Height = 17
      Caption = '30'#21495
      TabOrder = 29
      OnClick = CheckBox30Click
    end
    object CheckBox31: TCheckBox
      Left = 8
      Top = 496
      Width = 41
      Height = 17
      Caption = '31'#21495
      TabOrder = 30
      OnClick = CheckBox31Click
    end
    object CheckBox32: TCheckBox
      Left = 8
      Top = 512
      Width = 41
      Height = 17
      Caption = '32'#21495
      TabOrder = 31
      OnClick = CheckBox32Click
    end
    object CheckBox33: TCheckBox
      Left = 56
      Top = 16
      Width = 41
      Height = 17
      Caption = '33'#21495
      TabOrder = 32
      OnClick = CheckBox33Click
    end
    object CheckBox34: TCheckBox
      Left = 56
      Top = 32
      Width = 41
      Height = 17
      Caption = '34'#21495
      TabOrder = 33
      OnClick = CheckBox34Click
    end
    object CheckBox35: TCheckBox
      Left = 56
      Top = 48
      Width = 41
      Height = 17
      Caption = '35'#21495
      TabOrder = 34
      OnClick = CheckBox35Click
    end
    object CheckBox36: TCheckBox
      Left = 56
      Top = 64
      Width = 41
      Height = 17
      Caption = '36'#21495
      TabOrder = 35
      OnClick = CheckBox36Click
    end
    object CheckBox37: TCheckBox
      Left = 56
      Top = 80
      Width = 41
      Height = 17
      Caption = '37'#21495
      TabOrder = 36
      OnClick = CheckBox37Click
    end
    object CheckBox38: TCheckBox
      Left = 56
      Top = 96
      Width = 41
      Height = 17
      Caption = '38'#21495
      TabOrder = 37
      OnClick = CheckBox38Click
    end
    object CheckBox39: TCheckBox
      Left = 56
      Top = 112
      Width = 41
      Height = 17
      Caption = '39'#21495
      TabOrder = 38
      OnClick = CheckBox39Click
    end
    object CheckBox40: TCheckBox
      Left = 56
      Top = 128
      Width = 41
      Height = 17
      Caption = '40'#21495
      TabOrder = 39
      OnClick = CheckBox40Click
    end
    object CheckBox41: TCheckBox
      Left = 56
      Top = 144
      Width = 41
      Height = 17
      Caption = '41'#21495
      TabOrder = 40
      OnClick = CheckBox41Click
    end
    object CheckBox42: TCheckBox
      Left = 56
      Top = 160
      Width = 41
      Height = 17
      Caption = '42'#21495
      TabOrder = 41
      OnClick = CheckBox42Click
    end
    object CheckBox43: TCheckBox
      Left = 56
      Top = 176
      Width = 41
      Height = 17
      Caption = '43'#21495
      TabOrder = 42
      OnClick = CheckBox43Click
    end
    object CheckBox44: TCheckBox
      Left = 56
      Top = 192
      Width = 41
      Height = 17
      Caption = '44'#21495
      TabOrder = 43
      OnClick = CheckBox44Click
    end
    object CheckBox45: TCheckBox
      Left = 56
      Top = 208
      Width = 41
      Height = 17
      Caption = '45'#21495
      TabOrder = 44
      OnClick = CheckBox45Click
    end
    object CheckBox46: TCheckBox
      Left = 56
      Top = 224
      Width = 41
      Height = 17
      Caption = '46'#21495
      TabOrder = 45
      OnClick = CheckBox46Click
    end
    object CheckBox47: TCheckBox
      Left = 56
      Top = 240
      Width = 41
      Height = 17
      Caption = '47'#21495
      TabOrder = 46
      OnClick = CheckBox47Click
    end
    object CheckBox48: TCheckBox
      Left = 56
      Top = 256
      Width = 41
      Height = 17
      Caption = '48'#21495
      TabOrder = 47
      OnClick = CheckBox48Click
    end
    object CheckBox49: TCheckBox
      Left = 56
      Top = 272
      Width = 41
      Height = 17
      Caption = '49'#21495
      TabOrder = 48
      OnClick = CheckBox49Click
    end
    object CheckBox50: TCheckBox
      Left = 56
      Top = 288
      Width = 41
      Height = 17
      Caption = '50'#21495
      TabOrder = 49
      OnClick = CheckBox50Click
    end
    object CheckBox51: TCheckBox
      Left = 56
      Top = 304
      Width = 41
      Height = 17
      Caption = '51'#21495
      TabOrder = 50
      OnClick = CheckBox51Click
    end
    object CheckBox52: TCheckBox
      Left = 56
      Top = 320
      Width = 41
      Height = 17
      Caption = '52'#21495
      TabOrder = 51
      OnClick = CheckBox52Click
    end
    object CheckBox53: TCheckBox
      Left = 56
      Top = 336
      Width = 41
      Height = 17
      Caption = '53'#21495
      TabOrder = 52
      OnClick = CheckBox53Click
    end
    object CheckBox54: TCheckBox
      Left = 56
      Top = 352
      Width = 41
      Height = 17
      Caption = '54'#21495
      TabOrder = 53
      OnClick = CheckBox54Click
    end
    object CheckBox55: TCheckBox
      Left = 56
      Top = 368
      Width = 41
      Height = 17
      Caption = '55'#21495
      TabOrder = 54
      OnClick = CheckBox55Click
    end
    object CheckBox56: TCheckBox
      Left = 56
      Top = 384
      Width = 41
      Height = 17
      Caption = '56'#21495
      TabOrder = 55
      OnClick = CheckBox56Click
    end
    object CheckBox57: TCheckBox
      Left = 56
      Top = 400
      Width = 41
      Height = 17
      Caption = '57'#21495
      TabOrder = 56
      OnClick = CheckBox57Click
    end
    object CheckBox58: TCheckBox
      Left = 56
      Top = 416
      Width = 41
      Height = 17
      Caption = '58'#21495
      TabOrder = 57
      OnClick = CheckBox58Click
    end
    object CheckBox59: TCheckBox
      Left = 56
      Top = 432
      Width = 41
      Height = 17
      Caption = '59'#21495
      TabOrder = 58
      OnClick = CheckBox59Click
    end
    object CheckBox60: TCheckBox
      Left = 56
      Top = 448
      Width = 41
      Height = 17
      Caption = '60'#21495
      TabOrder = 59
      OnClick = CheckBox60Click
    end
    object CheckBox61: TCheckBox
      Left = 56
      Top = 464
      Width = 41
      Height = 17
      Caption = '61'#21495
      TabOrder = 60
      OnClick = CheckBox61Click
    end
    object CheckBox62: TCheckBox
      Left = 56
      Top = 480
      Width = 41
      Height = 17
      Caption = '62'#21495
      TabOrder = 61
      OnClick = CheckBox62Click
    end
    object CheckBox63: TCheckBox
      Left = 56
      Top = 496
      Width = 41
      Height = 17
      Caption = '63'#21495
      TabOrder = 62
      OnClick = CheckBox63Click
    end
    object CheckBox64: TCheckBox
      Left = 56
      Top = 512
      Width = 41
      Height = 17
      Caption = '64'#21495
      TabOrder = 63
      OnClick = CheckBox64Click
    end
  end
  object Winsock1: TWinsock
    Left = 608
    Top = 168
    Width = 28
    Height = 28
    OnDataArrival = Winsock1DataArrival
    OnConnectionRequest = Winsock1ConnectionRequest
    OnClose = Winsock1Close
    ControlData = {
      2143341208000000E5020000E502000092D88D24000006000000000000000000
      0000000000000000}
  end
  object Winsock2: TWinsock
    Left = 632
    Top = 168
    Width = 28
    Height = 28
    OnDataArrival = Winsock2DataArrival
    OnConnectionRequest = Winsock2ConnectionRequest
    OnClose = Winsock2Close
    ControlData = {
      2143341208000000E5020000E502000092D88D24000006000000000000000000
      0000000000000000}
  end
  object Winsock3: TWinsock
    Left = 656
    Top = 168
    Width = 28
    Height = 28
    OnDataArrival = Winsock3DataArrival
    OnConnectionRequest = Winsock3ConnectionRequest
    OnClose = Winsock3Close
    ControlData = {
      2143341208000000E5020000E502000092D88D24000006000000000000000000
      0000000000000000}
  end
  object Winsock4: TWinsock
    Left = 680
    Top = 168
    Width = 28
    Height = 28
    OnDataArrival = Winsock4DataArrival
    OnConnectionRequest = Winsock4ConnectionRequest
    OnClose = Winsock4Close
    ControlData = {
      2143341208000000E5020000E502000092D88D24000006000000000000000000
      0000000000000000}
  end
  object Winsock5: TWinsock
    Left = 704
    Top = 168
    Width = 28
    Height = 28
    OnDataArrival = Winsock5DataArrival
    OnConnectionRequest = Winsock5ConnectionRequest
    OnClose = Winsock5Close
    ControlData = {
      2143341208000000E5020000E502000092D88D24000006000000000000000000
      0000000000000000}
  end
  object Winsock6: TWinsock
    Left = 728
    Top = 168
    Width = 28
    Height = 28
    OnDataArrival = Winsock6DataArrival
    OnConnectionRequest = Winsock6ConnectionRequest
    OnClose = Winsock6Close
    ControlData = {
      2143341208000000E5020000E502000092D88D24000006000000000000000000
      0000000000000000}
  end
  object Winsock7: TWinsock
    Left = 752
    Top = 168
    Width = 28
    Height = 28
    OnDataArrival = Winsock7DataArrival
    OnConnectionRequest = Winsock7ConnectionRequest
    OnClose = Winsock7Close
    ControlData = {
      2143341208000000E5020000E502000092D88D24000006000000000000000000
      0000000000000000}
  end
  object Winsock8: TWinsock
    Left = 776
    Top = 168
    Width = 28
    Height = 28
    OnDataArrival = Winsock8DataArrival
    OnConnectionRequest = Winsock8ConnectionRequest
    OnClose = Winsock8Close
    ControlData = {
      2143341208000000E5020000E502000092D88D24000006000000000000000000
      0000000000000000}
  end
  object Winsock9: TWinsock
    Left = 800
    Top = 168
    Width = 28
    Height = 28
    OnDataArrival = Winsock9DataArrival
    OnConnectionRequest = Winsock9ConnectionRequest
    OnClose = Winsock9Close
    ControlData = {
      2143341208000000E5020000E502000092D88D24000006000000000000000000
      0000000000000000}
  end
  object Winsock10: TWinsock
    Left = 824
    Top = 168
    Width = 28
    Height = 28
    OnDataArrival = Winsock10DataArrival
    OnConnectionRequest = Winsock10ConnectionRequest
    OnClose = Winsock10Close
    ControlData = {
      2143341208000000E5020000E502000092D88D24000006000000000000000000
      0000000000000000}
  end
  object Winsock11: TWinsock
    Left = 608
    Top = 192
    Width = 28
    Height = 28
    OnDataArrival = Winsock11DataArrival
    OnConnectionRequest = Winsock11ConnectionRequest
    OnClose = Winsock11Close
    ControlData = {
      2143341208000000E5020000E502000092D88D24000006000000000000000000
      0000000000000000}
  end
  object Winsock12: TWinsock
    Left = 632
    Top = 192
    Width = 28
    Height = 28
    OnDataArrival = Winsock12DataArrival
    OnConnectionRequest = Winsock12ConnectionRequest
    OnClose = Winsock12Close
    ControlData = {
      2143341208000000E5020000E502000092D88D24000006000000000000000000
      0000000000000000}
  end
  object Winsock13: TWinsock
    Left = 656
    Top = 192
    Width = 28
    Height = 28
    OnDataArrival = Winsock13DataArrival
    OnConnectionRequest = Winsock13ConnectionRequest
    OnClose = Winsock13Close
    ControlData = {
      2143341208000000E5020000E502000092D88D24000006000000000000000000
      0000000000000000}
  end
  object Winsock14: TWinsock
    Left = 680
    Top = 192
    Width = 28
    Height = 28
    OnDataArrival = Winsock14DataArrival
    OnConnectionRequest = Winsock14ConnectionRequest
    OnClose = Winsock14Close
    ControlData = {
      2143341208000000E5020000E502000092D88D24000006000000000000000000
      0000000000000000}
  end
  object Winsock15: TWinsock
    Left = 704
    Top = 192
    Width = 28
    Height = 28
    OnDataArrival = Winsock15DataArrival
    OnConnectionRequest = Winsock15ConnectionRequest
    OnClose = Winsock15Close
    ControlData = {
      2143341208000000E5020000E502000092D88D24000006000000000000000000
      0000000000000000}
  end
  object Winsock16: TWinsock
    Left = 728
    Top = 192
    Width = 28
    Height = 28
    OnDataArrival = Winsock16DataArrival
    OnConnectionRequest = Winsock16ConnectionRequest
    OnClose = Winsock16Close
    ControlData = {
      2143341208000000E5020000E502000092D88D24000006000000000000000000
      0000000000000000}
  end
  object Winsock17: TWinsock
    Left = 752
    Top = 192
    Width = 28
    Height = 28
    OnDataArrival = Winsock17DataArrival
    OnConnectionRequest = Winsock17ConnectionRequest
    OnClose = Winsock17Close
    ControlData = {
      2143341208000000E5020000E502000092D88D24000006000000000000000000
      0000000000000000}
  end
  object Winsock18: TWinsock
    Left = 776
    Top = 192
    Width = 28
    Height = 28
    OnDataArrival = Winsock18DataArrival
    OnConnectionRequest = Winsock18ConnectionRequest
    OnClose = Winsock18Close
    ControlData = {
      2143341208000000E5020000E502000092D88D24000006000000000000000000
      0000000000000000}
  end
  object Winsock19: TWinsock
    Left = 800
    Top = 192
    Width = 28
    Height = 28
    OnDataArrival = Winsock19DataArrival
    OnConnectionRequest = Winsock19ConnectionRequest
    OnClose = Winsock19Close
    ControlData = {
      2143341208000000E5020000E502000092D88D24000006000000000000000000
      0000000000000000}
  end
  object Winsock20: TWinsock
    Left = 824
    Top = 192
    Width = 28
    Height = 28
    OnDataArrival = Winsock20DataArrival
    OnConnectionRequest = Winsock20ConnectionRequest
    OnClose = Winsock20Close
    ControlData = {
      2143341208000000E5020000E502000092D88D24000006000000000000000000
      0000000000000000}
  end
  object Winsock21: TWinsock
    Left = 608
    Top = 216
    Width = 28
    Height = 28
    OnDataArrival = Winsock21DataArrival
    OnConnectionRequest = Winsock21ConnectionRequest
    OnClose = Winsock21Close
    ControlData = {
      2143341208000000E5020000E502000092D88D24000006000000000000000000
      0000000000000000}
  end
  object Winsock22: TWinsock
    Left = 632
    Top = 216
    Width = 28
    Height = 28
    OnDataArrival = Winsock22DataArrival
    OnConnectionRequest = Winsock22ConnectionRequest
    OnClose = Winsock22Close
    ControlData = {
      2143341208000000E5020000E502000092D88D24000006000000000000000000
      0000000000000000}
  end
  object Winsock23: TWinsock
    Left = 656
    Top = 216
    Width = 28
    Height = 28
    OnDataArrival = Winsock23DataArrival
    OnConnectionRequest = Winsock23ConnectionRequest
    OnClose = Winsock23Close
    ControlData = {
      2143341208000000E5020000E502000092D88D24000006000000000000000000
      0000000000000000}
  end
  object Winsock24: TWinsock
    Left = 680
    Top = 216
    Width = 28
    Height = 28
    OnDataArrival = Winsock24DataArrival
    OnConnectionRequest = Winsock24ConnectionRequest
    OnClose = Winsock24Close
    ControlData = {
      2143341208000000E5020000E502000092D88D24000006000000000000000000
      0000000000000000}
  end
  object Winsock25: TWinsock
    Left = 704
    Top = 216
    Width = 28
    Height = 28
    OnDataArrival = Winsock25DataArrival
    OnConnectionRequest = Winsock25ConnectionRequest
    OnClose = Winsock25Close
    ControlData = {
      2143341208000000E5020000E502000092D88D24000006000000000000000000
      0000000000000000}
  end
  object Winsock26: TWinsock
    Left = 728
    Top = 216
    Width = 28
    Height = 28
    OnDataArrival = Winsock26DataArrival
    OnConnectionRequest = Winsock26ConnectionRequest
    OnClose = Winsock26Close
    ControlData = {
      2143341208000000E5020000E502000092D88D24000006000000000000000000
      0000000000000000}
  end
  object Winsock27: TWinsock
    Left = 752
    Top = 216
    Width = 28
    Height = 28
    OnDataArrival = Winsock27DataArrival
    OnConnectionRequest = Winsock27ConnectionRequest
    OnClose = Winsock27Close
    ControlData = {
      2143341208000000E5020000E502000092D88D24000006000000000000000000
      0000000000000000}
  end
  object Winsock28: TWinsock
    Left = 776
    Top = 216
    Width = 28
    Height = 28
    OnDataArrival = Winsock28DataArrival
    OnConnectionRequest = Winsock28ConnectionRequest
    OnClose = Winsock28Close
    ControlData = {
      2143341208000000E5020000E502000092D88D24000006000000000000000000
      0000000000000000}
  end
  object Winsock29: TWinsock
    Left = 800
    Top = 216
    Width = 28
    Height = 28
    OnDataArrival = Winsock29DataArrival
    OnConnectionRequest = Winsock29ConnectionRequest
    OnClose = Winsock29Close
    ControlData = {
      2143341208000000E5020000E502000092D88D24000006000000000000000000
      0000000000000000}
  end
  object Winsock30: TWinsock
    Left = 824
    Top = 216
    Width = 28
    Height = 28
    OnDataArrival = Winsock30DataArrival
    OnConnectionRequest = Winsock30ConnectionRequest
    OnClose = Winsock30Close
    ControlData = {
      2143341208000000E5020000E502000092D88D24000006000000000000000000
      0000000000000000}
  end
  object WinsockWait: TWinsock
    Left = 704
    Top = 272
    Width = 28
    Height = 28
    OnConnectionRequest = WinsockWaitConnectionRequest
    OnClose = WinsockWaitClose
    ControlData = {
      2143341208000000E5020000E502000092D88D24000006000000000000000000
      0000000000000000}
  end
  object WinsockControl: TWinsock
    Left = 760
    Top = 272
    Width = 28
    Height = 28
    OnConnectionRequest = WinsockControlConnectionRequest
    ControlData = {
      2143341208000000E5020000E502000092D88D24000006000000000000000000
      0000000000000000}
  end
  object Winsock31: TWinsock
    Left = 608
    Top = 240
    Width = 28
    Height = 28
    OnDataArrival = Winsock31DataArrival
    OnConnectionRequest = Winsock31ConnectionRequest
    OnClose = Winsock31Close
    ControlData = {
      2143341208000000E5020000E502000092D88D24000006000000000000000000
      0000000000000000}
  end
  object Winsock32: TWinsock
    Left = 632
    Top = 240
    Width = 28
    Height = 28
    OnDataArrival = Winsock32DataArrival
    OnConnectionRequest = Winsock32ConnectionRequest
    OnClose = Winsock32Close
    ControlData = {
      2143341208000000E5020000E502000092D88D24000006000000000000000000
      0000000000000000}
  end
  object ImageList1: TImageList
    Left = 528
    Top = 8
    Bitmap = {
      494C010110001400040010001000FFFFFFFFFF10FFFFFFFFFFFFFFFF424D3600
      0000000000003600000028000000400000005000000001002000000000000050
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000084848400848484000000000000000000000000000000
      00000000000000000000000000000000000000000000CECECE00C6C6C600E7E7
      E700000000000000000000000000000000000000000000000000000000000000
      00000000000000000000000000000000000000000000CECECE00C6C6C600E7E7
      E700000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000008484840000000000000000008484840000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000008484840084848400848484008484840000000000000000000000
      000000000000000000000000000000000000CECECE00639C9C0063639C009C9C
      9C00E7E7E7000000000000000000000000000000000000000000000000000000
      000000000000000000000000000000000000CECECE00B5B5B5009C9C9C009C9C
      9C00E7E7E7000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      000084848400FFFFFF0000000000000000008484840000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      000084848400FFFFFF0084848400848484008484840000000000000000000000
      0000000000000000000000000000000000000000000063CEFF00319CCE006363
      9C009C9C9C00E7E7E70000000000000000000000000000000000000000000000
      00000000000000000000000000000000000000000000CECECE00B5B5B5009C9C
      9C009C9C9C00E7E7E70000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000FFFFFF00FFFFFF0000000000FFFFFF00FFFFFF0000000000000000000000
      0000000000000000000000000000000000000000000000000000000000008484
      8400FFFFFF00FFFFFF0084848400FFFFFF00FFFFFF0000000000000000000000
      00000000000000000000000000000000000000000000CECEFF0063CEFF00319C
      CE0063639C009C9C9C00E7E7E700000000000000000000000000000000000000
      00000000000000000000000000000000000000000000E7E7E700CECECE00B5B5
      B5009C9C9C009C9C9C00E7E7E700000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000FFFFFF000000000000000000FFFFFF008484840084848400000000000000
      0000000000000000000000000000000000000000000000000000848484008484
      8400FFFFFF008484840084848400FFFFFF008484840084848400000000000000
      0000000000000000000000000000000000000000000000000000CECEFF0063CE
      FF00319CCE0063639C009C9C9C00E7E7E7000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000E7E7E700CECE
      CE00B5B5B5009C9C9C009C9C9C00E7E7E7000000000000000000000000000000
      0000000000000000000000000000000000000000000084848400000000000000
      0000000000000000000000000000848484000000000000000000000000000000
      000000000000000000000000000063639C000000000084848400848484008484
      8400848484008484840084848400848484008484840084848400000000000000
      00000000000000000000000000009C9C9C00000000000000000000000000CECE
      FF0063CEFF00319CCE0063639C00CECECE00FFCECE00CE9C9C00CE9C9C00CE9C
      9C00CECE9C00E7E7E7000000000000000000000000000000000000000000E7E7
      E700CECECE00B5B5B5009C9C9C00CECECE00E7E7E7009C9C9C009C9C9C009C9C
      9C00B5B5B500E7E7E700000000000000000084848400FFFFFF00FFFFFF000000
      0000FFFFFF000000000000000000C6C6C6000000000000000000000000000000
      0000000000000000000063639C00319CCE0084848400FFFFFF00FFFFFF008484
      8400FFFFFF008484840084848400C6C6C6008484840084848400848484000000
      000000000000000000009C9C9C00CECECE000000000000000000000000000000
      0000CECEFF0063CEFF00B5B5B500CE9C9C00CECE9C00F7EFBD00FFFFCE00F7EF
      BD00F7EFBD00CE9C9C00EFC6DE00000000000000000000000000000000000000
      0000E7E7E700CECECE00B5B5B5009C9C9C00B5B5B500CECECE00CECECE00CECE
      CE00CECECE009C9C9C00E7E7E7000000000084848400FFFFFF00000000000000
      0000FFFFFF0084848400C6C6C600FFFFFF008484840000000000FFFFFF008484
      84000000000063639C00319CCE0063CEFF0084848400FFFFFF00848484008484
      8400FFFFFF0084848400C6C6C600FFFFFF008484840084848400FFFFFF008484
      8400000000009C9C9C00CECECE00FFFFFF000000000000000000000000000000
      000000000000E7E7E700CE9C9C00FFCE9C00FFFFCE00FFFFCE00FFFFCE00FFFF
      FF00FFFFFF00FFFFFF00CE9C9C00E7E7E7000000000000000000000000000000
      000000000000E7E7E7009C9C9C00E7E7E700CECECE00CECECE00CECECE00E7E7
      E700E7E7E700E7E7E7009C9C9C00E7E7E7008484840000000000000000000000
      0000848484000000000000000000FFFFFF0000000000FFFFFF00FFFFFF00FFFF
      FF0063639C00319CCE0063CEFF00000000008484840084848400848484008484
      8400848484008484840084848400FFFFFF0084848400FFFFFF00FFFFFF00FFFF
      FF009C9C9C00CECECE00FFFFFF00000000000000000000000000000000000000
      000000000000FFCECE00CECE9C00FFFFCE00F7EFBD00FFFFCE00FFFFCE00FFFF
      FF00FFFFFF00FFFFFF00F7EFBD00CECE9C000000000000000000000000000000
      000000000000E7E7E700B5B5B500CECECE00CECECE00CECECE00CECECE00E7E7
      E700E7E7E700E7E7E700CECECE00B5B5B5000000000000000000000000008484
      8400000000000000000000000000000000000000000000000000FFFFFF006363
      9C00319CCE0063CEFF0000000000000000000000000084848400848484008484
      8400848484008484840084848400848484008484840084848400FFFFFF009C9C
      9C00CECECE00FFFFFF0000000000000000000000000000000000000000000000
      000000000000CECE9C00FFCE9C00F7EFBD00F7EFBD00FFFFCE00FFFFCE00FFFF
      CE00FFFFFF00FFFFFF00F7EFBD00CE9C9C000000000000000000000000000000
      000000000000B5B5B500E7E7E700CECECE00CECECE00CECECE00CECECE00CECE
      CE00E7E7E700E7E7E700CECECE009C9C9C000000000000000000C6C6C600C6C6
      C6000000000000000000FFFFFF0000000000000000000000000063639C00319C
      CE0063CEFF000000000000000000000000000000000084848400C6C6C600C6C6
      C6008484840084848400FFFFFF008484840084848400848484009C9C9C00CECE
      CE00FFFFFF000000000000000000000000000000000000000000000000000000
      000000000000CE9C9C00F7EFBD00F7EFBD00F7EFBD00F7EFBD00FFFFCE00FFFF
      CE00FFFFCE00FFFFCE00FFFFCE00CE9C9C000000000000000000000000000000
      0000000000009C9C9C00CECECE00CECECE00CECECE00CECECE00CECECE00CECE
      CE00CECECE00CECECE00CECECE009C9C9C000000000084848400C6C6C600FFFF
      FF0000000000FFFFFF00FFFFFF00FFFFFF000000000063639C00319CCE0063CE
      FF00000000000000000000000000000000000000000084848400C6C6C600FFFF
      FF0084848400FFFFFF00FFFFFF00FFFFFF00848484009C9C9C00CECECE00FFFF
      FF00000000000000000000000000000000000000000000000000000000000000
      000000000000CECE9C00F7EFBD00FFFFCE00F7EFBD00F7EFBD00F7EFBD00FFFF
      CE00FFFFCE00FFFFCE00F7EFBD00CE9C9C000000000000000000000000000000
      000000000000B5B5B500CECECE00CECECE00CECECE00CECECE00CECECE00CECE
      CE00CECECE00CECECE00CECECE009C9C9C000000000000000000848484008484
      84000000000000000000FFFFFF00FFFFFF0063639C00319CCE0063CEFF000000
      0000000000000000000000000000000000000000000000000000848484008484
      84008484840084848400FFFFFF00FFFFFF009C9C9C00CECECE00FFFFFF000000
      0000000000000000000000000000000000000000000000000000000000000000
      000000000000FFCECE00CECE9C00FFFFFF00FFFFFF00F7EFBD00F7EFBD00F7EF
      BD00F7EFBD00FFFFCE00CECE9C00CECE9C000000000000000000000000000000
      000000000000E7E7E700B5B5B500E7E7E700E7E7E700CECECE00CECECE00CECE
      CE00CECECE00CECECE00B5B5B500B5B5B5000000000000000000000000000000
      000000000000000000000000000063639C00319CCE0063CEFF00000000000000
      0000000000000000000000000000000000000000000000000000000000008484
      84008484840084848400848484009C9C9C00CECECE00FFFFFF00000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      000000000000E7E7E700CE9C9C00EFC6DE00FFFFFF00FFFFCE00F7EFBD00F7EF
      BD00F7EFBD00FFCE9C00CE9C9C00E7E7E7000000000000000000000000000000
      000000000000E7E7E7009C9C9C00E7E7E700E7E7E700CECECE00CECECE00CECE
      CE00CECECE00E7E7E7009C9C9C00E7E7E7000000000000000000000000000000
      0000000000000000000063639C00319CCE0063CEFF0000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      000084848400848484009C9C9C00CECECE00FFFFFF0000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      00000000000000000000FFCECE00CE9C9C00FFCECE00F7EFBD00F7EFBD00F7EF
      BD00CECE9C00CE9C9C00FFCECE00000000000000000000000000000000000000
      00000000000000000000E7E7E7009C9C9C00E7E7E700CECECE00CECECE00CECE
      CE00B5B5B5009C9C9C00E7E7E700000000000000000000000000000000000000
      00000000000063639C00319CCE0063CEFF000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000009C9C9C00CECECE00FFFFFF000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000E7E7E700CECE9C00CE9C9C00CE9C9C00CE9C
      9C00CE9C9C00E7E7E70000000000000000000000000000000000000000000000
      0000000000000000000000000000E7E7E700B5B5B5009C9C9C009C9C9C009C9C
      9C009C9C9C00E7E7E70000000000000000000000000000000000000000000000
      0000000000009C3100009C310000CE6300009C310000CE6300009C3100000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000009C9C9C009C9C9C00CECECE009C9C9C00CECECE009C9C9C000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000009C310000CE6300009C310000CE6300009C3100009C3100000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000009C9C9C00CECECE009C9C9C00CECECE009C9C9C009C9C9C000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000063
      9C0000639C0000639C0000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000009C9C
      9C009C9C9C009C9C9C0000000000000000000000000000000000000000000000
      0000000000009C3100009C310000CE6300009C310000CE6300009C3100000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000009C9C9C009C9C9C00CECECE009C9C9C00CECECE009C9C9C000000
      0000000000000000000000000000000000000000000000000000000000000000
      000000000000000000000000000000000000000000000000000000639C00319C
      CE00319CCE0063CEFF0000639C00000000000000000000000000000000000000
      00000000000000000000000000000000000000000000000000009C9C9C00CECE
      CE00CECECE00000000009C9C9C00000000000000000000000000000000000000
      0000000000009C9C9C009C3100009C3100009C3100009C3100009C9C9C000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000009C9C9C009C9C9C009C9C9C009C9C9C009C9C9C009C9C9C000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000639C00319CCE000063
      9C0000CEFF0063CEFF0000639C00000000000000000000000000000000000000
      000000000000000000000000000000000000000000009C9C9C00CECECE009C9C
      9C0000000000000000009C9C9C00000000000000000000000000000000000000
      000000000000000000009C9C9C00FFFFFF00FFCECE0063636300000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      000000000000000000009C9C9C00FFFFFF00CECECE009C9C9C00000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      00000000000000000000000000000000000000639C00319CCE0000639C0000CE
      FF00319CCE0063CEFF0000639C00000000000000000000000000000000000000
      0000000000000000000000000000000000009C9C9C00CECECE009C9C9C000000
      0000CECECE00000000009C9C9C00000000000000000000000000000000000000
      000000000000000000009C9C9C00FFFFFF00FFCECE0063636300000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      000000000000000000009C9C9C00FFFFFF00CECECE009C9C9C00000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000639C0000639C00319CCE0000639C0000CEFF00319C
      CE0063CEFF0000639C0000000000000000000000000000000000000000000000
      000000000000000000009C9C9C009C9C9C00CECECE009C9C9C0000000000CECE
      CE00000000009C9C9C0000000000000000000000000000000000000000000000
      000000000000000000009C9C9C00FFFFFF00FFCECE0063636300000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      000000000000000000009C9C9C00FFFFFF00CECECE009C9C9C00000000000000
      000000000000000000000000000000000000000000000000000000639C000063
      9C0000639C0000639C00319CCE00319CCE0000639C0000CEFF00319CCE0063CE
      FF0000639C0000000000000000000000000000000000000000009C9C9C009C9C
      9C009C9C9C009C9C9C00CECECE00CECECE009C9C9C0000000000CECECE000000
      00009C9C9C000000000000000000000000000000000000000000000000000000
      000000000000000000009C9C9C00FFFFFF00FFCECE0063636300000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      000000000000000000009C9C9C00FFFFFF00CECECE009C9C9C00000000000000
      0000000000000000000000000000000000000000000000639C0000CEFF0000CE
      FF0000CEFF0000CEFF0000639C0000639C0000CEFF00319CCE0063CEFF000063
      9C0000000000000000000000000000000000000000009C9C9C00000000000000
      000000000000000000009C9C9C009C9C9C0000000000CECECE00000000009C9C
      9C00000000000000000000000000000000000000000000000000000000000000
      000000000000000000009C9C9C00FFFFFF00FFCECE0063636300000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      000000000000000000009C9C9C00FFFFFF00CECECE009C9C9C00000000000000
      00000000000000000000000000000000000000639C0000CEFF0000CEFF00319C
      CE0000CEFF0000CEFF0000CEFF0000CEFF00319CCE0063CEFF0000639C000000
      0000000000000000000000000000000000009C9C9C000000000000000000CECE
      CE0000000000000000000000000000000000CECECE00000000009C9C9C000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000063636300636363006363630063636300000000000000
      0000000000000000000000000000636363000000000000000000000000000000
      000000000000000000009C9C9C009C9C9C009C9C9C009C9C9C00000000000000
      00000000000000000000000000009C9C9C0000639C0000CEFF00319CCE0000CE
      FF00319CCE0000CEFF0000CEFF0000CEFF0063CEFF0000639C00000000000000
      0000000000000000000000000000000000009C9C9C0000000000CECECE000000
      0000CECECE00000000000000000000000000000000009C9C9C00000000000000
      0000000000000000000000000000000000000000000063636300636363000000
      000000000000636363009C9C9C009C9C9C009C9C9C009C9C9C00636363000000
      000000000000000000006363630063636300000000009C9C9C009C9C9C000000
      0000000000009C9C9C009C9C9C009C9C9C009C9C9C009C9C9C009C9C9C000000
      000000000000000000009C9C9C009C9C9C0000639C0000CEFF0000CEFF00319C
      CE0000CEFF00319CCE0000CEFF0000CEFF0063CEFF0000639C00000000000000
      0000000000000000000000000000000000009C9C9C000000000000000000CECE
      CE0000000000CECECE000000000000000000000000009C9C9C00000000000000
      0000000000000000000000000000000000009C9C9C009C9C9C009C9C9C006363
      6300636363009C9C9C00CECECE00CECECE00CECECE009C9C9C00636363006363
      63006363630063636300CECECE00636363009C9C9C009C9C9C009C9C9C009C9C
      9C009C9C9C009C9C9C00CECECE00CECECE00CECECE009C9C9C009C9C9C009C9C
      9C009C9C9C009C9C9C00CECECE009C9C9C0000639C0000CEFF0000CEFF0000CE
      FF00319CCE0000CEFF00319CCE0000CEFF0063CEFF0000639C00000000000000
      0000000000000000000000000000000000009C9C9C0000000000000000000000
      0000CECECE0000000000CECECE0000000000000000009C9C9C00000000000000
      0000000000000000000000000000000000009C9C9C00FFFFFF00CECECE00CECE
      CE00CECECE00CECECE00CECECE00CECECE00CECECE00CECECE009C9C9C009C9C
      9C009C9C9C00CECECE0063636300000000009C9C9C00FFFFFF00CECECE00CECE
      CE00CECECE00CECECE00CECECE00CECECE00CECECE00CECECE009C9C9C009C9C
      9C009C9C9C00CECECE009C9C9C000000000000639C0000CEFF0000639C000063
      9C0000CEFF00319CCE0000CEFF00319CCE0063CEFF0000639C00000000000000
      0000000000000000000000000000000000009C9C9C00000000009C9C9C009C9C
      9C0000000000CECECE0000000000CECECE00000000009C9C9C00000000000000
      0000000000000000000000000000000000009C9C9C00FFFFFF00CECECE00CECE
      CE00FFFFFF00CECECE00CECECE00CECECE00CECECE00CECECE00CECECE00CECE
      CE00CECECE009C9C9C0000000000000000009C9C9C00FFFFFF00CECECE00CECE
      CE00FFFFFF00CECECE00CECECE00CECECE00CECECE00CECECE00CECECE00CECE
      CE00CECECE009C9C9C00000000000000000000639C009CFFFF00319CCE000063
      9C0000CEFF0000CEFF00319CCE0000CEFF009CFFFF0000639C00000000000000
      0000000000000000000000000000000000009C9C9C0000000000CECECE009C9C
      9C000000000000000000CECECE0000000000000000009C9C9C00000000000000
      0000000000000000000000000000000000009C9C9C00FFFFFF00FFFFFF009C9C
      9C009C9C9C00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF009C9C
      9C009C9C9C000000000000000000000000009C9C9C00FFFFFF00FFFFFF009C9C
      9C009C9C9C00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF009C9C
      9C009C9C9C000000000000000000000000000000000000639C009CFFFF0063CE
      FF0063CEFF0063CEFF0063CEFF009CFFFF0000639C0000000000000000000000
      000000000000000000000000000000000000000000009C9C9C00000000000000
      0000000000000000000000000000000000009C9C9C0000000000000000000000
      000000000000000000000000000000000000000000009C9C9C009C9C9C000000
      0000000000009C9C9C009C9C9C009C9C9C009C9C9C009C9C9C009C9C9C000000
      000000000000000000000000000000000000000000009C9C9C009C9C9C000000
      0000000000009C9C9C009C9C9C009C9C9C009C9C9C009C9C9C009C9C9C000000
      000000000000000000000000000000000000000000000000000000639C000063
      9C0000639C0000639C0000639C0000639C000000000000000000000000000000
      00000000000000000000000000000000000000000000000000009C9C9C009C9C
      9C009C9C9C009C9C9C009C9C9C009C9C9C000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      00000000000000000000E7EFF700000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      00000000000000000000E7EFF700000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      00000000000000000000E7EFF700000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      00000000000000000000E7EFF700000000000000000000000000000000000000
      000000000000000000000000000000000000000000000000000000000000E7EF
      F700E7E7E700CECECE00E7E7E700E7EFF7000000000000000000000000000000
      000000000000000000000000000000000000000000000000000000000000E7EF
      F70000000000CECECE00E7E7E700E7EFF7000000000000000000000000000000
      000000000000000000000000000000000000000000000000000000000000E7EF
      F700E7E7E700CECECE00E7E7E700E7EFF7000000000000000000000000000000
      000000000000000000000000000000000000000000000000000000000000E7EF
      F70000000000CECECE00E7E7E700E7EFF7000000000000000000000000000000
      00000000000000000000000000000000000000000000E7EFF700E7E7E700B5B5
      B500CE9C9C009C6363009C636300B5B5B500CECECE00E7E7E700E7EFF7000000
      00000000000000000000000000000000000000000000E7EFF70000000000B5B5
      B5009C9C9C009C9C9C009C9C9C00B5B5B500CECECE0000000000E7EFF7000000
      00000000000000000000000000000000000000000000E7EFF700E7E7E700B5B5
      B500CE9C9C009C6363009C636300B5B5B500CECECE00E7E7E700E7EFF7000000
      00000000000000000000000000000000000000000000E7EFF70000000000B5B5
      B5009C9C9C009C9C9C009C9C9C00B5B5B500CECECE0000000000E7EFF7000000
      000000000000000000000000000000000000E7E7E700CE9C9C009C636300CE9C
      9C00CE9C9C00FFFFFF009C6363009C9C9C009C9C9C00B5B5B500E7E7E7000000
      000000000000000000000000000000000000000000009C9C9C009C9C9C009C9C
      9C009C9C9C00FFFFFF009C9C9C009C9C9C009C9C9C00B5B5B500000000000000
      000000000000000000000000000000000000E7E7E700CE9C9C009C636300CE9C
      9C00CE9C9C00FFFFFF009C6363009C9C9C009C9C9C00B5B5B500E7E7E7000000
      000000000000000000000000000000000000000000009C9C9C009C9C9C009C9C
      9C009C9C9C00FFFFFF009C9C9C009C9C9C009C9C9C00B5B5B500000000000000
      0000000000000000000000000000000000009C636300CE9C9C00FFCE9C00FFCE
      9C00FFCECE00FFFFFF009C63630031639C0031639C0031639C00E7EFF7000000
      0000000000000000000000000000000000009C9C9C009C9C9C00C6C6C600C6C6
      C600CECECE00FFFFFF009C9C9C009C9C9C009C9C9C009C9C9C00E7EFF7000000
      0000000000000000000000000000000000009C636300CE9C9C00FFCE9C00FFCE
      9C00FFCECE00FFFFFF009C63630031639C0031639C0031639C00E7EFF7000000
      0000000000000000000000000000000000009C9C9C009C9C9C00C6C6C600C6C6
      C600CECECE00FFFFFF009C9C9C009C9C9C009C9C9C009C9C9C00E7EFF7000000
      0000000000000000000000000000000000009C636300FFCE9C00FFCE9C00FFCE
      9C00FFCECE00FFFFFF009C63630063CECE0063CECE00009CCE00FFFFFF00FFCE
      CE00000000000000000000000000000000009C9C9C00C6C6C600C6C6C600C6C6
      C600CECECE00FFFFFF009C9C9C00C6C6C600C6C6C6009C9C9C00FFFFFF00CECE
      CE00000000000000000000000000000000009C636300FFCE9C00FFCE9C00FFCE
      9C00FFCECE00FFFFFF009C63630063CECE0063CECE00009CCE00FFFFFF00FFCE
      CE00000000000000000000000000000000009C9C9C00C6C6C600C6C6C600C6C6
      C600CECECE00FFFFFF009C9C9C00C6C6C600C6C6C6009C9C9C00FFFFFF00CECE
      CE00000000000000000000000000000000009C636300FFCE9C00FFCE9C00FFCE
      9C00FFCECE00FFFFFF009C63630063CECE0063CEFF00319CCE00FFCECE00CE63
      0000000000000000000000000000000000009C9C9C00C6C6C600C6C6C600C6C6
      C600CECECE00FFFFFF009C9C9C00C6C6C600CECECE009C9C9C00CECECE009C9C
      9C00000000000000000000000000000000009C636300FFCE9C00FFCE9C00FFCE
      9C00FFCECE00FFFFFF009C63630063CECE0063CEFF00319CCE00FFCECE00CE63
      0000000000000000000000000000000000009C9C9C00C6C6C600C6C6C600C6C6
      C600CECECE00FFFFFF009C9C9C00C6C6C600CECECE009C9C9C00CECECE009C9C
      9C00000000000000000000000000000000009C636300FFCE9C00CE9C9C00CE9C
      6300FFCECE00FFFFFF009C6363009CCECE009CCEFF00B5B5B500FF630000CE63
      0000000000000000000000000000000000009C9C9C00C6C6C6009C9C9C009C9C
      9C00CECECE00FFFFFF009C9C9C00CECECE00CECECE00B5B5B5009C9C9C009C9C
      9C00000000000000000000000000000000009C636300FFCE9C00CE9C9C00CE9C
      6300FFCECE00FFFFFF009C6363009CCECE009CCEFF00B5B5B500FF630000CE63
      0000000000000000000000000000000000009C9C9C00C6C6C6009C9C9C009C9C
      9C00CECECE00FFFFFF009C9C9C00CECECE00CECECE00B5B5B5009C9C9C009C9C
      9C00000000000000000000000000000000009C636300FFCE9C009C636300FFFF
      FF00FFCECE00FFFFFF009C6363009CCECE00C6C6C600CE630000CE630000CE63
      0000CE630000CE630000CE630000000000009C9C9C00C6C6C60063636300FFFF
      FF00CECECE00FFFFFF009C9C9C00CECECE00C6C6C6009C9C9C009C9C9C009C9C
      9C009C9C9C009C9C9C009C9C9C00000000009C636300FFCE9C009C636300FFFF
      FF00FFCECE00FFFFFF009C6363009CCECE00C6C6C600CE630000CE630000CE63
      0000CE630000CE630000CE630000000000009C9C9C00C6C6C60063636300FFFF
      FF00CECECE00FFFFFF009C9C9C00CECECE00C6C6C6009C9C9C009C9C9C009C9C
      9C009C9C9C009C9C9C009C9C9C00000000009C636300FFCE9C00CE9C9C009C63
      6300FFCECE00FFFFFF009C63630000000000CE630000CE630000CE630000CE63
      0000CE630000CE630000CE630000000000009C9C9C00C6C6C6009C9C9C006363
      6300CECECE00FFFFFF009C9C9C00E7E7E7009C9C9C009C9C9C009C9C9C009C9C
      9C009C9C9C009C9C9C009C9C9C00000000009C636300FFCE9C00CE9C9C009C63
      6300FFCECE00FFFFFF009C63630000000000CE630000CE630000CE630000CE63
      0000CE630000CE630000CE630000000000009C9C9C00C6C6C6009C9C9C006363
      6300CECECE00FFFFFF009C9C9C00E7E7E7009C9C9C009C9C9C009C9C9C009C9C
      9C009C9C9C009C9C9C009C9C9C00000000009C636300FFCE9C00FFCE9C00FFCE
      9C00FFCECE00FFFFFF009C63630000000000CE9C9C00CE630000CE630000CE63
      0000CE630000CE630000CE630000000000009C9C9C00C6C6C600C6C6C600C6C6
      C600CECECE00FFFFFF009C9C9C00E7E7E7009C9C9C009C9C9C009C9C9C009C9C
      9C009C9C9C009C9C9C009C9C9C00000000009C636300FFCE9C00FFCE9C00FFCE
      9C00FFCECE00FFFFFF009C63630000000000CE9C9C00CE630000CE630000CE63
      0000CE630000CE630000CE630000000000009C9C9C00C6C6C600C6C6C600C6C6
      C600CECECE00FFFFFF009C9C9C00E7E7E7009C9C9C009C9C9C009C9C9C009C9C
      9C009C9C9C009C9C9C009C9C9C00000000009C636300FFCE9C00FFCE9C00FFCE
      9C00FFCECE00FFFFFF009C636300CECECE00E7EFF700CE9C9C00FF630000CE63
      0000000000000000000000000000000000009C9C9C00C6C6C600C6C6C600C6C6
      C600CECECE00FFFFFF009C9C9C00CECECE00E7EFF7009C9C9C009C9C9C009C9C
      9C00000000000000000000000000000000009C636300FFCE9C00FFCE9C00FFCE
      9C00FFCECE00FFFFFF009C636300CECECE00E7EFF700CE9C9C00FF630000CE63
      0000000000000000000000000000000000009C9C9C00C6C6C600C6C6C600C6C6
      C600CECECE00FFFFFF009C9C9C00CECECE00E7EFF7009C9C9C009C9C9C009C9C
      9C00000000000000000000000000000000009C636300FFCE9C00FFCE9C00FFCE
      9C00FFCECE00FFFFFF009C6363009CCECE00000000009CCECE00FFCE9C00CE63
      0000000000000000000000000000000000009C9C9C00C6C6C600C6C6C600C6C6
      C600CECECE00FFFFFF009C9C9C00CECECE00E7E7E700CECECE00C6C6C6009C9C
      9C00000000000000000000000000000000009C636300FFCE9C00FFCE9C00FFCE
      9C00FFCECE00FFFFFF009C6363009CCECE00000000009CCECE00FFCE9C00CE63
      0000000000000000000000000000000000009C9C9C00C6C6C600C6C6C600C6C6
      C600CECECE00FFFFFF009C9C9C00CECECE00E7E7E700CECECE00C6C6C6009C9C
      9C00000000000000000000000000000000009C636300CE9C9C00FFCE9C00FFCE
      9C00FFCECE00FFFFFF009C636300CECECE0000000000319CCE0000000000FFCE
      9C00000000000000000000000000000000009C9C9C009C9C9C00C6C6C600C6C6
      C600CECECE00FFFFFF009C9C9C00CECECE00E7E7E7009C9C9C0000000000C6C6
      C600000000000000000000000000000000009C636300CE9C9C00FFCE9C00FFCE
      9C00FFCECE00FFFFFF009C636300CECECE0000000000319CCE0000000000FFCE
      9C00000000000000000000000000000000009C9C9C009C9C9C00C6C6C600C6C6
      C600CECECE00FFFFFF009C9C9C00CECECE00E7E7E7009C9C9C0000000000C6C6
      C6000000000000000000000000000000000000000000C6C6C600CE9C6300CE9C
      9C00CECE9C00FFFFFF009C636300009CCE00009CCE00009CCE00000000000000
      00000000000000000000000000000000000000000000C6C6C6009C9C9C009C9C
      9C00C6C6C600FFFFFF009C9C9C009C9C9C009C9C9C009C9C9C00000000000000
      00000000000000000000000000000000000000000000C6C6C600CE9C6300CE9C
      9C00CECE9C00FFFFFF009C636300009CCE00009CCE00009CCE00000000000000
      00000000000000000000000000000000000000000000C6C6C6009C9C9C009C9C
      9C00C6C6C600FFFFFF009C9C9C009C9C9C009C9C9C009C9C9C00000000000000
      000000000000000000000000000000000000000000000000000000000000CECE
      CE00CE9C9C009C6363009C636300000000000000000000000000000000000000
      000000000000000000000000000000000000000000000000000000000000CECE
      CE009C9C9C009C9C9C009C9C9C00000000000000000000000000000000000000
      000000000000000000000000000000000000000000000000000000000000CECE
      CE00CE9C9C009C6363009C636300000000000000000000000000000000000000
      000000000000000000000000000000000000000000000000000000000000CECE
      CE009C9C9C009C9C9C009C9C9C00000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      00000000000000000000E7EFF700000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      00000000000000000000E7EFF700000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      00000000000000000000E7EFF700000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      00000000000000000000E7EFF700000000000000000000000000000000000000
      000000000000000000000000000000000000000000000000000000000000E7EF
      F700E7E7E700CECECE00E7E7E700E7EFF7000000000000000000000000000000
      000000000000000000000000000000000000000000000000000000000000E7EF
      F70000000000CECECE00E7E7E700E7EFF7000000000000000000000000000000
      000000000000000000000000000000000000000000000000000000000000E7EF
      F700E7E7E700CECECE00E7E7E700E7EFF7000000000000000000000000000000
      000000000000000000000000000000000000000000000000000000000000E7EF
      F70000000000CECECE00E7E7E700E7EFF7000000000000000000000000000000
      00000000000000000000000000000000000000000000E7EFF700E7E7E700B5B5
      B500CE9C9C009C6363009C636300B5B5B500CECECE00E7E7E700E7EFF7000000
      00000000000000000000000000000000000000000000E7EFF70000000000B5B5
      B5009C9C9C009C9C9C009C9C9C00B5B5B500CECECE0000000000E7EFF7000000
      00000000000000000000000000000000000000000000E7EFF700E7E7E700B5B5
      B500CE9C9C009C6363009C636300B5B5B500CECECE00E7E7E700E7EFF7000000
      00000000000000000000000000000000000000000000E7EFF70000000000B5B5
      B5009C9C9C009C9C9C009C9C9C00B5B5B500CECECE0000000000E7EFF7000000
      000000000000000000000000000000000000E7E7E700CE9C9C009C636300CE9C
      9C00CE9C9C00FFFFFF009C6363009C9C9C009C9C9C00B5B5B500E7E7E7000000
      000000000000000000000000000000000000000000009C9C9C009C9C9C009C9C
      9C009C9C9C00FFFFFF009C9C9C009C9C9C009C9C9C00B5B5B500000000000000
      000000000000000000000000000000000000E7E7E700CE9C9C009C636300CE9C
      9C00CE9C9C00FFFFFF009C6363009C9C9C009C9C9C00B5B5B500E7E7E7000000
      000000000000000000000000000000000000000000009C9C9C009C9C9C009C9C
      9C009C9C9C00FFFFFF009C9C9C009C9C9C009C9C9C00B5B5B500000000000000
      0000000000000000000000000000000000009C636300CE9C9C00FFCE9C00FFCE
      9C00FFCECE00FFFFFF009C63630031639C0031639C0031639C00E7EFF7000000
      0000000000000000000000000000000000009C9C9C009C9C9C00C6C6C600C6C6
      C600CECECE00FFFFFF009C9C9C009C9C9C009C9C9C009C9C9C00E7EFF7000000
      0000000000000000000000000000000000009C636300CE9C9C00FFCE9C00FFCE
      9C00FFCECE00FFFFFF009C63630031639C0031639C0031639C00E7EFF7000000
      0000000000000000000000000000000000009C9C9C009C9C9C00C6C6C600C6C6
      C600CECECE00FFFFFF009C9C9C009C9C9C009C9C9C009C9C9C00E7EFF7000000
      0000000000000000000000000000000000009C636300FFCE9C00FFCE9C00FFCE
      9C00FFCECE00FFFFFF009C63630063CECE0063CECE00009CCE00FFFFFF00FFCE
      CE00000000000000000000000000000000009C9C9C00C6C6C600C6C6C600C6C6
      C600CECECE00FFFFFF009C9C9C00C6C6C600C6C6C6009C9C9C00FFFFFF00CECE
      CE00000000000000000000000000000000009C636300FFCE9C00FFCE9C00FFCE
      9C00FFCECE00FFFFFF009C63630063CECE0063CECE00009CCE00FFFFFF00FFCE
      CE00000000000000000000000000000000009C9C9C00C6C6C600C6C6C600C6C6
      C600CECECE00FFFFFF009C9C9C00C6C6C600C6C6C6009C9C9C00FFFFFF00CECE
      CE00000000000000000000000000000000009C636300FFCE9C00FFCE9C00FFCE
      9C00FFCECE00FFFFFF009C63630063CECE0063CEFF00319CCE00FFCECE00CE63
      0000000000000000000000000000000000009C9C9C00C6C6C600C6C6C600C6C6
      C600CECECE00FFFFFF009C9C9C00C6C6C600CECECE009C9C9C00CECECE009C9C
      9C00000000000000000000000000000000009C636300FFCE9C00FFCE9C00FFCE
      9C00FFCECE00FFFFFF009C63630063CECE0063CEFF00319CCE00FFCECE00CE63
      0000000000000000000000000000000000009C9C9C00C6C6C600C6C6C600C6C6
      C600CECECE00FFFFFF009C9C9C00C6C6C600CECECE009C9C9C00CECECE009C9C
      9C00000000000000000000000000000000009C636300FFCE9C00CE9C9C00CE9C
      6300FFCECE00FFFFFF009C6363009CCECE009CCEFF00B5B5B500FF630000CE63
      0000000000000000000000000000000000009C9C9C00C6C6C6009C9C9C009C9C
      9C00CECECE00FFFFFF009C9C9C00CECECE00CECECE00B5B5B5009C9C9C009C9C
      9C00000000000000000000000000000000009C636300FFCE9C00CE9C9C00CE9C
      6300FFCECE00FFFFFF009C6363009CCECE009CCEFF00B5B5B500FF630000CE63
      0000000000000000000000000000000000009C9C9C00C6C6C6009C9C9C009C9C
      9C00CECECE00FFFFFF009C9C9C00CECECE00CECECE00B5B5B5009C9C9C009C9C
      9C00000000000000000000000000000000009C636300FFCE9C009C636300FFFF
      FF00FFCECE00FFFFFF009C6363009CCECE00C6C6C600CE630000CE630000CE63
      0000CE630000CE630000CE630000000000009C9C9C00C6C6C60063636300FFFF
      FF00CECECE00FFFFFF009C9C9C00CECECE00C6C6C6009C9C9C009C9C9C009C9C
      9C009C9C9C009C9C9C009C9C9C00000000009C636300FFCE9C009C636300FFFF
      FF00FFCECE00FFFFFF009C6363009CCECE00C6C6C600CE630000CE630000CE63
      0000CE630000CE630000CE630000000000009C9C9C00C6C6C60063636300FFFF
      FF00CECECE00FFFFFF009C9C9C00CECECE00C6C6C6009C9C9C009C9C9C009C9C
      9C009C9C9C009C9C9C009C9C9C00000000009C636300FFCE9C00CE9C9C009C63
      6300FFCECE00FFFFFF009C63630000000000CE630000CE630000CE630000CE63
      0000CE630000CE630000CE630000000000009C9C9C00C6C6C6009C9C9C006363
      6300CECECE00FFFFFF009C9C9C00E7E7E7009C9C9C009C9C9C009C9C9C009C9C
      9C009C9C9C009C9C9C009C9C9C00000000009C636300FFCE9C00CE9C9C009C63
      6300FFCECE00FFFFFF009C63630000000000CE630000CE630000CE630000CE63
      0000CE630000CE630000CE630000000000009C9C9C00C6C6C6009C9C9C006363
      6300CECECE00FFFFFF009C9C9C00E7E7E7009C9C9C009C9C9C009C9C9C009C9C
      9C009C9C9C009C9C9C009C9C9C00000000009C636300FFCE9C00FFCE9C00FFCE
      9C00FFCECE00FFFFFF009C63630000000000CE9C9C00CE630000CE630000CE63
      0000CE630000CE630000CE630000000000009C9C9C00C6C6C600C6C6C600C6C6
      C600CECECE00FFFFFF009C9C9C00E7E7E7009C9C9C009C9C9C009C9C9C009C9C
      9C009C9C9C009C9C9C009C9C9C00000000009C636300FFCE9C00FFCE9C00FFCE
      9C00FFCECE00FFFFFF009C63630000000000CE9C9C00CE630000CE630000CE63
      0000CE630000CE630000CE630000000000009C9C9C00C6C6C600C6C6C600C6C6
      C600CECECE00FFFFFF009C9C9C00E7E7E7009C9C9C009C9C9C009C9C9C009C9C
      9C009C9C9C009C9C9C009C9C9C00000000009C636300FFCE9C00FFCE9C00FFCE
      9C00FFCECE00FFFFFF009C636300CECECE00E7EFF700CE9C9C00FF630000CE63
      0000000000000000000000000000000000009C9C9C00C6C6C600C6C6C600C6C6
      C600CECECE00FFFFFF009C9C9C00CECECE00E7EFF7009C9C9C009C9C9C009C9C
      9C00000000000000000000000000000000009C636300FFCE9C00FFCE9C00FFCE
      9C00FFCECE00FFFFFF009C636300CECECE00E7EFF700CE9C9C00FF630000CE63
      0000000000000000000000000000000000009C9C9C00C6C6C600C6C6C600C6C6
      C600CECECE00FFFFFF009C9C9C00CECECE00E7EFF7009C9C9C009C9C9C009C9C
      9C00000000000000000000000000000000009C636300FFCE9C00FFCE9C00FFCE
      9C00FFCECE00FFFFFF009C6363009CCECE00000000009CCECE00FFCE9C00CE63
      0000000000000000000000000000000000009C9C9C00C6C6C600C6C6C600C6C6
      C600CECECE00FFFFFF009C9C9C00CECECE00E7E7E700CECECE00C6C6C6009C9C
      9C00000000000000000000000000000000009C636300FFCE9C00FFCE9C00FFCE
      9C00FFCECE00FFFFFF009C6363009CCECE00000000009CCECE00FFCE9C00CE63
      0000000000000000000000000000000000009C9C9C00C6C6C600C6C6C600C6C6
      C600CECECE00FFFFFF009C9C9C00CECECE00E7E7E700CECECE00C6C6C6009C9C
      9C00000000000000000000000000000000009C636300CE9C9C00FFCE9C00FFCE
      9C00FFCECE00FFFFFF009C636300CECECE0000000000319CCE0000000000FFCE
      9C00000000000000000000000000000000009C9C9C009C9C9C00C6C6C600C6C6
      C600CECECE00FFFFFF009C9C9C00CECECE00E7E7E7009C9C9C0000000000C6C6
      C600000000000000000000000000000000009C636300CE9C9C00FFCE9C00FFCE
      9C00FFCECE00FFFFFF009C636300CECECE0000000000319CCE0000000000FFCE
      9C00000000000000000000000000000000009C9C9C009C9C9C00C6C6C600C6C6
      C600CECECE00FFFFFF009C9C9C00CECECE00E7E7E7009C9C9C0000000000C6C6
      C6000000000000000000000000000000000000000000C6C6C600CE9C6300CE9C
      9C00CECE9C00FFFFFF009C636300009CCE00009CCE00009CCE00000000000000
      00000000000000000000000000000000000000000000C6C6C6009C9C9C009C9C
      9C00C6C6C600FFFFFF009C9C9C009C9C9C009C9C9C009C9C9C00000000000000
      00000000000000000000000000000000000000000000C6C6C600CE9C6300CE9C
      9C00CECE9C00FFFFFF009C636300009CCE00009CCE00009CCE00000000000000
      00000000000000000000000000000000000000000000C6C6C6009C9C9C009C9C
      9C00C6C6C600FFFFFF009C9C9C009C9C9C009C9C9C009C9C9C00000000000000
      000000000000000000000000000000000000000000000000000000000000CECE
      CE00CE9C9C009C6363009C636300000000000000000000000000000000000000
      000000000000000000000000000000000000000000000000000000000000CECE
      CE009C9C9C009C9C9C009C9C9C00000000000000000000000000000000000000
      000000000000000000000000000000000000000000000000000000000000CECE
      CE00CE9C9C009C6363009C636300000000000000000000000000000000000000
      000000000000000000000000000000000000000000000000000000000000CECE
      CE009C9C9C009C9C9C009C9C9C00000000000000000000000000000000000000
      000000000000000000000000000000000000424D3E000000000000003E000000
      2800000040000000500000000100010000000000800200000000000000000000
      000000000000000000000000FFFFFF0000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      00000000000000000000000000000000FCFFFCFF8FFF8FFFF87FF87F07FF07FF
      F07FF07F83FF83FFE07FE07F81FF81FFC03FC03FC0FFC0FF803E803EE003E003
      001C001CF001F00100080008F800F80000010001F800F80080038003F800F800
      80078007F800F800800F800FF800F800C01FC01FF800F800E03FE03FF800F800
      F07FF07FFC01FC01F8FFF8FFFE03FE03F81FF81FFFFFFFFFF81FF81FFFE3FFE3
      F81FF81FFFC1FFC5F81FF81FFF81FF8DFC3FFC3FFF01FF15FC3FFC3FFC03FC2B
      FC3FFC3FC007C057FC3FFC3F800FBCAFFC3FFC3F001F6F5FFC3EFC3E003F57BF
      981C981C003F6BBF00000000003F75BF00010001003F4ABF00030003003F4DBF
      00070007807FBF7F981F981FC0FFC0FFFDFFFDFFFDFFFDFFE0FFE8FFE0FFE8FF
      801FA05F801FA05F001F803F001F803F001F001F001F001F000F000F000F000F
      000F000F000F000F000F000F000F000F00010001000100010101000101010001
      0101000101010001000F000F000F000F008F000F008F000F00AF002F00AF002F
      803F803F803F803FE1FFE1FFE1FFE1FFFDFFFDFFFDFFFDFFE0FFE8FFE0FFE8FF
      801FA05F801FA05F001F803F001F803F001F001F001F001F000F000F000F000F
      000F000F000F000F000F000F000F000F00010001000100010101000101010001
      0101000101010001000F000F000F000F008F000F008F000F00AF002F00AF002F
      803F803F803F803FE1FFE1FFE1FFE1FF00000000000000000000000000000000
      000000000000}
  end
  object Timer1: TTimer
    Enabled = False
    Interval = 400
    OnTimer = Timer1Timer
    Left = 360
    Top = 296
  end
  object ADOQuery1: TADOQuery
    Connection = dm.ADOConnection1
    Parameters = <>
    Left = 440
    Top = 165
  end
  object Timer2: TTimer
    Enabled = False
    Interval = 200
    OnTimer = Timer2Timer
    Left = 392
    Top = 8
  end
end
