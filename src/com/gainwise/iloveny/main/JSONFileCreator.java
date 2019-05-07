package com.gainwise.iloveny.main;


import com.gainwise.iloveny.DataStructures.*;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class JSONFileCreator {

    private FileInputStream file;
    private Workbook workbook;
    private ListToJsonGenerator listToJsonGenerator;


    public void begin(){
        initializeVariables();
        createJSONfiles();
    }

    private void initializeVariables() {
        listToJsonGenerator = new ListToJsonGenerator();
        try {
            file = new FileInputStream(new File("assets/menu_data.xlsx"));
            workbook = new XSSFWorkbook(file);
            Utils.logInfo("Successfully connected to spreadsheet, proceeding to create JSON files...");
        } catch (IOException e) {
            e.printStackTrace();
            Utils.logError("Issue with creating/accessing  the file input Stream. "+
                    "\n" + e.getMessage() + "\n" + e.toString());
        }
    }

    private void createJSONfiles() {
        createIngredientTypeJSONFile();
        createFoodCategoriesJSONFile();
        createIngredientsJSONFile();
        createPriceJSONFile();
        createFoodJSONFile();
    }

    private void createIngredientTypeJSONFile() {
        String ingredientTypesJSON =
                listToJsonGenerator.generateIngredientTypeObjectList(workbook, IngredientType.class);
        Utils.saveJSONfileWithName(ingredientTypesJSON, "ingredients_type.json");
    }

    private void createFoodCategoriesJSONFile() {
        String foodCategoriesJSON =
                listToJsonGenerator.generateFoodCategoriesJSONFile(workbook, FoodCategory.class);
        Utils.saveJSONfileWithName(foodCategoriesJSON, "food_categories.json");
    }

    private void createIngredientsJSONFile() {
        String ingredientsJSON =
                listToJsonGenerator.generateIngredientsJSONFile(workbook, Ingredient.class);
        Utils.saveJSONfileWithName(ingredientsJSON, "ingredients.json");
    }

    private void createPriceJSONFile() {
        String priceJSON =
                listToJsonGenerator.generatePriceJSONFile(workbook, Price.class);
        Utils.saveJSONfileWithName(priceJSON, "prices.json");
    }

    private void createFoodJSONFile() {
        String foodJSON =
                listToJsonGenerator.generateFoodJSONFile(workbook, Food.class);
        Utils.saveJSONfileWithName(foodJSON, "food.json");
    }

}
