public class Bet {
    private String name;
    private int amount;
    private int horseNumber;
    private int betType;


    public Bet(String name, int amount, int horseNumber, int betType) {
        this.name = name;
        this.amount = amount;
        this.horseNumber = horseNumber;
        this.betType = betType;
    }

    public String getName() {
        return name;
    }

    public int getAmount() {
        return amount;
    }

    public int getHorseNumber() {
        return horseNumber;
    }

    public int getBetType() {
        return betType;
    }

    public String toString() {
        return name + " поставил " + amount + " на лощадь под номером  " + horseNumber + " на " +
                getBetTypeName();
    }

    private String getBetTypeName() {
        switch (betType) {
            case 1: return "Выигрыш";
            case 2: return "Место";
            case 3: return "Показ";
            default: return "Неизвестный номер";
        }
    }
}
