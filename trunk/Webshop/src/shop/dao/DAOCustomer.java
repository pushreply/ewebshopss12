package shop.dao;

import java.util.List;
import java.util.UUID;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Predicate;
import com.db4o.query.Query;

import shop.dto.DBCustomer;

public class DAOCustomer extends DBCustomer{
	
	DBCustomer result = null;
	
	public DAOCustomer() {
		// TODO Auto-generated constructor stub
	}
	
	/* 
	 * match username and password, for login process, return boolean value
	 */
	public boolean matchUser(final String username, final String password, ObjectContainer db){
		List<DBCustomer> customerlist = db.query(new Predicate<DBCustomer>() {
			public boolean match(DBCustomer candidate) {
				return candidate.getUsername().equals(username) && candidate.getPassword().equals(password);
			}
		});
		if (customerlist.isEmpty()){return false;}
		return true;
	}
	
	/*
	 * match username, check whether the username is taken (for registration process), return boolean value
	 */
	public boolean matchUser(String username, ObjectContainer db){
		Query query = db.query();
		query.constrain(DBCustomer.class);
		query.descend("username").constrain(username);
		ObjectSet<DBCustomer> result = query.execute();
		if(result.isEmpty()){return true;}
		return false;
	}
	
	
	/*
	 * find user by username
	 */
	public DBCustomer findUser(String username, ObjectContainer db){
		Query query = db.query();
		query.constrain(DBCustomer.class);
		query.descend("username").constrain(username);
		ObjectSet<DBCustomer> result = query.execute();
		return result.next();
	}
}