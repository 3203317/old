package net.foreworld.java.models;

import java.util.Date;

import net.foreworld.java.domain.BasePage;


/**
 * 
 * @author 	huangxin
 * @email  	huangxin@foreworld.net
 * @qq		3203317
 * @generated
 */
public class BaseModel extends BasePage{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9067461106995861851L;
	
	private Integer _id;
	
	public Integer getId(){
		return this._id;
	}
	
	public void setId(Integer $id){
		this._id = $id;
	}

	private String _id_operate;
	
	public String getId_operate(){
		return this._id_operate;
	}
	
	public void setId_operate(String $id_operate){
		this._id_operate = $id_operate;
	}
	
	
	private Date _addtime;
	
	public Date getAddtime(){
		return this._addtime;
	}
	
	public void setAddtime(Date $addtime){
		this._addtime = $addtime;
	}
	
	private Integer _opt_sysmanage_user_id;//0
	
	public Integer getOpt_sysmanage_user_id(){
		return this._opt_sysmanage_user_id;
	}
	
	public void setOpt_sysmanage_user_id(Integer $opt_sysmanage_user_id){
		this._opt_sysmanage_user_id = $opt_sysmanage_user_id;
	}
	
	private Integer _availsign;//1
	
	public Integer getAvailsign(){
		return this._availsign;
	}
	
	public void setAvailsign(Integer $availsign){
		this._availsign = $availsign;
	}
	
	private Integer _startusing;//1
	
	public Integer getStartusing(){
		return this._startusing;
	}
	
	public void setStartusing(Integer $startusing){
		this._startusing = $startusing;
	}
	
	////////********
	
	private String _opt_sysmanage_user_id_operate;
	
	public String getOpt_sysmanage_user_id_operate(){
		return this._opt_sysmanage_user_id_operate;
	}
	
	public void setOpt_sysmanage_user_id_operate(String $opt_sysmanage_user_id_operate){
		this._opt_sysmanage_user_id_operate = $opt_sysmanage_user_id_operate;
	}
	
	private String _addtime_operate;
	
	public String getAddtime_operate(){
		return this._addtime_operate;
	}
	
	public void setAddtime_operate(String $addtime_operate){
		this._addtime_operate = $addtime_operate;
	}

	private Date _addtime_startdate;
	
	public Date getAddtime_startdate(){
		return this._addtime_startdate;
	}
	
	public void setAddtime_startdate(Date $addtime_startdate){
		this._addtime_startdate = $addtime_startdate;
	}
	
	private Date _addtime_enddate;
	
	public Date getAddtime_enddate(){
		return this._addtime_enddate;
	}
	
	public void setAddtime_enddate(Date $addtime_enddate){
		this._addtime_enddate = $addtime_enddate;
	}

}