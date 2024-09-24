import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Player bot = new Player();
        Player alex = new Player(Player.VARIANTS.SCISSORS, "Alex");
        System.out.println(bot.whoWins(bot, alex));
    }
    public static class Player{
        public enum VARIANTS{
            ROCK, PAPER, SCISSORS;
            private static final Random PRNG = new Random();

            public static VARIANTS randomVariant()  {
                VARIANTS[] variants = values();
                return variants[PRNG.nextInt(variants.length)];
            }
        }
        public VARIANTS variant;
        public String name;

        public Player(){
            this.variant = VARIANTS.randomVariant();
            this.name = "Bot";
        }

        public Player(VARIANTS variant, String name){
            this.variant = variant;
            this.name = name;
        }

        public Object whoWins(Player bot, Player player){
            if (bot.variant == player.variant) {
                return "Ничья";
            }
            return switch (player.variant) {
                case ROCK -> (bot.variant == VARIANTS.SCISSORS) ? player.name + " победил" : bot.name + " победил";
                case PAPER -> (bot.variant == VARIANTS.ROCK) ? player.name + " победил" : bot.name + " победил";
                case SCISSORS -> (bot.variant == VARIANTS.PAPER) ? player.name + " победил" : bot.name + " победил";
            };
        }
    }
}



