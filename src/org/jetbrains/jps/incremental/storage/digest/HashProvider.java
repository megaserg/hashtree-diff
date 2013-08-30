package org.jetbrains.jps.incremental.storage.digest;

import java.security.MessageDigest;

/**
 * @author Sergey Serebryakov
 */
public interface HashProvider {
  MessageDigest getMessageDigest();
}
