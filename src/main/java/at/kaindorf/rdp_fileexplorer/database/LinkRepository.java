package at.kaindorf.rdp_fileexplorer.database;

import at.kaindorf.rdp_fileexplorer.pojos.Link;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LinkRepository extends JpaRepository<Link, Long> {
}
