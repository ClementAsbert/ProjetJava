package test.java;

import main.java.Client;
import main.java.ClientManager;
import main.java.Interface.ClientManagerInterface;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;

public class ClientManagerTest {

    private ClientManagerInterface clientManagerInterface;
    private Client client;
    @Before
    public void setUp() throws Exception {
        clientManagerInterface = new ClientManager();
        client = new Client("name","","","");
    }

    @Test
    public void getListClient() {
        this.clientManagerInterface.addClient(client);
        Assert.assertEquals(this.clientManagerInterface.getListClient().size(), 1);
    }

    @Test
    public void addClient() {
        Assert.assertEquals(this.clientManagerInterface.getListClient().size(), 0);
        this.clientManagerInterface.addClient(client);
        Assert.assertEquals(this.clientManagerInterface.getListClient().size(), 1);
    }

    @Test
    public void findClient() {
        this.clientManagerInterface.addClient(client);
        Assert.assertEquals(this.clientManagerInterface.findClient("name"), Optional.of(client));
    }
}