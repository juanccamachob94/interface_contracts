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
import mx.com.upax.models.Employee;

/**
 *
 * @author Dreamer
 */
@WebService(serviceName = "EmployeesByJobSearcher")
public class EmployeesByJobSearcher {

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "searchEmployeesByJob")
    public List<Employee> searchEmployeesByJob(@WebParam(name = "job_id") int jobId) {
        return new ArrayList<Employee>();
    }
}
