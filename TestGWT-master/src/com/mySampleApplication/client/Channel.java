package com.mySampleApplication.client;

/**
 * Created by rolique_pc on 10/19/2016.
 */
public class Channel {

    private long id;
    private String title;
    private String description;
    private String channelText;
    private String logoUrl;
    private String footerLogoUrl;
    private ChannelsNames channelName;
    private boolean isConnected;
    private boolean allowingDelete;

    public Channel() {
    }

    public Channel(long id, String description, String channelText, String logoUrl, String footerLogoUrl, ChannelsNames channelName, boolean isConnected, boolean allowingDelete) {
        this.id = id;
        this.title = channelName.getDisplayName();
        this.description = description;
        this.channelText = channelText;
        this.logoUrl = logoUrl;
        this.footerLogoUrl = footerLogoUrl;
        this.channelName = channelName;
        this.isConnected = isConnected;
        this.allowingDelete = allowingDelete;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public String getFooterLogoUrl() {
        return footerLogoUrl;
    }

    public ChannelsNames getChannelName() {
        return channelName;
    }

    public boolean isConnected() {
        return isConnected;
    }

    public boolean isAllowingDelete() {
        return allowingDelete;
    }

    public String getChannelText() {
        return channelText;
    }

    public void setConnected(boolean connected) {
        isConnected = connected;
    }

}
