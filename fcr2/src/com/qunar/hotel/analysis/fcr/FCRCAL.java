package com.qunar.hotel.analysis.fcr;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;

public class FCRCAL {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			File dirname = new File(args[0]);

			File writename = new File(args[1]);
			
			String reportdate = args[2];
			writename.createNewFile();
			BufferedWriter out = new BufferedWriter(new FileWriter(writename));


			for (File filename : dirname.listFiles()) {
				if (!filename.isFile())
					continue;
				InputStreamReader reader = new InputStreamReader(
						new FileInputStream(filename), "UTF-8");
				BufferedReader br = new BufferedReader(reader);
				String line = "";
				String lastOrderNo = "";
				
				FlowInfo fi = new FlowInfo();
				do {
					line = br.readLine();
					String[] flowInfo = (line == null ? null :line.split(FlowHistory.INPUT_SEPARATOR));
					if(!lastOrderNo.isEmpty() && (line == null || !lastOrderNo.equals(flowInfo[2]))) {
						fi.calResult();
						out.write(fi.toString(reportdate));
						fi.clearInfo();
					}
					fi.addInfo(line);
					lastOrderNo = line == null ? "" : flowInfo[2];
				} while (line != null);
				
				br.close();
			}
			
			System.out.println("OVER");

			out.flush();
			out.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
