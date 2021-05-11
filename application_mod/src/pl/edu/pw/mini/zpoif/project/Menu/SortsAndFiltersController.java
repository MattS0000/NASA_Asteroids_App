package pl.edu.pw.mini.zpoif.project.Menu;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import pl.edu.pw.mini.zpoif.project.Asteroid.Asteroid;
import pl.edu.pw.mini.zpoif.project.Asteroid.CloseApproachDatum;
import pl.edu.pw.mini.zpoif.project.FastData;
import pl.edu.pw.mini.zpoif.project.GETApi;
import pl.edu.pw.mini.zpoif.project.Main;
import pl.edu.pw.mini.zpoif.project.SortAsteroids;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class SortsAndFiltersController implements Initializable {
    private ArrayList<Asteroid> ChosenList;
    private ArrayList<String> ChosenName;

    @FXML
    private RadioButton SortVelRadio;

    @FXML
    private RadioButton SortMDRadio;

    @FXML
    private RadioButton AscendingRadio;

    @FXML
    private RadioButton DescendingRadio;

    @FXML
    private RadioButton SortDiameterRadio;

    @FXML
    private ToggleButton SentryButton;

    @FXML
    private ToggleButton HazardousButton;

    @FXML
    private ToggleButton BothButton;


    @FXML
    private ListView<String> SortList;

    @FXML
    private TableColumn<FastData, String> ShowData;

    @FXML
    private TableColumn<FastData, String> ShowOrbit;

    @FXML
    private TableView<FastData> approachesTable;

    @FXML
    void ShowDataClicked(ActionEvent event) {
        updateShowData();
    }

    void updateShowData(){
        String Name = "";
        ObservableList<String> Asteroid = SortList.getSelectionModel().getSelectedItems();
        for (Object item : Asteroid) {
            Name = (String) item;
        }
        TableColumn<FastData, String> firstCol = new TableColumn<>("Field Type");
        firstCol.setMaxWidth(150);
        firstCol.setMinWidth(150);
        firstCol.setCellValueFactory(fastData -> new ReadOnlyStringWrapper(fastData.getValue().getField()));
        TableColumn<FastData, String> secondCol = new TableColumn<>("Value");
        secondCol.setCellValueFactory(fastData -> new ReadOnlyStringWrapper(fastData.getValue().getValue()));
        secondCol.setEditable(false);
        secondCol.setSortable(false);


        TableColumn<FastData, String> thirdCol = new TableColumn<>("Orbit Attribute");
        thirdCol.setCellValueFactory(fastData -> new ReadOnlyStringWrapper(fastData.getValue().getField()));
        thirdCol.setMaxWidth(180);
        thirdCol.setMinWidth(180);

        TableColumn<FastData, String> fourthCol = new TableColumn<>("Value");
        fourthCol.setCellValueFactory(fastData -> new ReadOnlyStringWrapper(fastData.getValue().getValue()));
        fourthCol.setEditable(false);
        fourthCol.setSortable(false);

        TableColumn<FastData, String> fifthCol=new TableColumn<>("All approaches date");
        fifthCol.setCellValueFactory(fastData -> new ReadOnlyStringWrapper(fastData.getValue().getValue()));
        approachesTable.getColumns().setAll(fifthCol);

        for (Asteroid j : ChosenList) {
            if (j.getName() == Name) {
                Asteroid ShownAsteroid = null;
                try {
                    ShownAsteroid = GETApi.getAsteroidById(j.getId());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                ShowData.setText("Asteroid " + Name);

                TablesWrapper wrapper = new TablesWrapper(j, ShownAsteroid);

                ObservableList<FastData> dataA = wrapper.getDataA();
                ObservableList<FastData> dataB = wrapper.getDataB();
                ObservableList<FastData> dataC = wrapper.getDataC();
                ShowData.getTableView().setItems(dataA);
                ShowOrbit.getTableView().setItems(dataB);
                approachesTable.setItems(dataC);

            }
        }
        ShowOrbit.getColumns().setAll(thirdCol, fourthCol);
        ShowData.getColumns().setAll(firstCol, secondCol);
    }

    @FXML
    void ChangeToData(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Menu.fxml"));
        Scene scene=new Scene(root);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
        Node split = scene.lookup(".split-pane");
        split.lookup(".split-pane-divider").setMouseTransparent(true);
    }

    public ArrayList<Asteroid> copy(ArrayList<Asteroid> entry){
        ArrayList<Asteroid> copy = new ArrayList<>();
        copy.addAll(entry);
        return copy;
    }

    @FXML
    void AscDes(ActionEvent event) {
        ArrayList<Asteroid> working=copy(ChosenList);
        if(BothButton.isSelected()){SortAsteroids.filterByBothDangers(working);}
        else if(SentryButton.isSelected()){SortAsteroids.filterBySentry(working); }
        else if(HazardousButton.isSelected()){SortAsteroids.filterByHazardous(working);}

        if (SortVelRadio.isSelected()){
            if(AscendingRadio.isSelected()){SortAsteroids.sortByVelocityAscending(working);}
            else {SortAsteroids.sortByVelocityDescending(working);}
        }else if(SortMDRadio.isSelected()){
            if(AscendingRadio.isSelected()){SortAsteroids.sortByMissDistanceAscending(working);}
            else {SortAsteroids.sortByMissDistanceDescending(working);}
        }else if(SortDiameterRadio.isSelected()){
            if(AscendingRadio.isSelected()){SortAsteroids.sortByDiameterAscending(working);}
            else {SortAsteroids.sortByDiameterDescending(working);}
        }
        ArrayList<String> workingName=new ArrayList<>();
        for (Asteroid i:working){
            workingName.add(i.getName());
        }
        SortList.getItems().setAll(workingName);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TableColumn<FastData, String> col=new TableColumn<>("All approaches date");
        approachesTable.getColumns().setAll(col);
        ToggleGroup group = new ToggleGroup();
        ToggleGroup group2 = new ToggleGroup();
        ToggleGroup group3 = new ToggleGroup();
        SortDiameterRadio.setToggleGroup(group);
        SortMDRadio.setToggleGroup(group);
        SortVelRadio.setToggleGroup(group);
        AscendingRadio.setToggleGroup(group2);
        DescendingRadio.setToggleGroup(group2);
        HazardousButton.setToggleGroup(group3);
        SentryButton.setToggleGroup(group3);
        BothButton.setToggleGroup(group3);
        SortAsteroids.sortByVelocityAscending(Main.ChosenAsteroids);
        ChosenList= new ArrayList<>();
        ChosenList=Main.ChosenAsteroids;
        ChosenName=new ArrayList<>();
        for (Asteroid i:ChosenList){
            ChosenName.add(i.getName());
        }
        SortList.getItems().addAll(ChosenName);
    }
}
