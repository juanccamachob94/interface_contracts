package mx.com.upax.db.daos;

import mx.com.upax.db.connections.PostgresConnection;
import mx.com.upax.models.Job;
import java.util.List;
import mx.com.upax.models.Employee;

public class JobDAO {
  public static Job getJob(int id) throws Exception {
    return (Job) PostgresConnection.connect().
      getObject("SELECT j FROM Job j WHERE id = " + Integer.toString(id));
  }

  public static List<Employee> getEmployees(int jobId) throws Exception {
    return (List<Employee>) PostgresConnection.connect().
      getCollection("SELECT e FROM Employee e WHERE e.job.id = " + Integer.toString(jobId));
  }
}
