tuscany svn��ַ��http://svn.apache.org/repos/asf/tuscany/sca-java-2.x/trunk/

#�����ڴ��С
SET MAVEN_OPTS=-Xmx1555m



�����Ŀ�ȫ�ľ���http://mirrors.ibiblio.org/pub/mirrors/maven2/


�������� mvn clean install -Dmaven.test.skip=true 


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

osgi Զ��pomʼ�������������Ǵ�ģ�û�취ֻ������ �ֶ���������jar
-----------------------


#######################################################
��pom���

	<distributionManagement>
	  <snapshotRepository>
	    <id>snapshots</id>
	    <url>http://192.168.131.91:8082/nexus/content/repositories/snapshots</url>
	  </snapshotRepository>
	</distributionManagement>

2012-09-11 ����das��nexus   ���ɹ���
SET MAVEN_OPTS=-Xmx1555m
mvn deploy -fn -Dmaven.test.skip=true

����sdo��nexus
�����޸ĺ�dasһ���Ĳ����⣬����sdo-api�ᱨ��ֻ�е����������ܳɹ���������
����sdo-apiĿ¼�����http://192.168.131.91:8082/nexus/content/repositories/snapshots  ע�͵�repositories


����sca�����޸ĸ�pom.xml����Ҫ�޸�D:\soft\tuscany\code\20120817\maven\archetypes\policy\pom.xml

#######################################################


