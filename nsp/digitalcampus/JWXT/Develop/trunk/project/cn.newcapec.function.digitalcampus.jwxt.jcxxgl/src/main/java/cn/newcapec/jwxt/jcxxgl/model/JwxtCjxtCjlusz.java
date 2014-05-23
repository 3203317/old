package cn.newcapec.jwxt.jcxxgl.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * JwxtCjxtCjlusz entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="JWXT_CJXT_CJLUSZ"
    ,schema="JWXT"
)

public class JwxtCjxtCjlusz extends cn.newcapec.function.digitalcampus.common.model.AbstractModel implements java.io.Serializable {


    // Fields    

     private String id;
     private String jgh;
     private String bqh;
     private String cjr;
     private String jlssdw;
     private Date whsj;
     private Date cjsj;


    // Constructors

    /** default constructor */
    public JwxtCjxtCjlusz() {
    }

	/** minimal constructor */
    public JwxtCjxtCjlusz(String id, String jgh, String bqh, String cjr, String jlssdw, Date cjsj) {
        this.id = id;
        this.jgh = jgh;
        this.bqh = bqh;
        this.cjr = cjr;
        this.jlssdw = jlssdw;
        this.cjsj = cjsj;
    }
    
    /** full constructor */
    public JwxtCjxtCjlusz(String id, String jgh, String bqh, String cjr, String jlssdw, Date whsj, Date cjsj) {
        this.id = id;
        this.jgh = jgh;
        this.bqh = bqh;
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
    
    @Column(name="JGH", nullable=false, length=32)

    public String getJgh() {
        return this.jgh;
    }
    
    public void setJgh(String jgh) {
        this.jgh = jgh;
    }
    
    @Column(name="BQH", nullable=false, length=2)

    public String getBqh() {
        return this.bqh;
    }
    
    public void setBqh(String bqh) {
        this.bqh = bqh;
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