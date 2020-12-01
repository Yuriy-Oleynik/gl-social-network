package glsocialnetwork.exceptions;

public class SpringGlSocialNetworkException extends RuntimeException {
    public SpringGlSocialNetworkException(String exMessage, Exception exception) {
        super(exMessage, exception);
    }

    public SpringGlSocialNetworkException(String exMessage) {
        super(exMessage);
    }
}
