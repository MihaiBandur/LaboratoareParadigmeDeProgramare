package org.example;

import org.apache.commons.math3.stat.regression.SimpleRegression;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;

class LinearRegressionChart extends JFrame {

    public LinearRegressionChart(double[][] data) {
        setTitle("Regresie Liniară");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        XYSeries series = new XYSeries("Puncte");
        SimpleRegression regression = new SimpleRegression();

        for (double[] point : data) {
            series.add(point[0], point[1]);
            regression.addData(point[0], point[1]);
        }


        double slope = regression.getSlope();
        double intercept = regression.getIntercept();
        System.out.println("Ecuatia: y = " + slope + "x + " + intercept);


        XYSeries regressionSeries = new XYSeries("Regresie Liniara");
        double minX = data[0][0];
        double maxX = data[data.length - 1][0];
        regressionSeries.add(minX, slope * minX + intercept);
        regressionSeries.add(maxX, slope * maxX + intercept);


        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series);
        dataset.addSeries(regressionSeries);

        JFreeChart chart = ChartFactory.createXYLineChart(
                "Regresie Liniară",
                "X",
                "Y",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );


        XYPlot plot = chart.getXYPlot();
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesShapesVisible(0, true);
        renderer.setSeriesShapesVisible(1, false);
        renderer.setSeriesPaint(1, Color.RED);
        plot.setRenderer(renderer);


        ChartPanel chartPanel = new ChartPanel(chart);
        setContentPane(chartPanel);
    }

    public static void main(String[] args) {
        double[][] data = {
                {1, 2}, {2, 2.8}, {3, 3.6}, {4, 4.5}, {5, 5.1}
        };

        SwingUtilities.invokeLater(() -> {
            LinearRegressionChart example = new LinearRegressionChart(data);
            example.setVisible(true);
        });
    }
}
