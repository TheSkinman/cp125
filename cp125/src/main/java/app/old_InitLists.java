package app;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.scg.domain.ClientAccount;
import com.scg.domain.Consultant;
import com.scg.domain.ConsultantTime;
import com.scg.domain.NonBillableAccount;
import com.scg.domain.Skill;
import com.scg.domain.TimeCard;
import com.scg.util.Address;
import com.scg.util.PersonalName;
import com.scg.util.StateCode;

public class old_InitLists {
    /** Character encoding to use. */
    private static final String ENCODING = "ISO-8859-1";

    /** Number of hours in a standard working day. */
    private static final int STD_WORK_DAY = 8;

    /** Some overtime hours. */
    private static final int OT_HOURS = 4;

    /** Index to the first client. */
    private static final int FIRST_CLIENT_NDX = 0;

    /** Index to the second client. */
    private static final int SECOND_CLIENT_NDX = 1;

    /** This class' logger. */
    private static final Logger logger = LoggerFactory.getLogger("InitLists");

    /** The start month for our test cases. */
    private static final Month TIMECARD_START_MONTH = Month.FEBRUARY;

    /** The first Monday of the test month. */
    private static final int TEST_START_FIRST_WEEK = 27;

    /** The invoice month. */
    public static final Month TEST_INVOICE_MONTH = TIMECARD_START_MONTH.plus(1);

    /** The test year. */
    public static final int TEST_INVOICE_YEAR = 2017;

    private List<ClientAccount> clients;
    private List<Consultant> consultants;
    private List<TimeCard> timeCards;

    public old_InitLists() {
        clients = new ArrayList<>();
        timeCards = new ArrayList<>();
        consultants = new ArrayList<>();
    }

    public static void main(String[] args) {
        List<ClientAccount> clientsList = new ArrayList<>();
        ;
        List<Consultant> consultantsList = new ArrayList<>();
        ;
        List<TimeCard> timeCardsList = new ArrayList<>();
        ;

        populateLists(clientsList, consultantsList, timeCardsList);

        saveList(clientsList, "ClientList.ser");
        saveList(timeCardsList, "TimeCardList.ser");

    }

    /**
     * Saves a list of given objects to a file of a given file name.
     * 
     * @param listToSave
     *            the list of objects to be saved.
     * @param fileName
     *            the file name to save to.
     */
    public static <T> void saveList(List<T> listToSave, String fileName) {
        try (ObjectOutputStream objectOutputStram = new ObjectOutputStream(new FileOutputStream(fileName));) {
            objectOutputStram.writeObject(listToSave);
        } catch (IOException ex) {
            ex.printStackTrace();
            //logger.error(ex.printStackTrace() .toString());
        }
    }

    /**
     * Generates test data, empty lists are provided for clients, consultants and
     * time cards, each of these lists will be populated with consistent data that
     * may be used for test purposes.
     *
     * @param clients
     *            the list to return the clients in
     * @param consultants
     *            the list to return the consultants in
     * @param timeCards
     *            the to to return the timeCards in
     */
    public static void populateLists(final List<ClientAccount> clients, final List<Consultant> consultants,
            final List<TimeCard> timeCards) {
        clients.add(new ClientAccount("Acme Industries", new PersonalName("Coyote", "Wiley"),
                new Address("1616 Index Ct.", "Redmond", StateCode.WA, "98055")));
        clients.add(new ClientAccount("FooBar Enterprises", new PersonalName("Sam", "Yosemite"),
                new Address("1024 Kilobyte Dr.", "Silicone Gulch", StateCode.CA, "94105")));

        // Create some Consultants
        final Consultant programmer = new Consultant(new PersonalName("Coder", "Carl"));
        final Consultant systemAnalyst = new Consultant(new PersonalName("Architect", "Ann", "S."));
        consultants.add(programmer);
        consultants.add(systemAnalyst);

        LocalDate startDate = LocalDate.of(TEST_INVOICE_YEAR, TIMECARD_START_MONTH, TEST_START_FIRST_WEEK);

        // Create some TimeCards
        // The first one
        LocalDate currentDay = startDate;
        TimeCard timeCard = new TimeCard(programmer, currentDay);
        timeCard.addConsultantTime(
                new ConsultantTime(currentDay, clients.get(FIRST_CLIENT_NDX), Skill.SOFTWARE_ENGINEER, STD_WORK_DAY));
        currentDay = currentDay.plusDays(1);
        timeCard.addConsultantTime(
                new ConsultantTime(currentDay, clients.get(FIRST_CLIENT_NDX), Skill.SOFTWARE_ENGINEER, STD_WORK_DAY));
        currentDay = currentDay.plusDays(1);
        timeCard.addConsultantTime(
                new ConsultantTime(currentDay, clients.get(SECOND_CLIENT_NDX), Skill.SOFTWARE_ENGINEER, STD_WORK_DAY));
        currentDay = currentDay.plusDays(1);
        timeCard.addConsultantTime(
                new ConsultantTime(currentDay, clients.get(SECOND_CLIENT_NDX), Skill.SOFTWARE_ENGINEER, STD_WORK_DAY));
        currentDay = currentDay.plusDays(1);
        timeCard.addConsultantTime(
                new ConsultantTime(currentDay, clients.get(SECOND_CLIENT_NDX), Skill.SOFTWARE_ENGINEER, STD_WORK_DAY));
        logger.trace(String.format("Created first TimeCard: %s", timeCard.toReportString()));
        timeCards.add(timeCard);

        // The second one
        currentDay = startDate.plusWeeks(1);
        timeCard = new TimeCard(programmer, currentDay);
        timeCard.addConsultantTime(
                new ConsultantTime(currentDay, clients.get(FIRST_CLIENT_NDX), Skill.SOFTWARE_ENGINEER, STD_WORK_DAY));
        currentDay = currentDay.plusDays(1);
        timeCard.addConsultantTime(
                new ConsultantTime(currentDay, clients.get(FIRST_CLIENT_NDX), Skill.SOFTWARE_ENGINEER, STD_WORK_DAY));
        currentDay = currentDay.plusDays(1);
        timeCard.addConsultantTime(
                new ConsultantTime(currentDay, clients.get(SECOND_CLIENT_NDX), Skill.SOFTWARE_ENGINEER, STD_WORK_DAY));
        currentDay = currentDay.plusDays(1);
        timeCard.addConsultantTime(new ConsultantTime(currentDay, clients.get(SECOND_CLIENT_NDX),
                Skill.SOFTWARE_ENGINEER, STD_WORK_DAY + OT_HOURS));
        currentDay = currentDay.plusDays(1);
        timeCard.addConsultantTime(
                new ConsultantTime(currentDay, NonBillableAccount.VACATION, Skill.SOFTWARE_ENGINEER, STD_WORK_DAY));
        logger.trace(String.format("Created second TimeCard: %s", timeCard.toReportString()));
        timeCards.add(timeCard);

        // The third one
        currentDay = startDate.plusWeeks(1);
        timeCard = new TimeCard(systemAnalyst, currentDay);
        timeCard.addConsultantTime(
                new ConsultantTime(currentDay, clients.get(SECOND_CLIENT_NDX), Skill.SYSTEM_ARCHITECT, STD_WORK_DAY));
        currentDay = currentDay.plusDays(1);
        timeCard.addConsultantTime(
                new ConsultantTime(currentDay, NonBillableAccount.SICK_LEAVE, Skill.SYSTEM_ARCHITECT, STD_WORK_DAY));
        currentDay = currentDay.plusDays(1);
        timeCard.addConsultantTime(
                new ConsultantTime(currentDay, clients.get(SECOND_CLIENT_NDX), Skill.SYSTEM_ARCHITECT, STD_WORK_DAY));
        currentDay = currentDay.plusDays(1);
        timeCard.addConsultantTime(
                new ConsultantTime(currentDay, clients.get(SECOND_CLIENT_NDX), Skill.SYSTEM_ARCHITECT, STD_WORK_DAY));
        currentDay = currentDay.plusDays(1);
        timeCard.addConsultantTime(
                new ConsultantTime(currentDay, clients.get(SECOND_CLIENT_NDX), Skill.SYSTEM_ARCHITECT, STD_WORK_DAY));
        currentDay = currentDay.plusDays(1);
        timeCard.addConsultantTime(
                new ConsultantTime(currentDay, clients.get(SECOND_CLIENT_NDX), Skill.SYSTEM_ARCHITECT, STD_WORK_DAY));
        logger.trace(String.format("Created third TimeCard: %s", timeCard.toReportString()));
        timeCards.add(timeCard);

        // The forth one
        currentDay = startDate.plusWeeks(2);
        timeCard = new TimeCard(systemAnalyst, currentDay);
        timeCard.addConsultantTime(
                new ConsultantTime(currentDay, clients.get(SECOND_CLIENT_NDX), Skill.SYSTEM_ARCHITECT, STD_WORK_DAY));
        currentDay = currentDay.plusDays(1);
        timeCard.addConsultantTime(
                new ConsultantTime(currentDay, clients.get(SECOND_CLIENT_NDX), Skill.SYSTEM_ARCHITECT, STD_WORK_DAY));
        currentDay = currentDay.plusDays(1);
        timeCard.addConsultantTime(new ConsultantTime(currentDay, NonBillableAccount.BUSINESS_DEVELOPMENT,
                Skill.SYSTEM_ARCHITECT, STD_WORK_DAY));
        currentDay = currentDay.plusDays(1);
        timeCard.addConsultantTime(new ConsultantTime(currentDay, NonBillableAccount.BUSINESS_DEVELOPMENT,
                Skill.SYSTEM_ARCHITECT, STD_WORK_DAY));
        currentDay = currentDay.plusDays(1);
        timeCard.addConsultantTime(
                new ConsultantTime(currentDay, clients.get(SECOND_CLIENT_NDX), Skill.SYSTEM_ARCHITECT, STD_WORK_DAY));
        logger.trace(String.format("Created forth TimeCard: %s", timeCard.toReportString()));
        timeCards.add(timeCard);
    }
}
