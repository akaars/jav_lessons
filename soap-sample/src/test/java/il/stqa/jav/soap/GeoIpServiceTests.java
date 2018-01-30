package il.stqa.jav.soap;

import net.webservicex.GeoIP;
import net.webservicex.GeoIPService;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Created by ilya on 1/30/18
 */
public class GeoIpServiceTests {
  @Test
  public void testMyIp(){
    GeoIP geoIP = new GeoIPService().getGeoIPServiceSoap12().getGeoIP("194.28.29.15");
    assertEquals(geoIP.getCountryCode(), "RUS");
  }
}
