
package com.dpashko.fastrent.domain.repository;


import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * An annotation to mark objects to be stores in persistent repositories.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Identity {

  /**
   * A UUID for an entity class, to be used as a primary key in a repository.
   * <p>
   * Example:  "06b1eec1-ac39s4554-a44c-bcs4b35477ca"
   */

  String uuid();
}
