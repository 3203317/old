package cn.newcapec.jwxt.jcxxgl.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * JwxtJcxxDwxxKz entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="JWXT_JCXX_DWXX_KZ"
    ,schema="JWXT"
)

public class JwxtJcxxDwxxKz extends cn.newcapec.function.digitalcampus.common.model.AbstractModel implements java.io.Serializable {


    // Fields    

     private String id;
     private String dwh;
     private String sfst;
     private String xq;
     private String bz;
     private String cjr;
     private String jlssdw;
     private Date whsj;
     private Date cjsj;


    // Constructors

    /** default constructor */
    public JwxtJcxxDwxxKz() {
    }

	/** minimal constructor */
    public JwxtJcxxDwxxKz(String id, String dwh, String sfst, String cjr, String jlssdw, Date cjsj) {
        this.id = id;
        this.dwh = dwh;
        this.sfst = sfst;
        this.cjr = cjr;
        this.jlssdw = jlssdw;
        this.cjsj = cjsj;
    }
    
    /** full constructor */
    public JwxtJcxxDwxxKz(String id, String dwh, String sfst, String xq, String bz, String cjr, String jlssdw, Date whsj, Date cjsj) {
        this.id = id;
        this.dwh = dwh;
        this.sfst = sfst;
        this.xq = xq;
        this.bz = bz;
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
    
    @Column(name="DWH", nullable=false, length=32)

    public String getDwh() {
        return this.dwh;
    }
    
    public void setDwh(String dwh) {
        this.dwh = dwh;
    }
    
    @Column(name="SFST", nullable=false, length=1)

    public String getSfst() {
        return this.sfst;
    }
    
    public void setSfst(String sfst) {
        this.sfst = sfst;
    }
    
    @Column(name="XQ", length=32)

    public String getXq() {
        return this.xq;
    }
    
    public void setXq(String xq) {
        this.xq = xq;
    }
    
    @Column(name="BZ", length=2000)

    public String getBz() {
        return this.bz;
    }
    
    public void setBz(String bz) {
        this.bz = bz;
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