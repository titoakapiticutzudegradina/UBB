module GUI {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    //requires org.junit.jupiter.api;
    //requires org.xerial.sqlitejdbc;
    requires org.junit.jupiter.api;

    opens GUI to javafx.fxml;
    //opens Main to javafx.fxml;
    exports GUI;
}