package Listeners;


import java.util.LinkedHashSet;
import java.util.List;

public interface SystemUIEventListener {
    void displayQuestionToModel();
    void addOpenQuestionToModel(String text, String answer);
    void addMultiChoiceQuestionToModel(String text, LinkedHashSet<String> answersList, List<Boolean>booleanValues);
    void updateQuestionToModel(int questionNum, String text);
    void checkIfMultiChoiceQuestionToModel(int num);
    void updateOpenQuestionAnswerToModel(int num, String text);
    void updateMultiChoiceAnswerToModel(int questionNum, int answerNum, String questionText);
    void updateNumberOfAnswersToModel(int serial);
    void deleteAnswerToModel();
    void generateManualExamToModel();
    void generateAutomaticExamToModel();
    void copyLastExam();

}
