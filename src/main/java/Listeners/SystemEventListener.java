package Listeners;

public interface SystemEventListener {
    void displayQuestionToView(String s);
    void addOpenQuestionToView();
    void addMultiChoiceQuestionToView();
    void updateQuestionToView();
    void updateAnswerToView();
    void deleteAnswerToView();
    void generateManualExamToView();
    void generateAutomaticExamToView();


}
