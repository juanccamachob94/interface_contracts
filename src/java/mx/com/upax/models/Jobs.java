package mx.com.upax.models;
// Generated 22/08/2020 05:31:18 PM by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Jobs generated by hbm2java
 */
@Entity
@Table(name="jobs"
    ,schema="public"
)
public class Jobs  implements java.io.Serializable {


     private long id;
     private String name;
     private BigDecimal salary;
     private Date createdAt;
     private Date updatedAt;
     private Set employeeses = new HashSet(0);
     private Set employeeWorkedHourses = new HashSet(0);

    public Jobs() {
    }

	
    public Jobs(long id, String name, BigDecimal salary, Date createdAt, Date updatedAt) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
    public Jobs(long id, String name, BigDecimal salary, Date createdAt, Date updatedAt, Set employeeses, Set employeeWorkedHourses) {
       this.id = id;
       this.name = name;
       this.salary = salary;
       this.createdAt = createdAt;
       this.updatedAt = updatedAt;
       this.employeeses = employeeses;
       this.employeeWorkedHourses = employeeWorkedHourses;
    }
   
     @Id 

    
    @Column(name="id", unique=true, nullable=false)
    public long getId() {
        return this.id;
    }
    
    public void setId(long id) {
        this.id = id;
    }

    
    @Column(name="name", nullable=false)
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    
    @Column(name="salary", nullable=false, precision=9)
    public BigDecimal getSalary() {
        return this.salary;
    }
    
    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="created_at", nullable=false, length=29)
    public Date getCreatedAt() {
        return this.createdAt;
    }
    
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="updated_at", nullable=false, length=29)
    public Date getUpdatedAt() {
        return this.updatedAt;
    }
    
    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="jobs")
    public Set getEmployeeses() {
        return this.employeeses;
    }
    
    public void setEmployeeses(Set employeeses) {
        this.employeeses = employeeses;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="jobs")
    public Set getEmployeeWorkedHourses() {
        return this.employeeWorkedHourses;
    }
    
    public void setEmployeeWorkedHourses(Set employeeWorkedHourses) {
        this.employeeWorkedHourses = employeeWorkedHourses;
    }




}


