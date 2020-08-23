package mx.com.upax.db.connections;

import mx.com.upax.db.datasources.PostgresSource;

public class PostgresConnection {
  public static mx.com.upax.db.datasources.DataSource connect() {
    return PostgresSource.getInstance();
  }
}
