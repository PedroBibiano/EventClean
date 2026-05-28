package peabibiano.EventClean.infra.gateway;

import jakarta.persistence.Id;
import org.springframework.stereotype.Component;
import peabibiano.EventClean.core.entities.Evento;
import peabibiano.EventClean.core.gateway.EventoGateway;
import peabibiano.EventClean.infra.mappers.EventoEntityMapper;
import peabibiano.EventClean.infra.persistence.EventoEntity;
import peabibiano.EventClean.infra.persistence.EventoRepository;

import java.util.List;

@Component
public class EventoRepositoryGateway implements EventoGateway {

    private final EventoRepository eventoRepository;
    private final EventoEntityMapper eventoEntityMapper;

    public EventoRepositoryGateway(EventoRepository eventoRepository, EventoEntityMapper eventoEntityMapper) {
        this.eventoRepository = eventoRepository;
        this.eventoEntityMapper = eventoEntityMapper;
    }


    @Override
    public Evento criarEvento(Evento evento) {
        EventoEntity eventoEntity = eventoEntityMapper.toEntity(evento);
        EventoEntity novoEvento = eventoRepository.saveAndFlush(eventoEntity);
        return eventoEntityMapper.toDomain(novoEvento);
    }

    @Override
    public List<Evento> ListarEventos() {
        List<EventoEntity> listaEventos = eventoRepository.findAll();
        return listaEventos.stream().map(eventoEntityMapper::toDomain).toList();
    }

    @Override
    public void deletarEvento(Long id) {
        eventoRepository.deleteById(id);
    }
}
