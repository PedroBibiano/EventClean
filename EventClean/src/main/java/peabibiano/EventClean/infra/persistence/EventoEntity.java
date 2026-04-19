package peabibiano.EventClean.infra.persistence;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import peabibiano.EventClean.core.enums.tipoDeEvento;

import java.time.LocalDateTime;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Eventos")
public class EventoEntity {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   private String nome;
   private String descricao;
   private LocalDateTime dataInicio;
   private LocalDateTime dataFim;
   private String identificador;
   private String organizador;
   private int capacidade;
   @Enumerated(EnumType.STRING)
   private tipoDeEvento tipo;
   private String localEvento;

}

