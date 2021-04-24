package com.notice.controller.api.v1;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.notice.dto.NoticeDto;
import com.notice.service.NoticeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class NoticeRestController {

	private final NoticeService noticeService;
	
	@GetMapping("/notices")
	public ResponseEntity<List<NoticeDto>> getNoticeList(){
		return new ResponseEntity<List<NoticeDto>>(noticeService.getNoticeList(), HttpStatus.OK);
	}
	
	@GetMapping("/notices/{id}")
	public ResponseEntity<NoticeDto> getNotice(@PathVariable("id") Long noticeId){
		return new ResponseEntity<NoticeDto>(noticeService.getNotice(noticeId), HttpStatus.OK);
	}
	
	@PostMapping("/notices")
	public ResponseEntity<Long> saveNotice(NoticeDto noticeDto){
		return new ResponseEntity<Long>(noticeService.saveNotice(noticeDto), HttpStatus.OK);
	}
	
	@PutMapping("/notices/{id}")
	public ResponseEntity<Long> updateNotice(@PathVariable("id") Long noticeId, NoticeDto noticeDto){
		return new ResponseEntity<Long>(noticeService.updateNotice(noticeId, noticeDto), HttpStatus.OK);
	}
	
	@DeleteMapping("/notices/{id}")
	public ResponseEntity<Long> deleteNotice(@PathVariable("id") Long noticeId){
		return new ResponseEntity<Long>(noticeService.deleteNotice(noticeId), HttpStatus.OK);
	}
	
	
	
}
