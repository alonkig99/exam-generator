package View;

import Listeners.SystemUIEventListener;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.swing.*;

import javafx.scene.control.ScrollPane;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;


public class SystemView implements AbstractSystemView {

    private List<SystemUIEventListener> allListeners = new ArrayList<>();
    private Scene menuScene, addQuestionScene;
    private final Stage stgDisplayQuestion = new Stage();

    public SystemView(Stage theStage) {
        theStage.setTitle("Exam Generator");

        Button btnReturnToMenu = new Button("Return to main menu");
        btnReturnToMenu.setTranslateX(100);
        btnReturnToMenu.setTranslateY(100);
        btnReturnToMenu.setOnAction(actionEvent -> theStage.setScene(menuScene));

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
        hbQuestionText.setSpacing(5);
        Label addQuestionLabel = new Label("Enter question text:");
        TextField tfAddQuestion = new TextField();
        hbQuestionText.getChildren().addAll(addQuestionLabel, tfAddQuestion);
        Label chooseTypeLabel = new Label("Choose type of question:");
        ToggleGroup tglQuestionType = new ToggleGroup();
        RadioButton rdoAmericanButton = new RadioButton("American Question");
        RadioButton rdoOpenQuestionButton = new RadioButton("Open Question");
        rdoAmericanButton.setToggleGroup(tglQuestionType);
        rdoOpenQuestionButton.setToggleGroup(tglQuestionType);
        vbAddQuestion.setPadding(new Insets(10));
        vbAddQuestion.setSpacing(10);
        //Open Answer HBox//
        HBox hbAddOpenAnswer = new HBox();
        hbAddOpenAnswer.setVisible(false);
        Label lblAddOpenAnswer = new Label("Enter answer text:");
        TextField tfAnswerText = new TextField();
        Button btnSubmitOpenAnswer = new Button("Submit question&answer");
        btnSubmitOpenAnswer.setOnAction((actionEvent -> {
            for (SystemUIEventListener l : allListeners) {
                l.addOpenQuestionToModel(tfAddQuestion.getText(), tfAnswerText.getText());
            }
        }));
        hbAddOpenAnswer.setPadding(new Insets(10));
        hbAddOpenAnswer.setSpacing(5);
        hbAddOpenAnswer.getChildren().addAll(lblAddOpenAnswer, tfAnswerText, btnSubmitOpenAnswer);


        ////MultipleChoice Answer HBox//
        LinkedHashSet<String> answersList = new LinkedHashSet<>();
        ArrayList<Boolean>booleanList = new ArrayList<>();
        VBox vbAddAmericanAnswer = new VBox();
        vbAddAmericanAnswer.setVisible(false);
        HBox hbAddAmericanAnswer = new HBox();
        Label lblAddAmericanAnswer = new Label("Enter answers one by one:");
        TextField tfAmericanAnswerText = new TextField();
        ToggleGroup tglBooleanValue = new ToggleGroup();
        RadioButton btnTrue = new RadioButton("True");
        RadioButton btnFalse = new RadioButton("False");
        btnTrue.setToggleGroup(tglBooleanValue);
        btnFalse.setToggleGroup(tglBooleanValue);
        Button btnSubmitAmericanAnswer = new Button("Submit answer");
        btnSubmitAmericanAnswer.setOnAction(actionEvent -> {
            if (tglBooleanValue.getSelectedToggle()!=null && tglBooleanValue.getSelectedToggle().equals(btnTrue)){
                booleanList.add(true);
            }
            else if (tglBooleanValue.getSelectedToggle()!=null && tglBooleanValue.getSelectedToggle().equals(btnFalse)){
                booleanList.add(false);
            }
          answersList.add(tfAmericanAnswerText.getText());
          tfAmericanAnswerText.clear();
        });
        Button btnSubmit = new Button("Submit final question&answers");
        btnSubmit.setOnAction(actionEvent -> {
            for (SystemUIEventListener l : allListeners) {
                l.addMultiChoiceQuestionToModel(tfAddQuestion.getText(),answersList,booleanList);
                answersList.clear();
                booleanList.clear();
            }

        });
        hbAddAmericanAnswer.getChildren().addAll(lblAddAmericanAnswer,tfAmericanAnswerText);
        vbAddAmericanAnswer.setSpacing(5);
        vbAddAmericanAnswer.getChildren().addAll(hbAddAmericanAnswer,btnTrue,btnFalse,btnSubmitAmericanAnswer,btnSubmit);




        vbAddQuestion.getChildren().addAll(hbQuestionText, chooseTypeLabel, rdoAmericanButton, rdoOpenQuestionButton, hbAddOpenAnswer,vbAddAmericanAnswer, btnReturnToMenu);
        addQuestionScene = new Scene(vbAddQuestion, 500, 500);
        rdoAmericanButton.setOnAction(actionEvent -> {
          hbAddOpenAnswer.setVisible(false);
          vbAddAmericanAnswer.setVisible(true);
        });
        rdoOpenQuestionButton.setOnAction(actionEvent -> {
            vbAddAmericanAnswer.setVisible(false);
            hbAddOpenAnswer.setVisible(true);

        });

        VBox vbMenu = new VBox();
        vbMenu.getChildren().addAll(displayQuestionsButton, addQuestionButton);
        vbMenu.setSpacing(5);
        vbMenu.setPadding(new Insets(10));
        menuScene = new Scene(vbMenu, 750, 450);
        theStage.setScene(menuScene);
        theStage.show();


    }


    @Override
    public void registerListener(SystemUIEventListener listener) {
        allListeners.add(listener);
    }


    @Override
    public void displayQuestions(String s) {
        stgDisplayQuestion.setTitle("Questions list");
        ScrollPane sp = new ScrollPane(new Label(s));
        stgDisplayQuestion.setScene(new Scene(sp, 300, 250));
        stgDisplayQuestion.showAndWait();

    }

    public void successfulMessage(String msg) {
        JOptionPane.showMessageDialog(null, msg);


    }

    public void failedMessage(String msg) {
        JOptionPane.showMessageDialog(null, msg);


    }

    @Override
    public void questionAlreadyExistsMessage(String msg) {
        JOptionPane.showMessageDialog(null, msg);
    }
}
