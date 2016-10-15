package com.mySampleApplication.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptException;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.*;

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
        emailPanel.add(sharingCategories(emailImage, "(Group name)", true));
        emailPanel.setStylePrimaryName("actigate-share-panel-environments-panel");

        Image facebookImage = new Image(Images.INSTANCE.facebook());
        facebookImage.setSize("20px", "20px");
        facebookPanel.add(sharingCategories(facebookImage, "(Group name)", false));
        facebookPanel.setStylePrimaryName("actigate-share-panel-environments-panel");

        Image slackImage = new Image(Images.INSTANCE.slack());
        slackImage.setSize("20px", "20px");
        slackPanel.add(sharingCategories(slackImage, "(Group name)", false));
        slackPanel.setStylePrimaryName("actigate-share-panel-environments-panel");

        settingsLabel.setStylePrimaryName("actigate-share-panel-sharing-environments-panel-label-settings");

        sharingEnvironmentsPanel.add(descriptionLabel);
        sharingEnvironmentsPanel.add(emailPanel);
        sharingEnvironmentsPanel.add(facebookPanel);
        sharingEnvironmentsPanel.add(slackPanel);
        sharingEnvironmentsPanel.add(settingsLabel);

    }

    private Panel sharingCategories(Image image, String groupName, boolean preview){
        CheckBox checkBox = new CheckBox();
        checkBox.setStylePrimaryName("actigate-share-panel-checkbox");
        Label groupNameLabel = new Label(groupName);
        Label previewLabel = new Label("Preview");
        Label shareLabel = new Label("Share now");
        Panel panel = new FlowPanel();
        panel.setStylePrimaryName("actigate-share-panel-environments-content-panel");
//        panel.addStyleName("form-inline");
        image.setStylePrimaryName("actigate-share-panel-environments-table-images");
        groupNameLabel.setStylePrimaryName("actigate-share-panel-environments-table-images");
        panel.add(checkBox);
        panel.add(image);
        panel.add(groupNameLabel);

        Panel labelsPanel = new HorizontalPanel();
        if(preview){
            previewLabel.setStylePrimaryName("actigate-share-panel-environments-right-labels");
            labelsPanel.add(previewLabel);
        }
        shareLabel.setStylePrimaryName("actigate-share-panel-environments-right-labels");
        labelsPanel.add(shareLabel);
        labelsPanel.addStyleName("actigate-share-panel-environments-right-panel");
        panel.add(labelsPanel);
//        initWidget(panel);
        return panel;
    }

    private void initRepeatPanel(){
        CheckBox repeatCheckBox = new CheckBox("Repeat");
        repeatCheckBox.setStylePrimaryName("actigate-share-panel-checkbox");
        repeatCheckBox.addStyleName("actigate-share-panel-checkbox-repeat");
        repeatPanel.add(repeatCheckBox);
        repeatPanel.setStylePrimaryName("actigate-share-panel-repeat-panel");
        ListBox repeatListBoxFirst = new ListBox();
        repeatListBoxFirst.addItem("Daily");
        repeatListBoxFirst.setVisibleItemCount(1);
        repeatListBoxFirst.addStyleName("actigate-share-panel-repeat-list");
        repeatListBoxFirst.addStyleName("actigate-share-panel-repeat-list-hide");
        repeatListBoxFirst.setVisible(false);


        final ListBox repeatListBoxSecond = new ListBox();
        repeatListBoxSecond.addItem("Daily");
        repeatListBoxSecond.addItem("Monthly");
        repeatListBoxSecond.addItem("First day of month");
        repeatListBoxSecond.setVisibleItemCount(3);
        repeatListBoxSecond.addStyleName("actigate-share-panel-repeat-list");
        repeatListBoxSecond.setVisible(false);
        repeatCheckBox.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                if(repeatCheckBox.isChecked()){
                    repeatListBoxFirst.setVisible(true);
                }else{
                    repeatListBoxFirst.setVisible(false);
                    repeatListBoxSecond.setVisible(false);
                }
            }
        });
        repeatListBoxFirst.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                repeatListBoxSecond.setVisible(true);
            }
        });
        repeatListBoxSecond.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                repeatListBoxFirst.clear();
                repeatListBoxFirst.addItem(repeatListBoxSecond.getItemText(repeatListBoxSecond.getSelectedIndex()));
                repeatListBoxSecond.setVisible(false);
            }
        });
        repeatPanel.add(repeatListBoxFirst);
        repeatPanel.add(repeatListBoxSecond);
    }

    private Widget initMembersPanel(){
//        Panel formPanel = new FormPanel();
//        formPanel.setStylePrimaryName("form-horizontal");

        Panel panel = new FlowPanel();
        Panel textBoxPanel = new FlowPanel();
        TextBox textBox = new TextBox();
        textBox.setStylePrimaryName("actigate-share-panel-member-field");
        textBox.setEnabled(false);
        Button copyBtn = new Button("Copy");
        textBoxPanel.add(textBox);
        textBoxPanel.add(copyBtn);

        panel.add(textBoxPanel);

        RadioButton nobodyRB = new RadioButton("sharingAddress", "Nobody");
        RadioButton groupMembersRB = new RadioButton("sharingAddress", "Group members only");
        RadioButton anyoneRB = new RadioButton("sharingAddress", "Anyone with link");
        nobodyRB.setStylePrimaryName("actigate-share-panel-radio-btn");
        groupMembersRB.setStylePrimaryName("actigate-share-panel-radio-btn");
        anyoneRB.setStylePrimaryName("actigate-share-panel-radio-btn");
        panel.add(nobodyRB);
        panel.add(groupMembersRB);
        panel.add(anyoneRB);
//        formPanel.add(panel);

        return panel;
    }
}
