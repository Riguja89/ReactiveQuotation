package com.jaime.model.Quotation.commands;

import com.jaime.model.generic.Command;

import java.util.List;

public class SetGroupQuotesCommand extends Command {
    private List<GroupTuplesReadingList> groupTuplesReadingLists;
    private String clientId;

    public SetGroupQuotesCommand() {
    }

    public List<GroupTuplesReadingList> getGroupTuplesReadingLists() {
        return groupTuplesReadingLists;
    }

    public void setGroupTuplesReadingLists(List<GroupTuplesReadingList> groupTuplesReadingLists) {
        this.groupTuplesReadingLists = groupTuplesReadingLists;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }
}
