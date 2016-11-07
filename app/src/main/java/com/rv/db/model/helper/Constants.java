package com.rv.db.model.helper;

/**
 * Created by mc11 on 3/11/16.
 */
// back back what are u doing? looking at error db error? yes // ok can we debug now? haan do  :P ok boss ji
    // ru here ?check desktop once yu r back ok // anything for me :P // MOni :( ? is tht wrong no i dint get light called me where? f or lunch? :P :P her office lol for what reasonn just to meed, she also informed one more senior whome we both know, just to meet eve? or now? now :( no work for her? her lunch time ok go fast then
    //  then what abt ur issues yaar :?te me what should i do using tht (? t // ok, u have sqlite manager in firefox? dont knw // ok
// empty database, table not created :( :( can we continue this thnaks re y sry
   // yu r helping // taking words back :) and u too take

public class Constants {

    public static final class HTTP {
       // public static final String BASE_URL = "http://services.hanselandpetal.com";
    }
// u checked or not? chckng ok // meanwhile let me check ur db class  // now lets check for the table creationg, can u copy that db to machine and check? how? u have connected ur device, so u caa access file manager and sd card, hagnon ok
    public static final class DATABASE {
// wait, lets debug it// wait how you ll see log here show me // which log are u talking abt?
        public static final String DB_NAME = "YoutubeVChannel";
        public static final int DB_VERSION = 2;
        public static final String TABLE_NAME = "vchannel";

        public static final String DROP_QUERY = "DROP TABLE IF EXISTS " + TABLE_NAME;
 // I guess, this will work // run again
        public static final String GET_VCHANNEL_QUERY = "SELECT * FROM " + TABLE_NAME;
    public static final String DELET_VCHANNEL_QUERY = "DELETE  FROM " + TABLE_NAME;

        public static final String PRODUCT_ID = "productId";
      //  public static final String CATEGORY = "category";
        //public static final String PRICE = "price";
        public static final String DESCRIPTION = "description";
        public static final String TITLE = "Title";
        public static final String THUMBNAIL_URL = "thumbnail_url";
        public static final String PUBLIHED_AT = "datetime";
   /* String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "("
            + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
            + KEY_PH_NO + " TEXT" + ")";*/

    public static final String CREATE_TABLE_QUERY = "CREATE TABLE " + TABLE_NAME +
                "(" + PRODUCT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + // now again, deleted// ok
// hurray but how? remember that syntax we changed hehe yes thats the reason// now lets uncomment those things ok
            // let me make it sure
            TITLE + " TEXT not null," +
                DESCRIPTION + " TEXT not null," +
                PUBLIHED_AT + " TEXT not null," +
                THUMBNAIL_URL + " TEXT not null)";
    }

    /*public static final class REFERENCE {
        public static final String VCHANNEL = Config.PACKAGE_NAME + "vchannel";
    }

    public static final class Config {
        public static final String PACKAGE_NAME = "com.loadrv.dbdemo.";
    }*/
}
