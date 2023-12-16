package rs.wepublishlaws.cryptoservice.domain.exception;

public class QRCodeCreationException extends RuntimeException{
    public QRCodeCreationException(final String message, final Throwable cause){
        super(message, cause);
    }
}
