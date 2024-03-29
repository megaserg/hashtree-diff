package org.jetbrains.jps.incremental.storage;

import java.io.IOException;
import java.util.Collection;

/**
 * @author Sergey Serebryakov
 */
public interface ProjectHashedFileTree {
    /**
     * Whether the given node is a (possibly empty) directory.
     */
    boolean hasDirectory(String path);

    /**
     * Whether the given node is a file.
     */
    boolean hasFile(String path);

    void addDirectoryWithoutHash(String path, String parentPath);

    void addDirectory(String path, String parentPath, String hash);

    void addFile(String path, String parentPath, String hash);

    /**
     * Removes the whole subtree of the given node, including the given node itself.
     * Applicable both to directory and file node.
     */
    void removeSubtree(String path);

    String getPathByName(String parentPath, String name);

    /**
     * Returns names of the immediate children of the given node.
     * The given node is assumed to be a directory.
     */
    Collection<String> getSortedCopyOfChildrenNames(String dirPath);

    /**
     * Returns paths of all nodes in the subtree of the given node, including the given node itself.
     * Applicable both to directory and file node, in the latter case the result contains only one record.
     */
    Collection<String> listSubtree(String path);

    String getHash(String path);

    void updateHash(String path, String hash);

    /**
     * @return The total number of nodes in the tree.
     */
    int nodesCount();

    /**
     * @return The total number of directories in the tree.
     */
    int directoriesCount();

    void save() throws IOException;

    void load() throws IOException;
}
