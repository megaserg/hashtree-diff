package org.jetbrains.jps.incremental.storage.storage;

import org.jetbrains.jps.incremental.storage.util.FileUtil;

import java.io.File;

/**
 * @author Sergey Serebryakov
 */
public class PathToHashMapping extends SynchronizedHashMapStorage<String, String> {

    public PathToHashMapping(File storePath) {
        super(storePath);
    }

    @Override
    public void put(String path, String value) {
        super.put(FileUtil.toSystemIndependentName(path), value);
    }

    @Override
    public String get(String path) {
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
