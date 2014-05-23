package cn.newcapec.jwxt.jcxxgl.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * JwxtPkxtJssk entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="JWXT_PKXT_JSSK"
    ,schema="JWXT"
)

public class JwxtPkxtJssk extends cn.newcapec.function.digitalcampus.common.model.AbstractModel implements java.io.Serializable {


    // Fields    

     private String id;
     private String kcid;
     private String jgh;
     private String sklxm;
     private String cjr;
     private String jlssdw;
     private Date whsj;
     private Date cjsj;


    // Constructors

    /** default constructor */
    public JwxtPkxtJssk() {
    }

	/** minimal constructor */
    public JwxtPkxtJssk(String id, String kcid, String jgh, String sklxm, String cjr, String jlssdw, Date cjsj) {
        this.id = id;
        this.kcid = kcid;
        this.jgh = jgh;
        this.sklxm = sklxm;
        this.cjr = cjr;
        this.jlssdw = jlssdw;
        this.cjsj = cjsj;
    }
    
    /** full constructor */
    public JwxtPkxtJssk(String id, String kcid, String jgh, String sklxm, String cjr, String jlssdw, Date whsj, Date cjsj) {
        this.id = id;
        this.kcid = kcid;
        this.jgh = jgh;
        this.sklxm = sklxm;
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
    
    @Column(name="KCID", nullable=false, length=32)

    public String getKcid() {
        return this.kcid;
    }
    
    public void setKcid(String kcid) {
        this.kcid = kcid;
    }
    
    @Column(name="JGH", nullable=false, length=32)

    public String getJgh() {
        return this.jgh;
    }
    
    public void setJgh(String jgh) {
        this.jgh = jgh;
    }
    
    @Column(name="SKLXM", nullable=false, length=2)

    public String getSklxm() {
        return this.sklxm;
    }
    
    public void setSklxm(String sklxm) {
        this.sklxm = sklxm;
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