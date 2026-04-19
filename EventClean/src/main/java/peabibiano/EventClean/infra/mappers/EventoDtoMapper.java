package peabibiano.EventClean.infra.mappers;

import org.springframework.stereotype.Component;
import peabibiano.EventClean.core.entities.Evento;
import peabibiano.EventClean.infra.dtos.EventoDto;
import peabibiano.EventClean.infra.persistence.EventoEntity;

import java.time.LocalDateTime;

@Component
public class EventoMapper {
    public EventoEntity map(EventoDto eventoDto){
        EventoEntity evento = new EventoEntity();
        evento.setNome(eventoDto.getNome());
        evento.setId(evento.getId());
        evento.setDescricao(eventoDto.getDescricao());
        evento.setDataInicio(LocalDateTime.parse(eventoDto.getDataInicio()));
        evento.setDataFim(LocalDateTime.parse(eventoDto.getDataFim()));
        evento.setIdentificador(eventoDto.getIdentificador());
        evento.setLocalEvento(eventoDto.getLocalEvento());
        evento.setOrganizador(eventoDto.getOrganizador());
        evento.setCapacidade(eventoDto.getCapacidade());
        evento.setTipo(eventoDto.getTipo());
        return evento;
    }
    public EventoDto map(EventoEntity evento){
        EventoDto eventoDto = new EventoDto();
        eventoDto.setNome(evento.getNome());
        eventoDto.setId(evento.getId());
        eventoDto.setDescricao(evento.getDescricao());
        eventoDto.setDataInicio(evento.getDataInicio().toString());
        eventoDto.setDataFim(evento.getDataFim().toString());
        eventoDto.setIdentificador(evento.getIdentificador());
        eventoDto.setLocalEvento(evento.getLocalEvento());
        eventoDto.setOrganizador(evento.getOrganizador());
    return eventoDto;
    }
}
