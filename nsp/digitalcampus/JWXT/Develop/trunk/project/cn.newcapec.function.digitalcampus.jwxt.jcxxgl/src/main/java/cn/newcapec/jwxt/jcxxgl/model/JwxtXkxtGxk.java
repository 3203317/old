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
 * JwxtXkxtGxk entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="JWXT_XKXT_GXK"
    ,schema="JWXT"
)

public class JwxtXkxtGxk extends cn.newcapec.function.digitalcampus.common.model.AbstractModel implements java.io.Serializable {


    // Fields    

     private String id;
     private String xnxqid;
     private String kcid;
     private String skjsid;
     private String sksj;
     private String skcd;
     private BigDecimal rssx;
     private String jxzyid;
     private String gx;
     private String jxnj;
     private String qrbz;
     private String cjr;
     private String jlssdw;
     private Date whsj;
     private Date cjsj;


    // Constructors

    /** default constructor */
    public JwxtXkxtGxk() {
    }

	/** minimal constructor */
    public JwxtXkxtGxk(String id, String xnxqid, String kcid, BigDecimal rssx, String qrbz, String cjr, String jlssdw, Date cjsj) {
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
    public JwxtXkxtGxk(String id, String xnxqid, String kcid, String skjsid, String sksj, String skcd, BigDecimal rssx, String jxzyid, String gx, String jxnj, String qrbz, String cjr, String jlssdw, Date whsj, Date cjsj) {
        this.id = id;
        this.xnxqid = xnxqid;
        this.kcid = kcid;
        this.skjsid = skjsid;
        this.sksj = sksj;
        this.skcd = skcd;
        this.rssx = rssx;
        this.jxzyid = jxzyid;
        this.gx = gx;
        this.jxnj = jxnj;
        this.qrbz = qrbz;
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
    
    @Column(name="SKJSID", length=32)

    public String getSkjsid() {
        return this.skjsid;
    }
    
    public void setSkjsid(String skjsid) {
        this.skjsid = skjsid;
    }
    
    @Column(name="SKSJ", length=32)

    public String getSksj() {
        return this.sksj;
    }
    
    public void setSksj(String sksj) {
        this.sksj = sksj;
    }
    
    @Column(name="SKCD", length=32)

    public String getSkcd() {
        return this.skcd;
    }
    
    public void setSkcd(String skcd) {
        this.skcd = skcd;
    }
    
    @Column(name="RSSX", nullable=false, precision=22, scale=0)

    public BigDecimal getRssx() {
        return this.rssx;
    }
    
    public void setRssx(BigDecimal rssx) {
        this.rssx = rssx;
    }
    
    @Column(name="JXZYID", length=500)

    public String getJxzyid() {
        return this.jxzyid;
    }
    
    public void setJxzyid(String jxzyid) {
        this.jxzyid = jxzyid;
    }
    
    @Column(name="GX", length=2)

    public String getGx() {
        return this.gx;
    }
    
    public void setGx(String gx) {
        this.gx = gx;
    }
    
    @Column(name="JXNJ", length=300)

    public String getJxnj() {
        return this.jxnj;
    }
    
    public void setJxnj(String jxnj) {
        this.jxnj = jxnj;
    }
    
    @Column(name="QRBZ", nullable=false, length=2)

    public String getQrbz() {
        return this.qrbz;
    }
    
    public void setQrbz(String qrbz) {
        this.qrbz = qrbz;
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