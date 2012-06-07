/**
 * 
 */
package shop.util;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

import shop.dto.DBTrack;
import de.vdheide.mp3.ID3v2DecompressionException;
import de.vdheide.mp3.ID3v2IllegalVersionException;
import de.vdheide.mp3.ID3v2WrongCRCException;
import de.vdheide.mp3.NoMP3FrameException;

/**
 * @author Andreas
 * 
 */
public class TrackfactoryTest {
	File file = null;
	
	/**
	 * Test method for {@link shop.util.Trackfactory#createTrack(java.io.File)}.
	 */
	@Test
	public void testCreateTrack() throws ID3v2WrongCRCException, ID3v2DecompressionException, ID3v2IllegalVersionException, IOException, NoMP3FrameException {
		
		file = new File("WebContent/images/wwm.mp3");
		
		DBTrack underTest = Trackfactory.createTrack(file);
		assertEquals(underTest.getTrackArtist(), "myArtistName");
		assertEquals(underTest.getTrackDiskNumber(), 3);
		assertEquals(underTest.getTrackGenre(), "Rock/Pop");
		assertEquals(underTest.getTrackNumber(), 1);
		assertEquals(underTest.getTrackTitle(), "myTrackTitle");
		assertEquals(underTest.getTrackDate(), 2000);
	}
	
	/*no tests on correct exception throwing*/

}
