package com.mySampleApplication.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.DOM;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.ClickEvent;

/**
 * Entry point classes define <code>onModuleLoad()</code>
 */
public class MySampleApplication implements EntryPoint {

    /**
     * This is the entry point method.
     */
    public void onModuleLoad() {
//         Button button = new Button("Click me");
//         Label label = new Label();
//
//
        final SimplePanel view = new SimplePanel();
//
//
//        button.addClickHandler(new ClickHandler() {
//            public void onClick(ClickEvent event) {
                view.setWidth("390px");
                view.add(new ShareWidget().getView());

                RootPanel.get("slot2").add(view);
//            }
//        });
//
//
//        RootPanel.get("slot1").add(button);
//        RootPanel.get("slot2").add(label);
    }

    private static class MyAsyncCallback implements AsyncCallback<String> {
        private Label label;

        public MyAsyncCallback(Label label) {
            this.label = label;
        }

        public void onSuccess(String result) {
            label.getElement().setInnerHTML(result);
        }

        public void onFailure(Throwable throwable) {
            label.setText("Failed to receive answer from server!");
        }
    }
}
