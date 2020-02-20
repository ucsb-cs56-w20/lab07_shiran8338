package edu.ucsb.cs56.w20.lab07.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import edu.ucsb.cs56.w20.lab07.formbeans.EqSearch;
import geojson.FeatureCollection;
import edu.ucsb.cs56.w20.lab07.services.EarthquakeQueryService;
@Controller
public class EarthquakesController {
    @GetMapping("/earthquakes/search")
    public String getEarthquakesSearch(Model model, OAuth2AuthenticationToken oAuth2AuthenticationToken,
            EqSearch eqSearch) {
        return "earthquakes/search";
    }
     @GetMapping("/earthquakes/results")
      public String getEarthquakesResults(Model model, OAuth2AuthenticationToken oAuth2AuthenticationToken,
              EqSearch eqSearch) {
          model.addAttribute("eqSearch", eqSearch);
          // TODO: Actually do the search here and add results to the model
      EarthquakeQueryService e = new EarthquakeQueryService();
        String json = e.getJSON(eqSearch.getDistance(), eqSearch.getMinmag());
        model.addAttribute("json", json);
        
            FeatureCollection featureCollection = FeatureCollection.fromJSON(json);
            model.addAttribute("featureCollection",featureCollection);

          return "earthquakes/results";
      }

   
}
 

