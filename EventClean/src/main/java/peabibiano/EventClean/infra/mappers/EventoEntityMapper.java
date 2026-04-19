package peabibiano.EventClean.infra.mappers;

import org.springframework.stereotype.Component;
import peabibiano.EventClean.core.entities.Evento;
import peabibiano.EventClean.infra.persistence.EventoEntity;

@Component
public class EventoEntityMapper {
    public EventoEntity toEntity(Evento evento) {
    return new EventoEntity(
            evento.id(),
            evento.nome(),
            evento.descricao(),
            evento.dataInicio(),
            evento.dataFim(),
            evento.identificador(),
            evento.organizador(),
            evento.capacidade(),
            evento.tipo(),
            evento.localEvento()
    );
    }
    public Evento toDomain(EventoEntity entity) {
        return new Evento(
                entity.getId(),
                entity.getNome(),
                entity.getDescricao(),
                entity.getDataInicio(),
                entity.getDataFim(),
                entity.getIdentificador(),
                entity.getOrganizador(),
                entity.getCapacidade(),
                entity.getTipo(),
                entity.getLocalEvento()
        );
    }
}
