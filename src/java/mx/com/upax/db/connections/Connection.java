package mx.com.upax.db.connections;

import mx.com.upax.db.DataSource;
import mx.com.upax.db.PostgresSource;

public interface Connection {
  public static DataSource connect() throws ClassNotFoundException;
}
