package GAME;

import GUI.*;

public class FoodCreator extends Thread {
	
    private int fieldsX = 12;
    private int fieldsY = 9;
    private FoodManager foodMngr = null;
    private ISpielLogik gui = null;

    public FoodCreator(int fieldsX, int fieldsY, FoodManager foodMngr, ISpielLogik gui) {
        this.fieldsX = fieldsX;
        this.fieldsY = fieldsY;
        this.foodMngr = foodMngr;
        this.gui = gui;
    }

    public FoodCreator(int pxX, int pxY, FoodManager foodMngr) {
            this.fieldsX = pxX;
            this.fieldsY = pxY;
            this.foodMngr = foodMngr;
    }

    @Override
    public void run() {
            while (true) {
                    Food tmpFood = null;
                    if ( !foodMngr.isFull() ){
                        do {
                                int posX = (int)(fieldsX * Math.random());
                                int posY = (int)(fieldsY * Math.random());
    // TODO Wertebereich festlegen und randomisieren
                                int Value = (int)(100*Math.random());
                                long TTL = 100000;
                                long MHD = 30000;
                                tmpFood = new Food(Value, TTL, MHD, posX, posY);
                        } while (foodMngr.containsFood(tmpFood));
                        foodMngr.addFood(tmpFood);
                        gui.neuesFutter(tmpFood.getPosX(), tmpFood.getPosY(), tmpFood.getMHDinSec());
                    }
                    try {
// TODO schlafzeit noch festlegen
                        do {
                            sleep(1000);
                        } while (foodMngr.isFull());
                    } catch (InterruptedException e) {	}
            }
    }
	

}