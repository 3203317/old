package cn.newcapec.jwxt.jcxxgl.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * JwxtJcxxJzgxxKz entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="JWXT_JCXX_JZGXX_KZ"
    ,schema="JWXT"
)

public class JwxtJcxxJzgxxKz extends cn.newcapec.function.digitalcampus.common.model.AbstractModel implements java.io.Serializable {


    // Fields    

     private String id;
     private String jgh;
     private String ywxm;
     private String zp;
     private String xqh;
     private String tc;
     private String xklbm;
     private String yjxkm;
     private String ejxkm;
     private String yjfx;
     private String zc;
     private String ssjyb;
     private String gwh;
     private String bgdd;
     private String jl;
     private String zt;
     private String bz;
     private String cjr;
     private String jlssdw;
     private Date whsj;
     private Date cjsj;


    // Constructors

    /** default constructor */
    public JwxtJcxxJzgxxKz() {
    }

	/** minimal constructor */
    public JwxtJcxxJzgxxKz(String id, String jgh, String zt, String cjr, String jlssdw, Date cjsj) {
        this.id = id;
        this.jgh = jgh;
        this.zt = zt;
        this.cjr = cjr;
        this.jlssdw = jlssdw;
        this.cjsj = cjsj;
    }
    
    /** full constructor */
    public JwxtJcxxJzgxxKz(String id, String jgh, String ywxm, String zp, String xqh, String tc, String xklbm, String yjxkm, String ejxkm, String yjfx, String zc, String ssjyb, String gwh, String bgdd, String jl, String zt, String bz, String cjr, String jlssdw, Date whsj, Date cjsj) {
        this.id = id;
        this.jgh = jgh;
        this.ywxm = ywxm;
        this.zp = zp;
        this.xqh = xqh;
        this.tc = tc;
        this.xklbm = xklbm;
        this.yjxkm = yjxkm;
        this.ejxkm = ejxkm;
        this.yjfx = yjfx;
        this.zc = zc;
        this.ssjyb = ssjyb;
        this.gwh = gwh;
        this.bgdd = bgdd;
        this.jl = jl;
        this.zt = zt;
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
    
    @Column(name="JGH", nullable=false, length=30)

    public String getJgh() {
        return this.jgh;
    }
    
    public void setJgh(String jgh) {
        this.jgh = jgh;
    }
    
    @Column(name="YWXM", length=200)

    public String getYwxm() {
        return this.ywxm;
    }
    
    public void setYwxm(String ywxm) {
        this.ywxm = ywxm;
    }
    
    @Column(name="ZP", length=200)

    public String getZp() {
        return this.zp;
    }
    
    public void setZp(String zp) {
        this.zp = zp;
    }
    
    @Column(name="XQH", length=32)

    public String getXqh() {
        return this.xqh;
    }
    
    public void setXqh(String xqh) {
        this.xqh = xqh;
    }
    
    @Column(name="TC", length=200)

    public String getTc() {
        return this.tc;
    }
    
    public void setTc(String tc) {
        this.tc = tc;
    }
    
    @Column(name="XKLBM", length=10)

    public String getXklbm() {
        return this.xklbm;
    }
    
    public void setXklbm(String xklbm) {
        this.xklbm = xklbm;
    }
    
    @Column(name="YJXKM", length=10)

    public String getYjxkm() {
        return this.yjxkm;
    }
    
    public void setYjxkm(String yjxkm) {
        this.yjxkm = yjxkm;
    }
    
    @Column(name="EJXKM", length=10)

    public String getEjxkm() {
        return this.ejxkm;
    }
    
    public void setEjxkm(String ejxkm) {
        this.ejxkm = ejxkm;
    }
    
    @Column(name="YJFX", length=200)

    public String getYjfx() {
        return this.yjfx;
    }
    
    public void setYjfx(String yjfx) {
        this.yjfx = yjfx;
    }
    
    @Column(name="ZC", length=10)

    public String getZc() {
        return this.zc;
    }
    
    public void setZc(String zc) {
        this.zc = zc;
    }
    
    @Column(name="SSJYB", length=32)

    public String getSsjyb() {
        return this.ssjyb;
    }
    
    public void setSsjyb(String ssjyb) {
        this.ssjyb = ssjyb;
    }
    
    @Column(name="GWH", length=32)

    public String getGwh() {
        return this.gwh;
    }
    
    public void setGwh(String gwh) {
        this.gwh = gwh;
    }
    
    @Column(name="BGDD", length=200)

    public String getBgdd() {
        return this.bgdd;
    }
    
    public void setBgdd(String bgdd) {
        this.bgdd = bgdd;
    }
    
    @Column(name="JL")

    public String getJl() {
        return this.jl;
    }
    
    public void setJl(String jl) {
        this.jl = jl;
    }
    
    @Column(name="ZT", nullable=false, length=2)

    public String getZt() {
        return this.zt;
    }
    
    public void setZt(String zt) {
        this.zt = zt;
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