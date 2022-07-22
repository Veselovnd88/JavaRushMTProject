package htmleditor;

import javax.swing.filechooser.FileFilter;
import java.io.File;

public class HTMLFileFilter extends FileFilter {

    @Override
    public boolean accept(File f) {
        return ((f.isDirectory())||
                f.getName().toLowerCase().endsWith(".html")||
                f.toString().endsWith(".htm"));
    }

    @Override
    public String getDescription() {
        return "HTML и HTM файлы";
    }
}
