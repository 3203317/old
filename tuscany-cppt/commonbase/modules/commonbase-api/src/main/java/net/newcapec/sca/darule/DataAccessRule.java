package net.newcapec.sca.darule;

import net.newcapec.sca.result.ResultMsg;

/**
 * <p>
 * Title: Dataaccessrule
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright(c) 2011 郑州新开普电子股份有限公司
 * </p>
 *
 * @author 黄鑫 (huangxin)
 * @version
 * @data 创建日期：2011-11-11 修改日期： 修改人： 复审人：
 * @generated
 */
public class DataAccessRule {

	private ResultMsg resultMsg;

	public ResultMsg getResultMsg() {
		return resultMsg;
	}

	public void setResultMsg(ResultMsg resultMsg) {
		this.resultMsg = resultMsg;
	}

	private Integer code;
	private String name;
	private Integer resource_code;
	private Integer type_code;
	private String content;
	private String create_date;
	private String encryption_info;
	private String encryption_timestamp;
	private String ver;



	public Integer getCode(){
		return code;
	}

	public void setCode(Integer code){
		this.code = code;
	}



	public String getName(){
		return name;
	}

	public void setName(String name){
		this.name = name;
	}



	public Integer getResource_code(){
		return resource_code;
	}

	public void setResource_code(Integer resource_code){
		this.resource_code = resource_code;
	}



	public Integer getType_code(){
		return type_code;
	}

	public void setType_code(Integer type_code){
		this.type_code = type_code;
	}



	public String getContent(){
		return content;
	}

	public void setContent(String content){
		this.content = content;
	}



	public String getCreate_date(){
		return create_date;
	}

	public void setCreate_date(String create_date){
		this.create_date = create_date;
	}



	public String getEncryption_info(){
		return encryption_info;
	}

	public void setEncryption_info(String encryption_info){
		this.encryption_info = encryption_info;
	}



	public String getEncryption_timestamp(){
		return encryption_timestamp;
	}

	public void setEncryption_timestamp(String encryption_timestamp){
		this.encryption_timestamp = encryption_timestamp;
	}



	public String getVer(){
		return ver;
	}

	public void setVer(String ver){
		this.ver = ver;
	}



}