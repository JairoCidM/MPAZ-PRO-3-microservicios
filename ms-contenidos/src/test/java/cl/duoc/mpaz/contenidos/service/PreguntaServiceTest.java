package cl.duoc.mpaz.contenidos.service;

import cl.duoc.mpaz.contenidos.exception.RecursoNoEncontradoException;
import cl.duoc.mpaz.contenidos.model.Objetivo;
import cl.duoc.mpaz.contenidos.model.Pregunta;
import cl.duoc.mpaz.contenidos.repository.ObjetivoRepository;
import cl.duoc.mpaz.contenidos.repository.PreguntaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

// Pruebas unitarias de la lógica de objetivos y preguntas (sin base de datos)
class PreguntaServiceTest {

    private ObjetivoRepository objetivoRepository;
    private PreguntaRepository preguntaRepository;
    private ObjetivoService objetivoService;
    private PreguntaService preguntaService;

    @BeforeEach
    void preparar() {
        objetivoRepository = mock(ObjetivoRepository.class);
        preguntaRepository = mock(PreguntaRepository.class);
        objetivoService = new ObjetivoService(objetivoRepository);
        preguntaService = new PreguntaService(preguntaRepository, objetivoService);
        when(preguntaRepository.save(any(Pregunta.class))).thenAnswer(i -> i.getArgument(0));
        when(objetivoRepository.save(any(Objetivo.class))).thenAnswer(i -> i.getArgument(0));
    }

    @Test
    void crearPreguntaDePruebaDejaNivelCero() {
        when(objetivoRepository.findById(1L)).thenReturn(Optional.of(new Objetivo()));
        Pregunta pregunta = new Pregunta(1L, "PRUEBA", 2, "¿Cuánto es 2 + 2?", "3", "4", "5", 1);

        Pregunta creada = preguntaService.crear(pregunta);

        assertEquals(0, creada.getNivel());
        verify(preguntaRepository).save(pregunta);
    }

    @Test
    void crearPreguntaConObjetivoInexistenteFalla() {
        when(objetivoRepository.findById(9L)).thenReturn(Optional.empty());
        Pregunta pregunta = new Pregunta(9L, "DESAFIO", 1, "Pregunta", "a", "b", "c", 0);

        assertThrows(RecursoNoEncontradoException.class, () -> preguntaService.crear(pregunta));
    }

    @Test
    void listarPorObjetivoUsaElFiltro() {
        when(preguntaRepository.findByObjetivoIdOrderByNivelAscIdAsc(2L)).thenReturn(List.of(new Pregunta()));

        assertEquals(1, preguntaService.listar(2L).size());
    }

    @Test
    void objetivoObservadoQuedaSinAsignar() {
        Objetivo actual = new Objetivo("CIENCIAS", "Unidad 1", "OA", "Descripción", "APROBADO", true);
        when(objetivoRepository.findById(3L)).thenReturn(Optional.of(actual));
        Objetivo cambios = new Objetivo("CIENCIAS", "Unidad 1", "OA", "Descripción", "OBSERVADO", true);
        cambios.setMaxIntentos(2);

        Objetivo resultado = objetivoService.actualizar(3L, cambios);

        assertEquals("OBSERVADO", resultado.getEstado());
        assertFalse(resultado.getAsignado());
    }
}
