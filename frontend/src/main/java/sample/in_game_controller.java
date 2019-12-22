package sample;

import com.fasterxml.jackson.databind.deser.std.MapEntryDeserializer;
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
import javafx.scene.control.*;
import javafx.scene.effect.*;
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
    private boolean played = false;
    String wonderID;
    WonderBoard sampleWonderBoard = new WonderBoard();
    @FXML
     GridPane resources_grid_0;
    @FXML
    GridPane structure_grid_0;

    @FXML
            Button discard;
    @FXML
    Button build_wonder;
    @FXML
    Button build_card;



    Map<String,String> myStructuresBuilded = new HashMap<String,String>();
    Map<String,String> leftStructuresBuilded = new HashMap<String,String>();
    Map<String,String> rightStructuresBuilded = new HashMap<String,String>();
    static Map<String,Integer> mySources = new HashMap<String, Integer>();
    static String selectedCard ;
    int Hand;
    int myStructureNo = 0;
    public HashMap<String,Image> images2 = new HashMap<String, Image>();
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
    static Map<String,WonderBoard> wonderBoards ;
    @FXML
    GridPane trade_sources_grid;
    static int selection =0;
    static HandContainer handCards = new HandContainer();
    static Scene sceneOfTable;
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
    Image[] wonderStages2 =new Image[7];
    Image[] wonderStages0 = new Image[7];
    Image[] wonderStages1 = new Image[7];
    String [] wonderImages = new String[7];



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

    @FXML
    Parent root;
    @FXML
    public void mainMenuButton(MouseEvent event) throws Exception{

        /*Scene scene1 = roll_dice.getScene();
        root = FXMLLoader.load(getClass().getResource("/play_game_screen.fxml"));
        scene1.setRoot(root);*/
    }


    @FXML
    Button tradeBuy;


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
        wb3.setLeftNeighbor(wb4.getName());
        wb4.setLeftNeighbor(wb5.getName());
        wb5.setLeftNeighbor(wb6.getName());
        wb6.setLeftNeighbor(wb7.getName());
        wb7.setLeftNeighbor(wb.getName());

        wb.getSources().put("shield",7);
        wb2.getSources().put("shield",9);
        wb3.getSources().put("shield",6);
        wb4.getSources().put("shield",8);
        wb5.getSources().put("shield",3);
        wb6.getSources().put("shield",2);
        wb7.getSources().put("shield",11);



        /*wonderBoards.put(wb.getName(),wb);
        wonderBoards.put(wb2.getName(),wb2);
        wonderBoards.put(wb3.getName(),wb3);
        wonderBoards.put(wb4.getName(),wb4);
        wonderBoards.put(wb5.getName(),wb5);
        wonderBoards.put(wb6.getName(),wb6);
        wonderBoards.put(wb7.getName(),wb7);

        ageOver(1);*/

        HashMap<String,Integer> scores = new HashMap<String, Integer>();
        scores.put("TheTempleofArtemisinEphesus",20);

        scores.put("TheHangingGardensofBabylon",16);
        scores.put("TheColossusofRhodes",13);
        scores.put("ThePyramidsofGiza",17);

        scores.put("TheLighthouseofAlexandria",19);

        scores.put("TheMausoleumofHalicarnassus",25);

        scores.put("TheStatueofZeusinOlympia",18);
     getScoreBoard(scores);


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
    static PopOver popOverCard;
    public void selectCard(MouseEvent event)throws Exception{
        PopOver popOver = new PopOver();
        popOver.setArrowLocation(PopOver.ArrowLocation.BOTTOM_CENTER);
        popOver.setAutoFix(true);
        popOver.setAutoHide(true);
        popOver.setHideOnEscape(true);
        popOver.setDetachable(false);
        GridPane pane = (GridPane)Main.map.get("select_card_popover");
       popOver.setContentNode(pane);
        popOver.show((ImageView)event.getSource());
        selectedCard =((ImageView) event.getSource()).getId();
        popOver.setContentNode(pane);


        /*popOverCard.show((ImageView)event.getSource());
        selectedCard =((ImageView) event.getSource()).getId();
        System.out.println(selectedCard + " ppppppppppppppppppppppppppppppppppppp");*/
    }

    public void zoomTopStructure(MouseEvent event)throws Exception{
        zoomStructure(event, PopOver.ArrowLocation.BOTTOM_CENTER);
    }
    public void zoomBottomStructure(MouseEvent event)throws Exception{
        zoomStructure(event, PopOver.ArrowLocation.TOP_CENTER);
    }

    private void zoomStructure(MouseEvent event, PopOver.ArrowLocation loc)throws Exception{
        PopOver popOver = new PopOver();
        popOver.setArrowLocation(loc);
        popOver.setAutoFix(true);
        popOver.setAutoHide(true);
        popOver.setHideOnEscape(true);
        popOver.setDetachable(false);
        GridPane pane =  (GridPane) Main.map.get("zoom");
        popOver.setContentNode(pane);
        ((ImageView)pane.lookup("#zoom_image")).setImage(((ImageView)event.getSource()).getImage());
        popOver.show((ImageView)event.getSource());


    }
    static PopOver popOver2;
    public void trade(MouseEvent event)throws Exception{
        PopOver popOver = new PopOver();
        popOver.setArrowLocation(PopOver.ArrowLocation.BOTTOM_CENTER);
        popOver.setAutoFix(true);
        popOver.setAutoHide(true);
        popOver.setHideOnEscape(true);
        popOver.setDetachable(false);
        GridPane pane = (GridPane) Main.map.get("trade_popover");
        popOver.setContentNode(pane);

        ((Label)pane.lookup("#Llt_wood")).setText("0");
        ((Label)pane.lookup("#Llt_stone")).setText("0");
        ((Label)pane.lookup("#Llt_aclay")).setText("0");
        ((Label)pane.lookup("#Llt_ore")).setText("0");
        ((Label)pane.lookup("#Llt_papyrus")).setText("0");
        ((Label)pane.lookup("#Llt_loom")).setText("0");
        ((Label)pane.lookup("#Llt_glass")).setText("0");
        ((Label)pane.lookup("#Lrt_wood")).setText("0");
        ((Label)pane.lookup("#Lrt_stone")).setText("0");
        ((Label)pane.lookup("#Lrt_aclay")).setText("0");
        ((Label)pane.lookup("#Lrt_ore")).setText("0");
        ((Label)pane.lookup("#Lrt_papyrus")).setText("0");
        ((Label)pane.lookup("#Lrt_loom")).setText("0");
        ((Label)pane.lookup("#Lrt_glass")).setText("0");


        //BU TEST
        ((Label)pane.lookup("#t_coin")).setText("5");

        //BU DOĞRUSU
        //((Label)pane.lookup("#t_coin")).setText(wonderBoards.get(Main.wonderID).getSources().get("coin").toString());
        popOver.show((Button)event.getSource(),50,50);



    }

    public void buildCardClicked(MouseEvent event) throws Exception{
        boolean trade = true;
        // Map<String,Integer> source = wonderBoards.get(Main.wonderID).getSources();
        System.out.println(Main.wonderID);
        List<Card> hand = ServerConnection.cardss.getContainer().get(Main.wonderID);

        int index = Integer.parseInt(selectedCard.substring(4));
        if(selection != 0)
            trade = isPossible(hand.get(handSort.get(index)).getCost());
        System.out.println("OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO " + selectedCard);

        if(trade){
            System.out.println("Action will be sended"); //hand.indexOf(selectedCard)
            CardAction toSend = new CardAction(this.selection,null,null, handSort.get(Integer.parseInt(selectedCard.substring(4))),Main.wonderID);
            System.out.println(Main.wonderID);
            System.out.println(toSend);
            String isSuccess = con.sendAction(toSend,Main.tableID);
            if(isSuccess.equals("başarılı")){
                played = true;
                DropShadow selectEffect = new DropShadow();
                selectEffect.setHeight(40.0);
                selectEffect.setWidth(40.0);
                selectEffect.setSpread(0.5);
                selectEffect.setColor(Color.BLUE);
                for(int i = 0; i<7;i++){
                    ((Accordion)Main.map.get("home")).getScene().lookup("#card" + i).setEffect(null);
                }
                ((Accordion)Main.map.get("home")).getScene().lookup("#" + selectedCard).setEffect(selectEffect);
                /*int cardIndex = Integer.parseInt(selectedCard.charAt(4) + "");
                System.out.println(hand[cardIndex]);

                hand[cardIndex].setEffect(selectEffect);*/
            }

        }else{
            trade(event);

        }
    }
    private boolean isPossible(Cost cost){
        System.out.println(wonderBoards.get(Main.wonderID));
        System.out.println(wonderBoards.get(Main.wonderID).getSourcesToCalculate());
        ListIterator it = wonderBoards.get(Main.wonderID).getSourcesToCalculate().listIterator();
        String sourceToCheck;

        while (it.hasNext()) {
            sourceToCheck = (String) it.next();
            int sourceFoundIndex = 0;
            for (Map.Entry<String, Integer> entry : cost.getCost().entrySet()) {
                if (entry.getValue() != 0) {
                    String tmpToSearch = "";
                    for (int i = 0; i < entry.getValue(); i++) {
                        tmpToSearch += entry.getKey().charAt(0);
                    }
                    if (!sourceToCheck.contains(tmpToSearch)) {
                        break;
                    }
                }
                sourceFoundIndex++;
            }

            if(sourceFoundIndex == cost.getCost().entrySet().size())
                return true;
        }
        return false;
    }

    //Trade attiributes
    @FXML
    Label t_coin;

    public void tradeClicked(MouseEvent event)throws Exception{
        Button temp = (Button)event.getSource();
        Label coinLabel = (Label) tradeBuy.getScene().lookup("#t_coin");
        System.out.println(coinLabel.getText());
        int threshHold = 1;
        HashMap<String, Integer> leftDiscount = wonderBoards.get(Main.wonderID).getLeftDiscount();
        HashMap<String, Integer> rightDiscount = wonderBoards.get(Main.wonderID).getRightDiscount();
        if(temp.getId().substring(0,1).equals("l")){
            threshHold = leftDiscount.get(temp.getId().substring(5)) - 1 ;//burda discount varsa 1 yerine 0 oluyor böylece threshold 0 ken 0 ı
        }else if(temp.getId().substring(0,1).equals("r")){           //üstünde para olunca basabilecen tuşa
            threshHold = rightDiscount.get(temp.getId().substring(5)) - 1; //burdada aynı şekilde
        }


        if(coinLabel.getText().equals("0")) //////////
               coinLabel.setText(""+ mySources.get("coin"));
        Scene tempScene= trade_sources_grid.getScene();
        String labelName = "#";
        if(temp.getId().substring(0,4).equals("ltbp") && Integer.parseInt(coinLabel.getText()) > threshHold){
            System.out.println(Integer.parseInt(t_coin.getText())+ "asdas");
            labelName += "Llt_" + temp.getId().substring(5);
            Label tb = (Label) tempScene.lookup(labelName);
            tb.setText( Integer.parseInt(tb.getText()) + 1 + "");
            coinLabel.setText((Integer.parseInt(coinLabel.getText()) - (threshHold+1)) + "");
        }else if (temp.getId().substring(0,4).equals("ltbm") ){
            labelName += "Llt_" + temp.getId().substring(5);
            Label tb = (Label) tempScene.lookup(labelName);
            if(!tb.getText().equals("0")){
                tb.setText( Integer.parseInt(tb.getText()) - 1 + "");
                coinLabel.setText((Integer.parseInt(coinLabel.getText()) + (threshHold+1)) + "");
            }

        }else if(temp.getId().substring(0,4).equals("rtbm") ){
            labelName += "Lrt_" + temp.getId().substring(5);
            Label tb = (Label) tempScene.lookup(labelName);
            if(!tb.getText().equals("0")){
                tb.setText( Integer.parseInt(tb.getText()) - 1 + "");
                coinLabel.setText((Integer.parseInt(coinLabel.getText()) + (threshHold+1)) + "");
            }

        }else if(temp.getId().substring(0,4).equals("rtbp") && Integer.parseInt(coinLabel.getText()) > threshHold){
            labelName += "Lrt_" + temp.getId().substring(5);
            Label tb = (Label) tempScene.lookup(labelName);
            tb.setText( Integer.parseInt(tb.getText()) + 1 + "");
            coinLabel.setText((Integer.parseInt(coinLabel.getText()) - (threshHold+1)) + "");
        }
        System.out.println(labelName);

    }

    @FXML
    GridPane trade_sources_grid2;
    public void tradeBuyPressed(MouseEvent event) throws Exception{
        HashMap<String,Integer> leftTradeMap = new HashMap<String, Integer>();
        HashMap<String,Integer> rightTradeMap = new HashMap<String, Integer>();
        List<Card> hand = handCards.getContainer().get(Main.wonderID);
        if(selection == 1 || selection == 2){
            for (Node child : trade_sources_grid.getChildren()) {// bu kod biraz çirkin, böyle olmasının bir nedeni var, değiştirme
                if(child.getId() != null && child.getId().substring(0,2).equals("Ll")){
                    Label temp = (Label)child;
                    String sourceValue = temp.getText();
                    leftTradeMap.put( child.getId().substring(4), Integer.parseInt(sourceValue));
                }
            }
            for (Node child : trade_sources_grid2.getChildren()) {// bu kod biraz çirkin, böyle olmasının bir nedeni var, değiştirme
                if(child.getId() != null && child.getId().substring(0,2).equals("Lr")){
                    Label temp = (Label)child;
                    String sourceValue = temp.getText();
                    rightTradeMap.put( child.getId().substring(4), Integer.parseInt(sourceValue));
                }
            }
        }

        CardAction toSend = new CardAction(selection,leftTradeMap,rightTradeMap,  handSort.get(Integer.parseInt(selectedCard.substring(4))),Main.wonderID);
        con.sendAction(toSend, Main.tableID);
    }

    public void refreshSources(HashMap<String,Integer> sources, GridPane pane, HashMap<String,Boolean> ORsources, GridPane pane2)throws Exception{
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
        // burayayanlış geliyor, eğer or lu birşey oynarsan yanlış geliyor

        for( Map.Entry mapElement : ORsources.entrySet() ){
                String key = (String) mapElement.getKey();
                String nameOfImageView = "#s" + pane2.getId().substring(15,16) + key;
                ImageView orSourceImageView = (ImageView)sceneOfTable.lookup(nameOfImageView);
                System.out.println(nameOfImageView);
                if(ORsources.get(key)){
                    orSourceImageView.setVisible(true);
                }else{
                    orSourceImageView.setVisible(false);
                }
        }

        //this code refreshes disounts,  refreshdiscount
        HashMap<String, Integer> leftDiscount = wonderBoards.get(Main.wonderID).getLeftDiscount();
        HashMap<String, Integer> rightDiscount = wonderBoards.get(Main.wonderID).getRightDiscount();
        String discountImageView = "";
      /*  for(Map.Entry entry: leftDiscount.entrySet()){
            discountImageView = "dl";
            discountImageView = discountImageView + (String)entry.getKey();
            ImageView discountImage = (ImageView)sceneOfTable.lookup("#" + discountImageView);
            if(leftDiscount.get((String)entry.getKey()) != 2){
                discountImage.setVisible(true);
            }else{
                discountImage.setVisible(false);
            }
        }*/
       /* for(Map.Entry entry: rightDiscount.entrySet()){
            discountImageView = "dr";
            discountImageView = discountImageView + (String)entry.getKey();
            ImageView discountImage = (ImageView)dice.getScene().lookup("#" + discountImageView);
            if(rightDiscount.get((String)entry.getKey()) != 2){
                discountImage.setVisible(true);
            }else{
                discountImage.setVisible(false);
            }
        }*/
    }

    public void onPress(ActionEvent event)throws Exception{
        System.out.println("asdasda");

        System.out.println("refresh çağırılıyor");
        refresh();
    }

    public void refresh()throws Exception {
        HashMap<String,WonderBoard> wonders = (HashMap<String, WonderBoard>) con.ConvertJson(Main.tableID);
        wonderBoards = wonders;

        //HashMap<String,Integer> militaryPoint = con.getMilitaryPoint();
        //System.out.println(militaryPoint.toString());

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


       //bura commentten çıkacak, çıkacakkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk







        WonderBoard my_wonder = wonderBoards.get(wonderID);
        mySources= my_wonder.getSources();
        //refreshSources(my_wonder.getSources() ,resources_grid_0);
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
            GridPane sources_pane_OR =(GridPane)sceneOfTable.lookup(sourcesGridName + "o");
            structure_pane = (GridPane)sceneOfTable.lookup(structure_GridName);
            HashMap<String,Boolean> oring = wonderBoards.get(wonderName).getOrSources();
            refreshSources(wonderBoards.get(key).getSources(),sources_pane,oring,sources_pane_OR);
            refreshStructures(wonderBoards.get(key),structure_pane);
        }

        // burada her strucure gridi çağırılacak, refreshere gerekli gridler verilecek

       // con.sendAction(new Action());

        refreshHand(handCards);
        timerHandle();
        System.out.println(wonderBoards.get(Main.wonderID).getSources().get("victory point"));
        System.out.println(wonderBoards.get(Main.wonderID).getCurrentStage());


        wonderBoards.get("a").getCurrentStage();
        int i = 0;
        wonderBoards.get(Main.wonderID).getCurrentStage();
        int right =wonderBoards.get(wonderBoards.get(Main.wonderID).getRightNeighbor()).getCurrentStage();
        int left = wonderBoards.get(wonderBoards.get(Main.wonderID).getLeftNeighbor()).getCurrentStage();
        int my = wonderBoards.get(Main.wonderID).getCurrentStage();
        if(right > 0)
            right_wonder_stage_0.setEffect(null);
        if(left > 0)
            left_wonder_stage_0.setEffect(null);
        if(my > 0)
            my_wonder_stage_0.setEffect(null);
        if(right > 1)
            right_wonder_stage_1.setEffect(null);
        if(left > 1)
            left_wonder_stage_1.setEffect(null);
        if(my >1)
            my_wonder_stage_1.setEffect(null);
        if(right > 2)
            right_wonder_stage_2.setEffect(null);
        if(left > 2)
            left_wonder_stage_2.setEffect(null);
        if(my > 2)
            my_wonder_stage_2.setEffect(null);
       /* for(Map.Entry element: wonderBoards){

        }*/
        //stage 1 i yaptım diyelim
        //my_wonder_stage_0.setEffect(null);

    }

    @FXML
    ImageView wonder_image;
    @FXML
    Accordion in_game_accordion;

    @FXML
    TitledPane firstPane;



    public void beginRefresh(HashMap<String,WonderBoard> a) throws Exception{
        //System.out.println("START POINTE GIRDIIIIIIIIIIIII!");
        /*int i = 0;
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
        }*/
        in_game_accordion.setExpandedPane(firstPane);
        System.out.println("expanded");

        hand[0] = card0;
        hand[1] = card1;
        hand[2] = card2;
        hand[3] = card3;
        hand[4] = card4;
        hand[5] = card5;
        hand[6] = card6;

        wonderBoards = a;
        int temp = 3;
        String[] wonders2 = new String[7];
        for(Map.Entry mapElement : wonderBoards.entrySet()){
            String key = (String)mapElement.getKey();
            System.out.println(key);
            if(wonderBoards.get(Main.wonderID).getWonderName().equals(wonderBoards.get(key).getWonderName())){
                wonders2[0] = wonderBoards.get(key).getWonderName();
                System.out.println("birinci if " +  wonderBoards.get(key).getWonderName());
            }else
            if(wonderBoards.get(key).getWonderName().equals(wonderBoards.get(wonderBoards.get(Main.wonderID).getLeftNeighbor()).getWonderName())){
                wonders2[1] = wonderBoards.get(key).getWonderName();
                System.out.println("ikinci if " +  wonderBoards.get(key).getWonderName());
            }else
            if(wonderBoards.get(key).getWonderName().equals(wonderBoards.get(wonderBoards.get(Main.wonderID).getRightNeighbor()).getWonderName())){
                wonders2[2] = wonderBoards.get(key).getWonderName();
                System.out.println("üçüncü if " +  wonderBoards.get(key).getWonderName());
            }else{
                wonders2[temp] = wonderBoards.get(key).getWonderName();
                System.out.println("dördün cü if " +  wonderBoards.get(key).getWonderName());
                temp++;
            }

        }



        //FOR TEST PURPOSES!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!DELETE CODE ABOVE
        //ASIL CODE BURADA BAŞLIYOR

        //BURAYA WONDER RESIMLERININ İLK AÇILDIGINDA GÜNCELLENECEĞİ KODU KOYUYORUM.
        for(int j = 0; j<temp;j++){
            wonderImages[j] = "-fx-background-image: url(\"/WONDERS/"+ wonders2[j] +".jpg\")";
            String str1 = "/WONDERS/" + wonders2[j] + "_STAGE1.jpg";
            String str2 = "/WONDERS/" + wonders2[j] + "_STAGE2.jpg";
            String str3 = "/WONDERS/" + wonders2[j] + "_STAGE3.jpg";
            System.out.println(str3 + "nnnnnnnnnnnnnnnnnnnnnnnnnnnn");
            System.out.println(wonders2[j] + "nnnnnnnnnnnnnnnnnnnnnnnnnnnn");
            wonderStages0[j] = new Image(str1);
            wonderStages1[j] = new Image(str2);
            wonderStages2[j] = new Image(str3);
        }

        System.out.println("/WONDERS/" + wonders2[0] + ".jpg");
        wonder_image.setImage(new Image("/WONDERS/" + wonders2[0] + ".jpg"));
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

        if(wonders2[3] == null){
            wonder1.setVisible(false);
        }else{
            wonder1.setStyle(wonderImages[3]);
            wonder_1_stage_0.setImage(wonderStages0[3]);
            wonder_1_stage_1.setImage(wonderStages1[3]);
            wonder_1_stage_2.setImage(wonderStages2[3]);
        }if(wonders2[4]== null){
            wonder2.setVisible(false);
        }else{
            wonder2.setStyle(wonderImages[4]);
            wonder_2_stage_0.setImage(wonderStages0[4]);
            wonder_2_stage_1.setImage(wonderStages1[4]);
            wonder_2_stage_2.setImage(wonderStages2[4]);
        }if(wonders2[5] == null){
            wonder3.setVisible(false);
        }else{
            wonder3.setStyle(wonderImages[5]);
            wonder_3_stage_0.setImage(wonderStages0[5]);
            wonder_3_stage_1.setImage(wonderStages1[5]);
            wonder_3_stage_2.setImage(wonderStages2[5]);
        }if(wonders2[6]==null){
            wonder4.setVisible(false);
        }else{
            wonder4.setStyle(wonderImages[6]);
            wonder_4_stage_0.setImage(wonderStages0[6]);
            wonder_4_stage_1.setImage(wonderStages1[6]);
            wonder_4_stage_2.setImage(wonderStages2[6]);
        }

/*
        popOverCard = new PopOver();
        popOverCard.setArrowLocation(PopOver.ArrowLocation.BOTTOM_CENTER);
        popOverCard.setAutoFix(true);
        popOverCard.setAutoHide(true);
        popOverCard.setHideOnEscape(true);
        popOverCard.setDetachable(false);
        GridPane pane = (GridPane) Main.map.get("/select_card_popover.fxml"));
        popOverCard.setContentNode(pane);

        popOver2 = new PopOver();
        popOver2.setArrowLocation(PopOver.ArrowLocation.BOTTOM_CENTER);
        popOver2.setAutoFix(true);
        popOver2.setAutoHide(true);
        popOver2.setHideOnEscape(true);
        popOver2.setDetachable(false);
        GridPane pane2 = (GridPane) Main.map.get("/trade_popover.fxml"));
        popOver2.setContentNode(pane2);*/









        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                System.out.println("LLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL");

                    try {
                        Thread.sleep(350);
                        Platform.runLater(new Runnable() {
                            public void run() {
                                try {
                                    refresh();
                                } catch (Exception e) {

                                }

                            }
                        });
                    } catch (InterruptedException interrupted) {
                        if (isCancelled()) {
                            updateMessage("Cancelled");

                        }
                    }

                return null;
            }
        };
        Thread t = new Thread(task);
        t.start();
    }

    static HashMap<Integer,Integer> handSort= new HashMap<Integer, Integer>();
    static boolean roundEnds = false;
    public void refreshHand(HandContainer handCards)throws Exception{
        played = false;
        ArrayList<String> temphand = new ArrayList<String>();
        int noOf1 = 0;
        int noOf2 =0;
        Map<String,List<Card>> temp= handCards.getContainer();
        for(int i = 0; i < 7; i++){
            //hand[i].setEffect(null);
            //hand[i].setVisible(true);
            if(temp.get(Main.wonderID).get(i)!=null){
                String leeen = temp.get(wonderID).get(i).getName();
                if(leeen.indexOf(" ")>0)
                leeen = leeen.substring(0,leeen.indexOf(" "))+  leeen.substring(leeen.indexOf(" " ) +1);
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
                handSort.put(noOf2,noOf1);
                temphand.add(leeen);
                //hand[i].setImage(images2.get(leeen));
                //handAnimation(hand[i]);
                noOf1++;
                noOf2++;
            }else{
                noOf1++;
                System.out.println("laaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
                //hand[i].setVisible(false);
                //hand[i].setDisable(false);
            }
            /**/

        }


        for(int i = 0; i<temphand.size(); i++){
            hand[i].setEffect(null);
            hand[i].setVisible(true);
            hand[i].setImage(images2.get(temphand.get(i)));
        }
        for(int i = temphand.size(); i<7;i++){
            hand[i].setEffect(null);
            hand[i].setVisible(false);
            //hand[i].setDisable(false);

        }
       /* for(int i = 0 ; i <7; i++){
            hand[i].setImage(images2.get("ALTAR"));
        }*/
       /*int a = 0;
       for(int i = 0; i<7;i++){
           hand[i].setEffect(null);
           hand[i].setVisible(true);
           if(temphand[i] != null) {

               hand[a] = temphand[i];
               a++;
           }
       }
       for(int k = a; k<7;k++){
           hand[k].setVisible(false);
           hand[k].setDisable(false);
       }*/
        handAnimation(hand);

    }

    public void timerHandle() {
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
                    try{
                        if(played == false){
                            CardAction toSend = new CardAction(0,null,null,  handSort.get(0),Main.wonderID);
                            con.sendAction(toSend, Main.tableID);

                        }
                    }catch(Exception e){}
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
            System.out.println("LLLLLLLLLLLLLLLLLLLLLLLLLLLL" + gridName.getId().substring(15));
            String nameOfImageView = "#structure_" + gridName.getId().substring(15) + "_" + noOfImage; //15 çünkü structure_0 derken 11 den başlarsan 0 ı alırsın
            Map.Entry mapElement = (Map.Entry)cardIterator.next();
            Card marks = ((Card)mapElement.getValue());
            ImageView structureImagePlace = (ImageView) sceneOfWonder.lookup(nameOfImageView);
            String leeen = marks.getName();

            if(leeen.indexOf(" ")>0)
                leeen = leeen.substring(0,leeen.indexOf(" "))+  leeen.substring(leeen.indexOf(" " ) +1);
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
            structureImagePlace.setVisible(true);
            structureImagePlace.setImage(images2.get(leeen));
            noOfImage++;
            if(gridName.getId().substring(15).equals("0")){
                if(leeen.equals("EAST_TRADING_POST")){
                    sceneOfWonder.lookup("#et_d_0").setVisible(true);
                }else if(leeen.equals("WEST_TRADING_POST")){
                    sceneOfWonder.lookup("#wt_d_0").setVisible(true);
                }else if(leeen.equals("MARKETPLACE")){
                    sceneOfWonder.lookup("#mp_d_0").setVisible(true);
                }
            }
        }
    }



    public void ageOver(int ageNo) throws Exception{
        System.out.println("ENTERED AGE OVER");
        final PopOver popOver = new PopOver();
        popOver.setArrowLocation(PopOver.ArrowLocation.BOTTOM_CENTER);
        popOver.setAutoFix(true);
        popOver.setAutoHide(true);
        popOver.setHideOnEscape(true);
        popOver.setDetachable(false);
        System.out.println("ENTERED AGE OVER 1");
        GridPane pane = (GridPane) Main.map.get("age_over");
        System.out.println("ENTERED AGE OVER 2");
       // GridPane pane = (GridPane)FXMLLoader.load(getClass().getResource("/age_over.fxml"));
        popOver.setContentNode(pane);
        System.out.println("ENTERED AGE OVER3");
        int i = 0;
        String str1;
        String militaryImagePath = "/militarytoken"+ageNo+".png";
        Image militaryImage = new Image(militaryImagePath);
        Image defeatImage = new Image("/defeattoken.png");

        System.out.println("ENTERED AGE OVER5");

        for(String wonderName: wonderBoards.keySet()){
            System.out.println("ENTERED AGE OVER10");
            ((Label)pane.lookup("#vs" +i)).setText("VS");
            System.out.println("ENTERED AGE ooooooooooooo");
            ((Label)pane.lookup("#ageLabel")).setText("END OF THE AGE" + ageNo);

            System.out.println("ENTERED AGE 77777777777777777");
                str1 = "#w1_" + i + "_image";
                String url = "/WONDERS/" + wonderBoards.get(wonderName).getWonderName() +".jpg";
                Image image = new Image(url);
            System.out.println("ENTERED AGE ppppppppppppppppppppppppppppp5");
                ((ImageView)pane.lookup(str1)).setImage(image);

            System.out.println("ENTERED AGE OVER11");
                str1 = "#w2_" + i + "_image";
                url = "/WONDERS/" + wonderBoards.get(wonderBoards.get(wonderName).getLeftNeighbor()).getWonderName() +".jpg";
                ((ImageView)pane.lookup(str1)).setImage(new Image(url));

                if(wonderBoards.get(wonderName).getSources().get("zshield")<wonderBoards.get(wonderBoards.get(wonderName).getLeftNeighbor()).getSources().get("zshield")){
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
            System.out.println("ENTERED AGE OVER12");
            i++;
        }
        System.out.println("ENTERED AGE OVER 6");
        for(int j = i--;j<7;j++){
            ((GridPane)pane.lookup("#g" + j)).setPrefHeight(0);
            ((GridPane)pane.lookup("#g" + j)).setVisible(false);

        }
        System.out.println("ENTERED AGE OVER 7");
        popOver.show(resources_grid_0);
        System.out.println("ENTERED AGE OVER8");
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
        System.out.println("end AGE OVER");
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

        ArrayList<Map.Entry<String,Integer>> sorted = new ArrayList<Map.Entry<String, Integer>>(scores.entrySet());
        Collections.sort(sorted, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return (o2.getValue()).compareTo(o1.getValue());
            }
        });
        for(int i = 0; i<sorted.size();i++){
            String wonder = sorted.get(i).getKey();
            if(wonder.equals("TheColossusofRhodes")){
                ((Label) pane.lookup("#"+i+"Label")).setText("THE COLOSSUS OF RHODES");
            }else if(wonder.equals("TheHangingGardensofBabylon")){
                ((Label) pane.lookup("#"+i+"Label")).setText("THE HANGING GARDENS OF BABYLON");
            }else if(wonder.equals("TheLighthouseofAlexandria")){
                ((Label) pane.lookup("#"+i+"Label")).setText("THE LIGHTHOUSE OF ALEXANDRIA");
            }else if(wonder.equals("TheMausoleumofHalicarnassus")){
                ((Label) pane.lookup("#"+i+"Label")).setText("THE MAUSOLEUM OF HALICARNASSUS");
            }else if(wonder.equals("ThePyramidsofGiza")){
                ((Label) pane.lookup("#"+i+"Label")).setText("THE PYRAMIDS OF GIZA");
            }else if(wonder.equals("TheStatueofZeusinOlympia")){
                ((Label) pane.lookup("#"+i+"Label")).setText("THE STATUE OF ZEUS IN OLYMPIA");
            }else if(wonder.equals("TheTempleofArtemisinEphesus")){
                ((Label) pane.lookup("#"+i+"Label")).setText("THE TEMPLE OF ARTEMIS IN EPHESUS");
            }
            String imageUrl = "/WONDERS/"+sorted.get(i).getKey() +".jpg";
            ((ImageView) pane.lookup("#"+i+"image")).setImage(new Image(imageUrl));
            pane.lookup("#"+i+"image").setVisible(true);
            ((Label) pane.lookup("#s_"+i)).setText(sorted.get(i).getValue().toString());
            System.out.println(sorted.get(i));
        }
        popOver.show(resources_grid_0);
    }



    public void startPoint() throws Exception {
        System.out.println("START POINTE GIRDIIIIIIIIIIIII!");
    /*    int i = 0;
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
        hand[6] = card6;*/

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
      /*  SocketThread socketThread = new SocketThread(this);
        Thread newT = new Thread(socketThread);
        newT.start();*/
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




        //elif test
        /*ArrayList<String> wonderImages = new ArrayList<String>();
        for(String wonderName : wonderBoards.keySet()){
            wonderImages.add(wonderBoards.get(wonderName).getName());
        }

        String[] images = new String[7];
        Image[] wonderStages0 = new Image[7];
        Image[] wonderStages1 = new Image[7];
        Image[] wonderStages2 = new Image[7];

        String str1,str2,str3;
        for(int j = 0; j<7;j++){
            images[j] = "-fx-background-image: url(\"/WONDERS/"+ wonderImages.get(j) +".jpg\")";
            str1 = "/WONDERS/" + wonders[j] + "_STAGE1.jpg";
            str2 = "/WONDERS/" + wonders[j] + "_STAGE2.jpg";
            str3 = "/WONDERS/" + wonders[j] + "_STAGE3.jpg";
            wonderStages0[j] = new Image(str1);
            wonderStages1[j] = new Image(str2);
            wonderStages2[j] = new Image(str3);
        }






        String str = "\"-fx-background-image: url( \" /WONDERS/" + Main.wonderID + ".jpg";
        my_wonder.setStyle(str);
        for(int k = 0; k<wonderImages.size();k++){

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


/*
        popOverCard = new PopOver();
        popOverCard.setArrowLocation(PopOver.ArrowLocation.BOTTOM_CENTER);
        popOverCard.setAutoFix(true);
        popOverCard.setAutoHide(true);
        popOverCard.setHideOnEscape(true);
        popOverCard.setDetachable(false);
        GridPane pane = FXMLLoader.load(getClass().getResource("/select_card_popover.fxml"));
        popOverCard.setContentNode(pane);

        popOver2 = new PopOver();
        popOver2.setArrowLocation(PopOver.ArrowLocation.BOTTOM_CENTER);
        popOver2.setAutoFix(true);
        popOver2.setAutoHide(true);
        popOver2.setHideOnEscape(true);
        popOver2.setDetachable(false);
        GridPane pane2 = FXMLLoader.load(getClass().getResource("/trade_popover.fxml"));
        popOver2.setContentNode(pane2);
        */

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