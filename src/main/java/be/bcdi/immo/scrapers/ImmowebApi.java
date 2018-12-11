package be.bcdi.immo.scrapers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

@Service
public class ImmowebApi {

    Logger logger = LoggerFactory.getLogger(ImmowebApi.class);

    private static String API_URL = "https://apigw.immoweb.be/classifieds";

    private ObjectMapper mapper;

    ImmowebApi(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    ImmowebProperty[] search(ImmowebQuery query) throws IOException {

        URL url = new URL(API_URL + query.getQuery());
        logger.info("Querying : " + url);
        HttpURLConnection connection;

        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        HttpURLConnection.setFollowRedirects(true);
        connection.addRequestProperty("Accept", "application/vnd.be.immoweb.classifieds.v2.1+json");
        connection.addRequestProperty("x-iw-api-key", "bd0892bb-d350-4876-ac6e-6fbcead09734");
        connection.setRequestProperty("Accept-Charset", "UTF-8");

        // fire http request and handle response
        try (BufferedReader input = new BufferedReader(
                new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8))) {

            StringBuilder content = new StringBuilder();

            int read = 0;
            while (true) {
                read = input.read();
                if (read == -1) {
                    break;
                }
                content.append((char) read);
            }
            return this.mapper.readValue(content.toString(), ImmowebProperty[].class);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        return new ImmowebProperty[0]; // Empty
    }
}
