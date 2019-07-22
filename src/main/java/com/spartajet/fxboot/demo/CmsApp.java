package com.spartajet.fxboot.demo;
import java.io.IOException;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.spartajet.fxboot.demo.controller.PersonEditDialogController;
import com.spartajet.fxboot.demo.controller.PersonOverviewController;
import com.spartajet.fxboot.demo.controller.TabController;
//import com.spartajet.fxboot.demo.controller.PersonOverviewController;
import com.spartajet.fxboot.demo.mode.Person;
import com.spartajet.fxboot.demo.view.BdemoView;

import de.felixroske.jfxsupport.AbstractJavaFxApplicationSupport;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
@EnableScheduling
@SpringBootApplication
@ComponentScan(basePackages = {"com.spartajet.fxboot.demo.*"})
@MapperScan("com.spartajet.fxboot.demo.dao")
public class CmsApp extends AbstractJavaFxApplicationSupport {

    private Stage primaryStage;
    private BorderPane rootLayout;
    @Override
    public void start(Stage primaryStage) throws Exception {
    	//super.start(primaryStage);
    	this.primaryStage = primaryStage;
        this.primaryStage.setTitle("AddressApp");
        this.primaryStage.getIcons().add(new Image("/image/icon.jpg"));
        initRootLayout();

        showJframeView();
    }

    /**
     * Initializes the root layout.
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(CmsApp.class.getResource("/view/Bdemo.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Shows the person overview inside the root layout.
     */
    public void showJframeView() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(CmsApp.class.getResource("/view/Jframe.fxml"));
            AnchorPane Jframe = (AnchorPane) loader.load();
            // Set person overview into the center of root layout.
            rootLayout.setCenter(Jframe);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Returns the main stage.
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
    	SpringApplication.run(CmsApp.class, args);
        launchApp(CmsApp.class,BdemoView.class,args);
    }
}
