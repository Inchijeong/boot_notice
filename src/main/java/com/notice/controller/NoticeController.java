package com.notice.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.notice.dto.NoticeDto;
import com.notice.service.NoticeService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/notices")
@RequiredArgsConstructor
public class NoticeController {

	private final NoticeService noticeService;
	
	@GetMapping("")
	public String list(Model model) {
		List<NoticeDto> notices = noticeService.getNoticeList();
		model.addAttribute("notices", notices);
		
		return "/notices/list.html";
	}
	
	@GetMapping("/{id}")
	public String detail(@PathVariable("id") Long noticeId, Model model) {
		NoticeDto notice = noticeService.getNotice(noticeId);
		model.addAttribute("notice", notice);
		
		return "/notices/detail.html";
	}
	
	@GetMapping("/write")
	public String write() {
		
		return "/notices/write.html";
	}
	
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long noticeId, Model model) {
		NoticeDto notice = noticeService.getNotice(noticeId);
		model.addAttribute("notice", notice);
		
		return "/notices/edit.html";
	}
}
