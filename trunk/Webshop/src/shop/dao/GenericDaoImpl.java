package shop.dao;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import shop.dto.DBTrack;
import shop.dto.DBUUIDBase;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.ext.DatabaseClosedException;
import com.db4o.ext.DatabaseFileLockedException;
import com.db4o.ext.Db4oIOException;

/**
 * 
 * @author Andreas
 * @param <T> The DAO Classtype you want this Instance to work for
 */
public class GenericDaoImpl<T extends DBUUIDBase> implements IGenericDao<T> {
	private Class<T> type;
	private ObjectContainer db;

	public GenericDaoImpl(Class<T> type, ObjectContainer db) {
		super();
		this.type = type;
		this.db = db;
	}

	public UUID create(T o) {
		UUID newID = UUID.randomUUID();
		o.setIdentifier(newID);

		try {
			db.store(o);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.close();
		}
		
		System.out.println("nach store");
		return o.getIdentifier();

	}

	public T read(UUID id) {
		T example = null;
		try {
			example = type.newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		example.setIdentifier(id);
		ObjectSet<T> result = db.queryByExample(example);
		return (T) result.get(0);
	}
	
	public List<T> readAll(ObjectContainer db) {
		LinkedList<T> items = new LinkedList<T>();

		ObjectSet<T> result = null;
		try {
			result = db.queryByExample(type.newInstance());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		}
		while (result.hasNext()) {
			items.add(result.next());
		}

		return items;
	}
	public List<T> readAll() {
		LinkedList<T> items = new LinkedList<T>();

		ObjectSet<T> result = null;
		try {
			result = db.queryByExample(type.newInstance());
		} catch (Db4oIOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DatabaseClosedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		T item;

		while (result.hasNext()) {
			item = result.next();
			items.add(item);
		}

		return items;
	}

	public void update(T o) {
		db.store(o);
	}

	public void delete(T o) {
		db.delete(o);
	}

	// Not showing implementations of getSession() and setSessionFactory()
}