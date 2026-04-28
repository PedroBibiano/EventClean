package peabibiano.EventClean.infra.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import peabibiano.EventClean.core.gateway.EventoGateway;
import peabibiano.EventClean.core.usecase.CriarEventoCase;
import peabibiano.EventClean.core.usecase.CriarEventoCaseImpl;

@Configuration
public class BeanConfig {

    @Bean
    public CriarEventoCase criarEventoCase(EventoGateway eventoGateway) {
        return new CriarEventoCaseImpl(eventoGateway);
    }
}
