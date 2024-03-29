package shop.dao;

import java.util.List;

import shop.dto.DBCustomer;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.ext.Db4oException;
import com.db4o.query.Predicate;
import com.db4o.query.Query;

/**
 * This Class handles DB queries for login, registration and user addresses 
 * 
 * @author Hasiholan
 */

public class DAOCustomer{
	
	DBCustomer result = null;
	
	/**
	 * Default constructor
	 */
	public DAOCustomer() { super(); }
	
	/**
	 * Native Query:
	 * match username and password, for login process, return boolean value
	 */
	public boolean isMatchUser(final String username, final String password, ObjectContainer db){
		List<DBCustomer> customerlist = db.query(new Predicate<DBCustomer>() {
			private static final long serialVersionUID = -6286611367952498800L;
			public boolean match(DBCustomer candidate) {
				return candidate.getUsername().equals(username) && candidate.getPassword().equals(password);
			}
		});
		if (!customerlist.isEmpty()){return true;}
		else{throw new Db4oException("kein Match");}
	}
	
	
	/**
	 * SODA Query
	 * try to match username, check whether the username is taken (for registration process), return boolean value
	 */
	public boolean isMatchUser(String username, ObjectContainer db){
		Query query = db.query();
		query.constrain(DBCustomer.class);
		query.descend("username").constrain(username);
		ObjectSet<DBCustomer> result = query.execute();
		if(result.isEmpty()){return true;}
		else{return false;}
	}
	
	
	/**
	 * find customer by username
	 */
	public DBCustomer findUser(String username, ObjectContainer db){
		Query query = db.query();
		query.constrain(DBCustomer.class);
		query.descend("username").constrain(username); //query.descend("table_column").constrain(param);
		ObjectSet<DBCustomer> result = query.execute();
		if(result.hasNext()){
			return result.next();
		}
		else{
			return null;
		}
	}
	
	/**
	 * read user data: gender, firstname, lastname, address (street, country) 
	 */
	public DBCustomer readUserData(String uName, ObjectContainer db){
		Query query = db.query();
		query.constrain(DBCustomer.class);
		DBCustomer user = new DBCustomer();
		user.setUsername(uName); 
//		query.descend("username").descend("address").constrain(user);
		ObjectSet<DBCustomer> result = null;//query.execute();
		result= db.queryByExample(user);
		if(result.hasNext()){
			return result.next();
		}
		else{
			return null;
		}
	}
	
}