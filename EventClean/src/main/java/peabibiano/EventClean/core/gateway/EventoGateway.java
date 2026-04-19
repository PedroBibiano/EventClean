package peabibiano.EventClean.core.gateway;

import peabibiano.EventClean.core.entities.Evento;

public interface EventoGateway {
    Evento criarEvento(Evento evento);
}
