package shop.actions;


/**
 * @author Schneider Sergej
 */

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.dao.GenericDaoImpl;
import shop.dao.IGenericDao;
import shop.dto.DBAlbum;
import shop.dto.DBTrack;
import shop.util.ByteArray;
import shop.util.Trackfactory;

import com.db4o.ObjectContainer;

@WebServlet(urlPatterns = { "/upload" })
@MultipartConfig(location = "C:\\projekt", maxFileSize = 10485760L) // 10MB.
public class UploadMusicFile {

	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected ObjectContainer db;
	
    public UploadMusicFile(HttpServletRequest request,
			HttpServletResponse response, ObjectContainer db) {
    	this.request = request;
    	this.response = response;
    	this.db = db;
    	
    	
    	System.out.println("parameter ausgabe: "+request.getParameter("senden"));
    	if(request.getParameter("senden") != null && "senden".equals(request.getParameter("senden")))
    	{
    			try {
    				albumprocess(request, response, db);
    		} catch (ServletException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
		}
    	else
    	{
    		System.out.println("parameter mp3: "+request.getParameter("uploadFileSubmitButton"));
    		try {
					process(request, response, db);
    			} catch (ServletException e) {
    					// TODO Auto-generated catch block
    				e.printStackTrace();
    			} catch (IOException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    		}
    	}

	}

    
    protected void process(HttpServletRequest request, HttpServletResponse response, ObjectContainer db)
        throws ServletException, IOException
    {
    	System.out.println("ich bin in prozess");
        MultipartMap map = new MultipartMap(request, this);
        DBTrack dbTrack = Trackfactory.createTrack(map.getFile("file"));
        IGenericDao<DBTrack> dao = new GenericDaoImpl<DBTrack>(DBTrack.class, db);
        dao.create(dbTrack);
        map.getFile("file").delete();
        request.setAttribute("track", dbTrack);
        request.getRequestDispatcher("/weiter.jsp").forward(request, response);
    }
    
    
    protected void albumprocess(HttpServletRequest request, HttpServletResponse response, ObjectContainer db)
        throws ServletException, IOException
    {
    	System.out.println("ich bin albumprocess");
        MultipartMap map = new MultipartMap(request, this);
        DBAlbum dbalbum = new DBAlbum();
        System.out.println(map.getParameter("titel"));
        dbalbum.setAlbumTitel(map.getParameter("titel"));
        System.out.println(map.getParameter("artist"));
        dbalbum.setArtist(map.getParameter("artist"));
        System.out.println(map.getParameter("price"));
        dbalbum.setPrice(Double.parseDouble(map.getParameter("price")));
        System.out.println(map.getParameter("label"));
        dbalbum.setLabel(map.getParameter("label"));
        System.out.println(map.getParameter("trackanzahl"));
        dbalbum.setNumberOfTracks(Integer.parseInt(map.getParameter("trackanzahl")));
        System.out.println(map.getParameter("diskanzahl"));
        dbalbum.setNumberOfDisks(Integer.parseInt(map.getParameter("diskanzahl")));
        System.out.println(map.getParameter("albumanzahl"));
        dbalbum.setAmount(Integer.parseInt(map.getParameter("albumanzahl")));
        System.out.println(map.getFile("coverpage").getName());
        
        dbalbum.setCoverpath(ByteArray.getBytesFromFile(map.getFile("coverpage")));
        IGenericDao<DBAlbum>daoAlbum = new GenericDaoImpl<DBAlbum>(DBAlbum.class, db);
        daoAlbum.create(dbalbum);
        request.setAttribute("album", dbalbum);
        request.getRequestDispatcher("/trackhinzufuegen.jsp").forward(request, response);
    }

}
