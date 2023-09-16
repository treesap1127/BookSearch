package com.core.module.book.vo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import lombok.Setter;
@Setter
public class IndexableData {
	private List<Map<String, String>> indexableData = new ArrayList<>();

    public List<Map<String, String>> get() {
        return this.indexableData;
    }

    public boolean isNullOrEmpty() {
        return indexableData == null || indexableData.isEmpty();
    }

    public int size() {
        return indexableData.size();
    }

}
