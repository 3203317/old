tuscany svn地址：http://svn.apache.org/repos/asf/tuscany/sca-java-2.x/trunk/

#设置内存大小
SET MAVEN_OPTS=-Xmx1555m



比中心库全的镜像http://mirrors.ibiblio.org/pub/mirrors/maven2/


跳过测试 mvn clean install -Dmaven.test.skip=true 


mvn clean package -Dmaven.test.skip=true

mvn clean package -fn -o -Dmaven.test.skip=true


----------------------
[ERROR] Failed to execute goal on project tuscany-extensibility-equinox: Could n
ot resolve dependencies for project org.apache.tuscany.sca:tuscany-extensibility
-equinox:jar:2.5-SNAPSHOT: Failed to collect dependencies for [org.apache.tuscan
y.sca:tuscany-core-runtime-pom:pom:2.5-SNAPSHOT (provided), org.eclipse:osgi:jar
:3.5.0-v20090520 (compile), junit:junit:jar:4.8.1 (test)]: Failed to read artifa
ct descriptor for org.eclipse:osgi:jar:3.5.0-v20090520: Could not transfer artif
act org.eclipse:osgi:pom:3.5.0-v20090520 from/to tuscany.repo (http://svn.apache
.org/repos/asf/tuscany/maven): Connection to http://svn.apache.org refused: Conn
ection timed out: connect -> [Help 1]

osgi 远程pom始终下载下来的是错的，没办法只有跳过 手动下载所需jar
-----------------------


#######################################################
根pom添加

	<distributionManagement>
	  <snapshotRepository>
	    <id>snapshots</id>
	    <url>http://192.168.131.91:8082/nexus/content/repositories/snapshots</url>
	  </snapshotRepository>
	</distributionManagement>

2012-09-11 部署das到nexus   【成功】
SET MAVEN_OPTS=-Xmx1555m
mvn deploy -fn -Dmaven.test.skip=true

部署sdo到nexus
除了修改和das一样的步骤外，发布sdo-api会报错，只有单独发布才能成功。方法：
进入sdo-api目录，添加http://192.168.131.91:8082/nexus/content/repositories/snapshots  注释掉repositories


发布sca除了修改根pom.xml还需要修改D:\soft\tuscany\code\20120817\maven\archetypes\policy\pom.xml

#######################################################


