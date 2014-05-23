package cn.newcapec.jwxt.jcxxgl.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * JwxtKwglCdsj entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="JWXT_KWGL_CDSJ"
    ,schema="JWXT"
)

public class JwxtKwglCdsj extends cn.newcapec.function.digitalcampus.common.model.AbstractModel implements java.io.Serializable {


    // Fields    

     private String id;
     private String cdbh;
     private String kssj;
     private String xnxq;
     private String kslx;
     private String cjr;
     private String jlssdw;
     private Date whsj;
     private Date cjsj;


    // Constructors

    /** default constructor */
    public JwxtKwglCdsj() {
    }

	/** minimal constructor */
    public JwxtKwglCdsj(String id, String cdbh, String kssj, String xnxq, String kslx, String cjr, String jlssdw, Date cjsj) {
        this.id = id;
        this.cdbh = cdbh;
        this.kssj = kssj;
        this.xnxq = xnxq;
        this.kslx = kslx;
        this.cjr = cjr;
        this.jlssdw = jlssdw;
        this.cjsj = cjsj;
    }
    
    /** full constructor */
    public JwxtKwglCdsj(String id, String cdbh, String kssj, String xnxq, String kslx, String cjr, String jlssdw, Date whsj, Date cjsj) {
        this.id = id;
        this.cdbh = cdbh;
        this.kssj = kssj;
        this.xnxq = xnxq;
        this.kslx = kslx;
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
    
    @Column(name="CDBH", nullable=false, length=32)

    public String getCdbh() {
        return this.cdbh;
    }
    
    public void setCdbh(String cdbh) {
        this.cdbh = cdbh;
    }
    
    @Column(name="KSSJ", nullable=false, length=32)

    public String getKssj() {
        return this.kssj;
    }
    
    public void setKssj(String kssj) {
        this.kssj = kssj;
    }
    
    @Column(name="XNXQ", nullable=false, length=32)

    public String getXnxq() {
        return this.xnxq;
    }
    
    public void setXnxq(String xnxq) {
        this.xnxq = xnxq;
    }
    
    @Column(name="KSLX", nullable=false, length=32)

    public String getKslx() {
        return this.kslx;
    }
    
    public void setKslx(String kslx) {
        this.kslx = kslx;
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