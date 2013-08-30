package org.jetbrains.jps.incremental.storage.storage;

import java.io.*;
import java.util.HashMap;

public abstract class SynchronizedHashMapStorage<Key, Value> {
    protected final Object myDataLock = new Object();
    private final File myStorePath;
    private HashMap<Key, Value> myMap;

    public SynchronizedHashMapStorage(File storePath) {
        myStorePath = storePath;
        myMap = new HashMap<Key, Value>();
    }

    public void put(Key key, Value value) {
        synchronized (myDataLock) {
            myMap.put(key, value);
        }
    }

    public Value get(Key key) {
        synchronized (myDataLock) {
            return myMap.get(key);
        }
    }

    public boolean containsKey(Key key) {
        synchronized (myDataLock) {
            return myMap.containsKey(key);
        }
    }

    public void remove(Key key) {
        synchronized (myDataLock) {
            myMap.remove(key);
        }
    }

    public int size() {
        synchronized (myDataLock) {
            return myMap.size();
        }
    }

    public void save() throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(myStorePath));
        out.writeObject(myMap);
        out.close();
    }

    public void load() throws IOException {
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(myStorePath));
        try {
            myMap = (HashMap<Key, Value>) in.readObject();
        } catch (ClassNotFoundException e) {
            System.err.println("No HashMap? Really?");
        }
        in.close();
    }
}