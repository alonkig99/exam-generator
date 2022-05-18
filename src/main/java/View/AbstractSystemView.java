package View;

import Listeners.SystemEventListener;
import Listeners.SystemUIEventListener;

public interface AbstractSystemView {
    void registerListener(SystemUIEventListener listener);
    void displayQuestions(String s);
    void successfulMessage(String msg);
    void failedMessage(String msg);
  void questionAlreadyExistsMessage(String msg);



}
