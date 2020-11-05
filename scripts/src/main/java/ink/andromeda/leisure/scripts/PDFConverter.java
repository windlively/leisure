package ink.andromeda.leisure.scripts;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.fit.pdfdom.PDFDomTree;

import javax.xml.parsers.ParserConfigurationException;
import java.io.*;

public class PDFConverter {

    public static void main(String[] args) throws IOException, ParserConfigurationException {
        PDDocument pdf = PDDocument.load(new File("/Users/andromeda/Desktop/1.pdf"));
        Writer output = new PrintWriter("pdf.html", "utf-8");
        new PDFDomTree().writeText(pdf, output);
        output.close();
    }

}
