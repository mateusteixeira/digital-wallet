package br.com.dwallet.model;

import br.com.dwallet.model.repository.OperationErrorRepository;
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
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@EnableAutoConfiguration
@ContextConfiguration(classes = {UserRepository.class, OperationErrorRepository.class})
class OperationErrorRepositoryTest {

    private static final String CONNECTION_STRING = "mongodb://%s:%d";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OperationErrorRepository operationErrorRepository;

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
    public void should_retrieve_operation_error_by_id_user() {
        OperationError operationError = OperationErrorHelper.withIdUser("123");
        operationError = operationErrorRepository.save(operationError);

        List<OperationError> byUserId = operationErrorRepository.findByIdUser("123", Pageable.unpaged());
        assertNotNull(byUserId);
        assertEquals(1, byUserId.size());
        assertEquals(operationError, byUserId.get(0));
    }

    @Test
    public void should_not_retrieve_operation_error_by_id_user_when_different() {
        OperationError operationError = OperationErrorHelper.withIdUser("1234");
        operationErrorRepository.save(operationError);

        List<OperationError> byUserId = operationErrorRepository.findByIdUser("12344", Pageable.unpaged());
        assertNotNull(byUserId);
        assertEquals(0, byUserId.size());
    }


}