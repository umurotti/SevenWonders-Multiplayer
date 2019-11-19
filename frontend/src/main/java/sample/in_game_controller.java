package sample;

import com.sun.org.apache.xml.internal.dtm.ref.sax2dtm.SAX2DTM2;
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
import jdk.nashorn.internal.runtime.ECMAException;
import org.controlsfx.control.PopOver;

import java.io.File;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.*;
import java.util.concurrent.ExecutionException;



public class in_game_controller implements Initializable {

    WonderBoard sampleWonderBoard = new WonderBoard();



    @FXML
    GridPane my_resources_grid;
    @FXML
            GridPane my_structure_grid;
    Map<String,String> myStructuresBuilded = new HashMap<String,String>();
    Map<String,String> leftStructuresBuilded = new HashMap<String,String>();
    Map<String,String> rightStructuresBuilded = new HashMap<String,String>();
    String selectedCard;
    int Hand;
    int myStructureNo = 0;
    Map<String,Image> images2 = new HashMap<String, Image>();
    FileInputStream inputstream[] = new FileInputStream[78] ;
    Image images[] = new Image[78];
    ServerConnection con = new ServerConnection();
    @FXML
    Button discard;
    @FXML
    Button dice;
   // @FXML
    //Label wood;
    @FXML
    Label stone;
    @FXML
    Label clay;
    @FXML
    Label loom;
    @FXML
    Label tablet;
    @FXML
    Label glass;
    @FXML
    Label shield;
    @FXML
    Label coin;
    @FXML
    Label papyrus;
    @FXML
    Label compass;
    @FXML
    Label gear;
    @FXML
    ImageView my_structure_0;
    @FXML
    ImageView my_structure_1;
    @FXML
    ImageView my_structure_2;
    @FXML
    ImageView my_structure_3;
    @FXML
    ImageView my_structure_4;
    @FXML
    ImageView my_structure_5;
    @FXML
    ImageView my_structure_6;
    @FXML
    ImageView my_structure_7;
    @FXML
    ImageView my_structure_8;
    @FXML
    ImageView my_structure_9;
    @FXML
    ImageView my_structure_10;
    @FXML
    ImageView my_structure_11;
    @FXML
    ImageView my_structure_12;
    @FXML
    ImageView my_structure_13;
    @FXML
    ImageView my_structure_14;
    @FXML
    ImageView my_structure_15;
    @FXML
    ImageView my_structure_16;
    @FXML
    ImageView my_structure_17;

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

    /*@FXML
    ImageView left_neihbour_structure_0;
    @FXML
    ImageView my_structure_1;
    @FXML
    ImageView my_structure_2;
    @FXML
    ImageView my_structure_3;
    @FXML
    ImageView my_structure_4;
    @FXML
    ImageView my_structure_5;
    @FXML
    ImageView my_structure_6;
    @FXML
    ImageView my_structure_7;
    @FXML
    ImageView my_structure_8;
    @FXML
    ImageView my_structure_9;
    @FXML
    ImageView my_structure_10;
    @FXML
    ImageView my_structure_11;
    @FXML
    ImageView my_structure_12;
    @FXML
    ImageView my_structure_13;
    @FXML
    ImageView my_structure_14;
    @FXML
    ImageView my_structure_15;
    @FXML
    ImageView my_structure_16;
    @FXML
    ImageView my_structure_17;
    @FXML
    ImageView my_structure_0;
    @FXML
    ImageView my_structure_1;
    @FXML
    ImageView my_structure_2;
    @FXML
    ImageView my_structure_3;
    @FXML
    ImageView my_structure_4;
    @FXML
    ImageView my_structure_5;
    @FXML
    ImageView my_structure_6;
    @FXML
    ImageView my_structure_7;
    @FXML
    ImageView my_structure_8;
    @FXML
    ImageView my_structure_9;
    @FXML
    ImageView my_structure_10;
    @FXML
    ImageView my_structure_11;
    @FXML
    ImageView my_structure_12;
    @FXML
    ImageView my_structure_13;
    @FXML
    ImageView my_structure_14;
    @FXML
    ImageView my_structure_15;
    @FXML
    ImageView my_structure_16;
    @FXML
    ImageView my_structure_17;*/
   // @FXML
   // ImageView card1;

    ImageView hand[] = new ImageView[7];
    ImageView myStructures[] = new ImageView[18];
    ImageView leftStructures[] = new ImageView[18];
    ImageView rightStructures[] = new ImageView[18];
    Map<String,WonderBoard> wonderBoards = new HashMap<String, WonderBoard>();

    public void selectCard(MouseEvent event)throws Exception{
        PopOver popOver = new PopOver();
        popOver.setArrowLocation(PopOver.ArrowLocation.BOTTOM_CENTER);
        //popOver.setContentNode(new Label("Test"));
        popOver.setAutoFix(true);
        popOver.setAutoHide(true);
        popOver.setHideOnEscape(true);
        popOver.setDetachable(false);

        GridPane pane = FXMLLoader.load(getClass().getResource("/select_card_popover.fxml"));
        popOver.setContentNode(pane);
        popOver.show((ImageView)event.getSource());
        selectedCard = ((ImageView) event.getSource()).getId();
    }


    public void refreshBoard(HashMap<String,Integer> sources)throws Exception{
        for (Node child : my_resources_grid.getChildren()) {
            if (child.getId() != null)
            if(child.getId().charAt(0) == 'l'){
                String nameSource = child.getId().substring(5);
                Label temp = (Label)child;
                //temp.setText("adas");
                temp.setText(sources.get(nameSource) + "");
            }
        }
       /* for (Node child : sourcesGrid.getChildren()) {
            if(child.getId().charAt(0) == 'L'){
                child.getId();
                Label temp = (Label)child;
                temp.setText("adas");
                //temp.setText(sources.child.getId().substring(1));
            }
        }
        for (Node child : sourcesGrid.getChildren()) {
            if(child.getId().charAt(0) == 'L'){
                child.getId();
                Label temp = (Label)child;
                temp.setText("adas");
                //temp.setText(sources.child.getId().substring(1));
            }
        }*/
      //  wood.setText(sources.getColor());


    }


    public void onPress(ActionEvent event)throws Exception{

        System.out.println("asdasda");
        refresh();
    }

    public void refresh()throws Exception {
        startPoint();
        Hand = 6;
        HashMap<String,WonderBoard> wonders= (HashMap<String, WonderBoard>) con.ConvertJson("1");
        WonderBoard a = wonders.get("wonder1");
        ArrayList tempList = con.ConvertJsonHand();
        refreshBoard(a.getSources());
        refreshStructures(a);
        refreshHand(tempList);
    }
    public void refreshHand(ArrayList<Card> handCards){
        for(int i = 0; i < 7; i++){
            hand[i].setVisible(true);
            if(handCards.get(i).getName()!=null){
                hand[i].setImage(images2.get(handCards.get(i).getName()));
            }
        }
        for(int i = 6; i >= Hand; i--){
            hand[i].setVisible(false);
        }
    }

    public void choice(ActionEvent event) throws Exception{
     Button temp = (Button)event.getSource();
     if(temp.getId().equals("discard"))
         con.sendRequestChoice("discard", selectedCard);
     else if(temp.getId().equals("build_card"))
         con.sendRequestChoice("build_card", selectedCard);
     else if(temp.getId().equals("build_wonder"))
         con.sendRequestChoice("build_wonder", selectedCard);
 }

    public void refreshStructures(WonderBoard wonder){
        Iterator cardIterator =  wonder.getBuiltCards().entrySet().iterator();
        System.out.println("HashMap after adding bonus marks:");
        while (cardIterator.hasNext()) {
            Map.Entry mapElement = (Map.Entry)cardIterator.next();
            Card marks = ((Card)mapElement.getValue());
            System.out.println("r");
            System.out.println(marks.getName());

            if(!myStructuresBuilded.containsKey(marks.getName())){
                myStructures[myStructureNo].setImage(images2.get(marks.getName()));
                myStructureNo++;
            }
        }

    }



    public void startPoint() throws Exception {
        int i = 0;
        File dir = new File("D:\\UI\\src\\main\\resources\\cards\\");
        File[] directoryListing = dir.listFiles();
        if (directoryListing != null) {
            for (File child : directoryListing) {
                InputStream temp = new FileInputStream(child);
                images2.put(child.getName().substring(0,child.getName().length()-4), new Image(temp));
                i++;
            }
        } else {
        }


        myStructures[0] = my_structure_0;
        myStructures[1] = my_structure_1;
        myStructures[2] = my_structure_2;
        myStructures[3] = my_structure_3;
        myStructures[4] = my_structure_4;
        myStructures[5] = my_structure_5;
        myStructures[6] = my_structure_6;
        myStructures[7] = my_structure_7;
        myStructures[8] = my_structure_8;
        myStructures[9] = my_structure_9;
        myStructures[10] = my_structure_10;
        myStructures[11] = my_structure_11;
        myStructures[12] = my_structure_12;
        myStructures[13] = my_structure_13;
        myStructures[14] = my_structure_14;
        myStructures[15] = my_structure_15;
        myStructures[16] = my_structure_16;
        myStructures[17] = my_structure_17;
        hand[0] = card1;
        hand[1] = card2;
        hand[2] = card3;
        hand[3] = card4;
        hand[4] = card5;
        hand[5] = card6;
        hand[6] = card7;



        //initializing wonderboard
    /*   sampleWonderBoard.setName("alptekinin felaketi");
        HashMap<String, Card> temp = new HashMap<String, Card>();
        temp.put("Baracks", new Card("blue",new Cost(),"barraks"));
        temp.put("Altar", new Card("blue",new Cost(),"altar"));
        temp.put("Apothecary", new Card("blue",new Cost(),"apothecary"));
        temp.put("baths", new Card("blue",new Cost(),"baths"));
        sampleWonderBoard.setBuiltCards(temp);
        HashMap<String,Integer>sourcesTemp = new HashMap<String,Integer>();
        sourcesTemp.put("wood",5);
        sourcesTemp.put("stone",5);
        sourcesTemp.put("loom",5);
        sourcesTemp.put("shield",5);
        sourcesTemp.put("coin",5);
        sourcesTemp.put("gear",4);
        sourcesTemp.put("clay",5);
        sourcesTemp.put("ore",5);
        sourcesTemp.put("glass",5);
        sourcesTemp.put("compass",5);
        sourcesTemp.put("papyrus",5);
        sampleWonderBoard.sources = sourcesTemp;*/
    }

    public void initialize(URL location, ResourceBundle resources)  {

    }
}
