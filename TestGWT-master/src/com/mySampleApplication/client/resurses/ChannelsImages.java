package com.mySampleApplication.client.resurses;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;

/**
 * Created by rolique_pc on 10/19/2016.
 */
public interface ChannelsImages  extends ClientBundle {

    public static final ChannelsImages INSTANCE =  GWT.create(ChannelsImages.class);

    ImageResource slack();
//    ImageResource fortnoxF3_med();
//    ImageResource fortnoxF3_small();

    ImageResource whatsapp();
//    ImageResource sie_med();
//    ImageResource sie_small();

}