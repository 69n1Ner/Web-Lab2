import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

public class ResponseBody extends IResponseBody {
    @JsonProperty("is_hit")
    private boolean isHit;
    private int x;
    private double y;
    private double r;

    public ResponseBody(){

    }

    public ResponseBody(boolean isHit, int x,double y, double r  ) {
        this.isHit = isHit;
        this.r = r;
        this.x = x;
        this.y = y;
    }

    public boolean isHit() {
        return isHit;
    }

    public void setIsHit(boolean isHit) {
        this.isHit = isHit;
    }

    public double getR() {
        return r;
    }

    public void setR(double r) {
        this.r = r;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }


}
