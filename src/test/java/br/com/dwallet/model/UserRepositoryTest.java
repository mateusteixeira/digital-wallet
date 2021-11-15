package br.com.dwallet.model;

import com.mongodb.client.MongoClients;
import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.ImmutableMongodConfig;
import de.flapdoodle.embed.mongo.config.MongodConfig;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@EnableAutoConfiguration
@ContextConfiguration(classes = {UserRepository.class})
class UserRepositoryTest {

    private static final String CONNECTION_STRING = "mongodb://%s:%d";

    @Autowired
    private UserRepository userRepository;

    private MongodExecutable mongodExecutable;
    private MongoTemplate mongoTemplate;


    @AfterEach
    public void clean() {
        mongodExecutable.stop();
    }

    @BeforeEach
    public void setup() throws Exception {
        String ip = "localhost";
        int port = 27017;

        ImmutableMongodConfig mongodConfig = MongodConfig
                .builder()
                .version(Version.Main.PRODUCTION)
                .net(new Net(ip, port, Network.localhostIsIPv6()))
                .build();

        MongodStarter starter = MongodStarter.getDefaultInstance();
        mongodExecutable = starter.prepare(mongodConfig);
        mongodExecutable.start();
        mongoTemplate = new MongoTemplate(MongoClients.create(String.format(CONNECTION_STRING, ip, port)), "test");
    }

    @Test
    public void should_retrieve_user_by_document() {
        User user = UserHelper.userWithDocument("09009090900");
        user = userRepository.save(user);

        User byDocument = userRepository.findByDocument("09009090900");
        assertNotNull(byDocument);
        assertEquals(user, byDocument);
    }

    @Test
    public void should_not_retrieve_user_by_document_different() {
        User user = UserHelper.userWithDocument("0900390900");
        userRepository.save(user);

        User byDocument = userRepository.findByDocument("09009090900");
        assertNull(byDocument);
    }


}