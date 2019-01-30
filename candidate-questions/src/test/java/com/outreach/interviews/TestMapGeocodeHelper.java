package com.outreach.interviews;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.outreach.interviews.map.builder.MapGeocodeHelper;
import org.junit.Test;

import java.io.IOException;

public class TestMapGeocodeHelper {
    @Test
    public void testGeocodeFinderAPI1() throws UnsupportedOperationException, IOException {
        new MapGeocodeHelper.GeoCodeFinder()
                .setLocation("ottawa")
                .find();
    }

    @Test
    public void testGeocodeFinderAPI2() throws UnsupportedOperationException, IOException {
        double lat = new MapGeocodeHelper.GeoCodeFinder()
                .setLocation("ottawa")
                .find()
                .getLatitude();
        assertTrue( lat == 45.4215296);
    }

    @Test
    public void testGeocodeFinderAPI3() throws UnsupportedOperationException, IOException {
        double lon = new MapGeocodeHelper.GeoCodeFinder()
                .setLocation("ottawa")
                .find()
                .getLongitude();
        assertTrue( lon == -75.69719309999999);
    }

    @Test
    public void testGeocodeFinderAPI4() throws UnsupportedOperationException, IOException {
        double lat = new MapGeocodeHelper.GeoCodeFinder()
                .setLocation("ottawa")
                .find()
                .getLatitude();
        assertTrue( lat == 0);
    }

    @Test
    public void testGeocodeFinderAPI5() throws UnsupportedOperationException, IOException {
        double lat = new MapGeocodeHelper.GeoCodeFinder()
                .setLocation("ottawa")
                .find()
                .getLatitude();
        assertTrue( lat == 0);
    }

    @Test
    public void testGeocodeFinderAPI6() throws UnsupportedOperationException, IOException {
        double lon = new MapGeocodeHelper.GeoCodeFinder()
                .setLocation("ottawa")
                .find()
                .getLongitude();
        assertTrue( lon == 0);
    }
}
