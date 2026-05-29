package peabibiano.EventClean.infra.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Arrays;
import java.util.List;

public interface EventoRepository extends JpaRepository<EventoEntity, Long> {
    boolean existsByIdentificador(String identificador);

    List<EventoEntity> findByIdentificadorIgnoreCase(String identificador);

}
