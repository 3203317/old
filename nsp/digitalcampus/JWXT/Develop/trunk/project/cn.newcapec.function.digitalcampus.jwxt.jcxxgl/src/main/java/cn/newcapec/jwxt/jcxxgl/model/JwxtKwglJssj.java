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
 * JwxtKwglJssj entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="JWXT_KWGL_JSSJ"
    ,schema="JWXT"
)

public class JwxtKwglJssj extends cn.newcapec.function.digitalcampus.common.model.AbstractModel implements java.io.Serializable {


    // Fields    

     private String id;
     private String jsbh;
     private String kssj;
     private String xnxq;
     private BigDecimal kslx;
     private String cdbh;
     private String jxbbh;
     private String cjr;
     private String jlssdw;
     private Date whsj;
     private Date cjsj;


    // Constructors

    /** default constructor */
    public JwxtKwglJssj() {
    }

	/** minimal constructor */
    public JwxtKwglJssj(String id, String jsbh, String kssj, String xnxq, BigDecimal kslx, String cdbh, String jxbbh, String cjr, String jlssdw, Date cjsj) {
        this.id = id;
        this.jsbh = jsbh;
        this.kssj = kssj;
        this.xnxq = xnxq;
        this.kslx = kslx;
        this.cdbh = cdbh;
        this.jxbbh = jxbbh;
        this.cjr = cjr;
        this.jlssdw = jlssdw;
        this.cjsj = cjsj;
    }
    
    /** full constructor */
    public JwxtKwglJssj(String id, String jsbh, String kssj, String xnxq, BigDecimal kslx, String cdbh, String jxbbh, String cjr, String jlssdw, Date whsj, Date cjsj) {
        this.id = id;
        this.jsbh = jsbh;
        this.kssj = kssj;
        this.xnxq = xnxq;
        this.kslx = kslx;
        this.cdbh = cdbh;
        this.jxbbh = jxbbh;
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
    
    @Column(name="JSBH", nullable=false, length=32)

    public String getJsbh() {
        return this.jsbh;
    }
    
    public void setJsbh(String jsbh) {
        this.jsbh = jsbh;
    }
    
    @Column(name="KSSJ", nullable=false, length=32)

    public String getKssj() {
        return this.kssj;
    }
    
    public void setKssj(String kssj) {
        this.kssj = kssj;
    }
    
    @Column(name="XNXQ", nullable=false, length=32)

    public String getXnxq() {
        return this.xnxq;
    }
    
    public void setXnxq(String xnxq) {
        this.xnxq = xnxq;
    }
    
    @Column(name="KSLX", nullable=false, precision=22, scale=0)

    public BigDecimal getKslx() {
        return this.kslx;
    }
    
    public void setKslx(BigDecimal kslx) {
        this.kslx = kslx;
    }
    
    @Column(name="CDBH", nullable=false, length=32)

    public String getCdbh() {
        return this.cdbh;
    }
    
    public void setCdbh(String cdbh) {
        this.cdbh = cdbh;
    }
    
    @Column(name="JXBBH", nullable=false, length=32)

    public String getJxbbh() {
        return this.jxbbh;
    }
    
    public void setJxbbh(String jxbbh) {
        this.jxbbh = jxbbh;
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