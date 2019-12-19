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
import model.CardAction;
import model.Card;
import model.HandContainer;
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
    public String getWonderboardsService(@QueryParam("tableID") String tableID) throws IOException {
        HashMap<String, WonderBoard> wonderboards = House.getInstance().getInPlayTables().get(tableID).getWonders();
        return parseObjectToJSON(wonderboards);
    }
//    

    @GET
    @Produces("application/json")
//    @Produces("text/plain")
    @Path("getHandsService")
    public String getHandsService(@QueryParam("tableID") String tableID) throws IOException {
        HandContainer hands = House.getInstance().getInPlayTables().get(tableID).getTransfer();
        return parseObjectToJSON(hands);
    }

    @GET
//    @Produces("application/json")
    @Produces("text/plain")
    @Path("joinPlayerService")
    public String joinPlayerService(@QueryParam("tableID") String tableID, @QueryParam("playerID") String playerID) throws IOException, Exception {
        House.getInstance().getWaitingTables().get(tableID).playerJoined(playerID, null);
        return "başarılı";
    }

    @GET
//    @Produces("application/json")
    @Produces("text/plain")
    @Path("playActionService")
    public String playActionService(@QueryParam("tableID") String tableID, @QueryParam("action") String toAct) throws IOException, Exception {

        if (House.getInstance().getInPlayTables().get(tableID).isPossible((CardAction) parseJSONToObject(toAct))) {
            House.getInstance().getInPlayTables().get(tableID).lockAction((CardAction) parseJSONToObject(toAct));
            return "başarılı";
        } else {
            return "başarısız";
        }
    }

    @GET
    @Produces("text/plain")
    @Path("getMilitaryPointsService")
    public String getMilitaryPointsService(@QueryParam("tableID") String tableID) throws IOException, Exception {
        HashMap<String, Integer> tmp = House.getInstance().getInPlayTables().get(tableID).getMilitaryPointsTransfer();
        return parseObjectToJSON(tmp);

    }
    
    @GET
    @Produces("text/plain")
    @Path("getScoresService")
    public String getScoresService(@QueryParam("tableID") String tableID) throws IOException, Exception {
        HashMap<String, Integer> tmp = House.getInstance().getInPlayTables().get(tableID).getScoreboard().getTotalScores();
        return parseObjectToJSON(tmp);

    }

    private String parseObjectToJSON(Object toParse) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(toParse);
    }

    private Object parseJSONToObject(String toParse) throws JsonProcessingException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        Object returnObj = mapper.readValue(toParse, CardAction.class);
        return returnObj;
    }
}
