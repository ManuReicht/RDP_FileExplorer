package at.kaindorf.rdp_fileexplorer.pojos;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "file")
@DiscriminatorValue("FILE")
public class FileItem extends FileObject {
    private Long size;

    public FileItem(@NonNull String name, @NonNull LocalDateTime lastModified, Long size) {
        super(name, lastModified);
        this.size = size;
    }
}
