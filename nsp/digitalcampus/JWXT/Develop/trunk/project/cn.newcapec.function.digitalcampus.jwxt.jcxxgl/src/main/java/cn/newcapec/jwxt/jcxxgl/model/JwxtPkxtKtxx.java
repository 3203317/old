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
 * JwxtPkxtKtxx entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="JWXT_PKXT_KTXX"
    ,schema="JWXT"
)

public class JwxtPkxtKtxx extends cn.newcapec.function.digitalcampus.common.model.AbstractModel implements java.io.Serializable {


    // Fields    

     private String id;
     private String kth;
     private String kcid;
     private String ktmc;
     private BigDecimal zrs;
     private BigDecimal zxs;
     private BigDecimal zhxs;
     private String qszc;
     private String jszc;
     private String kkyxid;
     private String apbz;
     private String xnxqid;
     private String bz;
     private String cjr;
     private String jlssdw;
     private Date whsj;
     private Date cjsj;


    // Constructors

    /** default constructor */
    public JwxtPkxtKtxx() {
    }

	/** minimal constructor */
    public JwxtPkxtKtxx(String id, String kth, String kcid, String ktmc, String cjr, String jlssdw, Date cjsj) {
        this.id = id;
        this.kth = kth;
        this.kcid = kcid;
        this.ktmc = ktmc;
        this.cjr = cjr;
        this.jlssdw = jlssdw;
        this.cjsj = cjsj;
    }
    
    /** full constructor */
    public JwxtPkxtKtxx(String id, String kth, String kcid, String ktmc, BigDecimal zrs, BigDecimal zxs, BigDecimal zhxs, String qszc, String jszc, String kkyxid, String apbz, String xnxqid, String bz, String cjr, String jlssdw, Date whsj, Date cjsj) {
        this.id = id;
        this.kth = kth;
        this.kcid = kcid;
        this.ktmc = ktmc;
        this.zrs = zrs;
        this.zxs = zxs;
        this.zhxs = zhxs;
        this.qszc = qszc;
        this.jszc = jszc;
        this.kkyxid = kkyxid;
        this.apbz = apbz;
        this.xnxqid = xnxqid;
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
    
    @Column(name="KTH", nullable=false, length=32)

    public String getKth() {
        return this.kth;
    }
    
    public void setKth(String kth) {
        this.kth = kth;
    }
    
    @Column(name="KCID", nullable=false, length=32)

    public String getKcid() {
        return this.kcid;
    }
    
    public void setKcid(String kcid) {
        this.kcid = kcid;
    }
    
    @Column(name="KTMC", nullable=false, length=200)

    public String getKtmc() {
        return this.ktmc;
    }
    
    public void setKtmc(String ktmc) {
        this.ktmc = ktmc;
    }
    
    @Column(name="ZRS", precision=22, scale=0)

    public BigDecimal getZrs() {
        return this.zrs;
    }
    
    public void setZrs(BigDecimal zrs) {
        this.zrs = zrs;
    }
    
    @Column(name="ZXS", precision=22, scale=0)

    public BigDecimal getZxs() {
        return this.zxs;
    }
    
    public void setZxs(BigDecimal zxs) {
        this.zxs = zxs;
    }
    
    @Column(name="ZHXS", precision=22, scale=0)

    public BigDecimal getZhxs() {
        return this.zhxs;
    }
    
    public void setZhxs(BigDecimal zhxs) {
        this.zhxs = zhxs;
    }
    
    @Column(name="QSZC", length=10)

    public String getQszc() {
        return this.qszc;
    }
    
    public void setQszc(String qszc) {
        this.qszc = qszc;
    }
    
    @Column(name="JSZC", length=10)

    public String getJszc() {
        return this.jszc;
    }
    
    public void setJszc(String jszc) {
        this.jszc = jszc;
    }
    
    @Column(name="KKYXID", length=32)

    public String getKkyxid() {
        return this.kkyxid;
    }
    
    public void setKkyxid(String kkyxid) {
        this.kkyxid = kkyxid;
    }
    
    @Column(name="APBZ", length=2)

    public String getApbz() {
        return this.apbz;
    }
    
    public void setApbz(String apbz) {
        this.apbz = apbz;
    }
    
    @Column(name="XNXQID", length=10)

    public String getXnxqid() {
        return this.xnxqid;
    }
    
    public void setXnxqid(String xnxqid) {
        this.xnxqid = xnxqid;
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