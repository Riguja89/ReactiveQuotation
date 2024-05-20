package com.jaime.model.Quotation.events;

import com.jaime.model.generic.DomainEvent;

public class GroupQuotesAdded extends DomainEvent {
    String groupQuotesId;
    String nameClient;
    Float totalGroupDiscount;
    Float totalGroupQuote;

    public GroupQuotesAdded() {
        super(EventsEnum.GROUP_QUOTES_ADDED.toString());
    }

    public GroupQuotesAdded( String nameClient, String groupQuotesId, Float totalGroupDiscount, Float totalGroupQuote) {
        super(EventsEnum.GROUP_QUOTES_ADDED.toString());
        this.nameClient = nameClient;
        this.groupQuotesId = groupQuotesId;
        this.totalGroupDiscount = totalGroupDiscount;
        this.totalGroupQuote = totalGroupQuote;
    }

    public String getGroupQuotesId() {
        return groupQuotesId;
    }

    public void setGroupQuotesId(String groupQuotesId) {
        this.groupQuotesId = groupQuotesId;
    }

    public String getNameClient() {
        return nameClient;
    }

    public void setNameClient(String nameClient) {
        this.nameClient = nameClient;
    }

    public Float getTotalGroupDiscount() {
        return totalGroupDiscount;
    }

    public void setTotalGroupDiscount(Float totalGroupDiscount) {
        this.totalGroupDiscount = totalGroupDiscount;
    }

    public Float getTotalGroupQuote() {
        return totalGroupQuote;
    }

    public void setTotalGroupQuote(Float totalGroupQuote) {
        this.totalGroupQuote = totalGroupQuote;
    }
}
