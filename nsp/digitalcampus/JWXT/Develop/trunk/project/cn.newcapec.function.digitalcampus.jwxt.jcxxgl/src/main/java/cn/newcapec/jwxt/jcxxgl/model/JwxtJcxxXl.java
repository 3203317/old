package cn.newcapec.jwxt.jcxxgl.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * JwxtJcxxXl entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="JWXT_JCXX_XL"
    ,schema="JWXT"
)

public class JwxtJcxxXl extends cn.newcapec.function.digitalcampus.common.model.AbstractModel implements java.io.Serializable {


    // Fields    

     private String id;
     private String xnxqid;
     private String zc;
     private String xq;
     private String sklxm;
     private String bz;
     private String cjr;
     private String jlssdw;
     private Date whsj;
     private Date cjsj;
     private Date rq;


    // Constructors

    /** default constructor */
    public JwxtJcxxXl() {
    }

	/** minimal constructor */
    public JwxtJcxxXl(String id, String xnxqid, String zc, String xq, String cjr, String jlssdw, Date cjsj) {
        this.id = id;
        this.xnxqid = xnxqid;
        this.zc = zc;
        this.xq = xq;
        this.cjr = cjr;
        this.jlssdw = jlssdw;
        this.cjsj = cjsj;
    }
    
    /** full constructor */
    public JwxtJcxxXl(String id, String xnxqid, String zc, String xq, String sklxm, String bz, String cjr, String jlssdw, Date whsj, Date cjsj, Date rq) {
        this.id = id;
        this.xnxqid = xnxqid;
        this.zc = zc;
        this.xq = xq;
        this.sklxm = sklxm;
        this.bz = bz;
        this.cjr = cjr;
        this.jlssdw = jlssdw;
        this.whsj = whsj;
        this.cjsj = cjsj;
        this.rq = rq;
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
    
    @Column(name="XNXQID", nullable=false, length=32)

    public String getXnxqid() {
        return this.xnxqid;
    }
    
    public void setXnxqid(String xnxqid) {
        this.xnxqid = xnxqid;
    }
    
    @Column(name="ZC", nullable=false, length=2)

    public String getZc() {
        return this.zc;
    }
    
    public void setZc(String zc) {
        this.zc = zc;
    }
    
    @Column(name="XQ", nullable=false, length=2)

    public String getXq() {
        return this.xq;
    }
    
    public void setXq(String xq) {
        this.xq = xq;
    }
    
    @Column(name="SKLXM", length=2)

    public String getSklxm() {
        return this.sklxm;
    }
    
    public void setSklxm(String sklxm) {
        this.sklxm = sklxm;
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
    @Temporal(TemporalType.DATE)
    @Column(name="RQ", length=7)

    public Date getRq() {
        return this.rq;
    }
    
    public void setRq(Date rq) {
        this.rq = rq;
    }
   








}