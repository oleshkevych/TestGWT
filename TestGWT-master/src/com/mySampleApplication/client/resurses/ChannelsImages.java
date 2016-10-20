package com.mySampleApplication.client.resurses;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;

/**
 * Created by rolique_pc on 10/19/2016.
 */
public interface ChannelsImages  extends ClientBundle {

    public static final ChannelsImages INSTANCE =  GWT.create(ChannelsImages.class);

    @Source("slack.png")
    ImageResource slack();
    @Source("slack_small.png")
    ImageResource slack_small();
//    ImageResource fortnoxF3_small();
    @Source("whatsapp.png")
    ImageResource whatsapp();
    @Source("whatsapp_small.png")
    ImageResource whatsapp_small();
//    ImageResource sie_small();
    @Source("email.png")
    ImageResource email();
    @Source("email_small.png")
    ImageResource email_small();
//    ImageResource fortnoxF3_small();
    @Source("pinterest.png")
    ImageResource pinterest();
    @Source("pinterest_small.png")
    ImageResource pinterest_small();
//    ImageResource sie_small();



}