package cn.newcapec.jwxt.jcxxgl.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * JwxtPkxtKtxxJs entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="JWXT_PKXT_KTXX_JS"
    ,schema="JWXT"
)

public class JwxtPkxtKtxxJs extends cn.newcapec.function.digitalcampus.common.model.AbstractModel implements java.io.Serializable {


    // Fields    

     private String id;
     private String ktid;
     private String jgh;
     private String qszc;
     private String jszc;
     private String sklxm;
     private String xnxqid;
     private String bz;
     private String cjr;
     private String jlssdw;
     private Date whsj;
     private Date cjsj;


    // Constructors

    /** default constructor */
    public JwxtPkxtKtxxJs() {
    }

	/** minimal constructor */
    public JwxtPkxtKtxxJs(String id, String ktid, String jgh, String qszc, String jszc, String xnxqid, String cjr, String jlssdw, Date cjsj) {
        this.id = id;
        this.ktid = ktid;
        this.jgh = jgh;
        this.qszc = qszc;
        this.jszc = jszc;
        this.xnxqid = xnxqid;
        this.cjr = cjr;
        this.jlssdw = jlssdw;
        this.cjsj = cjsj;
    }
    
    /** full constructor */
    public JwxtPkxtKtxxJs(String id, String ktid, String jgh, String qszc, String jszc, String sklxm, String xnxqid, String bz, String cjr, String jlssdw, Date whsj, Date cjsj) {
        this.id = id;
        this.ktid = ktid;
        this.jgh = jgh;
        this.qszc = qszc;
        this.jszc = jszc;
        this.sklxm = sklxm;
        this.xnxqid = xnxqid;
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
    
    @Column(name="KTID", nullable=false, length=32)

    public String getKtid() {
        return this.ktid;
    }
    
    public void setKtid(String ktid) {
        this.ktid = ktid;
    }
    
    @Column(name="JGH", nullable=false, length=32)

    public String getJgh() {
        return this.jgh;
    }
    
    public void setJgh(String jgh) {
        this.jgh = jgh;
    }
    
    @Column(name="QSZC", nullable=false, length=10)

    public String getQszc() {
        return this.qszc;
    }
    
    public void setQszc(String qszc) {
        this.qszc = qszc;
    }
    
    @Column(name="JSZC", nullable=false, length=10)

    public String getJszc() {
        return this.jszc;
    }
    
    public void setJszc(String jszc) {
        this.jszc = jszc;
    }
    
    @Column(name="SKLXM", length=2)

    public String getSklxm() {
        return this.sklxm;
    }
    
    public void setSklxm(String sklxm) {
        this.sklxm = sklxm;
    }
    
    @Column(name="XNXQID", nullable=false, length=4)

    public String getXnxqid() {
        return this.xnxqid;
    }
    
    public void setXnxqid(String xnxqid) {
        this.xnxqid = xnxqid;
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