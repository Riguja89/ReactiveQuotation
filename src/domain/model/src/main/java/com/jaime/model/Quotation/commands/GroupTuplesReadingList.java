package com.jaime.model.Quotation.commands;

import java.util.List;

public class GroupTuplesReadingList {
    private List<TupleReadingAmount> readingAmountList;

    public GroupTuplesReadingList() {
    }

    public List<TupleReadingAmount> getReadingAmountList() {
        return readingAmountList;
    }

    public void setReadingAmountList(List<TupleReadingAmount> readingAmountList) {
        this.readingAmountList = readingAmountList;
    }
}
