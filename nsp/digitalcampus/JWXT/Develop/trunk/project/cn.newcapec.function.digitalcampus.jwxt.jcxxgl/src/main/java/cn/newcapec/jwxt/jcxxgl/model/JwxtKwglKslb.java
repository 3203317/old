package cn.newcapec.jwxt.jcxxgl.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * JwxtKwglKslb entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="JWXT_KWGL_KSLB"
    ,schema="JWXT"
)

public class JwxtKwglKslb extends cn.newcapec.function.digitalcampus.common.model.AbstractModel implements java.io.Serializable {


    // Fields    

     private String id;
     private String lbbh;
     private String lbmc;
     private String cjr;
     private String jlssdw;
     private Date whsj;
     private Date cjsj;


    // Constructors

    /** default constructor */
    public JwxtKwglKslb() {
    }

	/** minimal constructor */
    public JwxtKwglKslb(String id, String lbbh, String lbmc, String cjr, String jlssdw, Date cjsj) {
        this.id = id;
        this.lbbh = lbbh;
        this.lbmc = lbmc;
        this.cjr = cjr;
        this.jlssdw = jlssdw;
        this.cjsj = cjsj;
    }
    
    /** full constructor */
    public JwxtKwglKslb(String id, String lbbh, String lbmc, String cjr, String jlssdw, Date whsj, Date cjsj) {
        this.id = id;
        this.lbbh = lbbh;
        this.lbmc = lbmc;
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
    
    @Column(name="LBBH", nullable=false, length=32)

    public String getLbbh() {
        return this.lbbh;
    }
    
    public void setLbbh(String lbbh) {
        this.lbbh = lbbh;
    }
    
    @Column(name="LBMC", nullable=false, length=32)

    public String getLbmc() {
        return this.lbmc;
    }
    
    public void setLbmc(String lbmc) {
        this.lbmc = lbmc;
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