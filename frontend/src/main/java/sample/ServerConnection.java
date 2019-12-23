package sample;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

//import sun.java2d.opengl.WGLSurfaceData;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;



public class ServerConnection {
  //  String url = "http://139.179.103.144:8080/cs319deneme3/7wonders/SWhouseServices/createTableService?tableID=";
        static HandContainer cardss;
    public JSONObject getWonder(String tableId)throws Exception{
        String url = "http://192.168.1.32:8080/cs319deneme3/7wonders/SWtableServices/getWondersService?tableID=";
        //String url = "http://ec2-54-93-112-68.eu-central-1.compute.amazonaws.com:8080/cs319deneme3-1.0-SNAPSHOT/7wonders/SWtableServices/getWondersService?tableID=";
        url = url + tableId;
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        // optional default is GET
        con.setRequestMethod("GET");
        //add request header
        con.setRequestProperty("player1", "turn");
        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            System.out.println(inputLine);
            response.append(inputLine);
        }
        in.close();
        //print in String
        System.out.println(response.toString());
        //Read JSON response and print
        JSONObject myResponse = new JSONObject(response.toString());
        Map<String, Object> a = myResponse.toMap();
        System.out.println("Card1- "+a.toString());
        return myResponse;
    }
    public HashMap<String, String> getRollDiceResult() throws Exception{
       // String url = "http://139.179.103.139:8080/cs319deneme3/7wonders/SWtableServices/getRollDiceResult?tableID=";
         String url = "http://192.168.1.32:8080/cs319deneme3/7wonders/SWtableServices/getRollDiceResult?tableID=";
        //String url = "http://ec2-54-93-112-68.eu-central-1.compute.amazonaws.com:8080/cs319deneme3-1.0-SNAPSHOT/7wonders/SWtableServices/getHandsService?tableID=";
        url = url + Main.tableID;
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        // optional default is GET
        con.setRequestMethod("GET");
        //add request header
        con.setRequestProperty("player1", "turn");
        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            System.out.println(inputLine);
            response.append(inputLine);
        }
        in.close();
        //print in String
        System.out.println(response.toString());
        //Read JSON response and print
        HashMap<String, String> mapToSend = new HashMap<String, String>();
        JSONObject myResponse = new JSONObject(  response.toString() );
        Map<String,Object> o = myResponse.toMap();
        for (Map.Entry<String, Object> entry : o.entrySet()) {
            if(entry.getValue() instanceof String){
                mapToSend.put(entry.getKey(), (String) entry.getValue());
            }
        }
        return mapToSend;
    }

    public HashMap<String, String> getRollDiceMap() throws Exception{
        //String url = "http://139.179.103.139:8080/cs319deneme3/7wonders/SWtableServices/getRollDiceMap?tableID=";
         String url = "http://192.168.1.32:8080/cs319deneme3/7wonders/SWtableServices/getRollDiceMap?tableID=";
        //String url = "http://ec2-54-93-112-68.eu-central-1.compute.amazonaws.com:8080/cs319deneme3-1.0-SNAPSHOT/7wonders/SWtableServices/getHandsService?tableID=";
        url = url + Main.tableID;
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        // optional default is GET
        con.setRequestMethod("GET");
        //add request header
        con.setRequestProperty("player1", "turn");
        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            System.out.println(inputLine);
            response.append(inputLine);
        }
        in.close();
        //print in String
        System.out.println(response.toString());
        //Read JSON response and print
        HashMap<String, String> mapToSend = new HashMap<String, String>();
        JSONObject myResponse = new JSONObject(  response.toString() );
        Map<String,Object> o = myResponse.toMap();
        for (Map.Entry<String, Object> entry : o.entrySet()) {
            if(entry.getValue() instanceof String){
                mapToSend.put(entry.getKey(), (String) entry.getValue());
            }
        }
        return mapToSend;
    }
    public HashMap<String,String> addToRollDiceService() throws Exception{
        //String url = "http://139.179.103.139:8080/cs319deneme3/7wonders/SWtableServices/addToRollDiceService?tableID=";
        String url = "http://192.168.1.32:8080/cs319deneme3/7wonders/SWtableServices/addToRollDiceService?tableID=";
        //String url = "http://ec2-54-93-112-68.eu-central-1.compute.amazonaws.com:8080/cs319deneme3-1.0-SNAPSHOT/7wonders/SWtableServices/getHandsService?tableID=";
        url = url + Main.tableID;
        url = url + "&playerID=" + Main.wonderID;
        System.out.println("ADD TO ROLL DICE SERVICE " + url);
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        // optional default is GET
        con.setRequestMethod("GET");
        //add request header
        con.setRequestProperty("player1", "turn");
        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            System.out.println(inputLine);
            response.append(inputLine);
        }
        in.close();
        //print in String
        System.out.println(response.toString());
        //Read JSON response and print
        HashMap<String, String> mapToSend = new HashMap<String, String>();

        return mapToSend;


    public JSONObject getHand(String tableID)throws Exception{
        String url = "http://192.168.1.32:8080/cs319deneme3/7wonders/SWtableServices/getHandsService?tableID=";
        //String url = "http://ec2-54-93-112-68.eu-central-1.compute.amazonaws.com:8080/cs319deneme3-1.0-SNAPSHOT/7wonders/SWtableServices/getHandsService?tableID=";
        url = url + Main.tableID;
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        // optional default is GET
        con.setRequestMethod("GET");
        //add request header
        con.setRequestProperty("player1", "turn");
        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            System.out.println(inputLine);
            response.append(inputLine);
        }
        in.close();
        //print in String
        System.out.println(response.toString());
        //Read JSON response and print

        JSONObject myResponse = new JSONObject(  response.toString() );
        Map<String, Object> a = myResponse.toMap();
        System.out.println("Card1- "+a.toString());
        return myResponse;
    }



    public String sendRequestChoice(String actionJson,String tableID)throws Exception{
        String url = "http://192.168.1.32:8080/cs319deneme3/7wonders/SWtableServices/playActionService?tableID=";

        //String url ="http://ec2-54-93-112-68.eu-central-1.compute.amazonaws.com:8080/cs319deneme3-1.0-SNAPSHOT/7wonders/SWtableServices/playActionService?tableID=";
        url = url + Main.tableID  + "&action=";
    //    url = url +  actionJson;
        //url ye parametre ekleme, çalışmıyor UTF8 formatına çevirmeye çalıştım

        url = url +URLEncoder.encode(actionJson, "UTF-8");


        URL obj = new URL(url);

        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");
        //add request header
        con.setRequestProperty("player1", "turn");
        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            System.out.println(inputLine);
            response.append(inputLine);
        }
        in.close();
        //print in String
        System.out.println("elif kart sendrequestchoice" + response.toString());
        return response.toString();

       }

    public void sendRequest(String actionJson) throws  Exception{
        String url = "https://webhook.site/c4ba9839-4fad-491d-a68b-6236d16ef878";
        //String url = "http://ec2-54-93-112-68.eu-central-1.compute.amazonaws.com:8080/cs319deneme3-1.0-SNAPSHOT/7wonders/SWhouseServices/createTableService?";



        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        // optional default is GET
        con.setRequestMethod("GET");
        //add request header
        con.setRequestProperty("alptekin", "123123");
        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            System.out.println(inputLine);
            response.append(inputLine);
        }
        in.close();
        //print in String

        System.out.println(response.toString());

    }
    public void sendRequestCreate(String ownerID, String tableID) throws Exception{
        String url = "http://192.168.1.32:8080/cs319deneme3/7wonders/SWhouseServices/createTableService?ownerID=";
       //String url = "http://ec2-54-93-112-68.eu-central-1.compute.amazonaws.com:8080/cs319deneme3-1.0-SNAPSHOT/7wonders/SWhouseServices/createTableService?ownerID=";

        url += ownerID + "&tableID=";
        url += tableID;
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        // optional default is GET
        con.setRequestMethod("GET");
        //add request header
        con.setRequestProperty("alptekin", "123123");
        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            System.out.println(inputLine);
            response.append(inputLine);
        }
        in.close();
        System.out.println(response.toString());
    }
    public void sendRequestJoin(String tableID, String playerID) throws Exception{
        String url = "http://192.168.1.32:8080/cs319deneme3/7wonders/SWtableServices/joinPlayerService?tableID=";
        //String url = "http://ec2-54-93-112-68.eu-central-1.compute.amazonaws.com:8080/cs319deneme3-1.0-SNAPSHOT/7wonders/SWtableServices/joinPlayerService?tableID=";
        url += tableID + "&playerID=";
        url = url + playerID;
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        // optional default is GET
        con.setRequestMethod("GET");
        //add request header
        con.setRequestProperty("alptekin", "123123");
        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            System.out.println(inputLine);
            response.append(inputLine);
        }
        in.close();
        System.out.println(response.toString());
    }

    public void sendRequestStartTable(String tableID)throws Exception{
        String url = "http://192.168.1.32:8080/cs319deneme3/7wonders/SWhouseServices/startTableService?tableID=";
        //String url = "http://ec2-54-93-112-68.eu-central-1.compute.amazonaws.com:8080/cs319deneme3-1.0-SNAPSHOT/7wonders/SWhouseServices/startTableService?tableID=";
        url = url + tableID;
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        // optional default is GET
        con.setRequestMethod("GET");
        //add request header
        con.setRequestProperty("alptekin", "123123");
        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            System.out.println(inputLine);
            response.append(inputLine);
        }
        in.close();
        System.out.println(response.toString());
    }

    public HashMap<String,Integer> getMilitaryPoint()throws Exception{
        String url = "http://192.168.1.32:8080/cs319deneme3/7wonders/SWtableServices/getMilitaryPointsService?tableID=";
       // String url = "http://ec2-54-93-112-68.eu-central-1.compute.amazonaws.com:8080/cs319deneme3-1.0-SNAPSHOT/7wonders/SWtableServices/getMilitaryPointsService?tableID=";
        url = url + Main.tableID;
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        // optional default is GET
        con.setRequestMethod("GET");
        //add request header
        con.setRequestProperty("alptekin", "123123");
        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            System.out.println(inputLine);
            response.append(inputLine);
        }
        in.close();
        System.out.println(response.toString());

        JSONObject myResponse = new JSONObject(response.toString() );
        HashMap<String,Integer> mapToSend = new HashMap<String, Integer>();
        Map<String, Object> a = myResponse.toMap();
        for (Map.Entry<String, Object> entry : a.entrySet()) {
            if(entry.getValue() instanceof String){
                mapToSend.put(entry.getKey(), (Integer) entry.getValue());
            }
        }
        return mapToSend;

    }

    public HashMap<String,Integer> getTableList() throws Exception{
        String url = "http://192.168.1.32:8080/cs319deneme3/7wonders/SWhouseServices/listWaitingTableService";
        //String url = "http://ec2-54-93-112-68.eu-central-1.compute.amazonaws.com:8080/cs319deneme3-1.0-SNAPSHOT/7wonders/SWhouseServices/listWaitingTableService";
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        // optional default is GET
        con.setRequestMethod("GET");
        //add request header
        con.setRequestProperty("alptekin", "123123");
        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            System.out.println(inputLine);
            response.append(inputLine);
        }
        in.close();
        System.out.println(response.toString());

        JSONObject myResponse = new JSONObject(response.toString() );
        HashMap<String,Integer> mapToSend = new HashMap<String, Integer>();
        Map<String, Object> a = myResponse.toMap();
        System.out.println("a");
        System.out.println(a);
        for (Map.Entry<String, Object> entry : a.entrySet()) {
            if(entry.getValue() instanceof Integer){
                mapToSend.put(entry.getKey(), (Integer) entry.getValue());
            }
        }
        System.out.println("eliftable");
        System.out.println(mapToSend);
        return mapToSend;
    }

    public Object ConvertJson(String table)throws Exception
    {
        JSONObject hold = getWonder(table);
        ObjectMapper mapper  = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        String info = hold.toString();
        HashMap<String,WonderBoard> demoBoard = mapper.readValue(info, new TypeReference<Map<String, WonderBoard>>() {
        });
        //HashMap<String,WonderBoard> demoBoard = new HashMap<String, WonderBoard>();
        // mapper.readValue(info, demoBoard.class);
        return demoBoard;
    }

    public HandContainer ConvertJsonHand(String table)throws Exception
    {
        JSONObject hold = getHand(table);
        ObjectMapper mapper  = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        String info = hold.toString();
        //List<Card> cards = mapper.readValue(info, new TypeReference<List<Card>>() { });
         cardss = mapper.readValue(info, HandContainer.class);
        return cardss;
    }
    public String sendAction(CardAction action, String tableID) throws Exception{
        //Action actionToConvert = action;
    /*    Map<String,Integer> leftTrade = new HashMap<String, Integer>();
        leftTrade.put("Wood", 1);
        leftTrade.put("Stone", 4);
        Map<String,Integer> rightTrade = new HashMap<String, Integer>();
        rightTrade.put("Wood", 2);
        rightTrade.put("Stone", 3);
        Action actionToConvert = new Action(1,leftTrade,rightTrade,1,"1");*/
        ObjectMapper mapper= new ObjectMapper();
        String actionJson = mapper.writeValueAsString(action);
        System.out.println(actionJson );
        String response = sendRequestChoice(actionJson, tableID);
        System.out.println("response send action" + response);
        return response;

    }
}