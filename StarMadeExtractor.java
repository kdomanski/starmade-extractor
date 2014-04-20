import org.schema.game.network.objects.*;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Iterator;

public class StarMadeExtractor {

	public static void main(String[] argv) {
		String classes[] = {"org.schema.game.network.objects.NetworkShip"};

		for (String cname : classes) {
			try {
				Class<?> c = Class.forName(cname);
				String[] fields = new String[c.getFields().length];
				for (int i=0; i<fields.length; i++) {
					fields[i] = c.getFields()[i].getName();
				}

				assert (fields.length < 127);
				Arrays.sort(fields);

				System.out.println("+" + c.getSimpleName());
				for (String str : fields) {
					System.out.println(str);
				}
				System.out.println("-" + c.getSimpleName());
			}
			catch (ClassNotFoundException x) {
				x.printStackTrace();
			}
		}
	}
}