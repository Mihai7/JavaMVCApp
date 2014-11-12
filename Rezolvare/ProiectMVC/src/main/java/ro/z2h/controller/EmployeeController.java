package ro.z2h.controller;

import ro.z2h.annotation.MyController;
import ro.z2h.annotation.MyRequestMethod;
import ro.z2h.domain.Employee;
import ro.z2h.service.EmployeeService;
import ro.z2h.service.EmployeeServiceImpl;

import java.util.List;

/**
 * Created by user on 11/10/2014.
 */

@MyController(urlPath = "/employee")
public class EmployeeController {

    @MyRequestMethod(urlPath = "/all", methodType = "GET")
    public List<Employee> getAllEmployees() {
        EmployeeService es = new EmployeeServiceImpl();
        List<Employee> al;

        al = es.findAllEmployees();
        if(!(al.size()>0))
        {
        Employee emp  = new Employee();
            emp.setId(-1l);
            emp.setLastName("Angajat Test");
         al.add(emp);
        }
    return al;

    }


    @MyRequestMethod(urlPath = "/delete", methodType = "DELETE")
    public void deleteOneEmployee(String id){

            EmployeeService es = new EmployeeServiceImpl();

             es.deleteOneEmployee(Long.parseLong(id));


    }


    @MyRequestMethod(urlPath = "/one")
    public Employee getOneEmploy(String id) {
        EmployeeService es = new EmployeeServiceImpl();
        System.out.println("Am intrat in getOneEmploy si am primit ca param " + id);
        return es.findOneEmployee(Long.parseLong(id));
    }


    public void adunare(Integer a){
        System.out.println("sunnt in adunare : " +  a);
    }

}
