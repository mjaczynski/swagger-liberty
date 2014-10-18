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

import java.net.URI;
import java.util.Collection;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

/**
 * The JAX-RS resource.
 * 
 * @author Michel Jaczynski
 *
 */
@Path("/books")
@Api(value = "/books", description = "Book management API")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MctResource {

	// simple book library in memory
	ConcurrentHashMap<String, MctBook> library = new ConcurrentHashMap<String, MctBook>();

	@GET
	@ApiOperation(value = "Returns the list of books from the library.", response = MctBook.class, responseContainer = "List")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 500, message = "Internal error") })
	public Collection<MctBook> getBooks() {
		return library.values();
	}

	@POST
	@ApiOperation(value = "Adds a book to the library.")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "created, assigned id returned in the Location header"),
			@ApiResponse(code = 500, message = "Internal error") })
	public Response addBook(MctBook book, @Context UriInfo uriInfo) {
		book.setId(UUID.randomUUID().toString());
		library.put(book.getId(), book);
		return Response.created(
				URI.create(uriInfo.getBaseUri().toString() + "books/"
						+ book.getId())).build();
	}

	@DELETE
	@ApiOperation(value = "Deletes all the books from the library.")
	@ApiResponses(value = {
			@ApiResponse(code = 204, message = "OK, no content"),
			@ApiResponse(code = 500, message = "Internal error") })
	public void deleteBooks() {
		library.clear();
	}

	@Path("/{id}")
	@GET
	@ApiOperation(value = "Returns an existing book from the library.", response = MctBook.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 404, message = "Book not found"),
			@ApiResponse(code = 500, message = "Internal error") })
	public MctBook getBook(@PathParam("id") String id) {
		MctBook book = library.get(id);
		if (book == null)
			throw new WebApplicationException(Status.NOT_FOUND);
		return book;
	}

	@Path("/{id}")
	@PUT
	@ApiOperation(value = "Updates an existing book from the library.")
	@ApiResponses(value = {
			@ApiResponse(code = 204, message = "OK, no content"),
			@ApiResponse(code = 404, message = "Book not found"),
			@ApiResponse(code = 500, message = "Internal error") })
	public void updateBook(@PathParam("id") String id, MctBook book) {
		book.setId(id); // force id
		MctBook v = library.replace(book.getId(), book);
		if (v == null) {
			throw new WebApplicationException(Status.NOT_FOUND);
		}
	}

	@Path("/{id}")
	@DELETE
	@ApiOperation(value = "Deletes an existing book from the library.")
	@ApiResponses(value = {
			@ApiResponse(code = 204, message = "OK, no content"),
			@ApiResponse(code = 500, message = "Internal error") })
	public void deleteBook(@PathParam("id") String id) {
		library.remove(id);
	}

}
