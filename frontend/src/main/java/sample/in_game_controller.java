package sample;

import com.sun.org.apache.xml.internal.dtm.ref.sax2dtm.SAX2DTM2;
import javafx.animation.FadeTransition;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;
import jdk.nashorn.internal.runtime.ECMAException;
import org.controlsfx.control.PopOver;

import java.io.File;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.*;
import java.util.concurrent.ExecutionException;
import sample.Action;


public class in_game_controller implements Initializable  {

    String tableID;
    String wonderID;
    WonderBoard sampleWonderBoard = new WonderBoard();
    @FXML
    GridPane resources_grid_0;
    @FXML
    GridPane structure_grid_0;
    Map<String,String> myStructuresBuilded = new HashMap<String,String>();
    Map<String,String> leftStructuresBuilded = new HashMap<String,String>();
    Map<String,String> rightStructuresBuilded = new HashMap<String,String>();
    Map<String,String> mySources = new HashMap<String, String>();
    String selectedCard;
    int Hand;
    int myStructureNo = 0;
    Map<String,Image> images2 = new HashMap<String, Image>();
    FileInputStream inputstream[] = new FileInputStream[78] ;
    Image images[] = new Image[78];
    ServerConnection con = new ServerConnection();

    @FXML
    Button build_card_id;

    @FXML
    Button dice;

    @FXML
    ImageView card1;
    @FXML
    ImageView card2;
    @FXML
    ImageView card3;
    @FXML
    ImageView card4;
    @FXML
    ImageView card5;
    @FXML
    ImageView card6;
    @FXML
    ImageView card7;
    @FXML
    GridPane my_board_id ;

    @FXML
    Parent parent;
    // @FXML
    // ImageView card1;

    ImageView hand[] = new ImageView[7];
    ImageView myStructures[] = new ImageView[18];
    ImageView leftStructures[] = new ImageView[18];
    ImageView rightStructures[] = new ImageView[18];
    Map<String,WonderBoard> wonderBoards = new HashMap<String, WonderBoard>();
    @FXML
    GridPane trade_sources_grid;
    int selection =0;
    HandContainer handCards;
    Scene sceneOfTable;

    public void selectCard(MouseEvent event)throws Exception{
        PopOver popOver = new PopOver();
        popOver.setArrowLocation(PopOver.ArrowLocation.BOTTOM_CENTER);
        popOver.setAutoFix(true);
        popOver.setAutoHide(true);
        popOver.setHideOnEscape(true);
        popOver.setDetachable(false);
        GridPane pane = FXMLLoader.load(getClass().getResource("/select_card_popover.fxml"));
        popOver.setContentNode(pane);
        popOver.show((ImageView)event.getSource());
        selectedCard =((ImageView) event.getSource()).getId();
    }

    public void trade(MouseEvent event)throws Exception{
        PopOver popOver = new PopOver();
        popOver.setArrowLocation(PopOver.ArrowLocation.BOTTOM_CENTER);
        popOver.setAutoFix(true);
        popOver.setAutoHide(true);
        popOver.setHideOnEscape(true);
        popOver.setDetachable(true);

        GridPane pane = FXMLLoader.load(getClass().getResource("/trade_popover.fxml"));
        popOver.setContentNode(pane);
        popOver.show((Button)event.getSource(),1100,1200);
       /* Button buton = (Button)event.getSource();
        Scene tempScene = buton.getScene();
        Button temp = (Button) tempScene.lookup("#dice");
        popOver.show(temp);*/
    }

    public void buildCardClicked(MouseEvent event) throws Exception{
        boolean trade = false;
        Map<String,Integer> source = wonderBoards.get(wonderID).getSources();
        List<Card> hand = handCards.getContainer().get(wonderID);
        int index = hand.indexOf(selectedCard);
        Card chosen = hand.get(index);
        for(Map.Entry<String,String> entry : chosen.getCost().getCost().entrySet()){
            if(source.get(entry.getKey()) < Integer.parseInt(entry.getValue()) ){
                trade = true;
                break;
            }
        }
        /////////////////buraya bumu gelicek, wonderID ile mi yolluyorlar
        if(trade){
            Action toSend = new Action(selection,new HashMap<String, Integer>(), new HashMap<String, Integer>(), hand.indexOf(selectedCard),wonderID);
            con.sendAction(toSend);
        }else{
            tradeClicked(event);
        }
    }

    public void tradeClicked(MouseEvent event){
        Button temp = (Button)event.getSource();
        Label coinLabel = (Label)temp.getScene().lookup("#t_coin");
        if(coinLabel.getText().equals("0")) //////////
            //   coinLabel.setText(mySources.get("coin"));
            coinLabel.setText("5");
        Scene tempScene= trade_sources_grid.getScene();
        String labelName = "#";
        if(temp.getId().substring(0,4).equals("ltbp") && Integer.parseInt(coinLabel.getText()) > 1){
            labelName += "Llt_" + temp.getId().substring(5);
            Label tb = (Label) tempScene.lookup(labelName);
            tb.setText( Integer.parseInt(tb.getText()) + 1 + "");
            coinLabel.setText(Integer.parseInt(coinLabel.getText()) - 2 + "");
        }else if (temp.getId().substring(0,4).equals("ltbm") ){
            labelName += "Llt_" + temp.getId().substring(5);
            Label tb = (Label) tempScene.lookup(labelName);
            if(!tb.getText().equals("0")){
                tb.setText( Integer.parseInt(tb.getText()) - 1 + "");
                coinLabel.setText(Integer.parseInt(coinLabel.getText()) + 2 + "");
            }

        }else if(temp.getId().substring(0,4).equals("rtbm") ){
            labelName += "Lrt_" + temp.getId().substring(5);
            Label tb = (Label) tempScene.lookup(labelName);
            if(!tb.getText().equals("0")){
                tb.setText( Integer.parseInt(tb.getText()) - 1 + "");
                coinLabel.setText(Integer.parseInt(coinLabel.getText()) + 2 + "");
            }

        }else if(temp.getId().substring(0,4).equals("rtbp") && Integer.parseInt(coinLabel.getText()) > 1){
            labelName += "Lrt_" + temp.getId().substring(5);
            Label tb = (Label) tempScene.lookup(labelName);
            tb.setText( Integer.parseInt(tb.getText()) + 1 + "");
            coinLabel.setText(Integer.parseInt(coinLabel.getText()) - 2 + "");
        }
        System.out.println(labelName);

    }
    public void tradeBuyPressed(MouseEvent event) throws Exception{
        Map<String,Integer> leftTradeMap = new HashMap<String, Integer>();
        Map<String,Integer> rightTradeMap = new HashMap<String, Integer>();
        List<Card> hand = handCards.getContainer().get(wonderID);
        if(selection == 2 || selection == 3){
            for (Node child : trade_sources_grid.getChildren()) {// bu kod biraz çirkin, böyle olmasının bir nedeni var, değiştirme
                if(child.getId() != null)
                    System.out.println(child.getId().substring(0,2) + "BUraaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
                if(child.getId() != null && child.getId().substring(0,2).equals("Ll")){
                    Label temp = (Label)child;
                    String sourceValue = temp.getText();
                    leftTradeMap.put( child.getId().substring(4), Integer.parseInt(sourceValue));
                }
            }
            for (Node child : trade_sources_grid.getChildren()) {// bu kod biraz çirkin, böyle olmasının bir nedeni var, değiştirme
                if(child.getId() != null)
                    System.out.println(child.getId().substring(0,2) + "BUraaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
                if(child.getId() != null && child.getId().substring(0,2).equals("Lr")){
                    Label temp = (Label)child;
                    String sourceValue = temp.getText();
                    rightTradeMap.put( child.getId().substring(4), Integer.parseInt(sourceValue));
                }
            }
        }

        Action toSend = new Action(selection,leftTradeMap,rightTradeMap,  hand.indexOf(selectedCard),wonderID);
        con.sendAction(toSend);
    }

    public void refreshSources(HashMap<String,Integer> sources, GridPane pane)throws Exception{
        GridPane tempGrid= pane;
        for (Node child : tempGrid.getChildren()) {
            if (child.getId() != null)
                if(child.getId().charAt(0) == 'l'){
                    String nameSource = child.getId().substring(4);
                    Label temp = (Label)child;
                    temp.setText(sources.get(nameSource) + "");
                }
        }
    }

    public void onPress(ActionEvent event)throws Exception{

        System.out.println("asdasda");
        refresh();
    }

    public void refresh()throws Exception {
        sceneOfTable = dice.getScene();


        System.out.println("Entered refresh");
        Hand = 6;
        //HashMap<String,WonderBoard> wonders= (HashMap<String, WonderBoard>) con.ConvertJson(tableID);

        //handCards = con.ConvertJsonHand(tableID);

        System.out.println("Entered Test");
        //FOR TEST PURPOSES !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!DELETE CODE BELOW
        WonderBoard TestWonder = new WonderBoard();
        TestWonder.setName("ahmet");
        WonderBoard TestWonder1 = new WonderBoard();
        TestWonder1.setName("ahmet1");
        WonderBoard TestWonder2 = new WonderBoard();
        TestWonder2.setName("ahmet2");
        WonderBoard TestWonder3 = new WonderBoard();
        TestWonder3.setName("ahmet3");
        WonderBoard TestWonder4 = new WonderBoard();
        TestWonder4.setName("ahmet4");
        WonderBoard TestWonder5 = new WonderBoard();
        TestWonder5.setName("ahmet5");
        WonderBoard TestWonder6 = new WonderBoard();
        TestWonder6.setName("ahmet6");



        Card card1 = new Card();
        card1.setName("altar");
        Card card2 = new Card();
        card2.setName("altar");
        Card card3 = new Card();
        card3.setName("altar");
        Card card4 = new Card();
        card4.setName("altar");
        HashMap<String, Card> cardsMap = new HashMap<String, Card>();
        cardsMap.put("1", card1);
        cardsMap.put("2", card1);
        cardsMap.put("3", card1);
        cardsMap.put("4", card1);
        TestWonder.setBuiltCards(cardsMap);
        TestWonder1.setBuiltCards(cardsMap);
        TestWonder2.setBuiltCards(cardsMap);
        TestWonder3.setBuiltCards(cardsMap);
        TestWonder4.setBuiltCards(cardsMap);
        TestWonder5.setBuiltCards(new HashMap<String, Card>());
        TestWonder6.setBuiltCards(cardsMap);
        HashMap<String,WonderBoard> wonders= new HashMap<String, WonderBoard>();
        wonders.put("ahmet", TestWonder);
        wonders.put("ahmet1", TestWonder1);
        wonders.put("ahmet2", TestWonder2);
        wonders.put("ahmet3", TestWonder3);
        wonders.put("ahmet4", TestWonder4);
        wonders.put("ahmet5", TestWonder5);
        wonders.put("ahmet6", TestWonder6);
        int enemyNo = 3;

        WonderBoard my_wonder = wonders.get("ahmet");
        String left_neighbour_name = "ahmet1";
        String right_neighbour_name = "ahmet2";




        for(Map.Entry mapElement : wonders.entrySet()){
            String key = (String)mapElement.getKey();
            String structure_GridName = "#structure_grid_";
            String sourcesGridName = "#resources_grid_";
            String wonderName = wonders.get(key).getName();
            GridPane structure_pane;
            GridPane sources_pane;
            if(key.equals(my_wonder.getName())){
                structure_GridName = structure_GridName + "0"  ;
                sourcesGridName =  sourcesGridName + "0" ;
            }else if(key.equals(left_neighbour_name)){
                structure_GridName = structure_GridName + "1"  ;
                sourcesGridName =  sourcesGridName + "1" ;
            }else if(key.equals(right_neighbour_name)){
                structure_GridName = structure_GridName + "2" ;
                sourcesGridName = sourcesGridName + "2" ;
            }else {
                structure_GridName = structure_GridName + enemyNo ;
                sourcesGridName = sourcesGridName + enemyNo ;
                enemyNo++;
            }
            System.out.println(key  +"      "+ sourcesGridName);
           // sources_pane = (GridPane)sceneOfTable.lookup(sourcesGridName);

            sources_pane = (GridPane)sceneOfTable.lookup(sourcesGridName);
            structure_pane = (GridPane)sceneOfTable.lookup(structure_GridName);
            refreshSources(wonders.get(key).getSources(),sources_pane);
            refreshStructures(wonders.get(key),structure_pane);
        }

        // burada her strucure gridi çağırılacak, refreshere gerekli gridler verilecek
       /* refreshStructures(a, left_structure_grid);
        refreshStructures(a, right_structure_grid);*/










        //FOR TEST PURPOSES!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!DELETE CODE ABOVE
        //ASIL CODE BURADA BAŞLIYOR

        /*WonderBoard my_wonder = wonders.get(wonderID);
        refreshSources(my_wonder.getSources() ,resources_grid_0);
        refreshStructures(my_wonder, structure_grid_0);
        String left_neighbour_name = my_wonder.getLeftNeighbor().getName();
        String right_neighbour_name = my_wonder.getRightNeighbor().getName();

        int enemyNo = 3;

        //burası my wonder haric hepsini güncelliyor
        //1 ve 2 yi ayrıca kontrol ediyor, 1 left neighbour, 2 right neighbour
        for(Map.Entry mapElement : wonders.entrySet()){
            String key = (String)mapElement.getKey();
            String structure_GridName = "#structure_grid_";
            String sourcesGridName = "#resources_grid_";
            String wonderName = wonders.get(key).getName();
            GridPane structure_pane;
            GridPane sources_pane;
            if(key.equals(wonderID){
                structure_GridName = structure_GridName + "0"  ;
                sourcesGridName =  sourcesGridName + "0" ;
            }else if(key.equals(left_neighbour_name)){
                structure_GridName = structure_GridName + "1"  ;
                sourcesGridName =  sourcesGridName + "1" ;
            }else if(key.equals(right_neighbour_name)){
                structure_GridName = structure_GridName + "2" ;
                sourcesGridName = sourcesGridName + "2" ;
            }else {
                structure_GridName = structure_GridName + enemyNo ;
                sourcesGridName = sourcesGridName + enemyNo ;
                enemyNo++;
            }
            sources_pane = (GridPane)sceneOfTable.lookup(sourcesGridName);
            structure_pane = (GridPane)sceneOfTable.lookup(structure_GridName);
            refreshSources(wonders.get(key).getSources(),sources_pane);
            refreshStructures(wonders.get(key),structure_pane);
        }

        // burada her strucure gridi çağırılacak, refreshere gerekli gridler verilecek
       /* refreshStructures(a, left_structure_grid);
        refreshStructures(a, right_structure_grid);*/
       // con.sendAction(new Action());

        refreshHand(handCards);
    }

    public void refreshHand(HandContainer handCards){
        /*
        Map<String,List<Card>> temp= handCards.getContainer();
        for(int i = 0; i < 7; i++){
            hand[i].setVisible(true);
            if(temp.get(wonderID).get(i)!=null){
                String leeen = temp.get(wonderID).get(i).getName();
                if(leeen.indexOf(' ') != -1){
                    leeen = leeen.substring(0,(leeen.indexOf(" "))) + "_"+ leeen.substring(leeen.indexOf(" ")+ 1);
                    leeen = leeen.toLowerCase();
                    System.out.println(leeen + " asdsadsdaassdsadasda");
                }
                hand[i].setImage(images2.get(leeen));
                handAnimation(hand[i]);
            }
        }*/
        for(int i = 0 ; i <7; i++){
            hand[i].setImage(images2.get("altar"));
            handAnimation(hand[i]);
        }

    }

    public void choice(ActionEvent event) throws Exception{
        Button temp = (Button)event.getSource();
        if(temp.getId().equals("discard"))
            selection = 1;
        else if(temp.getId().equals("build_card"))
            selection = 2;
        else if(temp.getId().equals("build_wonder"))
            selection = 3;
    }

    public void refreshStructures(WonderBoard wonder,GridPane gridName){
        Iterator cardIterator =  wonder.getBuiltCards().entrySet().iterator();
        Scene sceneOfWonder = sceneOfTable;
        int noOfImage = 0;
        while(cardIterator.hasNext()){
            String nameOfImageView = "#structure_" + gridName.getId().substring(15) + "_" + noOfImage; //15 çünkü structure_0 derken 11 den başlarsan 0 ı alırsın
            Map.Entry mapElement = (Map.Entry)cardIterator.next();
            Card marks = ((Card)mapElement.getValue());
            ImageView structureImagePlace = (ImageView) sceneOfWonder.lookup(nameOfImageView);
            structureImagePlace.setImage(images2.get(marks.getName()));
            noOfImage++;

        }
    }

    public void startPoint() throws Exception {
        int i = 0;
        String workingDir = System.getProperty("user.dir");
        workingDir += "/src/main/resources/Cards";
        File dir = new File(workingDir);
        File[] directoryListing = dir.listFiles();
        if (directoryListing != null) {
            for (File child : directoryListing) {
                InputStream temp = new FileInputStream(child);
                images2.put(child.getName().substring(0,child.getName().length()-4), new Image(temp));
                i++;
            }
        }
        hand[0] = card1;
        hand[1] = card2;
        hand[2] = card3;
        hand[3] = card4;
        hand[4] = card5;
        hand[5] = card6;
        hand[6] = card7;
        System.out.println(start_game_controller.TableID + "OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO");
        if(start_game_controller.TableID.equals("") )
            tableID=join_game_controller.TableID;
        if(join_game_controller.TableID.equals(""))
            tableID=start_game_controller.TableID;
        wonderID = player_id_controller.WonderID;


    }

    public void handAnimation(ImageView hand){
        Duration sec2 = Duration.millis(2000);
        Duration sec3 = Duration.millis(3000);

        /*FadeTransition ft = new FadeTransition(sec3);
        ft.setFromValue(1.0f);
        ft.setToValue(0.3f);
        ft.setCycleCount(2);
        ft.setAutoReverse(true);
        ft.setNode(hand);
        ft.play();*/

        TranslateTransition tt = new TranslateTransition(sec2);
        tt.setFromX(0);
        tt.setToX(100);
        tt.setCycleCount(1);
        tt.setAutoReverse(true);
        tt.setNode(hand);
        tt.play();


        RotateTransition rt = new RotateTransition(sec3);
        rt.setByAngle(360);
        rt.setCycleCount(1);
        rt.setAutoReverse(true);
        rt.setNode(hand);
        rt.play();

        ScaleTransition st = new ScaleTransition(sec2);
        //st.setFromX(1);
        //st.setByX(2);
        //st.setToX(2);
        //st.setFromY(0);
        //st.setToY(2);
        st.setByX(2);
        st.setByY(2);
        st.setCycleCount(2);
        st.setAutoReverse(true);
        st.setNode(hand);
        st.play();
    }


    public void initialize(URL location, ResourceBundle resources)  {
        try{
            startPoint();
        }catch (Exception e){

        }
    }
}