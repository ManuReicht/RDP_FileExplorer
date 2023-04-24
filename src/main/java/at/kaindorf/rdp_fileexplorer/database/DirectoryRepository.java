package at.kaindorf.rdp_fileexplorer.database;

import at.kaindorf.rdp_fileexplorer.pojos.Directory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DirectoryRepository extends JpaRepository<Directory, Long> {
    @Query("SELECT d FROM Directory d WHERE d.name = ?1")
    Directory getDirectoryByName(String name);
}
