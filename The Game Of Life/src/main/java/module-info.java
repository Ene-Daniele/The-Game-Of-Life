module com.example.the_game_of_life {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.the_game_of_life to javafx.fxml;
    exports com.example.the_game_of_life;
}