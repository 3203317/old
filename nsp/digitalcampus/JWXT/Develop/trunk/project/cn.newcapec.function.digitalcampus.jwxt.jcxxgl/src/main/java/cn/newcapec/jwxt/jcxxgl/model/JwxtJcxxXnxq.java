package cn.newcapec.jwxt.jcxxgl.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * JwxtJcxxXnxq entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="JWXT_JCXX_XNXQ"
    ,schema="JWXT"
)

public class JwxtJcxxXnxq extends cn.newcapec.function.digitalcampus.common.model.AbstractModel implements java.io.Serializable {


    // Fields    

     private String id;
     private String xn;
     private String xq;
     private String xnxqmc;
     private String dqbz;
     private String bz;
     private String cjr;
     private String jlssdw;
     private Date whsj;
     private Date cjsj;


    // Constructors

    /** default constructor */
    public JwxtJcxxXnxq() {
    }

	/** minimal constructor */
    public JwxtJcxxXnxq(String id, String xn, String xq, String xnxqmc, String cjr, String jlssdw, Date cjsj) {
        this.id = id;
        this.xn = xn;
        this.xq = xq;
        this.xnxqmc = xnxqmc;
        this.cjr = cjr;
        this.jlssdw = jlssdw;
        this.cjsj = cjsj;
    }
    
    /** full constructor */
    public JwxtJcxxXnxq(String id, String xn, String xq, String xnxqmc, String dqbz, String bz, String cjr, String jlssdw, Date whsj, Date cjsj) {
        this.id = id;
        this.xn = xn;
        this.xq = xq;
        this.xnxqmc = xnxqmc;
        this.dqbz = dqbz;
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
    
    @Column(name="XN", nullable=false, length=4)

    public String getXn() {
        return this.xn;
    }
    
    public void setXn(String xn) {
        this.xn = xn;
    }
    
    @Column(name="XQ", nullable=false, length=2)

    public String getXq() {
        return this.xq;
    }
    
    public void setXq(String xq) {
        this.xq = xq;
    }
    
    @Column(name="XNXQMC", nullable=false, length=200)

    public String getXnxqmc() {
        return this.xnxqmc;
    }
    
    public void setXnxqmc(String xnxqmc) {
        this.xnxqmc = xnxqmc;
    }
    
    @Column(name="DQBZ", length=1)

    public String getDqbz() {
        return this.dqbz;
    }
    
    public void setDqbz(String dqbz) {
        this.dqbz = dqbz;
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