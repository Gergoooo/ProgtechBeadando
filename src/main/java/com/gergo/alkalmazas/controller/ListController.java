package com.gergo.alkalmazas.controller;

import com.gergo.alkalmazas.model.Filter;
import com.gergo.alkalmazas.model.Kamera;
import com.gergo.alkalmazas.model.KameraHaz;
import com.gergo.alkalmazas.model.Optika;
import com.gergo.alkalmazas.model.enums.Publisher;
import com.gergo.alkalmazas.repository.FilterRepository;
import com.gergo.alkalmazas.repository.KameraHazRepository;
import com.gergo.alkalmazas.repository.KameraRepository;
import com.gergo.alkalmazas.repository.OptikaRepository;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class ListController implements Initializable {

    private static final Logger LOGGER = Logger.getLogger(ListController.class.getName());

    @FXML
    private ListView<Optika> optikaListView = new ListView<>();
    @FXML
    private ListView<KameraHaz> kameraHazListView = new ListView<>();
    @FXML
    private ListView<Filter> filterListView = new ListView<>();
    @FXML
    private ChoiceBox<String> filterChoiceBox = new ChoiceBox<>();
    @FXML
    private ChoiceBox<Publisher> optikaChoiceBox = new ChoiceBox<>();
    @FXML
    private ChoiceBox<Publisher> kameraHazChoiceBox = new ChoiceBox<>();
    @FXML
    private Label combinedLabel = new Label("");
    @FXML
    private Label priceLabel = new Label("");
    @FXML
    private Button saveBtn = new Button();

    private List<KameraHaz> kameraHazList = new ArrayList<>();
    private List<Optika> optikaList = new ArrayList<>();
    private List<Filter> filterList = new ArrayList<>();
    private List<String> filterTypes = new ArrayList<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LOGGER.log(Level.INFO, "Initializing ListController...");
        // Fill list views
        fillKameraHazList();
        fillOptikaList();
        fillFilterList();

        // Set up optikaChoiceBox
        optikaChoiceBox.setItems(FXCollections.observableArrayList(Publisher.values()));

        // Set up kameraHazChoiceBox
        List<Publisher> kameraHazPublishers = Arrays.asList(Publisher.values()).subList(0, 3);
        kameraHazChoiceBox.setItems(FXCollections.observableArrayList(kameraHazPublishers));

        //Set up filterChoiceBox
        filterChoiceBox.setItems(FXCollections.observableArrayList(filterTypes));

        //Listener for optikaChoiceBox
        optikaChoiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            updateOptikaListView();
        });

        //Listener for kameraHazChoiceBox
        kameraHazChoiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            updateOptikaListView();
        });
        kameraHazChoiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            updateKameraHazListView(newValue);
        });

        //Listener for filterChoiceBox
        filterChoiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) ->{
            updateFilterListView();
        });

        //Listeners for combinedLabel and priceLabel update
        kameraHazListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) ->{
                updateCombinedLabel();
                updatePriceLabel();
        });
        optikaListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) ->{
                updateCombinedLabel();
                updatePriceLabel();
        });
        filterListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) ->{
                updateCombinedLabel();
                updatePriceLabel();
        });
    }

    private void fillKameraHazList() {
        LOGGER.log(Level.INFO, "Filling KameraHaz list...");

        kameraHazList = KameraHazRepository.KamerahazList();
    }
    private void fillOptikaList() {
        LOGGER.log(Level.INFO, "Filling Optika list...");

        optikaList = OptikaRepository.optikaList();
    }
    private void fillFilterList() {
        LOGGER.log(Level.INFO, "Filling filter list...");
        filterList = FilterRepository.filterList();
        if(!(filterList == null)) {
            for (Filter filter : filterList) {
                filterTypes.add(filter.getType());
            }
        }
    }

    private void updateKameraHazListView(Publisher publisher) {
        LOGGER.log(Level.INFO, "Updating kameraHaz list...");

        kameraHazListView.getItems().clear();

        for (KameraHaz object : kameraHazList) {
            if (object.getPublisher() == publisher) {
                kameraHazListView.getItems().add(object);
            }
        }
    }
    private void updateOptikaListView() {
        LOGGER.log(Level.INFO, "Updating Optika list...");

        optikaListView.getItems().clear();

        Publisher selectedPublisher = optikaChoiceBox.getSelectionModel().getSelectedItem();
        Publisher selectedKameraHazPublisher = kameraHazChoiceBox.getSelectionModel().getSelectedItem();

        List<Optika> filteredOptikaList = new ArrayList<>();
        for (Optika optika : optikaList) {
            String optikaPublisher = optika.getPublisher();
            String optikaCompatibility = optika.getCompatibility().toString();
            if(selectedKameraHazPublisher == null){
                if (optikaPublisher.equals(selectedPublisher.toString())){
                    filteredOptikaList.add(optika);
                }
            } else if (selectedPublisher == null) {
                if (optikaCompatibility.equals(selectedKameraHazPublisher.toString())){
                    filteredOptikaList.add(optika);
                }
            } else if (optikaPublisher.equals(selectedPublisher.toString()) && optikaCompatibility.equals(selectedKameraHazPublisher.toString())) {
                filteredOptikaList.add(optika);
            }
        }
        for (Optika optika : filteredOptikaList) {
            optikaListView.getItems().add(optika);
        }
    }
    private void updateFilterListView(){
        LOGGER.log(Level.INFO, "Updating Filter list...");

        filterListView.getItems().clear();
        String type = filterChoiceBox.getSelectionModel().getSelectedItem();

        for (Filter object : filterList) {
            if (object.getType().equals(type)) {
                filterListView.getItems().add(object);
            }
        }
    }
    private void updatePriceLabel() {
        LOGGER.log(Level.INFO, "Updating priceLabel...");

        int price = 0;

        if (kameraHazListView.getSelectionModel().getSelectedItem() != null) {
            price += kameraHazListView.getSelectionModel().getSelectedItem().getPrice();
        }
        if (optikaListView.getSelectionModel().getSelectedItem() != null) {
            price += optikaListView.getSelectionModel().getSelectedItem().getPrice();
        }
        if (filterListView.getSelectionModel().getSelectedItem() != null) {
            price += filterListView.getSelectionModel().getSelectedItem().getPrice();
        }

        priceLabel.setText(price + "$");
    }
    private void updateCombinedLabel() {
        LOGGER.log(Level.INFO, "Updating combinedLabel...");

        String selected1 = "";
        String selected2 = "";
        String selected3 = "";

        if (kameraHazListView.getSelectionModel().getSelectedItem() != null) {
            selected1 = kameraHazListView.getSelectionModel().getSelectedItem().toString();
        }
        if (optikaListView.getSelectionModel().getSelectedItem() != null) {
            selected2 = optikaListView.getSelectionModel().getSelectedItem().toString();
        }
        if (filterListView.getSelectionModel().getSelectedItem() != null) {
            selected3 = filterListView.getSelectionModel().getSelectedItem().toString();
        }

        combinedLabel.setText("Selected Items: " + selected1 + " | " + selected2 + " | " + selected3);
    }

    @FXML
    public void saveSelection() {
        LOGGER.log(Level.INFO, "Saving selection...");
        if (kameraHazListView.getSelectionModel().selectedItemProperty().isNull().get() ||
            optikaListView.getSelectionModel().selectedItemProperty().isNull().get() ||
            kameraHazListView.getSelectionModel().selectedItemProperty().isNull().get()) {
            openPopup("Please select something from all columns");
        }else {
            Kamera newSave = new Kamera();

            newSave.setHazId(kameraHazListView.getSelectionModel().getSelectedItem().getId());
            newSave.setOptikaId(optikaListView.getSelectionModel().getSelectedItem().getId());
            newSave.setFilterId(filterListView.getSelectionModel().getSelectedItem().getId());

            KameraRepository save = new KameraRepository();
            save.addKamera(newSave);
            LOGGER.log(Level.INFO, "Selection saved...");
        }
    }

    private void openPopup(String message){
        LOGGER.log(Level.INFO, "Opening popup...");
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Popup.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            PopupController popupController = fxmlLoader.getController();
            popupController.setPopupLabel(message);
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
        } catch(Exception e) {
            LOGGER.log(Level.ERROR, "Could'nt load popup...");
            e.printStackTrace();
        }
    }
}