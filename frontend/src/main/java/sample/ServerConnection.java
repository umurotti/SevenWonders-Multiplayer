package sample;
<<<<<<< HEAD
=======
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
>>>>>>> parent of b094520... improvment

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;

<<<<<<< HEAD
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

//import sun.java2d.opengl.WGLSurfaceData;
import java.io.UnsupportedEncodingException;
=======
import sample.Action;
import javax.net.ssl.HttpsURLConnection;
>>>>>>> parent of b094520... improvment
import java.net.URLEncoder;



public class ServerConnection {
<<<<<<< HEAD
  //  String url = "http://139.179.103.144:8080/cs319deneme3/7wonders/SWhouseServices/createTableService?tableID=";
        static HandContainer cardss;
=======
>>>>>>> parent of b094520... improvment
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


    public JSONObject getHand(String tableID)throws Exception{
        String url = "http://192.168.1.32:8080/cs319deneme3/7wonders/SWtableServices/getHandsService?tableID=";
        //String url = "http://ec2-54-93-112-68.eu-central-1.compute.amazonaws.com:8080/cs319deneme3-1.0-SNAPSHOT/7wonders/SWtableServices/getHandsService?tableID=";
        url = url + tableID;
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



    public void sendRequestChoice(String actionJson)throws Exception{
        String url = "http://192.168.1.32:8080/cs319deneme3/7wonders/SWhouseServices/";

<<<<<<< HEAD
        String url ="http://ec2-54-93-112-68.eu-central-1.compute.amazonaws.com:8080/cs319deneme3-1.0-SNAPSHOT/7wonders/SWtableServices/playActionService?tableID=";
        url = url + Main.tableID  + "&action=";
    //    url = url +  actionJson;
        //url ye parametre ekleme, çalışmıyor UTF8 formatına çevirmeye çalıştım

        url = url +URLEncoder.encode(actionJson, "UTF-8");


        URL obj = new URL(url);
=======


        String urlStr = "";
        CloseableHttpClient   httpClient    = HttpClientBuilder.create().build();
        HttpPost    post          = new HttpPost(urlStr);
        StringEntity params = new StringEntity(actionJson);
        post.setEntity(params);
        post.setHeader("Content-type", "application/json");
        HttpResponse  response = (HttpResponse) httpClient.execute(post);
        System.out.println(response + "          RESPONSE IS HERE; ACTION SEND");
>>>>>>> parent of b094520... improvment






        /*
        params.put("name", "Jinu Jawad");
        params.put("email", "helloworld@gmail.com");
        params.put("CODE", "asdas");
        params.put("message", "Hello Post Test success");
        Iterator it = params.entrySet().iterator();
        StringBuilder postData = new StringBuilder();
        for (int i = 1 ; i <= 4 ; i++) {
            System.out.println("sadsadas");
            if (postData.length() != 0) postData.append('&');
            postData.append(URLEncoder.encode("key"  + i, "UTF-8"));
            postData.append('=');
            postData.append(URLEncoder.encode("value"  + i, "UTF-8"));
        }

        byte[] postDataBytes = postData.toString().getBytes("UTF-8");
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
        conn.setDoOutput(true);
        conn.getOutputStream().write(postDataBytes);
        Reader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
        StringBuilder sb = new StringBuilder();
        for (int c; (c = in.read()) >= 0;)
            sb.append((char)c);
        String response = sb.toString();
        System.out.println(response);
        JSONObject myResponse = new JSONObject(response.toString());
        System.out.println("result after Reading JSON Response");
        System.out.println("origin- "+myResponse.getString("origin"));
        System.out.println("url- "+myResponse.getString("url"));
        JSONObject form_data = myResponse.getJSONObject("form");
        System.out.println("CODE- "+form_data.getString("key1"));
        System.out.println("email- "+form_data.getString("key2"));
        System.out.println("message- "+form_data.getString("key3"));
        System.out.println("name"+form_data.getString("key4"));*/    }

    public void sendRequest(String actionJson) throws  Exception{
        //String url = "https://webhook.site/c4ba9839-4fad-491d-a68b-6236d16ef878";
        String url = "http://ec2-54-93-112-68.eu-central-1.compute.amazonaws.com:8080/cs319deneme3-1.0-SNAPSHOT/7wonders/SWhouseServices/createTableService?";



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

    public Object ConvertJson(String table)throws Exception
    {
        JSONObject hold = getWonder(table);
        ObjectMapper mapper  = new ObjectMapper();
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
        String info = hold.toString();
        //List<Card> cards = mapper.readValue(info, new TypeReference<List<Card>>() { });
         cardss = mapper.readValue(info, HandContainer.class);
        return cardss;
    }
    public void sendAction(Action action) throws Exception{
        //Action actionToConvert = action;
        Map<String,Integer> leftTrade = new HashMap<String, Integer>();
        leftTrade.put("Wood", 1);
        leftTrade.put("Stone", 4);
        Map<String,Integer> rightTrade = new HashMap<String, Integer>();
        rightTrade.put("Wood", 2);
        rightTrade.put("Stone", 3);
        Action actionToConvert = new Action(1,leftTrade,rightTrade,1,"1");
        ObjectMapper mapper= new ObjectMapper();
        String actionJson = mapper.writeValueAsString(actionToConvert);
        System.out.println(actionJson );
        //sendRequestChoice(actionJson);
    }
}