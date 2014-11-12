package ro.z2h.service;

import ro.z2h.dao.DepartmentDao;
import ro.z2h.domain.Department;
import ro.z2h.utils.DatabaseManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mihai on 11/13/2014.
 */

public class DepartmentServiceImpl implements DepartmentService {


    @Override
    public List<Department> getAllDepartments() {
        Connection con = null;
        ArrayList<Department> listaDep = null;
        try {
            con = DatabaseManager.getConnection("ZTH_14", "passw0rd");
        listaDep = new DepartmentDao().getAllDepartments(con);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(con!=null)
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }
        return listaDep;

    }


}
