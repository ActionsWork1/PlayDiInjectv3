package auto01.nonguitests;

import auto01.base.FrameworkConfig;
import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Provides;
import org.aeonbits.owner.ConfigFactory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class NoneGUIBaseTest extends AbstractModule{

       public static final FrameworkConfig config = ConfigFactory.create(FrameworkConfig.class);
//       public NoneGUIBaseTest(){
//           config = ConfigFactory.create(FrameworkConfig.class);
//       }

      public static String getExlLocation(){
//          FrameworkConfig config = ConfigFactory.create(FrameworkConfig.class);
          System.out.println(config.testData());
           return config.testData();
      }

      @Provides
      String getLocation(){
          return config.testData();
      }

    public static String currentTimeStamp(){
        LocalDateTime currentDateTime = LocalDateTime.now();
        // Define the desired format pattern with "hh" for 12-hour format and "a" for AM/PM marker
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss a", Locale.ENGLISH);
        String formattedDateTime = currentDateTime.format(formatter);
        return formattedDateTime;

    }




}
