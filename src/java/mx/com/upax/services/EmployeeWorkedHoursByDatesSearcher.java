package mx.com.upax.services;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

@WebService(serviceName = "EmployeeWorkedHoursByDatesSearcher")
public class EmployeeWorkedHoursByDatesSearcher {

    @WebMethod(operationName = "searchHoursByDates")
    public String searchHoursByDates(
            @WebParam(name = "employee_id") int employeeId,
            @WebParam(name = "start_date") String startDate,
            @WebParam(name = "end_date") String endDate
    ) {
        return "Hello";
    }
}
