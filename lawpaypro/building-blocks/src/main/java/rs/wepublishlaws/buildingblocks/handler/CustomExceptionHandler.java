package rs.wepublishlaws.buildingblocks.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import rs.wepublishlaws.buildingblocks.handler.exceptions.MessageNullException;

@ControllerAdvice
public class CustomExceptionHandler {

        @ExceptionHandler({MessageNullException.class})
        @ResponseStatus(HttpStatus.BAD_REQUEST)
        public ErrorResponse handleMessageNullException(MessageNullException ex) {
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setMessage("Username must be unique");
            return errorResponse;
        }

}
