package mx.com.upax.db.daos;

import mx.com.upax.db.connections.PostgresConnection;
import mx.com.upax.models.Employee;

public class EmployeeDAO {
  public static Employee getEmployee(int id) throws Exception {
    return (Employee)PostgresConnection.connect().
      getObject("SELECT g FROM Employee g WHERE id = " + Integer.toString(id));
  }
}
