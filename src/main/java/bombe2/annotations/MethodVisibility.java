package bombe2.annotations;

import java.lang.annotation.*;

@Documented
@Target(ElementType.METHOD)
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface MethodVisibility {
    VisibilityType visibility() default VisibilityType.LOCAL;
}