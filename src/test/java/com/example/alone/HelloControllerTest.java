package com.example.alone;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.example.alone.dto.HelloResponseDto;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class HelloControllerTest {
	@Autowired
	private MockMvc mvc;
	
	@Test
	public void hello_return() throws Exception {
		String hello = "hello";
		mvc.perform(get("/hello"))
        .andExpect(status().isOk());
//        .andExpect(content().string(hello));
	}
	
	 @Test
	 public void helloDto가_리턴된다() throws Exception {
	        String name = "hello";
	        int amount = 1000;

	        mvc.perform(
	                    get("/hello/dto")
	                            .param("name", name)
	                            .param("amount", String.valueOf(amount)))
	                .andExpect(status().isOk())
	                .andExpect(jsonPath("$.name", is(name)))
	                .andExpect(jsonPath("$.amount", is(amount)));
	    }
	
	@Test
	public void lombokTest() {
		//given
		String name = "test";
		int amount = 1000;
		
		//when
		HelloResponseDto dto = new HelloResponseDto(name, amount);
		
		//then
		assertThat(dto.getName()).isEqualTo(name);
		assertThat(dto.getAmount()).isEqualTo(amount);
	}
	
	
}
