package com.mySampleApplication.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.*;

/**
 * Created by rolique_pc on 10/20/2016.
 */
public class ChannelTile extends Composite implements MouseOutHandler,
            MouseOverHandler {

        private Button actionButton = new Button("Edit");
        private DockLayoutPanel footerPanel;
        private DeckPanel descriptionDecks;
        private AbsolutePanel layout;
        private FocusPanel descriptionPanel;
        private Label descriptionLabel;

        public ChannelTile(String title, String description, String logoUrl, String footerLogoUrl) {

        descriptionDecks = new DeckPanel();
        footerPanel = new DockLayoutPanel(Style.Unit.PX);
        layout = new AbsolutePanel();
        layout.setStylePrimaryName("actigate-admin-connectors-tile");
        descriptionPanel = new FocusPanel();
        descriptionPanel
                .setStylePrimaryName("actigate-admin-connectors-focuswrapper");

        descriptionPanel.addMouseOutHandler(this);
        descriptionPanel.addMouseOverHandler(this);
        descriptionDecks
                .setStylePrimaryName("actigate-admin-connectors-tile-description-deck");
        descriptionDecks.add(new Image(logoUrl, 0, 0, 80, 80));

        DockLayoutPanel descriptionTextPanel = new DockLayoutPanel(Style.Unit.PX);
        descriptionLabel = new Label(description);
        descriptionLabel
                .setStylePrimaryName("actigate-admin-connectors-tile-description-text");
        descriptionTextPanel.addSouth(actionButton, 40);
        descriptionTextPanel.add(descriptionLabel);
        descriptionDecks.add(descriptionTextPanel);

        // TODO: Enable after upgrade to GWT 2.5
        // descriptionDecks.setAnimationEnabled(true);
        descriptionDecks.showWidget(0);

        footerPanel.addWest(new Image(footerLogoUrl, 0, 0, 80, 80), 30);
        footerPanel.add(new Label(title));
        footerPanel
                .setStylePrimaryName("actigate-admin-connectors-tile-description-footer");

        actionButton.setStylePrimaryName("btn");
        actionButton.addStyleDependentName("primary");
        actionButton.addStyleDependentName("xs");

        actionButton.setText("Connect");

        actionButton.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent event) {
                descriptionDecks.showWidget(0);
                layout.getElement().getStyle().clearOpacity();
                footerPanel.getElement().getStyle().clearMarginLeft();
            }
        });


        descriptionPanel.setWidget(descriptionDecks);
        layout.add(descriptionPanel, 0, 0);
        layout.add(footerPanel, 0, 95);
        initWidget(layout);

    }

    public HandlerRegistration addActionButtonClickedHandler(
            ClickHandler handler) {
        return actionButton.addClickHandler(handler);
    }

    @Override
    public void onMouseOut(MouseOutEvent arg0) {
        descriptionDecks.showWidget(0);
        layout.getElement().getStyle().clearOpacity();
        footerPanel.getElement().getStyle().clearMarginLeft();

    }

    @Override
    public void onMouseOver(MouseOverEvent arg0) {
        descriptionDecks.showWidget(1);
        layout.getElement().getStyle().setOpacity(1);
        footerPanel.getElement().getStyle().setMarginLeft(0, Style.Unit.PX);
    }



}
