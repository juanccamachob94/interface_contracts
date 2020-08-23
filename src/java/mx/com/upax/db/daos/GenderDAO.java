package mx.com.upax.db.daos;

import mx.com.upax.db.connections.PostgresConnection;
import mx.com.upax.models.Gender;

public class GenderDAO {
  public static Gender getGender(int id) throws Exception {
    return (Gender)PostgresConnection.connect().
      getObject("SELECT g FROM Gender g WHERE id = " + Integer.toString(id));
  }
}
