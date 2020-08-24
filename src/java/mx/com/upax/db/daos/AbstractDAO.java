package mx.com.upax.db.daos;

import java.util.ArrayList;
import mx.com.upax.db.connections.PostgresConnection;

public class AbstractDAO {
  public ArrayList<String> errors;

  public AbstractDAO() {
    this.errors = new ArrayList<String>();
  }

  public String errorsMessage() {
    String message = "";
    for (String s : this.errors) message += s + "\n";
    return message;
  }

  public boolean create() throws Exception {
    validate();
    if(this.errors.isEmpty()) PostgresConnection.connect().insert(mainObject());
    return this.errors.isEmpty();
  }

  public void validate() throws Exception {
    validateMandatoryAttributesPresence();
  }

  protected void validateMandatoryAttributesPresence() throws Exception {
    throw new Exception("validateMandatoryAttributesPresence function not implemented");
  }

  protected Object mainObject() throws Exception {
    throw new Exception("mainObject function not implemented");
  }
}
