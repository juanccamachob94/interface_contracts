package mx.com.upax.db.daos;

import mx.com.upax.utilities.DateTime;
import mx.com.upax.db.connections.PostgresConnection;

public class WorkedHoursDAO extends AbstractDAO {
  protected int employeeId;
  protected String strStartDate;
  protected String strEndDate;

  public WorkedHoursDAO(int employeeId, String startDate, String endDate) throws Exception {
    super();
    this.employeeId = employeeId;
    this.strStartDate = startDate;
    this.strEndDate = endDate;
  }

  @Override
  public void validate() throws Exception {
    super.validate();
    validateDateRange();
  }

  @Override
  protected void validateMandatoryAttributesPresence() throws Exception {
    validateEmployeePresence();
    validateStrStartDatePresence();
    validateStrEndDatePresence();
  }

  private void validateEmployeePresence() throws Exception {
    if(EmployeeDAO.getEmployee(this.employeeId) == null)
      this.errors.add("El empleado no existe");
  }

  private void validateStrStartDatePresence() {
    if(DateTime.sanitizedDate(this.strStartDate) == null)
      this.errors.add("La fecha de inicio es nula");
  }

  private void validateStrEndDatePresence() {
    if(DateTime.sanitizedDate(this.strEndDate) == null)
      this.errors.add("La fecha de finalización es nula");
  }

  private void validateDateRange() {
    if(this.errors.isEmpty() &&
      DateTime.sanitizedDate(this.strStartDate).after(DateTime.sanitizedDate(this.strEndDate)))
      this.errors.add("La fecha de inicio debe ser menor a la fecha de finalización");
  }

  public long getHours() throws Exception {
    Long res = (Long) PostgresConnection.connect().getObject(
        "SELECT SUM(workedHours) as d FROM EmployeeWorkedHour WHERE employee.id = " +
        Integer.toString(this.employeeId) +
        " AND workedDate BETWEEN '" + this.strStartDate + "' AND '" + this.strEndDate + "'"
    );
    return res == null ? 0 : res.longValue();
  }
}
