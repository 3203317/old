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
 * JwxtPkxtKtxxBj entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="JWXT_PKXT_KTXX_BJ"
    ,schema="JWXT"
)

public class JwxtPkxtKtxxBj extends cn.newcapec.function.digitalcampus.common.model.AbstractModel implements java.io.Serializable {


    // Fields    

     private String id;
     private String ktid;
     private String bjid;
     private BigDecimal rs;
     private String xnxqid;
     private String cjr;
     private String jlssdw;
     private Date whsj;
     private Date cjsj;


    // Constructors

    /** default constructor */
    public JwxtPkxtKtxxBj() {
    }

	/** minimal constructor */
    public JwxtPkxtKtxxBj(String id, String ktid, String bjid, BigDecimal rs, String xnxqid, String cjr, String jlssdw, Date cjsj) {
        this.id = id;
        this.ktid = ktid;
        this.bjid = bjid;
        this.rs = rs;
        this.xnxqid = xnxqid;
        this.cjr = cjr;
        this.jlssdw = jlssdw;
        this.cjsj = cjsj;
    }
    
    /** full constructor */
    public JwxtPkxtKtxxBj(String id, String ktid, String bjid, BigDecimal rs, String xnxqid, String cjr, String jlssdw, Date whsj, Date cjsj) {
        this.id = id;
        this.ktid = ktid;
        this.bjid = bjid;
        this.rs = rs;
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
    
    @Column(name="KTID", nullable=false, length=32)

    public String getKtid() {
        return this.ktid;
    }
    
    public void setKtid(String ktid) {
        this.ktid = ktid;
    }
    
    @Column(name="BJID", nullable=false, length=32)

    public String getBjid() {
        return this.bjid;
    }
    
    public void setBjid(String bjid) {
        this.bjid = bjid;
    }
    
    @Column(name="RS", nullable=false, precision=22, scale=0)

    public BigDecimal getRs() {
        return this.rs;
    }
    
    public void setRs(BigDecimal rs) {
        this.rs = rs;
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