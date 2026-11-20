package cl.duoc.mpaz.resultados.config;

import cl.duoc.mpaz.resultados.model.Intento;
import cl.duoc.mpaz.resultados.model.Respuesta;
import cl.duoc.mpaz.resultados.service.IntentoService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

// Resultados ficticios de 3 estudiantes de ejemplo, para que el informe del docente tenga datos.
// Las preguntas de prueba de Lenguaje tienen id 4, 5 y 6; las de Matemática, 10, 11 y 12.
@Component
public class DatosIniciales implements CommandLineRunner {

    private final IntentoService service;

    public DatosIniciales(IntentoService service) {
        this.service = service;
    }

    @Override
    public void run(String... args) {
        if (!service.listar(null, null).isEmpty()) {
            return;
        }
        long hoy = System.currentTimeMillis();
        service.registrar(intento("25377840-7", 1L, hoy, r(4, 0, true), r(5, 1, true), r(6, 1, true)));
        service.registrar(intento("25111222-3", 1L, hoy, r(4, 0, true), r(5, 0, false), r(6, 1, true)));
        service.registrar(intento("25333444-4", 1L, hoy, r(4, 0, true), r(5, 2, false), r(6, 0, false)));
        service.registrar(intento("25377840-7", 2L, hoy, r(10, 0, true), r(11, 1, true), r(12, 1, false)));
        service.registrar(intento("25111222-3", 2L, hoy, r(10, 0, true), r(11, 0, false), r(12, 2, false)));
        service.registrar(intento("25333444-4", 2L, hoy, r(10, 1, false), r(11, 1, true), r(12, 0, true)));
    }

    private Intento intento(String rut, Long objetivoId, long fecha, Respuesta... respuestas) {
        return new Intento(rut, objetivoId, fecha, List.of(respuestas));
    }

    private Respuesta r(long preguntaId, int elegida, boolean correcta) {
        return new Respuesta(preguntaId, elegida, correcta);
    }
}
