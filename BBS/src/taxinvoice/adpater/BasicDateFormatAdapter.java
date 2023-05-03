/**
 * (c)BOC
 */
package taxinvoice.adpater;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * Created by achiz on 14. 8. 27.
 */
public class BasicDateFormatAdapter extends XmlAdapter<String, Date> {

    @Override
    public Date unmarshal(String v) throws Exception {

        SimpleDateFormat transFormat = new SimpleDateFormat("yyyyMMdd", Locale.KOREA);

        return transFormat.parse(v);

    }

    @Override
    public String marshal(Date v) throws Exception {

        SimpleDateFormat transFormat = new SimpleDateFormat("yyyyMMdd", Locale.KOREA);

        String odate = transFormat.format(v);

        return odate;
    }

}
