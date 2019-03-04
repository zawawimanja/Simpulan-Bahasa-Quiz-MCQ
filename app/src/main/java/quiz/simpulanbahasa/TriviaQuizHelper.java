package quiz.simpulanbahasa;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;


class TriviaQuizHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DB_NAME = "TQuiz.db";

    //If you want to add more questions or wanna update table values
    //or any kind of modification in db just increment version no.
    private static final int DB_VERSION = 3;
    //Table name
    private static final String TABLE_NAME = "TQ";
    //Id of question
    private static final String UID = "_UID";
    //Question
    private static final String QUESTION = "QUESTION";
    //Option A
    private static final String OPTA = "OPTA";
    //Option B
    private static final String OPTB = "OPTB";
    //Option C
    private static final String OPTC = "OPTC";
    //Option D
    private static final String OPTD = "OPTD";
    //Answer
    private static final String ANSWER = "ANSWER";
    //So basically we are now creating table with first column-id , sec column-question , third column -option A, fourth column -option B , Fifth column -option C , sixth column -option D , seventh column - answer(i.e ans of  question)
    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ( " + UID + " INTEGER PRIMARY KEY AUTOINCREMENT , " + QUESTION + " VARCHAR(255), " + OPTA + " VARCHAR(255), " + OPTB + " VARCHAR(255), " + OPTC + " VARCHAR(255), " + OPTD + " VARCHAR(255), " + ANSWER + " VARCHAR(255));";
    //Drop table query
    private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

    TriviaQuizHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //OnCreate is called only once
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //OnUpgrade is called when ever we upgrade or increment our database version no
        sqLiteDatabase.execSQL(DROP_TABLE);
        onCreate(sqLiteDatabase);
    }

    void allQuestion() {
        ArrayList<TriviaQuestion> arraylist = new ArrayList<>();

        arraylist.add(new TriviaQuestion(" Cikgu Tan mudah mengajar Kelas 5 Gigih kerana murid-muridnya _______", " rambang mata", "tajam akal", "otak udang", "tanda mata", "tajam akal"));

        arraylist.add(new TriviaQuestion("Bacaan ayat-ayat suci al-Quran menjadi _____ Hani di tengah malam yang sunyi.", "halwa telinga ", "hati terbuka", "lapik perut", "telinga kuali ", "halwa telinga "));

        arraylist.add(new TriviaQuestion(" Emak membuat agar-agar sebagai ______ untuk kami selepas makan.", "mulut tempayan", " banyak mulut", "pembasuh", "mulut murai", "pembasuh"));

        arraylist.add(new TriviaQuestion("Kasim tidak _______ meninggalkan emak dan ayahnya di kampung untuk berhijrah ke bandar.", "sampai hati", " sagu hati", " makan hati", "sakit hati", "sampai hati"));

        arraylist.add(new TriviaQuestion("Azizah _______ membantu ibunya menyiapkan pakaian yang ditempah pelanggan.", "bersengkang mata", "tebal telinga", " berat mata", "sekangkang kera", "bersengkang mata"));

        arraylist.add(new TriviaQuestion("Budak kecil ini ______ menjadi pemimpin negara yang disegani suatu hari nanti", "buah fikiran", " gerak hati", "ada angin", "ada bakat", "ada bakat"));

        arraylist.add(new TriviaQuestion("Mageswari ______ apabila melihat tangan anaknya yang luka parah itu.", "ikat perut", " geli hati", "mabuk selasih", "mabuk darah", "mabuk darah"));

        arraylist.add(new TriviaQuestion("Pembunuhan kejam itu telah menjadi _______ semua rakyat di Malaysia.", "buah fikiran", "curi tulang", "curi hati", "buah mulut ", "buah mulut "));

        arraylist.add(new TriviaQuestion("“Kamu mestilah kawal pemakanan kamu, sikap _______ kamu itu akan mengundang penyakit,” tegur ibu", "telinga kuali", " buruk siku", "buruk selera", " telinga tempayan", "buruk selera"));

        arraylist.add(new TriviaQuestion("Setelah kematian kedua-dua ibu bapanya Rahul _____ ke luar negara.", "mandi peluh", "bawa diri", " bawah angin", "mandi mawar", "bawa diri"));

        arraylist.add(new TriviaQuestion("Swee Ling tidak tahan melihat ibu dan ayahnya yang selalu ______ setiap kali bertemu.", "kepala batu", "bertikam lidah", "habis jodoh", " berpeluk tubuh", "bertikam lidah"));

        arraylist.add(new TriviaQuestion(" Remaja yang diculik itu ditemui sedang bersembunyi ketakutan di ______ kampung itu.", "gigi hutan", "  hilang akal", "jalan belakang", " jejak bara", "gigi hutan"));

        arraylist.add(new TriviaQuestion("Kaki Ramli luka terkena serpihan paku akibat ber _______ semasa bermain di padang.", "gigi hutan", " jejak bara", "mulut tempayan", "kaki ayam", "kaki ayam"));

        arraylist.add(new TriviaQuestion("Mak Esah tidak disukai oleh orang kampung kerana sikapnya yang suka ______ tentang keburukan orang lain", "buta huruf", "tangan kanan", "alas perut", "bawa mulut", "bawa mulut"));

        arraylist.add(new TriviaQuestion("Emak membebel sebab emak ______ tentang hidup kamu. Emak sayang kamu, Eja.", "cari salah", "ambil hati", "ambil berat", "cari makan ", "ambil berat"));

        arraylist.add(new TriviaQuestion("Mak Minah ______ untuk mencari duit demi perbelanjaan sekolah anaknya.", "habis akal", "makan duit", "diam ubi ", " hilang akal", "habis akal"));

        arraylist.add(new TriviaQuestion(" Encik Chan berjaya menangkap pekerjanya yang ______ syarikat dan tindakan tatatertib telah dilakukan.", "makan angin", "makan duit", "mabuk darah ", "mabuk selasih", "makan duit"));

        arraylist.add(new TriviaQuestion("Encik Linggam ______ apabila mendapati cermin keretanya pecah.", "kera sumbang", "lipas kudung", "diam ubi", "naik darah", "naik darah"));

        arraylist.add(new TriviaQuestion("Saya kasihan melihat emak yang ______dengan perbuatan ayah yang berkahwin lain itu.", "tebal telinga", "makan hati", "rambang mata", "panjang akal", "makan hati"));

        arraylist.add(new TriviaQuestion("Mak Munah _______ apabila anak-anaknya sudah lama tidak pulang menjenguknya.", " jalan buntu", "berat sebelah", "jauh hati", "jatuh hati ", "jauh hati"));

        arraylist.add(new TriviaQuestion(" Jikalau mahu berjaya, berusahalah bersungguh-sungguh, jangan jadi seperti ______ yang penuh dengan angan-angan.", "Abu Nawas", "raja sehari", "Abu Jahal", "Mat Jenin", "Mat Jenin"));

        arraylist.add(new TriviaQuestion("  Masalah pecah rumah yang semakin menjadi-jadi di kampung itu menjadi _______ penduduk kanpung itu.", "buah mulut", "darah daging", "bahasa pasar", "cangkul angin ", "buah mulut"));

        arraylist.add(new TriviaQuestion("Setiap hujung minggu, panggung wayang mula dipenuhi dengan ______ yang tidak mahu ketinggalan menonton cerita terbaharu.", "kaki wayang", "kaki bangku", "kaki ayam", "kaki botol", "kaki wayang"));

        arraylist.add(new TriviaQuestion("Saya tidak mahu menegur Shima yang ______ itu kerana dia akan melenting sesuka hatinya.", "berat sebelah", "kepala angin", "ambil angin", "tin kosong", "kepala angin"));

        arraylist.add(new TriviaQuestion("Ayah selalu bangun awal pagi dan keluar bekerja sebelum ______", "fajar menyinsing", "embun jantan", "durian runtuh", "hilang akal", "fajar menyinsing"));

        arraylist.add(new TriviaQuestion("Dia lulus dalam ujian memandu kerana menggunakan _______", "jalan belakang", "jalan tengah", "jalan damai", "jalan buntu", "jalan belakang"));

        arraylist.add(new TriviaQuestion("Dani ______ setelah perniagaan yang diusahakan selama 10 tahun itu musnah akibat sikapnya yang asyik berjudi.", "mabuk darah", "jatuh melarat", "mati kutu", "jatuh hati", "jatuh melarat"));

        arraylist.add(new TriviaQuestion(" Saya kasihan melihat Azman yang ______ oleh ayahnya kerana gagal dalam peperiksaan.", "kena tangan", "kusut fikiran", "mata duitan", "kutu embun", "kena tangan"));

        arraylist.add(new TriviaQuestion(" “Bila kamu hendak mencari kerja, Amir? Hendak tunggu sampai _____ baru ehndak berubah?” soal Mak Pah.", "kucing bertanduk", "kambing hitam", "telinga kuali", "lintah darat", "kucing bertanduk"));

        arraylist.add(new TriviaQuestion("Jangan ______ sangat, kamu harus lebih tegas dalam membuat keputusan.", "campur tangan", "bongkok sabut", "betul bendul", "buah fikiran", "betul bendul"));

        arraylist.add(new TriviaQuestion("Mia tidak menyenangi sikap Wati yang suka _____ dan sering menceritakan kemewahannya itu.", "angkat senjata", "batu loncatan", "bekas tangan", "angkat diri", "angkat diri"));

        arraylist.add(new TriviaQuestion("Hakimi ______ dengan mengahwini adik iparnya setelah isterinya meninggal dunia akibat barah otak.", " gelap mata", "harga mati", "ganti tikar", "hati batu", "ganti tikar"));

        arraylist.add(new TriviaQuestion("Sarimah ______ menyiapkan latihan yang diberikan oleh gurunya.", "mandi peluh", "harga mati", "memerah keringat", "memeras otak", "memeras otak"));

        arraylist.add(new TriviaQuestion("Vince telah dijatuhkan hukuman penjara atas tuduhan ______", "titik peluh", "telinga kuali", "makan suap", "minta diri", "makan suap"));

        arraylist.add(new TriviaQuestion(" _____ ibu Shah apabila mendapat tahu dia terlibat dengan najis dadah itu.", "Hidung tinggi", "Iri hati", "Hancur hati", "Besar hati", "Hancur hati"));

        arraylist.add(new TriviaQuestion(" Mariam _____ sejak kematian seluruh ahli keluarganya dalam kemalangan itu.", "titik peluh", "mandi kerbau", "makan diri", "hilang akal", "hilang akal"));

        arraylist.add(new TriviaQuestion("Eza ______ ayahnya menjadi seorang pensyarah.", "anak tunggal", "anak angkat", "bulat hati", "ikut jejak", "ikut jejak"));

        arraylist.add(new TriviaQuestion("Walaupun Jong Ai Berharta, tetapi dia _____ dan tidak mahu menghulurkan bantuan kewangan kepaa adik-beradiknya yang susah.", "sampah masyarakat", "senyum kambing", "tangkai jering", "tali barut", "tangkai jering"));

        arraylist.add(new TriviaQuestion("Izat ______ untuk membantu ibunya mengemas rumah.", "berat tulang", "cari jalan", "alas perut", "senang hati", "berat tulang"));

        arraylist.add(new TriviaQuestion("Basri kecewa kerana tidak dapat mendekati anak Mak Cik Ramlah yang ______ itu.", "jinak-jinak merpati", "tidur-tidur ayam", "ajak-ajak ayam", "hidung belang", "jinak-jinak merpati"));

        arraylist.add(new TriviaQuestion("Ayah _____ bekerja di sawah demi mencari sesuap nasi.", "membuka jalan", "memeras keringat", "mati katak", "memeras ugut", "memeras keringat"));

        arraylist.add(new TriviaQuestion("Sikap Syafiah yang sering berlembut dengan adik-adiknya me______nya apabila dia dihalau dari rumah.", "makan garam", "makan gaji", "makan duit", "makan diri", "makan diri"));

        arraylist.add(new TriviaQuestion("Sikap Lia yang suka _____ itu menyebabkan Cindy dan Liza bergaduh.", "mencuit hati", "mencuci mata", "mengambil berat", "mengadu domba", "mengadu domba"));

        arraylist.add(new TriviaQuestion("Latifiah tidak tahan dengan sikap suaminya yang _____ dan suka berfoya-foya.", "hidung belang", "gulung tikar", "kaki bangku", "harga diri", "hidung belang"));

        arraylist.add(new TriviaQuestion("Pemuda-pemuda itu ber_____ semasa menyiapkan kerja-kerja menanam di ladang kelapa sawit.", "langkah kanan", "pekak badak", "mandi peluh", "ketam batu", "mandi peluh"));

        arraylist.add(new TriviaQuestion("Amira menyediakan beberapa keping roti untuk anaknya sebagai ______ sebelum ke sekolah.", "kaki bangku", "kutu embun", "hisap darah", "lapik perut", "lapik perut"));

        arraylist.add(new TriviaQuestion("Dia segera ______ apabila rancangan merompak bank itu diketahui oleh pihak polis.", "besar kepala", "cuci tangan", "buah pena", "curi tulang", "cuci tangan"));

        arraylist.add(new TriviaQuestion("Gajah itu ______ apabila mendapati anak kesayangannya hilang.", "naik minyak", "patah arang", "untung batu", "ubat hati", "naik minyak"));

        arraylist.add(new TriviaQuestion("Tina tidak suka bercerita apa-apa kepada Julia yang terkenal sebagai _______ itu.", "mulut tempayan", "kutu embun", "kaki ayam", "pekak badak", "mulut tempayan"));

        arraylist.add(new TriviaQuestion("Harga barang-barang yang dijual di pasar raya itu ______ dan tidak boleh dirunding lagi.", "hisap darah", "makan duit", "paku belanda", "tangkai jering", "paku belanda"));

        this.addAllQuestions(arraylist);

    }


    private void addAllQuestions(ArrayList<TriviaQuestion> allQuestions) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            for (TriviaQuestion question : allQuestions) {
                values.put(QUESTION, question.getQuestion());
                values.put(OPTA, question.getOptA());
                values.put(OPTB, question.getOptB());
                values.put(OPTC, question.getOptC());
                values.put(OPTD, question.getOptD());
                values.put(ANSWER, question.getAnswer());
                db.insert(TABLE_NAME, null, values);
            }
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
            db.close();
        }
    }


    List<TriviaQuestion> getAllOfTheQuestions() {

        List<TriviaQuestion> questionsList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();
        String coloumn[] = {UID, QUESTION, OPTA, OPTB, OPTC, OPTD, ANSWER};
        Cursor cursor = db.query(TABLE_NAME, coloumn, null, null, null, null, null);


        while (cursor.moveToNext()) {
            TriviaQuestion question = new TriviaQuestion();
            question.setId(cursor.getInt(0));
            question.setQuestion(cursor.getString(1));
            question.setOptA(cursor.getString(2));
            question.setOptB(cursor.getString(3));
            question.setOptC(cursor.getString(4));
            question.setOptD(cursor.getString(5));
            question.setAnswer(cursor.getString(6));
            questionsList.add(question);
        }

        db.setTransactionSuccessful();
        db.endTransaction();
        cursor.close();
        db.close();
        return questionsList;
    }
}
