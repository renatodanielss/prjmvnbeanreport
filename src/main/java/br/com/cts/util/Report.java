package br.com.cts.util;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import net.sf.jasperreports.view.JasperViewer;

public class Report<T> {
	private Collection<T> dataList;
	private String jrxml;
	
	public Report() {
	}

	public Report(Collection<T> dataList, String jrxml) {
		super();
		this.dataList = dataList;
		this.jrxml = jrxml;
	}
	
	public Collection<T> getDataList() {
		return dataList;
	}

	public void setDataList(Collection<T> dataList) {
		this.dataList = dataList;
	}

	public String getJrxml() {
		return jrxml;
	}

	public void setJrxml(String jrxml) {
		this.jrxml = jrxml;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void getReport(Collection<T> dataList, String jrxml) throws Exception {
		JasperReport report = null;
		JasperDesign reportDesign;

		try {
			reportDesign = JRXmlLoader.load(jrxml);
			report = JasperCompileManager.compileReport(reportDesign);

		} catch (JRException e2) {
			e2.printStackTrace();
		}

		//JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(dataList);

		Map params = new HashMap();
		params.put("dataSource", dataList);
		
		JasperPrint jsPrint = null;

		try {
			jsPrint = JasperFillManager.fillReport(report, params, new JREmptyDataSource());
		} catch (JRException e1) {
			e1.printStackTrace();
		}

		/*JasperViewer viewer = new JasperViewer(jsPrint, false);
		viewer.setVisible(true);*/
		
		OutputStream outputStream = new FileOutputStream("D:\\D\\Desktop\\arq.pdf");
		
		//exportar para pdf
		JRPdfExporter exporter = new JRPdfExporter();
		exporter.setExporterInput(new SimpleExporterInput(jsPrint));
		exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));
		SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
		configuration.setMetadataAuthor("Daniel");;
		exporter.setConfiguration(configuration);
		exporter.exportReport();
	}
}