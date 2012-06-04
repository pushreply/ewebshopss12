package shop.dao;

import java.util.List;

import shop.dto.DBCustomer;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.ext.Db4oException;
import com.db4o.query.Predicate;
import com.db4o.query.Query;

/*
 * @author: roha0001
 */

public class DAOCustomer{
	
	DBCustomer result = null;
	
	/*
	 * default constructor
	 */
	public DAOCustomer() { super(); }
	
	/* 
	 * NQ.
	 * match username and password, for login process, return boolean value
	 */
	public static boolean isMatchUser(final String username, final String password, ObjectContainer db){
		List<DBCustomer> customerlist = db.query(new Predicate<DBCustomer>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = -6286611367952498800L;

			public boolean match(DBCustomer candidate) {
				return candidate.getUsername().equals(username) && candidate.getPassword().equals(password);
			}
		});
		if (!customerlist.isEmpty()){return true;}
		else{throw new Db4oException("kein Match");}
	}
	
	
	/*
	 * SODA.
	 * match username, check whether the username is taken (for registration process), return boolean value
	 */
	public boolean isMatchUser(String username, ObjectContainer db){
		Query query = db.query();
		query.constrain(DBCustomer.class);
		query.descend("username").constrain(username);
		ObjectSet<DBCustomer> result = query.execute();
		if(result.isEmpty()){return true;}
		else{return false;}
	}
	
	
	/*
	 * find customer by username
	 */
	public DBCustomer findUserbyUUID(String username, ObjectContainer db){
		Query query = db.query();
		query.constrain(DBCustomer.class);
		query.descend("identifier").constrain(username); //query.descend("table_column").constrain(param);
		ObjectSet<DBCustomer> result = query.execute();
		if(result.hasNext()){
			return result.next();
		}
		else{
			return null;
		}
	}
	
	/*
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
	
	/*
	 * update user data, based on username: FirstName, LastName
	 */
	public void updateUserData_name(String uName, String uLastName, String uFirstName, ObjectContainer db){
		
	}
	
	/*
	 * update user data, based on username: Address
	 */
	public void updateUserData_address(String uName, String uStreet, String uCountry, ObjectContainer db){
		
	}
	
}