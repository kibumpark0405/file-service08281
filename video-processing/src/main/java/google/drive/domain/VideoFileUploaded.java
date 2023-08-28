package google.drive.domain;

import google.drive.domain.*;
import google.drive.infra.AbstractEvent;
import java.time.LocalDate;
import java.util.*;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class VideoFileUploaded extends AbstractEvent {

    public VideoFileUploaded(Video aggregate) {
        super(aggregate);
    }

    public VideoFileUploaded() {
        super();
    }
}
//>>> DDD / Domain Event
