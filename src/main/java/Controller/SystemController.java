package Controller;
import Listeners.SystemEventListener;
import Listeners.SystemUIEventListener;
import View.AbstractSystemView;
import model.Manager;
public class SystemController implements SystemUIEventListener,SystemEventListener{
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
    public void addOpenQuestionToView() {
        view.successfulMessage("Question has been added successfully.");

    }

    @Override
    public void addMultiChoiceQuestionToView() {

    }

    @Override
    public void updateQuestionToView() {

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
        try {
            model.addOpenQuestion(text, answer);
        } catch (Exception e) {
            view.failedMessage(e.getMessage());
        }
    }



    @Override
    public void addMultiChoiceQuestionToModel() {

    }



    @Override
    public void updateQuestionToModel() {

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
