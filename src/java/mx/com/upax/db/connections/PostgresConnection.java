package mx.com.upax.db.connections;

import mx.com.upax.db.datasources;

public class PostgresConnection extends Connection {
  public static DataSource connect() throws ClassNotFoundException {
    return SourcePostgres.getInstancia();
  }
}
