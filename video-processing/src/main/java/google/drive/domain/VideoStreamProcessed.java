package google.drive.domain;

import google.drive.domain.*;
import google.drive.infra.AbstractEvent;
import java.time.LocalDate;
import java.util.*;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class VideoStreamProcessed extends AbstractEvent {

    public VideoStreamProcessed(Video aggregate) {
        super(aggregate);
    }

    public VideoStreamProcessed() {
        super();
    }
}
//>>> DDD / Domain Event
