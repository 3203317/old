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
 * JwxtJcxxXxjbxx entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="JWXT_JCXX_XXJBXX"
    ,schema="JWXT"
)

public class JwxtJcxxXxjbxx extends cn.newcapec.function.digitalcampus.common.model.AbstractModel implements java.io.Serializable {


    // Fields    

     private String id;
     private String xxdm;
     private String xxmc;
     private String xxywmc;
     private String xxqtmc;
     private String xxdz;
     private String xxyzbm;
     private String xzqhm;
     private String jxny;
     private String xqr;
     private String xxxzm;
     private String xxbxlxm;
     private String xxjbzm;
     private String xxzgbmm;
     private String xxgh;
     private String xzxm;
     private String fddbrh;
     private String frzsh;
     private String dwfzrh;
     private String zzjgm;
     private String lxdh;
     private String czdh;
     private String dzxx;
     private String zydz;
     private String lsyg;
     private String xx211gczk;
     private String xx985gcyxzk;
     private String zdxxzk;
     private String yjsyzk;
     private String jbzljyzk;
     private String jbcrjyzk;
     private BigDecimal xkmls;
     private String gjsfxgzyxzk;
     private String bz;
     private String cjr;
     private String jlssdw;
     private Date whsj;
     private Date cjsj;


    // Constructors

    /** default constructor */
    public JwxtJcxxXxjbxx() {
    }

	/** minimal constructor */
    public JwxtJcxxXxjbxx(String id, String xxdm, String xxmc, String cjr, String jlssdw, Date cjsj) {
        this.id = id;
        this.xxdm = xxdm;
        this.xxmc = xxmc;
        this.cjr = cjr;
        this.jlssdw = jlssdw;
        this.cjsj = cjsj;
    }
    
    /** full constructor */
    public JwxtJcxxXxjbxx(String id, String xxdm, String xxmc, String xxywmc, String xxqtmc, String xxdz, String xxyzbm, String xzqhm, String jxny, String xqr, String xxxzm, String xxbxlxm, String xxjbzm, String xxzgbmm, String xxgh, String xzxm, String fddbrh, String frzsh, String dwfzrh, String zzjgm, String lxdh, String czdh, String dzxx, String zydz, String lsyg, String xx211gczk, String xx985gcyxzk, String zdxxzk, String yjsyzk, String jbzljyzk, String jbcrjyzk, BigDecimal xkmls, String gjsfxgzyxzk, String bz, String cjr, String jlssdw, Date whsj, Date cjsj) {
        this.id = id;
        this.xxdm = xxdm;
        this.xxmc = xxmc;
        this.xxywmc = xxywmc;
        this.xxqtmc = xxqtmc;
        this.xxdz = xxdz;
        this.xxyzbm = xxyzbm;
        this.xzqhm = xzqhm;
        this.jxny = jxny;
        this.xqr = xqr;
        this.xxxzm = xxxzm;
        this.xxbxlxm = xxbxlxm;
        this.xxjbzm = xxjbzm;
        this.xxzgbmm = xxzgbmm;
        this.xxgh = xxgh;
        this.xzxm = xzxm;
        this.fddbrh = fddbrh;
        this.frzsh = frzsh;
        this.dwfzrh = dwfzrh;
        this.zzjgm = zzjgm;
        this.lxdh = lxdh;
        this.czdh = czdh;
        this.dzxx = dzxx;
        this.zydz = zydz;
        this.lsyg = lsyg;
        this.xx211gczk = xx211gczk;
        this.xx985gcyxzk = xx985gcyxzk;
        this.zdxxzk = zdxxzk;
        this.yjsyzk = yjsyzk;
        this.jbzljyzk = jbzljyzk;
        this.jbcrjyzk = jbcrjyzk;
        this.xkmls = xkmls;
        this.gjsfxgzyxzk = gjsfxgzyxzk;
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
    
    @Column(name="XXMC", nullable=false, length=200)

    public String getXxmc() {
        return this.xxmc;
    }
    
    public void setXxmc(String xxmc) {
        this.xxmc = xxmc;
    }
    
    @Column(name="XXYWMC", length=200)

    public String getXxywmc() {
        return this.xxywmc;
    }
    
    public void setXxywmc(String xxywmc) {
        this.xxywmc = xxywmc;
    }
    
    @Column(name="XXQTMC", length=200)

    public String getXxqtmc() {
        return this.xxqtmc;
    }
    
    public void setXxqtmc(String xxqtmc) {
        this.xxqtmc = xxqtmc;
    }
    
    @Column(name="XXDZ", length=500)

    public String getXxdz() {
        return this.xxdz;
    }
    
    public void setXxdz(String xxdz) {
        this.xxdz = xxdz;
    }
    
    @Column(name="XXYZBM", length=6)

    public String getXxyzbm() {
        return this.xxyzbm;
    }
    
    public void setXxyzbm(String xxyzbm) {
        this.xxyzbm = xxyzbm;
    }
    
    @Column(name="XZQHM", length=32)

    public String getXzqhm() {
        return this.xzqhm;
    }
    
    public void setXzqhm(String xzqhm) {
        this.xzqhm = xzqhm;
    }
    
    @Column(name="JXNY", length=10)

    public String getJxny() {
        return this.jxny;
    }
    
    public void setJxny(String jxny) {
        this.jxny = jxny;
    }
    
    @Column(name="XQR", length=10)

    public String getXqr() {
        return this.xqr;
    }
    
    public void setXqr(String xqr) {
        this.xqr = xqr;
    }
    
    @Column(name="XXXZM", length=10)

    public String getXxxzm() {
        return this.xxxzm;
    }
    
    public void setXxxzm(String xxxzm) {
        this.xxxzm = xxxzm;
    }
    
    @Column(name="XXBXLXM", length=10)

    public String getXxbxlxm() {
        return this.xxbxlxm;
    }
    
    public void setXxbxlxm(String xxbxlxm) {
        this.xxbxlxm = xxbxlxm;
    }
    
    @Column(name="XXJBZM", length=10)

    public String getXxjbzm() {
        return this.xxjbzm;
    }
    
    public void setXxjbzm(String xxjbzm) {
        this.xxjbzm = xxjbzm;
    }
    
    @Column(name="XXZGBMM", length=32)

    public String getXxzgbmm() {
        return this.xxzgbmm;
    }
    
    public void setXxzgbmm(String xxzgbmm) {
        this.xxzgbmm = xxzgbmm;
    }
    
    @Column(name="XXGH", length=32)

    public String getXxgh() {
        return this.xxgh;
    }
    
    public void setXxgh(String xxgh) {
        this.xxgh = xxgh;
    }
    
    @Column(name="XZXM", length=20)

    public String getXzxm() {
        return this.xzxm;
    }
    
    public void setXzxm(String xzxm) {
        this.xzxm = xzxm;
    }
    
    @Column(name="FDDBRH", length=32)

    public String getFddbrh() {
        return this.fddbrh;
    }
    
    public void setFddbrh(String fddbrh) {
        this.fddbrh = fddbrh;
    }
    
    @Column(name="FRZSH", length=32)

    public String getFrzsh() {
        return this.frzsh;
    }
    
    public void setFrzsh(String frzsh) {
        this.frzsh = frzsh;
    }
    
    @Column(name="DWFZRH", length=32)

    public String getDwfzrh() {
        return this.dwfzrh;
    }
    
    public void setDwfzrh(String dwfzrh) {
        this.dwfzrh = dwfzrh;
    }
    
    @Column(name="ZZJGM", length=32)

    public String getZzjgm() {
        return this.zzjgm;
    }
    
    public void setZzjgm(String zzjgm) {
        this.zzjgm = zzjgm;
    }
    
    @Column(name="LXDH", length=20)

    public String getLxdh() {
        return this.lxdh;
    }
    
    public void setLxdh(String lxdh) {
        this.lxdh = lxdh;
    }
    
    @Column(name="CZDH", length=20)

    public String getCzdh() {
        return this.czdh;
    }
    
    public void setCzdh(String czdh) {
        this.czdh = czdh;
    }
    
    @Column(name="DZXX", length=100)

    public String getDzxx() {
        return this.dzxx;
    }
    
    public void setDzxx(String dzxx) {
        this.dzxx = dzxx;
    }
    
    @Column(name="ZYDZ", length=200)

    public String getZydz() {
        return this.zydz;
    }
    
    public void setZydz(String zydz) {
        this.zydz = zydz;
    }
    
    @Column(name="LSYG")

    public String getLsyg() {
        return this.lsyg;
    }
    
    public void setLsyg(String lsyg) {
        this.lsyg = lsyg;
    }
    
    @Column(name="XX211GCZK", length=1)

    public String getXx211gczk() {
        return this.xx211gczk;
    }
    
    public void setXx211gczk(String xx211gczk) {
        this.xx211gczk = xx211gczk;
    }
    
    @Column(name="XX985GCYXZK", length=1)

    public String getXx985gcyxzk() {
        return this.xx985gcyxzk;
    }
    
    public void setXx985gcyxzk(String xx985gcyxzk) {
        this.xx985gcyxzk = xx985gcyxzk;
    }
    
    @Column(name="ZDXXZK", length=1)

    public String getZdxxzk() {
        return this.zdxxzk;
    }
    
    public void setZdxxzk(String zdxxzk) {
        this.zdxxzk = zdxxzk;
    }
    
    @Column(name="YJSYZK", length=1)

    public String getYjsyzk() {
        return this.yjsyzk;
    }
    
    public void setYjsyzk(String yjsyzk) {
        this.yjsyzk = yjsyzk;
    }
    
    @Column(name="JBZLJYZK", length=1)

    public String getJbzljyzk() {
        return this.jbzljyzk;
    }
    
    public void setJbzljyzk(String jbzljyzk) {
        this.jbzljyzk = jbzljyzk;
    }
    
    @Column(name="JBCRJYZK", length=1)

    public String getJbcrjyzk() {
        return this.jbcrjyzk;
    }
    
    public void setJbcrjyzk(String jbcrjyzk) {
        this.jbcrjyzk = jbcrjyzk;
    }
    
    @Column(name="XKMLS", precision=22, scale=0)

    public BigDecimal getXkmls() {
        return this.xkmls;
    }
    
    public void setXkmls(BigDecimal xkmls) {
        this.xkmls = xkmls;
    }
    
    @Column(name="GJSFXGZYXZK", length=1)

    public String getGjsfxgzyxzk() {
        return this.gjsfxgzyxzk;
    }
    
    public void setGjsfxgzyxzk(String gjsfxgzyxzk) {
        this.gjsfxgzyxzk = gjsfxgzyxzk;
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