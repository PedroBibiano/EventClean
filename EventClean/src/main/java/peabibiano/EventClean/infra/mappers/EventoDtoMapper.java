package peabibiano.EventClean.infra.mappers;

import org.springframework.stereotype.Component;
import peabibiano.EventClean.core.entities.Evento;
import peabibiano.EventClean.infra.dtos.EventoDto;

@Component
public class EventoDtoMapper {

    public EventoDto toDto(Evento evento){
        return new EventoDto(
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

    public Evento toDomain(EventoDto dto){
        return new Evento(
                dto.id(),
                dto.nome(),
                dto.descricao(),
                dto.dataInicio(),
                dto.dataFim(),
                dto.identificador(),
                dto.organizador(),
                dto.capacidade(),
                dto.tipo(),
                dto.localEvento());
    }

}
