package com.notice;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.notice.dto.NoticeDTO;
import com.notice.vo.PageVO;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("API 통합 테스트")
public class NoticeApiTests {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext ctx;
	
	@BeforeEach
	void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx)
				.addFilter(new CharacterEncodingFilter("UTF-8", true))
				.build();
	}
	
	@Autowired
	private ObjectMapper objectMapper;

	@Test
	@DisplayName("01. 공지사항 전체 조회")
	public void _01_findAllNoticeApiTest() throws Exception {
		
		String content = objectMapper.writeValueAsString(new PageVO());
		
		ResultActions result = mockMvc.perform(
				get("/api/v1/notices")
					.content(content)
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON)
		);
		result.andDo(print())
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.result.content").isArray())
			.andExpect(jsonPath("$.result.content.length()", is(10)));
	}
	
	@Test
	@DisplayName("02. 공지사항 조회")
	public void _02_findOneNoticeApiTest() throws Exception {
		
		ResultActions result = mockMvc.perform(
				get("/api/v1/notices/1")
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON)
		);
		result.andDo(print())
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.id").value(1l));
	}
	
	@Test
	@DisplayName("03. 공지사항 등록")
	public void _03_saveNoticeApiTest() throws Exception {
		
		NoticeDTO noticeDTO = new NoticeDTO();
		noticeDTO.setWriter("테스트 작성자");
		noticeDTO.setTitle("테스트 제목");
		noticeDTO.setContent("테스트 내용");		
		
		String content = objectMapper.writeValueAsString(noticeDTO);
		
		ResultActions result = mockMvc.perform(
				post("/api/v1/notices/")
					.content(content)
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON)
		);
		result.andDo(print())
			.andExpect(status().isOk());
	}
	
	@Test
	@DisplayName("04. 공지사항 수정")
	public void _03_updateNoticeApiTest() throws Exception {
		
		NoticeDTO noticeDTO = new NoticeDTO();
		noticeDTO.setId(1l);
		noticeDTO.setWriter("변경된 작성자");
		noticeDTO.setTitle("변경된 제목");
		noticeDTO.setContent("변경된 내용");		
		
		String content = objectMapper.writeValueAsString(noticeDTO);
		
		ResultActions result = mockMvc.perform(
				put("/api/v1/notices/1")
					.content(content)
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON)
		);
		result.andDo(print())
			.andExpect(status().isOk());
		
		ResultActions result2 = mockMvc.perform(
				get("/api/v1/notices/1")
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON)
		);
		result2.andDo(print())
			.andExpect(status().isOk());
	}
	
	@Test
	@DisplayName("05. 공지사항 삭제")
	public void _03_deleteNoticeApiTest() throws Exception {
		
		ResultActions result = mockMvc.perform(
				delete("/api/v1/notices/1")
		);
		result.andDo(print())
			.andExpect(status().isOk());
		
		ResultActions result2 = mockMvc.perform(
				get("/api/v1/notices/1")
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON)
		);
		result2.andDo(print())
			.andExpect(status().isOk());
	}
}
