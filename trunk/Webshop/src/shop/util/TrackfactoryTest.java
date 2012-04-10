/**
 * 
 */
package shop.util;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import shop.dto.DBTrack;

import de.vdheide.mp3.ID3v2DecompressionException;
import de.vdheide.mp3.ID3v2IllegalVersionException;
import de.vdheide.mp3.ID3v2WrongCRCException;
import de.vdheide.mp3.MP3File;
import de.vdheide.mp3.NoMP3FrameException;

/**
 * @author Andreas
 * 
 */
public class TrackfactoryTest {
	MP3File file = null;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * Test method for {@link shop.util.Trackfactory#createTrack(java.io.File)}.
	 */
	@Test
	public void testCreateTrack() {
		try {

			file = new MP3File("WebContent/images/wwm.mp3");
		} catch (ID3v2WrongCRCException e) {
			e.printStackTrace();
		} catch (ID3v2DecompressionException e) {
			e.printStackTrace();
		} catch (ID3v2IllegalVersionException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NoMP3FrameException e) {
			e.printStackTrace();
		}
		
		DBTrack underTest = Trackfactory.createTrack(file);
		assertEquals(underTest.getTrackArtist(), "myArtistName");
		assertEquals(underTest.getTrackDiskNumber(), 3);
		assertEquals(underTest.getTrackGenre(), "Rock/Pop");
		assertEquals(underTest.getTrackNumber(), 1);
		assertEquals(underTest.getTrackTitle(), "myTrackTitle");
		assertEquals(underTest.getTrackDate(), 2000);
	}

}
