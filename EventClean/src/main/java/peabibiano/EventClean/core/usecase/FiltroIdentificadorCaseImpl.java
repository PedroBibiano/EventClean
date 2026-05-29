package peabibiano.EventClean.core.usecase;

import peabibiano.EventClean.core.entities.Evento;
import peabibiano.EventClean.core.gateway.EventoGateway;

import java.util.List;

public class FiltroIdentificadorCaseImpl implements FiltroIdentificadorCase {

    private final EventoGateway eventoGateway;

    public FiltroIdentificadorCaseImpl(EventoGateway eventoGateway) {
        this.eventoGateway = eventoGateway;
    }

    @Override
    public List<Evento> execute(String identificador) {
        return eventoGateway.FiltroIdentificador(identificador);
    }
}