import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class HorseRacing {
    private static final int MAX_HORSES = 7;
    private static final int FIRST_PLACE = 1;
    private static final int LAST_PLACE = MAX_HORSES;
    private static final int WINNING_MULTIPLIER = 2;
    private static final int PLACE_MULTIPLIER = 1;
    private static final int SHOW_MULTIPLIER = 1;

    private List<Horse> horses;
    private List<Bet> bets;
    private int winningHorseNumber;

    public HorseRacing() {
        horses = new ArrayList<>();
        bets = new ArrayList<>();
        winningHorseNumber = 0;
    }

    public void addHorse(Horse horse) {
        if (horses.size() >= MAX_HORSES) {
            System.out.println("Нельзя добавить больше, чем " + MAX_HORSES + " лощадей");
            return;
        }
        horses.add(horse);
    }

    public void printHorses() {
        System.out.println("Доступные лошади:");
        for (int i = 0; i < horses.size(); i++) {
            System.out.println(i + 1 + ". " + horses.get(i).getName());
        }
    }

    public void takeBets() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите ваше имя:");
        String name = scanner.nextLine();
        System.out.println("Введите сумму, которую вы хотите поставить:");
        int amount = scanner.nextInt();
        System.out.println("Выберите лошадь (введите номер):");
        printHorses();
        int horseNumber = scanner.nextInt();
        System.out.println("Выберите тип ставки:");
        System.out.println("1. Победа");
        System.out.println("2. Место");
        System.out.println("3. Шоу");
        int betType = scanner.nextInt();
        Bet bet = new Bet(name, amount, horseNumber, betType);
        bets.add(bet);
        System.out.println("Ставка принята:");
        System.out.println(bet);
    }

    public void runRace() {
        Random random = new Random();
        winningHorseNumber = random.nextInt(horses.size()) + 1;
        System.out.println("Победившая лошадь имеет номер " + winningHorseNumber);
    }

    public void settleBets() {
        for (Bet bet : bets) {
            int horseNumber = bet.getHorseNumber();
            int betType = bet.getBetType();
            int payout = 0;
            if (horseNumber == winningHorseNumber) {
                switch (betType) {
                    case 1:
                        payout = bet.getAmount() * WINNING_MULTIPLIER;
                        break;
                    case 2:
                        payout = bet.getAmount() * PLACE_MULTIPLIER;
                        break;
                    case 3:
                        payout = bet.getAmount() * SHOW_MULTIPLIER;
                        break;
                }
            } else if (betType == 2 && (horseNumber == FIRST_PLACE || horseNumber == LAST_PLACE)) {
                payout = bet.getAmount() * PLACE_MULTIPLIER;
            } else if (betType == 3 && (horseNumber == FIRST_PLACE || horseNumber == LAST_PLACE ||
                    horseNumber == winningHorseNumber)) {
                payout = bet.getAmount() * SHOW_MULTIPLIER;
            }
            System.out.println("Результат ставки для " + bet.getName() + ":");
            if (payout > 0) {
                System.out.println("Вы выиграли" + payout + " сом");
            } else {
                System.out.println("Вы проиграли");
            }
        }
    }

    public static void main(String[] args) {
        HorseRacing horseRacing = new HorseRacing();

        // добавляем лошадей
        horseRacing.addHorse(new Horse("Булат"));
        horseRacing.addHorse(new Horse("Шайтан"));
        horseRacing.addHorse(new Horse("Янтарь"));
        horseRacing.addHorse(new Horse("Ветерок"));
        horseRacing.addHorse(new Horse("Ласточка"));
        horseRacing.addHorse(new Horse("Океан"));
        horseRacing.addHorse(new Horse("Мустанг"));

        Scanner scanner = new Scanner(System.in);
        boolean quit = false;

        while (!quit) {
            System.out.println("Введите 1, чтобы сделать ставку, 2, чтобы начать гонку, или 3, чтобы выйти:");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    horseRacing.takeBets();
                    horseRacing.runRace();
                    horseRacing.settleBets();
                    break;
                case 2:
                    horseRacing.runRace();
                    horseRacing.settleBets();
                    break;
                case 3:
                    quit = true;
                    break;
                default:
                    System.out.println("Неверный выбор. Пожалуйста, попробуйте снова.");
                    break;
            }
        }
    }




}
