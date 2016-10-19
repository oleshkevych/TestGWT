package com.mySampleApplication.client;

/**
 * Created by rolique_pc on 10/19/2016.
 */
public enum ChannelsNames {
    SLACK("Slack"),
    EMAIL("Email"),
    WHATSAPP("Whatsapp"),
    PINTEREST("Pinterest");

    private String displayName;

    private ChannelsNames(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return this.displayName;
    }
}
