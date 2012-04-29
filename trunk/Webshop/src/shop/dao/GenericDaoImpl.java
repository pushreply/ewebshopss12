package shop.dao;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import shop.dto.DBUUIDBase;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

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

	public String create(T o) {
		UUID newID = UUID.randomUUID();
		o.setIdentifier(newID);

		try {
			db.store(o);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o.getIdentifier().toString();
	}

	public T read(String uuid) {
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
		example.setIdentifier(UUID.fromString(uuid));
		ObjectSet<T> result = db.queryByExample(example);
		return (T) result.get(0);
	}
	
	public List<T> readAll() {
		LinkedList<T> items = new LinkedList<T>();

		ObjectSet<T> result = null;
		try {
			result = db.queryByExample(type.newInstance());
			while (result.hasNext()) {
				items.add(result.next());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return items;
	}

	public void update(T o) {
		db.store(o);
	}

	public void delete(T o) {
		db.delete(o);
	}

	public void delete(String uuid) {
		T o = read(uuid); 
		delete(o);
	}
}