package com.thulani.rouletegame.service.impl;

import com.thulani.rouletegame.model.EvenOddBet;
import com.thulani.rouletegame.model.NumberBet;
import com.thulani.rouletegame.model.Player;
import com.thulani.rouletegame.service.ConsoleRouletteService;
import com.thulani.rouletegame.value.BetValue;
import com.thulani.rouletegame.value.Enum.EvenOddBetEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.nio.file.Files;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.lang.String.valueOf;

@Service
@Scope("singleton")
public class ConsoleRouletteServiceImpl extends TimerTask implements ConsoleRouletteService, CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(ConsoleRouletteServiceImpl.class);
    private static final Map<String, Player> PLAYERS = new HashMap<>();
    private static List<String> PLAYER_NAMES;

    private static int winningNumber;

    private static List<String[]> rouletteInPuts = new ArrayList<>();
    private ResourceLoader resourceLoader;
    @Value("5")
    private String numberOfTimeToPlay;

    @Value("classpath:player_names.txt")
    private String playerNameFileLocation;

    @Autowired
    public ConsoleRouletteServiceImpl(final ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public ConsoleRouletteServiceImpl() {

    }

    @Override
    public void run(String... args) throws Exception {
        logger.info("Initializing player names");
        rouletteInPuts = initializeAndReadPlayerNames(playerNameFileLocation);
        Timer timer = new Timer();
        timer.schedule(new ConsoleRouletteServiceImpl(), 0, 30000);

    }

    private List<String[]> initializeAndReadPlayerNames(String fileLocation) throws Exception {
        List<String[]> rouletteInPutsList = new ArrayList<>();
        Resource resource = resourceLoader.getResource(fileLocation);
        logger.info("Reading player names from file : {}", resource.getFilename());
        PLAYER_NAMES = Files.lines(resource.getFile().toPath())
                .collect(Collectors.toList());
        logger.info("Player names initialized from file : {}", PLAYER_NAMES);
        PLAYER_NAMES.forEach(n -> PLAYERS.put(n, Player.builder()
                .bets(new HashSet<>())
                .name(n)
                .build())
        );
        Scanner scanner = new Scanner(System.in);
        IntStream.rangeClosed(1, Integer.parseInt(numberOfTimeToPlay))
                .forEach(value -> {
                    System.out.println("\n Please enter 3 params, in a single line in this format [YourName] [YourBetValue (ODD OR EVEN)] [BetAmount]");
                    System.out.print("Input : ");
                    String[] inputLine = scanner.nextLine().split(" ");
                    rouletteInPutsList.add(inputLine);
                });
        return rouletteInPutsList;
    }

    @Override
    public List<String> getPlayerNames() {
        return PLAYER_NAMES;
    }

    private void validatePlayerInput(ArrayList<String[]> rouletteInPuts) {
        for (String[] inputString : rouletteInPuts) {
            String playerName = inputString[0];
            if (!PLAYER_NAMES.contains(playerName))
                throw new RuntimeException("Invalid Player Name. Specified player name does not exist on File");
            String betType = inputString[1];
            if (this.getPlayerNames().contains(playerName)) {
                placeBetForPlayer(playerName, betType, inputString[2]);
            }
        }
    }

    private void placeBetForPlayer(String playerName, String betType, String inputBetAmount) {
        if (betType.matches("[0-9]+")) {
            int inputBetNumber = Integer.parseInt(betType);
            if (inputBetNumber <= 36 && inputBetNumber >= 0) {
                PLAYERS.get(playerName)
                        .getBets()
                        .add(new NumberBet<>(new BetValue<>(inputBetNumber), new BigDecimal(inputBetAmount)));
                logPlayerAndTheirBets(PLAYERS.get(playerName));
            }
        } else {
            if (valueOf("ODD").toString().equalsIgnoreCase(betType)) {
                PLAYERS.get(playerName)
                        .getBets()
                        .add(new EvenOddBet<>(new BetValue<>(EvenOddBetEnum.ODD), new BigDecimal(inputBetAmount)));
                logPlayerAndTheirBets(PLAYERS.get(playerName));
            } else if (valueOf("EVEN").toString().equalsIgnoreCase(betType)) {
                PLAYERS.get(playerName)
                        .getBets()
                        .add(new EvenOddBet<>(new BetValue<>(EvenOddBetEnum.EVEN), new BigDecimal(inputBetAmount)));
                logPlayerAndTheirBets(PLAYERS.get(playerName));
            }
        }
    }

    public void logPlayerAndTheirBets(Player player) {
        logger.info(String.format("Player: %s, Bet: %s, Outcome: %s, Winnings: %s ", player.getName(), player.getBets(), player.getTotalAmountWonSoFar(), player.getNumberOfWins()));
    }

    @Override
    public void run() {
        Random random = new Random();
        winningNumber = random.ints(1, (36 + 1)).limit(1).findFirst().getAsInt();
        logger.info("New number generated ... {} ", winningNumber);
        validatePlayerInput((ArrayList<String[]>) rouletteInPuts);

    }

}
