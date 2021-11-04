package com.example.modelsim.panes;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;

public class LineInfo{
    public Rectangle back;
    public Text firstLine,secondLine;
    private int x,y;

    public static final int HEIGHT = 80;
    public static final Font FONT = Font.font("Arial",HEIGHT * 0.25f);

    public LineInfo(int x, int y){
        this.x = x;this.y = y;
        this.back = new Rectangle(250,HEIGHT);
        this.back.setX(x);this.back.setY(y);this.back.setFill(Color.LIGHTGRAY);
        this.firstLine = new Text("First");this.firstLine.setX(x+2d);this.firstLine.setY(y+(HEIGHT * 0.25d));this.firstLine.setFont(FONT);
        this.secondLine = new Text("Second");this.secondLine.setX(x+2d);this.secondLine.setY(y+(HEIGHT * 0.5d));this.secondLine.setFont(FONT);
    }

    public void setFirstLine(String text){this.firstLine.setText(text);}
    public void setSecondLine(String text){this.secondLine.setText(text);}
    public List<Node> getNodes(){
        List<Node> list_node = new ArrayList<>();
        list_node.add(back);
        list_node.add(firstLine);list_node.add(secondLine);
        return list_node;
    }
    public void setColorBackground(Paint colorBackground){
        this.back.setFill(colorBackground);
    }
}
