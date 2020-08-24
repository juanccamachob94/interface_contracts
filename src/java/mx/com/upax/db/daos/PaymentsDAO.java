package mx.com.upax.db.daos;

import mx.com.upax.db.connections.PostgresConnection;

public class PaymentsDAO extends WorkedHoursDAO {

  public PaymentsDAO(int employeeId, String startDate, String endDate) throws Exception {
    super(employeeId, startDate, endDate);
  }

  public Float getPaymentsAmount() throws Exception {
    PostgresConnection.connect().startsTransaction();
    float response = getHours() * EmployeeDAO.getEmployee(employeeId).getJob().getSalary().floatValue();
    PostgresConnection.connect().endsTransaction();
    return response;
  }
}
