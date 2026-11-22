import java.time.Instant;

public class ResponseBodyError extends IResponseBody {
    private String error;

    public ResponseBodyError(String error){
        this.error = error;
    }

    public ResponseBodyError(long date, String error, Instant workingTime) {
        this.error = error;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

}
