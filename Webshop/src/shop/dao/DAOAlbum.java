package shop.dao;

import java.util.LinkedList;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Query;

import shop.dto.DBAlbum;

public class DAOAlbum extends DBAlbum {
	
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
}
