package testutil;

import com.google.inject.Inject;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class GenericTable {

    private final Page page;
    private final Locator table;
    private Map<String, Integer> headerIndexMap;

    @Inject
    public GenericTable(Page page) {
        this.page = page;
        this.table = page.locator("div.oxd-table[role='table']");
    }

    /* =========================
       LOCATORS
       ========================= */

    private Locator headerCells() {
        return table.locator("div[role='columnheader']");
    }

    private Locator dataRows() {
        return table.locator("div.oxd-table-body div[role='row']");
    }

    /* =========================
       HEADER RESOLUTION
       ========================= */

    private void buildHeaderIndexIfNeeded() {
        if (headerIndexMap != null) return;

        headerIndexMap = new HashMap<>();

        // Virtual column for checkbox
        headerIndexMap.put("__checkbox__", 0);

        int headerCount = headerCells().count();

        for (int i = 0; i < headerCount; i++) {
            String rawText = headerCells().nth(i).innerText();
            String cleanText = normalizeHeaderText(rawText);

            // +1 offset because checkbox column exists
            headerIndexMap.put(cleanText, i + 1);
        }
    }

    private String normalizeHeaderText(String text) {
        return Pattern.compile("\\s+")
                .matcher(text)
                .replaceAll(" ")
                .trim();
    }

    public int getColumnIndex(String headerName) {
        buildHeaderIndexIfNeeded();

        Integer index = headerIndexMap.get(headerName);
        if (index == null) {
            throw new RuntimeException("Header not found: " + headerName);
        }
        return index;
    }

    /* =========================
       CELL ACCESS
       ========================= */

    public Locator getCell(int rowIndex, String headerName) {
        int colIndex = getColumnIndex(headerName);

        return dataRows()
                .nth(rowIndex)
                .locator("div[role='cell']")
                .nth(colIndex);
    }

    public String getCellText(
            String searchHeader,
            String searchValue,
            String targetHeader) {

        int rowIndex = findRowIndex(searchHeader, searchValue);
        return getCell(rowIndex, targetHeader).innerText();
    }

    /* =========================
       ROW SEARCH
       ========================= */

    public int findRowIndex(String headerName, String expectedText) {
        int colIndex = getColumnIndex(headerName);
        int rowCount = dataRows().count();

        for (int i = 0; i < rowCount; i++) {
            String cellText = dataRows()
                    .nth(i)
                    .locator("div[role='cell']")
                    .nth(colIndex)
                    .innerText()
                    .trim();

            if (cellText.equalsIgnoreCase(expectedText)) {
                return i;
            }
        }

        throw new RuntimeException("Row not found where " + headerName + " = " + expectedText);
    }

    /* =========================
       COMMON ACTIONS
       ========================= */

    public void selectRowCheckbox(String headerName, String value) {
        int rowIndex = findRowIndex(headerName, value);

        dataRows()
                .nth(rowIndex)
                .locator("input[type='checkbox']")
                .click();
    }

    public void clickAction(String headerName, String value, ActionType action) {
        int rowIndex = findRowIndex(headerName, value);

        String iconSelector = switch (action) {
            case DELETE -> "i.bi-trash";
            case EDIT -> "i.bi-pencil-fill";
        };

        dataRows()
                .nth(rowIndex)
                .locator(iconSelector)
                .click();
    }
}
