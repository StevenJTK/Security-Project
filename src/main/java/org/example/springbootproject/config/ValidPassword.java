package org.example.springbootproject.config;

/*import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;


 @Documented
@Constraint(validatedBy = PasswordConstraintValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME) */

import java.lang.annotation.Annotation;

public interface ValidPassword extends Annotation {
   /* ValidPassword {
        String message() default "Lösenordet är inte giltigt.";
        Class<?>[] groups() default {};
        Class<? extends Payload>[] payload() default {}; */
    }
//}