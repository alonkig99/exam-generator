package Controller;

import Listeners.SystemEventListener;
import Listeners.SystemUIEventListener;
import View.AbstractSystemView;
import model.Manager;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;


public class SystemController implements SystemUIEventListener, SystemEventListener {
    private Manager model;
    private AbstractSystemView view;


    public SystemController(Manager model, AbstractSystemView view) throws IOException, ClassNotFoundException {
        this.model = model;
        this.view = view;
        this.model.registerListeners(this);
        this.view.registerListener(this);
        this.model.loadFromBinaryFile();
    }

    @Override
    public void displayQuestionToView(String s) {
        this.view.displayQuestions(s);

    }

    @Override
    public void addQuestionToView() {
        view.showPopUpMessage("Question has been added successfully.");

    }

    @Override
    public void questionAlreadyExistsToView() {
        view.showPopUpMessage("Question already exists.");
    }


    @Override
    public void updateQuestionToView() {
        view.showPopUpMessage("Question has been successfully updated.");
    }

    @Override
    public void checkIfMultiChoiceQuestionToView(boolean isMultiChoice) {
        view.isMultiChoiceQuestion(isMultiChoice);
    }

    @Override
    public void updateOpenQuestionAnswerToView() {
        view.showPopUpMessage("Answer has been successfully updated.");
    }

    @Override
    public void updateNumOfQuestionsToView(int numOfQuestions) {
        view.updateNumOfQuestionsToComboBox(numOfQuestions);
    }

    @Override
    public void updateNumOfAnswersToView(int numOfAnswers) {
        view.updateNumOfAnswersToComboBox(numOfAnswers);

    }

    @Override
    public void invalidQuestionNumberToView() {
        view.showPopUpMessage("You must enter a valid question number.");
    }

    @Override
    public void invalidAnswerNumberToView() {
        view.showPopUpMessage("You must enter a valid answer number.");
    }

    @Override
    public void updateMultiChoiceAnswerToView() {
        view.showPopUpMessage("Answer has been successfully updated.");
    }


    @Override
    public void deleteAnswerToView() {
        view.showPopUpMessage("Answer has been successfully deleted.");

    }

    @Override
    public void cantDeleteAnswerToView() {
        view.showPopUpMessage("Cannot delete because there are less than 3 answers.");

    }

    @Override
    public void generateManualExamToView() {

    }


    @Override
    public void displayQuestionToModel() {
        model.fireDisplayQuestions();
    }

    @Override
    public void addOpenQuestionToModel(String text, String answer) {

        model.addOpenQuestion(text, answer);

    }

    @Override
    public void addMultiChoiceQuestionToModel(String text, LinkedHashSet<String> questionsList, List<Boolean> booleanList) {
        if (model.addMultiQuestion(text)) {
            Iterator<String> it1 = questionsList.iterator();
            Iterator<Boolean> it2 = booleanList.iterator();
            while (it1.hasNext() && it2.hasNext()) {
                model.addAnswer(it1.next(), it2.next());
            }
        }


    }

    @Override
    public void updateQuestionToModel(int questionNum, String updatedText) {
        model.updateQuestion(questionNum, updatedText);
    }

    @Override
    public void checkIfMultiChoiceQuestionToModel(int questionNum) {
        model.isMultiChoiceQuestion(questionNum);
    }

    @Override
    public void updateOpenQuestionAnswerToModel(int num, String text) {
        model.updateOpenQuestionAnswer(num, text);
    }

    @Override
    public void updateMultiChoiceAnswerToModel(int questionNum, int answerNum, String questionText) {
        model.updateMultiChoiceAnswer(questionNum, answerNum, questionText);
    }

    @Override
    public void updateNumberOfAnswersToModel(int serial) {
        model.fireUpdateNumOfAnswersEvent(serial);
    }

    @Override
    public void deleteAnswerToModel(int questionNum, int answerNum) {

        model.deleteAnswer(questionNum, answerNum);
    }

    @Override
    public void generateManualExamToModel(ArrayList<Integer> manualQuestionsArray, Integer size) {
        model.createExam(manualQuestionsArray,size);
    }

    @Override
    public void generateAutomaticExamToModel(int numOfQuestions) {
        try {
            model.generateExam(numOfQuestions, model);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void generateAutomaticExamToView(String examToString) {
        view.displayGeneratedExam(examToString);

    }

    @Override
    public void copyLastExamToModel() throws CloneNotSupportedException {
        model.cloneExam();
    }

    @Override
    public void saveBinaryFileToModel() throws IOException {
        model.loadToBinaryFile();
    }

    @Override
    public void checkIfMultiChoiceQuestionExamToModel(Integer questionNum) {
        model.isMultiChoiceQuestionExam(questionNum);
    }

    @Override
    public void copiedExamToView(String s) {
        view.showPopUpMessage("The Exam was copied successfully! \n the copied Exam: \n" + s);

    }

    @Override
    public void updateStartNumOfQuestionsToView(int size) {
        view.updateStartNumOfQuestionsToCmb(size);
    }

    @Override
    public void checkIfMultiChoiceQuestionExamToView(boolean isMultiChoice) {
        view.isMultiChoiceQuestionExam(isMultiChoice);
    }


}
