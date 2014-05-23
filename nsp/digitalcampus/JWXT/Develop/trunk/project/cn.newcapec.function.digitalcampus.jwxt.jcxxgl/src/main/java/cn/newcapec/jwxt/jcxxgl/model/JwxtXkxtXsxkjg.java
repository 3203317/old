package cn.newcapec.jwxt.jcxxgl.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * JwxtXkxtXsxkjg entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="JWXT_XKXT_XSXKJG"
    ,schema="JWXT"
)

public class JwxtXkxtXsxkjg extends cn.newcapec.function.digitalcampus.common.model.AbstractModel implements java.io.Serializable {


    // Fields    

     private String id;
     private String xnxqid;
     private String kcid;
     private String kclxm;
     private String xkmsm;
     private String qrbz;
     private Date xksj;
     private Date qrsj;
     private String cjr;
     private String jlssdw;
     private Date whsj;
     private Date cjsj;


    // Constructors

    /** default constructor */
    public JwxtXkxtXsxkjg() {
    }

	/** minimal constructor */
    public JwxtXkxtXsxkjg(String id, String xnxqid, String kcid, String kclxm, String xkmsm, String qrbz, Date xksj, String cjr, String jlssdw, Date cjsj) {
        this.id = id;
        this.xnxqid = xnxqid;
        this.kcid = kcid;
        this.kclxm = kclxm;
        this.xkmsm = xkmsm;
        this.qrbz = qrbz;
        this.xksj = xksj;
        this.cjr = cjr;
        this.jlssdw = jlssdw;
        this.cjsj = cjsj;
    }
    
    /** full constructor */
    public JwxtXkxtXsxkjg(String id, String xnxqid, String kcid, String kclxm, String xkmsm, String qrbz, Date xksj, Date qrsj, String cjr, String jlssdw, Date whsj, Date cjsj) {
        this.id = id;
        this.xnxqid = xnxqid;
        this.kcid = kcid;
        this.kclxm = kclxm;
        this.xkmsm = xkmsm;
        this.qrbz = qrbz;
        this.xksj = xksj;
        this.qrsj = qrsj;
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
    
    @Column(name="KCLXM", nullable=false, length=2)

    public String getKclxm() {
        return this.kclxm;
    }
    
    public void setKclxm(String kclxm) {
        this.kclxm = kclxm;
    }
    
    @Column(name="XKMSM", nullable=false, length=2)

    public String getXkmsm() {
        return this.xkmsm;
    }
    
    public void setXkmsm(String xkmsm) {
        this.xkmsm = xkmsm;
    }
    
    @Column(name="QRBZ", nullable=false, length=2)

    public String getQrbz() {
        return this.qrbz;
    }
    
    public void setQrbz(String qrbz) {
        this.qrbz = qrbz;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="XKSJ", nullable=false, length=7)

    public Date getXksj() {
        return this.xksj;
    }
    
    public void setXksj(Date xksj) {
        this.xksj = xksj;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="QRSJ", length=7)

    public Date getQrsj() {
        return this.qrsj;
    }
    
    public void setQrsj(Date qrsj) {
        this.qrsj = qrsj;
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