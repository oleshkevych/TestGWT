package com.mySampleApplication.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.mySampleApplication.client.Share.ShareWidget;
import com.mySampleApplication.client.Share.ShareTypes;

/**
 * Entry point classes define <code>onModuleLoad()</code>
 */
public class MySampleApplication implements EntryPoint, ShareWidget.SharePanelCallback{


//    private final DialogBox dialogBox = createDialogBox();
    /**
     * This is the entry point method.
     */
    public void onModuleLoad() {

//        dialogBox.setGlassEnabled(true);
//        dialogBox.setAnimationEnabled(true);


        ShareController s = new ShareController();
        final SimplePanel view = new SimplePanel();
//
//        s.displayEmptyPanel();
//        ChannelsCreator cc = new ChannelsCreator();
//        view.add(new ConfirmShareController().getView());
//        view.setStylePrimaryName("view-style");
//        view.setWidget(cc.getView());
//        view.setWidget(new Label("Test"));

//        ShareWidget shareWidget = new ShareWidget(this);
//        view.add(shareWidget);
        s.displayEmptyPanel();
        s.displaySharePanel(false);
        view.setWidth("390px");
        view.add(s.getView());
        RootPanel.get().add(view);
    }

    @Override
    public void onUpgrade(ShareTypes shareTypes) {
        GWT.log(shareTypes.toString());
    }

    @Override
    public void onShare(ShareWidget.ShareChannel channel) {
        if(channel.getName().equals("Email")){
            GWT.log("Share Email");
        }else if(channel.getName().equals("Slack")){
            GWT.log("Share Slack");
        }
    }

    @Override
    public void onPreview(ShareWidget.ShareChannel channel) {
        if(channel.getName().equals("Email")){
            GWT.log("Preview Email");
        }else if(channel.getName().equals("Slack")){
            GWT.log("Preview Slack");
        }
    }


    @Override
    public void onSetting() {
        GWT.log("settings");
    }

    private DialogBox createDialogBox() {
        final DialogBox dialogBox = new DialogBox();

        VerticalPanel dialogContents = new VerticalPanel();
        dialogContents.setSpacing(4);
        dialogBox.setWidget(dialogContents);

        HTML details = new HTML("Really delete this channel???");
        dialogContents.add(details);
        dialogContents.setCellHorizontalAlignment(
                details, HasHorizontalAlignment.ALIGN_CENTER);

        Button OKButton = new Button(
                "OK", new ClickHandler() {
            public void onClick(ClickEvent event) {
                GWT.log("Ok");
                dialogBox.hide();
            }
        });
        Button closeButton = new Button(
                "Cancel", new ClickHandler() {
            public void onClick(ClickEvent event) {
                GWT.log("NON");
                dialogBox.hide();
            }
        });
        OKButton.setStylePrimaryName("btn");
        OKButton.addStyleName("btn-primary");
        OKButton.addStyleName("actigate-channels-confirm-delete");
        closeButton.setStylePrimaryName("btn");
        closeButton.addStyleName("btn-default");
        closeButton.addStyleName("actigate-channels-confirm-delete");
        HorizontalPanel buttonsPanel = new HorizontalPanel();
        dialogContents.add(buttonsPanel);
        buttonsPanel.add(OKButton);
        buttonsPanel.setSpacing(8);
        buttonsPanel.add(new Label("   "));
        buttonsPanel.add(closeButton);
        dialogContents.setCellHorizontalAlignment(buttonsPanel, HasHorizontalAlignment.ALIGN_CENTER);
        return dialogBox;
    }


    private static class MyAsyncCallback implements AsyncCallback<String> {
        private Label label;

        public MyAsyncCallback(Label label) {
            this.label = label;
        }

        public void onSuccess(String result) {
            label.getElement().setInnerHTML(result);
        }

        public void onFailure(Throwable throwable) {
            label.setText("Failed to receive answer from server!");
        }
    }

//    private Widget initMyView(){
//        FlowPanel mainPanel = new FlowPanel();
//        Label mainChannelsLabel = new Label("Push channels setting");
//        Panel mainChannelsPanel = new FlowPanel();
//        Label otherChannelsLabel = new Label("Connect to another social channel");
//        Panel otherChannelsPanel = new FlowPanel();
//        mainChannelsLabel.setStylePrimaryName("actigate-share-panel-title-label");
//        otherChannelsLabel.setStylePrimaryName("actigate-share-panel-title-label");
//
//        //remove
//        Image facebook  = new Image(ShareWidget.Images.INSTANCE.facebook());
//        Image email  = new Image(ShareWidget.Images.INSTANCE.email());
//        Image slack  = new Image(ShareWidget.Images.INSTANCE.slack());
//        Image whatsapp  = new Image(ShareWidget.Images.INSTANCE.whatsapp());
//        Image pinterest  = new Image(ShareWidget.Images.INSTANCE.pinterest());
//        email.setSize("20px", "15px");
//        Image facebookImage = new Image(ShareWidget.Images.INSTANCE.facebook());
//        facebookImage.setSize("20px", "20px");
//        slack.setSize("20px", "20px");
//        mainChannelsPanel.add(mainChannels(email, "Email", "Members of this group", false));
////        mainChannelsPanel.add(mainChannels(facebookImage, "Facebook", "/facebookGroupName", true));
//        mainChannelsPanel.add(mainChannels(slack, "Slack", "/slackGroupName", true));
//        Panel emptyPanel = new HorizontalPanel();
//        emptyPanel.setStylePrimaryName("actigate-setting-share-panel-members-panel");
//        mainChannelsPanel.add(emptyPanel);
//        mainChannelsPanel.setStylePrimaryName("actigate-setting-share-panel-main-channels-panel");
//
//        otherChannelsPanel.add(otherChancels(facebook, "Facebook"));
//        otherChannelsPanel.add(otherChancels(whatsapp, "Whatsapp"));
//        otherChannelsPanel.add(otherChancels(pinterest, "Pinterest"));
//
//        mainPanel.add(mainChannelsLabel);
//        mainPanel.add(mainChannelsPanel);
//        mainPanel.add(otherChannelsLabel);
//        mainPanel.add(otherChannelsPanel);
//        mainPanel.setStylePrimaryName("actigate-setting-share-panel");
//        return mainPanel;
//    }
//    private Widget otherChancels(final Image image, String name){
//        Panel otherChannelPanel = new FlowPanel();
//        otherChannelPanel.setStylePrimaryName("actigate-setting-share-panel-other-panel");
//        Panel panel = new FlowPanel();
//        panel.setStylePrimaryName("actigate-setting-share-panel-image-panel");
//        image.setSize("60px", "60px");
//        panel.add(image);
//        Label label = new Label(name);
//
//        Panel descriptionPanel = new FlowPanel();
//        Label l1 = new Label("Connect to");
//        Label l2 = new Label(name);
//        Button btnHidden = new Button("Connect");
//        l1.setStylePrimaryName("actigate-setting-share-panel-image-panel-hidden-label");
//        l2.setStylePrimaryName("actigate-setting-share-panel-image-panel-hidden-label");
//        btnHidden.setStylePrimaryName("actigate-setting-share-panel-image-panel-hidden-button");
//        btnHidden.addStyleName("btn");
//        btnHidden.addStyleName("btn-primary");
//        descriptionPanel.add(l1);
//        descriptionPanel.add(l2);
//        descriptionPanel.add(btnHidden);
//        descriptionPanel.setVisible(false);
//        panel.add(descriptionPanel);
//
//
//        panel.sinkEvents(Event.ONMOUSEOVER);
//        panel.sinkEvents(Event.ONMOUSEOUT);
//        panel.addHandler(new MouseOverHandler() {
//            @Override
//            public void onMouseOver(MouseOverEvent event) {
//                image.setVisible(false);
//                descriptionPanel.setVisible(true);
//            }
//        }, MouseOverEvent.getType());
//
//
//
//        label.setStylePrimaryName("actigate-setting-share-panel-other-label");
//        otherChannelPanel.add(panel);
//        otherChannelPanel.add(label);
//
//        return otherChannelPanel;
//    }
//
//    private Widget mainChannels(Image image, String name, String groupName, boolean isRemovable){
//        Panel channelPanel = new FlowPanel();
//        channelPanel.setStylePrimaryName("actigate-setting-share-panel-members-panel");
//
//        Panel leftViews = new FlowPanel();
//        leftViews.setStylePrimaryName("actigate-setting-share-panel-left-panel");
//
//
//        ShareWidget s = new ShareWidget();
//        leftViews.add(s.sharingCategories(image, name, false, false));
//        Label membersLabel = new Label(groupName);
//        membersLabel.setStylePrimaryName("actigate-setting-share-panel-members-label");
//        leftViews.add(membersLabel);
//
//        Panel labelsPanel = new FlowPanel();
//        Label manageLabel = new Label("Manage");
//        Label deleteLabel = new Label("Delete");
//        manageLabel.setStylePrimaryName("actigate-setting-share-panel-right-labels");
//        labelsPanel.addStyleName("actigate-setting-share-panel-right-label-panel");
//        deleteLabel.setStylePrimaryName("actigate-setting-share-panel-right-labels");
//
//
//
//
//        if (isRemovable) {
//            labelsPanel.add(deleteLabel);
//            manageLabel.addStyleName("actigate-setting-share-panel-right-labels-single");
//        }
//
//
//
//
//        labelsPanel.add(manageLabel);
//        channelPanel.add(leftViews);
//        channelPanel.add(labelsPanel);
//
//
//
//        return  channelPanel;
//
//    }
}
