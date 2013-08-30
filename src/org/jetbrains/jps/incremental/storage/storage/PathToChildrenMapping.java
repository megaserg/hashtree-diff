package org.jetbrains.jps.incremental.storage.storage;

import org.jetbrains.jps.incremental.storage.util.FileUtil;

import java.io.File;
import java.util.Collection;

/**
 * @author Sergey Serebryakov
 */
public class PathToChildrenMapping extends SynchronizedHashMapStorage<String, Collection<String>> {

    public PathToChildrenMapping(File storePath) {
        super(storePath);
    }

    @Override
    public void put(String path, Collection<String> children) {
        super.put(FileUtil.toSystemIndependentName(path), children);
    }

    @Override
    public Collection<String> get(String path) {
        return super.get(FileUtil.toSystemIndependentName(path));
    }

    @Override
    public boolean containsKey(String path) {
        return super.containsKey(FileUtil.toSystemIndependentName(path));
    }

    @Override
    public void remove(String path) {
        super.remove(FileUtil.toSystemIndependentName(path));
    }
}
