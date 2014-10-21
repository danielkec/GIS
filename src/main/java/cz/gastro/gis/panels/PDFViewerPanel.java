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

package cz.gastro.gis.panels;

import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import org.icepdf.ri.common.SwingController;
import org.icepdf.ri.common.SwingViewBuilder;

/**
 *
 * @author Daniel Kec <daniel at kecovi.cz>
 */
public class PDFViewerPanel extends JPanel{
    private final SwingController controller;
    private final JPanel viewerComponentPanel;

    public PDFViewerPanel() {
        super();
        // build a component controller
        this.controller = new SwingController();

        SwingViewBuilder factory = new SwingViewBuilder(controller);

        viewerComponentPanel = factory.buildViewerPanel();

       //  add interactive mouse link annotation support via callback
        controller.getDocumentViewController().setAnnotationCallback(
                new org.icepdf.ri.common.MyAnnotationCallback(
                        controller.getDocumentViewController()));
        
        this.add(viewerComponentPanel);

      
    }
    
    public void openDocument(String path){
        // Now that the GUI is all in place, we can try openning a PDF
        controller.openDocument(path);
//        Component[] components = this.viewerComponentPanel.getComponents();
//        System.out.println(">>>"+components.length);
//        for (Component component : components) {
//            if(component instanceof JToolBar){
//                JToolBar bar = (JToolBar) component;
//                Component[] barComponents = bar.getComponents();
//                for (Component barComponent : barComponents) {
//                    if(barComponent instanceof JToolBar){
//                        JToolBar subar = (JToolBar) barComponent;
//                        Component[] subBarComponents = subar.getComponents();
//                        for (Component buttonComponent : subBarComponents) {
//                            if(buttonComponent instanceof JButton){
//                                JButton button = (JButton) buttonComponent;
//                                if(button.getToolTipText().contains("Save As") ||
//                                   button.getToolTipText().contains("Print")){
//                                    button.setVisible(false);
//                                }
//                               // button.setVisible(false);
//                                System.out.println(">>"+button.getToolTipText());
//                            }
//                        }
//                    }
//                }
//                
//                break;
//            }
//        }
       
    }
    
    public static void main(String[] args) {
        PDFViewerPanel pdfViewerPanel = new PDFViewerPanel();
        JFrame applicationFrame = new JFrame();
        applicationFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        applicationFrame.getContentPane().add(pdfViewerPanel);
        
        pdfViewerPanel.openDocument("/home/daniel/Downloads/sg246688.pdf");
        
        applicationFrame.pack();
        applicationFrame.setVisible(true);
        
        
    }
}
