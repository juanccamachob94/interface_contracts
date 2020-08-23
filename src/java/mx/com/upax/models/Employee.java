package mx.com.upax.models;

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

@Entity
@Table(name="employees", schema="public")
public class Employee implements java.io.Serializable {
  private Long id;
  private Gender gender;
  private Job job;
  private String name;
  private String lastName;
  private Date birthdate;

  public Employee() {
  }

  public Employee(Long id, Gender gender, Job job, String name, String lastName,
    Date birthdate) {
    this.id = id;
    this.gender = gender;
    this.job = job;
    this.name = name;
    this.lastName = lastName;
    this.birthdate = birthdate;
  }

  @Id
  @Column(name="id", unique=true, nullable=false)
  public Long getId() {
      return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  @ManyToOne(fetch=FetchType.LAZY)
  @JoinColumn(name="gender_id", nullable=false)
  public Gender getGender() {
    return this.gender;
  }

  public void setGender(Gender gender) {
    this.gender = gender;
  }

  @ManyToOne(fetch=FetchType.LAZY)
  @JoinColumn(name="job_id", nullable=false)
  public Job getJob() {
    return this.job;
  }

  public void setJob(Job job) {
    this.job = job;
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

  public Integer getAge() {
    if(this.birthdate == null) return null;
    return DateTime.getDiffYears(this.birthdate, DateTime.today());
  }
}
