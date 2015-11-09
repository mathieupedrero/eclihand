package com.pedrero.eclihand.converter.in;

import static com.pedrero.eclihand.converter.ConverterUtils.map;

import com.pedrero.eclihand.model.domain.DataObject;
import com.pedrero.eclihand.model.domain.Illustrable;
import com.pedrero.eclihand.model.dto.DataObjectDto;
import com.pedrero.eclihand.model.dto.IllustrableDto;

public abstract class InConverterUtils {

	private InConverterUtils() {
	}

	public static void mapIllustrableFields(IllustrableDto from, Illustrable to) {
		mapDataObjectFields(from, to);
	}

	public static void mapDataObjectFields(DataObjectDto from, DataObject to) {
		map(from::getId, to::setId);
		map(from::getIndex, to::setIndex);
	}

}
