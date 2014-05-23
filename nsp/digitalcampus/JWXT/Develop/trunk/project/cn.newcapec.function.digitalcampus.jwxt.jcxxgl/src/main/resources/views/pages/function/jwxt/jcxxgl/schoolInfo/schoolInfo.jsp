<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../../../common/head.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title></title>
<script type="text/javascript">
var PageMethod = {
	path :'<%=basePath %>',
	query:function(){
		
	},
	openEdit:function(){
		
	},
	saveOrUpdate:function(){
		
	},
	getById:function(){
		
	},
	remove:function(){
	
	},
	input:function(){
		
	},
	report:function(){
	
	},
	print:function(){
	
	},
	show:function(){
		
	}
}
</script>
</head>
<body>
		<div class="form_item">
		<form id="servProQueryForm" action="<%=basePath %>/sp/query" method="post">
			<ul>
				<li>
				<label style="width:100px">服务提供商名称：</label>
				<input id="queryName" name="queryName" type="text" class="text_input" /> 
					
					<a class="sub-btn sub-btn-over" href="#"
					onclick="query();" id="query"> <span><img 
					src="<%=basePath %>/images/search_ico.png" />查询</span>
					</a>
					
			</ul>
			<ul>
				<a class="sub-btn sub-btn-over" href="#" onclick="create()" id="create"> <span><img src="<%=basePath %>/images/zengjia_ico.png" />增加</span></a>
				<a class="sub-btn sub-btn-over" href="#" onclick="update()" id="update"> <span><img src="<%=basePath %>/images/xiugai_ico.png" />修改</span> </a>
				<a class="sub-btn sub-btn-over" href="#" onclick="del();" id="del"> <span><img src="<%=basePath %>/images/shanchu_ico.png" />删除</span></a>
				<a class="sub-btn sub-btn-over" href="#" onclick="del();" id="input"> <span><img src="<%=basePath %>/images/upload.png" />导入</span></a>
				<a class="sub-btn sub-btn-over" href="#" onclick="del();" id="report"> <span><img src="<%=basePath %>/images/drzy_ico.png" />导出</span></a>
			</ul>
		</form>
	</div>
	<div id="message">
		<table id="list">
		</table>
		<div id="pager"></div> 
	</div>
	<div id="dialog" style="display: none; position: relative;height:200px !important;">
        <form id="formDialog">
			<table width="100%" border="0" class="sp_maininfo">
				<tr height="30">
					<td><div align="right">提供商名称：</div></td>
					<input id="insertId" name="insertId" type="hidden" />
					<td><label><input id="insertName" name="insertName"
							type="text" size="20" class="required" title="请输入提供商名称"
							style="width: 150px;" maxlength="32"/>
					</label></td>
					<td><div align="right">提供商地址：</div></td>
					<td><label><input id="insertLoc" name="insertLoc"
							type="text" size="20" class="required" title="请输入提供商地址"
							style="width: 150px;" maxlength="32"/>
					</label></td>
				</tr>
				<tr height="30">
					<td><div align="right">提供商类型：</div></td>
				    <td><select id="insertType" name="insertType" style="width:154px;">
				      <option value="0">旧开普</option>
				      <option value="1">新开普</option>
				    </select></td>
				    <td style="text-align: right;"><span align="right">联系电话：</span></td>
				    <td><input id="insertTel" name="insertTel" type="text" 
				    size="15"  class="required" title="请联系电话" style="width:150px;"/></td>
				</tr>
				<tr height="30">
					<td><div align="right">电子邮件：</div></td>
					<td><label><input id="insertEmail" name="insertEmail"
							type="text" size="20" class="required" title="请输入电子邮件："
							style="width: 150px;" maxlength="32"/>
					</label></td>
					<td></td>
					<td></td>
				</tr>
				<tr style="height: 10px;">
					<td></td>
				</tr>
			</table>	
		</form>        
     </div>
     
     
	
</body>
</html>