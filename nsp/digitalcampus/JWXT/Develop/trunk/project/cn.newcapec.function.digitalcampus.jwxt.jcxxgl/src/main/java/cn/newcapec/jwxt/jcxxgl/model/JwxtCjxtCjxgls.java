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
 * JwxtCjxtCjxgls entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="JWXT_CJXT_CJXGLS"
    ,schema="JWXT"
)

public class JwxtCjxtCjxgls extends cn.newcapec.function.digitalcampus.common.model.AbstractModel implements java.io.Serializable {


    // Fields    

     private String id;
     private String cjbh;
     private String xh;
     private String cjzcx;
     private BigDecimal ycj;
     private BigDecimal xgcj;
     private String cjr;
     private String jlssdw;
     private Date whsj;
     private Date cjsj;


    // Constructors

    /** default constructor */
    public JwxtCjxtCjxgls() {
    }

	/** minimal constructor */
    public JwxtCjxtCjxgls(String id, String cjbh, String xh, String cjzcx, BigDecimal ycj, BigDecimal xgcj, String cjr, String jlssdw, Date cjsj) {
        this.id = id;
        this.cjbh = cjbh;
        this.xh = xh;
        this.cjzcx = cjzcx;
        this.ycj = ycj;
        this.xgcj = xgcj;
        this.cjr = cjr;
        this.jlssdw = jlssdw;
        this.cjsj = cjsj;
    }
    
    /** full constructor */
    public JwxtCjxtCjxgls(String id, String cjbh, String xh, String cjzcx, BigDecimal ycj, BigDecimal xgcj, String cjr, String jlssdw, Date whsj, Date cjsj) {
        this.id = id;
        this.cjbh = cjbh;
        this.xh = xh;
        this.cjzcx = cjzcx;
        this.ycj = ycj;
        this.xgcj = xgcj;
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
    
    @Column(name="CJZCX", nullable=false, length=32)

    public String getCjzcx() {
        return this.cjzcx;
    }
    
    public void setCjzcx(String cjzcx) {
        this.cjzcx = cjzcx;
    }
    
    @Column(name="YCJ", nullable=false, precision=22, scale=0)

    public BigDecimal getYcj() {
        return this.ycj;
    }
    
    public void setYcj(BigDecimal ycj) {
        this.ycj = ycj;
    }
    
    @Column(name="XGCJ", nullable=false, precision=22, scale=0)

    public BigDecimal getXgcj() {
        return this.xgcj;
    }
    
    public void setXgcj(BigDecimal xgcj) {
        this.xgcj = xgcj;
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