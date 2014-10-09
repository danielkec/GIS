/*
 * The MIT License
 *
 * Copyright 2014 daniel.
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

package cz.gastro.gis.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Enumeration;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

/**
 *
 * @author Daniel Kec
 */
public class ManifestUtils {
    private static Manifest _manifest;
    
    public static void init() {
        try {
            if (ManifestUtils._manifest == null) {
                Enumeration<URL> manifestIsEnum = Thread.currentThread().getContextClassLoader().getResources(JarFile.MANIFEST_NAME);
                while (manifestIsEnum.hasMoreElements()) {
                    InputStream inputStream = manifestIsEnum.nextElement().openStream();
                    try{
                        Manifest manifest = new Manifest(inputStream);
                        Object name = manifest.getMainAttributes().get("Implementation-Title");
                        if(name==null)continue;
                        if(name.equals("GIS")){
                            ManifestUtils._manifest = manifest;
                        }
                    }catch(Exception e){
                        
                    }
                }
            }
        } catch (IOException ex) {
            throw new RuntimeException("Error when reading the manifest", ex);
        }
    }
    public static String getVersion() {
        return ManifestUtils._manifest.getMainAttributes().get("Implementation-Version")+"";        
    }
    public static String getAuthor() {
        return ManifestUtils._manifest.getMainAttributes().get("Built-By")+"";        
    }
    public static String getBuildDate() {
        return ManifestUtils._manifest.getMainAttributes().get("Time")+"";        
    }
    
}
