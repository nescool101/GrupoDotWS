package com.test.service;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.json.JSONObject;
import org.json.XML;
import org.w3c.dom.Document;

import com.test.model.Movies;
import com.test.mongocontext.MovieDao;
import com.test.utilities.Utilities;

@Path("/movie")
@Consumes({ "appplication/xml", "application/json" })
/**
 * Class with Service and methods to consume restful
 * 
 * @author NESTOR version 1.0 23/07/2016
 */
public class ServiceMovie {

  private static final AtomicLong counter = new AtomicLong();

  /**Method that get the movies in Json or XML
   * 
   * @return Response
   */
  
  @GET
  @Produces("application/json")
  public Response getFestivities() {
    JSONObject jsonObject = new JSONObject();
    MovieDao movieContext = Utilities.getBeanContext();
    List<Movies> movies2 = (List<Movies>) movieContext.findAll();
    if (!movies2.isEmpty()) {
      for (int i = 0; i < movies2.size(); i++) {
        jsonObject.put("desc", movies2.get(i).getDesc());
        jsonObject.put("code", movies2.get(i).getCode());
        jsonObject.put("name", movies2.get(i).getName());
    
      }
    }
    return Response.ok(jsonObject.toString(), MediaType.APPLICATION_JSON).build();
  }
  
  
  /**Method that get the movies by ID in Json or XML
   * 
   * @return Response
   */

  @Path("/id/{id}")
  @GET
  @Produces("application/json")
  public Response getUserById(@PathParam("id") int id, @Context UriInfo info) {
    MovieDao movieContext = Utilities.getBeanContext();
    String format = info.getQueryParameters().getFirst("format");
    Movies movies = movieContext.findOne(new Long(id));
    if (format.equalsIgnoreCase("json")) {
      return Response.ok(movies.toString(), MediaType.APPLICATION_JSON).build();
    } else {
      String xml = "<movies>" + XML.toString(movies.toString()) + "</movies>";
      return Response.ok(xml, MediaType.APPLICATION_XML).build();
    }
  }

  
  /**Method that create the movies using Json or XML
   * 
   * @return Response
   */
  
  @SuppressWarnings("null")
  @Path("/create/")
  @POST
  @Produces("application/json")
  @Consumes({ "application/json", "application/xml" })
  public Response createUser(String movie, @Context HttpHeaders headers) {

    Movies movies = null;
    MovieDao movieContext = Utilities.getBeanContext();
    counter.set(Movies.movies.size());
    movies.setId(counter.incrementAndGet());

    if (headers.getMediaType().toString().equalsIgnoreCase("application/xml")) {
      // String xml = "<movies>" + XML.toString(movie) +
      // "</movies>";
      try {
        Document doc = Utilities.convertStringToXml(movie);

        movies.setDesc(doc.getElementsByTagName("desc").item(0).getTextContent());
        movies.setCode(doc.getElementsByTagName("code").item(0).getTextContent());
        movies.setName(doc.getElementsByTagName("name").item(0).getTextContent());

      } catch (Exception e) {
        e.printStackTrace();
      }
      movieContext.save(movies);
      return Response.ok(movie, MediaType.APPLICATION_XML).build();
    } else {
      JSONObject jsonObject = new JSONObject(movie);
      movies.setDesc((String) jsonObject.get("desc"));
      movies.setCode((String) jsonObject.get("code"));
      movies.setName((String) jsonObject.get("name"));
      movieContext.save(movies);
      return Response.status(200).entity(jsonObject.toString()).build();
    }

  }

}
