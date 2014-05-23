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
 * JwxtJcxxCgxx entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="JWXT_JCXX_CGXX"
    ,schema="JWXT"
)

public class JwxtJcxxCgxx extends cn.newcapec.function.digitalcampus.common.model.AbstractModel implements java.io.Serializable {


    // Fields    

     private String id;
     private String jzwh;
     private String jzwmc;
     private String fwcqm;
     private String syzkm;
     private String xqh;
     private String jzwflm;
     private BigDecimal jzwcs;
     private String jcny;
     private String jzwdz;
     private String jzwzkm;
     private String jzwtp;
     private Double jzwzdmj;
     private String jzwyt;
     private String cgzt;
     private String cjr;
     private String jlssdw;
     private Date whsj;
     private Date cjsj;


    // Constructors

    /** default constructor */
    public JwxtJcxxCgxx() {
    }

	/** minimal constructor */
    public JwxtJcxxCgxx(String id, String jzwh, String jzwmc, String xqh, BigDecimal jzwcs, String jzwdz, String cjr, String jlssdw, Date cjsj) {
        this.id = id;
        this.jzwh = jzwh;
        this.jzwmc = jzwmc;
        this.xqh = xqh;
        this.jzwcs = jzwcs;
        this.jzwdz = jzwdz;
        this.cjr = cjr;
        this.jlssdw = jlssdw;
        this.cjsj = cjsj;
    }
    
    /** full constructor */
    public JwxtJcxxCgxx(String id, String jzwh, String jzwmc, String fwcqm, String syzkm, String xqh, String jzwflm, BigDecimal jzwcs, String jcny, String jzwdz, String jzwzkm, String jzwtp, Double jzwzdmj, String jzwyt, String cgzt, String cjr, String jlssdw, Date whsj, Date cjsj) {
        this.id = id;
        this.jzwh = jzwh;
        this.jzwmc = jzwmc;
        this.fwcqm = fwcqm;
        this.syzkm = syzkm;
        this.xqh = xqh;
        this.jzwflm = jzwflm;
        this.jzwcs = jzwcs;
        this.jcny = jcny;
        this.jzwdz = jzwdz;
        this.jzwzkm = jzwzkm;
        this.jzwtp = jzwtp;
        this.jzwzdmj = jzwzdmj;
        this.jzwyt = jzwyt;
        this.cgzt = cgzt;
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
    
    @Column(name="JZWH", nullable=false, length=32)

    public String getJzwh() {
        return this.jzwh;
    }
    
    public void setJzwh(String jzwh) {
        this.jzwh = jzwh;
    }
    
    @Column(name="JZWMC", nullable=false, length=200)

    public String getJzwmc() {
        return this.jzwmc;
    }
    
    public void setJzwmc(String jzwmc) {
        this.jzwmc = jzwmc;
    }
    
    @Column(name="FWCQM", length=2)

    public String getFwcqm() {
        return this.fwcqm;
    }
    
    public void setFwcqm(String fwcqm) {
        this.fwcqm = fwcqm;
    }
    
    @Column(name="SYZKM", length=2)

    public String getSyzkm() {
        return this.syzkm;
    }
    
    public void setSyzkm(String syzkm) {
        this.syzkm = syzkm;
    }
    
    @Column(name="XQH", nullable=false, length=32)

    public String getXqh() {
        return this.xqh;
    }
    
    public void setXqh(String xqh) {
        this.xqh = xqh;
    }
    
    @Column(name="JZWFLM", length=2)

    public String getJzwflm() {
        return this.jzwflm;
    }
    
    public void setJzwflm(String jzwflm) {
        this.jzwflm = jzwflm;
    }
    
    @Column(name="JZWCS", nullable=false, precision=22, scale=0)

    public BigDecimal getJzwcs() {
        return this.jzwcs;
    }
    
    public void setJzwcs(BigDecimal jzwcs) {
        this.jzwcs = jzwcs;
    }
    
    @Column(name="JCNY", length=10)

    public String getJcny() {
        return this.jcny;
    }
    
    public void setJcny(String jcny) {
        this.jcny = jcny;
    }
    
    @Column(name="JZWDZ", nullable=false, length=200)

    public String getJzwdz() {
        return this.jzwdz;
    }
    
    public void setJzwdz(String jzwdz) {
        this.jzwdz = jzwdz;
    }
    
    @Column(name="JZWZKM", length=2)

    public String getJzwzkm() {
        return this.jzwzkm;
    }
    
    public void setJzwzkm(String jzwzkm) {
        this.jzwzkm = jzwzkm;
    }
    
    @Column(name="JZWTP", length=100)

    public String getJzwtp() {
        return this.jzwtp;
    }
    
    public void setJzwtp(String jzwtp) {
        this.jzwtp = jzwtp;
    }
    
    @Column(name="JZWZDMJ", precision=9)

    public Double getJzwzdmj() {
        return this.jzwzdmj;
    }
    
    public void setJzwzdmj(Double jzwzdmj) {
        this.jzwzdmj = jzwzdmj;
    }
    
    @Column(name="JZWYT", length=2)

    public String getJzwyt() {
        return this.jzwyt;
    }
    
    public void setJzwyt(String jzwyt) {
        this.jzwyt = jzwyt;
    }
    
    @Column(name="CGZT", length=2)

    public String getCgzt() {
        return this.cgzt;
    }
    
    public void setCgzt(String cgzt) {
        this.cgzt = cgzt;
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