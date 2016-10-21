package com.mySampleApplication.client.Share;

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

import static com.mySampleApplication.client.Share.ShareGroups.ANYONE;
import static com.mySampleApplication.client.Share.ShareGroups.GROUP_MEMBERS;
import static com.mySampleApplication.client.Share.ShareGroups.NOBODY;

/**
 * Created by Volodymyr Oleshkevych on 10/9/2016.
 */
public class ShareWidget extends Composite  implements  ClickHandler{

    public interface SharePanelCallback{

        public void onUpgrade(Upgrade upgrade);
        public void onShare();
        public void onPreview();
        public void onSetting();

    }

    private class ShareChannel{
        private String name;
        private String nameP;
        private String groupName;
        private Image image;
        private boolean preview;
        private boolean checked;

        public ShareChannel(String name, String groupName, Image image, boolean preview) {
            this.name = name;
            this.nameP = name+"P";
            this.groupName = groupName;
            this.image = image;
            this.preview = preview;
            this.checked = false;
        }

        public String getNameP() {
            return nameP;
        }

        public boolean isChecked() {
            return checked;
        }

        public void setChecked(boolean checked) {
            this.checked = checked;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getGroupName() {
            return groupName;
        }

        public void setGroupName(String groupName) {
            this.groupName = groupName;
        }

        public Image getImage() {
            return image;
        }

        public void setImage(Image image) {
            this.image = image;
        }

        public boolean isPreview() {
            return preview;
        }

        public void setPreview(boolean preview) {
            this.preview = preview;
        }
    }

    private SharePanelCallback callback;

    private FlowPanel upgradePanel = new FlowPanel();
    private Label upgradeLabel = new Label("Upgrade");
    private FlowPanel sharingEnvironmentsPanel = new FlowPanel();

    private Label descriptionLabel = new Label("Push sharing");

    private Panel emailPanel = new FlowPanel();
    //	private Panel facebookPanel = new FlowPanel();
    private Panel slackPanel = new FlowPanel();
    private Label settingsLabel = new Label("Settings");

    private Panel repeatPanel = new FlowPanel();

    private Label shareLinkLabel = new Label("Share link");

    private SimplePanel view = new SimplePanel();

    private Upgrade upgrade = new Upgrade();
    private ShareChannel email;
    private ShareChannel slack;
    private Panel mainContainer = new FlowPanel();
    interface Images extends ClientBundle {

        public static final Images INSTANCE = GWT.create(Images.class);
        @Source("email.png")
        ImageResource email();

        @Source("slack.png")
        ImageResource slack();

    }
    public ShareWidget(SharePanelCallback callback) {
        this.callback = callback;
        Image emailImage = new Image(Images.INSTANCE.email());
        emailImage.setSize("20px", "15px");
        Image slackImage = new Image(Images.INSTANCE.slack());
        slackImage.setSize("20px", "20px");
        email = new ShareChannel("Email", "(Group name)", emailImage, true);
        slack = new ShareChannel("Slack", "(Group name)", slackImage, false);
        init();
        initWidget(view);
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
//        view.setStylePrimaryName("actigate-share-panel");

    }

    private void initUpgrade(int completeShares, int maxShares) {
        Panel label = new HorizontalPanel();
        Label l1 = new Label("Using ");
        Label l2 = new Label("" + completeShares);
        Label l3 = new Label(" of ");
        Label l4 = new Label("" + maxShares);
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
        upgradeLabel.getElement().setId("upgrade");
        upgradeLabel.addClickHandler(this);

    }

    private void initShareEnvironments() {
        descriptionLabel.setStylePrimaryName("actigate-share-panel-title-label");
        emailPanel.add(sharingCategories(email));
        emailPanel.setStylePrimaryName("actigate-share-panel-environments-panel");
        slackPanel.add(sharingCategories(slack));
        slackPanel.setStylePrimaryName("actigate-share-panel-environments-panel");

        settingsLabel.setStylePrimaryName("actigate-share-panel-sharing-environments-panel-label-settings");
        settingsLabel.getElement().setId("setting");
        settingsLabel.addClickHandler(this);

        sharingEnvironmentsPanel.add(descriptionLabel);
        sharingEnvironmentsPanel.add(emailPanel);
        sharingEnvironmentsPanel.add(slackPanel);
        sharingEnvironmentsPanel.add(settingsLabel);

    }

    private Panel sharingCategories(ShareChannel shareChanel) {
        final CheckBox checkBox = new CheckBox();
        checkBox.setStylePrimaryName("gwt-CheckBox");
        checkBox.addStyleName("actigate-share-panel-checkbox");


        Image image = shareChanel.getImage();
        Label groupNameLabel = new Label(shareChanel.getGroupName());
        Label previewLabel = new Label("Preview");
        Label shareLabel = new Label("Share now");
        shareLabel.getElement().setId(shareChanel.getName());
        shareLabel.addClickHandler(this);
        previewLabel.getElement().setId(shareChanel.getNameP());
        previewLabel.addClickHandler(this);
        Panel panel = new FlowPanel();
        panel.setStylePrimaryName("actigate-share-panel-environments-content-panel");

        image.setStylePrimaryName("actigate-share-panel-environments-table-images");
        groupNameLabel.setStylePrimaryName("actigate-share-panel-environments-table-images");
        panel.add(checkBox);
        panel.add(image);
        panel.add(groupNameLabel);

        final Panel labelsPanel = new HorizontalPanel();
        if (shareChanel.isPreview()) {
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
                    shareChanel.setChecked(true);
                } else {
                    labelsPanel.setVisible(false);
                    shareChanel.setChecked(false);
                }
            }
        });
        return panel;
    }

    private void initRepeatPanel() {
        final CheckBox repeatCheckBox = new CheckBox("Repeat");
//		Label repeatLabel = new Label("Repeat");
//		repeatCheckBox.setStylePrimaryName("actigate-share-panel-checkbox");
        repeatCheckBox.setStylePrimaryName("gwt-CheckBox");
//		repeatLabel.addStyleName("gwt-CheckBox");
        repeatPanel.add(repeatCheckBox);
//		repeatPanel.add(repeatLabel);
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
                if (repeatCheckBox.getValue()) {
                    repeatListBox.setVisible(true);
                    repeatPanel.addStyleName("actigate-share-panel-repeat-list-click-out");
                    upgrade.setRepeat(true);
                    upgrade.setRepeatId(repeatListBox.getSelectedIndex());
                } else {
                    repeatListBox.setVisible(false);
                    repeatPanel.removeStyleName("actigate-share-panel-repeat-list-click-out");
                    upgrade.setRepeat(false);
                }
            }
        });

        repeatListBox.addChangeHandler(new ChangeHandler() {
            @Override
            public void onChange(ChangeEvent event) {
                upgrade.setRepeatId(repeatListBox.getSelectedIndex());
            }
        });

        repeatPanel.add(repeatListBox);
    }

    private Widget initMembersPanel() {
        Panel panel = new FlowPanel();
        final Panel textBoxPanel = new FlowPanel();
        TextBox textBox = new TextBox();
        textBox.setStylePrimaryName("actigate-share-panel-member-field");
        final Button copyBtn = new Button("Copy");
        copyBtn.setStylePrimaryName("btn");
        copyBtn.addStyleName("btn-primary");
        copyBtn.addStyleName("actigate-share-panel-sharing-environments-panel-btn");
        copyBtn.addClickHandler(this);
        textBoxPanel.setStylePrimaryName("actigate-share-panel-memberpanel");
        textBoxPanel.add(textBox);
        textBoxPanel.add(copyBtn);
        textBoxPanel.setVisible(false);


        panel.add(textBoxPanel);

        final RadioButton nobodyRB = new RadioButton("sharingAddress", NOBODY.getGroupName());
        RadioButton groupMembersRB = new RadioButton("sharingAddress", GROUP_MEMBERS.getGroupName());
        RadioButton anyoneRB = new RadioButton("sharingAddress", ANYONE.getGroupName());
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
                    if (nobodyRB.getValue()) {
                        upgrade.setShareGroup(NOBODY.getGroupName());
                        textBoxPanel.setVisible(false);
                    } else {
                        textBoxPanel.setVisible(true);
                        if (groupMembersRB.getValue()){
                            upgrade.setShareGroup(GROUP_MEMBERS.getGroupName());
                        }else{
                            upgrade.setShareGroup(ANYONE.getGroupName());
                        }
                    }
                }
            });
        }

        panel.add(nobodyRB);
        panel.add(groupMembersRB);
        panel.add(anyoneRB);
        // formPanel.add(panel);

        return panel;
    }



    @Override
    public void onClick(ClickEvent event) {
        if(event.getRelativeElement().getId().equals("upgrade")){
            if(email.isChecked()){
                upgrade.getCheckedChannels().set(0, true);
            }
            if(slack.isChecked()){
                upgrade.getCheckedChannels().set(1, true);
            }
            callback.onUpgrade(upgrade);
        }else if(event.getRelativeElement().getId().equals("settings")){
            callback.onSetting();
        }else if(event.getRelativeElement().getId().equals(email.getName())){
           callback.onShare();
        }
    }
}
