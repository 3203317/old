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
 * JwxtCjxtXscj entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="JWXT_CJXT_XSCJ"
    ,schema="JWXT"
)

public class JwxtCjxtXscj extends cn.newcapec.function.digitalcampus.common.model.AbstractModel implements java.io.Serializable {


    // Fields    

     private String id;
     private String cjbh;
     private String xh;
     private String xnxqid;
     private String kch;
     private BigDecimal kscj;
     private BigDecimal pscj;
     private BigDecimal sycj;
     private BigDecimal sjcj;
     private String zcj;
     private String cjsxm;
     private BigDecimal bkcj;
     private BigDecimal cxcj;
     private BigDecimal qlbkcj;
     private BigDecimal cjjd;
     private BigDecimal jdxf;
     private String tjbz;
     private Date tjsj;
     private String tjr;
     private String rdbz;
     private Date rdsj;
     private String rdr;
     private String fbbz;
     private Date fbsj;
     private String fbr;
     private String cjr;
     private String jlssdw;
     private Date whsj;
     private Date cjsj;


    // Constructors

    /** default constructor */
    public JwxtCjxtXscj() {
    }

	/** minimal constructor */
    public JwxtCjxtXscj(String id, String cjbh, String xh, String xnxqid, String kch, BigDecimal jdxf, String tjbz, String rdbz, String fbbz, String cjr, String jlssdw, Date cjsj) {
        this.id = id;
        this.cjbh = cjbh;
        this.xh = xh;
        this.xnxqid = xnxqid;
        this.kch = kch;
        this.jdxf = jdxf;
        this.tjbz = tjbz;
        this.rdbz = rdbz;
        this.fbbz = fbbz;
        this.cjr = cjr;
        this.jlssdw = jlssdw;
        this.cjsj = cjsj;
    }
    
    /** full constructor */
    public JwxtCjxtXscj(String id, String cjbh, String xh, String xnxqid, String kch, BigDecimal kscj, BigDecimal pscj, BigDecimal sycj, BigDecimal sjcj, String zcj, String cjsxm, BigDecimal bkcj, BigDecimal cxcj, BigDecimal qlbkcj, BigDecimal cjjd, BigDecimal jdxf, String tjbz, Date tjsj, String tjr, String rdbz, Date rdsj, String rdr, String fbbz, Date fbsj, String fbr, String cjr, String jlssdw, Date whsj, Date cjsj) {
        this.id = id;
        this.cjbh = cjbh;
        this.xh = xh;
        this.xnxqid = xnxqid;
        this.kch = kch;
        this.kscj = kscj;
        this.pscj = pscj;
        this.sycj = sycj;
        this.sjcj = sjcj;
        this.zcj = zcj;
        this.cjsxm = cjsxm;
        this.bkcj = bkcj;
        this.cxcj = cxcj;
        this.qlbkcj = qlbkcj;
        this.cjjd = cjjd;
        this.jdxf = jdxf;
        this.tjbz = tjbz;
        this.tjsj = tjsj;
        this.tjr = tjr;
        this.rdbz = rdbz;
        this.rdsj = rdsj;
        this.rdr = rdr;
        this.fbbz = fbbz;
        this.fbsj = fbsj;
        this.fbr = fbr;
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
    
    @Column(name="CJBH", nullable=false, length=32)

    public String getCjbh() {
        return this.cjbh;
    }
    
    public void setCjbh(String cjbh) {
        this.cjbh = cjbh;
    }
    
    @Column(name="XH", nullable=false, length=32)

    public String getXh() {
        return this.xh;
    }
    
    public void setXh(String xh) {
        this.xh = xh;
    }
    
    @Column(name="XNXQID", nullable=false, length=10)

    public String getXnxqid() {
        return this.xnxqid;
    }
    
    public void setXnxqid(String xnxqid) {
        this.xnxqid = xnxqid;
    }
    
    @Column(name="KCH", nullable=false, length=32)

    public String getKch() {
        return this.kch;
    }
    
    public void setKch(String kch) {
        this.kch = kch;
    }
    
    @Column(name="KSCJ", precision=22, scale=0)

    public BigDecimal getKscj() {
        return this.kscj;
    }
    
    public void setKscj(BigDecimal kscj) {
        this.kscj = kscj;
    }
    
    @Column(name="PSCJ", precision=22, scale=0)

    public BigDecimal getPscj() {
        return this.pscj;
    }
    
    public void setPscj(BigDecimal pscj) {
        this.pscj = pscj;
    }
    
    @Column(name="SYCJ", precision=22, scale=0)

    public BigDecimal getSycj() {
        return this.sycj;
    }
    
    public void setSycj(BigDecimal sycj) {
        this.sycj = sycj;
    }
    
    @Column(name="SJCJ", precision=22, scale=0)

    public BigDecimal getSjcj() {
        return this.sjcj;
    }
    
    public void setSjcj(BigDecimal sjcj) {
        this.sjcj = sjcj;
    }
    
    @Column(name="ZCJ", length=10)

    public String getZcj() {
        return this.zcj;
    }
    
    public void setZcj(String zcj) {
        this.zcj = zcj;
    }
    
    @Column(name="CJSXM", length=2)

    public String getCjsxm() {
        return this.cjsxm;
    }
    
    public void setCjsxm(String cjsxm) {
        this.cjsxm = cjsxm;
    }
    
    @Column(name="BKCJ", precision=22, scale=0)

    public BigDecimal getBkcj() {
        return this.bkcj;
    }
    
    public void setBkcj(BigDecimal bkcj) {
        this.bkcj = bkcj;
    }
    
    @Column(name="CXCJ", precision=22, scale=0)

    public BigDecimal getCxcj() {
        return this.cxcj;
    }
    
    public void setCxcj(BigDecimal cxcj) {
        this.cxcj = cxcj;
    }
    
    @Column(name="QLBKCJ", precision=22, scale=0)

    public BigDecimal getQlbkcj() {
        return this.qlbkcj;
    }
    
    public void setQlbkcj(BigDecimal qlbkcj) {
        this.qlbkcj = qlbkcj;
    }
    
    @Column(name="CJJD", precision=22, scale=0)

    public BigDecimal getCjjd() {
        return this.cjjd;
    }
    
    public void setCjjd(BigDecimal cjjd) {
        this.cjjd = cjjd;
    }
    
    @Column(name="JDXF", nullable=false, precision=22, scale=0)

    public BigDecimal getJdxf() {
        return this.jdxf;
    }
    
    public void setJdxf(BigDecimal jdxf) {
        this.jdxf = jdxf;
    }
    
    @Column(name="TJBZ", nullable=false, length=1)

    public String getTjbz() {
        return this.tjbz;
    }
    
    public void setTjbz(String tjbz) {
        this.tjbz = tjbz;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="TJSJ", length=7)

    public Date getTjsj() {
        return this.tjsj;
    }
    
    public void setTjsj(Date tjsj) {
        this.tjsj = tjsj;
    }
    
    @Column(name="TJR", length=32)

    public String getTjr() {
        return this.tjr;
    }
    
    public void setTjr(String tjr) {
        this.tjr = tjr;
    }
    
    @Column(name="RDBZ", nullable=false, length=1)

    public String getRdbz() {
        return this.rdbz;
    }
    
    public void setRdbz(String rdbz) {
        this.rdbz = rdbz;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="RDSJ", length=7)

    public Date getRdsj() {
        return this.rdsj;
    }
    
    public void setRdsj(Date rdsj) {
        this.rdsj = rdsj;
    }
    
    @Column(name="RDR", length=32)

    public String getRdr() {
        return this.rdr;
    }
    
    public void setRdr(String rdr) {
        this.rdr = rdr;
    }
    
    @Column(name="FBBZ", nullable=false, length=1)

    public String getFbbz() {
        return this.fbbz;
    }
    
    public void setFbbz(String fbbz) {
        this.fbbz = fbbz;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="FBSJ", length=7)

    public Date getFbsj() {
        return this.fbsj;
    }
    
    public void setFbsj(Date fbsj) {
        this.fbsj = fbsj;
    }
    
    @Column(name="FBR", length=32)

    public String getFbr() {
        return this.fbr;
    }
    
    public void setFbr(String fbr) {
        this.fbr = fbr;
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