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

@Entity
@Table(name="employee_worked_hours", schema="public")
public class EmployeeWorkedHours  implements java.io.Serializable {
  private Long id;
  private Jobs jobs;
  private short workedHours;
  private Date workedDate;

  public EmployeeWorkedHours() {
  }

  public EmployeeWorkedHours(Long id, Jobs jobs, short workedHours, Date workedDate) {
    this.id = id;
    this.jobs = jobs;
    this.workedHours = workedHours;
    this.workedDate = workedDate;
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
  @JoinColumn(name="employee_id", nullable=false)
  public Jobs getJobs() {
    return this.jobs;
  }

  public void setJobs(Jobs jobs) {
    this.jobs = jobs;
  }


  @Column(name="worked_hours", nullable=false)
  public short getWorkedHours() {
    return this.workedHours;
  }

  public void setWorkedHours(short workedHours) {
    this.workedHours = workedHours;
  }

  @Temporal(TemporalType.DATE)
  @Column(name="worked_date", nullable=false, length=13)
  public Date getWorkedDate() {
    return this.workedDate;
  }

  public void setWorkedDate(Date workedDate) {
    this.workedDate = workedDate;
  }
}
