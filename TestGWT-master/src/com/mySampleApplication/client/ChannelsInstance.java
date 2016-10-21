package com.mySampleApplication.client;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.cell.client.ImageResourceCell;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.cell.client.ValueUpdater;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.EventTarget;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.dom.client.Style;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.ProvidesKey;
import com.google.gwt.view.client.SingleSelectionModel;
import com.mySampleApplication.client.resurses.ChannelsImages;
import com.mySampleApplication.client.resurses.ConnectorTableResources;

import java.util.ArrayList;
import java.util.List;

import static com.mySampleApplication.client.ChannelsNames.*;

/**
 * Created by rolique_pc on 10/19/2016.
 */
public class ChannelsInstance {

    public interface ChannelsInstancesCallback{
        public void onClickDelete(Channel channel);
        public void onClickManage(Channel channel);

    }

    private static final ProvidesKey<Channel> INSTANCES_KEY_PROVIDER = new ProvidesKey<Channel>() {
        @Override
        public Object getKey(Channel channel) {
            return  channel == null ? null : channel.getId();
        }
    };
    private FlowPanel layoutContainer = new FlowPanel();
    private CellTable<Channel> instancesTable;
    private SingleSelectionModel<Channel> selectionModel;
    private ListDataProvider<Channel> provider;

    private final ConnectorTableResources resources = GWT
            .create(ConnectorTableResources.class);

    public Widget getMainView(){
        return layoutContainer;
    }

    private ChannelsInstancesCallback callback;

    public ChannelsInstance(List<Channel>channels, ChannelsInstancesCallback callback){
        this.callback = callback;

        List<Channel> tableChannels = new ArrayList<>();
        for (Channel c: channels){
            if (c.isConnected()) {
                tableChannels.add(c);
            }
        }

        if(tableChannels.size() != 0) {
//            tableChannels.add(channels.get(0));
//        }
            provider = new ListDataProvider<Channel>();
            provider.setList(tableChannels);
            selectionModel = new SingleSelectionModel<Channel>(
                    INSTANCES_KEY_PROVIDER);
            initInstancesTable();
            provider.addDataDisplay(instancesTable);
            layoutContainer.add(instancesTable);
        }
    }


    public void initInstancesTable(){
        instancesTable = new CellTable<Channel>(10, resources,
                INSTANCES_KEY_PROVIDER);
        instancesTable.setSelectionModel(selectionModel);
        initTableColumns();
    }

    private void initTableColumns() {

        Column<Channel, ImageResource> iconColumn = new Column<Channel, ImageResource>(
                new ImageResourceCell()) {

            @Override
            public ImageResource getValue(Channel channel) {
                return ChannelsInstance.this.getImageURLs(channel.getChannelName());
            }

        };

        instancesTable.addColumn(iconColumn,"");
//
        instancesTable.setColumnWidth(iconColumn, 85, Style.Unit.PX);
//
        Column<Channel, String> nameColumn = new Column<Channel, String>(
                new TextCell()) {

            @Override
            public String getValue(Channel channel) {
                return channel.getTitle();
            }

        };

        instancesTable.addColumn(nameColumn,"");

        Column<Channel, String> channelText = new Column<Channel, String>(
                new TextCell()) {

            @Override
            public String getValue(Channel channel) {
                return channel.getChannelText();
            }
        };

        instancesTable.addColumn(channelText, "");

        Column<Channel, Channel> manageColumn = new Column<Channel, Channel>(
                new ManageCell()) {
            @Override
            public Channel getValue(Channel channel) {
                return channel;
            }
        };
        instancesTable.addColumn(manageColumn,
                SafeHtmlUtils.fromSafeConstant(""));
        instancesTable.setColumnWidth(manageColumn, 20, Style.Unit.PX);


        Column<Channel, Channel> editColumn = new Column<Channel, Channel>(
                new DeleteCell()) {
            @Override
            public Channel getValue(Channel channel) {
                if(channel.isAllowingDelete()) {
                    return channel;
                }else{
                    return null;
                }
            }
        };

        instancesTable.addColumn(editColumn,
                SafeHtmlUtils.fromSafeConstant(""));
        instancesTable.setColumnWidth(editColumn, 20, Style.Unit.PX);

    }

    private ImageResource getImageURLs(ChannelsNames channelName) {

        switch (channelName) {
            case SLACK:
                return ChannelsImages.INSTANCE.slack();

            case PINTEREST:
                return ChannelsImages.INSTANCE.pinterest();


            case WHATSAPP:
                return ChannelsImages.INSTANCE.whatsapp();

            case EMAIL:
                return ChannelsImages.INSTANCE.email();

        }
        return null;
    }


    public class ManageCell extends AbstractCell<Channel> {



        public ManageCell() {
            super("click");

        }

        @Override
        public void onBrowserEvent(Context context, Element parent,
                                   Channel value, NativeEvent event,
                                   ValueUpdater<Channel> valueUpdater) {
            super.onBrowserEvent(context, parent, value, event, valueUpdater);

            GWT.log("Event: " + event.getType());

            if ("click".equals(event.getType())) {
                EventTarget eventTarget = event.getEventTarget();
                if (parent.getFirstChildElement().isOrHasChild(
                        Element.as(eventTarget))) {
                        callback.onClickManage(value);
                }
            }

        }

        @Override
        public void render(com.google.gwt.cell.client.Cell.Context arg0,
                           Channel channel, SafeHtmlBuilder sb) {
            if(channel!=null) {
                sb.appendHtmlConstant("<div class=\"actigate-admin-connectors-editconnector-uploadbutton"
                            + "\">");

                sb.appendHtmlConstant("Manage");


                sb.appendHtmlConstant("</div>");
                sb.toSafeHtml();
            }
        }
    }

    public class DeleteCell extends AbstractCell<Channel> {


        public DeleteCell() {
            super("click");

        }

        @Override
        public void onBrowserEvent(Context context, Element parent,
                                   Channel value, NativeEvent event,
                                   ValueUpdater<Channel> valueUpdater) {

            super.onBrowserEvent(context, parent, value, event, valueUpdater);

            super.onBrowserEvent(context, parent, value, event, valueUpdater);

            GWT.log("Event: " + event.getType());

            if ("click".equals(event.getType())) {
                EventTarget eventTarget = event.getEventTarget();
                if (parent.getFirstChildElement().isOrHasChild(
                        Element.as(eventTarget))) {
                    callback.onClickDelete(value);
                }
            }

        }

        @Override
        public void render(com.google.gwt.cell.client.Cell.Context arg0,
                           Channel channel, SafeHtmlBuilder sb) {
            if(channel!=null) {
                if (!channel.isAllowingDelete()) {

                    sb.appendHtmlConstant("<div style=\"display:none" + "\">");
                } else {

                    sb.appendHtmlConstant("<div class=\"actigate-admin-connectors-editconnector-uploadbutton"
                            + "\">");
                }

                sb.appendHtmlConstant("Delete");


                sb.appendHtmlConstant("</div>");
                sb.toSafeHtml();
            }

        }

    }
}
