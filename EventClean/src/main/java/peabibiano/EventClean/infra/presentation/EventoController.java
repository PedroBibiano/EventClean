package peabibiano.EventClean.infra.presentation;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import peabibiano.EventClean.core.entities.Evento;
import peabibiano.EventClean.core.usecase.CriarEventoCase;
import peabibiano.EventClean.infra.dtos.EventoDto;
import peabibiano.EventClean.infra.gateway.EventoRepositoryGateway;
import peabibiano.EventClean.infra.mappers.EventoDtoMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/")
@CrossOrigin(origins = "*")
public class EventoController {

    private final EventoRepositoryGateway eventoRepositoryGateway;
    private final EventoDtoMapper eventoDtoMapper;
    private final CriarEventoCase criarEventoCase;

    public EventoController(EventoRepositoryGateway eventoRepositoryGateway, EventoDtoMapper eventoDtoMapper, CriarEventoCase criarEventoCase) {
        this.eventoRepositoryGateway = eventoRepositoryGateway;
        this.eventoDtoMapper = eventoDtoMapper;
        this.criarEventoCase = criarEventoCase;
    }

    @PostMapping("criarevento")
    public ResponseEntity<Map<String, Object>> criarevento(@RequestBody EventoDto eventoDto) {
        Evento novoEvento = criarEventoCase.execute(eventoDtoMapper.toDomain(eventoDto));
        Map<String, Object> response = new HashMap<>();
        response.put("Message:","evento cadastrado");
        response.put("dados do evento: ",  eventoDtoMapper.toDto(novoEvento));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("listareventos")
    public ResponseEntity<List<EventoDto>> Listareventos() {
        List<Evento> eventosDomain = eventoRepositoryGateway.ListarEventos();

        List<EventoDto> eventosDtos = eventosDomain.stream()
                .map(eventoDtoMapper::toDto)
                .toList();
        return ResponseEntity.ok(eventosDtos);
    }
    @DeleteMapping("deletarevento/{id}")
    public ResponseEntity<Void> Deletarevento(@PathVariable Long id) {
        eventoRepositoryGateway.deletarEvento(id);
        return ResponseEntity.ok().build();
    }
}
