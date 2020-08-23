package mx.com.upax.db.daos;

import java.util.ArrayList;
import mx.com.upax.db.connections.PostgresConnection;
import mx.com.upax.models.Employee;
import mx.com.upax.utilities.DateTime;

public class NewEmployeeDAO {
  private static final int LEGAL_AGE = 18;
  public Employee employee;
  public ArrayList<String> errors;

  public NewEmployeeDAO(int genderId, int jobId, String name, String lastName, String birthdate)
    throws Exception {
    this.employee = new Employee(null, GenderDAO.getGender(genderId),
      JobDAO.getJob(jobId), name, lastName, DateTime.sanitizedDate(birthdate));
    this.errors = new ArrayList<String>();
  }

  public boolean create() throws Exception {
    validateDAO();
    if(this.errors.isEmpty())
      PostgresConnection.connect().insert(this.employee);
    return this.errors.isEmpty();
  }

  public String errorsMessage() {
    String message = "";
    for (String s : this.errors) message += s + "\n";
    return message;
  }
  

  protected void validateDAO() throws Exception {
    validateMandatoryAttributesPresence();
    validateLegalAge();
  }

  protected void validateMandatoryAttributesPresence() {
    validateGenderPresence();
    validateJobPresence();
    validateNamePresence();
    validateLastNamePresence();
  }

  private void validateGenderPresence() {
    if(this.employee.getGender() == null) this.errors.add("Género no encontrado");
  }

  private void validateJobPresence() {
    if(this.employee.getJob() == null) this.errors.add("Trabajo no encontrado");
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
}
