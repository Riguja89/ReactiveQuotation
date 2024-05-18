package com.jaime.model.Quotation.entities.Client.values;

import com.jaime.model.generic.Identity;

public class ClientId extends Identity {
    public ClientId() {
        super();
    }

    private ClientId(String uuid) {
        super(uuid);
    }

    public static ClientId of(String uuid) {
        return new ClientId(uuid);
    }
}
