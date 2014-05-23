package cn.newcapec.jwxt.jcxxgl.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * JwxtJcxxBjxxKz entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="JWXT_JCXX_BJXX_KZ"
    ,schema="JWXT"
)

public class JwxtJcxxBjxxKz extends cn.newcapec.function.digitalcampus.common.model.AbstractModel implements java.io.Serializable {


    // Fields    

     private String id;
     private String bjbh;
     private String bysj;
     private String jbny;
     private String bzrgh;
     private String bzxh;
     private String sfddb;
     private String cjr;
     private String jlssdw;
     private Date whsj;
     private Date cjsj;


    // Constructors

    /** default constructor */
    public JwxtJcxxBjxxKz() {
    }

	/** minimal constructor */
    public JwxtJcxxBjxxKz(String id, String bjbh, String bysj, String cjr, String jlssdw, Date cjsj) {
        this.id = id;
        this.bjbh = bjbh;
        this.bysj = bysj;
        this.cjr = cjr;
        this.jlssdw = jlssdw;
        this.cjsj = cjsj;
    }
    
    /** full constructor */
    public JwxtJcxxBjxxKz(String id, String bjbh, String bysj, String jbny, String bzrgh, String bzxh, String sfddb, String cjr, String jlssdw, Date whsj, Date cjsj) {
        this.id = id;
        this.bjbh = bjbh;
        this.bysj = bysj;
        this.jbny = jbny;
        this.bzrgh = bzrgh;
        this.bzxh = bzxh;
        this.sfddb = sfddb;
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
    
    @Column(name="BJBH", nullable=false, length=20)

    public String getBjbh() {
        return this.bjbh;
    }
    
    public void setBjbh(String bjbh) {
        this.bjbh = bjbh;
    }
    
    @Column(name="BYSJ", nullable=false, length=10)

    public String getBysj() {
        return this.bysj;
    }
    
    public void setBysj(String bysj) {
        this.bysj = bysj;
    }
    
    @Column(name="JBNY", length=10)

    public String getJbny() {
        return this.jbny;
    }
    
    public void setJbny(String jbny) {
        this.jbny = jbny;
    }
    
    @Column(name="BZRGH", length=32)

    public String getBzrgh() {
        return this.bzrgh;
    }
    
    public void setBzrgh(String bzrgh) {
        this.bzrgh = bzrgh;
    }
    
    @Column(name="BZXH", length=32)

    public String getBzxh() {
        return this.bzxh;
    }
    
    public void setBzxh(String bzxh) {
        this.bzxh = bzxh;
    }
    
    @Column(name="SFDDB", length=2)

    public String getSfddb() {
        return this.sfddb;
    }
    
    public void setSfddb(String sfddb) {
        this.sfddb = sfddb;
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