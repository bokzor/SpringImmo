package be.bcdi.immo.utils;

import be.bcdi.immo.enums.PropertyTypeEnum;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
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

    String json;
    Map<String, Object> mapped;

    @Before
    public void before() throws IOException {
        json = getFileContent("data/nested.json");
        ObjectMapper mapper = new ObjectMapper();
        mapped = mapper.readValue(json, new TypeReference<Map<String, Object>>() {
        });
    }

    @Test
    public void shouldAccessNestedProperty() throws IOException, InstantiationException, IllegalAccessException {
        Optional<Boolean> resultBoolean = get(mapped, "l1.l2.boolean", Boolean.class);
        assertTrue(resultBoolean.get());
    }

    @Test
    public void shouldConvertEnum() throws InstantiationException, IllegalAccessException {
        Optional<PropertyTypeEnum> resultEnum = get(mapped, "l1.l2.enum", PropertyTypeEnum.class);
        assert (resultEnum.isPresent());
    }

    @Test
    public void shouldConvertToInteger() throws InstantiationException, IllegalAccessException {
        Optional<Integer> resultInteger = get(mapped, "l1.l2.integer", Integer.class);
        assert (resultInteger.get() == 6);
    }

    @Test
    public void shouldConvertToTest() throws InstantiationException, IllegalAccessException {
        Optional<String> resultInteger = get(mapped, "l1.l2.string", String.class);
        assert (resultInteger.get().equals("test"));
    }

    private String getFileContent(String fileName) throws IOException {
        File resource = new ClassPathResource(fileName).getFile();
        return new String(
                Files.readAllBytes(resource.toPath()));
    }

}
