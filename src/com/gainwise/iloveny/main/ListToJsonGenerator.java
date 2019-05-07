package com.gainwise.iloveny.main;

import com.gainwise.iloveny.DataStructures.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import jdk.jshell.execution.Util;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Optional;

public class ListToJsonGenerator {
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public <T> String generateIngredientTypeObjectList(Workbook workbook, Class<T> classOfList){
        String ingredientsTypeJSON = "";
        ArrayList<T> ingredientTypesList = new ArrayList<>();
        Sheet sheet = workbook.getSheet("Ingredient Type");
        boolean titleRow = true;


        for(Row row : sheet){
            if(!titleRow){
                int id = (int) row.getCell(0).getNumericCellValue();
                String name = row.getCell(1).getRichStringCellValue().getString();
                IngredientType ingredientType = new IngredientType(id,name);
                ingredientTypesList.add((T) ingredientType);
            }else{
                titleRow = false;
            }
        }

        ingredientsTypeJSON = convertListToJSON(ingredientTypesList, classOfList);

        return ingredientsTypeJSON;
    }

    public <T> String generateFoodCategoriesJSONFile(Workbook workbook, Class<T> classOfList){
        String foodCategoriesJSON = "";
        ArrayList<FoodCategory> foodCategoriesList = new ArrayList<>();
        Sheet sheet = workbook.getSheet("Food Categories");
        boolean titleRow = true;

        for(Row row : sheet){
            if(!titleRow){
                int id = (int) row.getCell(0).getNumericCellValue();
                String name = row.getCell(1).getRichStringCellValue().getString();
                String description = assignDescription(row,2);
                FoodCategory foodCategory = new FoodCategory(id,name,description);
                foodCategoriesList.add(foodCategory);
            }else{
                titleRow = false;
            }
        }

        foodCategoriesJSON = convertListToJSON(foodCategoriesList, classOfList);

        return foodCategoriesJSON;
    }

    public <T> String generateIngredientsJSONFile(Workbook workbook, Class<T> classOfList){
        String ingredientsJSON = "";
        ArrayList<Ingredient> ingredientsList = new ArrayList<>();
        Sheet sheet = workbook.getSheet("Ingredients");
        boolean titleRow = true;


        for(Row row : sheet){
            if(!titleRow){
                int id = (int) row.getCell(0).getNumericCellValue();
                String name = row.getCell(1).getRichStringCellValue().getString();
                int type = (int) row.getCell(2).getNumericCellValue();
                Ingredient ingredientType = new Ingredient(id,name, type);
                ingredientsList.add(ingredientType);
            }else{
                titleRow = false;
            }
        }

        ingredientsJSON = convertListToJSON(ingredientsList, classOfList);

        return ingredientsJSON;
    }

    public <T> String generatePriceJSONFile(Workbook workbook, Class<T> classOfList){
        String pricesJSON = "";
        ArrayList<Price> pricesList = new ArrayList<>();
        Sheet sheet = workbook.getSheet("Prices");
        boolean titleRow = true;


        for(Row row : sheet){
            if(!titleRow){
                int id = (int) row.getCell(0).getNumericCellValue();
                double price =  row.getCell(1).getNumericCellValue();
                Price ingredientType = new Price(id,price);
                pricesList.add(ingredientType);
            }else{
                titleRow = false;
            }
        }

        pricesJSON = convertListToJSON(pricesList, classOfList);

        return pricesJSON;
    }

    public <T> String generateFoodJSONFile(Workbook workbook, Class<T> classOfList){
        String foodJSON = "";
        ArrayList<Food> foodList = new ArrayList<>();
        Sheet sheet = workbook.getSheet("Foods");
        boolean titleRow = true;
        int counter = 0;
        for(Row row : sheet){
            if(!titleRow){
                Utils.logInfo(String.valueOf(counter++));
                int id = (int) row.getCell(0).getNumericCellValue();
                String name =  row.getCell(1).getRichStringCellValue().getString();
                int foodCategory = (int) row.getCell(2).getNumericCellValue();
                Double price =  row.getCell(4).getNumericCellValue();
                String description = assignDescription(row,5);
                String ingredientsArray = "";
                switch (row.getCell(3).getCellTypeEnum()) {
                    case STRING:
                        ingredientsArray = row.getCell(3).getRichStringCellValue().getString();
                        break;
                    case NUMERIC:
                        ingredientsArray = String.valueOf(row.getCell(3).getNumericCellValue());
                        break;
                    case BOOLEAN:
                    case FORMULA:
                    default:
                        ingredientsArray = "";
                }
                Food food;
                if(foodCategory == 1 || foodCategory == 2){
                    food = new Food(id,name + " Pizza",foodCategory,ingredientsArray,price,description);
                }else{
                    food = new Food(id,name,foodCategory,ingredientsArray,price,description);
                }
                foodList.add(food);
            }else{
                titleRow = false;
            }
        }

        foodJSON = convertListToJSON(foodList, classOfList);

        return foodJSON;
    }

    private <T> String convertListToJSON(ArrayList listIn, Class<T> genericOfList){
        String jsonReturnString = "";
        Type typeToken = new TypeToken<ArrayList<T>>(){}.getType();
        jsonReturnString = gson.toJson(listIn, typeToken);
        return jsonReturnString;
    }

    private String assignDescription(Row row, int indexOfCell){
        String value = "";
        switch (row.getCell(indexOfCell).getCellTypeEnum()) {
            case STRING:
                value = row.getCell(indexOfCell).getRichStringCellValue().getString();
                break;
            case NUMERIC:
            case BOOLEAN:
            case FORMULA:
            default:
                value = "";
        }
        return value;
    }




}
