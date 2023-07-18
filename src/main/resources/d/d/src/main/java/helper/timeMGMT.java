package helper;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

/** the timeMGMT class manages all the time conversions within the application
 * converts system clock to UTC, UTC to system time, and converts UTC to EST*/

public class timeMGMT {


    /**converts system time to UTC time zone
     * @return  returns time in UTC
     * @param systemT  LocalDateTime system time of users device*/
    public static LocalDateTime convertSystemToUTC(LocalDateTime systemT)
    {return systemT.atZone(ZoneId.systemDefault()).withZoneSameInstant(ZoneId.of("UTC")).toLocalDateTime();
    }

    /**converts from UTC time zone to system time of users device
     * @return  returns time in system time
     * @param utcT  time in UTC time*/
    public static LocalDateTime convertUTCToSystem(LocalDateTime utcT){
        return  utcT.atZone(ZoneId.of("UTC")).withZoneSameInstant(ZoneId.systemDefault()).toLocalDateTime();
    }

    /**converts from UTC Timezone to EST timezone
     * @return returns time in EST
     * @param utcT time in UTC time*/

    public static LocalDateTime convertUTCToEST(LocalDateTime utcT){
        return utcT.atZone(ZoneId.of("UTC")).withZoneSameInstant(ZoneId.of("America/New_York")).toLocalDateTime() ;
    }


}
