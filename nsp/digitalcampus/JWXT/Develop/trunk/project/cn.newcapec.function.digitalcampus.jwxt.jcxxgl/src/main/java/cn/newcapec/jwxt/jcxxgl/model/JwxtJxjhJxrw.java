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
 * JwxtJxjhJxrw entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="JWXT_JXJH_JXRW"
    ,schema="JWXT"
)

public class JwxtJxjhJxrw extends cn.newcapec.function.digitalcampus.common.model.AbstractModel implements java.io.Serializable {


    // Fields    

     private String id;
     private String nj;
     private String xq;
     private String yx;
     private String jys;
     private String kch;
     private String kcmc;
     private String kcxzm;
     private String kclx;
     private String kkfs;
     private String skfs;
     private String jxlx;
     private String xiq;
     private BigDecimal xqxf;
     private BigDecimal llxf;
     private BigDecimal zhxs;
     private BigDecimal shjxs;
     private BigDecimal sjxs;
     private BigDecimal syxs;
     private BigDecimal zxxs;
     private BigDecimal hbs;
     private BigDecimal kclb;
     private String kkz;
     private String zlx;
     private BigDecimal xsrs;
     private String sfpk;
     private String sfxk;
     private String skdx;
     private String rkjs;
     private String bz;
     private String qdbz;
     private Date qdsj;
     private String qdr;
     private String cjr;
     private String jlssdw;
     private Date whsj;
     private Date cjsj;


    // Constructors

    /** default constructor */
    public JwxtJxjhJxrw() {
    }

	/** minimal constructor */
    public JwxtJxjhJxrw(String id, String nj, String xq, String yx, String jys, String kch, String kcmc, String kcxzm, String kclx, String sfpk, String cjr, String jlssdw, Date cjsj) {
        this.id = id;
        this.nj = nj;
        this.xq = xq;
        this.yx = yx;
        this.jys = jys;
        this.kch = kch;
        this.kcmc = kcmc;
        this.kcxzm = kcxzm;
        this.kclx = kclx;
        this.sfpk = sfpk;
        this.cjr = cjr;
        this.jlssdw = jlssdw;
        this.cjsj = cjsj;
    }
    
    /** full constructor */
    public JwxtJxjhJxrw(String id, String nj, String xq, String yx, String jys, String kch, String kcmc, String kcxzm, String kclx, String kkfs, String skfs, String jxlx, String xiq, BigDecimal xqxf, BigDecimal llxf, BigDecimal zhxs, BigDecimal shjxs, BigDecimal sjxs, BigDecimal syxs, BigDecimal zxxs, BigDecimal hbs, BigDecimal kclb, String kkz, String zlx, BigDecimal xsrs, String sfpk, String sfxk, String skdx, String rkjs, String bz, String qdbz, Date qdsj, String qdr, String cjr, String jlssdw, Date whsj, Date cjsj) {
        this.id = id;
        this.nj = nj;
        this.xq = xq;
        this.yx = yx;
        this.jys = jys;
        this.kch = kch;
        this.kcmc = kcmc;
        this.kcxzm = kcxzm;
        this.kclx = kclx;
        this.kkfs = kkfs;
        this.skfs = skfs;
        this.jxlx = jxlx;
        this.xiq = xiq;
        this.xqxf = xqxf;
        this.llxf = llxf;
        this.zhxs = zhxs;
        this.shjxs = shjxs;
        this.sjxs = sjxs;
        this.syxs = syxs;
        this.zxxs = zxxs;
        this.hbs = hbs;
        this.kclb = kclb;
        this.kkz = kkz;
        this.zlx = zlx;
        this.xsrs = xsrs;
        this.sfpk = sfpk;
        this.sfxk = sfxk;
        this.skdx = skdx;
        this.rkjs = rkjs;
        this.bz = bz;
        this.qdbz = qdbz;
        this.qdsj = qdsj;
        this.qdr = qdr;
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
    
    @Column(name="NJ", nullable=false, length=4)

    public String getNj() {
        return this.nj;
    }
    
    public void setNj(String nj) {
        this.nj = nj;
    }
    
    @Column(name="XQ", nullable=false, length=2)

    public String getXq() {
        return this.xq;
    }
    
    public void setXq(String xq) {
        this.xq = xq;
    }
    
    @Column(name="YX", nullable=false, length=32)

    public String getYx() {
        return this.yx;
    }
    
    public void setYx(String yx) {
        this.yx = yx;
    }
    
    @Column(name="JYS", nullable=false, length=32)

    public String getJys() {
        return this.jys;
    }
    
    public void setJys(String jys) {
        this.jys = jys;
    }
    
    @Column(name="KCH", nullable=false, length=32)

    public String getKch() {
        return this.kch;
    }
    
    public void setKch(String kch) {
        this.kch = kch;
    }
    
    @Column(name="KCMC", nullable=false, length=32)

    public String getKcmc() {
        return this.kcmc;
    }
    
    public void setKcmc(String kcmc) {
        this.kcmc = kcmc;
    }
    
    @Column(name="KCXZM", nullable=false, length=2)

    public String getKcxzm() {
        return this.kcxzm;
    }
    
    public void setKcxzm(String kcxzm) {
        this.kcxzm = kcxzm;
    }
    
    @Column(name="KCLX", nullable=false, length=2)

    public String getKclx() {
        return this.kclx;
    }
    
    public void setKclx(String kclx) {
        this.kclx = kclx;
    }
    
    @Column(name="KKFS", length=2)

    public String getKkfs() {
        return this.kkfs;
    }
    
    public void setKkfs(String kkfs) {
        this.kkfs = kkfs;
    }
    
    @Column(name="SKFS", length=2)

    public String getSkfs() {
        return this.skfs;
    }
    
    public void setSkfs(String skfs) {
        this.skfs = skfs;
    }
    
    @Column(name="JXLX", length=2)

    public String getJxlx() {
        return this.jxlx;
    }
    
    public void setJxlx(String jxlx) {
        this.jxlx = jxlx;
    }
    
    @Column(name="XIQ", length=32)

    public String getXiq() {
        return this.xiq;
    }
    
    public void setXiq(String xiq) {
        this.xiq = xiq;
    }
    
    @Column(name="XQXF", precision=22, scale=0)

    public BigDecimal getXqxf() {
        return this.xqxf;
    }
    
    public void setXqxf(BigDecimal xqxf) {
        this.xqxf = xqxf;
    }
    
    @Column(name="LLXF", precision=22, scale=0)

    public BigDecimal getLlxf() {
        return this.llxf;
    }
    
    public void setLlxf(BigDecimal llxf) {
        this.llxf = llxf;
    }
    
    @Column(name="ZHXS", precision=22, scale=0)

    public BigDecimal getZhxs() {
        return this.zhxs;
    }
    
    public void setZhxs(BigDecimal zhxs) {
        this.zhxs = zhxs;
    }
    
    @Column(name="SHJXS", precision=22, scale=0)

    public BigDecimal getShjxs() {
        return this.shjxs;
    }
    
    public void setShjxs(BigDecimal shjxs) {
        this.shjxs = shjxs;
    }
    
    @Column(name="SJXS", precision=22, scale=0)

    public BigDecimal getSjxs() {
        return this.sjxs;
    }
    
    public void setSjxs(BigDecimal sjxs) {
        this.sjxs = sjxs;
    }
    
    @Column(name="SYXS", precision=22, scale=0)

    public BigDecimal getSyxs() {
        return this.syxs;
    }
    
    public void setSyxs(BigDecimal syxs) {
        this.syxs = syxs;
    }
    
    @Column(name="ZXXS", precision=22, scale=0)

    public BigDecimal getZxxs() {
        return this.zxxs;
    }
    
    public void setZxxs(BigDecimal zxxs) {
        this.zxxs = zxxs;
    }
    
    @Column(name="HBS", precision=22, scale=0)

    public BigDecimal getHbs() {
        return this.hbs;
    }
    
    public void setHbs(BigDecimal hbs) {
        this.hbs = hbs;
    }
    
    @Column(name="KCLB", precision=22, scale=0)

    public BigDecimal getKclb() {
        return this.kclb;
    }
    
    public void setKclb(BigDecimal kclb) {
        this.kclb = kclb;
    }
    
    @Column(name="KKZ", length=20)

    public String getKkz() {
        return this.kkz;
    }
    
    public void setKkz(String kkz) {
        this.kkz = kkz;
    }
    
    @Column(name="ZLX", length=2)

    public String getZlx() {
        return this.zlx;
    }
    
    public void setZlx(String zlx) {
        this.zlx = zlx;
    }
    
    @Column(name="XSRS", precision=22, scale=0)

    public BigDecimal getXsrs() {
        return this.xsrs;
    }
    
    public void setXsrs(BigDecimal xsrs) {
        this.xsrs = xsrs;
    }
    
    @Column(name="SFPK", nullable=false, length=2)

    public String getSfpk() {
        return this.sfpk;
    }
    
    public void setSfpk(String sfpk) {
        this.sfpk = sfpk;
    }
    
    @Column(name="SFXK", length=2)

    public String getSfxk() {
        return this.sfxk;
    }
    
    public void setSfxk(String sfxk) {
        this.sfxk = sfxk;
    }
    
    @Column(name="SKDX", length=2)

    public String getSkdx() {
        return this.skdx;
    }
    
    public void setSkdx(String skdx) {
        this.skdx = skdx;
    }
    
    @Column(name="RKJS", length=32)

    public String getRkjs() {
        return this.rkjs;
    }
    
    public void setRkjs(String rkjs) {
        this.rkjs = rkjs;
    }
    
    @Column(name="BZ", length=2000)

    public String getBz() {
        return this.bz;
    }
    
    public void setBz(String bz) {
        this.bz = bz;
    }
    
    @Column(name="QDBZ", length=2)

    public String getQdbz() {
        return this.qdbz;
    }
    
    public void setQdbz(String qdbz) {
        this.qdbz = qdbz;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="QDSJ", length=7)

    public Date getQdsj() {
        return this.qdsj;
    }
    
    public void setQdsj(Date qdsj) {
        this.qdsj = qdsj;
    }
    
    @Column(name="QDR", length=32)

    public String getQdr() {
        return this.qdr;
    }
    
    public void setQdr(String qdr) {
        this.qdr = qdr;
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