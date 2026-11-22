import com.fastcgi.FCGIInterface;
import tools.jackson.databind.json.JsonMapper;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.HashMap;

import static java.lang.System.nanoTime;

public class Main {
    public static final JsonMapper mapper = new JsonMapper();

    public static void main(String[] args) {
        long startTime = nanoTime();
        Instant date = Instant.now();
        IResponseBody responseBody;
        int status;

        FCGIInterface fcgiInterface = new FCGIInterface();
        while (fcgiInterface.FCGIaccept() >= 0) {
            String method = FCGIInterface.request.params.getProperty("REQUEST_METHOD");
            if ("POST".equals(method)) {
                try {
                    String requestBody = readRequestBody();
                    RequestBody request = mapper.readValue(requestBody, RequestBody.class);
                    HashMap<String, Number> map = HitChecker.validate(request.x(), request.y(), request.r());
                    int x = (Integer) map.get("x");
                    double y = (Double) map.get("y");
                    double r = (Double) map.get("r");
                    responseBody = new ResponseBody(HitChecker.isHit(x, y, r), x, y, r);
                    status = 200;

                } catch (IOException ex) {
                    responseBody = new ResponseBodyError("failed to read request body");
                    status = 400;

                } catch (ValidateException e) {
                    responseBody = new ResponseBodyError(e.getMessage());
                    status = e.getStatus();
                }

                responseBody.setDate(date);
                responseBody.setWorkingTime(nanoTime() - startTime);
            }else {
                responseBody = new ResponseBodyError(method + " method prohibited");
                status = 405;
            }

            send(new Response(status,responseBody.toJsonString()));

        }
    }

    private static void send(Response response) {
        String stringResponse = """
                Status: %d
                Content-Type: %s
                Content-Length: %d
                
                %s
                """.formatted(response.getStatus()
                , response.getContentType()
                , response.getContentLength()
                , response.getBody()
        );

        System.out.println(stringResponse);
        System.out.flush();
    }

    private static String readRequestBody() throws IOException {
        FCGIInterface.request.inStream.fill();

        int contentLen = FCGIInterface.request.inStream.available();
        ByteBuffer buffer = ByteBuffer.allocate(contentLen);
        int readBytes = FCGIInterface.request.inStream.read(buffer.array(), 0, contentLen);

        byte[] requestBodyRaw = new byte[readBytes];
        buffer.get(requestBodyRaw);
        buffer.clear();

        return new String(requestBodyRaw, StandardCharsets.UTF_8);
    }


}
