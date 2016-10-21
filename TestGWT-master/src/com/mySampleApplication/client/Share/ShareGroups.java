package com.mySampleApplication.client.Share;

/**
 * Created by rolique_pc on 10/21/2016.
 */
public enum ShareGroups {

    NOBODY("Nobody"),
    GROUP_MEMBERS("Group members only"),
    ANYONE("Anyone with link");

    private String groupName;

    private ShareGroups(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupName() {
        return this.groupName;
    }
}
