package cn.newcapec.jwxt.jcxxgl.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * JwxtJcxxXqxx entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="JWXT_JCXX_XQXX"
    ,schema="JWXT"
)

public class JwxtJcxxXqxx extends cn.newcapec.function.digitalcampus.common.model.AbstractModel implements java.io.Serializable {


    // Fields    

     private String id;
     private String xxdm;
     private String xqh;
     private String xqmc;
     private String xqdz;
     private String xqyzbm;
     private String xqlxdh;
     private String xqczdh;
     private String xqfzr;
     private String bz;
     private String cjr;
     private String jlssdw;
     private Date whsj;
     private Date cjsj;


    // Constructors

    /** default constructor */
    public JwxtJcxxXqxx() {
    }

	/** minimal constructor */
    public JwxtJcxxXqxx(String id, String xxdm, String xqh, String cjr, String jlssdw, Date cjsj) {
        this.id = id;
        this.xxdm = xxdm;
        this.xqh = xqh;
        this.cjr = cjr;
        this.jlssdw = jlssdw;
        this.cjsj = cjsj;
    }
    
    /** full constructor */
    public JwxtJcxxXqxx(String id, String xxdm, String xqh, String xqmc, String xqdz, String xqyzbm, String xqlxdh, String xqczdh, String xqfzr, String bz, String cjr, String jlssdw, Date whsj, Date cjsj) {
        this.id = id;
        this.xxdm = xxdm;
        this.xqh = xqh;
        this.xqmc = xqmc;
        this.xqdz = xqdz;
        this.xqyzbm = xqyzbm;
        this.xqlxdh = xqlxdh;
        this.xqczdh = xqczdh;
        this.xqfzr = xqfzr;
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
    
    @Column(name="XXDM", nullable=false, length=32)

    public String getXxdm() {
        return this.xxdm;
    }
    
    public void setXxdm(String xxdm) {
        this.xxdm = xxdm;
    }
    
    @Column(name="XQH", nullable=false, length=32)

    public String getXqh() {
        return this.xqh;
    }
    
    public void setXqh(String xqh) {
        this.xqh = xqh;
    }
    
    @Column(name="XQMC", length=200)

    public String getXqmc() {
        return this.xqmc;
    }
    
    public void setXqmc(String xqmc) {
        this.xqmc = xqmc;
    }
    
    @Column(name="XQDZ", length=2000)

    public String getXqdz() {
        return this.xqdz;
    }
    
    public void setXqdz(String xqdz) {
        this.xqdz = xqdz;
    }
    
    @Column(name="XQYZBM", length=6)

    public String getXqyzbm() {
        return this.xqyzbm;
    }
    
    public void setXqyzbm(String xqyzbm) {
        this.xqyzbm = xqyzbm;
    }
    
    @Column(name="XQLXDH", length=15)

    public String getXqlxdh() {
        return this.xqlxdh;
    }
    
    public void setXqlxdh(String xqlxdh) {
        this.xqlxdh = xqlxdh;
    }
    
    @Column(name="XQCZDH", length=15)

    public String getXqczdh() {
        return this.xqczdh;
    }
    
    public void setXqczdh(String xqczdh) {
        this.xqczdh = xqczdh;
    }
    
    @Column(name="XQFZR", length=32)

    public String getXqfzr() {
        return this.xqfzr;
    }
    
    public void setXqfzr(String xqfzr) {
        this.xqfzr = xqfzr;
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