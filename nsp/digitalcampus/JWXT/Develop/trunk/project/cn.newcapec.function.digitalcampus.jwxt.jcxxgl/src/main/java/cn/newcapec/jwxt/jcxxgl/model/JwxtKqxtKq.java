package cn.newcapec.jwxt.jcxxgl.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * JwxtKqxtKq entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="JWXT_KQXT_KQ"
    ,schema="JWXT"
)

public class JwxtKqxtKq extends cn.newcapec.function.digitalcampus.common.model.AbstractModel implements java.io.Serializable {


    // Fields    

     private String id;
     private String rylxm;
     private String xh;
     private String kqrq;
     private String kcjc;
     private String kqjg;
     private String sgsj;
     private String xgr;
     private String cjr;
     private String jlssdw;
     private Date whsj;
     private Date cjsj;


    // Constructors

    /** default constructor */
    public JwxtKqxtKq() {
    }

	/** minimal constructor */
    public JwxtKqxtKq(String id, String rylxm, String xh, String kqrq, String kcjc, String kqjg, String cjr, String jlssdw, Date cjsj) {
        this.id = id;
        this.rylxm = rylxm;
        this.xh = xh;
        this.kqrq = kqrq;
        this.kcjc = kcjc;
        this.kqjg = kqjg;
        this.cjr = cjr;
        this.jlssdw = jlssdw;
        this.cjsj = cjsj;
    }
    
    /** full constructor */
    public JwxtKqxtKq(String id, String rylxm, String xh, String kqrq, String kcjc, String kqjg, String sgsj, String xgr, String cjr, String jlssdw, Date whsj, Date cjsj) {
        this.id = id;
        this.rylxm = rylxm;
        this.xh = xh;
        this.kqrq = kqrq;
        this.kcjc = kcjc;
        this.kqjg = kqjg;
        this.sgsj = sgsj;
        this.xgr = xgr;
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
    
    @Column(name="RYLXM", nullable=false, length=2)

    public String getRylxm() {
        return this.rylxm;
    }
    
    public void setRylxm(String rylxm) {
        this.rylxm = rylxm;
    }
    
    @Column(name="XH", nullable=false, length=32)

    public String getXh() {
        return this.xh;
    }
    
    public void setXh(String xh) {
        this.xh = xh;
    }
    
    @Column(name="KQRQ", nullable=false, length=10)

    public String getKqrq() {
        return this.kqrq;
    }
    
    public void setKqrq(String kqrq) {
        this.kqrq = kqrq;
    }
    
    @Column(name="KCJC", nullable=false, length=32)

    public String getKcjc() {
        return this.kcjc;
    }
    
    public void setKcjc(String kcjc) {
        this.kcjc = kcjc;
    }
    
    @Column(name="KQJG", nullable=false, length=2)

    public String getKqjg() {
        return this.kqjg;
    }
    
    public void setKqjg(String kqjg) {
        this.kqjg = kqjg;
    }
    
    @Column(name="SGSJ", length=10)

    public String getSgsj() {
        return this.sgsj;
    }
    
    public void setSgsj(String sgsj) {
        this.sgsj = sgsj;
    }
    
    @Column(name="XGR", length=32)

    public String getXgr() {
        return this.xgr;
    }
    
    public void setXgr(String xgr) {
        this.xgr = xgr;
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