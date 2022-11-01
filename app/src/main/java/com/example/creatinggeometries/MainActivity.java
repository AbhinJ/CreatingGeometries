package com.example.creatinggeometries;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.esri.arcgisruntime.ArcGISRuntimeEnvironment;
import com.esri.arcgisruntime.geometry.Point;
import com.esri.arcgisruntime.geometry.PointCollection;
import com.esri.arcgisruntime.geometry.Polyline;
import com.esri.arcgisruntime.geometry.SpatialReference;
import com.esri.arcgisruntime.geometry.SpatialReferences;
import com.esri.arcgisruntime.mapping.ArcGISMap;
import com.esri.arcgisruntime.mapping.BasemapStyle;
import com.esri.arcgisruntime.mapping.Viewpoint;
import com.esri.arcgisruntime.mapping.view.Graphic;
import com.esri.arcgisruntime.mapping.view.GraphicsOverlay;
import com.esri.arcgisruntime.mapping.view.MapView;
import com.esri.arcgisruntime.symbology.SimpleFillSymbol;
import com.esri.arcgisruntime.symbology.SimpleLineSymbol;
import com.esri.arcgisruntime.symbology.SimpleMarkerSymbol;

public class MainActivity extends AppCompatActivity {
    MapView map1;
    private Point createPoint(){
        Point pt = new Point(72.87, 19.07, SpatialReferences.getWgs84());
        return pt;
    }
    private Polyline createLine(){
        PointCollection polyPoints = new PointCollection(SpatialReferences.getWgs84());
        polyPoints.add(72.9133, 19.1334);
        polyPoints.add(79.0023 ,10.1692);

        Polyline line = new Polyline(polyPoints);
        return line;
    }
    private void addGraphics(){
        GraphicsOverlay graphicsOverlay = new GraphicsOverlay();
        map1.getGraphicsOverlays().add(graphicsOverlay);

        SimpleMarkerSymbol pointSymbol = new SimpleMarkerSymbol(SimpleMarkerSymbol.Style.TRIANGLE, Color.BLUE, 20);

        graphicsOverlay.getGraphics().add(new Graphic(createPoint(), pointSymbol));

        SimpleLineSymbol lineSymbol = new SimpleLineSymbol(SimpleLineSymbol.Style.SOLID, Color.BLUE, 1);
        graphicsOverlay.getGraphics().add(new Graphic(createLine(), lineSymbol));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        map1 = findViewById(R.id.mapView);

        ArcGISRuntimeEnvironment.setApiKey("AAPKbb2a8f77ffd14fe9a5b98f261a5938bdub_FAryDtoXbCfKPwBYaXw9le4lAPa5piuBYpydU4nbm-HrbkNpQId5IzNXP7O7c");
        ArcGISMap map = new ArcGISMap(BasemapStyle.ARCGIS_TOPOGRAPHIC);

        map1.setMap(map);
        addGraphics();
        map1.setViewpoint(new Viewpoint(
                19.1334, 72.9133, 100000
        ));
    }

}