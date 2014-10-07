/*
 * The MIT License
 *
 * Copyright 2014 Daniel Kec <daniel at kecovi.cz>.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package cz.gastro.gis.images;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 * Managing all images and cashing them in memory
 * @author Daniel Kec <daniel at kecovi.cz>
 * @since 14:00:30 7.10.2014
 */
public final class ImageManager {
    private static final HashMap<String,ImageIcon> IMAGE_CACHE = new HashMap<>();
    
    
    private static final String ICON_FAVICON = "favicon.png";
        
    public static ImageIcon getFavicon() throws IOException{
        return loadIcon(ICON_FAVICON);
    }
    
    private static ImageIcon loadIcon(String name) throws IOException{
        ImageIcon cachedIcon = ImageManager.IMAGE_CACHE.get(name);
        if(cachedIcon==null){
            BufferedImage bi = ImageIO.read(ImageManager.class.getResourceAsStream("favicon.png"));
            ImageManager.IMAGE_CACHE.put(name, new ImageIcon(bi));
        }       
        return ImageManager.IMAGE_CACHE.get(name);
    }

}
