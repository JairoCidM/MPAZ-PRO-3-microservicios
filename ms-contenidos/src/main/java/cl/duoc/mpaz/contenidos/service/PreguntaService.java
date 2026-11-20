package cl.duoc.mpaz.contenidos.service;

import cl.duoc.mpaz.contenidos.exception.RecursoNoEncontradoException;
import cl.duoc.mpaz.contenidos.model.Pregunta;
import cl.duoc.mpaz.contenidos.repository.PreguntaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

// Lógica de negocio de las preguntas (CRUD que usa el docente desde la app)
@Service
public class PreguntaService {

    private final PreguntaRepository repository;
    private final ObjetivoService objetivoService;

    public PreguntaService(PreguntaRepository repository, ObjetivoService objetivoService) {
        this.repository = repository;
        this.objetivoService = objetivoService;
    }

    public List<Pregunta> listar(Long objetivoId) {
        if (objetivoId == null) {
            return repository.findAllByOrderByIdAsc();
        }
        return repository.findByObjetivoIdOrderByNivelAscIdAsc(objetivoId);
    }

    public Pregunta buscar(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("No existe la pregunta " + id));
    }

    public Pregunta crear(Pregunta pregunta) {
        objetivoService.buscar(pregunta.getObjetivoId()); // El objetivo debe existir
        pregunta.setId(null);
        ajustarNivel(pregunta);
        return repository.save(pregunta);
    }

    public Pregunta actualizar(Long id, Pregunta datos) {
        Pregunta actual = buscar(id);
        objetivoService.buscar(datos.getObjetivoId());
        actual.setObjetivoId(datos.getObjetivoId());
        actual.setTipo(datos.getTipo());
        actual.setNivel(datos.getNivel());
        actual.setEnunciado(datos.getEnunciado());
        actual.setOpcionA(datos.getOpcionA());
        actual.setOpcionB(datos.getOpcionB());
        actual.setOpcionC(datos.getOpcionC());
        actual.setCorrecta(datos.getCorrecta());
        ajustarNivel(actual);
        return repository.save(actual);
    }

    public void eliminar(Long id) {
        repository.delete(buscar(id));
    }

    // Las preguntas de prueba siempre tienen nivel 0; los desafíos, nivel 1 a 3
    private void ajustarNivel(Pregunta pregunta) {
        if ("PRUEBA".equals(pregunta.getTipo())) {
            pregunta.setNivel(0);
        } else if (pregunta.getNivel() == null || pregunta.getNivel() < 1) {
            pregunta.setNivel(1);
        }
    }
}
