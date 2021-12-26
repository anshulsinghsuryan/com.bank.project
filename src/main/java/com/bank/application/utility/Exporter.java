package com.bank.application.utility;

import java.awt.Color;
import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import com.bank.application.dto.account.TransactionDetails;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class Exporter {

	
	private TransactionDetails details;
	public Exporter(TransactionDetails emplist){
		this.details = emplist;
	}
	private void writeTableHeader(PdfPTable table) {
		PdfPCell cell = new PdfPCell();
		cell.setBackgroundColor(Color.BLUE);
		cell.setPadding(5);
		Font font = FontFactory.getFont(FontFactory.HELVETICA);
		font.setColor(Color.WHITE);
		cell.setPhrase(new Phrase("SN", font));
		table.addCell(cell);
		cell.setPhrase(new Phrase("FIELD", font));
		table.addCell(cell);
		cell.setPhrase(new Phrase("VALUE", font));
		table.addCell(cell);
	}
	private void writeTableData(PdfPTable table) {
		
		table.addCell(String.valueOf(1));
		table.addCell("TransactionId");
		table.addCell(String.valueOf(details.getTransactionId()));
		
		table.addCell(String.valueOf(2));
		table.addCell("Account Number");
		table.addCell(String.valueOf(details.getAccount()));
		
		table.addCell(String.valueOf(3));
		table.addCell("Status");
		table.addCell(details.getStatus());
		
		table.addCell(String.valueOf(4));
		table.addCell("Amount");
		table.addCell(String.valueOf(details.getAmmount()));
		
		table.addCell(String.valueOf(5));
		table.addCell("Type");
		table.addCell(details.getValue());
		
		table.addCell(String.valueOf(6));
		table.addCell("Transaction Date");
		table.addCell(details.getTransactionOn().toString());
	}
	public void export(HttpServletResponse response) throws DocumentException, IOException{
		Document document = new Document(PageSize.A4);
		PdfWriter.getInstance(document, response.getOutputStream());
		document.open();
		Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		font.setSize(18);
		font.setColor(Color.BLUE);
		Paragraph p = new Paragraph("Transaction Slip", font);
		p.setAlignment(Paragraph.ALIGN_CENTER);
		document.add(p);
		PdfPTable table = new PdfPTable(3);
		table.setWidthPercentage(100f);
		table.setWidths(new float[] {1.5f, 3.5f, 3.0f});
		table.setSpacingBefore(10);
		writeTableHeader(table);
		writeTableData(table);
		document.add(table);
		document.close();
	}

}
