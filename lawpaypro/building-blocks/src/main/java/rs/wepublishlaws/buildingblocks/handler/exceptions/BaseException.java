package rs.wepublishlaws.buildingblocks.handler.exceptions;

public abstract class BaseException extends RuntimeException {
    protected BaseException(final String message){
        super(message);
    }

}
