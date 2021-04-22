package com.notice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.notice.dto.NoticeDto;
import com.notice.repository.NoticeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NoticeService {

	private final NoticeRepository noticeRepository;
	
//	public List<NoticeDto> getNoticeList() {
//		noticeRepository.findAll()
//	}
}
