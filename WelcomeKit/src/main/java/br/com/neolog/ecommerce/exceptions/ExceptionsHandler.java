package br.com.neolog.ecommerce.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class ExceptionsHandler
    extends
        ResponseEntityExceptionHandler
{

    @ExceptionHandler( StockProductRemoveWithQuantityException.class )
    public final ResponseEntity<ErrorDetails> handleProductStockQuantityExceptionException(
        final StockProductRemoveWithQuantityException ex,
        final WebRequest request )
    {
        final ErrorDetails errorDetails = new ErrorDetails( ex.getMessage(), request.getDescription( false ),
            ex.getClass().getName(), HttpStatus.BAD_REQUEST.name(), HttpStatus.BAD_REQUEST.value() );
        return new ResponseEntity<ErrorDetails>( errorDetails, HttpStatus.BAD_REQUEST );
    }

    @ExceptionHandler( ProductDuplicatedCodeException.class )
    public final ResponseEntity<ErrorDetails> handleProductDuplicatedCodeException(
        final ProductDuplicatedCodeException ex,
        final WebRequest request )
    {
        final ErrorDetails errorDetails = new ErrorDetails( ex.getMessage(), request.getDescription( false ),
            ex.getClass().getName(), HttpStatus.BAD_REQUEST.name(), HttpStatus.BAD_REQUEST.value() );
        return new ResponseEntity<ErrorDetails>( errorDetails, HttpStatus.BAD_REQUEST );
    }

    @ExceptionHandler( ProductNotFoundException.class )
    public final ResponseEntity<ErrorDetails> handleProductNotFoundException(
        final ProductNotFoundException ex,
        final WebRequest request )
    {
        final ErrorDetails errorDetails = new ErrorDetails( ex.getMessage(), request.getDescription( false ),
            ex.getClass().getName(), HttpStatus.NOT_FOUND.name(), HttpStatus.NOT_FOUND.value() );
        return new ResponseEntity<ErrorDetails>( errorDetails, HttpStatus.NOT_FOUND );
    }

    @ExceptionHandler( CategoryNotFoundException.class )
    public final ResponseEntity<ErrorDetails> handleCategoryNotFoundException(
        final CategoryNotFoundException ex,
        final WebRequest request )
    {
        final ErrorDetails errorDetails = new ErrorDetails( ex.getMessage(), request.getDescription( false ),
            ex.getClass().getName(), HttpStatus.NOT_FOUND.name(), HttpStatus.NOT_FOUND.value() );
        return new ResponseEntity<ErrorDetails>( errorDetails, HttpStatus.NOT_FOUND );
    }

    @ExceptionHandler( CategoryFillException.class )
    public final ResponseEntity<ErrorDetails> handleCategoryFillException(
        final CategoryFillException ex,
        final WebRequest request )
    {
        final ErrorDetails errorDetails = new ErrorDetails( ex.getMessage(), request.getDescription( false ),
            ex.getClass().getName(), HttpStatus.BAD_REQUEST.name(), HttpStatus.BAD_REQUEST.value() );
        return new ResponseEntity<ErrorDetails>( errorDetails, HttpStatus.BAD_REQUEST );
    }

    @ExceptionHandler( CategoryDuplicatedCodeException.class )
    public final ResponseEntity<ErrorDetails> handleCategoryDuplicatedCodeException(
        final CategoryDuplicatedCodeException ex,
        final WebRequest request )
    {
        final ErrorDetails errorDetails = new ErrorDetails( ex.getMessage(), request.getDescription( false ),
            ex.getClass().getName(), HttpStatus.BAD_REQUEST.name(), HttpStatus.BAD_REQUEST.value() );
        return new ResponseEntity<ErrorDetails>( errorDetails, HttpStatus.BAD_REQUEST );
    }

    @ExceptionHandler( CategoryDuplicatedNameException.class )
    public final ResponseEntity<ErrorDetails> handleCategoryDuplicatedNameException(
        final CategoryDuplicatedNameException ex,
        final WebRequest request )
    {
        final ErrorDetails errorDetails = new ErrorDetails( ex.getMessage(), request.getDescription( false ),
            ex.getClass().getName(), HttpStatus.BAD_REQUEST.name(), HttpStatus.BAD_REQUEST.value() );
        return new ResponseEntity<ErrorDetails>( errorDetails, HttpStatus.BAD_REQUEST );
    }

    @ExceptionHandler( CategoryRemoveWithProductException.class )
    public final ResponseEntity<ErrorDetails> handleCategoryRemoveWithProductException(
        final CategoryRemoveWithProductException ex,
        final WebRequest request )
    {
        final ErrorDetails errorDetails = new ErrorDetails( ex.getMessage(), request.getDescription( false ),
            ex.getClass().getName(), HttpStatus.BAD_REQUEST.name(), HttpStatus.BAD_REQUEST.value() );
        return new ResponseEntity<ErrorDetails>( errorDetails, HttpStatus.BAD_REQUEST );
    }

    @ExceptionHandler( StockNotFoundException.class )
    public final ResponseEntity<ErrorDetails> handleStockNotFoundException(
        final StockNotFoundException ex,
        final WebRequest request )
    {
        final ErrorDetails errorDetails = new ErrorDetails( ex.getMessage(), request.getDescription( false ),
            ex.getClass().getName(), HttpStatus.NOT_FOUND.name(), HttpStatus.NOT_FOUND.value() );
        return new ResponseEntity<ErrorDetails>( errorDetails, HttpStatus.NOT_FOUND );
    }

    @ExceptionHandler( StockFillException.class )
    public final ResponseEntity<ErrorDetails> handleStockFillException(
        final StockFillException ex,
        final WebRequest request )
    {
        final ErrorDetails errorDetails = new ErrorDetails( ex.getMessage(), request.getDescription( false ),
            ex.getClass().getName(), HttpStatus.BAD_REQUEST.name(), HttpStatus.BAD_REQUEST.value() );
        return new ResponseEntity<ErrorDetails>( errorDetails, HttpStatus.BAD_REQUEST );
    }

    @ExceptionHandler( StockDuplicatedProductException.class )
    public final ResponseEntity<ErrorDetails> handleStockDuplicatedProductException(
        final StockDuplicatedProductException ex,
        final WebRequest request )
    {
        final ErrorDetails errorDetails = new ErrorDetails( ex.getMessage(), request.getDescription( false ),
            ex.getClass().getName(), HttpStatus.BAD_REQUEST.name(), HttpStatus.BAD_REQUEST.value() );
        return new ResponseEntity<ErrorDetails>( errorDetails, HttpStatus.BAD_REQUEST );
    }

    @ExceptionHandler( CustomerNotFoundException.class )
    public final ResponseEntity<ErrorDetails> handleCustomerNotFoundException(
        final CustomerNotFoundException ex,
        final WebRequest request )
    {
        final ErrorDetails errorDetails = new ErrorDetails( ex.getMessage(), request.getDescription( false ),
            ex.getClass().getName(), HttpStatus.NOT_FOUND.name(), HttpStatus.NOT_FOUND.value() );
        return new ResponseEntity<ErrorDetails>( errorDetails, HttpStatus.NOT_FOUND );
    }

    @ExceptionHandler( CustomerInactiveException.class )
    public final ResponseEntity<ErrorDetails> handleCustomerInactiveException(
        final CustomerInactiveException ex,
        final WebRequest request )
    {
        final ErrorDetails errorDetails = new ErrorDetails( ex.getMessage(), request.getDescription( false ),
            ex.getClass().getName(), HttpStatus.BAD_REQUEST.name(), HttpStatus.BAD_REQUEST.value() );
        return new ResponseEntity<ErrorDetails>( errorDetails, HttpStatus.BAD_REQUEST );
    }

    @ExceptionHandler( CustomerFillException.class )
    public final ResponseEntity<ErrorDetails> handleCustomerFillException(
        final CustomerFillException ex,
        final WebRequest request )
    {
        final ErrorDetails errorDetails = new ErrorDetails( ex.getMessage(), request.getDescription( false ),
            ex.getClass().getName(), HttpStatus.BAD_REQUEST.name(), HttpStatus.BAD_REQUEST.value() );
        return new ResponseEntity<ErrorDetails>( errorDetails, HttpStatus.BAD_REQUEST );
    }

    @ExceptionHandler( Exception.class )
    public final ResponseEntity<ErrorDetails> handleGeneralException(
        final Exception ex,
        final WebRequest request )
    {
        final ErrorDetails errorDetails = new ErrorDetails( ex.getMessage(), request.getDescription( false ),
            ex.getClass().getName(), HttpStatus.BAD_REQUEST.name(), HttpStatus.BAD_REQUEST.value() );
        return new ResponseEntity<ErrorDetails>( errorDetails, HttpStatus.BAD_REQUEST );
    }

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