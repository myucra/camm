package com.bisa.capacitacion.atm.spring;

import com.bisa.commons.dsl.DomainException;
import com.bisa.commons.spring.rest.ErrorResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.log.LogFormatUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * @author Miguel A. Vega
 * @version 1.0: ControllerExceptionsHandler.java
 */
@ControllerAdvice
public class ControllerExceptionsHandler extends ResponseEntityExceptionHandler {
    private final Logger logger = LogManager.getLogger(getClass());

    @ExceptionHandler
    public ResponseEntity handleDomainException(DomainException x, WebRequest wr) {
        logger.error("Ha ocurrido un error inesperado", x);
        return ErrorResponse.unprocessableEntity(x).toResponseEntity();
    }

    /*@ExceptionHandler
    public ResponseEntity handleDomainException(PedidoNoEncontradoException x, WebRequest wr) {
        return ErrorResponse.notFound(x).toResponseEntity();
    }*/

    @ExceptionHandler(Exception.class)
    public ResponseEntity handleAnyException(Exception x, WebRequest wr) {
        logger.error("Ha ocurrido un error inesperado", x);
        return ErrorResponse.internalServerError(x).toResponseEntity();
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        //AbstractHandlerExceptionResolver#resolveException
        String msg = "Resolved [" + LogFormatUtils.formatValue(ex, -1, true) + "]";
        logger.warn(msg);
//        logger.warn("Bad request caused by:", ex);
        return super.handleHttpMessageNotReadable(ex, headers, status, request);
    }

}
