package com.example.javaonlineshop.DatabaseFiles;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.javaonlineshop.GroceryItem;

import java.util.ArrayList;

@Database(entities = {GroceryItem.class, CartItem.class}, version = 1)
public abstract class ShopDatabase extends RoomDatabase {
    public abstract GroceryItemDao groceryItemDao();
    public abstract CartItemDao cartItemDao();

    private static ShopDatabase instance;

    public static synchronized ShopDatabase getInstance(Context context) {
        if(null == instance){
            instance = Room.databaseBuilder(context, ShopDatabase.class, "shop_database")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .addCallback(initialCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback initialCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            new InitialAsyncTask(instance).execute();

        }
    };

    private static class InitialAsyncTask extends AsyncTask<Void, Void, Void>{

        private GroceryItemDao groceryItemDao;

        public InitialAsyncTask(ShopDatabase db) {
            this.groceryItemDao = db.groceryItemDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            ArrayList<GroceryItem> allItems = new ArrayList<>();
            GroceryItem milk = new GroceryItem("Milch",
                    "Besonders lecker cremig schmeckt Bärenmarke Die haltbare Milch in einer heißen Tasse Latte Macchiato, Cappuccino und Milchkaffee. Der Fettgehalt von 3,8 % verfeinert den sahnigen Kaffee-Genuss und rundet den Geschmack ab.",
                    "https://cdn02.plentymarkets.com/f3fwwhiwe7th/item/images/71811280/full/Baerenmarke-Die-haltbare-Milch-3-8--Fett--Vollmilch-mit-Drehverschluss-1l--Milch-71811280.jpg",
                    "Trinken", 2.3, 10);
            allItems.add(milk);

            GroceryItem iceCream = new GroceryItem("Ben & Jerry",
                    "Vanille-Doppelrahmglace mit Cookieteig-Stücken (mit Schokoladenstückchen) (16%) und kakaohaltigen Fettglasurstücken (1%).",
                    "https://i5.walmartimages.com/asr/6089ec90-dfea-4280-9dd2-a4e2972bf52e.9736d7870d96bd279b602f4f22a4f323.jpeg",
                    "Essen", 5.9, 15);
            allItems.add(iceCream);

            GroceryItem nivea = new GroceryItem("Nivea",
                    "Vertrauen Sie der blauen Dose für die ganze Familie. Seit über 100 Jahren pflegt und schützt die NIVEA Creme mit ihrer reichhaltigen Formel jeden Hauttypen, in jedem Alter und bei jeder Gelegenheit. Die cremige Textur mit dem beliebten Duft in der typischen Dose schützt und verwöhnt die Haut besonders unkompliziert - Denn NIVEA ist für alle da. Ein Effekt, den Sie fühlen und sehen können: NIVEA Creme Mini.",
                    "https://images-na.ssl-images-amazon.com/images/I/81CGBGpbNqL._SL1500_.jpg",
                    "Pflege", 3.5, 10);
            allItems.add(nivea);

            GroceryItem barilla = new GroceryItem("Barilla",
                    "Ein schmackhafter Teller Pasta ist ein Genuss für jeden Tag des Jahres. Aus diesem Grund verwenden wir nur den besten Weizen, Wasser und liebevolle Sorgfalt. So wird Barilla Pasta seit 1877 hergestellt.",
                    "https://images-na.ssl-images-amazon.com/images/I/41llTtim9SL.jpg",
                    "Essen", 1.65, 40);
            allItems.add(barilla);

            GroceryItem nutella = new GroceryItem("Nutella",
                    "Süß in den Tag starten \n" +
                            "Den Morgen mal süß beginnen? Mit der großen Auswahl an süßen Brotaufstrichen von REWE zaubern Sie ein Lächeln auf die Gesichter aller Schleckermäuler. Probieren Sie die Vielfalt der schokoladigen Aufstriche bis hin zur fruchtigen Konfitüre. ",
                    "https://d3el976p2k4mvu.cloudfront.net/medias/sys_master/h22/hb9/8822395600926.png",
                    "Essen", 2.99, 25);
            allItems.add(nutella);

            GroceryItem cola = new GroceryItem("Coca Cola",
                    "Kalorienfreies koffeinhaltiges Erfrischungsgetränk mit Pflanzenextrakten mit Süßungsmitteln",
                    "https://paralelaplus.rs/wp-content/uploads/2020/05/Coca-Cola-Zero-limenka-0.33l.png",
                    "Trinken", 0.69, 5);
            allItems.add(cola);

            GroceryItem gillette = new GroceryItem("Gillette Rasierer",
                    "Die Gillette Mach3 Rasierer für Männer verfügen über 3 DuraComfort Klingen, stärker als Stahl, die länger scharf bleiben (im Vergleich zu Sensor3). Mit schärferen Klingen (die ersten beiden Klingen im Vergleich zu Sensor3) ist er für 15 angenehme Rasuren entwickelt. Diese Gillette Rasierer haben einen Feuchtigkeitsstreifen für besseres Gleiten, und um Ihre Haut vor Hautreizungen zu schützen. Die mikrofeinen Hautschutz-Lamellen, spannen die Haut und bereiten das Haar auf die Rasur vor. Der Mach3 Rasierer verfügt über einen perfekt ausbalancierten ergonomischen Griff, für ein herausragendes Rasurerlebnis. Alle Mach3 Klingen können mit allen Mach3 Griffen verwendet werden.",
                    "https://www.hiper.rs/pub/media/catalog/product/cache/f8158826193ba5faa8b862a9bd1eb9e9/6/6/6606-mach-3-turbo-1-up.jpg",
                    "Pflege", 7.99, 10);
            allItems.add(gillette);

            GroceryItem axeDeo = new GroceryItem("Axe Deospray",
                    "Für authentische Typen, die ihren Weg vom Anfang bis zum Ende gehen, hat AXE dieses konsequente Bodyspray kreiert. Es kombiniert den effizienten AXE-Schutz vor Körpergeruch mit einem männlichen Duft, entdeckt von einigen der besten Duftexperten auf der Welt. Vollkommen ohne Aluminiumsalze schützt AXE Alaska seinen Träger so effektiv, dass er sich voll und ganz auf seinen eigenen Plan konzentrieren kann. ",
                    "https://m.media-amazon.com/images/I/61QBH6DZT2L._AC_SS450_.jpg",
                    "Pflege", 2.75, 30);
            allItems.add(axeDeo);

            for(GroceryItem g: allItems){
                groceryItemDao.insert(g);
            }

            return null;
        }
    }
}
