package serVC.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import serVC.dto.DtoException;
import serVC.exceptions.AuthorException;
import serVC.exceptions.SortException;

import java.sql.SQLException;

@ControllerAdvice
public class ExceptionController {

    Logger logger = LoggerFactory.getLogger(ExceptionController.class);

    @ExceptionHandler(SortException.class)
    public ResponseEntity<DtoException> handleSort(SortException ex) {
        String exceptionResponse = String.format("parameters for sorting are not correctly passed: %s\n",
                ex.getMessage());
        logger.error(ex.getMessage(), ex);
        return new ResponseEntity<>(new DtoException(exceptionResponse),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SQLException.class)
    public ResponseEntity<DtoException> sqlHandle(SQLException ex) {
        String exceptionResponse = String.format("ex in a DB: %s", ex.getMessage());
        logger.error(ex.getMessage(), ex);
        return new ResponseEntity<>(new DtoException(exceptionResponse), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(AuthorException.class)
    public ResponseEntity<DtoException> sqlHandle(AuthorException ex) {
        String exceptionResponse = String.format("No such author: %s", ex.getMessage());
        logger.error(ex.getMessage(), ex);
        return new ResponseEntity<>(new DtoException(exceptionResponse), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public final ResponseEntity<DtoException> handleConstraintViolationExceptions(
            MethodArgumentNotValidException ex) {
        String exceptionResponse = String.format("Invalid input parameters: %s", ex.getMessage());
        logger.error(ex.getMessage(), ex);
        return new ResponseEntity<>(new DtoException(exceptionResponse), HttpStatus.BAD_REQUEST);
    }
}
