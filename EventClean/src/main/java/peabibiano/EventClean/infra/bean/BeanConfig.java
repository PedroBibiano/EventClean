package peabibiano.EventClean.infra.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import peabibiano.EventClean.core.entities.Evento;
import peabibiano.EventClean.core.gateway.EventoGateway;
import peabibiano.EventClean.core.usecase.*;

@Configuration
public class BeanConfig {

    @Bean
    public CriarEventoCase criarEventoCase(EventoGateway eventoGateway) {
        return new CriarEventoCaseImpl(eventoGateway);
    }

    @Bean
    public ListarEventoCase listarEventoCase(EventoGateway eventoGateway) {
        return new ListarEventoCaseImpl(eventoGateway);
    }

    @Bean
    public DeletarEventoCase deletarEventoCase(EventoGateway eventoGateway) {
        return new DeletarEventoCaseImpl(eventoGateway);
    }
    @Bean
    public FiltroIdentificadorCase filtroIdentificadorCase(EventoGateway eventoGateway) {
        return new FiltroIdentificadorCaseImpl(eventoGateway);
    }
}
