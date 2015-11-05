package com.pedrero.eclihand.rest;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.pedrero.eclihand.model.dto.DataObjectDto;
import com.pedrero.eclihand.model.dto.PageableDto;
import com.pedrero.eclihand.service.common.DataObjectService;

public abstract class AbstractEntityWs<T extends DataObjectDto> extends AbstractWs {

	protected abstract DataObjectService<T> getService();

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public T findById(@PathVariable(value = "id") Long id) {
		return getService().findById(id);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/all")
	public List<T> findAll() {
		return getService().findAll();
	}

	@RequestMapping(method = RequestMethod.GET, value = "/page")
	public List<T> find(@RequestBody PageableDto pageable) {
		return getService().findAll(pageable);
	}

	@RequestMapping(method = RequestMethod.POST)
	public Long save(@RequestBody T entity) {
		return getService().save(entity).getId();
	}

	@RequestMapping(method = RequestMethod.PUT)
	public void update(@RequestBody T entity) {
		getService().update(entity);
	}

}