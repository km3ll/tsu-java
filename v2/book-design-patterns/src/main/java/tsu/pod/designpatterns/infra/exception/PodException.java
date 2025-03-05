package tsu.pod.designpatterns.infra.exception;

public class PodException extends RuntimeException {
    public PodException(String message) {
        super(message);
    }
}
