package cl.duoc.mpaz.contenidos.config;

import cl.duoc.mpaz.contenidos.model.Objetivo;
import cl.duoc.mpaz.contenidos.model.Pregunta;
import cl.duoc.mpaz.contenidos.repository.ObjetivoRepository;
import cl.duoc.mpaz.contenidos.repository.PreguntaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

// Carga datos ficticios la primera vez (si la base está vacía).
// El orden importa: los id quedan iguales a los de la app (objetivos 1 a 4, preguntas 1 a 24).
@Component
public class DatosIniciales implements CommandLineRunner {

    private final ObjetivoRepository objetivos;
    private final PreguntaRepository preguntas;

    public DatosIniciales(ObjetivoRepository objetivos, PreguntaRepository preguntas) {
        this.objetivos = objetivos;
        this.preguntas = preguntas;
    }

    @Override
    public void run(String... args) {
        if (objetivos.count() > 0) {
            return;
        }
        Objetivo lenguaje = objetivos.save(new Objetivo("LENGUAJE", "Unidad 1: El mundo de los cuentos", "Por definir",
                "Reconocer personajes, lugares y acciones de un cuento", "APROBADO", true));
        Objetivo matematica = objetivos.save(new Objetivo("MATEMATICA", "Unidad 1: Números hasta el 1.000", "Por definir",
                "Sumar y restar números hasta el 1.000", "APROBADO", true));
        Objetivo ciencias = objetivos.save(new Objetivo("CIENCIAS", "Unidad 1: Las plantas", "Por definir",
                "Reconocer qué necesitan las plantas para vivir", "PENDIENTE", false));
        Objetivo historia = objetivos.save(new Objetivo("HISTORIA", "Unidad 1: Ubicándonos en el mapa", "Por definir",
                "Usar los puntos cardinales para ubicar lugares", "PENDIENTE", false));

        Long l = lenguaje.getId();
        Long m = matematica.getId();
        Long c = ciencias.getId();
        Long h = historia.getId();

        preguntas.saveAll(List.of(
                // Lenguaje
                new Pregunta(l, "DESAFIO", 1, "¿Quién puede ser un personaje de un cuento?", "El perro Tito", "Una mesa", "El lunes", 0),
                new Pregunta(l, "DESAFIO", 2, "Ana corrió al parque. ¿Dónde ocurre?", "En la casa", "En el parque", "En la escuela", 1),
                new Pregunta(l, "DESAFIO", 3, "Ana buscó su pelota en el parque. ¿Qué hizo Ana?", "Durmió", "Cantó", "Buscó su pelota", 2),
                new Pregunta(l, "PRUEBA", 0, "¿Qué palabra nombra un lugar?", "bosque", "saltar", "feliz", 0),
                new Pregunta(l, "PRUEBA", 0, "El gato Lolo subió al techo. ¿Quién es el personaje?", "El techo", "El gato Lolo", "Subió", 1),
                new Pregunta(l, "PRUEBA", 0, "Pedro plantó un árbol. ¿Qué hizo Pedro?", "Comió", "Plantó un árbol", "Nadó", 1),
                // Matemática
                new Pregunta(m, "DESAFIO", 1, "¿Cuánto es 7 + 5?", "10", "12", "13", 1),
                new Pregunta(m, "DESAFIO", 2, "¿Cuánto es 40 + 30?", "70", "60", "700", 0),
                new Pregunta(m, "DESAFIO", 3, "¿Cuánto es 124 + 35?", "149", "159", "169", 1),
                new Pregunta(m, "PRUEBA", 0, "¿Cuánto es 15 - 6?", "9", "11", "8", 0),
                new Pregunta(m, "PRUEBA", 0, "¿Cuánto es 200 + 300?", "400", "500", "600", 1),
                new Pregunta(m, "PRUEBA", 0, "¿Cuánto es 350 - 120?", "230", "220", "130", 0),
                // Ciencias Naturales
                new Pregunta(c, "DESAFIO", 1, "¿Las plantas son seres vivos?", "Sí", "No", "Solo en verano", 0),
                new Pregunta(c, "DESAFIO", 2, "¿Qué necesita una planta para vivir?", "Agua y luz", "Solo arena", "Plástico", 0),
                new Pregunta(c, "DESAFIO", 3, "¿Por dónde absorbe agua una planta?", "Por las hojas", "Por la raíz", "Por los frutos", 1),
                new Pregunta(c, "PRUEBA", 0, "¿Qué parte de la planta está bajo la tierra?", "La flor", "La raíz", "La hoja", 1),
                new Pregunta(c, "PRUEBA", 0, "¿Qué le pasa a una planta sin luz?", "Crece más rápido", "Se debilita", "Da más flores", 1),
                new Pregunta(c, "PRUEBA", 0, "¿Qué parte de la planta forma las semillas?", "La flor", "La raíz", "El tallo", 0),
                // Historia
                new Pregunta(h, "DESAFIO", 1, "¿Cuántos puntos cardinales hay?", "Dos", "Cuatro", "Seis", 1),
                new Pregunta(h, "DESAFIO", 2, "¿Por dónde sale el sol?", "Por el este", "Por el oeste", "Por el sur", 0),
                new Pregunta(h, "DESAFIO", 3, "En la mayoría de los mapas, ¿dónde está el norte?", "Abajo", "Arriba", "A la izquierda", 1),
                new Pregunta(h, "PRUEBA", 0, "¿Por dónde se pone el sol?", "Por el oeste", "Por el norte", "Por el este", 0),
                new Pregunta(h, "PRUEBA", 0, "¿Cuál de estos es un punto cardinal?", "Derecha", "Sur", "Adelante", 1),
                new Pregunta(h, "PRUEBA", 0, "Si el norte está arriba, ¿dónde está el sur?", "A la derecha", "Arriba", "Abajo", 2)
        ));
    }
}
