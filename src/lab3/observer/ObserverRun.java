package lab3.observer;

import javax.swing.*;

public class ObserverRun {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainView::new);
    }
}
