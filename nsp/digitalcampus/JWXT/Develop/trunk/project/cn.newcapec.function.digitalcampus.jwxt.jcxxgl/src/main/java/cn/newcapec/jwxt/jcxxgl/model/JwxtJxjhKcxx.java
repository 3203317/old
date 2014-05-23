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
 * JwxtJxjhKcxx entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="JWXT_JXJH_KCXX"
    ,schema="JWXT"
)

public class JwxtJxjhKcxx extends cn.newcapec.function.digitalcampus.common.model.AbstractModel implements java.io.Serializable {


    // Fields    

     private String id;
     private String kch;
     private String kcmc;
     private String kcywmc;
     private BigDecimal xf;
     private BigDecimal zhxs;
     private BigDecimal zxs;
     private BigDecimal llxs;
     private BigDecimal syxs;
     private BigDecimal sjxs;
     private String kcjj;
     private String jc;
     private String cksm;
     private String kcfzr;
     private String kcksdwh;
     private String kclbm;
     private String kcxzm;
     private String khlxm;
     private String skjs;
     private String bz;
     private String cjr;
     private String jlssdw;
     private Date whsj;
     private Date cjsj;
     private String kclxm;
     private String kcsxm;


    // Constructors

    /** default constructor */
    public JwxtJxjhKcxx() {
    }

	/** minimal constructor */
    public JwxtJxjhKcxx(String id, String kch, String kcmc, String cjr, String jlssdw, Date cjsj) {
        this.id = id;
        this.kch = kch;
        this.kcmc = kcmc;
        this.cjr = cjr;
        this.jlssdw = jlssdw;
        this.cjsj = cjsj;
    }
    
    /** full constructor */
    public JwxtJxjhKcxx(String id, String kch, String kcmc, String kcywmc, BigDecimal xf, BigDecimal zhxs, BigDecimal zxs, BigDecimal llxs, BigDecimal syxs, BigDecimal sjxs, String kcjj, String jc, String cksm, String kcfzr, String kcksdwh, String kclbm, String kcxzm, String khlxm, String skjs, String bz, String cjr, String jlssdw, Date whsj, Date cjsj, String kclxm, String kcsxm) {
        this.id = id;
        this.kch = kch;
        this.kcmc = kcmc;
        this.kcywmc = kcywmc;
        this.xf = xf;
        this.zhxs = zhxs;
        this.zxs = zxs;
        this.llxs = llxs;
        this.syxs = syxs;
        this.sjxs = sjxs;
        this.kcjj = kcjj;
        this.jc = jc;
        this.cksm = cksm;
        this.kcfzr = kcfzr;
        this.kcksdwh = kcksdwh;
        this.kclbm = kclbm;
        this.kcxzm = kcxzm;
        this.khlxm = khlxm;
        this.skjs = skjs;
        this.bz = bz;
        this.cjr = cjr;
        this.jlssdw = jlssdw;
        this.whsj = whsj;
        this.cjsj = cjsj;
        this.kclxm = kclxm;
        this.kcsxm = kcsxm;
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
    
    @Column(name="KCH", nullable=false, length=32)

    public String getKch() {
        return this.kch;
    }
    
    public void setKch(String kch) {
        this.kch = kch;
    }
    
    @Column(name="KCMC", nullable=false, length=200)

    public String getKcmc() {
        return this.kcmc;
    }
    
    public void setKcmc(String kcmc) {
        this.kcmc = kcmc;
    }
    
    @Column(name="KCYWMC", length=32)

    public String getKcywmc() {
        return this.kcywmc;
    }
    
    public void setKcywmc(String kcywmc) {
        this.kcywmc = kcywmc;
    }
    
    @Column(name="XF", precision=22, scale=0)

    public BigDecimal getXf() {
        return this.xf;
    }
    
    public void setXf(BigDecimal xf) {
        this.xf = xf;
    }
    
    @Column(name="ZHXS", precision=22, scale=0)

    public BigDecimal getZhxs() {
        return this.zhxs;
    }
    
    public void setZhxs(BigDecimal zhxs) {
        this.zhxs = zhxs;
    }
    
    @Column(name="ZXS", precision=22, scale=0)

    public BigDecimal getZxs() {
        return this.zxs;
    }
    
    public void setZxs(BigDecimal zxs) {
        this.zxs = zxs;
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
    
    @Column(name="KCJJ", length=2000)

    public String getKcjj() {
        return this.kcjj;
    }
    
    public void setKcjj(String kcjj) {
        this.kcjj = kcjj;
    }
    
    @Column(name="JC", length=200)

    public String getJc() {
        return this.jc;
    }
    
    public void setJc(String jc) {
        this.jc = jc;
    }
    
    @Column(name="CKSM", length=200)

    public String getCksm() {
        return this.cksm;
    }
    
    public void setCksm(String cksm) {
        this.cksm = cksm;
    }
    
    @Column(name="KCFZR", length=32)

    public String getKcfzr() {
        return this.kcfzr;
    }
    
    public void setKcfzr(String kcfzr) {
        this.kcfzr = kcfzr;
    }
    
    @Column(name="KCKSDWH", length=32)

    public String getKcksdwh() {
        return this.kcksdwh;
    }
    
    public void setKcksdwh(String kcksdwh) {
        this.kcksdwh = kcksdwh;
    }
    
    @Column(name="KCLBM", length=2)

    public String getKclbm() {
        return this.kclbm;
    }
    
    public void setKclbm(String kclbm) {
        this.kclbm = kclbm;
    }
    
    @Column(name="KCXZM", length=2)

    public String getKcxzm() {
        return this.kcxzm;
    }
    
    public void setKcxzm(String kcxzm) {
        this.kcxzm = kcxzm;
    }
    
    @Column(name="KHLXM", length=2)

    public String getKhlxm() {
        return this.khlxm;
    }
    
    public void setKhlxm(String khlxm) {
        this.khlxm = khlxm;
    }
    
    @Column(name="SKJS", length=500)

    public String getSkjs() {
        return this.skjs;
    }
    
    public void setSkjs(String skjs) {
        this.skjs = skjs;
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
    
    @Column(name="KCLXM", length=2)

    public String getKclxm() {
        return this.kclxm;
    }
    
    public void setKclxm(String kclxm) {
        this.kclxm = kclxm;
    }
    
    @Column(name="KCSXM", length=2)

    public String getKcsxm() {
        return this.kcsxm;
    }
    
    public void setKcsxm(String kcsxm) {
        this.kcsxm = kcsxm;
    }
   








}