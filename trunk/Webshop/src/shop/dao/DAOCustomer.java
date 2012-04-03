package shop.dao;

import com.db4o.ObjectSet;
import com.db4o.ObjectContainer;
import shop.dto.DBCustomer;

public class DAOCustomer {
	
	public static ObjectSet<DBCustomer> findAllCustomers(ObjectContainer db)
	{
	   return null;	
	}
	
	public static DBCustomer findCustomerByUsername(ObjectContainer db, String login)
	{
		
		return null;
	}
	
	public static void UpdateCustomers(ObjectContainer db)
	{
		
	}
    
	public static void DeleteCustommer(ObjectContainer db)
	{
		
	}
}
