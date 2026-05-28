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
@Table(name = "eventos")
public class EventoEntity {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   private String nome;

   private String descricao;

   @Column(name = "data_inicio")
   private LocalDateTime dataInicio;

   @Column(name = "data_fim")
   private LocalDateTime dataFim;

   @Column(unique = true)
   private String identificador;

   private String organizador;

   private int capacidade;

   @Enumerated(EnumType.STRING)
   private tipoDeEvento tipo;

   @Column(name = "local_evento")
   private String localEvento;
}