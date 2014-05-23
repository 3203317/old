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
 * JwxtKwglXzbsj entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="JWXT_KWGL_XZBSJ"
    ,schema="JWXT"
)

public class JwxtKwglXzbsj extends cn.newcapec.function.digitalcampus.common.model.AbstractModel implements java.io.Serializable {


    // Fields    

     private String id;
     private String bjbh;
     private String bjmc;
     private String zybh;
     private BigDecimal rxnf;
     private String xnxq;
     private BigDecimal xsrs;
     private String kssj;
     private String cjr;
     private String jlssdw;
     private Date whsj;
     private Date cjsj;


    // Constructors

    /** default constructor */
    public JwxtKwglXzbsj() {
    }

	/** minimal constructor */
    public JwxtKwglXzbsj(String id, String bjbh, String bjmc, String zybh, BigDecimal rxnf, String xnxq, BigDecimal xsrs, String kssj, String cjr, String jlssdw, Date cjsj) {
        this.id = id;
        this.bjbh = bjbh;
        this.bjmc = bjmc;
        this.zybh = zybh;
        this.rxnf = rxnf;
        this.xnxq = xnxq;
        this.xsrs = xsrs;
        this.kssj = kssj;
        this.cjr = cjr;
        this.jlssdw = jlssdw;
        this.cjsj = cjsj;
    }
    
    /** full constructor */
    public JwxtKwglXzbsj(String id, String bjbh, String bjmc, String zybh, BigDecimal rxnf, String xnxq, BigDecimal xsrs, String kssj, String cjr, String jlssdw, Date whsj, Date cjsj) {
        this.id = id;
        this.bjbh = bjbh;
        this.bjmc = bjmc;
        this.zybh = zybh;
        this.rxnf = rxnf;
        this.xnxq = xnxq;
        this.xsrs = xsrs;
        this.kssj = kssj;
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
    
    @Column(name="BJBH", nullable=false, length=32)

    public String getBjbh() {
        return this.bjbh;
    }
    
    public void setBjbh(String bjbh) {
        this.bjbh = bjbh;
    }
    
    @Column(name="BJMC", nullable=false, length=32)

    public String getBjmc() {
        return this.bjmc;
    }
    
    public void setBjmc(String bjmc) {
        this.bjmc = bjmc;
    }
    
    @Column(name="ZYBH", nullable=false, length=32)

    public String getZybh() {
        return this.zybh;
    }
    
    public void setZybh(String zybh) {
        this.zybh = zybh;
    }
    
    @Column(name="RXNF", nullable=false, precision=22, scale=0)

    public BigDecimal getRxnf() {
        return this.rxnf;
    }
    
    public void setRxnf(BigDecimal rxnf) {
        this.rxnf = rxnf;
    }
    
    @Column(name="XNXQ", nullable=false, length=32)

    public String getXnxq() {
        return this.xnxq;
    }
    
    public void setXnxq(String xnxq) {
        this.xnxq = xnxq;
    }
    
    @Column(name="XSRS", nullable=false, precision=22, scale=0)

    public BigDecimal getXsrs() {
        return this.xsrs;
    }
    
    public void setXsrs(BigDecimal xsrs) {
        this.xsrs = xsrs;
    }
    
    @Column(name="KSSJ", nullable=false, length=32)

    public String getKssj() {
        return this.kssj;
    }
    
    public void setKssj(String kssj) {
        this.kssj = kssj;
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