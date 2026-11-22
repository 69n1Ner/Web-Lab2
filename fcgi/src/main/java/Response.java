import java.nio.charset.StandardCharsets;

public class Response {
    private final String contentType;
    private int  status;
    private int contentLength;
    private String body;

    public Response(int status, String body) {
        this.contentType = "application/json";
        this.body = body;
        this.contentLength = body.getBytes(StandardCharsets.UTF_8).length;
        this.status = status;
    }

    public void setContentLength(String string) {
        this.contentLength = string.getBytes(StandardCharsets.UTF_8).length;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getContentLength() {
        return contentLength;
    }

    public String getContentType() {
        return contentType;
    }


    public int getStatus() {
        return status;
    }

    public String getBody() {
        return body;
    }

}
