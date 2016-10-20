package com.mySampleApplication.client.resurses;

import com.google.gwt.user.cellview.client.CellTable;

public interface ConnectorTableResources extends CellTable.Resources {
	interface TableStyle extends CellTable.Style {

		@Override
		String cellTableCell();
		@Override
		String cellTableEvenRow();
		@Override
		String cellTableOddRow();
		@Override
		String cellTableHoveredRow();
		@Override
		String cellTableHoveredRowCell();
		@Override
		String cellTableSelectedRow();
		@Override
		String cellTableSelectedRowCell();
		@Override
		String cellTableHeader();

	}
	@Override
	@Source({CellTable.Style.DEFAULT_CSS, "ConnectorTable.css"})
	TableStyle cellTableStyle();
}