package com.mySampleApplication.client;

import com.google.gwt.user.client.ui.*;

import java.util.ArrayList;
import java.util.List;

import static com.mySampleApplication.client.ChannelsNames.*;


/**
 * Created by rolique_pc on 10/19/2016.
 */
public class ChannelsCreator implements ChannelsControllerSelection.ChannelSelectionCallback,ChannelsInstance.ChannelsInstancesCallback{
    private SimplePanel viewContainer = new SimplePanel();
    private FlowPanel mainPageLayout = new FlowPanel();

    private List<Channel> channels = new ArrayList<>();
    private ChannelsControllerSelection channelsControllerSelection;
    private ChannelsInstance channelsInstance;

    public SimplePanel getView() {
        return viewContainer;
    }

    public ChannelsCreator(){
        initChannels();
        channelsControllerSelection = new ChannelsControllerSelection(channels, this);
        channelsInstance = new ChannelsInstance(channels, this);

        mainPageLayout.setStylePrimaryName("col-md-6");
        mainPageLayout.add(channelsInstance.getMainView());
        mainPageLayout.add(new HTML("<div style=\"margin-top: 50px;\"><h4 style=\"font-size: 1.3em;\">Connect to another service</h4></div>"));
        mainPageLayout.add(channelsControllerSelection.getMainView());

        displayMainView();

    }
    private void initChannels(){
        channels.add(new Channel(0, "Connect your Slack", "Share the information to your page on Slack", "", "", SLACK, true, true));
        channels.add(new Channel(1, "Connect your Email", "Share the information to your Email","", "", EMAIL, false, false));
        channels.add(new Channel(2, "Connect your Whatsapp", "Share the information to your page on Whatsapp","", "", WHATSAPP, false, true));
        channels.add(new Channel(3, "Connect your Pinterest", "Share the information to your page on Pinterest","", "", PINTEREST, false, true));
    }

    private Widget displayMainView(){
        viewContainer.setWidget(mainPageLayout);
//        channelsInstance.refresh();
        channelsControllerSelection.updateChannelsView();
        return viewContainer;

    }

    private void refreshWidgets(){
        mainPageLayout.clear();
        ChannelsControllerSelection channelsControllerSelection = new ChannelsControllerSelection(channels, this);
        channelsInstance = new ChannelsInstance(channels, this);
        channelsControllerSelection.updateChannelsView();

        mainPageLayout.setStylePrimaryName("col-md-6");
        mainPageLayout.add(channelsInstance.getMainView());
        mainPageLayout.add(new HTML("<div style=\"margin-top: 50px;\"><h4 style=\"font-size: 1.3em;\">Connect to another service</h4></div>"));
        mainPageLayout.add(channelsControllerSelection.getMainView());
    }


    @Override
    public void onChannelSelectionSelected(Channel channel) {
        channels.get(channels.indexOf(channel)).setConnected(true);
        refreshWidgets();
    }

    @Override
    public void onClickDelete(Channel channel) {
        channels.get(channels.indexOf(channel)).setConnected(false);
        refreshWidgets();
    }

    @Override
    public void onClickManage(Channel channel) {

    }
}
