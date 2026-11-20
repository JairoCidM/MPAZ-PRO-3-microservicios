package cl.duoc.mpaz.contenidos.repository;

import cl.duoc.mpaz.contenidos.model.Pregunta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PreguntaRepository extends JpaRepository<Pregunta, Long> {
    List<Pregunta> findAllByOrderByIdAsc();

    List<Pregunta> findByObjetivoIdOrderByNivelAscIdAsc(Long objetivoId);
}
