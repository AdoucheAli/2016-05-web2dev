package com.store.catalog.ws.rest.view.nosql;

import com.store.catalog.model.nosql.SearchableItem;
import com.store.catalog.service.catalog.CatalogSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by ZCadi on 26/10/2015.
 */
@Path("elastic")
@Component
public class ElasticResource {



    @Autowired
    private CatalogSearchService catalogSearchService;

    public CatalogSearchService getCatalogServiceImpl() {
        return catalogSearchService;
    }

    public void setCatalogServiceImpl(CatalogSearchService catalogServiceImpl) {
        this.catalogSearchService = catalogSearchService;
    }

    @GET
    @Path("toto")
    @Produces(MediaType.TEXT_PLAIN)
    public String getToto() {
        return "toto is back";
    }




    @GET
    @Path("item/match/{name}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response findItemsByMatch(@PathParam("name") String nameToMatch, @QueryParam("from") Integer from, @QueryParam("size") Integer size) throws Exception {

        if ((from == null) || "".equals(from))  {
            from = Integer.valueOf(0);
        }

        if ((size == null) || ("".equals(size))){
            size = Integer.valueOf(10);
        }

        List<SearchableItem> items = catalogSearchService.matchItems(nameToMatch, from, size) ;

        return Response.status(200).entity(items).build();
    }


    @GET
    @Path("item/match_prefixe/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findItemsByMatchPhrasePrefixe(@PathParam("name") String nameToPrefixeMatch, @QueryParam("from") Integer from, @QueryParam("size") Integer size) throws Exception {

        if ((from == null) || "".equals(from))  {
            from = Integer.valueOf(0);
        }

        if ((size == null) || ("".equals(size))){
            size = Integer.valueOf(10);
        }

        List<SearchableItem>  items = catalogSearchService.matchPhrasePrefixeItems(nameToPrefixeMatch, from, size) ;

        return Response.status(200).entity(items).build();

    }

}
