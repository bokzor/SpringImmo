package be.bcdi.immo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class ImmowebApi {
  private static String API_URL = "https://apigw.immoweb.be/classifieds";

  private ObjectMapper mapper;

  ImmowebApi(ObjectMapper mapper) {
    this.mapper = mapper;
  }

  ImmowebProperty[] search(ImmowebQuery query) throws IOException {

    URL url = new URL(API_URL + query.getQuery());
    HttpURLConnection connection;

    connection = (HttpURLConnection) url.openConnection();
    connection.setRequestMethod("GET");
    HttpURLConnection.setFollowRedirects(true);
    connection.addRequestProperty("Accept", "application/vnd.be.immoweb.classifieds.v2.1+json");
    connection.addRequestProperty("x-iw-api-key", "bd0892bb-d350-4876-ac6e-6fbcead09734");

    // fire http request and handle response
    BufferedInputStream bis = new BufferedInputStream(
      connection.getInputStream());
    StringBuilder content = new StringBuilder();

    int read = 0;
    while (true) {
      read = bis.read();
      if (read == -1) {
        break;
      }
      content.append((char) read);
    }
    return this.mapper.readValue(content.toString(), ImmowebProperty[].class);
  }
}
