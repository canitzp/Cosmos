package de.canitzp.cosmos;

/**
 * @author canitzp
 */
public class Util {



    private static final double AU_TO_LY = 1.5813 * Math.pow(10, -5);

    public static double kilometerToAstronomicalUnits(long kilometer){
        return Math.round(((kilometer * 1000) / 149597870700D) * 100) / 100D;
    }

    public static double kilometerToAstronomicalUnits(long apKM, long periKM){
        return kilometerToAstronomicalUnits(periKM + ((apKM-periKM) / 2));
    }

    public static double astronomicalUnitsToLightYears(double au){
        return au * AU_TO_LY;
    }

    public static double lightYearsToAstronomicalUnits(double ly){
        return ly / AU_TO_LY;
    }

    public static double parsecToLightYears(double parsec){
        return parsec * 3.26156D;
    }

}
