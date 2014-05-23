package cn.newcapec.jwxt.jcxxgl.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * JwxtJcxxZyxxKz entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="JWXT_JCXX_ZYXX_KZ"
    ,schema="JWXT"
)

public class JwxtJcxxZyxxKz extends cn.newcapec.function.digitalcampus.common.model.AbstractModel implements java.io.Serializable {


    // Fields    

     private String id;
     private String pkey;
     private String xxdm;
     private String pyccm;
     private String xwm;
     private String zt;
     private String cjr;
     private String jlssdw;
     private Date whsj;
     private Date cjsj;


    // Constructors

    /** default constructor */
    public JwxtJcxxZyxxKz() {
    }

	/** minimal constructor */
    public JwxtJcxxZyxxKz(String id, String pkey, String xxdm, String cjr, String jlssdw, Date cjsj) {
        this.id = id;
        this.pkey = pkey;
        this.xxdm = xxdm;
        this.cjr = cjr;
        this.jlssdw = jlssdw;
        this.cjsj = cjsj;
    }
    
    /** full constructor */
    public JwxtJcxxZyxxKz(String id, String pkey, String xxdm, String pyccm, String xwm, String zt, String cjr, String jlssdw, Date whsj, Date cjsj) {
        this.id = id;
        this.pkey = pkey;
        this.xxdm = xxdm;
        this.pyccm = pyccm;
        this.xwm = xwm;
        this.zt = zt;
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
    
    @Column(name="PKEY", nullable=false, length=50)

    public String getPkey() {
        return this.pkey;
    }
    
    public void setPkey(String pkey) {
        this.pkey = pkey;
    }
    
    @Column(name="XXDM", nullable=false, length=32)

    public String getXxdm() {
        return this.xxdm;
    }
    
    public void setXxdm(String xxdm) {
        this.xxdm = xxdm;
    }
    
    @Column(name="PYCCM", length=2)

    public String getPyccm() {
        return this.pyccm;
    }
    
    public void setPyccm(String pyccm) {
        this.pyccm = pyccm;
    }
    
    @Column(name="XWM", length=2)

    public String getXwm() {
        return this.xwm;
    }
    
    public void setXwm(String xwm) {
        this.xwm = xwm;
    }
    
    @Column(name="ZT", length=2)

    public String getZt() {
        return this.zt;
    }
    
    public void setZt(String zt) {
        this.zt = zt;
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