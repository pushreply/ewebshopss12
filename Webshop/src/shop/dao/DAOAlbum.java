package shop.dao;

/**
 * @author Schneider Sergej
 */

import java.util.LinkedList;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Query;

import shop.dto.DBAlbum;
import shop.dto.DBCategory;
import shop.dto.DBKeyword;

public class DAOAlbum extends DBAlbum {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	DBAlbum dbalbum = null;

	
	@SuppressWarnings("unused")
	public LinkedList<DBAlbum> findAlbumbyTitle(String albumtitle, ObjectContainer db)
	{
		LinkedList<DBAlbum> dbalbumlist = new LinkedList<DBAlbum>();
		Query query = db.query();
		query.constrain(DBAlbum.class);
		query.descend("albumTitel").constrain(albumtitle).like();
		ObjectSet<DBAlbum> result = query.execute();
	    while(result.hasNext())
	    {
	    	dbalbumlist.add(result.next());
	    }
	    
	    if(dbalbumlist != null)
	    {
	    	return dbalbumlist;
	    }
	    else
	    {
	    	return null;
	    }
	}
	
	@SuppressWarnings("unused")
	public LinkedList<DBAlbum> findAlbumbyArtist(String albumartist, ObjectContainer db)
	{
		LinkedList<DBAlbum> dbalbumlist = new LinkedList<DBAlbum>();
		Query query = db.query();
		query.constrain(DBAlbum.class);
		query.descend("artist").constrain(albumartist).like();
		ObjectSet<DBAlbum> result = query.execute();
	    while(result.hasNext())
	    {
	    	dbalbumlist.add(result.next());
	    }
	    
	    if(dbalbumlist != null)
	    {
	    	return dbalbumlist;
	    }
	    else
	    {
	    	return null;
	    }
	}
	
	@SuppressWarnings("unused")
	public LinkedList<DBAlbum> findAlbumbyTitleArtist(String albumtitle, String albumartist, ObjectContainer db)
	{
		LinkedList<DBAlbum> dbalbumlist = new LinkedList<DBAlbum>();
		Query query = db.query();
		query.constrain(DBAlbum.class);
		query.descend("albumTitel").constrain(albumtitle).like();
		query.descend("artist").constrain(albumartist).like();
		ObjectSet<DBAlbum> result = query.execute();
	    while(result.hasNext())
	    {
	    	dbalbumlist.add(result.next());
	    }
	    
	    if(dbalbumlist != null)
	    {
	    	return dbalbumlist;
	    }
	    else
	    {
	    	return null;
	    }
	}
	
	@SuppressWarnings("unused")
	public LinkedList<DBAlbum> findAlbumbyCategory(String[] categorys,
			ObjectContainer db) {
		
		LinkedList<DBAlbum> dbalbumlist = new LinkedList<DBAlbum>();
		
		Query query = db.query();
		query.constrain(DBAlbum.class);
		Query empQuery = query.descend("categories");
		empQuery.constrain(DBCategory.class);
		Query nameQuery = empQuery.descend("categoryName");
		for (String category : categorys) {
			nameQuery.constrain(category);
			ObjectSet<DBAlbum> result = query.execute();
			System.out.println(result.size() );
			while(result.hasNext())
			{
				dbalbumlist.add(result.next());
			}
		}
	    
	    if(dbalbumlist != null)
	    {
	    	return dbalbumlist;
	    }
	    else
	    {
	    	return null;
	    }
	}
	
	@SuppressWarnings("unused")
	public LinkedList<DBAlbum> findAlbumbyTitleCategory(String albumtitle,
			String[] categorys, ObjectContainer db) {
		LinkedList<DBAlbum> dbalbumlist = new LinkedList<DBAlbum>();
		
		Query query = db.query();
		query.constrain(DBAlbum.class);
		query.descend("albumTitel").constrain(albumtitle).like();
		Query empQuery = query.descend("categories");
		empQuery.constrain(DBCategory.class);
		Query nameQuery = empQuery.descend("categoryName");
		for (String category : categorys) {
			nameQuery.constrain(category);
			ObjectSet<DBAlbum> result = query.execute();
			System.out.println(result.size() );
			while(result.hasNext())
			{
				dbalbumlist.add(result.next());
			}
		}
	    
	    if(dbalbumlist != null)
	    {
	    	return dbalbumlist;
	    }
	    else
	    {
	    	return null;
	    }
	}
	
	@SuppressWarnings("unused")
	public LinkedList<DBAlbum> findAlbumbyArtistCategory(String albumartist,
			String[] categorys, ObjectContainer db) {
		LinkedList<DBAlbum> dbalbumlist = new LinkedList<DBAlbum>();
		
		Query query = db.query();
		query.constrain(DBAlbum.class);
		query.descend("artist").constrain(albumartist).like();
		Query empQuery = query.descend("categories");
		empQuery.constrain(DBCategory.class);
		Query nameQuery = empQuery.descend("categoryName");
		for (String category : categorys) {
			nameQuery.constrain(category);
			ObjectSet<DBAlbum> result = query.execute();
			System.out.println(result.size() );
			while(result.hasNext())
			{
				dbalbumlist.add(result.next());
			}
		}
	    
	    if(dbalbumlist != null)
	    {
	    	return dbalbumlist;
	    }
	    else
	    {
	    	return null;
	    }
	}
	
	@SuppressWarnings("unused")
	public LinkedList<DBAlbum> findAlbumbyTitleArtistCategory(String albumtitle, String albumartist, String[] categorys, ObjectContainer db)
	{
	LinkedList<DBAlbum> dbalbumlist = new LinkedList<DBAlbum>();
		
		Query query = db.query();
		query.constrain(DBAlbum.class);
		query.descend("albumTitel").constrain(albumtitle).like();
		query.descend("artist").constrain(albumartist).like();
		Query empQuery = query.descend("categories");
		empQuery.constrain(DBCategory.class);
		Query nameQuery = empQuery.descend("categoryName");
		for (String category : categorys) {
			nameQuery.constrain(category);
			ObjectSet<DBAlbum> result = query.execute();
			System.out.println(result.size() );
			while(result.hasNext())
			{
				dbalbumlist.add(result.next());
			}
		}
	    
	    if(dbalbumlist != null)
	    {
	    	return dbalbumlist;
	    }
	    else
	    {
	    	return null;
	    }
	}
	
	@SuppressWarnings("unused")
	public LinkedList<DBAlbum> findAlbumbyKeyword(String[] keywords,
			ObjectContainer db) {
	LinkedList<DBAlbum> dbalbumlist = new LinkedList<DBAlbum>();
		
		Query query = db.query();
		query.constrain(DBAlbum.class);
		Query empQuery = query.descend("keywords");
		empQuery.constrain(DBKeyword.class);
		Query nameQuery = empQuery.descend("keywordName");
		for (String keyword : keywords) {
			nameQuery.constrain(keyword);
			ObjectSet<DBAlbum> result = query.execute();
			System.out.println(result.size() );
			while(result.hasNext())
			{
				dbalbumlist.add(result.next());
			}
		}
	    
	    if(dbalbumlist != null)
	    {
	    	return dbalbumlist;
	    }
	    else
	    {
	    	return null;
	    }
	}
	
	@SuppressWarnings("unused")
	public LinkedList<DBAlbum> findAlbumbyTitleKeyword(String albumtitle, String[] keywords,
			ObjectContainer db) {
	LinkedList<DBAlbum> dbalbumlist = new LinkedList<DBAlbum>();
		
		Query query = db.query();
		query.constrain(DBAlbum.class);
		query.descend("albumTitel").constrain(albumtitle).like();
		Query empQuery = query.descend("keywords");
		empQuery.constrain(DBKeyword.class);
		Query nameQuery = empQuery.descend("keywordName");
		for (String keyword : keywords) {
			nameQuery.constrain(keyword);
			ObjectSet<DBAlbum> result = query.execute();
			System.out.println(result.size() );
			while(result.hasNext())
			{
				dbalbumlist.add(result.next());
			}
		}
	    
	    if(dbalbumlist != null)
	    {
	    	return dbalbumlist;
	    }
	    else
	    {
	    	return null;
	    }
	}
	
	@SuppressWarnings("unused")
	public LinkedList<DBAlbum> findAlbumbyArtistKeyword(String albumartist,
			String[] keywords, ObjectContainer db) {
	LinkedList<DBAlbum> dbalbumlist = new LinkedList<DBAlbum>();
		
		Query query = db.query();
		query.constrain(DBAlbum.class);
		query.descend("artist").constrain(albumartist).like();
		Query empQuery = query.descend("keywords");
		empQuery.constrain(DBKeyword.class);
		Query nameQuery = empQuery.descend("keywordName");
		for (String keyword : keywords) {
			nameQuery.constrain(keyword);
			ObjectSet<DBAlbum> result = query.execute();
			System.out.println(result.size() );
			while(result.hasNext())
			{
				dbalbumlist.add(result.next());
			}
		}
	    
	    if(dbalbumlist != null)
	    {
	    	return dbalbumlist;
	    }
	    else
	    {
	    	return null;
	    }
	}

	@SuppressWarnings("unused")
	public LinkedList<DBAlbum> findAlbumbyTitleArtistKeyword(String albumtitle,
			String albumartist, String[] keywords, ObjectContainer db) {
	LinkedList<DBAlbum> dbalbumlist = new LinkedList<DBAlbum>();
		
		Query query = db.query();
		query.constrain(DBAlbum.class);
		query.descend("albumTitel").constrain(albumtitle).like();
		query.descend("artist").constrain(albumartist).like();
		Query empQuery = query.descend("keywords");
		empQuery.constrain(DBKeyword.class);
		Query nameQuery = empQuery.descend("keywordName");
		for (String keyword : keywords) {
			nameQuery.constrain(keyword);
			ObjectSet<DBAlbum> result = query.execute();
			System.out.println(result.size() );
			while(result.hasNext())
			{
				dbalbumlist.add(result.next());
			}
		}
	    
	    if(dbalbumlist != null)
	    {
	    	return dbalbumlist;
	    }
	    else
	    {
	    	return null;
	    }
	}
	
	@SuppressWarnings("unused")
	public LinkedList<DBAlbum> findAlbumbyCategoryKeyword(String[] categorys,
			String[] keywords, ObjectContainer db) {
		
		LinkedList<DBAlbum> dbalbumlist = new LinkedList<DBAlbum>();
		
		Query query = db.query();
		query.constrain(DBAlbum.class);
		Query keyQuery = query.descend("keywords");
		Query catQuery = query.descend("categories");
		keyQuery.constrain(DBKeyword.class);
		catQuery.constrain(DBCategory.class);
		Query knameQuery = keyQuery.descend("keywordName");
		Query cnameQuery = catQuery.descend("categoryName");
		for (String keyword : keywords) {
			knameQuery.constrain(keyword);
			System.out.println(keyword);
			for (String category : categorys) {
				cnameQuery.constrain(category);
				ObjectSet<DBAlbum> result = query.execute();
				System.out.println(result.size() );
				while(result.hasNext())
				{
					dbalbumlist.add(result.next());
				}
			}
		}
	    
	    if(dbalbumlist != null)
	    {
	    	return dbalbumlist;
	    }
	    else
	    {
	    	return null;
	    }
	}
	
	@SuppressWarnings("unused")
	public LinkedList<DBAlbum> findAlbumbyTitleCategoryKeyword(String albumtitle,
			String[] categorys, String[] keywords,
			ObjectContainer db) {
		
	LinkedList<DBAlbum> dbalbumlist = new LinkedList<DBAlbum>();
		
		Query query = db.query();
		query.constrain(DBAlbum.class);
		query.descend("albumTitel").constrain(albumtitle).like();
		Query keyQuery = query.descend("keywords");
		Query catQuery = query.descend("categories");
		keyQuery.constrain(DBKeyword.class);
		catQuery.constrain(DBCategory.class);
		Query knameQuery = keyQuery.descend("keywordName");
		Query cnameQuery = catQuery.descend("categoryName");
		for (String keyword : keywords) {
			knameQuery.constrain(keyword);
			for (String category : categorys) {
				cnameQuery.constrain(category);
				ObjectSet<DBAlbum> result = query.execute();
				System.out.println(result.size() );
				while(result.hasNext())
				{
					dbalbumlist.add(result.next());
				}
			}
		}
	    
	    if(dbalbumlist != null)
	    {
	    	return dbalbumlist;
	    }
	    else
	    {
	    	return null;
	    }
	}
	
	@SuppressWarnings("unused")
	public LinkedList<DBAlbum> findAlbumbyArtistCategoryKeyword(String albumartist,
			String[] categorys, String[] keywords,
			ObjectContainer db) {
	LinkedList<DBAlbum> dbalbumlist = new LinkedList<DBAlbum>();
		
		Query query = db.query();
		query.constrain(DBAlbum.class);
		query.descend("artist").constrain(albumartist).like();
		Query keyQuery = query.descend("keywords");
		Query catQuery = query.descend("categories");
		keyQuery.constrain(DBKeyword.class);
		catQuery.constrain(DBCategory.class);
		Query knameQuery = keyQuery.descend("keywordName");
		Query cnameQuery = catQuery.descend("categoryName");
		for (String keyword : keywords) {
			knameQuery.constrain(keyword);
			for (String category : categorys) {
				cnameQuery.constrain(category);
				ObjectSet<DBAlbum> result = query.execute();
				System.out.println(result.size() );
				while(result.hasNext())
				{
					dbalbumlist.add(result.next());
				}
			}
		}
	    
	    if(dbalbumlist != null)
	    {
	    	return dbalbumlist;
	    }
	    else
	    {
	    	return null;
	    }
	}

	@SuppressWarnings("unused")
	public LinkedList<DBAlbum> findAlbumbyTitleArtistCategoryKeyword(String albumtitle,
			String albumartist, String[] categorys,
			String[] keywords, ObjectContainer db) {
	LinkedList<DBAlbum> dbalbumlist = new LinkedList<DBAlbum>();
		
		Query query = db.query();
		query.constrain(DBAlbum.class);
		query.descend("albumTitel").constrain(albumtitle).like();
		query.descend("artist").constrain(albumartist).like();
		Query keyQuery = query.descend("keywords");
		Query catQuery = query.descend("categories");
		keyQuery.constrain(DBKeyword.class);
		catQuery.constrain(DBCategory.class);
		Query knameQuery = keyQuery.descend("keywordName");
		Query cnameQuery = catQuery.descend("categoryName");
		for (String keyword : keywords) {
			knameQuery.constrain(keyword);
			for (String category : categorys) {
				cnameQuery.constrain(category);
				ObjectSet<DBAlbum> result = query.execute();
				System.out.println(result.size() );
				while(result.hasNext())
				{
					dbalbumlist.add(result.next());
				}
			}
		}
	    
	    if(dbalbumlist != null)
	    {
	    	return dbalbumlist;
	    }
	    else
	    {
	    	return null;
	    }
	}
}
