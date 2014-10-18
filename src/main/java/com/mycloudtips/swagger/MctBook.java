/**
 *  Copyright 2014 Michel Jaczynski
 *  
 *  Visit my blog for more explanation and tips: 
 *  http://www.mycloudtips.com/
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.mycloudtips.swagger;

import java.util.ArrayList;
import java.util.List;

/**
 * An simple model object used in the API.
 * 
 * @author Michel Jaczynski
 *
 */
public class MctBook {

	private String id;
	private String title;
	private int pages;
	private List<String> authors = new ArrayList<String>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	public List<String> getAuthors() {
		return authors;
	}

}
