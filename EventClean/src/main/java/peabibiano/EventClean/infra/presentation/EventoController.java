package peabibiano.EventClean.infra.presentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import peabibiano.EventClean.core.entities.Evento;
import peabibiano.EventClean.core.usecase.CriarEventoCase;
import peabibiano.EventClean.core.usecase.ListarEventoCase;
import peabibiano.EventClean.infra.dtos.EventoDto;
import peabibiano.EventClean.infra.gateway.EventoRepositoryGateway;
import peabibiano.EventClean.infra.mappers.EventoDtoMapper;
import peabibiano.EventClean.infra.persistence.EventoEntity;

@RestController
@RequestMapping("api/v1/")
public class EventoController {

    private final EventoRepositoryGateway eventoRepositoryGateway;
    private final EventoDtoMapper eventoDtoMapper;
    private final CriarEventoCase criarEvento;

    public EventoController(EventoRepositoryGateway eventoRepositoryGateway, EventoDtoMapper eventoDtoMapper, CriarEventoCase criarEvento) {
        this.eventoRepositoryGateway = eventoRepositoryGateway;
        this.eventoDtoMapper = eventoDtoMapper;
        this.criarEvento = criarEvento;
    }

    @PostMapping("criarevento")
    public EventoDto Criarevento(@RequestBody EventoDto eventoDto) {
        Evento novoEvento = criarEventoCase.execute(eventoDtoMapper.toDomain(eventoDto));
        return eventoDtoMapper.toDto(novoEvento);
    }
    @GetMapping("listareventos")
    public String Listareventos(){
        return null;
    }

}
