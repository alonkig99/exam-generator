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
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javax.swing.*;


import javafx.scene.control.ScrollPane;


import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;


public class SystemView implements AbstractSystemView {

    private final List<SystemUIEventListener> listeners = new ArrayList<>();
    private Scene menuScene, addQuestionScene, updateQuestionScene, updateAnswerScene, deleteAnswerScene, manualExamScene, manualMultiChoiceScene, generateExamScene;
    private final Stage stgDisplayQuestion = new Stage();
    private final Stage stgDisplayGeneratedExam = new Stage();
    private final VBox vbUpdateOpenQuestionAnswer;
    private final VBox vbUpdateMultiChoiceAnswer;
    private final VBox vbManualMultiChoice;
    private final ComboBox<Integer> cmbQuestionsNums1 = new ComboBox<>();
    private final ComboBox<Integer> cmbQuestionsNums2 = new ComboBox<>();
    private final ComboBox<Integer> cmbQuestionsNums3 = new ComboBox<>();
    private final ComboBox<Integer> cmbQuestionsNums4 = new ComboBox<>();
    private final ComboBox<Integer> cmbQuestionsNums5 = new ComboBox<>();
    private final ComboBox<Integer> cmbQuestionsNums6 = new ComboBox<>();
    private final ComboBox<Integer> cmbAnswersNums1 = new ComboBox<>();
    private final ComboBox<Integer> cmbAnswersNums2 = new ComboBox<>();
    private final ComboBox<Integer> cmbAnswersNums3 = new ComboBox<>();

    public SystemView(Stage theStage) throws FileNotFoundException {
        theStage.setTitle("Exam Generator");
        VBox vbMenu = new VBox(500);


        Button btnDisplayQuestions = new Button("Display Questions");
        btnDisplayQuestions.setMaxWidth(150);
        btnDisplayQuestions.setOnAction(actionEvent -> {
            for (SystemUIEventListener l : listeners) {
                l.displayQuestionToModel();
            }
        });

        Button btnAddQuestion = new Button("Add Question");
        btnAddQuestion.setMaxWidth(150);
        btnAddQuestion.setOnAction(actionEvent -> {
            stgDisplayQuestion.close();
            theStage.setScene(addQuestionScene);
        });

        Button btnUpdateQuestion = new Button("Update Question");
        btnUpdateQuestion.setMaxWidth(150);
        btnUpdateQuestion.setOnAction(actionEvent -> {

            stgDisplayQuestion.close();
            theStage.setScene(updateQuestionScene);
            for (SystemUIEventListener l : listeners) {
                l.displayQuestionToModel();
            }
        });
        Button btnUpdateAnswer = new Button("Update Answer");
        btnUpdateAnswer.setMaxWidth(150);
        btnUpdateAnswer.setOnAction(actionEvent -> {
            stgDisplayQuestion.close();
            theStage.setScene(updateAnswerScene);
            for (SystemUIEventListener l : listeners) {
                l.displayQuestionToModel();
            }
        });

        Button btnDeleteAnswer = new Button("Delete Answer");
        btnDeleteAnswer.setMaxWidth(150);
        btnDeleteAnswer.setOnAction(actionEvent -> {
            stgDisplayQuestion.close();
            theStage.setScene(deleteAnswerScene);
            for (SystemUIEventListener l : listeners) {
                l.displayQuestionToModel();
            }
        });

        Button btnManualExam = new Button("Create Exam Manually");
        btnManualExam.setMaxWidth(150);
        btnManualExam.setOnAction(actionEvent -> {
            stgDisplayQuestion.close();
            theStage.setScene(manualExamScene);
            for (SystemUIEventListener l : listeners) {
                l.displayQuestionToModel();
            }
        });

        Button btnGenerateExam = new Button("Generate Exam");
        btnGenerateExam.setMaxWidth(150);
        btnGenerateExam.setOnAction(actionEvent -> {
            stgDisplayQuestion.close();
            theStage.setScene(generateExamScene);
        });

        Button btnSaveAndExit = new Button("Save to file & Exit");
        btnSaveAndExit.setTextFill(Paint.valueOf("Red"));
        btnSaveAndExit.setMaxWidth(150);
        btnSaveAndExit.setOnAction(actionEvent -> {
            for (SystemUIEventListener l : listeners) {
                try {
                    l.saveBinaryFileToModel();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } finally {
                    theStage.close();
                }
            }
        });

        Button btnCopyExam = new Button("Copy the latest Exam");
        btnCopyExam.setMaxWidth(150);
        btnCopyExam.setOnAction(actionEvent -> {
            for (SystemUIEventListener l : listeners) {
                try {
                    l.copyLastExamToModel();
                } catch (CloneNotSupportedException e) {
                    throw new RuntimeException(e);
                }
            }
        });


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

        VBox vbAddOpenAnswer = new VBox();
        vbAddOpenAnswer.setVisible(false);
        Label lblAddOpenAnswer = new Label("Enter answer text:");
        TextField tfAnswerText = new TextField();
        Button btnSubmitOpenAnswer = new Button("Submit question & answer");
        btnSubmitOpenAnswer.setOnAction((actionEvent -> {
            if (tfAddQuestion.getText().equals("") || tfAnswerText.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Question/answer fields cannot be empty. Try again.");
                return;
            }
            for (SystemUIEventListener l : listeners) {
                l.addOpenQuestionToModel(tfAddQuestion.getText(), tfAnswerText.getText());
            }
            tfAddQuestion.clear();
            tfAnswerText.clear();
        }));
        vbAddOpenAnswer.setPadding(new Insets(10));
        vbAddOpenAnswer.setSpacing(10);
        vbAddOpenAnswer.getChildren().addAll(lblAddOpenAnswer, tfAnswerText, btnSubmitOpenAnswer);

        VBox vbAddAmericanAnswer = new VBox();
        vbAddAmericanAnswer.setVisible(false);
        LinkedHashSet<String> answersList = new LinkedHashSet<>();
        ArrayList<Boolean> booleanList = new ArrayList<>();
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
            if (tfAmericanAnswerText.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "answer field cannot be empty. Try again.");
                return;
            }
            if (tglBooleanValue.getSelectedToggle() != null && tglBooleanValue.getSelectedToggle().equals(btnTrue)) {
                booleanList.add(true);
            } else if (tglBooleanValue.getSelectedToggle() != null && tglBooleanValue.getSelectedToggle().equals(btnFalse)) {
                booleanList.add(false);
            }
            answersList.add(tfAmericanAnswerText.getText());
            tfAmericanAnswerText.clear();
        });
        Button btnSubmit = new Button("Submit final question & answers");
        btnSubmit.setOnAction(actionEvent -> {
            if (answersList.size() < 2) {
                JOptionPane.showMessageDialog(null, "Minimum 2 answers required.");
                return;
            }
            if (tfAddQuestion.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Question field cannot be empty. Try again.");
                answersList.clear();
                booleanList.clear();
                tfAddQuestion.clear();
                return;
            }
            for (SystemUIEventListener l : listeners) {
                l.addMultiChoiceQuestionToModel(tfAddQuestion.getText(), answersList, booleanList);
                answersList.clear();
                booleanList.clear();
                tfAddQuestion.clear();
            }
        });
        hbAddAmericanAnswer.getChildren().addAll(lblAddAmericanAnswer, tfAmericanAnswerText);
        vbAddAmericanAnswer.setSpacing(5);
        vbAddAmericanAnswer.getChildren().addAll(hbAddAmericanAnswer, btnTrue, btnFalse, btnSubmitAmericanAnswer, btnSubmit);
        Button btnReturnToMenu1 = new Button("Return to main menu");
        btnReturnToMenu1.setOnAction(actionEvent -> {
            answersList.clear();
            booleanList.clear();
            theStage.setScene(menuScene);
        });
        vbAddQuestion.getChildren().addAll(hbQuestionText, chooseTypeLabel, rdoAmericanButton, rdoOpenQuestionButton, vbAddOpenAnswer, vbAddAmericanAnswer, btnReturnToMenu1);
        addQuestionScene = new Scene(vbAddQuestion, 400, 500);
        rdoAmericanButton.setOnAction(actionEvent -> {
            vbAddOpenAnswer.setVisible(false);
            vbAddOpenAnswer.setManaged(false);
            vbAddAmericanAnswer.setVisible(true);
            vbAddAmericanAnswer.setManaged(true);
        });
        rdoOpenQuestionButton.setOnAction(actionEvent -> {
            vbAddAmericanAnswer.setVisible(false);
            vbAddAmericanAnswer.setManaged(false);
            vbAddOpenAnswer.setVisible(true);
            vbAddOpenAnswer.setManaged(true);

        });


        VBox vbUpdateQuestion = new VBox();
        Label lblUpdatedQuestionNum = new Label("Choose question number below:");
        Label lblUpdatedQuestionText = new Label("Enter the updated text:");
        TextField tfUpdatedQuestionText = new TextField();
        Button btnSubmitUpdatedQuestion = new Button("Submit updated question.");
        btnSubmitUpdatedQuestion.setOnAction(actionEvent -> {
            if (cmbQuestionsNums1.getValue() == null) {
                showPopUpMessage("You must choose a question number!");
                return;
            }
            if (tfUpdatedQuestionText.getText().equals("")) {
                showPopUpMessage("Text cannot be empty. Try again.");
                return;
            }

            for (SystemUIEventListener l : listeners) {
                l.updateQuestionToModel(cmbQuestionsNums1.getValue(), tfUpdatedQuestionText.getText());
            }
        });
        vbUpdateQuestion.setSpacing(5);
        vbUpdateQuestion.setPadding(new Insets(10));
        Button btnReturnToMenu2 = new Button("Return to main menu");
        btnReturnToMenu2.setOnAction(actionEvent -> {
            stgDisplayQuestion.close();
            theStage.setScene(menuScene);
        });
        vbUpdateQuestion.getChildren().addAll(lblUpdatedQuestionNum, cmbQuestionsNums1, lblUpdatedQuestionText, tfUpdatedQuestionText, btnSubmitUpdatedQuestion, btnReturnToMenu2);
        updateQuestionScene = new Scene(vbUpdateQuestion, 400, 500);


        VBox vbUpdateAnswer = new VBox();
        HBox hbQuestionNum = new HBox();
        Label lblQuestionNum = new Label("Choose question number:");
        cmbQuestionsNums2.setOnAction(actionEvent -> {
            if (cmbQuestionsNums2.getValue()==null) {
                return;
            }
            for (SystemUIEventListener l : listeners) {
                l.checkIfMultiChoiceQuestionToModel(cmbQuestionsNums2.getValue());
            }
            cmbAnswersNums1.getItems().clear();
            cmbAnswersNums2.getItems().clear();
            for (SystemUIEventListener l : listeners) {
                l.updateNumberOfAnswersToModel(cmbQuestionsNums2.getValue());
            }
        });
        hbQuestionNum.getChildren().addAll(lblQuestionNum, cmbQuestionsNums2);
        hbQuestionNum.setPadding(new Insets(10));
        hbQuestionNum.setSpacing(10);

        vbUpdateOpenQuestionAnswer = new VBox();
        vbUpdateOpenQuestionAnswer.setVisible(false);
        Label lblUpdatedAnswer = new Label("Enter updated answer below:");
        TextField tfEnterUpdatedAnswer = new TextField();
        Button btnSubmitUpdatedAnswer = new Button("Submit answer");
        btnSubmitUpdatedAnswer.setOnAction(actionEvent -> {
            if (cmbQuestionsNums2.getValue() == null) {
                showPopUpMessage("You must choose a question number.");
                return;
            }
            if (tfEnterUpdatedAnswer.getText().equals("")) {
                showPopUpMessage("Text cannot be empty. Try again.");
            }
            for (SystemUIEventListener l : listeners) {
                l.updateOpenQuestionAnswerToModel(cmbQuestionsNums2.getValue(), tfEnterUpdatedAnswer.getText());
                tfEnterUpdatedAnswer.clear();
            }

        });
        vbUpdateOpenQuestionAnswer.setPadding(new Insets(10));
        vbUpdateOpenQuestionAnswer.setSpacing(10);
        vbUpdateOpenQuestionAnswer.getChildren().addAll(lblUpdatedAnswer, tfEnterUpdatedAnswer, btnSubmitUpdatedAnswer);

        vbUpdateMultiChoiceAnswer = new VBox();
        vbUpdateMultiChoiceAnswer.setVisible(false);
        Label lblPickAnswer = new Label("Enter answer number:");
        Label lblEnterText = new Label("Enter the updated text:");
        TextField tfUpdatedText = new TextField();
        Button btnSubmitAnswer = new Button("Submit answer");
        vbUpdateMultiChoiceAnswer.setPadding(new Insets(10));
        vbUpdateMultiChoiceAnswer.setSpacing(10);
        vbUpdateMultiChoiceAnswer.getChildren().addAll(lblPickAnswer, cmbAnswersNums1, lblEnterText, tfUpdatedText, btnSubmitAnswer);
        btnSubmitAnswer.setOnAction(actionEvent -> {
            if (cmbAnswersNums1.getValue() == null) {
                showPopUpMessage("Must pick an answer number.");
                return;
            }
            if (tfUpdatedText.getText().equals("")) {
                showPopUpMessage("Updated answer cannot be empty.");
                return;
            }
            for (SystemUIEventListener l : listeners) {
                l.updateMultiChoiceAnswerToModel(cmbQuestionsNums2.getValue(), cmbAnswersNums1.getValue(), tfUpdatedText.getText());
            }
            tfUpdatedText.clear();

        });
        Button btnReturnToMenu3 = new Button("Return to menu");

        btnReturnToMenu3.setOnAction(actionEvent -> {

                cmbAnswersNums1.getSelectionModel().clearSelection();
                cmbQuestionsNums2.getSelectionModel().clearSelection();

            stgDisplayQuestion.close();
            theStage.setScene(menuScene);
        });
        vbUpdateAnswer.setPadding(new Insets(10));
        vbUpdateAnswer.setSpacing(10);
        vbUpdateAnswer.getChildren().addAll(hbQuestionNum, vbUpdateOpenQuestionAnswer, vbUpdateMultiChoiceAnswer, btnReturnToMenu3);
        updateAnswerScene = new Scene(vbUpdateAnswer, 400, 500);


        VBox vbDeleteAnswer = new VBox();
        Label lblChooseAnsNumber = new Label("Choose question number:");
        cmbQuestionsNums3.setOnAction(actionEvent -> {
            cmbAnswersNums2.getItems().clear();
            if (cmbQuestionsNums3.getValue() == null) {
                return;
            }
            for (SystemUIEventListener l : listeners) {
                l.updateNumberOfAnswersToModel(cmbQuestionsNums3.getValue());
            }
        });
        Label lblChooseAnsToDelete = new Label("Choose answer to delete:");
        Button btnClickToDelete = new Button("Delete Answer");
        btnClickToDelete.setOnAction(actionEvent -> {

            if (cmbQuestionsNums3.getValue() == null || cmbAnswersNums2.getValue() == null) {
                return;
            }
            for (SystemUIEventListener l : listeners) {
                l.deleteAnswerToModel(cmbQuestionsNums3.getValue(), cmbAnswersNums2.getValue());
            }
            cmbAnswersNums2.getItems().clear();
            for (SystemUIEventListener l : listeners) {
                l.updateNumberOfAnswersToModel(cmbQuestionsNums3.getValue());
            }
        });
        Button btnReturnToMenu4 = new Button("Return to menu");
        btnReturnToMenu4.setOnAction(actionEvent -> {
            cmbQuestionsNums3.getSelectionModel().clearSelection();
            cmbAnswersNums2.getSelectionModel().clearSelection();
            stgDisplayQuestion.close();
            theStage.setScene(menuScene);
        });
        vbDeleteAnswer.getChildren().addAll(lblChooseAnsNumber, cmbQuestionsNums3, lblChooseAnsToDelete, cmbAnswersNums2, btnClickToDelete, btnReturnToMenu4);
        vbDeleteAnswer.setPadding(new Insets(10));
        vbDeleteAnswer.setSpacing(10);
        deleteAnswerScene = new Scene(vbDeleteAnswer, 400, 500);


        VBox vbGenerateExam = new VBox();
        Label lblNumOfQuestionsToGenerate = new Label("Choose number of questions:");
        Button btnClickToGenerate = new Button("Click to generate exam");
        btnClickToGenerate.setOnAction(actionEvent -> {
            if (cmbQuestionsNums4.getValue() == null) {
                showPopUpMessage("You must choose number of questions.");
                return;
            }
            for (SystemUIEventListener l : listeners) {
                l.generateAutomaticExamToModel(cmbQuestionsNums4.getValue());
            }
        });
        Button btnReturnToMenu6 = new Button("Return to menu");
        btnReturnToMenu6.setOnAction(actionEvent -> {
            theStage.setScene(menuScene);
        });
        vbGenerateExam.setPadding(new Insets(10));
        vbGenerateExam.setSpacing(10);
        vbGenerateExam.getChildren().addAll(lblNumOfQuestionsToGenerate, cmbQuestionsNums4, btnClickToGenerate, btnReturnToMenu6);
        generateExamScene = new Scene(vbGenerateExam, 400, 500);



        //manual exam libi

        VBox vbManualExam = new VBox();
        Button btnNumOfQuestionsManual = new Button("Choose number of questions");
        VBox vbQuestionsForManualExam = new VBox();
        Label lblNumOfQuestionToManual = new Label("Choose the question ID you want in the Exam: ");
        Button btnChooseQuestionNum = new Button("Choose question");
        Button btnCreateManualExam = new Button("Create Exam");
        Label lblChosenQuestions = new Label();
        Label lblMultiChoiceQuestion = new Label();
        Button btnReturnToMenu7 = new Button("Return to menu");
        Button btnChooseTheAnswers = new Button("Choose this answers");
        Button btnShowManualExam = new Button("Show final Exam");
        vbManualExam.setPadding(new Insets(10));
        vbManualExam.setSpacing(10);
        vbQuestionsForManualExam.setPadding(new Insets(10));
        vbQuestionsForManualExam.setSpacing(10);
        vbQuestionsForManualExam.setVisible(false);
        ArrayList<Integer> manualQuestionsArray = new ArrayList();


        btnReturnToMenu7.setOnAction(actionEvent -> {
            manualQuestionsArray.removeAll(manualQuestionsArray);
            cmbQuestionsNums6.getSelectionModel().clearSelection();
            cmbQuestionsNums5.getSelectionModel().clearSelection();
                    stgDisplayQuestion.close();
                    theStage.setScene(menuScene);
                });
        vbManualMultiChoice = new VBox();
        vbManualMultiChoice.setVisible(false);
        vbManualMultiChoice.setPadding(new Insets(10));
        vbManualMultiChoice.setSpacing(10);


        btnNumOfQuestionsManual.setOnAction(actionEvent -> {
            if (cmbQuestionsNums5.getValue() == null) {
                showPopUpMessage("You must choose number of questions.");
                return;
            }
            vbQuestionsForManualExam.setVisible(true);
        });
        btnChooseQuestionNum.setOnAction(actionEvent1 -> {
                if (cmbQuestionsNums6.getValue() == null) {
                    showPopUpMessage("You must choose a question.");
                    return;
                }
                for (int j = 0; j < manualQuestionsArray.size(); j++) {
                    if (manualQuestionsArray.get(j).equals(cmbQuestionsNums6.getValue())) {
                        showPopUpMessage("the question is already in the Exam, choose another.");
                        return;
                    }
                }
                manualQuestionsArray.add(cmbQuestionsNums6.getValue());
                lblChosenQuestions.setText("the questions you have already chosen: \n" + manualQuestionsArray);
                return;
            });
                btnCreateManualExam.setOnAction(actionEvent2 -> {
                    if (cmbQuestionsNums5.getValue() > manualQuestionsArray.size()) {
                        showPopUpMessage("you must choose "+ cmbQuestionsNums5.getValue()+" questions to continue");
                        return;
                    }
                        for (SystemUIEventListener l : listeners) {
                            l.generateManualExamToModel(manualQuestionsArray, cmbQuestionsNums5.getValue());
                        }
                ArrayList <Integer> multiQuestionsInExam = new ArrayList<>();
                for(int i=0;i<manualQuestionsArray.size();i++){
                    for(SystemUIEventListener l : listeners){
                        if(l.checkIfMultiChoiceQuestionExamToModel(manualQuestionsArray.get(i))){
                            multiQuestionsInExam.add(manualQuestionsArray.get(i));
                        }
                    }
                }
                if(multiQuestionsInExam.size()>0) {
                            for (int i = 0; i < multiQuestionsInExam.size(); i++) {
                                lblMultiChoiceQuestion.setText("plesae choose answers for question number " + multiQuestionsInExam.get(i) + ": \n");
                                vbManualMultiChoice.getChildren().add(0, lblMultiChoiceQuestion);
                                ArrayList<CheckBox> answers = new ArrayList<>();
                                        for (int j = 0; j < getNumOfAnswers(multiQuestionsInExam.get(i)); j++) {
                                            answers.add(new CheckBox(getAnswersText(multiQuestionsInExam.get(i), j)));
                                            vbManualMultiChoice.getChildren().add(j + 1, answers.get(j));
                                        }
                                        vbManualMultiChoice.getChildren().add(btnChooseTheAnswers);
                                        vbManualMultiChoice.setVisible(true);
                                        int finalI = i;
                                btnChooseTheAnswers.setOnAction(actionEvent3 -> {
                                            ArrayList<Integer> chosenAnswers = new ArrayList<>();
                                            for (int j = 0; j < answers.size(); j++) {
                                                if (answers.get(j).isSelected()) {
                                                    chosenAnswers.add(j + 1);
                                                }
                                            }
                                            if (chosenAnswers.size() < 2) {
                                                showPopUpMessage("you must select at least 2 answers or more");
                                                return;
                                            }
                                                for (SystemUIEventListener l : listeners) {
                                                    l.manualExamMultiChoiceAnswersToModel(multiQuestionsInExam.get(finalI), chosenAnswers);
                                                    vbManualMultiChoice.getChildren().clear();
                                                }
                                        });

                                    } vbManualMultiChoice.setVisible(false);
                    vbManualExam.getChildren().add(4,btnShowManualExam);
                        }

                });
        btnShowManualExam.setOnAction(actionEvent -> {
            manualQuestionsArray.removeAll(manualQuestionsArray);
            cmbQuestionsNums6.getSelectionModel().clearSelection();
            cmbQuestionsNums5.getSelectionModel().clearSelection();
        for (SystemUIEventListener l : listeners){
                    try {
                        l.printManualExam();
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                } theStage.setScene(menuScene);
    });


        vbQuestionsForManualExam.getChildren().addAll(lblNumOfQuestionToManual, cmbQuestionsNums6, btnChooseQuestionNum, lblChosenQuestions,btnCreateManualExam);
        vbManualExam.getChildren().addAll( cmbQuestionsNums5, btnNumOfQuestionsManual, vbQuestionsForManualExam, vbManualMultiChoice,btnReturnToMenu7);
        manualExamScene = new Scene(vbManualExam, 500, 500);



        VBox vbImage = new VBox();
        InputStream stream = new FileInputStream("src/main/java/View/logo.png");
        Image img = new Image(stream);
        ImageView imageView = new ImageView(img);
        Label lblTitle = new Label("Exam Generator");
        //lblTitle.setFont(new Font("Arial", 16));
        lblTitle.setFont(Font.font("Verdana", FontWeight.BOLD, 16));
        lblTitle.setTextFill(Color.BLUEVIOLET);
        imageView.setFitHeight(100);
        imageView.setFitWidth(100);
        vbImage.getChildren().addAll( imageView,lblTitle);
        vbImage.setAlignment(Pos.CENTER);

        VBox vbMenuButtons = new VBox();
        vbMenuButtons.setAlignment(Pos.CENTER);
        vbMenuButtons.setSpacing(5);
        vbMenuButtons.setPadding(new Insets(10));
        vbMenuButtons.getChildren().addAll(btnDisplayQuestions, btnAddQuestion, btnUpdateQuestion, btnUpdateAnswer, btnDeleteAnswer, btnManualExam, btnGenerateExam, btnCopyExam,btnSaveAndExit);

        vbMenu.getChildren().addAll(vbImage,vbMenuButtons);
        vbMenu.setSpacing(5);
        vbMenu.setPadding(new Insets(10));
        menuScene = new Scene(vbMenu, 400, 500);
        theStage.setScene(menuScene);
        theStage.show();


    }

    public String getAnswersText(int serial,int answerIndex) {
       String answerText = null;
        for (SystemUIEventListener l : listeners){
            answerText = l.getAnswerTextFromView(serial,answerIndex);
        } return answerText;
    }


    @Override
    public void registerListener(SystemUIEventListener listener) {
        listeners.add(listener);
    }


    @Override
    public void displayQuestions(String s) {
        stgDisplayQuestion.setTitle("Questions list");
        stgDisplayQuestion.setX(150);
        stgDisplayQuestion.setY(100);
        ScrollPane sp = new ScrollPane(new Label(s));
        stgDisplayQuestion.setScene(new Scene(sp, 282, 250));
        stgDisplayQuestion.show();

    }

    @Override
    public void showPopUpMessage(String msg) {
        JOptionPane.showMessageDialog(null, msg);

    }

    @Override
    public void isMultiChoiceQuestion(boolean isMultiChoice) {
        if (isMultiChoice) {
            vbUpdateMultiChoiceAnswer.setManaged(true);
           vbUpdateMultiChoiceAnswer.setVisible(true);
            vbUpdateOpenQuestionAnswer.setManaged(false);
            vbUpdateOpenQuestionAnswer.setVisible(false);
        } else {
            vbUpdateOpenQuestionAnswer.setManaged(true);
           vbUpdateOpenQuestionAnswer.setVisible(true);
            vbUpdateMultiChoiceAnswer.setManaged(false);
            vbUpdateMultiChoiceAnswer.setVisible(false);
        }

    }

    @Override
    public void updateNumOfQuestionsToComboBox(int numOfQuestions) {
        cmbQuestionsNums1.getItems().add(numOfQuestions);
        cmbQuestionsNums2.getItems().add(numOfQuestions);
        cmbQuestionsNums3.getItems().add(numOfQuestions);
        cmbQuestionsNums4.getItems().add(numOfQuestions);
        cmbQuestionsNums5.getItems().add(numOfQuestions);
        cmbQuestionsNums6.getItems().add(numOfQuestions);

    }

    @Override
    public void updateNumOfAnswersToComboBox(int numOfAnswers) {
        for (int i = 1; i <= numOfAnswers; i++) {
            cmbAnswersNums1.getItems().add(i);
            cmbAnswersNums2.getItems().add(i);
            cmbAnswersNums3.getItems().add(i);
        }
    }

    @Override
    public void displayGeneratedExam(String examToString) {
        stgDisplayGeneratedExam.setTitle("Exam");
        ScrollPane sp = new ScrollPane(new Label(examToString));
        stgDisplayGeneratedExam.setScene(new Scene(sp, 300, 250));
        stgDisplayGeneratedExam.show();
    }

    @Override
    public void updateStartNumOfQuestionsToCmb(int size) {
        for (int i = 1; i <= size; i++) {
            cmbQuestionsNums1.getItems().add(i);
            cmbQuestionsNums2.getItems().add(i);
            cmbQuestionsNums3.getItems().add(i);
            cmbQuestionsNums4.getItems().add(i);
            cmbQuestionsNums5.getItems().add(i);
            cmbQuestionsNums6.getItems().add(i);
        }
    }

    @Override
    public int getNumOfAnswers(int serial) {
        int numOfAnswersView=0 ;
       for (SystemUIEventListener l : listeners){
            numOfAnswersView = l.getAnswersSizeToModel(serial);
       } return numOfAnswersView;
    }

}
