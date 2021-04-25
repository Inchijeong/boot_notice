package com.notice.dto;

import java.time.LocalDateTime;

import com.notice.domain.Notice;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
public class NoticeDTO {
	
	private Long id;
    private String writer;
    private String title;
    private String content;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
	
	public NoticeDTO(Notice entity) {
		this.id = entity.getId();
		this.writer = entity.getWriter();
		this.title = entity.getTitle();
		this.content = entity.getContent();
		this.createdDate = entity.getCreatedDate();
		this.modifiedDate = entity.getModifiedDate();
	}
	
	public Notice toEntity() {
		return Notice.builder()
				.id(this.id)
				.writer(this.writer)
				.title(this.title)
				.content(this.content)
				.build();
	}
}
