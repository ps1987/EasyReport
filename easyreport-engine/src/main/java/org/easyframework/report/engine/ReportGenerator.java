package org.easyframework.report.engine;

import org.easyframework.report.engine.data.LayoutType;
import org.easyframework.report.engine.data.ReportDataSet;
import org.easyframework.report.engine.data.ReportDataSource;
import org.easyframework.report.engine.data.ReportParameter;
import org.easyframework.report.engine.data.ReportTable;

/**
 * 报表产生器类
 * 
 */
public class ReportGenerator {

	/**
	 * 
	 * @param ds
	 * @param parameter
	 * @return ReportTable
	 */
	public static ReportTable generate(ReportDataSource ds, ReportParameter parameter) {
		return generate(getDataSet(ds, parameter), parameter);
	}

	/**
	 * 
	 * @param dataSet
	 * @param parameter
	 * @return ReportTable
	 */
	public static ReportTable generate(ReportDataSet dataSet, ReportParameter parameter) {
		ReportBuilder builder = createBuilder(dataSet, parameter);
		ReportDirector director = new ReportDirector(builder);
		director.build();
		return builder.getTable();
	}

	/**
	 * 
	 * @param ds
	 * @param parameter
	 * @return
	 */
	public static ReportDataSet getDataSet(ReportDataSource ds, ReportParameter parameter) {
		return new DataExecution(ds, parameter).execute();
	}

	private static ReportBuilder createBuilder(ReportDataSet reportData, ReportParameter parameter) {
		if (parameter.getStatColumnLayout() == LayoutType.HORIZONTAL) {
			return new HorizontalStatColumnReportBuilder(reportData, parameter);
		}
		return new VerticalStatColumnReportBuilder(reportData, parameter);
	}
}
