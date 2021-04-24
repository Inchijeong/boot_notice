package com.notice.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.notice.domain.Notice;
import com.notice.dto.NoticeDto;
import com.notice.repository.NoticeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NoticeService {

	private final NoticeRepository noticeRepository;
	
	public List<NoticeDto> getNoticeList() {
		return noticeRepository.findAll()
				.stream()
				.map(NoticeDto::new)
				.collect(Collectors.toList());
	}
	
	public NoticeDto getNotice(Long noticeId) {
		Optional<Notice> noticeWrapper = noticeRepository.findById(noticeId);
		NoticeDto noticeDto = null;
		
		if(noticeWrapper.isPresent()) {
			noticeDto = new NoticeDto(noticeWrapper.get());
		}else {
			noticeDto = new NoticeDto();
		}
		return noticeDto;
	}
	
	public Long saveNotice(NoticeDto noticeDto) {
		Notice notice = noticeRepository.save(noticeDto.toEntity());
		return notice.getId();
	}
	
	public Long updateNotice(Long noticeId, NoticeDto noticeDto) {
		Optional<Notice> noticeWrapper = noticeRepository.findById(noticeId);
		if(noticeWrapper.isPresent()) {
			noticeRepository.save(noticeDto.toEntity());
		}
		return noticeId;
	}
	
	public Long deleteNotice(Long noticeId) {
		noticeRepository.deleteById(noticeId);
		return noticeId; 
	}
}
