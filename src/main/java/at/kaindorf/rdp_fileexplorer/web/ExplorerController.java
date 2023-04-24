package at.kaindorf.rdp_fileexplorer.web;

import at.kaindorf.rdp_fileexplorer.database.DirectoryRepository;
import at.kaindorf.rdp_fileexplorer.database.FileItemRepository;
import at.kaindorf.rdp_fileexplorer.database.LinkRepository;
import at.kaindorf.rdp_fileexplorer.pojos.Directory;
import at.kaindorf.rdp_fileexplorer.pojos.FileObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/explorer")
public class ExplorerController {
    private final DirectoryRepository directoryRepo;
    private final FileItemRepository fileItemRepo;
    private final LinkRepository linkRepo;

    public ExplorerController(DirectoryRepository directoryRepo, FileItemRepository fileItemRepo, LinkRepository linkRepo) {
        this.directoryRepo = directoryRepo;
        this.fileItemRepo = fileItemRepo;
        this.linkRepo = linkRepo;
    }

    @GetMapping
    public List<FileObject> getDirContent(@RequestParam String dir) {
        log.debug("dir: " + dir);
        Directory directory = directoryRepo.getDirectoryByName(dir);
        return directory.getContent();

    }

}
