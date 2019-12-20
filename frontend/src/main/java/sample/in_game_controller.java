package sample;

import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.concurrent.Task;
import com.sun.org.apache.xml.internal.dtm.ref.sax2dtm.SAX2DTM2;
import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.effect.*;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.*;
import javafx.scene.text.Font;
import javafx.util.Duration;
import javafx.util.Pair;
import org.controlsfx.control.PopOver;
import org.controlsfx.control.spreadsheet.Grid;

import java.io.File;

import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.*;
import java.util.concurrent.TimeUnit;


public class in_game_controller implements Initializable  {

    String tableID;
    String wonderID;
    WonderBoard sampleWonderBoard = new WonderBoard();
    @FXML
    GridPane resources_grid_0;
    @FXML
    GridPane structure_grid_0;
    @FXML
    Accordion in_game_accordion;

    @FXML
            Button discard;
    @FXML
    Button build_wonder;
    @FXML
    Button build_card;



    Map<String,String> myStructuresBuilded = new HashMap<String,String>();
    Map<String,String> leftStructuresBuilded = new HashMap<String,String>();
    Map<String,String> rightStructuresBuilded = new HashMap<String,String>();
    Map<String,String> mySources = new HashMap<String, String>();
    static String selectedCard ;
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
    Button diceGame;
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
    ImageView card0;
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
    Map<String,WonderBoard> wonderBoards ;
    @FXML
    GridPane trade_sources_grid;
    static int selection =0;
    HandContainer handCards = new HandContainer();
    Scene sceneOfTable;
    //dice popover attributes
    Scene sceneOfDicePopOver;
    @FXML
    Button roll_dice;
    ArrayList<String> diceGamePlayer = new ArrayList<String>();;
    boolean diceGameOn = false;

    //socket Attributes
    SocketThread socket;
    Thread socketThread;

    //wonder boards images and stages images
    Image[] wonderStages2 ;
    Image[] wonderStages0;
    Image[] wonderStages1;
    String [] wonderImages;

    @FXML
    Label dice_time;

    @FXML
    public void onPressEnemies(MouseEvent event) throws Exception{
        SoundManager.play(SoundManager.MUSIC.ENEMIES);
    }

    @FXML
    public void onPressMyWonder(MouseEvent event) throws Exception{
        SoundManager.play(SoundManager.MUSIC.MY_WONDER);
    }
    //methods of dice




    Timeline diceTimeLine;
    Integer diceTime;

    public void diceGamePopOver(ActionEvent event) throws Exception{
        final PopOver popOver = new PopOver();
        popOver.setArrowLocation(PopOver.ArrowLocation.BOTTOM_CENTER);
        popOver.setAutoFix(true);
        popOver.setAutoHide(false);
        popOver.setHideOnEscape(false);
        popOver.setDetachable(false);
        //inal GridPane pane = FXMLLoader.load(getClass().getResource("/dice_popover.fxml"));
        final GridPane pane = (GridPane)Main.map.get("dicePopOver");
        popOver.setContentNode(pane);
        popOver.show(resources_grid_0, 1000, 1000);



            if(timeline!=null){
                timeline.stop();
            }
            diceTimeLine = new Timeline();
            diceTime = 5;
            ((Label)pane.lookup("#dice_time")).setText(diceTime.toString());
            //dice_time.setText(diceTime.toString());
            //update
            diceTimeLine.setCycleCount(5);
            EventHandler eventHandler2 = new EventHandler() {
                public void handle(Event event) {
                    diceTime--;
                    ((Label)pane.lookup("#dice_time")).setText(diceTime.toString());
                    if(diceTime==0){
                        diceTimeLine.stop();
                        popOver.hide();

                    }
                }
            };
            KeyFrame keyFrame = new KeyFrame(Duration.seconds(1),eventHandler2);
            diceTimeLine.getKeyFrames().add(keyFrame);
            diceTimeLine.playFromStart();

        //popOver.show((Button)event.getSource());

        /*
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                final PopOver popOver2 = popOver;
                while (true) {
                    try {
                        Thread.sleep(1000);

                        Platform.runLater(new Runnable() {
                            public void run() {
                                try {
                                    Label time = (Label)popOver2.getScene().lookup("#dice_time");
                                    if(time.getText().equals("0"))
                                    time.setText((Integer.parseInt(time.getText()) -1) + "");
                                } catch (Exception e) {
                                }
                            }
                        });
                    } catch (InterruptedException interrupted) {
                        if (isCancelled()) {
                            updateMessage("Cancelled");
                            break;
                        }
                    }
                    System.out.println("aaa");
                }
                return null;
            }
        };
        Thread a = new Thread(task);
        a.start();
        //test scoreboard
        HashMap<String,Integer> scores =new HashMap<String, Integer>();
        scores.put("TheTempleofArtemisinEphesus",30);
        scores.put("TheHangingGardensofBabylon",27);
        scores.put("TheLighthouseofAlexandria",43);
        //scores.put("TheColossusofRhodes",34);
        scores.put("ThePyramidsofGiza",30);
       // scores.put("TheMausoleumofHalicarnassus",44);
        scores.put("TheStatueofZeusinOlympia",37);
        getScoreBoard(scores);


        */
        //BURAYA KADAR

        //testAgeOver
        /*HashMap<String,Integer>militaryTokens = new HashMap<String, Integer>();
        HashMap<String,Integer>defeatTokens = new HashMap<String, Integer>();
        //HashMap<String, Pair<Integer,Integer>> = new
        militaryTokens.put("TheTempleofArtemisinEphesus",1);
        militaryTokens.put("TheHangingGardensofBabylon",2);
        militaryTokens.put("TheLighthouseofAlexandria",1);
        militaryTokens.put("TheColossusofRhodes",0);
        militaryTokens.put("ThePyramidsofGiza",1);
        militaryTokens.put("TheMausoleumofHalicarnassus",2);
        militaryTokens.put("TheStatueofZeusinOlympia",1);
        defeatTokens.put("TheTempleofArtemisinEphesus",1);
        defeatTokens.put("TheHangingGardensofBabylon",0);
        defeatTokens.put("TheLighthouseofAlexandria",1);
        defeatTokens.put("TheColossusofRhodes",2);
        defeatTokens.put("ThePyramidsofGiza",1);
        defeatTokens.put("TheMausoleumofHalicarnassus",0);
        defeatTokens.put("TheStatueofZeusinOlympia",1);*/
        WonderBoard wb = new WonderBoard();
        wb.setName("TheTempleofArtemisinEphesus");
        WonderBoard wb2 = new WonderBoard();
        wb2.setName("TheHangingGardensofBabylon");
        WonderBoard wb3 = new WonderBoard();
        wb3.setName("TheColossusofRhodes");
        WonderBoard wb4 = new WonderBoard();
        wb4.setName("ThePyramidsofGiza");
        WonderBoard wb5 = new WonderBoard();
        wb5.setName("TheLighthouseofAlexandria");
        WonderBoard wb6 = new WonderBoard();
        wb6.setName("TheMausoleumofHalicarnassus");
        WonderBoard wb7 = new WonderBoard();
        wb7.setName("TheStatueofZeusinOlympia");
        wb.setLeftNeighbor(wb2.getName());
        wb2.setLeftNeighbor(wb3.getName());
        wb3.setLeftNeighbor(wb.getName());
        //wb3.setLeftNeighbor(wb4);
        wb4.setLeftNeighbor(wb5.getName());
        //wb5.setLeftNeighbor(wb6);
        wb6.setLeftNeighbor(wb7.getName());
        wb7.setLeftNeighbor(wb.getName());

        wb.getSources().put("shield",7);
        wb2.getSources().put("shield",9);
        wb3.getSources().put("shield",6);
        wb4.getSources().put("shield",8);
        wb5.getSources().put("shield",3);
        wb6.getSources().put("shield",2);
        wb7.getSources().put("shield",11);
        wonderBoards.put(wb.getName(),wb);
        wonderBoards.put(wb2.getName(),wb2);
        wonderBoards.put(wb3.getName(),wb3);
        //wonderBoards.put(wb4.getName(),wb4);
        //wonderBoards.put(wb5.getName(),wb5);
        //wonderBoards.put(wb6.getName(),wb6);
        //wonderBoards.put(wb7.getName(),wb7);

        ageOver(1);


    }
    public void playerEnteredDiceGame(String wonderName){
        diceGamePlayer.add(wonderName);
        dicePopOverRefresh();

    }


    public void dicePopOverRefresh(){
        for(int i = 0; i < diceGamePlayer.size(); i++){
            String wonderName = "TheLighthouseofAlexandria";
            wonderName = diceGamePlayer.get(i);
            wonderName = "#" + wonderName;
            ImageView wonderImage = (ImageView)(roll_dice.getScene().lookup(wonderName));
            Effect colorAdjust = new ColorAdjust();
            wonderImage.setEffect(colorAdjust);
        }
        String winner = "";
        String winnerCard = "";
        Map<String,Integer> noOfDices = new HashMap<String, Integer>();
        boolean diceGameEnds = false;
        if(diceGameEnds){
            for(Map.Entry mapElement : noOfDices.entrySet()) {
                String key = (String) mapElement.getKey();
                String nameOfLabel = "#L" + key;
                Label diceNoLabel = (Label)(roll_dice.getScene().lookup(key));
                String diceNo = noOfDices.get(key) + "";
                diceNoLabel.setText(diceNo);
            }
            ImageView winnersCardImage = (ImageView)(roll_dice.getScene().lookup("#winnersCard"));
            winnersCardImage.setImage(images2.get(winnerCard));
        }

        
    }


    public void play_dice_game(ActionEvent event){
        diceGamePlayer.add("TheLighthouseofAlexandria");
        dicePopOverRefresh();
    }



    @FXML
    Label timer;
    @FXML
    Circle timerCircle;
    @FXML
    Arc timerCircleArc;
    @FXML
    ProgressIndicator timerProgress;
    Integer timeSeconds;
    Timeline timeline;

    Integer timeseconds;



    @FXML
    GridPane my_wonder;
    @FXML
    Label my_wonder_name;
    @FXML
    ImageView my_wonder_stage_0;
    @FXML
    ImageView my_wonder_stage_1;
    @FXML
    ImageView my_wonder_stage_2;

    @FXML
    GridPane left;
    @FXML
    Label left_wonder_name;
    @FXML
    ImageView left_wonder_stage_0;
    @FXML
    ImageView left_wonder_stage_1;
    @FXML
    ImageView left_wonder_stage_2;

    @FXML
    GridPane right;
    @FXML
    Label right_wonder_name;
    @FXML
    ImageView right_wonder_stage_0;
    @FXML
    ImageView right_wonder_stage_1;
    @FXML
    ImageView right_wonder_stage_2;

    @FXML
    GridPane wonder1;
    @FXML
    ImageView wonder_1_stage_0;
    @FXML
    ImageView wonder_1_stage_1;
    @FXML
    ImageView wonder_1_stage_2;

    @FXML
    GridPane wonder2;
    @FXML
    ImageView wonder_2_stage_0;
    @FXML
    ImageView wonder_2_stage_1;
    @FXML
    ImageView wonder_2_stage_2;

    @FXML
    GridPane wonder3;
    @FXML
    ImageView wonder_3_stage_0;
    @FXML
    ImageView wonder_3_stage_1;
    @FXML
    ImageView wonder_3_stage_2;

    @FXML
    GridPane wonder4;
    @FXML
    ImageView wonder_4_stage_0;
    @FXML
    ImageView wonder_4_stage_1;
    @FXML
    ImageView wonder_4_stage_2;



    //card popover definition
    PopOver popOverCard;
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
        popOver.setContentNode(pane);

        /*
        popOverCard.show((ImageView)event.getSource());
        selectedCard =((ImageView) event.getSource()).getId();
        System.out.println(selectedCard + " ppppppppppppppppppppppppppppppppppppp");*/
    }

    public void trade(MouseEvent event)throws Exception{
        PopOver popOver = new PopOver();
        popOver.setArrowLocation(PopOver.ArrowLocation.BOTTOM_CENTER);
        popOver.setHideOnEscape(false);
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
        boolean trade = true;
       // Map<String,Integer> source = wonderBoards.get(Main.wonderID).getSources();
        System.out.println(Main.wonderID);
        List<Card> hand = ServerConnection.cardss.getContainer().get(Main.wonderID);
        System.out.println("OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO  " + selectedCard);


        /*for(Map.Entry<String,Integer> entry : chosen.getCost().getCost().entrySet()){
            if(source.get(entry.getKey()) < (entry.getValue()) ){
                trade = true;
                break;
            }
        } //BURASI BÖYLE ÇÜNKÜ HENÜZ TRADE VE WONDER ALMA YOK*/

        if(!trade){
            System.out.println("Action will be sended"); //hand.indexOf(selectedCard)
            CardAction toSend = new CardAction(this.selection,null,null, Integer.parseInt(selectedCard.substring(4)),Main.wonderID);
            System.out.println(Main.wonderID);
            System.out.println(toSend);
            con.sendAction(toSend,Main.tableID);
        }else{
            tradeClicked(event);
        }
    }

    public void tradeClicked(MouseEvent event)throws Exception{
        trade(event);
        Button temp = (Button)event.getSource();
        Label coinLabel = (Label)temp.getScene().lookup("#t_coin");
        System.out.println(coinLabel.getText());
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
        HashMap<String,Integer> leftTradeMap = new HashMap<String, Integer>();
        HashMap<String,Integer> rightTradeMap = new HashMap<String, Integer>();
        List<Card> hand = handCards.getContainer().get(Main.wonderID);
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

        CardAction toSend = new CardAction(selection,leftTradeMap,rightTradeMap,  hand.indexOf(selectedCard),Main.wonderID);
        con.sendAction(toSend, Main.tableID);
    }

    public void refreshSources(HashMap<String,Integer> sources, GridPane pane, HashMap<String,Boolean> ORsources)throws Exception{
        GridPane tempGrid= pane;
        //refreshing normal sources
        for (Node child : tempGrid.getChildren()) {
            if (child.getId() != null)
                if(child.getId().charAt(0) == 'l'){
                    String nameSource = child.getId().substring(4);
                    Label temp = (Label)child;
                    temp.setText(sources.get(nameSource) + "");
                }
        }
        //refreshing OR sources

        for( Map.Entry mapElement : ORsources.entrySet() ){
                String key = (String) mapElement.getKey();
                String nameOfImageView = "#s" + pane.getId().substring(15) + key;
                ImageView orSourceImageView = (ImageView)sceneOfTable.lookup(nameOfImageView);
                if(ORsources.get(key)){
                    orSourceImageView.setVisible(true);
                }else{
                    orSourceImageView.setVisible(false);
                }
        }
    }

    public void onPress(ActionEvent event)throws Exception{
        System.out.println("asdasda");
        HashMap<String,WonderBoard> wonders = (HashMap<String, WonderBoard>) con.ConvertJson(Main.tableID);
        wonderBoards = wonders;
        System.out.println("refresh çağırılıyor");
        refresh();
    }

    public void refresh()throws Exception {
        sceneOfTable = my_wonder.getScene();
        tableID = Main.tableID;
        wonderID = Main.wonderID;

        sceneOfTable = dice.getScene();
        diceGameOn = false;

        System.out.println(handCards);

        System.out.println("Entered refresh");
        diceGameOn = false;
        sceneOfTable = dice.getScene();

        System.out.println("Entered refresh");
        Hand = 6;
     //   HashMap<String,WonderBoard> wonders = (HashMap<String, WonderBoard>) con.ConvertJson(Main.tableID);
       // wonderBoards = wonders;
        handCards = con.ConvertJsonHand(tableID);
        System.out.println(handCards.getContainer().toString()       + " AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        System.out.println("Entered Test");
       /* //FOR TEST PURPOSES !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!DELETE CODE BELOW
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
        card1.setName("ALTAR");
        Card card2 = new Card();
        card2.setName("ALTAR");
        Card card3 = new Card();
        card3.setName("ALTAR");
        Card card4 = new Card();
        card4.setName("ALTAR");
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
        //OR SOURCES TEST
        HashMap<String, Boolean> orsource = new HashMap<String, Boolean>();
        orsource.put("stoneORwood", false);




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
            refreshSources(wonders.get(key).getSources(),sources_pane, orsource);
            refreshStructures(wonders.get(key),structure_pane);
        }

        // burada her strucure gridi çağırılacak, refreshere gerekli gridler verilecek
       /* refreshStructures(a, left_structure_grid);
        refreshStructures(a, right_structure_grid);*/

        int temp = 3;
        String[] wonders2 = new String[7];
        for(Map.Entry mapElement : wonderBoards.entrySet()){
            String key = (String)mapElement.getKey();
            if(Main.wonderID.equals(wonderBoards.get(key))){
                wonders2[0] = key;
            }else
            if(Main.wonderID.equals(wonderBoards.get(key).getLeftNeighbor())){
                wonders2[1] = key;
            }else
            if(Main.wonderID.equals(wonderBoards.get(key).getRightNeighbor())){
                wonders2[2] = key;
            }else{
                wonders2[temp] = key;
                temp++;
            }

        }




        //FOR TEST PURPOSES!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!DELETE CODE ABOVE
        //ASIL CODE BURADA BAŞLIYOR

        //BURAYA WONDER RESIMLERININ İLK AÇILDIGINDA GÜNCELLENECEĞİ KODU KOYUYORUM.
       /* for(int j = 0; j<7;j++){
            wonderImages[j] = "-fx-background-image: url(\"/WONDERS/"+ wonders2[j] +".jpg\")";
            String str1 = "/WONDERS/" + wonders2[j] + "_STAGE1.jpg";
            String str2 = "/WONDERS/" + wonders2[j] + "_STAGE2.jpg";
            String str3 = "/WONDERS/" + wonders2[j] + "_STAGE3.jpg";
            wonderStages0[j] = new Image(str1);
            wonderStages1[j] = new Image(str2);
            wonderStages2[j] = new Image(str3);
        }

        my_wonder.setStyle(wonderImages[0]);
        my_wonder_name.setText(wonders2[0]);
        my_wonder_stage_0.setImage(wonderStages0[0]);
        my_wonder_stage_1.setImage(wonderStages1[0]);
        my_wonder_stage_2.setImage(wonderStages2[0]);

        left.setStyle(wonderImages[1]);
        left_wonder_name.setText(wonders2[1]);
        left_wonder_stage_0.setImage(wonderStages0[1]);
        left_wonder_stage_1.setImage(wonderStages1[1]);
        left_wonder_stage_2.setImage(wonderStages2[1]);

        right.setStyle(wonderImages[2]);
        right_wonder_name.setText(wonders2[2]);
        right_wonder_stage_0.setImage(wonderStages0[2]);
        right_wonder_stage_1.setImage(wonderStages1[2]);
        right_wonder_stage_2.setImage(wonderStages2[2]);

        if(wonders2[3].equals("")){
            wonder1.setVisible(false);
        }else{
            wonder1.setStyle(wonderImages[3]);
            wonder_1_stage_0.setImage(wonderStages0[3]);
            wonder_1_stage_1.setImage(wonderStages1[3]);
            wonder_1_stage_2.setImage(wonderStages2[3]);
        }if(wonders2[4].equals("")){
            wonder2.setVisible(false);
        }else{
            wonder2.setStyle(wonderImages[4]);
            wonder_2_stage_0.setImage(wonderStages0[4]);
            wonder_2_stage_1.setImage(wonderStages1[4]);
            wonder_2_stage_2.setImage(wonderStages2[4]);
        }if(wonders2[5].equals("")){
            wonder3.setVisible(false);
        }else{
            wonder3.setStyle(wonderImages[5]);
            wonder_3_stage_0.setImage(wonderStages0[5]);
            wonder_3_stage_1.setImage(wonderStages1[5]);
            wonder_3_stage_2.setImage(wonderStages2[5]);
        }if(wonders2[6].equals("")){
            wonder4.setVisible(false);
        }else{
            wonder4.setStyle(wonderImages[6]);
            wonder_4_stage_0.setImage(wonderStages0[6]);
            wonder_4_stage_1.setImage(wonderStages1[6]);
            wonder_4_stage_2.setImage(wonderStages2[6]);
        }



*/




        WonderBoard my_wonder = wonderBoards.get(wonderID);
        //refreshSources(my_wonder.getSources() ,resources_grid_0);
        refreshStructures(my_wonder, structure_grid_0);
        String left_neighbour_name = my_wonder.getLeftNeighbor();
        String right_neighbour_name = my_wonder.getRightNeighbor();

        int enemyNo = 3;

        //burası my wonder haric hepsini güncelliyor
        //1 ve 2 yi ayrıca kontrol ediyor, 1 left neighbour, 2 right neighbour
        for(Map.Entry mapElement : wonderBoards.entrySet()){
            String key = (String)mapElement.getKey();
            String structure_GridName = "#structure_grid_";
            String sourcesGridName = "#resources_grid_";
            String wonderName = wonderBoards.get(key).getName();
            GridPane structure_pane;
            GridPane sources_pane;
            if(key.equals(wonderID)){
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
            HashMap<String,Boolean> oring = wonderBoards.get(wonderName).getOrSources();
            refreshSources(wonderBoards.get(key).getSources(),sources_pane,oring);
            refreshStructures(wonderBoards.get(key),structure_pane);
        }

        // burada her strucure gridi çağırılacak, refreshere gerekli gridler verilecek

       // con.sendAction(new Action());

        refreshHand(handCards);
        timerHandle();

        //stage 1 i yaptım diyelim
        my_wonder_stage_0.setEffect(null);



    }


    public void refreshHand(HandContainer handCards){

        Map<String,List<Card>> temp= handCards.getContainer();
        for(int i = 0; i < 7; i++){
            hand[i].setVisible(true);
            if(temp.get(Main.wonderID).get(i)!=null){
                String leeen = temp.get(wonderID).get(i).getName();
                if(leeen.indexOf(" ")>0)
                leeen = leeen.substring(0,leeen.indexOf(" "))+  leeen.substring(leeen.indexOf(" " ) +1);
                if(leeen.indexOf("i")>0){
                    leeen = leeen.substring(0,leeen.indexOf("i"))+ "I"+   leeen.substring(leeen.indexOf("i" ) +1);
                }

                if(leeen.indexOf("i")>0)
                    leeen = leeen.substring(0,leeen.indexOf("i"))+ "I"+  leeen.substring(leeen.indexOf("i" ) +1);
                if(leeen.indexOf("i")>0)
                    leeen = leeen.substring(0,leeen.indexOf("i"))+ "I"+   leeen.substring(leeen.indexOf("i" ) +1);
                leeen = leeen.toUpperCase();

                hand[i].setImage(images2.get(leeen));
                //handAnimation(hand[i]);
            }
        }
       /* for(int i = 0 ; i <7; i++){
            hand[i].setImage(images2.get("ALTAR"));
        }*/
        handAnimation(hand);

    }

    public void timerHandle(){
        if(timeline !=null){
            timeline.stop();
        }

        timeline =new Timeline();
        timeSeconds = 0;
        timer.setText(timeSeconds.toString());
        timerProgress.setProgress(0);
        timerProgress.setStyle(" -fx-progress-color: #68ba86");
        //update
        timeline.setCycleCount(60);
        EventHandler eventHandler = new EventHandler() {
            public void handle(Event event) {
                timeSeconds++;
                timer.setText(timeSeconds.toString());
                double d = (double)timeSeconds/60;
                timerProgress.setProgress(d);
                if(timeSeconds==60){
                    timeline.stop();
                }
                if(timeSeconds == 45){
                    timerProgress.setStyle(" -fx-progress-color: #de7e2a");
                }if(timeSeconds == 55){
                    timerProgress.setStyle(" -fx-progress-color: #db4332");
                }

            }
        };
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(1),eventHandler);
        timeline.getKeyFrames().add(keyFrame);
        timeline.playFromStart();
    }

    public void handAnimation(ImageView[] hand){
        Duration sec2 = Duration.millis(1500);
        Duration sec3 = Duration.millis(3000);

        /*FadeTransition ft = new FadeTransition(sec3);
        ft.setFromValue(1.0f);
        ft.setToValue(0.3f);
        ft.setCycleCount(2);
        ft.setAutoReverse(true);
        ft.setNode(hand);
        ft.play();*/
        


        for(int i = 0; i<hand.length;i++){
            TranslateTransition tt = new TranslateTransition(sec2);
            tt.setFromX(-((205*i)+57));
            System.out.println(card2.getLayoutX());
            System.out.println(card1.getLayoutX());
            System.out.println(card2.getLayoutX()-card1.getLayoutX());
            tt.setToX(0);
            tt.setCycleCount(1);
            //tt.setAutoReverse(true);
            tt.setNode(hand[i]);
            tt.play();
            RotateTransition rt = new RotateTransition(sec2);
            rt.setByAngle(360);
            rt.setCycleCount(1);
            rt.setAutoReverse(true);
            rt.setNode(hand[i]);
            rt.play();
        }







        /*ScaleTransition st = new ScaleTransition(sec2);
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
        st.play();*/
    }

    public void choice(MouseEvent event) throws Exception{
        Button temp = (Button)event.getSource();
        if(temp.getId().equals("discard"))
            selection = 0;
        else if(temp.getId().equals("build_card"))
            selection = 1;
        else if(temp.getId().equals("build_wonder"))
            selection = 2;
        buildCardClicked(event);
    }

    public void refreshStructures(WonderBoard wonder,GridPane gridName){
        Iterator cardIterator =  wonder.getBuiltCards().entrySet().iterator();
        Scene sceneOfWonder = sceneOfTable;
            System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAA" + gridName.getId().substring(15));
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



    public void ageOver(int ageNo) throws Exception{

        final PopOver popOver = new PopOver();
        popOver.setArrowLocation(PopOver.ArrowLocation.BOTTOM_CENTER);
        popOver.setAutoFix(true);
        popOver.setAutoHide(true);
        popOver.setHideOnEscape(true);
        popOver.setDetachable(false);
        GridPane pane = FXMLLoader.load(getClass().getResource("/age_over.fxml"));
        popOver.setContentNode(pane);


        int i = 0;
        String str1;
        String militaryImagePath = "/militarytoken"+ageNo+".png";
        Image militaryImage = new Image(militaryImagePath);
        Image defeatImage = new Image("/defeattoken.png");

        for(String wonderName: wonderBoards.keySet()){

            ((Label)pane.lookup("#vs" +i)).setText("VS");
            ((Label)pane.lookup("#ageLabel")).setText("END OF THE AGE" + ageNo);


                str1 = "#w1_" + i + "_image";
                String url = "/WONDERS/" + wonderName +".jpg";
                Image image = new Image(url);
                ((ImageView)pane.lookup(str1)).setImage(image);


                str1 = "#w2_" + i + "_image";
                url = "/WONDERS/" + wonderBoards.get(wonderName).getLeftNeighbor() +".jpg";
                ((ImageView)pane.lookup(str1)).setImage(new Image(url));

                if(wonderBoards.get(wonderName).getSources().get("shield")<wonderBoards.get(wonderBoards.get(wonderName).getLeftNeighbor()).getSources().get("shield")){
                    str1 = "#w1_" + i + "_token";
                    ((ImageView)pane.lookup(str1)).setImage(defeatImage);
                    str1 = "#w2_" + i + "_token";
                    ((ImageView)pane.lookup(str1)).setImage(militaryImage);

                }else{
                    str1 = "#w1_" + i + "_token";
                    ((ImageView)pane.lookup(str1)).setImage(militaryImage);
                    str1 = "#w2_" + i + "_token";
                    ((ImageView)pane.lookup(str1)).setImage(defeatImage);

                }

            i++;
        }
        for(int j = i--;j<7;j++){
            ((GridPane)pane.lookup("#g" + j)).setPrefHeight(0);
            ((GridPane)pane.lookup("#g" + j)).setVisible(false);

        }
        popOver.show(resources_grid_0);

        /*final PopOver popOver = new PopOver();
        popOver.setArrowLocation(PopOver.ArrowLocation.BOTTOM_CENTER);
        popOver.setAutoFix(true);
        popOver.setAutoHide(true);
        popOver.setHideOnEscape(true);
        popOver.setDetachable(false);
        GridPane pane = FXMLLoader.load(getClass().getResource("/age_over.fxml"));
        popOver.setContentNode(pane);

        String militaryImagePath = "/militarytoken"+ageNo+".png";
        Image militaryImage = new Image(militaryImagePath);
        Image defeatImage = new Image("/defeattoken.png");
        for(String wonderName : militaryTokens.keySet()){
            int militaryTokenCount = militaryTokens.get(wonderName);
            if(militaryTokenCount>0){
                ((ImageView)pane.lookup("#n1_"+wonderName)).setImage(militaryImage);
                if(militaryTokenCount>1){
                    ((ImageView)pane.lookup("#n2_"+wonderName)).setImage(militaryImage);
                }
            }

            int defeatTokenCount = defeatTokens.get(wonderName);
            if(defeatTokenCount==2) {
                ((ImageView) pane.lookup("#n1_" + wonderName)).setImage(defeatImage);
                ((ImageView) pane.lookup("#n2_" + wonderName)).setImage(defeatImage);
            }
            else if(defeatTokenCount==1){
                ((ImageView) pane.lookup("#n2_" + wonderName)).setImage(defeatImage);
            }
        }

        popOver.show(resources_grid_0);*/
    }
    @FXML
    GridPane scores_grid;
    public void getScoreBoard(HashMap<String,Integer> scores) throws Exception{
        final PopOver popOver = new PopOver();
        popOver.setArrowLocation(PopOver.ArrowLocation.BOTTOM_CENTER);
        popOver.setAutoFix(true);
        popOver.setAutoHide(true);
        popOver.setHideOnEscape(true);
        popOver.setDetachable(false);
        GridPane pane = FXMLLoader.load(getClass().getResource("/score_popover.fxml"));
        popOver.setContentNode(pane);
        String winner = "";
        int winscore = 0;

        for(String wonderName : scores.keySet()){
            System.out.println(scores.get(wonderName));
            System.out.println(pane.lookup("#SCOREBOARD").getId()) ;
            if(scores.get(wonderName)>winscore){
                winscore = scores.get(wonderName);
                winner = wonderName;
            }

            DropShadow ds = new DropShadow();
            Label wonderlabel = (Label) pane.lookup("#L" + wonderName);
            wonderlabel.setEffect(ds);
            System.out.println(GridPane.getRowIndex(wonderlabel));
            //scores_grid.getRowConstraints().get(GridPane.getRowIndex(wonderlabel)).setPercentHeight(-1);
            ((Label)pane.lookup("#s_" + wonderName)).setText(scores.get(wonderName).toString());
            pane.lookup("#" + wonderName).setEffect(null);
        }

        pane.lookup("#L" + winner).setStyle("-fx-text-fill: radial-gradient(radius 100%, green, #33ce2d,green);  -fx-font-weight: bold;-fx-font-size: 20;");
        pane.lookup("#s_" + winner).setStyle("-fx-text-fill: radial-gradient(radius 100%, green, #33ce2d,green);  -fx-font-weight: bold;");
        //pane.lookup("#L" + winner).setEffect(null);

        pane.lookup("#w_" + winner).setVisible(true);

        popOver.show(resources_grid_0);



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
                String name =child.getName();
                if(name.indexOf(" ") >0){
                    name = name.substring(0,name.indexOf(" "))+  name.substring(name.indexOf(" " ) +1);
                }
                name = name.toUpperCase();
                images2.put(child.getName().substring(0,child.getName().length()-4), new Image(temp));
                i++;
            }
        }
        hand[0] = card0;
        hand[1] = card1;
        hand[2] = card2;
        hand[3] = card3;
        hand[4] = card4;
        hand[5] = card5;
        hand[6] = card6;

     /*   if(start_game_controller.TableID.equals("") )
            tableID=join_game_controller.TableID;
        if(join_game_controller.TableID.equals(""))
            tableID=start_game_controller.TableID;
        wonderID = player_id_controller.WonderID;
        tableID = join_game_controller.TableID;
*/

        /*Task<Void> task = new Task<Void>() {
            @Override protected Void call() throws Exception {

                while(true){
                    try {
                        Thread.sleep(5000);

                        Platform.runLater(new Runnable() {
                             public void run() {
                                try{
                                    refresh();
                                }catch (Exception e){

                                }

                            }
                        });


                    } catch (InterruptedException interrupted) {
                        if (isCancelled()) {
                            updateMessage("Cancelled");
                            break;
                        }
                    }

                    System.out.println("aaa");

                }
                return null;
            }


        };*/
        SocketThread socketThread = new SocketThread(this);
        Thread newT = new Thread(socketThread);
        newT.start();
       /* socket = new SocketThread(this);
        socketThread = new Thread(socket);
        Platform.runLater(socketThread);*/
        //socketThread.start();





        //elif test

        //DİYELİMKİ CURRENT BÖYLE GELDİ başarılı
        String[] wonders = {"TheStatueofZeusinOlympia",
                "TheMausoleumofHalicarnassus",
                "ThePyramidsofGiza",
                "TheColossusofRhodes",
                "TheLighthouseofAlexandria",
                "TheHangingGardensofBabylon",
                "TheTempleofArtemisinEphesus"};
        //String[] wonders = {"BABYLON","GIZAH","RHODOS","OLYMPIA","ALEXANDRIA","EPHESOS",""};





        String [] wonderImages = new String[7];
        Image[] wonderStages0 = new Image[7];
        Image[] wonderStages1 = new Image[7];
        Image[] wonderStages2 = new Image[7];
        wonderImages = new String[7];
        wonderStages0 = new Image[7];
        wonderStages1 = new Image[7];
        wonderStages2 = new Image[7];
        String str1,str2,str3;
        for(int j = 0; j<7;j++){
            wonderImages[j] = "-fx-background-image: url(\"/WONDERS/"+ wonders[j] +".jpg\")";
            str1 = "/WONDERS/" + wonders[j] + "_STAGE1.jpg";
            str2 = "/WONDERS/" + wonders[j] + "_STAGE2.jpg";
            str3 = "/WONDERS/" + wonders[j] + "_STAGE3.jpg";
            wonderStages0[j] = new Image(str1);
            wonderStages1[j] = new Image(str2);
            wonderStages2[j] = new Image(str3);
        }

        my_wonder.setStyle(wonderImages[0]);
        my_wonder_name.setText(wonders[0]);
        my_wonder_stage_0.setImage(wonderStages0[0]);
        my_wonder_stage_1.setImage(wonderStages1[0]);
        my_wonder_stage_2.setImage(wonderStages2[0]);

        left.setStyle(wonderImages[1]);
        left_wonder_name.setText(wonders[1]);
        left_wonder_stage_0.setImage(wonderStages0[1]);
        left_wonder_stage_1.setImage(wonderStages1[1]);
        left_wonder_stage_2.setImage(wonderStages2[1]);

        right.setStyle(wonderImages[2]);
        right_wonder_name.setText(wonders[2]);
        right_wonder_stage_0.setImage(wonderStages0[2]);
        right_wonder_stage_1.setImage(wonderStages1[2]);
        right_wonder_stage_2.setImage(wonderStages2[2]);

        if(wonders[3].equals("")){
            wonder1.setVisible(false);
        }else{
            wonder1.setStyle(wonderImages[3]);
            wonder_1_stage_0.setImage(wonderStages0[3]);
            wonder_1_stage_1.setImage(wonderStages1[3]);
            wonder_1_stage_2.setImage(wonderStages2[3]);
        }if(wonders[4].equals("")){
            wonder2.setVisible(false);
        }else{
            wonder2.setStyle(wonderImages[4]);
            wonder_2_stage_0.setImage(wonderStages0[4]);
            wonder_2_stage_1.setImage(wonderStages1[4]);
            wonder_2_stage_2.setImage(wonderStages2[4]);
        }if(wonders[5].equals("")){
            wonder3.setVisible(false);
        }else{
            wonder3.setStyle(wonderImages[5]);
            wonder_3_stage_0.setImage(wonderStages0[5]);
            wonder_3_stage_1.setImage(wonderStages1[5]);
            wonder_3_stage_2.setImage(wonderStages2[5]);
        }if(wonders[6].equals("")){
            wonder4.setVisible(false);
        }else{
            wonder4.setStyle(wonderImages[6]);
            wonder_4_stage_0.setImage(wonderStages0[6]);
            wonder_4_stage_1.setImage(wonderStages1[6]);
            wonder_4_stage_2.setImage(wonderStages2[6]);
        }


        popOverCard = new PopOver();
        popOverCard.setArrowLocation(PopOver.ArrowLocation.BOTTOM_CENTER);
        popOverCard.setAutoFix(true);
        popOverCard.setAutoHide(true);
        popOverCard.setHideOnEscape(true);
        popOverCard.setDetachable(false);
        GridPane pane = FXMLLoader.load(getClass().getResource("/select_card_popover.fxml"));
        popOverCard.setContentNode(pane);



       /* SocketThread socketThread = new SocketThread(this);
        Thread newT = new Thread(socketThread);
        newT.start();*/
    }





    public void initialize(URL location, ResourceBundle resources)  {
        try{
            startPoint();
        }catch (Exception e){

        }
    }
}