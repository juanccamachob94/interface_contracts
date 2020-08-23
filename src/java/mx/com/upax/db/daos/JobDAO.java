package mx.com.upax.db.daos;

import mx.com.upax.db.connections.PostgresConnection;
import mx.com.upax.models.Job;

public class JobDAO {
  public static Job getJob(int id) throws Exception {
    return (Job)PostgresConnection.connect().
      getObject("SELECT j FROM Job j WHERE id = " + Integer.toString(id));
  }
}
