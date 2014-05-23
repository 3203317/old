package cn.newcapec.jwxt.jcxxgl.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * JwxtPkxtBjkb entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="JWXT_PKXT_BJKB"
    ,schema="JWXT"
)

public class JwxtPkxtBjkb extends cn.newcapec.function.digitalcampus.common.model.AbstractModel implements java.io.Serializable {


    // Fields    

     private String id;
     private String xnxqid;
     private String pkjxbid;
     private String bz;
     private String cjr;
     private String jlssdw;
     private Date whsj;
     private Date cjsj;


    // Constructors

    /** default constructor */
    public JwxtPkxtBjkb() {
    }

	/** minimal constructor */
    public JwxtPkxtBjkb(String id, String xnxqid, String pkjxbid, String cjr, String jlssdw, Date cjsj) {
        this.id = id;
        this.xnxqid = xnxqid;
        this.pkjxbid = pkjxbid;
        this.cjr = cjr;
        this.jlssdw = jlssdw;
        this.cjsj = cjsj;
    }
    
    /** full constructor */
    public JwxtPkxtBjkb(String id, String xnxqid, String pkjxbid, String bz, String cjr, String jlssdw, Date whsj, Date cjsj) {
        this.id = id;
        this.xnxqid = xnxqid;
        this.pkjxbid = pkjxbid;
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
    
    @Column(name="XNXQID", nullable=false, length=32)

    public String getXnxqid() {
        return this.xnxqid;
    }
    
    public void setXnxqid(String xnxqid) {
        this.xnxqid = xnxqid;
    }
    
    @Column(name="PKJXBID", nullable=false, length=32)

    public String getPkjxbid() {
        return this.pkjxbid;
    }
    
    public void setPkjxbid(String pkjxbid) {
        this.pkjxbid = pkjxbid;
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