package com.epam.hotel.entity;

import java.io.Serializable;

/**
 * Special interface-marker that must be implemented by all entity classes of application
 */
public interface Identifier extends Serializable, Cloneable {
    /**
     * All entities must have id and this contract is designed to return this id
     *
     * @return id of entity
     */
    Long getId();
}
