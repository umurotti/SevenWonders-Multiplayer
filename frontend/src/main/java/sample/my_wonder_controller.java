package sample;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;

import java.util.HashMap;

public class my_wonder_controller {
    @FXML
    Parent root;
    @FXML
    Label wood ;
    my_wonder_controller(Label a){
        wood = a;
    }
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



    public void refreshBoard(HashMap<String,String> sources)throws Exception{
        wood = new Label("aasdda");
        wood.setText("sadadas");
        stone.setText(sources.get("stone"));
        glass.setText(sources.get("glass"));
        loom.setText(sources.get("loom"));
        clay.setText(sources.get("clay"));
        papyrus.setText(sources.get("papyrus"));
        coin.setText(sources.get("coin"));
        shield.setText(sources.get("shield"));
        compass.setText(sources.get("compass"));
        gear.setText(sources.get("gear"));
        tablet.setText(sources.get("tablet"));
    }

    public Label getLabel(String labelName){
        return wood;
    }

}
