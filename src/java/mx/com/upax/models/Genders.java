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
  private Integer id;
  private String name;
  private Date createdAt;
  private Date updatedAt;
  private Set employeeses = new HashSet(0);

  public Genders() {
  }

  public Genders(Integer id, String name) {
    this.id = id;
    this.name = name;
  }

  @Id
  @Column(name="id", unique=true, nullable=false)
  public Integer getId() {
    return this.id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  @Column(name="name", nullable=false)
  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name="created_at", length=29)
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

  @OneToMany(fetch=FetchType.LAZY, mappedBy="genders")
  public Set getEmployeeses() {
    return this.employeeses;
  }

  public void setEmployeeses(Set employeeses) {
    this.employeeses = employeeses;
  }
}
