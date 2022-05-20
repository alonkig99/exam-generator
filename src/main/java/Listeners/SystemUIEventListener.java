package Listeners;


import java.util.LinkedHashSet;
import java.util.List;

public interface SystemUIEventListener {
    void displayQuestionToModel();
    void addOpenQuestionToModel(String text, String answer);
    void addMultiChoiceQuestionToModel(String text, LinkedHashSet<String> answersList, List<Boolean>booleanValues);
    void updateQuestionToModel(int questionNum, String text);
    void checkIfMultiChoiceQuestionToModel(int num);
    void updateOpenQuestionAnswerToModel(String num, String text);
    void updateMultiChoiceAnswerToModel(String questionNum, String answerNum, String questionText);
    void deleteAnswerToModel();
    void generateManualExamToModel();
    void generateAutomaticExamToModel();
    void copyLastExam();

}
