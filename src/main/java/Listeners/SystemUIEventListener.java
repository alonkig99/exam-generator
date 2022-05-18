package Listeners;

import java.util.ArrayList;

public interface SystemUIEventListener {
    void displayQuestionToModel();
    void addOpenQuestionToModel(String text, String answer);
    void addMultiChoiceQuestionToModel();
    void updateQuestionToModel();
    void updateAnswerToModel();
    void deleteAnswerToModel();
    void generateManualExamToModel();
    void generateAutomaticExamToModel();
    void copyLastExam();

}
