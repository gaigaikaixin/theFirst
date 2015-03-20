package com;
import java.awt.Font;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.htmlcleaner.XPatherException;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

public class ChartData extends JFrame {
	ArrayList<HashMap> list;

	// TODO Auto-generated constructor stub
	public CategoryDataset getDatase() {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		for (HashMap map : this.list) {
			dataset.addValue(
					Integer.parseInt((String) map.get("highTemperature")),
					"最高气温", (Comparable) map.get("date"));
			dataset.addValue(
					Integer.parseInt((String) map.get("lowTemperature")),
					"最低气温", (Comparable) map.get("date"));

		}
		return dataset;
	}

	public JFreeChart getChart(){
		Object chartFactory;
		JFreeChart chart=ChartFactory.createLineChart("北京天气预报","15日气温","气温",this.getDatase(),PlotOrientation.VERTICAL,true,true,false);
		chart.setTitle(new TextTitle("天气气温",new Font("宋体",Font.BOLD+Font.ITALIC,20)));
		CategoryPlot plot =(CategoryPlot)chart.getPlot();
		CategoryAxis catagoryAxis=plot.getDomainAxis();
		catagoryAxis.setLabelFont(new Font("黑体",Font.BOLD,12));
		LineAndShapeRenderer  lineAndshaperenderer=(LineAndShapeRenderer)plot.getRenderer();
		lineAndshaperenderer.setBaseShapesVisible(true);
		lineAndshaperenderer.setBaseLinesVisible(true);
		lineAndshaperenderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		lineAndshaperenderer.setBaseItemLabelsVisible(true);
		return chart;
	}	public JPanel getPanel() {
		JFreeChart chart = getChart();
		return new ChartPanel(chart);

	}

	public ChartData(ArrayList list) {
		this.setSize(1024, 600);
		this.list = list;
		JPanel chartPanel = this.getPanel();
		this.add(chartPanel);
	}

	public static void main(String[] ards) throws IOException, XPatherException {
		final String urlString = "http://www.15tianqi.com/beijing/";
		Requests requests = new Requests(urlString);
		HtmlParser htmlParser = new HtmlParser(requests.getContext());
		ChartData chartData = new ChartData(htmlParser.parse());
		chartData.setVisible(true);
	}

}
