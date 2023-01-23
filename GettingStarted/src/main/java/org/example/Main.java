package org.example;

public class Main {
	private static final String XLSX_FILE = "ExcelFile.xlsx";
    private static final String PASSWORD = "whatever";

	public static void main(String[] args) throws IOException {
		importXlsx();
	}

	private static void importXlsx() throws IOException {
		Path path = Paths.get(XLSX_FILE);
		if (path.toFile().exists()) {
			XSSFWorkbook workbook = (XSSFWorkbook) WorkbookFactory.create(new FileInputStream(XLSX_FILE), PASSWORD);
			XSSFSheet sheet = workbook.getSheetAt(0);
			Iterator<Row> rowIterator = sheet.iterator();
			while (rowIterator.hasNext()) {
				Row nextRow = rowIterator.next();
				Iterator<Cell> cellIterator = nextRow.cellIterator();
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					if (!CellType.BLANK.equals(cell.getCellType())) {
						System.out.print(cell.getAddress() + "\t" + cell.getCellType() + "\t");
						switch (cell.getCellType()) {
							case STRING:
								System.out.println(cell.getStringCellValue());
								break;
							case NUMERIC:
								System.out.println(cell.getNumericCellValue());
								break;
							default:
								System.out.println();
								break;
						}
					}
				}
			}
		} else {
			System.err.println("File '" + XLSX_FILE + "' not found.");
		}
	}
}
