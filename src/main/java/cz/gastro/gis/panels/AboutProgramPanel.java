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
import cz.gastro.gis.images.ImageManager;
import cz.gastro.gis.utils.ManifestUtils;
import cz.gastro.gis.utils.ScreenUtils;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

/**
 * AboutProgramPanel	
 * @author Daniel Kec
 * @since Sep 5, 2013 4:47:45 PM
 * @qname cz.syntea.dn.log.panels.AboutProgramPanel
 */
public class AboutProgramPanel extends JDialog {
	private static final long serialVersionUID = 1L;
	private final Component parent;
	
	public AboutProgramPanel(Component parent) {
		super();
		this.setTitle("O programe");
		this.setIconImage(ImageManager.getFavicon().getImage());
		this.parent = parent;
		this.setLayout(new BorderLayout());
		
		JLabel iconLabel = new JLabel(ImageManager.getFavicon());
		iconLabel.setVerticalAlignment(SwingConstants.TOP);
		this.add(iconLabel,BorderLayout.WEST);
		//this.setBorder(BorderFactory.createEmptyBorder());
		iconLabel.setBorder(BorderFactory.createEmptyBorder(0,5,5,5));
		
		JPanel area = new JPanel();
		area.setLayout(new BoxLayout(area,BoxLayout.Y_AXIS));
		JLabel title = new JLabel("DnLogViewer "+ManifestUtils.getVersion());
		title.setFont(title.getFont().deriveFont(Font.BOLD));
		area.add(title);
		area.add(new JLabel("© RM GASTRO, s.r.o."));
		area.add(new JLabel("IS servisního technika"));
		area.add(new JLabel("  "));
		area.add(new JLabel(System.getProperty("java.runtime.name")));
		area.add(new JLabel(System.getProperty("java.vm.version")));
		area.add(new JLabel(System.getProperty("java.vm.vendor")));
		area.add(new JLabel(System.getProperty("java.vm.name")));
		area.add(new JLabel(System.getProperty("java.vm.specification")));
		area.add(new JLabel(System.getProperty("java.runtime.version")));

		/*
		java.runtime.name=Java(TM) SE Runtime Environment
		java.vm.version=22.1-b02
		java.vm.vendor=Oracle Corporation
		java.vendor.url=http://java.oracle.com/
		java.vm.name=Java HotSpot(TM) Client VM
		java.vm.specification.name=Java Virtual Machine Specification
		java.runtime.version=1.7.0_03-b05 
		  */
		
		JScrollPane scrollPane = new JScrollPane(area);
		scrollPane.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		add(scrollPane,BorderLayout.CENTER);
		this.setPreferredSize(new Dimension(500,250));
		this.pack();
		this.setLocation(ScreenUtils.getCenteredLocationForWindow(this,MainFrame.getInstance()));
		area.updateUI();
		this.setVisible(true);
	}
	

}
