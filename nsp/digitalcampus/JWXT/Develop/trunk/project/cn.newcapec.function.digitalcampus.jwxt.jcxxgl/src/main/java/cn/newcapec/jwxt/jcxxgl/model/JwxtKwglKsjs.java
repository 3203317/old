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
 * JwxtKwglKsjs entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="JWXT_KWGL_KSJS"
    ,schema="JWXT"
)

public class JwxtKwglKsjs extends cn.newcapec.function.digitalcampus.common.model.AbstractModel implements java.io.Serializable {


    // Fields    

     private String id;
     private String jsdm;
     private String jsmc;
     private String cdlx;
     private BigDecimal rnrs;
     private String cjr;
     private String jlssdw;
     private Date whsj;
     private Date cjsj;


    // Constructors

    /** default constructor */
    public JwxtKwglKsjs() {
    }

	/** minimal constructor */
    public JwxtKwglKsjs(String id, String jsdm, String jsmc, String cdlx, BigDecimal rnrs, String cjr, String jlssdw, Date cjsj) {
        this.id = id;
        this.jsdm = jsdm;
        this.jsmc = jsmc;
        this.cdlx = cdlx;
        this.rnrs = rnrs;
        this.cjr = cjr;
        this.jlssdw = jlssdw;
        this.cjsj = cjsj;
    }
    
    /** full constructor */
    public JwxtKwglKsjs(String id, String jsdm, String jsmc, String cdlx, BigDecimal rnrs, String cjr, String jlssdw, Date whsj, Date cjsj) {
        this.id = id;
        this.jsdm = jsdm;
        this.jsmc = jsmc;
        this.cdlx = cdlx;
        this.rnrs = rnrs;
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
    
    @Column(name="JSDM", nullable=false, length=32)

    public String getJsdm() {
        return this.jsdm;
    }
    
    public void setJsdm(String jsdm) {
        this.jsdm = jsdm;
    }
    
    @Column(name="JSMC", nullable=false, length=32)

    public String getJsmc() {
        return this.jsmc;
    }
    
    public void setJsmc(String jsmc) {
        this.jsmc = jsmc;
    }
    
    @Column(name="CDLX", nullable=false, length=32)

    public String getCdlx() {
        return this.cdlx;
    }
    
    public void setCdlx(String cdlx) {
        this.cdlx = cdlx;
    }
    
    @Column(name="RNRS", nullable=false, precision=22, scale=0)

    public BigDecimal getRnrs() {
        return this.rnrs;
    }
    
    public void setRnrs(BigDecimal rnrs) {
        this.rnrs = rnrs;
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