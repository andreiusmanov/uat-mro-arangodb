package uz.uat.mro.apps.utils;

import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

public class ItextTrial {
    private static String sourceString = "/home/andreyu/Documents/B-757/MPD-B757/AB___012.PDF";
    private static String targetString;

    public static void main(String[] args) {

        try {
            // PdfReader reader = new PdfReader(sourceString);
            // PdfDocument doc = new PdfDocument(reader);
            // PdfPage page = doc.getPage(259);

            // // Extract the page content using PdfTextExtractor.
            // String pageContent = PdfTextExtractor.getTextFromPage(page);

            // System.out.println("Content on Page " + ": " + pageContent);

            PDDocument doc = PDDocument.load(new File(sourceString));

            PDFTextStripper stripper = new PDFTextStripper();
            stripper.setStartPage(284);
            stripper.setEndPage(284);
            stripper.setShouldSeparateByBeads(false);
            stripper.getSpacingTolerance();
            String text = stripper.getText(doc);
            System.out.println(text);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
