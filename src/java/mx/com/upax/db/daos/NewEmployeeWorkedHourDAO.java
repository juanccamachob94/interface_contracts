package mx.com.upax.db.daos;

import mx.com.upax.db.connections.PostgresConnection;
import mx.com.upax.models.EmployeeWorkedHour;
import mx.com.upax.utilities.DateTime;

public class NewEmployeeWorkedHourDAO extends AbstractDAO {
  private static final int MAX_HOURS = 20;
  public EmployeeWorkedHour employeeWorkedHour;

  public NewEmployeeWorkedHourDAO(int employeeId, int workedHours, String workedDate)
    throws Exception {
    super();
    this.employeeWorkedHour = new EmployeeWorkedHour(null, EmployeeDAO.getEmployee(employeeId),
      (short)workedHours, DateTime.sanitizedDate(workedDate));
  }

  @Override
  public void validate() throws Exception {
    super.validate();
    validateWorkedHours();
    validateWorkedDate();
  }

  @Override
  protected void validateMandatoryAttributesPresence() {
    validateEmployeePresence();
  }

  @Override
  protected EmployeeWorkedHour mainObject() {
    return this.employeeWorkedHour;
  }

  private void validateEmployeePresence() {
    if(this.employeeWorkedHour.getEmployee() == null) this.errors.add("Empleado no encontrado");
  }

  private void validateWorkedHours() {
    if(this.employeeWorkedHour.getWorkedHours() <= 0) this.errors.add("Horas inválidas");
    if(this.employeeWorkedHour.getWorkedHours() > MAX_HOURS)
      this.errors.add("El máximo valor permitido es de " + Integer.toString(MAX_HOURS));
  }

  private void validateWorkedDate() throws Exception {
    if(DateTime.today().before(this.employeeWorkedHour.getWorkedDate()))
      this.errors.add("La fecha de trabajo debe ser menor o igual a la actual");
    if(this.employeeWorkedHour.getEmployee() != null && dateRegistered())
      this.errors.add("La fecha ya se encuentra registrada");
  }

  private boolean dateRegistered() throws Exception {
    return ((Long)PostgresConnection.connect().getObject(
      "SELECT COUNT(*) FROM EmployeeWorkedHour ewh WHERE ewh.employee.id = " +
        this.employeeWorkedHour.getEmployee().getId() + " AND ewh.workedDate = '" +
        DateTime.yyyymmddDate(this.employeeWorkedHour.getWorkedDate()) + "'")
    ).intValue() > 0;
  }
}
