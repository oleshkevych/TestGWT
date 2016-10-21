package com.mySampleApplication.client.Share;

import com.google.gwt.user.client.rpc.core.java.lang.Boolean_CustomFieldSerializer;
import com.google.gwt.user.client.ui.Image;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.mySampleApplication.client.Share.ShareGroups.NOBODY;

/**
 * Created by rolique_pc on 10/21/2016.
 */
public class Upgrade {


    private List<Boolean> checkedChannels;
    private boolean isRepeat;
    private int repeatId;
    private String shareGroup;

    public Upgrade() {

        this.checkedChannels = new ArrayList<>(Arrays.asList(new Boolean[]{false, false}));
        this.isRepeat = false;
        this.shareGroup = NOBODY.getGroupName();
    }


    public List<Boolean> getCheckedChannels() {
        return checkedChannels;
    }

    public void setCheckedChannels(List<Boolean> checkedChannels) {
        this.checkedChannels = checkedChannels;
    }

    public boolean isRepeat() {
        return isRepeat;
    }

    public void setRepeat(boolean repeat) {
        isRepeat = repeat;
    }

    public int getRepeatId() {
        return repeatId;
    }

    public void setRepeatId(int repeatId) {
        this.repeatId = repeatId;
    }

    public String getShareGroup() {
        return shareGroup;
    }

    public void setShareGroup(String shareGroup) {
        this.shareGroup = shareGroup;
    }
}
