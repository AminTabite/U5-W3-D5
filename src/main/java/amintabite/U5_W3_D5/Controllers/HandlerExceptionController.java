package amintabite.U5_W3_D5.Controllers;

import amintabite.U5_W3_D5.Exceptions.BadRequestException;
import amintabite.U5_W3_D5.Exceptions.NotFoundException;
import amintabite.U5_W3_D5.Exceptions.ValidationsException;
import amintabite.U5_W3_D5.Payloads.ErrorsPayload;
import jakarta.validation.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;

public class HandlerExceptionController {



    @ExceptionHandler(ValidationException.class)

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorsPayload handleValidationErrors(ValidationsException ex) {
        return new ErrorsPayload(ex.getMessage(), LocalDateTime.now());
    }





    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorsPayload handleBadeRequest(BadRequestException ex){
        return new ErrorsPayload(ex.getMessage(), LocalDateTime.now());
    }



    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorsPayload handleNotFouns(NotFoundException ex){
        return new ErrorsPayload(ex.getMessage(), LocalDateTime.now());

    }


    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public ErrorsPayload handleServerError(Exception ex){

        ex.printStackTrace();
        return new ErrorsPayload("Errore generico", LocalDateTime.now());
    }


}
