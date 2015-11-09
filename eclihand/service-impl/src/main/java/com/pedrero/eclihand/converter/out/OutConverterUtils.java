package com.pedrero.eclihand.converter.out;

import static com.pedrero.eclihand.converter.ConverterUtils.map;

import com.pedrero.eclihand.model.domain.DataObject;
import com.pedrero.eclihand.model.domain.Illustrable;
import com.pedrero.eclihand.model.dto.DataObjectDto;
import com.pedrero.eclihand.model.dto.IllustrableDto;

public abstract class OutConverterUtils {

	private OutConverterUtils() {
	}

	public static void mapIllustrableFields(Illustrable from, IllustrableDto to) {
		mapDatoObjectFields(from, to);
	}

	public static void mapDatoObjectFields(DataObject from, DataObjectDto to) {
		map(from::getId, to::setId);
		map(from::getIndex, to::setIndex);
	}

}
