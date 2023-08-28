package google.drive.domain;

import google.drive.domain.*;
import google.drive.infra.AbstractEvent;
import java.time.LocalDate;
import java.util.*;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class DashboardDisplayed extends AbstractEvent {

    public DashboardDisplayed(FileStatus aggregate) {
        super(aggregate);
    }

    public DashboardDisplayed() {
        super();
    }
}
//>>> DDD / Domain Event
