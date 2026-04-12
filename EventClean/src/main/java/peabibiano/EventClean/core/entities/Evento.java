package peabibiano.EventClean.core.entities;

import peabibiano.EventClean.core.enums.tipoDeEvento;

import java.time.LocalDateTime;

public record Evento(
        Long id,
        String nome,
        String descricao,
        LocalDateTime dataInicio,
        LocalDateTime dataFim,
        String identificador,
        String local,
        String organizador,
        tipoDeEvento tipo

        ) {

}
