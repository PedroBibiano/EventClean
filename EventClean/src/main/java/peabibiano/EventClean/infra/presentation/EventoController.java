package peabibiano.EventClean.infra.presentation;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import peabibiano.EventClean.core.entities.Evento;
import peabibiano.EventClean.core.usecase.CriarEventoCase;
import peabibiano.EventClean.core.usecase.ListarEventoCase;
import peabibiano.EventClean.infra.dtos.EventoDto;
import peabibiano.EventClean.infra.dtos.IdentificadorRequest;
import peabibiano.EventClean.infra.gateway.EventoRepositoryGateway;
import peabibiano.EventClean.infra.mappers.EventoDtoMapper;
import peabibiano.EventClean.infra.persistence.EventoEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@RestController
@RequestMapping("api/v1")
@CrossOrigin(origins = "*")
public class EventoController {

    private static final Log log = LogFactory.getLog(EventoController.class);
    private final EventoRepositoryGateway eventoRepositoryGateway;
    private final EventoDtoMapper eventoDtoMapper;
    private final CriarEventoCase criarEventoCase;
    private final ListarEventoCase listarEventoCase;

    public EventoController(EventoRepositoryGateway eventoRepositoryGateway, EventoDtoMapper eventoDtoMapper, CriarEventoCase criarEventoCase, ListarEventoCase listarEventoCase) {
        this.eventoRepositoryGateway = eventoRepositoryGateway;
        this.eventoDtoMapper = eventoDtoMapper;
        this.criarEventoCase = criarEventoCase;
        this.listarEventoCase = listarEventoCase;
    }

    @PostMapping("/criarevento")
    public ResponseEntity<Map<String, Object>> criarevento(@RequestBody EventoDto eventoDto) {
        Evento novoEvento = criarEventoCase.execute(eventoDtoMapper.toDomain(eventoDto));
        Map<String, Object> response = new HashMap<>();
        response.put("Message:","evento cadastrado");
        response.put("dados do evento: ", eventoDtoMapper.toDto(novoEvento));
        return ResponseEntity.status(HttpStatus.CREATED).body(response); }

    @PostMapping("/buscar-por/identificador")
    public ResponseEntity<List<EventoDto>> buscarPorIdentificador(@RequestBody IdentificadorRequest request) {
        // Acessa o identificador de dentro do objeto do request
        List<Evento> eventDomainIdentificador = eventoRepositoryGateway.FiltroIdentificador(request.identificador());

        List<EventoDto> eventosDtos = eventDomainIdentificador.stream()
                .map(eventoDtoMapper::toDto)
                .toList();

        return ResponseEntity.ok(eventosDtos);
    }
    @GetMapping("/listareventos")
    public ResponseEntity<List<EventoDto>> Listareventos() {
        List<Evento> eventosDomain = eventoRepositoryGateway.ListarEventos();

        List<EventoDto> eventosDtos = eventosDomain.stream()
                .map(eventoDtoMapper::toDto)
                .toList();
        return ResponseEntity.ok(eventosDtos);
    }
    @DeleteMapping("/deletarevento/{id}")
    public ResponseEntity<Void> Deletarevento(@PathVariable Long id) {
        eventoRepositoryGateway.deletarEvento(id);
        return ResponseEntity.ok().build();
    }
}
