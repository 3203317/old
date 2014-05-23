package cn.newcapec.jwxt.jcxxgl.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * JwxtXjxxXszp entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="JWXT_XJXX_XSZP"
    ,schema="JWXT"
)

public class JwxtXjxxXszp extends cn.newcapec.function.digitalcampus.common.model.AbstractModel implements java.io.Serializable {


    // Fields    

     private String id;
     private String xh;
     private String gkksh;
     private String zpnr;
     private String zplj;
     private String zplx;
     private String cjr;
     private String jlssdw;
     private Date whsj;
     private Date cjsj;


    // Constructors

    /** default constructor */
    public JwxtXjxxXszp() {
    }

	/** minimal constructor */
    public JwxtXjxxXszp(String id, String xh, String gkksh, String zplx, String cjr, String jlssdw, Date cjsj) {
        this.id = id;
        this.xh = xh;
        this.gkksh = gkksh;
        this.zplx = zplx;
        this.cjr = cjr;
        this.jlssdw = jlssdw;
        this.cjsj = cjsj;
    }
    
    /** full constructor */
    public JwxtXjxxXszp(String id, String xh, String gkksh, String zpnr, String zplj, String zplx, String cjr, String jlssdw, Date whsj, Date cjsj) {
        this.id = id;
        this.xh = xh;
        this.gkksh = gkksh;
        this.zpnr = zpnr;
        this.zplj = zplj;
        this.zplx = zplx;
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
    
    @Column(name="XH", nullable=false, length=32)

    public String getXh() {
        return this.xh;
    }
    
    public void setXh(String xh) {
        this.xh = xh;
    }
    
    @Column(name="GKKSH", nullable=false, length=200)

    public String getGkksh() {
        return this.gkksh;
    }
    
    public void setGkksh(String gkksh) {
        this.gkksh = gkksh;
    }
    
    @Column(name="ZPNR")

    public String getZpnr() {
        return this.zpnr;
    }
    
    public void setZpnr(String zpnr) {
        this.zpnr = zpnr;
    }
    
    @Column(name="ZPLJ", length=200)

    public String getZplj() {
        return this.zplj;
    }
    
    public void setZplj(String zplj) {
        this.zplj = zplj;
    }
    
    @Column(name="ZPLX", nullable=false, length=2)

    public String getZplx() {
        return this.zplx;
    }
    
    public void setZplx(String zplx) {
        this.zplx = zplx;
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