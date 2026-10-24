module net.vinograd.generativeflowfield {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires static lombok;


    opens net.vinograd.generativeflowfield to javafx.fxml;
    exports net.vinograd.generativeflowfield;
    exports net.vinograd.generativeflowfield.presentation;
    opens net.vinograd.generativeflowfield.presentation to javafx.fxml;
}