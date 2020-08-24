package mx.com.upax.db.daos;

import mx.com.upax.db.connections.PostgresConnection;
import mx.com.upax.models.Job;
import java.util.List;

public class JobDAO {
  public static Job getJob(int id) throws Exception {
    return (Job) PostgresConnection.connect().
      getObject("SELECT j FROM Job j WHERE id = " + Integer.toString(id));
  }

  public static List<Integer> getEmployeeIds(int jobId) throws Exception{
    List<Integer> employeeIds = null;
    PostgresConnection.connect().startsTransaction();
    try {
     employeeIds = (List<Integer>) PostgresConnection.connect().
      getCollection("SELECT e.id FROM Employee e WHERE e.job.id = " + Integer.toString(jobId));
    } catch(Exception e) {
        throw e;
    } finally {
        PostgresConnection.connect().endsTransaction();
    }
    return employeeIds;
  }
}
