package peabibiano.EventClean.infra.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ControllerExceptionHandler
{

    @ExceptionHandler(IdentificadorEventoException.class)
    public ResponseEntity<String> handledIdentificadorEventoException(IdentificadorEventoException e) {
        Map<String, String> response = new HashMap<>();
        response.put("Error:","Erro ao criar evento");
        response.put("dados do Erro:  ", "Identificador do evento ja esta em uso" );
        return new ResponseEntity(response, HttpStatus.CONFLICT);
    }


}
