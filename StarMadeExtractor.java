import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URLClassLoader;
import java.net.URL;
import java.util.Arrays;

public class StarMadeExtractor {

	public static void main(String[] argv) {
		String classes[] = {"org.schema.game.network.objects.NetworkShip"};
		
		try {
			File file = new File(argv[0]);
			if( !file.exists() )
				throw new FileNotFoundException(file.getPath());
			URLClassLoader loader = new URLClassLoader (new URL[] { file.toURI().toURL() }, StarMadeExtractor.class.getClassLoader());

			for (String cname : classes) {
				Class<?> c = Class.forName(cname, true, loader);
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
		}
			catch (ClassNotFoundException x) {
			x.printStackTrace();
		}
			catch (IOException x) {
			x.printStackTrace();
		}
	}
}