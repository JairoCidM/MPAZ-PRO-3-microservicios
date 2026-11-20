package cl.duoc.mpaz.resultados.service;

import cl.duoc.mpaz.resultados.exception.RecursoNoEncontradoException;
import cl.duoc.mpaz.resultados.model.Intento;
import cl.duoc.mpaz.resultados.model.Respuesta;
import cl.duoc.mpaz.resultados.repository.IntentoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

// Pruebas unitarias del cálculo de puntaje y la búsqueda de intentos
class IntentoServiceTest {

    private IntentoRepository repository;
    private IntentoService service;

    @BeforeEach
    void preparar() {
        repository = mock(IntentoRepository.class);
        service = new IntentoService(repository);
        when(repository.save(any(Intento.class))).thenAnswer(i -> i.getArgument(0));
    }

    @Test
    void registrarCalculaElPuntaje() {
        Intento intento = new Intento("25418732-1", 1L, null, List.of(
                new Respuesta(4L, 0, true),
                new Respuesta(5L, 2, false),
                new Respuesta(6L, 1, true)
        ));

        Intento guardado = service.registrar(intento);

        assertEquals(2, guardado.getCorrectas());
        assertEquals(3, guardado.getTotal());
        assertNotNull(guardado.getFecha());
    }

    @Test
    void listarPorEstudianteYObjetivo() {
        when(repository.findByRutEstudianteAndObjetivoIdOrderByFechaDesc("25418732-1", 1L))
                .thenReturn(List.of(new Intento()));

        assertEquals(1, service.listar(1L, "25418732-1").size());
    }

    @Test
    void buscarIntentoInexistenteFalla() {
        when(repository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(RecursoNoEncontradoException.class, () -> service.buscar(99L));
    }

    @Test
    void eliminarBorraElIntento() {
        Intento intento = new Intento();
        when(repository.findById(1L)).thenReturn(Optional.of(intento));

        service.eliminar(1L);

        verify(repository).delete(intento);
    }
}
