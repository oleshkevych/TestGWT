package com.mySampleApplication.client;

import com.google.gwt.user.client.ui.SimplePanel;

import java.util.ArrayList;
import java.util.List;

import static com.mySampleApplication.client.ChannelsNames.*;


/**
 * Created by rolique_pc on 10/19/2016.
 */
public class ChannelsCreator {
    private SimplePanel view = new SimplePanel();
    private List<Channel> channels = new ArrayList<>();
    private ChannelsController channelsController;
    private ChannelsInstance channelsInstance;

    public SimplePanel getView() {
        return view;
    }

    public ChannelsCreator(){
        initChannels();
        initView();
    }
    private void initChannels(){
        channels.add(new Channel(0, "Connect your Slack", "", "", SLACK, true, true));
        channels.add(new Channel(1, "Connect your Email", "", "", EMAIL, true, false));
        channels.add(new Channel(2, "Connect your Whatsapp", "", "", WHATSAPP, true, true));
        channels.add(new Channel(3, "Connect your Pinterest", "", "", PINTEREST, true, true));
    }

    private void initView(){

    }


}
