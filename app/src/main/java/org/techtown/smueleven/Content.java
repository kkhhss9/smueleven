package org.techtown.smueleven;


public class Content {

    String productName;
    String productNum;
    String productDate;
    int productCount;

    public String getNum(){
        return productNum;
    }

    public void setNum(String productNum){
        this.productNum= productNum;
    }

    public String getContent(){
        return productName;
    }

    public void setContent(String productName){
        this.productName= productName;
    }

    public String getDate(){
        return productDate;
    }

    public void setDate(String productDate){
        this.productDate = productDate;
    }

    public int getProductCount(){ return productCount; }

    public void setProductCount(int productCount){ this.productCount = productCount; }

    public Content(String productName,String productNum, int productCount, String productDate){
        this.productName=productName;
        this.productNum = productNum;
        this.productCount=productCount;
        this.productDate=productDate;

    }
}