package nl.mitw.ch13.many2one.ctrlalteat.services;


import nl.mitw.ch13.many2one.ctrlalteat.model.Ingredient;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

@Service
public class IngredientImportService {
    private static final NumberFormat format = NumberFormat.getInstance(Locale.FRANCE);


    public List<Ingredient> readCsvFile(String csvFileName) {

        List<Ingredient> ingredients = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(csvFileName))) {
            scanner.nextLine();
            while (scanner.hasNextLine()) {
                ingredients.add(getIngredientFromLine(scanner.nextLine()));
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return ingredients;
    }


    private static Ingredient getIngredientFromLine(String line) {
        List<String> values = new ArrayList<>();
        Ingredient ingredient = new Ingredient();
        try (Scanner rowScanner = new Scanner(line)) {
            rowScanner.useDelimiter("\\|");
            while (rowScanner.hasNext()) {
                values.add(rowScanner.next());
            }
            setIngredientVariables(ingredient, values);
        }
        return ingredient;
    }
    private static void setIngredientVariables(Ingredient ingredient, List<String> variables) {
        ingredient.setName(variables.get(5));
        ingredient.setkCal(variables.get(12));
        ingredient.setProtein(Double.parseDouble(variables.get(14).replace(',', '.')));
        ingredient.setFats(Double.parseDouble(variables.get(19).replace(',', '.')));
        ingredient.setCarbs(Double.parseDouble(variables.get(27).replace(',', '.')));

    }

}





