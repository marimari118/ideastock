package com.gmail.marimari118yt.ideastock.dto;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ValidationException extends IllegalStateException {

	private static final long serialVersionUID = 1L;

	private Map<String, List<String>> details = new HashMap<>();
	
	public void addDetail(String fieldName, String cause) {
		List<String> field;
		if ((field = details.get(fieldName)) == null) {
			field = new LinkedList<>();
			details.put(fieldName, field);
		}
		field.add(cause);
	}
	
	public Map<String, List<String>> getDetails() {
		return details;
	}

}
