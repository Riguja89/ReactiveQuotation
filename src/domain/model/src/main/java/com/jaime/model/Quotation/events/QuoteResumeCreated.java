package com.jaime.model.Quotation.events;

import com.jaime.model.generic.DomainEvent;

public class QuoteResumeCreated extends DomainEvent {
    private String quoteResumeId;
    private String groupQuotesId;
    private String nameClient;
    private String startDateClient;
    private String quoteType;
    private Float totaDiscount;
    private Float resumeTotal;

    public QuoteResumeCreated() {
        super(EventsEnum.QUOTE_RESUME_CREATED.toString());

    }

    public QuoteResumeCreated(
            String quoteResumeId,
            String nameClient,
            String startDateClient,
            String quoteType,
            Float totaDiscount,
            Float resumeTotal
    ) {
        super(EventsEnum.QUOTE_RESUME_CREATED.toString());
        this.quoteResumeId = quoteResumeId;
        this.nameClient = nameClient;
        this.startDateClient = startDateClient;
        this.quoteType = quoteType;
        this.totaDiscount = totaDiscount;
        this.resumeTotal = resumeTotal;
        this.groupQuotesId="default";
    }

    public QuoteResumeCreated(
            String quoteResumeId,
            String groupQuotesId,
            String nameClient,
            String startDateClient,
            String quoteType,
            Float totaDiscount,
            Float resumeTotal) {
        super(EventsEnum.QUOTE_RESUME_CREATED.toString());
        this.quoteResumeId = quoteResumeId;
        this.groupQuotesId = groupQuotesId;
        this.nameClient = nameClient;
        this.startDateClient = startDateClient;
        this.quoteType = quoteType;
        this.totaDiscount = totaDiscount;
        this.resumeTotal = resumeTotal;
    }

    public String getGroupQuotesId() {
        return groupQuotesId;
    }

    public void setGroupQuotesId(String groupQuotesId) {
        this.groupQuotesId = groupQuotesId;
    }

    public String getQuoteResumeId() {
        return quoteResumeId;
    }

    public void setQuoteResumeId(String quoteResumeId) {
        this.quoteResumeId = quoteResumeId;
    }

    public String getNameClient() {
        return nameClient;
    }

    public void setNameClient(String nameClient) {
        this.nameClient = nameClient;
    }

    public String getStartDateClient() {
        return startDateClient;
    }

    public void setStartDateClient(String startDateClient) {
        this.startDateClient = startDateClient;
    }

    public String getQuoteType() {
        return quoteType;
    }

    public void setQuoteType(String quoteType) {
        this.quoteType = quoteType;
    }

    public Float getTotaDiscount() {
        return totaDiscount;
    }

    public void setTotaDiscount(Float totaDiscount) {
        this.totaDiscount = totaDiscount;
    }

    public Float getResumeTotal() {
        return resumeTotal;
    }

    public void setResumeTotal(Float resumeTotal) {
        this.resumeTotal = resumeTotal;
    }
}
