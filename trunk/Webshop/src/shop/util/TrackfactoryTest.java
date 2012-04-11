/**
 * 
 */
package shop.util;

import static org.junit.Assert.assertEquals;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import shop.dto.DBTrack;

/**
 * @author Andreas
 * 
 */
public class TrackfactoryTest {
	File file = null;
	static final int ID = 0;
	
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
		
		file = new File("WebContent/images/wwm.mp3");
		
		DBTrack underTest = Trackfactory.createTrack(file,ID);
		assertEquals(underTest.getTrackArtist(), "myArtistName");
		assertEquals(underTest.getTrackDiskNumber(), 3);
		assertEquals(underTest.getTrackGenre(), "Rock/Pop");
		assertEquals(underTest.getTrackNumber(), 1);
		assertEquals(underTest.getTrackTitle(), "myTrackTitle");
		assertEquals(underTest.getTrackDate(), 2000);
		assertEquals(underTest.getTrackID(), ID);
	}

}
