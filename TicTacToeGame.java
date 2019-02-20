import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

//Reudverly Duvert
public class TicTacToeGame extends Application implements EventHandler<ActionEvent> {
	Button button1 = new Button();
	Button button2 = new Button();
	Button button3 = new Button();
	Button button4 = new Button();
	Button button5 = new Button();
	Button button6 = new Button();
	Button button7 = new Button();
	Button button8 = new Button();
	Button button9 = new Button();
	Button restart = new Button();
	Button[][] boardButtons = new Button[3][3];
	GridPane gridPane = new GridPane();
	boolean player1 = true;
	boolean player2 = false; 
	boolean playing = true; 


	@Override
	public void start(Stage primaryStage) throws Exception {
		BorderPane root = new BorderPane();
		root.setPadding(new Insets(0, 72, 108, 104));
		VBox vbox = new VBox();
		vbox.setPadding(new Insets(12, 54, 52, 52));
		vbox.setSpacing(10);

		Label label = new Label("Option");
		label.setFont(Font.font("vedana", FontWeight.BOLD, 24));
		vbox.getChildren().add(label);
		restart.setText("Restart");
		vbox.getChildren().add(restart);
		primaryStage.setTitle("Tic Tac Toe");
		
		gridPane.add(button1, 0, 0, 1, 1);
		button1.setPrefSize(133, 133);
		boardButtons[0][0] = button1; 
		
		gridPane.add(button2, 1, 0, 1, 1);
		button2.setPrefSize(133, 133);
		boardButtons[1][0] = button2; 
		
		gridPane.add(button3, 2, 0, 1, 1);
		button3.setPrefSize(133, 133);
		boardButtons[2][0] = button3; 

		gridPane.add(button4, 0, 1, 1, 1);
		button4.setPrefSize(133, 133);
		boardButtons[0][1] = button4; 


		gridPane.add(button5, 1, 1, 1, 1);
		button5.setPrefSize(133, 133);
		boardButtons[1][1] = button5; 

		gridPane.add(button6, 2, 1, 1, 1);
		button6.setPrefSize(133, 133);
		boardButtons[2][1] = button6;

		gridPane.add(button7, 0, 2, 1, 1);
		button7.setPrefSize(133, 133);
		boardButtons[0][2] = button7;

		gridPane.add(button8, 1, 2, 1, 1);
		button8.setPrefSize(133, 133);
		boardButtons[1][2] = button8;

		gridPane.add(button9, 2, 2, 1, 1);
		button9.setPrefSize(133, 133);
		boardButtons[2][2] = button9;

		button1.setOnAction(this);
		button2.setOnAction(this);
		button3.setOnAction(this);
		button4.setOnAction(this);
		button5.setOnAction(this);
		button6.setOnAction(this);
		button7.setOnAction(this);
		button8.setOnAction(this);
		button9.setOnAction(this);
		restart.setOnAction(this);

		Scene scene = new Scene(root, 800, 500);
		primaryStage.setScene(scene);
		root.setRight(vbox);
		root.setCenter(gridPane);
		primaryStage.show();
	}

	@Override
	public void handle(ActionEvent event) {
		if(playing){
			if(player1 && !player2){
				for(int i = 0; i < boardButtons.length; i++){
					for(int j = 0; j < boardButtons[i].length; j++){
						if (event.getSource() == boardButtons[j][i]){ 
							if(emptyButton(boardButtons[j][i])) {
								drawX(boardButtons[j][i]); 
								System.out.println("Player 1 has made a move, it's player 2's turn");
							}
							if(player1Wins()){
								System.out.println("Player 1 Has Won!!!");
								playing = false; 
								if(event.getSource() == restart)
									playing = true; 
							}


						}

					}
				}
				player1 = false; 
				player2 = true; 
			}

			else if(player2 && !player1){
				for(int i = 0; i < boardButtons.length; i++){
					for(int j = 0; j < boardButtons[i].length; j++){
						if (event.getSource() == boardButtons[j][i]){ 
							if (emptyButton(boardButtons[j][i])){
								drawO(boardButtons[j][i]); 
								System.out.println("Player 2 has made a move, it's player 1's turn");
							}
							if(player2Wins()){
								System.out.println("Player 2 Has Won");
								playing = false; 
							}
						}

					}
				}
				player1 = true; 
				player2 = false; 
			}
		}
		if(!player1Wins() && !player2Wins() && draw()){
			System.out.println("Draw");  
			playing = false;
			if(!playing && event.getSource() == restart){
				startOver(); 
			}

		}

		else if(event.getSource()== restart){
			startOver(); 
		}
	}

	private boolean startOver(){
		System.out.println("A New Game has started");
		for(int i = 0; i < boardButtons.length; i++){
			for(int j = 0; j < boardButtons[i].length; j++){
				boardButtons[j][i].setText("");
				player1 = true; 
				player2 = false; 
				playing = true; 
			}
		}
		return false; 

	}

	public boolean emptyButton(Button button){
		if(button.getText().equals("X") || button.getText().equals("O")){
			return false; 
		}
		return true; 

	}

	public boolean draw(){
		for(int i = 0; i < boardButtons.length; i++){
			for(int j = 0; j < boardButtons[i].length; j++){
				if(emptyButton(boardButtons[j][i])){
					return false; 
				}

			} 
		}
		return true; 
	}

	public boolean player1Wins(){
		//vertical
		for(int i = 0; i < 3; i++){
			if(boardButtons[i][0].getText().equals("X") && boardButtons[i][1].getText().equals("X") &&
					boardButtons[i][2].getText().equals("X")){
				return true; 

			}
		}

		//horizontal
		for(int i = 0; i < 3; i++){
			//boardButtons[0][i] boardButtons[1][i] boardButtons[2][i]
			if(boardButtons[0][i].getText().equals("X") && boardButtons[1][i].getText().equals("X") &&
					boardButtons[2][i].getText().equals("X")){
				return true; 
			}

		}

		if(boardButtons[0][0].getText().equals("X") && boardButtons[1][1].getText().equals("X") &&
				boardButtons[2][2].getText().equals("X") || boardButtons[0][2].getText().equals("X") && 
				boardButtons[1][1].getText().equals("X") &&
				boardButtons[2][0].getText().equals("X") ){
			return true; 

		}
		return false; 

	}

	public boolean player2Wins(){
		//vertical
		for(int i = 0; i < 3; i++){
			if(boardButtons[i][0].getText().equals("O") && boardButtons[i][1].getText().equals("O") &&
					boardButtons[i][2].getText().equals("O")){
				return true; 

			}
		}

		//horizontal
		for(int i = 0; i < 3; i++){
			//boardButtons[0][i] boardButtons[1][i] boardButtons[2][i]
			if(boardButtons[0][i].getText().equals("O") && boardButtons[1][i].getText().equals("O") &&
					boardButtons[2][i].getText().equals("O")){
				return true; 
			}

		}
		
		if(boardButtons[0][0].getText().equals("O") && boardButtons[1][1].getText().equals("O") &&
				boardButtons[2][2].getText().equals("O") || boardButtons[0][2].getText().equals("O") && 
				boardButtons[1][1].getText().equals("O") &&
				boardButtons[2][0].getText().equals("O") ){
			return true; 

		}
		return false; 


	}

	private void drawX(Button but) {
		but.setText("X");
		but.setFont(Font.font("vedana", 60));
	}

	private void drawO(Button button) {
		button.setText("O");
		button.setFont(Font.font("vedana", 60));
	}
	public static void main(String[] args) {
		Application.launch(args); 
	}

}

