package mx.com.upax.models;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="jobs", schema="public")
public class Job  implements java.io.Serializable {
  private Long id;
  private String name;
  private BigDecimal salary;
  private Set employees = new HashSet(0);

  public Job() {
  }

  public Job(Long id, String name, BigDecimal salary) {
    this.id = id;
    this.name = name;
    this.salary = salary;
  }

  @Id
  @Column(name="id", unique=true, nullable=false)
  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
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

  @OneToMany(fetch=FetchType.LAZY, mappedBy="jobs")
  public Set getEmployees() {
    return this.employees;
  }

  public void setEmployees(Set employees) {
    this.employees = employees;
  }
}
