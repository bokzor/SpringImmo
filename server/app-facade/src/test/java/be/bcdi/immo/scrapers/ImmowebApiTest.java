package be.bcdi.immo.scrapers;


import be.bcdi.immo.model.enums.PropertyTypeEnum;
import org.junit.Test;

import java.io.IOException;

public class ImmowebApiTest {


  @Test
  public void shouldQueryImmoweb() throws IOException {
    ImmowebQuery immowebQuery = new ImmowebQuery(ImmowebProperty.TransactionTypeEnum.FOR_RENT, PropertyTypeEnum.APARTMENT);
    immowebQuery.addPostalCode("4000");
  //  var result = new ImmowebApi(new ObjectMapper()).search(immowebQuery);
    //assert result.length > 0;
  }
}
