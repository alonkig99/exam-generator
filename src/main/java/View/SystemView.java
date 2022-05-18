package View;

import Listeners.SystemUIEventListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;



public class SystemView implements AbstractSystemView {

    private List<SystemUIEventListener> allListeners = new ArrayList<>();
    private Scene menuScene,  addQuestionScene;
  private final Button btnReturnToMenu = new Button("return to main menu");


    public SystemView(Stage theStage) {
        btnReturnToMenu.setTranslateX(300);
        btnReturnToMenu.setTranslateY(200);
        btnReturnToMenu.setOnAction(actionEvent -> theStage.setScene(menuScene));

        theStage.setTitle("Exam Generator");

        //// MAIN MENU BUTTONS /////////
        VBox vbMenu = new VBox();


        Button displayQuestionsButton = new Button("Display Questions");
        displayQuestionsButton.setOnAction(actionEvent -> {
            for (SystemUIEventListener l : allListeners) {
                l.displayQuestionToModel();
            }
        });

        Button addQuestionButton = new Button("Add question");
        addQuestionButton.setOnAction(actionEvent -> theStage.setScene(addQuestionScene));



        //// Add Question Scene ////
        VBox vbAddQuestion = new VBox();
        HBox hbQuestionText = new HBox();
        Label addQuestionLabel = new Label("Enter question text:");
        TextField tfAddQuestion = new TextField();
        hbQuestionText.setSpacing(5);
        hbQuestionText.getChildren().addAll(addQuestionLabel, tfAddQuestion);
        Label chooseTypeLabel = new Label("Choose type of question:");
        ToggleGroup tglQuestionType = new ToggleGroup();
        RadioButton rdoAmericanButton = new RadioButton("American Question");
        RadioButton rdoOpenButton = new RadioButton("Open Question");
        rdoAmericanButton.setToggleGroup(tglQuestionType);
        rdoOpenButton.setToggleGroup(tglQuestionType);
        vbAddQuestion.setPadding(new Insets(10));
        vbAddQuestion.setSpacing(10);



        //Open Answer HBox//
        HBox hbAddOpenAnswer = new HBox();
        hbAddOpenAnswer.setVisible(false);
        Label addAnswerLabel = new Label("Enter answer text:");
        TextField tfAnswerText = new TextField();
        Button submitAnsButton = new Button("Submit");
        submitAnsButton.setOnAction((actionEvent -> {
            for (SystemUIEventListener l : allListeners) {
                l.addOpenQuestionToModel(tfAddQuestion.getText(), tfAnswerText.getText());
            }
        }));
        hbAddOpenAnswer.setPadding(new Insets(10));
        hbAddOpenAnswer.setSpacing(5);
        hbAddOpenAnswer.getChildren().addAll(addAnswerLabel, tfAnswerText, submitAnsButton);



        rdoOpenButton.setOnAction(actionEvent -> hbAddOpenAnswer.setVisible(true));

        vbAddQuestion.getChildren().addAll(hbQuestionText, chooseTypeLabel, rdoAmericanButton, rdoOpenButton, hbAddOpenAnswer, btnReturnToMenu);
        addQuestionScene = new Scene(vbAddQuestion, 500, 500);


        vbMenu.getChildren().addAll(displayQuestionsButton, addQuestionButton);
        vbMenu.setSpacing(5);
        vbMenu.setPadding(new Insets(10));
        menuScene = new Scene(vbMenu, 750, 450);
      //  theStage.setScene(new Scene(vbMenu, 750, 450));
theStage.setScene(menuScene);
        theStage.show();


    }

    ///////// METHODS /////////////
    @Override
    public void registerListener(SystemUIEventListener listener) {
        allListeners.add(listener);
    }


    @Override
    public void displayQuestions(String s) {
        JOptionPane.showMessageDialog(null, s);

    }
    public void  successfulMessage(String msg){
        JOptionPane.showMessageDialog(null, "Question added successfully!");


    }
    public void failedMessage(String message){
        JTextArea area = new JTextArea(message);
        JScrollPane scrollPane = new JScrollPane(area);

        JOptionPane.showMessageDialog( scrollPane, area);
        //JScrollPane.


    }
}
