/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui.Demandes;

import com.codename1.charts.ChartComponent;
import com.codename1.charts.models.XYMultipleSeriesDataset;
import com.codename1.charts.models.XYSeries;
import com.codename1.charts.renderers.XYMultipleSeriesRenderer;
import com.codename1.charts.renderers.XYSeriesRenderer;
import com.codename1.charts.views.CubicLineChart;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.SideMenuBar;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.codename1.uikit.materialscreens.ProfileForm;
import com.codename1.uikit.materialscreens.SideMenuBaseForm;

/**
 *
 * @author yahia
 */
public class QrCode  extends SideMenuBaseForm{
     public QrCode(Resources res) {
        super(new BorderLayout());
        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);
        Image profilePic = res.getImage("user-picture.jpg");        
        Image tintedImage = Image.createImage(profilePic.getWidth(), profilePic.getHeight());
        Graphics g = tintedImage.getGraphics();
        g.drawImage(profilePic, 0, 0);
        g.drawImage(res.getImage("gradient-overlay.png"), 0, 0, profilePic.getWidth(), profilePic.getHeight());
        
        tb.getUnselectedStyle().setBgImage(tintedImage);
        
        Button menuButton = new Button("");
        menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        menuButton.addActionListener(e -> ((SideMenuBar)getToolbar().getMenuBar()).openMenu(null));

        Button settingsButton = new Button("");
        settingsButton.setUIID("Title");
        FontImage.setMaterialIcon(settingsButton, FontImage.MATERIAL_SETTINGS);
        
        Label space = new Label("", "TitlePictureSpace");
        space.setShowEvenIfBlank(true);
        Container titleComponent = 
                BorderLayout.north(
                    BorderLayout.west(menuButton).add(BorderLayout.EAST, settingsButton)
                ).
                add(BorderLayout.CENTER, space).
                add(BorderLayout.SOUTH, 
                        FlowLayout.encloseIn(
                                new Label("  QRCODE ", "WelcomeBlue"),
                                new Label("Scanner", "WelcomeWhite")
                        ));
        titleComponent.setUIID("BottomPaddingContainer");
        tb.setTitleComponent(titleComponent);
        
        Label separator = new Label("", "BlueSeparatorLine");
        separator.setShowEvenIfBlank(true);
        add(BorderLayout.NORTH, separator);
        

        XYMultipleSeriesDataset multi = new XYMultipleSeriesDataset();

        XYSeries seriesXY = new XYSeries("AAA", 0);
        multi.addSeries(seriesXY);
        seriesXY.add(3, 3);
        seriesXY.add(4, 4);
        seriesXY.add(5, 5);
        seriesXY.add(6, 4);
        seriesXY.add(7, 2);
        seriesXY.add(8, 5);

        seriesXY = new XYSeries("BBB", 0);
        multi.addSeries(seriesXY);
        seriesXY.add(3, 7);
        seriesXY.add(4, 6);
        seriesXY.add(5, 3);
        seriesXY.add(6, 2);
        seriesXY.add(7, 1);
        seriesXY.add(8, 4);

        //XYMultipleSeriesRenderer renderer = createChartMultiRenderer();
        
//        CubicLineChart chart = new CubicLineChart(multi, renderer,
//                0.5f);
        Button ScanCode=new Button("Scanner");
        ScanCode.setUIID("login");
        Container enclosure = BorderLayout.center(ScanCode).
                add(BorderLayout.NORTH, FlowLayout.encloseCenter(
                        new Label(" "),
                        new Label(" "),
                        new Label(" ")
                ));
        
        add(BorderLayout.CENTER, 
                enclosure);
        
        setupSideMenu(res);
    }

    private Image colorCircle(int color) {
        int size = Display.getInstance().convertToPixels(3);
        Image i = Image.createImage(size, size, 0);
        Graphics g = i.getGraphics();
        g.setColor(color);
        g.fillArc(0, 0, size, size, 0, 360);
        return i;
    }
    
    @Override
    protected void showOtherForm(Resources res) {
        new ProfileForm(res).show();
    }

   

}
