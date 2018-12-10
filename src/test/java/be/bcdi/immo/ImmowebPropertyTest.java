package be.bcdi.immo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class ImmowebPropertyTest {

  @Value("classpath:data/for_rent.json")
  private Resource propertiesForRent;

  @Test
  public void shouldCorrectlySerialize() throws IOException {
    String json = getFileContent("data/for_rent.json");
    ObjectMapper mapper = new ObjectMapper();
    ImmowebProperty[] immoWebProperties =  mapper.readValue(json, ImmowebProperty[].class);
  }

  private String getFileContent(String fileName) throws IOException {
    File resource = new ClassPathResource(fileName).getFile();
    return  new String(
      Files.readAllBytes(resource.toPath()));
  }
}
