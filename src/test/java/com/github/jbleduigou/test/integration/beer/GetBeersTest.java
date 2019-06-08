package com.github.jbleduigou.test.integration.beer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = com.github.jbleduigou.beer.Application.class)
@AutoConfigureMockMvc
public class GetBeersTest {

  @Autowired
  private MockMvc mvc;

  @Test
  public void shouldRetrieveAllBeers() throws Exception {
    String expectedResponse = "[{\"id\":1,\"name\":\"Punk IPA\",\"alcoholByVolume\":5.6},{\"id\":2,\"name\":\"Nanny State\",\"alcoholByVolume\":0.5}]";
    mvc.perform(MockMvcRequestBuilders.get("/beers/").accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().string(equalTo(expectedResponse)));
  }

  @Test
  public void shouldRetrieveOneAlcoholFreeBeer() throws Exception {
    String expectedResponse = "[{\"id\":2,\"name\":\"Nanny State\",\"alcoholByVolume\":0.5}]";
    mvc.perform(MockMvcRequestBuilders.get("/beers/non-alcoholic/").accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().string(equalTo(expectedResponse)));
  }
}
