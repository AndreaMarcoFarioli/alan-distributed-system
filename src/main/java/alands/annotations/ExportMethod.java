package alands.annotations;

import java.lang.annotation.*;

/**
 * @author Andrea Marco Farioli
 * @version 0.1.0
 * Annotazione utilizzata al fine di identificare il grado di accesso ad un metodo in un servizio
 */
@Documented
@Target(ElementType.METHOD)
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface ExportMethod {
    VisibilityType visibility() default VisibilityType.PROTECTED;
}