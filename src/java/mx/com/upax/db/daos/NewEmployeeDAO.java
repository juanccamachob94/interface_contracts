package mx.com.upax.db.daos;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import mx.com.upax.models.Employees;

public class NewEmployeeDAO {
  private static final int LEGAL_AGE = 18;
  public Employees employee;
  public ArrayList<String> errors;

  public NewEmployeeDAO(int genderId, int jobId, String name, String lastName, String birthdate)
    throws Exception {
    this.employee = new Employees(null, GenderDAO.getGender(genderId),
      JobDAO.getJob(genderId), name, lastName, sanitizedBirthDate(birthdate));
    this.errors = new ArrayList<String>();
  }

  public boolean create() {
    validateMandatoryAttributesPresence();
    validateLegalAge();
    return this.errors.isEmpty();
  }

  public String errorsMessage() {
    String message = "";
    for (String s : this.errors) message += s + "\t";
    return message;
  }

  private void validateMandatoryAttributesPresence() {
    validateGenderPresence();
    validateJobPresence();
    validateNamePresence();
    validateLastNamePresence();
  }

  private void validateGenderPresence() {
    if(this.employee.getGenders() == null) this.errors.add("Género no encontrado");
  }

  private void validateJobPresence() {
    if(this.employee.getJobs() == null) this.errors.add("Trabajo no encontrado");
  }

  private void validateNamePresence() {
    if(this.employee.getName() == null) this.errors.add("Nombre vacío");
  }

  private void validateLastNamePresence() {
    if(this.employee.getLastName() == null) this.errors.add("Apellido vacío");
  }

  private void validateLegalAge() {
    if(this.employee.getAge() == null || this.employee.getAge() < LEGAL_AGE)
      this.errors.add("Debe ser mayor de edad");
  }

  private Date sanitizedBirthDate(String strBirthDate) {
    try {
      return (new SimpleDateFormat("yyyy-MM-dd")).parse(strBirthDate);
    } catch(Exception e) {
      return null;
    }
  }
}
