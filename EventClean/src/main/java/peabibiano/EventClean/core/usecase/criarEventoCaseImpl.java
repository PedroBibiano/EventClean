package peabibiano.EventClean.core.usecase;

import peabibiano.EventClean.core.entities.Evento;

public class criarEventoCaseImpl implements criarEventoCase {

    @Override
    public Evento execute(Evento evento) {
        return evento;
    }

}
