package com.mySampleApplication.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.mySampleApplication.client.resurses.ChannelsImages;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rolique_pc on 10/20/2016.
 */
public class ChannelController implements ClickHandler{

    private Channel channel;
    private ChannelTile channelTile;
    private ChannelCallback callback;

    public interface ChannelCallback{
        public void onChannelSelected(Channel channel);
    }

    public ChannelController(Channel channel,  ChannelCallback callback){
        this.callback = callback;
        this.channel = channel;
        List<String> imageUrls = getImageURLs();

        channelTile = new ChannelTile(channel.getTitle(), channel.getDescription(), imageUrls.get(0), imageUrls.get(1));

        channelTile.addActionButtonClickedHandler(this);
    }
    public List<String> getImageURLs() {
        List<String> imageURLs = new ArrayList<String>();
        String logoUrl = null;
        String footerLogoUrl = null;

        switch (channel.getChannelName()) {
            case SLACK:
                logoUrl = ChannelsImages.INSTANCE.slack().getSafeUri().asString();
                footerLogoUrl = ChannelsImages.INSTANCE.slack_small().getSafeUri()
                        .asString();
                break;

            case PINTEREST:
                logoUrl = ChannelsImages.INSTANCE.pinterest().getSafeUri().asString();
                footerLogoUrl = ChannelsImages.INSTANCE.pinterest_small().getSafeUri()
                        .asString();
                break;

            case WHATSAPP:
                logoUrl = ChannelsImages.INSTANCE.whatsapp().getSafeUri().asString();
                footerLogoUrl = ChannelsImages.INSTANCE.whatsapp_small().getSafeUri()
                        .asString();
                break;

            case EMAIL:
                logoUrl = ChannelsImages.INSTANCE.email().getSafeUri().asString();
                footerLogoUrl = ChannelsImages.INSTANCE.email_small().getSafeUri()
                        .asString();
                break;
            default:
                break;
        }

        imageURLs.add(logoUrl);
        imageURLs.add(footerLogoUrl);
        return imageURLs;
    }

    public ChannelTile getChannelTile() {
        return channelTile;
    }

    @Override
    public void onClick(ClickEvent event) {

        if(callback != null){
            callback.onChannelSelected(channel);
        }
    }
}
