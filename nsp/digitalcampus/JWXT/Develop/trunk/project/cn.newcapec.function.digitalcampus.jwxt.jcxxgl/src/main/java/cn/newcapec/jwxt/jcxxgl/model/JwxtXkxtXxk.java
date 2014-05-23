package cn.newcapec.jwxt.jcxxgl.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * JwxtXkxtXxk entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="JWXT_XKXT_XXK"
    ,schema="JWXT"
)

public class JwxtXkxtXxk extends cn.newcapec.function.digitalcampus.common.model.AbstractModel implements java.io.Serializable {


    // Fields    

     private String id;
     private String xnxqid;
     private String kcid;
     private BigDecimal rssx;
     private String xxzyid;
     private String xxnj;
     private String qrbz;
     private String xkmsm;
     private String cjr;
     private String jlssdw;
     private Date whsj;
     private Date cjsj;


    // Constructors

    /** default constructor */
    public JwxtXkxtXxk() {
    }

	/** minimal constructor */
    public JwxtXkxtXxk(String id, String xnxqid, String kcid, BigDecimal rssx, String qrbz, String cjr, String jlssdw, Date cjsj) {
        this.id = id;
        this.xnxqid = xnxqid;
        this.kcid = kcid;
        this.rssx = rssx;
        this.qrbz = qrbz;
        this.cjr = cjr;
        this.jlssdw = jlssdw;
        this.cjsj = cjsj;
    }
    
    /** full constructor */
    public JwxtXkxtXxk(String id, String xnxqid, String kcid, BigDecimal rssx, String xxzyid, String xxnj, String qrbz, String xkmsm, String cjr, String jlssdw, Date whsj, Date cjsj) {
        this.id = id;
        this.xnxqid = xnxqid;
        this.kcid = kcid;
        this.rssx = rssx;
        this.xxzyid = xxzyid;
        this.xxnj = xxnj;
        this.qrbz = qrbz;
        this.xkmsm = xkmsm;
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
    
    @Column(name="KCID", nullable=false, length=32)

    public String getKcid() {
        return this.kcid;
    }
    
    public void setKcid(String kcid) {
        this.kcid = kcid;
    }
    
    @Column(name="RSSX", nullable=false, precision=22, scale=0)

    public BigDecimal getRssx() {
        return this.rssx;
    }
    
    public void setRssx(BigDecimal rssx) {
        this.rssx = rssx;
    }
    
    @Column(name="XXZYID", length=500)

    public String getXxzyid() {
        return this.xxzyid;
    }
    
    public void setXxzyid(String xxzyid) {
        this.xxzyid = xxzyid;
    }
    
    @Column(name="XXNJ", length=300)

    public String getXxnj() {
        return this.xxnj;
    }
    
    public void setXxnj(String xxnj) {
        this.xxnj = xxnj;
    }
    
    @Column(name="QRBZ", nullable=false, length=2)

    public String getQrbz() {
        return this.qrbz;
    }
    
    public void setQrbz(String qrbz) {
        this.qrbz = qrbz;
    }
    
    @Column(name="XKMSM", length=2)

    public String getXkmsm() {
        return this.xkmsm;
    }
    
    public void setXkmsm(String xkmsm) {
        this.xkmsm = xkmsm;
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