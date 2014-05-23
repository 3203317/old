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
 * JwxtKwglChb entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="JWXT_KWGL_CHB"
    ,schema="JWXT"
)

public class JwxtKwglChb extends cn.newcapec.function.digitalcampus.common.model.AbstractModel implements java.io.Serializable {


    // Fields    

     private String id;
     private String chbbh;
     private String xzbbh;
     private String jxbbh;
     private String kcbh;
     private BigDecimal xsrs;
     private String cjr;
     private String jlssdw;
     private Date whsj;
     private Date cjsj;


    // Constructors

    /** default constructor */
    public JwxtKwglChb() {
    }

	/** minimal constructor */
    public JwxtKwglChb(String id, String chbbh, String xzbbh, String jxbbh, String kcbh, BigDecimal xsrs, String cjr, String jlssdw, Date cjsj) {
        this.id = id;
        this.chbbh = chbbh;
        this.xzbbh = xzbbh;
        this.jxbbh = jxbbh;
        this.kcbh = kcbh;
        this.xsrs = xsrs;
        this.cjr = cjr;
        this.jlssdw = jlssdw;
        this.cjsj = cjsj;
    }
    
    /** full constructor */
    public JwxtKwglChb(String id, String chbbh, String xzbbh, String jxbbh, String kcbh, BigDecimal xsrs, String cjr, String jlssdw, Date whsj, Date cjsj) {
        this.id = id;
        this.chbbh = chbbh;
        this.xzbbh = xzbbh;
        this.jxbbh = jxbbh;
        this.kcbh = kcbh;
        this.xsrs = xsrs;
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
    
    @Column(name="CHBBH", nullable=false, length=32)

    public String getChbbh() {
        return this.chbbh;
    }
    
    public void setChbbh(String chbbh) {
        this.chbbh = chbbh;
    }
    
    @Column(name="XZBBH", nullable=false, length=32)

    public String getXzbbh() {
        return this.xzbbh;
    }
    
    public void setXzbbh(String xzbbh) {
        this.xzbbh = xzbbh;
    }
    
    @Column(name="JXBBH", nullable=false, length=32)

    public String getJxbbh() {
        return this.jxbbh;
    }
    
    public void setJxbbh(String jxbbh) {
        this.jxbbh = jxbbh;
    }
    
    @Column(name="KCBH", nullable=false, length=32)

    public String getKcbh() {
        return this.kcbh;
    }
    
    public void setKcbh(String kcbh) {
        this.kcbh = kcbh;
    }
    
    @Column(name="XSRS", nullable=false, precision=22, scale=0)

    public BigDecimal getXsrs() {
        return this.xsrs;
    }
    
    public void setXsrs(BigDecimal xsrs) {
        this.xsrs = xsrs;
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