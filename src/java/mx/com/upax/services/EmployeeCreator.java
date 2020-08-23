package mx.com.upax.services;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import mx.com.upax.db.daos.NewEmployeeDAO;

@WebService(serviceName = "EmployeeCreator")
public class EmployeeCreator {
  private NewEmployeeDAO newEmployeeDAO;

  @WebMethod(operationName = "createEmployee")
  public String createEmployee(
    @WebParam(name = "gender_id") int genderId,
    @WebParam(name = "job_id") int jobId,
    @WebParam(name = "name") String name,
    @WebParam(name = "lastName") String lastName,
    @WebParam(name = "birthdate") String birthdate
  ) {
    try {
      this.newEmployeeDAO =
        new NewEmployeeDAO(genderId, jobId, name, lastName, birthdate);
      return getResponse();
    } catch(Exception e) {
      return "Ha ocurrido un error: " + e.getMessage();
    }
  }

  private String getResponse() throws Exception {
    if(this.newEmployeeDAO.create())
      return "Empleado creado exitosamente";
    else
      return this.newEmployeeDAO.errorsMessage();
  }
}
