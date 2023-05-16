package com.udacity.pricing;

import com.udacity.pricing.domain.price.Price;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import com.udacity.pricing.domain.price.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class PricingServiceApplicationTests {
	@LocalServerPort
	private int port;

	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private TestRestTemplate testRestTemplate;

	@Test
	public void contextLoads() {

		assertThat(this.testRestTemplate.getForEntity("http://localhost:" + port + "/services/price?vehicleId=1", Price.class).getStatusCode(), equalTo(HttpStatus.OK));
	}


	@Test
	public void testGetPrice() throws Exception {
		mockMvc.perform(get(("/services/price")).param("vehicleId","1"))
				.andExpect(status().isOk());
	}
}
