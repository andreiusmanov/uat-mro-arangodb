package uz.uat.mro.apps.utils;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import uz.uat.mro.apps.model.library.entity.MpdSubzone;

public class importLibrary {
    public List<MpdSubzone> importSubzones(String fileName, int startPage, int endPage){
        File f = new File(fileName);
        try {
            PDDocument doc = PDDocument.load(f);
            PDFTextStripper stripper = new PDFTextStripper();
            stripper.setStartPage(startPage);
            stripper.setEndPage(endPage);
            stripper.setShouldSeparateByBeads(false);
            stripper.getSpacingTolerance();
            String text = stripper.getText(doc);
            System.out.println(text);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        
        
        
        return null;
    }
}
