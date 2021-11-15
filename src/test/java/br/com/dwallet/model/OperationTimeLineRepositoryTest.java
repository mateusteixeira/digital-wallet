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
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@EnableAutoConfiguration
@ContextConfiguration(classes = {UserRepository.class, OperationTimeLineRepository.class, WalletAccountRepository.class})
public class OperationTimeLineRepositoryTest {

    private static final String CONNECTION_STRING = "mongodb://%s:%d";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OperationTimeLineRepository operationTimeLineRepository;

    @Autowired
    private WalletAccountRepository walletAccountRepository;

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
    public void should_retrieve_operation_time_lime_by_id_user() {
        User user = UserHelper.user();
        User save = userRepository.save(user);
        OperationTimeLine operationTimeLine = OperationTimeLineHelper.withUser(user);
        operationTimeLineRepository.save(operationTimeLine);
        List<OperationTimeLine> byUserId = operationTimeLineRepository.findByUserId(save.getId(), Pageable.unpaged());
        assertNotNull(byUserId);
        assertEquals(1, byUserId.size());
        assertEquals(save, byUserId.get(0).getUser());
    }

    @Test
    public void should_retrieve_all_operation_time_lime_by_id_user() {
        User user = UserHelper.user();
        User save = userRepository.save(user);
        OperationTimeLine operationTimeLine = OperationTimeLineHelper.withUser(user);
        operationTimeLineRepository.save(operationTimeLine);
        OperationTimeLine operationTimeLine2 = OperationTimeLineHelper.withUser(user);
        operationTimeLineRepository.save(operationTimeLine2);
        List<OperationTimeLine> byUserId = operationTimeLineRepository.findByUserId(save.getId(), Pageable.unpaged());
        assertNotNull(byUserId);
        assertEquals(2, byUserId.size());
        assertEquals(save, byUserId.get(0).getUser());
    }

    @Test
    public void should_not_retrieve_operation_time_lime_by_id_user_when_another_user() {
        User anotherUser = UserHelper.user();
        anotherUser = userRepository.save(anotherUser);
        User user = UserHelper.user();
        userRepository.save(user);
        OperationTimeLine operationTimeLine = OperationTimeLineHelper.withUser(user);
        operationTimeLineRepository.save(operationTimeLine);
        OperationTimeLine operationTimeLine2 = OperationTimeLineHelper.withUser(user);
        operationTimeLineRepository.save(operationTimeLine2);
        List<OperationTimeLine> byUserId = operationTimeLineRepository.findByUserId(anotherUser.getId(), Pageable.unpaged());
        assertNotNull(byUserId);
        assertEquals(0, byUserId.size());
    }

    @Test
    public void should_retrieve_operation_time_life_by_user_and_wallet_account() {
        User user = UserHelper.user();
        WalletAccount walletAccount = WalletAccountHelper.walletAccount();
        user = userRepository.save(user);
        walletAccount = walletAccountRepository.save(walletAccount);
        OperationTimeLine operationTimeLine = OperationTimeLineHelper.withUserAndWalletAccount(user, walletAccount);
        operationTimeLineRepository.save(operationTimeLine);

        List<OperationTimeLine> byUserIdAndWalletAccountId = operationTimeLineRepository.findByUserIdAndWalletAccountId(user.getId(), walletAccount.getId(), Pageable.unpaged());
        assertNotNull(byUserIdAndWalletAccountId);
        assertEquals(1, byUserIdAndWalletAccountId.size());
        OperationTimeLine operationTimeLineRetrieved = byUserIdAndWalletAccountId.get(0);
        assertNotNull(operationTimeLineRetrieved);
        assertEquals(user, operationTimeLineRetrieved.getUser());
        assertEquals(walletAccount, operationTimeLineRetrieved.getWalletAccount());
    }

    @Test
    public void should_retrieve_all_operation_time_life_by_user_and_wallet_account() {
        User user = UserHelper.user();
        WalletAccount walletAccount = WalletAccountHelper.walletAccount();
        user = userRepository.save(user);
        walletAccount = walletAccountRepository.save(walletAccount);
        OperationTimeLine operationTimeLine = OperationTimeLineHelper.withUserAndWalletAccount(user, walletAccount);
        operationTimeLineRepository.save(operationTimeLine);
        OperationTimeLine operationTimeLine2 = OperationTimeLineHelper.withUserAndWalletAccount(user, walletAccount);
        operationTimeLineRepository.save(operationTimeLine2);

        List<OperationTimeLine> byUserIdAndWalletAccountId = operationTimeLineRepository.findByUserIdAndWalletAccountId(user.getId(), walletAccount.getId(), Pageable.unpaged());
        assertNotNull(byUserIdAndWalletAccountId);
        assertEquals(2, byUserIdAndWalletAccountId.size());
        OperationTimeLine operationTimeLineRetrieved = byUserIdAndWalletAccountId.get(0);
        assertNotNull(operationTimeLineRetrieved);
        assertEquals(user, operationTimeLineRetrieved.getUser());
        assertEquals(walletAccount, operationTimeLineRetrieved.getWalletAccount());
    }

    @Test
    public void should_not_retrieve_operation_time_life_by_user_different() {
        User user = UserHelper.user();
        User user2 = UserHelper.user();
        WalletAccount walletAccount = WalletAccountHelper.walletAccount();
        user = userRepository.save(user);
        user2 = userRepository.save(user2);
        walletAccount = walletAccountRepository.save(walletAccount);
        OperationTimeLine operationTimeLine = OperationTimeLineHelper.withUserAndWalletAccount(user2, walletAccount);
        operationTimeLineRepository.save(operationTimeLine);
        OperationTimeLine operationTimeLine2 = OperationTimeLineHelper.withUserAndWalletAccount(user2, walletAccount);
        operationTimeLineRepository.save(operationTimeLine2);

        List<OperationTimeLine> byUserIdAndWalletAccountId = operationTimeLineRepository.findByUserIdAndWalletAccountId(user.getId(), walletAccount.getId(), Pageable.unpaged());
        assertNotNull(byUserIdAndWalletAccountId);
        assertEquals(0, byUserIdAndWalletAccountId.size());
    }

    @Test
    public void should_not_retrieve_all_operation_time_life_by_wallet_account_different() {
        User user = UserHelper.user();
        WalletAccount walletAccount = WalletAccountHelper.walletAccount();
        WalletAccount walletAccount2 = WalletAccountHelper.walletAccount();
        user = userRepository.save(user);
        walletAccount = walletAccountRepository.save(walletAccount);
        walletAccount2 = walletAccountRepository.save(walletAccount2);
        OperationTimeLine operationTimeLine = OperationTimeLineHelper.withUserAndWalletAccount(user, walletAccount2);
        operationTimeLineRepository.save(operationTimeLine);
        OperationTimeLine operationTimeLine2 = OperationTimeLineHelper.withUserAndWalletAccount(user, walletAccount2);
        operationTimeLineRepository.save(operationTimeLine2);

        List<OperationTimeLine> byUserIdAndWalletAccountId = operationTimeLineRepository.findByUserIdAndWalletAccountId(user.getId(), walletAccount.getId(), Pageable.unpaged());
        assertNotNull(byUserIdAndWalletAccountId);
        assertEquals(0, byUserIdAndWalletAccountId.size());
    }
}


