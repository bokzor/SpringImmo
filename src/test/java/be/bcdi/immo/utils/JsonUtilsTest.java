package be.bcdi.immo.utils;

import be.bcdi.immo.ImmowebProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Map;
import java.util.Optional;

import static be.bcdi.immo.utils.JsonUtils.get;
import static org.junit.Assert.*;

public class JsonUtilsTest {

  @Test
  public void shouldCorrectlySerialize() throws IOException {
    String json = getFileContent("data/nested.json");
    ObjectMapper mapper = new ObjectMapper();
    Map<String, Object> map =  mapper.readValue(json, new TypeReference<Map<String, Object>>(){});
    Optional<Boolean> result = get(map, "flagsAndStatistics.flags.isALifeAnnuitySale", Boolean.class);
  }

  private String getFileContent(String fileName) throws IOException {
    File resource = new ClassPathResource(fileName).getFile();
    return  new String(
      Files.readAllBytes(resource.toPath()));
  }

}
