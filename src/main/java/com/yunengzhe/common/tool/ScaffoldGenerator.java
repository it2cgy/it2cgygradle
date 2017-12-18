package com.yunengzhe.common.tool;

import com.yunengzhe.common.tool.scaffold.ScaffoldGen;
 

public class ScaffoldGenerator {

	public static void main(String[] args) {
		// arg1 子系统名
		// arg2 业务对象名,即Model,双词以上要求单词首字大写
		// arg3 表名
//		ScaffoldGen generator7 = new ScaffoldGen("PVMTS", "Consignee", "pvmts_letter_consignee");
//		ScaffoldGen generator7 = new ScaffoldGen("PVMTS", "Letter", "pvmts_letter");
//		ScaffoldGen generator7 = new ScaffoldGen("PVMTS", "Sender", "pvmts_letter_sender");
//		ScaffoldGen generator7 = new ScaffoldGen("PVMTS", "NoticeInfo", "pvmts_notice");
//		ScaffoldGen generator7 = new ScaffoldGen("PVMTS", "NoticeType", "pvmts_notice_type");
//		ScaffoldGen generator7 = new ScaffoldGen("PVMTS", "NoticeReader", "pvmts_notice_read");
//		ScaffoldGen generator7 = new ScaffoldGen("PVMTS", "RulesInfo", "pvmts_rules_info");
//		ScaffoldGen generator7 = new ScaffoldGen("PVMTS", "RulesReader", "pvmts_rules_read");
//		ScaffoldGen generator7 = new ScaffoldGen("PVMTS", "PowerStation", "mt_project");
//		ScaffoldGen generator7 = new ScaffoldGen("PVMTS", "Ammeter", "mc_electric_meter");
//		ScaffoldGen generator7 = new ScaffoldGen("PVMTS", "Inverter", "mc_inverter");
//		ScaffoldGen generator7 = new ScaffoldGen("PVMTS", "GenerationMonth", "mc_month_generation");
//		ScaffoldGen generator7 = new ScaffoldGen("PVMTS", "WeatherStation", "mc_meteor_monitor");
//		ScaffoldGen generator7 = new ScaffoldGen("PVMTS", "FaultInfo", "pvmts_fault_info");
//		ScaffoldGen generator7 = new ScaffoldGen("PVMTS", "PointInfo", "pvmts_point_info");
//		ScaffoldGen generator7 = new ScaffoldGen("PVMTS", "CurveInfo", "pvmts_curve_info");
//		ScaffoldGen generator7 = new ScaffoldGen("PVMTS", "ReportsPoints", "pvmts_report_points");
//		ScaffoldGen generator8 = new ScaffoldGen("PVMTS", "ReportsPoints", "pvmts_report_points");
//		generator7.execute(false);

//		ScaffoldGen generator8 = new ScaffoldGen("PVMTS", "SettingInfo", "pvmts_setting_push");
//		generator8.execute(false);

//		ScaffoldGen generator7 = new ScaffoldGen("PVMTS", "Company", "pvmts_company");
//		ScaffoldGen generator8 = new ScaffoldGen("PVMTS", "User", "pvmts_user");
//		ScaffoldGen generator9 = new ScaffoldGen("PVMTS", "UserAndRole", "pvmts_user_role");
//		ScaffoldGen generator10 = new ScaffoldGen("PVMTS", "User", "pvmts_user");
//		ScaffoldGen generator7 = new ScaffoldGen("PVMTS", "ScanInfo", "pvmts_scanInfo");
//		ScaffoldGen generator8 = new ScaffoldGen("PVMTS", "FaultSolve", "pvmts_workorder_solve");
//		ScaffoldGen generator8 = new ScaffoldGen("PVMTS", "TaskConfig", "pvmts_task");
//		ScaffoldGen generator7 = new ScaffoldGen("PVMTS", "MeasurementBox", "mc_measurement_box");
		ScaffoldGen generator8 = new ScaffoldGen("PVMTS", "DeviceRatedIv", "pvmts_device_rated_iv");
		generator8.execute(false);
}
}