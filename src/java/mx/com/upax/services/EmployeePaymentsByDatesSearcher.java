package mx.com.upax.services;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import mx.com.upax.db.daos.PaymentsDAO;

@WebService(serviceName = "EmployeePaymentsByDatesSearcher")
public class EmployeePaymentsByDatesSearcher {
  private PaymentsDAO paymentsDAO;

  @WebMethod(operationName = "searchPaymentsByDates")
  public String searchPaymentsByDates(
    @WebParam(name = "employee_id") int employeeId,
    @WebParam(name = "start_date") String startDate,
    @WebParam(name = "end_date") String endDate
  ) {
    try {
      this.paymentsDAO = new PaymentsDAO(employeeId, startDate, endDate);
      this.paymentsDAO.validate();
      return getResponse();
    } catch(Exception e) {
      return "Ha ocurrido un error: " + e.getMessage();
    }
  }

  private String getResponse() throws Exception {
   if(this.paymentsDAO.errors.isEmpty())
    return "$" + Float.toString(this.paymentsDAO.getPaymentsAmount()) + " pagados al empleado";
   else
    return this.paymentsDAO.errorsMessage();
  }
}
