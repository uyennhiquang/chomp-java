module chompJava {
    requires transitive javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.media;

    opens chompJava to javafx.fxml;
    exports chompJava;
}
