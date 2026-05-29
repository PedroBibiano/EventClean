package peabibiano.EventClean.core.usecase;

import peabibiano.EventClean.core.entities.Evento;
import java.util.List;

public interface FiltroIdentificadorCase {
    List<Evento> execute(String identificador);
}