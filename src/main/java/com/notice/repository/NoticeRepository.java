package com.notice.repository;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.notice.domain.Notice;
import com.notice.domain.QNotice;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;

@Repository
public interface NoticeRepository extends CrudRepository<Notice, Long>, QuerydslPredicateExecutor<Notice>{

	public default Predicate makePredicate(String type, String keyword) {
		
		BooleanBuilder builder = new BooleanBuilder();
		
		QNotice notice = QNotice.notice;

		builder.and(notice.id.gt(0));
		
		if(type == null) {
			return builder;
		}
		
		switch(type) {
		case "t":
			builder.and(notice.title.like("%" + keyword + "%"));
			break;
		case "c":
			builder.and(notice.content.like("%" + keyword + "%"));
			break;
		case "w":
			builder.and(notice.writer.like("%" + keyword + "%"));
			break;
		}
		
		return builder;
	}
}
