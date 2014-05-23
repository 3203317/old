package cn.newcapec.jwxt.jcxxgl.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * JwxtPkxtBjkbMx entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="JWXT_PKXT_BJKB_MX"
    ,schema="JWXT"
)

public class JwxtPkxtBjkbMx extends cn.newcapec.function.digitalcampus.common.model.AbstractModel implements java.io.Serializable {


    // Fields    

     private String id;
     private String bjkbid;
     private Double zc;
     private Double xingqi;
     private String jcid;
     private String kcid;
     private String csid;
     private String jsids;
     private String jxff;
     private String bz;
     private String cjr;
     private String jlssdw;
     private Date whsj;
     private Date cjsj;


    // Constructors

    /** default constructor */
    public JwxtPkxtBjkbMx() {
    }

	/** minimal constructor */
    public JwxtPkxtBjkbMx(String id, String bjkbid, Double zc, Double xingqi, String jcid, String kcid, String csid, String jsids, String jxff, String cjr, String jlssdw, Date cjsj) {
        this.id = id;
        this.bjkbid = bjkbid;
        this.zc = zc;
        this.xingqi = xingqi;
        this.jcid = jcid;
        this.kcid = kcid;
        this.csid = csid;
        this.jsids = jsids;
        this.jxff = jxff;
        this.cjr = cjr;
        this.jlssdw = jlssdw;
        this.cjsj = cjsj;
    }
    
    /** full constructor */
    public JwxtPkxtBjkbMx(String id, String bjkbid, Double zc, Double xingqi, String jcid, String kcid, String csid, String jsids, String jxff, String bz, String cjr, String jlssdw, Date whsj, Date cjsj) {
        this.id = id;
        this.bjkbid = bjkbid;
        this.zc = zc;
        this.xingqi = xingqi;
        this.jcid = jcid;
        this.kcid = kcid;
        this.csid = csid;
        this.jsids = jsids;
        this.jxff = jxff;
        this.bz = bz;
        this.cjr = cjr;
        this.jlssdw = jlssdw;
        this.whsj = whsj;
        this.cjsj = cjsj;
    }

   
    // Property accessors
    @Id 
    
    @Column(name="ID", unique=true, nullable=false, length=32)

    public String getId() {
        return this.id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    @Column(name="BJKBID", nullable=false, length=32)

    public String getBjkbid() {
        return this.bjkbid;
    }
    
    public void setBjkbid(String bjkbid) {
        this.bjkbid = bjkbid;
    }
    
    @Column(name="ZC", nullable=false, precision=126, scale=0)

    public Double getZc() {
        return this.zc;
    }
    
    public void setZc(Double zc) {
        this.zc = zc;
    }
    
    @Column(name="XINGQI", nullable=false, precision=126, scale=0)

    public Double getXingqi() {
        return this.xingqi;
    }
    
    public void setXingqi(Double xingqi) {
        this.xingqi = xingqi;
    }
    
    @Column(name="JCID", nullable=false, length=32)

    public String getJcid() {
        return this.jcid;
    }
    
    public void setJcid(String jcid) {
        this.jcid = jcid;
    }
    
    @Column(name="KCID", nullable=false, length=32)

    public String getKcid() {
        return this.kcid;
    }
    
    public void setKcid(String kcid) {
        this.kcid = kcid;
    }
    
    @Column(name="CSID", nullable=false, length=32)

    public String getCsid() {
        return this.csid;
    }
    
    public void setCsid(String csid) {
        this.csid = csid;
    }
    
    @Column(name="JSIDS", nullable=false, length=2000)

    public String getJsids() {
        return this.jsids;
    }
    
    public void setJsids(String jsids) {
        this.jsids = jsids;
    }
    
    @Column(name="JXFF", nullable=false, length=32)

    public String getJxff() {
        return this.jxff;
    }
    
    public void setJxff(String jxff) {
        this.jxff = jxff;
    }
    
    @Column(name="BZ", length=2000)

    public String getBz() {
        return this.bz;
    }
    
    public void setBz(String bz) {
        this.bz = bz;
    }
    
    @Column(name="CJR", nullable=false, length=32)

    public String getCjr() {
        return this.cjr;
    }
    
    public void setCjr(String cjr) {
        this.cjr = cjr;
    }
    
    @Column(name="JLSSDW", nullable=false, length=32)

    public String getJlssdw() {
        return this.jlssdw;
    }
    
    public void setJlssdw(String jlssdw) {
        this.jlssdw = jlssdw;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="WHSJ", length=7)

    public Date getWhsj() {
        return this.whsj;
    }
    
    public void setWhsj(Date whsj) {
        this.whsj = whsj;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="CJSJ", nullable=false, length=7)

    public Date getCjsj() {
        return this.cjsj;
    }
    
    public void setCjsj(Date cjsj) {
        this.cjsj = cjsj;
    }
   








}