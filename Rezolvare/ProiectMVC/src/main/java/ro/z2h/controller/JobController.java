package ro.z2h.controller;

import ro.z2h.annotation.MyController;
import ro.z2h.annotation.MyRequestMethod;
import ro.z2h.domain.Employee;
import ro.z2h.domain.Job;
import ro.z2h.service.EmployeeService;
import ro.z2h.service.EmployeeServiceImpl;
import ro.z2h.service.JobService;
import ro.z2h.service.JobServiceImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 11/12/2014.
 */

@MyController(urlPath = "/job")
public class JobController {

    @MyRequestMethod(urlPath = "/all", methodType = "GET")
    public List<Job> getAllJobs() {

        ArrayList<Job> listaJob;
        JobService jS = new JobServiceImpl();
        return jS.getAllJobs();

    }
}
