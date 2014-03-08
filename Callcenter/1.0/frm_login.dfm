object frmlogin: Tfrmlogin
  Left = 371
  Top = 390
  BorderIcons = [biSystemMenu, biMinimize]
  BorderStyle = bsSingle
  Caption = #31649#29702#30331#38470
  ClientHeight = 66
  ClientWidth = 271
  Color = clBtnFace
  Font.Charset = ANSI_CHARSET
  Font.Color = clWindowText
  Font.Height = -12
  Font.Name = #23435#20307
  Font.Style = []
  OldCreateOrder = False
  Position = poDesktopCenter
  PixelsPerInch = 96
  TextHeight = 12
  object GroupBox1: TGroupBox
    Left = 0
    Top = 0
    Width = 271
    Height = 66
    Align = alClient
    Caption = #35831#36755#20837#23494#30721
    TabOrder = 0
    object Edit1: TEdit
      Left = 16
      Top = 24
      Width = 153
      Height = 20
      ImeName = #32043#20809#25340#38899#36755#20837#27861
      PasswordChar = '*'
      TabOrder = 0
    end
    object Button1: TButton
      Left = 176
      Top = 24
      Width = 75
      Height = 23
      Caption = #30830#23450
      TabOrder = 1
      OnClick = Button1Click
    end
  end
end
