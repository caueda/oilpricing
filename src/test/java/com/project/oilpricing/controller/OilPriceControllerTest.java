package com.project.oilpricing.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class OilPriceControllerTest {

	@Autowired
	private MockMvc mvc;

	@InjectMocks
    private OilPriceController oilPriceController;
	
	@Before
	public void setUp() {
	}
	
    @Test
    public void contexLoads() throws Exception {
        assertThat(oilPriceController).isNotNull();
    }
	
	@Test
	public void testGetOils() throws Exception {
		mvc.perform( MockMvcRequestBuilders
			      .get("/list/oil")
			      .accept(MediaType.APPLICATION_JSON))
			      .andDo(print())
			      .andExpect(status().isOk());
	}

}
