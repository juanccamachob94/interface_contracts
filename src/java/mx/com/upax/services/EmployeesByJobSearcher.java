/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.upax.services;

import java.util.ArrayList;
import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import mx.com.upax.db.daos.JobDAO;

@WebService(serviceName = "EmployeesByJobSearcher")
public class EmployeesByJobSearcher {
  @WebMethod(operationName = "searchEmployeesByJob")
  public List<Integer> searchEmployeesByJob(@WebParam(name = "job_id") int jobId) {
    try {
      return JobDAO.getEmployeeIds(jobId);
    } catch(Exception e) {
      return new ArrayList<Integer>();
    }
  }
}
