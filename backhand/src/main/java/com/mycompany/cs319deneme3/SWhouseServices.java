/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.cs319deneme3;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import model.House;
import model.Table;

/**
 *
 * @author umur
 */
    @Api("SW houseServices")
    @Path("SWhouseServices")
public class SWhouseServices {

    @GET
    //@Produces("application/json")
    @Produces("text/plain")
    @Path("createTableService")
    public String createTableService(@QueryParam("ownerID") String ownerID, @QueryParam("tableID") String tableID ) throws IOException {
        if (House.getInstance().createTable(ownerID, tableID) ) {
            return "table(tableID: " + tableID + " | ownerID: " + ownerID + " successfully created";
        } else {
            return "table with given ID already exists";
        }
    }
    
    @GET
    //@Produces("application/json")
    @Produces("text/plain")
    @Path("startTableService")
    public String startTableService(@QueryParam("tableID") String tableID ) throws IOException, CloneNotSupportedException {
        if (House.getInstance().startTable(tableID) ) {
            return "başarılı";
        } else {
            return "başarısız";
        }
    }
    
    @GET
    @Produces("application/json")
//    @Produces("text/plain")
    @Path("listWaitingTableService")
    public String listWaitingTableService( ) throws IOException {
        if (House.getInstance().getWaitingTables() != null) {
            HashMap<String, Integer> result = new HashMap<>();
            for(Map.Entry<String, Table> entry : House.getInstance().getWaitingTables().entrySet()) {
                result.put(entry.getValue().getTableID(), entry.getValue().getNoOfPlayers());
            }
            return parseJSON(result);
        } else {
            return null;
        }
    }
    
    private String parseJSON(Object toParse) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(toParse.toString());
        return mapper.writeValueAsString(toParse);
    }
}
