module PartTwoFinalProject{

    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;

    opens dao;
    opens dao.factories;
    opens dao.abstractClasses;
    opens dao.predatorsAnimalClasses;
    opens dao.herbivoresAnimalsClasses;
    opens dao.allInterface;
    opens dao.plantClasses;
}