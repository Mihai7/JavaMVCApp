package ro.z2h.service;

import ro.z2h.dao.EmployeeDao;
import ro.z2h.domain.Employee;
import ro.z2h.utils.DatabaseManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by user on 11/11/2014.
 */
public class EmployeeServiceImpl implements EmployeeService {
    @Override
    public List<Employee> findAllEmployees() {

            Connection con = null;
            try {
                con = DatabaseManager.getConnection("ZTH_14", "passw0rd");
                return new EmployeeDao().getAllEmployees(con);
            } catch (SQLException e) {
            e.printStackTrace();
        }finally {
                if(con!=null)
                    try {
                        con.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
            }
            return null;}

    @Override
    public Employee findOneEmployee(Long id) {
        Connection con = null;
        try {
            con = DatabaseManager.getConnection("ZTH_14", "passw0rd");
            return new EmployeeDao().getEmployeeById(con,id);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if(con!=null)
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }
        return null;}

    @Override
    public void deleteOneEmployee(Long id) {
        Connection con = null;
        try {
            con = DatabaseManager.getConnection("ZTH_14", "passw0rd");
            Employee emp = new Employee();
            emp.setId(id);
            new EmployeeDao().deleteEmployee(emp,con);
        } finally {
            if(con!=null)
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }
    }


}


