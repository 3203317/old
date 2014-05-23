package cn.newcapec.jwxt.jcxxgl.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * JwxtKqxtKqrz entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="JWXT_KQXT_KQRZ"
    ,schema="JWXT"
)

public class JwxtKqxtKqrz extends cn.newcapec.function.digitalcampus.common.model.AbstractModel implements java.io.Serializable {


    // Fields    

     private String id;
     private String kqrq;
     private Date kssj;
     private Date jssj;
     private String hs;
     private String cjr;
     private String jlssdw;
     private Date whsj;
     private Date cjsj;


    // Constructors

    /** default constructor */
    public JwxtKqxtKqrz() {
    }

	/** minimal constructor */
    public JwxtKqxtKqrz(String id, String kqrq, Date kssj, Date jssj, String cjr, String jlssdw, Date cjsj) {
        this.id = id;
        this.kqrq = kqrq;
        this.kssj = kssj;
        this.jssj = jssj;
        this.cjr = cjr;
        this.jlssdw = jlssdw;
        this.cjsj = cjsj;
    }
    
    /** full constructor */
    public JwxtKqxtKqrz(String id, String kqrq, Date kssj, Date jssj, String hs, String cjr, String jlssdw, Date whsj, Date cjsj) {
        this.id = id;
        this.kqrq = kqrq;
        this.kssj = kssj;
        this.jssj = jssj;
        this.hs = hs;
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
    
    @Column(name="KQRQ", nullable=false, length=10)

    public String getKqrq() {
        return this.kqrq;
    }
    
    public void setKqrq(String kqrq) {
        this.kqrq = kqrq;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="KSSJ", nullable=false, length=7)

    public Date getKssj() {
        return this.kssj;
    }
    
    public void setKssj(Date kssj) {
        this.kssj = kssj;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="JSSJ", nullable=false, length=7)

    public Date getJssj() {
        return this.jssj;
    }
    
    public void setJssj(Date jssj) {
        this.jssj = jssj;
    }
    
    @Column(name="HS", length=10)

    public String getHs() {
        return this.hs;
    }
    
    public void setHs(String hs) {
        this.hs = hs;
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