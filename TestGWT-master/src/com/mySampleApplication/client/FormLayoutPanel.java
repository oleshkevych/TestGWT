package com.mySampleApplication.client;

import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.ui.*;

/**
 * Created by rolique_pc on 10/18/2016.
 */
public class FormLayoutPanel extends Composite {

    public enum LabelPosition {
        LEFT, TOP, RIGHT
    }

    protected class FormCursor {
        int column;
        int row;

        int top;
        int left;

        protected FormCursor() {
            reset();
        }

        protected void moveNext() {
            if (column < (columns.length - 1)) {
                left = left + columns[column].intValue() + columnSpacing;
                column++;
            } else {
                moveNextRow();
            }
        }

        protected void moveNextRow() {
            column = 0;
            left = columnSpacing;
            row++;
            if (labelPosition == LabelPosition.LEFT) {
                top = top + rowHeight + rowSpacing;
            } else {
                top = top + 2 * rowHeight + rowSpacing;
            }
        }

        protected void reset() {
            column = 0;
            left = columnSpacing;

            row = 0;
            top = rowSpacing;
        }
    }

    private LabelPosition labelPosition;

    private int rowHeight = 22;
    private int rowSpacing;
    private int columnSpacing;

    private Integer[] columns;

    private FormCursor cursor;

    private LayoutPanel layout;

    public FormLayoutPanel(Integer... columns) {
        init(LabelPosition.LEFT, columns);
    }

    public FormLayoutPanel(FormLayoutPanel.LabelPosition position,
                           Integer... columns) {
        init(position, columns);
    }

    private void init(FormLayoutPanel.LabelPosition position,
                      Integer... columns) {
        layout = new LayoutPanel();
        // layout.getElement().getStyle().setBackgroundColor("#aaaaaa");
        labelPosition = position;
        initWidget(layout);
        rowSpacing = 10;
        columnSpacing = 11;
        cursor = new FormCursor();
        this.columns = columns;
    }

    @Override
    public void setSize(String width, String height) {
        layout.setSize(width, height);
    }

    @Override
    public void setPixelSize(int width, int height) {
        layout.setPixelSize(width, height);
    }

    public void setRowSpacing(int spacing) {
        rowSpacing = spacing;
    }

    public void setRowHeight(int rowHeight) {
        this.rowHeight = rowHeight;
    }

    public int getRowSpacing() {
        return rowSpacing;
    }

    public void setColumnSpacing(int spacing) {
        columnSpacing = spacing;
    }

    public int getColumnSpacing() {
        return columnSpacing;
    }

    public void append(Widget widget) {
        append("", widget);
    }

    public void append(String text, Widget widget) {
        append(new Label(text), widget);
    }

    public void append(String text, Widget widget, int colSpan) {
        append(new Label(text), widget, colSpan);
    }

    public void append(String text, Widget widget, int colSpan, int height) {
        append(new Label(text), widget, colSpan, height);
    }

    /**
     *
     * @param label
     * @param widget
     */
    public void append(Label label, Widget widget) {
        append(label, widget, 1);
    }

    public void append(Label label, Widget widget, int colSpan) {
        append(label, widget, colSpan, rowHeight);
    }

    public void append(Label label, Widget widget, int colSpan, int height) {
        label.setSize("100%", "100%");

        String s = label.getText();
        if (!s.equals("")) {
            label.setText(label.getText());
        }

        int labelTop;
        int labelHeight;
        int labelLeft;
        int labelWidth;

        int widgetTop;
        int widgetHeight;
        int widgetLeft;
        int widgetWidth;

        if (labelPosition == LabelPosition.LEFT) {
            labelTop = cursor.top;
            labelHeight = rowHeight;
            labelLeft = cursor.left;
            labelWidth = columns[cursor.column].intValue();

            cursor.moveNext();

            widgetTop = labelTop;
            widgetHeight = height;
            widgetLeft = cursor.left;
            widgetWidth = 0;
            for (int c = 0; c < colSpan; c++) {
                widgetWidth = widgetWidth + columns[cursor.column].intValue();
                cursor.moveNext();
            }

        } else { // Right now TOP
            labelTop = cursor.top;
            labelHeight = rowHeight;
            labelLeft = cursor.left;
            labelWidth = columns[cursor.column].intValue();

            widgetTop = labelTop + labelHeight + 2;
            widgetHeight = height;
            widgetLeft = cursor.left;
            widgetWidth = 0;
            for (int c = 0; c < colSpan; c++) {
                widgetWidth = widgetWidth + columns[cursor.column].intValue();
                cursor.moveNext();
            }

        }

        label.setPixelSize(labelWidth, labelHeight);
        label.getElement().getStyle().setVerticalAlign(Style.VerticalAlign.MIDDLE);
        label.getElement()
                .getStyle()
                .setProperty("lineHeight", Integer.toString(labelHeight) + "px");
        label.getElement().getStyle().setPadding(2, Style.Unit.PX);
        layout.add(label);
        layout.setWidgetTopHeight(label, labelTop, Style.Unit.PX, labelHeight + 4,
                Style.Unit.PX);
        layout.setWidgetLeftWidth(label, labelLeft, Style.Unit.PX, labelWidth,
                Style.Unit.PX);

        widget.setWidth(widgetWidth + "px");
        layout.add(widget);
        layout.setWidgetTopHeight(widget, widgetTop, Style.Unit.PX, widgetHeight + 4,
                Style.Unit.PX);
        layout.setWidgetLeftWidth(widget, widgetLeft, Style.Unit.PX, widgetWidth + 8,
                Style.Unit.PX);

        if (height > rowHeight) {
            cursor.top = cursor.top + (height - rowHeight);
        }
    }

    /**
     *
     * @param label
     * @param widget
     */
    public void append(String label, Widget... widgets) {
        append(new Label(label), widgets);
    }

    /**
     *
     * @param label
     * @param widget
     */
    public void append(Label label, Widget... widgets) {
        if ((widgets.length + 1) != columns.length) {
            return;
        }

        int labelTop;
        int labelHeight;
        int labelLeft;
        int labelWidth;

        int widgetTop;
        int widgetHeight;
        int widgetLeft;
        int widgetWidth;

        labelTop = cursor.top;
        labelHeight = rowHeight;
        labelLeft = cursor.left;
        labelWidth = columns[cursor.column].intValue();

        label.setText(label.getText());
        label.setPixelSize(labelWidth, labelHeight);
        label.getElement().getStyle().setVerticalAlign(Style.VerticalAlign.MIDDLE);
        label.getElement().getStyle().setPadding(2, Style.Unit.PX);

        layout.add(label);
        layout.setWidgetTopHeight(label, labelTop, Style.Unit.PX, labelHeight,
                Style.Unit.PX);
        layout.setWidgetLeftWidth(label, labelLeft, Style.Unit.PX, labelWidth,
                Style.Unit.PX);

        cursor.moveNext();

        for (Widget widget : widgets) {
            widgetTop = labelTop;
            widgetHeight = rowHeight;
            widgetLeft = cursor.left;
            widgetWidth = columns[cursor.column].intValue();

            widget.setWidth(widgetWidth + "px");
            layout.add(widget);
            layout.setWidgetTopHeight(widget, widgetTop, Style.Unit.PX,
                    widgetHeight + 4, Style.Unit.PX);
            layout.setWidgetLeftWidth(widget, widgetLeft, Style.Unit.PX,
                    widgetWidth + 8, Style.Unit.PX);
            cursor.moveNext();
        }
    }

    public void appendAsNewRow(String text, Widget widget) {

        append(new Label(text), widget);
        cursor.moveNextRow();

    }

    public void clear() {
        cursor.reset();
        layout.clear();
    }

    public void forceLayout() {
        layout.forceLayout();
    }

    public Widget getWidget(int index) {
        return layout.getWidget(index);
    }

    public Element getWidgetContainerElement(Widget child) {
        return layout.getWidgetContainerElement(child);
    }

    public int getWidgetCount() {
        return layout.getWidgetCount();
    }

    public int getWidgetIndex(IsWidget child) {
        return layout.getWidgetIndex(child);
    }

    public int getWidgetIndex(Widget child) {
        return layout.getWidgetIndex(child);
    }

}
