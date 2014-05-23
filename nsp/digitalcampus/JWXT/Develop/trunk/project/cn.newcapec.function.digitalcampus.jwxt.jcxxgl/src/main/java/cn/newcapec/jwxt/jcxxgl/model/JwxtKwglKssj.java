package cn.newcapec.jwxt.jcxxgl.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * JwxtKwglKssj entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="JWXT_KWGL_KSSJ"
    ,schema="JWXT"
)

public class JwxtKwglKssj extends cn.newcapec.function.digitalcampus.common.model.AbstractModel implements java.io.Serializable {


    // Fields    

     private String id;
     private String xnxq;
     private String kssj;
     private String kssjmc;
     private String kssjd;
     private String lxbh;
     private String cjr;
     private String jlssdw;
     private Date whsj;
     private Date cjsj;


    // Constructors

    /** default constructor */
    public JwxtKwglKssj() {
    }

	/** minimal constructor */
    public JwxtKwglKssj(String id, String xnxq, String kssj, String kssjmc, String kssjd, String lxbh, String cjr, String jlssdw, Date cjsj) {
        this.id = id;
        this.xnxq = xnxq;
        this.kssj = kssj;
        this.kssjmc = kssjmc;
        this.kssjd = kssjd;
        this.lxbh = lxbh;
        this.cjr = cjr;
        this.jlssdw = jlssdw;
        this.cjsj = cjsj;
    }
    
    /** full constructor */
    public JwxtKwglKssj(String id, String xnxq, String kssj, String kssjmc, String kssjd, String lxbh, String cjr, String jlssdw, Date whsj, Date cjsj) {
        this.id = id;
        this.xnxq = xnxq;
        this.kssj = kssj;
        this.kssjmc = kssjmc;
        this.kssjd = kssjd;
        this.lxbh = lxbh;
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
    
    @Column(name="XNXQ", nullable=false, length=32)

    public String getXnxq() {
        return this.xnxq;
    }
    
    public void setXnxq(String xnxq) {
        this.xnxq = xnxq;
    }
    
    @Column(name="KSSJ", nullable=false, length=32)

    public String getKssj() {
        return this.kssj;
    }
    
    public void setKssj(String kssj) {
        this.kssj = kssj;
    }
    
    @Column(name="KSSJMC", nullable=false, length=32)

    public String getKssjmc() {
        return this.kssjmc;
    }
    
    public void setKssjmc(String kssjmc) {
        this.kssjmc = kssjmc;
    }
    
    @Column(name="KSSJD", nullable=false, length=32)

    public String getKssjd() {
        return this.kssjd;
    }
    
    public void setKssjd(String kssjd) {
        this.kssjd = kssjd;
    }
    
    @Column(name="LXBH", nullable=false, length=32)

    public String getLxbh() {
        return this.lxbh;
    }
    
    public void setLxbh(String lxbh) {
        this.lxbh = lxbh;
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