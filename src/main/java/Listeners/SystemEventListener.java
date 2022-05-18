package Listeners;

public interface SystemEventListener {
    void displayQuestionToView(String s);
    void addQuestionToView();
    void questionAlreadyExistsToView();
    void updateQuestionToView();
    void updateAnswerToView();
    void deleteAnswerToView();
    void generateManualExamToView();
    void generateAutomaticExamToView();


}
