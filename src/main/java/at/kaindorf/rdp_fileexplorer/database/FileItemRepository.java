package at.kaindorf.rdp_fileexplorer.database;

import at.kaindorf.rdp_fileexplorer.pojos.FileItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileItemRepository extends JpaRepository<FileItem, Long> {
}
