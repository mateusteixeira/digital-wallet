package br.com.dwallet.model;

import br.com.dwallet.model.repository.WalletAccountRepository;
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

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@EnableAutoConfiguration
@ContextConfiguration(classes = {UserRepository.class, WalletAccountRepository.class})
class WalletAccountRepositoryTest {

    private static final String CONNECTION_STRING = "mongodb://%s:%d";

    @Autowired
    private UserRepository userRepository;

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
    public void should_retrieve_wallet_account_by_account_number() {
        WalletAccount walletAccount = WalletAccountHelper.walletAccountWithNumber(123L);
        walletAccount = walletAccountRepository.save(walletAccount);

        WalletAccount byAccountNumber = walletAccountRepository.findByAccountNumber(123L);
        assertNotNull(byAccountNumber);
        assertEquals(walletAccount, byAccountNumber);
    }

    @Test
    public void should_not_retrieve_wallet_account_by_account_number_when_not_equals() {
        WalletAccount walletAccount = WalletAccountHelper.walletAccountWithNumber(12332L);
        walletAccountRepository.save(walletAccount);

        WalletAccount byAccountNumber = walletAccountRepository.findByAccountNumber(123L);
        assertNull(byAccountNumber);
    }

    @Test
    public void should_retrieve_wallet_account_by_user() {
        User user = UserHelper.user();
        user = userRepository.save(user);

        WalletAccount walletAccount = WalletAccountHelper.walletAccountWithUser(user);
        walletAccount = walletAccountRepository.save(walletAccount);

        List<WalletAccount> byUserId = walletAccountRepository.findByUserId(user.getId());
        assertNotNull(byUserId);
        assertEquals(1, byUserId.size());
        assertEquals(walletAccount, byUserId.get(0));
    }

    @Test
    public void should_retrieve_all_wallet_account_by_user() {
        User user = UserHelper.user();
        user = userRepository.save(user);

        WalletAccount walletAccount = WalletAccountHelper.walletAccountWithUser(user);
        walletAccountRepository.save(walletAccount);
        WalletAccount walletAccount2 = WalletAccountHelper.walletAccountWithUser(user);
        walletAccountRepository.save(walletAccount2);

        List<WalletAccount> byUserId = walletAccountRepository.findByUserId(user.getId());
        assertNotNull(byUserId);
        assertEquals(2, byUserId.size());
    }

    @Test
    public void should_not_retrieve_wallet_account_by_user_when_other_user() {
        User user = UserHelper.user();
        user = userRepository.save(user);
        User user2 = UserHelper.user();
        user2 = userRepository.save(user2);

        WalletAccount walletAccount = WalletAccountHelper.walletAccountWithUser(user);
        walletAccountRepository.save(walletAccount);

        List<WalletAccount> byUserId = walletAccountRepository.findByUserId(user2.getId());
        assertNotNull(byUserId);
        assertEquals(0, byUserId.size());
    }

    @Test
    public void should_retrieve_wallet_by_id_and_id_user() {
        User user = UserHelper.user();
        user = userRepository.save(user);
        WalletAccount walletAccount = WalletAccountHelper.walletAccountWithUser(user);
        walletAccountRepository.save(walletAccount);

        WalletAccount byIdAndUserId = walletAccountRepository.findByIdAndUserId(walletAccount.getId(), user.getId());
        assertNotNull(byIdAndUserId);
        assertEquals(walletAccount, byIdAndUserId);
    }

    @Test
    public void should_not_retrieve_wallet_by_id_and_id_user_when_id_user_different() {
        User user = UserHelper.user();
        user = userRepository.save(user);
        User user2 = UserHelper.user();
        user2 = userRepository.save(user2);
        WalletAccount walletAccount = WalletAccountHelper.walletAccountWithUser(user);
        walletAccountRepository.save(walletAccount);

        WalletAccount byIdAndUserId = walletAccountRepository.findByIdAndUserId(walletAccount.getId(), user2.getId());
        assertNull(byIdAndUserId);
    }

}