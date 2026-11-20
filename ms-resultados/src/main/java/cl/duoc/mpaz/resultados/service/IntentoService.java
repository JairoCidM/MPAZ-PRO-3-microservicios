package cl.duoc.mpaz.resultados.service;

import cl.duoc.mpaz.resultados.exception.RecursoNoEncontradoException;
import cl.duoc.mpaz.resultados.model.Intento;
import cl.duoc.mpaz.resultados.model.Respuesta;
import cl.duoc.mpaz.resultados.repository.IntentoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

// Lógica de negocio de los intentos de la prueba final
@Service
public class IntentoService {

    private final IntentoRepository repository;

    public IntentoService(IntentoRepository repository) {
        this.repository = repository;
    }

    // Filtra por objetivo y, si se indica, por estudiante
    @Transactional(readOnly = true)
    public List<Intento> listar(Long objetivoId, String rut) {
        if (objetivoId != null && rut != null) {
            return repository.findByRutEstudianteAndObjetivoIdOrderByFechaDesc(rut, objetivoId);
        }
        if (objetivoId != null) {
            return repository.findByObjetivoIdOrderByFechaDesc(objetivoId);
        }
        return repository.findAllByOrderByFechaDesc();
    }

    @Transactional(readOnly = true)
    public Intento buscar(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("No existe el intento " + id));
    }

    // El puntaje se calcula en el servidor a partir de las respuestas
    @Transactional
    public Intento registrar(Intento intento) {
        intento.setId(null);
        intento.getRespuestas().forEach(respuesta -> respuesta.setId(null));
        long correctas = intento.getRespuestas().stream()
                .filter(respuesta -> Boolean.TRUE.equals(respuesta.getEsCorrecta()))
                .count();
        intento.setCorrectas((int) correctas);
        intento.setTotal(intento.getRespuestas().size());
        if (intento.getFecha() == null) {
            intento.setFecha(System.currentTimeMillis());
        }
        return repository.save(intento);
    }

    // El docente puede reiniciar los intentos de un estudiante
    @Transactional
    public void eliminar(Long id) {
        repository.delete(buscar(id));
    }

    public List<Respuesta> respuestas(Long id) {
        return buscar(id).getRespuestas();
    }
}
