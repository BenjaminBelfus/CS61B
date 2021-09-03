import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexPuzzles {

    public static List<String> urlRegex(String[] urls) {
        List<String> m = new ArrayList<>();
        Pattern urlPattern = Pattern.compile("^([\\(])([\\w \\W]?)([\\.]?)([\\w \\W]?)([\\.]?)([\\w \\W]?)([\\.]?)([\\w \\W]?)([\\.]?)(https?:)(\\/\\/)([\\w]?)([\\.]?)([\\w]?)([\\.]?)([\\w]?)([\\.]?)([\\w]+)([\\.])([\\w]{2,3})([\\/])([\\w]+)([\\.])(html)([\\(])([\\w \\W]?)([\\.]?)([\\w \\W]?)([\\.]?)([\\w \\W]?)([\\.]?)([\\w \\W]?)([\\.]?)([\\)])");

        for (String link : urls) {
            Matcher matcher = urlPattern.matcher(link);
            if (matcher.matches()) {
                m.add(link);
            }
        }
        return m;
    }

    public static List<String> findStartupName(String[] names) {
        // Create a String pattern to fill return array
        List<String> matched= new ArrayList<String>();
        Pattern su = Pattern.compile("^(Data|App|my|on|un)[A-H J-Z a-h j-z 0-9]+(ly|sy|ify|.io|.fm|.tv)$");
        for (String brand : names) {
            if (su.matcher(brand).find()) {
                matched.add(brand);
            }
        }

        return matched;

    }

    public static BufferedImage imageRegex(String filename, int width, int height) {
        BufferedReader br;
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(filename)));
        } catch (FileNotFoundException e) {
            throw new RuntimeException("No such file found: " + filename);
        }

        // Initialize both Patterns and 3-d array
        try {
            String line;
            while ((line = br.readLine()) != null) {
                // Initialize both Matchers and find() for each

                // Parse each group as an Integer

                // Store in array
            }
        } catch (IOException e) {
            System.err.printf("Input error: %s%n", e.getMessage());
            System.exit(1);
        }
        // Return the BufferedImage of the array
        return null;
    }

    public static BufferedImage arrayToBufferedImage(int[][][] arr) {
        BufferedImage img = new BufferedImage(arr.length,
                arr[0].length, BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                int pixel = 0;
                for (int k = 0; k < 3; k++) {
                    pixel += arr[i][j][k] << (16 - 8*k);
                }
                img.setRGB(i, j, pixel);
            }
        }

        return img;
    }

    public static void main(String[] args) {
        /* For testing image regex */
        BufferedImage img = imageRegex("mystery.txt", 400, 400);

        File outputfile = new File("output_img.jpg");
        try {
            ImageIO.write(img, "jpg", outputfile);
        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
        }
    }
}