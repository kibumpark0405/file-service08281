package google.drive.domain;

import google.drive.DriveApplication;
import google.drive.domain.FileIndexed;
import google.drive.domain.FileUploaded;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "File_table")
@Data
//<<< DDD / Aggregate Root
public class File {

    @Id
    private String fileName;

    private String fileSize;

    private Boolean uploaded;

    private Boolean indexed;

    private String videoUrl;

    @PostPersist
    public void onPostPersist() {
        FileUploaded fileUploaded = new FileUploaded(this);
        fileUploaded.publishAfterCommit();

        FileIndexed fileIndexed = new FileIndexed(this);
        fileIndexed.publishAfterCommit();
    }

    @PrePersist
    public void onPrePersist() {}

    public static FileRepository repository() {
        FileRepository fileRepository = DriveApplication.applicationContext.getBean(
            FileRepository.class
        );
        return fileRepository;
    }
}
//>>> DDD / Aggregate Root
