package Listeners;

public interface SystemEventListener {
    void displayQuestionToView(String s);
    void addQuestionToView();
    void questionAlreadyExistsToView();
    void updateQuestionToView();
    void checkIfMultiChoiceQuestionToView(boolean isMultiChoice);
    void updateOpenQuestionAnswerToView();
    void updateNumOfQuestionsToView(int numOfQuestions);
    void updateNumOfAnswersToView(int numOfAnswers);
    void invalidQuestionNumberToView();
    void invalidAnswerNumberToView();
    void updateMultiChoiceAnswerToView();
    void deleteAnswerToView();
    void cantDeleteAnswerToView();
    void generateAutomaticExamToView(String examToString);
    void copiedExamToView(String s);
    void updateStartNumOfQuestionsToView(int size);

}
