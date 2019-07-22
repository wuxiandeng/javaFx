package com.spartajet.fxboot.demo.controller;


import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class TabController {
    @FXML
    private TreeView<String> tree;
    @FXML
    private void initialize() {
    	loadTreeItems();
    }
    public void loadTreeItems() {
    	Node rootIcon = new ImageView(
    			new Image(getClass().getResourceAsStream("/image/tree.bmp"))
    	    );
    	TreeItem<String> parent = new TreeItem<String>();
        TreeItem<String> root = new TreeItem<String>("补货管理",rootIcon);
        root.setExpanded(true);
        root.getChildren().add(new TreeItem<String>("补货"));
        parent.getChildren().add(root);root = new TreeItem<String>("补货管理");
        root.setExpanded(false);
        root.getChildren().add(new TreeItem<String>("补货"));
        parent.getChildren().add(root);root = new TreeItem<String>("补货管理");
        root.setExpanded(false);
        root.getChildren().add(new TreeItem<String>("补货"));
        parent.getChildren().add(root);
        tree.setRoot(parent);
        tree.setShowRoot(false);
      }
}
