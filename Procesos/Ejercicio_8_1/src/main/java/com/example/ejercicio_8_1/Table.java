package com.example.ejercicio_8_1;

import com.example.ejercicio_8_1.Model.Persona;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class Table implements Initializable {

    @FXML
    public TableView<Persona> tvData;
    @FXML public TableColumn<Persona, Integer> idColumn;
    @FXML public TableColumn<Persona, String> nameColumn;
    @FXML public TableColumn<Persona, Integer> ageColumn;
    private Persona data;

    private ObservableList<Persona> getPeople() {
        File file = new File("person.bin");

        ObservableList<Persona> people = null;

        if (file.exists()) {
            //Get data form the bin
            data = (Persona) ObjReader.read(file);
            System.out.println(data.toString());

            people = FXCollections.observableArrayList(data.getData());
        }
        return people;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Add data to colum
        idColumn.setCellValueFactory(new PropertyValueFactory<>("idColumn"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("nameColumn"));
        ageColumn.setCellValueFactory(new PropertyValueFactory<>("ageColumn"));

        tvData.setItems(getPeople());
    }
}
