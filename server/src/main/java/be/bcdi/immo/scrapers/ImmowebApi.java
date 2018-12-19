package be.bcdi.immo.scrapers;

import be.bcdi.immo.entities.ImmoProperty;
import be.bcdi.immo.enums.PropertyTypeEnum;
import be.bcdi.immo.enums.SourceEnum;
import be.bcdi.immo.repositories.ImmoPropertyJpaRepository;
import be.bcdi.immo.repositories.ImmoPropertyRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.var;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ImmowebApi {

    Logger logger = LoggerFactory.getLogger(ImmowebApi.class);

    private static String API_URL = "https://apigw.immoweb.be/classifieds";

    private ObjectMapper mapper;
    private ImmowebMapper immowebMapper;
    private ImmoPropertyRepository immoPropertyRepository;
    private ImmoPropertyJpaRepository immoPropertyJpaRepository;

    ImmowebApi(ObjectMapper mapper, ImmowebMapper immowebMapper, ImmoPropertyRepository immoPropertyRepository, ImmoPropertyJpaRepository immoPropertyJpaRepository) {
        this.mapper = mapper;
        this.immowebMapper = immowebMapper;
        this.immoPropertyRepository = immoPropertyRepository;
        this.immoPropertyJpaRepository = immoPropertyJpaRepository;
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

    public void go() throws IOException, InterruptedException {

        for(ImmowebQuery query: getQueries()) {
            Thread.sleep(10000);
            var results = this.search(query);
            List<ImmoProperty> properties = Arrays.stream(results).map(immowebMapper::immowebDTOtoProperty).collect(Collectors.toList());
            this.immoPropertyRepository.saveAll(properties);
        }

    }

    public List<ImmowebQuery> getQueries() {
        ArrayList<ImmowebQuery> queries = new ArrayList<>();

        ImmowebQuery immowebHouseToSaleQuery = new ImmowebQuery(ImmowebProperty.TransactionTypeEnum.FOR_SALE, PropertyTypeEnum.HOUSE);
        immowebHouseToSaleQuery.setProvince(ImmowebQuery.ProvinceEnum.LIEGE);

        ImmowebQuery immowebHouseToRentQuery = new ImmowebQuery(ImmowebProperty.TransactionTypeEnum.FOR_RENT, PropertyTypeEnum.HOUSE);
        immowebHouseToRentQuery.setProvince(ImmowebQuery.ProvinceEnum.LIEGE);

        ImmowebQuery immowebFlatToSaleQuery = new ImmowebQuery(ImmowebProperty.TransactionTypeEnum.FOR_SALE, PropertyTypeEnum.APARTMENT);
        immowebFlatToSaleQuery.setProvince(ImmowebQuery.ProvinceEnum.LIEGE);

        ImmowebQuery immowebFlatToRentQuery = new ImmowebQuery(ImmowebProperty.TransactionTypeEnum.FOR_RENT, PropertyTypeEnum.APARTMENT);
        immowebFlatToRentQuery.setProvince(ImmowebQuery.ProvinceEnum.LIEGE);

        queries.add(immowebHouseToSaleQuery);
        queries.add(immowebHouseToRentQuery);
        queries.add(immowebFlatToSaleQuery);
        queries.add(immowebFlatToRentQuery);

        return queries;

    }

}
