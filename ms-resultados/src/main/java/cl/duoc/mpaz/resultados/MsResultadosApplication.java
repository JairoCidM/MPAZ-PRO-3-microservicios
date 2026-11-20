package cl.duoc.mpaz.resultados;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Microservicio de resultados: intentos de la prueba final y sus respuestas (puerto 8082)
@SpringBootApplication
public class MsResultadosApplication {
    public static void main(String[] args) {
        SpringApplication.run(MsResultadosApplication.class, args);
    }
}
