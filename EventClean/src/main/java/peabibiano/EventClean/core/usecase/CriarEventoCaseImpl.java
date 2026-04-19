package peabibiano.EventClean.core.usecase;

import peabibiano.EventClean.core.entities.Evento;
import peabibiano.EventClean.core.gateway.EventoGateway;

public class CriarEventoCaseImpl implements CriarEventoCase {

    private final EventoGateway eventoGateway;
    public CriarEventoCaseImpl(EventoGateway eventoGateway) {
        this.eventoGateway = eventoGateway;
    }

    @Override
    public Evento execute(Evento evento) {
        return evento;
    }

}
