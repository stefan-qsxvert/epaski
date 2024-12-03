package epaski.app;

import java.io.File;
import java.io.FilenameFilter;

public class ExtFilter implements FilenameFilter {
    private String ext;

    public void setExt(String ext) {
       this.ext = ext;
    }

    @Override
        public boolean accept(File dir, String fileName) {
        String plik = fileName.substring(0, fileName.lastIndexOf("."));

            if (fileName.endsWith(ext) && (plik != "")) {
                return true;
            }
            return false;
        }
    }