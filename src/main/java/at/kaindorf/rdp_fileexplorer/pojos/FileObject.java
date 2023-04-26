package at.kaindorf.rdp_fileexplorer.pojos;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type")
public class FileObject {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NonNull
    private String name;
    @NonNull
    private LocalDateTime lastModified;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "parent")
    private FileObject parent;
}
