package com.vaonova.backendvaonova.service;

import com.vaonova.backendvaonova.dto.RequestIdeaDto;
import com.vaonova.backendvaonova.dto.ResponseAnalizedIdeaDto;
import com.vaonova.backendvaonova.mapper.AnalizedIdeaMapper;
import com.vaonova.backendvaonova.mapper.IdeaMapper;
import com.vaonova.backendvaonova.model.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
public class IdeaService implements IIdeaService {

    @Override
    public ResponseAnalizedIdeaDto analizeIdea(RequestIdeaDto idea) {
        Integer RADIUS = 250; //Meters
        String API_KEY = System.getenv("API_KEY");
        Idea parsedIdea = IdeaMapper.toIdea(idea);

        Double lat1 = parsedIdea.getLatitude() + 0.001347;
        Double lon1 = parsedIdea.getLongitude();
        Double lat2 = parsedIdea.getLatitude() - 0.001347;
        Double lon2 = parsedIdea.getLongitude();
        Double lat3 = parsedIdea.getLatitude();
        Double lon3 = parsedIdea.getLongitude() + 0.001623;
        Double lat4 = parsedIdea.getLatitude();
        Double lon4 = parsedIdea.getLongitude() - 0.001623;

        List<Business> nearbyBusinesses = new ArrayList<>();
        try {
            searchNearby(parsedIdea.getBusinessType(), lat1, lon1, RADIUS, API_KEY).forEach(b -> addIfNotExists(nearbyBusinesses, b));
            searchNearby(parsedIdea.getBusinessType(), lat2, lon2, RADIUS, API_KEY).forEach(b -> addIfNotExists(nearbyBusinesses, b));
            searchNearby(parsedIdea.getBusinessType(), lat3, lon3, RADIUS, API_KEY).forEach(b -> addIfNotExists(nearbyBusinesses, b));
            searchNearby(parsedIdea.getBusinessType(), lat4, lon4, RADIUS, API_KEY).forEach(b -> addIfNotExists(nearbyBusinesses, b));
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar negocios cercanos: " + e.getMessage(), e);
        }

        Integer total = nearbyBusinesses.size();
        Double ratingsTotal = 0D;
        Integer withRating = 0;

        for (Business n : nearbyBusinesses) {
            if (n.getRating() >= 0) {
                ratingsTotal += n.getRating();
                withRating++;
            }
        }

        Double ratingPromedio = withRating > 0 ? ratingsTotal / withRating : 0;

        Risk risk = new Risk((int) Math.min(100, total * (ratingPromedio / 5.0) * 10));
        Integer viabilityScore = Math.max(0, 100 - risk.getValue());

        List<String> recommendations = new ArrayList<>();
        if (total == 0) {
            recommendations.add("No hay negocios similares cerca. Puede ser una oportunidad.");
        } else if (risk.getValue() > 70) {
            recommendations.add("Alta competencia detectada. Considerá diferenciarte claramente.");
        } else if (ratingPromedio > 4.2) {
            recommendations.add("Los negocios existentes tienen buen servicio. Necesitarás igualar o superar su nivel.");
        } else if (ratingPromedio < 3.0 && total > 3) {
            recommendations.add("Hay competencia, pero la calidad parece baja. Puede ser una oportunidad.");
        }

        Competition competition = new Competition(total);
        AnalizedIdea analizedIdea = new AnalizedIdea(
                parsedIdea.getLongitude(),
                parsedIdea.getLatitude(),
                parsedIdea.getBusinessType(),
                parsedIdea.getBudget(),
                risk,
                competition,
                viabilityScore,
                recommendations
        );

        return AnalizedIdeaMapper.toAnalizedIdeaDto(analizedIdea);
    }

    private void addIfNotExists(List<Business> businesses, Business newBusiness) {
        boolean exists = businesses.stream().anyMatch(b ->
                b.getLatitude().equals(newBusiness.getLatitude()) &&
                b.getLongitude().equals(newBusiness.getLongitude()));
        if (!exists) {
            businesses.add(newBusiness);
        }
    }

    private List<Business> searchNearby(BusinessType businessType, Double lat, Double lng, Integer radius, String apiKey) throws Exception {
        String endpoint = "https://places.googleapis.com/v1/places:searchNearby";

        JSONObject requestBody = new JSONObject();
        requestBody.put("includedTypes", new JSONArray().put(businessType.toString().toLowerCase()));

        JSONObject location = new JSONObject();
        location.put("latitude", lat);
        location.put("longitude", lng);

        JSONObject circle = new JSONObject();
        circle.put("center", location);
        circle.put("radius", radius.doubleValue());

        JSONObject locationRestriction = new JSONObject();
        locationRestriction.put("circle", circle);

        requestBody.put("locationRestriction", locationRestriction);

        URL url = new URL(endpoint);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setRequestProperty("X-Goog-Api-Key", apiKey);
        conn.setRequestProperty("X-Goog-FieldMask", "places.displayName,places.location,places.rating");
        conn.setDoOutput(true);

        try (OutputStream os = conn.getOutputStream()) {
            byte[] input = requestBody.toString().getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        int responseCode = conn.getResponseCode();
        if (responseCode != 200) {
            throw new RuntimeException("La API respondió con código: " + responseCode);
        }

        StringBuilder response = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"))) {
            String responseLine;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
        }

        return parseBusiness(response.toString());
    }

    private List<Business> parseBusiness(String json) {
        List<Business> businesses = new ArrayList<>();

        JSONObject obj = new JSONObject(json);
        if (!obj.has("places")) return businesses;

        JSONArray results = obj.getJSONArray("places");
        for (int i = 0; i < results.length(); i++) {
            JSONObject place = results.getJSONObject(i);

            String name = place.optJSONObject("displayName") != null
                    ? place.getJSONObject("displayName").optString("text", "Unknown")
                    : "Unknown";

            JSONObject location = place.optJSONObject("location");
            if (location == null) continue;

            Double latitude = location.optDouble("latitude", Double.NaN);
            Double longitude = location.optDouble("longitude", Double.NaN);

            if (latitude.isNaN() || longitude.isNaN()) continue;

            Double rating = place.has("rating") ? place.optDouble("rating", -1.0) : -1.0;

            businesses.add(new Business(name, latitude, longitude, rating));
        }

        return businesses;
    }
}
