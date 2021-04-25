package com.notice.controller.api.v1;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.notice.dto.NoticeDTO;
import com.notice.service.NoticeService;
import com.notice.vo.PageMaker;
import com.notice.vo.PageVO;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class NoticeRestController {

	private final NoticeService noticeService;
	
	@GetMapping("/notices")
	public ResponseEntity<PageMaker<NoticeDTO>> getNoticeList(@ModelAttribute("pageVO") PageVO pageVO){
		return new ResponseEntity<PageMaker<NoticeDTO>>(noticeService.getNoticeList(pageVO), HttpStatus.OK);
	}
	
	@GetMapping("/notices/{id}")
	public ResponseEntity<NoticeDTO> getNotice(@PathVariable("id") Long noticeId){
		return new ResponseEntity<NoticeDTO>(noticeService.getNotice(noticeId), HttpStatus.OK);
	}
	
	@PostMapping("/notices")
	public ResponseEntity<Long> saveNotice(NoticeDTO noticeDTO){
		return new ResponseEntity<Long>(noticeService.saveNotice(noticeDTO), HttpStatus.OK);
	}
	
	@PutMapping("/notices/{id}")
	public ResponseEntity<Long> updateNotice(@PathVariable("id") Long noticeId, NoticeDTO noticeDTO){
		return new ResponseEntity<Long>(noticeService.updateNotice(noticeId, noticeDTO), HttpStatus.OK);
	}
	
	@DeleteMapping("/notices/{id}")
	public ResponseEntity<Long> deleteNotice(@PathVariable("id") Long noticeId){
		return new ResponseEntity<Long>(noticeService.deleteNotice(noticeId), HttpStatus.OK);
	}
	
	
	
}
