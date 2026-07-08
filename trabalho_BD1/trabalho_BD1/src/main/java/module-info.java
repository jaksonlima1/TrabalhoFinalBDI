module org.example.trabalho_bd1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;//Permite que o projeto use conexões SQL


    // Permite que o JavaFX acesse e desenhe as telas da pasta Visual
    opens App.Visual to javafx.graphics, javafx.fxml;
    exports App.Visual;
    // Permite que o JavaFX e o Driver JDBC leiam os seus Modelos de dados
    opens App.Model to javafx.base;
    exports App.Model;
}
