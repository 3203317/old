package cn.newcapec.jwxt.jcxxgl.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * JwxtPkxtKcap entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="JWXT_PKXT_KCAP"
    ,schema="JWXT"
)

public class JwxtPkxtKcap extends cn.newcapec.function.digitalcampus.common.model.AbstractModel implements java.io.Serializable {


    // Fields    

     private String id;
     private String kcid;
     private String xq;
     private String skjcid;
     private String qzzc;
     private String yqlxm;
     private String bz;
     private String cjr;
     private String jlssdw;
     private Date whsj;
     private Date cjsj;


    // Constructors

    /** default constructor */
    public JwxtPkxtKcap() {
    }

	/** minimal constructor */
    public JwxtPkxtKcap(String id, String kcid, String xq, String skjcid, String qzzc, String yqlxm, String cjr, String jlssdw, Date cjsj) {
        this.id = id;
        this.kcid = kcid;
        this.xq = xq;
        this.skjcid = skjcid;
        this.qzzc = qzzc;
        this.yqlxm = yqlxm;
        this.cjr = cjr;
        this.jlssdw = jlssdw;
        this.cjsj = cjsj;
    }
    
    /** full constructor */
    public JwxtPkxtKcap(String id, String kcid, String xq, String skjcid, String qzzc, String yqlxm, String bz, String cjr, String jlssdw, Date whsj, Date cjsj) {
        this.id = id;
        this.kcid = kcid;
        this.xq = xq;
        this.skjcid = skjcid;
        this.qzzc = qzzc;
        this.yqlxm = yqlxm;
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
    
    @Column(name="KCID", nullable=false, length=32)

    public String getKcid() {
        return this.kcid;
    }
    
    public void setKcid(String kcid) {
        this.kcid = kcid;
    }
    
    @Column(name="XQ", nullable=false, length=1)

    public String getXq() {
        return this.xq;
    }
    
    public void setXq(String xq) {
        this.xq = xq;
    }
    
    @Column(name="SKJCID", nullable=false, length=32)

    public String getSkjcid() {
        return this.skjcid;
    }
    
    public void setSkjcid(String skjcid) {
        this.skjcid = skjcid;
    }
    
    @Column(name="QZZC", nullable=false, length=100)

    public String getQzzc() {
        return this.qzzc;
    }
    
    public void setQzzc(String qzzc) {
        this.qzzc = qzzc;
    }
    
    @Column(name="YQLXM", nullable=false, length=2)

    public String getYqlxm() {
        return this.yqlxm;
    }
    
    public void setYqlxm(String yqlxm) {
        this.yqlxm = yqlxm;
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