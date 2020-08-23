package mx.com.upax.db.daos;

import mx.com.upax.db.connections.PostgresConnection;
import mx.com.upax.models.Jobs;

public class JobDAO {
  public static Jobs getJob(int id) throws Exception {
    return (Jobs)PostgresConnection.connect().
      getObject("SELECT j FROM Jobs j WHERE id = " + Integer.toString(id));
  }
}
