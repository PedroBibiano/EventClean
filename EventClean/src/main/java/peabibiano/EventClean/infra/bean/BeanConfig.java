package peabibiano.EventClean.infra.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import peabibiano.EventClean.core.gateway.EventoGateway;
import peabibiano.EventClean.core.usecase.CriarEventoCase;
import peabibiano.EventClean.core.usecase.CriarEventoCaseImpl;
import peabibiano.EventClean.infra.gateway.EventoRepositoryGateway;

@Configuration
public class EventoConfig {

    @Bean
    public CriarEventoCase criarEventoCase(EventoGateway eventoGateway) {
        return new CriarEventoCaseImpl(eventoGateway);
    }
}
