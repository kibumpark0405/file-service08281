package google.drive.domain;

import google.drive.VideoProcessingApplication;
import google.drive.domain.VideoFileUploaded;
import google.drive.domain.VideoStreamProcessed;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Video_table")
@Data
//<<< DDD / Aggregate Root
public class Video {

    @Id
    private String fileName;

    private String videoUrl;

    @PostPersist
    public void onPostPersist() {
        VideoFileUploaded videoFileUploaded = new VideoFileUploaded(this);
        videoFileUploaded.publishAfterCommit();

        VideoStreamProcessed videoStreamProcessed = new VideoStreamProcessed(
            this
        );
        videoStreamProcessed.publishAfterCommit();
    }

    @PrePersist
    public void onPrePersist() {}

    public static VideoRepository repository() {
        VideoRepository videoRepository = VideoProcessingApplication.applicationContext.getBean(
            VideoRepository.class
        );
        return videoRepository;
    }
}
//>>> DDD / Aggregate Root
