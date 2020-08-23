package mx.com.upax.models;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="genders", schema="public")
public class Gender implements java.io.Serializable {
  private Long id;
  private String name;
  private Set employees = new HashSet(0);

  public Gender() {
  }

  public Gender(Long id, String name) {
    this.id = id;
    this.name = name;
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

  @OneToMany(fetch=FetchType.LAZY, mappedBy="genders")
  public Set getEmployees() {
    return this.employees;
  }

  public void setEmployees(Set employees) {
    this.employees = employees;
  }
}
