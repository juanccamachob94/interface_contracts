package mx.com.upax.models;
// Generated 22/08/2020 05:31:18 PM by Hibernate Tools 4.3.1


import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import mx.com.upax.utilities.DateTime;

/**
 * Employees generated by hbm2java
 */
@Entity
@Table(name="employees"
    ,schema="public"
)
public class Employees implements java.io.Serializable {


     private Integer id;
     private Genders genders;
     private Jobs jobs;
     private String name;
     private String lastName;
     private Date birthdate;
     private Date createdAt;
     private Date updatedAt;

    public Employees() {
    }

    public Employees(Integer id, Genders genders, Jobs jobs, String name, String lastName, Date birthdate) {
        this.id = id;
        this.genders = genders;
        this.jobs = jobs;
        this.name = name;
        this.lastName = lastName;
    }
    

    public Employees(Integer id, Genders genders, Jobs jobs, String name, String lastName, Date createdAt, Date updatedAt) {
        this.id = id;
        this.genders = genders;
        this.jobs = jobs;
        this.name = name;
        this.lastName = lastName;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
    public Employees(Integer id, Genders genders, Jobs jobs, String name, String lastName, Date birthdate, Date createdAt, Date updatedAt) {
       this.id = id;
       this.genders = genders;
       this.jobs = jobs;
       this.name = name;
       this.lastName = lastName;
       this.birthdate = birthdate;
       this.createdAt = createdAt;
       this.updatedAt = updatedAt;
    }

     @Id


    @Column(name="id", unique=true, nullable=false)
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="gender_id", nullable=false)
    public Genders getGenders() {
        return this.genders;
    }

    public void setGenders(Genders genders) {
        this.genders = genders;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="job_id", nullable=false)
    public Jobs getJobs() {
        return this.jobs;
    }

    public void setJobs(Jobs jobs) {
        this.jobs = jobs;
    }


    @Column(name="name", nullable=false)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Column(name="last_name", nullable=false)
    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="birthdate", length=13)
    public Date getBirthdate() {
        return this.birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
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
    
    public Integer getAge() {
      if(this.birthdate == null) return null;
      return DateTime.getDiffYears(this.birthdate, DateTime.today());
    }

}
