package sample;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.deploy.net.HttpResponse;
import com.sun.org.apache.bcel.internal.generic.JsrInstruction;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.*;
import sun.java2d.opengl.WGLSurfaceData;
import sun.misc.IOUtils;
import sun.net.www.http.HttpClient;


import javax.net.ssl.HttpsURLConnection;
import java.net.URLEncoder;
import java.util.*;
import java.util.concurrent.ExecutionException;

public class ServerConnection {
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


    public JSONObject getHand()throws Exception{
        String url = "http://192.168.1.32:8080/cs319deneme3/7wonders/SWtableServices/getHandsService?tableID=5";
        //String url = "http://ec2-54-93-112-68.eu-central-1.compute.amazonaws.com:8080/cs319deneme3-1.0-SNAPSHOT/7wonders/SWtableServices/getHandsService?tableID=1";
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

        JSONObject myResponse = new JSONObject(  "{"+response.toString() +"}");
        Map<String, Object> a = myResponse.toMap();
        System.out.println("Card1- "+a.toString());
        return myResponse;
    }



    public void sendRequest()throws Exception{

        URL url = new URL("http://httpbin.org/ip");
        Map params = new HashMap<String, String>();
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
        System.out.println("name"+form_data.getString("key4"));


    }

    public void sendRequestChoice(String choice, String selectedCard) throws  Exception{
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
        //String url = "http://ec2-54-93-112-68.eu-central-1.compute.amazonaws.com:8080/cs319deneme3-1.0-SNAPSHOT/7wonders/SWhouseServices/createTableService?";
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
        String url = "http://ec2-54-93-112-68.eu-central-1.compute.amazonaws.com:8080/cs319deneme3-1.0-SNAPSHOT/7wonders/SWtableServices/joinPlayerService?tableID=";
        url += tableID + "&tableID=";
        url += playerID;
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
        //String url = "http://ec2-54-93-112-68.eu-central-1.compute.amazonaws.com:8080/cs319deneme3-1.0-SNAPSHOT/7wonders/SWhouseServices/startTableService?tableID=1";
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

    public Object ConvertJson(String table)throws Exception
    {
        JSONObject hold = getWonder(table);
        ObjectMapper mapper  = new ObjectMapper();
        String info = hold.toString();
        HashMap<String,WonderBoard> demoBoard = mapper.readValue(info, HashMap.class);
        //HashMap<String,WonderBoard> demoBoard = new HashMap<String, WonderBoard>();
       // mapper.readValue(info, demoBoard.class);
        return demoBoard;


    }

    public ArrayList<Card> ConvertJsonHand()throws Exception
    {
        JSONObject hold = getHand();
        ObjectMapper mapper  = new ObjectMapper();
        String info = hold.toString();
        List<Card> cards = mapper.readValue(info, new TypeReference<List<Card>>() { });
        return (ArrayList)cards;
    }
}
