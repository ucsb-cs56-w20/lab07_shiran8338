package edu.ucsb.cs56.w20.lab07.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EarthquakeQueryService {

    private Logger logger = LoggerFactory.getLogger(EarthquakeQueryService.class);

        public String getJSON(int distance, int minmag) {
        String fakeJson = "{ \"key\": \"value\" }";
        String json = fakeJson;
        logger.info("json=" + json);
        return json;
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>("body", headers);

        String uri = "https://earthquake.usgs.gov/fdsnws/event/1/query";
        double ucsbLat = 34.4140;
        double ucsbLong = -119.8489;
        String params = String.format("?format=geojson&minmagnitude=%d&maxradiuskm=%d&latitude=%f&longitude=%f",
           minmag,distance,ucsbLat,ucsbLong);

        String url = uri + params;
        logger.info("url=" + url);

        String retVal="";
        try {   
            ResponseEntity<String> re = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
             MediaType contentType = re.getHeaders().getContentType();
            HttpStatus statusCode = re.getStatusCode();
            retVal = re.getBody();
        } catch (HttpClientErrorException e) {
            retVal = "{\"error\": \"401: Unauthorized\"}";
        }
        logger.info("from EarthquakeQueryService.getJSON: " + retVal);
        return retVal;
    }
}
