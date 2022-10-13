module harryPotterApp {


    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires MaterialFX;
    requires lombok;
    requires org.apache.logging.log4j;
    requires jakarta.inject;
    requires jakarta.cdi;
    requires com.squareup.moshi;
    requires retrofit2;
    requires retrofit2.converter.moshi;
    requires okhttp3;
    requires io.vavr;
    requires retrofit2.adapter.rxjava3;
    requires io.reactivex.rxjava3;
    requires org.pdfsam.rxjavafx;

    exports dao.modelo;
    exports ui.main to javafx.graphics;
    exports ui.screens.principal;
    exports common.config;
    exports ui.screens.common;
    exports common;
    exports dao;
    exports ui.screens.start;
    exports ui.screens.choice;
    exports domain.servicios;
    exports ui.screens.students;
    exports domain.modelo;
    exports dao.retrofit;
    exports ui.screens.spells;
    exports ui.screens.staff;
    exports ui.screens.houses;

    opens dao.modelo;
    opens dao;
    opens ui.screens.principal;
    opens ui.screens.choice;
    opens ui.screens.start;
    opens ui.screens.spells;
    opens ui.screens.students;
    opens ui.screens.houses;
    opens common.config;
    opens domain.modelo;
    opens domain.servicios;
    opens ui.main;
    opens config;
    opens css;
    opens fxml;
    opens ui.screens.staff;
}
