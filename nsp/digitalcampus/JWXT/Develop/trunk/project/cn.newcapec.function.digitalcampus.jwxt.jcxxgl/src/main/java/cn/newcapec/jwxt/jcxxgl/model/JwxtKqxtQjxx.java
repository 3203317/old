package cn.newcapec.jwxt.jcxxgl.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * JwxtKqxtQjxx entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="JWXT_KQXT_QJXX"
    ,schema="JWXT"
)

public class JwxtKqxtQjxx extends cn.newcapec.function.digitalcampus.common.model.AbstractModel implements java.io.Serializable {


    // Fields    

     private String id;
     private String xh;
     private String qjlb;
     private String fj;
     private String sy;
     private Date qjsjq;
     private Date qjsjz;
     private Date tjsj;
     private String tjr;
     private String kqzt;
     private String spzt;
     private Date spsj;
     private String spr;
     private String spyj;
     private String cjr;
     private String jlssdw;
     private Date whsj;
     private Date cjsj;


    // Constructors

    /** default constructor */
    public JwxtKqxtQjxx() {
    }

	/** minimal constructor */
    public JwxtKqxtQjxx(String id, String xh, String qjlb, Date qjsjq, Date qjsjz, Date tjsj, String tjr, String kqzt, String spzt, String cjr, String jlssdw, Date cjsj) {
        this.id = id;
        this.xh = xh;
        this.qjlb = qjlb;
        this.qjsjq = qjsjq;
        this.qjsjz = qjsjz;
        this.tjsj = tjsj;
        this.tjr = tjr;
        this.kqzt = kqzt;
        this.spzt = spzt;
        this.cjr = cjr;
        this.jlssdw = jlssdw;
        this.cjsj = cjsj;
    }
    
    /** full constructor */
    public JwxtKqxtQjxx(String id, String xh, String qjlb, String fj, String sy, Date qjsjq, Date qjsjz, Date tjsj, String tjr, String kqzt, String spzt, Date spsj, String spr, String spyj, String cjr, String jlssdw, Date whsj, Date cjsj) {
        this.id = id;
        this.xh = xh;
        this.qjlb = qjlb;
        this.fj = fj;
        this.sy = sy;
        this.qjsjq = qjsjq;
        this.qjsjz = qjsjz;
        this.tjsj = tjsj;
        this.tjr = tjr;
        this.kqzt = kqzt;
        this.spzt = spzt;
        this.spsj = spsj;
        this.spr = spr;
        this.spyj = spyj;
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
    
    @Column(name="XH", nullable=false, length=32)

    public String getXh() {
        return this.xh;
    }
    
    public void setXh(String xh) {
        this.xh = xh;
    }
    
    @Column(name="QJLB", nullable=false, length=2)

    public String getQjlb() {
        return this.qjlb;
    }
    
    public void setQjlb(String qjlb) {
        this.qjlb = qjlb;
    }
    
    @Column(name="FJ", length=2000)

    public String getFj() {
        return this.fj;
    }
    
    public void setFj(String fj) {
        this.fj = fj;
    }
    
    @Column(name="SY", length=2000)

    public String getSy() {
        return this.sy;
    }
    
    public void setSy(String sy) {
        this.sy = sy;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="QJSJQ", nullable=false, length=7)

    public Date getQjsjq() {
        return this.qjsjq;
    }
    
    public void setQjsjq(Date qjsjq) {
        this.qjsjq = qjsjq;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="QJSJZ", nullable=false, length=7)

    public Date getQjsjz() {
        return this.qjsjz;
    }
    
    public void setQjsjz(Date qjsjz) {
        this.qjsjz = qjsjz;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="TJSJ", nullable=false, length=7)

    public Date getTjsj() {
        return this.tjsj;
    }
    
    public void setTjsj(Date tjsj) {
        this.tjsj = tjsj;
    }
    
    @Column(name="TJR", nullable=false, length=32)

    public String getTjr() {
        return this.tjr;
    }
    
    public void setTjr(String tjr) {
        this.tjr = tjr;
    }
    
    @Column(name="KQZT", nullable=false, length=32)

    public String getKqzt() {
        return this.kqzt;
    }
    
    public void setKqzt(String kqzt) {
        this.kqzt = kqzt;
    }
    
    @Column(name="SPZT", nullable=false, length=2)

    public String getSpzt() {
        return this.spzt;
    }
    
    public void setSpzt(String spzt) {
        this.spzt = spzt;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="SPSJ", length=7)

    public Date getSpsj() {
        return this.spsj;
    }
    
    public void setSpsj(Date spsj) {
        this.spsj = spsj;
    }
    
    @Column(name="SPR", length=32)

    public String getSpr() {
        return this.spr;
    }
    
    public void setSpr(String spr) {
        this.spr = spr;
    }
    
    @Column(name="SPYJ", length=2000)

    public String getSpyj() {
        return this.spyj;
    }
    
    public void setSpyj(String spyj) {
        this.spyj = spyj;
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