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
    public void updateAnswerToView() {

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
        model.addMultiQuestion(text);
        Iterator<String> it1 = questionsList.iterator();
        Iterator<Boolean> it2 = booleanList.iterator();
        while (it1.hasNext() && it2.hasNext()) {
            model.addAnswer(it1.next(), it2.next());
        }


    }


    @Override
    public void updateQuestionToModel(String questionNum, String updatedText) {
        int num = 0;
        try {
            num = Integer.parseInt(questionNum);
        } catch (Exception e) {
            view.showPopUpMessage("You must enter an actual number! Try again.");
            return;
        }
        if (model.getQuestionById(num) == null) {
            view.showPopUpMessage("You must enter a valid question number! Try again.");
            return;
        }
        if (model.questionTextExists(updatedText)) {
            view.showPopUpMessage("This question text already exists.");
            return;
        }
        model.updateQuestion(num, updatedText);


    }

    @Override
    public void updateAnswerToModel() {

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
