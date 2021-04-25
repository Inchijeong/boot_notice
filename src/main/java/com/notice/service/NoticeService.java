package com.notice.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.notice.domain.Notice;
import com.notice.dto.NoticeDTO;
import com.notice.repository.NoticeRepository;
import com.notice.vo.PageMaker;
import com.notice.vo.PageVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NoticeService {

	private final NoticeRepository noticeRepository;
	
	public PageMaker<NoticeDTO> getNoticeList(PageVO pageVO) {
		Pageable page = pageVO.makePageable(0, "id");

		Page<Notice> result = noticeRepository.findAll(
				noticeRepository.makePredicate(pageVO.getType(), pageVO.getKeyword()), page);

		return new PageMaker<NoticeDTO>(result.map(NoticeDTO::new));
	}
	
	public NoticeDTO getNotice(Long noticeId) {
		Optional<Notice> noticeWrapper = noticeRepository.findById(noticeId);
		NoticeDTO noticeDTO = null;
		
		if(noticeWrapper.isPresent()) {
			noticeDTO = new NoticeDTO(noticeWrapper.get());
		}else {
			noticeDTO = new NoticeDTO();
		}
		return noticeDTO;
	}
	
	public Long saveNotice(NoticeDTO noticeDTO) {
		Notice notice = noticeRepository.save(noticeDTO.toEntity());
		return notice.getId();
	}
	
	public Long updateNotice(Long noticeId, NoticeDTO noticeDTO) {
		Optional<Notice> noticeWrapper = noticeRepository.findById(noticeId);
		if(noticeWrapper.isPresent()) {
			noticeRepository.save(noticeDTO.toEntity());
		}
		return noticeId;
	}
	
	public Long deleteNotice(Long noticeId) {
		noticeRepository.deleteById(noticeId);
		return noticeId; 
	}
}
