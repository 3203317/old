package cn.newcapec.jwxt.jcxxgl.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * JwxtXjxxXsxxKz entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="JWXT_XJXX_XSXX_KZ"
    ,schema="JWXT"
)

public class JwxtXjxxXsxxKz extends cn.newcapec.function.digitalcampus.common.model.AbstractModel implements java.io.Serializable {


    // Fields    

     private String id;
     private String xh;
     private String ywxm;
     private String xmpy;
     private String csdm;
     private String gjdqm;
     private String sfjlxm;
     private String hyzkm;
     private String gatqwm;
     private String whcdm;
     private String xyzjm;
     private String xxm;
     private String sfzd;
     private String sfsfs;
     private String sfwps;
     private String gkzkzh;
     private String yzbm;
     private String txdz;
     private String pyfsm;
     private String hdxlfsm;
     private String sfxfz;
     private String ldfsm;
     private String cjr;
     private String jlssdw;
     private Date whsj;
     private Date cjsj;


    // Constructors

    /** default constructor */
    public JwxtXjxxXsxxKz() {
    }

	/** minimal constructor */
    public JwxtXjxxXsxxKz(String id, String xh, String gjdqm, String sfjlxm, String whcdm, String cjr, String jlssdw, Date cjsj) {
        this.id = id;
        this.xh = xh;
        this.gjdqm = gjdqm;
        this.sfjlxm = sfjlxm;
        this.whcdm = whcdm;
        this.cjr = cjr;
        this.jlssdw = jlssdw;
        this.cjsj = cjsj;
    }
    
    /** full constructor */
    public JwxtXjxxXsxxKz(String id, String xh, String ywxm, String xmpy, String csdm, String gjdqm, String sfjlxm, String hyzkm, String gatqwm, String whcdm, String xyzjm, String xxm, String sfzd, String sfsfs, String sfwps, String gkzkzh, String yzbm, String txdz, String pyfsm, String hdxlfsm, String sfxfz, String ldfsm, String cjr, String jlssdw, Date whsj, Date cjsj) {
        this.id = id;
        this.xh = xh;
        this.ywxm = ywxm;
        this.xmpy = xmpy;
        this.csdm = csdm;
        this.gjdqm = gjdqm;
        this.sfjlxm = sfjlxm;
        this.hyzkm = hyzkm;
        this.gatqwm = gatqwm;
        this.whcdm = whcdm;
        this.xyzjm = xyzjm;
        this.xxm = xxm;
        this.sfzd = sfzd;
        this.sfsfs = sfsfs;
        this.sfwps = sfwps;
        this.gkzkzh = gkzkzh;
        this.yzbm = yzbm;
        this.txdz = txdz;
        this.pyfsm = pyfsm;
        this.hdxlfsm = hdxlfsm;
        this.sfxfz = sfxfz;
        this.ldfsm = ldfsm;
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
    
    @Column(name="XH", nullable=false, length=30)

    public String getXh() {
        return this.xh;
    }
    
    public void setXh(String xh) {
        this.xh = xh;
    }
    
    @Column(name="YWXM", length=200)

    public String getYwxm() {
        return this.ywxm;
    }
    
    public void setYwxm(String ywxm) {
        this.ywxm = ywxm;
    }
    
    @Column(name="XMPY", length=200)

    public String getXmpy() {
        return this.xmpy;
    }
    
    public void setXmpy(String xmpy) {
        this.xmpy = xmpy;
    }
    
    @Column(name="CSDM", length=10)

    public String getCsdm() {
        return this.csdm;
    }
    
    public void setCsdm(String csdm) {
        this.csdm = csdm;
    }
    
    @Column(name="GJDQM", nullable=false, length=5)

    public String getGjdqm() {
        return this.gjdqm;
    }
    
    public void setGjdqm(String gjdqm) {
        this.gjdqm = gjdqm;
    }
    
    @Column(name="SFJLXM", nullable=false, length=2)

    public String getSfjlxm() {
        return this.sfjlxm;
    }
    
    public void setSfjlxm(String sfjlxm) {
        this.sfjlxm = sfjlxm;
    }
    
    @Column(name="HYZKM", length=2)

    public String getHyzkm() {
        return this.hyzkm;
    }
    
    public void setHyzkm(String hyzkm) {
        this.hyzkm = hyzkm;
    }
    
    @Column(name="GATQWM", length=32)

    public String getGatqwm() {
        return this.gatqwm;
    }
    
    public void setGatqwm(String gatqwm) {
        this.gatqwm = gatqwm;
    }
    
    @Column(name="WHCDM", nullable=false, length=2)

    public String getWhcdm() {
        return this.whcdm;
    }
    
    public void setWhcdm(String whcdm) {
        this.whcdm = whcdm;
    }
    
    @Column(name="XYZJM", length=2)

    public String getXyzjm() {
        return this.xyzjm;
    }
    
    public void setXyzjm(String xyzjm) {
        this.xyzjm = xyzjm;
    }
    
    @Column(name="XXM", length=2)

    public String getXxm() {
        return this.xxm;
    }
    
    public void setXxm(String xxm) {
        this.xxm = xxm;
    }
    
    @Column(name="SFZD", length=2)

    public String getSfzd() {
        return this.sfzd;
    }
    
    public void setSfzd(String sfzd) {
        this.sfzd = sfzd;
    }
    
    @Column(name="SFSFS", length=2)

    public String getSfsfs() {
        return this.sfsfs;
    }
    
    public void setSfsfs(String sfsfs) {
        this.sfsfs = sfsfs;
    }
    
    @Column(name="SFWPS", length=2)

    public String getSfwps() {
        return this.sfwps;
    }
    
    public void setSfwps(String sfwps) {
        this.sfwps = sfwps;
    }
    
    @Column(name="GKZKZH", length=32)

    public String getGkzkzh() {
        return this.gkzkzh;
    }
    
    public void setGkzkzh(String gkzkzh) {
        this.gkzkzh = gkzkzh;
    }
    
    @Column(name="YZBM", length=6)

    public String getYzbm() {
        return this.yzbm;
    }
    
    public void setYzbm(String yzbm) {
        this.yzbm = yzbm;
    }
    
    @Column(name="TXDZ", length=200)

    public String getTxdz() {
        return this.txdz;
    }
    
    public void setTxdz(String txdz) {
        this.txdz = txdz;
    }
    
    @Column(name="PYFSM", length=2)

    public String getPyfsm() {
        return this.pyfsm;
    }
    
    public void setPyfsm(String pyfsm) {
        this.pyfsm = pyfsm;
    }
    
    @Column(name="HDXLFSM", length=2)

    public String getHdxlfsm() {
        return this.hdxlfsm;
    }
    
    public void setHdxlfsm(String hdxlfsm) {
        this.hdxlfsm = hdxlfsm;
    }
    
    @Column(name="SFXFZ", length=2)

    public String getSfxfz() {
        return this.sfxfz;
    }
    
    public void setSfxfz(String sfxfz) {
        this.sfxfz = sfxfz;
    }
    
    @Column(name="LDFSM", length=2)

    public String getLdfsm() {
        return this.ldfsm;
    }
    
    public void setLdfsm(String ldfsm) {
        this.ldfsm = ldfsm;
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