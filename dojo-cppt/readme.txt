《产品平台公共基础应用》--原型界面 
dojo开发  dojo-ext为扩展js功能
纯静态页面 html 和js

cdn为dojo的全部代码，需配置tomcat  端口8082

<Context path="/cdn" docBase="D:\cdn">
	<Manager className="org.apache.catalina.session.PersistentManager"
		debug="0"
		saveOnRestart="false"
		maxActiveSessions="-1"
		minIdleSwap="-1"
		maxIdleSwap="-1"
		maxIdleBackup="-1">
		
		<Store className="org.apache.catalina.session.FileStore"/>
	</Manager>

</Context>


http://localhost:8083/rfs/main2.html?theme=night 为word风格主框架

http://localhost:8083/rfs/main3.html?theme=night 为tabtree风格主框架

night为皮肤