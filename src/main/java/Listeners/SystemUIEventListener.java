package Listeners;


import java.util.LinkedHashSet;
import java.util.List;

public interface SystemUIEventListener {
    void displayQuestionToModel();
    void addOpenQuestionToModel(String text, String answer);
    void addMultiChoiceQuestionToModel(String text, LinkedHashSet<String> answersList, List<Boolean>booleanValues);
    void updateQuestionToModel();
    void updateAnswerToModel();
    void deleteAnswerToModel();
    void generateManualExamToModel();
    void generateAutomaticExamToModel();
    void copyLastExam();

}
