package com.example.limelight;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class HttpVisionSource implements VisionSource {

    private static final String LIMELIGHT_URL =
            "http://limelight.local:5807/results";

    private final HttpClient client = HttpClient.newHttpClient();
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public List<VisionTarget> getTargets() {
        List<VisionTarget> targets = new ArrayList<>();

        try {
            // Attempt to connect to the Limelight
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(LIMELIGHT_URL))
                    .GET()
                    .build();
            
            // Send the request and get the response
            HttpResponse<String> response =
                    client.send(request, HttpResponse.BodyHandlers.ofString());

            // Check if the response is successful
            JsonNode root = mapper.readTree(response.body());

            // Check if Fiducial array exists. If not, return empty list.
            JsonNode fiducials = root.path("Fiducial");
            if (fiducials.isMissingNode() || !fiducials.isArray() || fiducials.size() == 0) {
                System.out.println("No AprilTags detected");
                return targets;
            }

            // If we have tags, print how many we found
            System.out.println("Detected " + fiducials.size() + " AprilTag(s)");

            // Process each detected tag
            for (JsonNode tag : fiducials) {
                int id = tag.path("fID").asInt();

                // Safe pose reading
                JsonNode pose = tag.path("t6t_cs");
                double x = 0, y = 0, z = 0;
                if (pose.isArray() && pose.size() >= 3) {
                    x = pose.get(0).asDouble();
                    y = pose.get(1).asDouble();
                    z = pose.get(2).asDouble();
                }

                // Latency is directly under root
                double latency = root.path("tl").asDouble();

                targets.add(new VisionTarget(id, x, y, z, latency));
            }
            
            System.out.println("Finished processing AprilTags");
            // Thread.sleep(1000);

        } catch (Exception e) {
            System.err.println("Limelight not reachable: " + e.getMessage());
        }

        return targets;
    }
}
