package peabibiano.EventClean.infra.gateway;

import org.springframework.stereotype.Component;
import peabibiano.EventClean.core.entities.Evento;
import peabibiano.EventClean.core.gateway.EventoGateway;
import peabibiano.EventClean.infra.exception.IdentificadorEventoException;
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
        if(eventoRepository.existsByIdentificador(evento.identificador())) {
        throw new IdentificadorEventoException("evento com esse identificador: " + evento.identificador() + " ja esta em uso");
        }
        EventoEntity eventoEntity = eventoEntityMapper.toEntity(evento);
        EventoEntity novoEvento = eventoRepository.saveAndFlush(eventoEntity);
        System.out.println("evento criado com sucesso");
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
        System.out.println("evento deletado com sucesso");

    }

    @Override
    public List<Evento> FiltroIdentificador(String identificador) {

        return eventoRepository.findByIdentificadorIgnoreCase(identificador).stream()
                .map(eventoEntityMapper::toDomain)
                .toList();
    }

    public boolean existsByIdentificador(String identificador) {
        return eventoRepository.findAll().stream()
                .anyMatch(evento -> evento.getIdentificador().equalsIgnoreCase(identificador));
    }
}
