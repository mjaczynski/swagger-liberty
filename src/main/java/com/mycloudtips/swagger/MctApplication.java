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

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

import com.wordnik.swagger.jaxrs.listing.ApiDeclarationProvider;
import com.wordnik.swagger.jaxrs.listing.ApiListingResourceJSON;
import com.wordnik.swagger.jaxrs.listing.ResourceListingProvider;

/**
 * This is the entry point to configure the REST application. It is referenced
 * in the web.xml.
 * 
 * @author Michel Jaczynski
 *
 */
public class MctApplication extends Application {

	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> classes = new HashSet<Class<?>>();

		// these are the Swagger provider and resources to reply to the
		// meta-data
		// API requests in JSON.
		classes.add(ApiDeclarationProvider.class);
		classes.add(ResourceListingProvider.class);
		classes.add(ApiListingResourceJSON.class);

		return classes;
	}

	@Override
	public Set<Object> getSingletons() {

		Set<Object> singletons = new HashSet<Object>();

		// this is the application resource, declared as a singleton
		singletons.add(new MctResource());

		return singletons;

	}

}
