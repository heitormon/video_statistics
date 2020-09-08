package br.com.heitor.samba.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.heitor.samba.dto.StatisticsDTO;
import br.com.heitor.samba.dto.VideoDTO;
import br.com.heitor.samba.service.VideoService;

@SpringBootTest
@AutoConfigureMockMvc
public class VideoControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private VideoService videoService;

	@Test
	void verificandoFluxoPrincipal() throws Exception {
		VideoDTO newVideo1 = new VideoDTO(200.0, new Date().getTime());
		VideoDTO newVideo2 = new VideoDTO(100.0, new Date().getTime());
		VideoDTO newVideoError = new VideoDTO(200.5, 1599527070289L);
		
		mockMvc.perform(
				post("/videos").contentType("application/json").content(objectMapper.writeValueAsString(newVideo1)))
				.andExpect(status().isOk());
		mockMvc.perform(
				post("/videos").contentType("application/json").content(objectMapper.writeValueAsString(newVideo2)))
				.andExpect(status().isOk());
		mockMvc.perform(
				post("/videos").contentType("application/json").content(objectMapper.writeValueAsString(newVideoError)))
				.andExpect(status().is(204));
		StatisticsDTO statistics = videoService.getStatistics();
		assertThat(statistics.getAvg()).isEqualTo(150);
		assertThat(statistics.getCount()).isEqualTo(2);
		assertThat(statistics.getMax()).isEqualTo(200);
		assertThat(statistics.getMin()).isEqualTo(100);
		assertThat(statistics.getSum()).isEqualTo(300);
		
		mockMvc.perform(
				delete("/videos"))
				.andExpect(status().is(204));
		StatisticsDTO statistics2 = videoService.getStatistics();
		assertThat(statistics2.getAvg()).isEqualTo(0);
		assertThat(statistics2.getCount()).isEqualTo(0);
		assertThat(statistics2.getMax()).isEqualTo(0);
		assertThat(statistics2.getMin()).isEqualTo(0);
		assertThat(statistics2.getSum()).isEqualTo(0);
	}


}
