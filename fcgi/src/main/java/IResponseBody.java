import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

public abstract class IResponseBody {
    private Instant date;
    @JsonProperty("working_time")
    private long workingTime;

    public String toJsonString(){
        return Main.mapper.writeValueAsString(this);
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public void setWorkingTime(long workingTime) {
        this.workingTime = workingTime;
    }

    public Instant getDate() {
        return date;
    }

    public long getWorkingTime() {
        return workingTime;
    }
}
