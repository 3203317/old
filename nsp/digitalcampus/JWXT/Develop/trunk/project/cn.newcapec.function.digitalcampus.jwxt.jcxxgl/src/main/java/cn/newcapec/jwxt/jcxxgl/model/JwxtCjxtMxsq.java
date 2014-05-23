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
 * JwxtCjxtMxsq entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="JWXT_CJXT_MXSQ"
    ,schema="JWXT"
)

public class JwxtCjxtMxsq extends cn.newcapec.function.digitalcampus.common.model.AbstractModel implements java.io.Serializable {


    // Fields    

     private String id;
     private String mxh;
     private String xh;
     private String kch;
     private String xnxqid;
     private String mxyy;
     private BigDecimal zhfs;
     private String yxsh;
     private String jwsh;
     private Date sqsj;
     private Date yxshsj;
     private Date jwshsj;
     private String cjr;
     private String jlssdw;
     private Date whsj;
     private Date cjsj;


    // Constructors

    /** default constructor */
    public JwxtCjxtMxsq() {
    }

	/** minimal constructor */
    public JwxtCjxtMxsq(String id, String mxh, String xh, String kch, String xnxqid, BigDecimal zhfs, Date sqsj, String cjr, String jlssdw, Date cjsj) {
        this.id = id;
        this.mxh = mxh;
        this.xh = xh;
        this.kch = kch;
        this.xnxqid = xnxqid;
        this.zhfs = zhfs;
        this.sqsj = sqsj;
        this.cjr = cjr;
        this.jlssdw = jlssdw;
        this.cjsj = cjsj;
    }
    
    /** full constructor */
    public JwxtCjxtMxsq(String id, String mxh, String xh, String kch, String xnxqid, String mxyy, BigDecimal zhfs, String yxsh, String jwsh, Date sqsj, Date yxshsj, Date jwshsj, String cjr, String jlssdw, Date whsj, Date cjsj) {
        this.id = id;
        this.mxh = mxh;
        this.xh = xh;
        this.kch = kch;
        this.xnxqid = xnxqid;
        this.mxyy = mxyy;
        this.zhfs = zhfs;
        this.yxsh = yxsh;
        this.jwsh = jwsh;
        this.sqsj = sqsj;
        this.yxshsj = yxshsj;
        this.jwshsj = jwshsj;
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
    
    @Column(name="MXH", nullable=false, length=32)

    public String getMxh() {
        return this.mxh;
    }
    
    public void setMxh(String mxh) {
        this.mxh = mxh;
    }
    
    @Column(name="XH", nullable=false, length=32)

    public String getXh() {
        return this.xh;
    }
    
    public void setXh(String xh) {
        this.xh = xh;
    }
    
    @Column(name="KCH", nullable=false, length=32)

    public String getKch() {
        return this.kch;
    }
    
    public void setKch(String kch) {
        this.kch = kch;
    }
    
    @Column(name="XNXQID", nullable=false, length=32)

    public String getXnxqid() {
        return this.xnxqid;
    }
    
    public void setXnxqid(String xnxqid) {
        this.xnxqid = xnxqid;
    }
    
    @Column(name="MXYY", length=2000)

    public String getMxyy() {
        return this.mxyy;
    }
    
    public void setMxyy(String mxyy) {
        this.mxyy = mxyy;
    }
    
    @Column(name="ZHFS", nullable=false, precision=22, scale=0)

    public BigDecimal getZhfs() {
        return this.zhfs;
    }
    
    public void setZhfs(BigDecimal zhfs) {
        this.zhfs = zhfs;
    }
    
    @Column(name="YXSH", length=2)

    public String getYxsh() {
        return this.yxsh;
    }
    
    public void setYxsh(String yxsh) {
        this.yxsh = yxsh;
    }
    
    @Column(name="JWSH", length=2)

    public String getJwsh() {
        return this.jwsh;
    }
    
    public void setJwsh(String jwsh) {
        this.jwsh = jwsh;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="SQSJ", nullable=false, length=7)

    public Date getSqsj() {
        return this.sqsj;
    }
    
    public void setSqsj(Date sqsj) {
        this.sqsj = sqsj;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="YXSHSJ", length=7)

    public Date getYxshsj() {
        return this.yxshsj;
    }
    
    public void setYxshsj(Date yxshsj) {
        this.yxshsj = yxshsj;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="JWSHSJ", length=7)

    public Date getJwshsj() {
        return this.jwshsj;
    }
    
    public void setJwshsj(Date jwshsj) {
        this.jwshsj = jwshsj;
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