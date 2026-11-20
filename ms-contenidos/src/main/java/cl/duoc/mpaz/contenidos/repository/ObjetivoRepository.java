package cl.duoc.mpaz.contenidos.repository;

import cl.duoc.mpaz.contenidos.model.Objetivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ObjetivoRepository extends JpaRepository<Objetivo, Long> {
    List<Objetivo> findAllByOrderByIdAsc();
}
