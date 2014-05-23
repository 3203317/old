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
 * JwxtKwglKsjh entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="JWXT_KWGL_KSJH"
    ,schema="JWXT"
)

public class JwxtKwglKsjh extends cn.newcapec.function.digitalcampus.common.model.AbstractModel implements java.io.Serializable {


    // Fields    

     private String id;
     private String jxbbh;
     private BigDecimal rxnf;
     private String xnxq;
     private String ksfs;
     private String jsbh;
     private BigDecimal xsrs;
     private String yxj;
     private String kssj;
     private String cjr;
     private String jlssdw;
     private Date whsj;
     private Date cjsj;


    // Constructors

    /** default constructor */
    public JwxtKwglKsjh() {
    }

	/** minimal constructor */
    public JwxtKwglKsjh(String id, String jxbbh, BigDecimal rxnf, String xnxq, String ksfs, String jsbh, BigDecimal xsrs, String yxj, String kssj, String cjr, String jlssdw, Date cjsj) {
        this.id = id;
        this.jxbbh = jxbbh;
        this.rxnf = rxnf;
        this.xnxq = xnxq;
        this.ksfs = ksfs;
        this.jsbh = jsbh;
        this.xsrs = xsrs;
        this.yxj = yxj;
        this.kssj = kssj;
        this.cjr = cjr;
        this.jlssdw = jlssdw;
        this.cjsj = cjsj;
    }
    
    /** full constructor */
    public JwxtKwglKsjh(String id, String jxbbh, BigDecimal rxnf, String xnxq, String ksfs, String jsbh, BigDecimal xsrs, String yxj, String kssj, String cjr, String jlssdw, Date whsj, Date cjsj) {
        this.id = id;
        this.jxbbh = jxbbh;
        this.rxnf = rxnf;
        this.xnxq = xnxq;
        this.ksfs = ksfs;
        this.jsbh = jsbh;
        this.xsrs = xsrs;
        this.yxj = yxj;
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
    
    @Column(name="JXBBH", nullable=false, length=32)

    public String getJxbbh() {
        return this.jxbbh;
    }
    
    public void setJxbbh(String jxbbh) {
        this.jxbbh = jxbbh;
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
    
    @Column(name="KSFS", nullable=false, length=32)

    public String getKsfs() {
        return this.ksfs;
    }
    
    public void setKsfs(String ksfs) {
        this.ksfs = ksfs;
    }
    
    @Column(name="JSBH", nullable=false, length=32)

    public String getJsbh() {
        return this.jsbh;
    }
    
    public void setJsbh(String jsbh) {
        this.jsbh = jsbh;
    }
    
    @Column(name="XSRS", nullable=false, precision=22, scale=0)

    public BigDecimal getXsrs() {
        return this.xsrs;
    }
    
    public void setXsrs(BigDecimal xsrs) {
        this.xsrs = xsrs;
    }
    
    @Column(name="YXJ", nullable=false, length=32)

    public String getYxj() {
        return this.yxj;
    }
    
    public void setYxj(String yxj) {
        this.yxj = yxj;
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