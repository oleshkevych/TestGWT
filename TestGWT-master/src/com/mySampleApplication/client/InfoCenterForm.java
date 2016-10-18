package com.mySampleApplication.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.FocusEvent;
import com.google.gwt.event.dom.client.FocusHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.regexp.shared.RegExp;
import com.google.gwt.user.client.ui.*;

/**
 * Created by rolique_pc on 10/18/2016.
 */
public class InfoCenterForm extends Composite implements FocusHandler {


    private Label validationFail = new Label("Fields can not be empty");

    private static final String INFOCENTERNAME_REGEX_PATTERN = "^[\\waåäöÄÖÅ -]*$";


    private TextBox name = new TextBox();
    //private TextBox purpose = new TextBox();
    private Button updateButton = new Button("Rename");
    private DockLayoutPanel buttonsPanel  = new DockLayoutPanel(Style.Unit.PX);

    public InfoCenterForm(){
        DockLayoutPanel layout = new DockLayoutPanel(Style.Unit.PX);
        layout.setStylePrimaryName("actigate-admin-infocenters-newinfocenter");
        layout.setHeight("250px");

        //	buttonsPanel.addEast(updateButton, 85);
        //	buttonsPanel.add(validationFail);

        layout.addSouth(buttonsPanel, 50);
        updateButton.setStylePrimaryName("btn");
        updateButton.addStyleDependentName("primary");

        validationFail.setStylePrimaryName("actigate-admin-infocenters-members-validation-message");
        validationFail.addStyleDependentName("fail");

        layout.add(initEditForm());
        name.addFocusHandler(this);
        //.addFocusHandler(this);
        initWidget(layout);
    }


    private Widget initEditForm(){



        FormLayoutPanel form = new FormLayoutPanel(FormLayoutPanel.LabelPosition.LEFT, 0,200,100);
        form.setStylePrimaryName("actigate-admin-infocenters-members-newuserform");
        form.setRowHeight(40);
        form.append ("", name,updateButton);
        //	form.append("Description", purpose);

        return form;

    }

    public String getName() {
        return name.getText();
    }

    public void setName(String name) {
        this.name.setText(name);
    }

	/*public String getPurpose() {
		return purpose.getText();
	}

	public void setPurpose(String purpose) {
		this.purpose.setText(purpose);
	}*/


    public void displayValidationFail(String message){
        validationFail.setText(message);
        validationFail.getElement().getStyle().setMarginLeft(20, Style.Unit.PX);

    }

    public void hideValidationFail(){
        validationFail.getElement().getStyle().clearMarginLeft();
    }

    public HandlerRegistration addUpdateClickedHandler(ClickHandler handler){
        return updateButton.addClickHandler(handler);
    }

    public Widget getUpdateButton(){
        return updateButton;
    }

    public void clearForm(){
        enableUpdateButton();
        hideValidationFail();
        name.setText("");
        //	purpose.setText("");
    }


    public void disableUpdateButton(){

        updateButton.setText("Renaming...");
        updateButton.setEnabled(false);
    }

    public void enableUpdateButton(){

        updateButton.setText("Rename");
        updateButton.setEnabled(true);
    }

    public boolean validate() {
        if(name.getText().trim().isEmpty()
				/*|| purpose.getText().trim().isEmpty()*/){
            displayValidationFail("FAild");
            return false;
        }
        else{
            RegExp regexp = RegExp.compile(INFOCENTERNAME_REGEX_PATTERN);
            if(regexp.test(name.getText().trim())){
                return true;
            }
            else{
                displayValidationFail("Letters, numbers, '-', '_', ' '");
                return false;
            }
        }
    }

    @Override
    public void onFocus(FocusEvent arg0) {
        hideValidationFail();

    }

}
