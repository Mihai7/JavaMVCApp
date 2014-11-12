package ro.z2h.annotation;

import java.lang.annotation.*;

/**
 * Created by user on 11/10/2014.
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyRequestMethod {
    String urlPath();
    String methodType() default "GET";
}
