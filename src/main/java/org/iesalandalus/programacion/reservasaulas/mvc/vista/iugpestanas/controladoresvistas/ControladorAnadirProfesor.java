package org.iesalandalus.programacion.reservasaulas.mvc.vista.iugpestanas.controladoresvistas;

import org.iesalandalus.programacion.reservasaulas.mvc.controlador.IControlador;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.mvc.vista.iugpestanas.utilidades.Dialogos;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ControladorAnadirProfesor {
	
	private static final String ER_OBLIGATORIO = ".+";
	private static final String ER_TELEFONO = "\\d{9}";
	private static final String ER_CORREO = "\\w+(?:\\.\\w+)*@\\w+\\.\\w{2,5}";
	
	private IControlador controladorMVC;
	private ObservableList<Profesor> profesores;
	
	@FXML private TextField tfNombre;
	@FXML private TextField tfCorreo;
	@FXML private TextField tfTelefono;
	@FXML private Button btAnadir;
	@FXML private Button btCancelar;
	
	@FXML
	private void initialize() {
		tfNombre.textProperty().addListener((ob, ov, nv) -> compruebaCampoTexto(ER_OBLIGATORIO, tfNombre));
		tfCorreo.textProperty().addListener((ob, ov, nv) -> compruebaCampoTexto(ER_CORREO, tfCorreo));
		tfTelefono.textProperty().addListener((ob, ov, nv) -> compruebaCampoTexto(ER_TELEFONO, tfTelefono));
	}
	
	public void setControladorMVC(IControlador controladorMVC) {
		this.controladorMVC = controladorMVC;
	}
	
	public void setProfesores(ObservableList<Profesor> profesores) {
		this.profesores = profesores;
	}
	
	@FXML
	private void anadirProfesor() {
		Profesor profesor = null;
		try {
			profesor = getProfesor();
			controladorMVC.insertar(profesor);
			profesores.setAll(controladorMVC.getProfesores());
			Stage propietario = ((Stage) btAnadir.getScene().getWindow());
			Dialogos.mostrarDialogoInformacion("Añadir Profesor", "Profesor añadido satisfactoriamente", propietario);
		} catch (Exception e) {
			Dialogos.mostrarDialogoError("Añadir Profesor", e.getMessage());
		}	
	}
	
	@FXML
	private void cancelar() {
		((Stage) btCancelar.getScene().getWindow()).close();
	}
	
    public void inicializa() {
    	tfNombre.setText("");
    	compruebaCampoTexto(ER_OBLIGATORIO, tfNombre);
    	tfCorreo.setText("");
    	compruebaCampoTexto(ER_CORREO, tfCorreo);
    	tfTelefono.setText("");
    	compruebaCampoTexto(ER_TELEFONO, tfTelefono);
    }
	
	private void compruebaCampoTexto(String er, TextField campoTexto) {	
		String texto = campoTexto.getText();
		if (texto.matches(er)) {
			campoTexto.setStyle("-fx-border-color: green; -fx-border-radius: 5;");
		}
		else {
			campoTexto.setStyle("-fx-border-color: red; -fx-border-radius: 5;");
		}
	}
	
	private Profesor getProfesor() {
		String nombre = tfNombre.getText();
		String correo = tfCorreo.getText();
		String telefono = tfTelefono.getText().isEmpty() ? null : tfTelefono.getText();
		return new Profesor(nombre, correo, telefono);
	}

}
