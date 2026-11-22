public class ValidateException extends Exception {
    private final int status;
    public ValidateException(String message,int status) {
        super(message);
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}
