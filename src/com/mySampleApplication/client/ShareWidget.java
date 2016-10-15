package com.mySampleApplication.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptException;
import com.google.gwt.dom.client.Style.Float;
import com.google.gwt.logging.client.SystemLogHandler;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.resources.client.ClientBundle.Source;
import com.google.gwt.user.client.ui.*;
import sun.rmi.runtime.Log;

import static sun.rmi.runtime.Log.*;

/**
 * Created by Volodymyr Oleshkevych on 10/9/2016.
 */
public class ShareWidget{


    private FlowPanel upgradePanel = new FlowPanel();
    private Label upgradeLabel = new Label("Upgrade");

    private FlowPanel sharingEnvironmentsPanel = new FlowPanel();

    private Label descriptionLabel = new Label("Push sharing");
    private Panel emailPanel = new FlowPanel();
    private Panel facebookPanel = new FlowPanel();
    private Panel slackPanel = new FlowPanel();
//    private Label shareLabel = new Label("Share now");
//    private Label previewLabel = new Label("Preview");
    private Label settingsLabel = new Label("Settings");

    private Panel repeatPanel = new FlowPanel();

    private Label shareLinkLabel = new Label("Share link");

    private FlowPanel membersPanel = new FlowPanel();

    private SimplePanel view = new SimplePanel();

    private Panel mainContainer = new FlowPanel();

    
    interface Images extends ClientBundle {
		public static final Images INSTANCE = GWT.create(Images.class);

		@Source("email.png")
		ImageResource email();

		@Source("facebook.png")
		ImageResource facebook();

		@Source("slack.png")
		ImageResource slack();
	}
    public SimplePanel getView() {
        return view;
    }

    public ShareWidget() {
        init();
    }

    private void init() {
        initUpgrade(5, 10);
//        try {
            initShareEnvironments();
//        }catch(JavaScriptException j){
//            System.out.print(j.getMessage());
//
//        }
        initRepeatPanel();
        shareLinkLabel.setStylePrimaryName("actigate-share-panel-title-label");
        mainContainer.add(upgradePanel);
        mainContainer.add(sharingEnvironmentsPanel);
        mainContainer.add(repeatPanel);
        mainContainer.add(shareLinkLabel);
        mainContainer.add(initMembersPanel());
        view.setWidget(mainContainer);
        view.setStylePrimaryName("actigate-share-panel");

    }

    private void initUpgrade(int completeShares, int maxShares){
        String informString = "Using " + completeShares + " of " + maxShares + " shares";
        Label label = new  Label(informString);
        label.setStyleName("actigate-share-panel-upgrade-label-inform");
        upgradePanel.add(label);
        upgradeLabel.setStyleName("actigate-share-panel-upgrade-label-upgrade");
        upgradePanel.add(upgradeLabel);
        upgradePanel.setStylePrimaryName("actigate-share-panel-upgrade");

    }

    private void initShareEnvironments()throws NullPointerException, JavaScriptException{
        descriptionLabel.setStylePrimaryName("actigate-share-panel-title-label");
//        previewLabel.setStylePrimaryName("actigate-share-panel-environments-right-labels");
//        shareLabel.getElement().getStyle().setFloat(Float.RIGHT);
//        previewLabel.getElement(*/).getStyle().setFloat(Float.RIGHT);
        Image emailImage = new Image(Images.INSTANCE.email());
        emailImage.setSize("20px", "15px");
        emailPanel.add(sharingCategories(emailImage, "Place for group", true));
        emailPanel.setStylePrimaryName("actigate-share-panel-environments-panel");

        Image facebookImage = new Image(Images.INSTANCE.facebook());
        facebookImage.setSize("20px", "20px");
        facebookPanel.add(sharingCategories(facebookImage, "Place for group", false));
        facebookPanel.setStylePrimaryName("actigate-share-panel-environments-panel");

        Image slackImage = new Image(Images.INSTANCE.slack());
        slackImage.setSize("20px", "20px");
        slackPanel.add(sharingCategories(slackImage, "Place for group", false));
        slackPanel.setStylePrimaryName("actigate-share-panel-environments-panel");

        settingsLabel.setStylePrimaryName("actigate-share-panel-sharing-environments-panel-label-settings");

        sharingEnvironmentsPanel.add(descriptionLabel);
        sharingEnvironmentsPanel.add(emailPanel);
        sharingEnvironmentsPanel.add(facebookPanel);
        sharingEnvironmentsPanel.add(slackPanel);
        sharingEnvironmentsPanel.add(settingsLabel);

    }

    private Panel sharingCategories(Image image, String groupName, boolean preview) throws  JavaScriptException{
        CheckBox checkBox = new CheckBox();
        Label groupNameLabel = new Label(groupName);
        Label previewLabel = new Label("Preview");
        Label shareLabel = new Label("Share now");
        Panel panel = new FlowPanel();
        image.setStylePrimaryName("actigate-share-panel-environments-table-images");
        groupNameLabel.setStylePrimaryName("actigate-share-panel-environments-table-images");
        panel.add(checkBox);
        panel.add(image);
        panel.add(groupNameLabel);
        FlowPanel labelsPanel = new FlowPanel();
        if(preview){
            previewLabel.setStylePrimaryName("actigate-share-panel-environments-right-labels");
            labelsPanel.add(previewLabel);
        }
        shareLabel.setStylePrimaryName("actigate-share-panel-environments-right-labels");
        labelsPanel.add(shareLabel);
        labelsPanel.setStylePrimaryName("actigate-share-panel-environments-right-panel");
        labelsPanel.addStyleName("actigate-share-panel-inline-block");
        panel.add(labelsPanel);
//        initWidget(panel);
        return panel;
    }

    private void initRepeatPanel(){
        CheckBox repeatCheckBox = new CheckBox("Repeat");
        repeatCheckBox.setSize("15px", "15px");
        repeatPanel.add(repeatCheckBox);
        repeatPanel.setStylePrimaryName("actigate-share-panel-repeat-panel");
        ListBox repeatListBox = new ListBox();
        repeatListBox.setStylePrimaryName("form-control");
        repeatListBox.addItem("Daily");
        repeatListBox.addItem("Monthly");
        repeatListBox.addItem("First day of month");
        repeatListBox.setVisibleItemCount(1);
        repeatListBox.addStyleName("actigate-share-panel-repeat-list");
        repeatPanel.add(repeatListBox);
    }

    private Widget initMembersPanel(){
        Panel formPanel = new FlowPanel();
        formPanel.setStylePrimaryName("form-horizontal");

        Panel textBoxPanel = new FlowPanel();
        textBoxPanel.setStylePrimaryName("form-group");
        TextBox textBox = new TextBox();
        textBox.setStylePrimaryName("form-control");
        Button copyBtn = new Button();
        copyBtn.setStylePrimaryName("control-label col-sm-2");
        textBoxPanel.add(textBox);
        textBoxPanel.add(copyBtn);


        formPanel.add(textBoxPanel);

        RadioButton nobodyRB = new RadioButton("sharingAddress", "Nobody");
        RadioButton groupMembersRB = new RadioButton("sharingAddress", "Group members only");
        RadioButton anyoneRB = new RadioButton("sharingAddress", "Anyone with link");
        nobodyRB.setStylePrimaryName("form-group");
        groupMembersRB.setStylePrimaryName("form-group");
        anyoneRB.setStylePrimaryName("form-group");
        formPanel.add(nobodyRB);
        formPanel.add(groupMembersRB);
        formPanel.add(anyoneRB);

        return formPanel;
    }
}
