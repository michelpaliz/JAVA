package Testing.android;

import java.io.File;
import java.util.ArrayList;



public class ImgList {
    
    public static void main(String[] args) {
        
    }

    public ArrayList<Integer> img(){
        ArrayList<Integer>imgList = new ArrayList<>();
        String path = "/home/michael/AndroidStudioProjects/selectCountry/app/src/main/res/drawable-v24/flags";
        File folder = new File(path);
        for (int i = 0; i < folder.length() ; i++) {
            int drawableId = getResources().getIdentifier(folder.getName().substring(0,folder.getName().lastIndexOf(".")),"drawable",path);
             imgList.add(drawableId);
        }
        return imgList;
    }
}
