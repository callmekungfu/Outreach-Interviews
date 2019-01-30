package com.outreach.interviews.map.builder;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;

public class MapGeocodeHelper {
    public static class GeoCodeFinder {
        private String location;
        private JsonObject result;

        private final String URL = "https://maps.googleapis.com/maps/api/geocode";
        private CloseableHttpClient httpClient = HttpClients.createDefault();

        /**
         *  Sets the address parameter for the request made to Google Maps
         *
         * @param location The address of the location in question, please encode all spaces with "+"
         */

        public GeoCodeFinder setLocation(String location) {
            this .location = location;
            return this;
        }

        /**
         * Makes the request to Google and stores the results for further analysis
         * @return The Geocode Finder itself
         * @throws UnsupportedOperationException
         * @throws IOException
         * @throws IllegalArgumentException
         */

        public GeoCodeFinder find() throws UnsupportedOperationException, IOException, IllegalArgumentException {
            String request = this.getURL() + "address=" + getLocation()
                                           + "&key=" + getAPIKey();

            HttpGet httpGet = new HttpGet(request);
            CloseableHttpResponse response = httpClient.execute(httpGet);

            try {
                HttpEntity entity = response.getEntity();
                String data = IOUtils.toString(entity.getContent(), "UTF-8");
                this.result = new JsonParser().parse(data).getAsJsonObject();
            }
            finally {
                response.close();
            }

            return this;
        }

        /**
         * Gets the longitude value of the location
         * @return the longitude in double form
         */

        public double getLongitude () {
            if (!zeroResults(this.result)) {
                double longitude = this.result.get("results").getAsJsonArray().get(0).getAsJsonObject().get("geometry").getAsJsonObject().get("location").getAsJsonObject().get("lng").getAsDouble();
                return longitude;
            } else {
                throw new IllegalArgumentException("Failed to find the longitude of the following address: " + getLocation());
            }
        }

        /**
         * Gets the latitude value of the location
         * @return the latitude of the location in double form
         */
        public double getLatitude () {
            if (!zeroResults(this.result)) {
                double latitude = this.result.get("results").getAsJsonArray().get(0).getAsJsonObject().get("geometry").getAsJsonObject().get("location").getAsJsonObject().get("lat").getAsDouble();
                return latitude;
            } else {
                throw new IllegalArgumentException("Failed to find the latitude of the following address: " + getLocation());
            }
        }


        // Internal Methods
        private final String getURL() {
            return this.URL + "/json?";
        }

        private final String getAPIKey() {
            return "AIzaSyD_-LurHQB-TRD1YGYGvjasJovEHPoOWTM";
        }

        private final String getLocation() {
            if(this.location == null)
                throw new IllegalArgumentException("Your address Cannot Be Empty");
            return this.location;
        }

        private final boolean zeroResults(JsonObject obj) {
            return obj.get("status").getAsString().equals("ZERO_RESULTS");
        }
    }
}