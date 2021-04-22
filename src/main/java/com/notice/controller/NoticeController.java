package com.notice.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.notice.domain.Notice;
import com.notice.service.NoticeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1")
public class NoticeController {

	private final NoticeService noticeService;
	
//	@GetMapping
//	public List<Notice> getNoticeList(){
//		return ;
//	}
}
