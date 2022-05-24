package View;

import Listeners.SystemEventListener;
import Listeners.SystemUIEventListener;

public interface AbstractSystemView {
    void registerListener(SystemUIEventListener listener);
    void displayQuestions(String s);
    void showPopUpMessage(String msg);
    void isMultiChoiceQuestion(boolean isMultiChoice);
    void updateNumOfQuestionsToComboBox(int numOfQuestions);
    void updateNumOfAnswersToComboBox(int numOfAnswers);
    void displayGeneratedExam(String examToString);
    void updateStartNumOfQuestionsToCmb(int size);
    int getNumOfAnswers(int serial);
}
