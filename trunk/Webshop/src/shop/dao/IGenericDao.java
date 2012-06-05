package shop.dao;

import java.util.List;

import com.db4o.ext.DatabaseClosedException;
import com.db4o.ext.Db4oIOException;

import shop.dto.DBUUIDBase;

/**
 * 
 * @author Andreas
 *
 * @param <T> you can enter any class of the Webshop that is meant to be persisted; just let the class extend {@link DBUUIDBase}
 */
public interface IGenericDao <T extends DBUUIDBase> {

    /** Persist the newInstance object into database and returns the id */
    String create(T newInstance);

    /** Retrieve an object that was previously persisted to the database using
     *   the indicated id as primary key
     * @throws IllegalAccessException 
     * @throws InstantiationException 
     */
    T read(String id) throws InstantiationException, IllegalAccessException;
    
    /**
     * Retrieve all objects that were previously persisted to the database
     * @throws IllegalAccessException 
     * @throws InstantiationException 
     * @throws DatabaseClosedException 
     * @throws Db4oIOException 
     */
    List<T> readAll() throws Db4oIOException, DatabaseClosedException, InstantiationException, IllegalAccessException;
    
    /** Save changes made to a persistent object.  */
    void update(T transientObject);

    /** Remove an object from persistent storage in the database */
    void delete(T persistentObject);
    
    /** Remove an object from persistent storage in the database based on the ID as String 
     * @throws IllegalAccessException 
     * @throws InstantiationException */
    void delete(String id) throws InstantiationException, IllegalAccessException;
    
    /**
     * checks wheater an object already exists in the database. Good for checks like "is this name already in use?"
     * @param attribute the attribute you want to check (eg. identifier, name, according to your DTO)
     * @param value the concrete value of the attribute you want to check
     * @return true if any item has the attribute with the value, else false
     */
    boolean existByAttribute(String attribute, String value);
    
    /**
     * checks wheater an object already exists in the database according to two attributes.
     * Good for checks like "Does this name and password combination exist?"
     * @param attribute1
     * @param value1
     * @param attribute2
     * @param value2
     * @return
     */
    boolean existByTwoAttributes(String attribute1, String value1, String attribute2, String value2) ;
}