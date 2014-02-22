Set vbs=CreateObject("Wscript.Shell")
vbs.Run "cmd"
wscript.sleep 3000
vbs.SendKeys "copy D:\workspace-helios\commonbase\modules\commonbase-api\target\commonbase-api-1.0.1-SNAPSHOT.jar D:\soft\tuscany\RC2.0\tuscany-distribution-all-2.0\tuscany-sca\modules\commonbase-api-1.0.1-SNAPSHOT.jar /y{enter}"
vbs.SendKeys "copy D:\workspace-helios\commonbase\modules\commonbase-util\target\commonbase-util-1.0.1-SNAPSHOT.jar D:\soft\tuscany\RC2.0\tuscany-distribution-all-2.0\tuscany-sca\modules\commonbase-util-1.0.1-SNAPSHOT.jar /y{enter}"
vbs.SendKeys "copy D:\workspace-helios\SmartReport\smartreport-modules\smartreport-api\target\smartreport-api-1.0.1-SNAPSHOT.jar D:\soft\tuscany\RC2.0\tuscany-distribution-all-2.0\tuscany-sca\modules\smartreport-api-1.0.1-SNAPSHOT.jar /y{enter}"
wscript.sleep 1000
vbs.SendKeys "tuscany{enter}"
'vbs.SendKeys "domain hx{enter}"

'主框架
vbs.SendKeys "install file:///D:/workspace-helios/commonbase/modules/commonbase-session/target/commonbase-session-1.0.1-SNAPSHOT.jar{enter}"
vbs.SendKeys "start commonbase-session session.composite{enter}"
vbs.SendKeys "install file:///D:/workspace-helios/commonbase/modules/commonbase-user/target/commonbase-user-1.0.1-SNAPSHOT.jar{enter}"
vbs.SendKeys "start commonbase-user userPasswordDAS.composite{enter}"
vbs.SendKeys "install file:///D:/workspace-helios/commonbase/modules/commonbase-login/target/commonbase-login-1.0.1-SNAPSHOT.jar{enter}"
vbs.SendKeys "start commonbase-login loginService.composite{enter}"
vbs.SendKeys "install file:///D:/workspace-helios/commonbase/modules/commonbase-techui/target/commonbase-techui-1.0.1-SNAPSHOT.jar{enter}"
vbs.SendKeys "start commonbase-techui menu-menuBind.composite{enter}"
vbs.SendKeys "start commonbase-techui mainFormManagerUI.composite{enter}"
vbs.SendKeys "start commonbase-techui menu-treeInfo.composite{enter}"
vbs.SendKeys "start commonbase-techui formAndReport.composite{enter}"

vbs.SendKeys "install file:///D:/workspace-helios/commonbase/modules/commonbase-dbconn/target/commonbase-dbconn-1.0.1-SNAPSHOT.jar{enter}"
vbs.SendKeys "start commonbase-dbconn DBConnectionService.composite{enter}"
vbs.SendKeys "start commonbase-dbconn dbconnUI-manager.composite{enter}"

vbs.SendKeys "install file:///D:/workspace-helios/commonbase/modules/commonbase-tech/target/commonbase-tech-1.0.1-SNAPSHOT.jar{enter}"
vbs.SendKeys "start commonbase-tech SequenceService.composite{enter}"
vbs.SendKeys "start commonbase-tech defDBConnectionService.composite{enter}"

'resource http://localhost:8080/hx/resource/manager/?sessionId=fd22b2505b894acb97fcdfab0d8935c5
'vbs.SendKeys "install file:///D:/workspace-helios/commonbase/modules/commonbase-tech/target/commonbase-tech-1.0.1-SNAPSHOT.jar{enter}"
'vbs.SendKeys "start commonbase-tech SequenceService.composite{enter}"
'vbs.SendKeys "install file:///D:/workspace-helios/commonbase/modules/commonbase-session/target/commonbase-session-1.0.1-SNAPSHOT.jar{enter}"
'vbs.SendKeys "start commonbase-session session.composite{enter}"
vbs.SendKeys "install file:///D:/workspace-helios/commonbase/modules/commonbase-org/target/commonbase-org-1.0.1-SNAPSHOT.jar{enter}"
vbs.SendKeys "start commonbase-org organizationService.composite{enter}"
vbs.SendKeys "start commonbase-org orgUI-manager.composite{enter}"
vbs.SendKeys "install file:///D:/workspace-helios/commonbase/modules/commonbase-darule/target/commonbase-darule-1.0.1-SNAPSHOT.jar{enter}"
vbs.SendKeys "start commonbase-darule dataAccessRuleDAS.composite{enter}"
vbs.SendKeys "start commonbase-darule dataAccessRuleService.composite{enter}"
vbs.SendKeys "install file:///D:/workspace-helios/commonbase/modules/commonbase-operation/target/commonbase-operation-1.0.1-SNAPSHOT.jar{enter}"
vbs.SendKeys "start commonbase-operation operateService.composite{enter}"
vbs.SendKeys "start commonbase-operation operationUI-manager.composite{enter}"
vbs.SendKeys "install file:///D:/workspace-helios/commonbase/modules/commonbase-resource/target/commonbase-resource-1.0.1-SNAPSHOT.jar{enter}"
vbs.SendKeys "start commonbase-resource ResourceService.composite{enter}"
vbs.SendKeys "start commonbase-resource resourceManagerUIComponent.composite{enter}"


'role http://localhost:8080/hx/role/manager/?sessionId=fd22b2505b894acb97fcdfab0d8935c5
vbs.SendKeys "install file:///D:/workspace-helios/commonbase/modules/commonbase-rolepermission/target/commonbase-rolepermission-1.0.1-SNAPSHOT.jar{enter}"
vbs.SendKeys "start commonbase-rolepermission rolePermisionDAS.composite{enter}"
vbs.SendKeys "start commonbase-rolepermission rolePermisionService.composite{enter}"
vbs.SendKeys "install file:///D:/workspace-helios/commonbase/modules/commonbase-role/target/commonbase-role-1.0.1-SNAPSHOT.jar{enter}"
vbs.SendKeys "start commonbase-role roleDAS.composite{enter}"
vbs.SendKeys "start commonbase-role roleService.composite{enter}"
vbs.SendKeys "start commonbase-role roleManagerUIComponent.composite{enter}"


'pemission http://localhost:8080/hx/permision/manager/?sessionId=fd22b2505b894acb97fcdfab0d8935c5  完成
'vbs.SendKeys "install file:///D:/workspace-helios/commonbase/modules/commonbase-tech/target/commonbase-tech-1.0.1-SNAPSHOT.jar{enter}"
'vbs.SendKeys "start commonbase-tech SequenceService.composite{enter}"
'vbs.SendKeys "install file:///D:/workspace-helios/commonbase/modules/commonbase-session/target/commonbase-session-1.0.1-SNAPSHOT.jar{enter}"
'vbs.SendKeys "start commonbase-session session.composite{enter}"
'vbs.SendKeys "install file:///D:/workspace-helios/commonbase/modules/commonbase-operation/target/commonbase-operation-1.0.1-SNAPSHOT.jar{enter}"
'vbs.SendKeys "start commonbase-operation operateService.composite{enter}"
'vbs.SendKeys "install file:///D:/workspace-helios/commonbase/modules/commonbase-techui/target/commonbase-techui-1.0.1-SNAPSHOT.jar{enter}"
'vbs.SendKeys "start commonbase-techui mainFormManagerUI.composite{enter}"
'vbs.SendKeys "start commonbase-techui menu-treeInfo.composite{enter}"
'vbs.SendKeys "install file:///D:/workspace-helios/commonbase/modules/commonbase-resource/target/commonbase-resource-1.0.1-SNAPSHOT.jar{enter}"
'vbs.SendKeys "start commonbase-resource ResourceService.composite{enter}"
vbs.SendKeys "install file:///D:/workspace-helios/commonbase/modules/commonbase-pemission/target/commonbase-pemission-1.0.1-SNAPSHOT.jar{enter}"
vbs.SendKeys "start commonbase-pemission permisionDAS.composite{enter}"
vbs.SendKeys "start commonbase-pemission permisionService.composite{enter}"
vbs.SendKeys "start commonbase-pemission permisionManagerUIComponent.composite{enter}"


'datasource http://localhost:8080/hx/datasource/manager/?sessionId=fd22b2505b894acb97fcdfab0d8935c5
vbs.SendKeys "install file:///D:/workspace-helios/SmartReport/smartreport-modules/smartreport-datasource/target/smartreport-datasource-1.0.1-SNAPSHOT.jar{enter}"
vbs.SendKeys "start smartreport-datasource dataSourceDAS.composite{enter}"
vbs.SendKeys "start smartreport-datasource dataSourceService.composite{enter}"
vbs.SendKeys "start smartreport-datasource fieldPrepDAS.composite{enter}"
vbs.SendKeys "start smartreport-datasource fieldPrepService.composite{enter}"
vbs.SendKeys "start smartreport-datasource dataSourceManagerUIComponent.composite{enter}"



'ZZY
vbs.SendKeys "install file:///D:/workspace-helios/commonbase/modules/commonbase-userrole/target/commonbase-userrole-1.0.1-SNAPSHOT.jar{enter}"
vbs.SendKeys "start commonbase-userrole userRoleService.composite{enter}"
vbs.SendKeys "start commonbase-userrole userRoleManageUI.composite{enter}"
'vbs.SendKeys "install file:///E:/sca/commonbase/target/commonbase-user-1.0.1-SNAPSHOT.jar{enter}"
vbs.SendKeys "start commonbase-user userBaseInfoService.composite{enter}"
vbs.SendKeys "start commonbase-user userBaseInfoManagerUI.composite{enter}"
vbs.SendKeys "install file:///D:/workspace-helios/SmartReport/smartreport-modules/smartreport-gridstat/target/smartreport-gridstat-1.0.1-SNAPSHOT.jar{enter}"
vbs.SendKeys "start smartreport-gridstat gridStatDAS.composite{enter}"
vbs.SendKeys "start smartreport-gridstat gridStatService.composite{enter}"
vbs.SendKeys "start smartreport-gridstat GridStatUIComponent.composite{enter}"
vbs.SendKeys "install file:///D:/workspace-helios/SmartReport/smartreport-modules/smartreport-gridcondition/target/smartreport-gridcondition-1.0.1-SNAPSHOT.jar{enter}"
vbs.SendKeys "start smartreport-gridcondition GridConditionService.composite{enter}"
vbs.SendKeys "install file:///D:/workspace-helios/SmartReport/smartreport-modules/smartreport-gridview/target/smartreport-gridview-1.0.1-SNAPSHOT.jar{enter}"
vbs.SendKeys "start smartreport-gridview gridViewDAS.composite{enter}"
vbs.SendKeys "start smartreport-gridview gridViewService.composite{enter}"
vbs.SendKeys "install file:///D:/workspace-helios/SmartReport/smartreport-modules/smartreport-gridfield/target/smartreport-gridfield-1.0.1-SNAPSHOT.jar{enter}"
vbs.SendKeys "start smartreport-gridfield gridFieldDAS.composite{enter}"
vbs.SendKeys "start smartreport-gridfield gridFieldService.composite{enter}"
vbs.SendKeys "install file:///D:/workspace-helios/SmartReport/smartreport-modules/smartreport-gridprint/target/smartreport-gridprint-1.0.1-SNAPSHOT.jar{enter}"
vbs.SendKeys "start smartreport-gridprint GridPrintService.composite{enter}"


'PXX
'vbs.SendKeys "install file:///D:/tuscany-sca/modules/commonbase-user-1.0.1-SNAPSHOT.jar{enter}"
'vbs.SendKeys "start commonbase-user userPasswordDAS.composite{enter}"
vbs.SendKeys "start commonbase-user userPasswordService.composite{enter}"
vbs.SendKeys "start commonbase-user userPasswordManagerUIComponent.composite{enter}"
vbs.SendKeys "install file:///D:/workspace-helios/commonbase/modules/commonbase-diccode/target/commonbase-diccode-1.0.1-SNAPSHOT.jar{enter}"
vbs.SendKeys "start commonbase-diccode dicCodeDAS.composite{enter}"
vbs.SendKeys "start commonbase-diccode dicCodeService.composite{enter}"
vbs.SendKeys "start commonbase-diccode dicCodeManagerUIComponent.composite{enter}"


'hx
vbs.SendKeys "install file:///D:/workspace-helios/SmartReport/smartreport-modules/smartreport-report/target/smartreport-report-1.0.1-SNAPSHOT.jar{enter}"
vbs.SendKeys "start smartreport-report reportDAS.composite{enter}"
vbs.SendKeys "start smartreport-report reportService.composite{enter}"
vbs.SendKeys "start smartreport-report reportDsDAS.composite{enter}"
vbs.SendKeys "start smartreport-report reportDsService.composite{enter}"
vbs.SendKeys "start smartreport-report reportDsFieldDAS.composite{enter}"
vbs.SendKeys "start smartreport-report reportDsFieldService.composite{enter}"
vbs.SendKeys "start smartreport-report reportDsParamDAS.composite{enter}"
vbs.SendKeys "start smartreport-report reportDsParamService.composite{enter}"
vbs.SendKeys "start smartreport-report reportDesignerUIComponent.composite{enter}"
vbs.SendKeys "start smartreport-report reportUIComponent.composite{enter}"
vbs.SendKeys "start smartreport-report reportManagerUIComponent.composite{enter}"

'pxx
vbs.SendKeys "install file:///D:/workspace-helios/SmartReport/smartreport-modules/smartreport-componentdataset/target/smartreport-componentdataset-1.0.1-SNAPSHOT.jar{enter}"
vbs.SendKeys "start smartreport-componentdataset componentDatasetDAS.composite{enter}"
vbs.SendKeys "start smartreport-componentdataset componentDatasetService.composite{enter}"
vbs.SendKeys "start smartreport-componentdataset componentDatasetManagerUIComponent.composite{enter}"
vbs.SendKeys "start smartreport-componentdataset treeComponentForReportServlet.composite{enter}"
vbs.SendKeys "start smartreport-componentdataset treeItemForReportDAS.composite{enter}"

vbs.SendKeys "install file:///D:/workspace-helios/SmartReport/smartreport-modules/smartreport-reportpublish/target/smartreport-reportpublish-1.0.1-SNAPSHOT.jar{enter}"
vbs.SendKeys "start smartreport-reportpublish reportPublishDAS.composite{enter}"
vbs.SendKeys "start smartreport-reportpublish reportPublishService.composite{enter}"
vbs.SendKeys "start smartreport-reportpublish reportPublishManagerUIComponent.composite{enter}"


vbs.SendKeys "start commonbase-user userPhotoService.composite{enter}"
vbs.SendKeys "start commonbase-user photoUpload.composite{enter}"
vbs.SendKeys "start commonbase-user userPhotoBatchUpload.composite{enter}"
vbs.SendKeys "start commonbase-user ShowUserPhotoByUserCodeComponent.composite{enter}"
vbs.SendKeys "start commonbase-user UploadUserPhotoByUserCodeComponent.composite{enter}"

vbs.SendKeys "start commonbase-techui gridFormUIComponent.composite{enter}"