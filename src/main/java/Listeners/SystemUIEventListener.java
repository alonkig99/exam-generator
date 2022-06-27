package Listeners;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
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
    void deleteAnswerToModel(int questionNum, int answerNum);
    void generateManualExamToModel(ArrayList<Integer> manualQuestionsArray, Integer size);
    void generateAutomaticExamToModel(int numOfQuestions);
    void copyLastExamToModel() throws CloneNotSupportedException;
    void saveBinaryFileToModel();
    int getAnswersSizeToModel(int serial);
    String getAnswerTextFromView(int answerIndex, int index);
    boolean checkIfMultiChoiceQuestionExamToModel(int serial);
    void manualExamMultiChoiceAnswersToModel(int serial,ArrayList<Integer> answersIndex);
    void printManualExam() throws FileNotFoundException;
}
