package mx.com.upax.services;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import mx.com.upax.db.daos.WorkedHoursDAO;

@WebService(serviceName = "EmployeeWorkedHoursByDatesSearcher")
public class EmployeeWorkedHoursByDatesSearcher {
  private WorkedHoursDAO workedHoursDAO;

  @WebMethod(operationName = "searchHoursByDates")
  public String searchHoursByDates(
    @WebParam(name = "employee_id") int employeeId,
    @WebParam(name = "start_date") String startDate,
    @WebParam(name = "end_date") String endDate
  ) {
    try {
      this.workedHoursDAO = new WorkedHoursDAO(employeeId, startDate, endDate);
      this.workedHoursDAO.validate();
      return getResponse();
    } catch(Exception e) {
      return "Ha ocurrido un error: " + e.getMessage();
    }
  }

  private String getResponse() throws Exception {
   if(this.workedHoursDAO.errors.isEmpty())
    return Long.toString(this.workedHoursDAO.getHours()) +
      " horas trabajadas por el empleado";
   else
    return this.workedHoursDAO.errorsMessage();
  }
}
