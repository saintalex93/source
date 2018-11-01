package br.com.neolog.ecommerce.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class ExceptionHandlerForValidArguments
    extends
        ResponseEntityExceptionHandler
{

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
        final MethodArgumentNotValidException ex,
        final HttpHeaders headers,
        final HttpStatus status,
        final WebRequest request )
    {
        final ErrorDetails errorDetails = new ErrorDetails( bindingResultToString( ex ), request.getDescription( false ),
            ex.getClass().getName(), HttpStatus.BAD_REQUEST.name(), HttpStatus.BAD_REQUEST.value() );
        return new ResponseEntity<Object>( errorDetails, HttpStatus.BAD_REQUEST );
    }

    private String bindingResultToString(
        final MethodArgumentNotValidException e )
    {
        final StringBuilder sb = new StringBuilder( "Validation failed. Error(s) details: " );
        for( final ObjectError error : e.getBindingResult().getAllErrors() ) {
            final String[] field = error.getCodes();
            sb.append( "[" ).append( field[ 0 ] ).append( ": " ).append( error.getDefaultMessage() ).append( "]; " );
        }
        return sb.toString();
    }

}
