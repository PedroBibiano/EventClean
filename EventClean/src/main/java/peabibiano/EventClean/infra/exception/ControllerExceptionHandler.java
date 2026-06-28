package peabibiano.EventClean.infra.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(IdentificadorEventoException.class)
    public ResponseEntity<Map<String, String>> handleIdentificadorEventoException(IdentificadorEventoException e) {
        Map<String, String> response = new HashMap<>();
        // Ajustado o espaçamento e os nomes das chaves para um padrão mais limpo
        response.put("error", "Erro ao criar evento");
        response.put("details", "Identificador do evento ja esta em uso");
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ClassNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleClassNotFoundException(ClassNotFoundException e) {
        Map<String, String> response = new HashMap<>();
        response.put("error", "Erro ao buscar evento");
        response.put("details", "The requested event was not found, check the identifier again");
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND); // Alterado para NOT_FOUND (404) porque faz mais sentido para "não encontrado"
    }
}