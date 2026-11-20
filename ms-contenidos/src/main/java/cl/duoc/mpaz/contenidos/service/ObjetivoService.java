package cl.duoc.mpaz.contenidos.service;

import cl.duoc.mpaz.contenidos.exception.RecursoNoEncontradoException;
import cl.duoc.mpaz.contenidos.model.Objetivo;
import cl.duoc.mpaz.contenidos.repository.ObjetivoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

// Lógica de negocio de los objetivos de aprendizaje
@Service
public class ObjetivoService {

    private final ObjetivoRepository repository;

    public ObjetivoService(ObjetivoRepository repository) {
        this.repository = repository;
    }

    public List<Objetivo> listar() {
        return repository.findAllByOrderByIdAsc();
    }

    public Objetivo buscar(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("No existe el objetivo " + id));
    }

    public Objetivo crear(Objetivo objetivo) {
        objetivo.setId(null);
        return repository.save(objetivo);
    }

    // Lo usan UTP (aprobar u observar), el docente (asignar e intentos) y la validación del informe
    public Objetivo actualizar(Long id, Objetivo datos) {
        Objetivo actual = buscar(id);
        actual.setAsignatura(datos.getAsignatura());
        actual.setUnidad(datos.getUnidad());
        actual.setCodigo(datos.getCodigo());
        actual.setDescripcion(datos.getDescripcion());
        actual.setEstado(datos.getEstado());
        actual.setObservacion(datos.getObservacion());
        // Un objetivo con observaciones no puede quedar asignado a los estudiantes
        actual.setAsignado("OBSERVADO".equals(datos.getEstado()) ? false : datos.getAsignado());
        actual.setMaxIntentos(datos.getMaxIntentos());
        actual.setInformeValidado(datos.getInformeValidado());
        actual.setFechaValidacion(datos.getFechaValidacion());
        return repository.save(actual);
    }

    public void eliminar(Long id) {
        repository.delete(buscar(id));
    }
}
