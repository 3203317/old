配置Java WebStart，以 net.foreworld.rss2 RSS聚合浏览器为例：

环境：MyEclipse3.3+JDK1.4

1、创建KeyStore

C:\j2sdk1.4.2_16\bin\keytool -genkey -alias Foreworld.Net -keypass 123456 -storepass 123456 -keystore Foreworld.Net
[创建KeyStore.jpg]
执行成功后，在当前目录下生成 foreworld.net 数字签名文件。


2、打包并进行数字签名

首先，准备一个目录，比如D:\workspace5\net.foreworld.rss2\jws，作为打包目录，同时也是放置主JNLP文件的目录。

在Eclipse中，打开菜单File->Export，选中Plug-in Development下的Deployable features，点击Next。在可用功能部件列表中，将net.foreworld.rss2.feature选中。

值得一提的是打包时需要用Eclipse的JDK方式，创建一个快捷方式：
D:\MyEclipse-6.0M1\eclipse\eclipse.exe -vm "C:\j2sdk1.4.2_16\bin\javaw.exe"

在Destination页，输入或浏览选定所准备的打包目录：

C:\j2sdk1.4.2_16\bin\jarsigner -keystore D:\workspace5\net.foreworld.rss2\jws\foreworld.net net.foreworld.rss2_1.0.0.200905212126.jar foreworld.net
结果：
D:\workspace5\net.foreworld.rss2\jws\plugins>C:\j2sdk1.4.2_16\bin\jarsigner -key
store D:\workspace5\net.foreworld.rss2\jws\foreworld.net net.foreworld.rss2_1.0.
0.200905212126.jar foreworld.net
Enter Passphrase for keystore: 123456


3、配置主JNLP文件


