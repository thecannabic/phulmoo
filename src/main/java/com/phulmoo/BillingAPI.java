
package com.phulmoo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
import com.itextpdf.text.pdf.draw.VerticalPositionMark;
import com.phulmoo.entity.Order;
import com.phulmoo.modal.OrderUIModel;

public class BillingAPI {
	public static String generatePDF(Order order, OrderUIModel oum) {
		File file = new File(order.getOrderID() + "-INVOICE.pdf");
		// if (file.exists()) {
		// file.delete();
		// try {
		// file.createNewFile();
		// } catch (IOException e) {
		// e.printStackTrace();
		// return null;
		// }
		// }
		Document document = new Document(PageSize.A4);
		try {
			PdfWriter.getInstance(document, new FileOutputStream(file));

			Font headfont = new Font(Font.FontFamily.HELVETICA, 20, Font.BOLD, BaseColor.RED);
			Font TIfont = new Font(Font.FontFamily.HELVETICA, 15, Font.BOLD, BaseColor.RED);
			Font thfooterFont = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.BLACK);
			Font footerFont = new Font(Font.FontFamily.HELVETICA, 8, Font.BOLD, BaseColor.BLACK);

			Chunk glue = new Chunk(new VerticalPositionMark());
			Paragraph gstindate = new Paragraph("GSTN");
			gstindate.add(new Chunk(glue));
			gstindate.add("INVOICE DATE");
			Paragraph stateIno = new Paragraph("STATE");
			stateIno.add(new Chunk(glue));
			stateIno.add("INVOICE NO.");
			document.open();
			document.add(new Paragraph("Phulmoo", headfont));
			document.add(gstindate);
			document.add(stateIno);
			Paragraph title = new Paragraph("TAX INVOICE", TIfont);
			title.setAlignment(Element.ALIGN_CENTER);
			document.add(title);
			document.add(Chunk.NEWLINE);
			LineSeparator lineFormatting = new LineSeparator();
			document.add(lineFormatting);
			String[] headerst2 = { "CUSTOMER NAME", "BILLING ADDRESS", "SHIPPING ADDRESS" };
			Font table3FontHead = new Font(Font.FontFamily.HELVETICA, 8, Font.BOLD, BaseColor.BLACK);
			addCellsInTable2(document, headerst2, table3FontHead);

			String customerName = "Gurdeep";
			String customerAddress = "Chandigarh sector 4";
			String customerShippingAdd = "Mohali";
			String[] valuest2 = { customerName, customerAddress, customerShippingAdd };
			Font table3ValueFont = new Font(Font.FontFamily.HELVETICA, 8, Font.NORMAL, BaseColor.BLACK);
			addCellsInTable(document, valuest2, table3ValueFont);
			document.add(Chunk.NEWLINE);
			document.add(Chunk.NEWLINE);
			document.add(lineFormatting);
			String customerGSTIN = "C1358s4";
			String placeofSupply = "Chandigarh sector 14";
			String[] headerst3 = { "CUSTOMER GSTIN", "PLACE OF SUPPLY" };
			addCellsInTable3(document, headerst3, table3FontHead);
			String[] valuest3 = { customerGSTIN, placeofSupply };
			addCellsInTable(document, valuest3, table3ValueFont);

			document.add(Chunk.NEWLINE);
			document.add(Chunk.NEWLINE);
			document.add(lineFormatting);

			String[] headers = { "SR NO.", "ITEM NO", "PRODUCT", "RATE", "QTY", "AMOUNT", "TAX AMOUNT", "CGST@2.5%",
					"SGST@2.5%", "IGST", "TOTAL" };
			addCellsInTable(document, headers, table3FontHead);

			String srNo_PI = "1";
			String itemNo_PI = "1234";
			String product_PI = "COTTON";
			String rate_PI = "1000";
			String qty_PI = "2";
			String amount_PI = "2000";
			String taxAmount_PI = "1904";
			String cgst_PI = "47.62";
			String sgst_PI = "47.62";
			String igst_PI = "";
			String total_PI = "2000";

			String[] values = { srNo_PI, itemNo_PI, product_PI, rate_PI, qty_PI, amount_PI, taxAmount_PI, cgst_PI,
					sgst_PI, igst_PI, total_PI };
			addCellsInTable(document, values, table3ValueFont);

			document.add(Chunk.NEWLINE);
			document.add(Chunk.NEWLINE);
			Paragraph forphulmoo_P = new Paragraph("FOR PHULMOO");
			forphulmoo_P.setAlignment(Element.ALIGN_RIGHT);
			document.add(forphulmoo_P);
			document.add(Chunk.NEWLINE);
			Paragraph for_Sign = new Paragraph("AUTH. SIGNATORY");
			for_Sign.setAlignment(Element.ALIGN_RIGHT);
			document.add(for_Sign);
			document.add(Chunk.NEWLINE);
			document.add(Chunk.NEWLINE);
			document.add(Chunk.NEWLINE);
			document.add(Chunk.NEWLINE);
			document.add(Chunk.NEWLINE);
			document.add(Chunk.NEWLINE);
			document.add(Chunk.NEWLINE);
			Paragraph footer_P = new Paragraph("THANK YOU, VISIT AGAIN!", thfooterFont);
			footer_P.setAlignment(Element.ALIGN_CENTER);
			document.add(footer_P);
			document.add(Chunk.NEWLINE);
			document.add(Chunk.NEWLINE);
			document.add(lineFormatting);
			Paragraph footer_ADD = new Paragraph(
					"PHULMOO.COM, V.P.O-KHARWAR,TEHSIL-BHORANJ, DISTT.-HAMIRPUR,HP-176041,MOB:9418025114. T&C AVAILABLE AT PHULMOO.COM",
					footerFont);
			footer_ADD.setAlignment(Element.ALIGN_CENTER);
			document.add(footer_ADD);
			document.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (DocumentException e) {
			e.printStackTrace();
			return null;
		}
		System.out.println("Email sent to the customer and the biller");
		System.out.println(file.getAbsolutePath());
		return file.getPath();
	}

	private static void addCellsInTable(Document document, String[] cellText, Font table3FontHead)
			throws DocumentException {
		if (cellText != null) {
			PdfPTable table = new PdfPTable(cellText.length);
			table.setTotalWidth(520);
			table.setLockedWidth(true);
			table.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
			for (int i = 0; i < cellText.length; i++) {
				String cellStr = cellText[i];
				PdfPCell cell = new PdfPCell(new Phrase(cellStr, table3FontHead));
				table.addCell(cell);
			}
			document.add(table);
		}
	}

	private static void addCellsInTable2(Document document, String[] cellText, Font table3FontHead)
			throws DocumentException {
		if (cellText != null) {
			PdfPTable table = new PdfPTable(cellText.length);
			table.setTotalWidth(520);
			table.setLockedWidth(true);
			table.getDefaultCell().setBorder(0);
			for (int i = 0; i < cellText.length; i++) {
				String cellStr = cellText[i];
				PdfPCell cell = new PdfPCell(new Phrase(cellStr, table3FontHead));
				table.addCell(cell);
			}
			document.add(table);
		}
	}

	private static void addCellsInTable3(Document document, String[] cellText, Font table3FontHead)
			throws DocumentException {
		if (cellText != null) {
			PdfPTable table = new PdfPTable(cellText.length);
			table.setTotalWidth(520);
			table.setLockedWidth(true);
			table.getDefaultCell().setBorder(0);
			for (int i = 0; i < cellText.length; i++) {
				String cellStr = cellText[i];
				PdfPCell cell = new PdfPCell(new Phrase(cellStr, table3FontHead));
				table.addCell(cell);
			}
			document.add(table);
		}
	}

	private static void addCellsInTable3Total(Document document, String[] cellText, Font table3FontHead)
			throws DocumentException {
		if (cellText != null) {
			PdfPTable table = new PdfPTable(cellText.length);
			table.setTotalWidth(520);
			table.setLockedWidth(true);
			table.getDefaultCell().setBorder(0);
			for (int i = 0; i < cellText.length; i++) {
				String cellStr = cellText[i];
				PdfPCell cell = new PdfPCell(new Phrase(cellStr, table3FontHead));
				table.addCell(cell);
			}
			document.add(table);
		}
	}
}
