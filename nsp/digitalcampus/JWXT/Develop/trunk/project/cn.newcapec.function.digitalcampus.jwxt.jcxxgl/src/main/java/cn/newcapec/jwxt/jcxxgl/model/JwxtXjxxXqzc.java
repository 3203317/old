package cn.newcapec.jwxt.jcxxgl.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * JwxtXjxxXqzc entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="JWXT_XJXX_XQZC"
    ,schema="JWXT"
)

public class JwxtXjxxXqzc extends cn.newcapec.function.digitalcampus.common.model.AbstractModel implements java.io.Serializable {


    // Fields    

     private String id;
     private String bh;
     private String ssnj;
     private String yxid;
     private String zyh;
     private String zczkm;
     private String zcrq;
     private String xnxqid;
     private String cjr;
     private String jlssdw;
     private Date whsj;
     private Date cjsj;


    // Constructors

    /** default constructor */
    public JwxtXjxxXqzc() {
    }

	/** minimal constructor */
    public JwxtXjxxXqzc(String id, String bh, String ssnj, String yxid, String zyh, String zcrq, String xnxqid, String cjr, String jlssdw, Date cjsj) {
        this.id = id;
        this.bh = bh;
        this.ssnj = ssnj;
        this.yxid = yxid;
        this.zyh = zyh;
        this.zcrq = zcrq;
        this.xnxqid = xnxqid;
        this.cjr = cjr;
        this.jlssdw = jlssdw;
        this.cjsj = cjsj;
    }
    
    /** full constructor */
    public JwxtXjxxXqzc(String id, String bh, String ssnj, String yxid, String zyh, String zczkm, String zcrq, String xnxqid, String cjr, String jlssdw, Date whsj, Date cjsj) {
        this.id = id;
        this.bh = bh;
        this.ssnj = ssnj;
        this.yxid = yxid;
        this.zyh = zyh;
        this.zczkm = zczkm;
        this.zcrq = zcrq;
        this.xnxqid = xnxqid;
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
    
    @Column(name="BH", nullable=false, length=32)

    public String getBh() {
        return this.bh;
    }
    
    public void setBh(String bh) {
        this.bh = bh;
    }
    
    @Column(name="SSNJ", nullable=false, length=32)

    public String getSsnj() {
        return this.ssnj;
    }
    
    public void setSsnj(String ssnj) {
        this.ssnj = ssnj;
    }
    
    @Column(name="YXID", nullable=false, length=32)

    public String getYxid() {
        return this.yxid;
    }
    
    public void setYxid(String yxid) {
        this.yxid = yxid;
    }
    
    @Column(name="ZYH", nullable=false, length=32)

    public String getZyh() {
        return this.zyh;
    }
    
    public void setZyh(String zyh) {
        this.zyh = zyh;
    }
    
    @Column(name="ZCZKM", length=2)

    public String getZczkm() {
        return this.zczkm;
    }
    
    public void setZczkm(String zczkm) {
        this.zczkm = zczkm;
    }
    
    @Column(name="ZCRQ", nullable=false, length=10)

    public String getZcrq() {
        return this.zcrq;
    }
    
    public void setZcrq(String zcrq) {
        this.zcrq = zcrq;
    }
    
    @Column(name="XNXQID", nullable=false, length=10)

    public String getXnxqid() {
        return this.xnxqid;
    }
    
    public void setXnxqid(String xnxqid) {
        this.xnxqid = xnxqid;
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