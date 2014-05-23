package cn.newcapec.jwxt.jcxxgl.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * JwxtKwglJkjs entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="JWXT_KWGL_JKJS"
    ,schema="JWXT"
)

public class JwxtKwglJkjs extends cn.newcapec.function.digitalcampus.common.model.AbstractModel implements java.io.Serializable {


    // Fields    

     private String id;
     private String jsbh;
     private String jsmc;
     private String ssbm;
     private String lxdh;
     private String cjr;
     private String jlssdw;
     private Date whsj;
     private Date cjsj;


    // Constructors

    /** default constructor */
    public JwxtKwglJkjs() {
    }

	/** minimal constructor */
    public JwxtKwglJkjs(String id, String jsbh, String jsmc, String ssbm, String lxdh, String cjr, String jlssdw, Date cjsj) {
        this.id = id;
        this.jsbh = jsbh;
        this.jsmc = jsmc;
        this.ssbm = ssbm;
        this.lxdh = lxdh;
        this.cjr = cjr;
        this.jlssdw = jlssdw;
        this.cjsj = cjsj;
    }
    
    /** full constructor */
    public JwxtKwglJkjs(String id, String jsbh, String jsmc, String ssbm, String lxdh, String cjr, String jlssdw, Date whsj, Date cjsj) {
        this.id = id;
        this.jsbh = jsbh;
        this.jsmc = jsmc;
        this.ssbm = ssbm;
        this.lxdh = lxdh;
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
    
    @Column(name="JSMC", nullable=false, length=32)

    public String getJsmc() {
        return this.jsmc;
    }
    
    public void setJsmc(String jsmc) {
        this.jsmc = jsmc;
    }
    
    @Column(name="SSBM", nullable=false, length=32)

    public String getSsbm() {
        return this.ssbm;
    }
    
    public void setSsbm(String ssbm) {
        this.ssbm = ssbm;
    }
    
    @Column(name="LXDH", nullable=false, length=32)

    public String getLxdh() {
        return this.lxdh;
    }
    
    public void setLxdh(String lxdh) {
        this.lxdh = lxdh;
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