2008-11-27
这几天一直在做公务用车的需求分析，没有好好研究RCP，这可是目前我最大的“业余爱好”了。
但是每天我扔坚持抽空研究关于自动更新的功能，rssowl的更新很简单，我已经实现了，但还不是我所希望的。
今天偶然看到jnlp功能，它实现通过在浏览器上点击一个链接，即可启动应用程序，并与用户桌面进行无缝集成，
程序下载后会在客户端进行缓存，下次启动时，如果网络连接可用，会首先检查是否有更新版本，自动完成应用程序
的版本控制；如果网络连接不可用，也可以离线运行。
关于JNLP：http://www.ibm.com/developerworks/cn/opensource/os-ecl-rcpws/index.html
Foreworld.net 123456



2008-11-29
jarsigner命令
jarsigner -keystore D:\workspace4\rss2\deployable\foreworld.net D:\workspace4\rss2\deployable\startup.jar Foreworld.net
java web start site url:file:D:\workspace4\rss2\deployable
缺少这个包org.eclipse.swt.wpf.win32.x86_3.3.0.v3346.jar

找到一个很全的eclipse3.3插件网址 http://ftp.daum.net/eclipse/eclipse/updates/3.3/plugins/

jarsigner -keystore D:\workspace4\rss2\deployable\foreworld.net D:\workspace4\rss2\deployable\plugins\org.eclipse.equinox.launcher.wpf.win32.x86_1.0.0.v20070523.jar Foreworld.net

jarsigner -keystore D:\workspace4\rss2\deployable\foreworld.net D:\workspace4\rss2\deployable\plugins\org.eclipse.swt.wpf.win32.x86_3.3.0.v3346.jar Foreworld.net

org.eclipse.core.launcher.WebStartMain

2008-12-10
jnlp资料 http://lopica.sourceforge.net/quick.html
可以搜索eclipse3.3+jws的相关资料

2008-12-24
startup.jar是3.2所需的，3.3不需要。
今天在家用googlesvn真tmd慢。

2008-12-25
googlesvn老报错 nnd。

2009-3-18
好久没更新了，因为近段时间一直在研究插件的编辑器，修改了程序中的删除所用的图标，改成和eclipse一样的了。

2009-4-16
整理电脑上所以关于rcp相关的程序，因为以前在研究的时候写的demo很凌乱，所以今天把众多功能都集中到这个软件里了。