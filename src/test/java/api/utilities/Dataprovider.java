package api.utilities;

import org.testng.annotations.DataProvider;

public class Dataprovider {

	@DataProvider
	public String[][] getAllData() {

		String path = System.getProperty("user.dir") + "//testdata//userData.xlsx";
		XLUtility xl = new XLUtility(path);

		int rowCount = xl.getRowCount("Sheet1");
		int cellCount = xl.getCellCount("Sheet1", 1);

		String apiData[][] = new String[rowCount][cellCount];

		for (int i = 1; i <= rowCount; i++) {
			for (int j = 0; j < cellCount; j++) {

				apiData[i - 1][j] = xl.getCellData("Sheet1", i, j);
			}
		}

		return apiData;

	}

	@DataProvider
	public String[] getUserName() {
		String path = System.getProperty("user.dir") + "//testdata//userData.xlsx";
		XLUtility xl = new XLUtility(path);

		int rowCount = xl.getRowCount("Sheet1");

		String apiData[] = new String[rowCount];

		for (int i = 1; i <= rowCount; i++) {
			apiData[i - 1] = xl.getCellData("Sheet1", i, 1);
		}

		return apiData;
	}

}
