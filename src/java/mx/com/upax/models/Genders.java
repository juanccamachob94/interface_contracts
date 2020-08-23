package mx.com.upax.models;

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

@Entity
@Table(name="genders", schema="public")
public class Genders  implements java.io.Serializable {
  private Long id;
  private String name;
  private Set employeeses = new HashSet(0);

  public Genders() {
  }

  public Genders(Long id, String name) {
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
  public Set getEmployeeses() {
    return this.employeeses;
  }

  public void setEmployeeses(Set employeeses) {
    this.employeeses = employeeses;
  }
}
