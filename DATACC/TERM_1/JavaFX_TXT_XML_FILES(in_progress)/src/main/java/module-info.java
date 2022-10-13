module javafx11.multipantalla {


    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires MaterialFX;

    requires lombok;
    requires org.apache.logging.log4j;

    requires jakarta.inject;
    requires jakarta.cdi;
    requires com.google.gson;
    requires org.junit.jupiter.api;
    requires io.vavr;

    exports ui.main to javafx.graphics;
    exports ui.screens.principal;
    exports common.config;
    exports ui.screens.common;
    exports domain.modelo;
    exports domain.services;
    exports common;
    exports dao.impl;
    exports ui.screens.appStart;
    exports ui.screens.newspapersDelete;
    exports ui.screens.newspapersUpdate;
    exports ui.screens.newspapersList;
    exports ui.screens.articlesList;
    exports ui.screens.articlesDelete;
    exports di;

    opens di;
    opens dao.impl;
    opens ui.screens.principal;
    opens ui.screens.appStart;
    opens ui.screens.newspapersList;
    opens ui.screens.newspapersDelete;
    opens ui.screens.newspapersUpdate;
    opens ui.screens.articlesList;
    opens ui.screens.articlesDelete;
    opens common.config;
    opens domain.modelo;
    opens domain.services;
    opens ui.main;
    opens config;
    opens css;
    opens fxml;

}
