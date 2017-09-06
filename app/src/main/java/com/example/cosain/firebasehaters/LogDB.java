package com.example.cosain.firebasehaters;

/**
 * Created by cosain on 9/2/2017.
 */

public class LogDB {

    private int id;
    private String raw_content;
    public static String tag_content;
    public static String time_stamp;


    public LogDB(){
    }

        public void setTag_content(String tag_content){

         this.tag_content = tag_content;

    }
        public String getTag_content(){

             return tag_content;

        }
           public void setTime_stamp(String time_stamp_recorded){

               this.time_stamp = time_stamp_recorded;

           }

    public String getTime_stamp(){

          return time_stamp;

         }

//          public void setId(int id){
//              this.id = id;
//          }
//public int getId(){
//
//    return id;
//}
//
//         public void setRaw_content(String raw_content){
//
//             this.raw_content = raw_content;
//
//         }
//
//         public String getRaw_content(){
//             return  raw_content;
//
//         }
}
