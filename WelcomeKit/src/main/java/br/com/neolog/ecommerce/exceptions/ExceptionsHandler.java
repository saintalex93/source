package br.com.neolog.ecommerce.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
@RestController
public class ExceptionsHandler

{

    @ExceptionHandler( StockProductRemoveWithQuantityException.class )
    public final ResponseEntity<ErrorDetails> handleProductStockQuantityExceptionException(
        final StockProductRemoveWithQuantityException exception,
        final WebRequest request )
    {
        final ErrorDetails errorDetails = new ErrorDetails( exception.getMessage(), request.getDescription( false ),
            exception.getClass().getName(), HttpStatus.BAD_REQUEST.name(), HttpStatus.BAD_REQUEST.value() );
        return new ResponseEntity<ErrorDetails>( errorDetails, HttpStatus.BAD_REQUEST );
    }

    @ExceptionHandler( ProductDuplicatedCodeException.class )
    public final ResponseEntity<ErrorDetails> handleProductDuplicatedCodeException(
        final ProductDuplicatedCodeException exception,
        final WebRequest request )
    {
        final ErrorDetails errorDetails = new ErrorDetails( exception.getMessage(), request.getDescription( false ),
            exception.getClass().getName(), HttpStatus.BAD_REQUEST.name(), HttpStatus.BAD_REQUEST.value() );
        return new ResponseEntity<ErrorDetails>( errorDetails, HttpStatus.BAD_REQUEST );
    }

    @ExceptionHandler( ProductNotFoundException.class )
    public final ResponseEntity<ErrorDetails> handleProductNotFoundException(
        final ProductNotFoundException exception,
        final WebRequest request )
    {
        final ErrorDetails errorDetails = new ErrorDetails( exception.getMessage(), request.getDescription( false ),
            exception.getClass().getName(), HttpStatus.NOT_FOUND.name(), HttpStatus.NOT_FOUND.value() );
        return new ResponseEntity<ErrorDetails>( errorDetails, HttpStatus.NOT_FOUND );
    }

    @ExceptionHandler( CategoryNotFoundException.class )
    public final ResponseEntity<ErrorDetails> handleCategoryNotFoundException(
        final CategoryNotFoundException exception,
        final WebRequest request )
    {
        final ErrorDetails errorDetails = new ErrorDetails( exception.getMessage(), request.getDescription( false ),
            exception.getClass().getName(), HttpStatus.NOT_FOUND.name(), HttpStatus.NOT_FOUND.value() );
        return new ResponseEntity<ErrorDetails>( errorDetails, HttpStatus.NOT_FOUND );
    }

    @ExceptionHandler( CategoryFillException.class )
    public final ResponseEntity<ErrorDetails> handleCategoryFillException(
        final CategoryFillException exception,
        final WebRequest request )
    {
        final ErrorDetails errorDetails = new ErrorDetails( exception.getMessage(), request.getDescription( false ),
            exception.getClass().getName(), HttpStatus.BAD_REQUEST.name(), HttpStatus.BAD_REQUEST.value() );
        return new ResponseEntity<ErrorDetails>( errorDetails, HttpStatus.BAD_REQUEST );
    }

    @ExceptionHandler( CategoryDuplicatedCodeException.class )
    public final ResponseEntity<ErrorDetails> handleCategoryDuplicatedCodeException(
        final CategoryDuplicatedCodeException exception,
        final WebRequest request )
    {
        final ErrorDetails errorDetails = new ErrorDetails( exception.getMessage(), request.getDescription( false ),
            exception.getClass().getName(), HttpStatus.BAD_REQUEST.name(), HttpStatus.BAD_REQUEST.value() );
        return new ResponseEntity<ErrorDetails>( errorDetails, HttpStatus.BAD_REQUEST );
    }

    @ExceptionHandler( CategoryDuplicatedNameException.class )
    public final ResponseEntity<ErrorDetails> handleCategoryDuplicatedNameException(
        final CategoryDuplicatedNameException exception,
        final WebRequest request )
    {
        final ErrorDetails errorDetails = new ErrorDetails( exception.getMessage(), request.getDescription( false ),
            exception.getClass().getName(), HttpStatus.BAD_REQUEST.name(), HttpStatus.BAD_REQUEST.value() );
        return new ResponseEntity<ErrorDetails>( errorDetails, HttpStatus.BAD_REQUEST );
    }

    @ExceptionHandler( CategoryRemoveWithProductException.class )
    public final ResponseEntity<ErrorDetails> handleCategoryRemoveWithProductException(
        final CategoryRemoveWithProductException exception,
        final WebRequest request )
    {
        final ErrorDetails errorDetails = new ErrorDetails( exception.getMessage(), request.getDescription( false ),
            exception.getClass().getName(), HttpStatus.BAD_REQUEST.name(), HttpStatus.BAD_REQUEST.value() );
        return new ResponseEntity<ErrorDetails>( errorDetails, HttpStatus.BAD_REQUEST );
    }

    @ExceptionHandler( StockNotFoundException.class )
    public final ResponseEntity<ErrorDetails> handleStockNotFoundException(
        final StockNotFoundException exception,
        final WebRequest request )
    {
        final ErrorDetails errorDetails = new ErrorDetails( exception.getMessage(), request.getDescription( false ),
            exception.getClass().getName(), HttpStatus.NOT_FOUND.name(), HttpStatus.NOT_FOUND.value() );
        return new ResponseEntity<ErrorDetails>( errorDetails, HttpStatus.NOT_FOUND );
    }

    @ExceptionHandler( StockFillException.class )
    public final ResponseEntity<ErrorDetails> handleStockFillException(
        final StockFillException exception,
        final WebRequest request )
    {
        final ErrorDetails errorDetails = new ErrorDetails( exception.getMessage(), request.getDescription( false ),
            exception.getClass().getName(), HttpStatus.BAD_REQUEST.name(), HttpStatus.BAD_REQUEST.value() );
        return new ResponseEntity<ErrorDetails>( errorDetails, HttpStatus.BAD_REQUEST );
    }

    @ExceptionHandler( StockDuplicatedProductException.class )
    public final ResponseEntity<ErrorDetails> handleStockDuplicatedProductException(
        final StockDuplicatedProductException exception,
        final WebRequest request )
    {
        final ErrorDetails errorDetails = new ErrorDetails( exception.getMessage(), request.getDescription( false ),
            exception.getClass().getName(), HttpStatus.BAD_REQUEST.name(), HttpStatus.BAD_REQUEST.value() );
        return new ResponseEntity<ErrorDetails>( errorDetails, HttpStatus.BAD_REQUEST );
    }

    @ExceptionHandler( CustomerNotFoundException.class )
    public final ResponseEntity<ErrorDetails> handleCustomerNotFoundException(
        final CustomerNotFoundException exception,
        final WebRequest request )
    {
        final ErrorDetails errorDetails = new ErrorDetails( exception.getMessage(), request.getDescription( false ),
            exception.getClass().getName(), HttpStatus.NOT_FOUND.name(), HttpStatus.NOT_FOUND.value() );
        return new ResponseEntity<ErrorDetails>( errorDetails, HttpStatus.NOT_FOUND );
    }

    @ExceptionHandler( CustomerInactiveException.class )
    public final ResponseEntity<ErrorDetails> handleCustomerInactiveException(
        final CustomerInactiveException exception,
        final WebRequest request )
    {
        final ErrorDetails errorDetails = new ErrorDetails( exception.getMessage(), request.getDescription( false ),
            exception.getClass().getName(), HttpStatus.BAD_REQUEST.name(), HttpStatus.BAD_REQUEST.value() );
        return new ResponseEntity<ErrorDetails>( errorDetails, HttpStatus.BAD_REQUEST );
    }

    @ExceptionHandler( CustomerFillException.class )
    public final ResponseEntity<ErrorDetails> handleCustomerFillException(
        final CustomerFillException exception,
        final WebRequest request )
    {
        final ErrorDetails errorDetails = new ErrorDetails( exception.getMessage(), request.getDescription( false ),
            exception.getClass().getName(), HttpStatus.BAD_REQUEST.name(), HttpStatus.BAD_REQUEST.value() );
        return new ResponseEntity<ErrorDetails>( errorDetails, HttpStatus.BAD_REQUEST );
    }

    @ExceptionHandler( CustomerPasswordException.class )
    public final ResponseEntity<ErrorDetails> handleCustomerPasswordException(
        final CustomerPasswordException exception,
        final WebRequest request )
    {
        final ErrorDetails errorDetails = new ErrorDetails( exception.getMessage(), request.getDescription( false ),
            exception.getClass().getName(), HttpStatus.BAD_REQUEST.name(), HttpStatus.BAD_REQUEST.value() );
        return new ResponseEntity<ErrorDetails>( errorDetails, HttpStatus.BAD_REQUEST );
    }

    @ExceptionHandler( CustomerDuplicatedEmailException.class )
    public final ResponseEntity<ErrorDetails> handleCustomerDuplicatedEmailException(
        final CustomerDuplicatedEmailException exception,
        final WebRequest request )
    {
        final ErrorDetails errorDetails = new ErrorDetails( exception.getMessage(), request.getDescription( false ),
            exception.getClass().getName(), HttpStatus.BAD_REQUEST.name(), HttpStatus.BAD_REQUEST.value() );
        return new ResponseEntity<ErrorDetails>( errorDetails, HttpStatus.BAD_REQUEST );
    }

    @ExceptionHandler( CartItemStockException.class )
    public final ResponseEntity<ErrorDetails> handleCartItemStockException(
        final CartItemStockException exception,
        final WebRequest request )
    {
        final ErrorDetails errorDetails = new ErrorDetails( exception.getMessage(), request.getDescription( false ),
            exception.getClass().getName(), HttpStatus.BAD_REQUEST.name(), HttpStatus.BAD_REQUEST.value() );
        return new ResponseEntity<ErrorDetails>( errorDetails, HttpStatus.BAD_REQUEST );
    }

    @ExceptionHandler( CartItemNotFoundException.class )
    public final ResponseEntity<ErrorDetails> handleCartItemNotFoundException(
        final CartItemNotFoundException exception,
        final WebRequest request )
    {
        final ErrorDetails errorDetails = new ErrorDetails( exception.getMessage(), request.getDescription( false ),
            exception.getClass().getName(), HttpStatus.NOT_FOUND.name(), HttpStatus.NOT_FOUND.value() );
        return new ResponseEntity<ErrorDetails>( errorDetails, HttpStatus.NOT_FOUND );
    }

    @ExceptionHandler( CartNotFoundException.class )
    public final ResponseEntity<ErrorDetails> handleCartNotFoundException(
        final CartNotFoundException exception,
        final WebRequest request )
    {
        final ErrorDetails errorDetails = new ErrorDetails( exception.getMessage(), request.getDescription( false ),
            exception.getClass().getName(), HttpStatus.NOT_FOUND.name(), HttpStatus.NOT_FOUND.value() );
        return new ResponseEntity<ErrorDetails>( errorDetails, HttpStatus.NOT_FOUND );
    }

    @ExceptionHandler( CartCustomerException.class )
    public final ResponseEntity<ErrorDetails> handleCartItemCustomerException(
        final CartCustomerException exception,
        final WebRequest request )
    {
        final ErrorDetails errorDetails = new ErrorDetails( exception.getMessage(), request.getDescription( false ),
            exception.getClass().getName(), HttpStatus.BAD_REQUEST.name(), HttpStatus.BAD_REQUEST.value() );
        return new ResponseEntity<ErrorDetails>( errorDetails, HttpStatus.BAD_REQUEST );
    }

    @ExceptionHandler( SessionNotFoundException.class )
    public final ResponseEntity<ErrorDetails> handleSessionNotFoundException(
        final SessionNotFoundException exception,
        final WebRequest request )
    {
        final ErrorDetails errorDetails = new ErrorDetails( exception.getMessage(), request.getDescription( false ),
            exception.getClass().getName(), HttpStatus.NOT_FOUND.name(), HttpStatus.NOT_FOUND.value() );
        return new ResponseEntity<ErrorDetails>( errorDetails, HttpStatus.NOT_FOUND );
    }

    @ExceptionHandler( CartItemRemoveException.class )
    public ResponseEntity<ErrorDetails> handleCartItemRemoveException(
        final CartItemRemoveException exception,
        final WebRequest request )
    {
        final ErrorDetails errorDetails = new ErrorDetails( exception.getMessage(), request.getDescription( false ),
            exception.getClass().getName(), HttpStatus.BAD_REQUEST.name(), HttpStatus.BAD_REQUEST.value() );
        return new ResponseEntity<>( errorDetails, HttpStatus.BAD_REQUEST );
    }

    @ExceptionHandler( OptimizationInvalidValueException.class )
    public ResponseEntity<ErrorDetails> handleOptimizationInvalidValueException(
        final OptimizationInvalidValueException exception,
        final WebRequest request )
    {
        final ErrorDetails errorDetails = new ErrorDetails( exception.getMessage(), request.getDescription( false ),
            exception.getClass().getName(), HttpStatus.BAD_REQUEST.name(), HttpStatus.BAD_REQUEST.value() );
        return new ResponseEntity<>( errorDetails, HttpStatus.BAD_REQUEST );
    }

    @ExceptionHandler( ProblemNullException.class )
    public ResponseEntity<ErrorDetails> handleProblemNullException(
        final ProblemNullException exception,
        final WebRequest request )
    {
        final ErrorDetails errorDetails = new ErrorDetails( exception.getMessage(), request.getDescription( false ),
            exception.getClass().getName(), HttpStatus.BAD_REQUEST.name(), HttpStatus.BAD_REQUEST.value() );
        return new ResponseEntity<>( errorDetails, HttpStatus.BAD_REQUEST );
    }

}