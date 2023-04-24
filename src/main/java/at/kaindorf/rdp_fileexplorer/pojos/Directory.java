package at.kaindorf.rdp_fileexplorer.pojos;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@DiscriminatorValue("DIR")
public class Directory extends FileObject {
    @OneToMany(mappedBy = "parent")
    private List<FileObject> content;
}
