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
 * JwxtJxjhPyfa entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="JWXT_JXJH_PYFA"
    ,schema="JWXT"
)

public class JwxtJxjhPyfa extends cn.newcapec.function.digitalcampus.common.model.AbstractModel implements java.io.Serializable {


    // Fields    

     private String id;
     private String jhpcbh;
     private String pyccm;
     private String kkmsm;
     private String yx;
     private String zy;
     private String sknj;
     private String skxq;
     private String ssjys;
     private String kch;
     private BigDecimal zxf;
     private BigDecimal xqxf;
     private BigDecimal zxs;
     private BigDecimal xqxs;
     private BigDecimal llxs;
     private BigDecimal syxs;
     private BigDecimal sjxs;
     private BigDecimal shjxs;
     private BigDecimal zxxs;
     private String kcxzm;
     private String kclxm;
     private String khlxm;
     private String zyfx;
     private String sfxwk;
     private String qzk;
     private String jxdg;
     private String cjr;
     private String jlssdw;
     private Date whsj;
     private Date cjsj;


    // Constructors

    /** default constructor */
    public JwxtJxjhPyfa() {
    }

	/** minimal constructor */
    public JwxtJxjhPyfa(String id, String jhpcbh, String pyccm, String kkmsm, String yx, String zy, String sknj, String skxq, String ssjys, String kch, BigDecimal zxf, String sfxwk, String cjr, String jlssdw, Date cjsj) {
        this.id = id;
        this.jhpcbh = jhpcbh;
        this.pyccm = pyccm;
        this.kkmsm = kkmsm;
        this.yx = yx;
        this.zy = zy;
        this.sknj = sknj;
        this.skxq = skxq;
        this.ssjys = ssjys;
        this.kch = kch;
        this.zxf = zxf;
        this.sfxwk = sfxwk;
        this.cjr = cjr;
        this.jlssdw = jlssdw;
        this.cjsj = cjsj;
    }
    
    /** full constructor */
    public JwxtJxjhPyfa(String id, String jhpcbh, String pyccm, String kkmsm, String yx, String zy, String sknj, String skxq, String ssjys, String kch, BigDecimal zxf, BigDecimal xqxf, BigDecimal zxs, BigDecimal xqxs, BigDecimal llxs, BigDecimal syxs, BigDecimal sjxs, BigDecimal shjxs, BigDecimal zxxs, String kcxzm, String kclxm, String khlxm, String zyfx, String sfxwk, String qzk, String jxdg, String cjr, String jlssdw, Date whsj, Date cjsj) {
        this.id = id;
        this.jhpcbh = jhpcbh;
        this.pyccm = pyccm;
        this.kkmsm = kkmsm;
        this.yx = yx;
        this.zy = zy;
        this.sknj = sknj;
        this.skxq = skxq;
        this.ssjys = ssjys;
        this.kch = kch;
        this.zxf = zxf;
        this.xqxf = xqxf;
        this.zxs = zxs;
        this.xqxs = xqxs;
        this.llxs = llxs;
        this.syxs = syxs;
        this.sjxs = sjxs;
        this.shjxs = shjxs;
        this.zxxs = zxxs;
        this.kcxzm = kcxzm;
        this.kclxm = kclxm;
        this.khlxm = khlxm;
        this.zyfx = zyfx;
        this.sfxwk = sfxwk;
        this.qzk = qzk;
        this.jxdg = jxdg;
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
    
    @Column(name="JHPCBH", nullable=false, length=32)

    public String getJhpcbh() {
        return this.jhpcbh;
    }
    
    public void setJhpcbh(String jhpcbh) {
        this.jhpcbh = jhpcbh;
    }
    
    @Column(name="PYCCM", nullable=false, length=2)

    public String getPyccm() {
        return this.pyccm;
    }
    
    public void setPyccm(String pyccm) {
        this.pyccm = pyccm;
    }
    
    @Column(name="KKMSM", nullable=false, length=2)

    public String getKkmsm() {
        return this.kkmsm;
    }
    
    public void setKkmsm(String kkmsm) {
        this.kkmsm = kkmsm;
    }
    
    @Column(name="YX", nullable=false, length=32)

    public String getYx() {
        return this.yx;
    }
    
    public void setYx(String yx) {
        this.yx = yx;
    }
    
    @Column(name="ZY", nullable=false, length=32)

    public String getZy() {
        return this.zy;
    }
    
    public void setZy(String zy) {
        this.zy = zy;
    }
    
    @Column(name="SKNJ", nullable=false, length=4)

    public String getSknj() {
        return this.sknj;
    }
    
    public void setSknj(String sknj) {
        this.sknj = sknj;
    }
    
    @Column(name="SKXQ", nullable=false, length=2)

    public String getSkxq() {
        return this.skxq;
    }
    
    public void setSkxq(String skxq) {
        this.skxq = skxq;
    }
    
    @Column(name="SSJYS", nullable=false, length=32)

    public String getSsjys() {
        return this.ssjys;
    }
    
    public void setSsjys(String ssjys) {
        this.ssjys = ssjys;
    }
    
    @Column(name="KCH", nullable=false, length=32)

    public String getKch() {
        return this.kch;
    }
    
    public void setKch(String kch) {
        this.kch = kch;
    }
    
    @Column(name="ZXF", nullable=false, precision=22, scale=0)

    public BigDecimal getZxf() {
        return this.zxf;
    }
    
    public void setZxf(BigDecimal zxf) {
        this.zxf = zxf;
    }
    
    @Column(name="XQXF", precision=22, scale=0)

    public BigDecimal getXqxf() {
        return this.xqxf;
    }
    
    public void setXqxf(BigDecimal xqxf) {
        this.xqxf = xqxf;
    }
    
    @Column(name="ZXS", precision=22, scale=0)

    public BigDecimal getZxs() {
        return this.zxs;
    }
    
    public void setZxs(BigDecimal zxs) {
        this.zxs = zxs;
    }
    
    @Column(name="XQXS", precision=22, scale=0)

    public BigDecimal getXqxs() {
        return this.xqxs;
    }
    
    public void setXqxs(BigDecimal xqxs) {
        this.xqxs = xqxs;
    }
    
    @Column(name="LLXS", precision=22, scale=0)

    public BigDecimal getLlxs() {
        return this.llxs;
    }
    
    public void setLlxs(BigDecimal llxs) {
        this.llxs = llxs;
    }
    
    @Column(name="SYXS", precision=22, scale=0)

    public BigDecimal getSyxs() {
        return this.syxs;
    }
    
    public void setSyxs(BigDecimal syxs) {
        this.syxs = syxs;
    }
    
    @Column(name="SJXS", precision=22, scale=0)

    public BigDecimal getSjxs() {
        return this.sjxs;
    }
    
    public void setSjxs(BigDecimal sjxs) {
        this.sjxs = sjxs;
    }
    
    @Column(name="SHJXS", precision=22, scale=0)

    public BigDecimal getShjxs() {
        return this.shjxs;
    }
    
    public void setShjxs(BigDecimal shjxs) {
        this.shjxs = shjxs;
    }
    
    @Column(name="ZXXS", precision=22, scale=0)

    public BigDecimal getZxxs() {
        return this.zxxs;
    }
    
    public void setZxxs(BigDecimal zxxs) {
        this.zxxs = zxxs;
    }
    
    @Column(name="KCXZM", length=2)

    public String getKcxzm() {
        return this.kcxzm;
    }
    
    public void setKcxzm(String kcxzm) {
        this.kcxzm = kcxzm;
    }
    
    @Column(name="KCLXM", length=2)

    public String getKclxm() {
        return this.kclxm;
    }
    
    public void setKclxm(String kclxm) {
        this.kclxm = kclxm;
    }
    
    @Column(name="KHLXM", length=2)

    public String getKhlxm() {
        return this.khlxm;
    }
    
    public void setKhlxm(String khlxm) {
        this.khlxm = khlxm;
    }
    
    @Column(name="ZYFX", length=200)

    public String getZyfx() {
        return this.zyfx;
    }
    
    public void setZyfx(String zyfx) {
        this.zyfx = zyfx;
    }
    
    @Column(name="SFXWK", nullable=false, length=2)

    public String getSfxwk() {
        return this.sfxwk;
    }
    
    public void setSfxwk(String sfxwk) {
        this.sfxwk = sfxwk;
    }
    
    @Column(name="QZK", length=300)

    public String getQzk() {
        return this.qzk;
    }
    
    public void setQzk(String qzk) {
        this.qzk = qzk;
    }
    
    @Column(name="JXDG")

    public String getJxdg() {
        return this.jxdg;
    }
    
    public void setJxdg(String jxdg) {
        this.jxdg = jxdg;
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