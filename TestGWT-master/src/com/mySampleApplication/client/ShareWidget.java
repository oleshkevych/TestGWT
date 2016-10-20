package com.mySampleApplication.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.*;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Volodymyr Oleshkevych on 10/9/2016.
 */
public class ShareWidget{


 /*   private FlowPanel upgradePanel = new FlowPanel();
    private Label upgradeLabel = new Label("Upgrade");

    private FlowPanel sharingEnvironmentsPanel = new FlowPanel();

    private Label descriptionLabel = new Label("Push sharing");
    private Panel emailPanel = new FlowPanel();
    private Panel facebookPanel = new FlowPanel();
    private Panel slackPanel = new FlowPanel();

    private Label settingsLabel = new Label("Settings");

    private Panel repeatPanel = new FlowPanel();

    private Label shareLinkLabel = new Label("Share link");

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

        @Source("pinterest.png")
        ImageResource pinterest();

        @Source("whatsapp.png")
        ImageResource whatsapp();
	}
    public SimplePanel getView() {
        return view;
    }

    public ShareWidget() {
        init();
    }

    private void init() {
        initUpgrade(5, 10);
        initShareEnvironments();
        initRepeatPanel();
        shareLinkLabel.setStylePrimaryName("actigate-share-panel-title-label");
        mainContainer.add(upgradePanel);
        mainContainer.add(sharingEnvironmentsPanel);
        mainContainer.add(repeatPanel);
        mainContainer.add(shareLinkLabel);
        mainContainer.add(initMembersPanel());
        view.setWidget(mainContainer);
        view.setStylePrimaryName("actigate-share-panel");

        initClickHandlers();
    }

    private void initUpgrade(int completeShares, int maxShares){
        Panel label = new HorizontalPanel();
        Label l1 = new Label("Using ");
        Label l2 = new Label(""+completeShares);
        Label l3 = new Label(" of ");
        Label l4 = new Label(""+maxShares);
        Label l5 = new Label(" shares");

        l2.setStylePrimaryName("actigate-share-panel-upgrade-label-numbers");
        l4.setStylePrimaryName("actigate-share-panel-upgrade-label-numbers");

        label.add(l1);
        label.add(l2);
        label.add(l3);
        label.add(l4);
        label.add(l5);
        label.setStyleName("actigate-share-panel-upgrade-label-inform");
        upgradePanel.add(label);
        upgradeLabel.setStyleName("actigate-share-panel-upgrade-label-upgrade");
        upgradePanel.add(upgradeLabel);
        upgradePanel.setStylePrimaryName("actigate-share-panel-upgrade");

    }

    private void initShareEnvironments(){
        descriptionLabel.setStylePrimaryName("actigate-share-panel-title-label");

        Image emailImage = new Image(Images.INSTANCE.email());
        emailImage.setSize("20px", "15px");
        emailPanel.add(sharingCategories(emailImage, "(Group name)", true, true));
        emailPanel.setStylePrimaryName("actigate-share-panel-environments-panel");

        Image facebookImage = new Image(Images.INSTANCE.facebook());
        facebookImage.setSize("20px", "20px");
        facebookPanel.add(sharingCategories(facebookImage, "(Group name)", false, true));
        facebookPanel.setStylePrimaryName("actigate-share-panel-environments-panel");

        Image slackImage = new Image(Images.INSTANCE.slack());
        slackImage.setSize("20px", "20px");
        slackPanel.add(sharingCategories(slackImage, "(Group name)", false, true));
        slackPanel.setStylePrimaryName("actigate-share-panel-environments-panel");

        settingsLabel.setStylePrimaryName("actigate-share-panel-sharing-environments-panel-label-settings");

        sharingEnvironmentsPanel.add(descriptionLabel);
        sharingEnvironmentsPanel.add(emailPanel);
        sharingEnvironmentsPanel.add(facebookPanel);
        sharingEnvironmentsPanel.add(slackPanel);
        sharingEnvironmentsPanel.add(settingsLabel);

    }

    public Panel sharingCategories(Image image, String groupName, boolean preview, boolean rightMenu){
        Panel panel = new FlowPanel();
            panel.setStylePrimaryName("actigate-share-panel-environments-content-panel");
            image.setStylePrimaryName("actigate-share-panel-environments-table-images");
            Label groupNameLabel = new Label(groupName);
            groupNameLabel.setStylePrimaryName("actigate-share-panel-environments-table-images");
            if(rightMenu) {
            final CheckBox checkBox = new CheckBox();
            checkBox.setStylePrimaryName("actigate-share-panel-checkbox");
            Label previewLabel = new Label("Preview");
            Label shareLabel = new Label("Share now");
            panel.add(checkBox);
            panel.add(image);
            panel.add(groupNameLabel);
            final Panel labelsPanel = new HorizontalPanel();
            if (preview) {
                previewLabel.setStylePrimaryName("actigate-share-panel-environments-right-labels");
                labelsPanel.add(previewLabel);
            }
            shareLabel.setStylePrimaryName("actigate-share-panel-environments-right-labels");
            labelsPanel.add(shareLabel);
            labelsPanel.addStyleName("actigate-share-panel-environments-right-panel");
            panel.add(labelsPanel);
            labelsPanel.setVisible(false);
            checkBox.addClickHandler(new ClickHandler() {
                @Override
                public void onClick(ClickEvent event) {
                    if (checkBox.getValue()) {
                        labelsPanel.setVisible(true);
                    } else {
                        labelsPanel.setVisible(false);
                    }
                }
            });
        }else{
            panel.add(image);
            panel.add(groupNameLabel);
        }
        return panel;
    }

    private void initRepeatPanel(){
        final CheckBox repeatCheckBox = new CheckBox();
        Label repeatLabel = new Label("Repeat");
        repeatCheckBox.setStylePrimaryName("actigate-share-panel-checkbox");
        repeatLabel.addStyleName("actigate-share-panel-checkbox-repeat");
        repeatPanel.add(repeatCheckBox);
        repeatPanel.add(repeatLabel);
        repeatPanel.setStylePrimaryName("actigate-share-panel-repeat-panel");

        final ListBox repeatListBox = new ListBox();
        repeatListBox.addItem("Daily");
        repeatListBox.addItem("Monthly");
        repeatListBox.addItem("First day of month");
        repeatListBox.setVisibleItemCount(1);
        repeatListBox.addStyleName("actigate-share-panel-repeat-list");
        repeatListBox.setVisible(false);
        repeatCheckBox.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                Logger log = Logger.getLogger("MY LOG");
                log.log(Level.INFO, "Click");

                if(repeatCheckBox.getValue()){
                    repeatListBox.setVisible(true);
                    repeatPanel.addStyleName("actigate-share-panel-repeat-list-click-out");
                }else{
                    repeatListBox.setVisible(false);
                    repeatPanel.removeStyleName("actigate-share-panel-repeat-list-click-out");
                }
            }
        });


        repeatPanel.add(repeatListBox);
    }


    private Widget initMembersPanel(){
//        Panel formPanel = new FormPanel();
//        formPanel.setStylePrimaryName("form-horizontal");

        Panel panel = new FlowPanel();
        final Panel textBoxPanel = new FlowPanel();
        TextBox textBox = new TextBox();
        textBox.setStylePrimaryName("actigate-share-panel-member-field");
//        textBox.setEnabled(false);
        Button copyBtn = new Button("Copy");
        copyBtn.addStyleName("btn");
        copyBtn.addStyleName("btn-primary");
        copyBtn.setStylePrimaryName("actigate-share-panel-sharing-environments-panel-btn");
        copyBtn.addMouseDownHandler(new MouseDownHandler() {
            @Override
            public void onMouseDown(MouseDownEvent event) {
                copyBtn.addStyleName("actigate-share-panel-sharing-environments-panel-btn-click");
                copyBtn.removeStyleName("actigate-share-panel-sharing-environments-panel-btn");
            }
        });
        copyBtn.addMouseUpHandler(new MouseUpHandler() {
            @Override
            public void onMouseUp(MouseUpEvent event) {
                copyBtn.addStyleName("actigate-share-panel-sharing-environments-panel-btn");
                copyBtn.removeStyleName("actigate-share-panel-sharing-environments-panel-btn-click");
            }
        });
        textBoxPanel.add(textBox);
        textBoxPanel.add(copyBtn);
        textBoxPanel.setVisible(false);

        panel.add(textBoxPanel);

        final RadioButton nobodyRB = new RadioButton("sharingAddress", "Nobody");
        RadioButton groupMembersRB = new RadioButton("sharingAddress", "Group members only");
        RadioButton anyoneRB = new RadioButton("sharingAddress", "Anyone with link");
        nobodyRB.setStylePrimaryName("actigate-share-panel-radio-btn");
        groupMembersRB.setStylePrimaryName("actigate-share-panel-radio-btn");
        anyoneRB.setStylePrimaryName("actigate-share-panel-radio-btn");
        nobodyRB.setValue(true);

        List<RadioButton> allRadioButtons = new ArrayList<RadioButton>();
        allRadioButtons.add(nobodyRB);
        allRadioButtons.add(groupMembersRB);
        allRadioButtons.add(anyoneRB);

        for (RadioButton radioButton : allRadioButtons) {
            radioButton.addValueChangeHandler(new ValueChangeHandler<Boolean>() {
                @Override
                public void onValueChange(ValueChangeEvent<Boolean> event) {
                    if(nobodyRB.getValue()){
                    textBoxPanel.setVisible(false);
                }else{
                    textBoxPanel.setVisible(true);
                }
                }
            });
        }

        panel.add(nobodyRB);
        panel.add(groupMembersRB);
        panel.add(anyoneRB);
//        formPanel.add(panel);

        return panel;
    }

    private void initClickHandlers(){
        upgradeLabel.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                Logger log = Logger.getLogger("MY LOG");
                log.log(Level.INFO, "Click");
                log.log(Level.INFO, event.getRelativeElement().getClassName());
            }
        });
    }*/
}
