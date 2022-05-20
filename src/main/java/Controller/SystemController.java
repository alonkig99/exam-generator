package Controller;

import Listeners.SystemEventListener;
import Listeners.SystemUIEventListener;
import View.AbstractSystemView;
import model.Manager;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class SystemController implements SystemUIEventListener, SystemEventListener {
    private Manager model;
    private AbstractSystemView view;


    public SystemController(Manager model, AbstractSystemView view) {
        this.model = model;
        this.view = view;

        this.model.registerListeners(this);
        this.view.registerListener(this);


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
    public void invalidQuestionNumberToView() {
        view.showPopUpMessage("You must enter a valid question number.");
    }

    @Override
    public void updateMultiChoiceAnswerToView() {

    }



    @Override
    public void deleteAnswerToView() {

    }

    @Override
    public void generateManualExamToView() {

    }

    @Override
    public void generateAutomaticExamToView() {

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
        if(model.addMultiQuestion(text)){
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
    public void updateOpenQuestionAnswerToModel(String num, String text) {
        model.updateOpenQuestionAnswer(Integer.parseInt(num),text);

    }

    @Override
    public void updateMultiChoiceAnswerToModel(String questionNum, String answerNum, String questionText) {
        int answerSerial = stringToInteger(answerNum);
        if (answerSerial < 1 || answerSerial > model.getNumOfAnswers(Integer.parseInt(questionNum))){
            view.showPopUpMessage("Enter a valid answer number.");
            return;
        }
      model.updateMultiChoiceAnswer(Integer.parseInt(questionNum),Integer.parseInt(answerNum),questionText);

    }


    @Override
    public void deleteAnswerToModel() {

    }

    @Override
    public void generateManualExamToModel() {

    }

    @Override
    public void generateAutomaticExamToModel() {

    }

    @Override
    public void copyLastExam() {

    }


}
