package com.gr.comcome.search_pd.model;

import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SearchOption {
	private ArrayList<String> brand;
	private ArrayList<String> screenSize;
	private ArrayList<String> cpu;
	private ArrayList<String> memory;
}
