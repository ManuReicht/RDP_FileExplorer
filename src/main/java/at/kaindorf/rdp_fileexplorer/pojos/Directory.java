package at.kaindorf.rdp_fileexplorer.pojos;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@DiscriminatorValue("DIR")
public class Directory extends FileObject {

    @OneToMany(mappedBy = "parent", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private List<FileObject> content = new LinkedList<>();

    public Directory(@NonNull String name, @NonNull LocalDateTime lastModified) {
        super(name, lastModified);
    }

    public void addFileObject(FileObject fileObject) {
        content.add(fileObject);
    }
}
