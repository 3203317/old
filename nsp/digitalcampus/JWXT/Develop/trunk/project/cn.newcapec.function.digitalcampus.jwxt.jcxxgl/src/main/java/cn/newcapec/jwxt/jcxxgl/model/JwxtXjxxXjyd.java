package cn.newcapec.jwxt.jcxxgl.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * JwxtXjxxXjyd entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="JWXT_XJXX_XJYD"
    ,schema="JWXT"
)

public class JwxtXjxxXjyd extends cn.newcapec.function.digitalcampus.common.model.AbstractModel implements java.io.Serializable {


    // Fields    

     private String id;
     private String xh;
     private String ydlbm;
     private String ydrq;
     private String ydyym;
     private String sprq;
     private String spwh;
     private String ydlyxxm;
     private String ydqxxxm;
     private String ydsm;
     private String yyxsh;
     private String yzym;
     private String ybh;
     private String ynj;
     private String yxz;
     private String xyxsh;
     private String xzym;
     private String xbh;
     private String xnj;
     private String xxz;
     private String shjg;
     private String shyy;
     private String shsj;
     private String cjr;
     private String jlssdw;
     private Date whsj;
     private Date cjsj;


    // Constructors

    /** default constructor */
    public JwxtXjxxXjyd() {
    }

	/** minimal constructor */
    public JwxtXjxxXjyd(String id, String xh, String ydlbm, String ydrq, String ydyym, String cjr, String jlssdw, Date cjsj) {
        this.id = id;
        this.xh = xh;
        this.ydlbm = ydlbm;
        this.ydrq = ydrq;
        this.ydyym = ydyym;
        this.cjr = cjr;
        this.jlssdw = jlssdw;
        this.cjsj = cjsj;
    }
    
    /** full constructor */
    public JwxtXjxxXjyd(String id, String xh, String ydlbm, String ydrq, String ydyym, String sprq, String spwh, String ydlyxxm, String ydqxxxm, String ydsm, String yyxsh, String yzym, String ybh, String ynj, String yxz, String xyxsh, String xzym, String xbh, String xnj, String xxz, String shjg, String shyy, String shsj, String cjr, String jlssdw, Date whsj, Date cjsj) {
        this.id = id;
        this.xh = xh;
        this.ydlbm = ydlbm;
        this.ydrq = ydrq;
        this.ydyym = ydyym;
        this.sprq = sprq;
        this.spwh = spwh;
        this.ydlyxxm = ydlyxxm;
        this.ydqxxxm = ydqxxxm;
        this.ydsm = ydsm;
        this.yyxsh = yyxsh;
        this.yzym = yzym;
        this.ybh = ybh;
        this.ynj = ynj;
        this.yxz = yxz;
        this.xyxsh = xyxsh;
        this.xzym = xzym;
        this.xbh = xbh;
        this.xnj = xnj;
        this.xxz = xxz;
        this.shjg = shjg;
        this.shyy = shyy;
        this.shsj = shsj;
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
    
    @Column(name="YDLBM", nullable=false, length=2)

    public String getYdlbm() {
        return this.ydlbm;
    }
    
    public void setYdlbm(String ydlbm) {
        this.ydlbm = ydlbm;
    }
    
    @Column(name="YDRQ", nullable=false, length=10)

    public String getYdrq() {
        return this.ydrq;
    }
    
    public void setYdrq(String ydrq) {
        this.ydrq = ydrq;
    }
    
    @Column(name="YDYYM", nullable=false, length=2)

    public String getYdyym() {
        return this.ydyym;
    }
    
    public void setYdyym(String ydyym) {
        this.ydyym = ydyym;
    }
    
    @Column(name="SPRQ", length=10)

    public String getSprq() {
        return this.sprq;
    }
    
    public void setSprq(String sprq) {
        this.sprq = sprq;
    }
    
    @Column(name="SPWH", length=50)

    public String getSpwh() {
        return this.spwh;
    }
    
    public void setSpwh(String spwh) {
        this.spwh = spwh;
    }
    
    @Column(name="YDLYXXM", length=200)

    public String getYdlyxxm() {
        return this.ydlyxxm;
    }
    
    public void setYdlyxxm(String ydlyxxm) {
        this.ydlyxxm = ydlyxxm;
    }
    
    @Column(name="YDQXXXM", length=200)

    public String getYdqxxxm() {
        return this.ydqxxxm;
    }
    
    public void setYdqxxxm(String ydqxxxm) {
        this.ydqxxxm = ydqxxxm;
    }
    
    @Column(name="YDSM", length=2000)

    public String getYdsm() {
        return this.ydsm;
    }
    
    public void setYdsm(String ydsm) {
        this.ydsm = ydsm;
    }
    
    @Column(name="YYXSH", length=32)

    public String getYyxsh() {
        return this.yyxsh;
    }
    
    public void setYyxsh(String yyxsh) {
        this.yyxsh = yyxsh;
    }
    
    @Column(name="YZYM", length=32)

    public String getYzym() {
        return this.yzym;
    }
    
    public void setYzym(String yzym) {
        this.yzym = yzym;
    }
    
    @Column(name="YBH", length=32)

    public String getYbh() {
        return this.ybh;
    }
    
    public void setYbh(String ybh) {
        this.ybh = ybh;
    }
    
    @Column(name="YNJ", length=32)

    public String getYnj() {
        return this.ynj;
    }
    
    public void setYnj(String ynj) {
        this.ynj = ynj;
    }
    
    @Column(name="YXZ", length=2)

    public String getYxz() {
        return this.yxz;
    }
    
    public void setYxz(String yxz) {
        this.yxz = yxz;
    }
    
    @Column(name="XYXSH", length=32)

    public String getXyxsh() {
        return this.xyxsh;
    }
    
    public void setXyxsh(String xyxsh) {
        this.xyxsh = xyxsh;
    }
    
    @Column(name="XZYM", length=32)

    public String getXzym() {
        return this.xzym;
    }
    
    public void setXzym(String xzym) {
        this.xzym = xzym;
    }
    
    @Column(name="XBH", length=32)

    public String getXbh() {
        return this.xbh;
    }
    
    public void setXbh(String xbh) {
        this.xbh = xbh;
    }
    
    @Column(name="XNJ", length=32)

    public String getXnj() {
        return this.xnj;
    }
    
    public void setXnj(String xnj) {
        this.xnj = xnj;
    }
    
    @Column(name="XXZ", length=2)

    public String getXxz() {
        return this.xxz;
    }
    
    public void setXxz(String xxz) {
        this.xxz = xxz;
    }
    
    @Column(name="SHJG", length=2)

    public String getShjg() {
        return this.shjg;
    }
    
    public void setShjg(String shjg) {
        this.shjg = shjg;
    }
    
    @Column(name="SHYY", length=200)

    public String getShyy() {
        return this.shyy;
    }
    
    public void setShyy(String shyy) {
        this.shyy = shyy;
    }
    
    @Column(name="SHSJ", length=10)

    public String getShsj() {
        return this.shsj;
    }
    
    public void setShsj(String shsj) {
        this.shsj = shsj;
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