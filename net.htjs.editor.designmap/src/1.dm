<?xml version="1.0" encoding="UTF-8"?>
<designMap validate="false" namespace="得到" strutsBase="a" sqlMapBase="c" springBase="b">
	<contentProviders>
		<contentProvider id="bxfBean" beanClass="net.htjs.clgl.beans.BxfBean">
			<method id="ssssssssss" beanMethod="select" parameter="bxf"></method>
			<method id="wew����"></method>
			<method id="sdf" beanMethod="44455" parameter="">
				<params>
					<param></param>
				</params>
				<exceptions>
					<exception></exception>
					<exception></exception>
				</exceptions>
				<return></return>
				<description></description>
			</method>
			<method id="��ʱ��">
				<description></description>
				<params>
					<param></param>
					<param></param>
				</params>
				<exceptions>
					<exception></exception>
				</exceptions>
				<return></return>
			</method>
			<description></description>
		</contentProvider>
		<contentProvider></contentProvider>
	</contentProviders>
	<labelProviders>
		<labelProvider id="viewMain">
			<input id="BDH" javaName="model.BDH" htmlName="model.BDH" htmlType="text" label="������" width="10"></input>
			<input id="CLPZHM" javaName="model.CLPZHM" htmlName="model.CLPZHM" htmlType="text" label="��~���պ���"></input>
			<description></description>
			<description></description>
			<img></img>
			<input></input>
			<radio></radio>
			<submit></submit>
			<a></a>
			<textarea></textarea>
			<select>
				<option></option>
				<option></option>
			</select>
		</labelProvider>
		<labelProvider id="selectMx">
			<input id="CLPZHM" javaName="CLPZHM" htmlType="text" label="��~���պ���"></input>
			<input id="DWMC" javaName="DWMC" htmlName="model.DWMC" htmlType="text" label="�տλ���"></input>
			<input id="BDH" javaName="BDH" htmlName="model.BDH" htmlType="text" label="������">
				<option></option>
			</input>
			<input id="JE" javaName="JE" htmlName="model.JE" htmlType="text" label="���"></input>
			<input id="JFRQ" javaName="JFRQ" htmlName="model.JFRQ" htmlType="text" label="��������"></input>
			<input id="JLRQ" javaName="JLRQ" htmlName="model.JLRQ" htmlType="text" label="��¼����"></input>
			<input id="BZ" javaName="BZ" htmlName="model.BZ" htmlType="text" label="��ע"></input>
		</labelProvider>
		<labelProvider id="viewclMx">
			<input id="CLPZHM" javaName="CLPZHM" htmlName="model.CLPZHM" label="��~���պ���"></input>
			<!-- 
      <input id="ZP" javaName="ZP" htmlName="model.ZP"   label="��~��Ƭ" ></input>
       -->
			<select id="DM_CLLX" javaName="DM_CLLX" htmlName="model.DM_CLLX" htmlType="select" label="��~����" prompt="*" dataType="Require" msg="��~���Ͳ���Ϊ��">
				<option value="">��ѡ��</option>
				<option value="1">�γ�(10��Ԫ����[��])</option>
				<option value="2">�γ�(10[����]-15��Ԫ[��])</option>
				<option value="3">�γ�(15[����]-20��Ԫ[��])</option>
				<option value="4">�γ�(20[����]-25��Ԫ[��])</option>
				<option value="5">ԽҰ��(15��Ԫ����[����15��Ԫ])</option>
				<option value="6">ԽҰ��(15-20��Ԫ[��15��Ԫ])</option>
				<option value="7">ԽҰ��(20-25��Ԫ[��20��Ԫ])</option>
				<option value="8">ԽҰ��(25��Ԫ����[��25��Ԫ])</option>
				<option value="9">����(20��Ԫ����[��20��Ԫ])</option>
				<option value="10">����(15-20��Ԫ[��15��Ԫ])</option>
				<option value="11">����(15��Ԫ����[����15��Ԫ])</option>
				<option value="12">΢�Ϳͳ�������L��3.5��)</option>
				<option value="13">[cd��ͳ���3.5�סܳ���L��6��)</option>
				<option value="14">��ͳ���6�׳���L��12��)</option>
			</select>
			<input id="CLPP" javaName="CLPP" htmlName="model.CLPP" label="��~Ʒ��"></input>
			<input id="CLXH" javaName="CLXH" htmlName="model.CLXH" label="��~�ͺ�"></input>
			<input id="PQL" javaName="PQL" htmlName="model.PQL" label="����ml"></input>
			<input id="CLYZ" javaName="CLYZ" htmlName="model.CLYZ" label="��~ԭֵ/�������"></input>
			<input id="CLJZ" javaName="CLJZ" htmlName="model.CLJZ" label="��~��ֵ"></input>
			<date id="BFRQ" javaName="BFRQ" htmlName="model.BFRQ" label="��������"></date>
			<select id="DM_TSCL" javaName="DM_TSCL" htmlName="model.DM_TSCL" htmlType="select" label="���⳵~��ʶ">
				<option value="1">��</option>
				<option value="2">��</option>
				<option value="3">��</option>
				<option value="4">˾</option>
				<option value="5">����</option>
			</select>
			<select id="CKZT" javaName="CKZT" htmlName="model.CKZT" htmlType="select" label="����״̬">
				<option value="1">����</option>
				<option value="2">����׼</option>
				<option value="3">δͨ������</option>
				<option value="4">������׼</option>
				<option value="5">��ʧ</option>
				<option value="6">���</option>
				<option value="7">��̴��</option>
				<option value="8">�ﵽ����</option>
				<option value="9">����ԭ��</option>
				<option value="10">��״̬</option>
			</select>
			<input id="CLDJ" javaName="CLDJ" htmlName="model.CLDJ" label="��~�ȼ�"></input>
			<input id="XSLC" javaName="XSLC" htmlName="model.XSLC" label="��ʻ���"></input>
			<input id="DM_CLYS" javaName="DM_CLYS" htmlName="model.DM_CLYS" label="��~��ɫ"></input>
			<input id="CJH" javaName="CJH" htmlName="model.CJH" label="���ܺ�"></input>
			<select id="JKBZ" javaName="JKBZ" htmlName="model.JKBZ" htmlType="select" label="��ڱ�־">
				<option value="1">���</option>
				<option value="2">���</option>
			</select>
			<input id="FDJH" javaName="FDJH" htmlName="model.FDJH" label="���������"></input>
			<input id="FDJXH" javaName="FDJXH" htmlName="model.FDJXH" label="�������ͺ�"></input>
			<select id="DM_RLZL" javaName="DM_RLZL" htmlName="model.DM_CLLX" htmlType="select" label="ȼ������">
				<option value="1">����90#</option>
				<option value="2">����93#</option>
				<option value="3">����95#</option>
				<option value="4">����97#</option>
				<option value="5">����98#</option>
				<option value="6">�����</option>
				<option value="7">�ز���</option>
				<option value="8">����</option>
			</select>
			<input id="GL" javaName="GL" htmlName="model.GL" label="����kw"></input>
			<input id="ZZCMC" javaName="ZZCMC" htmlName="model.ZZCMC" label="���쳧���"></input>
			<input id="ZXXS" javaName="ZXXS" htmlName="model.ZXXS" label="ת����ʽ"></input>
			<input id="LJQ" javaName="LJQ" htmlName="model.LJQ" label="�־�ǰmm"></input>
			<input id="LJH" javaName="LJH" htmlName="model.LJH" label="�־��mm"></input>
			<input id="LTS" javaName="LTS" htmlName="model.LTS" label="��̥��"></input>
			<input id="LTGG" javaName="LTGG" htmlName="model.LTGG" label="��̥���"></input>
			<input id="GBTHPS" javaName="GBTHPS" htmlName="model.GBTHPS" label="�ְ嵯��Ƭ��"></input>
			<input id="ZJ" javaName="ZJ" htmlName="model.ZJ" label="���mm"></input>
			<input id="ZS" javaName="ZS" htmlName="model.ZS" label="����"></input>
			<input id="WKC" javaName="WKC" htmlName="model.WKC" label="��*�ߴ糤mm"></input>
			<input id="WKK" javaName="WKK" htmlName="model.WKK" label="��*�ߴ��mm"></input>
			<input id="WKG" javaName="WKG" htmlName="model.WKG" label="��*�ߴ��mm"></input>
			<input id="HXC" javaName="HXC" htmlName="model.HXC" label="�����ڲ��ߴ糤mm"></input>
			<input id="HXK" javaName="HXK" htmlName="model.HXK" label="�����ڲ��ߴ��mm"></input>
			<input id="HXG" javaName="HXG" htmlName="model.HXG" label="�����ڲ��ߴ��mm"></input>
			<input id="ZZL" javaName="ZZL" htmlName="model.ZZL" label="����kg"></input>
			<input id="HDZZL" javaName="HDZZL" htmlName="model.HDZZL" label="�˶�����kg"></input>
			<input id="HDZK" javaName="HDZK" htmlName="model.HDZK" label="�˶��ؿ�"></input>
			<input id="ZQYZZL" javaName="ZQYZZL" htmlName="model.ZQYZZL" label="׼ǣ������"></input>
			<input id="JSSZK" javaName="JSSZK" htmlName="model.JSSZK" label="��ʻ���ؿ�"></input>
			<input id="SYXZ" javaName="SYXZ" htmlName="model.SYXZ" label="ʹ������"></input>
			<input id="CLHDFS" javaName="CLHDFS" htmlName="model.CLHDFS" label="��~��÷�ʽ"></input>
			<input id="ZZDW" javaName="ZZDW" htmlName="model." label="���ء���λ"></input>
			<date id="CCRQ" javaName="CCRQ" htmlName="model.CCRQ" label="������"></date>
			<date id="GZRQ" javaName="GZRQ" htmlName="model.GZRQ" label="��������"></date>
			<input id="JDCXSZ_HM" javaName="JDCXSZ_HM" htmlName="model.JDCXSZ_HM" label="����ʻ֤����"></input>
			<input id="CGS_DA_BH" javaName="CGS_DA_BH" htmlName="model.CGS_DA_BH" label="������~�������"></input>
			<input id="CKJS" javaName="CKJS" htmlName="model.CKJS" label="�������"></input>
			<select id="YXBS" javaName="YXBS" htmlName="model.YXBS" htmlType="select" label="��Ч��ʶ">
				<option value="1">��Ч</option>
				<option value="0">��Ч</option>
			</select>
			<input id="SYNX" javaName="SYNX" htmlName="model.SYNX" label="ʹ������"></input>
		</labelProvider>
		<labelProvider id="select">
			<a id="CLPZHM" javaName="CLPZHM" label="��~���պ���" href="javascript:jsDispatcher('url=/server/clgl/bxf/selectMx.do`callback=buildXHTML`id=100`width=420`height=430`title=��ϸ','store.JLID=#{JLID}');" style="cursor:hand"></a>
			<input id="DWMC" javaName="DWMC" htmlName="model.DWMC" htmlType="text" label="�տλ���"></input>
			<input id="BDH" javaName="BDH" htmlName="model.BDH" htmlType="text" label="������"></input>
			<input id="JE" javaName="JE" htmlName="model.JE" htmlType="text" label="���"></input>
			<input id="JFRQ" javaName="JFRQ" htmlName="model.JFRQ" htmlType="text" label="��������"></input>
			<a id="SC" href="javascript:jsDispatcher('url=/server/clgl/bxf/delete.do`callbefore=jsDelete`callback=jsBkReload`backvalue=select`onload=ref1','store.FJLID=#{FJLID}');" label="����" value="ɾ��"></a>
			<input id="FJLID" javaName="FJLID" htmlName="store.FJLID" htmlType="hidden" label="��¼ID"></input>
			<input id="JLID" javaName="JLID" htmlName="store.JLID" htmlType="hidden" label="��¼ID"></input>
		</labelProvider>
		<labelProvider id="selectViewMain">
			<a id="CLPZHM" javaName="CLPZHM" label="��~���պ���" href="javascript:jsDispatcher('url=/server/clgl/bxf/selectMx.do`callback=buildXHTML`id=100`width=420`height=430`title=��ϸ','store.JLID=#{JLID}');" style="cursor:hand"></a>
			<input id="DWMC" javaName="DWMC" htmlName="model.DWMC" htmlType="text" label="�տλ���"></input>
			<input id="BDH" javaName="BDH" htmlName="model.BDH" htmlType="text" label="������"></input>
			<input id="JE" javaName="JE" htmlName="model.JE" htmlType="text" label="���"></input>
			<input id="JFRQ" javaName="JFRQ" htmlName="model.JFRQ" htmlType="text" label="��������"></input>
			<input id="FJLID" javaName="FJLID" htmlName="store.FJLID" htmlType="hidden" label="��¼ID"></input>
			<input id="JLID" javaName="JLID" htmlName="store.JLID" htmlType="hidden" label="��¼ID"></input>
		</labelProvider>
		<labelProvider id="viewAdd">
			<date id="JFRQ" javaName="model.JFRQ" htmlName="model.JFRQ" htmlType="text"></date>
			<input id="BDH" javaName="model.BDH" htmlName="model.BDH" htmlType="text" label="������" prompt="*" dataType="Require" msg="�����뱣����"></input>
			<input id="CLPZHM" javaName="model.CLPZHM" htmlName="model.CLPZHM" htmlType="userSelect" label="��~���պ���" prompt="*" dataType="Require" msg="�����복~���պ���"></input>
			<input id="JE" javaName="model.JE" htmlName="model.JE" htmlType="text" label="���" prompt="*" dataType="Range" msg="��������" min="0" max="9999999999"></input>
			<textarea id="BZ" javaName="model.BZ" htmlName="model.BZ" label="��ע"></textarea>
			<input id="DWMC" javaName="model.DWMC" htmlName="model.DWMC" htmlType="userSelect" label="�տλ���" prompt="*" dataType="Require" msg="�������տλ"></input>
			<!--  <button id="btnButton" value="���" onClick="jsDispatcher('url=/server/clgl/bxf/add.do`callback=buildXHTML`onload=ref1', viewAdd)"></button> -->
			<button id="btnButton" value="���" onClick="jsDispatcher('url=/server/clgl/bxf/add.do`callback=buildXHTML', viewAdd)"></button>
			<input id="DWID" javaName="model.DWID" htmlName="model.DWID" htmlType="hidden" dataType="Require" msg="�������տλID"></input>
			<input id="CLID" javaName="model.CLID" htmlName="model.CLID" htmlType="hidden" dataType="Require" msg="�����복~ID"></input>
		</labelProvider>
		<labelProvider id="viewEdit" extends="viewAdd">
			<input id="JLID" javaName="model.JLID" htmlName="store.JLID" htmlType="hidden"></input>
			<button id="btnButton" value="�޸�" onClick="jsDispatcher('url=/server/clgl/bxf/edit.do`callback=jsBkReload`backvalue=select','viewEdit')"></button>
		</labelProvider>
		<labelProvider></labelProvider>
		<labelProvider>
			<input></input>
		</labelProvider>
		<labelProvider>
			<input></input>
		</labelProvider>
		<labelProvider>
			<description></description>
			<input></input>
		</labelProvider>
		<labelProvider>
			<description></description>
			<input>
				<option value="b"><![CDATA[a]]></option>
				<option value="c"><![CDATA[b]]></option>
			</input>
		</labelProvider>
	</labelProviders>
	<action actionClass="net.htjs.clgl.actions.BxfAction" id="bxfAction" base="/server/clgl/bxf/">
		<method id="viewMain">
			<target id="t1" container="conditionAreaDiv">
				<viewer cacheLevel="9" id="v1" xslt="/server/global/xslt/defaultFormSearch.xsl" labelProvider="viewMain" buildMode="js" cache="false" description=""></viewer>
			</target>
		</method>
		<method id="select">
			<target id="t1" container="barRightDiv">
				<viewer cacheLevel="9" id="v1" xslt="viewContainer.xsl" buildMode="js" description=""></viewer>
			</target>
			<target id="t2" container="viewMainAreaDiv">
				<viewer cacheLevel="9" id="v1" xslt="/server/global/xslt/defaultFormSearch.xsl" labelProvider="viewMain" buildMode="js" description="" contentProvider="this"></viewer>
			</target>
			<target id="t3" container="selectAreaDiv">
				<viewer cacheLevel="9" id="v1" xslt="/server/global/xslt/defaultDataGrid.xsl" labelProvider="select" buildMode="js" contentProvider="this.store.data" description=""></viewer>
			</target>
		</method>
		<method id="viewAdd">
			<target id="t1" container="jsBindObject">
				<viewer cacheLevel="9" contentProvider="this" labelProvider="viewAdd" xslt="/server/clgl/bxf/bx.xsl" id="v12" buildMode="js" cache="false" xsltContainer="editAreaDiv"></viewer>
				<viewer></viewer>
			</target>
		</method>
		<method id="add"></method>
		<method id="viewEdit">
			<target id="t1" container="jsWindow">
				<viewer cacheLevel="9" id="v1" contentProvider="this" labelProvider="viewEdit" buildMode="js" xslt="/server/global/xslt/defaultFormEdit.xsl"></viewer>
			</target>
		</method>
		<method id="edit"></method>
		<method id="delete"></method>
		<method id="selectMx">
			<target id="t1" container="jsWindowTab">
				<viewer id="v1" xslt="viewAddContainer.xsl" cacheLevel="2" buildMode="js" description="��ϸ"></viewer>
			</target>
			<target container="addDiv" id="t2">
				<viewer id="t2" labelProvider="selectMx" xslt="/server/global/xslt/defaultFormPrview.xsl" contentProvider="this.store.data" buildMode="js" cache="false" cacheLevel="2"></viewer>
			</target>
			<target container="editDiv" id="t3">
				<viewer id="t3" labelProvider="viewclMx" xslt="/server/global/xslt/defaultFormPrview.xsl" contentProvider="this.store.data" buildMode="js" cache="false" cacheLevel="2"></viewer>
			</target>
		</method>
		<method id="selectViewMain" jsMethod="4" jsMsg="3" forms="2" description="11"></method>
		<![CDATA[sdddddddddddddddddddddddddddsdddddddddddddddddddddddddddsdddddddddddddddddddddddddddsddddddddddddddddddddddddddd]]>
	</action>
	<pojos>
		<pojo dataset="aaa">
			<property><![CDATA[aassww ddqq dsdsdaassww ddqq dsdsdaassww ddqq dsdsdaassww ddqq dsdsdaassww ddqq dsdsdaassww ddqq dsdsdaassww ddqq dsdsdaassww ddqq dsdsdaassww ddqq dsdsdaassww ddqq dsdsdaassww ddqq dsdsdaassww ddqq dsdsdaassww ddqq dsdsd]]></property>
			<property initValue="sas" description="sss" columnType="wwqas" column="sa" primaryKey="sa"><![CDATA[ddddddddddddddddddddd1111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111]]></property>
			<property></property>
			<![CDATA[dsdsds dsdsd]]>
			<property></property>
			<property></property>
		</pojo>
		<![CDATA[ssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss]]>
	</pojos>
	<charset contentType="1" from="2"></charset>
	<daos>
		<dao id="moduleDaoxxx" class="net.htjs.sys.dao.ModuleDao" dataset="user">
			<description><![CDATA[sddsds]]></description>
			<method id="selectModule" methodName="selectModule" type="select">
				<description><![CDATA[bbbbb]]></description>
				<params>
					<param name="aaa" type="java.util.List" description="aaaaa"></param>
					<param name="cccc" type="java.util.List" description="cccccc"></param>
				</params>
				<exceptions>
					<exception><![CDATA[java.util.DAOException]]></exception>
					<exception><![CDATA[java.util.Exception]]></exception>
				</exceptions>
				<return><![CDATA[java.lang.String]]></return>
			</method>
		</dao>
		<dao>
			<method>
				<params>
					<param></param>
				</params>
				<exceptions>
					<exception></exception>
				</exceptions>
				<return></return>
			</method>
		</dao>
		<dao id="ssss"></dao>
		<dao></dao>
	</daos>
	<services>
		<service>
			<description></description>
			<method>
				<params>
					<param></param>
				</params>
				<exceptions></exceptions>
				<return></return>
			</method>
			<method>
				<exceptions>
					<exception></exception>
				</exceptions>
				<return></return>
				<description></description>
				<params>
					<param></param>
				</params>
			</method>
		</service>
		<service>
			<description></description>
			<method></method>
		</service>
		<service>
			<description></description>
			<method>
				<description></description>
				<params>
					<param></param>
				</params>
				<exceptions>
					<exception></exception>
					<exception></exception>
				</exceptions>
				<return></return>
			</method>
		</service>
		<service>
			<description></description>
		</service>
	</services>
	<ejbs>
		<ejb>
			<description></description>
			<method>
				<exceptions></exceptions>
			</method>
		</ejb>
		<ejb>
			<method>
				<return></return>
				<exceptions></exceptions>
				<params>
					<param></param>
				</params>
			</method>
		</ejb>
	</ejbs>
	<action>
		<description></description>
		<method>
			<data>
				<contentProvider></contentProvider>
				<labelProvider></labelProvider>
				<contentProvider></contentProvider>
				<description></description>
			</data>
			<description></description>
			<data></data>
		</method>
		<method>
			<description></description>
			<data>
				<contentProvider></contentProvider>
				<description></description>
				<labelProvider></labelProvider>
			</data>
			<params></params>
			<params>
				<![CDATA[ds]]>
				<param target="2"></param>
				<param target="1"></param>
			</params>
		</method>
		<description><![CDATA[�¸¹���123456]]></description>
	</action>
	<ejbs>
		<ejb>
			<description></description>
			<method></method>
		</ejb>
	</ejbs>
	<import></import>
	<contentProviders></contentProviders>
	<labelProviders>
		<labelProvider id="dddd">
			<description></description>
			<img></img>
			<select request="true" notNull="false"></select>
			<input request="true"></input>
			<checkbox request="true"></checkbox>
			<radio request="true"></radio>
			<hidden request="false"></hidden>
			<submit></submit>
			<button></button>
			<reset></reset>
			<image></image>
			<file request="true"></file>
			<textarea request="false"></textarea>
			<iframe></iframe>
			<label></label>
			<a></a>
			<session></session>
			<request></request>
		</labelProvider>
		<labelProvider>
			<file request="true"></file>
		</labelProvider>
	</labelProviders>
	<pojos>
		<pojo dataset="cl_cl" class="sdf��ʱ�Ĺ���">
			<property name="ssdwid" type="java.lang.Integer" column="SSDWID" columnType="NUMBER" initValue="" description="????ID"></property>
			<property name="clpzhm" type="java.lang.String" column="CLPZHM" columnType="VARCHAR2" initValue="" description="??????"></property>
			<property name="dm_cllx" type="java.lang.Integer" column="DM_CLLX" columnType="NUMBER" initValue="" description="??????"></property>
			<property name="clpp" type="java.lang.String" column="CLPP" columnType="VARCHAR2" initValue="" description="�����ͺ�"></property>
			<property name="clxh" type="java.lang.String" column="CLXH" columnType="VARCHAR2" initValue="" description="��~�ͺ�"></property>
			<property name="pql" type="java.lang.Integer" column="PQL" columnType="NUMBER" initValue="" description="???ml"></property>
			<property name="clyz" type="java.lang.Integer" column="CLYZ" columnType="NUMBER" initValue="" description="�������"></property>
			<property name="cljz" type="java.lang.Integer" column="CLJZ" columnType="NUMBER" initValue="" description="????"></property>
			<property name="bfrq" type="java.util.Date" column="BFRQ" columnType="DATE" initValue="" description="????"></property>
			<property name="dm_tscl" type="java.lang.Integer" column="DM_TSCL" columnType="NUMBER" initValue="" description="??????"></property>
			<property name="ckzt" type="java.lang.Integer" column="CKZT" columnType="NUMBER" initValue="null" description="????"></property>
			<property name="cldj" type="java.lang.String" column="CLDJ" columnType="VARCHAR2" initValue="" description="????"></property>
			<property name="xslc" type="java.lang.Integer" column="XSLC" columnType="NUMBER" initValue="" description="��ʻ���"></property>
			<property name="dm_clys" type="java.lang.Integer" column="DM_CLYS" columnType="NUMBER" initValue="null" description="????"></property>
			<property name="cjh" type="java.lang.String" column="CJH" columnType="VARCHAR2" initValue="" description="???"></property>
			<property name="jkbz" type="java.lang.Integer" column="JKBZ" columnType="NUMBER" initValue="null" description="????"></property>
			<property name="fdjh" type="java.lang.String" column="FDJH" columnType="VARCHAR2" initValue="" description="?????"></property>
			<property name="fdjxh" type="java.lang.String" column="FDJXH" columnType="VARCHAR2" initValue="" description="?????"></property>
			<property name="dm_rlzl" type="java.lang.Integer" column="DM_RLZL" columnType="NUMBER" initValue="" description="????"></property>
			<property name="gl" type="java.lang.Integer" column="GL" columnType="NUMBER" initValue="" description="??kw"></property>
			<property name="zzcmc" type="java.lang.String" column="ZZCMC" columnType="VARCHAR2" initValue="" description="?????"></property>
			<property name="zxxs" type="java.lang.String" column="ZXXS" columnType="VARCHAR2" initValue="" description="????"></property>
			<property name="ljq" type="java.lang.Integer" column="LJQ" columnType="NUMBER" initValue="" description="???mm"></property>
			<property name="ljh" type="java.lang.Integer" column="LJH" columnType="NUMBER" initValue="" description="???mm"></property>
			<property name="lts" type="java.lang.Integer" column="LTS" columnType="NUMBER" initValue="" description="???"></property>
			<property name="ltgg" type="java.lang.String" column="LTGG" columnType="VARCHAR2" initValue="" description="????"></property>
			<property name="gbthps" type="java.lang.Integer" column="GBTHPS" columnType="NUMBER" initValue="" description="??????"></property>
			<property name="zj" type="java.lang.Integer" column="ZJ" columnType="NUMBER" initValue="" description="??mm"></property>
			<property name="zs" type="java.lang.Integer" column="ZS" columnType="NUMBER" initValue="" description="??"></property>
			<property name="wkc" type="java.lang.Integer" column="WKC" columnType="NUMBER" initValue="" description="?????mm"></property>
			<property name="wkk" type="java.lang.Integer" column="WKK" columnType="NUMBER" initValue="" description="?????mm"></property>
			<property name="wkg" type="java.lang.Integer" column="WKG" columnType="NUMBER" initValue="" description="?????mm"></property>
			<property name="hxc" type="java.lang.Integer" column="HXC" columnType="NUMBER" initValue="" description="???????mm"></property>
			<property name="hxk" type="java.lang.Integer" column="HXK" columnType="NUMBER" initValue="" description="???????mm"></property>
			<property name="hxg" type="java.lang.Integer" column="HXG" columnType="NUMBER" initValue="" description="???????mm"></property>
			<property name="zzl" type="java.lang.Integer" column="ZZL" columnType="NUMBER" initValue="" description="???kg"></property>
			<property name="hdzzl" type="java.lang.Integer" column="HDZZL" columnType="NUMBER" initValue="" description="?????kg"></property>
			<property name="hdzk" type="java.lang.Integer" column="HDZK" columnType="NUMBER" initValue="" description="????"></property>
			<property name="zqyzzl" type="java.lang.Integer" column="ZQYZZL" columnType="NUMBER" initValue="" description="??????"></property>
			<property name="jsszk" type="java.lang.Integer" column="JSSZK" columnType="NUMBER" initValue="" description="?????"></property>
			<property name="syxz" type="java.lang.String" column="SYXZ" columnType="VARCHAR2" initValue="" description="????"></property>
			<property name="clhdfs" type="java.lang.String" column="CLHDFS" columnType="VARCHAR2" initValue="" description="�ʽ�4Դ"></property>
			<property name="zzdw" type="java.lang.Integer" column="ZZDW" columnType="NUMBER" initValue="" description="?????"></property>
			<property name="ccrq" type="java.util.Date" column="CCRQ" columnType="DATE" initValue="" description="????"></property>
			<property name="gzrq" type="java.util.Date" column="GZRQ" columnType="DATE" initValue="" description="????"></property>
			<property name="jdcxsz_hm" type="java.lang.String" column="JDCXSZ_HM" columnType="VARCHAR2" initValue="" description="????????"></property>
			<property name="cgs_da_bh" type="java.lang.String" column="CGS_DA_BH" columnType="VARCHAR2" initValue="" description="?????????"></property>
			<property name="ckjs" type="java.lang.String" column="CKJS" columnType="VARCHAR2" initValue="" description="????"></property>
			<property name="yxbs" type="java.lang.String" column="YXBS" columnType="VARCHAR2" initValue="1" description="????"></property>
			<property name="lrry" type="java.lang.Integer" column="LRRY" columnType="NUMBER" initValue="0" description="????"></property>
			<property name="jlrq" type="java.util.Date" column="JLRQ" columnType="DATE" initValue="sysdate" description="????"></property>
			<property name="jlbs" type="java.lang.Integer" column="JLBS" columnType="NUMBER" initValue="0" description="????"></property>
			<property name="synx" type="java.lang.Integer" column="SYNX" columnType="NUMBER" initValue="" description="????"></property>
			<property name="syz" type="java.lang.String" column="SYZ" columnType="VARCHAR2" initValue="" description="ʹ����"></property>
			<property name="djrq" type="java.util.Date" column="DJRQ" columnType="DATE" initValue="" description="????"></property>
			<property name="jyk" type="java.lang.String" column="JYK" columnType="VARCHAR2" initValue="" description="���Ϳ�"></property>
			<property name="xgrq" type="java.util.Date" column="XGRQ" columnType="DATE" initValue="" description="��ʻ��������"></property>
			<property></property>
		</pojo>
		<pojo dataset="cl_cl" class="123" id="得到"><![CDATA[234方法567]]></pojo>
		<![CDATA[啊啊得到]]>
	</pojos>
</designMap>

