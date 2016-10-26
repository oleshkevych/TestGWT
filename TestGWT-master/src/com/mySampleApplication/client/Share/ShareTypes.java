package com.mySampleApplication.client.Share;

import com.google.gwt.aria.client.ListitemRole;
import com.google.gwt.user.client.rpc.core.java.lang.Boolean_CustomFieldSerializer;
import com.google.gwt.user.client.ui.Image;
import com.mySampleApplication.client.Channel;

import java.util.*;

import static com.mySampleApplication.client.Share.ShareGroups.NOBODY;

/**
 * Created by rolique_pc on 10/21/2016.
 */
public class ShareTypes {


//    private List<Boolean> checkedChannels;
    private boolean isRepeat;
    private int repeatId;
    private String shareGroup;
    private Map<String, Boolean> checkedChannels;

    public ShareTypes(List<String> listChannels) {

//        this.checkedChannels = new ArrayList<>(Arrays.asList(new Boolean[]{false, false}));
        this.isRepeat = false;
        this.shareGroup = NOBODY.getGroupName();
        this.checkedChannels = new HashMap<String, Boolean>();
        for(String c: listChannels) {
            this.checkedChannels.put(c, false);
        }


    }


//    public List<Boolean> getCheckedChannels() {
//        return checkedChannels;
//    }
//
//    public void setCheckedChannels(List<Boolean> checkedChannels) {
//        this.checkedChannels = checkedChannels;
//    }

    public void setCheckedChannels(Map<String, Boolean> checkedChannels) {
        this.checkedChannels = checkedChannels;
    }

    public Map<String, Boolean> getCheckedChannels() {
        return checkedChannels;
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

    @Override
    public String toString() {
        return "ShareTypes{" +
                "isRepeat=" + isRepeat +
                ", repeatId=" + repeatId +
                ", shareGroup='" + shareGroup + '\'' +
                ", checkedChannels=" + checkedChannels +
                '}';
    }
}
