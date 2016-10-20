package com.mySampleApplication.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rolique_pc on 10/19/2016.
 */
public class ChannelsControllerSelection implements ChannelController.ChannelCallback {

    public interface ChannelSelectionCallback{

        public void onChannelSelectionSelected(Channel channel);
    }
    private ChannelSelectionCallback callback;

    private FlowPanel tilesContainer = new FlowPanel();
    private List<Channel> channels;

    public Widget getMainView() {
        return tilesContainer;
    }
    public ChannelsControllerSelection(List<Channel> channels, ChannelSelectionCallback callback){
        this.callback = callback;
        this.channels = channels;
    }


    public void updateChannelsView(){
        setChannels(channels);
    }


    private void setChannels(List<Channel> channels) {
        tilesContainer.clear();

        if (channels != null) {
            for (Channel c : channels) {
                if(!c.isConnected()) {
                    ChannelController controller = new ChannelController(c, this);
                    tilesContainer.add(controller.getChannelTile());
                }
            }
        }
    }

    @Override
    public void onChannelSelected(Channel channel) {
        callback.onChannelSelectionSelected(channel);
    }
}
