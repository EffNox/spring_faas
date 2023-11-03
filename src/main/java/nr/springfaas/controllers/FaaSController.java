package nr.springfaas.controllers;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Configuration
@Component
public class FaaSController {

    @Bean
    Function<String, String> function() {
        return in -> {
            return "Hola " + in;
        };
    }

    @Bean
    Consumer<?> consumer() {
        return in -> {
            System.out.println("Entrada " + in);
        };
    }

    @Bean
    Supplier<?> supply() {
        return () -> {
            return "Salida sin consumir nada";
        };
    }

    /*  */

    @Bean
    Function<Map<String, Object>, String> mapjson() {
        return input -> {
            return "Hola " + input.get("a") + " " + ((List<?>) input.get("b")).size();
        };
    }

    @Bean
    Function<PersonaEntity, String> mapobject() {
        return input -> {
            return "Hola " + input.name() + " " + input.lst().size();
        };
    }

    @Bean
    Function<Message<PersonaEntity>, String> mapmessage() {
        return input -> {
            return "Headers: " + input.getHeaders() + "\nPayload: " + input.getPayload() + "\n";
        };
    }

}

record PersonaEntity(String name, List<Integer> lst) {}