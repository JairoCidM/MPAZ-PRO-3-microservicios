package cl.duoc.mpaz.contenidos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Microservicio de contenidos: objetivos de aprendizaje y preguntas (puerto 8081)
@SpringBootApplication
public class MsContenidosApplication {
    public static void main(String[] args) {
        SpringApplication.run(MsContenidosApplication.class, args);
    }
}
