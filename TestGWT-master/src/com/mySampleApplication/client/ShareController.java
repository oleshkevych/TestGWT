package com.mySampleApplication.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.mySampleApplication.client.Share.ShareTypes;
import com.mySampleApplication.client.Share.ShareWidget;

/**
 * Created by rolique_pc on 10/26/2016.
 */
public class ShareController implements ShareWidget.SharePanelCallback {

    private final ShareWidget sharePanel;

    private SimplePanel view = new SimplePanel();
    private FlowPanel emptyPanel = new FlowPanel();
    public ShareController() {
        sharePanel = new ShareWidget(this);
//        view.add(sharePanel);
//
//
//		view.setWidget(emptyMassage);

    }

    public void displayEmptyPanel() {
        emptyPanel.clear();
        Label emptyMassage = new Label("Please select an item that can be shared.");
		emptyMassage.setStylePrimaryName("actigate-empty-dialog-message");
		emptyPanel.add(emptyMassage);
//		if(emptyPanel.isAttached()){
			view.setWidget(emptyPanel);
//		}
    }

    public void displaySharePanel(boolean b) {
        if(b) {
            view.setWidget(sharePanel);
//        }
//		if (!(proxy.getMarkedViewlet() instanceof MultiChartViewlet)) {
//			if (proxy.getMarkedViewlet() instanceof DynamicViewlet) {
//
//				if(sharePanel.isAttached()){
//					view.setWidget(sharePanel);
//				}
//
			} else {
				displayEmptyPanel();
//				;
			}
//		}

    }

    public Widget getView() {
        return view;
    }

    @Override
    public void onUpgrade(ShareTypes shareTypes) {
        GWT.log(shareTypes.toString());
    }

    @Override
    public void onShare(ShareWidget.ShareChannel channel) {
        if (channel.getName().equals("Email")) {
            GWT.log("Share Email");
        } else if (channel.getName().equals("Slack")) {
            GWT.log("Share Slack");
        }
    }

    @Override
    public void onPreview(ShareWidget.ShareChannel channel) {
        if (channel.getName().equals("Email")) {
            GWT.log("Preview Email");
        } else if (channel.getName().equals("Slack")) {
            GWT.log("Preview Slack");
        }
    }

    @Override
    public void onSetting() {
        GWT.log("settings");


    }

}