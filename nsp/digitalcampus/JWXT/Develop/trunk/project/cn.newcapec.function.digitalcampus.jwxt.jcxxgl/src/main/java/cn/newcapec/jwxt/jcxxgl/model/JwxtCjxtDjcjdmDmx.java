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
 * JwxtCjxtDjcjdmDmx entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="JWXT_CJXT_DJCJDM_DMX"
    ,schema="JWXT"
)

public class JwxtCjxtDjcjdmDmx extends cn.newcapec.function.digitalcampus.common.model.AbstractModel implements java.io.Serializable {


    // Fields    

     private String id;
     private String jdcjid;
     private String dm;
     private String mc;
     private BigDecimal bfz;
     private String cjr;
     private String jlssdw;
     private Date whsj;
     private Date cjsj;


    // Constructors

    /** default constructor */
    public JwxtCjxtDjcjdmDmx() {
    }

	/** minimal constructor */
    public JwxtCjxtDjcjdmDmx(String id, String jdcjid, String dm, String mc, BigDecimal bfz, String cjr, String jlssdw, Date cjsj) {
        this.id = id;
        this.jdcjid = jdcjid;
        this.dm = dm;
        this.mc = mc;
        this.bfz = bfz;
        this.cjr = cjr;
        this.jlssdw = jlssdw;
        this.cjsj = cjsj;
    }
    
    /** full constructor */
    public JwxtCjxtDjcjdmDmx(String id, String jdcjid, String dm, String mc, BigDecimal bfz, String cjr, String jlssdw, Date whsj, Date cjsj) {
        this.id = id;
        this.jdcjid = jdcjid;
        this.dm = dm;
        this.mc = mc;
        this.bfz = bfz;
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
    
    @Column(name="JDCJID", nullable=false, length=2)

    public String getJdcjid() {
        return this.jdcjid;
    }
    
    public void setJdcjid(String jdcjid) {
        this.jdcjid = jdcjid;
    }
    
    @Column(name="DM", nullable=false, length=2)

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
    
    @Column(name="BFZ", nullable=false, precision=22, scale=0)

    public BigDecimal getBfz() {
        return this.bfz;
    }
    
    public void setBfz(BigDecimal bfz) {
        this.bfz = bfz;
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