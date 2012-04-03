package tagger;

import java.io.File;
import java.io.IOException;
import de.vdheide.mp3.*;

/**
 * This is just a test of the de.vdheide.mp3 library to show how to read tags out of mp3 files
 * test was created due to bad tutorials and sucky API of the lib. ;-)
 * 
 * REMOVE THIS CLASS WHEN TRACKS ARE READ PROPERLY!
 * 
 * @author Andreas
 *
 */
public class TaggingTest {
	public static void starttest() throws FrameDamagedException {
		MP3File file = null;
		try {	
			file = new MP3File("C:\\projekt\\DB\\Webshop\\WebContent\\images", "wwm.mp3");
		} catch (ID3v2WrongCRCException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ID3v2DecompressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ID3v2IllegalVersionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoMP3FrameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Length: \t" + file.getLength());
		System.out.println("Album: \t\t" + file.getName());
		System.out.println("Artist:\t\t" + file.getArtist().getTextContent());
		System.out.println("Composer:\t" + file.getComposer().getTextContent());
		System.out.println("Genre: \t\t" + file.getGenre().getTextContent());
		
		//.getTrack().getTextContent() gibt Track in der Art '5/14' aus!...daher split
		System.out.println("Tracknumber: \t" +  new Integer(file.getTrack().getTextContent().split("/")[0]).intValue());
		System.out.println("max. Tracks: \t" + new Integer(file.getTrack().getTextContent().split("/")[1]).intValue());
		//analog zu getTrack
		System.out.println("Discnumber: \t" + new Integer(file.getPartOfSet().getTextContent().split("/")[0]).intValue());
		System.out.println("max. Discs: \t" +  new Integer(file.getPartOfSet().getTextContent().split("/")[1]).intValue());

		System.out.println("Composer: \t" + file.getComposer().getTextContent());
		System.out.println("Comment: \t" + file.getComments().getTextContent());
	}
}
