package ec.edu.espol.turnerocovid19g4;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import ec.edu.espol.turnerocovid19g4.datos.Data;
import ec.edu.espol.turnerocovid19g4.modelo.Cita;
import ec.edu.espol.turnerocovid19g4.modelo.Puesto;
import ec.edu.espol.turnerocovid19g4.modelo.Diagnostico;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.FlowPane;
import com.jfoenix.controls.JFXTreeView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.cells.editors.TextFieldEditorBuilder;
import com.jfoenix.controls.cells.editors.base.GenericEditableTreeTableCell;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import ec.edu.espol.turnerocovid19g4.modelo.Medicamento;
import ec.edu.espol.turnerocovid19g4.modelo.MedicamentoAsignado;
import ec.edu.espol.turnerocovid19g4.modelo.Receta;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableColumn.CellEditEvent;

/**
 * FXML Controller class
 *
 * @author mbpretina
 */
public class PatientsManagementController implements Initializable {

    @FXML
    private Label lblSintoma;
    
    @FXML
    private Label lblPatientsName;
    
    @FXML
    private Label lblInfoPuesto;
    
    @FXML
    private Label lblPuestosTotal;
    
    @FXML
    private ScrollPane paneGridPuestos;
    
    @FXML
    private JFXTreeTableView tvDiag;
    
    @FXML
    private JFXTreeTableView tvReceta;
    
    @FXML
    private JFXButton btTerminarConsulta;
    
    private ObservableMap<Puesto,PuestoBotonController> mapaPuesto;
    
    private Puesto currentPuesto;
    private FlowPane flowpane;
    private ObservableList<RecetaElemento> receta;
    private ObservableList<DiagnosticoElemento> diagnostico;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.submit(fetchPuestos);
        
        fetchPuestos.setOnSucceeded((var event) -> {
            
            Data.getInstance().setMapaPuesto(FXCollections.observableMap(fetchPuestos.getValue()));
            mapaPuesto = Data.getInstance().getMapaPuesto();
            
            
            int n = mapaPuesto.size();
            
            lblPuestosTotal.setText("Puestos (" + n + ")");
            
            flowpane = new FlowPane();
            flowpane.setMaxWidth(286);
            flowpane.setHgap(5);
            flowpane.setVgap(7);
            flowpane.setStyle("-fx-background-color: #474C5F");
                for (Map.Entry<Puesto,PuestoBotonController> entry : mapaPuesto.entrySet()){
                    //load specific item
                    Puesto puesto = entry.getKey();
                    
                    FXMLLoader loader = new FXMLLoader(App.class.getResource("puestoBoton.fxml"));
                    PuestoBotonController controller = entry.getValue();
                    loader.setController(controller);
                    
                    try {
                        Node object = loader.load();
                        controller.setNode(object);
                        flowpane.getChildren().add(object);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    controller.setPuesto(puesto);
                    controller.getLbPuestoID().setOnAction(e -> {
                            if (currentPuesto == null)
                                if (controller.getPuesto().getCita() != null)
                                    controller.setColor(0);
                            
                            Cita cita = controller.getPuesto().getCita();
                            if (cita != null){ 
                                controller.setColor(0);
                                if (currentPuesto != null) mapaPuesto.get(currentPuesto).setColor(1);
                                currentPuesto = puesto;
                                lblSintoma.setText("Sintoma: "+ cita.getSintoma().getNombre());
                                lblPatientsName.setText(cita.getPaciente().getNombre()+" "+cita.getPaciente().getApellido());
                                lblInfoPuesto.setText("Puesto ("+currentPuesto.getCodPuesto()+") | Doctor  ("+currentPuesto.getMedicoEncargado().getNombre()+" "+currentPuesto.getMedicoEncargado().getApellido()+")");
                                
                            }
                            
                            
                        });
                }
                mapaPuesto.addListener((MapChangeListener<Puesto, PuestoBotonController>) CHANGE -> {
                        if (CHANGE.wasAdded()) {
                            PuestoBotonController controller = CHANGE.getValueAdded();
                            FXMLLoader loader = new FXMLLoader(App.class.getResource("puestoBoton.fxml"));
                            Puesto puesto = controller.getPuesto();
                            loader.setController(controller);
                            
                            try {
                                Node object = loader.load();
                                controller.setNode(object);
                                flowpane.getChildren().add(object);
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                            lblPuestosTotal.setText("Puestos (" + mapaPuesto.size() + ")");
                            controller.setPuesto(puesto);
                            controller.getLbPuestoID().setOnAction(e -> {
                                
                            if (currentPuesto == null)
                                if (controller.getPuesto().getCita() != null)
                                    controller.setColor(0);
                            Cita cita = controller.getPuesto().getCita();
                            if (cita != null){ 
                                controller.setColor(0);
                                if (currentPuesto != null) mapaPuesto.get(currentPuesto).setColor(1);
                                currentPuesto = puesto;
                                    lblSintoma.setText("Sintoma: "+ cita.getSintoma().getNombre());
                                    lblPatientsName.setText(cita.getPaciente().getNombre()+" "+cita.getPaciente().getApellido());
                                    lblInfoPuesto.setText("Puesto ("+currentPuesto.getCodPuesto()+") | Doctor  ("+currentPuesto.getMedicoEncargado().getNombre()+" "+currentPuesto.getMedicoEncargado().getApellido()+")");
                                   
                                }            
                        });
                    } else if(CHANGE.wasRemoved()) {
                        flowpane.getChildren().remove(CHANGE.getValueRemoved().getNode());
                        lblPuestosTotal.setText("Puestos (" + mapaPuesto.size() + ")");
                        if (CHANGE.getValueRemoved().equals(currentPuesto)){
                            currentPuesto = null;
                            inicializarTableDiagnostico();
                            inicializarTableReceta();
                            lblInfoPuesto.setText("Esperando seleccion de un puesto");
                            lblPatientsName.setText("No hay puesto seleccionado");
                            lblSintoma.setText("Sintoma:"+" Esperando seleccion de un puesto");
                            
                        }
                        }
                });
                paneGridPuestos.setContent(flowpane); 
                btTerminarConsulta.setOnAction(e ->{
                    Cita cita = currentPuesto.getCita();
                    if(cita != null){
                        for (DiagnosticoElemento diagnostico : diagnostico){    
                        cita.getDiagnostico().add(new Diagnostico(diagnostico.codigo.getValue(),diagnostico.nombre.getValue()));
                    
                        Receta recetita = new Receta();
                        for (RecetaElemento medicamento : receta){
                            MedicamentoAsignado medAsig = new MedicamentoAsignado(new Medicamento(medicamento.ctd.getValue(),medicamento.nombreGenerico.getValue()),medicamento.dosis.getValue());
                            recetita.getMedicamentoAsignado().add(medAsig);
                        }
                        cita.setReceta(recetita);
                        }
                        
                        currentPuesto.setCita(null);
                        mapaPuesto.get(currentPuesto).setPuesto(currentPuesto);
                        mapaPuesto.get(currentPuesto).setColor(2);
                        Data.getInstance().getPuestosAtendiendo().add(currentPuesto);
                        
                        inicializarTableDiagnostico();
                        inicializarTableReceta();
                        lblInfoPuesto.setText("Esperando seleccion de un puesto");
                        lblPatientsName.setText("No hay puesto seleccionado");
                        lblSintoma.setText("Sintoma:"+" Esperando seleccion de un puesto");
                        
                    }
                    
                });

                inicializarTableDiagnostico();
                inicializarTableReceta();
                
        }
        
        );
        
        
    }
    private void inicializarTableReceta(){
        JFXTreeTableColumn<RecetaElemento, String> recetCtd = new JFXTreeTableColumn<>("ctd");
                recetCtd.setPrefWidth(50);
                recetCtd.setCellValueFactory((TreeTableColumn.CellDataFeatures<RecetaElemento, String> param) -> {
                    if (recetCtd.validateValue(param)) {
                        return param.getValue().getValue().ctd;
                    } else {
                        return recetCtd.getComputedValue(param);
                    }
                });

                JFXTreeTableColumn<RecetaElemento, String> recetNombre = new JFXTreeTableColumn<>("Diagnostico");
                recetNombre.setPrefWidth(190);
                recetNombre.setCellValueFactory((TreeTableColumn.CellDataFeatures<RecetaElemento, String> param) -> {
                    if (recetNombre.validateValue(param)) {
                        return param.getValue().getValue().nombreGenerico;
                    } else {
                        return recetNombre.getComputedValue(param);
                    }
                });
                JFXTreeTableColumn<RecetaElemento, String> recetDosis = new JFXTreeTableColumn<>("Dosis");
                                recetDosis.setPrefWidth(190);
                                recetDosis.setCellValueFactory((TreeTableColumn.CellDataFeatures<RecetaElemento, String> param) -> {
                                    if (recetDosis.validateValue(param)) {
                                        return param.getValue().getValue().nombreGenerico;
                                    } else {
                                        return recetDosis.getComputedValue(param);
                                    }
                                });

                recetCtd.setCellFactory((TreeTableColumn<RecetaElemento, String> param) -> new GenericEditableTreeTableCell<>(
                    new TextFieldEditorBuilder()));
                recetCtd.setOnEditCommit((CellEditEvent<RecetaElemento, String> t) -> t.getTreeTableView()
                                                                              .getTreeItem(t.getTreeTablePosition()
                                                                                            .getRow())
                                                                              .getValue().ctd.set(t.getNewValue()));
                recetNombre.setCellFactory((TreeTableColumn<RecetaElemento, String> param) -> new GenericEditableTreeTableCell<>(
                    new TextFieldEditorBuilder()));
                recetNombre.setOnEditCommit((CellEditEvent<RecetaElemento, String> t) -> t.getTreeTableView()
                                                                              .getTreeItem(t.getTreeTablePosition()
                                                                                            .getRow())
                                                                              .getValue().nombreGenerico.set(t.getNewValue()));
                recetDosis.setCellFactory((TreeTableColumn<RecetaElemento, String> param) -> new GenericEditableTreeTableCell<>(
                    new TextFieldEditorBuilder()));
                recetDosis.setOnEditCommit((CellEditEvent<RecetaElemento, String> t) -> t.getTreeTableView()
                                                                              .getTreeItem(t.getTreeTablePosition()
                                                                                            .getRow())
                                                                              .getValue().dosis.set(t.getNewValue()));

                
                receta = FXCollections.observableArrayList();
                receta.add(new RecetaElemento("{}","{Doble click para modificar}", " "));
                final TreeItem<RecetaElemento> rooti = new RecursiveTreeItem<>(receta, RecursiveTreeObject::getChildren);
                ContextMenu contextMenur = new ContextMenu();
                MenuItem menuItem1r = new MenuItem("Añadir Medicamento");
                MenuItem menuItem2r = new MenuItem("Eliminar Medicamento");
                menuItem1r.setOnAction(e ->{
                    receta.add(new RecetaElemento("{}","{Doble click para modificar}", " "));
                });
                menuItem2r.setOnAction(e ->{
                    TreeItem<RecetaElemento> selectedItem = (TreeItem<RecetaElemento>) tvReceta.getSelectionModel().getSelectedItem();
                    if (selectedItem == tvReceta.getRoot()) { 
                        tvReceta.setRoot(null);
                    } else { 
                        selectedItem.getParent().getChildren().remove(selectedItem);
                    }
                });
                
                contextMenur.getItems().addAll(menuItem1r,menuItem2r);
                tvReceta.setRoot(rooti);
                tvReceta.setShowRoot(false);
                tvReceta.setEditable(true);
                tvReceta.getColumns().setAll(recetCtd,recetNombre,recetDosis);
                tvReceta.setContextMenu(contextMenur);
                tvReceta.setMinHeight(200);
    }
    private void inicializarTableDiagnostico(){
        JFXTreeTableColumn<DiagnosticoElemento, String> diagColumn = new JFXTreeTableColumn<>("CIE-10");
                diagColumn.setPrefWidth(50);
                diagColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<DiagnosticoElemento, String> param) -> {
                    if (diagColumn.validateValue(param)) {
                        return param.getValue().getValue().codigo;
                    } else {
                        return diagColumn.getComputedValue(param);
                    }
                });

                JFXTreeTableColumn<DiagnosticoElemento, String> diagNColumn = new JFXTreeTableColumn<>("Diagnostico");
                diagNColumn.setPrefWidth(390);
                diagNColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<DiagnosticoElemento, String> param) -> {
                    if (diagNColumn.validateValue(param)) {
                        return param.getValue().getValue().nombre;
                    } else {
                        return diagNColumn.getComputedValue(param);
                    }
                });


                diagNColumn.setCellFactory((TreeTableColumn<DiagnosticoElemento, String> param) -> new GenericEditableTreeTableCell<>(
                    new TextFieldEditorBuilder()));
                diagNColumn.setOnEditCommit((CellEditEvent<DiagnosticoElemento, String> t) -> t.getTreeTableView()
                                                                              .getTreeItem(t.getTreeTablePosition()
                                                                                            .getRow())
                                                                              .getValue().nombre.set(t.getNewValue()));

                diagColumn.setCellFactory((TreeTableColumn<DiagnosticoElemento, String> param) -> new GenericEditableTreeTableCell<>(
                    new TextFieldEditorBuilder()));
                diagColumn.setOnEditCommit((CellEditEvent<DiagnosticoElemento, String> t) -> t.getTreeTableView()
                                                                              .getTreeItem(t.getTreeTablePosition()
                                                                                            .getRow())
                                                                              .getValue().codigo.set(t.getNewValue()));
                
                diagnostico = FXCollections.observableArrayList();
                
                diagnostico.add(new DiagnosticoElemento("{}","{Doble click para modificar}"));
                final TreeItem<DiagnosticoElemento> root = new RecursiveTreeItem<>(diagnostico, RecursiveTreeObject::getChildren);
                ContextMenu contextMenu = new ContextMenu();
                MenuItem menuItem1 = new MenuItem("Añadir diagnostico");
                MenuItem menuItem2 = new MenuItem("Eliminar diagnostico");
                menuItem1.setOnAction(e ->{
                    diagnostico.add(new DiagnosticoElemento("{}","{Doble click para modificar}"));
                });
                menuItem2.setOnAction(e ->{
                    TreeItem<DiagnosticoElemento> selectedItem = (TreeItem<DiagnosticoElemento>) tvDiag.getSelectionModel().getSelectedItem();
                    if (selectedItem == tvDiag.getRoot()) { // remove root, if you want:
                        tvDiag.setRoot(null);
                    } else { // remove item from its parent:
                        selectedItem.getParent().getChildren().remove(selectedItem);
                    }
                });
                
                contextMenu.getItems().addAll(menuItem1,menuItem2);
                tvDiag.setRoot(root);
                tvDiag.setShowRoot(false);
                tvDiag.setEditable(true);
                tvDiag.getColumns().setAll(diagColumn,diagNColumn);
                tvDiag.setContextMenu(contextMenu);
                tvDiag.setMinHeight(200);
    }
    private final Task<Map<Puesto,PuestoBotonController>> fetchPuestos = new Task() {

        @Override
        protected Map<Puesto,PuestoBotonController> call() throws Exception {
            Map<Puesto,PuestoBotonController> mapa = new HashMap<>();
            try {
                
                for (Puesto puesto : Data.getInstance().getPuestos()) {
                    PuestoBotonController puestoC = new PuestoBotonController();
                    mapa.put(puesto, puestoC);
                    
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return mapa;
        }

    };
    private static final class DiagnosticoElemento extends RecursiveTreeObject<DiagnosticoElemento> {
        final StringProperty codigo;
        final StringProperty nombre;

        DiagnosticoElemento(String codigo, String nombre) {
            this.codigo = new SimpleStringProperty(codigo);
            this.nombre = new SimpleStringProperty(nombre);
        }
    }
    private static final class RecetaElemento extends RecursiveTreeObject<RecetaElemento> {
        final StringProperty ctd;
        final StringProperty nombreGenerico;
        final StringProperty dosis;

        public RecetaElemento(String ctd, String nombreGenerico, String dosis) {
            this.ctd = new SimpleStringProperty(ctd);
            this.nombreGenerico = new SimpleStringProperty(nombreGenerico);
            this.dosis = new SimpleStringProperty(dosis);
        }
    }
    
}
