package com.pedrero.eclihand.dao.request.factory;

import com.pedrero.eclihand.dao.request.PageableParam;
import com.pedrero.eclihand.model.dto.PageableDto;

public interface PageableFactory {
	PageableParam createFrom(PageableDto pageableDto);
}
