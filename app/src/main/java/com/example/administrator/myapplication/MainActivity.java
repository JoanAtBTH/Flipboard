package com.example.administrator.myapplication;

import android.content.res.AssetManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Random;


public class MainActivity extends AppCompatActivity {
    private Button button,button2,button3,button4,button5,button7;
    private ArrayList topicsArrayList;
    private final String LOG_TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.button);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        button5 = (Button) findViewById(R.id.button5);
        button7 = (Button) findViewById(R.id.button7);

        topicsArrayList = new ArrayList<String>();
        DBHelper dbHelper = DBHelper.getInstance(this);
        int n = dbHelper.select_new().getCount();
        /*Log.d(LOG_TAG, "Initialy, size(db) ---------->\t" + n);*/
        if (dbHelper.select_new().getCount() <= 1) { //first fill of db
            fill_db();      // initialize db content
        }
        Cursor cursor = dbHelper.select_topics();
        /*if (cursor != null) {
            String info = "\t>>>>>>>>>>>>>>>>>>>>>>>>>>>\n" + cursor.toString();
                    Log.d(LOG_TAG, info);
        }*/
        if (cursor != null && cursor.moveToFirst()) {
            do {
                String aux = cursor.getString(cursor.getColumnIndex(Contract.TNews.COLUMN_TOPIC));
                topicsArrayList.add(aux);
            }
            while (cursor.moveToNext());
        }

        /*Log.d(LOG_TAG, "Size arrayL topics: " + topicsArrayList.size());*/

        if (!topicsArrayList.isEmpty()) {
            //Random color = new Random();

            button.setText(topicsArrayList.get(0).toString());
            button.setTypeface(null, Typeface.BOLD);

            if (topicsArrayList.size() > 1) {
                //p.setARGB(200, color.nextInt(256), color.nextInt(256), color.nextInt(256));
                button2.setText(topicsArrayList.get(1).toString());
                button2.setTypeface(null, Typeface.BOLD);

            }
            if (topicsArrayList.size() > 2) {
                //p.setARGB(200, color.nextInt(256), color.nextInt(256), color.nextInt(256));
                button3.setText(topicsArrayList.get(2).toString());
                button3.setTypeface(null, Typeface.BOLD);

            }
            if (topicsArrayList.size() > 3) {
                //p.setARGB(200, color.nextInt(256), color.nextInt(256), color.nextInt(256));
                button4.setText(topicsArrayList.get(3).toString());
                button4.setTypeface(null, Typeface.BOLD);
                /*button4.setBackgroundColor(p.getColor());*/
                Drawable drawable = getResources().getDrawable(R.drawable.topics_sports);
            }
            if (topicsArrayList.size() > 4) {
                //p.setARGB(200, color.nextInt(256), color.nextInt(256), color.nextInt(256));
                button5.setText(topicsArrayList.get(4).toString());
                button5.setTypeface(null, Typeface.BOLD);

            }
            if (topicsArrayList.size() > 5) {
                //p.setARGB(200, color.nextInt(256), color.nextInt(256), color.nextInt(256));
                button7.setText(topicsArrayList.get(5).toString());
                button7.setTypeface(null, Typeface.BOLD);

            }
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SubcategoryActivity.class);
                intent.putExtra("category", button.getText().toString());
                if (intent.resolveActivity(getPackageManager()) != null)
                    startActivityForResult(intent, 0);
                else
                    Log.e(LOG_TAG, "Error on changing view clicking the topic");
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SubcategoryActivity.class);
                intent.putExtra("category", button2.getText().toString());
                if (intent.resolveActivity(getPackageManager()) != null)
                    startActivityForResult(intent, 0);
                else
                    Log.e(LOG_TAG, "Error on changing view clicking the topic");
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SubcategoryActivity.class);
                intent.putExtra("category", button3.getText().toString());
                if (intent.resolveActivity(getPackageManager()) != null)
                    startActivityForResult(intent, 0);
                else
                    Log.e(LOG_TAG, "Error on changing view clicking the topic");
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SubcategoryActivity.class);
                intent.putExtra("category", button4.getText().toString());
                if (intent.resolveActivity(getPackageManager()) != null)
                    startActivityForResult(intent, 0);
                else
                    Log.e(LOG_TAG, "Error on changing view clicking the topic");
            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SubcategoryActivity.class);
                intent.putExtra("category", button5.getText().toString());
                if (intent.resolveActivity(getPackageManager()) != null)
                    startActivityForResult(intent, 0);
                else
                    Log.e(LOG_TAG, "Error on changing view clicking the topic");
            }
        });
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SubcategoryActivity.class);
                intent.putExtra("category", button7.getText().toString());
                if (intent.resolveActivity(getPackageManager()) != null)
                    startActivityForResult(intent, 0);
                else
                    Log.e(LOG_TAG, "Error on changing view clicking the topic");
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        Intent intent = new Intent();

        if (id == R.id.help_item)
            intent = new Intent(MainActivity.this, HelpActivity.class);
        else if (id == R.id.subscribe_item)
            intent = new Intent(MainActivity.this, SubscribeActivity.class);
        else if (id == R.id.logout_item)
            intent = new Intent(MainActivity.this, loginActivity.class);

        //noinspection SimplifiableIfStatement
        if (intent.resolveActivity(getPackageManager()) != null)
            startActivityForResult(intent, 0);
        else
            Log.e(LOG_TAG, "Error on changing view with main options");

        return super.onOptionsItemSelected(item);
    }

    /**
     *  private methods of the class
     */

    /*
     * first fill of the database with initial content
     */
    private void fill_db() {
        DBHelper dbHelper = DBHelper.getInstance(this);

        // topics
        dbHelper.add_new("Technology", "", 0, "", "", Date.valueOf("2000-11-01"));
        dbHelper.add_new("Entertainment", "", 0, "", "", Date.valueOf("2001-12-02"));
        dbHelper.add_new("Sports", "", 0, "", "", Date.valueOf("2002-01-03"));
        dbHelper.add_new("Politics", "", 0, "", "", Date.valueOf("2003-02-04"));
        dbHelper.add_new("Life style", "", 0, "", "", Date.valueOf("2004-03-05"));
        dbHelper.add_new("Business", "", 0, "", "", Date.valueOf("2005-04-06"));

        // subcategories
        dbHelper.add_new("Technology", "Computers", 1, "", "", Date.valueOf("2000-11-01"));
        dbHelper.add_new("Technology", "SmartPhones", 1, "", "", Date.valueOf("2000-11-01"));
        dbHelper.add_new("Technology", "Automobiles", 1, "", "", Date.valueOf("2010-12-11"));
        dbHelper.add_new("Technology", "Green Technology", 1, "", "", Date.valueOf("2002-11-21"));
        dbHelper.add_new("Technology", "Information & Communication Technology", 1, "", "", Date.valueOf("2003-10-15"));

        dbHelper.add_new("Sports","Formula 1",1,"","",Date.valueOf("2000-11-01"));
        dbHelper.add_new("Sports","Cricket",1,"","",Date.valueOf("2000-11-01"));
        dbHelper.add_new("Sports","Football",1,"","",Date.valueOf("2010-12-11"));
        dbHelper.add_new("Sports","Tennis",1,"","",Date.valueOf("2002-11-21"));
        dbHelper.add_new("Sports","Badminton",1,"","",Date.valueOf("2015-10-15"));

        dbHelper.add_new("Entertainment","Music",1,"","",Date.valueOf("2000-11-01"));
        dbHelper.add_new("Entertainment","Trending",1,"","",Date.valueOf("2000-11-01"));
        dbHelper.add_new("Entertainment","Hollywood",1,"","",Date.valueOf("2010-12-11"));
        dbHelper.add_new("Entertainment","Television",1,"","",Date.valueOf("2002-11-21"));
        dbHelper.add_new("Entertainment","Celebrities",1,"","",Date.valueOf("2015-10-15"));

        dbHelper.add_new("Life style","Travel",1,"","",Date.valueOf("2000-11-01"));
        dbHelper.add_new("Life style","Food",1,"","",Date.valueOf("2000-11-01"));
        dbHelper.add_new("Life style","Fashion",1,"","",Date.valueOf("2010-12-11"));
        dbHelper.add_new("Life style","Health",1,"","",Date.valueOf("2002-11-21"));

        dbHelper.add_new("Politics","Nordic Politics",1,"","",Date.valueOf("2000-11-01"));
        dbHelper.add_new("Politics","US Politics",1,"","",Date.valueOf("2000-11-01"));
        dbHelper.add_new("Politics","East asian",1,"","",Date.valueOf("2010-12-11"));
        dbHelper.add_new("Politics","Indian politics",1,"","",Date.valueOf("2002-11-21"));

        dbHelper.add_new("Business","BBC",1,"","",Date.valueOf("2000-11-01"));
        dbHelper.add_new("Business","CNN",1,"","",Date.valueOf("2000-11-01"));
        dbHelper.add_new("Business","CNBC",1,"","",Date.valueOf("2000-11-01"));
        dbHelper.add_new("Business","Forbes",1,"","",Date.valueOf("2002-11-21"));
        dbHelper.add_new("Business","Bloomberg",1,"","",Date.valueOf("2015-10-15"));

        // apple news
        dbHelper.add_new("Technology", "Computers", 1, "This Dell 2-in-1 laptop can wirelessly charge through its keyboard/Wireless charging remains an elusive concept. It pops up here and there, often for mobile devices using special cases, and yet many solutions are painfully incomplete or too cumbersome for consumers to care. The Dell Latitude 7285, a new 2-in-1 business laptop announced yesterday at CES, uses a clever method to bypass wireless charging’s pernicious roadblocks to deliver a true solution. Dell claims it’s the first fully fleshed-out version of the feature for laptops.\n" +
                "The trick is in the keyboard. The Latitude 7285 is a 2-in-1 hybrid. That means 100 percent of its components fit inside the tablet display, which can be attached to one of three accompanying keyboards to create a clamshell laptop. One of these keyboards communicates with Dell’s new wireless charging pad. So when you place the 7285 down on the mat with the keyboard attached, the power bypasses the keyboard and goes straight to the computer. It’s wireless charing with a caveat, but wireless charging nonetheless.\n", "", Date.valueOf("2000-11-01"));
        dbHelper.add_new("Technology", "SmartPhones", 1, "What Samsung expects from iPhone 8 /" +
                "Samsung works pretty hard to make sure its products offer more or less the same features as Apple's iPhone, so the rumors speak volumes here.One way Samsung tries to compete is to match the iPhone rumors with the features of its own flagship phones, which it rushes to market to remain competitive.In my opinion this means that when we look at what Samsung is expected to ship we are also looking at what Samsung thinks Apple may be working on. Given both firms have connections across each other’s supply chains, I think it’s a supportable theory." +
                "\nWhat are Samsung’s plans?\n" +
                "The Samsung v. Apple struggle is good for consumers (other than when devices are rushed to market in order to explode), but it also means that when you look at the rumors you’re also getting a good insight into what Samsung thinks the next iPhone will look like.", "", Date.valueOf("2000-11-01"));
        dbHelper.add_new("Technology", "Automobiles", 1, "2016 Honda Accord V-6/Over the last few years, Honda has hinted at a revival of some of its past glory. The upcoming Civic Si and Type R, as well as the NSX, prove that Honda is producing fun cars again. Yet, it’s the manual 2016 Accord Coupe V-6 that cements our opinion. It has enough character — and oomph — to carry Honda’s performance credibility by itself. It’s a car that deserves more press, adoration, and we can’t help but love it.\n" +
                "This car’s only negatives are the seat and seating position. The power-adjustable front seat cushioning is soft and comfortable on long drives; however, the actual seat sits far too high off the car’s floor plan for taller individuals. Drivers of “vertically abnormal” height have to heavily angle the seat back, nearly 60 degrees, as you’ll knock your head on the roof every time the car hits a bump.\n" +
                "Furthermore, the height of the seat makes it impossible to get your legs comfortable in relation to the depth of the dash. It extends too far towards the floor, and you end up sitting like you’re on a horse, bow-legged. If Honda were to drop the seat 2 to 3 inches — which it can most definitely do — both issues would be solved.\n" +
                "Even though the seat and seating position aren’t perfect, the rest of the car makes up for this unfortunate error.", "", Date.valueOf("2010-12-11"));
        dbHelper.add_new("Technology", "Green Technology", 1, "Go Green: Technology That Puts The Environment First / For a long time, recycling was the extent of most people’s efforts to help the environment. In recent years, however, images of oil spills, devastated forests, and melting polar ice caps have flooded the mainstream media and communicated to an increasingly concerned public the declining health of their planet. Many have responded with a heightened sense of responsibility, including developers of technology who, in response to the information technology boom of the past 20 years, have begun to search for viable alternatives that will not only continue to advance the technological gains already made, but also minimize the impacts of those technologies on the environment. The result has been the development of a field called green technology (also known as “green tech” or “clean technology”), which allows you and I to make even more choices in our everyday lives to help preserve the health of our environment. Here is an overview of this up-and-coming field, plus the latest designs in green technology.", "", Date.valueOf("2002-11-21"));
        dbHelper.add_new("Technology", "Information & Communication Technology", 1, "Indian Govt to bring information and communication policy: I&B minister Venkaiah Naidu/The Centre will bring out an Information and Communication policy which will be formulated in consultation with states, Information and Broadcasting minister M Venkaiah Naidu today said. \n" +
                "Speaking at a conference of state information ministers and top officials, he also announced a cash component of Rs 1 crore for the 'Most Film Friendly State' award that is given at the national awards. \n" +
                "The senior minister noted that while the government has a policy on areas rang .. \n", "", Date.valueOf("2003-10-15"));

        dbHelper.add_new("Sports","Formula 1",1,"Formula 1's debt-ridden Manor Racing go into administration /LONDON (AP) The operating company of Manor Racing was placed in administration on Friday, putting in major doubt the British-based Formula One team's participation this year.\n" +
                "Manor, which finished 11th and last in the constructors' championship last season, has been seeking new investors, and administrator FRP Advisory said there was ''a limited window of opportunity'' for new owners before preseason testing and the first race in Melbourne on March 26.\n" +
                "Geoff Rowley, a joint administrator and partner at FRP Advisory, said, ''During recent months, the senior management team has worked tirelessly to bring new investment to the team to secure its long-term future, but regrettably has been unable to do so within the time available.\n" +
                "''Therefore, they have been left with no alternative but to place Just Racing Services Limited into administration.''\n" +
                "Manor Grand Prix Racing Ltd, the sister company of JRSL, which has the rights for the team's participation in F1, was not in administration, which is designed to protect insolvent companies from their creditors.\n" +
                "FRP said no redundancies have been made following JRSL's entering into administration, and all 212 staff were paid in full to the end of December.\n" +
                "Manor, formerly known as Marussia, which went into administration in late 2014, was in discussions with investor groups throughout last year, and agreed on terms of a sale to an Asian investment consortium in December, only for the deal to fall through.\n" +
                "German driver Pascal Wehrlein secured the team's only point last year with a 10th-place finish in Austria. But in the the penultimate race in Brazil, Manor was overtaken by Sauber in the standings following Felipe Nasr's ninth-place finish, a result that was reported to have cost Manor some $30 million in prize money.\n" +
                "Stephen Fitzpatrick, owner of Manor Racing since 2015, said in a letter to staff on Friday that it was a ''disappointing end to a two-year journey for Manor.''\n","",Date.valueOf("2000-11-01"));
        dbHelper.add_new("Sports","Cricket",1,"Full schedule of India-England Test, ODI, T20I series (November 2016 to February 2017)/The opening Test of the series will be held in Rajkot from November 9. The ODI rubber starts in Pune on January 15, 2017. The T20I series begins in Kanpur on January 26.\n" +
                "After the conclusion of the Test series on December 20, England will return home for Christmas and New Year break. They will be back in India in January 2017 for the limited overs leg of the tour.\n" +
                "\"The Board of Control for Cricket in India (BCCI) and England Cricket Board (ECB) announced the schedule for the forthcoming England's tour of India during the months of November 2016 - February 2017. \n" +
                "\"England will play a five-match Test series followed by three One-Day Internationals and three T20 Internationals,\" BCCI secretary Ajay Shirke said in a media advisory. \n" +
                "Before England arrive, India will be playing against New Zealand at home in September and October. The teams will face off in 3 Tests and 5 ODIs. India will play 13 Tests at home in their 2016-17 season. Bangladesh and Australia are the other two countries set to tour.\n","",Date.valueOf("2000-11-01"));
        dbHelper.add_new("Sports","Football",1,"Wayne Rooney equals record as Manchester United ease past Reading/Wayne Rooney became Manchester United’s joint record scorer with Sir Bobby Charlton on 249, 4,484 days after his first goal for the club as the holders began their FA Cup defence with this convincing victory.\n" +
                "Reading arrived managed by a United former favourite in Jaap Stam and left having had scant chance of doubling their victory tally over these opponents, the only previous win coming in the competition in January 1927.\n" +
                "This was United’s eighth straight win in all competitions and stretched their unbeaten sequence to 14 to leave José Mourinho particularly pleased. He said: “Very professional. I could feel it from the first minute. When we had two big chances to score, the team was intense, dynamic, playing well. For a long time the game was still open at 2-0 but I’m really happy with our attitude.”\n" +
                "The day belonged to Rooney. Seven minutes had been played when he made history. The captain was making a first appearance following three games out due to a thigh injury. The 31-year-old had already offered one classy moment when controlling a high ball in Reading’s area. A shot saved by Ali al-Habsi followed and this proved an augury of what was about to occur.\n" +
                "The strike that took this finest of footballers equal with Charlton also impressed as it displayed Rooney’s quicksilver brain. Anthony Martial made a run along the left, pulled the ball back to Juan Mata and from a mishit effort Rooney improvised to use a right knee to beat Habsi.\n" +
                "Cue a celebration that consisted of a few steps, then a triumphant punch of the air. In the posh seats, members of United’s board stood to applaud and Charlton wore a broad smile. All around the ground the old cry of “Rooney, Rooney” went up to hail a run that began with a Champions League hat-trick against Fenerbahce here on 28 September 2004.\n" +
                "The Liverpudlian was running the show for United as in days of yore. In their last outing in this stadium, on New Year’s Eve, Martial had been the outstanding performer. Then, he scored a late equaliser in a 2-1 win over Middlesbrough. Now, 15 minutes were gone when he struck – and he could thank Rooney. The French winger executed a slick one-two with the United captain, ran down the inside-left channel and gave Habsi zero chance with a right-foot finish.\n","",Date.valueOf("2010-12-11"));
        dbHelper.add_new("Sports","Tennis",1,"Qatar Open: Sir Andy Murray to face Novak Djokovic in final/Murray won 6-3 6-4 against Czech Berdych, who needed treatment on an ankle injury after the first set.\n" +
                "It will be the 19th ATP final meeting between Murray and the man he replaced as world number one in November.\n" +
                "Second seed Djokovic survived five match points on his way to beating Fernando Verdasco in his semi-final.\n" +
                "Murray, who won the tournament in 2008 and 2009, has now recorded 28 consecutive victories in ATP Tour matches.\n" +
                "\"I want to try and keep it going - I feel a little bit like this year's a fresh start,\" he told Eurosport.\n" +
                "\"It's been the perfect week to get ready for the Australian Open.\"\n" +
                "Earlier, Serb Djokovic made only one unforced error in the decider to win 4-6 7-6 (9-7) 6-3 after Spaniard Verdasco, ranked 42nd in the world, controlled the first two sets.\n" +
                "\"It's definitely one of the most exciting matches I have played,\" Djokovic said. \"I haven't saved five match points many times. He should have finished it off.\"\n" +
                "You can follow live coverage of the Qatar Open final in Doha between Murray and Djokovic on the BBC Sport website from 15:00 GMT.\n","",Date.valueOf("2002-11-21"));
        dbHelper.add_new("Sports","Badminton",1,"Nobody can understand how tough my life is: Carolina Marin/Carolina Marin looks possessed when on the court - lunging, diving, jumping and screaming as she dismantles her rivals. Off it, she is more like the girl next door - relaxed, friendly, sporting a smile that can light up a room. The shuttle star from Spain, who has two World and an Olympic title to her name, spent an hour in the TOI office as our Guest Sports Editor. She spoke with passion and conviction, breaking into laughter every now and then. She loves to be in control on the court but on Thursday she let her guard down and spoke about what makes her the badminton player to beat, and how she still craves for the simple things in life...\n" +
                "Excerpts from an interview...\n" +
                "You come from a country where badminton is not a mass sport. Tell us something about your journey as a player...\n" +
                "I started playing badminton when I was eight years old. I was also learning flamenco (a popular dance form) then. I went to a badminton hall one day with a friend and I really liked what I saw. It was a bit of a strange sport in my country because we are used to watching tennis. I liked hitting the shuttle.","",Date.valueOf("2015-10-15"));

        dbHelper.add_new("Entertainment","Music",1,"Drake, Chainsmokers Lead 2017 iHeartRadio Music Awards Noms/The iHeartRadio Music Awards nominations have been annouced. The awards are dominated hip-hop artist Drake, who earned 12 nominations, including Male Artist of the year, and the group The Chainsmokers, who grabbed 11 nods, including the inaugural Best New Pop Artist category. Beyonce and Adele also earned several nominations each. The award show has a new new category this year as well, for Best Music Video, where Rihanna, Ariana Grande and more will probably face off.","",Date.valueOf("2000-11-01"));
        dbHelper.add_new("Entertainment","Trending",1,"Carrie Fisher's ashes carried in 'a giant Prozac pill/Carrie Fisher, who has been laid to rest alongside her mother Debbie Reynolds, was adored by family, friends and fans for her gallows humour and frank talk about her struggles with mental illness.\n" +
                "What better home for her ashes then, her brother Todd Fisher and daughter Billie Lourd decided, than a porcelain urn in the shape of an outsized anti-depressant?\n" +
                "\"Carrie's favourite possession was a giant Prozac pill that she bought many years ago,\" Todd Fisher said Friday as he left the private joint funeral at Forest Lawn - Hollywood Hills for his mother and sister. \"She loved it, and it was in her house, and Billie and I felt it was where she'd want to be,\" he said.\n" +
                "Carrie Fisher's daughter posts touching tribute \n" +
                "Other than Carrie Fisher's cremation and unique urn, nearly no details were revealed about the ceremony, or about what form the two women's graves took.\n" +
                "They'll have plenty of celebrity company at the sprawling, hillside cemetery just across the Los Angeles River from Warner Bros. and Disney studios, including Bette Davis, Lucille Ball, Dick Van Patten, Liberace and Florence Henderson.","",Date.valueOf("2000-11-01"));
        dbHelper.add_new("Entertainment","Hollywood",1,"China Is Mad About Hollywood Remakes/BEIJING—Aiming to crack new frontiers in China, Hollywood studios are turning to something familiar: established American films and TV series that can be remade for Chinese audiences.\n" +
                "Lions Gate Entertainment Corp., based in Santa Monica, CA, is in talks to produce a Chinese-language sequel of its magic-themed “Now You See Me” films, which were popular in China after first screening in 2013. Shooting has already begun on a Chinese...","",Date.valueOf("2010-12-11"));
        dbHelper.add_new("Entertainment","Television",1,"Carrie Fisher And Debbie Reynolds Celebrated In HBO Doc/The HBO documentary Bright Lights, starring Hollywood legends Debbie Reynolds and her daughter Carrie Fisher airs on Saturday night -- weeks before its original March premiere date. This past Christmas Fisher suffered a heart attack and passed away days later. Her mother, 84-year-old Reynolds, died the following day \"after expressing her desire to be with her daughter.","",Date.valueOf("2002-11-21"));
        dbHelper.add_new("Entertainment","Celebrities",1,"Last year really WAS a terrible year to be a celebrity: Number of famous deaths in 2016 is only expected once every 200 years/Last year really was a terrible year to be famous, according to statistics.\n" +
                "It certainly seemed like 2016, the year that started with the death of David Bowie and ended with George Michael, saw a greater number famous deaths than usual.\n" +
                "Some argued there could be other explanations for the ‘curse’ of 2016, like a greater number of ageing celebrities meaning we should expect more deaths.\n" +
                "But now a mathematician has analysed the year and concluded the number of famous deaths was much greater than could be expected – a fluctuation that statistically is only expected once every two hundred years.\n" +
                "The world lost icons from all genres, with the deaths of stars such as David Bowie, Sir Terry Wogan, Victoria Wood and singer Prince last year year.\n" +
                "One statistician decided to use mathematics to work out whether last year was a statistical anomaly, or business as usual.\n","",Date.valueOf("2015-10-15"));

        dbHelper.add_new("Life style","Travel",1,"World's First Solar Road Opens in Normandy, France./France has just opened what it claims is the first public solar panel road in the world, officials said on Thursday, NBC News reported. \n" +
                "The French Ministry of the Environment announced the inauguration of the \"unprecedented\" new road on Thursday, which is covered by solar panels and stretches for more than half mile in the town of Tourouvre-au-Perche in Normandy, France. ","",Date.valueOf("2000-11-01"));
        dbHelper.add_new("Life style","Food",1,"Prepare Your Taste Buds: McDonald's Is Now Offering a Chicken and Waffles Sandwich/Get excited — like, really excited. McDonald's is now offering Chicken McGriddles, an all-day breakfast menu addition inspired by everyone's favorite hangover cure, chicken and waffles. The tasty hybrid will feature a crispy McChicken patty nestled between two maple-flavored McGriddle cakes for an on-the-go version of a delectable brunch mainstay. Is your mouth watering yet? Chicken McGriddles will officially be available in select restaurants in Florida until April 26, 2017. But don't fret if you don't have a vacation planned for down south this year — this combo is technically a \"secret menu item\" that foodies can hack by ordering a Sausage McGriddle with a crispy chicken patty instead. Phew!\n" +
                "This appetizing news comes after the recent announcement that the fast food chain would begin testing delivery service in the Sunshine State starting in January 2017. Those Floridians sure do love their Mickey D's!","",Date.valueOf("2000-11-01"));
        dbHelper.add_new("Life style","Fashion",1,"Award-Season-Worthy Gowns From The SS17 Shows./Chanel to the Oscars, Gucci to the Baftas, Dior to the SAGs - what the stars wear to Hollywood's annual awards is often the most discussed point of the evening. In the last round of shows, designers from across the globe presented a plentiful array of options for those nominated and attending to choose from. Here's what Vogue is hoping to see on the red carpet in the coming weeks...","",Date.valueOf("2010-12-11"));
        dbHelper.add_new("Life style","Health",1,"Is popcorn healthy?/Whether popcorn is \"healthy\" depends how you eat it.\n" +
                "In its purest form -- that is, plain air-popped kernels -- it's a healthy, whole-grain, antioxidant-rich snack food that comes at a pretty low-calorie cost for those who like to mindlessly nibble: A three-cup serving of air-popped popcorn has only 93 calories, 1 gram of fat and close to 4 grams of fiber.\n" +
                "But movie theater popcorn, which is popped in coconut oil before salt and a \"buttery topping\" are added, is a distant cousin to its more clean-living relative.\n" +
                "According to a report from the Center for Science in the Public Interest, a medium bucket of popcorn holds 20 cups and contains 1,200 calories, 980 milligrams of sodium and 60 grams of saturated fat, or about three days worth. Even if you ate only 3 cups (probably unlikely), you would still consume about half a day's worth of saturated fat.\n" +
                "If you're buying pre-popped popcorn in supermarkets, be sure to check nutrition labels, as serving sizes, sodium and sugars vary. Kettle corn, for example, can have up to 4 teaspoons of sugar per 1¼-cup serving.\n" +
                "But Skinny Pop's original popcorn has zero grams of sugar and close to 4 cups per serving. Sodium levels can range too, from 75 milligrams for Skinny Pop's original popcorn to 310 milligrams for Wise's white cheddar version.\n" +
                "If you're popping at home in the microwave, choose light or lower-fat versions. Limit the amount of salt and butter you add, and consider adding herbs such as basil, oregano or red pepper flakes -- or even Parmesan cheese instead.","",Date.valueOf("2002-11-21"));

        dbHelper.add_new("Politics","Nordic Politics",1,"Elections in Finland.The Centre Party holds./AS EXPECTED, Finnish voters on Sunday turned out their country's Conservative-led government and its pro-European prime minister, Alex Stubb. The opposition Centre Party came in a clear first, with 21% of the vote. But the verdict seemed more an expression of economic frustration and a rejection of the current government than an endorsement of a new one. Only the smallest of margins separated the runner-up parties: the Conservatives won 18%, the Finns Party 17.6%, and the Social Democrats 16.5%. Finns waited until the last minute to make up their minds. Less than a week before the election, more than 40% of voters were still undecided.\n" +
                "Finland has a long tradition of broad coalition governments, and Finns often say that they prefer to elect their politicians and then leave them alone to do their jobs. But voters have grown unusually fed up with the current government, which started out with six parties and now has four. The coalition is seen as being too broad and incapable of agreeing on necessary economic reforms. It has also failed in a major effort to reform the healthcare sector.\n" +
                "Juha Sipila, a businessman who took over as Centre Party leader in 2012, kept his campaign light on specifics. He is expected to keep his governing programme as vague as possible, to leave room for manoeuvre as he forms a coalition. But he will have a delicate job in handling the populist, anti-immigrant Finns Party. In 2011 the Finns Party tied for second place with 19% of the vote, but Timo Soini, the party's leader, chose to stay out of government rather than condone bail-out programmes for Greece and other peripheral euro-zone countries. Some in Brussels worry that if Mr Soini joins the coalition this time, Finland could make further Greek rescue programmes impossible, possibly forcing Greece out of the euro.","",Date.valueOf("2000-11-01"));
        dbHelper.add_new("Politics","US Politics",1,"Auto industry's Trump fear: 'Everyone dreads being subject of a tweet'/“I like the car I’m in now. It’s a Chevrolet Suburban. Made in the USA,” Donald Trump told the Detroit News last year when the then presidential hopeful was asked to name his favorite car from his 100-plus vehicle fleet that includes a scissor-door Lamborghini Diablo and a 1956 Rolls-Royce Silver Cloud.\n" +
                "'Just the beginning': Trump may disrupt 20-year boom for Mexico auto industry\n" +
                "For General Motors, Chevy’s owner, it was nice PR, the latest in a series of endorsements from the Trumps that reach back like a stretch limo to the Cadillac Trump’s landlord father Fred used to pick up his rent checks. In the 1980s Donald Trump even worked on building a Trump-branded Cadillac, complete with VCR and paper shredder. The Trumps may be fans of the US’s largest car manufacturer but GM, and the car industry in general, should have been paying more attention to the final sentence of his pre-election endorsement: “Made in the USA.”\n" +
                "Trump hit out at the car industry last week like a drive-by shooter, firing off a series of angry tweets about their outsourcing of US jobs. Those tweets will be the hottest topic this week as Detroit’s annual car jamboree, the North American International Auto Show, gets started. It’s a chance for the industry to show off all its latest products and for its executives to address the media about the future. Trump’s blimp-like shadow is overhanging the event as executives and analysts fret about how this overtly interventionist president will use the bully pulpit of his office, and his Twitter account, to try and force a radical change in the way they do business.\n" +
                "“Pretty much everybody is dreading being the subject of a tweet. Getting hauled out into the court of public opinion with virtually no warning is not something anybody wants to get engaged with,” said Kristin Dziczek, director of the industry, labor and economics group at the Michigan-based Center for Automotive Research (CAR).","",Date.valueOf("2000-11-01"));
        dbHelper.add_new("Politics","East asian",1,"China ready to step up scrutiny of US firms if Trump starts feud: sources/China is prepared to retaliate should President-elect Donald Trump take punitive measures against Chinese goods and trigger a trade war between the world’s two biggest economies, according to people familiar with the matter.\n" +
                "Options include subjecting well-known U.S. companies or ones with large Chinese operations to tax or antitrust probes, said the people, asking not to be identified because the matter isn’t public. Other possible measures include anti-dumping investigations and scaling back government purchases of American products, the people said.\n" +
                "The move illustrates how the fallout from escalating tensions between the two nations risks spreading to companies. Trump has made China a frequent target of his attacks and nominated trade-related officials who the Communist Party’s Global Times newspaper said would form an \"iron curtain\" of protectionism.\n" +
                "Any retaliation by China against Trump stands to be risky. A backlash may result in China damaging access to its biggest trading partner, said Michael Every, head of financial markets research at Rabobank Group in Hong Kong.\n" +
                "\"When you have a country with a large trade deficit that retaliates against a country with a large trade surplus with it, it’s the country with the trade deficit that wins,\" said Every. \"The country with the surplus loses, every time.\"\n" +
                "America’s trade deficit with China narrowed to $31.1 billion from $32.5 billion in October as U.S. exports to the nation were the strongest since December 2013, according to the most recent data available. That brought the trade deficit to $288.78 billion for the 10 months to the end of October.","",Date.valueOf("2010-12-11"));
        dbHelper.add_new("Politics","Indian politics",1,"BJP gets battle ready with Modi, surgical strikes and demonetisation in its arsenal/Prime Minister Narendra Modi’s charisma and two of his actions taken in the closing months of 2016 — Indian Army’s strike across the Line of Control (LoC) at terror launch pads in Pakistan occupied Kashmir (PoK) and demonetisation — make for a recipe for success for the Bharatiya Janata Party (BJP) in the upcoming Assembly elections scheduled to take place between February and March.\n" +
                "The proceedings of the two-day national executive meet of BJP in New Delhi is a clear pointer to that. To that add the party’s pro-poor tilt or Antodyaya, a term championed by its foremost ideologue Deen Dayal Upadhaya whose birthday it is celebrating, and the BJP leadership feels that its public narrative to seek yet another popular endorsement for the party and Modi is complete.\n" +
                "A major part of party president Amit Shah’s speech and the political resolution passed at the meet are focussed on those same issues.\n" +
                "Around the time when one thought that demonetization was the singular most important issue, agitating public mind and creating political discourse, Shah got back to talking at length about strikes across LoC, an action which inflamed passion and had swung national mood to assertive nationalism.\n" +
                "If with the passage of time, people forgot Rahul Gandhi’s khoon ki dalali charge against Modi and Arvind Kejriwal’s demand for submission of proof by the BJP government for strikes across LoC, the BJP leadership made a conscious attempt make these a part of the electoral discourse. Remember, how popular passion had flown high and both Gandhi and Kejriwal had to face the brunt of popular opinion for making such undesirable remarks.\n" +
                "Out of five states which are going to the polls, with the exception of Uttar Pradesh, the BJP and Congress are pitted against each other in Goa, Punjab, Manipur and Uttrakhand. The Aam Admi Party is making the fight triangular in Goa and Punjab. Now, the Indian army connect is very strong in Punjab and Uttrakhand and Uttar Pradesh has seen the arrival of coffins of martyred soldiers due to terror attacks and firings along the LoC.\n" +
                "The BJP, however, is conscious of keeping referring to the valor of the armed forces. Its political resolution said “the surgical strikes executed with utmost precision by our armed forces have shown to the world that India is led by a government today that is committed to securing its borders and interests with determination and will power. The national executive congratulates the prime minister and his other concerned ministerial colleagues for the bold decision of responding appropriately to the terror tactics of our recalcitrant neighbour. We salute our brave soldiers for valiantly fighting and laying down their lives to secure our borders and honour. That the strikes have had the required effect on our neighbour can be gauged from the fact that there is near normal silence on the Indo-Pak border in the last few weeks\".","",Date.valueOf("2002-11-21"));

        dbHelper.add_new("Business","BBC",1,"Trump says Toyota will face tariffs on cars made in Mexico's/Trump has spent the entire week ordering auto makers to transfer their compact vehicle production out from Mexico. \n" +
                "Donald Trump took credit for Ford's decision to keep operations in the USA instead of Mexico. \n" +
                "Toyota (TM) has already broken ground for the new factory, which is actually in Mexico's Bajio region, not Baja. \n" +
                "While some manufacturers are clearly reconsidering their overseas investments and looking into expanding their manufacturing operations in the United States, it is not clear that such developments will lead to significant job creation. \n" +
                "On Tuesday, Ford announced that it had scrapped plans to build a new $1.6 billion small-car factory in San Luis Potosi, Mexico, and will instead invest some of that money in a USA factory near Detroit that will build new electric and autonomous vehicles. The Mexican peso declined nearly a percent in response.After Ford, Mr. Trump targeted General Motors' plant in Mexico, saying GM needed to be taxed for certain versions of the Chevrolet Cruze small vehicle that it imports from Mexico to USA dealers. \n" +
                "Toyota's US corporation released a statement on January 5 that the volume of production and employment in the United States would not be cut down with the construction of a new plant in Mexico. President-elect Donald Trump speaks at a Victory Tour Rally, on December 8, 2016 in Des Moines, Iowa. The Ontario plant will build more higher-priced midsize vehicles.","",Date.valueOf("2000-11-01"));
        dbHelper.add_new("Business","CNN",1,"SpaceX gets green light days before scheduled launch/SpaceX's Falcon 9 rocket will launch with 10 Iridium NEXT satellites. According to the satellite maker, the launch is scheduled for Monday at 10:22 am PST, barring complications due to weather.\n" +
                "SpaceX hasn't attempted to launch a rocket since September 1, when the exercise ended in disaster. The rocket exploded, destroying itself and a pricey Facebook satellite made by Israeli company Spacecom.Last week, Elon Musk's SpaceX said that it was ready to launch on Sunday, January 8, but conceded it had not yet received a necessary license from the Federal Aviation Administration.\n" +
                "\"SpaceX applied for a license to launch the Iridium NEXT satellites from Vandenberg Air Force Base\" in California, the FAA said in a statement on Friday. \"The FAA has granted a license for that purpose.\"The launch is now scheduled for Monday, January 9.\n" +
                "\n" +
                "The launch will once again attempt to take a satellite into space -- 10 satellites, actually.\n" +
                "\"The Iridium team has been anxiously awaiting launch day, and we're now all the more excited to send those first ten Iridium NEXT satellites into orbit,\" Iridium CEO Matt Desch said in a statement.Those satellites are designed to increase the company's speed and bandwidth. Each satellite will also host an aircraft tracking and surveillance system made by flight tracking company Aireon.\n" +
                "After the September explosion, SpaceX, NASA, the FAA, the U.S. Air Force and the National Transportation Safety Board launched a probe into the cause of the blast. On Monday, SpaceX said the investigation had concluded, and blamed a failed pressure vessel in a liquid oxygen tank for the failure.The FAA said Friday it \"accepted the investigation report... and has closed the investigation.\"","",Date.valueOf("2000-11-01"));
        dbHelper.add_new("Business","CNBC",1,"Economic forecasting in crisis: Bank of England chief economist/The Bank of England's (BoE) Chief Economist bemoaned the inherent risks of placing too much faith in economic forecasting given the failure to foresee the financial crash in 2008 as well as the Brexit vote.\n" +
                "Andrew Haldane conceded the economics profession was in \"some degree of crisis\", when speaking at the Institute for Government on Thursday.\n" +
                "However, he reiterated the central banks view that the U.K. economy would suffer in 2017 as a consequence of the country's vote to leave the European Union (EU).\n" +
                "\n" +
                "\"It's a fair cop to say the (economic) profession is to some degree in crisis,\" Haldane said.\"I'm not someone who would say that all that's been done in the past is terrible. It's just that the models we had were rather narrow and fragile. The problem came when the world was tipped upside down and those models were ill-equipped to making sense of behaviors that were deeply irrational,\" he added.\n" +
                "The BoE's chief economist noted the apparent \"disconnect\" between the historically high levels of political uncertainty and the \"remarkably placid\" response evident in the financial markets at present.\n" +
                "Before the referendum took place in June 2016, BoE Governor Mark Carney warned that the likelihood of a \"technical recession\" would be superior if the majority of citizens voted to leave the EU. Instead, the U.K.'s economy outperformed all other advanced economies in 2016.\n" +
                "More recently, Britain's benchmark stock index, the FTSE 100, extended its record breaking streak on Thursday as the index closed higher for the sixth consecutive day for the first time in two decades.Moreover, the U.K.'s services sector grew at its fastest pace in almost 18 months in December. The Markit/CIPS services purchasing manager's index (PMI) hit 56.2, its highest level since July 2015.","",Date.valueOf("2000-11-01"));
        dbHelper.add_new("Business","Forbes",1,"First Volkswagen Manager Goes To Jail For Diesel Gate/Volkswagen’s dieselgate scandal is beginning to cost the personal freedom of its managers. A South Korean court sentenced a Volkswagen executive to 18 months in jail for fudging the type approval of Volkswagen vehicles, Reuters said. According to the court, \"Volkswagen has by itself undermined its credibility as a global brand as a result of this crime which has caused grave social and economic damages.\"\n" +
                "The executive was only identified by his surname, Yun, or Yoon, depending on the transcription from Korean. A 52-year-old Yoon was arrested in Seoul in July of last year. Last year, South Korea’s Environment Ministry also filed a criminal suit against Johannes Thammer, managing director of Audi Volkswagen Korea. That suit appears to still be pending. According to Germany’s Manager Magazin, if Thammer wants to travel outside the country, his wife has to stay behind, thereby securing Thammer’s return to Seoul.\n" +
                "Meanwhile in the U.S., Volkswagen engineer James R. Liang is awaiting his sentencing. Last September, Liang pleaded guilty after being charged with conspiring to commit wire fraud and violating U.S. clean air laws. Liang cut a deal with prosecutors, and turned into a cooperative witness. In order to “allow more time for defendant’s cooperation in the investigation,\" Liang’s sentencing, originally scheduled for February 1, was delayed until May 3 a few days ago.\n" +
                "Volkswagen managers worrying about their career ending behind bars already cause major headaches in the company’s far-flung global operations. Managers sent abroad want to get home ASAP, “and many are reluctant to accept a foreign posting,” a contact at Volkswagen told me. For the first time, Volkswagen’s board from CEO Matthias Mueller on down will skip the Detroit Auto show that will open its doors on Sunday. Volkswagen offered Reuters the rather meek excuse that because there is no Volkswagen Group Night, the board doesn’t need to go. “It’s not about not going, it’s about not coming back,” my Wolfsburg contact quipped.\n" +
                "The EU and South Korea entered a trade agreement in 2011, and instead of the EU being swamped with even more Korean cars, the opposite happened. Previously a closed market, South Korea developed “a voracious appetite for imported cars,” as Reuters put it. Especially in high demand were German high end cars of the kind Hyundai and Kia did not want to produce. That demand received a damper. Sales of imports in South Korea were down 6.5% last year, mostly due to a stop sale on 80 Volkswagen models in August of 2016.","",Date.valueOf("2002-11-21"));
        dbHelper.add_new("Business","Bloomberg",1,"The Strong Dollar Could Bash the Economy—and It’s Just Getting Started./The headlines are full of scary reports about the dollar’s rise to a 14-year high against a basket of six major currencies. Its strength will hurt U.S. manufacturing while triggering capital flight from emerging markets, economists say. The appreciation “is a real serious noose around the neck of the global economy,” David Beckworth, a senior research fellow at the Mercatus Center at George Mason University in Arlington, Va., said in November.\n" +
                "What’s really alarming, though, is that even though the dollar has jumped 6 percent against the euro and 12 percent against the yen since the U.S. presidential election, it remains well below its historic highs. If its rise to date is causing trouble, imagine how much worse things could get if it went on a serious upward run.\n" +
                "The chart below shows how much headroom remains for the greenback. It’s the Federal Reserve’s index of the value of the dollar against a basket of currencies of 26 trading partners, with each one’s value adjusted for that nation’s inflation rate. This is a better indicator of the dollar’s strength than the frequently cited U.S. Dollar Index, which covers just six currencies and isn’t adjusted for inflation. The Fed’s index remains 10 percent below its 2002 high and fully 19 percent below the lofty high of 1985, which led to an emergency international accord to lower the greenback’s value through official, coordinated sales of dollar reserves.\n" +
                "Fundamental factors are driving the dollar upward. Because U.S. growth is strong and unemployment low, Fed policymakers are projecting three more quarter-point increases in short-term rates in 2017. That will tend to push up the dollar by making U.S. Treasurys and other fixed-income investments more lucrative. Investors are also betting President-elect Donald Trump will touch off a growth spurt through tax cuts and infrastructure investment.","",Date.valueOf("2015-10-15"));
    }
}
