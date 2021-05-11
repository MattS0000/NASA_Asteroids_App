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
import pl.edu.pw.mini.zpoif.project.Asteroid.AsteroidByDates;
import pl.edu.pw.mini.zpoif.project.Asteroid.CloseApproachDatum;
import pl.edu.pw.mini.zpoif.project.FastData;
import pl.edu.pw.mini.zpoif.project.GETApi;
import pl.edu.pw.mini.zpoif.project.Main;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;

public class MenuController implements Initializable {

    private LocalDate end = Main.end;
    private LocalDate start = Main.start;
    @FXML
    private Button ToSort;
    @FXML
    private DatePicker datePickerStart;
    @FXML
    private DatePicker datePickerEnd;
    @FXML
    private Accordion accordion = new Accordion();

    private AsteroidByDates asteroidByDates;

    @FXML
    private TableColumn<FastData, String> ShowData;

    @FXML
    private TableColumn<FastData, String> ShowOrbit;

    @FXML
    private TableView<FastData> approachesTable;

    @FXML
    void ChangeToSort(ActionEvent event) throws IOException {
        for (String i : asteroidByDates.getMapOfAsteroidsKeySet()) {
            for (Asteroid j : asteroidByDates.getMapOfAsteroids().get(i)) {
                if (!Main.ChosenAsteroids.contains(j)) {
                    Main.ChosenAsteroids.add(j);
                }
            }
        }
        Stage stage = (Stage) ToSort.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("SortsAndFilters.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        Node split = scene.lookup(".split-pane");
        split.lookup(".split-pane-divider").setMouseTransparent(true);
    }

    @FXML
    void ShowDataClicked(ActionEvent event) {
        updateShowData();
    }

    void updateShowData() {
        String Name = "";
        ListView<FastData> Lista = accordion.getExpandedPane()==null?new ListView<>():(accordion.getExpandedPane().getContent()==null?new ListView<>():(ListView<FastData>) accordion.getExpandedPane().getContent());
        ObservableList<FastData> Asteroid = Lista.getSelectionModel().getSelectedItems();
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

        for (String i : asteroidByDates.getMapOfAsteroidsKeySet()) {
            for (Asteroid j : asteroidByDates.getMapOfAsteroids().get(i)) {
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
        }
        ShowOrbit.getColumns().setAll(thirdCol, fourthCol);
        ShowData.getColumns().setAll(firstCol, secondCol);
    }

    @FXML
    public void selectStartDate(ActionEvent event) {
        start = datePickerStart.getValue();
        Main.start = datePickerStart.getValue();
    }

    @FXML
    public void selectEndDate(ActionEvent event) {
        end = datePickerEnd.getValue();
        Main.end = datePickerEnd.getValue();
    }

    @FXML
    void AcceptClicked(ActionEvent event) {
        updateDate();
        updateTree();
    }

    void updateTree() {
        accordion.getPanes().setAll();
        for (String i : asteroidByDates.getMapOfAsteroidsKeySet()) {
            ListView<String> Ltmp = new ListView<>();
            ArrayList<Asteroid> ListAsteroid = asteroidByDates.getMapOfAsteroids().get(i);
            ArrayList<String> ListAsteroidName = new ArrayList<>();
            for (Asteroid j : ListAsteroid) {
                ListAsteroidName.add(j.getName());

            }
            Ltmp.getItems().addAll(ListAsteroidName);
            TitledPane pane = new TitledPane(i, Ltmp);
            accordion.getPanes().add(pane);
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        Comparator<TitledPane> DatesComperator = Comparator.comparing(d -> LocalDate.parse(d.getText(), formatter));
        accordion.getPanes().sort(DatesComperator);
    }

    void updateDate() {
        try {
            asteroidByDates = GETApi.getAsteroidByDates(start.toString(), end.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Main.ChosenAsteroids.clear();
        updateDate();
        updateTree();
        TableColumn<FastData, String> col=new TableColumn<>("All approaches date");
        approachesTable.getColumns().setAll(col);
        ShowData.setText("Asteroid");
        ShowOrbit.setText("Orbit Data");
    }


}
