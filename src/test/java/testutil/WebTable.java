package testutil;

import com.google.inject.Inject;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import java.util.List;

public class WebTable {
    private final Page page;
    private final Locator tableRoot;
    private final Locator rows;
    private final Locator headers;

    @Inject
    public WebTable(Page page) {
        this.page = page;
        this.tableRoot = page.locator(".oxd-table");               //tableRoot;
        // We define the sub-locators relative to the table root
        this.rows = tableRoot.locator(".oxd-table-card");
        this.headers = tableRoot.locator(".oxd-table-header-cell");
    }

    public int getRowCount() {
        return rows.count();
    }

    public List<String> getAllHeaderNames() {
        return headers.allInnerTexts();
    }

    public int searchDataInColoumn(String colName, String value) {

        int headerSize = getAllHeaderNames().size();
        int colNum =0;
        int searchRowNum=0;
        for(int i=0; i<headerSize;i++){
            if(getAllHeaderNames().get(i).contains(colName)){
                colNum =i;
                break;
            }
        }
        for(int j=0; j<getRowCount();j++){

            if(getCellValue(j, colNum).equals(value)){
                searchRowNum= j;
                break;
            }
        }
        return searchRowNum;
    }









    /**
     * Gets text from a specific cell based on row and column index (0-based)
     */
    public String getCellValue(int rowIndex, int colIndex) {
        return rows.nth(rowIndex)
                .locator(".oxd-table-cell")
                .nth(colIndex)
                .innerText().trim();
    }


    public void performActionOnCell(int rowIndex, int colIndex) {
        rows.nth(rowIndex)
                .locator(".oxd-table-cell")
                .nth(colIndex)
                .click();

    }






    /**
     * Finds a row containing a specific text (e.g., "Admin")
     * and clicks a specific action button inside that row.
     */
    public void performActionOnRow(String rowText, String action) {
        Locator targetRow = rows.filter(new Locator.FilterOptions().setHasText(rowText));

        if (action.equalsIgnoreCase("edit")) {
            targetRow.locator(".bi-pencil-fill").click();
        } else if (action.equalsIgnoreCase("delete")) {
            targetRow.locator(".bi-trash").click();
        }
    }

    /**
     * Selects the checkbox for a specific row identified by text.
     */
    public void selectRowCheckbox(String rowText) {
        rows.filter(new Locator.FilterOptions().setHasText(rowText))
                .locator(".oxd-checkbox-input")
                .click();
    }
}
