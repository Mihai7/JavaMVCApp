package ro.z2h;

import ro.z2h.annotation.MyController;
import ro.z2h.controller.DepartmentController;
import ro.z2h.domain.Department;
import ro.z2h.fmk.AnnotationScanUtils;

import java.io.IOException;
import java.util.Iterator;

/**
 * Created by user on 11/10/2014.
 */
public class TestProgram {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Iterable<Class> ic = AnnotationScanUtils.getClasses("ro.z2h.controller");

        for (Class o : ic) {
            if(o.isAnnotationPresent(MyController.class))
              System.out.println(o);

        }
    }
}
