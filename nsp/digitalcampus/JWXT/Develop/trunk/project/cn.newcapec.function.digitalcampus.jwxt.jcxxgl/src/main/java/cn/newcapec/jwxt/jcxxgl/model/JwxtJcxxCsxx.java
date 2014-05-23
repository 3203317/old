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
 * JwxtJcxxCsxx entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="JWXT_JCXX_CSXX"
    ,schema="JWXT"
)

public class JwxtJcxxCsxx extends cn.newcapec.function.digitalcampus.common.model.AbstractModel implements java.io.Serializable {


    // Fields    

     private String id;
     private String jsh;
     private String xqh;
     private String jsmc;
     private String jsyt;
     private String jxlh;
     private BigDecimal szlc;
     private String mph;
     private BigDecimal zws;
     private BigDecimal yxzws;
     private BigDecimal kszws;
     private String jslxm;
     private String jsms;
     private String jsglbm;
     private String kqjbh;
     private String cjr;
     private String jlssdw;
     private Date whsj;
     private Date cjsj;


    // Constructors

    /** default constructor */
    public JwxtJcxxCsxx() {
    }

	/** minimal constructor */
    public JwxtJcxxCsxx(String id, String xqh, String jxlh, BigDecimal szlc, BigDecimal zws, BigDecimal yxzws, BigDecimal kszws, String jslxm, String cjr, String jlssdw, Date cjsj) {
        this.id = id;
        this.xqh = xqh;
        this.jxlh = jxlh;
        this.szlc = szlc;
        this.zws = zws;
        this.yxzws = yxzws;
        this.kszws = kszws;
        this.jslxm = jslxm;
        this.cjr = cjr;
        this.jlssdw = jlssdw;
        this.cjsj = cjsj;
    }
    
    /** full constructor */
    public JwxtJcxxCsxx(String id, String jsh, String xqh, String jsmc, String jsyt, String jxlh, BigDecimal szlc, String mph, BigDecimal zws, BigDecimal yxzws, BigDecimal kszws, String jslxm, String jsms, String jsglbm, String kqjbh, String cjr, String jlssdw, Date whsj, Date cjsj) {
        this.id = id;
        this.jsh = jsh;
        this.xqh = xqh;
        this.jsmc = jsmc;
        this.jsyt = jsyt;
        this.jxlh = jxlh;
        this.szlc = szlc;
        this.mph = mph;
        this.zws = zws;
        this.yxzws = yxzws;
        this.kszws = kszws;
        this.jslxm = jslxm;
        this.jsms = jsms;
        this.jsglbm = jsglbm;
        this.kqjbh = kqjbh;
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
    
    @Column(name="JSH", length=10)

    public String getJsh() {
        return this.jsh;
    }
    
    public void setJsh(String jsh) {
        this.jsh = jsh;
    }
    
    @Column(name="XQH", nullable=false, length=32)

    public String getXqh() {
        return this.xqh;
    }
    
    public void setXqh(String xqh) {
        this.xqh = xqh;
    }
    
    @Column(name="JSMC", length=200)

    public String getJsmc() {
        return this.jsmc;
    }
    
    public void setJsmc(String jsmc) {
        this.jsmc = jsmc;
    }
    
    @Column(name="JSYT", length=2)

    public String getJsyt() {
        return this.jsyt;
    }
    
    public void setJsyt(String jsyt) {
        this.jsyt = jsyt;
    }
    
    @Column(name="JXLH", nullable=false, length=32)

    public String getJxlh() {
        return this.jxlh;
    }
    
    public void setJxlh(String jxlh) {
        this.jxlh = jxlh;
    }
    
    @Column(name="SZLC", nullable=false, precision=22, scale=0)

    public BigDecimal getSzlc() {
        return this.szlc;
    }
    
    public void setSzlc(BigDecimal szlc) {
        this.szlc = szlc;
    }
    
    @Column(name="MPH", length=10)

    public String getMph() {
        return this.mph;
    }
    
    public void setMph(String mph) {
        this.mph = mph;
    }
    
    @Column(name="ZWS", nullable=false, precision=22, scale=0)

    public BigDecimal getZws() {
        return this.zws;
    }
    
    public void setZws(BigDecimal zws) {
        this.zws = zws;
    }
    
    @Column(name="YXZWS", nullable=false, precision=22, scale=0)

    public BigDecimal getYxzws() {
        return this.yxzws;
    }
    
    public void setYxzws(BigDecimal yxzws) {
        this.yxzws = yxzws;
    }
    
    @Column(name="KSZWS", nullable=false, precision=22, scale=0)

    public BigDecimal getKszws() {
        return this.kszws;
    }
    
    public void setKszws(BigDecimal kszws) {
        this.kszws = kszws;
    }
    
    @Column(name="JSLXM", nullable=false, length=32)

    public String getJslxm() {
        return this.jslxm;
    }
    
    public void setJslxm(String jslxm) {
        this.jslxm = jslxm;
    }
    
    @Column(name="JSMS", length=2000)

    public String getJsms() {
        return this.jsms;
    }
    
    public void setJsms(String jsms) {
        this.jsms = jsms;
    }
    
    @Column(name="JSGLBM", length=32)

    public String getJsglbm() {
        return this.jsglbm;
    }
    
    public void setJsglbm(String jsglbm) {
        this.jsglbm = jsglbm;
    }
    
    @Column(name="KQJBH", length=2000)

    public String getKqjbh() {
        return this.kqjbh;
    }
    
    public void setKqjbh(String kqjbh) {
        this.kqjbh = kqjbh;
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