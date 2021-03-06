package Data;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.File;
import java.io.IOException;

//  Открывает изображения, заполняет матрицу PixelMap
public class ImageData {
    private PixelMap map;
    private Raster raster;
    private BufferedImage bufImage;

    private PixelMap makePixels() {
        PixelMap pixelMap = new PixelMap(raster.getHeight(), raster.getWidth());
        Pixel[][] mas = pixelMap.getPixels();

        int[] RGB = new int[3];
        for (int y = 0; y < raster.getHeight(); ++y)
            for (int x = 0; x < raster.getWidth(); ++x) {
                RGB = raster.getPixel(x, y, RGB);
                if (mas[y][x] != null)
                    mas[y][x].setPixel(RGB[0], RGB[1], RGB[2]);
                else
                    mas[y][x] = new Pixel(x, y, RGB);
            }
        return pixelMap;
    }

    public void openImage(String fName) {
        try {
            bufImage = ImageIO.read(new File(fName));
            raster = bufImage.getRaster();
        } catch (IOException e) {
            e.printStackTrace();
        }
        map = makePixels();
    }

    public PixelMap getPixelMap() {
        return map;
    }

    public BufferedImage getImage() {
        return bufImage;
    }
}
