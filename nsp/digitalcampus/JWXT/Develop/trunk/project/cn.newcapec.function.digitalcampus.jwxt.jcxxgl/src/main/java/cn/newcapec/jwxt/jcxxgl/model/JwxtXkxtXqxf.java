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
 * JwxtXkxtXqxf entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="JWXT_XKXT_XQXF"
    ,schema="JWXT"
)

public class JwxtXkxtXqxf extends cn.newcapec.function.digitalcampus.common.model.AbstractModel implements java.io.Serializable {


    // Fields    

     private String id;
     private String nj;
     private String xq;
     private BigDecimal xxkms;
     private Double xxkfs;
     private BigDecimal gxkms;
     private Double gxkfs;
     private String cjr;
     private String jlssdw;
     private Date whsj;
     private Date cjsj;


    // Constructors

    /** default constructor */
    public JwxtXkxtXqxf() {
    }

	/** minimal constructor */
    public JwxtXkxtXqxf(String id, String nj, String xq, BigDecimal xxkms, Double xxkfs, BigDecimal gxkms, Double gxkfs, String cjr, String jlssdw, Date cjsj) {
        this.id = id;
        this.nj = nj;
        this.xq = xq;
        this.xxkms = xxkms;
        this.xxkfs = xxkfs;
        this.gxkms = gxkms;
        this.gxkfs = gxkfs;
        this.cjr = cjr;
        this.jlssdw = jlssdw;
        this.cjsj = cjsj;
    }
    
    /** full constructor */
    public JwxtXkxtXqxf(String id, String nj, String xq, BigDecimal xxkms, Double xxkfs, BigDecimal gxkms, Double gxkfs, String cjr, String jlssdw, Date whsj, Date cjsj) {
        this.id = id;
        this.nj = nj;
        this.xq = xq;
        this.xxkms = xxkms;
        this.xxkfs = xxkfs;
        this.gxkms = gxkms;
        this.gxkfs = gxkfs;
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
    
    @Column(name="NJ", nullable=false, length=32)

    public String getNj() {
        return this.nj;
    }
    
    public void setNj(String nj) {
        this.nj = nj;
    }
    
    @Column(name="XQ", nullable=false, length=32)

    public String getXq() {
        return this.xq;
    }
    
    public void setXq(String xq) {
        this.xq = xq;
    }
    
    @Column(name="XXKMS", nullable=false, precision=22, scale=0)

    public BigDecimal getXxkms() {
        return this.xxkms;
    }
    
    public void setXxkms(BigDecimal xxkms) {
        this.xxkms = xxkms;
    }
    
    @Column(name="XXKFS", nullable=false, precision=9)

    public Double getXxkfs() {
        return this.xxkfs;
    }
    
    public void setXxkfs(Double xxkfs) {
        this.xxkfs = xxkfs;
    }
    
    @Column(name="GXKMS", nullable=false, precision=22, scale=0)

    public BigDecimal getGxkms() {
        return this.gxkms;
    }
    
    public void setGxkms(BigDecimal gxkms) {
        this.gxkms = gxkms;
    }
    
    @Column(name="GXKFS", nullable=false, precision=9)

    public Double getGxkfs() {
        return this.gxkfs;
    }
    
    public void setGxkfs(Double gxkfs) {
        this.gxkfs = gxkfs;
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