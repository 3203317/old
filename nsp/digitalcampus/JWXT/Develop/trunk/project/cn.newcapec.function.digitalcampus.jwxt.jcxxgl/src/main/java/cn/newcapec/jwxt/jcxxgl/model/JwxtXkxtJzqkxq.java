package cn.newcapec.jwxt.jcxxgl.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * JwxtXkxtJzqkxq entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="JWXT_XKXT_JZQKXQ"
    ,schema="JWXT"
)

public class JwxtXkxtJzqkxq extends cn.newcapec.function.digitalcampus.common.model.AbstractModel implements java.io.Serializable {


    // Fields    

     private String id;
     private String zyid;
     private String nj;
     private String xq;
     private String cjr;
     private String jlssdw;
     private Date whsj;
     private Date cjsj;


    // Constructors

    /** default constructor */
    public JwxtXkxtJzqkxq() {
    }

	/** minimal constructor */
    public JwxtXkxtJzqkxq(String id, String zyid, String nj, String xq, String cjr, String jlssdw, Date cjsj) {
        this.id = id;
        this.zyid = zyid;
        this.nj = nj;
        this.xq = xq;
        this.cjr = cjr;
        this.jlssdw = jlssdw;
        this.cjsj = cjsj;
    }
    
    /** full constructor */
    public JwxtXkxtJzqkxq(String id, String zyid, String nj, String xq, String cjr, String jlssdw, Date whsj, Date cjsj) {
        this.id = id;
        this.zyid = zyid;
        this.nj = nj;
        this.xq = xq;
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
    
    @Column(name="ZYID", nullable=false, length=32)

    public String getZyid() {
        return this.zyid;
    }
    
    public void setZyid(String zyid) {
        this.zyid = zyid;
    }
    
    @Column(name="NJ", nullable=false, length=32)

    public String getNj() {
        return this.nj;
    }
    
    public void setNj(String nj) {
        this.nj = nj;
    }
    
    @Column(name="XQ", nullable=false, length=32)

    public String getXq() {
        return this.xq;
    }
    
    public void setXq(String xq) {
        this.xq = xq;
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