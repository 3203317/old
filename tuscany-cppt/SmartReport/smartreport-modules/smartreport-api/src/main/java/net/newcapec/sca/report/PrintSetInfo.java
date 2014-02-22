package net.newcapec.sca.report;
/**
 *
 *实体描述：通用报表打印信息
 *@author: 赵鹏飞
 *@date： 日期：2013-1-18 时间：下午04:01:12
 *@version 1.0
 *修改人：
 *修改时间：
 *修改备注：
 */
public class PrintSetInfo {
	private String title;//通用报表标题
	private boolean yema;//页码是否显示
	private boolean printdate;//打印时间是否显示
	private boolean printperson;//打印人是否显示
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public boolean isYema() {
		return yema;
	}
	public void setYema(boolean yema) {
		this.yema = yema;
	}

	public boolean isPrintdate() {
		return printdate;
	}
	public void setPrintdate(boolean printdate) {
		this.printdate = printdate;
	}
	public boolean isPrintperson() {
		return printperson;
	}
	public void setPrintperson(boolean printperson) {
		this.printperson = printperson;
	}



}
