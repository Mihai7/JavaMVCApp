package ro.z2h.service;

import ro.z2h.dao.DepartmentDao;
import ro.z2h.dao.JobDao;
import ro.z2h.domain.Department;
import ro.z2h.domain.Job;
import ro.z2h.utils.DatabaseManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 11/12/2014.
 */
public class JobServiceImpl implements JobService {
    @Override
    public List<Job> getAllJobs() {
        Connection con = null;
        ArrayList<Job> listaJob = null;
        try {
            con = DatabaseManager.getConnection("ZTH_14", "passw0rd");
            listaJob = new JobDao().getAllJobs(con);
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
        return listaJob;

    }
}
