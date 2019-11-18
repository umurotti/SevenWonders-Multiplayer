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
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import model.Card;
import model.House;
import model.WonderBoard;

/**
 *
 * @author umur
 */

    @Api("SW tableServices")
    @Path("SWtableServices")
public class SWtableServices {
        
    @GET
    @Produces("application/json")
//    @Produces("text/plain")
    @Path("getWondersService")
    public String getWonderboardsService(@QueryParam("tableID") String tableID ) throws IOException {
        HashMap<String, WonderBoard> wonderboards = House.getInstance().getInPlayTables().get(tableID).getWonders();
        return parseJSON(wonderboards);
    }
//    
    @GET
    @Produces("application/json")
//    @Produces("text/plain")
    @Path("getHandsService")
    public String getHandsService(@QueryParam("tableID") String tableID ) throws IOException {
        Card [][] hands = House.getInstance().getInPlayTables().get(tableID).getHands();
        return parseJSON(hands);
    }

    @GET
//    @Produces("application/json")
    @Produces("text/plain")
    @Path("joinPlayerService")
    public String joinPlayerService(@QueryParam("tableID") String tableID, @QueryParam("playerID") String playerID) throws IOException, Exception {
        House.getInstance().getWaitingTables().get(tableID).playerJoined(playerID, null);
        return "başarılı";
    }
    
    private String parseJSON(Object toParse) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(toParse);
    }
}
