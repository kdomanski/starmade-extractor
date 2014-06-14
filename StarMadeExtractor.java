import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.MalformedURLException;
import java.net.URLClassLoader;
import java.net.URL;
import java.util.Arrays;

public class StarMadeExtractor {

	public static void main(String[] argv) {
		String classes[] = {
			"org.schema.game.network.objects.NetworkShip",
			"org.schema.game.network.objects.NetworkSegmentController",
			"org.schema.game.network.objects.NetworkShop",
			"org.schema.game.network.objects.NetworkSector",
			"org.schema.game.network.objects.NetworkPlayer",
			"org.schema.game.network.objects.NetworkSpaceStation",
			"org.schema.game.network.objects.NetworkSegmentProvider",
			"org.schema.game.network.objects.NetworkClientChannel",
			"org.schema.game.network.objects.NetworkGameState",
			"org.schema.game.network.objects.NetworkPlayerCharacter",
			"org.schema.game.network.objects.NetworkGameState",
			"org.schema.schine.network.objects.NetworkChat",
			"org.schema.schine.network.objects.NetworkEntity"
		};
		URLClassLoader loader = getLoader(argv[0]);

		for (String cname : classes) {
			printClass(loader, cname);
		}
		
	}

	private static URLClassLoader getLoader(String path) {
		try {
			File file = new File(path);
			
			if( !file.exists() )
				throw new FileNotFoundException(file.getPath());

			URLClassLoader loader = new URLClassLoader (new URL[] { file.toURI().toURL() }, StarMadeExtractor.class.getClassLoader());
			return loader;
		} catch (FileNotFoundException x) {
			x.printStackTrace();
			System.exit(1);
			return null;
		} catch (MalformedURLException x) {
			x.printStackTrace();
			System.exit(1);
			return null;
		}
	}

	private static void printClass(URLClassLoader loader, String name) {
		try {
			Class<?> c = Class.forName(name, true, loader);
			String[] fields = new String[c.getFields().length];
			for (int i=0; i<fields.length; i++) {
				fields[i] = c.getFields()[i].getName();
			}

			assert (fields.length < 127);
			Arrays.sort(fields);

			System.out.println("+" + c.getSimpleName());
			for (int i=0; i<fields.length; ++i) {
				String str = "";
				if (i<10)
					str += "0";
				str += Integer.toString(i);
				str += ": ";
				str += fields[i];
				System.out.println(str);
			}
			System.out.println("-" + c.getSimpleName());
		}
			catch (ClassNotFoundException x) {
			x.printStackTrace();
		}
	}
}

