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
import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Window;

import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JViewport;

/**
 * ScreenUtils	
 * @author Daniel Kec
 * @since Sep 5, 2013 10:47:25 AM
 * @qname cz.syntea.dn.log.utils.ScreenUtils
 */
public class ScreenUtils {
	public static final int MARGIN = 100; 
	
	
	public static Dimension getMultiMonitorCurrentResolution(){
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		int width = gd.getDisplayMode().getWidth();
		int height = gd.getDisplayMode().getHeight();
		return new Dimension(width,height);
	}
	
	public static Dimension getMarginedSize(){
		Dimension fullres = getMultiMonitorCurrentResolution();
		fullres.setSize(fullres.getWidth()-MARGIN, fullres.getHeight()-MARGIN);
		return fullres;
	}
	
	public static Point getMarginedPosition(){
		return new Point(MARGIN/2,MARGIN/2);
	}
	
	public static Point getCenteredPosition(Point location,Dimension winSize){		
		int x = (int) ((winSize.getWidth()/2)+location.getX());
		int y = (int) ((winSize.getHeight()/2)+location.getY());
		return new Point(x,y);
	}
	
	public static Point getCenteredLocationForWindow(Window dialog,Window parent){
		Point parentCenteredPos = getCenteredPosition(parent.getLocation(),parent.getSize());
		Dimension dialogSize = dialog.getSize();
		return new Point(parentCenteredPos.x-(dialogSize.width/2),
						 parentCenteredPos.y-(dialogSize.height/2));
	}
	
	private static JTextArea jTextAreaLiveLog;
	public static void setLiveLog(JTextArea livelog){
		jTextAreaLiveLog = livelog;
	}
	
	/**
	 * Scroluje na radek a slopec tabulky
	 * @param table
	 * @param rowIndex
	 * @param vColIndex
	 */
	public static void scrollToVisible(JTable table, int rowIndex, int vColIndex) {
        if (!(table.getParent() instanceof JViewport)) {
            return;
        }
        JViewport viewport = (JViewport)table.getParent();

        // This rectangle is relative to the table where the
        // northwest corner of cell (0,0) is always (0,0).
        Rectangle rect = table.getCellRect(rowIndex, vColIndex, true);

        // The location of the viewport relative to the table
        Point pt = viewport.getViewPosition();

        // Translate the cell location so that it is relative
        // to the view, assuming the northwest corner of the
        // view is (0,0)
        rect.setLocation(rect.x-pt.x, rect.y-pt.y);

        table.scrollRectToVisible(rect);

        // Scroll the area into view
        //viewport.scrollRectToVisible(rect);
    }
	
	
	synchronized
	public static void println(final String message){
		print(message+"\n");
	}
	
	synchronized
	public static void print(final String message){
		if(jTextAreaLiveLog != null){// aby nam to nepreteklo
			if(jTextAreaLiveLog.getText().length()>10000){
				jTextAreaLiveLog.replaceRange("", 0, 500);
			}
			jTextAreaLiveLog.append(message);
			jTextAreaLiveLog.setSelectionStart(jTextAreaLiveLog.getText().length()-1);
			jTextAreaLiveLog.setSelectionEnd(jTextAreaLiveLog.getText().length()-1);			
		}
		System.out.print(message);// aby nam neco neuteklo
	}
}
