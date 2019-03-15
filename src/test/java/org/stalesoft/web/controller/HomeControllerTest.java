package org.stalesoft.web.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles(value="test")
@AutoConfigureMockMvc(secure=true)
public class HomeControllerTest {

	@Autowired
	MockMvc mockMvc;

	@Test
	public void testHome_shouldRenderRepositories() throws Exception {
		
		mockMvc.perform(get("/app/home")).andExpect(view().name("app/repositories"));
		
	}
	
}
