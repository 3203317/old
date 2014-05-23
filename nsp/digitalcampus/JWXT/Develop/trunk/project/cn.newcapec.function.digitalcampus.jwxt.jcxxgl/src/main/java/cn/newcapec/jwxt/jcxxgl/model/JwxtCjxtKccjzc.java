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
 * JwxtCjxtKccjzc entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="JWXT_CJXT_KCCJZC"
    ,schema="JWXT"
)

public class JwxtCjxtKccjzc extends cn.newcapec.function.digitalcampus.common.model.AbstractModel implements java.io.Serializable {


    // Fields    

     private String id;
     private String kth;
     private String cjzl;
     private String cjlx;
     private BigDecimal szbz;
     private String cjr;
     private String jlssdw;
     private Date whsj;
     private Date cjsj;


    // Constructors

    /** default constructor */
    public JwxtCjxtKccjzc() {
    }

	/** minimal constructor */
    public JwxtCjxtKccjzc(String id, String kth, String cjr, String jlssdw, Date cjsj) {
        this.id = id;
        this.kth = kth;
        this.cjr = cjr;
        this.jlssdw = jlssdw;
        this.cjsj = cjsj;
    }
    
    /** full constructor */
    public JwxtCjxtKccjzc(String id, String kth, String cjzl, String cjlx, BigDecimal szbz, String cjr, String jlssdw, Date whsj, Date cjsj) {
        this.id = id;
        this.kth = kth;
        this.cjzl = cjzl;
        this.cjlx = cjlx;
        this.szbz = szbz;
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
    
    @Column(name="CJZL", length=2)

    public String getCjzl() {
        return this.cjzl;
    }
    
    public void setCjzl(String cjzl) {
        this.cjzl = cjzl;
    }
    
    @Column(name="CJLX", length=2)

    public String getCjlx() {
        return this.cjlx;
    }
    
    public void setCjlx(String cjlx) {
        this.cjlx = cjlx;
    }
    
    @Column(name="SZBZ", precision=22, scale=0)

    public BigDecimal getSzbz() {
        return this.szbz;
    }
    
    public void setSzbz(BigDecimal szbz) {
        this.szbz = szbz;
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