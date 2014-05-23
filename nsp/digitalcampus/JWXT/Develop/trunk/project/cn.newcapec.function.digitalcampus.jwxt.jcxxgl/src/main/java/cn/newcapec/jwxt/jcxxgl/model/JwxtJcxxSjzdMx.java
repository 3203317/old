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
 * JwxtJcxxSjzdMx entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="JWXT_JCXX_SJZD_MX"
    ,schema="JWXT"
)

public class JwxtJcxxSjzdMx extends cn.newcapec.function.digitalcampus.common.model.AbstractModel implements java.io.Serializable {


    // Fields    

     private String id;
     private String zdid;
     private String dm;
     private String mc;
     private String sjdm;
     private BigDecimal dmjc;
     private String bz;
     private String cjr;
     private String jlssdw;
     private Date whsj;
     private Date cjsj;


    // Constructors

    /** default constructor */
    public JwxtJcxxSjzdMx() {
    }

	/** minimal constructor */
    public JwxtJcxxSjzdMx(String id, String zdid, String dm, String mc, String sjdm, BigDecimal dmjc, String cjr, String jlssdw, Date cjsj) {
        this.id = id;
        this.zdid = zdid;
        this.dm = dm;
        this.mc = mc;
        this.sjdm = sjdm;
        this.dmjc = dmjc;
        this.cjr = cjr;
        this.jlssdw = jlssdw;
        this.cjsj = cjsj;
    }
    
    /** full constructor */
    public JwxtJcxxSjzdMx(String id, String zdid, String dm, String mc, String sjdm, BigDecimal dmjc, String bz, String cjr, String jlssdw, Date whsj, Date cjsj) {
        this.id = id;
        this.zdid = zdid;
        this.dm = dm;
        this.mc = mc;
        this.sjdm = sjdm;
        this.dmjc = dmjc;
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
    
    @Column(name="ZDID", nullable=false, length=32)

    public String getZdid() {
        return this.zdid;
    }
    
    public void setZdid(String zdid) {
        this.zdid = zdid;
    }
    
    @Column(name="DM", nullable=false, length=32)

    public String getDm() {
        return this.dm;
    }
    
    public void setDm(String dm) {
        this.dm = dm;
    }
    
    @Column(name="MC", nullable=false, length=200)

    public String getMc() {
        return this.mc;
    }
    
    public void setMc(String mc) {
        this.mc = mc;
    }
    
    @Column(name="SJDM", nullable=false, length=32)

    public String getSjdm() {
        return this.sjdm;
    }
    
    public void setSjdm(String sjdm) {
        this.sjdm = sjdm;
    }
    
    @Column(name="DMJC", nullable=false, precision=22, scale=0)

    public BigDecimal getDmjc() {
        return this.dmjc;
    }
    
    public void setDmjc(BigDecimal dmjc) {
        this.dmjc = dmjc;
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