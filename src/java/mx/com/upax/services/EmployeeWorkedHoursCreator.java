package mx.com.upax.services;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import mx.com.upax.db.daos.NewEmployeeWorkedHourDAO;

@WebService(serviceName = "EmployeeWorkedHoursCreator")
public class EmployeeWorkedHoursCreator {
  private NewEmployeeWorkedHourDAO newEmployeeWorkedHourDAO;

  @WebMethod(operationName = "createEmployeeWorkedHour")
  public String createEmployeeWorkedHour(
   @WebParam(name = "employee_id") int employeeId,
   @WebParam(name = "worked_hours") int workedHours,
   @WebParam(name = "worked_date") String workedDate
  ) {
    try {
      this.newEmployeeWorkedHourDAO =
       new NewEmployeeWorkedHourDAO(employeeId, workedHours, workedDate);
      return getResponse();
    } catch(Exception e) {
      return "Ha ocurrido un error: " + e.getMessage();
    }
  }

  private String getResponse() throws Exception {
   if(this.newEmployeeWorkedHourDAO.create())
    return "Horas trabajadas añadidas exitosamente";
   else
    return this.newEmployeeWorkedHourDAO.errorsMessage();
  }
}
