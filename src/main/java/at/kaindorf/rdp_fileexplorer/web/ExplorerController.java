package at.kaindorf.rdp_fileexplorer.web;

import at.kaindorf.rdp_fileexplorer.database.DirectoryRepository;
import at.kaindorf.rdp_fileexplorer.database.FileItemRepository;
import at.kaindorf.rdp_fileexplorer.database.LinkRepository;
import at.kaindorf.rdp_fileexplorer.pojos.Directory;
import at.kaindorf.rdp_fileexplorer.pojos.FileObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/explorer")
@SessionAttributes({"dirPath"})
public class ExplorerController {
    private final DirectoryRepository directoryRepo;
    private final FileItemRepository fileItemRepo;
    private final LinkRepository linkRepo;

    public ExplorerController(DirectoryRepository directoryRepo, FileItemRepository fileItemRepo, LinkRepository linkRepo) {
        this.directoryRepo = directoryRepo;
        this.fileItemRepo = fileItemRepo;
        this.linkRepo = linkRepo;
    }

    @ModelAttribute
    public void initModel(Model model) {
        model.addAttribute("fileObject", new FileObject());
        model.addAttribute("content", new ArrayList<FileObject>());
    }

    @GetMapping
    public String getDirContent(@RequestParam(name = "dirName", defaultValue = "C:") String dirName, Model model) {
        Directory currentDir = directoryRepo.getDirectoryByName(dirName);
        updateModel(model, getDirPath(currentDir), currentDir.getContent());
        return "explorerView";
    }

    @PostMapping("/up")
    public String getUpDirContent(@RequestParam(name = "dirPath", defaultValue = "C:") String dirPath, Model model) {
        String dirName = dirPath.equals("C:") ? "C:" : dirPath.substring(dirPath.lastIndexOf(File.separator) + 1);
        System.out.println(dirName);
        Directory currentDir = directoryRepo.getDirectoryByName(dirName);
        Directory upDir = currentDir.getParent() == null ? currentDir : (Directory) currentDir.getParent();
        updateModel(model, getDirPath(upDir), upDir.getContent());
        return "explorerView";
    }

    public void updateModel(Model model, String dirPath, List<FileObject> fileItems) {
        fileItems.sort(Comparator.comparing(FileObject::getName));
        fileItems.sort(Comparator.comparing(c -> !(c instanceof Directory)));
        model.addAttribute("dirPath", dirPath);
        model.addAttribute("content", fileItems);
    }

    private String getDirPath(Directory currentDir) {
        String dirPath = currentDir.getName();
        Directory tmpDir = currentDir;
        while (tmpDir.getParent() != null) {
            tmpDir = (Directory) tmpDir.getParent();
            dirPath = tmpDir.getName() + File.separator + dirPath;
        }
        return dirPath;
    }

}
