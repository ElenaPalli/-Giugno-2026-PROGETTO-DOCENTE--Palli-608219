package it.uniroma3.siw.calcio.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

// Specifica che l'annotazione può essere usata solo sopra agli attributi (campi) di una classe
@Target(ElementType.FIELD)
// Dice a Java di mantenere viva l'annotazione in memoria anche durante
// l'esecuzione
@Retention(RetentionPolicy.RUNTIME)
// Collega questa annotazione alla classe che contiene la logica reale di
// validazione
@Constraint(validatedBy = NotFutureYearValidator.class)
// Include l'annotazione nella generazione automatica della documentazione
// Javadoc
@Documented
public @interface NotFutureYear {
    String message() default "{team.futureYear}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
