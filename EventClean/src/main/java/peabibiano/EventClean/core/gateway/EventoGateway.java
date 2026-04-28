package peabibiano.EventClean.core.gateway;
import peabibiano.EventClean.core.entities.Evento;
import java.util.List;

public interface EventoGateway {
    Evento criarEvento(Evento evento);
    List<Evento> ListarEventos();
    void deletarEvento(Long id);
}
