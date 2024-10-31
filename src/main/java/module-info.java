module top.cubik65536.beeperfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens top.cubik65536.beeperfx to javafx.fxml;
    exports top.cubik65536.beeperfx;
    exports top.cubik65536.beeperfx.controllers;
    opens top.cubik65536.beeperfx.controllers to javafx.fxml;
    opens top.cubik65536.beeperfx.model to javafx.base;
}
