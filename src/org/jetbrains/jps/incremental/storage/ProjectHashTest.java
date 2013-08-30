package org.jetbrains.jps.incremental.storage;

import org.jetbrains.jps.incremental.storage.util.FileUtil;

import java.io.*;
import java.util.zip.ZipFile;

/**
 * @author Sergey Serebryakov
 */
public class ProjectHashTest {

    /*private static File ourProjectRoot = new File("C:\\Work\\hashtest\\test1\\");
    private static File ideaProjectRoot = new File("C:\\Work\\intellij-fresh-13-A\\");
    private static File ideaOutputRoot = new File(ideaProjectRoot, "out");*/

    /*public static void testActualize() throws IOException {
        File createdFile = new File(ourProjectRoot, "y/y/x");
        if (createdFile.exists()) createdFile.delete();
        File deletedFile = new File(ourProjectRoot, "bbb/bb.in");
        if (!deletedFile.exists()) deletedFile.createNewFile();
        File changedFile = new File(ourProjectRoot, "t/txt.txt.");
        PrintWriter p = new PrintWriter(changedFile);
        p.write("FILE CONTENT NUMBER ONE ONE ONE");
        p.close();

        TreeActualizer a = new TreeActualizer();

        File dataStorageDirectory = FileUtil.createTempDirectory("actualizer-test-", null);
        ProjectHashedFileTree tree = new ProjectHashedFileTreeImpl(dataStorageDirectory);
        System.err.println("Actualizing 1...");
        a.actualize(ourProjectRoot, tree, ".", ".");
        System.out.println(tree.listSubtree("."));

        createdFile.createNewFile();
        deletedFile.delete();
        PrintWriter q = new PrintWriter(changedFile);
        q.write("FILE CONTENT NUMBER TWO TWO TWO");
        q.close();

        System.err.println("Actualizing 2...");
        a.actualize(ourProjectRoot, tree, ".", ".");
        System.out.println(tree.listSubtree("."));
        System.out.println("Tree size: " + tree.nodesCount());

        if (createdFile.exists()) createdFile.delete();
        if (!deletedFile.exists()) deletedFile.createNewFile();
        p = new PrintWriter(changedFile);
        p.write("FILE CONTENT NUMBER ONE ONE ONE");
        p.close();
    }*/

    /*public static void testCompare() throws IOException {
        File createdFile = new File(ourProjectRoot, "y/y/x");
        if (createdFile.exists()) createdFile.delete();
        File deletedFile = new File(ourProjectRoot, "bbb/bb.in");
        if (!deletedFile.exists()) deletedFile.createNewFile();
        File changedFile = new File(ourProjectRoot, "t/txt.txt.");
        PrintWriter p = new PrintWriter(changedFile);
        p.write("FILE CONTENT NUMBER ONE ONE ONE");
        p.close();

        TreeActualizer a = new TreeActualizer();

        File dataStorageDirectory1 = FileUtil.createTempDirectory("comparer-test1-", null);
        ProjectHashedFileTree tree1 = new ProjectHashedFileTreeImpl(dataStorageDirectory1);
        System.err.println("Actualizing 1...");
        a.actualize(ourProjectRoot, tree1, ".", ".");
        System.out.println(tree1.listSubtree("."));

        createdFile.createNewFile();
        deletedFile.delete();
        PrintWriter q = new PrintWriter(changedFile);
        q.write("FILE CONTENT NUMBER TWO TWO TWO");
        q.close();

        File dataStorageDirectory2 = FileUtil.createTempDirectory("comparer-test2-", null);
        ProjectHashedFileTree tree2 = new ProjectHashedFileTreeImpl(dataStorageDirectory2);
        System.err.println("Actualizing 2...");
        a.actualize(ourProjectRoot, tree2, ".", ".");
        System.out.println(tree2.listSubtree("."));

        TreeDifferenceCollector c = new TreeDifferenceCollector();
        TreeComparator.compare(tree1, tree2, c, ".");
        System.out.println(c);

        if (createdFile.exists()) createdFile.delete();
        if (!deletedFile.exists()) deletedFile.createNewFile();
        p = new PrintWriter(changedFile);
        p.write("FILE CONTENT NUMBER ONE ONE ONE");
        p.close();
    }*/

    /*private static void ideaTestActualize() throws IOException {
        File changedFile = new File(ideaProjectRoot, "jps\\jps-builders\\src\\org\\jetbrains\\jps\\incremental\\storage\\Something.java");
        PrintWriter p = new PrintWriter(changedFile);
        p.write("FILE CONTENT NUMBER ONE ONE ONE");
        p.close();

        TreeActualizer a = new TreeActualizer();
        File dataStorageDirectory = FileUtil.createTempDirectory("idea-actualizer-test-", null);
        ProjectHashedFileTree tree = new ProjectHashedFileTreeImpl(dataStorageDirectory);
        System.err.println("Actualizing 1...");

        long start1 = System.currentTimeMillis();
        a.actualize(ideaProjectRoot, tree, ".", ".");
        long finish1 = System.currentTimeMillis();

        System.err.println("Tree size: " + tree.nodesCount());
        System.err.println("Time consumed: " + (finish1 - start1) / 1000.0);

        PrintWriter q = new PrintWriter(changedFile);
        q.write("FILE CONTENT NUMBER TWO TWO TWO");
        q.close();

        System.err.println("Actualizing 2...");

        long start2 = System.currentTimeMillis();
        a.actualize(ideaProjectRoot, tree, ".", ".");
        long finish2 = System.currentTimeMillis();

        System.err.println("Tree size: " + tree.nodesCount());
        System.err.println("Time consumed: " + (finish2 - start2) / 1000.0);
    }*/

    /*private static void ideaTestCompare() throws IOException {
        File changedFile = new File(ideaProjectRoot, "jps\\jps-builders\\src\\org\\jetbrains\\jps\\incremental\\storage\\Something.java");
        PrintWriter p = new PrintWriter(changedFile);
        p.write("FILE CONTENT NUMBER ONE ONE ONE");
        p.close();

        File dataStorageDirectory1 = FileUtil.createTempDirectory("idea-comparator-test1-", null);
        ProjectHashedFileTree tree1 = new ProjectHashedFileTreeImpl(dataStorageDirectory1);
        System.err.println("Actualizing 1...");

        long start1 = System.currentTimeMillis();
        new TreeActualizer().actualize(ideaProjectRoot, tree1, ".", ".");
        long finish1 = System.currentTimeMillis();
        System.err.println("Time consumed 1: " + (finish1 - start1) / 1000.0);

        PrintWriter q = new PrintWriter(changedFile);
        q.write("FILE CONTENT NUMBER TWO TWO TWO");
        q.close();

        File dataStorageDirectory2 = FileUtil.createTempDirectory("idea-comparator-test2-", null);
        ProjectHashedFileTree tree2 = new ProjectHashedFileTreeImpl(dataStorageDirectory2);
        System.err.println("Actualizing 2...");
        long start2 = System.currentTimeMillis();
        new TreeActualizer().actualize(ideaProjectRoot, tree2, ".", ".");
        long finish2 = System.currentTimeMillis();
        System.err.println("Time consumed 2: " + (finish2 - start2) / 1000.0);

        TreeDifferenceCollector c = new TreeDifferenceCollector();
        long start3 = System.currentTimeMillis();
        TreeComparator.compare(tree1, tree2, c, ".");
        long finish3 = System.currentTimeMillis();
        System.err.println("Time consumed 3: " + (finish3 - start3) / 1000.0);
        System.err.println(c);
    }*/

    private static boolean actualize(String classDirectory, String storageDirectory) {
        File storageDirectoryFile = new File(storageDirectory);
        if (!storageDirectoryFile.exists() && !storageDirectoryFile.mkdir()) {
            System.err.println("Cannot create storage directory");
            return false;
        }

        ProjectHashedFileTree tree = new ProjectHashedFileTreeImpl(storageDirectoryFile);
        TreeActualizer a = new TreeActualizer();

        try {
            System.err.println("Actualizing...");
            long moment1 = System.currentTimeMillis();
            a.actualize(new File(classDirectory), tree, ".", ".");
            long moment2 = System.currentTimeMillis();
            System.err.println("Time consumed: " + (moment2 - moment1) / 1000.0);
        } catch (IOException e) {
            System.err.println("IOException while actualizing");
            e.printStackTrace();
            return false;
        }

        try {
            System.err.println("Saving hashtree...");
            long moment3 = System.currentTimeMillis();
            tree.save();
            long moment4 = System.currentTimeMillis();
            System.err.println("Time consumed: " + (moment4 - moment3) / 1000.0);
        } catch (IOException e) {
            System.err.println("IOException while saving a hashtree");
            e.printStackTrace();
            return false;
        }

        return true;
    }

    private static void copyFromZip(String zipFilePath, String classDirectory, String relativePath) throws IOException {
        ZipFile zip = new ZipFile(zipFilePath);
        BufferedInputStream in = new BufferedInputStream(zip.getInputStream(zip.getEntry(relativePath)));

        File target = new File(classDirectory, relativePath);
        BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(target.getAbsolutePath()));

        byte[] buffer = new byte[8192];
        int len;
        while ((len = in.read(buffer)) != -1) {
            out.write(buffer, 0, len);
        }
        in.close();
        out.close();
    }

    private static boolean compare(String oldStorageDirectory, String newStorageDirectory, String zipFilePath, String classDirectory) {
        File oldStorageDirectoryFile = new File(oldStorageDirectory);
        if (!oldStorageDirectoryFile.exists() || !oldStorageDirectoryFile.isDirectory()) {
            System.err.println("Missing storage directory: " + oldStorageDirectory);
            return false;
        }

        File newStorageDirectoryFile = new File(newStorageDirectory);
        if (!newStorageDirectoryFile.exists() || !newStorageDirectoryFile.isDirectory()) {
            System.err.println("Missing storage directory: " + newStorageDirectory);
            return false;
        }

        File zipFile = new File(zipFilePath);
        if (!zipFile.exists()) {
            System.err.println("Missing zip file: " + zipFilePath);
            return false;
        }

        ProjectHashedFileTree oldTree = new ProjectHashedFileTreeImpl(oldStorageDirectoryFile);
        ProjectHashedFileTree newTree = new ProjectHashedFileTreeImpl(newStorageDirectoryFile);

        System.err.println("Loading hashtrees...");
        try {
            long moment1 = System.currentTimeMillis();
            oldTree.load();
            newTree.load();
            long moment2 = System.currentTimeMillis();
            System.err.println("Time consumed: " + (moment2 - moment1) / 1000.0);
        } catch (IOException e) {
            System.err.println("IOException while loading a hashtree");
            e.printStackTrace();
            return false;
        }

        System.err.println("Comparing hashtrees...");
        long moment3 = System.currentTimeMillis();
        TreeDifferenceCollector diff = new TreeDifferenceCollector();
        TreeComparator.compare(newTree, oldTree, diff, ".");
        long moment4 = System.currentTimeMillis();
        System.err.println("Time consumed: " + (moment4 - moment3) / 1000.0);

        if (Debug.DEBUG) {
            System.err.println(diff);
        }

        System.err.println("Applying changes...");
        long moment5 = System.currentTimeMillis();
        int changesCount = 0;

        for (String deleted : diff.getDeletedFiles()) {
            File deletedFile = new File(classDirectory, deleted);
            if (!deletedFile.exists()) {
                //System.err.println("Comparator says " + deleted + " was deleted, but there was no file initially");
                continue;
            }
            if (!FileUtil.delete(deletedFile)) {
                System.err.println("Cannot delete file " + deleted);
            }
            changesCount++;
        }

        for (String created : diff.getCreatedFiles()) {
            File createdFile = new File(classDirectory, created);
            if (createdFile.exists()) {
                //System.err.println("Comparator says " + created + " was created, but there was a file already");
                continue;
            }

            if (newTree.hasDirectory(created)) {
                createdFile.mkdirs();
            } else if (newTree.hasFile(created)) {
                FileUtil.createIfNotExists(createdFile);
                try {
                    String relativePath = FileUtil.toSystemIndependentName(created).replaceFirst("\\./", "");
                    copyFromZip(zipFilePath, classDirectory, relativePath);
                } catch (IOException e) {
                    System.err.println("IOException while unzipping " + created);
                    e.printStackTrace();
                }
            } else {
                System.err.println("Comparator says " + created + " was created, but the new tree doesn't have this node");
            }
            changesCount++;
        }

        for (String changed : diff.getChangedFiles()) {
            File changedFile = new File(classDirectory, changed);
            if (!changedFile.exists()) {
                System.err.println("Comparator says " + changed + " was changed, but there was no file initially");
                continue;
            }
            if (!FileUtil.delete(changedFile)) {
                System.err.println("Cannot delete file " + changed);
            }

            FileUtil.createIfNotExists(changedFile);
            try {
                String relativePath = FileUtil.toSystemIndependentName(changed).replaceFirst("\\./", "");
                copyFromZip(zipFilePath, classDirectory, relativePath);
            } catch (IOException e) {
                System.err.println("IOException while unzipping " + changed);
                e.printStackTrace();
            }
            changesCount++;
        }

        long moment6 = System.currentTimeMillis();
        System.err.println("Time consumed: " + (moment6 - moment5) / 1000.0);
        System.err.println("Applied " + changesCount + " changes");

        return true;
    }

    public static void main(String[] args) throws IOException {
        if (args.length < 1) {
            System.err.println("No arguments provided!");
            System.err.println("Usage:");
            System.err.println("    a <class-dir> <storage-dir>: actualize hashtree");
            System.err.println("    c <old-storage-dir> <new-storage-dir> <data-zip> <class-dir>: compare hashtrees and roll changes from zip file");
            return;
        }

        String command = args[0];

        if (command.equals("a")) {
            if (args.length < 3) {
                System.err.println("Not enough arguments!");
                return;
            }
            String classDirectory = args[1];
            String storageDirectory = args[2];

            if (actualize(classDirectory, storageDirectory)) {
                System.err.println("Success!");
            } else {
                System.err.println("Failure!");
            }
        } else if (command.equals("c")) {
            if (args.length < 5) {
                System.err.println("Not enough arguments!");
                return;
            }
            String oldStorageDirectory = args[1];
            String newStorageDirectory = args[2];
            String zipFilePath = args[3];
            String classDirectory = args[4];

            if (compare(oldStorageDirectory, newStorageDirectory, zipFilePath, classDirectory)) {
                System.err.println("Success!");
            } else {
                System.err.println("Failure!");
            }
        } else {
            System.err.println("Unsupported command!");
        }
    }


}
