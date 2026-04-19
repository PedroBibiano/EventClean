package peabibiano.EventClean.infra.dtos;

import lombok.*;
import org.springframework.stereotype.Component;
import peabibiano.EventClean.core.enums.tipoDeEvento;
import java.time.LocalDateTime;

public record EventoDto(
     Long id,
     String nome,
     String descricao,
     LocalDateTime dataInicio,
     LocalDateTime dataFim,
     String identificador,
     String organizador,
     int capacidade,
     tipoDeEvento tipo,
     String localEvento){

}
