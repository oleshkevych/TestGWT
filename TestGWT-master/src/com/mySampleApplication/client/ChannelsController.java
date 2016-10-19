package com.mySampleApplication.client;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rolique_pc on 10/19/2016.
 */
public class ChannelsController {




    public ChannelsController(){
        List<String> imageUrls = getImageURLs();
    }

    public List<String> getImageURLs() {
        List<String> imageURLs = new ArrayList<String>();
        String logoUrl = null;
        String footerLogoUrl = null;

        switch (connector.getConnectorName()) {
            case KPI_EXCEL:
                logoUrl = ConnectImages.INSTANCE.kpi().getSafeUri().asString();
                footerLogoUrl = ConnectImages.INSTANCE.kpi_small().getSafeUri()
                        .asString();
                break;

            case TARGETS_EXCEL:
                logoUrl = ConnectImages.INSTANCE.target().getSafeUri().asString();
                footerLogoUrl = ConnectImages.INSTANCE.target_small().getSafeUri()
                        .asString();
                break;

            case ZOHOCRM:
                logoUrl = ConnectImages.INSTANCE.zoho().getSafeUri().asString();
                footerLogoUrl = ConnectImages.INSTANCE.zoho_small().getSafeUri()
                        .asString();
                break;

            case CAPSULECRM:
                logoUrl = ConnectImages.INSTANCE.capsule().getSafeUri().asString();
                footerLogoUrl = ConnectImages.INSTANCE.capsule_small().getSafeUri()
                        .asString();
                break;
            case SIE:
                logoUrl = ConnectImages.INSTANCE.sie().getSafeUri().asString();
                footerLogoUrl = ConnectImages.INSTANCE.sie_small().getSafeUri()
                        .asString();
                break;
            case FORTNOX_F3:
                logoUrl = ConnectImages.INSTANCE.fortnoxF3().getSafeUri()
                        .asString();
                footerLogoUrl = ConnectImages.INSTANCE.fortnoxF3_small()
                        .getSafeUri().asString();
                break;

            case WEBTEMP:
                logoUrl = ConnectImages.INSTANCE.webtemp().getSafeUri().asString();
                footerLogoUrl = ConnectImages.INSTANCE.webtemp_small().getSafeUri()
                        .asString();
                break;

            case CUSTOM:
                logoUrl = ConnectImages.INSTANCE.custom().getSafeUri().asString();
                footerLogoUrl = ConnectImages.INSTANCE.custom_small().getSafeUri()
                        .asString();

                break;

            default:
                break;
        }

        imageURLs.add(logoUrl);
        imageURLs.add(footerLogoUrl);
        return imageURLs;
    }
}
