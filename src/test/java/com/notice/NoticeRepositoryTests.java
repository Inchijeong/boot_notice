package com.notice;

import java.util.Optional;
import java.util.stream.LongStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.notice.domain.Notice;
import com.notice.dto.NoticeDTO;
import com.notice.repository.NoticeRepository;

import lombok.extern.java.Log;

@Log
@DataJpaTest
@Transactional(propagation = Propagation.NOT_SUPPORTED)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class NoticeRepositoryTests {

	@Autowired
	private NoticeRepository noticeRepository;
	
	@Test
	public void createNoticeTest() {
		
		LongStream.range(0, 10).forEach(i -> {
			
			Notice notice = Notice.builder()
					.writer("작성자"+i)
					.title("제목"+i)
					.content("내용"+i)
					.build();
			
			noticeRepository.save(notice);
		});
	}
	
	@Test
	public void readNoticeTest() {
		
		Optional<Notice> noticeWrapper = noticeRepository.findById(1l);
		log.info("단일 조회");
		noticeWrapper.ifPresent(noti -> log.info(new NoticeDTO(noti).toString()));
		
//		List<Notice> noticeList = noticeRepository.findAll();		
//		log.info("목록 조회");
//		noticeList.stream().forEach(noti -> log.info(new NoticeDTO(noti).toString()));
	}
	
	@Test
	public void updateNoticeTest() {
		
		Optional<Notice> noticeWrapper = noticeRepository.findById(2l);
		
		noticeWrapper.ifPresent(noti -> log.info(new NoticeDTO(noti).toString()));
		
		noticeWrapper.ifPresent(noti -> {
			
			Notice editedNotice = Notice.builder()
					.id(noti.getId())
					.writer("변경된 작성자")
					.title("변경된 제목")
					.content("변경된 내용")
					.build();
			
			noticeRepository.save(editedNotice);
		});
		
		noticeRepository.findById(1l).ifPresent(noti -> log.info(new NoticeDTO(noti).toString()));
	}
	
	@Test
	public void deleteNoticeTest() {
		
		noticeRepository.findById(2l).ifPresent(noti -> {
			noticeRepository.deleteById(noti.getId());			
		});
	}
	
}
