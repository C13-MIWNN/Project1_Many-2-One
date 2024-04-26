package nl.mitw.ch13.many2one.ctrlalteat.services;

import nl.mitw.ch13.many2one.ctrlalteat.model.Recipe;
import nl.mitw.ch13.many2one.ctrlalteat.model.RecipeIngredient;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;


//@Service
public class RecipePdfService {


    public static final int MAX_LINE_LENGTH = 80;

    public static byte[] makeRecipePDF(Recipe recipe) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);
        PDDocumentInformation pdfInfo = document.getDocumentInformation();
        pdfInfo.setAuthor("CtrlAltEat");
        pdfInfo.setTitle(recipe.getRecipeName());

        try {
            PDPageContentStream contentStream = new PDPageContentStream(document, page);

            contentStream.beginText();
            setContentStreamInitialSettings(contentStream);
            addTitleToContentStream(recipe, contentStream);
            addIngredientsToContentStream(recipe, contentStream);
            addPreparationMethodToContentStream(recipe, contentStream);


            contentStream.endText();
            contentStream.close();
            document.save(baos);
            document.close();
        } catch (IOException e) {
            System.err.println(e + "Failed to make pdf");
        }
        return baos.toByteArray();
    }

    private static void addPreparationMethodToContentStream(Recipe recipe, PDPageContentStream contentStream) throws IOException {
        contentStream.newLine();
        contentStream.newLine();
        contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 10);
        contentStream.showText("Preparation");
        contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 10);


        String preparationMethod = recipe.buildRecipePreparationMethodString();
        parseLineByLine(contentStream, preparationMethod);
    }

    private static void parseLineByLine(PDPageContentStream contentStream, String preparationMethod) throws IOException {
        String preparedPreparationMethod = prepareString(preparationMethod);

        BufferedReader bufferedReader = new BufferedReader(new StringReader(preparedPreparationMethod));
        String line = null;
        while ((line = bufferedReader.readLine()) != null) {
            contentStream.newLine();
            contentStream.showText(line);
        }
    }

    private static String prepareString(String preparationMethod) {
        String[] lines = preparationMethod.split("\n");
        StringBuilder stringBuilder = new StringBuilder();
        for (String line : lines) {
            stringBuilder.append(addNewLinesToString(line));
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    private static String addNewLinesToString(String line) {
        StringBuilder stringBuilder = new StringBuilder();
        String[] words = line.split(" ");
        int lineLength = 0;
        for (String word : words) {
            if (lineLength + word.length() > MAX_LINE_LENGTH) {
                stringBuilder.append("\n");
                stringBuilder.append(word);
                stringBuilder.append(" ");
                lineLength = word.length();
            }else {
                stringBuilder.append(word);
                stringBuilder.append(" ");
                lineLength += word.length();
            }
        }
        return stringBuilder.toString();
    }

    private static void setContentStreamInitialSettings(PDPageContentStream contentStream) throws IOException {
        contentStream.setLeading(14.5f);
        contentStream.newLineAtOffset(100, 750);
    }

    private static void addTitleToContentStream(Recipe recipe, PDPageContentStream contentStream) throws IOException {
        contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 12);
        contentStream.showText(recipe.getRecipeName());
        contentStream.newLine();
        contentStream.newLine();
    }

    private static void addIngredientsToContentStream(Recipe recipe, PDPageContentStream contentStream) throws IOException {
        contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 10);
        contentStream.showText("Ingredients");
        contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 10);
        for (RecipeIngredient ingredient : recipe.getIngredients()) {
            contentStream.newLine();
            contentStream.showText(ingredient.toString());
        }
    }


}
