package com.notice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.notice.dto.NoticeDTO;
import com.notice.service.NoticeService;
import com.notice.vo.PageMaker;
import com.notice.vo.PageVO;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/notices")
@RequiredArgsConstructor
public class NoticeController {

	private final NoticeService noticeService;
	
	@GetMapping("")
	public String list(@ModelAttribute("pageVO") PageVO pageVO, Model model) {
		
		PageMaker<NoticeDTO> result = noticeService.getNoticeList(pageVO);
		model.addAttribute("result", result);
		
		return "notices/list";
	}
	
	@GetMapping("/{id}")
	public String detail(
			@PathVariable("id") Long noticeId,
			@ModelAttribute("pageVO") PageVO vo,
			Model model) {
		
		NoticeDTO notice = noticeService.getNotice(noticeId);
		model.addAttribute("notice", notice);
		
		return "notices/detail";
	}
	
	@GetMapping("/write")
	public String write() {
		
		return "notices/write";
	}
	
	@GetMapping("/edit/{id}")
	public String edit(
			@PathVariable("id") Long noticeId,
			@ModelAttribute("pageVO") PageVO vo,
			Model model) {
		
		NoticeDTO notice = noticeService.getNotice(noticeId);
		model.addAttribute("notice", notice);
		
		return "notices/edit";
	}
	
	@PostMapping("")
	public String register(
			@ModelAttribute("notice") NoticeDTO noticeDTO,
			RedirectAttributes rttr) {
		
		noticeService.saveNotice(noticeDTO);
		
		return "redirect:/notices";
	}
	
	@PostMapping("/edit/{id}")
	public String update(
			@PathVariable("id") Long noticeId,
			@ModelAttribute("notice") NoticeDTO noticeDTO,
			@ModelAttribute("pageVO") PageVO pageVO,
			RedirectAttributes rttr) {
		
		Long id = noticeService.updateNotice(noticeId, noticeDTO);
		
		rttr.addFlashAttribute("msg", "success");
		rttr.addAttribute("id", id);
		
		rttr.addAttribute("page", pageVO.getPage());
		rttr.addAttribute("size", pageVO.getSize());
		rttr.addAttribute("type", pageVO.getType());
		rttr.addAttribute("keyword", pageVO.getKeyword());
		
		return "redirect:/notices/"+id;
	}
	
	@PostMapping("/delete/{id}")
	public String delete(
			@PathVariable("id") Long noticeId,
			@ModelAttribute("pageVO") PageVO pageVO,
			RedirectAttributes rttr) {
		
		noticeService.deleteNotice(noticeId);
		
		rttr.addFlashAttribute("msg", "success");
		
		rttr.addAttribute("page", pageVO.getPage());
		rttr.addAttribute("size", pageVO.getSize());
		rttr.addAttribute("type", pageVO.getType());
		rttr.addAttribute("keyword", pageVO.getKeyword());
		
		return "redirect:/notices";
	}	
	
}
