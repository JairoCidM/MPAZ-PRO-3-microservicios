package cl.duoc.mpaz.resultados.repository;

import cl.duoc.mpaz.resultados.model.Intento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IntentoRepository extends JpaRepository<Intento, Long> {
    List<Intento> findAllByOrderByFechaDesc();

    List<Intento> findByObjetivoIdOrderByFechaDesc(Long objetivoId);

    List<Intento> findByRutEstudianteAndObjetivoIdOrderByFechaDesc(String rutEstudiante, Long objetivoId);
}
