package com.mySampleApplication.client;

/**
 * Created by rolique_pc on 10/19/2016.
 */
public class Channel {

    private long id;
    private String title;
    private String description;
    private String logoUrl;
    private String footerLogoUrl;
    private ChannelsNames channelsNames;
    private boolean isConnected;
    private boolean allowingDelete;

    public Channel() {
    }

    public Channel(long id, String description, String logoUrl, String footerLogoUrl, ChannelsNames channelsNames, boolean isConnected, boolean allowingDelete) {
        this.id = id;
        this.title = channelsNames.getDisplayName();
        this.description = description;
        this.logoUrl = logoUrl;
        this.footerLogoUrl = footerLogoUrl;
        this.channelsNames = channelsNames;
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

    public ChannelsNames getChannelsNames() {
        return channelsNames;
    }

    public boolean isConnected() {
        return isConnected;
    }

    public boolean isAllowingDelete() {
        return allowingDelete;
    }
}
