package peabibiano.EventClean.core.usecase;

import peabibiano.EventClean.core.entities.Evento;
import peabibiano.EventClean.core.gateway.EventoGateway;

public class DeletarEventoCaseImpl implements DeletarEventoCase{
    private final EventoGateway eventoGateway;

    public DeletarEventoCaseImpl(EventoGateway eventoGateway) {
        this.eventoGateway = eventoGateway;
    }


    public void execute(Long id) {

    }
}
