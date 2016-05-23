package rocket.app.view;

import java.net.URL;
import java.util.ResourceBundle;

import eNums.eAction;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import rocket.app.MainApp;
import rocketCode.Action;
import rocketData.LoanRequest;

public class MortgageController implements Initializable {

	private MainApp mainApp;

	@FXML
	private TextField txtIncome;
	@FXML
	private TextField txtExpenses;
	@FXML
	private TextField txtCreditScore;
	@FXML
	private TextField txtHouseCost;
	@FXML
	private ComboBox cmbTerm;
	@FXML
	private TextField txtDownPayment;
	@FXML
	private Label labelRate;
	@FXML
	private Label labelPayment;
	@FXML
	private Button btnPayment;
	@FXML
	private Label labelError;

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}

	@FXML
	public void btnCalculatePayment(ActionEvent event) {
		Object message = null;

		Action a = new Action(eAction.CalculatePayment);
		LoanRequest lq = new LoanRequest();
		lq.setdAmount(Double.parseDouble(txtHouseCost.getText()));
		lq.setiCreditScore(Integer.parseInt(txtCreditScore.getText()));
		lq.setIncome(Double.parseDouble(txtIncome.getText()));
		lq.setExpenses(Double.parseDouble(txtExpenses.getText()));
		lq.setiTerm(Integer.parseInt(cmbTerm.getSelectionModel().getSelectedItem().toString()));
		a.setLoanRequest(lq);
		mainApp.messageSend(lq);
	}

	public void HandleLoanRequestDetails(LoanRequest lq) {

		labelRate.setText(Double.toString(lq.getdRate()));
		String pmt = Double.toString(lq.getdPayment());
		int idec = pmt.indexOf(".");
		labelPayment.setText(pmt.substring(0, idec + 3));
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		cmbTerm.getItems().add("15");
		cmbTerm.getItems().add("30");
		cmbTerm.getSelectionModel().select("30");

	}
}
