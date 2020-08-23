package mx.com.upax.db.daos;

import mx.com.upax.db.connections.PostgresConnection;
import mx.com.upax.models.Genders;

public class GenderDAO {
  public static Genders getGender(int id) throws Exception {
    return (Genders)PostgresConnection.connect().
      getObject("SELECT g FROM Genders g WHERE id = " + Integer.toString(id));
  }
}
