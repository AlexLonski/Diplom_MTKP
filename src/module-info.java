module DeparHum {
	requires javafx.controls;
	requires javafx.fxml;
	requires java.sql;
	requires javafx.base;
	requires javafx.graphics;
	requires jdk.compiler;
	requires java.desktop;
	requires jdk.javadoc;
	requires org.apache.poi.ooxml;
	requires org.apache.poi.ooxml.schemas;
	requires org.apache.commons.io;
	
	exports application;
	opens application to javafx.graphics, javafx.fxml;
}
