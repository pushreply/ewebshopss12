package shop.dao;

import java.util.List;
import java.util.UUID;

import shop.dto.DBUUIDBase;

/**
 * 
 * @author Andreas
 *
 * @param <T> you can enter any class of the Webshop that is meant to be persisted; just let the class extend {@link DBUUIDBase}
 */
public interface IGenericDao <T extends DBUUIDBase> {

    /** Persist the newInstance object into database */
    UUID create(T newInstance);

    /** Retrieve an object that was previously persisted to the database using
     *   the indicated id as primary key
     */
    T read(UUID id);
    
    /**
     * Retrieve all objects that were previously persisted to the database
     */
    List<T> readAll();
    
    /** Save changes made to a persistent object.  */
    void update(T transientObject);

    /** Remove an object from persistent storage in the database */
    void delete(T persistentObject);
    
    /** Remove an object from persistent storage in the database based on the ID as String */
    void delete(String uuid);
}