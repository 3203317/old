package cn.newcapec.jwxt.jcxxgl.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * JwxtXkxtGxksksjJc entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="JWXT_XKXT_GXKSKSJ_JC"
    ,schema="JWXT"
)

public class JwxtXkxtGxksksjJc extends cn.newcapec.function.digitalcampus.common.model.AbstractModel implements java.io.Serializable {


    // Fields    

     private String id;
     private String sksjid;
     private String jcid;
     private String cjr;
     private String jlssdw;
     private Date whsj;
     private Date cjsj;


    // Constructors

    /** default constructor */
    public JwxtXkxtGxksksjJc() {
    }

	/** minimal constructor */
    public JwxtXkxtGxksksjJc(String id, String sksjid, String jcid, String cjr, String jlssdw, Date cjsj) {
        this.id = id;
        this.sksjid = sksjid;
        this.jcid = jcid;
        this.cjr = cjr;
        this.jlssdw = jlssdw;
        this.cjsj = cjsj;
    }
    
    /** full constructor */
    public JwxtXkxtGxksksjJc(String id, String sksjid, String jcid, String cjr, String jlssdw, Date whsj, Date cjsj) {
        this.id = id;
        this.sksjid = sksjid;
        this.jcid = jcid;
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
    
    @Column(name="SKSJID", nullable=false, length=32)

    public String getSksjid() {
        return this.sksjid;
    }
    
    public void setSksjid(String sksjid) {
        this.sksjid = sksjid;
    }
    
    @Column(name="JCID", nullable=false, length=32)

    public String getJcid() {
        return this.jcid;
    }
    
    public void setJcid(String jcid) {
        this.jcid = jcid;
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