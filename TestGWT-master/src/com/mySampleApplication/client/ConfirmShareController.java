package com.mySampleApplication.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rolique_pc on 10/19/2016.
 */
public class ConfirmShareController implements ClickHandler {

    public interface ConfirmShareCallback{

        public void onClickBtn();
    }
    private List<RadioButton> allRadioButtons;

    private SimplePanel view = new SimplePanel();
    public SimplePanel getView() {
        view.setWidth("250px");
        return view;
    }
    Channel channel;
    ConfirmShareCallback callback;

    public ConfirmShareController(Channel channel, ConfirmShareCallback callback){
        this.channel = channel;
        this.callback = callback;
        view.setWidget(panel());
    }

    private Widget panel(){
        Panel mainPanel = new FlowPanel();

        Label titleLabel = new Label("Secure email");
        titleLabel.setStylePrimaryName("actigate-share-panel-title-label");

        Panel panelRadio = new FlowPanel();
        panelRadio.setStylePrimaryName("actigate-share-setting-confirm-panel-radio-panel");


        final RadioButton nonSecure = new RadioButton("security", "Non-secure (unencrypted)");
        RadioButton secure = new RadioButton("security", "Secure (encrypted)");
        nonSecure.setStylePrimaryName("actigate-share-panel-radio-btn");
        secure.setStylePrimaryName("actigate-share-panel-radio-btn");
        secure.setValue(true);
        nonSecure.addStyleName("actigate-share-setting-confirm-panel-radio");
        secure.addStyleName("actigate-share-setting-confirm-panel-radio");

        allRadioButtons = new ArrayList<RadioButton>();
        allRadioButtons.add(nonSecure);
        allRadioButtons.add(secure);

        final Panel labelInfoPanel = new FlowPanel();
        Label l1 = new Label("This connection is not secured, at your own risk.");
        labelInfoPanel.add(l1);
        l1.addStyleName("actigate-share-panel-upgrade-label-inform");
        labelInfoPanel.setStylePrimaryName("actigate-share-panel-upgrade");
        labelInfoPanel.addStyleName("actigate-share-setting-confirm-panel-radio");
        labelInfoPanel.setVisible(false);

        for (RadioButton radioButton : allRadioButtons) {
            radioButton.addValueChangeHandler(new ValueChangeHandler<Boolean>() {
                @Override
                public void onValueChange(ValueChangeEvent<Boolean> event) {
                    if(nonSecure.getValue()){
                        labelInfoPanel.setVisible(true);
                    }else{
                        labelInfoPanel.setVisible(false);
                    }
                }
            });
        }

        panelRadio.add(nonSecure);
        panelRadio.add(secure);
        panelRadio.add(labelInfoPanel);

        Panel buttonsPanel = new FlowPanel();
        buttonsPanel.setStylePrimaryName("actigate-setting-share-panel-other-panel");
        buttonsPanel.addStyleName("actigate-share-setting-confirm-panel-buttons-panel");
        Button okButton = new Button("OK");
        Button cancelButton = new Button("Cancel");
        okButton.setStylePrimaryName("btn");
        okButton.addStyleName("btn-primary");
        okButton.addStyleName("actigate-share-setting-confirm-panel-button");
        cancelButton.setStylePrimaryName("btn");
        cancelButton.addStyleName("btn-default");
        cancelButton.addStyleName("actigate-share-setting-confirm-panel-button");
        buttonsPanel.add(okButton);
        buttonsPanel.add(cancelButton);
        okButton.addClickHandler(this);
        cancelButton.addClickHandler(this);

        mainPanel.add(titleLabel);
        mainPanel.add(buttonsPanel);
        mainPanel.add(panelRadio);

        return mainPanel;
    }

    @Override
    public void onClick(ClickEvent event) {

        if(event.getRelativeElement().getInnerText().equals("OK")){
            if(allRadioButtons.get(0).getValue()){
                GWT.log("Confirm: NonSecure");
            }else{
                GWT.log("Confirm: Secure");
            }
        }
        callback.onClickBtn();
    }
}
