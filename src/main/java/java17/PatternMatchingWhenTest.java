package java17;

public class PatternMatchingWhenTest {



    class MyEarth {
        int getDamage(Object obj) {
            return switch (obj) {
                case AirPollution airPol  when airPol.getAQI() > 200 -> 500;
                case Deforestation def -> def.getTreeDamage();
                case null, default -> -1;
            };
        }
    }

    class Pollution { }

    class AirPollution extends Pollution {
        public int getAQI() {
            return 100;
        }
    }

    class Deforestation {
        public int getTreeDamage() {
            return 300;
        }
    }

}

