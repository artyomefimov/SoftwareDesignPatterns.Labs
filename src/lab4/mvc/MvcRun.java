package lab4.mvc;

import lab4.mvc.view.MainView;

import javax.swing.*;

public class MvcRun {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainView::new);
    }
}
