package com.kingston.ui.viewcontroller;

import java.net.URL;
import java.util.ResourceBundle;

import com.kingston.base.ClientBaseService;
import com.kingston.logic.user.message.UserManager;
import com.kingston.ui.ControlledStage;
import com.kingston.ui.R;
import com.kingston.ui.StageController;
import com.kingston.ui.container.ResourceContainer;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;

public class RegisterViewController implements ControlledStage, Initializable {

	@FXML
	private Button register;
	@FXML
	private TextField userName;
	@FXML
	private PasswordField password;
	@FXML
	private Label errorText;
	@FXML
	private ToggleGroup sexGroup;

	@FXML
	private void register() {
		String nickName = userName.getText();
		String psw = password.getText();
		byte sexCode = Byte.parseByte(sexGroup.getSelectedToggle().getUserData().toString());
		UserManager.getInstance().registerAccount(sexCode, nickName, psw);
	}

	@FXML
	private void register_entered() {
		register.setStyle("-fx-background-radius:4;-fx-background-color: #097299");
	}

	@FXML
	private void register_exit() {
		register.setStyle("-fx-background-radius:4;-fx-background-color: #09A3DC");
	}

	@FXML
	private void userNameChange() {
	}

	@FXML
	private void close() {
		System.exit(1);
	}

	@FXML
	private void min() {
	}

	@FXML
	private void closeEntered() {
		Image image = ResourceContainer.getClose_1();
	}

	@FXML
	private void closeExited() {
		Image image = ResourceContainer.getClose();
	}

	@FXML
	private void gotoLogin() {
		clearFields();
		StageController stageController = ClientBaseService.INSTANCE.getStageController();
		stageController.switchStage(R.id.LoginView, R.id.RegisterView);
	}

	private void clearFields() {
		userName.setText("");
		password.setText("");
		errorText.setText("");
		errorText.setVisible(false);
	}

	@FXML
	private void callBackLogin() {

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//验证规则：　userId非空且为数字　password非空
		register.disableProperty().bind(
			Bindings.createBooleanBinding(
				() -> userName.getText().length() == 0 ||
					  password.getText().length() == 0,
				userName.textProperty(),
				password.textProperty()));
		//把性别的常量值填进去
		for (int i=0;i<this.sexGroup.getToggles().size();i++) {
			Toggle sexToggle = this.sexGroup.getToggles().get(i);
			sexToggle.setUserData(String.valueOf(i));
		}
	}


}
