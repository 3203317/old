package net.newcapec.sca.reportpublish.das.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.newcapec.sca.dbconn.service.DefDBConnService;
import net.newcapec.sca.param.FilterParam;
import net.newcapec.sca.reportpublish.ReportPublish;
import net.newcapec.sca.reportpublish.das.ReportPublishDAS;
import net.newcapec.sca.sequence.SequenceService;
import net.newcapec.sca.util.DASFactory;

import org.apache.log4j.Logger;
import org.apache.tuscany.das.rdb.Command;
import org.apache.tuscany.das.rdb.DAS;
import org.apache.tuscany.das.rdb.config.ResultDescriptor;
import org.apache.tuscany.das.rdb.config.impl.ResultDescriptorImpl;
import org.oasisopen.sca.annotation.Reference;

import commonj.sdo.DataObject;

/**
 * <p>Title: DAS实现类 </p>
 * <p>Description:报表发布数据构件实现类</p>
 * <p>Copyright: 新开普电子股份有限公司 Copyright (c) 2013</p>
 * @author  普秀霞
 * @version
 * @date 创建日期：2013-01-08
 * 修改日期：
 * 修改人：
 * 复审人：
 */

public class ReportPublishDASImpl implements ReportPublishDAS {

	private static final Logger _log = Logger
	.getLogger(ReportPublishDASImpl.class);
	private SequenceService sequenceService;

	@Reference(name = "sequenceService", required = true)
	public void setSequenceService(SequenceService sequenceService) {
		this.sequenceService = sequenceService;
	}

	private DefDBConnService defDBConnService;

	@Reference(name = "defDBConnService", required = true)
	public void setDefDBConnService(DefDBConnService defDBConnService) {
			this.defDBConnService = defDBConnService;
	}

//	//获得DAS
//	public DAS getDAS()
//	{
//		DAS das = DASFactory.Factory.createDAS(defDBConnService.getDefDBConn(),null);
//		return das;
//	}


	//默认的功能模块das配置文件
	private static String FUNCTIONCONFIGFILE = "/reportpublish.xml";
	public DAS getDAS()
	{
		String path = "";
		path = this.getClass().getResource(FUNCTIONCONFIGFILE).getPath();
		DAS das = DASFactory.Factory.createDAS(defDBConnService.getDefDBConn(),path);
		return das;
	}



	public List<ReportPublish> findUnpublishReportList(Integer domainId,
			Integer orgId, List<FilterParam> filter, Integer begin,
			Integer limit) {

		DAS das = this.getDAS();
		StringBuilder   sbSql = new StringBuilder();
		sbSql.append(" select  t.* from ( select code, name, nvl(memo, '') memo , type , ");
		sbSql.append("   case  type  when 1  then  '报表'   when  2  then  '查询表单' end  type_name , ");
		sbSql.append("   resource_code , case  resource_code  when 0 then '未发布' else  '已发布' end  publish, ");
		sbSql.append("   rownum  row_num   from   p_report  a   where 1=1  ");

		if (filter != null) {
			for (FilterParam  filterParam : filter) {
				sbSql.append(" ");
				sbSql.append(filterParam.getRelation());
				sbSql.append(" a.");
				sbSql.append(filterParam.getField());
				sbSql.append(" ");
				sbSql.append(filterParam.getLogical());
				sbSql.append(" ");
				sbSql.append(filterParam.getValue().trim());
			}
		}

		sbSql.append(" order by a.code desc ) t ");

		if (begin > -1 && limit > -1)
			sbSql.append( " where t.row_num between ? and ? ");

		List<ResultDescriptor>  rdList = new ArrayList<ResultDescriptor>();
		String columns[] = {"code", "name", "memo", "type" ,"type_name","resource_code","publish","row_num"};
		rdList = getResultDescriptorList(columns,"p_report");
		Command   cmd = das.createCommand(sbSql.toString());
		if (begin > -1 && limit > -1){
			 cmd.setParameter(1, begin+1);
			 cmd.setParameter(2, begin + limit);
		}
		cmd.setResultDescriptors(rdList);
		DataObject  dataObject = cmd.executeQuery();

		List<ReportPublish>  cdList = new ArrayList<ReportPublish>();

		for (DataObject  data : (List<DataObject>) dataObject.getList("p_report")) {
			ReportPublish  reportPublish =  new  ReportPublish();
			reportPublish.setCode(data.getInt("code"));
			reportPublish.setName(data.getString("name"));
			reportPublish.setMemo(data.getString("memo"));
			reportPublish.setType(data.getInt("type"));
			reportPublish.setType_name(data.getString("type_name"));
			reportPublish.setResource_code(data.getInt("resource_code"));
			reportPublish.setPublish(data.getString("publish"));
			cdList.add(reportPublish);
		}
		return cdList;

	}

	/**
	 * @getResultDescriptorList
	 * @Description:根据 传入的列和表名获得List<ResultDescriptor>
	 * @return List<ResultDescriptor>
	 * @throws
	 * @author 普秀霞
	 * @date 2013-01-08  修改日期： 修改人： 复审人：
	 */
	public List<ResultDescriptor> getResultDescriptorList(String[] fields, String tableName) {

		List<ResultDescriptor> __list = new ArrayList<ResultDescriptor>();
		ResultDescriptor __rdesc = null;
		for (String field : fields) {
			__rdesc = new ResultDescriptorImpl();
			__rdesc.setColumnName(field);
			__rdesc.setTableName(tableName);
			__rdesc.setColumnType("commonj.sdo.String");
			__list.add(__rdesc);
		}
		return __list;
	}

	public Integer getUnpublishReportListRowCount(Integer resourceId,
			List<FilterParam> filter) {

		DAS das = this.getDAS();
		StringBuilder   sbSql = new StringBuilder();
		sbSql.append(" select count(1)  code  from   p_report  a  where 1=1 ");
        //组合sql语句条件
		if (filter != null) {
			for (FilterParam  filterParam : filter) {
				sbSql.append(" ");
				sbSql.append(filterParam.getRelation());
				sbSql.append(" a.");
				sbSql.append(filterParam.getField());
				sbSql.append(" ");
				sbSql.append(filterParam.getLogical());
				sbSql.append(" ");
				sbSql.append(filterParam.getValue());
			}
		}

		List<ResultDescriptor>  rdList = new ArrayList<ResultDescriptor>();
		ResultDescriptor   rdesc = null;
		rdesc = new ResultDescriptorImpl();
		rdesc.setColumnName("code");
		rdesc.setTableName("p_report");
		rdesc.setColumnType("commonj.sdo.String");
		rdList.add(rdesc);
		Command  cmd = das.createCommand(sbSql.toString());
		cmd.setResultDescriptors(rdList);
		DataObject  dataObject =  cmd.executeQuery();

		List<DataObject>  dataList = dataObject.getList("p_report");
		int  count = 0;
        //获得数量
		if (dataList != null && dataList.size() > 0) {
			DataObject  data = dataList.get(0);
			count = data.getInt("code");
		}
		return  count;
	}

	public  Boolean  publishReport(ReportPublish  reportPublish){
		Command  command = this.getDAS().getCommand("ReportPublish");
		command.setParameter(4, (String)reportPublish.getMenu_name()); // menu_name
		command.setParameter(5, (String)reportPublish.getMemo()); // memo
		command.setParameter(6, (Integer)reportPublish.getCode()); // form_code
		command.setParameter(7, (Integer)reportPublish.getUser_account_id());   // user_account_id
		command.setParameter(8, (Integer)reportPublish.getGrant_permission());   // current_role_permit
		command.setParameter(9, (Integer)reportPublish.getCode()); //report_code
		command.setParameter(10, (Integer)reportPublish.getType()); //report_code
//		command.setParameter(2, "TestProc"); // menu_name
//		command.setParameter(3, "TestProc"); // memo
//		command.setParameter(4, "3"); // form_code
//		command.setParameter(5, 9);   // user_account_id
//		command.setParameter(6, 1);   // current_role_permit
//		command.setParameter(7, 3);   // report_code
        try {
        	command.execute();
        	System.out.println("executePro begin");
            System.out.println(command.getParameter(1).toString());
            System.out.println(command.getParameter(2).toString());
            System.out.println(command.getParameter(3).toString());
            System.out.println("executePro end");
            reportPublish.setResource_code((Integer)command.getParameter(2));
            reportPublish.setUrl(command.getParameter(3).toString());
        	return  true ;
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("执行存储过程失败");
            return  false;
        }
	}

	public Boolean  revokePublishReport(ReportPublish  reportPublish){
		Boolean rtn = true;
		Command  command = this.getDAS().getCommand("RevokeReportPublish");
		command.setParameter(2,(Integer)reportPublish.getCode()); // report_code
		command.setParameter(3,(Integer)reportPublish.getResource_code()); // resource_code
//		command.setParameter(2,3); // report_code
//		command.setParameter(3,28); // resource_code
        try {
        	command.execute();
        	System.out.println("executePro begin");
            System.out.println(command.getParameter(1).toString());
            System.out.println("executePro end");
        	return  true;
        }catch(Exception e){
            e.printStackTrace();
            return  false;
        }
	}

}
