package org.jetbrains.jps.incremental.storage.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author JetBrains
 */
public class FileUtil {

    private static String ensureEnds(String s, final char endsWith) {
        return StringUtil.endsWithChar(s, endsWith) ? s : s + endsWith;
    }

    public static String getRelativePath(String basePath, String filePath, final char separator) {
        return getRelativePath(basePath, filePath, separator, /*isFileSystemCaseSensitive = */ false);
    }

    public static String getRelativePath(String basePath,
                                         String filePath,
                                         final char separator,
                                         final boolean caseSensitive) {
        basePath = ensureEnds(basePath, separator);

        String basePathToCompare = caseSensitive ? basePath : basePath.toLowerCase();
        String filePathToCompare = caseSensitive ? filePath : filePath.toLowerCase();
        if (basePathToCompare.equals(ensureEnds(filePathToCompare, separator))) return ".";
        int len = 0;
        int lastSeparatorIndex = 0; // need this for cases like this: base="/temp/abc/base" and file="/temp/ab"
        while (len < filePath.length() && len < basePath.length() && filePathToCompare.charAt(len) == basePathToCompare.charAt(len)) {
            if (basePath.charAt(len) == separator) {
                lastSeparatorIndex = len;
            }
            len++;
        }

        if (len == 0) return null;

        StringBuilder relativePath = new StringBuilder();
        for (int i = len; i < basePath.length(); i++) {
            if (basePath.charAt(i) == separator) {
                relativePath.append("..");
                relativePath.append(separator);
            }
        }
        relativePath.append(filePath.substring(lastSeparatorIndex + 1));

        return relativePath.toString();
    }

    public static String toSystemIndependentName(String path) {
        return path.replace('\\', '/');
    }

    public static boolean createIfNotExists(java.io.File file) {
        if (file.exists()) return true;
        try {
            if (!createParentDirs(file)) return false;

            OutputStream s = new FileOutputStream(file);
            s.close();
            return true;
        }
        catch (IOException e) {
            return false;
        }
    }

    public static boolean createParentDirs(java.io.File file) {
        if (!file.exists()) {
            final File parentFile = file.getParentFile();
            if (parentFile != null) {
                return createDirectory(parentFile);
            }
        }
        return true;
    }

    public static boolean createDirectory(File path) {
        return path.isDirectory() || path.mkdirs();
    }


    public static boolean delete(File file) {
        if (file.isDirectory()) {
            if (!deleteChildren(file)) return false;
        }
        return deleteFile(file);
    }

    protected static boolean deleteChildren(File file) {
        File[] files = file.listFiles();
        if (files != null) {
            for (File child : files) {
                if (!delete(child)) return false;
            }
        }
        return true;
    }

    protected static boolean deleteFile(final File file) {
        return (file.delete() || !file.exists());
    }

}
