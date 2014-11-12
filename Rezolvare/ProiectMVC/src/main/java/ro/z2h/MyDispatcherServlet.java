package ro.z2h;

import org.codehaus.jackson.map.ObjectMapper;
import ro.z2h.annotation.MyController;
import ro.z2h.annotation.MyRequestMethod;
import ro.z2h.controller.DepartmentController;
import ro.z2h.controller.EmployeeController;
import ro.z2h.fmk.AnnotationScanUtils;
import ro.z2h.fmk.MethodAttributes;
import sun.reflect.generics.tree.Tree;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java
.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.*;

/**
 * Created by user on 11/10/2014.
 */
public class MyDispatcherServlet extends HttpServlet {
/* asta e cheie si implemeneteaza conceptul de FrontController*/

    @Override
    protected void doGet(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
        dispatchReply("GET",req, resp);
    }



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        dispatchReply("POST",req, resp);
    }



    private void dispatchReply(String httpMethod, HttpServletRequest req, HttpServletResponse resp) {
        try {
        /* Dispatch */
        Object r  = dispatch(httpMethod,req,resp);
        ObjectMapper mapper = new ObjectMapper();

        /* Reply */

            reply(mapper.writeValueAsString(r),req,resp);

              /* Catch Errors */
        } catch (Exception ex) {
            sendException(ex, req, resp);
        }




    }

    private void sendException(Exception ex, HttpServletRequest req, HttpServletResponse resp) {

        try {
            resp.getWriter().write(ex.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void reply(Object r, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        resp.getWriter().write(r.toString());

    }

    private Object dispatch(String httpMethod, HttpServletRequest req, HttpServletResponse resp) {


        Object returnObject;


        String cale = req.getPathInfo();


        MethodAttributes methodAttributes  = map.get(cale);






            try {


                Class<?> controllerClass = Class.forName(methodAttributes.getControllerClass());

                Object controllerInstance = controllerClass.newInstance();

                Method metodaController =    controllerInstance.getClass().getMethod(methodAttributes.getMethodName(), methodAttributes.getTipuri());
                Parameter[] parametrii = metodaController.getParameters();
                String[] params = new String[parametrii.length];

                for(int i=0;i<parametrii.length;i++)
                {
                    System.out.println(parametrii[i].getName());
                params[i] =req.getParameter( parametrii[i].getName());
                }
              returnObject = metodaController.invoke(controllerInstance,params);

                ObjectMapper mapper = new ObjectMapper();

                return returnObject;
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();

        }
        return " Situatie nu a decurs corespunzator!  ";


    }
    Map<String,MethodAttributes> map = new HashMap<String,MethodAttributes>();
    @Override
    public void init() throws ServletException {
        try {
            Iterable<Class>
                    classes = AnnotationScanUtils.getClasses("ro.z2h.controller");



            for (Class o : classes ) {
                if (o.isAnnotationPresent(MyController.class)) {
                    {


                        MyController a = (MyController) o.getAnnotation(MyController.class);
                        for (Method method : o.getMethods()) {
                            if (method.isAnnotationPresent(MyRequestMethod.class)) {

                                MyRequestMethod m = (MyRequestMethod) method.getAnnotation(MyRequestMethod.class);
                                MethodAttributes ma = new MethodAttributes();
                                ma.setControllerClass(o.getName());
                                ma.setMethodName(method.getName());
                                ma.setMethodType(m.methodType());
                                ma.setTipuri(method.getParameterTypes());
                                String cheie = a.urlPath() + m.urlPath();
                                map.put(cheie, ma);
                                System.out.println(a.urlPath() + m.urlPath() + " " + m.methodType());

                            }
                        }
                        for (Map.Entry<String, MethodAttributes> ent : map.entrySet()) {
                            System.out.println("Cheie : " + ent.getKey() + " \n " + "Value : " + ent.getValue());
                        }
                    }
                }


            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


        String pathController(Class Controller){
        if(!Controller.isAnnotationPresent(MyController.class))
            return null;
         return ((MyController)Controller.getAnnotation(MyController.class)).urlPath();
        }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        dispatchReply("DELETE",req, resp);
    }
}
