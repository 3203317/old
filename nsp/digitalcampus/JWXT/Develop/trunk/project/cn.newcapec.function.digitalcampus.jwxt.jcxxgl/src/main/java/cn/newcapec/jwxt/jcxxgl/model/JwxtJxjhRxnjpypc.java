package cn.newcapec.jwxt.jcxxgl.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * JwxtJxjhRxnjpypc entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="JWXT_JXJH_RXNJPYPC"
    ,schema="JWXT"
)

public class JwxtJxjhRxnjpypc extends cn.newcapec.function.digitalcampus.common.model.AbstractModel implements java.io.Serializable {


    // Fields    

     private String id;
     private String jhpcbh;
     private String rxnf;
     private String pycc;
     private String cjr;
     private String jlssdw;
     private Date whsj;
     private Date cjsj;


    // Constructors

    /** default constructor */
    public JwxtJxjhRxnjpypc() {
    }

	/** minimal constructor */
    public JwxtJxjhRxnjpypc(String id, String jhpcbh, String rxnf, String pycc, String cjr, String jlssdw, Date cjsj) {
        this.id = id;
        this.jhpcbh = jhpcbh;
        this.rxnf = rxnf;
        this.pycc = pycc;
        this.cjr = cjr;
        this.jlssdw = jlssdw;
        this.cjsj = cjsj;
    }
    
    /** full constructor */
    public JwxtJxjhRxnjpypc(String id, String jhpcbh, String rxnf, String pycc, String cjr, String jlssdw, Date whsj, Date cjsj) {
        this.id = id;
        this.jhpcbh = jhpcbh;
        this.rxnf = rxnf;
        this.pycc = pycc;
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
    
    @Column(name="JHPCBH", nullable=false, length=32)

    public String getJhpcbh() {
        return this.jhpcbh;
    }
    
    public void setJhpcbh(String jhpcbh) {
        this.jhpcbh = jhpcbh;
    }
    
    @Column(name="RXNF", nullable=false, length=4)

    public String getRxnf() {
        return this.rxnf;
    }
    
    public void setRxnf(String rxnf) {
        this.rxnf = rxnf;
    }
    
    @Column(name="PYCC", nullable=false, length=2)

    public String getPycc() {
        return this.pycc;
    }
    
    public void setPycc(String pycc) {
        this.pycc = pycc;
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