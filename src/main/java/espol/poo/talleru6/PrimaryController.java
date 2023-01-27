package espol.poo.talleru6;

import espol.poo.modelo.*;
import java.io.*;
import java.io.FileInputStream;
import java.util.ArrayList;
import javafx.application.Platform;
import javafx.fxml.FXML;

import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

public class PrimaryController {

    @FXML
    private FlowPane fpProductos;
    @FXML
    private VBox vbSeccionCrear;
    @FXML
    private TextField txtID;
    @FXML
    private TextField txtNombre;
    @FXML
    private ComboBox cmbCategoria;
    @FXML
    private Label lblEstado;

    /**
     * Initializes the controller class.
     */
    public void initialize() {
        //esconder la seccion de crear producto
        vbSeccionCrear.setVisible(false);
        //llenar el panel central
        llenarPanelCentral();
        

    }

    /**
     * COMPLETAR
     * muestra el VBox del formulario y llena el combobox con los valores de categoria
     */
    @FXML
    private void mostrarSeccion() {
        vbSeccionCrear.setVisible(true);
        
        for(Categoria c :Categoria.values()){
            cmbCategoria.getItems().add(c);
        }
    }


    /**
     * COMPLETAR
     * metodo que llena el FlowPane con la informacion de los productos la
     * imagen del archivo es el id con extension jpg, si no hay archivo mostrar
     * imagen x.  Los archivos se encuentran en la carpeta files, a nivel del proyecto
     */
    private void llenarPanelCentral() {
        fpProductos.getChildren().clear();
        ArrayList<Producto> productos= Producto.cargarProductos(App.rutaProductos);
       
        
        
        

        for(Producto p: productos){
            ImageView imgv = new ImageView();
            try ( FileInputStream input = new FileInputStream("files/"+p.getId()+".jpg")) {
                System.out.println(p);
                
                VBox vBox= new VBox(3);
                vBox.getChildren().clear();
                Image image = new Image(input);
                imgv.setImage(image);
                imgv.setFitHeight(100);
                imgv.setPreserveRatio(true);
                 
                Label lblID = new Label(p.getId()+"");
                Label lblNombre = new Label(p.getNombre());
                vBox.getChildren().addAll(imgv, lblID,lblNombre);
                fpProductos.getChildren().add(vBox);
        
            } catch (Exception e){
                try ( FileInputStream input = new FileInputStream("files/x.jpg")) {
                    

                    VBox vBox= new VBox(3);
                    vBox.getChildren().clear();
                    Image image = new Image(input);
                    imgv.setImage(image);
                    imgv.setFitHeight(100);
                    imgv.setPreserveRatio(true);

                    Label lblID = new Label(p.getId()+"");
                    Label lblNombre = new Label(p.getNombre());
                    vBox.getChildren().addAll(imgv, lblID,lblNombre);
                    fpProductos.getChildren().add(vBox);
                }catch(Exception ex){
                    System.out.println(ex.getMessage());
                }
            }
        }
                
        
    }
     /**
     * COMPLETAR
     * metodo asociado al boton Guardar
     * verifica que se haya completado el formulario para guardar el producto
     * 
     */
    
    @FXML
    private void guardarProducto() {
        //llamar al metodo guardar producto y presentar los dialogos correspondientes
        //int id=Integer.parseInt(txtID.getText());
        //String nombre = txtNombre.getText();
        //Categoria cat=(Categoria) cmbCategoria.getValue();
     
        
        try {
            Producto.guardarProducto(App.rutaProductos,Integer.parseInt(txtID.getText()),txtNombre.getText(),(Categoria) cmbCategoria.getValue());
            
            
            mostrarAlerta(Alert.AlertType.INFORMATION,"Producto guardado exitosamente" );
            
            txtID.clear();
            txtNombre.clear();
            cancelarCrear();
            
            
     
        } catch (IOException ex) {
            mostrarAlerta(Alert.AlertType.ERROR, ex.getMessage());
        } catch(Exception exc){
            mostrarAlerta(Alert.AlertType.ERROR, exc.getMessage());
        }finally{
          llenarPanelCentral();  
        }
        

    }
    
    /**
     * COMPLETAR
     * metodo asociado al boton Sincronizar Repositorio
     * mediante un hilo llama al metodo sincronizar de Utils y muestra en el Label
     * el estado de la transacción
     * 
     */
    @FXML
    private void sincRepositorio() {
        lblEstado.setText("Realizando sincronización");
        
        Thread t = new Thread(() -> {
            
            Utils.sincronizar();
            Platform.runLater(() -> {
                lblEstado.setText("Archivo creado exitosamente");
            });
        });
        t.start();
        
    }

    @FXML
    private void cancelarCrear() {
        vbSeccionCrear.setVisible(false);
    }
    private void mostrarAlerta(Alert.AlertType tipo, String mensaje) {
        Alert alert = new Alert(tipo);

        alert.setTitle("Resultado de operacion");
        alert.setHeaderText("Notificacion");
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
