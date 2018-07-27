package telegram.bot.data;

import com.pengrad.telegrambot.TelegramBot;
import telegram.bot.data.chat.ChatData;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

public class Common {
    public static final String ETS_USERS = "/tmp/ets_users.ser";
    public static final String ETS_USERS_IN_VACATION = "/tmp/ets_users_in_vacation.ser";
    public static final String COMMON_INT_DATA = "/tmp/common.ser";
    public static final String ACTION_ITEMS2 = "/tmp/actionItems2.ser";
    public static final String JOKE_ITEMS = "/tmp/jokeItems.ser";
    public static final String JENKINS_STATUSES = "/tmp/jenkinsStatuses.ser";
    public static final String JIRA_CHECKER_STATUSES = "/tmp/jiraCheckerStatuses.ser";
    public static final String RESOLVED_ACTION_ITEMS = "/tmp/resolvedActionItems.ser";
    private static final Properties PROPERTIES = System.getProperties();
    public static final Common data = new Common();
    public static final LoginData JIRA = new LoginData(PROPERTIES, "atlassian.jira");
    public static final GoogleData GOOGLE = new GoogleData(PROPERTIES);
    public static final UpsourceData UPSOURCE = new UpsourceData(PROPERTIES);
    public static final long BIG_GENERAL_CHAT_ID;
    public static final long OLLIE_BALLOONIES_CHAT_ID;
    public static final long DEV_TALKS_CHAT_ID;
    public static final long OLLIE_ELECTRIC_TIGER_CHAT_ID;
    public static final long OLLIE_WILD_FURY_CHAT_ID;
    public static final long OLLIE_ACTION_JACK_CHAT_ID;
    public static final long OLLIE_CRAZY_WIZARD_CHAT_ID;
    public static final long TEST_FOR_BOT_GROUP_ID;
    public static final List<Long> BIG_GENERAL_GROUP_IDS;
    public static final String HELP_LINK;
    public static final String HELP_LINKS;
    public static final String BIG_HELP_LINKS;
    public static final String JENKINS_URL;
    public static final String JENKINS_JOBS_URL;

    static {
        BIG_GENERAL_CHAT_ID = Long.parseLong(PROPERTIES.getProperty("telegram.chat.BIG_GENERAL_CHAT_ID"));
        OLLIE_BALLOONIES_CHAT_ID = Long.parseLong(PROPERTIES.getProperty("telegram.chat.OLLIE_BALLOONIES_CHAT_ID"));
        DEV_TALKS_CHAT_ID = Long.parseLong(PROPERTIES.getProperty("telegram.chat.DEV_TALKS_CHAT_ID"));
        OLLIE_ELECTRIC_TIGER_CHAT_ID = Long.parseLong(PROPERTIES.getProperty("telegram.chat.OLLIE_ELECTRIC_TIGER_CHAT_ID"));
        OLLIE_WILD_FURY_CHAT_ID = Long.parseLong(PROPERTIES.getProperty("telegram.chat.OLLIE_WILD_FURY_CHAT_ID"));
        OLLIE_ACTION_JACK_CHAT_ID = Long.parseLong(PROPERTIES.getProperty("telegram.chat.OLLIE_ACTION_JACK_CHAT_ID"));
        OLLIE_CRAZY_WIZARD_CHAT_ID = Long.parseLong(PROPERTIES.getProperty("telegram.chat.OLLIE_CRAZY_WIZARD_CHAT_ID"));
        TEST_FOR_BOT_GROUP_ID = Long.parseLong(PROPERTIES.getProperty("telegram.chat.TEST_FOR_BOT_GROUP_ID"));
        List<String> bigGeneralGroupIds = Arrays.asList(PROPERTIES.getProperty("telegram.chat.BIG_GENERAL_GROUP_IDS").split(","));
        BIG_GENERAL_GROUP_IDS = bigGeneralGroupIds.stream().map(Long::parseLong).collect(Collectors.toList());
        HELP_LINK = PROPERTIES.getProperty("telegram.commands.help.file");
        HELP_LINKS = PROPERTIES.getProperty("telegram.commands.help_links.file");
        BIG_HELP_LINKS = PROPERTIES.getProperty("telegram.commands.help_links.big.file");
        JENKINS_URL = PROPERTIES.getProperty("jenkins.url");
        JENKINS_JOBS_URL = PROPERTIES.getProperty("jenkins.jobs.url");
    }

    public final String token;


    private Common() {
        String configFile = "/config.properties";
        try {
            PROPERTIES.load(Common.class.getResourceAsStream(configFile));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Could not loaded: " + configFile, e);
        }
        token = PROPERTIES.getProperty("telegram.bot.token");
    }

    public static final List<ChatData> BIG_GENERAL_GROUPS = Arrays.asList(
        new ChatData(BIG_GENERAL_CHAT_ID, Collections.emptyList(), Collections.emptyList(), Collections.singletonList("BIGIP")),
        new ChatData(OLLIE_BALLOONIES_CHAT_ID, Collections.singletonList("ballooniesIXF"), Collections.singletonList("BIXF_NEW"), Collections.emptyList()),
        new ChatData(OLLIE_WILD_FURY_CHAT_ID, Collections.singletonList("wildFury"), Collections.singletonList("wildfury"), Collections.singletonList("WILDFU")),
        new ChatData(OLLIE_ELECTRIC_TIGER_CHAT_ID, Collections.singletonList("electricTigerIXF"), Collections.singletonList("electrictigerixf"), Collections.emptyList()),
        new ChatData(OLLIE_ACTION_JACK_CHAT_ID, Collections.singletonList("actionJack"), Collections.emptyList()/*Collections.singletonList("actionjack")*/, Collections.singletonList("ACTJA")),
        new ChatData(OLLIE_CRAZY_WIZARD_CHAT_ID, Collections.singletonList("crazyWizard"), Collections.emptyList(), Collections.singletonList("CRZWZRD")),
//        new ChatData(DEV_TALKS_CHAT_ID, Arrays.asList("ballooniesIXF", "electricTigerIXF", "wildFury", "actionJack"), Arrays.asList("wildfury", "actionjack", "electrictigerixf", "BIXF_NEW"), Collections.emptyList()),
        new ChatData(DEV_TALKS_CHAT_ID, Arrays.asList("crazyWizard", "ballooniesIXF", "electricTigerIXF", "wildFury", "actionJack"), Collections.emptyList(), Collections.emptyList()),
        new ChatData(TEST_FOR_BOT_GROUP_ID, Arrays.asList("crazyWizard", "ballooniesIXF", "electricTigerIXF", "wildFury", "actionJack"), Collections.emptyList(), Collections.singletonList("CRZWZRD"))
//        new ChatData(TEST_FOR_BOT_GROUP_ID, Arrays.asList("ballooniesIXF", "electricTigerIXF", "wildFury", "actionJack"), Arrays.asList("BIXF_NEW", "wildfury", "actionjack", "electrictigerixf"))
    );
}
