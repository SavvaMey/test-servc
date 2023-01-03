package serVC.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import serVC.dto.DtoException;
import serVC.exceptions.SortException;

import java.sql.SQLException;

@ControllerAdvice
public class ExceptionController {

    Logger logger = LoggerFactory.getLogger(ExceptionController.class);

    @ExceptionHandler(SortException.class)
    public ResponseEntity<Object> handleSort(SortException ex) {
        logger.error(ex.getMessage(), ex);
        return new ResponseEntity<>(new DtoException("parameters for sorting are not correctly passed"), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SQLException.class)
    public ResponseEntity<Object> sqlHandle(Exception ex) {
        logger.error(ex.getMessage(), ex);
        return new ResponseEntity<>(new DtoException("ex in a DB"), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
