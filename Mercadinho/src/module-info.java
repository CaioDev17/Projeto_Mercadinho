module Mercadinho {
	requires javafx.controls;
	requires java.sql;
	requires javafx.fxml;
	requires javafx.graphics;
	requires javafx.base;
	requires org.controlsfx.controls;
	
	opens application to javafx.graphics, javafx.fxml;
	opens controller to javafx.graphics, javafx.fxml;
	opens model to javafx.graphics, javafx.fxml,javafx.base;
}
