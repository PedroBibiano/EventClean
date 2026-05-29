package peabibiano.EventClean.core.gateway;
import peabibiano.EventClean.core.entities.Evento;
import peabibiano.EventClean.infra.persistence.EventoEntity;

import java.util.List;

public interface EventoGateway {
    Evento criarEvento(Evento evento);
    List<Evento> ListarEventos();
    void deletarEvento(Long id);
    List<Evento> FiltroIdentificador(String identificador);
    }
